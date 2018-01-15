package com.hys.exam.struts.form;

import com.hys.exam.model.system.SystemIndustry;
import com.hys.framework.web.form.BaseForm;

/**
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午9:42:24
*/
@SuppressWarnings("serial")
public class SystemIndustryForm extends BaseForm{

	private SystemIndustry industry = new SystemIndustry();
	
	private String method ;

	public SystemIndustry getIndustry() {
		return industry;
	}

	public void setIndustry(SystemIndustry industry) {
		this.industry = industry;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}


