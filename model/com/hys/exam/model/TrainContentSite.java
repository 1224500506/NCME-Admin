package com.hys.exam.model;

/**
 * 
 * 标题：站点信息表
 * 
 * 作者：陈明凯 2013-4-9
 * 
 * 描述：
 * 
 * 说明:
 */
public class TrainContentSite extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8599088433782114025L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private String siteName;
	/**
	 * 
	 */
	private String siteLogo;
	/**
	 * 
	 */
	private String applicationId;
	/**
	 * 
	 */
	private Long siteVisit;
	/**
	 * 
	 */
	private String siteFoot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteLogo() {
		return siteLogo;
	}

	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Long getSiteVisit() {
		return siteVisit;
	}

	public void setSiteVisit(Long siteVisit) {
		this.siteVisit = siteVisit;
	}

	public String getSiteFoot() {
		return siteFoot;
	}

	public void setSiteFoot(String siteFoot) {
		this.siteFoot = siteFoot;
	}

}
