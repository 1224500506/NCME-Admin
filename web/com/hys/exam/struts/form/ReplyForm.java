package com.hys.exam.struts.form;

import com.hys.exam.model.Reply;
import com.hys.framework.web.form.BaseForm;

public class ReplyForm extends BaseForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2828330633845931773L;

	private String method ;
	
	private Reply model = new Reply();

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Reply getModel() {
		return model;
	}

	public void setModel(Reply model) {
		this.model = model;
	}


}
