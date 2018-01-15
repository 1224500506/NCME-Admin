package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.SystemOrg;
import com.hys.exam.service.local.SystemOrgManage;
import com.hys.exam.sessionfacade.local.SystemOrgFacade;
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
public class SystemOrgFacadeImpl extends BaseSessionFacadeImpl implements
		SystemOrgFacade {

	private SystemOrgManage systemOrgManage;

	public void setSystemOrgManage(SystemOrgManage systemOrgManage) {
		this.systemOrgManage = systemOrgManage;
	}
	

	@Override
	public List<SystemOrg> getListAll() {
		try {
			return systemOrgManage.getListAll();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}

	}


}
