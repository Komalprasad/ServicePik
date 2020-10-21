package com.zuna.servicepik.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.zuna.servicepik.daoInt.SmsTemplateInt;
import com.zuna.servicepik.factory.EntityManagerFactoryBuilder;
import com.zuna.servicepik.pojo.SmsTemplate;

public class SmsTemplateImpl implements SmsTemplateInt{

	public SmsTemplate persist(SmsTemplate persistInstance) {
		EntityManager entityManager=null;
		try{
			
			entityManager=EntityManagerFactoryBuilder.createEntityManager();
			entityManager.getTransaction().begin();
			
			entityManager.persist(persistInstance);
			entityManager.getTransaction().commit();
			
			return persistInstance;
		}catch(Exception exp){
			
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		return null;
	}

	public SmsTemplate merge(SmsTemplate mergeInstance) {
		EntityManager entityManager=null;
		try{
			entityManager=EntityManagerFactoryBuilder.createEntityManager();
			entityManager.getTransaction().begin();
			
			SmsTemplate smsTemplate =entityManager.merge(mergeInstance);
			entityManager.getTransaction().commit();
			
			return smsTemplate;
		}catch(Exception exp){
			
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		return null;
	}

	public void remove(SmsTemplate removeInstance) {
		EntityManager entityManager=null;
		try{
			entityManager=EntityManagerFactoryBuilder.createEntityManager();
			entityManager.getTransaction().begin();
			
			entityManager.remove(entityManager.contains(removeInstance) ? removeInstance : 
				entityManager.merge(removeInstance));
			
			entityManager.getTransaction().commit();
			
		}catch(Exception exp){
			
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		
	}

	public List<SmsTemplate> findAllTemplate() {
		EntityManager entityManager=null;
		try{
			entityManager=EntityManagerFactoryBuilder.createEntityManager();
			entityManager.getTransaction().begin();
			
			CriteriaBuilder builder=entityManager.getCriteriaBuilder();
			CriteriaQuery<SmsTemplate> query=builder.createQuery(SmsTemplate.class);
			Root<SmsTemplate> root=query.from(SmsTemplate.class);
			
			CriteriaQuery<SmsTemplate> allData=query.select(root);
			allData.where(builder.equal(root.get("active"), true));
			
			TypedQuery<SmsTemplate> typedQuery=entityManager.createQuery(query);
			List<SmsTemplate> smsTemplateList=typedQuery.getResultList();
			
			entityManager.getTransaction().commit();
			
			return smsTemplateList;
		}catch(Exception exp){
			
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		return null;
	}

	public SmsTemplate findById(int templateId) {
		EntityManager entityManager=null;
		try{
			entityManager=EntityManagerFactoryBuilder.createEntityManager();
			entityManager.getTransaction().begin();
			
			CriteriaBuilder builder=entityManager.getCriteriaBuilder();
			CriteriaQuery<SmsTemplate> query=builder.createQuery(SmsTemplate.class);
			
			Root<SmsTemplate> root=query.from(SmsTemplate.class);
			CriteriaQuery<SmsTemplate> allData=query.select(root);
			
			allData.where(builder.equal(root.get("templateId"), templateId));
			
			TypedQuery<SmsTemplate> typedQuery=entityManager.createQuery(query);
			
			List<SmsTemplate> smsTemplate =typedQuery.getResultList();
			if(smsTemplate.isEmpty()|| smsTemplate==null){
				return null;
			}
			
			return smsTemplate.get(0);
			
		}catch(Exception exp){
			
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		
		
		return null;
	}

}
