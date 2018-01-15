package com.hys.exam.struts.action.expert;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 委员会届期数据管理
 * @author Han
 *
 */
public class ExpertGroupTermAction extends BaseAction {

	private ExpertGroupManage localExpertgroupManage;
	
	public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String method = RequestUtil.getParameter(request, "mode");
		if (method.equals("edit")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}

	/**
	 * 添加届期
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
		//取得届期数据
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));
		String startDate = request.getParameter("startDate") == null ? "" : request.getParameter("startDate");
		String endDate = request.getParameter("endDate") == null ? "" : request.getParameter("endDate");
		
		
		ExpertGroup group = new ExpertGroup();
		group.setId(groupId);
		group = localExpertgroupManage.getExpertGroup(group);
		List<ExpertGroupTerm> list = localExpertgroupManage.getTermList(group);
		//设置届期数据
		ExpertGroupTerm term = new ExpertGroupTerm();
		term.setGroupId(groupId);
		term.setStartDate(DateUtil.parse(startDate, "yyyy-MM-dd"));
		term.setEndDate(DateUtil.parse(endDate, "yyyy-MM-dd"));
		for(int i = 0 ; i < list.size() ; i++)
		{
			if(((list.get(i).getStartDate().getTime() < term.getStartDate().getTime()) && (list.get(i).getEndDate().getTime()>term.getStartDate().getTime()))||
				((list.get(i).getStartDate().getTime() < term.getEndDate().getTime()) && (list.get(i).getEndDate().getTime()>term.getEndDate().getTime()))||
				((list.get(i).getStartDate().getTime() < term.getStartDate().getTime()) && (list.get(i).getEndDate().getTime()>term.getEndDate().getTime()))||
				((list.get(i).getStartDate().getTime() > term.getStartDate().getTime()) && (list.get(i).getEndDate().getTime()<term.getEndDate().getTime()))||
				term.getStartDate().getTime() > term.getEndDate().getTime())
			{
				StrutsUtil.renderText(response, "error");	
				return null;
			}
		}
		boolean flag = localExpertgroupManage.addExpertGroupTerm(term);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "error1");
		}
		return null;
	}
	
	/**
	 * 保存届期数据
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
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));
		Long id = Long.valueOf(request.getParameter("id") == null ? "0" : request.getParameter("id"));
		String startDate = request.getParameter("startDate") == null ? "" : request.getParameter("startDate");
		String endDate = request.getParameter("endDate") == null ? "" : request.getParameter("endDate");
		
		
		ExpertGroup group = new ExpertGroup();
		group.setId(groupId);
		group = localExpertgroupManage.getExpertGroup(group);
		List<ExpertGroupTerm> list = localExpertgroupManage.getTermList(group);
		//设置届期数据
		ExpertGroupTerm term = new ExpertGroupTerm();
		term.setGroupId(groupId);
		term.setId(id);
		term.setStartDate(DateUtil.parse(startDate, "yyyy-MM-dd"));
		term.setEndDate(DateUtil.parse(endDate, "yyyy-MM-dd"));
		for(int i = 0 ; i < list.size() ; i++)
		{
			if(!list.get(i).getId().equals(id))
			{
				if(((list.get(i).getStartDate().getTime() < term.getStartDate().getTime()) && (list.get(i).getEndDate().getTime()>term.getStartDate().getTime()))||
						((list.get(i).getStartDate().getTime() < term.getEndDate().getTime()) && (list.get(i).getEndDate().getTime()>term.getEndDate().getTime()))||
						((list.get(i).getStartDate().getTime() < term.getStartDate().getTime()) && (list.get(i).getEndDate().getTime()>term.getEndDate().getTime()))||
						((list.get(i).getStartDate().getTime() > term.getStartDate().getTime()) && (list.get(i).getEndDate().getTime()<term.getEndDate().getTime()))||
						term.getStartDate().getTime() > term.getEndDate().getTime())
					{
						StrutsUtil.renderText(response, "error");	
						return null;
					}
			}
		}
		boolean flag = localExpertgroupManage.updateExpertGroupTerm(term);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "error1");
		}
		return null;
	}
	
	/**
	 * 删除届期
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
		Long id = Long.valueOf(request.getParameter("id") == null ? "0" : request.getParameter("id"));
		
		ExpertGroupTerm term = new ExpertGroupTerm();
		term.setId(id);
		
		boolean flag = localExpertgroupManage.deleteExpertGroupTerm(term);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 取得被选委员会的届期数据
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
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));
		
		ExpertGroup group = new ExpertGroup();
		group.setId(groupId);
		group = localExpertgroupManage.getExpertGroup(group);
		List<ExpertGroupTerm> list = localExpertgroupManage.getTermList(group);
		
		request.setAttribute("groupName", group.getName());
		request.setAttribute("gid", groupId);
		request.setAttribute("termList", list);
		return "SUCCESS";
	}
	

}
