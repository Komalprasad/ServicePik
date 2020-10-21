package com.zuna.servicepik.crm.sms.template;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuna.servicepik.daoImpl.SmsTemplateImpl;
import com.zuna.servicepik.pojo.SmsTemplate;

public class ShowTemplateData {
	
	private static Logger logger=LoggerFactory.getLogger(ShowTemplateData.class);
	
	public String showTemplateData(){
		
		JSONObject sendResponse=new JSONObject();
		List<SmsTemplate> smsTemplateList=new SmsTemplateImpl().findAllTemplate();
		
		for(SmsTemplate smsTemplate : smsTemplateList){
			
			JSONObject templateObject=new JSONObject();
			templateObject.put(Constant.TEMPLATEID, smsTemplate.getTemplateId()+"");
			templateObject.put(Constant.TEMPLATENAME, smsTemplate.getTemplateName());
			
			sendResponse.append(Constant.TEMPLATELIST, templateObject);
			
			JSONObject messageObject=new JSONObject();
			messageObject.put(Constant.TEMPLATEMESSAGE, smsTemplate.getTemplateMessage());
			sendResponse.put(smsTemplate.getTemplateId()+"", messageObject);
			
		}
		
		JSONObject customObject=new JSONObject();
		customObject.put(Constant.TEMPLATEID, "0");
		customObject.put(Constant.TEMPLATENAME, Constant.CUSTOM);
		sendResponse.append(Constant.TEMPLATELIST, customObject);
		
		JSONObject messageObject=new JSONObject();
		messageObject.put(Constant.TEMPLATEMESSAGE, Constant.CUSTOMMESSAGE);
		sendResponse.put("0", messageObject);
		
		return sendResponse.toString();
	}

}
