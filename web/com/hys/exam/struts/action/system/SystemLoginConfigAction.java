/**
 * systemLoginConfigAction.java
 * com.hys.exam.struts.action.system
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月14日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.exam.struts.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.service.local.SystemConfigManage;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.framework.web.action.BaseAction;

/**
 * ClassName:systemLoginConfigAction
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月14日	上午10:40:27
 *
 * @see 	 
 *  
 */

public class SystemLoginConfigAction extends BaseAction {
	private SystemConfigManage systemConfigManage;
	
	

	/**
	 * systemConfigManage
	 *
	 * @return  the systemConfigManage
	 * @since    Ver 1.0
	*/
	
	public SystemConfigManage getSystemConfigManage() {
		return systemConfigManage;
	}

	/**
	 * systemConfigManage
	 *
	 * systemConfigManage    the systemConfigManage to set
	 * @since    Ver 1.0
	 */
	
	public void setSystemConfigManage(SystemConfigManage systemConfigManage) {
		this.systemConfigManage = systemConfigManage;
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.framework.web.action.BaseAction#actionExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = RequestUtil.getParameter(request, "method");
		
		if (method.equals("get")){
			return this.getConfig(mapping, form, request, response);
		}else if(method.equals("update")){
			return this.updateConfig(mapping, form, request, response);
		}else{
			
			return this.index(mapping, form, request, response);
		}
	
		
	}

	/**
	 * index:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param mapping
	 * @param  @param form
	 * @param  @param request
	 * @param  @param response
	 * @param  @return    
	 * @return String    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private String index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		SystemConfigVO systemConfigVO = systemConfigManage.getConfig();
		
		
		request.setAttribute("systemConfigVO", systemConfigVO );
		
		return "index";
		
	}

	/**
	 * updateConfig:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param mapping
	 * @param  @param form
	 * @param  @param request
	 * @param  @param response
	 * @param  @return    
	 * @return String    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private String updateConfig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String time  = request.getParameter("time");
		String num  = request.getParameter("loginNum");
		String lockTime  = request.getParameter("lockTime");

		this.systemConfigManage.updateConfig(time,num,lockTime);
		
		SystemConfigVO systemConfigVO = systemConfigManage.getConfig();
		
		
		request.setAttribute("systemConfigVO", systemConfigVO );
		return "index";
		
	}

	/**
	 * getConfig:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param mapping
	 * @param  @param form
	 * @param  @param request
	 * @param  @param response
	 * @param  @return    
	 * @return String    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private String getConfig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		this.systemConfigManage.getConfig();
		return null;
		
	}
	
	
	

}
