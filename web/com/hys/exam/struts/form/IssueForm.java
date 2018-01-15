package com.hys.exam.struts.form;

import com.hys.exam.model.ContentIssue;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：培训平台
 * 
 * 作者：郭津 2016-11-29
 * 
 * 描述：通知公告信息 form
 * 
 * 说明:
 */
public class IssueForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String method ;
	
	private ContentIssue model = new ContentIssue();

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ContentIssue getModel() {
		return model;
	}

	public void setModel(ContentIssue model) {
		this.model = model;
	}

	
}
