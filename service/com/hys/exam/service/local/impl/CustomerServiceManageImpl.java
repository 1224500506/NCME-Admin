package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.CustomerServiceDAO;
import com.hys.exam.dao.local.SystemUserDAO;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;
import com.hys.exam.service.local.CustomerServiceManage;
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
public class CustomerServiceManageImpl extends BaseMangerImpl implements CustomerServiceManage {

	private CustomerServiceDAO customerServiceDAO ;

	public void setCustomerServiceDAO(CustomerServiceDAO customerServiceDAO) {
		this.customerServiceDAO = customerServiceDAO;
	}
	
	@Override //取得所有站点信息
	public List<SystemUser> getListByItem(SystemUser item){
		return customerServiceDAO.getListByItem(item) ;
	}
	
	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item) {
		customerServiceDAO.querySystemUserList(page, item);
	}
	
	@Override
	public SystemUserConfig getSystemUserConfigByUserId(Long userId){
		SystemUserConfig config = this.customerServiceDAO.getSystemUserConfigByUserId(userId);
		if(null != config){
			SystemIndustry industry = this.customerServiceDAO.getSystemIndustryByPositionId(config.getUserIndustryId());
			if(null != industry)
				config.setUserIndustryName(industry.getIndustryName());
		}
		return config;
	}
	
	
	public int update(SystemUser item) {
		return customerServiceDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return customerServiceDAO.deleteList(ids,idColName);
	}

	
	public SystemUser getItemById(Long id) {
		return customerServiceDAO.getItemById(id);
	}
	public SystemUser getItemByAccountName(String accountName , String password)
	{
		return customerServiceDAO.getItemByAccountName(accountName, password);
	}

	
	public Integer save(SystemUser item) {
		return customerServiceDAO.save(item);
	}
	
	public Integer insert(SystemUser item) {
		return customerServiceDAO.insert(item);
	}

	@Override
	public int delete(long id, String idColName) {
		return customerServiceDAO.delete(id,idColName);
	}
	
	//user post history
	@Override
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p){
		return this.customerServiceDAO.getSystemPostHistoryList(page, p);
	}
	
	@Override
	public SystemUserPostHistory getSystemPostHistoryById(Long id){
		return this.customerServiceDAO.getSystemPostHistoryById(id);
	}
	
	@Override
	public int updateSystemUserPostHistory(SystemUserPostHistory history){
		return this.customerServiceDAO.updateSystemUserPostHistory(history);
	}
	
	@Override
	public List<SystemExpress> getSystemExpressList(){
		return this.customerServiceDAO.getSystemExpressList();
	}

	@Override
	public Integer setPass(String accountName, String accountPass) {
		return this.customerServiceDAO.setPass(accountName, accountPass);
	}
	@Override
	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role)
	{
		this.customerServiceDAO.querySystemAccountList(page,item,role);
	}
	@Override
	public List<SystemUserType> getUserTypeList()
	{
		return this.customerServiceDAO.getUserTypeList();
	}
	@Override
	public List<SystemRole> getUserRoleList(Long userId)
	{
		return this.customerServiceDAO.getUserRoleList(userId);
	}
	@Override
	public int updateAccountState(Integer accountId,Integer newState)
	{
		return this.customerServiceDAO.updateAccountState(accountId,newState);
	}
	@Override
	public int updateAccountRole(Integer accountId,String[] roleList)
	{
		return this.customerServiceDAO.updateAccountRole(accountId,roleList);
	}

	@Override
	public List<PropUnit> getHealthData(Long id) {
		// TODO Auto-generated method stub
		return customerServiceDAO.getHealthData(id);
	}

	@Override
	public void querySystemUserPageList(PageList pl, SystemUser item) {
		customerServiceDAO.querySystemUserPageList(pl,item);
	}

	@Override
	public List<SystemUser> isDuplicate(SystemUser item) {
		// TODO Auto-generated method stub
		return customerServiceDAO.isDuplicate(item);
	}

	@Override
	public int updateSystemAccount(Long id, Integer state) {
		return customerServiceDAO.updateSystemAccount(id,state);
	}
}