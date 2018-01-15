package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamQuestionPropValCascadeDAO;
import com.hys.exam.model.ExamQuestionPropValCascade;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 10, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionPropValCascadeJDBCDAO extends BaseDao implements
		ExamQuestionPropValCascadeDAO {

	private static final String SQL_QUEST_PROPVAL_CASCADE_BY_ID = "select question_id, propval_name, propval_id from exam_quest_propval_cascade where question_id = ?";

	private static final String SQL_ADD_QUEST_PROPVAL_CASCADE = "insert into exam_quest_propval_cascade (question_id, propval_name, propval_id) values (?, ?, ?)";
	
	private static final String SQL_DEL_QUEST_PROPVAL_CASCADE = "delete from exam_quest_propval_cascade where question_id = ?";
	
	
	
	@Override
	public void addQuestionPropValCascade(String key,
			List<ExamQuestionPropValCascade> questPropVal) throws SQLException {
		
		List<Object[]> batch = new ArrayList<Object[]>();
		for(ExamQuestionPropValCascade c : questPropVal){
			Object[] values = new Object[] { 
					c.getQuestion_id(),
					c.getPropval_name(),
					c.getPropval_id()
			};
			batch.add(values);
		}
		
		batchUpdate(key,SQL_ADD_QUEST_PROPVAL_CASCADE, batch);
	}

	@Override
	public void deleteQuestionPropValCascade(String key,
			List<ExamQuestionPropValCascade> questPropVal) throws SQLException {
		List<Object[]> batch = new ArrayList<Object[]>();
		for(ExamQuestionPropValCascade c : questPropVal){
			Object[] values = new Object[] { 
				c.getQuestion_id()
			};
			batch.add(values);
		}
		batchUpdate(key,SQL_DEL_QUEST_PROPVAL_CASCADE, batch);

	}

	@Override
	public void deleteQuestionPropValCascadeByQuestionId(String key,
			List<Object[]> id) throws SQLException {
		String sql = "delete from exam_quest_propval_cascade where exists (select * from exam_question q where q.id = exam_quest_propval_cascade.question_id and (q.parent_id = ? or q.id = ?))";
		batchUpdate(key, sql, id);
	}

	@Override
	public ExamQuestionPropValCascade getQuestionPropValCascadeById(String key,
			Long id) throws SQLException {
		ParameterizedRowMapper<ExamQuestionPropValCascade> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionPropValCascadeListMapper);
		List<ExamQuestionPropValCascade> list = queryForList(key, SQL_QUEST_PROPVAL_CASCADE_BY_ID, new Object[]{id},mapper);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updateQuestionPropValCascade(String key,
			ExamQuestionPropValCascade questPropVal) throws SQLException {
		 update(key, SQL_DEL_QUEST_PROPVAL_CASCADE, new Object[]{questPropVal.getQuestion_id()});
		 update(key, SQL_ADD_QUEST_PROPVAL_CASCADE, new Object[] {questPropVal.getQuestion_id(),questPropVal.getPropval_name(),questPropVal.getPropval_id()});
	}

}
