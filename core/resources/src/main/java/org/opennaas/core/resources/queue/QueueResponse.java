package org.opennaas.core.resources.queue;

/*
 * #%L
 * OpenNaaS :: Core :: Resources
 * %%
 * Copyright (C) 2007 - 2014 Fundació Privada i2CAT, Internet i Innovació a Catalunya
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.opennaas.core.resources.action.ActionResponse;
import org.opennaas.core.resources.action.ActionResponse.STATUS;
import org.opennaas.core.resources.action.IAction;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QueueResponse {

	public ActionResponse	prepareResponse	= new ActionResponse();

	public ActionResponse	confirmResponse	= new ActionResponse();

	public ActionResponse	restoreResponse	= new ActionResponse();

	public ActionResponse	refreshResponse	= new ActionResponse();

	Vector<ActionResponse>	responses		= new Vector<ActionResponse>();

	public String			user;

	public long				totalTime		= 0;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int	timestamp;

	public ActionResponse getPrepareResponse() {
		return prepareResponse;
	}

	public void setPrepareResponse(ActionResponse prepareResponse) {
		this.prepareResponse = prepareResponse;
	}

	public ActionResponse getConfirmResponse() {
		return confirmResponse;
	}

	public void setConfirmResponse(ActionResponse confirmResponse) {
		this.confirmResponse = confirmResponse;
	}

	public Vector<ActionResponse> getResponses() {
		return responses;
	}

	public void setResponses(Vector<ActionResponse> responses) {
		this.responses = responses;
	}

	public static QueueResponse newQueueResponse(List<IAction> actions) {
		QueueResponse queueResponse = new QueueResponse();
		queueResponse.setPrepareResponse(ActionResponse.newPendingAction(QueueConstants.PREPARE));
		queueResponse.setConfirmResponse(ActionResponse.newPendingAction(QueueConstants.CONFIRM));
		queueResponse.setRestoreResponse(ActionResponse.newPendingAction(QueueConstants.RESTORE));
		queueResponse.setRefreshResponse(ActionResponse.newPendingAction(QueueConstants.REFRESH));

		Vector<ActionResponse> responses = new Vector<ActionResponse>();
		for (IAction action : actions)
			responses.add(ActionResponse.newPendingAction(action.getActionID()));
		queueResponse.setResponses(responses);

		return queueResponse;

	}

	public ActionResponse getRestoreResponse() {
		return restoreResponse;
	}

	public void setRestoreResponse(ActionResponse restoreResponse) {
		this.restoreResponse = restoreResponse;
	}

	public ActionResponse getRefreshResponse() {
		return refreshResponse;
	}

	public void setRefreshResponse(ActionResponse refreshResponse) {
		this.refreshResponse = refreshResponse;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public boolean isOk() {
		for (ActionResponse action : responses)
		{
			if (action.getStatus() == STATUS.ERROR)
				return false;
		}
		return getConfirmResponse().getStatus().equals(STATUS.OK) && getRefreshResponse().getStatus().equals(STATUS.OK);
	}
	/* status connection */
	// TODO ADD necessary parametes which we will need

}
