package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserAddress;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.utils.PageList;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 dao
 * 
 * 说明:
 */
public interface OrgDAO {
	
	public List<PeixunOrg> queryOrgList(PeixunOrg item);

	public Long addPeixunOrg(PeixunOrg item);

	public PeixunOrg getItemById(Long id);

	public void updatePeixunOrg(PeixunOrg item);

	public void setState(Long id);

	public List<ExamHospital> getHospital();

	public List<SystemAccount> getAccount();

	public String duplicate(PeixunOrg item);
	
	public String duplicateAccountName(String accountName);

	public void queryOrgPageList(PageList pl, PeixunOrg item);
	
}