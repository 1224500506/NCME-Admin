package com.hys.exam.queryObj;

import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-16
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperQuery extends AbstractQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7536267227487503993L;

	private Long id;
	
	private String name;
	
	private Long parent_id;
	
	/**
	 * 分类ID
	 */
	private Long type_id;
	
	/**
	 * 试卷分类名称
	 */
	private String type_name;
	
	/**
	 * 子试卷个数
	 */
	private Integer child_paper_num;
	
	/**
	 * 试卷总分数
	 */
	private Double paper_score;
	
	/**
	 * 出卷方式
	 */
	private Integer paper_mode;
	
	/**
	 * 试题总数
	 */
	private Integer question_num;
	
	/**
	 * 创建时间
	 */
	private String create_date;
	
	/**
	 * 难度
	 */
	private Integer grade;
	
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
	private String useful_date;
	
	/**
	 * 安排方式
	 * 1：练习
	 * 2：考试
	 */
	private Integer paper_plan_type;
	
	/**
	 * 答题时间
	 */
	private Integer examination_time;
	
	/**
	 * 重考次数
	 */
	private Integer redo_num;

	private String  type_ids;
	
	public String getType_ids() {
		return type_ids;
	}

	public void setType_ids(String type_ids) {
		this.type_ids = type_ids;
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

	public void setParent_id(Long parentId) {
		parent_id = parentId;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long typeId) {
		type_id = typeId;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String typeName) {
		type_name = typeName;
	}

	public Integer getChild_paper_num() {
		return child_paper_num;
	}

	public void setChild_paper_num(Integer childPaperNum) {
		child_paper_num = childPaperNum;
	}

	public Double getPaper_score() {
		return paper_score;
	}

	public void setPaper_score(Double paperScore) {
		paper_score = paperScore;
	}

	public Integer getPaper_mode() {
		return paper_mode;
	}

	public void setPaper_mode(Integer paperMode) {
		paper_mode = paperMode;
	}

	public Integer getQuestion_num() {
		return question_num;
	}

	public void setQuestion_num(Integer questionNum) {
		question_num = questionNum;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String createDate) {
		create_date = createDate;
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

	public String getUseful_date() {
		return useful_date;
	}

	public void setUseful_date(String usefulDate) {
		useful_date = usefulDate;
	}

	public Integer getPaper_plan_type() {
		return paper_plan_type;
	}

	public void setPaper_plan_type(Integer paperPlanType) {
		paper_plan_type = paperPlanType;
	}

	public Integer getExamination_time() {
		return examination_time;
	}

	public void setExamination_time(Integer examinationTime) {
		examination_time = examinationTime;
	}

	public Integer getRedo_num() {
		return redo_num;
	}

	public void setRedo_num(Integer redoNum) {
		redo_num = redoNum;
	}
	
	
}
