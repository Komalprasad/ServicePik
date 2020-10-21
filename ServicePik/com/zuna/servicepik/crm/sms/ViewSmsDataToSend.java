package com.zuna.servicepik.crm.sms;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuna.servicepik.daoImpl.CustomerSmsImpl;
import com.zuna.servicepik.daoImpl.SmsTemplateImpl;
import com.zuna.servicepik.pojo.CustomerSms;
import com.zuna.servicepik.pojo.SmsTemplate;

public class ViewSmsDataToSend {
	
	private static Logger logger=LoggerFactory.getLogger(ViewSmsDataToSend.class);
	
	public String viewSmsData(String mobileNumber,String name){
		
		String mobileNumberArray[]=mobileNumber.split(",");
		String nameArray[]=name.split(",");
		
		JSONObject sendResponse=new JSONObject();
		
		for(int index=0;index<nameArray.length;index++){
			
			String customerNumber =mobileNumberArray[index];
			String customerName =nameArray[index];
			
			JSONObject dataObject=new JSONObject();
			dataObject.put(Constant.MOBILE, customerNumber);
			dataObject.put(Constant.NAME, customerName);
			sendResponse.append(Constant.SMS, dataObject);
			
		}
		
		sendResponse=this.smsTemplate(sendResponse);
		
		return sendResponse.toString();
	}

	
	private JSONObject smsTemplate(JSONObject sendResponse){
		
		List<SmsTemplate> smsTemplateList=new SmsTemplateImpl().findAllTemplate();
		
		
		if(!smsTemplateList.isEmpty()||smsTemplateList!=null){
			
			for(SmsTemplate smsTemplate :smsTemplateList){
				JSONObject templateName=new JSONObject();
				templateName.put(Constant.NAME,smsTemplate.getTemplateName());
				sendResponse.append(Constant.TEMPLATELIST, templateName);
				
				JSONObject messageObject=new JSONObject();
				messageObject.put(Constant.TEMPLATEMESSAGE, smsTemplate.getTemplateMessage());
				sendResponse.put(smsTemplate.getTemplateName(), messageObject);
				
			}
		}
		
		JSONObject customObject=new JSONObject();
		customObject.put(Constant.NAME, Constant.CUSTOM);
		sendResponse.append(Constant.TEMPLATELIST, customObject);
		
		JSONObject messageObject=new JSONObject();
		messageObject.put(Constant.TEMPLATEMESSAGE, Constant.CUSTOMMESSAGE);
		sendResponse.put(Constant.CUSTOM, messageObject);
		
		return sendResponse;
	}
	
}
