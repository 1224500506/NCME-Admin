package com.hys.exam.struts.form;

import com.hys.exam.model.SystemClient;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-18
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemClientForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 189625609171665011L;
	private String method ;
	
	private SystemClient model = new SystemClient();
	
	public SystemClient getModel() {
		return model;
	}
	public void setModel(SystemClient model) {
		this.model = model;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	 

	
	
}
