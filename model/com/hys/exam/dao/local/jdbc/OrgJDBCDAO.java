package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.OrgDAO;
import com.hys.exam.dao.local.SystemUserDAO;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserPostHistory;
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
public  class OrgJDBCDAO extends BaseDao implements OrgDAO {
	
   
	@Override  
	public List<PeixunOrg> queryOrgList(PeixunOrg item){
		
		List <Object> list = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("select p.*, s.account_id, s.account_name, s.account_password, s.account_remark, s.account_status from peixun_org p left join system_account s on p.user_id=s.user_id");
		
		if(item.getId() != null){
			query.append(" where p.id="+item.getId());
			return  getJdbcTemplate().query(query.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class),list.toArray());
		}
			query.append(" where p.id > 0");
			if (item.getName() != null && !item.getName().equals("")){
				query.append(" and p.name like ?");
				list.add("%"+item.getName()+"%");
			}
			
			if (item.getRegion1_Id() != null && item.getRegion1_Id() > 0){
				query.append(" and p.region1_Id=?");
				list.add(item.getRegion1_Id());
			}
			if (item.getRegion2_Id() != null && item.getRegion2_Id() > 0){
				query.append(" and p.region2_Id=?");
				list.add(item.getRegion2_Id());
			}
			
			/*if(item.getLevel() != null && item.getLevel()>0)
			{
				query.append(" and p.level=?");
				list.add(item.getLevel());
			}*/
			if(item.getType() != null && item.getType()>0)
			{
				query.append(" and p.type=?");
				list.add(item.getType());
			}
			if(item.getState() != null && item.getState() > 0)
			{
				query.append(" and p.state=?");
				list.add(item.getState());
			}
			query.append(" order by id desc");
			
