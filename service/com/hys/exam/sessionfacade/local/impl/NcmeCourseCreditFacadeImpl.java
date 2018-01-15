package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.service.local.NcmeCourseCreditManage;
import com.hys.exam.sessionfacade.local.NcmeCourseCreditFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseCreditFacadeImpl extends BaseSessionFacadeImpl implements
		NcmeCourseCreditFacade {

	private NcmeCourseCreditManage ncmeCourseCreditManage;

	public void setNcmeCourseCreditManage(
			NcmeCourseCreditManage ncmeCourseCreditManage) {
		this.ncmeCourseCreditManage = ncmeCourseCreditManage;
	}

	@Override
	public void getNcmeCourseCreditList(Page<NcmeCourseCredit> page,
			NcmeCourseCredit ncmeCourseCredit) {
		try {
			ncmeCourseCreditManage.getNcmeCourseCreditList(page, ncmeCourseCredit);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int addNcmeCourseCredit(NcmeCourseCredit n) {
		try {
			return ncmeCourseCreditManage.addNcmeCourseCredit(n);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int deleteNcmeCourseCredit(NcmeCourseCredit n) {
		try {
			return ncmeCourseCreditManage.deleteNcmeCourseCredit(n);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void addNcmeCourseCreditList(List<NcmeCourseCredit> list) {
		try {
			ncmeCourseCreditManage.addNcmeCourseCreditList(list);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void deleteNcmeCourseCreditList(List<NcmeCourseCredit> list) {
		try {
			ncmeCourseCreditManage.deleteNcmeCourseCreditList(list);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
}
