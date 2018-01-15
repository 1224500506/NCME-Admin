package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.NcmeCreditType;
import com.hys.exam.service.local.NcmeCreditTypeManage;
import com.hys.exam.sessionfacade.local.NcmeCreditTypeFacade;
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
public class NcmeCreditTypeFacadeImpl extends BaseSessionFacadeImpl implements
		NcmeCreditTypeFacade {

	private NcmeCreditTypeManage ncmeCreditTypeManage;

	public void setNcmeCreditTypeManage(NcmeCreditTypeManage ncmeCreditTypeManage) {
		this.ncmeCreditTypeManage = ncmeCreditTypeManage;
	}

	@Override
	public List<NcmeCreditType> getNcmeCreditTypeList() {
		try {
			return ncmeCreditTypeManage.getNcmeCreditTypeList();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
}
