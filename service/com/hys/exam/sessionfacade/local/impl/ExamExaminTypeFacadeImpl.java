package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.service.local.ExamExaminTypeManage;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-10
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminTypeFacadeImpl extends BaseSessionFacadeImpl implements
		ExamExaminTypeFacade {
	
	private ExamExaminTypeManage localExamExaminTypeManage;
	
	public ExamExaminTypeManage getLocalExamExaminTypeManage() {
		return localExamExaminTypeManage;
	}

	public void setLocalExamExaminTypeManage(
			ExamExaminTypeManage localExamExaminTypeManage) {
		this.localExamExaminTypeManage = localExamExaminTypeManage;
	}

	@Override
	public ExamExaminType addExamExaminType(ExamExaminType etype)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.addExamExaminType(etype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteExamExaminTypeById(ExamExaminType etype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			localExamExaminTypeManage.deleteExamExaminTypeById(etype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
//			throw new FrameworkRuntimeException(ErrorCode.ec00000);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public ExamExaminType getExamExaminTypeById(Long id)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.getExamExaminTypeById(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(Integer[] id)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.getExamExaminTypeRootBySysId(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamExaminType updateExamExaminType(ExamExaminType etype)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.updateExamExaminType(etype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamReturnExaminType getExamExaminTypeListByParentId(
			ExamExaminTypeQuery query) throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.getExamExaminTypeListByParentId(query);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean updateMoveExaminType(ExamExaminType etype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			localExamExaminTypeManage.updateMoveExaminType(etype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(Integer[] id)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.getExamExaminTypeRootById(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootListByChildId(Long id)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.getExamExaminTypeRootListByChildId(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean deleteExamExaminTypeList(List<ExamExaminType> list)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminTypeManage.deleteExamExaminTypeList(list);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

}
