package com.hys.exam.struts.form;

import com.hys.exam.model.Message;
import com.hys.framework.web.form.BaseForm;

public class MessageForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4057895893146370846L;
	private String method;
	private Message model = new Message();
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Message getModel() {
		return model;
	}
	public void setModel(Message model) {
		this.model = model;
	}

	
}
