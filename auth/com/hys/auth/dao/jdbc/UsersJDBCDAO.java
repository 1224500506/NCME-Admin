package com.hys.auth.dao.jdbc;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.hys.auth.constants.Constants;
import com.hys.auth.dao.UsersDAO;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.PageList;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.HysUserRoleProp;

public class UsersJDBCDAO extends AbstractJDBCDAO implements UsersDAO {

	public UsersJDBCDAO() {
		super(HysUsers.class, Constants.TABLE_USERS);
	}

	@SuppressWarnings("deprecation")
	public HysUsers valid(String userName, String passWord) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u.ID,u.LOGIN_NAME,u.PASSWORD,u.REALNAME FROM HYS_USERS u ");
		sql.append("WHERE u.LOGIN_NAME = ? ");
		
		Object [] o = new Object[] { userName };
		if(null != passWord) {
			sql.append(" and u.PASSWORD = ? ");
			o = new Object[] { userName, passWord };
		}
		log.info(sql.toString());
		HysUsers users = null;
		try {
			users = getJdbcTemplate()
					.queryForObject(
							sql.toString(),
							ParameterizedBeanPropertyRowMapper
									.newInstance(HysUsers.class),o
							);
		} catch (EmptyResultDataAccessException e) {
			log.info("不存在该用户！", e);
		}
		return users;
	}

	//删除系统用户
	public int delete(Integer id) {
		StringBuilder sql1 = new StringBuilder();
		sql1.append("UPDATE HYS_USERS SET ");
		sql1.append(" ENABLE=ABS(ENABLE-2)+1");
		sql1.append(" WHERE ID = ?");
		return getSimpleJdbcTemplate().update(sql1.toString(), id);
		
	}

	//更新系统用户
	public int update(HysUsers users) {
		StringBuilder sql1 = new StringBuilder();
		sql1.append("UPDATE HYS_USERS SET ");
		sql1.append("LOGIN_NAME = :loginName,");
		sql1.append(" REALNAME = :realName,");
		sql1.append(" ENABLE = :enable");
		sql1.append(" WHERE ID = :id");
		log.info(sql1.toString());
		
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append("UPDATE HYS_USER_DATA SET ");
		sql2.append("DEPT_NAME = :deptName, ");
		sql2.append("SEX = :sex, ");
		sql2.append("WORK_UNIT = :workUnit, ");
		sql2.append("PHONE_NUMBER = :phoneNumber ");
		sql2.append("WHERE USERID = :id");
		
		getSimpleJdbcTemplate().update(sql1.toString(),new BeanPropertySqlParameterSource(users));
		return getSimpleJdbcTemplate().update(sql2.toString(),new BeanPropertySqlParameterSource(users));
		
	}

	//更新系统用户密码
	public int updatePassword(HysUsers users) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE HYS_USERS SET ");
		sql.append("PASSWORD = :password ");
		sql.append("WHERE ID = :id");
		log.info(sql.toString());
		return getSimpleJdbcTemplate().update(sql.toString(),
				new BeanPropertySqlParameterSource(users));
	}

	public List<HysUsers> getUsersWithPagination(String loginName,
			PageList pageList) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u.ID,u.LOGIN_NAME,u.ROLE_ID,u.REALNAME,u.ENABLE,u.password,r.NAME_DESC AS ROLENAME,d.* ");
		sql.append("FROM HYS_USERS u LEFT JOIN HYS_ROLES r on u.ROLE_ID=r.ID LEFT JOIN HYS_USER_DATA d on u.ID=d.USERID WHERE U.ID<>1 ");
		sql.append("ORDER BY ID DESC");
		return this.getListWithPagination(sql.toString(), pageList);
	}
	
	//查询系统用户
	public List<HysUsers> getSearchList(List<String> list, PageList pageList) {
		StringBuilder sql = new StringBuilder();
		
		String realName = list.get(0);
		Integer sex;
		if (list.get(1) == null || list.get(1).equals("")) {
			sex = null;
		} else {
			sex = Integer.valueOf(list.get(1));
		}
		String workUnit = list.get(2);
		String enable = list.get(3);
		String loginName = list.get(4);

		String deptName = list.get(5);

		sql.append("SELECT u.ID,u.LOGIN_NAME,u.ROLE_ID,u.REALNAME,u.ENABLE,u.password,d.* ");
		sql.append("FROM HYS_USERS u LEFT JOIN HYS_USER_DATA d on u.ID=d.USERID WHERE U.ID<>1");
		if (loginName != null && !loginName.equals("")) {
			sql.append(" AND u.LOGIN_NAME like '%").append(loginName).append("%'");
		}
		if (realName != null && !realName.equals("")) {
			sql.append(" AND u.REALNAME like '%").append(realName).append("%'");
		}
/*		if (roleName != null && !roleName.equals("")) {
			sql.append(" AND r.NAME_DESC = '").append(roleName).append("'");
		}
*/		if (sex != null) {
			sql.append(" AND d.SEX = '").append(sex).append("'");
		}
		if (workUnit != null && !workUnit.equals("")) {
			sql.append(" AND d.WORK_UNIT like '%").append(workUnit).append("%'");
		}
		if (enable != null && !enable.equals("")) {
			sql.append(" AND u.ENABLE = '").append(enable).append("'");
		}
		if (deptName != null && !deptName.equals("")) {
			sql.append(" AND d.DEPT_NAME like '%").append(deptName).append("%'");
		}
		sql.append(" ORDER BY ID DESC");
		return this.getListWithPagination(sql.toString(), pageList);
	}

	//保存系统用户
	@Override
	public Long save(HysUsers users) {
		users.setId(getSequence());
		 
		String sql = "INSERT INTO HYS_USERS (ID, LOGIN_NAME, REALNAME, ENABLE) values (:id, :loginName, :realName, 1)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(users));

		sql = "INSERT INTO HYS_USER_DATA (USERID,PHONE_NUMBER,WORK_UNIT,SEX,DEPT_NAME) values (:id, :phoneNumber, :workUnit, :sex, :deptName)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(users));

		return users.getId();
	}

	@Override
	public void saveUserRoles(HysUserRoleProp role) {
		// TODO Auto-generated method stub
		String insertSql = "insert into HYS_USER_ROLE_PROP (USERID,ROLEID) values (:userid, :roleid)";
		getSimpleJdbcTemplate().update(insertSql, new BeanPropertySqlParameterSource(role));
	}
	@Override
	public void deleteUserRoles(Long userid)
	{
		String deleteSql = "delete from HYS_USER_ROLE_PROP where USERID = ?";
		getSimpleJdbcTemplate().update(deleteSql.toString(), userid);
	}
	@Override
	public List<HysUserRoleProp> getUserRoleList(Long userid)
	{
		String sql = "select * from HYS_USER_ROLE_PROP where USERID = ?";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(HysUserRoleProp.class),userid);
	}

	@Override
	public boolean deleteRow(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}
}
