package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.CodeGenerator;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPaperTypeDAO;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
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
public class ExamPaperTypeJDBCDAO extends BaseDao implements
		ExamPaperTypeDAO {

	public ExamPaperType addExamPaperType(ExamPaperType ptype) {
		String sql = "insert into exam_paper_type (id, sub_sys_id, parent_id, name, code, seq, layer, state, remark) values (:id, :sub_sys_id, :parent_id, :name, :code, :seq, :layer, :state, :remark)";
		String code = getMaxCode(ptype.getParent_id());
		ptype.setCode(code);
		ptype.setId(getNextId("exam_paper_type"));
		ptype.setSeq(Integer.parseInt(code.substring(code.length() - 8)));
		getSimpleJdbcTemplate()
				.update(sql, new BeanPropertySqlParameterSource(ptype));
		return ptype;		
	}

	public void deleteExamPaperType(ExamPaperType ptype) {
		String sql = "delete from exam_paper_type where code like concat(?,'%')";
		getJdbcTemplate().update(sql, getExamPaperTypeById(ptype.getId()).getCode());
	}

	public ExamPaperType getExamPaperTypeById(Long id) {
		String sql = "select * from exam_paper_type t where t.id = ?";
		return getJdbcTemplate().queryForObject(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPaperType.class), id);
	}

	@SuppressWarnings("unchecked")
	public ExamReturnPaperType getExamPaperTypeListByParentId(ExamPaperTypeQuery query) {
		ExamReturnPaperType returnObj = new ExamReturnPaperType();
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select t.*,(select count(1) from exam_paper_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_paper_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		sql.append("order by t.seq");
		
		List<ExamPaperType> returnList = getJdbcTemplate().query(
				PageUtil.getPageSql(sql.toString(), query.getPageNo(), query
						.getPageSize()),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPaperType.class),params.toArray());
		returnObj.setReturnList(returnList);
		returnObj.setTotal_count(getExamPaperTypeSizeByParentId(query));
		
		return returnObj;
	}
	
	@SuppressWarnings("unchecked")
	private int getExamPaperTypeSizeByParentId(ExamPaperTypeQuery query){
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select count(1) from exam_paper_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		
		return getJdbcTemplate().queryForInt(sql.toString(), params.toArray());		
	}

	public List<ExamPaperType> getExamPaperTypeRootListBySysId(Integer[] idArr) {
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t.*,(select count(1) from exam_paper_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_paper_type t where t.parent_id = 0 and t.layer = 0 and t.state = 1");

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
						.newInstance(ExamPaperType.class));
	}

	@SuppressWarnings("unchecked")
	public ExamPaperType updateExamPaperType(ExamPaperType ptype) {
		StringBuffer sql = new StringBuffer();
		List list = new ArrayList();
		sql.append("update exam_paper_type set ");
		if (!StringUtils.checkNull(ptype.getName())) {
			sql.append("name = ?,");
			list.add(ptype.getName());
		}
		if (ptype.getParent_id() != null) {
			sql.append("parent_id = ?,");
			list.add(ptype.getParent_id());
		}
		if (ptype.getSeq() != null) {
			sql.append("seq = ?,");
			list.add(ptype.getSeq());
		}
		if (!StringUtils.checkNull(ptype.getCode())) {
			sql.append("code = ?,");
			list.add(ptype.getCode());
		}
		if (!StringUtils.checkNull(ptype.getRemark())) {
			sql.append("remark = ?,");
			list.add(ptype.getRemark());
		}
		sql.append(" state = state where id = ?");
		list.add(ptype.getId());
		getJdbcTemplate().update(sql.toString(), list.toArray());
		return ptype;
	}
	
	private static final String SQL_GET_MAX_CODE = "select max(code) as code from exam_paper_type t where t.parent_id = ?";

	private String getMaxCode(Long parentId) {
		// 父节点CODE
		String parentCode = "0";
		if (parentId.intValue() != 0) {
			parentCode = getExamPaperTypeById(parentId).getCode();
		}
		// 平级最大CODE
		String maxCode = getJdbcTemplate().queryForObject(
				SQL_GET_MAX_CODE,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPaperType.class), parentId)
				.getCode();
		return CodeGenerator.genratorCode(parentCode, maxCode);
	}

	@Override
	public void updateMovePaperType(ExamPaperType ptype) {
		String code = getMaxCode(ptype.getParent_id());
		int seq = Integer.parseInt(code.substring(code.length()-8));
		String sql = "update exam_paper_type t set t.code= CONCAT(? ,SUBSTR(t.code,LENGTH(?)+1))"
				+ ",t.parent_id = CASE WHEN (t.code = ?) THEN ? ELSE t.parent_id END,t.seq = CASE WHEN(t.code = ?) THEN ? ELSE t.seq END"
				+ ",t.layer = LENGTH(CONCAT(?, SUBSTR(t.code, LENGTH(?) + 1)))/8 where code like CONCAT(?,'%')";
		getSimpleJdbcTemplate().update(sql,code,ptype.getCode(),ptype.getCode(),ptype.getParent_id(),ptype.getCode(),seq,code,ptype.getCode(),ptype.getCode());
		
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListById(Integer[] idArr) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,(select count(1) from exam_paper_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_paper_type t where t.state = 1");
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
						.newInstance(ExamPaperType.class));
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListByChildId(Long id) {
		String sql = "SELECT tx.* FROM exam_paper_type tx START WITH tx.ID = ? CONNECT BY PRIOR tx.parent_id = tx.id order by tx.id desc";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper
				.newInstance(ExamPaperType.class),id);
	}

}
