package com.hys.auth.dao;

import java.util.List;

import com.hys.auth.dto.ResourceDTO;
import com.hys.auth.model.HysResources;
import com.hys.auth.model.RoleResource;

/**
 * 链接DataAccessObject
 * 
 * @author zdz
 * 
 */
public interface ResourceDAO extends AbstractDAO {
	
	/**
	 * 保存资源
	 * @param resource
	 * @return
	 */
	public Long save(HysResources resource);

	/**
	 * 更新资源
	 * 
	 * @param resource
	 * @return
	 */
	public int update(HysResources resource);

	/**
	 * 查询所有资源
	 * 
	 * @return
	 */
	public List<ResourceDTO> findResources();

	/**
	 * 获取资源
	 * 
	 * @param value
	 * @return
	 */
	public HysResources getResource(String value);

	/**
	 * 关联资源和角色
	 * 
	 * @param rr
	 * @return
	 */
	public int relateRoleAndResource(RoleResource rr);

	/**
	 * 删除资源和角色关联
	 * 
	 * @param resourceId
	 * @return
	 */
	public int deleteRoleAndResource(Integer resourceId);

	public int delete(Integer resourceId);

}
