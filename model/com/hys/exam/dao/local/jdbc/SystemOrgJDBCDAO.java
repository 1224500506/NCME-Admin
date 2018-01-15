package com.hys.exam.dao.local.jdbc;

import java.util.List;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.SystemOrgDAO;
import com.hys.exam.model.SystemOrg;

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
public class SystemOrgJDBCDAO extends AbstractJDBCDAO implements SystemOrgDAO {

//	final static private String getSystemClientList_SQL = "select * from System_Client t where t.status=1 ";
//	final static private String addSystemClient_SQL = "insert into system_client (id, client_name, org_id, contact, contact_phone, backup_phone, main_charge, main_charge_contact, remark, status) values (:id, :clientName, :orgId, :contact, :contactPhone, :backupPhone, :mainCharge, :mainChargeContact, :remark, :status) ";
	
	public SystemOrgJDBCDAO() {
		super(SystemOrg.class, Constants.TABLE_SYSTEM_ORG);
	}

	@Override
	public List<SystemOrg> getSystemOrgList(SystemOrg item) {
		// TODO Auto-generated method stub
		return null;
	}

}
