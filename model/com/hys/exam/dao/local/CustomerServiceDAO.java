package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserAddress;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;
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
public interface CustomerServiceDAO {
	
	public List<SystemUser> getListByItem(SystemUser item);

	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item);
	
	public SystemUserConfig getSystemUserConfigByUserId(Long userId);
	
	public SystemIndustry getSystemIndustryByPositionId(Long id);

	public Integer save(SystemUser item);
	
	public Integer insert(SystemUser item);
	
	public Integer setPass(String account,String password);

	public SystemUser getItemById(Long id);

	public int update(SystemUser item);
	
	public int updateSystemAccount(Long id,Integer state);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
	
	//user post history
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p);
	
	public SystemUserPostHistory getSystemPostHistoryById(Long id);
	
	public int updateSystemUserPostHistory(SystemUserPostHistory history);
	
	public List<SystemExpress> getSystemExpressList();
	
	public SystemUserAddress getSystemUserAddressById(Long id);
	
	public SystemExpress getSystemExpressById(Long id);
	
	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role);
	
	public SystemUser getItemByAccountName(String accountName , String password);
	
	public List<SystemUserType> getUserTypeList();
	
	public List<SystemRole> getUserRoleList(Long userId);
	
	public int updateAccountState(Integer accountId,Integer newState);
	
	public int updateAccountRole(Integer accountId,String[] roleList);

	public List<PropUnit> getHealthData(Long id);

	public void querySystemUserPageList(PageList pl, SystemUser item);
	
	public List<SystemUser> isDuplicate(SystemUser item);
}