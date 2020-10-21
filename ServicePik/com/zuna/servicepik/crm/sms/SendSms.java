package com.zuna.servicepik.crm.sms;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuna.servicepik.daoImpl.CustomerSmsImpl;
import com.zuna.servicepik.pojo.CustomerSms;

public class SendSms {
	
	static HttpURLConnection connection;
	
	private static Logger logger=LoggerFactory.getLogger(SendSms.class);
	
	public String sendSms(String customerData,String message){
		logger.info("Entering sendSms Parameter "+customerData +" -> "+message);
		
		if(customerData.isEmpty()|| message.isEmpty() ||customerData==null){
			logger.debug("Empty List");
			return "Please check you data, Empty List";}
		
		JSONArray customerDataJSONArray=new JSONObject(customerData).getJSONArray("Sms");
		
		for(int index=0;index<customerDataJSONArray.length();index++){
			
			JSONObject dataObject=customerDataJSONArray.getJSONObject(index);
			
			String name=dataObject.getString(Constant.NAME);
			String mobile=dataObject.getString(Constant.MOBILE);
			
			String responseFromSending=this.sendingSms(mobile, message);
			
			if(responseFromSending.equalsIgnoreCase("success")){
				this.saveDataInDB(name, mobile, message);
			}
			logger.info(name +" -> "+mobile +" -> "+"Sms Send Success");
		}
		
		return "success";
	}
	
	
	private synchronized String sendingSms(String mobile,String message){
		
		logger.debug("Entering sendingSms Parameter "+mobile +" -> "+message);
		
		String messageValue = "message=" + message + "&sendto=" + mobile
				+ "&username=SERVICEPIK1&password=532758&sender=SVCPIK";
		byte[] messageArray = messageValue.getBytes(StandardCharsets.UTF_8);

		URL url;
		try {
			url = new URL("http://sms.thinkbuyget.com/api.php");

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/html");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(5000);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(messageArray.length));
			connection.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.write(messageArray);
			connection.getContentLength();
			connection.disconnect();
			
			logger.debug("Message Sent Successfully Returning");
			return "success";
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.info("Error in URL Returning"+e.getMessage());
			return "error";

		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Error in URL Returning "+e.getMessage());
			return "error";

		}
	}

	
	private synchronized String saveDataInDB(String name,String mobile,String message){
		logger.debug("Entering saveDataInDB Parameter "+name +" -> "+mobile+" -> "+message);
		
		try {
			CustomerSms customerSms = new CustomerSms();
			customerSms.setCustomerName(name);
			customerSms.setMobileNumber(mobile);
			customerSms.setSentDate(new Date(System.currentTimeMillis()));
			customerSms.setMessage(message);
			
			
			CustomerSms saveCustomerSms = new CustomerSmsImpl().persist(customerSms);
			logger.debug(saveCustomerSms.getCustomerName());

		} catch (Exception exp) {
			logger.info("Erorr in Saving "+exp.getMessage());
		}
		return null;
	}
	
}
