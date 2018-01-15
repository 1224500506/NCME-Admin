package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.model.NcmeCourseType;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public interface NcmeCourseTypeFacade {
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<NcmeCourseType> getNcmeCourseTypeList();
}
