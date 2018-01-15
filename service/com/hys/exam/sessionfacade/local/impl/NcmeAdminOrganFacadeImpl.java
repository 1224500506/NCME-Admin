package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.NcmeAdminOrgan;
import com.hys.exam.service.local.NcmeAdminOrganManage;
import com.hys.exam.sessionfacade.local.NcmeAdminOrganFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeAdminOrganFacadeImpl extends BaseSessionFacadeImpl implements NcmeAdminOrganFacade {
	private NcmeAdminOrganManage ncmeAdminOrganManage;

	public void setNcmeAdminOrganManage(
			NcmeAdminOrganManage ncmeAdminOrganManage) {
		this.ncmeAdminOrganManage = ncmeAdminOrganManage;
	}

	@Override
	public List<NcmeAdminOrgan> getNcmeAdminOrganList() {
		try {
			return ncmeAdminOrganManage.getNcmeAdminOrganList();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
}
