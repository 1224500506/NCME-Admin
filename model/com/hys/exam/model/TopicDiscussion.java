package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author   张建国
 * @time     2017-02-23
 * 说       明:  话题讨论实体类
 */

public class TopicDiscussion  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//主键Id
	private Long id;
	
	//讨论内容
	private String talkContent;
	
	//讨论解析
	private String talkAnsy;
	
	//创建人
	private String createUser;
	
	//创建时间
	private String createDate;
	
	//讨论点对应的评论
	private List<CvSetUnitDiscuss> cvSetUnitDiscussList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTalkContent() {
		return talkContent;
	}

	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent;
	}

	public String getTalkAnsy() {
		return talkAnsy;
	}

	public void setTalkAnsy(String talkAnsy) {
		this.talkAnsy = talkAnsy;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<CvSetUnitDiscuss> getCvSetUnitDiscussList() {
		return cvSetUnitDiscussList;
	}

	public void setCvSetUnitDiscussList(List<CvSetUnitDiscuss> cvSetUnitDiscussList) {
		this.cvSetUnitDiscussList = cvSetUnitDiscussList;
	}

}
