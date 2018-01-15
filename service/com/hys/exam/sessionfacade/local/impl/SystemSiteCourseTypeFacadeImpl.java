package com.hys.exam.sessionfacade.local.impl;

import com.hys.exam.service.local.SystemSiteCourseTypeManage;
import com.hys.exam.sessionfacade.local.SystemSiteCourseTypeFacade;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 facadeImpl
 * 
 * 说明:
 */
public class SystemSiteCourseTypeFacadeImpl extends BaseSessionFacadeImpl implements SystemSiteCourseTypeFacade {
	private SystemSiteCourseTypeManage systemSiteCourseTypeManage ;

	public void setSystemSiteCourseTypeManage(
			SystemSiteCourseTypeManage systemSiteCourseTypeManage) {
		this.systemSiteCourseTypeManage = systemSiteCourseTypeManage;
	}
	
}