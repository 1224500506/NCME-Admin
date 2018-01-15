package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.model.SystemClient;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemSiteCourseType;
import com.hys.exam.sessionfacade.local.SystemApplicationFacade;
import com.hys.exam.sessionfacade.local.SystemClientFacade;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.exam.struts.form.SystemSiteForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-25
 * 
 * 描述：注册站点管理
 * 
 * 说明:
 */
public class SystemSiteAction extends AppBaseAction {
	private SystemSiteFacade systemSiteFacade;
	private SystemClientFacade systemClientFacade;
	private SystemApplicationFacade systemApplicationFacade;
	private SystemRoleFacade systemRoleFacade;

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
		}else if (method.equals("updateByAjax")){
			
			return this.updateByAjax(actionMapping, actionForm, request, response);
		}else if (method.equals("delete")){
			
			return this.delete(actionMapping, actionForm, request, response);
		}else if (method.equals("exam")){
			
			return this.exam(actionMapping, actionForm, request, response);
		}else if (method.equals("siteExam")){
			
			return this.siteExam(actionMapping, actionForm, request, response);
		}else if (method.equals("updateEdit")){
			return this.updateEdit(actionMapping, actionForm, request, response);
		}
		
		else {
			
			return this.list(actionMapping, actionForm, request, response);
		}
	}
	
	//页面常用数据
	protected void referenceData(HttpServletRequest request){
		List<SystemClient> clients = systemClientFacade.getListByItem(new SystemClient());
		List<SystemApplication> applications = systemApplicationFacade.getListByItem(new SystemApplication());
		List<SystemRole> roles = systemRoleFacade.getListByItem(new SystemRole());
		
		request.setAttribute("clients", clients );
		request.setAttribute("applications", applications );
		request.setAttribute("roles", roles );
	}
	
	//查询
	protected String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/*SystemSiteForm aform = (SystemSiteForm)actionForm;
		SystemSite item = aform.getModel();*/
		SystemSite item = new SystemSite ();
		item.setDomainName(request.getParameter("domainName"));
		item.setAliasName(request.getParameter("aliasName"));
		
		///Page<SystemSite> page = PageUtil.getPageByRequest(request);
		@SuppressWarnings("static-access")
		Page<SystemSite> page = this.createPage(request, "systemSite");
		systemSiteFacade.querySystemSiteList(page, item);
		
		request.setAttribute("page", page);
		request.setAttribute("item", item);
		
		//referenceData(request);
		
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

		SystemSite item = new SystemSite();
		item.setDomainName(request.getParameter("domainName").trim());
		if(request.getParameter("domainName") == null || request.getParameter("domainName").trim().equals("")){
			StrutsUtil.renderText(response, "empty");
			return null;
		}
		List<SystemSite> temp_itemList = systemSiteFacade.getListByItem(request.getParameter("domainName").trim(), null);
		if (temp_itemList == null || temp_itemList.size() == 0) {		
			item.setAliasName(request.getParameter("aliasName"));
			item.setRemark(request.getParameter("remark"));
			item.setStatus(1);//正常
			item.setApplicationId(1L);
			item.setClientId(13L);
			item.setRoleId(1L);
			systemSiteFacade.save(item);
			StrutsUtil.renderText(response, "success");
		} else {
			StrutsUtil.renderText(response, "duplicated domainName");
		}
		return null;
	}
	
/*	protected String save(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemSiteForm aform = (SystemSiteForm)actionForm;
		SystemSite item = aform.getModel();
		SystemSite check_item=systemSiteFacade.getItemById(item.getId());
		if(check_item !=null){
			request.setAttribute("meg", "新增");

			return this.list(actionMapping, actionForm, request, response);
		}else{
		item.setStatus(1);//正常

			item.setApplicationId(1L);
			item.setClientId(13L);
			item.setRoleId(1L);
			systemSiteFacade.save(item);
			request.setAttribute("meg", "新增成功");

		return this.list(actionMapping, actionForm, request, response);
		}
	}*/
