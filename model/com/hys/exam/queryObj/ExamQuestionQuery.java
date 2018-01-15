package com.hys.exam.queryObj;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamQuestionProp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-15
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionQuery extends AbstractQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3516733932500813101L;
	
	/**
	 * 试题ID数组
	 */
	private Long[] idArr;
	
	/**
	 * 试题ID
	 */
	private Long id;
	
	/**
	 * 题型组
	 */
	private String question_label_ids;
	
	/**
	 * 题型
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
	 * 是否有试题分析
	 * 1是
	 */
	private Integer isExistAnalyse;	
	
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
	private String create_date2;


	/**
	 * 创建者
	 */
	private String author;
	private String checker;
	
	/**
	 * 上线时间
	 */
	private String online_date;
	private String online_date2;
	
	/**
	 * 试题属性
	 * questionPropMap<属性类别,ExamQuestionProp>
	 */
	private Map<String,List<ExamQuestionProp>> questionPropMap;
	
	
	/**
	 * 排序条件
	 * 1:题型
	 * 2:题干
	 * 3:创建时间
	 * 4:审核时间
	 */
	private Integer[] orderItem;
	
	/**
	 * 子系统分类ID
	 */
	private Long subTypeId;
	
	/**
	 * 子系统分类ID
	 * ?,?
	 */
	private String subTypeIds;
	
	private String sort;
	private String dir;
	/**
	 * 是否是多媒体试题
	 * １:是
	 */
	
	
	private Integer isnot_multimedia;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Long[] getIdArr() {
		return idArr;
	}

	public void setIdArr(Long[] idArr) {
		this.idArr = idArr;
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

	public Integer getIsExistAnalyse() {
		return isExistAnalyse;
	}

	public void setIsExistAnalyse(Integer isExistAnalyse) {
		this.isExistAnalyse = isExistAnalyse;
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

	public Map<String,List<ExamQuestionProp>> getQuestionPropMap() {
		return questionPropMap;
	}

	public void setQuestionPropMap(Map<String,List<ExamQuestionProp>> questionPropMap) {
		this.questionPropMap = questionPropMap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion_label_ids() {
		return question_label_ids;
	}

	public void setQuestion_label_ids(String questionLabelIds) {
		question_label_ids = questionLabelIds;
	}

	public Integer getQuestion_label_id() {
		return question_label_id;
	}

	public void setQuestion_label_id(Integer questionLabelId) {
		question_label_id = questionLabelId;
	}

	public Integer[] getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Integer[] orderItem) {
		this.orderItem = orderItem;
	}

	public Long getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(Long subTypeId) {
		this.subTypeId = subTypeId;
	}

	public String getSubTypeIds() {
		return subTypeIds;
	}

	public void setSubTypeIds(String subTypeIds) {
		this.subTypeIds = subTypeIds;
	}

	public Integer getIsnot_multimedia() {
		return isnot_multimedia;
	}

	public void setIsnot_multimedia(Integer isnotMultimedia) {
		isnot_multimedia = isnotMultimedia;
	}
	
	public String getCreate_date2() {
		return create_date2;
	}

	public void setCreate_date2(String create_date2) {
		this.create_date2 = create_date2;
	}

	public String getOnline_date2() {
		return online_date2;
	}

	public void setOnline_date2(String online_date2) {
		this.online_date2 = online_date2;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}
	
}
