package com.hys.exam.sessionfacade.remote.impl;

import java.util.List;

import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.exam.service.remote.ExamQuestionTypeManage;
import com.hys.exam.sessionfacade.remote.ExamQuestionTypeFacade;
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
public class ExamQuestionTypeFacadeImpl extends BaseSessionFacadeImpl implements
		ExamQuestionTypeFacade {

	private ExamQuestionTypeManage remoteExamQuestionTypeManage;
	
	
	
	public ExamQuestionTypeManage getRemoteExamQuestionTypeManage() {
		return remoteExamQuestionTypeManage;
	}

	public void setRemoteExamQuestionTypeManage(
			ExamQuestionTypeManage remoteExamQuestionTypeManage) {
		this.remoteExamQuestionTypeManage = remoteExamQuestionTypeManage;
	}

	@Override
	public ExamQuestionType addExamQuestType(String key, ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionTypeManage.addExamQuestType(key,qtype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean deleteExamQuestTypeById(String key, ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			remoteExamQuestionTypeManage.deleteExamQuestTypeById(key,qtype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public ExamQuestionType getExamQuestionTypeById(String key, Long id)
			throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionTypeManage.getExamQuestionTypeById(key,id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(String key,
			ExamQuestionTypeQuery query) throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionTypeManage.getExamQuestionTypeListByParentId(key,query);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestionType> getQuestionTypeRootById(String key,
			Integer[] idArr) throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionTypeManage.getQuestionTypeRootById(key,idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestionType> getQuestionTypeRootBySysId(String key,
			Integer[] idArr) throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionTypeManage.getQuestionTypeRootBySysId(key,idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestionType> getSubSysTypeByTypeId(String key, Long[] idArr)
			throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionTypeManage.getSubSysTypeByTypeId(key,idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamQuestionType updateExamQuestTypeById(String key,
			ExamQuestionType qtype) throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionTypeManage.updateExamQuestTypeById(key,qtype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean updateMoveQuestionType(String key, ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			remoteExamQuestionTypeManage.updateMoveQuestionType(key,qtype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

}
