package com.hys.exam.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hys.auth.model.HysUsers;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;

/**
 * 
 * 标题：住院医师
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：业务工具类
 * 
 * 说明:
 */
public class LogicUtil {

	/**
	 * 取得session用户对象信息
	 * @param request
	 * @return
	 */
	public static SystemUser getSystemUser(HttpServletRequest request){
		HttpSession session = request.getSession() ;
		return getSystemUser(session) ;
	}

	/**
	 * 取得session用户对象信息
	 * @param session
	 * @return
	 */
	public static SystemUser getSystemUser(HttpSession session){
		return (SystemUser)session.getAttribute(Constants.SESSION_TRAIN_ADMIN_USER) ;
	}
}