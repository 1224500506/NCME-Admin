package com.hys.exam.struts.form;

import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-19
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7465564856550968932L;
	
	private Long[] ids;

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
	 * 题型IDs
	 */
	private String question_label_ids;
	
	
	
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
	 * 答案内容
	 */
	private String[] answer_content;
	
	/**
	 * 是否正确
	 */
	private int[] answer_key;
	
	/**
	 * 子试题内容
	 */
	private String[] child_content;
	
	/**
	 * 子试题答案
	 */
	private String[] child_answer;
	
	/**
	 * 子试题试题分析
	 */
	private String[] child_analyse; 
	
	
	
	/**
	 * 是否有试题分析
	 * 1是
	 */
	private Integer isExistAnalyse;	
	
	/**
	 * // A3 A4子试题
	 */
	private String[] sub_questions; 
	
	/**
	 * 试题基本属性ids
	 */
	private String renderSelIds;
	
	/**
	 * 试题基本属性名称
	 */	
	private String renderSelNames;
	
	private String txtLoc;
	
	private Integer upLoad;
	
	/**
	 * 
	 */
	private Long[] questStudy1_1;
	
	private Long[] questStudy2_2;
	
	private Long[] questStudy3_3;
	
	private Long[] questUnit_4;
	
	private Long[] questPoint_5;
	
	private Long[] questSys_6;
	
	private Long[] questPoint2_7;
	
	private Long[] questCognize_8;
	
	private Long[] questTitle_9;
	
	private Long[] questSource_10;
	
	private Long[] questICD10_11;
	
	/**
	 * 试题分类
	 */
	private Long[] questType;
	
	
	/**
	 * 排序条件
	 * 1:题型
	 * 2:题干
	 * 3:创建时间
	 * 4:审核时间
	 */
	private Integer[] orderItem;
	
	
	/**
	 * 是否是多媒体试题
	 * １:是
	 */
	private Integer isnot_multimedia;

	private String surname;
	
	public Long[] getQuestStudy1_1() {
		return questStudy1_1;
	}

	public void setQuestStudy1_1(Long[] questStudy1_1) {
		this.questStudy1_1 = questStudy1_1;
	}

	public Long[] getQuestStudy2_2() {
		return questStudy2_2;
	}

	public void setQuestStudy2_2(Long[] questStudy2_2) {
		this.questStudy2_2 = questStudy2_2;
	}

	public Long[] getQuestStudy3_3() {
		return questStudy3_3;
	}

	public void setQuestStudy3_3(Long[] questStudy3_3) {
		this.questStudy3_3 = questStudy3_3;
	}

	public Long[] getQuestUnit_4() {
		return questUnit_4;
	}

	public void setQuestUnit_4(Long[] questUnit_4) {
		this.questUnit_4 = questUnit_4;
	}

	public Long[] getQuestPoint_5() {
		return questPoint_5;
	}

	public void setQuestPoint_5(Long[] questPoint_5) {
		this.questPoint_5 = questPoint_5;
	}

	public Long[] getQuestSys_6() {
		return questSys_6;
	}

	public void setQuestSys_6(Long[] questSys_6) {
		this.questSys_6 = questSys_6;
	}

	public Long[] getQuestPoint2_7() {
		return questPoint2_7;
	}

	public void setQuestPoint2_7(Long[] questPoint2_7) {
		this.questPoint2_7 = questPoint2_7;
	}

	public Long[] getQuestCognize_8() {
		return questCognize_8;
	}

	public void setQuestCognize_8(Long[] questCognize_8) {
		this.questCognize_8 = questCognize_8;
	}
	

	public Integer getIsExistAnalyse() {
		return isExistAnalyse;
	}

	public void setIsExistAnalyse(Integer isExistAnalyse) {
		this.isExistAnalyse = isExistAnalyse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parentId) {
		parent_id = parentId;
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

	public String[] getAnswer_content() {
		return answer_content;
	}

	public void setAnswer_content(String[] answerContent) {
		answer_content = answerContent;
	}

	public int[] getAnswer_key() {
		return answer_key;
	}

	public void setAnswer_key(int[] answerKey) {
		answer_key = answerKey;
	}

	public String[] getChild_content() {
		return child_content;
	}

	public void setChild_content(String[] childContent) {
		child_content = childContent;
	}

	public String[] getChild_answer() {
		return child_answer;
	}

	public void setChild_answer(String[] childAnswer) {
		child_answer = childAnswer;
	}

	public String getQuestion_label_ids() {
		return question_label_ids;
	}

	public void setQuestion_label_ids(String questionLabelIds) {
		question_label_ids = questionLabelIds;
	}

	public String[] getChild_analyse() {
		return child_analyse;
	}

	public void setChild_analyse(String[] childAnalyse) {
		child_analyse = childAnalyse;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String[] getSub_questions() {
		return sub_questions;
	}

	public void setSub_questions(String[] subQuestions) {
		sub_questions = subQuestions;
	}

	public String getRenderSelIds() {
		return renderSelIds;
	}

	public void setRenderSelIds(String renderSelIds) {
		this.renderSelIds = renderSelIds;
	}
	
	public String getRenderSelNames() {
		return renderSelNames;
	}

	public void setRenderSelNames(String renderSelNames) {
		this.renderSelNames = renderSelNames;
	}

	public String getTxtLoc() {
		return txtLoc;
	}

	public void setTxtLoc(String txtLoc) {
		this.txtLoc = txtLoc;
	}

	public Integer[] getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Integer[] orderItem) {
		this.orderItem = orderItem;
	}

	public Long[] getQuestType() {
		return questType;
	}

	public void setQuestType(Long[] questType) {
		this.questType = questType;
	}

	public Long[] getQuestTitle_9() {
		return questTitle_9;
	}

	public void setQuestTitle_9(Long[] questTitle_9) {
		this.questTitle_9 = questTitle_9;
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

	public Long[] getQuestSource_10() {
		return questSource_10;
	}

	public void setQuestSource_10(Long[] questSource_10) {
		this.questSource_10 = questSource_10;
	}

	public Long[] getQuestICD10_11() {
		return questICD10_11;
	}

	public void setQuestICD10_11(Long[] questICD10_11) {
		this.questICD10_11 = questICD10_11;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Integer getUpLoad() {
		return upLoad;
	}

	public void setUpLoad(Integer upLoad) {
		this.upLoad = upLoad;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
}
