package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.CodeGenerator;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamQuestionTypeDAO;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-13
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionTypeJDBCDAO extends BaseDao implements
		ExamQuestionTypeDAO {

	@Override
	public List<ExamQuestionType> getQuestionTypeRootBySysId(Integer[] idArr) {
		StringBuffer sql = new StringBuffer();

		sql.append("select t.*,(select count(1) from exam_question_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_question_type t where t.parent_id = 0 and t.layer = 0 and t.state = 1");

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
						.newInstance(ExamQuestionType.class));
	}
	

	@Override
	public List<ExamQuestionType> getQuestionTypeRootById(Integer[] idArr) {
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t.*,(select count(1) from exam_question_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_question_type t where t.state = 1");

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
						.newInstance(ExamQuestionType.class));
	}

	@Override
	public List<ExamQuestionType> getSubSysTypeByTypeId(Long[] idArr) {
		StringBuffer sql = new StringBuffer();

		sql.append("select * from exam_question_type t ");
		if (idArr != null) {
			String di = "";
			for (Long id : idArr) {
				if (di.equals("")) {
					di += id.toString();
				} else {
					di += "," + id;
				}
			}
			sql.append("where t.id in(" + di + ")");
		}

		return getJdbcTemplate().query(
				sql.toString(),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestionType.class));
	}

	public ExamQuestionType addExamQuestType(ExamQuestionType qtype) {
		String sql = "insert into exam_question_type (id, parent_id, sub_sys_id, name, code, seq, state, layer, remark) values (:id, :parent_id, :sub_sys_id, :name, :code, :seq, :state, :layer, :remark)";
		String code = getMaxCode(qtype.getParent_id());
		qtype.setCode(code);
		qtype.setId(getNextId("exam_question_type"));
		qtype.setSeq(Integer.parseInt(code.substring(code.length() - 8)));
		getSimpleJdbcTemplate()
				.update(sql, new BeanPropertySqlParameterSource(qtype));
		return qtype;
	}

	public void deleteExamQuestTypeById(ExamQuestionType qtype) {
		String sql = "delete from exam_question_type where code like concat(?,'%')";
		getJdbcTemplate().update(sql, getExamQuestionTypeById(qtype.getId()).getCode());
	}

	public ExamQuestionType getExamQuestionTypeById(Long id) {
		String sql = "select * from exam_question_type t where t.id = ?";
		return getJdbcTemplate().queryForObject(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestionType.class), id);
	}

	@SuppressWarnings("unchecked")
	public ExamQuestionType updateExamQuestTypeById(ExamQuestionType qtype) {
		StringBuffer sql = new StringBuffer();
		List list = new ArrayList();
		sql.append("update exam_question_type set ");
		if (!StringUtils.checkNull(qtype.getName())) {
			sql.append("name = ?,");
			list.add(qtype.getName());
		}
		if (qtype.getParent_id() != null) {
			sql.append("parent_id = ?,");
			list.add(qtype.getParent_id());
		}
		if (qtype.getSeq() != null) {
			sql.append("seq = ?,");
			list.add(qtype.getSeq());
		}
		if (!StringUtils.checkNull(qtype.getCode())) {
			sql.append("code = ?,");
			list.add(qtype.getCode());
		}
		
		if(!StringUtils.checkNull(qtype.getRemark())){
			sql.append("remark = ?,");
			list.add(qtype.getRemark());
		}
		
		sql.append(" state = state where id = ?");
		list.add(qtype.getId());
		getJdbcTemplate().update(sql.toString(), list.toArray());
		return qtype;
	}

	private static final String SQL_GET_MAX_CODE = "select max(code) as code from exam_question_type t where t.parent_id = ?";

	private String getMaxCode(Long parentId) {
		// 父节点CODE
		String parentCode = "0";
		if (parentId.intValue() != 0) {
			parentCode = getExamQuestionTypeById(parentId).getCode();
		}
		// 平级最大CODE
		String maxCode = getJdbcTemplate().queryForObject(
				SQL_GET_MAX_CODE,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestionType.class), parentId)
				.getCode();
		return CodeGenerator.genratorCode(parentCode, maxCode);
	}

	@SuppressWarnings("unchecked")
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(ExamQuestionTypeQuery query) {
		ExamReturnQuestionType returnObj = new ExamReturnQuestionType();
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select t.*,(select count(1) from exam_question_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_question_type t where t.state = 1 and t.parent_id = ? ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		sql.append("order by t.seq");
		
		List<ExamQuestionType> returnList = getJdbcTemplate().query(
				PageUtil.getPageSql(sql.toString(), query.getPageNo(), query
						.getPageSize()),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestionType.class),params.toArray());
		returnObj.setReturnList(returnList);
		returnObj.setTotal_count(getExamQuestionTypeSizeByParentId(query));
		return returnObj;
	}
	
	@SuppressWarnings("unchecked")
	private int getExamQuestionTypeSizeByParentId(ExamQuestionTypeQuery query){
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select count(1) from exam_question_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and t.name like ? ");
			params.add("%"+query.getName()+"%");
			
		}
		
		return getJdbcTemplate().queryForInt(sql.toString(), params.toArray());
	}

	@Override
	public void updateMoveQuestionType(ExamQuestionType qtype) {
		String code = getMaxCode(qtype.getParent_id());
		int seq = Integer.parseInt(code.substring(code.length()-8));
		String sql = "update exam_question_type t set t.code= CONCAT(? ,SUBSTR(t.code,LENGTH(?)+1))"
				+ ",t.parent_id = CASE WHEN (t.code = ?) THEN ? ELSE t.parent_id END,t.seq = CASE WHEN(t.code = ?) THEN ? ELSE t.seq END"
				+ ",t.layer = LENGTH(CONCAT(?, SUBSTR(t.code, LENGTH(?) + 1)))/8 where code like CONCAT(?,'%')";
		getSimpleJdbcTemplate().update(sql,code,qtype.getCode(),qtype.getCode(),qtype.getParent_id(),qtype.getCode(),seq,code,qtype.getCode(),qtype.getCode());
	}


	@Override
	public List<ExamQuestionType> getExamQuestionTypeRootListByChildId(Long id) {
		String sql = "SELECT tx.* FROM exam_question_type tx START WITH tx.ID = ? CONNECT BY PRIOR tx.parent_id = tx.id order by tx.id desc";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper
				.newInstance(ExamQuestionType.class),id);
	}
}
