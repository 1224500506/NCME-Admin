package com.hys.auth.dao;

import java.util.List;

import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.model.HysRoles;

/**
 * 权限DataAccessObject
 * 
 * @author zdz
 * 
 */
public interface RolesDAO extends AbstractDAO {
	
	//保存角色
	public Long save(HysRoles roles);
	
	//保存每一角色的权限
	public Long saveData(HysDataRoles dataRoles);
	
	//更新每一角色的权限
	public Long updateData(HysDataRoles dataRoles);

	/**
	 * 更新角色
	 * 
	 * @param roles
	 * @return
	 */
	public int update(HysRoles roles);

	/**
	 * 根据资源Id获取角色
	 * 
	 * @param resourceId
	 * @return
	 */
	public List<HysRoles> getRoleByResourceId(Integer resourceId);

	/**
	 *取得角色数据 
	 * @return
	 */
	public List<HysDataDetail> getRolesDataList();
}
