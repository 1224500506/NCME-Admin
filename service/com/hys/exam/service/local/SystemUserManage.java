package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.model.system.SystemExpress;
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
 * 描述：账户信息 manage
 * 
 * 说明:
 */
public interface SystemUserManage {
	
	public List<SystemUser> getListByItemCopy(SystemUser item);
	
	public List<SystemUser> getListByItem(SystemUser item);
	
	public List<SystemUser> isDuplicate(SystemUser item);
	
	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item);
	
	public Integer save(SystemUser item);
	
	public Integer insert(SystemUser item);
	
	public Integer setPass(String accountName, String accountPass);

	public SystemUser getItemById(Long id);
	public SystemUser getItemByIdNew(Long id);

	public SystemUser getItemByAccountName(String accountName , String password);
	
	public SystemUserConfig getSystemUserConfigByUserId(Long userId);

	public int update(SystemUser item);
	public int updateSystemAccount(Long id,Integer state);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
	
	//user post history
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p);
	
	public SystemUserPostHistory getSystemPostHistoryById(Long id);
	
	public int updateSystemUserPostHistory(SystemUserPostHistory history);
	
	public List<SystemExpress> getSystemExpressList();

	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role);
	
	public List<SystemUserType> getUserTypeList();
	
	public List<SystemRole> getUserRoleList(Long userId);
	
	public int updateAccountState(Integer accountId,Integer newState);
	
	public int updateAccountRole(Integer accountId,String[] roleList);

	public List<PropUnit> getHealthData(Long id);

	public void querySystemUserPageList(PageList pl, SystemUser item);
	
	public SystemConfigVO getLoginLimtVo();

	public int updateLoginErrors(SystemUser item);
	
	public int updatenew(SystemUser item);
	
	public int updateAccount(SystemUser item);
	
	/**
	 * 方法说明： 根据证件号码查询是否已经存在
	 */
	public int checkIdCard(String idCard,Integer userId);
	
	/**
	 * 方法说明：根据邮箱查询是否已经存在
	 */
	public int checkEmail(String email,Integer userId);
	
	/**
	 * 方法说明： 验证执业医师号是否存在
	 */
	public int checkUserCode(String code,Integer userId);
	
	/**
	 * 方法说明： 根据手机号码查询是否已经存在
	 */
	public int checkMobile(String phone,Integer userId);
	
	/**
	 * 
	 * 方法说明：检测用户名是否可用
	 */
	public int checkUserName(String username,Integer userId);
	
	
}