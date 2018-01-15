package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.local.NcmeAdminOrganDAO;
import com.hys.exam.model.NcmeAdminOrgan;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeAdminOrganJDBCDAO extends AbstractJDBCDAO implements NcmeAdminOrganDAO {

	//final static private String getNcmeAdminOrganList_SQL = "select * from system_Admin_Organ order by organ_id";
	//暂只北京
	final static private String getNcmeAdminOrganList_SQL = "select * from system_Admin_Organ o where o.organ_id=2";

	@Override
	public List<NcmeAdminOrgan> getNcmeAdminOrganList() {
		return getJdbcTemplate().query(
				getNcmeAdminOrganList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(NcmeAdminOrgan.class));
	}
	
	@Override
	public List<NcmeAdminOrgan> getNcmeAdminOrganList(Page<NcmeAdminOrgan> page, NcmeAdminOrgan organ){
		String sql = "select * from system_admin_organ t where 1=1 ";
		List<Object> pl = new ArrayList<Object>();
		if(null != organ && null != organ.getName()){
			sql += " and t.name like ?";
			pl.add("%" + organ.getName().trim() + "%");
		}
		List<NcmeAdminOrgan> list = this.getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(NcmeAdminOrgan.class));
		Integer totalCount = getCount(sql, pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
		return list;
	}
	
	@Override
	public List<NcmeAdminOrgan> getNcmeAdminOrganList(Long pid){
		String sql = "select * from system_admin_organ t left join system_org t2 on t.org_id = t2.id" +
				" where t2.status = 1 and (t2.parent_org_id = ? or t2.id = ?) order by t.organ_id ";
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(NcmeAdminOrgan.class), pid, pid);
	}
	
	@Override
	public NcmeAdminOrgan getNcmeAdminOrganById(Long id){
		String sql = "select *  from system_admin_organ t where t.organ_id = ?";
		List<NcmeAdminOrgan> list = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(NcmeAdminOrgan.class), id);
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int saveNcmeAdminOrgan(NcmeAdminOrgan organ){
		int row = 0;
		if(null != organ.getOrganId()){
			String sql = "update system_admin_organ t set organ_id = :organId";
			sql+=" where organ_id = :organId";
			row = this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(organ.getOrganId()));
		}else{
			organ.setOrganId(this.getSequence("system_admin_organ_seq")+"");
			
			String sql = "insert into system_admin_organ(organ_id)" +
					" values (:organId)";
			row = this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(organ));
		}
		return row;
	}
	
	@Override
	public int deleteNcmeAdminOrgan(Long id){
		String sql = "delete from system_admin_organ t where t.organ_id = ?";
		return this.getJdbcTemplate().update(sql, id);
	}
}
