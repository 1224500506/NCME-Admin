package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemRole;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 facade
 * 
 * 说明:
 */
public interface SystemRoleFacade {

	public List<SystemRole> getListByItem(SystemRole item);
	
	public List<SystemRole> getListByItem(SystemRole item,Page<SystemRole> page);

	public void querySystemRoleList(Page<SystemRole> page,
			SystemRole item);

	public Integer save(SystemRole item);
	
	/**
	 * 添加角色的时候直接在system_role_resource中添加一条数据，保证所有角色和用户关联后都能登录
	 * @param id
	 * @return
	 */
	public Integer saveRoleResourceRelation(int id);

	public SystemRole getItemById(Long id);

	public int update(SystemRole item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
}