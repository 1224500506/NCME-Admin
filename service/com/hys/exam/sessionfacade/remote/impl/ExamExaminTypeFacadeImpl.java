package com.hys.exam.sessionfacade.remote.impl;

import java.util.List;

import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.service.remote.ExamExaminTypeManage;
import com.hys.exam.sessionfacade.remote.ExamExaminTypeFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 13, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminTypeFacadeImpl extends BaseSessionFacadeImpl implements
		ExamExaminTypeFacade {

	private ExamExaminTypeManage remoteExamExaminTypeManage;
	
	public ExamExaminTypeManage getRemoteExamExaminTypeManage() {
		return remoteExamExaminTypeManage;
	}

	public void setRemoteExamExaminTypeManage(
			ExamExaminTypeManage remoteExamExaminTypeManage) {
		this.remoteExamExaminTypeManage = remoteExamExaminTypeManage;
	}

	@Override
	public ExamExaminType addExamExaminType(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		try{
			return remoteExamExaminTypeManage.addExamExaminType(key,etype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean deleteExamExaminTypeById(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			remoteExamExaminTypeManage.deleteExamExaminTypeById(key,etype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public ExamExaminType getExamExaminTypeById(String key, Long id)
			throws FrameworkRuntimeException {
		try{
			return remoteExamExaminTypeManage.getExamExaminTypeById(key,id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamReturnExaminType getExamExaminTypeListByParentId(String key,
			ExamExaminTypeQuery query) throws FrameworkRuntimeException {
		try{
			return remoteExamExaminTypeManage.getExamExaminTypeListByParentId(key,query);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(String key,
			Integer[] id) throws FrameworkRuntimeException {
		try{
			return remoteExamExaminTypeManage.getExamExaminTypeRootById(key,id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(String key,
			Integer[] id) throws FrameworkRuntimeException {
		try{
			return remoteExamExaminTypeManage.getExamExaminTypeRootBySysId(key,id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamExaminType updateExamExaminType(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		try{
			return remoteExamExaminTypeManage.updateExamExaminType(key,etype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean updateMoveExaminType(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			remoteExamExaminTypeManage.updateMoveExaminType(key,etype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

}
