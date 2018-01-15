package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.OrgDAO;
import com.hys.exam.dao.local.SystemUserDAO;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.service.local.OrgManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 manageImpl
 * 
 * 说明:
 */
public class OrgManageImpl extends BaseMangerImpl implements OrgManage {

	private OrgDAO localOrgDAO ;

	public OrgDAO getLocalOrgDAO() {
		return localOrgDAO;
	}

	public void setLocalOrgDAO(OrgDAO localOrgDAO) {
		this.localOrgDAO = localOrgDAO;
	}

	@Override
	public List<PeixunOrg> queryOrgList(PeixunOrg item){
		return localOrgDAO.queryOrgList(item);
	}

	public Long addPeixunOrg(PeixunOrg item) {
		
		return localOrgDAO.addPeixunOrg(item);
	}

	public PeixunOrg getItemById(Long id) {
		// TODO Auto-generated method stub
		return localOrgDAO.getItemById(id);
	}


	@Override
	public void setState(Long id) {
		// TODO Auto-generated method stub
		localOrgDAO.setState(id);
	}


	@Override
	public void updatePeixunOrg(PeixunOrg item) {
		// TODO Auto-generated method stub
		localOrgDAO.updatePeixunOrg(item);
	}


	@Override
	public List<ExamHospital> getHospital() {
		return localOrgDAO.getHospital();
	}


	@Override
	public List<SystemAccount> getAccount() {
		return localOrgDAO.getAccount();
	}

	@Override
	public String Duplicate(PeixunOrg item) {
		return localOrgDAO.duplicate(item);
	}
	@Override
	public String duplicateAccountName(String accountName) {
		return localOrgDAO.duplicateAccountName(accountName);
	}

	@Override
	public void queryOrgPageList(PageList pl, PeixunOrg item) {
		localOrgDAO.queryOrgPageList(pl,item);
	}
	
}