/**
 *
 * <p>快递</p>
 * @author chenlaibin
 * @version 1.0 2015-8-13
 */

package com.hys.exam.struts.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.struts.action.AppBaseAction;

public class SystemPostHistoryAction extends AppBaseAction{
	
	private SystemUserManage systemUserManage;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("toUpdate")){
			return this.toUpdate(mapping, form, request, response);
		}else if(method.equals("updateJson")){
			return this.updateJson(mapping, form, request, response);
		}
		else if(method.equals("update")){
			return this.update(mapping, form, request, response);
		}
		return null;
	}
	
	//to update
	@SuppressWarnings("static-access")
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String certificateName = ParamUtils.getParameter(request, "certificateName");
		String mobilePhone = ParamUtils.getParameter(request, "mobilePhone");
		String userName = ParamUtils.getParameter(request, "userName");
		SystemUserPostHistory p = new SystemUserPostHistory();
		p.setCertificateName(certificateName);
		p.setMobilePhone(mobilePhone);
		p.setUserName(userName);		
		request.setAttribute("postHistory", p);
		Page<SystemUserPostHistory> page = this.createPage(request, "SystemUserPostHistory");
		this.systemUserManage.getSystemPostHistoryList(page, p);
		List<SystemExpress> expressList = this.systemUserManage.getSystemExpressList();
		request.getSession().setAttribute("expressList", expressList);
		request.setAttribute("page", page);
		return "list";
	}
	
	//新增,修改
	protected String toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		SystemUserPostHistory p = this.systemUserManage.getSystemPostHistoryById(id);
		List<SystemExpress> expressList = this.systemUserManage.getSystemExpressList();
		request.setAttribute("postHistory", p);
		request.setAttribute("expressList", expressList);
		return "update";
	}
	
	//update
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SystemUserPostHistory p = new SystemUserPostHistory();
		p.setId(ParamUtils.getLongParameter(request, "id", 0));
		p.setUserName(ParamUtils.getParameter(request, "userName"));
		p.setMobilePhone(ParamUtils.getParameter(request, "mobilePhone"));
		p.setAddress(ParamUtils.getParameter(request, "address"));
		p.setCertificateName(ParamUtils.getParameter(request, "certificateName"));
		p.setDescription(ParamUtils.getParameter(request, "description"));		
		p.setExpressNo(ParamUtils.getParameter(request, "expressNo"));
		p.setExpressId(ParamUtils.getLongParameter(request, "expressId", 0));
		p.setInvoiceStatus(ParamUtils.getIntParameter(request, "invoiceStatus", 0));
		this.systemUserManage.updateSystemUserPostHistory(p);
		/*
		p.setInvoiceStatus(ParamUtils.getIntParameter(request, "invoiceStatus", 0));
		p.setExpressId(ParamUtils.getLongParameter(request, "expressId", 0));
		return this.list(mapping, form, request, response);
		*/
		SystemUserPostHistory p1 = new SystemUserPostHistory();
		Page<SystemUserPostHistory> page = this.createPage(request, "SystemUserPostHistory");
		this.systemUserManage.getSystemPostHistoryList(page, p1);
		request.setAttribute("page", page);
		return "list";
	}

	protected String updateJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		SystemUserPostHistory item = this.systemUserManage.getSystemPostHistoryById(id);		
		
		JSONObject result = new JSONObject();
		result.put("item", item);
		
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}
	
	public SystemUserManage getSystemUserManage() {
		return systemUserManage;
	}

	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}
}


