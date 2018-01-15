package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

import com.hys.exam.model.CVSet;

/**
 * 
 * @author Tiger
 * @time 2017-1-12
 * Detail : Model of SYSTEM_MESSAGE table.
 */

public class SystemMessageModel implements Serializable {

	private static final long serialVersionUID = 3299179869424516827L;

	//id
	private Long id;
	
	//用户ID
	private Long system_user_id;
	
	// 项目id
     private Long cv_set_id;
	
	
	//消息内容
	private String message_content;
	
	//消息时间
	private Date message_date;
	
	/**
	 *	是否读
	 *	1 - 未读
	 *  2 - 已读
	 */
	private Integer is_read;

	public Long getId() {
		return id;
	}

	








	public Long getCv_set_id() {
		return cv_set_id;
	}










	public void setCv_set_id(Long cv_set_id) {
		this.cv_set_id = cv_set_id;
	}










	public void setId(Long id) {
		this.id = id;
	}

	public Long getSystem_user_id() {
		return system_user_id;
	}

	public void setSystem_user_id(Long system_user_id) {
		this.system_user_id = system_user_id;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public Date getMessage_date() {
		return message_date;
	}

	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}

	public Integer getIs_read() {
		return is_read;
	}

	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}

	
	
	
	public SystemMessageModel() {
		super();
	}

	public SystemMessageModel(Long id, Long system_user_id,
			String message_content, Date message_date, Integer is_read,Long cv_set_id) {
		this.id = id;
		this.system_user_id = system_user_id;
		this.message_content = message_content;
		this.message_date = message_date;
		this.is_read = is_read;
		this.cv_set_id =cv_set_id;
	}

	
	
	
	
}