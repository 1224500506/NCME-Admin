package com.hys.exam.struts.action.expert;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.exam.struts.form.ExpertInfoForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 学组成员管理
 * @author Han
 *
 */
public class ExpertSubGroupMemberAction extends BaseAction {

	private ExpertGroupManage localExpertgroupManage;
	
	private ExpertManageManage localExpertManageManage;

	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}

	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
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
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("batchdel")){
			return batchdel(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}

	/**
	 * 显示添加学组成员页面
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
		Long studyId = Long.valueOf(request.getParameter("sid") == null ? "0" : request.getParameter("sid"));

		ExpertInfo expert = new ExpertInfo();
		expert.setSubGroupId(studyId);

		//取得委员会目录
		ExpertGroup group = new ExpertGroup();
		group.setParent(0L);
		List<ExpertGroup> grouptemplist = localExpertgroupManage.getExpertGroupList(group);
		List<ExpertGroup> grouplist = new ArrayList<ExpertGroup>();
		for(int i = 0 ; i < grouptemplist.size() ; i ++){
			if(grouptemplist.get(i).getLockState().equals(1)){
				grouplist.add(grouptemplist.get(i));
			}
		}
		//取得职称目录
		ExamProp prop = new ExamProp();
		prop.setType(9);
		List<ExamProp> proplist = localExamPropValFacade.getPropListByType(prop);
		group.setParent(expert.getGroupId());
		List<ExpertGroup> subtemplist = localExpertgroupManage.getExpertGroupList(group);
		List<ExpertGroup> sublist = new ArrayList<ExpertGroup>();
		for(int i = 0 ; i < subtemplist.size() ; i ++){
			if(subtemplist.get(i).getLockState().equals(1) && subtemplist.get(i).getParent().equals(groupId)){
				sublist.add(subtemplist.get(i));
			}
		}
		List<ExpertGroupTerm> termlist = new ArrayList<ExpertGroupTerm>();
		expert.setGroupId(groupId);
		expert.setSubGroupId(studyId);
		request.setAttribute("sublist", sublist);
		request.setAttribute("termlist", termlist);
		request.setAttribute("proplist", proplist);
		request.setAttribute("grouplist", grouplist);
		request.setAttribute("info", expert);
		request.setAttribute("groupId", groupId);
		request.setAttribute("studyId", studyId);
		return "add";
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
		Long studyId = Long.valueOf(request.getParameter("sid") == null ? "0" : request.getParameter("sid"));
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));		
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expert = new ExpertInfo();
		expert.setId(eform.getId());
		expert = localExpertManageManage.getExpertInfo(expert);
		
		//取得委员会目录
		ExpertGroup group = new ExpertGroup();
		group.setParent(0L);
		List<ExpertGroup> grouplist = localExpertgroupManage.getExpertGroupList(group);
		
		ExamProp prop = new ExamProp();
		prop.setType(9);
		List<ExamProp> proplist = localExamPropValFacade.getPropListByType(prop);

		group.setParent(Long.valueOf(expert.getGroupIds()));
		
		//取得所属委员会的所有学组目录
		List<ExpertGroup> subtemplist = localExpertgroupManage.getExpertGroupList(group);
		List<ExpertGroup> sublist = new ArrayList<ExpertGroup>();
		for(int i = 0 ; i < subtemplist.size() ; i ++){
			if(subtemplist.get(i).getLockState().equals(1)){
				sublist.add(subtemplist.get(i));
			}
		}
		
		//取得所属委员会的所有届期目录
		group.setParent(0L);
		group.setId(expert.getGroupId());
		List<ExpertGroupTerm> termlist = localExpertgroupManage.getTermList(group);

		request.setAttribute("sublist", sublist);
		request.setAttribute("termlist", termlist);
		request.setAttribute("proplist", proplist);
		request.setAttribute("grouplist", grouplist);
		request.setAttribute("info", expert);
		request.setAttribute("groupId", groupId);
		request.setAttribute("studyId", studyId);
		return "add";
	}
	

	/**
	 * 移除学组成员
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
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expert = new ExpertInfo();
		expert.setId(eform.getId());
		expert.setGroupIds("0");

		boolean flag = localExpertManageManage.updateExpertInfo(expert);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 批量移除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String batchdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
//		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expert = new ExpertInfo();
		
		//取得选中的成员ID目录
		String[] array = ids.split(",");
		boolean flag = true;
		for (int i = 0; i < array.length; i++){
			expert.setId(Long.valueOf(array[i]));
			
			//删除学组信息
			expert.setSubGroupId(0L);

			if (!localExpertManageManage.updateExpertInfo(expert))
				flag = false;
		}

		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}

	/**
	 * 取得符合查询条件的成员
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
		Long studyId = Long.valueOf(request.getParameter("sid") == null ? "0" : request.getParameter("sid"));
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expert = new ExpertInfo();
		
		//设置查询条件
		expert.setName(eform.getName());
		expert.setTerm(eform.getTerm());
		expert.setGroupIds(studyId.toString());
		
		List<ExpertInfo> list =  localExpertManageManage.getExpertList(expert);
		
		//取得委员会和学组名称
		String groupName = new String();
		ExpertGroup subgroup = new ExpertGroup();
		subgroup.setId(studyId);
		subgroup = localExpertgroupManage.getExpertGroup(subgroup);
		
		ExpertGroup group = new ExpertGroup();
		group.setId(subgroup.getParent());
		group = localExpertgroupManage.getExpertGroup(group);
		
		if (group.getName() != null)
			groupName = group.getName() + "-";
		groupName += subgroup.getName();
		
		for (ExpertInfo e: list){
			
			ExpertGroupTerm term = new ExpertGroupTerm();
			term.setId(e.getTerm());
			term = localExpertgroupManage.getExpertGroupTerm(term);
			if (term!=null)
				e.setTermStr(term.getStartDateStr() + " - " + term.getEndDateStr());
		}

		List<ExpertGroupTerm> termlist = localExpertgroupManage.getTermList(group);

		request.setAttribute("termlist", termlist);
		request.setAttribute("groupName", groupName);
		request.setAttribute("islocked", subgroup.getLockState());
		request.setAttribute("info", expert);
		request.setAttribute("parentid", group.getId());
		request.setAttribute("gid", groupId);
		request.setAttribute("sid", studyId);
		request.setAttribute("extList", list);
		return "list";
	}
	
}
