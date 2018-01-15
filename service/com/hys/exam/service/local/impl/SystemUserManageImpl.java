package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemUserDAO;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;
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
public class SystemUserManageImpl extends BaseMangerImpl implements SystemUserManage {

	private SystemUserDAO systemUserDAO ;

	public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
		this.systemUserDAO = systemUserDAO;
	}
	
	@Override
	public List<SystemUser> getListByItemCopy(SystemUser item){
		return systemUserDAO.getListByItemCopy(item);
	}
	
	@Override //取得所有站点信息
	public List<SystemUser> getListByItem(SystemUser item){
		return systemUserDAO.getListByItem(item) ;
	}
	
	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item) {
		systemUserDAO.querySystemUserList(page, item);
	}
	
	@Override
	public SystemUserConfig getSystemUserConfigByUserId(Long userId){
		SystemUserConfig config = this.systemUserDAO.getSystemUserConfigByUserId(userId);
		if(null != config){
			SystemIndustry industry = this.systemUserDAO.getSystemIndustryByPositionId(config.getUserIndustryId());
			if(null != industry)
				config.setUserIndustryName(industry.getIndustryName());
		}
		return config;
	}
	
	
	public int update(SystemUser item) {
		return systemUserDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return systemUserDAO.deleteList(ids,idColName);
	}

	
	public SystemUser getItemById(Long id) {
		return systemUserDAO.getItemById(id);
	}
	public SystemUser getItemByIdNew(Long id) {
		return systemUserDAO.getListByItemNew(id);
	}
	public SystemUser getItemByAccountName(String accountName , String password)
	{
		return systemUserDAO.getItemByAccountName(accountName, password);
	}

	
	public Integer save(SystemUser item) {
		return systemUserDAO.save(item);
	}
	
	public Integer insert(SystemUser item) {
		return systemUserDAO.insert(item);
	}

	@Override
	public int delete(long id, String idColName) {
		return systemUserDAO.delete(id,idColName);
	}
	
	//user post history
	@Override
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p){
		return this.systemUserDAO.getSystemPostHistoryList(page, p);
	}
	
	@Override
	public SystemUserPostHistory getSystemPostHistoryById(Long id){
		return this.systemUserDAO.getSystemPostHistoryById(id);
	}
	
	@Override
	public int updateSystemUserPostHistory(SystemUserPostHistory history){
		return this.systemUserDAO.updateSystemUserPostHistory(history);
	}
	
	@Override
	public List<SystemExpress> getSystemExpressList(){
		return this.systemUserDAO.getSystemExpressList();
	}

	@Override
	public Integer setPass(String accountName, String accountPass) {
		return this.systemUserDAO.setPass(accountName, accountPass);
	}
	@Override
	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role)
	{
		this.systemUserDAO.querySystemAccountList(page,item,role);
	}
	@Override
	public List<SystemUserType> getUserTypeList()
	{
		return this.systemUserDAO.getUserTypeList();
	}
	@Override
	public List<SystemRole> getUserRoleList(Long userId)
	{
		return this.systemUserDAO.getUserRoleList(userId);
	}
	@Override
	public int updateAccountState(Integer accountId,Integer newState)
	{
		return this.systemUserDAO.updateAccountState(accountId,newState);
	}
	@Override
	public int updateAccountRole(Integer accountId,String[] roleList)
	{
		return this.systemUserDAO.updateAccountRole(accountId,roleList);
	}

	@Override
	public List<PropUnit> getHealthData(Long id) {
		// TODO Auto-generated method stub
		return systemUserDAO.getHealthData(id);
	}

	@Override
	public void querySystemUserPageList(PageList pl, SystemUser item) {
		systemUserDAO.querySystemUserPageList(pl,item);
	}

	@Override
	public List<SystemUser> isDuplicate(SystemUser item) {
		// TODO Auto-generated method stub
		return systemUserDAO.isDuplicate(item);
	}

	@Override
	public int updateSystemAccount(Long id, Integer state) {
		return systemUserDAO.updateSystemAccount(id,state);
	}
	
	@Override
	public SystemConfigVO getLoginLimtVo(){
		return systemUserDAO.getLoginLimtVo();
	}
	
	@Override
	public int updateLoginErrors(SystemUser item){
		return systemUserDAO.updateLoginErrors(item);
	}

	@Override
	public int updatenew(SystemUser item) {
		return systemUserDAO.updatenew(item);
	}
	
	@Override
	public int updateAccount(SystemUser item) {
		return systemUserDAO.updateAccount(item);
	}

	@Override
	public int checkIdCard(String idCard,Integer userId) {
		return systemUserDAO.checkIdCard(idCard,userId);
	}

	@Override
	public int checkEmail(String email,Integer userId) {
		return systemUserDAO.checkEmail(email,userId);
		
	}

	@Override
	public int checkUserCode(String code, Integer userId) {
		return systemUserDAO.checkUserCode(code, userId);
	}

	@Override
	public int checkMobile(String phone, Integer userId) {
		return systemUserDAO.checkMobile(phone,userId);
	}

	@Override
	public int checkUserName(String username, Integer userId) {
		return systemUserDAO.checkUserName(username, userId);
	}
	
}