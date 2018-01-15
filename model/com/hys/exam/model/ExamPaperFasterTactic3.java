package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-5-9
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperFasterTactic3 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4869509646615492470L;
	
	/**
	 * 策略模板ID
	 */
	private Long tactic_id;
	
	/**
	 * 副知识点
	 */
	private Long point2_id;
	
	/**
	 * 副知识点名称
	 */
	private String point2_name;
	
	/**
	 * 认知水平
	 */
	private Long cognize_id;
	/**
	 * 认知不平名称
	 */
	private String cognize_name;
	
	/**
	 * 职称
	 */
	private Long title_id;
	
	/**
	 * 职称名称
	 */
	private String title_name;

	private Long sourceId;
	private String sourceName;

	public Long getTactic_id() {
		return tactic_id;
	}

	public void setTactic_id(Long tacticId) {
		tactic_id = tacticId;
	}

	public Long getPoint2_id() {
		return point2_id;
	}

	public void setPoint2_id(Long point2Id) {
		point2_id = point2Id;
	}

	public String getPoint2_name() {
		return point2_name;
	}

	public void setPoint2_name(String point2Name) {
		point2_name = point2Name;
	}

	public Long getCognize_id() {
		return cognize_id;
	}

	public void setCognize_id(Long cognizeId) {
		cognize_id = cognizeId;
	}

	public String getCognize_name() {
		return cognize_name;
	}

	public void setCognize_name(String cognizeName) {
		cognize_name = cognizeName;
	}

	public Long getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Long titleId) {
		title_id = titleId;
	}

	public String getTitle_name() {
		return title_name;
	}

	public void setTitle_name(String titleName) {
		title_name = titleName;
	}
	 public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
