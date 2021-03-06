package org.opennaas.extensions.vnmapper.model;

/*
 * #%L
 * OpenNaaS :: VNMapper Resource
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

import java.util.ArrayList;
import java.util.List;

import org.opennaas.core.resources.IModel;
import org.opennaas.core.resources.SerializationException;

/**
 * 
 * @author Elisabeth Rigol
 * 
 */
public class VNMapperModel implements IModel {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4002472167559948067L;

	@Override
	public List<String> getChildren() {
		return new ArrayList<String>();
	}

	@Override
	public String toXml() throws SerializationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
