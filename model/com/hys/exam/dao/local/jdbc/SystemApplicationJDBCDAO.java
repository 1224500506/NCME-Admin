package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.SystemApplicationDAO;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 JdbcDao
 * 
 * 说明:
 */
public class SystemApplicationJDBCDAO extends AbstractJDBCDAO implements SystemApplicationDAO {
	public SystemApplicationJDBCDAO() {
		super(SystemApplication.class, Constants.TABLE_SYSTEM_APPLICATION);
	}
	
	//取得所有站点信息
	final static private String getSystemApplicationList_SQL = "select t.* from system_Application t" ;
	final static private String addSystemApplication_SQL = " insert into system_Application (id, domain_name, remark, client_id, application_id, status, role_id) values (:id, :domainName, :remark, :clientId, :applicationId, :status, :roleId) ";
	
	@Override //取得所有站点信息
	public List<SystemApplication> getListByItem(SystemApplication item){
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemApplicationList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getApplicationName() != null && !"".equals(item.getApplicationName())) {
			sql.append(" and t.application_Name like ? ");
			list.add("%" + item.getApplicationName() + "%");
		}

		// 查询结果集
		List<SystemApplication> resList = getList(sql.toString(), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemApplication.class));
		return resList;
	}
	
	@Override
	public void querySystemApplicationList(Page<SystemApplication> page, SystemApplication item) {

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemApplicationList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getApplicationName() != null && !"".equals(item.getApplicationName())) {
			sql.append(" and t.application_Name like ? ");
			list.add("%" + item.getApplicationName() + "%");
		}

		// 查询结果集
		List<SystemApplication> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemApplication.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}

	public Integer save(SystemApplication item) {
		Long id = this.getSequence("SYSTEM_APPLICATION_SEQ");
		item.setId(id);
		this.getNamedParameterJdbcTemplate().update(addSystemApplication_SQL,
				new BeanPropertySqlParameterSource(item));
		return  id.intValue();
	}
	@Override
	public int update(SystemApplication item) {
		 
		return 0;
	}
}