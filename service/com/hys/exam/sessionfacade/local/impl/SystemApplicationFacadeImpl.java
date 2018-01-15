package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.model.SystemClient;
import com.hys.exam.service.local.SystemApplicationManage;
import com.hys.exam.sessionfacade.local.SystemApplicationFacade;
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
public class SystemApplicationFacadeImpl extends BaseSessionFacadeImpl implements SystemApplicationFacade {
	
	private SystemApplicationManage systemApplicationManage ;

	public void setSystemApplicationManage(SystemApplicationManage systemApplicationManage) {
		this.systemApplicationManage = systemApplicationManage;
	}
	
	@Override //取得所有站点信息
	public List<SystemApplication> getListByItem(SystemApplication item){
		try {
			return systemApplicationManage.getListByItem(item) ;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public void querySystemApplicationList(Page<SystemApplication> page,
			SystemApplication item) {
		try {
			systemApplicationManage.querySystemApplicationList(page, item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}

	}

	@Override
	public Integer save(SystemApplication item) {
		try {
			return systemApplicationManage.save(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
		
	}
	
	@Override
	public SystemApplication getItemById(Long id) {
		try {
			return systemApplicationManage.get(id.intValue());
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int update(SystemApplication item) {
		try {
			return systemApplicationManage.update(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override
	public int deleteList(long[] ids,String idColName) {
		try {
			return systemApplicationManage.deleteList(ids,idColName);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
}