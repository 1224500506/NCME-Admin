package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemClient;
import com.hys.exam.model.SystemOrg;
import com.hys.exam.service.local.SystemClientManage;
import com.hys.exam.service.local.SystemOrgManage;
import com.hys.exam.sessionfacade.local.SystemClientFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemClientFacadeImpl extends BaseSessionFacadeImpl implements
		SystemClientFacade {

	private SystemClientManage systemClientManage;

	public void setSystemClientManage(SystemClientManage systemClientManage) {
		this.systemClientManage = systemClientManage;
	}
	
	public List<SystemClient> getListByItem(SystemClient item){
		try {
			return this.systemClientManage.getListByItem(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void getSystemClientList(Page<SystemClient> page,
			SystemClient item) {
		try {
			this.systemClientManage.getSystemClientList(page, item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}

	}

	@Override
	public Integer save(SystemClient item) {
		try {
			return this.systemClientManage.save(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
		
	}
	
	@Override
	public SystemClient getItemById(Long id) {
		try {
			return this.systemClientManage.get(id.intValue());
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int update(SystemClient item) {
		try {
			return this.systemClientManage.update(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override
	public int deleteList(long[] ids,String idColName) {
		try {
			return this.systemClientManage.deleteList(ids,idColName);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	public int delete(long id, String idColName){
		try {
			return this.systemClientManage.delete(id,idColName);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}


}
