package com.hys.exam.struts.form;

import com.hys.exam.model.systemQuery.SystemCardOrderQuery;
import com.hys.framework.web.form.BaseForm;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-19
*/
@SuppressWarnings("serial")
public class SystemCardOrderForm extends BaseForm{

	//卡订单
	private SystemCardOrderQuery orderQuery = new SystemCardOrderQuery();
	
	private String method ;


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public SystemCardOrderQuery getOrderQuery() {
		return orderQuery;
	}

	public void setOrderQuery(SystemCardOrderQuery orderQuery) {
		this.orderQuery = orderQuery;
	}

}


