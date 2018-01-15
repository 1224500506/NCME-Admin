package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.common.util.CodeGenerator;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamPaperTypeDAO;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
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
public class ExamPaperTypeJDBCDAO extends BaseDao implements ExamPaperTypeDAO {


	public ExamPaperType addExamPaperType(String key, ExamPaperType ptype) throws SQLException {
		String sql = "insert into exam_paper_type (id, sub_sys_id, parent_id, name, code, seq, layer, state, remark) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String code = getMaxCode(key,ptype.getParent_id());
		ptype.setCode(code);
		ptype.setId(getSeqNextvalForLong(key,"exam_paper_type"));
		ptype.setSeq(Integer.parseInt(code.substring(code.length() - 8)));
		
		List<Object> params = new ArrayList<Object>();
		params.add(ptype.getId());
		params.add(ptype.getSub_sys_id());
		params.add(ptype.getParent_id());
		params.add(ptype.getName());
		params.add(ptype.getCode());
		params.add(ptype.getSeq());
		params.add(ptype.getLayer());
		params.add(ptype.getState());
		params.add(ptype.getRemark());
		
		update(key,sql,params);
		
		return ptype;		
	}

	public void deleteExamPaperType(String key,ExamPaperType ptype) throws SQLException {
		String sql = "delete from exam_paper_type where code like concat(?,'%')";
		List<Object> params = new ArrayList<Object>();
		params.add(getExamPaperTypeById(key,ptype.getId()).getCode());
		update(key, sql, params);
	}

	public ExamPaperType getExamPaperTypeById(String key,Long id) throws SQLException {
		String sql = "select t.*,(select count(1) from exam_paper_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_paper_type t where t.id = ?";
		ParameterizedRowMapper<ExamPaperType> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperTypeListMapper);
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return (ExamPaperType)queryForObject(key, sql, params, mapper);
	}

	@SuppressWarnings("unchecked")
	public ExamReturnPaperType getExamPaperTypeListByParentId(String key,ExamPaperTypeQuery query) throws SQLException {
		ExamReturnPaperType returnObj = new ExamReturnPaperType();
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select t.*,(select count(1) from exam_paper_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_paper_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append("and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		sql.append("order by t.seq");
		
		ParameterizedRowMapper<ExamPaperType> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperTypeListMapper);
		List<ExamPaperType> returnList = queryForList(key,
				PageUtil.getPageSql(sql.toString(), query.getPageNo(), query
						.getPageSize()),params.toArray(), mapper);
		returnObj.setReturnList(returnList);
		returnObj.setTotal_count(getExamPaperTypeSizeByParentId(key,query));
		
		return returnObj;
	}
	
	@SuppressWarnings("unchecked")
	private int getExamPaperTypeSizeByParentId(String key,ExamPaperTypeQuery query) throws SQLException {
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("select count(1) from exam_paper_type t where t.parent_id = ? and t.state = 1 ");
		params.add(query.getParent_id());
		if(!StringUtils.checkNull(query.getName())){
			sql.append("and t.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		
		return queryForInt(key,sql.toString(), params.toArray());		
	}

	public List<ExamPaperType> getExamPaperTypeRootListBySysId(String key,Integer[] idArr) throws SQLException {
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
		
		ParameterizedRowMapper<ExamPaperType> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperTypeListMapper);
		
		return queryForList(key, sql.toString(), mapper);
	}

	@SuppressWarnings("unchecked")
	public ExamPaperType updateExamPaperType(String key,ExamPaperType ptype) throws SQLException {
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
		update(key, sql.toString(), list.toArray());
		return ptype;
	}
	
	private static final String SQL_GET_MAX_CODE = "select max(code) as code from exam_paper_type t where t.parent_id = ?";

	private String getMaxCode(String key,Long parentId) throws SQLException {
		// 父节点CODE
		String parentCode = "0";
		if (parentId.intValue() != 0) {
			parentCode = getExamPaperTypeById(key,parentId).getCode();
		}
		// 平级最大CODE
		String maxCode = queryForString(key,SQL_GET_MAX_CODE,new Object[]{parentId});
		return CodeGenerator.genratorCode(parentCode, maxCode);
	}

	@Override
	public void updateMovePaperType(String key,ExamPaperType ptype) throws SQLException {
		String code = getMaxCode(key,ptype.getParent_id());
		int seq = Integer.parseInt(code.substring(code.length()-8));
		String sql = "update exam_paper_type t set t.code= CONCAT(? ,SUBSTR(t.code,LENGTH(?)+1))"
				+ ",t.parent_id = CASE WHEN (t.code = ?) THEN ? ELSE t.parent_id END,t.seq = CASE WHEN(t.code = ?) THEN ? ELSE t.seq END"
				+ ",t.layer = LENGTH(CONCAT(?, SUBSTR(t.code, LENGTH(?) + 1)))/8 where code like CONCAT(?,'%')";
		
		List<Object> params = new ArrayList<Object>();
		params.add(code);
		params.add(ptype.getCode());
		params.add(ptype.getCode());
		params.add(ptype.getParent_id());
		params.add(ptype.getCode());
		params.add(seq);
		params.add(code);
		params.add(ptype.getCode());
		params.add(ptype.getCode());
		
		update(key,sql,params);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListById(String key,Integer[] idArr) throws SQLException {
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t.*,(select count(1) from exam_paper_type x where x.parent_id = t.id and x.state = 1) as childNum from exam_paper_type t where t.state = 1");

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
		
		ParameterizedRowMapper<ExamPaperType> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperTypeListMapper);
		return queryForList(key, sql.toString(), mapper);
	}

}
