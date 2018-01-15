package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.CodeGenerator;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamExaminTypeDAO;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-10
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminTypeJDBCDAO extends BaseDao implements
		ExamExaminTypeDAO {

	public ExamExaminType addExamExaminType(ExamExaminType etype) {
		String sql = "insert into exam_exam_type (id, sub_sys_id, parent_id, name, code, seq, layer, state, remark) values (:id, :sub_sys_id, :parent_id, :name, :code, :seq, :layer, :state, :remark)";
		String code = getMaxCode(etype.getParent_id());
		etype.setCode(code);
		etype.setId(getNextId("exam_exam_type"));
		etype.setSeq(Integer.parseInt(code.substring(code.length() - 8)));
		getSimpleJdbcTemplate()
				.update(sql, new BeanPropertySqlParameterSource(etype));
		return etype;
	}

	@Override
	public void deleteExamExaminTypeById(ExamExaminType etype) {
		String sql = "delete from exam_exam_type where code like concat(?,'%')";
		getJdbcTemplate().update(sql, getExamExaminTypeById(etype.getId()).getCode());
	}

	@Override
	public void deleteExamExaminType(ExamExaminType etype) {
		String sql = "update exam_exam_type set STATE=? where code like concat(?,'%')";
		getJdbcTemplate().update(sql, Constants.STATUS_2,
				getExamExaminTypeById(etype.getId()).getCode());
	}

	@Override
	public ExamExaminType getExamExaminTypeById(Long id) {
		String sql = "select t.*,(select count(1) from exam_exam_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_exam_type t where t.id = ?";
		return getJdbcTemplate().queryForObject(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExaminType.class), id);
	}

	@SuppressWarnings("unchecked")
	public ExamReturnExaminType getExamExaminTypeListByParentId(ExamExaminTypeQuery query) {
		ExamReturnExaminType returnObj = new ExamReturnExaminType();
		
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select t.*,(select count(1) from exam_exam_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_exam_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		sql.append("order by t.seq");
		
		List<ExamExaminType> returnList = getJdbcTemplate().query(
				PageUtil.getPageSql(sql.toString(), query.getPageNo(), query
						.getPageSize()),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExaminType.class),params.toArray());
		
		returnObj.setReturnList(returnList);
		returnObj.setTotal_count(getExamExaminTypeSizeByParentId(query));
		return returnObj;
	}
	
	@SuppressWarnings("unchecked")
	private int getExamExaminTypeSizeByParentId(ExamExaminTypeQuery query){
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select count(1) from exam_exam_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		return getJdbcTemplate().queryForInt(sql.toString(), params.toArray());
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(Integer[] idArr) {
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t.*,(select count(1) from exam_exam_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_exam_type t where t.parent_id = 0 and t.layer = 0 and t.state = 1");

		if (idArr != null) {
			String id = "";
			for (int di : idArr) {
				if (id.equals("")) {
					id += di + "";
				} else {
					id += "," + di;
				}
			}
			sql.append(" and t.sub_sys_id in(" + id + ")");
		}
		
		sql.append(" order by t.seq");

		return getJdbcTemplate().query(
				sql.toString(),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExaminType.class));
	}

	@SuppressWarnings("unchecked")
	public ExamExaminType updateExamExaminType(ExamExaminType etype) {
		StringBuffer sql = new StringBuffer();
		List list = new ArrayList();
		sql.append("update exam_exam_type set ");
		if (!StringUtils.checkNull(etype.getName())) {
			sql.append("name = ?,");
			list.add(etype.getName());
		}
		if (etype.getParent_id() != null) {
			sql.append("parent_id = ?,");
			list.add(etype.getParent_id());
		}
		if (etype.getSeq() != null) {
			sql.append("seq = ?,");
			list.add(etype.getSeq());
		}
		if (!StringUtils.checkNull(etype.getCode())) {
			sql.append("code = ?,");
			list.add(etype.getCode());
		}
		
		if (!StringUtils.checkNull(etype.getRemark())) {
			sql.append("remark = ?,");
			list.add(etype.getRemark());
		}
		
		sql.append(" state = state where id = ?");
		list.add(etype.getId());
		getJdbcTemplate().update(sql.toString(), list.toArray());
		return etype;
	}
	
	private static final String SQL_GET_MAX_CODE = "select max(code) as code from exam_exam_type t where t.parent_id = ?";

	private String getMaxCode(Long parentId) {
		// 父节点CODE
		String parentCode = "0";
		if (parentId.intValue() != 0) {
			parentCode = getExamExaminTypeById(parentId).getCode();
		}
		// 平级最大CODE
		String maxCode = getJdbcTemplate().queryForObject(
				SQL_GET_MAX_CODE,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExaminType.class), parentId)
				.getCode();
		return CodeGenerator.genratorCode(parentCode, maxCode);
	}

	@Override
	public void updateMoveExaminType(ExamExaminType etype) {
		String code = getMaxCode(etype.getParent_id());
		int seq = Integer.parseInt(code.substring(code.length()-8));
		String sql = "update exam_exam_type t set t.code= CONCAT(? ,SUBSTR(t.code,LENGTH(?)+1))"
				+ ",t.parent_id = CASE WHEN (t.code = ?) THEN ? ELSE t.parent_id END,t.seq = CASE WHEN(t.code = ?) THEN ? ELSE t.seq END"
				+ ",t.layer = LENGTH(CONCAT(?, SUBSTR(t.code, LENGTH(?) + 1)))/8 where code like CONCAT(?,'%')";
		getSimpleJdbcTemplate().update(sql,code,etype.getCode(),etype.getCode(),etype.getParent_id(),etype.getCode(),seq,code,etype.getCode(),etype.getCode());
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(Integer[] idArr) {
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t.*,(select count(1) from exam_exam_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_exam_type t where t.state = 1");

		if (idArr != null) {
			String id = "";
			for (int di : idArr) {
				if (id.equals("")) {
					id += di + "";
				} else {
					id += "," + di;
				}
			}
			sql.append(" and t.id in(" + id + ")");
		}
		
		sql.append(" order by t.seq");

		return getJdbcTemplate().query(
				sql.toString(),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExaminType.class));
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootListByChildId(Long id) {
		String sql = "SELECT tx.* FROM exam_exam_type tx START WITH tx.ID = ? CONNECT BY PRIOR tx.parent_id = tx.id order by tx.id desc";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper
				.newInstance(ExamExaminType.class),id);
	}

}
