package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.system.SystemRoleMenuProp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 dao
 * 
 * 说明:
 */
public interface SystemRoleDAO {
	
	public List<SystemRole> getListByItem(SystemRole item);
	
	public List<SystemRole> getListByItem(SystemRole item,Page<SystemRole> page);

	public void querySystemRoleList(Page<SystemRole> page,
			SystemRole item);

	public Integer save(SystemRole item);

	public SystemRole getItemById(Long id);

	public int update(SystemRole item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
	
	public List<SystemRoleMenuProp> getRoleMenuList(Integer roleId);
	
	public int setRoleMenuProp(Integer roleId, String menuList);

	/**
	 * 添加角色的时候直接在system_role_resource中添加一条数据，保证所有角色和用户关联后都能登录
	 * @param id
	 * @return
	 */
	public Integer saveRoleResourceRelation(int id);
}