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
@Table(name="sp_sms_template")
public class SmsTemplate {

	private Integer templateId;
	private String templateName;
	private String templateMessage;
	private Boolean active;
	private Date createDate;
	
	public SmsTemplate(){}
	
	public SmsTemplate(String templateName,String templateMessage,Boolean active,Date createDate){
		
		this.templateName=templateName;
		this.templateMessage=templateMessage;
		this.active=active;
		this.createDate=createDate;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="template_id", nullable=false,unique=true)
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	
	@Column(name="template_name")
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	@Column(name="template_message")
	public String getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(String templateMessage) {
		this.templateMessage = templateMessage;
	}
	
	@Column(name="active")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
}
