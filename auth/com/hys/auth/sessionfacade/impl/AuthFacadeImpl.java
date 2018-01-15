package com.hys.auth.sessionfacade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.hys.auth.dto.ResourceDTO;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.model.HysResources;
import com.hys.auth.model.RoleResource;
import com.hys.auth.model.HysRoles;
import com.hys.auth.model.HysUsers;
import com.hys.auth.service.ResourceManage;
import com.hys.auth.service.RolesManage;
import com.hys.auth.service.UsersManage;
import com.hys.auth.sessionfacade.AuthFacade;
import com.hys.auth.util.PageList;
import com.hys.exam.model.HysUserRoleProp;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * Facade
 * 
 * @author zdz
 */
public class AuthFacadeImpl extends BaseSessionFacadeImpl implements AuthFacade {
	
	private UsersManage usersManage;
	private RolesManage rolesManage;
	private ResourceManage resourceManage;

	public void setRolesManage(RolesManage rolesManage) {
		this.rolesManage = rolesManage;
	}

	public void setResourceManage(ResourceManage resourceManage) {
		this.resourceManage = resourceManage;
	}


	public int deleteUsers(Integer id) throws FrameworkRuntimeException {
		if (id == null) {
			return 0;
		}
		return usersManage.delete(id);
	}

	public HysUsers getUsers(Integer id) throws FrameworkRuntimeException {
		if (id == null) {
			return null;
		}
		HysUsers user = null;
		try {
			user = this.usersManage.get(id);
		} catch (Exception e) {
			logger.error("不存在该数据！", e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
		return user;
	}

	public List<HysUsers> getUsersList(String loginName, PageList pageList) throws FrameworkRuntimeException {
		return this.usersManage.getUsersWithPagination(loginName, pageList);
	}

	public List<HysUsers> getSearchList(List<String> list, PageList pageList) throws FrameworkRuntimeException {
		return this.usersManage.getSearchList(list, pageList);
	}

	public Long saveUsers(HysUsers users) throws FrameworkRuntimeException {
		if (users == null) {
			return 0L;
		}
		return this.usersManage.save(users);
	}

	
	public void setUsersManage(UsersManage usersManage) {
		this.usersManage = usersManage;
	}

	
	public int updatePassword(HysUsers users) throws FrameworkRuntimeException {
		if (users == null) {
			return 0;
		}
		return this.usersManage.updatePassword(users);
	}

	public int delete(Integer id) throws DataAccessException {
		return usersManage.delete(id);
	}

	public int updateUsers(HysUsers users) throws FrameworkRuntimeException {
		if (users == null) {
			return 0;
		}
		return this.usersManage.updateUsers(users);
	}

	public HysUsers valid(String userName, String passWord) {
		return this.usersManage.valid(userName, passWord);
	}

	public List<HysRoles> getRolesList() throws FrameworkRuntimeException {
		return this.rolesManage.getListAll();
	}
	
	public List<HysDataDetail> getRolesDataList() throws FrameworkRuntimeException {
		return this.rolesManage.getRolesDataList();
	}

	public Long saveResource(HysResources resource) throws FrameworkRuntimeException {
		if (resource == null) {
			return null;
		}
		return this.resourceManage.save(resource);
	}

	public Long saveRoles(HysRoles roles) throws FrameworkRuntimeException {
		if (roles == null) {
			return 0L;
		}
		return this.rolesManage.save(roles);
	}
	
	public Long saveRolesData(HysDataRoles dataRoles) throws FrameworkRuntimeException {
		if (dataRoles == null) {
			return 0L;
		}
		return this.rolesManage.saveData(dataRoles);
	}
	
	public Long updateRolesData(HysDataRoles dataRoles) throws FrameworkRuntimeException {
		if (dataRoles == null) {
			return 0L;
		}
		return this.rolesManage.updateData(dataRoles);
	}

	

	public Integer deleteRoles(Integer id) throws FrameworkRuntimeException {
		return this.rolesManage.delete(id);
	}
	
	public boolean deleteRo(Integer id) throws FrameworkRuntimeException {
		return this.rolesManage.deleteRow(id);
	}
	

	public List<ResourceDTO> findResources() throws FrameworkRuntimeException {
		return this.resourceManage.findResources();
	}

	public List<HysResources> getAllResource(PageList pageList) throws FrameworkRuntimeException {
		return this.resourceManage.getListWithPagination(new String[] { "*" }, pageList);
	}

	public HysResources getResource(String value) throws FrameworkRuntimeException {
		return this.resourceManage.getResource(value);
	}

	public int saveRelateRoleAndResource(String roleIds, String resourceId) throws FrameworkRuntimeException {
		return this.resourceManage.saveRelateRoleAndResource(roleIds, resourceId);
	}

	public List<HysRoles> getRoleByResourceId(Integer resourceId) throws FrameworkRuntimeException {
		return this.rolesManage.getRoleByResourceId(resourceId);
	}

	public HysResources getResource(Integer id) throws FrameworkRuntimeException {
		return this.resourceManage.get(id);
	}

	public Integer updateResource(HysResources resource) throws FrameworkRuntimeException {
		return this.resourceManage.update(resource);
	}

	public int deleteResource(Integer resourceId) throws FrameworkRuntimeException {
		return this.resourceManage.delete(resourceId);
	}


	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[] { "model/applicationContext_jndi.xml", "model/applicationContext_dao.xml",
				"service/applicationContext_service.xml", "service/applicationContext_transaction.xml" });
		AuthFacade facade = (AuthFacade) ctx.getBean("facade");
		PageList pagelist = new PageList();
		pagelist.setSortDirection(SortOrderEnum.ASCENDING);
		System.out.println(facade.getAllResource(pagelist));
		RoleResource rr = new RoleResource();
		rr.setRoleId(1);
		rr.setResourceId(12);
	}
	
	public void saveUserRoles(HysUserRoleProp role)
	{
		usersManage.saveUserRoles(role);
	}
	
	public void deleteUserRoles(Long userId)
	{
		usersManage.deleteUserRoles(userId);
	}
	public List<HysUserRoleProp> getUserRoleList(Long userid)
	{
		return usersManage.getUserRoleList(userid);
	}
	
}
