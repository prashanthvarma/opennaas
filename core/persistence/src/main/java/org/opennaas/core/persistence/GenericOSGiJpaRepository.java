package org.opennaas.core.persistence;

/*
 * #%L
 * OpenNaaS :: Core :: JPA Persistence
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

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * GenericJPARepository that gets the EntityManagerFactory from the OSGi registry
 * 
 * @author eduardgrasa
 * @author Isart Canyameres Gimenez (i2cat)
 * 
 * @param <T>
 * @param <ID>
 */
public class GenericOSGiJpaRepository<T, ID extends Serializable> extends GenericJpaRepository<T, ID> {

	private static Log				logger	= LogFactory.getLog(GenericOSGiJpaRepository.class);

	private EntityManagerFactory	entityManagerFactory;

	public GenericOSGiJpaRepository() {
		super();
	}

	/**
	 * @return the entityManagerFactory
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * @param entityManagerFactory
	 *            the entityManagerFactory to set
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void initializeEntityManager() throws Exception {

		if (getEntityManager() == null) {
			initializeEntityManagerFromFactory();
		}

		if (getEntityManager() == null)
			throw new PersistenceException("Required EntityManager is null");
	}

	/**
	 * Workaround for https://issues.apache.org/jira/browse/ARIES-796
	 * 
	 * This method supports loading the entityManager from an injected entityManagerFactory. entityManagerFactory can easily be injected using
	 * blueprint normal injection, which is not affected by ARIES-796 issue, even when a property-placeholder is being used.
	 */
	private void initializeEntityManagerFromFactory() {
		if (entityManagerFactory != null) {
			setEntityManager(entityManagerFactory.createEntityManager());
		}
	}

	public void close() {
		EntityManager entityManager = getEntityManager();
		if (getEntityManager() != null) {
			logger.debug("Closing entity manager: " + getEntityManager());
			entityManager.close();
			setEntityManager(null);
		}
	}
}
