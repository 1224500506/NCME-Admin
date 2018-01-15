package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamQuestionLabelDAO;
import com.hys.exam.model.ExamQuestionLabel;

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
public class ExamQuestionLabelJDBCDAO extends BaseDao implements
		ExamQuestionLabelDAO {

	/**
	 * 取试题题型 
	 * @param type 0: 取所有; 1： 不是子试题题型
 	 * @return
	 */
	public List<ExamQuestionLabel> getQuestionLabel(int type) {
		String sql = "";
		if(type==0){
			sql = "select id, name, is_child, l_type from exam_question_label order by id";
		}else{
			sql = "select id, name, is_child, l_type from exam_question_label where is_child = 0 order by id";
		}
		return getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionLabel.class));
	}

}
