package com.hys.exam.model.system;

import java.util.Date;

import com.hys.exam.model.BaseObject;
/**
 * 新闻栏目
 * @author xusq 2013-12-18
 *
 */
public class SystemCategory extends BaseObject{

	private static final long serialVersionUID = -8812420956548184997L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 站点ID
	 */
	private String applicationId;
	/**
	 * 站点域名
	 */
	private String domainName;
	/**
	 * 站点下的新闻条数 用于判断是否可以删除
	 */
	private Integer newsNum;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public Integer getNewsNum() {
		return newsNum;
	}
	public void setNewsNum(Integer newsNum) {
		this.newsNum = newsNum;
	}
}
