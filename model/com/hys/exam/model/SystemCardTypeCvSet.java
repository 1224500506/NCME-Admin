package com.hys.exam.model;

import java.io.Serializable;

public class SystemCardTypeCvSet implements Serializable  {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	private Integer CARDTYPE_ID;
	private Long CV_SET_ID;
	
	
	public Integer getCARDTYPE_ID() {
		return CARDTYPE_ID;
	}
	public void setCARDTYPE_ID(Integer cARDTYPE_ID) {
		CARDTYPE_ID = cARDTYPE_ID;
	}
	public Long getCV_SET_ID() {
		return CV_SET_ID;
	}
	public void setCV_SET_ID(Long cV_SET_ID) {
		CV_SET_ID = cV_SET_ID;
	}
	
}
