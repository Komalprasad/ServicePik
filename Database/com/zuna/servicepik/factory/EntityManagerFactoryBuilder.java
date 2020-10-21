package com.zuna.servicepik.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryBuilder {
	
	public static EntityManagerFactory entityManagerFactory;

	public static EntityManager createEntityManager() {
		
		entityManagerFactory=Persistence.createEntityManagerFactory("servicepikDB");
		
		return entityManagerFactory.createEntityManager();
	}
	
}
