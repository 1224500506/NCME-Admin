package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemRole;
import com.hys.exam.service.local.SystemRoleManage;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 facadeImpl
 * 
 * 说明:
 */
public class SystemRoleFacadeImpl extends BaseSessionFacadeImpl implements SystemRoleFacade {
	
	private SystemRoleManage systemRoleManage ;

	public void setSystemRoleManage(SystemRoleManage systemRoleManage) {
		this.systemRoleManage = systemRoleManage;
	}
	
	@Override  
	public List<SystemRole> getListByItem(SystemRole item){
		try {
			return systemRoleManage.getListByItem(item) ;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override  
	public List<SystemRole> getListByItem(SystemRole item,Page<SystemRole> page){
		try {
			return systemRoleManage.getListByItem(item,page) ;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public void querySystemRoleList(Page<SystemRole> page,
			SystemRole item) {
		try {
			systemRoleManage.querySystemRoleList(page, item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}

	}

	@Override
	public Integer save(SystemRole item) {
		try {
			return systemRoleManage.save(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
		
	}
	
	/**
	 * 添加角色的时候直接在system_role_resource中添加一条数据，保证所有角色和用户关联后都能登录
	 * @param id
	 * @return
	 */
	public Integer saveRoleResourceRelation(int id){
		try {
			return systemRoleManage.saveRoleResourceRelation(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override
	public SystemRole getItemById(Long id) {
		try {
			return systemRoleManage.getItemById(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int update(SystemRole item) {
		try {
			return systemRoleManage.update(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override
	public int deleteList(long[] ids,String idColName) {
		try {
			return systemRoleManage.deleteList(ids,idColName);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int delete(long id, String idColName) {
		return systemRoleManage.delete(id,idColName);
	}
}