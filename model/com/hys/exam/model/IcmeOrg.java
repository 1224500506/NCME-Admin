/**
 *
 * <p>ICEM_ORG</p>
 * @author chenlaibin
 * @version 1.0 2014-9-19
 */

package com.hys.exam.model;

@SuppressWarnings("serial")
public class IcmeOrg extends BaseObject{

	/**
	 * 主键ID
	 */
	private Long id ;
	
	/**
	 * 机构名称
	 */
	private String orgName ;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}


