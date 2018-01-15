package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.NcmeCourseType;
import com.hys.exam.service.local.NcmeCourseTypeManage;
import com.hys.exam.sessionfacade.local.NcmeCourseTypeFacade;
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
public class NcmeCourseTypeFacadeImpl extends BaseSessionFacadeImpl implements
		NcmeCourseTypeFacade {

	private NcmeCourseTypeManage ncmeCourseTypeManage;

	public void setNcmeCourseTypeManage(
			NcmeCourseTypeManage ncmeCourseTypeManage) {
		this.ncmeCourseTypeManage = ncmeCourseTypeManage;
	}

	@Override
	public List<NcmeCourseType> getNcmeCourseTypeList() {
		try{
			return ncmeCourseTypeManage.getNcmeCourseTypeList();
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

}
