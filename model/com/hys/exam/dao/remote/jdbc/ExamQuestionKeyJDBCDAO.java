package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamQuestionKeyDAO;
import com.hys.exam.model.ExamQuestionKey;

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
public class ExamQuestionKeyJDBCDAO extends BaseDao implements
		ExamQuestionKeyDAO {



	private static final String SQL_ADD_QUESTION_KEY = "insert into exam_question_key (id, question_id, content, isnot_true, seq) values (?, ?, ?, ?, ?)";

	private static final String SQL_DEL_QUESTION_KEY = "delete from exam_question_key where exists (select * from exam_question q where q.id = exam_question_key.question_id and (q.parent_id = ? or q.id = ?))";

	private static final String SQL_GET_QUESTIONKEY_BY_QUESTIONID = "select id, question_id, content, isnot_true, seq from exam_question_key k where k.question_id = ? order by seq";

	public int[] addQuestionKey(String key,List<ExamQuestionKey> questionKeyList) throws SQLException {

		List<Object[]> batch = new ArrayList<Object[]>();

		for (int i = 0; i < questionKeyList.size(); i++) {
			ExamQuestionKey questionkey = questionKeyList.get(i);
			questionkey.setId(getSeqNextvalForLong(key,Constants.TABLE_EXAM_QUESTION_KEY));
			Object[] values = new Object[] { questionkey.getId(),
					questionkey.getQuestion_id(), questionkey.getContent(),
					questionkey.getIsnot_true(), questionkey.getSeq() };
			batch.add(values);
		}

		return batchUpdate(key,SQL_ADD_QUESTION_KEY, batch);

	}

	public int[] deleteQuestionKey(String key,List<Object[]> id) throws SQLException {
		return batchUpdate(key,SQL_DEL_QUESTION_KEY,id);
	}

	public List<ExamQuestionKey> getQuestionKeys(String key,Long id) throws SQLException {
		
		ParameterizedRowMapper<ExamQuestionKey> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionKeyListMapper);

		return queryForList(key, SQL_GET_QUESTIONKEY_BY_QUESTIONID,
				new Object[] { id }, mapper);
	}

}
