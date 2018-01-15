package com.hys.exam.model;

import java.util.Date;

import com.hys.exam.model.system.SystemUserConfig;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 model
 * 
 * 说明:
 */
public class SystemUser extends SystemAccount {

	private static final long serialVersionUID = 3120104883143599691L;

	/**
	 * 主键ID
	 */
	private Long userId;
	
	/**
	 * 用户ID
	 */
	
	private Long id;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 出生日期
	 */
	private Date birthday;
	
	/**
	 * 性别 1 -男 2 -女
	 */
	private Integer sex;
	
	/**
	 * 手机
	 */
	private String mobilPhone;
	
	/**
	 * 固定电话
	 */
	private String telphone;
	
	/**
	 * 电子邮件
	 */
	private String email;
	
	/**
	 * 证件类型 1 -身份证 2 -军官证
	 */
	private Integer certificateType;
	
	/**
	 * 证件号码
	 */
	private String certificateNo;
	
	/**
	 * 单位信息
	 */
	private String workUnit;
	
	private Integer work_unit_id;
	
	/**
	 * 联系地址
	 */
	private String contactAddress;
	
	/**
	 * 邮政编码
	 */
	private String postCode;
	
	/**
	 * 所属行业
	 */
	private String profession;
	
	/**
	 * 所属科室
	 */
	private String deptName;
	/**
	 * 职称
	 */
	private String profTitle;
	
	/**
	 * 密码问题
	 */
	private String passwordQuestion;
	
	/**
	 * 答案
	 */
	private String passwordQuestionAnswer;
	
	/**
	 * 用户状态 1 -正常 -1 -删除 -2 -禁用
	 */
	private Integer status;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginDate;
	
	private Date regDate;
	
	/**
	 * 最后登录IP
	 */
	private String lastLoginIp;
	
	/**
	 * 用户状态 1 -正常 -1 -删除 -2 -禁用
	 */
	private Integer age;
	

	/**
	 * 用户类别根据不同的站点区分
	 * 1 -学员 
	 * 101 -带教老师 102 -科室管理员 103 -医院管理员 104 -市管理员 105 -省管理员
	 */
	private Integer userType;
	
	/**
	 * 绑定时间
	 */
	private Date bindDate;
	
	private Integer education;
	
	private SystemUserConfig userConfig = new SystemUserConfig();

	private String propIds;
	private String job_Id;
	private String health_certificate;

	//所属医院地址
	private String hospitalAddress;
	private String otherHospitalName;
	
	private Integer loginErrorNum;//登录错误次数
	
	private Long loginErrorFirstTime;//登录错误第一次时间
	private Long loginErrorLastTime;//登录错误最后一次时间
	private Integer grassroot;		//是否来自基层1:是  0:否
	private String reason;  		//停用原因
	private String reasondate;		//停用时间
	private Date lastUpdateDate;	//更新时间
	private String regDatee;		//辅助-注册时间
	private String lastUpdateDatee;	//辅助-更新时间
	private String workExtType;     //职务ID
	private String xueke3;
	private String roleIds;         //角色ID
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLoginErrorNum() {
		return loginErrorNum;
	}

	public void setLoginErrorNum(Integer loginErrorNum) {
		this.loginErrorNum = loginErrorNum;
	}

	public Long getLoginErrorFirstTime() {
		return loginErrorFirstTime;
	}

	public void setLoginErrorFirstTime(Long loginErrorFirstTime) {
		this.loginErrorFirstTime = loginErrorFirstTime;
	}

	public Long getLoginErrorLastTime() {
		return loginErrorLastTime;
	}

	public void setLoginErrorLastTime(Long loginErrorLastTime) {
		this.loginErrorLastTime = loginErrorLastTime;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobilPhone() {
		return mobilPhone;
	}

	public void setMobilPhone(String mobilPhone) {
		this.mobilPhone = mobilPhone;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getProfTitle() {
		return profTitle;
	}

	public void setProfTitle(String profTitle) {
		this.profTitle = profTitle;
	}

	public String getPasswordQuestion() {
		return passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordQuestionAnswer() {
		return passwordQuestionAnswer;
	}

	public void setPasswordQuestionAnswer(String passwordQuestionAnswer) {
		this.passwordQuestionAnswer = passwordQuestionAnswer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public SystemUserConfig getUserConfig() {
		return userConfig;
	}

	public void setUserConfig(SystemUserConfig userConfig) {
		this.userConfig = userConfig;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getWork_unit_id() {
		return work_unit_id;
	}

	public void setWork_unit_id(Integer work_unit_id) {
		this.work_unit_id = work_unit_id;
	}

	public String getPropIds() {
		return propIds;
	}

	public void setPropIds(String propIds) {
		this.propIds = propIds;
	}

	public String getHealth_certificate() {
		return health_certificate;
	}

	public void setHealth_certificate(String health_certificate) {
		this.health_certificate = health_certificate;
	}

	public String getOtherHospitalName() {
		return otherHospitalName;
	}

	public void setOtherHospitalName(String otherHospitalName) {
		this.otherHospitalName = otherHospitalName;
	}
	public String getJob_Id() {
		return job_Id;
	}

	public void setJob_Id(String job_Id) {
		this.job_Id = job_Id;
	}

	public Integer getGrassroot() {
		return grassroot;
	}

	public void setGrassroot(Integer grassroot) {
		this.grassroot = grassroot;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasondate() {
		return reasondate;
	}

	public void setReasondate(String reasondate) {
		this.reasondate = reasondate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getRegDatee() {
		return regDatee;
	}

	public void setRegDatee(String regDatee) {
		this.regDatee = regDatee;
	}

	public String getLastUpdateDatee() {
		return lastUpdateDatee;
	}

	public void setLastUpdateDatee(String lastUpdateDatee) {
		this.lastUpdateDatee = lastUpdateDatee;
	}

	public String getWorkExtType() {
		return workExtType;
	}

	public void setWorkExtType(String workExtType) {
		this.workExtType = workExtType;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getXueke3() {
		return xueke3;
	}

	public void setXueke3(String xueke3) {
		this.xueke3 = xueke3;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
}
