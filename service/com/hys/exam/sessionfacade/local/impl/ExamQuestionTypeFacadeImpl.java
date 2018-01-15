package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.exam.service.local.ExamQuestionTypeManage;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-13
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionTypeFacadeImpl extends BaseSessionFacadeImpl implements
		ExamQuestionTypeFacade {

	
	private ExamQuestionTypeManage localExamQuestionTypeManage;
	
	public ExamQuestionTypeManage getLocalExamQuestionTypeManage() {
		return localExamQuestionTypeManage;
	}


	public void setLocalExamQuestionTypeManage(
			ExamQuestionTypeManage localExamQuestionTypeManage) {
		this.localExamQuestionTypeManage = localExamQuestionTypeManage;
	}


	public List<ExamQuestionType> getQuestionTypeRootBySysId(Integer[] idArr)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.getQuestionTypeRootBySysId(idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	

	@Override
	public List<ExamQuestionType> getQuestionTypeRootById(Integer[] idArr)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.getQuestionTypeRootById(idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public List<ExamQuestionType> getSubSysTypeByTypeId(Long[] idArr)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.getSubSysTypeByTypeId(idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamQuestionType addExamQuestType(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.addExamQuestType(qtype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteExamQuestTypeById(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			localExamQuestionTypeManage.deleteExamQuestTypeById(qtype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public ExamQuestionType getExamQuestionTypeById(Long id)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.getExamQuestionTypeById(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}



	@Override
	public ExamQuestionType updateExamQuestTypeById(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.updateExamQuestTypeById(qtype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public ExamReturnQuestionType getExamQuestionTypeListByParentId(
			ExamQuestionTypeQuery query) throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.getExamQuestionTypeListByParentId(query);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean updateMoveQuestionType(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			localExamQuestionTypeManage.updateMoveQuestionType(qtype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}		
	}


	@Override
	public List<ExamQuestionType> getExamQuestionTypeRootListByChildId(Long id)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionTypeManage.getExamQuestionTypeRootListByChildId(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
}
