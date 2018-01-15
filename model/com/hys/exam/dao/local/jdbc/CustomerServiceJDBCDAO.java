package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.CustomerServiceDAO;
import com.hys.exam.dao.local.SystemUserDAO;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserAddress;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;
import com.hys.exam.utils.MD5Util;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 jdbcDao
 * 
 * 说明:
 */
public class CustomerServiceJDBCDAO extends AbstractJDBCDAO implements CustomerServiceDAO {
	
	////final static private String getSystemUserList_SQL = "select u.*,u.id user_id,a.account_id,a.account_password,a.account_remark from system_user u,system_account a where a.account_name = u.account_name and u.status = 1  " ;
	private String getSystemUserList_SQL = " select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark, a.account_status from system_user u left join system_user_config c on u.id=c.user_id left join system_account a on u.id = a.user_Id where u.id>0  " ;
	private String getSystemUserList_SQL_size = " select count(1) from system_user u left join system_user_config c on u.id=c.user_id left join system_account a on u.id = a.user_Id where u.id>0  " ;
	final static private String addSystemUser_SQL = " insert into system_user (id, domain_name, remark, client_id, application_id, status, role_id) values (:id, :domainName, :remark, :clientId, :applicationId, :status, :roleId) ";
	final static private String insertSystemUser_SQL = " insert into system_user (id, real_name, work_unit, user_type, status, sex, mobil_phone, dept_name) values (:userId, :realName, :workUnit, :userType, :status, :sex, :mobilPhone, :deptName) ";

	@Override  
	public List<SystemUser> getListByItem(SystemUser item){
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemUserList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getUserId() != null && item.getUserId()>0) {
			sql.append(" and u.Id = ? ");
			list.add(item.getUserId() );
		}
		if (item.getRealName() != null && !item.getRealName().trim().equals("")) {
			sql.append(" and u.Real_Name like ? ");
			list.add("%"+item.getRealName().trim()+"%" );
		} 
		if (item.getCertificateNo() != null && !item.getCertificateNo().trim().equals("")) {
			sql.append(" and u.Certificate_No like ? ");
			list.add("%"+item.getCertificateNo().trim()+"%" );
		} 
		if(item.getUserType() != null)
		{
			if(item.getUserType() != 1)
			{
				if (item.getWorkUnit() != null && !item.getWorkUnit().equals("")) {
					sql.append(" and u.Work_Unit like ? ");
					list.add("%"+item.getWorkUnit().trim()+"%" );
				}	
			}else{
				if (!StringUtil.checkNull(item.getProfTitle())) {
					sql.append(" and u.work_unit_id in ("+item.getProfTitle()+")");
				}	
			}
		}
		
		if (item.getAccountName() != null && !item.getAccountName().trim().equals("")) {
			sql.append(" and a.ACCOUNT_NAME = ? ");
			list.add(item.getAccountName().trim());
		}
		if (item.getAccountPassword() != null && !item.getAccountPassword().trim().equals("")) {
			sql.append(" and a.ACCOUNT_PASSWORD = ?");
			list.add(item.getAccountPassword().trim());
		}
		sql.append(" order by u.reg_date desc ");

