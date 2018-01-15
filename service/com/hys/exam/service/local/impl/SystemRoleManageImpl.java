package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemRoleDAO;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.system.SystemRoleMenuProp;
import com.hys.exam.service.local.SystemRoleManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 manageImpl
 * 
 * 说明:
 */
public class SystemRoleManageImpl extends BaseMangerImpl implements SystemRoleManage {

	private SystemRoleDAO systemRoleDAO ;

	public void setSystemRoleDAO(SystemRoleDAO systemRoleDAO) {
		this.systemRoleDAO = systemRoleDAO;
	}
	
	@Override //取得所有站点信息
	public List<SystemRole> getListByItem(SystemRole item){
		return systemRoleDAO.getListByItem(item) ;
	}
	@Override //取得所有站点信息
	public List<SystemRole> getListByItem(SystemRole item,Page<SystemRole> page){
		return systemRoleDAO.getListByItem(item,page) ;
	}
	
	public void querySystemRoleList(Page<SystemRole> page,
			SystemRole item) {
		systemRoleDAO.querySystemRoleList(page, item);
	}
	
	
	public int update(SystemRole item) {
		return systemRoleDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return systemRoleDAO.deleteList(ids,idColName);
	}

	
	public SystemRole getItemById(Long id) {
		return systemRoleDAO.getItemById(id);
	}

	
	public Integer save(SystemRole item) {
		return systemRoleDAO.save(item);
	}
	
	/**
	 * 添加角色的时候直接在system_role_resource中添加一条数据，保证所有角色和用户关联后都能登录
	 * @param id
	 * @return
	 */
	public Integer saveRoleResourceRelation(int id){
		return systemRoleDAO.saveRoleResourceRelation(id);
	}

	@Override
	public int delete(long id, String idColName) {
		return systemRoleDAO.delete(id,idColName);
	}
	@Override
	public List<SystemRoleMenuProp> getRoleMenuList(Integer roleId)
	{
		return systemRoleDAO.getRoleMenuList(roleId);
	}
	@Override
	public int setRoleMenuProp(Integer roleId, String menuList)
	{
		return systemRoleDAO.setRoleMenuProp(roleId,menuList);
	}

}