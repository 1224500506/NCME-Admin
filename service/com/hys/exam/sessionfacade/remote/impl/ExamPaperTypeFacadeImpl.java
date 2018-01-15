package com.hys.exam.sessionfacade.remote.impl;

import java.util.List;

import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.exam.service.remote.ExamPaperTypeManage;
import com.hys.exam.sessionfacade.remote.ExamPaperTypeFacade;
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
public class ExamPaperTypeFacadeImpl extends BaseSessionFacadeImpl implements
		ExamPaperTypeFacade {

	private ExamPaperTypeManage remoteExamPaperTypeManage;
	
	public ExamPaperTypeManage getRemoteExamPaperTypeManage() {
		return remoteExamPaperTypeManage;
	}

	public void setRemoteExamPaperTypeManage(
			ExamPaperTypeManage remoteExamPaperTypeManage) {
		this.remoteExamPaperTypeManage = remoteExamPaperTypeManage;
	}

	@Override
	public ExamPaperType addExamPaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		try{
			return remoteExamPaperTypeManage.addExamPaperType(key,ptype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean deleteExamPaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			remoteExamPaperTypeManage.deleteExamPaperType(key,ptype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public ExamPaperType getExamPaperTypeById(String key, Long id)
			throws FrameworkRuntimeException {
		try{
			return remoteExamPaperTypeManage.getExamPaperTypeById(key,id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamReturnPaperType getExamPaperTypeListByParentId(String key,
			ExamPaperTypeQuery query) throws FrameworkRuntimeException {
		try{
			return remoteExamPaperTypeManage.getExamPaperTypeListByParentId(key,query);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListById(String key,
			Integer[] idArr) throws FrameworkRuntimeException {
		try{
			return remoteExamPaperTypeManage.getExamPaperTypeRootListById(key,idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(String key,
			Integer[] idArr) throws FrameworkRuntimeException {
		try{
			return remoteExamPaperTypeManage.getExamPaperTypeRootListBySysId(key,idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamPaperType updateExamPaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		try{
			return remoteExamPaperTypeManage.updateExamPaperType(key,ptype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean updateMovePaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			remoteExamPaperTypeManage.updateMovePaperType(key,ptype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

}
