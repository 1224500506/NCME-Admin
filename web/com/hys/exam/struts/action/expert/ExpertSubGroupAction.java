package com.hys.exam.struts.action.expert;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.LongUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 学组管理
 * @author Han
 *
 */
public class ExpertSubGroupAction extends BaseAction {

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
		if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return edit(mapping, form, request, response);
		}
		else if (method.equals("save")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("lock")){
			return lock(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}

	/**
	 * 显示添加页面
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
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));

		ExpertGroup group = new ExpertGroup();
		group = localExpertgroupManage.getExpertGroup(groupId);

		request.setAttribute("parentName", group.getName());
		request.setAttribute("gid", groupId);
		request.setAttribute("info", new ExpertGroup());
		return "edit";
	}
	
	/**
	 * 显示修改页面
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
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));

		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();	
		
		//取得委员会名称
		group = localExpertgroupManage.getExpertGroup(groupId);
		request.setAttribute("parentName", group.getName());
		
		//取得被选学组的信息
		group = localExpertgroupManage.getExpertGroup(eform.getId());
		request.setAttribute("gid", groupId);
		request.setAttribute("info", group);
		return "edit";
	}
	
	/**
	 * 保存学组信息
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

		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		ExpertGroup oldGroup = new ExpertGroup();
		
		String name = request.getParameter("rsName");
		String parent = request.getParameter("parent");
		String email = request.getParameter("email");
		String phone2 = request.getParameter("phone2");
		String contact = request.getParameter("contact");
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		String breakDate = request.getParameter("breakDate");
		
		//设置查询条件
		oldGroup.setName(name);
		List<ExpertGroup> nameList = localExpertgroupManage.getExpertGroupfromName(oldGroup);
		
		if(nameList.size() != 0)
		{
			StrutsUtil.renderText(response, "repeatname");
			return null;
		}
		
		//设置学组信息
		if (id != null && !id.equals("")){
			group.setId(LongUtil.parseLong(id));
			group.setLockState(1);
		}
		group.setName(name);
		group.setParent(LongUtil.parseLong(parent));
		group.setContact(contact);
		group.setPhone2(phone2);
		group.setState(Integer.valueOf(state));
		group.setEmail(email);
		group.setBreakDate(DateUtil.parse(breakDate, "yyyy-MM-dd"));
//		group.setMaster(eform.getMaster());
//		group.setAddress(eform.getAddress());
//		group.setNote(eform.getNote());
//		group.setOrganizeDate(DateUtil.parse(eform.getOrganizeDate(), "yyyy-MM-dd"));
//		group.setPhone1(eform.getPhone1());
//		group.setSummary(eform.getSummary());
		
		if (group.getId() == null)
			localExpertgroupManage.addExpertGroup(group);
		else
			localExpertgroupManage.updateExpertGroup(group);
		StrutsUtil.renderText(response, "success");
		return null;
	}
	
	/**
	 * 删除学组
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
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		group.setId(eform.getId());
		
		boolean flag = localExpertgroupManage.deleteExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 禁用学组
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String lock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置禁用状态为2
		group.setId(eform.getId());
		group = localExpertgroupManage.getExpertGroup(group);
		if(group.getLockState() == null || group.getLockState() != 1)
			group.setLockState(1);
		else
			group.setLockState(2);
		
		boolean flag = localExpertgroupManage.updateExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 取得符合查询条件的学组目录
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
	
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置查询条件
		group.setName(eform.getName());
		group.setParent(groupId);
		group.setContact(eform.getContact());
		group.setLockState(eform.getState());
		
		List<ExpertGroup> list =  localExpertgroupManage.getExpertGroupList(group);
		
		ExpertGroup group1 = new ExpertGroup();
		group1.setId(groupId);
		group1 = localExpertgroupManage.getExpertGroup(group1);
		
		request.setAttribute("parent", group1);
		request.setAttribute("info", group);
		request.setAttribute("gid", groupId);
		request.setAttribute("extList", list);
		return "list";
	}
	
}
