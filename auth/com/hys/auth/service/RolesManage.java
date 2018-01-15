package com.hys.auth.service;

import java.util.List;

import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.model.HysRoles;
import com.hys.framework.exception.FrameworkRuntimeException;

/**
 * 权限Manage
 * 
 * @author zdz
 * 
 */
public interface RolesManage extends AbstractManage {
	
	//保存角色
	public Long save(HysRoles roles);
	
	//保存角色数据
	public Long saveData(HysDataRoles dataRoles);
	
	//更新角色数据
	public Long updateData(HysDataRoles dataRoles);
	
	public int delete(Integer id);
	/**
	 * 更新角色
	 * 
	 * @param roles
	 * @return
	 */
	public boolean deleteRow(Integer id);
	public int update(HysRoles roles);
	
	/**
	 * 根据资源Id获取角色
	 * 
	 * @param resourceId
	 * @return
	 */
	public List<HysRoles> getRoleByResourceId(Integer resourceId);
	
	//取得角色数据
	public List<HysDataDetail> getRolesDataList();
}
