package com.hys.auth.service;

import java.util.List;

import com.hys.auth.dto.ResourceDTO;
import com.hys.auth.model.HysResources;

/**
 * 链接Manage
 * 
 * @author zdz
 * 
 */
public interface ResourceManage extends AbstractManage {
	
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
	public int saveRelateRoleAndResource(String roleIds, String resourceId);

	/**
	 * 删除资源和角色关联
	 * 
	 * @param resourceId
	 * @return
	 */
	public int deleteRoleAndResource(Integer resourceId);
}
