package org.opennaas.extensions.gim.controller;

/*
 * #%L
 * GIM :: GIModel and APC PDU driver
 * %%
 * Copyright (C) 2007 - 2014 Fundació Privada i2CAT, Internet i Innovació a Catalunya
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Date;
import java.util.List;

import org.opennaas.extensions.gim.model.core.entities.sockets.PowerReceptor;
import org.opennaas.extensions.gim.model.energy.Energy;
import org.opennaas.extensions.gim.model.load.MeasuredLoad;
import org.opennaas.extensions.gim.model.log.PowerMonitorLog;

public class RouterWithPDUPowerController extends AbstractPowerController {

	private IConsumerController			consumerController;
	private String						consumerId;
	private AbstractPDUPowerController	pduController;

	/**
	 * @return the consumerController
	 */
	public IConsumerController getConsumerController() {
		return consumerController;
	}

	/**
	 * @param consumerController
	 *            the consumerController to set
	 */
	public void setConsumerController(IConsumerController consumerController) {
		this.consumerController = consumerController;
	}

	/**
	 * @return the consumerId
	 */
	public String getConsumerId() {
		return consumerId;
	}

	/**
	 * @param consumerId
	 *            the consumerId to set
	 */
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	/**
	 * @return the pduController
	 */
	public AbstractPDUPowerController getPduController() {
		return pduController;
	}

	/**
	 * @param pduController
	 *            the pduController to set
	 */
	public void setPduController(AbstractPDUPowerController pduController) {
		this.pduController = pduController;
	}

	@Override
	public MeasuredLoad getCurrentPowerMetrics() throws Exception {
		// causes pdu to update model with current values
		List<PowerReceptor> receptors = consumerController.getPowerReceptors(getConsumerId());
		for (PowerReceptor receptor : receptors) {
			// ignore not attached receptors
			if (receptor.getAttachedTo() != null) {
				pduController.getCurrentPowerMetrics(receptor.getAttachedTo().getId());
			}
		}

		// calculates aggregated MeasuredLoad from last values
		return consumerController.getCurrentPowerMetrics(getConsumerId());
	}

	@Override
	public PowerMonitorLog getPowerMetricsByTimeRange(Date from, Date to) throws Exception {
		return null;
	}

	@Override
	public boolean getPowerStatus() throws Exception {
		// ask pdu for current power status (don't trust last read status)
		List<PowerReceptor> receptors = consumerController.getPowerReceptors(getConsumerId());
		for (PowerReceptor receptor : receptors) {
			// ignore not attached receptors
			if (receptor.getAttachedTo() != null) {
				pduController.getPowerStatus(receptor.getAttachedTo().getId());
			}
		}
		return consumerController.getPowerStatus(getConsumerId());
	}

	@Override
	public boolean powerOn() throws Exception {
		List<PowerReceptor> receptors = consumerController.getPowerReceptors(getConsumerId());
		for (PowerReceptor receptor : receptors) {
			// ignore not attached receptors
			if (receptor.getAttachedTo() != null) {
				pduController.powerOn(receptor.getAttachedTo().getId());
			}
		}
		return true;
	}

	@Override
	public boolean powerOff() throws Exception {
		List<PowerReceptor> receptors = consumerController.getPowerReceptors(getConsumerId());
		for (PowerReceptor receptor : receptors) {
			// ignore not attached receptors
			if (receptor.getAttachedTo() != null) {
				pduController.powerOff(receptor.getAttachedTo().getId());
			}
		}
		return true;
	}

	@Override
	public Energy getAggregatedEnergy() throws Exception {
		return consumerController.getAggregatedEnergy(consumerId);
	}

	@Override
	public double getAggregatedPricePerEnergyUnit() throws Exception {
		return consumerController.getAggregatedEnergyPrice(consumerId);
	}

}
