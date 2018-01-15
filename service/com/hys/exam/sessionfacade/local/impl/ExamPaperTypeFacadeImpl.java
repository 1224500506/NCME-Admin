package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.exam.service.local.ExamPaperTypeManage;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
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
public class ExamPaperTypeFacadeImpl extends BaseSessionFacadeImpl implements
		ExamPaperTypeFacade {

	private ExamPaperTypeManage localExamPaperTypeManage;
	
	public ExamPaperTypeManage getLocalExamPaperTypeManage() {
		return localExamPaperTypeManage;
	}

	public void setLocalExamPaperTypeManage(
			ExamPaperTypeManage localExamPaperTypeManage) {
		this.localExamPaperTypeManage = localExamPaperTypeManage;
	}

	@Override
	public ExamPaperType addExamPaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		try{
			return localExamPaperTypeManage.addExamPaperType(ptype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteExamPaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			localExamPaperTypeManage.deleteExamPaperType(ptype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
//			throw new FrameworkRuntimeException(ErrorCode.ec00000);
		} finally {
			return flag;
		}
	}
	
	@Override //删除试卷分类
	@SuppressWarnings("finally")
	public boolean deleteExamPaperType(List<Long> delArray)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			localExamPaperTypeManage.deleteExamPaperType(delArray);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public ExamPaperType getExamPaperTypeById(Long id)
			throws FrameworkRuntimeException {
		try{
			return localExamPaperTypeManage.getExamPaperTypeById(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(Integer[] idArr)
			throws FrameworkRuntimeException {
		try{
			return localExamPaperTypeManage.getExamPaperTypeRootListBySysId(idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamPaperType updateExamPaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		try{
			return localExamPaperTypeManage.updateExamPaperType(ptype);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamReturnPaperType getExamPaperTypeListByParentId(
			ExamPaperTypeQuery query) throws FrameworkRuntimeException {
		try{
			return localExamPaperTypeManage.getExamPaperTypeListByParentId(query);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean updateMovePaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try{
			localExamPaperTypeManage.updateMovePaperType(ptype);
			flag = true;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	public List<ExamPaperType> getExamPaperTypeRootListById(Integer[] idArr)
			throws FrameworkRuntimeException {
		try{
			return localExamPaperTypeManage.getExamPaperTypeRootListById(idArr);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListByChildId(Long id)
			throws FrameworkRuntimeException {
		try{
			return localExamPaperTypeManage.getExamPaperTypeRootListByChildId(id);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

}
