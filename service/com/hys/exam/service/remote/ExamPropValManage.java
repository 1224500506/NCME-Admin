package com.hys.exam.service.remote;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-22
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPropValManage {
	
	/**
	 * 无关联属性值
	 * @return
	 */
	public Map<String,List<ExamPropVal>> getSysPropValBySysId(String key) throws Exception;
	
	/**
	 * 系统基本属性
	 * @param sysId
	 * @param type
	 * @return
	 */
	public List<ExamPropValTemp> getBasePropVal(String key,Integer sysId,Integer type) throws Exception;
	

}
