package com.hys.exam.model.system;

import java.util.Date;
import java.util.List;

import com.hys.exam.model.BaseObject;
/**
 * 新闻
 * @author xusq 2013-12-18
 *
 */
public class SystemNews extends BaseObject{

	private static final long serialVersionUID = 602936688169223859L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 新闻标题
	 */
	private String title;
	/**
	 * 新闻的类别 1:普通文章 2：下载资源 3：外部连接
	 */
	private Integer type = 1;
	/**
	 * 新闻内容 1：普通新闻的内容 2：下载资源的文件名称 3：连接的url
	 */
	private String content;
	/**
	 * 链接地址URL 在资源为 2、3的时候使用
	 */
	private String url;
	/**
	 * 新闻的来源
	 */
	private String original;
	/**
	 * 新闻的作者
	 */
	private String author;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date editDate;
	/**
	 * 栏目ID
	 */
	private Long categoryId;
	/**
	 * 课程 行业关联/或者 岗位   为空则不限制
	 */
	private List<SystemIndustry> systemIndustryList;
	
	/**
	 * 行业或者岗位ID 用于添加 删除 更新 关系表 
	 */
	private Long[] systemIndustryIds;
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<SystemIndustry> getSystemIndustryList() {
		return systemIndustryList;
	}
	public void setSystemIndustryList(List<SystemIndustry> systemIndustryList) {
		this.systemIndustryList = systemIndustryList;
	}
	public Long[] getSystemIndustryIds() {
		return systemIndustryIds;
	}
	public void setSystemIndustryIds(Long[] systemIndustryIds) {
		this.systemIndustryIds = systemIndustryIds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
