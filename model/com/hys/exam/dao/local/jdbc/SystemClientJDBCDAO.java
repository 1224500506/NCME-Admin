package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.SystemClientDAO;
import com.hys.exam.model.SystemClient;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemClientJDBCDAO extends AbstractJDBCDAO implements SystemClientDAO {

	final static private String getSystemClientList_SQL = "select t.*,(select o.org_name from system_org o where o.id = t.org_id) org_name from System_Client t where t.status=1 ";
	final static private String addSystemClient_SQL = "insert into system_client (id, client_name, org_id, contact, contact_phone, backup_phone, main_charge, main_charge_contact, remark, status) values (:id, :clientName, :orgId, :contact, :contactPhone, :backupPhone, :mainCharge, :mainChargeContact, :remark, :status) ";
	
	public SystemClientJDBCDAO() {
		super(SystemClient.class, Constants.TABLE_SYSTEM_CLIENT);
	}
	public List<SystemClient> getListByItem(SystemClient item){
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemClientList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getClientName() != null && !"".equals(item.getClientName())) {
			sql.append(" and t.client_Name like ? ");
			list.add("%" + item.getClientName() + "%");
		}

		if (item.getRemark() != null && !"".equals(item.getRemark())) {
			sql.append(" and t.remark like ? ");
			list.add("%" + item.getRemark() + "%");
		}
		// 查询结果集
		List<SystemClient> resList = getList(sql.toString(), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemClient.class));
		return resList;
	}
	
	@Override
	public void getSystemClientList(Page<SystemClient> page, SystemClient item) {

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemClientList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getClientName() != null && !"".equals(item.getClientName())) {
			sql.append(" and t.client_Name like ? ");
			list.add("%" + item.getClientName() + "%");
		}

		if (item.getOrgId() != null && item.getOrgId() >0) {
			sql.append(" and t.org_Id = ? ");
			list.add(item.getOrgId() );
		}
		
		if (item.getRemark() != null && !"".equals(item.getRemark())) {
			sql.append(" and t.remark like ? ");
			list.add("%" + item.getRemark() + "%");
		}
		// 查询结果集
		List<SystemClient> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemClient.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}

	public Integer save(SystemClient item) {
		Long id = this.getSequence("SYSTEM_CLIENT_SEQ");
		item.setId(id);
		getNamedParameterJdbcTemplate().update(addSystemClient_SQL,
				new BeanPropertySqlParameterSource(item));
		return  id.intValue();
	}
	@Override
	public int update(SystemClient item) {
		StringBuffer sqlBf = new StringBuffer(" update SYSTEM_CLIENT set id = :id ");  
		Long orgId = item.getOrgId(); 
		Long id = item.getId(); 
		String contactPhone = item.getContactPhone(); 
		String mainChargeContact = item.getMainChargeContact(); 
		Integer status = item.getStatus(); 
		String remark = item.getRemark(); 
		String clientName = item.getClientName(); 
		String mainCharge = item.getMainCharge(); 
		String backupPhone = item.getBackupPhone(); 
		String contact = item.getContact(); 
		if (id==null || "".equals(id) || id==0){ 
		}else{ 
			if (orgId!=null && !"".equals(orgId)){ 
				sqlBf.append(",org_Id = :orgId "); 
			} 
			if (contactPhone!=null && !"".equals(contactPhone.trim())){ 
				sqlBf.append(",contact_Phone = :contactPhone "); 
			} 
			if (mainChargeContact!=null && !"".equals(mainChargeContact.trim())){ 
				sqlBf.append(",main_Charge_Contact = :mainChargeContact "); 
			} 
			if (status!=null && !"".equals(status)){ 
				sqlBf.append(",status = :status "); 
			} 
			if (remark!=null && !"".equals(remark.trim())){ 
				sqlBf.append(",remark = :remark "); 
			} 
			if (clientName!=null && !"".equals(clientName.trim())){ 
				sqlBf.append(",client_Name = :clientName "); 
			} 
			if (mainCharge!=null && !"".equals(mainCharge.trim())){ 
				sqlBf.append(",main_Charge = :mainCharge "); 
			} 
			if (backupPhone!=null && !"".equals(backupPhone.trim())){ 
				sqlBf.append(",backup_Phone = :backupPhone "); 
			} 
			if (contact!=null && !"".equals(contact.trim())){ 
				sqlBf.append(",contact = :contact "); 
			} 
		sqlBf.append(" where id = :id "); 
		return this.getNamedParameterJdbcTemplate().update(sqlBf.toString(), new BeanPropertySqlParameterSource(item)); 
			
		} 
		
		return 0;


	}

}
