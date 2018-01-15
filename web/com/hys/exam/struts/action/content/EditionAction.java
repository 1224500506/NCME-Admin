package com.hys.exam.struts.action.content;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.EditionManage;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.CE_Form;
import com.hys.exam.struts.form.ExpertInfoForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;

public class EditionAction extends BaseAction {

	private EditionManage localEditionManage;
	
	
	private ExpertManageManage localExpertManageManage;
	
	private ExpertGroupManage localExpertGroupManage;
	
	private ExamPropValFacade localExamPropValFacade;
	
	private SystemUserManage localSystemUserManage;	
	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}

	public ExpertGroupManage getLocalExpertGroupManage() {
		return localExpertGroupManage;
	}

	public void setLocalExpertGroupManage(ExpertGroupManage localExpertGroupManage) {
		this.localExpertGroupManage = localExpertGroupManage;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	public EditionManage getLocalEditionManage() {
		return localEditionManage;
	}

	public void setLocalEditionManage(EditionManage localEditionManage) {
		this.localEditionManage = localEditionManage;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// TODO Auto-generated method stub
		
		String mode = RequestUtil.getParameter(request, "mode");
		if (mode.equals("setorder")){
			return setorder(mapping, form, request, response);
		}else if(mode.equals("setorderCV")){
			return setorderCV(mapping, form, request, response);
		} else if(mode.equals("list")) {
			return list(mapping, form, request, response);			
		} else if(mode.equals("view")) {
			return view(mapping, form, request, response);
		} else if(mode.equals("edit")) {
			return edit(mapping, form, request, response);
		}
		else if(mode.equals("bind")) {
			return bind(mapping, form, request, response);
		}
		else if(mode.equals("unbind")) {
			return unbind(mapping, form, request, response);
		}
		else if(mode.equals("viewCV")) {
			return viewCV(mapping, form, request, response);
		} else if(mode.equals("editCV")) {
			return editCV(mapping, form, request, response);
		}
		else if(mode.equals("bindCV")) {
			return bindCV(mapping, form, request, response);
		}
		else if(mode.equals("unbindCV")) {
			return unbindCV(mapping, form, request, response);
		}else if(mode.equals("bindCount")) {
			return bindCount(mapping, form, request, response);
		}
		else {
			return list(mapping, form, request, response);
		}
	}

	protected String setorder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String orderstr = ParamUtils.getParameter(request, "orderstr", "");
		
		boolean flag = localEditionManage.resortOrderNum(orderstr);
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}
	
	//chenlb add
	protected String setorderCV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String orderstr = ParamUtils.getParameter(request, "orderstr", "");
		
		boolean flag = localEditionManage.resortOrderNumCV(orderstr);
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}
	
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form; 
		
		Edition edition = new Edition();
		
		List<Edition> list = new ArrayList<Edition>();
		
		if(ceForm != null) {
			edition.setName(ceForm.getBookName());
			edition.setTitle(ceForm.getKindName());
			edition.setKind(ceForm.getTakeKind());
		}
		edition.setType(0);
		
		list = localEditionManage.getEditionList(edition);
		
		request.setAttribute("Edition", list);
		request.setAttribute("bName", edition.getName());
		request.setAttribute("kName", edition.getTitle());
		request.setAttribute("tKind", edition.getKind());
		
		return "success";
	}
	
	//String数组转化为Long数组
	private Long [] switchStringtoLongArray(String [] strArr){
		if(null == strArr || strArr.length==0)
			return new Long[0];
		Long [] longArr = new Long[strArr.length];
		for(int i=0; i< strArr.length; i++){
			if(StringUtils.isNotBlank(strArr[i])){
				longArr[i]=Long.parseLong(strArr[i]);
			}
		}
		return longArr;		
	}
	
	//绑定  chenlb add
	private String bind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int count = 0;
		String _cvsetIds = ParamUtils.getParameter(request, "cvsetIds");
		_cvsetIds = _cvsetIds.substring(0, _cvsetIds.length()-1);
		String [] ids = _cvsetIds.split(",");
		Long [] cvsetIds = this.switchStringtoLongArray(ids);
		count = this.localEditionManage.saveEditionCVSetIds(id, cvsetIds);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	
	//解绑 chenlb add
	private String unbind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		int count = 0;
		String _cvsetIds = ParamUtils.getParameter(request, "cvsetIds");
		_cvsetIds = _cvsetIds.substring(0, _cvsetIds.length()-1);
		String [] ids = _cvsetIds.split(",");
		Long [] cvsetIds = this.switchStringtoLongArray(ids);
		count = this.localEditionManage.deleteEditionCVSetIds(id, cvsetIds);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//绑定课程  chenlb add
	private String bindCV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int count = 0;
		String _cvIds = ParamUtils.getParameter(request, "cvIds");
		if(StringUtils.isNotBlank(_cvIds)){
			_cvIds = _cvIds.substring(0, _cvIds.length()-1);
			String [] ids = _cvIds.split(",");
			Long [] cvIds = this.switchStringtoLongArray(ids);
			count = this.localEditionManage.saveEditionCVIds(id, cvIds);
			Utils.renderText(response, String.valueOf(count));
		}
		return null;
	}
		
	//
	//解绑课程 chenlb add
	private String unbindCV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		int count = 0;
		String _cvIds = ParamUtils.getParameter(request, "cvIds");
		if(StringUtils.isNotBlank(_cvIds)){
			_cvIds = _cvIds.substring(0, _cvIds.length()-1);
			String [] ids = _cvIds.split(",");
			Long [] cvIds = this.switchStringtoLongArray(ids);
			count = this.localEditionManage.deleteEditionCVIds(id, cvIds);
			Utils.renderText(response, String.valueOf(count));
		}
		return null;
	}
	
	
	//---------------------------专委会--------绑定---
	private String bindZWH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int count = 0;
		String _cvIds = ParamUtils.getParameter(request, "cvIds");
		if(StringUtils.isNotBlank(_cvIds)){
			_cvIds = _cvIds.substring(0, _cvIds.length()-1);
			String [] ids = _cvIds.split(",");
			Long [] cvIds = this.switchStringtoLongArray(ids);
			count = this.localEditionManage.saveEditionZWHIds(id, cvIds);
			Utils.renderText(response, String.valueOf(count));
		}
		return null;
		
	}
	//---------------------------专委会--------解绑---
	private String unbindZWH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int count = 0;
		String _cvIds = ParamUtils.getParameter(request, "cvIds");
		if(StringUtils.isNotBlank(_cvIds)){
			_cvIds = _cvIds.substring(0, _cvIds.length()-1);
			String [] ids = _cvIds.split(",");
			Long [] cvIds = this.switchStringtoLongArray(ids);
			count = this.localEditionManage.deleteEditionZWHIds(id, cvIds);
			Utils.renderText(response, String.valueOf(count));
		}
		return null;
		
	}
	
	private String view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form;
		
		Edition edition = new Edition();
		
		List<CVSet> cvList = new ArrayList<CVSet>();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String cvsetName = ParamUtils.getParameter(request, "cvsetName");
		
		if(ceForm != null) {
			CVSet cvSet = new CVSet();
			//cvSet.setName(ceForm.getGroupIds());  //need only Course Name by change Lee
			cvSet.setDeli_man(ceForm.getCreateMan());
			cvSet.setName(ceForm.getEditionName());
			cvSet.setStatus(ceForm.getStatus());
			
			edition.setCvSet(cvSet);
		}
		edition.setId(id);
		edition.setType(1);
		
		cvList = this.localEditionManage.getCVSetListByEdition(id, cvsetName, true);		//false 为绑定的项目
		
		
		request.setAttribute("list", cvList);
		if(ceForm != null) {
			//request.setAttribute("groupNames01", arg1);
			request.setAttribute("createMan", ceForm.getCreateMan());
			request.setAttribute("editionName", ceForm.getEditionName());
			request.setAttribute("status", ceForm.getStatus());
		}
		request.setAttribute("id", id);
		request.setAttribute("cvsetName", cvsetName);
		return "view";
	}
	
	//chenlb add  查看绑定的课程
	private String viewCV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form;
		
		Edition edition = new Edition();
		
		List<CV> cvList = new ArrayList<CV>();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String cvName = ParamUtils.getParameter(request, "cvName");
		
		if(ceForm != null) {
			CVSet cvSet = new CVSet();
			//cvSet.setName(ceForm.getGroupIds());  //need only Course Name by change Lee
			cvSet.setDeli_man(ceForm.getCreateMan());
			cvSet.setName(ceForm.getEditionName());
			cvSet.setStatus(ceForm.getStatus());
			
			edition.setCvSet(cvSet);
		}
		edition.setId(id);
		edition.setType(1);
		
		cvList = this.localEditionManage.getCVListByEdition(id, cvName, true);		//true  已绑定课程 ;  false 绑定的课程
		 
		request.setAttribute("list", cvList);
		if(ceForm != null) {
			//request.setAttribute("groupNames01", arg1);
			request.setAttribute("createMan", ceForm.getCreateMan());
			request.setAttribute("editionName", ceForm.getEditionName());
			request.setAttribute("status", ceForm.getStatus());
		}
		request.setAttribute("id", id);
		request.setAttribute("cvName",cvName);
		return "viewCV";
	}
	
	//chenlb  edit
	//查询栏目未绑定的项目
	
	private String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form;
		
		Edition edition = new Edition();
		
		List<CVSet> cvList = new ArrayList<CVSet>();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String cvsetName = ParamUtils.getParameter(request, "cvsetName");
		
		if(ceForm != null) {
			CVSet cvSet = new CVSet();
			//cvSet.setName(ceForm.getGroupIds());  //need only Course Name by change Lee
			cvSet.setDeli_man(ceForm.getCreateMan());
			cvSet.setName(ceForm.getEditionName());
			cvSet.setStatus(ceForm.getStatus());
			
			edition.setCvSet(cvSet);
		}
		edition.setId(id);
		edition.setType(1);
		
		cvList = this.localEditionManage.getCVSetListByEdition(id, cvsetName, false);		//false 为未绑定的项目
		
		request.setAttribute("list", cvList);
		if(ceForm != null) {
			//request.setAttribute("groupNames01", arg1);
			request.setAttribute("createMan", ceForm.getCreateMan());
			request.setAttribute("editionName", ceForm.getEditionName());
			request.setAttribute("status", ceForm.getStatus());
		}
		request.setAttribute("id", id);
		request.setAttribute("cvsetName", cvsetName);
		return "edit";
	}
	
	//chenlb  edit
	//查询栏目未绑定的课程
		
	private String editCV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form;
		
		Edition edition = new Edition();
		
		List<CV> cvList = new ArrayList<CV>();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String cvName = ParamUtils.getParameter(request, "cvName");
		
		if(ceForm != null) {
			CVSet cvSet = new CVSet();
			cvSet.setDeli_man(ceForm.getCreateMan());
			cvSet.setName(ceForm.getEditionName());
			cvSet.setStatus(ceForm.getStatus());
			
			edition.setCvSet(cvSet);
		}
		edition.setId(id);
		edition.setType(1);
		
		cvList = this.localEditionManage.getCVListByEdition(id, cvName, false);		//false 为未绑定的课程
		
		request.setAttribute("list", cvList);
		if(ceForm != null) {
			//request.setAttribute("groupNames01", arg1);
			request.setAttribute("createMan", ceForm.getCreateMan());
			request.setAttribute("editionName", ceForm.getEditionName());
			request.setAttribute("status", ceForm.getStatus());
		}
		request.setAttribute("id", id);
		request.setAttribute("cvName", cvName);
		return "editCV";
	}

	/**
	 * 查询页面管理中各个栏目绑定的项目数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String bindCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<CV> cvList = new ArrayList<CV>();
		List<CVSet> cvSetList = new ArrayList<CVSet>();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String flag = ParamUtils.getParameter(request, "flag");
		
		Edition edition = new Edition();
		edition.setId(id);
		edition.setType(1);
		if ("CV".equals(flag)) {
			cvList = this.localEditionManage.getCVListByEdition(id, "", true);		//true  已绑定课程 ;  false 绑定的课程
			StrutsUtil.renderText(response, String.valueOf(cvList.size()));
		}else {
			cvSetList = this.localEditionManage.getCVSetListByEdition(id, "", true);		//false 为绑定的项目
			StrutsUtil.renderText(response, String.valueOf(cvSetList.size()));
		}
		
		return null;
	}
}
