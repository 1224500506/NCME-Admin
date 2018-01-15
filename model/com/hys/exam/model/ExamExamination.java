package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-17
 * 
 * 描述：
 * 
 * 说明: 考场
 */
public class ExamExamination implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 470502570090795912L;

	private Long id;
	
	private String name;
	
	private String aliasName;
	private String domainName;
	
	/**
	 * 考试分类ID
	 */
	private Long exam_type_id;
	
	/**
	 * 考试分类名称
	 */
	private String exam_type_name;
	
	/**
	 * 考试时长 
	 * 单位:分钟
	 */
	private Integer exam_times;
	
	/**
	 * 考试时间
	 * 1 随时 
	 * 2 固定
	 */
	private Integer exam_time;
	
	/**
	 * 考试性质
	 * 1 需要监考
	 */
	private Integer exam_nature;
	
	/**
	 * 结果显示
	 * 1 显示 
	 * 2 不显示
	 */
	private Integer isnot_see_result;
	
	/**
	 * 通过条件
	 * 1 分数  
	 * 2  得分率 
	 */
	private Integer pass_condition;
	
	/**
	 * 通过值
	 */
	private Double pass_value;
	
	/**
	 * 状态
	 * 1  正常
	 * 2  禁用
	 * -1  删除
	 * 3  已结束
	 */
	private Integer state;
	
	/**
	 * 是否是否允许查看测评报告
	 * 1 允许
	 */
	private Integer isnot_see_test_report;
	
	/**
	 * 开始时间
	 */
	private String start_time;
	
	/**
	 * 结束时间
	 */
	private String end_time;
	
	/**
	 * 是否盲判
	 * 1:否
	 * 2:是
	 */
	private Integer isnot_decide;
	
	/**
	 * 是否线上考试
	 * 1:线上 
	 * 2:线下
	 */
	private Integer isnot_online;
	
	/**
	 * 考试类别
	 * 1：考试
	 * 2：练习
	 */
	private Integer exam_type;
	
	/**
	 * 练习次数
	 */
	private Integer exam_num;
	
	/**
	 * 是否防止切屏
	 */
	private Integer is_cut_screen;
	
	/**
	 * 切屏次数
	 */
	private Integer cut_screen_num;
	
	/**
	 * 试卷显示方式
	 * 1 单向(考试中只能点下一题) 
	 * 2 双向(考试中可以点上一题和下一题)
	 */
	private Integer paper_display_mode;
	
	/**
	 * 试题显示方式
	 * 1 正常
	 * 2 随机
	 */
	private Integer question_display_mode;
	
	/**
	 * 单题计分方式
	 * 1 答对得满分，答错不等分
	 * 2 答对得满分，答错得一半分
	 */
	private Integer single_scoring;
	
	/**
	 * 试卷计分方式
	 * 1 第一次得分为准
	 * 2 最后一次等分为准
	 * 3 以最高得分为准
	 */
	private Integer paper_scoring;
	
	/**
	 * 创建时间
	 */
	private String create_time;
	
	/**
	 * 考试试卷列表
	 */
	private List<ExamExaminPaper> examinPaperList;
	

	private List<ExamExaminUser> examinUserList;

	private List<ExamExaminUser> examinConsierList;
	
	private List<ExamExaminOrg> examinOrgList;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 住院医考试类别
	 */
	private Integer zyy_exam_type;
	
	/**
	 * 是否开放本场考试统计给下级
	 */
	private Integer isopen_nextlevel;
	
	private String countUsers;
	
	public String getCountUsers() {
		return countUsers;
	}

	public void setCountUsers(String countUsers) {
		this.countUsers = countUsers;
	}

	//所属站点
	//20150416 clb add
	private Long siteId;

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

	public Long getExam_type_id() {
		return exam_type_id;
	}

	public void setExam_type_id(Long examTypeId) {
		exam_type_id = examTypeId;
	}

	public String getExam_type_name() {
		return exam_type_name;
	}

	public void setExam_type_name(String examTypeName) {
		exam_type_name = examTypeName;
	}

	public Integer getExam_times() {
		return exam_times;
	}

	public void setExam_times(Integer examTimes) {
		exam_times = examTimes;
	}

	public Integer getExam_time() {
		return exam_time;
	}

	public void setExam_time(Integer examTime) {
		exam_time = examTime;
	}

	public Integer getExam_nature() {
		return exam_nature;
	}

	public void setExam_nature(Integer examNature) {
		exam_nature = examNature;
	}

	public Integer getIsnot_see_result() {
		return isnot_see_result;
	}

	public void setIsnot_see_result(Integer isnotSeeResult) {
		isnot_see_result = isnotSeeResult;
	}

	public Integer getPass_condition() {
		return pass_condition;
	}

	public void setPass_condition(Integer passCondition) {
		pass_condition = passCondition;
	}

	public Double getPass_value() {
		return pass_value;
	}

	public void setPass_value(Double passValue) {
		pass_value = passValue;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsnot_see_test_report() {
		return isnot_see_test_report;
	}

	public void setIsnot_see_test_report(Integer isnotSeeTestReport) {
		isnot_see_test_report = isnotSeeTestReport;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String startTime) {
		start_time = startTime;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}

	public Integer getIsnot_decide() {
		return isnot_decide;
	}

	public void setIsnot_decide(Integer isnotDecide) {
		isnot_decide = isnotDecide;
	}

	public Integer getIsnot_online() {
		return isnot_online;
	}

	public void setIsnot_online(Integer isnotOnline) {
		isnot_online = isnotOnline;
	}

	public Integer getExam_type() {
		return exam_type;
	}

	public void setExam_type(Integer examType) {
		exam_type = examType;
	}

	public Integer getExam_num() {
		return exam_num;
	}

	public void setExam_num(Integer examNum) {
		exam_num = examNum;
	}

	public Integer getIs_cut_screen() {
		return is_cut_screen;
	}

	public void setIs_cut_screen(Integer isCutScreen) {
		is_cut_screen = isCutScreen;
	}

	public Integer getCut_screen_num() {
		return cut_screen_num;
	}

	public void setCut_screen_num(Integer cutScreenNum) {
		cut_screen_num = cutScreenNum;
	}

	public Integer getPaper_display_mode() {
		return paper_display_mode;
	}

	public void setPaper_display_mode(Integer paperDisplayMode) {
		paper_display_mode = paperDisplayMode;
	}

	public Integer getQuestion_display_mode() {
		return question_display_mode;
	}

	public void setQuestion_display_mode(Integer questionDisplayMode) {
		question_display_mode = questionDisplayMode;
	}

	public Integer getSingle_scoring() {
		return single_scoring;
	}

	public void setSingle_scoring(Integer singleScoring) {
		single_scoring = singleScoring;
	}

	public Integer getPaper_scoring() {
		return paper_scoring;
	}

	public void setPaper_scoring(Integer paperScoring) {
		paper_scoring = paperScoring;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public List<ExamExaminPaper> getExaminPaperList() {
		return examinPaperList;
	}

	public void setExaminPaperList(List<ExamExaminPaper> examinPaperList) {
		this.examinPaperList = examinPaperList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getZyy_exam_type() {
		return zyy_exam_type;
	}

	public void setZyy_exam_type(Integer zyyExamType) {
		zyy_exam_type = zyyExamType;
	}

	public Integer getIsopen_nextlevel() {
		return isopen_nextlevel;
	}

	public void setIsopen_nextlevel(Integer isopenNextlevel) {
		isopen_nextlevel = isopenNextlevel;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public List<ExamExaminUser> getExaminUserList() {
		return examinUserList;
	}

	public void setExaminUserList(List<ExamExaminUser> examinUserList) {
		this.examinUserList = examinUserList;
	}

	public List<ExamExaminUser> getExaminConsierList() {
		return examinConsierList;
	}

	public void setExaminConsierList(List<ExamExaminUser> examinConsierList) {
		this.examinConsierList = examinConsierList;
	}

	public List<ExamExaminOrg> getExaminOrgList() {
		return examinOrgList;
	}

	public void setExaminOrgList(List<ExamExaminOrg> examinOrgList) {
		this.examinOrgList = examinOrgList;
	}
	
}
