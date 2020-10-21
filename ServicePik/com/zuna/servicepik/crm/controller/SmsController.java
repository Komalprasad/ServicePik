package com.zuna.servicepik.crm.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zuna.servicepik.crm.sms.ViewSmsDataToSend;
import com.zuna.servicepik.crm.sms.template.RemoveTemplate;
import com.zuna.servicepik.crm.sms.template.SaveTemplate;
import com.zuna.servicepik.crm.sms.template.ShowTemplateData;

@Controller
@RequestMapping("/sms")
public class SmsController {
	
	private static Logger logger=LoggerFactory.getLogger(SmsController.class);
	
	String globalCustomer;
	
	@RequestMapping(value ="/view") 
	public String view(@RequestParam String mobileNumberList, @RequestParam String nameList,Model model){
		
		String sendResponse =new ViewSmsDataToSend().viewSmsData(mobileNumberList, nameList);
		
		model.addAttribute("customerData",sendResponse);
		
		return "sms-view";
	}
	
	@RequestMapping(value="/sendsms",method=RequestMethod.POST)
	public void sendSms(HttpServletRequest request,HttpServletResponse response ,Model model){
		
		String message =request.getParameter("message");
		
		String smsData=request.getParameter("customerData");
	
		
		String sendResponse="Sms Already Sent";
		
//		sendResponse=new SendSms().sendSms(globalCustomer, message);
		
		globalCustomer=null;
		
		
		try {
			response.getWriter().write(sendResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/template/view")
	public String viewTemplate(Model model){
		
		String sendResponse=new ShowTemplateData().showTemplateData();
		
		model.addAttribute("templateData" , sendResponse);
		return "template-view";
	}
	
	@RequestMapping("/template/save")
	public void saveTemplate(HttpServletRequest request,HttpServletResponse response){
		String templateName =request.getParameter("templateName");
		String templateMessage=request.getParameter("templateMessage");
		String templateId=request.getParameter("templateId");
		
		String sendResponse =new SaveTemplate().saveTemplate(templateName, templateMessage,templateId);
		
		try {
			response.getWriter().write(sendResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/template/delete")
	public void removeTemplate(HttpServletRequest request,HttpServletResponse response){
		String templateId =request.getParameter("templateId");
		
		String sendResponse= new RemoveTemplate().removeTemplate(templateId);
		
		try {
			response.getWriter().write(sendResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