/*	protected String save(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemSiteForm aform = (SystemSiteForm)actionForm;
		SystemSite item = aform.getModel();
		item.setStatus(1);//正常
		if(this.isTokenValid(request,true)){
			item.setApplicationId(1L);
			item.setClientId(13L);
			item.setRoleId(1L);
			systemSiteFacade.save(item);
			request.setAttribute("meg", "新增成功");
		}else{
			request.setAttribute("meg", "重复提交");
		}
		return this.list(actionMapping, actionForm, request, response);
	}*/
	
	//编辑页面
	protected String edit(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemSite item = systemSiteFacade.getItemById(id);
		request.setAttribute("item", item);
		
		referenceData(request);
		
		return "edit";
	}
	
	protected String updateEdit(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception{

		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemSite item = systemSiteFacade.getItemById(id);

		JSONObject result = new JSONObject();
		result.put("item", item);
		
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}
	
	//查看页面
	protected String view(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemSite item = systemSiteFacade.getItemById(id);
		request.setAttribute("item", item);
		
		return "view";
	}
	
	protected String updateByAjax(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SystemSite item = new SystemSite();
		item.setId(ParamUtils.getLongParameter(request, "id", 0L));
		if (item.getId()!=null && item.getId()>0){				
			item.setDomainName(request.getParameter("domainName"));	
			
			if(request.getParameter("domainName") ==null || request.getParameter("domainName").trim().equals("")){
				StrutsUtil.renderText(response, "empty");
				return null;
			}
			
			List<SystemSite> temp_itemList = systemSiteFacade.getListByItem(request.getParameter("domainName").trim(), item.getId());
			if (temp_itemList != null && temp_itemList.size() > 0 && temp_itemList.get(0).getId() != item.getId()) {	
				StrutsUtil.renderText(response, "duplicated domainName");
			} else {
				item.setAliasName(request.getParameter("aliasName"));
				item.setRemark(request.getParameter("remark"));
				systemSiteFacade.update(item);
				StrutsUtil.renderText(response, "success");			
			}
		}
		return null;
	}
	
	//更新
	protected String update(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemSiteForm aform = (SystemSiteForm)actionForm;
		SystemSite item = aform.getModel();
			if (item.getId()!=null && item.getId()>0){
				systemSiteFacade.update(item);
				request.setAttribute("meg", "更新成功");
			}
		
		return this.list(actionMapping, actionForm, request, response);
	}
	
	//删除 
	protected String delete(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		
		//2017/01/11, by lee		
		long[] selIdArr = ParamUtils.getLongParametersFromString(request, "selId", 0);
		int res = 0;
		if(selIdArr!=null && selIdArr.length>0){ 
			for (long id : selIdArr ){
				res = systemSiteFacade.delete(id,"id");
				if(res==0){
					StrutsUtil.renderText(response, "error");
					return null;
				}
			}
			StrutsUtil.renderText(response, "success");
		}
		return null;
	}

	//安排考试
	protected String exam(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemSite item = systemSiteFacade.getItemById(id);

		setSystemSiteExamList(item.getSiteCourseTypes(), item.getId());

		request.setAttribute("item", item);
		referenceData(request);

		return "exam";
	}
	
	//安排考试
	protected String siteExam(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "siteExam";
	}

	// 设置考试信息列表
	private void setSystemSiteExamList(
			List<SystemSiteCourseType> siteCourseTypes,Long siteId) {
		if (siteCourseTypes == null || siteCourseTypes.isEmpty()) {
			return;
		}

		for (int i = 0; i < siteCourseTypes.size(); ++i) {
			SystemSiteCourseType type = siteCourseTypes.get(i);

			type.setExamExaminationList(systemSiteFacade
					.getExamExaminationList(siteId, type.getCourseTypeId()));
		}
	}
	
	
	public void setSystemSiteFacade(SystemSiteFacade systemSiteFacade) {
		this.systemSiteFacade = systemSiteFacade;
	}

	public void setSystemClientFacade(SystemClientFacade systemClientFacade) {
		this.systemClientFacade = systemClientFacade;
	}

	public void setSystemApplicationFacade(
			SystemApplicationFacade systemApplicationFacade) {
		this.systemApplicationFacade = systemApplicationFacade;
	}

	public void setSystemRoleFacade(SystemRoleFacade systemRoleFacade) {
		this.systemRoleFacade = systemRoleFacade;
	}

}
