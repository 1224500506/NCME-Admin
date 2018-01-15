package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.exam.service.local.ExamOlemPropManage;
import com.hys.exam.sessionfacade.local.ExamOlemPropFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemPropFacadeImpl extends BaseSessionFacadeImpl implements
		ExamOlemPropFacade {

	
	private ExamOlemPropManage localExamOlemPropManage;
	
	public ExamOlemPropManage getLocalExamOlemPropManage() {
		return localExamOlemPropManage;
	}

	public void setLocalExamOlemPropManage(
			ExamOlemPropManage localExamOlemPropManage) {
		this.localExamOlemPropManage = localExamOlemPropManage;
	}

	@SuppressWarnings("finally")
	public boolean addExamOlemProp(ExamOlemProp prop)
			throws FrameworkRuntimeException {
		
		boolean flag = false;
		try {
			localExamOlemPropManage.addExamOlemProp(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}		
	}

	@Override
	public boolean deleteExamOlemPropById(ExamOlemProp prop)
			throws FrameworkRuntimeException {
		try{
			return localExamOlemPropManage.deleteExamOlemPropById(prop);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
		
	}

	@Override
	public ExamOlemProp getExamOlemPropById(ExamOlemProp prop)
			throws FrameworkRuntimeException {
		try{
			return localExamOlemPropManage.getExamOlemPropById(prop);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
		
	}

	@Override
	public ExamReturnOlemProp getExamOlemPropList(ExamOlemPropQuery query)
			throws FrameworkRuntimeException {
		try{
			return localExamOlemPropManage.getExamOlemPropList(query);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
		
	}

	@SuppressWarnings("finally")
	public boolean updateExamOlemProp(ExamOlemProp prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamOlemPropManage.updateExamOlemProp(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
		
	}

	@SuppressWarnings("finally")
	public boolean deleteExamOlemPropRefBaseProp(ExamOlemPropRefBaseProp prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamOlemPropManage.deleteExamOlemPropRefBaseProp(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public List<ExamOlemPropRefBaseProp> getExamOlemPropRefBasePropList(Long id)
			throws FrameworkRuntimeException {
		return localExamOlemPropManage.getExamOlemPropRefBasePropList(id);
	}

	@SuppressWarnings("finally")
	public int addExamOlemPropRefBaseProp(Long olemPropId,
			List<ExamOlemPropRefBaseProp> prop, int BasePropType)
			throws FrameworkRuntimeException {
		int flag = 0;
		try {
			int type = localExamOlemPropManage.getExamOlemPropType(olemPropId);
			if(type==0 || type==BasePropType){
				localExamOlemPropManage.addExamOlemPropRefBaseProp(prop);
				flag=0;
			}else{
				flag=1;
			}
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = 2;
		} finally {
			return flag;
		}
	}

	@Override
	public void addImportExamOlemProp(List<ExamOlemProp> olemPropList,
			List<ExamOlemPropRefBaseProp> olemBasePropList)
			throws FrameworkRuntimeException {
		try{
			localExamOlemPropManage.addImportExamOlemProp(olemPropList, olemBasePropList);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public Long getId() throws FrameworkRuntimeException {
		try{
			return localExamOlemPropManage.getId();
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

}
