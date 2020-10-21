package com.zuna.servicepik.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuna.servicepik.daoInt.CustomerSmsInt;
import com.zuna.servicepik.factory.EntityManagerFactoryBuilder;
import com.zuna.servicepik.pojo.CustomerSms;


public class CustomerSmsImpl implements CustomerSmsInt{
	
	private static Logger logger=LoggerFactory.getLogger(CustomerSmsImpl.class);

	public CustomerSms persist(CustomerSms persistInstance) {
		// TODO Auto-generated method stub
		
		EntityManager entityManager=null;
		try{
			
			entityManager=EntityManagerFactoryBuilder.createEntityManager();
			
			entityManager.getTransaction().begin();
			entityManager.persist(persistInstance);
			entityManager.getTransaction().commit();
			
			return persistInstance;
			
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		
		return null;
	}

}
