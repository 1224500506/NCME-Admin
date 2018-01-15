package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.SystemUserFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 facadeImpl
 * 
 * 说明:
 */
public class SystemUserFacadeImpl extends BaseSessionFacadeImpl implements SystemUserFacade {
	
	private SystemUserManage systemUserManage ;

	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}
	
	@Override  
	public List<SystemUser> getListByItem(SystemUser item){
		try {
			return systemUserManage.getListByItem(item) ;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item) {
		try {
			systemUserManage.querySystemUserList(page, item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}

	}

	@Override
	public Integer save(SystemUser item) {
		try {
			return systemUserManage.save(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
		
	}
	
	@Override
	public SystemUser getItemById(Long id) {
		try {
			return systemUserManage.getItemById(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int update(SystemUser item) {
		try {
			return systemUserManage.update(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override
	public int deleteList(long[] ids,String idColName) {
		try {
			return systemUserManage.deleteList(ids,idColName);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int delete(long id, String idColName) {
		return systemUserManage.delete(id,idColName);
	}
}