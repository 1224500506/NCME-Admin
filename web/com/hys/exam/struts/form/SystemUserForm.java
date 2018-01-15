package com.hys.exam.struts.form;

import com.hys.exam.model.SystemUser;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 form
 * 
 * 说明:
 */
public class SystemUserForm extends BaseForm{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 189625609171665011L;
	
	private String method ;
	
	private SystemUser model = new SystemUser();
	
	public SystemUser getModel() {
		return model;
	}
	public void setModel(SystemUser model) {
		this.model = model;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
