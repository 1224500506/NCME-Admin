package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-13
 * 
 * 描述：
 * 
 * 说明: 试题
 */
public class ExamQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1719375875837883071L;

	/**
	 * 试题ID
	 */
	private Long id;
	
	/**
	 * 父试题ID
	 */
	private Long parent_id;
	
	/**
	 * 题型ID
	 */
	private Integer question_label_id;
	
	/**
	 * 试题题干
	 */
	private String content;
	
	/**
	 * 试题状态
	 *  1 ：正常 
	 *  2 ：禁用 
	 *  3 ：已过期  
	 *  4 ：已审核 
	 *  5 ：未审核
	 */
	private Integer state;
	
	/**
	 * 试题难度
	 * 
	 * 0-5
	 */
	private Integer grade;
	
	/**
	 * 区分度
	 */
	private Integer differ;
	
	/**
	 * 分析
	 */
	private String analyse;
	
	/**
	 * 来源
	 */
	private String source;
	
	/**
	 * 顺序
	 */
	private Integer seq;
	
	/**
	 * 创建时间
	 */
	private String create_date;
	
	/**
	 * 创建者
	 */
	private String author;
	
	private String checker;
	
	/**
	 * 上线时间
	 */
	private String online_date;
	
	/**
	 * 子试题数量
	 */
	private Integer childQuestionNum;
	
	/**
	 * 试卷试题分数
	 */
	private Double question_score;
	
	
	/**
	 * 子试题
	 */
	private List<ExamQuestion> childQuestionList;
	
	/**
	 * 试题答案
	 */
	private List<ExamQuestionKey> questionKeyList;
	
	/**
	 * 子系统试题分类
	 */
	private List<ExamSubSysQuestType> subSysQuestTypeList;
	

	/**
	 * 试题属性
	 * questionPropMap<属性类别,ExamQuestionProp>
	 */
	private Map<String,List<ExamQuestionProp>> questionPropMap;
	
	/**
	 * 试题属性级联关系
	 */
	private ExamQuestionPropValCascade questPropValCascade;
	
	/**
	 * 试题属性级联关系（导入专用）
	 */
	private List<ExamQuestionPropValCascade> questPropValCascadeList;
	
	/**
	 * 是否是多媒体试题
	 * １:是; 0:否
	 */
	private Integer isnot_multimedia;
	
	/**
	 * 答案
	 */
	private ExamResult result;
	
	private String surname;
	
	private String opinion;
	
	
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuestion_label_id() {
		return question_label_id;
	}

	public void setQuestion_label_id(Integer questionLabelId) {
		question_label_id = questionLabelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getDiffer() {
		return differ;
	}

	public void setDiffer(Integer differ) {
		this.differ = differ;
	}

	public String getAnalyse() {
		return analyse;
	}

	public void setAnalyse(String analyse) {
		this.analyse = analyse;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String createDate) {
		create_date = createDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOnline_date() {
		return online_date;
	}

	public void setOnline_date(String onlineDate) {
		online_date = onlineDate;
	}

	public List<ExamQuestion> getChildQuestionList() {
		return childQuestionList;
	}

	public void setChildQuestionList(List<ExamQuestion> childQuestionList) {
		this.childQuestionList = childQuestionList;
	}

	public List<ExamQuestionKey> getQuestionKeyList() {
		return questionKeyList;
	}

	public void setQuestionKeyList(List<ExamQuestionKey> questionKeyList) {
		this.questionKeyList = questionKeyList;
	}

	public Map<String, List<ExamQuestionProp>> getQuestionPropMap() {
		return questionPropMap;
	}

	public void setQuestionPropMap(
			Map<String, List<ExamQuestionProp>> questionPropMap) {
		this.questionPropMap = questionPropMap;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parentId) {
		parent_id = parentId;
	}

	public ExamQuestionPropValCascade getQuestPropValCascade() {
		return questPropValCascade;
	}

	public void setQuestPropValCascade(
			ExamQuestionPropValCascade questPropValCascade) {
		this.questPropValCascade = questPropValCascade;
	}

	public List<ExamSubSysQuestType> getSubSysQuestTypeList() {
		return subSysQuestTypeList;
	}

	public void setSubSysQuestTypeList(List<ExamSubSysQuestType> subSysQuestTypeList) {
		this.subSysQuestTypeList = subSysQuestTypeList;
	}

	public Integer getChildQuestionNum() {
		return childQuestionNum;
	}

	public void setChildQuestionNum(Integer childQuestionNum) {
		this.childQuestionNum = childQuestionNum;
	}

	public Double getQuestion_score() {
		return question_score;
	}

	public void setQuestion_score(Double questionScore) {
		question_score = questionScore;
	}

	public ExamResult getResult() {
		return result;
	}

	public void setResult(ExamResult result) {
		this.result = result;
	}

	public List<ExamQuestionPropValCascade> getQuestPropValCascadeList() {
		return questPropValCascadeList;
	}

	public void setQuestPropValCascadeList(
			List<ExamQuestionPropValCascade> questPropValCascadeList) {
		this.questPropValCascadeList = questPropValCascadeList;
	}

	public Integer getIsnot_multimedia() {
		return isnot_multimedia;
	}

	public void setIsnot_multimedia(Integer isnotMultimedia) {
		isnot_multimedia = isnotMultimedia;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
