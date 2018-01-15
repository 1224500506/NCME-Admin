package com.hys.exam.dao.local.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamQuestionKeyDAO;
import com.hys.exam.model.ExamQuestionKey;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2010-10-14
 * 
 * 描述：
 * 
 * 说明: 答案
 */
public class ExamQuestionKeyJDBCDAO extends BaseDao implements
		ExamQuestionKeyDAO {

	private ExamQuestionKeyRowMapper rowMapper = new ExamQuestionKeyRowMapper();

	private static final String SQL_ADD_QUESTION_KEY = "insert into exam_question_key (id, question_id, content, isnot_true, seq) values (?, ?, ?, ?, ?)";

	private static final String SQL_DEL_QUESTION_KEY = "delete from exam_question_key where exists (select * from exam_question q where q.id = exam_question_key.question_id and (q.parent_id = ? or q.id = ?))";

	private static final String SQL_GET_QUESTIONKEY_BY_QUESTIONID = "select id, question_id, content, isnot_true, seq from exam_question_key k where k.question_id = ? order by seq";

	public int[] addQuestionKey(List<ExamQuestionKey> key) {

		List<Object[]> batch = new ArrayList<Object[]>();

		for (int i = 0; i < key.size(); i++) {
			ExamQuestionKey questionkey = key.get(i);
			//questionkey.setId(getNextId(Constants.TABLE_EXAM_QUESTION_KEY));
			Object[] values = new Object[] { questionkey.getId(),
					questionkey.getQuestion_id(), questionkey.getContent(),
					questionkey.getIsnot_true(), questionkey.getSeq() };
			batch.add(values);
		}

		return getSimpleJdbcTemplate().batchUpdate(SQL_ADD_QUESTION_KEY, batch);

	}

	public int[] deleteQuestionKey(List<Object[]> id) {
		return updateBatch(SQL_DEL_QUESTION_KEY,id);
	}

	public List<ExamQuestionKey> getQuestionKeys(Long id) {
		return getJdbcTemplate().query(SQL_GET_QUESTIONKEY_BY_QUESTIONID,
				rowMapper, id);
	}

	private static final class ExamQuestionKeyRowMapper implements
			ParameterizedRowMapper<ExamQuestionKey> {
		public ExamQuestionKey mapRow(ResultSet rs, int arg1)
				throws SQLException {
			ExamQuestionKey qk = new ExamQuestionKey();
			qk.setId(rs.getLong("id"));
			qk.setQuestion_id(rs.getLong("question_id"));
			qk.setContent(rs.getString("content"));
			qk.setIsnot_true(rs.getInt("isnot_true"));
			qk.setSeq(rs.getInt("seq"));
			return qk;
		}
	}

}
