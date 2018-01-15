package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamQuestionPropDAO;
import com.hys.exam.model.ExamQuestionProp;

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
public class ExamQuestionPropJDBCDAO extends BaseDao implements
		ExamQuestionPropDAO {

	@Override
	@SuppressWarnings("unchecked")
	public void addQuestionProp(String key,
			Map<String, List<ExamQuestionProp>> questionPropMap)
			throws SQLException {
		
		String base_sql[] = {"insert into "," (question_id, prop_val_id) values (:question_id, :prop_val_id)"};
		for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
			String sql = "";
			Map.Entry entry = (Map.Entry) iter.next();
			List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
			List<Object[]> batch = new ArrayList<Object[]>();
			for(ExamQuestionProp p : propValList){
				Object[] values = new Object[] {
						p.getQuestion_id(),
						p.getProp_val_id()
				};
				batch.add(values);
			}
			sql = base_sql[0]+ Constants.getPropNameAll().get(entry.getKey().toString()) + base_sql[1];
			batchUpdate(key, sql, batch);
		}

	}

	@Override
	public boolean checkProp(String key, String tableName, ExamQuestionProp prop)
			throws SQLException {
		
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append(tableName);
		sql.append(" where question_id = ? and prop_val_id = ?");
		ParameterizedRowMapper<ExamQuestionProp> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionPropMapper);
		List<ExamQuestionProp> list = queryForList(key,sql.toString(),new Object[]{prop.getQuestion_id(),prop.getProp_val_id()},mapper);
		
		if(null == list && list.isEmpty()) {
			flag = true;
		}
		return flag;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteQuestionPropByQuestionId(String key, List<Object[]> id) throws SQLException {
		for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			batchUpdate(key, "delete from "+entry.getValue()+" where exists (select * from exam_question q where q.id = "+entry.getValue()+".question_id and (q.parent_id = ? or q.id = ?))",id);
		}	
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteQuestionProprByQuestionId(String key, List<Object[]> id)
			throws SQLException {
		for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().toString().equals(Constants.EXAM_PROP_STUDY1) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_STUDY2) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_STUDY3) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_UNIT) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_POINT) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_SYS)) {
				batchUpdate(key, "delete from "+entry.getValue()+" where exists (select * from exam_question q where q.id = "+entry.getValue()+".question_id and (q.parent_id = ? or q.id = ?))", id);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, List<ExamQuestionProp>> getQuestionPropByQuestionId(
			String key, Long id) throws SQLException {
		
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
		String base_sql[] = {"select s.question_id, t.id as prop_val_id, t.name as prop_val_name from exam_prop_val t,"," s where t.id = s.prop_val_id and s.question_id = ?"};
		
		for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
			String sql = "";
			Map.Entry entry = (Map.Entry) iter.next();
			sql = base_sql[0]+ entry.getValue().toString() + base_sql[1];
			ParameterizedRowMapper<ExamQuestionProp> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionPropListMapper);
			List<ExamQuestionProp> propValList = queryForList(key, sql, new Object[]{id}, mapper);
			if(propValList!=null && (!propValList.isEmpty())){
				questionPropMap.put(entry.getKey().toString(), propValList);
			}
		}	
		
		return questionPropMap;
	}

}
