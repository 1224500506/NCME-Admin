package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamQuestionPropValCascadeDAO;
import com.hys.exam.model.ExamQuestionPropValCascade;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-11-4
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionPropValCascadeJDBCDAO extends BaseDao
		implements ExamQuestionPropValCascadeDAO {

	
	private static final String SQL_QUEST_PROPVAL_CASCADE_BY_ID = "select question_id, propval_name, propval_id from exam_quest_propval_cascade where question_id = ?";

	private static final String SQL_ADD_QUEST_PROPVAL_CASCADE = "insert into exam_quest_propval_cascade (question_id, propval_name, propval_id) values (:question_id, :propval_name, :propval_id)";
	
	private static final String SQL_DEL_QUEST_PROPVAL_CASCADE = "delete from exam_quest_propval_cascade where question_id = :question_id";
	
	
	public ExamQuestionPropValCascade getQuestionPropValCascadeById(Long id) {
		
		List<ExamQuestionPropValCascade> list = getJdbcTemplate().query(SQL_QUEST_PROPVAL_CASCADE_BY_ID,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionPropValCascade.class), id);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	public void addQuestionPropValCascade(
			List<ExamQuestionPropValCascade> questPropVal) {
		saveList(SQL_ADD_QUEST_PROPVAL_CASCADE, questPropVal);
	}
	
	public void updateQuestionPropValCascade(
			ExamQuestionPropValCascade questPropVal) {
		 getSimpleJdbcTemplate().update(SQL_DEL_QUEST_PROPVAL_CASCADE, new BeanPropertySqlParameterSource(questPropVal));
		 List<ExamQuestionPropValCascade> questPropValCascade = new ArrayList<ExamQuestionPropValCascade>();
		 questPropValCascade.add(questPropVal);
		 saveList(SQL_ADD_QUEST_PROPVAL_CASCADE, questPropValCascade);
	}
	@Override
	public void deleteQuestionPropValCascade(
			List<ExamQuestionPropValCascade> questPropVal) {
		saveList(SQL_DEL_QUEST_PROPVAL_CASCADE,questPropVal);
	}
	@Override
	public void deleteQuestionPropValCascadeByQuestionId(List<Object[]> id) {
		String sql = "delete from exam_quest_propval_cascade where exists (select * from exam_question q where q.id = exam_quest_propval_cascade.question_id and (q.parent_id = ? or q.id = ?))";
		updateBatch(sql, id);
	}

}
