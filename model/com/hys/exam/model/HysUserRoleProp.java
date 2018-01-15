package com.hys.exam.model;

import java.io.Serializable;

public class HysUserRoleProp  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userid;
	private Long roleid;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

}
