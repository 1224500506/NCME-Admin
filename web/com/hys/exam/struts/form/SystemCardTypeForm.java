package com.hys.exam.struts.form;

import com.hys.exam.model.system.SystemCardType;
import com.hys.framework.web.form.BaseForm;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-19
*/
@SuppressWarnings("serial")
public class SystemCardTypeForm extends BaseForm{

	//学习卡类型
	private SystemCardType systemCardType = new SystemCardType();

	public SystemCardType getSystemCardType() {
		return systemCardType;
	}

	public void setSystemCardType(SystemCardType systemCardType) {
		this.systemCardType = systemCardType;
	}
}


