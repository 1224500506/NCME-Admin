package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CVSet implements Serializable {

	/**
	 * boy
	 */
	private static final long serialVersionUID = 3299179869424516827L;
	
	private Long id;
	
	private String flag;
	
	private String name;
	
	private Integer forma;
	
	private String code;
	
	private List<PropUnit> courseList;
	
	private List<UserImage> userImageList;
	
	private List<ExpertInfo> managerList;
	
	private List<ExpertInfo> teacherList;
	
	private List<ExpertInfo> otherTeacherList;
	
	private List<PeixunOrg> organizationList;
	
	private String introduction;
	
	private String announcement;
	
	private String file_path;
	
	private String knowledge_needed;
	
	private String knowledge_base;
	
	private String reference;
	
	private String reference_lately;
	
	private String relationQuiz;
	
	private List<CVSchedule> CVScheduleList;
	
	private List<SystemSite> siteList;
	
	private String serial_number;
	
	private Integer level;
	
	private Double score;
	
	private Double cost;
	
	private Integer type;
	
	private Long status;
	
	private String maker;

	private String maker2;//只在“我的项目”查询列表时进行参数传递使用，数据库表中不存在！
	
	private Date start_date;
	
	private Date end_date;
	
	private Date create_date;
	
	private Date deli_date;
	
	private Long break_days;
	
	private String sign;
	
	private Long provinceId;
	
	private Long cityId;
	
	private Long deli_man_id;
	 
	private String opinion;
	
	private String opinionType;
	
	private String report;
	
	private List<Qualify> qualifyList;
	
	private String deli_man;
	
	private int ordernum;
	
	private Long endTime;

	private String propIds;//仅在项目授权列表条件查询中的区域查询使用
	
	//YHQ，2017-05-15，begin
	private List<BaseVO> refereeBookList = null ;
	private List<BaseVO> knowledgeBaseList = null ;
	private List<BaseVO> referenceLatelyList = null ;
	
	private Integer cv_set_type ; //YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
	private String  cv_set_type_name = "" ; //YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
	
	//YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
	public String getCv_set_type_name() {        
		if (this.cv_set_type == 0) cv_set_type_name = "继教项目" ;
		if (this.cv_set_type == 1) cv_set_type_name = "乡医培训" ;				
		
		return cv_set_type_name;
	}
	
	private Integer cost_type;//0 免费，1 学习卡 ，2 收费(默认都为免费)--tl
	private String cost_type_name = "";////0 免费，1 学习卡 ，2 收费(默认都为免费)--tl
	private Integer sort;//排序----taoliang
	private CVSetAuthorization cVSetAuthorization;//项目授权信息类
	private Integer authorStatus;//授权状态
	private String orgName;//归属机构
	
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getAuthorStatus() {
		return authorStatus;
	}

	public void setAuthorStatus(Integer authorStatus) {
		this.authorStatus = authorStatus;
	}

	public CVSetAuthorization getcVSetAuthorization() {
		return cVSetAuthorization;
	}

	public void setcVSetAuthorization(CVSetAuthorization cVSetAuthorization) {
		this.cVSetAuthorization = cVSetAuthorization;
	}

	public String getCost_type_name() {
		if (this.cost_type == 0) cost_type_name = "免费" ;
		if (this.cost_type == 1) cost_type_name = "学习卡" ;
		if (this.cost_type == 2) cost_type_name = "收费" ;
		return cost_type_name;
	}

	public String getPropIds() {
		return propIds;
	}
	public void setPropIds(String propIds) {
		this.propIds = propIds;
	}
	public Integer getCv_set_type() {
		return cv_set_type;
	}	
	public void setCv_set_type(Integer cv_set_type) {
		this.cv_set_type = cv_set_type;
	}
	public Integer getCost_type() {
		return cost_type;
	}
	public void setCost_type(Integer cost_type) {
		this.cost_type = cost_type;
	}
	public List<BaseVO> getRefereeBookList() {
		return refereeBookList;
	}
	public void setRefereeBookList(List<BaseVO> refereeBookList) {
		this.refereeBookList = refereeBookList;
	}
	public List<BaseVO> getKnowledgeBaseList() {
		return knowledgeBaseList;
	}
	public void setKnowledgeBaseList(List<BaseVO> knowledgeBaseList) {
		this.knowledgeBaseList = knowledgeBaseList;
	}
	public List<BaseVO> getReferenceLatelyList() {
		return referenceLatelyList;
	}
	public void setReferenceLatelyList(List<BaseVO> referenceLatelyList) {
		this.referenceLatelyList = referenceLatelyList;
	}
	//YHQ，2017-05-15，over

	
	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
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

	public Integer getForma() {
		return forma;
	}

	public void setForma(Integer forma) {
		this.forma = forma;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<UserImage> getUserImageList() {
		return userImageList;
	}

	public void setUserImageList(List<UserImage> userImageList) {
		this.userImageList = userImageList;
	}

	public List<ExpertInfo> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<ExpertInfo> managerList) {
		this.managerList = managerList;
	}

	public List<ExpertInfo> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<ExpertInfo> teacherList) {
		this.teacherList = teacherList;
	}

	public List<ExpertInfo> getOtherTeacherList() {
		return otherTeacherList;
	}

	public void setOtherTeacherList(List<ExpertInfo> otherTeacherList) {
		this.otherTeacherList = otherTeacherList;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}	

	public String getKnowledge_base() {
		return knowledge_base;
	}

	public void setKnowledge_base(String knowledge_base) {
		this.knowledge_base = knowledge_base;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference_lately() {
		return reference_lately;
	}

	public void setReference_lately(String reference_lately) {
		this.reference_lately = reference_lately;
	}

	public List<CVSchedule> getCVScheduleList() {
		return CVScheduleList;
	}

	public void setCVScheduleList(List<CVSchedule> cVScheduleList) {
		CVScheduleList = cVScheduleList;
	}

	public List<SystemSite> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SystemSite> siteList) {
		this.siteList = siteList;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Long getBreak_days() {
		return break_days;
	}

	public void setBreak_days(Long break_days) {
		this.break_days = break_days;
	}

	public String getKnowledge_needed() {
		return knowledge_needed;
	}

	public void setKnowledge_needed(String knowledge_needed) {
		this.knowledge_needed = knowledge_needed;
	}

	public List<PropUnit> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<PropUnit> courseList) {
		this.courseList = courseList;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long cVSetStatus) {
		this.status = cVSetStatus;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getMaker2() {
		return maker2;
	}

	public void setMaker2(String maker2) {
		this.maker2 = maker2;
	}
	

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public Date getDeli_date() {
		return deli_date;
	}

	public void setDeli_date(Date deli_date) {
		this.deli_date = deli_date;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getOpinionType() {
		return opinionType;
	}

	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}

	public List<PeixunOrg> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<PeixunOrg> organizationList) {
		this.organizationList = organizationList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getRelationQuiz() {
		return relationQuiz;
	}

	public void setRelationQuiz(String relationQuiz) {
		this.relationQuiz = relationQuiz;
	}
	
	public Long getDeli_man_id() {
		return deli_man_id;
	}

	public void setDeli_man_id(Long deli_man_id) {
		this.deli_man_id = deli_man_id;
	}

	public List<Qualify> getQualifyList() {
		return qualifyList;
	}

	public void setQualifyList(List<Qualify> qualifyList) {
		this.qualifyList = qualifyList;
	}

	public String getDeli_man() {
		return deli_man;
	}

	public void setDeli_man(String deli_man) {
		this.deli_man = deli_man;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
