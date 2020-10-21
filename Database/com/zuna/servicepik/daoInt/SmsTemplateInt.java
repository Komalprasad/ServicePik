package com.zuna.servicepik.daoInt;

import java.util.List;

import com.zuna.servicepik.pojo.SmsTemplate;

public interface SmsTemplateInt {

	
	SmsTemplate persist (SmsTemplate persistInstance);
	
	
	SmsTemplate merge(SmsTemplate mergeInstance);
	
	void remove (SmsTemplate removeInstance);
	
	SmsTemplate findById(int templateId);
	
	List<SmsTemplate> findAllTemplate();
	
}
