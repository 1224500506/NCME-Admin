package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class StudyGuide implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 745001190825727665L;

	private Long id;
	
	private String name;
	
	private Long parent_id;
	
	private Long level;
	
	private UserImage userImage;
	
	private QualityModel quality;
	
	private List<QualityModel> qualityList;
	
	private List<PropUnit> icdPropList;
	
	private List<UserImage> userImageList;

	public StudyGuide() {

	}
	public StudyGuide(StudyGuide guide) {
		id = guide.id;
		name = guide.name;
		parent_id = guide.parent_id;
		level = guide.level;
		userImage = guide.userImage;
		quality = guide.quality;
		icdPropList = guide.icdPropList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public UserImage getUserImage() {
		return userImage;
	}

	public void setUserImage(UserImage userImage) {
		this.userImage = userImage;
	}

	public QualityModel getQuality() {
		return quality;
	}

	public void setQuality(QualityModel quality) {
		this.quality = quality;
	}

	public List<PropUnit> getIcdPropList() {
		return icdPropList;
	}

	public void setIcdPropList(List<PropUnit> icdPropList) {
		this.icdPropList = icdPropList;
	}
	public List<UserImage> getUserImageList() {
		return userImageList;
	}
	public void setUserImageList(List<UserImage> userImageList) {
		this.userImageList = userImageList;
	}
	public List<QualityModel> getQualityList() {
		return qualityList;
	}
	public void setQualityList(List<QualityModel> qualityList) {
		this.qualityList = qualityList;
	}
}
