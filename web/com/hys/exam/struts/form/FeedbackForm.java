package com.hys.exam.struts.form;


import com.hys.exam.model.Advert;
import com.hys.exam.model.Feedback;
import com.hys.framework.web.form.BaseForm;

public class FeedbackForm extends BaseForm{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8016002330696496831L;

	private String method ;
	
	private Feedback model = new Feedback();

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Feedback getModel() {
		return model;
	}

	public void setModel(Feedback model) {
		this.model = model;
	}

	

}
