package com.zuna.servicepik.pojo;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="sp_customer_sms")
public class CustomerSms {
	
	private Integer customerSmsId;
	private String mobileNumber;
	private String customerName;
	private String message;
	private Date sentDate;
	
	public CustomerSms(){
		
	}
	
	public CustomerSms(String mobileNumber,String customerName,String message,Date sentDate){
		this.mobileNumber=mobileNumber;
		this.customerName=customerName;
		this.message=message;
		this.sentDate=sentDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="sp_customer_sms_id", nullable=false,unique=true)
	public Integer getCustomerSmsId() {
		return customerSmsId;
	}

	public void setCustomerSmsId(Integer customerSmsId) {
		this.customerSmsId = customerSmsId;
	}
	
	@Column(name="mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String string) {
		this.mobileNumber = string;
	}

	@Column(name="customer_name")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name="message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sent_date",length=19)
	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	
	
	
	
	
}

