package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.common.util.CodeGenerator;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamQuestionTypeDAO;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

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
public class ExamQuestionTypeJDBCDAO extends BaseDao implements
		ExamQuestionTypeDAO {


	@Override
	public List<ExamQuestionType> getQuestionTypeRootBySysId(String key,Integer[] idArr) throws SQLException {
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t.*,(select count(1) from exam_question_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_question_type t where t.parent_id = 0 and t.layer = 0 and t.state = 1");

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

		ParameterizedRowMapper<ExamQuestionType> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionTypeListMapper);
		
		return queryForList(key, sql.toString(),
				mapper);
	}
	

	@Override
	public List<ExamQuestionType> getQuestionTypeRootById(String key,Integer[] idArr) throws SQLException {
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
		
		ParameterizedRowMapper<ExamQuestionType> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionTypeListMapper);

		return queryForList(key,
				sql.toString(), mapper);
	}

	@Override
	public List<ExamQuestionType> getSubSysTypeByTypeId(String key,Long[] idArr) throws SQLException {
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
		ParameterizedRowMapper<ExamQuestionType> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionTypeListMapper);
		
		return queryForList(sql.toString(), mapper);
	}

	public ExamQuestionType addExamQuestType(String key,ExamQuestionType qtype) throws SQLException {
		String sql = "insert into exam_question_type (id, parent_id, sub_sys_id, name, code, seq, state, layer, remark) values (?,?,?,?,?,?,?,?,?)";
		String code = getMaxCode(key,qtype.getParent_id());
		qtype.setCode(code);
		qtype.setId(getSeqNextvalForLong(key,"exam_question_type"));
		qtype.setSeq(Integer.parseInt(code.substring(code.length() - 8)));
		
		List<Object> params = new ArrayList<Object>();
		params.add(qtype.getId());
		params.add(qtype.getParent_id());
		params.add(qtype.getSub_sys_id());
		params.add(qtype.getName());
		params.add(qtype.getCode());
		params.add(qtype.getSeq());
		params.add(qtype.getState());
		params.add(qtype.getLayer());
		params.add(qtype.getRemark());
		
		update(key,sql,params);
		return qtype;
	}

	public void deleteExamQuestTypeById(String key,ExamQuestionType qtype) throws SQLException {
		String sql = "delete from exam_question_type where code like concat(?,'%')";
		update(key, sql, new Object[]{getExamQuestionTypeById(key,qtype.getId()).getCode()});
	}

	public ExamQuestionType getExamQuestionTypeById(String key,Long id) throws SQLException {
		String sql = "select t.*,(select count(1) from exam_question_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_question_type t where t.id = ?";
		ParameterizedRowMapper<ExamQuestionType> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionTypeListMapper);
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return (ExamQuestionType)queryForObject(key, sql, params, mapper);

	}

	@SuppressWarnings("unchecked")
	public ExamQuestionType updateExamQuestTypeById(String key,ExamQuestionType qtype) throws SQLException {
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
		update(key, sql.toString(), list.toArray());
		return qtype;
	}

	private static final String SQL_GET_MAX_CODE = "select max(code) as code from exam_question_type t where t.parent_id = ?";

	private String getMaxCode(String key,Long parentId) throws SQLException {
		// 父节点CODE
		String parentCode = "0";
		if (parentId.intValue() != 0) {
			parentCode = getExamQuestionTypeById(key,parentId).getCode();
		}
		// 平级最大CODE
		String maxCode = queryForString(key,SQL_GET_MAX_CODE, new Object[]{parentId});
		return CodeGenerator.genratorCode(parentCode, maxCode);
	}

	@SuppressWarnings("unchecked")
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(String key,ExamQuestionTypeQuery query) throws SQLException {
		ExamReturnQuestionType returnObj = new ExamReturnQuestionType();
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select t.*,(select count(1) from exam_question_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_question_type t where t.state = 1 and t.parent_id = ? ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append("and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		sql.append("order by t.seq");
		
		ParameterizedRowMapper<ExamQuestionType> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionTypeListMapper);
		List<ExamQuestionType> returnList = queryForList(key,
				PageUtil.getPageSql(sql.toString(), query.getPageNo(), query
						.getPageSize()),params.toArray(), mapper);
		returnObj.setReturnList(returnList);
		returnObj.setTotal_count(getExamQuestionTypeSizeByParentId(key,query));
		return returnObj;
	}
	
	@SuppressWarnings("unchecked")
	private int getExamQuestionTypeSizeByParentId(String key,ExamQuestionTypeQuery query) throws SQLException {
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select count(1) from exam_question_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append("and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		
		return queryForInt(key,sql.toString(), params.toArray());
	}

	@Override
	public void updateMoveQuestionType(String key,ExamQuestionType qtype) throws SQLException {
		String code = getMaxCode(key,qtype.getParent_id());
		int seq = Integer.parseInt(code.substring(code.length()-8));
		String sql = "update exam_question_type t set t.code= CONCAT(? ,SUBSTR(t.code,LENGTH(?)+1))"
				+ ",t.parent_id = CASE WHEN (t.code = ?) THEN ? ELSE t.parent_id END,t.seq = CASE WHEN(t.code = ?) THEN ? ELSE t.seq END"
				+ ",t.layer = LENGTH(CONCAT(?, SUBSTR(t.code, LENGTH(?) + 1)))/8 where code like CONCAT(?,'%')";
		getSimpleJdbcTemplate().update(sql,code,qtype.getCode(),qtype.getCode(),qtype.getParent_id(),qtype.getCode(),seq,code,qtype.getCode(),qtype.getCode());

		List<Object> params = new ArrayList<Object>();
		params.add(code);
		params.add(qtype.getCode());
		params.add(qtype.getCode());
		params.add(qtype.getParent_id());
		params.add(qtype.getCode());
		params.add(seq);
		params.add(code);
		params.add(qtype.getCode());
		params.add(qtype.getCode());
		
		update(key,sql,params);
	}

}
