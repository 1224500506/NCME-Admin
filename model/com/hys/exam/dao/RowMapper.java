package com.hys.exam.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hys.exam.model.ExamExaminPaper;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamPaperQuestion;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.model.ExamSubSysQuestType;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 9, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class RowMapper {

	public final static String getExamExaminationListMapper = "getExamExaminationListMapper";
	
	public final static String getExamExaminTypeListMapper = "getExamExaminTypeListMapper";
	
	public final static String getExamExaminPaperListMapper = "getExamExaminPaperListMapper";
	
	public final static String getExamPaperListMapper = "getExamPaperListMapper";
	
	public final static String getExamPaperTypeListMapper = "getExamPaperTypeListMapper";
	
	public final static String getExamPaperQuestionListMapper = "getExamPaperQuestionListMapper";
	
	public final static String getExamPaperTacticListMapper = "getExamPaperTacticListMapper";
	
	public final static String getExamPaperQuestionAndKeyListMapper = "getExamPaperQuestionAndKeyListMapper";

	public final static String getExamQuestionKeyListMapper = "getExamQuestionKeyListMapper";
	
	public final static String getExamQuestionListMapper = "getExamQuestionListMapper";
	
	public final static String getExamQuestionTypeListMapper = "getExamQuestionTypeListMapper";
	
	public final static String getQuestionPropListMapper = "getQuestionPropListMapper";
	
	public final static String getExamSubSysQuestTypeByIdMapper = "getExamSubSysQuestTypeByIdMapper";
	
	public final static String getExamQuestionChildNumListMapper = "getExamQuestionChildNumListMapper";
	
	public final static String getExamQuestionPropMapper = "getExamQuestionPropMapper";
	
	public final static String getExamQuestionPropListMapper = "getExamQuestionPropListMapper";

	public final static String getExamQuestionLabelListMapper = "getExamQuestionLabelListMapper";
	
	public final static String getExamQuestionPropValCascadeListMapper = "getExamQuestionPropValCascadeListMapper";
	
	public final static String getExamPaperFasterTacticListMappper = "getExamPaperFasterTacticListMappper";
	
	public final static String getExamPaperFasterTactic1ListMapper = "getExamPaperFasterTactic1ListMapper";
	
	public final static String getExamPaperFasterTactic2ListMapper = "getExamPaperFasterTactic2ListMapper";
	
	public final static String getExamPaperFasterTactic3ListMapper = "getExamPaperFasterTactic3ListMapper";
	
	public final static String getBasePropValListMapper = "getBasePropValListMapper";
	
	public final static String getSysPropValBySysIdListMapper = "getSysPropValBySysIdListMapper";
	
	
	
	public ExamExamination getExamExaminationListMapper(ResultSet rs) throws SQLException {
		
		ExamExamination obj = new ExamExamination();
		obj.setId(rs.getLong("id"));
		obj.setExam_type_id(rs.getLong("EXAM_TYPE_ID"));
		obj.setName(rs.getString("NAME"));
		obj.setExam_times(rs.getInt("EXAM_TIMES"));
		obj.setExam_time(rs.getInt("EXAM_TIME"));
		obj.setExam_nature(rs.getInt("EXAM_NATURE"));
		obj.setIsnot_see_result(rs.getInt("ISNOT_SEE_RESULT"));
		obj.setPass_condition(rs.getInt("PASS_CONDITION"));
		obj.setPass_value(rs.getDouble("PASS_VALUE"));
		obj.setState(rs.getInt("STATE"));
		obj.setIsnot_see_test_report(rs.getInt("ISNOT_SEE_TEST_REPORT"));
		obj.setStart_time(rs.getString("START_TIME"));
		obj.setEnd_time(rs.getString("END_TIME"));
		obj.setIsnot_decide(rs.getInt("ISNOT_DECIDE"));
		obj.setIsnot_online(rs.getInt("ISNOT_ONLINE"));
		obj.setExam_type(rs.getInt("EXAM_TYPE"));
		obj.setExam_num(rs.getInt("EXAM_NUM"));
		obj.setIs_cut_screen(rs.getInt("IS_CUT_SCREEN"));
		obj.setCut_screen_num(rs.getInt("CUT_SCREEN_NUM"));
		obj.setPaper_display_mode(rs.getInt("PAPER_DISPLAY_MODE"));
		obj.setQuestion_display_mode(rs.getInt("QUESTION_DISPLAY_MODE"));
		obj.setSingle_scoring(rs.getInt("SINGLE_SCORING"));
		obj.setPaper_scoring(rs.getInt("PAPER_SCORING"));
		obj.setCreate_time(rs.getString("CREATE_TIME"));
		obj.setRemark(rs.getString("REMARK"));
		obj.setZyy_exam_type(rs.getInt("ZYY_EXAM_TYPE"));
		obj.setIsopen_nextlevel(rs.getInt("ISOPEN_NEXTLEVEL"));
		return obj;
	}
	
	
	public ExamExaminPaper getExamExaminPaperListMapper(ResultSet rs) throws SQLException {
		ExamExaminPaper obj = new ExamExaminPaper();
		obj.setExam_id(rs.getLong("exam_id"));
		obj.setPaper_id(rs.getLong("paper_id"));
		obj.setPaper_mode(rs.getInt("paper_mode"));
		obj.setPaper_name(rs.getString("paper_name"));
		obj.setSeq(rs.getInt("seq"));
		obj.setChild_paper_num(rs.getInt("child_paper_num"));
		return obj;
	}
	
	public ExamPaper getExamPaperListMapper(ResultSet rs) throws SQLException {
		ExamPaper obj = new ExamPaper();
		obj.setId(rs.getLong("id"));
		obj.setParent_id(rs.getLong("PARENT_ID"));
		obj.setChild_paper_num(rs.getInt("CHILD_PAPER_NUM"));
		obj.setType_id(rs.getLong("TYPE_ID"));
		obj.setName(rs.getString("NAME"));
		obj.setPaper_score(rs.getDouble("PAPER_SCORE"));
		obj.setPaper_mode(rs.getInt("PAPER_MODE"));
		obj.setQuestion_num(rs.getInt("QUESTION_NUM"));
		obj.setCreate_date(rs.getString("CREATE_DATE"));
		obj.setGrade(rs.getInt("GRADE"));
		obj.setState(rs.getInt("STATE"));
		obj.setUseful_date(rs.getString("USEFUL_DATE"));
		obj.setPaper_plan_type(rs.getInt("PAPER_PLAN_TYPE"));
		obj.setExamination_time(rs.getInt("EXAMINATION_TIME"));
		obj.setRedo_num(rs.getInt("REDO_NUM"));
		obj.setIsnot_view_score(rs.getInt("ISNOT_VIEW_SCORE"));
		obj.setIsnot_exp_paper(rs.getInt("ISNOT_EXP_PAPER"));
		return obj;
	}
	
	

	public ExamPaperTactic getExamPaperTacticListMapper(ResultSet rs) throws SQLException {
		ExamPaperTactic obj = new ExamPaperTactic();
		obj.setId(rs.getLong("id"));
		obj.setPaper_id(rs.getLong("paper_id"));
		obj.setGrade(rs.getInt("grade"));
		obj.setAmount(rs.getInt("amount"));
		obj.setQuestion_score(rs.getDouble("question_score"));
		obj.setQuesiton_type_id(rs.getString("question_type_id"));
		obj.setQuestion_type_name(rs.getString("question_type_name"));
		obj.setCascade_id(rs.getString("cascade_id"));
		obj.setCascade_name(rs.getString("cascade_name"));
		obj.setProp_point2_id(rs.getString("prop_point2_id"));
		obj.setProp_point2_name(rs.getString("prop_point2_name"));
		obj.setProp_cognize_id(rs.getString("prop_cognize_id"));
		obj.setProp_cognize_name(rs.getString("prop_cognize_name"));
		obj.setProp_title_id(rs.getString("prop_title_id"));
		obj.setProp_title_name(rs.getString("prop_title_name"));
		return obj;
		
	}
	
	
	public ExamExaminType getExamExaminTypeListMapper(ResultSet rs) throws SQLException {
		ExamExaminType obj = new ExamExaminType();
		obj.setId(rs.getLong("id"));
		obj.setName(rs.getString("name"));
		obj.setCode(rs.getString("code"));
		obj.setParent_id(rs.getLong("parent_id"));
		obj.setSeq(rs.getInt("seq"));
		obj.setSub_sys_id(rs.getInt("sub_sys_id"));
		obj.setRemark(rs.getString("remark"));
		obj.setChildNum(rs.getInt("childNum"));
		return obj;
		
	}
	
	
	public ExamPaperQuestion getExamPaperQuestionListMapper(ResultSet rs) throws SQLException {
		ExamPaperQuestion obj = new ExamPaperQuestion();
		obj.setId(rs.getLong("id"));
		obj.setPaper_id(rs.getLong("paper_id"));
		obj.setQuestion_id(rs.getLong("question_id"));
		obj.setQuestion_score(rs.getDouble("question_score"));
		obj.setSeq(rs.getInt("seq"));
		return obj;
	}
	
	
	public ExamQuestion getExamPaperQuestionAndKeyListMapper(ResultSet rs) throws SQLException {
		ExamQuestion obj = new ExamQuestion();
		obj.setId(rs.getLong("id"));
		obj.setQuestion_label_id(rs.getInt("question_label_id"));
		obj.setParent_id(rs.getLong("parent_id"));
		obj.setContent(rs.getString("content"));
		obj.setState(rs.getInt("state"));
		obj.setGrade(rs.getInt("grade"));
		obj.setDiffer(rs.getInt("differ"));
		obj.setAnalyse(rs.getString("analyse"));
		obj.setSource(rs.getString("source"));
		obj.setSeq(rs.getInt("seq"));
		obj.setCreate_date(rs.getString("create_date"));
		obj.setAuthor(rs.getString("author"));
		obj.setOnline_date(rs.getString("online_date"));
		obj.setChildQuestionNum(rs.getInt("childQuestionNum"));
		obj.setQuestion_score(rs.getDouble("question_score"));
		return obj;
	}
	
	
	public ExamQuestion getExamQuestionChildNumListMapper(ResultSet rs) throws SQLException {
		ExamQuestion obj = new ExamQuestion();
		obj.setId(rs.getLong("id"));
		obj.setQuestion_label_id(rs.getInt("question_label_id"));
		obj.setParent_id(rs.getLong("parent_id"));
		obj.setContent(rs.getString("content"));
		obj.setState(rs.getInt("state"));
		obj.setGrade(rs.getInt("grade"));
		obj.setDiffer(rs.getInt("differ"));
		obj.setAnalyse(rs.getString("analyse"));
		obj.setSource(rs.getString("source"));
		obj.setSeq(rs.getInt("seq"));
		obj.setCreate_date(rs.getString("create_date"));
		obj.setAuthor(rs.getString("author"));
		obj.setOnline_date(rs.getString("online_date"));
		obj.setChildQuestionNum(rs.getInt("childQuestionNum"));
		return obj;
	}
	
	public ExamQuestion getExamQuestionListMapper(ResultSet rs) throws SQLException {
		ExamQuestion obj = new ExamQuestion();
		obj.setId(rs.getLong("id"));
		obj.setQuestion_label_id(rs.getInt("question_label_id"));
		obj.setParent_id(rs.getLong("parent_id"));
		obj.setContent(rs.getString("content"));
		obj.setState(rs.getInt("state"));
		obj.setGrade(rs.getInt("grade"));
		obj.setDiffer(rs.getInt("differ"));
		obj.setAnalyse(rs.getString("analyse"));
		obj.setSource(rs.getString("source"));
		obj.setSeq(rs.getInt("seq"));
		obj.setCreate_date(rs.getString("create_date"));
		obj.setAuthor(rs.getString("author"));
		obj.setOnline_date(rs.getString("online_date"));
		return obj;
	}
	
	public ExamQuestionKey getExamQuestionKeyListMapper(ResultSet rs) throws SQLException {
		
		ExamQuestionKey obj = new  ExamQuestionKey();
		obj.setId(rs.getLong("id"));
		obj.setContent(rs.getString("content"));
		obj.setIsnot_true(rs.getInt("isnot_true"));
		obj.setQuestion_id(rs.getLong("question_id"));
		obj.setSeq(rs.getInt("seq"));
		
		return obj;
	}
	
	public ExamQuestionType getExamQuestionTypeListMapper(ResultSet rs) throws SQLException {
		
		ExamQuestionType obj = new ExamQuestionType();
		obj.setId(rs.getLong("id"));
		obj.setName(rs.getString("name"));
		obj.setCode(rs.getString("code"));
		obj.setParent_id(rs.getLong("parent_id"));
		obj.setSeq(rs.getInt("seq"));
		obj.setSub_sys_id(rs.getLong("sub_sys_id"));
		obj.setRemark(rs.getString("remark"));
		obj.setChildNum(rs.getInt("childNum"));
		return obj;
	}
	
	public ExamPaperType getExamPaperTypeListMapper(ResultSet rs) throws SQLException {
		ExamPaperType obj = new ExamPaperType();
		obj.setId(rs.getLong("id"));
		obj.setName(rs.getString("name"));
		obj.setCode(rs.getString("code"));
		obj.setParent_id(rs.getLong("parent_id"));
		obj.setSeq(rs.getInt("seq"));
		obj.setSub_sys_id(rs.getInt("sub_sys_id"));
		obj.setRemark(rs.getString("remark"));
		obj.setChildNum(rs.getInt("childNum"));
		return obj;
	}
	
	public ExamPropVal getQuestionPropListMapper(ResultSet rs) throws SQLException {
		ExamPropVal obj =  new ExamPropVal();
		obj.setCode(rs.getString("code"));
		obj.setName(rs.getString("name"));
		return obj;
		
	}
	

	public ExamSubSysQuestType getExamSubSysQuestTypeByIdMapper(ResultSet rs) throws SQLException {
		ExamSubSysQuestType obj = new ExamSubSysQuestType();
			obj.setQuestion_id(rs.getLong("question_id"));
			obj.setSub_type_id(rs.getLong("sub_type_id"));
			obj.setSub_sys_id(rs.getLong("sub_sys_id"));
			obj.setSub_sys_name(rs.getString("sub_sys_name"));
			obj.setSub_type_name(rs.getString("sub_type_name"));
		return obj;
		
	}
	
	public ExamQuestionProp getExamQuestionPropMapper(ResultSet rs) throws SQLException {
		ExamQuestionProp obj = new ExamQuestionProp();
		obj.setQuestion_id(rs.getLong("question_id"));
		obj.setProp_val_id(rs.getLong("prop_val_id"));
		return obj;
	}
	
	public ExamQuestionProp getExamQuestionPropListMapper(ResultSet rs) throws SQLException {
		ExamQuestionProp obj = new ExamQuestionProp();
		obj.setQuestion_id(rs.getLong("question_id"));
		obj.setProp_val_id(rs.getLong("prop_val_id"));
		obj.setProp_val_name(rs.getString("prop_val_name"));
		return obj;
	}

	public ExamQuestionLabel getExamQuestionLabelListMapper(ResultSet rs) throws SQLException {
		ExamQuestionLabel obj = new  ExamQuestionLabel();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setIs_child(rs.getInt("is_child"));
		obj.setL_type(rs.getInt("l_type"));
		return obj;
	}
	public ExamQuestionPropValCascade getExamQuestionPropValCascadeListMapper(ResultSet rs) throws SQLException {
		ExamQuestionPropValCascade obj = new ExamQuestionPropValCascade();
			obj.setQuestion_id(rs.getLong("question_id"));
			obj.setPropval_name(rs.getString("propval_name"));
			obj.setPropval_id(rs.getString("propval_id"));
		return obj;
	}
	
	public ExamPaperFasterTactic getExamPaperFasterTacticListMappper(ResultSet rs) throws SQLException {
		ExamPaperFasterTactic obj = new ExamPaperFasterTactic();
		obj.setId(rs.getLong("id"));
		obj.setTactic_name(rs.getString("tactic_name"));
		obj.setQuestion_type_id(rs.getString("question_type_id"));
		obj.setExam_paper_type_id(rs.getLong("exam_paper_type_id"));
		return obj;
	}
	
	public ExamPaperFasterTactic1 getExamPaperFasterTactic1ListMapper(ResultSet rs) throws SQLException {
		ExamPaperFasterTactic1 obj = new ExamPaperFasterTactic1();
		obj.setTactic_id(rs.getLong("tactic_id"));
		obj.setQuestion_label_id(rs.getInt("question_label_id"));
		obj.setScore(rs.getDouble("score"));
		obj.setNum(rs.getInt("num"));
		return obj;
	}
	
	public ExamPaperFasterTactic2 getExamPaperFasterTactic2ListMapper(ResultSet rs) throws SQLException {
		ExamPaperFasterTactic2 obj = new ExamPaperFasterTactic2();
		obj.setTactic_id(rs.getLong("tactic_id"));
		obj.setStudy1_id(rs.getLong("study1_id"));
		obj.setStudy1_name(rs.getString("study1_name"));
		obj.setStudy2_id(rs.getLong("study2_id"));
		obj.setStudy2_name(rs.getString("study2_name"));
		obj.setStudy3_id(rs.getLong("study3_id"));
		obj.setStudy3_name(rs.getString("study3_name"));
		obj.setUnit_id(rs.getLong("unit_id"));
		obj.setUnit_name(rs.getString("unit_name"));
		obj.setPoint_id(rs.getLong("point_id"));
		obj.setPoint_name(rs.getString("point_name"));
		return obj;
	}
	

	public ExamPaperFasterTactic3 getExamPaperFasterTactic3ListMapper(ResultSet rs) throws SQLException {
		ExamPaperFasterTactic3 obj = new ExamPaperFasterTactic3();
		obj.setTactic_id(rs.getLong("tactic_id"));
		obj.setCognize_id(rs.getLong("cognize_id"));
		obj.setCognize_name(rs.getString("cognize_name"));
		obj.setPoint2_id(rs.getLong("point2_id"));
		obj.setPoint2_name(rs.getString("point2_name"));
		obj.setTitle_id(rs.getLong("title_id"));
		obj.setTitle_name(rs.getString("title_name"));
		return obj;
	}
	
	public ExamPropVal getBasePropValListMapper(ResultSet rs) throws SQLException {
		ExamPropVal obj = new ExamPropVal();
			obj.setId(rs.getLong("id"));
			obj.setName(rs.getString("name"));
			obj.setCode(rs.getString("code"));
			obj.setType(rs.getInt("type"));		
			obj.setRefId(rs.getLong("refId"));
		return obj;
	}
	
	public ExamPropVal getSysPropValBySysIdListMapper(ResultSet rs)
			throws SQLException {
		ExamPropVal obj = new ExamPropVal();
		obj.setId(rs.getLong("id"));
		obj.setName(rs.getString("name"));
		obj.setCode(rs.getString("code"));
		obj.setType(rs.getInt("type"));
		return obj;
	}
}
