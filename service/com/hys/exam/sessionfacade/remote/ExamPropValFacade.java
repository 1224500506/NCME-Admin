package com.hys.exam.sessionfacade.remote;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.BaseSessionFacade;

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
public interface ExamPropValFacade extends BaseSessionFacade {
	
	/**
	 * 无关联属性值
	 * @return
	 */
	public Map<String,List<ExamPropVal>> getSysPropValBySysId(String key) throws FrameworkRuntimeException;
	
	/**
	 * 系统基本属性
	 * @param sysId
	 * @param type
	 * @return
	 */
	public List<ExamPropValTemp> getBasePropVal(String key,Integer sysId,Integer type)throws FrameworkRuntimeException;
	

}
