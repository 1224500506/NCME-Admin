package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.service.local.StudyCoursewareManage;
import com.hys.exam.sessionfacade.local.StudyCoursewareFacade;
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
public class StudyCoursewareFacadeImpl extends BaseSessionFacadeImpl implements
		StudyCoursewareFacade {

	private StudyCoursewareManage studyCoursewareManage;

	public void setStudyCoursewareManage(StudyCoursewareManage studyCoursewareManage) {
		this.studyCoursewareManage = studyCoursewareManage;
	}

	@Override
	public void getStudyCoursewareList(Page<StudyCourseware> page,
			StudyCourseware courseware) {
		try {
			studyCoursewareManage.getStudyCoursewareList(page, courseware);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override //查询课程下的课件信息
	public void getCourseWareByCourse(Page<StudyCourseware> page, StudyCourseware curware){
		try {
			studyCoursewareManage.getCourseWareByCourse(page, curware);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int saveStudyCourseware(StudyCourseware s) {
		try {
			return studyCoursewareManage.saveStudyCourseware(s);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public StudyCourseware getStudyCoursewareById(Long id) {
		try {
			return studyCoursewareManage.getStudyCoursewareById(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void deleteStudyCourseware(List<StudyCourseware> list) {
		try {
			studyCoursewareManage.deleteStudyCourseware(list);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override //根据课程组件ID 查询课件信息
	public StudyCourseware getStudyCoursewareByEleId(Long elementId) {
		try {
			return studyCoursewareManage.getStudyCoursewareByEleId(elementId);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
}
