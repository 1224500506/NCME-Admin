package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.model.SystemClient;
import com.hys.exam.model.SystemOrg;
import com.hys.exam.sessionfacade.local.SystemClientFacade;
import com.hys.exam.sessionfacade.local.SystemOrgFacade;
import com.hys.exam.struts.form.SystemClientForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemClientAction extends BaseAction {
	private SystemClientFacade systemClientFacade;
	private SystemOrgFacade systemOrgFacade;

	@Override
	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String method = RequestUtil.getParameter(request, "method");
		if (method.equals("list")){
			
			return this.list(actionMapping, actionForm, request, response);
		}else if (method.equals("add")){
			
			return this.add(actionMapping, actionForm, request, response);
		}else if (method.equals("save")){
			
			return this.save(actionMapping, actionForm, request, response);
		}else if (method.equals("edit")){
			
			return this.edit(actionMapping, actionForm, request, response);
		}else if (method.equals("update")){
			
			return this.update(actionMapping, actionForm, request, response);
		}else if (method.equals("delete")){
			
			return this.delete(actionMapping, actionForm, request, response);
		}else {
			
			return this.list(actionMapping, actionForm, request, response);
		}
	}
	
	//页面常用数据
	protected void referenceData(HttpServletRequest request){
		List<SystemOrg> orgs = systemOrgFacade.getListAll();
		request.setAttribute("orgs", orgs);
		
	}
	
	//查询
	protected String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SystemClientForm aform = (SystemClientForm)actionForm;
		SystemClient item = aform.getModel();
		
		Page<SystemClient> page = PageUtil.getPageByRequest(request);
		systemClientFacade.getSystemClientList(page, item);
		
		request.setAttribute("page", page);
		
		referenceData(request);
		
		return "list";
	}
	
	//新增页面
	protected String add(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.saveToken(request) ;//设置token
		
		referenceData(request);
		
		return "add";
	}
	
	//保存
	protected String save(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemClientForm aform = (SystemClientForm)actionForm;
		SystemClient item = aform.getModel();
		item.setStatus(1);//正常
		if(this.isTokenValid(request,true)){
				systemClientFacade.save(item);
				request.setAttribute("meg", "新增成功");
		}else{
				request.setAttribute("meg", "重复提交");
		}
		return this.list(actionMapping, actionForm, request, response);
	}
	
	//编辑页面
	protected String edit(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemClient item = systemClientFacade.getItemById(id);
		request.setAttribute("item", item);
		
		referenceData(request);
		
		return "edit";
	}
	
	//查看页面
	protected String view(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemClient item = systemClientFacade.getItemById(id);
		request.setAttribute("item", item);
		
		return "view";
	}
	
	//更新
	protected String update(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemClientForm aform = (SystemClientForm)actionForm;
		SystemClient item = aform.getModel();
			if (item.getId()!=null && item.getId()>0){
				systemClientFacade.update(item);
				request.setAttribute("meg", "更新成功");
			}
		
		return this.list(actionMapping, actionForm, request, response);
	}
	
	//删除 
	protected String delete(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long[] selIdArr = ParamUtils.getLongParameters(request, "selId", 0L);
		if(selIdArr!=null && selIdArr.length>0){ 
			for (long id : selIdArr ){
				systemClientFacade.delete(id,"id");
			}
				
		}
		return this.list(actionMapping, actionForm, request, response);
	}

	public void setSystemOrgFacade(SystemOrgFacade systemOrgFacade) {
		this.systemOrgFacade = systemOrgFacade;
	}
	
	public void setSystemClientFacade(SystemClientFacade systemClientFacade) {
		this.systemClientFacade = systemClientFacade;
	}

}
