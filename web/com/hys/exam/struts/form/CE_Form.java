package com.hys.exam.struts.form;

import java.util.List;
//import org.apache.struts.upload.FormFile;
import com.hys.exam.model.ExpertInfo;
//import com.hys.exam.model.PropUnit;
import com.hys.framework.web.form.BaseForm;

public class CE_Form extends BaseForm {
	/**
	 * Lee 2016-11-29
	 */
	private static final long serialVersionUID = 8528979877396678303L;

	private Long id;
	
	private String bookName;
	
	private String kindName;
	
	private String takeKind;
	
	private String groupIds;
	
	private String createMan;
	
	private String editionName;
	
	private Long status;
	
	private Integer check_state;

	public Integer getCheck_state() {
		return check_state;
	}

	public void setCheck_state(Integer check_state) {
		this.check_state = check_state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getTakeKind() {
		return takeKind;
	}

	public void setTakeKind(String takeKind) {
		this.takeKind = takeKind;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getEditionName() {
		return editionName;
	}

	public void setEditionName(String editionManage) {
		this.editionName = editionManage;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

}
