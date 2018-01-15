package com.hys.auth.model;

/**
 * 
 * 标题：
 * 
 * 作者：zdz
 * 
 * 描述：
 * 
 * 说明:
 */
public abstract class AbstractObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -739673598590749394L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