			return getJdbcTemplate().query(query.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class),list.toArray());
	}
	
	public Long addPeixunOrg(PeixunOrg item)
	{	
//		Long accountId = this.getNextId("system_account");
		Long orgId = this.getNextId("peixun_org");
		///// insert SystemAccount
/*		String insertSystemAccount_SQL = "insert into system_account (account_id, account_name, account_password, account_remark, user_id, account_status) values (?, ?, ?, ?, ?, ?)";
		item.setAccount_status(1);		
		item.setAccountRemark("");
		item.setAccountId(accountId);
		String newPass = MD5Util.string2MD5("123456");
		item.setAccountPassword(newPass);
		getJdbcTemplate().update(insertSystemAccount_SQL, item.getAccountId(), item.getAccountName(), item.getAccountPassword(),item.getAccountRemark(), item.getAccountId(), item.getAccount_status());
		
*/		///// get new SystemAccount id
		//// insert PeixunOrg
		//item.setState(1);
		
		item.setId(orgId);
		String insertPeixunOrg_SQL = "insert into peixun_org (ID, NAME, REGION1_ID, REGION2_ID,TYPE, LEVEL, HOSPITAL_ID, CONTACT, STATE, PHONE_NUMBER, DESCRIPTION, USER_ID, FILEPATH, PHOTOPATH) values (:id, :name, :region1_Id, :region2_Id, :type, :level, :hospital_Id, :contact, :state, :phone_Number, :description, :user_Id, :filePath, :photoPath)";
		
		getSimpleJdbcTemplate().update(insertPeixunOrg_SQL, new BeanPropertySqlParameterSource(item));
		return orgId;
	}
	
	public PeixunOrg getItemById(Long id) {
		
		PeixunOrg item = new PeixunOrg();
		item.setId(id);
		List<PeixunOrg> list = this.queryOrgList(item);
		if(list!=null && list.size()==1)
			return list.get(0);
		else return  item;
	}
	
	public void updatePeixunOrg(PeixunOrg item) {
		
		if(item.getName() != "" && item.getName()!= null){
			StringBuilder query = new StringBuilder("update peixun_org set ");	
			List<Object> list = new ArrayList<Object>();
			
			if(item.getType() != null && item.getType() > 0){
				query.append("TYPE=?,");
				list.add(item.getType());
			}
			if(item.getType() == 4){
				//此次根据JIRA上STUDYADMIN-701，要加上地区信息
//				query.append("HOSPITAL_ID=?,");
//				list.add(item.getHospital_Id());
//				query.append("REGION1_ID=null,");
//				query.append("REGION2_ID=null,");
				if(item.getRegion1_Id() != null){
					query.append("REGION1_ID=?,");
					list.add(item.getRegion1_Id());
				}
				if(item.getRegion1_Id() != null){
					query.append("REGION2_ID=?,");
					list.add(item.getRegion2_Id());
				}
			}else{
				query.append("HOSPITAL_ID=null,");
				if(item.getRegion1_Id() != null){
					query.append("REGION1_ID=?,");
					list.add(item.getRegion1_Id());
				}
				if(item.getRegion1_Id() != null){
					query.append("REGION2_ID=?,");
					list.add(item.getRegion2_Id());
				}
			}/*
			if(item.getType() == 2){
				query.append("HOSPITAL_ID=null,");
			}
			if(item.getLevel() != null){
				query.append("LEVEL=?,");
				list.add(item.getLevel());
			}*/
			if(item.getContact() != null && item.getContact() != ""){
				query.append("CONTACT=?,");
				list.add(item.getContact());
			}
			if(item.getPhone_Number()!= null){
				query.append("PHONE_NUMBER=?,");
				list.add(item.getPhone_Number());
			}
			if(item.getDescription() != null && item.getDescription() != ""){
				query.append("DESCRIPTION=?,");
				list.add(item.getDescription());
			}
			if(item.getFilePath() != null && item.getFilePath() != ""){
				query.append("FILEPATH=?,");
				list.add(item.getFilePath());
			}
			if(item.getPhotoPath() != null && item.getPhotoPath() != ""){
				query.append("PHOTOPATH=?,");
				list.add(item.getPhotoPath());
			}
			query.append("NAME='"+item.getName()+"' where Id=?");
			list.add(item.getId());
			getJdbcTemplate().update(query.toString(), list.toArray());			
		}
		if(item.getAccountName() !=null){
			if(item.getAccountName() == ""){
				String delaccount = "delete from system_account where ACCOUNT_ID="+item.getAccountId();
				getJdbcTemplate().execute(delaccount);
			}
			else{
				String updateAccount = "update system_account set ACCOUNT_NAME='"+item.getAccountName()+"' where ACCOUNT_ID="+item.getAccountId();
				getJdbcTemplate().update(updateAccount);
			}/*getJdbcTemplate().update(updateAccount.toString(), acclist.toArray());*/
		}
	}	

	@Override
	public void setState(Long id) {
		StringBuilder query = new StringBuilder("update peixun_org set STATE=:state where ID=:id");
		PeixunOrg item = new PeixunOrg();
		item.setId(id);
		List<PeixunOrg> list = this.queryOrgList(item);
		item = list.get(0);
		//同步更新用户状态
		Long user_id=item.getUser_Id();
		int newUserState=1;
		if(item.getState() == 1){
			item.setState(2);
			newUserState=2;
		}else{ 
			item.setState(1);
		}
		getSimpleJdbcTemplate().update(query.toString(), new BeanPropertySqlParameterSource(item));
		String sql = "UPDATE system_account set ACCOUNT_STATUS=? where ACCOUNT_ID=?";
		getJdbcTemplate().update(sql,newUserState,user_id);
	}

	@Override
	public List<ExamHospital> getHospital() {
		StringBuilder query = new StringBuilder("select id, name from exam_hospital");
		List <ExamHospital> list = new ArrayList<ExamHospital>();
		return getJdbcTemplate().query(query.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class), list.toArray());
	}

	@Override
	public List<SystemAccount> getAccount() {
		StringBuilder query = new StringBuilder("select account_id, account_name from system_account");
		List <SystemAccount> list = new ArrayList<SystemAccount>();
		return getJdbcTemplate().query(query.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemAccount.class), list.toArray());
	}

	@Override
	public String duplicate(PeixunOrg item) {
		String duplicateSql_Name="select * from  peixun_org where name='"+item.getName()+"'";
		List<SystemAccount> nameList = getJdbcTemplate().query(duplicateSql_Name, ParameterizedBeanPropertyRowMapper.newInstance(SystemAccount.class));
  		 if(nameList.size()>0)
			  return "nameDuplicate";
		 else
			  return "fail";
	}
	@Override
	public String duplicateAccountName(String accountName) {
		String duplicateSql_Name="select * from system_account where ACCOUNT_NAME='"+accountName+"'";
		List<SystemAccount> nameList = getJdbcTemplate().query(duplicateSql_Name, ParameterizedBeanPropertyRowMapper.newInstance(SystemAccount.class));
		if(nameList.size()>0)
			return "accountNameDuplicate";
		else
			return "fail";
	}

	@Override
	public void queryOrgPageList(PageList pl, PeixunOrg item) {
		List <Object> list = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("select p.*, s.account_id, s.account_name, s.account_password, s.account_remark, s.account_status , h.name as hospital_name from peixun_org p left join system_account s on p.user_id=s.user_id left join exam_hospital h on p.hospital_id=h.id");
		StringBuilder querycount = new StringBuilder("select count(1) from peixun_org p left join system_account s on p.user_id=s.user_id left join exam_hospital h on p.hospital_id=h.id ");

		query.append(" where p.id > 0");
		querycount.append(" where p.id > 0");
		if (item.getName() != null && !item.getName().equals("")){
			query.append(" and p.name like ?");
			querycount.append(" and p.name like ?");
			list.add("%"+item.getName()+"%");
		}
		
		if (item.getRegion1_Id() != null && item.getRegion1_Id() > 0){
			query.append(" and p.region1_Id=?");
			querycount.append(" and p.region1_Id=?");
			list.add(item.getRegion1_Id());
		}
		if (item.getRegion2_Id() != null && item.getRegion2_Id() > 0){
			query.append(" and p.region2_Id=?");
			querycount.append(" and p.region2_Id=?");
			list.add(item.getRegion2_Id());
		}
		
		if(item.getType() != null && item.getType()>0)
		{
			query.append(" and p.type=?");
			querycount.append(" and p.type=?");
			list.add(item.getType());
		}
		if(item.getState() != null && item.getState() > 0)
		{
			query.append(" and p.state=?");
			querycount.append(" and p.state=?");
			list.add(item.getState());
		}
		
		// paging code added by Tiger.
		if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			query.append(" order by ").append(pl.getSortCriterion());
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				query.append(" desc");
		}
		
		
		//Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(query.toString()), list.toArray());
		Integer fullListSize = getJdbcTemplate().queryForInt(querycount.toString(), list.toArray());
		pl.setFullListSize(fullListSize);

		List<PeixunOrg> orgList = new ArrayList<PeixunOrg>();
				 orgList = getJdbcTemplate().query(PageUtil.getPageSql(query.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class),list.toArray());
		pl.setList(orgList);
	}
}