		// 查询结果集
		List<SystemUser> resList = getList(sql.toString(), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		for(SystemUser user : resList)
		{
			SystemUserConfig userConfig = new SystemUserConfig();
			userConfig = this.getSystemUserConfigByUserId(user.getUserId());
			user.setUserConfig(userConfig);
			
			user.setDeptName("");
			String getPropName = "SELECT p.*  from exam_prop_val p left join sub_sys_prop_val s on p.ID=s.PROP_VAL_ID LEFT JOIN system_user_prop_val v on v.PROP_ID=s.id where v.USER_ID="+user.getUserId();
			try{
				String propName = "";
				String propIds = "";
				List<ExamProp> prop = this.getJdbcTemplate().query(getPropName, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
				if (prop!=null && prop.size()>0){
					for(int i = 0 ; i < prop.size() ; i++)
					{
						if(i != 0)
						{
							propName += "," + prop.get(i).getName();
							propIds += "," + prop.get(i).getId();
						}
						else
						{
							propName += prop.get(i).getName();
							propIds += prop.get(i).getId();
						}
					}
				}
				user.setDeptName(propName);
				user.setPropIds(propIds);
				
				if(user.getWork_unit_id() != null && !user.getWork_unit_id().equals(0L))
				{
					String hospSql = "select * from exam_hospital where id = " + user.getWork_unit_id(); 
					List<ExamHospital> hosp = getJdbcTemplate().query(hospSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class));
					if(hosp != null && hosp.size() > 0)
					{
						user.getUserConfig().setAddress(hosp.get(0).getHospital_address());
						user.setWorkUnit(hosp.get(0).getName());
					}
					
				}
			}catch(Exception e){}
			
			user.setProfTitle("");
			getPropName = "SELECT p.*  from exam_prop_val p left join sub_sys_prop_val s on p.ID=s.PROP_VAL_ID LEFT JOIN system_user_job_val v on v.JOB_ID=s.id where v.USER_ID="+user.getUserId();
			try{
				List<ExamProp> prop = this.getJdbcTemplate().query(getPropName, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
				if (prop!=null && prop.size()>0){
					user.setProfTitle(prop.get(0).getName());
				}
			}catch(Exception e){}
		}
		return resList;
	}

	@Override
	public void querySystemUserList(Page<SystemUser> page, SystemUser item) {

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemUserList_SQL);

		Integer status = item.getStatus();
		String workUnit = item.getWorkUnit();
		String deptName = item.getDeptName();
		Integer sex = item.getSex();
		Integer userType = item.getUserType();


		List<Object> list = new ArrayList<Object>();

		if (item.getUserId() != null && item.getUserId()>0) {
			sql.append(" and u.Id = ? ");
			list.add(item.getUserId() );
		} 
		if (StringUtils.isNotBlank(item.getRealName())) {
			sql.append(" and u.Real_Name like ? ");
			list.add("%"+item.getRealName().trim()+"%" );
		} 		
		if (StringUtils.isNotBlank(item.getAccountName())) {
			sql.append(" and a.Account_Name like ? ");
			list.add("%"+item.getAccountName().trim()+"%" );
		}
		if (StringUtils.isNotBlank(item.getCertificateNo())) {
			sql.append(" and u.Certificate_No like ? ");
			list.add("%"+item.getCertificateNo().trim()+"%" );
		}
		if (status != null && status > 0) {
			sql.append(" and a.account_status = ? ");
			list.add(status);
		}
		/*if (item.getUserType() != null)
		if(item.getUserType() != 1)
		{*/
			if (workUnit != null && !workUnit.equals("")) {
				sql.append(" and u.Work_Unit like ? ");
				list.add("%"+workUnit.trim()+"%" );
			}	
		/*}
		else
		{
			if (workUnit != null && !workUnit.equals("")) {
				sql.append(" and u.work_unit_id in (").append(workUnit.trim()).append(")");
			}	
		}*/
		
		if (deptName != null && !deptName.equals("")) {
			/*sql.append(" and u.DEPT_NAME like ? ");
			list.add("%"+deptName.trim()+"%" );*/
			String getPropVal = " select USER_ID from system_user_prop_val where PROP_ID in (" + deptName + ")";
			try{
				List<Long> userIds = getJdbcTemplate().queryForList(getPropVal, Long.class);
				String ids = userIds.toString();
				ids = ids.substring(1, ids.length()-1);
				if (ids.equals("")) ids = "0";
				sql.append(" and u.id in (").append(ids).append(")");
			}catch(Exception e){}
		}
		if (sex != null && sex > 0) {
			sql.append(" and u.sex = ? ");
			list.add(sex);
		}
		if (userType != null && userType > 0L) {
			sql.append(" and u.user_Type = ? ");
			list.add(userType);
		}
		
		//add by han.
		
		if (item.getProfTitle()!=null && !item.getProfTitle().equals("")){
			/*sql.append(" and u.prof_title = ? ");
			list.add(item.getProfTitle());*/
			String getPropVal = " select USER_ID from system_user_job_val where JOB_ID in (" + item.getProfTitle() + ")";
			try{
				List<Long> userIds = getJdbcTemplate().queryForList(getPropVal, Long.class);
				String ids = userIds.toString();
				ids = ids.substring(1, ids.length()-1);
				if (ids.equals("")) ids = "0";
				sql.append(" and u.id in (").append(ids).append(")");
			}catch(Exception e){}
		}
		if (item.getUserConfig() != null){
			SystemUserConfig userConfig = item.getUserConfig();
			if (userConfig.getUserProvinceId()!=null && userConfig.getUserProvinceId()!=0){
				sql.append(" and c.user_province_id = ? ");
				list.add(userConfig.getUserProvinceId());
			}
			if (userConfig.getUserCityId()!=null && userConfig.getUserCityId()!=0){
				sql.append(" and c.user_city_id = ? ");
				list.add(userConfig.getUserCityId());
			}
			if (userConfig.getUserCountiesId()!=null && userConfig.getUserCountiesId()!=0){
				sql.append(" and c.user_counties_id = ? ");
				list.add(userConfig.getUserCountiesId());
			}
		}
		//end.		
		sql.append(" order by u.reg_date desc ");

		// 查询结果集
		List<SystemUser> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),  list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));				
				
		// add by han.
		for(SystemUser user: resList){
			SystemUserConfig userConfig = new SystemUserConfig();
			userConfig = this.getSystemUserConfigByUserId(user.getUserId());
			user.setUserConfig(userConfig);
			
			user.setDeptName("");
			String getPropName = "SELECT p.*  from exam_prop_val p left join sub_sys_prop_val s on p.ID=s.PROP_VAL_ID LEFT JOIN system_user_prop_val v on v.PROP_ID=s.id where v.USER_ID="+user.getUserId();
			try{
				List<ExamProp> prop = this.getJdbcTemplate().query(getPropName, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
				if (prop!=null && prop.size()>0){
					user.setDeptName(prop.get(0).getName());
				}
			}catch(Exception e){}
			
			user.setProfTitle("");
			getPropName = "SELECT p.*  from exam_prop_val p left join sub_sys_prop_val s on p.ID=s.PROP_VAL_ID LEFT JOIN system_user_job_val v on v.JOB_ID=s.id where v.USER_ID="+user.getUserId();
			try{
				List<ExamProp> prop = this.getJdbcTemplate().query(getPropName, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
				if (prop!=null && prop.size()>0){
					user.setProfTitle(prop.get(0).getName());
				}
			}catch(Exception e){}
		}
		//取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}
	
	@Override
	public SystemUserConfig getSystemUserConfigByUserId(Long userId){
		String sql = "select t.id, (select o1.name from exam_prop_val o1 left join sub_sys_prop_val s1 on o1.id=s1.prop_val_id where s1.id = t.user_province_id) userProvinceName," +
				" (select o2.name from exam_prop_val o2 left join sub_sys_prop_val s2 on o2.id=s2.prop_val_id where s2.id = t.user_city_id) userCityName," +
				" (select o3.name from exam_prop_val o3 left join sub_sys_prop_val s3 on o3.id=s3.prop_val_id where s3.id = t.user_counties_id) userCountiesName," +
				" (select o4.name from exam_prop_val o4 left join sub_sys_prop_val s4 on o4.id=s4.prop_val_id where s4.id = t.user_street_id) userStreetName," +
				" t.user_industry_id " +
				" from system_user_config t  where t.user_id = ?";
		List<Object> p = new ArrayList<Object>();
		p.add(userId);
		List<SystemUserConfig> list = getList(sql.toString(), p,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUserConfig.class));
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public SystemIndustry getSystemIndustryByPositionId(Long id){
		String sql = "select (select t.industry_name from system_industry t where t.id = si.parent_id)  || '一' || si.industry_name as industryName" +
				" from system_industry si where si.id = ?";
		List<SystemIndustry> industry = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemIndustry.class), new Object[] { id });
		if(industry != null && industry.size() != 0 )
		{
			return industry.get(0);
		}
		else
		{
			return null;
		}
	}

	public Integer save(SystemUser item) {
		Long id = this.getSequence("SYSTEM_USER_SEQ");
		item.setUserId(id);
		getNamedParameterJdbcTemplate().update(addSystemUser_SQL, new BeanPropertySqlParameterSource(item));
		return  id.intValue();
	}
	
	public Integer insert(SystemUser item) {
		Long id = this.getSequence("SYSTEM_USER");
		item.setUserId(id);
	//	NamedParameterJdbcTemplate templ = getNamedParameterJdbcTemplate();
	//	templ.update(insertSystemUser_SQL, new BeanPropertySqlParameterSource(item));
		getNamedParameterJdbcTemplate().update(insertSystemUser_SQL, new BeanPropertySqlParameterSource(item));
		
		// add by han
		String insertSystemAccount_SQL = "insert into system_account (account_id, account_name, account_password, account_remark, user_id, account_status) value (:userId, :accountName, :accountPassword, :accountRemark, :userId, :account_status)";
		item.setAccount_status(1);
		item.setAccountRemark("");
		String newPass = MD5Util.string2MD5("123456");
		item.setAccountPassword(newPass);
		if (item.getAccountName() != null && !item.getAccountName().equals(""))
			getNamedParameterJdbcTemplate().update(insertSystemAccount_SQL, new BeanPropertySqlParameterSource(item));
		// end
		//Add by Tiger for insert propVal
		if(!StringUtil.checkNull(item.getPropIds())) {
			String[] propIdArr = item.getPropIds().split(",");
			for(String propId : propIdArr)
			{
				
				String insertSystemPropVal_SQL = "insert into system_user_prop_val set user_id = " + item.getUserId().toString() + ",prop_id=" + propId;
				getJdbcTemplate().update(insertSystemPropVal_SQL);
			}
		}
		
		return  id.intValue();
	}
	
	public Integer setPass(String account,String password) {
		String sql = "update system_account set account_password=? where account_name = ?";
		return this.getJdbcTemplate().update(sql,password,account);
	}
	
	public SystemUser getItemById(Long userId) {
		SystemUser item = new SystemUser();
		item.setUserId(userId);
		List<SystemUser> list = this.getListByItem(item);
		
		return list!=null&&list.size()==1 ? list.get(0) : item;
	}
	
	public SystemUser getItemByAccountName(String accountName,String password) {
		SystemUser item = new SystemUser();
		item.setAccountName(accountName);
		item.setAccountPassword(password);
		List<SystemUser> list = this.getListByItem(item);
		
		return list!=null&&list.size()==1 ? list.get(0) : null;
	}
	@Override
	public int update(SystemUser item) {
		//Add by Pak for insert propVal
		if(!StringUtil.checkNull(item.getPropIds())) {
			if(item.getUserId()!= null && !item.getUserId().equals(0L))
			{
				String deletePropSql = "delete from system_user_prop_val where user_id = " + item.getUserId().toString() ;
				getJdbcTemplate().update(deletePropSql);
			}
			String[] propIdArr = item.getPropIds().split(",");
			for(String propId : propIdArr)
			{
				String insertSystemPropVal_SQL = "insert into system_user_prop_val set user_id = " + item.getUserId().toString() + ",prop_id=" + propId;
				getJdbcTemplate().update(insertSystemPropVal_SQL);
			}
		}
		if(item.getAccountName() != null && !item.getAccountName().equals(""))
		{
			String accountSql = "update system_account set account_name='" + item.getAccountName() + "' where user_id = " + item.getUserId().toString();
			getJdbcTemplate().update(accountSql);
		}
		
		String sql = "update system_user s set s.REAL_NAME = ?, s.WORK_UNIT = ?, s.SEX = ?, s.MOBIL_PHONE = ?, s.DEPT_NAME = ?,s.USER_TYPE = ?,s.STATUS=? where s.ID = ?";
		return this.getJdbcTemplate().update(sql, item.getRealName(), item.getWorkUnit(), item.getSex(), item.getMobilPhone(), item.getDeptName(), item.getUserType(), item.getStatus(), item.getUserId());
	}

	@Override
	public int delete(long id, String idColName) {
		String sql = "delete system_user s where s.USER_ID = ?";
	//	return this.getJdbcTemplate().execute(sql);
		return super.delete(id, idColName);
	}
	
	@Override
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*, t2.user_name, t2.address, t2.mobile_phone, t3.name from system_user_post_history t");
		sql.append(" left join system_user_address t2 on t.address_id = t2.id ");
		sql.append(" left join system_express t3 on t.express_id = t3.id ");
		sql.append("  where t.status = ?");

		List<Object> list = new ArrayList<Object>();
		list.add(Constants.STATUS_1);
		
		if(null != p.getCertificateName()){
			sql.append(" and t.certificate_name like ? ");
			list.add("%" + p.getCertificateName().trim() + "%");
		}
		
		if(null != p.getMobilePhone()){
			sql.append(" and t2.mobile_phone like ? ");
			list.add("%" + p.getMobilePhone().trim() + "%");
		}
		
		if(null != p.getUserName()){
			sql.append(" and t2.user_name like ? ");
			list.add("%" + p.getUserName().trim() + "%");
		}
		
		sql.append(" order by t.id desc ");

		// 查询结果集
		List<SystemUserPostHistory> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),  list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUserPostHistory.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
		
		return resList;
	}
	
	@Override
	public SystemUserPostHistory getSystemPostHistoryById(Long id){
		String sql = "select t.*, t2.user_name, t2.address, t2.mobile_phone, t3.name, t3.code as expressCode from system_user_post_history t " +
				" left join system_user_address t2 on t.address_id = t2.id " +
				" left join system_express t3 on t.express_id = t3.id where t.id = ?";
		return this.getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUserPostHistory.class),id);
	}
	
	@Override
	public int updateSystemUserPostHistory(SystemUserPostHistory p){
		String sql = "update system_user_post_history t set t.express_no = ?, t.express_id = ?, t.invoice_status = ?, t.certificate_name=?, t.description=?, t.create_date=sysdate() where t.id = ?";
		this.getJdbcTemplate().update(sql, p.getExpressNo(), p.getExpressId(), p.getInvoiceStatus(), p.getCertificateName(), p.getDescription(), p.getId());
		sql = "update system_user_address set user_name = ?, address = ?, mobile_phone = ?, create_date = sysdate() where id in (select address_id from system_user_post_history where id = ?)";
		return this.getJdbcTemplate().update(sql, p.getUserName(), p.getAddress(), p.getMobilePhone(), p.getId());		
	}
	
	@Override
	public List<SystemExpress> getSystemExpressList(){
		String sql = "select * from system_express t where t.status = 1";
		return getList(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemExpress.class));
	}
	
	@Override
	public SystemUserAddress getSystemUserAddressById(Long id){
		String sql = "select * from system_user_address t where t.id = ?";
		return this.getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUserAddress.class), id);
	}
	
	@Override
	public SystemExpress getSystemExpressById(Long id){
		String sql = "select * from system_express t where t.id = ?";
		return this.getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemExpress.class), id);
	}
	
	@Override
	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role) {

		StringBuilder sql = new StringBuilder();
		StringBuilder sqlcount = new StringBuilder();
		if(role != null)
		{
			sql.append("select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark,a.account_status from system_account a left join system_user u on a.user_Id = u.id left join system_account_role ar on a.account_id = ar.account_id where a.account_id>0 ");
			sqlcount.append("select count(1) as count from system_account a left join system_user u on a.user_id=u.id left join system_account_role ar on a.account_id=ar.account_id where a.account_id>0 ");
		}
		else
		{
			sql.append("select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark,a.account_status from system_account a left join system_user u on a.user_Id = u.id where a.account_id>0 ");
			sqlcount.append("select count(1) as count from system_account a left join system_user u on a.user_Id = u.id  where a.account_id>0 ");
		}
		

		Integer status = item.getAccount_status();
		String workUnit = item.getWorkUnit();
		Integer userType = item.getUserType();


		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(item.getRealName())) {
			sql.append(" and u.Real_Name like ? ");
			sqlcount.append(" and u.Real_Name like ? ");
			list.add("%"+item.getRealName().trim()+"%" );
		} 
		if (StringUtils.isNotBlank(item.getAccountName())) {
			sql.append(" and a.account_name like ? ");
			sqlcount.append(" and a.account_name like ? ");
			list.add("%"+item.getAccountName().trim()+"%" );
		} 
		if (status != null && status > 0) {
			sql.append(" and a.account_status = ? ");
			sqlcount.append(" and a.account_status = ? ");
			list.add(status);
		}
		if (workUnit != null && !workUnit.equals("")) {
			sql.append(" and u.Work_Unit like ? ");
			sqlcount.append(" and u.Work_Unit like ? ");
			list.add("%"+workUnit.trim()+"%" );
		}
		if (userType != null && userType > 0L) {
			sql.append(" and u.User_Type = ? ");
			sqlcount.append(" and u.User_Type = ? ");
			list.add(userType);
		}
		if(role != null && role>0L)
		{
			sql.append(" and ar.role_id = ?");
			sqlcount.append(" and ar.role_id = ?");
			list.add(role);
		}
		

		sql.append(" order by u.reg_date desc ");
		sqlcount.append(" order by u.reg_date desc ");

		// 查询结果集
		List<SystemUser> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),  list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));				
				
		// //取得总记录数
		//Integer totalCount = getCount(sql.toString(), list.toArray());
		Integer totalCount = getJdbcTemplate().queryForInt(sqlcount.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}
	
	public List<SystemUserType> getUserTypeList()
	{
		String sql = "select * from system_user_type";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUserType.class));
	}
	public List<SystemRole> getUserRoleList(Long userId)
	{
		String sql = "select r.* from system_role r LEFT JOIN system_account_role a on r.ROLE_ID = a.ROLE_ID where a.ACCOUNT_ID = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(userId);
		return getJdbcTemplate().query(sql, list.toArray(),
				ParameterizedBeanPropertyRowMapper.newInstance(SystemRole.class));
	}
	
	public int updateAccountState(Integer id, Integer newState)
	{
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_account set ");
		if(null != newState){
			sql.append("ACCOUNT_STATUS=? ");
			values.add(newState);
		}
		else
		{
			return 0;
		}
		sql.append("where account_id = ?");
		values.add(id);
		
		return getJdbcTemplate().update(sql.toString(),values.toArray());
		
	}
	public int updateAccountRole(Integer accountId,String[] roleList)
	{
		StringBuffer deleteSql = new StringBuffer();
		deleteSql.append("delete from system_account_role where account_id = ").append(accountId);
		getJdbcTemplate().update(deleteSql.toString());
		
		int result = 0;
		for(String roleId : roleList)
		{
			String sql = "insert into system_account_role (account_id,role_id) values (?,?)";
			List list = new ArrayList();
			list.add(accountId);
			list.add(roleId.trim());
			result = getJdbcTemplate().update(sql.toString(),list.toArray());
		}
		return result;
	}

	@Override
	public List<PropUnit> getHealthData(Long id) {
		String sql_select_health = "select health_certificate as id from system_user where id="+id;
		List<PropUnit> list = getJdbcTemplate().query(sql_select_health,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		
		return list;
	}

	@Override
	public void querySystemUserPageList(PageList pl, SystemUser item) {

		String selectSql = " select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark, a.account_status from system_user u " +
				"LEFT JOIN exam_hospital e on u.WORK_UNIT_ID = e.ID " +
				"left join system_user_config c on u.id=c.user_id left join system_account a on u.id = a.user_Id where u.id>0 and u.user_type = 9  " ;
		String selectSqlSize = " select count(1) from system_user u " +
				"LEFT JOIN exam_hospital e on u.WORK_UNIT_ID = e.ID " +
				"left join system_user_config c on u.id=c.user_id left join system_account a on u.id = a.user_Id where u.id>0  and u.user_type = 9 " ;
		
		
		StringBuilder sql = new StringBuilder();
		StringBuilder sql_size = new StringBuilder();
		sql.append(selectSql);
		sql_size.append(selectSqlSize);
		
		Integer status = item.getStatus();
		String workUnit = item.getWorkUnit();
		String deptName = item.getDeptName();
		Integer sex = item.getSex();
		Integer userType = item.getUserType();


		List<Object> list = new ArrayList<Object>();

		if (item.getUserId() != null && item.getUserId()>0) {
			sql.append(" and u.Id = ? ");
			sql_size.append(" and u.Id = ? ");
			list.add(item.getUserId() );
		} 
		if (StringUtils.isNotBlank(item.getRealName())) {
			sql.append(" and u.Real_Name like ? ");
			sql_size.append(" and u.Real_Name like ? ");
			list.add("%"+item.getRealName().trim()+"%" );
		} 		
		if (StringUtils.isNotBlank(item.getAccountName())) {
			sql.append(" and a.Account_Name like ? ");
			sql_size.append(" and a.Account_Name like ? ");
			list.add("%"+item.getAccountName().trim()+"%" );
		}
		if (StringUtils.isNotBlank(item.getCertificateNo())) {
			sql.append(" and u.Certificate_No like ? ");
			sql_size.append(" and u.Certificate_No like ? ");
			list.add("%"+item.getCertificateNo().trim()+"%" );
		}
		if (status != null && status > 0) {
			sql.append(" and a.account_status = ? ");
			sql_size.append(" and a.account_status = ? ");
			list.add(status);
		}
		/*if (item.getUserType() != null)
		if(item.getUserType() != 1)
		{*/
			if (workUnit != null && !workUnit.equals("")) {
				sql.append(" and e.NAME like ? ");
				sql_size.append(" and e.NAME like ? ");
				list.add("%"+workUnit.trim()+"%" );
			}	
		/*}
		else
		{
			if (workUnit != null && !workUnit.equals("")) {
				sql.append(" and u.work_unit_id in (").append(workUnit.trim()).append(")");
			}	
		}*/
		
		if (deptName != null && !deptName.equals("")) {
			/*sql.append(" and u.DEPT_NAME like ? ");
			list.add("%"+deptName.trim()+"%" );*/
			String getPropVal = " select USER_ID from system_user_prop_val where PROP_ID in (" + deptName + ")";
			try{
				List<Long> userIds = getJdbcTemplate().queryForList(getPropVal, Long.class);
				String ids = userIds.toString();
				ids = ids.substring(1, ids.length()-1);
				if (ids.equals("")) ids = "0";
				sql.append(" and u.id in (").append(ids).append(")");
				sql_size.append(" and u.id in (").append(ids).append(")");
			}catch(Exception e){}
		}
		if (sex != null && sex > 0) {
			sql.append(" and u.sex = ? ");
			sql_size.append(" and u.sex = ? ");
			list.add(sex);
		}
		if (userType != null && userType > 0L) {
			sql.append(" and u.user_Type = ? ");
			sql_size.append(" and u.user_Type = ? ");
			list.add(userType);
		}
		
		//add by han.
		
		if (item.getProfTitle()!=null && !item.getProfTitle().equals("")){
			/*sql.append(" and u.prof_title = ? ");
			list.add(item.getProfTitle());*/
			String getPropVal = " select USER_ID from system_user_job_val where JOB_ID in (" + item.getProfTitle() + ")";
			try{
				List<Long> userIds = getJdbcTemplate().queryForList(getPropVal, Long.class);
				String ids = userIds.toString();
				ids = ids.substring(1, ids.length()-1);
				if (ids.equals("")) ids = "0";
				sql.append(" and u.id in (").append(ids).append(")");
				sql_size.append(" and u.id in (").append(ids).append(")");
			}catch(Exception e){}
		}
		if (item.getUserConfig() != null){
			SystemUserConfig userConfig = item.getUserConfig();
			if (userConfig.getUserProvinceId()!=null && userConfig.getUserProvinceId()!=0){
				sql.append(" and c.user_province_id = ? ");
				sql_size.append(" and c.user_province_id = ? ");
				list.add(userConfig.getUserProvinceId());
			}
			if (userConfig.getUserCityId()!=null && userConfig.getUserCityId()!=0){
				sql.append(" and c.user_city_id = ? ");
				sql_size.append(" and c.user_city_id = ? ");
				list.add(userConfig.getUserCityId());
			}
			if (userConfig.getUserCountiesId()!=null && userConfig.getUserCountiesId()!=0){
				sql.append(" and c.user_counties_id = ? ");
				sql_size.append(" and c.user_counties_id = ? ");
				list.add(userConfig.getUserCountiesId());
			}
		}
		//end.
		// paging code added by Tiger.
		if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			sql.append(" order by ").append(pl.getSortCriterion());
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				sql.append(" desc");
		}
		
		Integer fullListSize = getJdbcTemplate().queryForInt(sql_size.toString(), list.toArray());
		pl.setFullListSize(fullListSize);
		
		// 查询结果集
		List<SystemUser> resList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class),list.toArray());				
				
		// add by han.
		for(SystemUser user: resList){
			SystemUserConfig userConfig = new SystemUserConfig();
			userConfig = this.getSystemUserConfigByUserId(user.getUserId());
			user.setUserConfig(userConfig);
			
			user.setDeptName("");
			String getPropName = "SELECT p.*  from exam_prop_val p left join sub_sys_prop_val s on p.ID=s.PROP_VAL_ID LEFT JOIN system_user_prop_val v on v.PROP_ID=s.id where v.USER_ID="+user.getUserId();
			try{
				List<ExamProp> prop = this.getJdbcTemplate().query(getPropName, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
				/*if (prop!=null && prop.size()>0){
					user.setDeptName(prop.get(0).getName());
				}*/
				String courNames = "";
				for(ExamProp p :prop){
					courNames += p.getName()+",";
				}
				if(StringUtils.isNotBlank(courNames)){
					courNames = courNames.substring(0, courNames.length()-1);
				}
				user.setDeptName(courNames);
			}catch(Exception e){}
			
			if(user.getWork_unit_id() != null && !user.getWork_unit_id().equals(0L))
			{
				String hospSql = "select * from exam_hospital where id = " + user.getWork_unit_id(); 
				List<ExamHospital> hosp = getJdbcTemplate().query(hospSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class));
				if(hosp != null && hosp.size() > 0)
				{
//					user.getUserConfig().setAddress(hosp.get(0).getHospital_address());
					user.setWorkUnit(hosp.get(0).getName());
				}
				
			}
			
			
			user.setProfTitle("");
			getPropName = "SELECT p.*  from exam_prop_val p left join sub_sys_prop_val s on p.ID=s.PROP_VAL_ID LEFT JOIN system_user_job_val v on v.JOB_ID=s.id where v.USER_ID="+user.getUserId();
			try{
				List<ExamProp> prop = this.getJdbcTemplate().query(getPropName, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
				if (prop!=null && prop.size()>0){
					user.setProfTitle(prop.get(0).getName());
				}
			}catch(Exception e){}
		}
		pl.setList(resList);
	}

	/*
	 * @Create Date : 2017/01/11
	 * @Author      : lee
	 * @see com.hys.exam.dao.local.SystemUserDAO#isDuplicate(com.hys.exam.model.SystemUser)
	 */
	@Override
	public List<SystemUser> isDuplicate(SystemUser item) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select * from system_user u left join system_account a on u.id = a.user_id where u.id > 0");
		if(item.getUserId() != null && !item.getUserId().equals(0L))
		{
			sql.append(" and u.id != ").append(item.getUserId());
		}
		if(!StringUtil.checkNull(item.getAccountName()))
		{
			sql.append(" and a.account_name = '").append(item.getAccountName()).append("'");
		}
		if(!StringUtil.checkNull(item.getRealName()))
		{
			sql.append(" and u.real_name = '").append(item.getRealName()).append("'");
		}
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
	}

	@Override
	public int updateSystemAccount(Long id, Integer state) {
		String sql = "update system_account s set s.account_status=? where s.user_id=?";
		return this.getJdbcTemplate().update(sql, state, id);
	
	}
}