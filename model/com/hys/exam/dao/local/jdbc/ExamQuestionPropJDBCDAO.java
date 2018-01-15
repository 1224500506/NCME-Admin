package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamQuestionPropDAO;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamSource;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-13
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionPropJDBCDAO extends BaseDao implements
		ExamQuestionPropDAO {
	
	@SuppressWarnings("unchecked")
	public void addQuestionProp(Map<String,List<ExamQuestionProp>> questionPropMap) {
		String base_sql[] = {"insert into "," (question_id, prop_val_id) values (:question_id, :prop_val_id)"};
		for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
			String sql = "";
			Map.Entry entry = (Map.Entry) iter.next();
			List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
			sql = base_sql[0]+ Constants.getPropNameAll().get(entry.getKey().toString()) + base_sql[1];
			
/*			if (!entry.getKey().toString().equals(Constants.EXAM_PROP_SOURCE))
			for (int i=0; i<propValList.size(); i++) {
				try{
					String my_sql = "select prop_val_id from SUB_SYS_PROP_VAL where id=" + propValList.get(i).getProp_val_id();
					Long id = getJdbcTemplate().queryForLong(my_sql);
					propValList.get(i).setProp_val_id(id);
				}catch(Exception e){}
			}
*/			
			saveList(sql, propValList);
		}
	}
	
	@Override
	public void addQuestionProp(ExamQuestionProp questionProp, String propKey) {
		String base_sql[] = {"insert into "," (question_id, prop_val_id) values (:question_id, :prop_val_id)"};
		String sql = base_sql[0]+ Constants.getPropNameAll().get(propKey) + base_sql[1];
		
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(questionProp));
		
	}

	@SuppressWarnings("unchecked")
	public void deleteQuestionPropByQuestionId(List<Object[]> id) {
		for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			updateBatch("delete from "+entry.getValue()+" where exists (select * from exam_question q where q.id = "+entry.getValue()+".question_id and (q.parent_id = ? or q.id = ?))",id);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void deleteQuestionProprByQuestionId(List<Object[]> id) {
		for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().toString().equals(Constants.EXAM_PROP_STUDY1) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_STUDY2) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_STUDY3) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_UNIT) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_POINT) ||
					entry.getKey().toString().equals(Constants.EXAM_PROP_SYS)) {
			updateBatch("delete from "+entry.getValue()+" where exists (select * from exam_question q where q.id = "+entry.getValue()+".question_id and (q.parent_id = ? or q.id = ?))",id);
			}
		}	
	}

	@SuppressWarnings("unchecked")
	public Map<String,List<ExamQuestionProp>> getQuestionPropByQuestionId(Long id) {
		
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
		String base_sql[] = {"select s.question_id, v.id as prop_val_id, t.name as prop_val_name from exam_prop_val t left join sub_sys_prop_val v on t.id=v.prop_val_id,"," s where v.id = s.prop_val_id and s.question_id = ?"};
		
		for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
			String sql = "";
			Map.Entry entry = (Map.Entry) iter.next();
			sql = base_sql[0]+ entry.getValue().toString() + base_sql[1];
			if (entry.getValue().toString().equals("EXAM_QUESTION_SOURCE")) {
				sql = "select s.QUESTION_ID, t.id as prop_val_id, t.name as prop_val_name from exam_source_val t, exam_question_source s where t.id=s.PROP_VAL_ID and s.QUESTION_ID=?";
			}
			List<ExamQuestionProp> propValList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionProp.class),id);
			
			/*if (entry.getValue().toString().equals("EXAM_QUESTION_SOURCE")) {
				String prop_sql = "select * from EXAM_SOURCE_VAL where Id=?";
				for (int i=0; i<propValList.size(); i++) {
					try{ExamSource source = new ExamSource();
					try{
						source = getJdbcTemplate().queryForObject(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamSource.class), propValList.get(i).getProp_val_id());
						propValList.get(i).setProp_val_name(source.getName());
					} catch(Exception ex){}
					}catch(Exception e){;}
				}
			}*/
			
			if(propValList!=null && (!propValList.isEmpty())){
				questionPropMap.put(entry.getKey().toString(), propValList);
			}
		}	
		
		return questionPropMap;
	}

	@Override
	public boolean checkProp(String tableName, ExamQuestionProp prop) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append(tableName);
		sql.append(" where question_id = ? and prop_val_id = ?");
		
		try{
			ExamQuestionProp temp = getJdbcTemplate().queryForObject(sql.toString(),
					ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionProp.class), prop.getQuestion_id(),prop.getProp_val_id());
			if(null == temp) {
				flag = true;
			}
		}catch(EmptyResultDataAccessException e) {
			return true;
		}
		
		return flag;
	}


}
