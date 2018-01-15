package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.BaseSessionFacade;

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
public interface ExamOlemPropFacade extends BaseSessionFacade {
	/**
	 * 添加三基属性
	 * @param prop
	 */
	public boolean addExamOlemProp(ExamOlemProp prop) throws FrameworkRuntimeException;
	
	/**
	 * 修改三基属性
	 * @param prop
	 */
	public boolean updateExamOlemProp(ExamOlemProp prop) throws FrameworkRuntimeException;
	
	/**
	 * 查三基属性
	 * @param prop
	 * @return
	 */
	public ExamOlemProp getExamOlemPropById(ExamOlemProp prop) throws FrameworkRuntimeException;
	
	/**
	 * 删除属性
	 * @param prop
	 */
	public boolean deleteExamOlemPropById(ExamOlemProp prop) throws FrameworkRuntimeException;
	
	
	/**
	 * 查询三基属性列表
	 * @param query
	 * @return
	 */
	public ExamReturnOlemProp getExamOlemPropList(ExamOlemPropQuery query) throws FrameworkRuntimeException;
	
	/**
	 * 增加三基属性与基本属性的关系
	 * @param olemPropId  三基属性
	 * @param prop 三基属性与基本属性
	 * @param BasePropType 基本属性类型
	 * @return 0:成功,1:基本属性类型错误,2:写入失败
	 * @throws FrameworkRuntimeException
	 */
	public int addExamOlemPropRefBaseProp(Long olemPropId,List<ExamOlemPropRefBaseProp> prop,int BasePropType) throws FrameworkRuntimeException;
	
	/**
	 * 删除三基属性与基本属性的关系
	 * @param prop
	 */
	public boolean deleteExamOlemPropRefBaseProp(ExamOlemPropRefBaseProp prop) throws FrameworkRuntimeException;
	
	/**
	 * 取对应基本属性
	 * @param id
	 * @return
	 */
	public List<ExamOlemPropRefBaseProp> getExamOlemPropRefBasePropList(Long id) throws FrameworkRuntimeException;
	
	
	public Long getId() throws FrameworkRuntimeException;
	/**
	 * 导入三基属性
	 * @param olemPropList
	 * @param olemBasePropList
	 */
	public void addImportExamOlemProp(List<ExamOlemProp> olemPropList,List<ExamOlemPropRefBaseProp> olemBasePropList) throws FrameworkRuntimeException;
}
