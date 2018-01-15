/**
 *
 * <p>系统bug日志</p>
 * @author chenlaibin
 * @version 1.0 2014-4-1
 */

package com.hys.exam.model.system;

import java.util.Date;

import com.hys.exam.model.BaseObject;

@SuppressWarnings("serial")
public class SystemBugLog extends BaseObject {

	private Long id;

	private String title;

	private String content;

	private Integer bugLevel;	//一级,二级,三级(严重)

	private Integer bugStatus;  //0未修改,1 已修改,2 不需修改,3 暂无法修改,4,不是问题,打回

	private Integer status;		//状态,1 正常  -1 删除

	private String reply;		//回复

	private String creator;

	private String finisher;

	private Date createDate;

	private Date updateDate;

	private Integer type;
	
	private String filePath;
	
	private String filePathTwo;
	
	private String filePathThree;

	public String getFilePathThree() {
		return filePathThree;
	}

	public void setFilePathThree(String filePathThree) {
		this.filePathThree = filePathThree;
	}

	public String getFilePathTwo() {
		return filePathTwo;
	}

	public void setFilePathTwo(String filePathTwo) {
		this.filePathTwo = filePathTwo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBugLevel() {
		return bugLevel;
	}

	public void setBugLevel(Integer bugLevel) {
		this.bugLevel = bugLevel;
	}

	public Integer getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(Integer bugStatus) {
		this.bugStatus = bugStatus;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getFinisher() {
		return finisher;
	}

	public void setFinisher(String finisher) {
		this.finisher = finisher;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
