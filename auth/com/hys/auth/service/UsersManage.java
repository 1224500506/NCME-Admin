package com.hys.auth.service;

import java.util.List;

import com.hys.auth.model.HysUsers;
import com.hys.auth.util.PageList;
import com.hys.exam.model.HysUserRoleProp;

/**
 * 用户Manage
 * 
 * @author zdz
 * 
 */
public interface UsersManage extends AbstractManage {
	
	public Long save(HysUsers users);

	/**
	 * 更新用户
	 * 
	 * @param users
	 * @return
	 */
	public int updateUsers(HysUsers users);

	/**
	 * 修改密码
	 * 
	 * @param users
	 * @return
	 */
	public int updatePassword(HysUsers users);

	/**
	 * 校验用户名和密码
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public HysUsers valid(String userName, String passWord);

	/**
	 * 查询用户分页
	 * 
	 * @param loginName
	 * @param pageList
	 * @return
	 */
	public List<HysUsers> getUsersWithPagination(String loginName, PageList pageList);
	
	/**
	 * 取得系统用户
	 * @param list
	 * @param pageList
	 * @return
	 */
	public List<HysUsers> getSearchList(List<String> list, PageList pageList);
	
	public int delete(Integer id);
	public void saveUserRoles(HysUserRoleProp role);
	public void deleteUserRoles(Long userId);
	public List<HysUserRoleProp> getUserRoleList(Long userid);
}
