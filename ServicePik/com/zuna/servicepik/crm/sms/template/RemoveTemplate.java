package com.zuna.servicepik.crm.sms.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuna.servicepik.daoImpl.SmsTemplateImpl;
import com.zuna.servicepik.pojo.SmsTemplate;

public class RemoveTemplate {

	private static Logger logger=LoggerFactory.getLogger(RemoveTemplate.class);
	
	public String removeTemplate(String templateId){
		logger.info("Entering removeTemplate Parameter "+templateId);
		
		
		
		SmsTemplate smsTemplate =new SmsTemplateImpl().findById(Integer.valueOf(templateId));
		
		logger.info("Deleting Template "+smsTemplate.getTemplateName());
		
		new SmsTemplateImpl().remove(smsTemplate);
		
		return "success";
	}
	
}
