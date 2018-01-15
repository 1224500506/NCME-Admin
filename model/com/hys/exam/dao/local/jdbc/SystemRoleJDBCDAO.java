package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.SystemRoleDAO;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.system.SystemAdminOrgan;
import com.hys.exam.model.system.SystemRoleMenuProp;
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
public class SystemRoleJDBCDAO extends AbstractJDBCDAO implements SystemRoleDAO {
	public SystemRoleJDBCDAO() {
		super(SystemRole.class, Constants.TABLE_SYSTEM_ROLE);
	}
	
	//取得所有站点信息
	final static private String getSystemRoleList_SQL = "select t.* from system_Role t where t.ROLE_ID>0" ;
	final static private String addSystemRole_SQL = " insert into system_role (role_name,role_name_desc,status) values (:roleName,:roleNameDesc,:status) ";
	
	@Override //取得所有站点信息
	public List<SystemRole> getListByItem(SystemRole item){
		List values = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemRoleList_SQL);

		if (item.getRoleName() != null && !"".equals(item.getRoleName())) {
			sql.append(" and t.ROLE_NAME like ?");
			values.add("%"+item.getRoleName()+"%");
		}
		if (item.getStatus() != null) {
			sql.append(" and t.STATUS= ?");
			values.add(item.getStatus());
		}
		// 查询结果集
		List<SystemRole> resList = getList(sql.toString(), 
				ParameterizedBeanPropertyRowMapper.newInstance(SystemRole.class), values.toArray());
		return resList;
	}
	
	public List<SystemRole> getListByItem(SystemRole item,Page<SystemRole> page){
		List<Object> values = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemRoleList_SQL);

		if (item.getRoleName() != null && !"".equals(item.getRoleName())) {
			sql.append(" and t.ROLE_NAME like ?");
			values.add("%"+item.getRoleName()+"%");
		}
		if (item.getStatus() != null) {
			sql.append(" and t.STATUS= ?");
			values.add(item.getStatus());
		}
		// 查询结果集
		
		List<SystemRole> list = getList(sql.toString(), values, ParameterizedBeanPropertyRowMapper.newInstance(SystemRole.class));
		Integer totalCount = getCount(sql.toString(), values.toArray());
		page.setList(list);
		page.setCount(totalCount);
		return list;
	}
	
	@Override
	public void querySystemRoleList(Page<SystemRole> page, SystemRole item) {

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemRoleList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getRoleName() != null && !"".equals(item.getRoleName())) {
			sql.append(" and t.application_Name like ? ");
			list.add("%" + item.getRoleName() + "%");
		}

		// 查询结果集
		List<SystemRole> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemRole.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}

	public Integer save(SystemRole item) {
		Map<String, Object> resultMap = getJdbcTemplate().queryForMap("show table status like 'system_role'");
		Long id =  Long.valueOf(resultMap.get("Auto_increment").toString());
		item.setRoleId(id);
		this.getNamedParameterJdbcTemplate().update(addSystemRole_SQL,
				new BeanPropertySqlParameterSource(item));
		return  id.intValue();
	}
	
	/**
	 * 添加角色的时候直接在system_role_resource中添加一条数据，保证所有角色和用户关联后都能登录
	 * @param id
	 * @return
	 */
	public Integer saveRoleResourceRelation(int id){
		String sql = "insert into system_role_resource (role_id,resource_id) values (?,1)";
		return getJdbcTemplate().update(sql.toString(),id);
	}
	
	@Override
	public int update(SystemRole item) {
		 
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_role set ");
		if(item.getRoleName() != null && !item.getRoleName().equals(""))
		{
			sql.append("ROLE_NAME = ?,");
			values.add(item.getRoleName());
		}
		if(item.getStatus() != null)
		{
			sql.append("STATUS = ? ");
			values.add(item.getStatus());
		}
		sql.append("where ROLE_ID = ?");
		values.add(item.getRoleId());
		
		return getJdbcTemplate().update(sql.toString(),values.toArray());
	}

	@Override
	public int delete(long id, String idColName) {
		return super.delete(id, idColName);
	}

	@Override
	public SystemRole getItemById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from system_role where ROLE_ID = ").append(id);
		return getJdbcTemplate().queryForObject(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemRole.class));
	}
	public List<SystemRoleMenuProp> getRoleMenuList(Integer roleId)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from system_role_menu where ROLE_ID = ").append(roleId);
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemRoleMenuProp.class));
	}
	public int setRoleMenuProp(Integer roleId, String menuList)
	{
		StringBuffer deleteSql = new StringBuffer();
		deleteSql.append("delete from system_role_menu where ROLE_ID = ").append(roleId);
		getJdbcTemplate().update(deleteSql.toString());
		
		if(menuList.charAt(menuList.length() - 1) == ',')
		{
			menuList = menuList.substring(0,menuList.length()-1);
		}
		String[] menuArray = menuList.split(",");
		int result = 0;
		for(String menuId : menuArray)
		{
			String sql = "insert into system_role_menu (role_id,menu_id) values (?,?)";
			List list = new ArrayList();
			list.add(roleId);
			list.add(menuId.trim());
			result = getJdbcTemplate().update(sql.toString(),list.toArray());
		}
		return result;		
	}
}