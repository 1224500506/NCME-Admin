package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamQuestionLabelDAO;
import com.hys.exam.model.ExamQuestionLabel;

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
public class ExamQuestionLabelJDBCDAO extends BaseDao implements
		ExamQuestionLabelDAO {

	@Override
	public List<ExamQuestionLabel> getQuestionLabel(String key, int type)
			throws SQLException {
		String sql = "";
		if(type==0){
			sql = "select id, name, is_child, l_type from exam_question_label";
		}else{
			sql = "select id, name, is_child, l_type from exam_question_label where is_child = 0";
		}
		
		ParameterizedRowMapper<ExamQuestionLabel> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionLabelListMapper);
		
		return queryForList(key, sql, mapper);
	}

}
