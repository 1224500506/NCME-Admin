package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamOlemPropDAO;
import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemPropJDBCDAO extends BaseDao implements
		ExamOlemPropDAO {

	
	
	public void addExamOlemProp(ExamOlemProp prop) {
		String sql = "insert into olem_question_prop (id, parent_id, olem_prop_name, olem_prop_type, relation, sys_prop) values (null, :parent_id, :olem_prop_name, :olem_prop_type, :relation, :sys_prop)";
		getSimpleJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(prop));		
	}
	
	@Override
	public void addBatchExamOlemProp(List<ExamOlemProp> propList) {
		String sql = "insert into olem_question_prop (id, parent_id, olem_prop_name, olem_prop_type, relation, sys_prop) values (:id, :parent_id, :olem_prop_name, :olem_prop_type, :relation, :sys_prop)";
		saveList(sql,propList);
	}

	public Long getId() {
		return getNextId("olem_question_prop");
	}

	public boolean deleteExamOlemPropById(ExamOlemProp prop) {
		String sql_ref = "delete from olem_prop_ref_base_prop where olem_prop_id = ?";
		String sql_olem = "delete from olem_question_prop where id = ?";
		
		ExamOlemPropQuery query = new ExamOlemPropQuery();
		query.setParent_id(prop.getId());
		
		int size = getExamOlemPropList(query).getTotal_count();
		//是否有关联下级属性
		if(size==0){
			//删除与基本属性的关联
			getSimpleJdbcTemplate().update(sql_ref,prop.getId());
			//删除三基的应用属性
			getSimpleJdbcTemplate().update(sql_olem,prop.getId());
			return true;
		} else {
			return false;
		}

	}

	public ExamOlemProp getExamOlemPropById(ExamOlemProp prop) {
		String sql = "select * from olem_question_prop where id = ?";
		return getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamOlemProp.class), prop.getId());
	}

	public ExamReturnOlemProp getExamOlemPropList(ExamOlemPropQuery query) {
		String sql = "select * from olem_question_prop t where t.parent_id = ? order by id";
		ExamReturnOlemProp rprop = new ExamReturnOlemProp();
		List<ExamOlemProp> returnList = getJdbcTemplate().query(PageUtil.getPageSql(sql, query.getPageNo(), query
				.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(ExamOlemProp.class), query.getParent_id());
		rprop.setReturnList(returnList);
		rprop.setTotal_count(getExamOlemPropSize(query));
		return rprop;
	}
	
	private int getExamOlemPropSize(ExamOlemPropQuery query){
		String sql = "select count(1) from olem_question_prop t where t.parent_id = ?";
		return getJdbcTemplate().queryForInt(sql,query.getParent_id());
	}

	public void updateExamOlemProp(ExamOlemProp prop) {
		String sql = "update olem_question_prop set olem_prop_name = ? where id = ?";
		getJdbcTemplate().update(sql,prop.getOlem_prop_name(),prop.getId());
	}

	public void addExamOlemPropRefBaseProp(List<ExamOlemPropRefBaseProp> prop) {
		String sql = "insert into olem_prop_ref_base_prop (olem_prop_id, base_prop_id) values (:olem_prop_id, :base_prop_id)";
		saveList(sql,prop);
	}

	public void deleteExamOlemPropRefBaseProp(ExamOlemPropRefBaseProp prop) {
		String sql = "delete from olem_prop_ref_base_prop where olem_prop_id = ? and base_prop_id = ?";
		getJdbcTemplate().update(sql,prop.getOlem_prop_id(),prop.getBase_prop_id());
	}

	@Override
	public List<ExamOlemPropRefBaseProp> getExamOlemPropRefBasePropList(Long id) {
		String sql = "select t1.olem_prop_id, t1.base_prop_id, t3.id, t3.type, t3.name from olem_question_prop t, olem_prop_ref_base_prop t1, SUB_SYS_PROP_VAL t2, EXAM_PROP_VAL t3 where t.id = t1.olem_prop_id and t1.base_prop_id = t2.id and t2.prop_val_id = t3.id and t.id = ?";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamOlemPropRefBaseProp.class), id);
	}

	@Override
	public int getExamOlemPropType(Long id) {
		String sql = "select max(distinct t3.type) from olem_question_prop t, olem_prop_ref_base_prop t1,  SUB_SYS_PROP_VAL t2, EXAM_PROP_VAL t3 where t.id = t1.olem_prop_id and t1.base_prop_id = t2.id and t2.prop_val_id = t3.id and t.id = ?";
		return getJdbcTemplate().queryForInt(sql,id);
	}


	
	
}
