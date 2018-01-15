package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;

import com.hys.framework.web.form.BaseForm;

public class SystemNewsForm extends BaseForm{
	private static final long serialVersionUID = 1L;
	/**
	 *  上传的文件
	 */
	private FormFile file;
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
	 * 栏目ID
	 */
	private Long categoryId;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long[] getSystemIndustryIds() {
		return systemIndustryIds;
	}
	public void setSystemIndustryIds(Long[] systemIndustryIds) {
		this.systemIndustryIds = systemIndustryIds;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
}
