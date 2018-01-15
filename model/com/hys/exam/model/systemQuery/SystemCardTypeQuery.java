package com.hys.exam.model.systemQuery;

import com.hys.exam.model.system.SystemCardType;

/**
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-17
*/
@SuppressWarnings("serial")
public class SystemCardTypeQuery extends SystemCardType {

	//总数
	private Integer allNum;
	
	//已使用
	private Integer userdNum;
	
	//剩余数量
	private Integer remainNum;
	

	public Integer getAllNum() {
		return allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getUserdNum() {
		return userdNum;
	}

	public void setUserdNum(Integer userdNum) {
		this.userdNum = userdNum;
	}

	public Integer getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

}


