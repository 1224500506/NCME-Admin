package com.hys.auth.dao.jdbc;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.hys.auth.constants.Constants;
import com.hys.auth.dao.ResourceDAO;
import com.hys.auth.dto.ResourceDTO;
import com.hys.auth.model.HysResources;
import com.hys.auth.model.RoleResource;

public class ResourceJDBCDAO extends AbstractJDBCDAO implements ResourceDAO {

	public ResourceJDBCDAO() {
		super(HysResources.class, Constants.TABLE_RESOURCE);
	}

	public int deleteRoleAndResource(Integer resourceId) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from role_resource where resource_id = ?");
		return getSimpleJdbcTemplate().update(sql.toString(), new Object[] { resourceId });
	}

	public int relateRoleAndResource(RoleResource rr) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ROLE_RESOURCE(ROLE_ID,RESOURCE_ID) VALUES(:roleId, :resourceId)");
		return getSimpleJdbcTemplate().update(sql.toString(), new BeanPropertySqlParameterSource(rr));
	}

	public HysResources getResource(String value) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM HYS_RESOURCES WHERE VALUE = ?");
		HysResources resource = null;
		try {
			resource = getSimpleJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(HysResources.class), new Object[] { value });
		} catch (Exception e) {
			logger.info("不存在该资源", e);
			return resource;
		}
		return resource;
	}

	public List<ResourceDTO> findResources() {
		StringBuilder sql = new StringBuilder();
//		sql.append("select role,url from (select r.name as role,rs.value as url,to_number(rs.type) ordertype from HYS_ROLES r,role_resource rr,HYS_RESOURCES rs where r.id = rr.role_id and rr.resource_id = rs.id) order by ordertype");
		
		//YHQ,2017-11-07,修改jira上:STUDYADMIN-646
		sql.append("select r.role_name as role,rs.value as url from SYSTEM_ROLE r,system_role_resource rr,system_resource rs where r.role_id = rr.role_id and rr.resource_id = rs.id order by rs.type");		
		//sql.append("select r.role_name as role,rs.URL as url from SYSTEM_ROLE r,system_role_menu rr,system_menu rs where r.role_id = rr.role_id and rr.MENU_ID = rs.id and rs.state = 1 and r.STATUS = 1 and rs.SYSTEM_TYPE = '后台管理系统' order by rs.MENU_TYPE ") ;
		
		return getSimpleJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ResourceDTO.class), new Object[] {});
	}

	public int update(HysResources resource) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE HYS_RESOURCES SET ");
		sql.append("TYPE = :type,");
		sql.append("NAME = :name,");
		sql.append("VALUE = :value ");
		sql.append("WHERE ID = :id");
		return getSimpleJdbcTemplate().update(sql.toString(), new BeanPropertySqlParameterSource(resource));
	}

	@Override
	public Long save(HysResources resource) {
		resource.setId(getSequence());
		SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource())
				.withTableName(Constants.TABLE_RESOURCE);
		insert.execute(new BeanPropertySqlParameterSource(resource));
		return resource.getId();
	}

	@Override
	public boolean deleteRow(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

}
