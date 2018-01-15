package com.hys.exam.struts.form;

import com.hys.exam.model.system.SystemCard;
import com.hys.framework.web.form.BaseForm;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-19
*/
@SuppressWarnings("serial")
public class SystemCardForm extends BaseForm{

	//学习卡
	private SystemCard systemCard = new SystemCard();

	public SystemCard getSystemCard() {
		return systemCard;
	}

	public void setSystemCard(SystemCard systemCard) {
		this.systemCard = systemCard;
	}
	
}


