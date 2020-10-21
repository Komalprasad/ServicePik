package com.zuna.servicepik.crm.sms.template;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuna.servicepik.daoImpl.SmsTemplateImpl;
import com.zuna.servicepik.pojo.SmsTemplate;

public class SaveTemplate {
	
	private static Logger logger=LoggerFactory.getLogger(SaveTemplate.class);
	
	public String saveTemplate(String name,String message,String templateId){
		logger.info("Entering saveTemplate Parameter "+name+" -> "+message+" -> "+templateId);
		
		if(templateId.equalsIgnoreCase("0"))
		{
		try {
			SmsTemplate smsTemplate = new SmsTemplate();
			smsTemplate.setActive(true);
			smsTemplate.setCreateDate(new Date(System.currentTimeMillis()));
			smsTemplate.setTemplateMessage(message);
			smsTemplate.setTemplateName(name);
			
			SmsTemplate saveTemplate = new SmsTemplateImpl().persist(smsTemplate);
			logger.debug("Template Saved Id " + saveTemplate.getTemplateId());
			return "success";
		} catch (Exception exp) {
			logger.info("Error in saving " + exp.getMessage());
			return "fail";
		}
		}else{
			logger.info("Merge Template");
			
			SmsTemplate smsTemplate =new SmsTemplateImpl().findById(Integer.valueOf(templateId));
			smsTemplate.setTemplateMessage(message);
			
			SmsTemplate mergeSmsTemplate=new SmsTemplateImpl().merge(smsTemplate);
			logger.info("Merged "+mergeSmsTemplate.getTemplateName());
			return "success";
		}

	}

}
