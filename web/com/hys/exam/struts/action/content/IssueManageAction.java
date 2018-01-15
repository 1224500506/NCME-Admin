package com.hys.exam.struts.action.content;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ContentIssue;
import com.hys.exam.model.SystemSite;
import com.hys.exam.service.local.ContentIssueManage;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.exam.struts.form.IssueForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * @author Han
 * 通知公告管理
 */
public class IssueManageAction extends BaseAction {

	private ContentIssueManage localContentIssueManage;
	
	private SystemSiteFacade systemSiteFacade;
	
	public SystemSiteFacade getSystemSiteFacade() {
		return systemSiteFacade;
	}
	public void setSystemSiteFacade(SystemSiteFacade systemSiteFacade) {
		this.systemSiteFacade = systemSiteFacade;
	}
	public ContentIssueManage getLocalContentIssueManage() {
		return localContentIssueManage;
	}
	public void setLocalContentIssueManage(
			ContentIssueManage localContentIssueManage) {
		this.localContentIssueManage = localContentIssueManage;
	}
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String method = RequestUtil.getParameter(request, "method");
		if (method.equals("setorder")){
			return setorder(mapping, form, request, response);
		}
		else if (method.equals("save")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("detail")){
			return detail(mapping, form, request, response);
		}
		else if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return edit(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}

	/**
	 * 修改序号
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String setorder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String orderstr = ParamUtils.getParameter(request, "orderstr", "");
		
		boolean flag = localContentIssueManage.resortOrderNum(orderstr);
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}
	/**
	 * 保存通知公告信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		IssueForm qform = (IssueForm) form;
		ContentIssue issue = qform.getModel();
		
		boolean flag = false;
		
		if (issue != null && issue.getId() != null)
		if (issue.getId() == 0){
			flag = localContentIssueManage.insertContentIssue(issue);
		}
		else{
			flag = localContentIssueManage.updateContentIssue(issue);
		}
		
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}
	
	/**
	 * 删除通知公告
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		boolean flag = localContentIssueManage.deleteContentIssue(id);;
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	

	/**
	 * 取得符合查询条件的通知公告数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IssueForm qform = (IssueForm) form;
		ContentIssue issue = qform.getModel();
		
		PageList pl = new PageList();

		int currentPage = PageUtil.getPageIndex(request);

		pl.setPageNumber(currentPage);
		pl.setSortCriterion("i.ordernum");
		pl.setSortDirection(SortOrderEnum.ASCENDING);
		
		localContentIssueManage.queryIssuePageList(pl, issue);
		
		List<SystemSite> siteList = systemSiteFacade.getListByItem(new SystemSite());
		request.setAttribute("page", pl);
		request.setAttribute("item", issue);
		request.setAttribute("sitelist", siteList);
		return "list";
	}
	/**
	 * 显示添加通知公告页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		if (id != 0L){
			ContentIssue issue = localContentIssueManage.getIssueById(id);
			
			JSONObject obj = new JSONObject();
			obj.put("issue", issue);
			StrutsUtil.renderText(response, obj.toString());
		}
		return null;
	}
	
	/**
	 * 显示添加通知公告页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ContentIssue issue = new ContentIssue();
		List<SystemSite> siteList = systemSiteFacade.getListByItem(new SystemSite());
		request.setAttribute("sitelist", siteList);
		request.setAttribute("issue", issue);
		return "edit";
	}
	
	/**
	 * 显示编辑通知公告页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		if (id != 0L){
			ContentIssue issue = localContentIssueManage.getIssueById(id);
			List<SystemSite> siteList = systemSiteFacade.getListByItem(new SystemSite());
			request.setAttribute("sitelist", siteList);
			request.setAttribute("issue", issue);
		}
		return "edit";
	}
}
