package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.common.util.CodeGenerator;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamExaminTypeDAO;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 9, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminTypeJDBCDAO extends BaseDao implements ExamExaminTypeDAO {

	@Override
	public ExamExaminType addExamExaminType(String key, ExamExaminType etype)
			throws SQLException {
		String sql = "insert into exam_exam_type (id, sub_sys_id, parent_id, name, code, seq, layer, state, remark) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String code = getMaxCode(key,etype.getParent_id());
		etype.setCode(code);
		etype.setId(getSeqNextvalForLong(key,"exam_exam_type"));
		etype.setSeq(Integer.parseInt(code.substring(code.length() - 8)));
		
		List<Object> params = new ArrayList<Object>();
		params.add(etype.getId());
		params.add(etype.getSub_sys_id());
		params.add(etype.getParent_id());
		params.add(etype.getName());
		params.add(etype.getCode());
		params.add(etype.getSeq());
		params.add(etype.getLayer());
		params.add(etype.getState());
		params.add(etype.getRemark());
		
		update(key,sql,params);
		
		return etype;
	}

	@Override
	public void deleteExamExaminTypeById(String key, ExamExaminType etype)
			throws SQLException {
		String sql = "delete from exam_exam_type where code like concat(?,'%')";
		List<Object> params = new ArrayList<Object>();
		params.add(getExamExaminTypeById(key,etype.getId()).getCode());
		update(key, sql, params);
	}

	@Override
	public ExamExaminType getExamExaminTypeById(String key, Long id)
			throws SQLException {
		String sql = "select t.*,(select count(1) from exam_exam_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_exam_type t where t.id = ?";
		ParameterizedRowMapper<ExamExaminType> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminTypeListMapper);
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return (ExamExaminType)queryForObject(key, sql, params, mapper);
	}

	@Override
	public ExamReturnExaminType getExamExaminTypeListByParentId(String key,
			ExamExaminTypeQuery query) throws SQLException {
		ExamReturnExaminType returnObj = new ExamReturnExaminType();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.*,(select count(1) from exam_exam_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_exam_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append("and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		sql.append("order by t.seq");
		
		ParameterizedRowMapper<ExamExaminType> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminTypeListMapper);
		
		List<ExamExaminType> returnList = queryForList(key,
				PageUtil.getPageSql(sql.toString(), query.getPageNo(), query
						.getPageSize()),params,mapper);
		
		returnObj.setReturnList(returnList);
		returnObj.setTotal_count(getExamExaminTypeSizeByParentId(key,query));
		return returnObj;
	}

	@SuppressWarnings("unchecked")
	private int getExamExaminTypeSizeByParentId(String key,ExamExaminTypeQuery query)  throws SQLException {
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select count(1) from exam_exam_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append("and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		return queryForInt(key,sql.toString(), params);
	}
	
	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(String key,
			Integer[] idArr) throws SQLException {
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
		ParameterizedRowMapper<ExamExaminType> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminTypeListMapper);
		return queryForList(key,sql.toString(),mapper);

	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(String key,
			Integer[] idArr) throws SQLException {
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

		ParameterizedRowMapper<ExamExaminType> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminTypeListMapper);
		
		return queryForList(sql.toString(), mapper);
	}

	@Override
	public ExamExaminType updateExamExaminType(String key, ExamExaminType etype)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
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
		update(key, sql.toString(), list);
		return etype;
	}
	
	private static final String SQL_GET_MAX_CODE = "select max(code) as code from exam_exam_type t where t.parent_id = ?";

	private String getMaxCode(String key,Long parentId) throws SQLException {
		// 父节点CODE
		String parentCode = "0";
		if (parentId.intValue() != 0) {
			parentCode = getExamExaminTypeById(key,parentId).getCode();
		}
		// 平级最大CODE
		String maxCode = queryForString(key,SQL_GET_MAX_CODE,new Object[]{parentId});
		return CodeGenerator.genratorCode(parentCode, maxCode);
	}

	@Override
	public void updateMoveExaminType(String key, ExamExaminType etype)
			throws SQLException {
		String code = getMaxCode(key,etype.getParent_id());
		int seq = Integer.parseInt(code.substring(code.length()-8));
		String sql = "update exam_exam_type t set t.code= CONCAT(? ,SUBSTR(t.code,LENGTH(?)+1))"
				+ ",t.parent_id = CASE WHEN (t.code = ?) THEN ? ELSE t.parent_id END,t.seq = CASE WHEN(t.code = ?) THEN ? ELSE t.seq END"
				+ ",t.layer = LENGTH(CONCAT(?, SUBSTR(t.code, LENGTH(?) + 1)))/8 where code like CONCAT(?,'%')";
		
		List<Object> params = new ArrayList<Object>();
		params.add(code);
		params.add(etype.getCode());
		params.add(etype.getCode());
		params.add(etype.getParent_id());
		params.add(etype.getCode());
		params.add(seq);
		params.add(code);
		params.add(etype.getCode());
		params.add(etype.getCode());
		
		update(key,sql,params);
	}

}
