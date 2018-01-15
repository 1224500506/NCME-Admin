package com.hys.exam.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 标题：权限验证
 * 
 * 作者：陈明凯 2012-03-27
 * 
 * 描述：资源
 * 
 * 说明:
 */
public class SystemResource extends BaseObject {

	private static final long serialVersionUID = 579063031848494249L;

	/**
	 * 资源id
	 */
	private Long resourceId;

	/**
	 * 资源父id
	 */
	private Long resourceParentId;

	/**
	 * 资源名称
	 */
	private String resourceName;

	/**
	 * 资源url
	 */
	private String resourceUrl;

	/**
	 * 资源顺序
	 */
	private int resourceSeq;

	/**
	 * 资源备注
	 */
	private String resourceRemark;

	/**
	 * 资源属性 1 -显示 2 -不显示 -1 -删除
	 */
	private int resourceFlag;

	/**
	 * 资源图片
	 */
	private String resourceImage;

	/**
	 * 资源级别
	 */
	private String resourceLevel;

	/**
	 * 资源属性2 1 -操作 2 -查看
	 * 
	 */
	private Integer resourceProp;

	/**
	 * 是否叶子结点 0 -否 1 -是
	 * 
	 */
	private int isLeaf;

	/**
	 * 子资源列表
	 */
	private List<SystemResource> resources = new ArrayList<SystemResource>();

	public Integer getResourceProp() {
		return resourceProp;
	}

	public void setResourceProp(Integer resourceProp) {
		this.resourceProp = resourceProp;
	}

	public String getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(String resourceLevel) {
		this.resourceLevel = resourceLevel;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public Long getResourceParentId() {
		return resourceParentId;
	}

	public List<SystemResource> getResources() {
		return resources;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public int getResourceSeq() {
		return resourceSeq;
	}

	public int getResourceFlag() {
		return resourceFlag;
	}

	public String getResourceRemark() {
		return resourceRemark;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public void setResourceParentId(Long resourceParentId) {
		this.resourceParentId = resourceParentId;
	}

	public void setResources(List<SystemResource> resources) {
		this.resources = resources;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public void setResourceSeq(int resourceSeq) {
		this.resourceSeq = resourceSeq;
	}

	public void setResourceFlag(int resourceFlag) {
		this.resourceFlag = resourceFlag;
	}

	public void setResourceRemark(String resourceRemark) {
		this.resourceRemark = resourceRemark;
	}

	public String getResourceImage() {
		return resourceImage;
	}

	public void setResourceImage(String resourceImage) {
		this.resourceImage = resourceImage;
	}

	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
}
