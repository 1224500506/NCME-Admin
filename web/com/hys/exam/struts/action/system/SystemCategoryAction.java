package com.hys.exam.struts.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.system.SystemCategory;
import com.hys.exam.service.remote.SystemCategoryNewsManage;
import com.hys.exam.service.remote.SystemPropManage;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.exam.struts.action.AppBaseAction;

public class SystemCategoryAction extends AppBaseAction {
	
	private SystemCategoryNewsManage systemCategoryNewsManage;
	
	private SystemPropManage systemPropManage;
	
	private SystemSiteFacade systemSiteFacade;

	public SystemCategoryNewsManage getSystemCategoryNewsManage() {
		return systemCategoryNewsManage;
	}

	public void setSystemCategoryNewsManage(
			SystemCategoryNewsManage systemCategoryNewsManage) {
		this.systemCategoryNewsManage = systemCategoryNewsManage;
	}

	public SystemPropManage getSystemPropManage() {
		return systemPropManage;
	}

	public void setSystemPropManage(SystemPropManage systemPropManage) {
		this.systemPropManage = systemPropManage;
	}
	public SystemSiteFacade getSystemSiteFacade() {
		return systemSiteFacade;
	}

	public void setSystemSiteFacade(SystemSiteFacade systemSiteFacade) {
		this.systemSiteFacade = systemSiteFacade;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		setData(request);
		String method = request.getParameter("method");
		if("delete".equals(method)){
			return this.delete(mapping, form, request, response);
		}else if("updateUI".equals(method)){
			return this.updateUI(mapping, form, request, response);
		}else if("update".equals(method)){
			return this.update(mapping, form, request, response);
		}else if("addUI".equals(method)){
			return "add";
		}else if("add".equals(method)){
			return this.add(mapping, form, request, response);
		}else{
			return this.list(mapping, form, request, response);
		}
	}
	//栏目列表
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SystemCategory systemCategory = new SystemCategory();
		
		String siteId = request.getParameter("applicationId");
		if(siteId != null && !"".equals(siteId)){
			systemCategory.setApplicationId(siteId);
			request.setAttribute("applicationId", siteId);
		}
		String title = request.getParameter("title");
		if(title != null && !"".equals(title)){
			systemCategory.setTitle(title);
			request.setAttribute("title", title);
		}
		Page<SystemCategory> page = createPage(request,"systemCategory");
		systemCategoryNewsManage.getSystemCategoryList(page, systemCategory);
		request.setAttribute("page", page);
		return "list";
	}
	//栏目列表
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String[] ids = request.getParameterValues("cardIds");
		if(ids!=null && ids.length!=0){
			Long[] longIds = new Long[ids.length];
			int i = 0;
			for(String id : ids){
				longIds[i++] = Long.valueOf(id.trim());
			}
			systemCategoryNewsManage.deleteSystemCategory(longIds);
			request.setAttribute("message", "删除成功!");
		}else{
			request.setAttribute("message", "没有选择要删除的栏目!");
		}
		return this.list(mapping, form, request, response);
	}
	//更新栏目UI
	protected String updateUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String categoryId = request.getParameter("categoryId");
		if(null!=categoryId && !categoryId.equals("")){
			request.setAttribute("category", systemCategoryNewsManage.getSystemCategoryById(Long.valueOf(categoryId)));
		}
		return "update";
	}
	//更新栏目
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String categoryId = request.getParameter("categoryId");
		if(null!=categoryId && !categoryId.equals("")){
			SystemCategory systemCategory = systemCategoryNewsManage.getSystemCategoryById(Long.valueOf(categoryId));
			String siteId = request.getParameter("applicationId");
			if(siteId != null && !"".equals(siteId)){
				systemCategory.setApplicationId(siteId);
				request.setAttribute("applicationId", siteId);
			}
			String title = request.getParameter("title");
			if(title != null && !"".equals(title)){
				systemCategory.setTitle(title);
				request.setAttribute("title", title);
			}
			systemCategoryNewsManage.updateSystemCategory(systemCategory);
		}
		
		request.setAttribute("message", "更新栏目成功!");
		return this.list(mapping, form, request, response);
	}
	//添加 栏目
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SystemCategory systemCategory = new SystemCategory();
		String siteId = request.getParameter("applicationId");
		if(siteId != null && !"".equals(siteId)){
			systemCategory.setApplicationId(siteId);
			request.setAttribute("applicationId", siteId);
		}
		String title = request.getParameter("title");
		if(title != null && !"".equals(title)){
			systemCategory.setTitle(title);
			request.setAttribute("title", title);
		}
		systemCategoryNewsManage.addSystemCategory(systemCategory);
		
		request.setAttribute("message", "添加栏目成功!");
		return this.list(mapping, form, request, response);
	}
	private void setData(HttpServletRequest request){
		List<SystemSite> siteList = systemSiteFacade.getListByItem(new SystemSite());
		request.setAttribute("siteList", siteList);
	}

	
}
