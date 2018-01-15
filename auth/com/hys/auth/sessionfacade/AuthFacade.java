package com.hys.auth.sessionfacade;

import java.util.List;

import com.hys.auth.dto.ResourceDTO;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.model.HysResources;
import com.hys.auth.model.HysRoles;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.PageList;
import com.hys.exam.model.HysUserRoleProp;
import com.hys.framework.exception.FrameworkRuntimeException;

/**
 * 乡医Facade
 * 
 * @author zdz
 */
public interface AuthFacade {

	/* ----------------- Users here ----------------- */

	/**
	 * 获取用户对象
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public HysUsers getUsers(Integer id) throws FrameworkRuntimeException;

	/**
	 * 保存用户对象
	 * 
	 * @param users
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public Long saveUsers(HysUsers users) throws FrameworkRuntimeException;

	/**
	 * 更新用户对象
	 * 
	 * @param users
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public int updateUsers(HysUsers users) throws FrameworkRuntimeException;

	/**
	 * 更新密码
	 * 
	 * @param users
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public int updatePassword(HysUsers users) throws FrameworkRuntimeException;

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public int deleteUsers(Integer id) throws FrameworkRuntimeException;

	/**
	 * 获取用户列表
	 * 
	 * @param loginName
	 * @param page
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<HysUsers> getUsersList(String loginName, PageList page) throws FrameworkRuntimeException;
	
	public List<HysUsers> getSearchList(List<String> list, PageList page) throws FrameworkRuntimeException;

	/**
	 * 用户验证
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public HysUsers valid(String userName, String passWord) throws FrameworkRuntimeException;



	/* ----------------- Roles here ----------------- */

	/**
	 * 保存角色
	 * 
	 * @param roles
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public Long saveRoles(HysRoles roles) throws FrameworkRuntimeException;
	
	public Long saveRolesData(HysDataRoles dataRoles) throws FrameworkRuntimeException;
	
	public Long updateRolesData(HysDataRoles dataRoles) throws FrameworkRuntimeException;

	/**
	 * 获取角色列表
	 * 
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<HysRoles> getRolesList() throws FrameworkRuntimeException;
	
	public List<HysDataDetail> getRolesDataList() throws FrameworkRuntimeException;

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public Integer deleteRoles(Integer id) throws FrameworkRuntimeException;
	public boolean deleteRo(Integer id) throws FrameworkRuntimeException;


	/**
	 * 根据资源ID获取角色列表
	 * 
	 * @param resourceId
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<HysRoles> getRoleByResourceId(Integer resourceId) throws FrameworkRuntimeException;

	/* ----------------- Resource here ----------------- */

	/**
	 * 保存资源
	 * 
	 * @param resource
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public Long saveResource(HysResources resource) throws FrameworkRuntimeException;

	/**
	 * 更新资源
	 * 
	 * @param resource
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public Integer updateResource(HysResources resource) throws FrameworkRuntimeException;

	/**
	 * 查找资源列表
	 * 
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ResourceDTO> findResources() throws FrameworkRuntimeException;

	/**
	 * 获取所有资源列表
	 * 
	 * @param pageList
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<HysResources> getAllResource(PageList pageList) throws FrameworkRuntimeException;

	/**
	 * 获取资源对象，通过资源链接
	 * 
	 * @param value
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public HysResources getResource(String value) throws FrameworkRuntimeException;

	/**
	 * 获取资源对象，通过资源ID
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public HysResources getResource(Integer id) throws FrameworkRuntimeException;

	/**
	 * 保存资源和角色的关联
	 * 
	 * @param roleIds
	 * @param resourceId
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public int saveRelateRoleAndResource(String roleIds, String resourceId) throws FrameworkRuntimeException;

	/**
	 * 删除资源
	 * 
	 * @param resourceId
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public int deleteResource(Integer resourceId) throws FrameworkRuntimeException;
	public void saveUserRoles(HysUserRoleProp role);
	public void deleteUserRoles(Long userId);
	public List<HysUserRoleProp> getUserRoleList(Long userid);
}
