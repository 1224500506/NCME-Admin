package com.hys.exam.struts.form;

import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2010-2-3
 * 
 * 描述：
 * 
 * 说明:
 */
public class OlemPaperForm extends BaseForm {

	private static final long serialVersionUID = -1251376418361496553L;

	
	private Long id;
	
	
	private String name;
	
	/**
	 * 分类ID
	 */
	private Long typeId;
	
	/**
	 * 试卷分类名称
	 */
	private String typeName;
	
	
	/**
	 * 试卷总分数
	 */
	private Double paperScore;
	
	/**
	 * 出卷方式  5：快捷策略     2：精细策略    3：手工组卷    4：卷中卷
	 */
	private Integer paperMode;
	
	/**
	 * 试题总数
	 */
	private Integer questionNum;
	
	/**
	 * 创建时间
	 */
	private String createDate;
	
	/**
	 * 难度
	 */
	private Integer grade;
	
	/**
	 * 是否多媒体
	 * 1 是
	 */
	private Integer isnot_multimedia;
	
	/**
	 * 状态
	 * 1 正常
	 * 2 禁用
	 * 3 已删除
	 */
	private Integer state;
	
	/**
	 * 有试卷有效期
	 */
	private String usefulDate;
	
	/**
	 * 安排方式
	 * 1：练习
	 * 2：考试
	 */
	private Integer paperPlanType;
	
	/**
	 * 答题时间
	 */
	private Integer examinationTime;
	
	/**
	 * 重考次数
	 */
	private Integer redoNum;
	
	/**
	 * 试卷导出：1:否 2：是 3：
	 */
	private Integer isNotExp;
	
	/**
	 * 手工选择的试题 
	 * 规则 1_2+2_3  试题id_每题分数+试题id_每题分数
	 */
	private String seleQues;
	
	private String type_Ids;
	/**
	 * 试卷个数
	 */
	private Integer childPaperNum;

	private Integer tacticEditFlag;
	
	public String getType_Ids() {
		return type_Ids;
	}

	public void setType_Ids(String type_Ids) {
		this.type_Ids = type_Ids;
	}

	public Long getId() {
		return id;
	}
	
	public Integer getIsNotExp() {
		return isNotExp;
	}

	public Integer getChildPaperNum() {
		return childPaperNum;
	}

	public void setChildPaperNum(Integer childPaperNum) {
		this.childPaperNum = childPaperNum;
	}

	public void setIsNotExp(Integer isNotExp) {
		this.isNotExp = isNotExp;
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

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Double getPaperScore() {
		return paperScore;
	}

	public void setPaperScore(Double paperScore) {
		this.paperScore = paperScore;
	}

	public Integer getPaperMode() {
		return paperMode;
	}

	public void setPaperMode(Integer paperMode) {
		this.paperMode = paperMode;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUsefulDate() {
		return usefulDate;
	}

	public void setUsefulDate(String usefulDate) {
		this.usefulDate = usefulDate;
	}

	public Integer getPaperPlanType() {
		return paperPlanType;
	}

	public void setPaperPlanType(Integer paperPlanType) {
		this.paperPlanType = paperPlanType;
	}

	public Integer getExaminationTime() {
		return examinationTime;
	}

	public void setExaminationTime(Integer examinationTime) {
		this.examinationTime = examinationTime;
	}

	public Integer getRedoNum() {
		return redoNum;
	}

	public void setRedoNum(Integer redoNum) {
		this.redoNum = redoNum;
	}

	public String getSeleQues() {
		return seleQues;
	}

	public void setSeleQues(String seleQues) {
		this.seleQues = seleQues;
	}

	public Integer getIsnot_multimedia() {
		return isnot_multimedia;
	}

	public void setIsnot_multimedia(Integer isnot_multimedia) {
		this.isnot_multimedia = isnot_multimedia;
	}

	public Integer getTacticEditFlag() {
		return tacticEditFlag;
	}

	public void setTacticEditFlag(Integer tacticEditFlag) {
		this.tacticEditFlag = tacticEditFlag;
	}
	
}