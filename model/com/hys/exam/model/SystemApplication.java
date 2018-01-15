package com.hys.exam.model;


/**
 * 
 * 标题：用户信息
 * 
 * 作者：张伟清 2013-1-24
 * 
 * 描述：应用
 * 
 * 说明:
 */
public class SystemApplication extends BaseObject {

	private static final long serialVersionUID = -2897255851089732589L;

	/**
	 * 主键ID
	 */
	private Long id ;
	
	/**
	 * 应用名称
	 */
	private String applicationName ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
