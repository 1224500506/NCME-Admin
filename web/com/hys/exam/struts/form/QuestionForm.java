package com.hys.exam.struts.form;

import com.hys.framework.web.form.BaseForm;

public class QuestionForm extends BaseForm {

	private static final long serialVersionUID = -8821837738919610789L;

	private Long[] qid;

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
	 * 是否多媒体
	 * 1 是 0否
	 */
	private Integer isnot_multimedia;

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

	/**
	 * 上线时间
	 */
	private String online_date;

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
	private String questPoint2_7_2;

	private Long[] questCognize_8;
	private String questCognize_8_2;

	private Long[] questTitle_9;
	private String questTitle_9_2;

	/**
	 * 试题分类
	 */
	private Long[] questType;
	private String questType_2;
	private Integer subTypeId;

	/**
	 * 排序条件
	 * 1:题型
	 * 2:题干
	 * 3:创建时间
	 * 4:审核时间
	 */
	private Integer[] orderItem;

	private String txtInd;
	private String txtInd2;
	private String txtInd3;
	private String txtInd4;

	public String getQuestPoint2_7_2() {
		return questPoint2_7_2;
	}

	public void setQuestPoint2_7_2(String questPoint2_7_2) {
		this.questPoint2_7_2 = questPoint2_7_2;
	}

	public String getQuestCognize_8_2() {
		return questCognize_8_2;
	}

	public void setQuestCognize_8_2(String questCognize_8_2) {
		this.questCognize_8_2 = questCognize_8_2;
	}

	public Long[] getQid() {
		return qid;
	}

	public void setQid(Long[] qid) {
		this.qid = qid;
	}

	public String getQuestType_2() {
		return questType_2;
	}

	public void setQuestType_2(String questType_2) {
		this.questType_2 = questType_2;
	}

	public Integer getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(Integer subTypeId) {
		this.subTypeId = subTypeId;
	}

	public String getTxtInd2() {
		return txtInd2;
	}

	public void setTxtInd2(String txtInd2) {
		this.txtInd2 = txtInd2;
	}

	public String getTxtInd3() {
		return txtInd3;
	}

	public void setTxtInd3(String txtInd3) {
		this.txtInd3 = txtInd3;
	}

	public String getTxtInd4() {
		return txtInd4;
	}

	public void setTxtInd4(String txtInd4) {
		this.txtInd4 = txtInd4;
	}

	public String getTxtInd() {
		return txtInd;
	}

	public void setTxtInd(String txtInd) {
		this.txtInd = txtInd;
	}

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

	public void setIsnot_multimedia(Integer isnot_multimedia) {
		this.isnot_multimedia = isnot_multimedia;
	}

	public String getQuestTitle_9_2() {
		return questTitle_9_2;
	}

	public void setQuestTitle_9_2(String questTitle_9_2) {
		this.questTitle_9_2 = questTitle_9_2;
	}

	

}
