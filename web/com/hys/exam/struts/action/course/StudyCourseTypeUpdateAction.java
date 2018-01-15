package com.hys.exam.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseTypeUpdateAction extends StudyCourseTypeBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		saveToken(request);// 设置token
		return getStudyCourseType(request);
	}
}
