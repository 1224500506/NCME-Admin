package com.hys.exam.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.OrgManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.OrgForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息管理
 * 
 * 说明:
 */
public class OrgManageAction extends AppBaseAction {
	//private SystemUserFacade systemUserFacade;
	
	private OrgManage localOrgManage;
	
	private ExamPropValFacade localExamPropValFacade;
	
	private SystemUserManage localSystemUserManage;
	
	private String fileDir = "upload/Org";
	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	public SystemUserManage getSystemUserManage() {
		return systemUserManage;
	}

	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}

	private SystemUserManage systemUserManage ;

	public OrgManage getLocalOrgManage() {
		return localOrgManage;
	}

	public void setLocalOrgManage(OrgManage localOrgManage) {
		this.localOrgManage = localOrgManage;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	@Override
	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		if (method.equals("list")){
			return this.list(actionMapping, actionForm, request, response);
		}else if(method.equals("add")){
			
			return this.add(actionMapping, actionForm, request, response);
		
		}else if(method.equals("test")){
			return this.duplicateTest(actionMapping, actionForm, request, response);
		}else if(method.equals("update")){
			
			return this.update(actionMapping, actionForm, request, response);
		
		}else if(method.equals("view")){
		
			return this.view(actionMapping, actionForm, request, response);
		
		}else if (method.equals("setState")){
			
			return this.setstate(actionMapping, actionForm, request, response);
		}else if (method.equals("getListByAjax")){
			return this.getlistByAjax(actionMapping, actionForm, request, response);
		}else if (method.equals("getOrgByType")){
			return this.getOrgByType(actionMapping, actionForm, request, response);
		}
		else {
			return list(actionMapping, actionForm, request, response);
		}
	}
	
	 

	private String getOrgByType(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		int type = Integer.parseInt(request.getParameter("type"));
		PeixunOrg item = new PeixunOrg();
		item.setType(type);
		List<PeixunOrg> data = localOrgManage.queryOrgList(item);				
		JSONObject result = new JSONObject();
		result.put("item", data);
		StrutsUtil.renderText(response, result.toString());
		return null;
	
	}

	private String duplicateTest(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		 String type=request.getParameter("type");
		 
		 String TestStr=request.getParameter("param");
		 String accountName=request.getParameter("accountName");
		 OrgForm aform = (OrgForm)actionForm;
			PeixunOrg item = aform.getModel();
		if(type.equals("1")){
			 /*String result="updatestr";
			 StrutsUtil.renderText(response, result);
			 return null;*/
			String id = request.getParameter("id");
			PeixunOrg org = localOrgManage.getItemById(Long.valueOf(id));
			//修改时如果机构名称改变，就检查是否重复
			if(!org.getName().equals(TestStr)){
				item.setName(TestStr);
				String result= localOrgManage.Duplicate(item);
				if(!result.equals("fail")){
					StrutsUtil.renderText(response, result);
					return null;
				}
			}
			//修改时如果机构账号改变，就检查是否重复
			if(!org.getAccountName().equals(accountName)){
				String returnAccountName= localOrgManage.duplicateAccountName(accountName);
				StrutsUtil.renderText(response, returnAccountName);
			}
		 }else{
			 if(!StringUtil.checkNull(TestStr) && !TestStr.equals(""))
		 	   {
			 	item.setName(TestStr);
				String result= localOrgManage.Duplicate(item);	
					if(result.equals("fail")){
						String returnAccountName= localOrgManage.duplicateAccountName(accountName);
						StrutsUtil.renderText(response, returnAccountName);
					}else{
						StrutsUtil.renderText(response, result);
					}
			    } 
		 }
		 
		return null;
	}

	private String getlistByAjax(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		OrgForm aform = (OrgForm)actionForm;
		PeixunOrg item = aform.getModel();
		//设置机构状态为启用状态
		item.setState(1);
		List<PeixunOrg> data = localOrgManage.queryOrgList(item);				
		JSONObject result = new JSONObject();
		result.put("item", data);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	//查询
	protected String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		OrgForm aform = (OrgForm)actionForm;
		PeixunOrg item = aform.getModel();
		// paging code added by Tiger.
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		String sort = ParamUtils.getParameter(request, "sort", "id");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion(sort);
		if (dir.equals("asc"))
			pl.setSortDirection(SortOrderEnum.ASCENDING);
		else
			pl.setSortDirection(SortOrderEnum.DESCENDING);
		
		localOrgManage.queryOrgPageList(pl, item);
		// end.
		//List<PeixunOrg> data = localOrgManage.queryOrgList(item);
		List<ExamHospital> hosArray = localOrgManage.getHospital();
		//List<SystemAccount> accountArray = localOrgManage.getAccount();

		ExamProp prop = new ExamProp();
		prop.setType(Integer.valueOf(9));
		
		prop.setType(Integer.valueOf(20));
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
		
		List<ExamPropVal> region2list = localExamPropValFacade.getCityList();
		
		request.setAttribute("item", item);
		request.setAttribute("page", pl);
		request.setAttribute("hosArry", hosArray);
		//request.setAttribute("accountarry", accountArray);

		request.setAttribute("region1list", region1list);
		request.setAttribute("region2list", region2list);
		
		return "list";
	}
	
	protected String add(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		OrgForm aform = (OrgForm)actionForm;		
		PeixunOrg item = aform.getModel();
		item.setState(1);
		Long orgId = 0L;
		if(item.getName() != null){
			SystemUser newUser = new SystemUser();
			newUser.setStatus(item.getState());
			newUser.setUserType(item.getType());
			newUser.setAccountName(item.getAccountName());
			newUser.setRealName(item.getName());
			Integer userId = localSystemUserManage.insert(newUser);
			item.setUser_Id(Long.valueOf(userId.toString()));
			if(userId<=0){
				request.setAttribute("flag", "succuess");
			}else{
				 //Logo图片
				item.setFilePath(aform.getLogoPath());
				//机构封面图片
				item.setPhotoPath(aform.getOrgPath());
				//执行保存机构
				orgId =  localOrgManage.addPeixunOrg(item);
				/*FormFile filedata = aform.getModelfile();
				item.setId(orgId);
				if (filedata != null && !filedata.getFileName().equals(""))
				{
					FilesUtils.fileUpload(filedata, request, fileDir, orgId, item.getName(), "");
					item.setFilePath(fileDir+"/"+orgId);
				}
				FormFile photodata = aform.getPhotofile();
				if (photodata != null && !photodata.getFileName().equals(""))
				{
					FilesUtils.fileUpload(photodata, request, fileDir, orgId, item.getName(), "photo");
					item.setPhotoPath(fileDir+"/photo/"+orgId);
				}*/
				//localOrgManage.updatePeixunOrg(item);
			}
		}
		//response.sendRedirect("peixunOrglist.do?method=list");
	    Map<String,String> map = new HashMap<String,String>();
		if(orgId>0L){
			map.put("message", "success");
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	protected String view(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response)
	{
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		PeixunOrg data = localOrgManage.getItemById(id);
		
		JSONObject result = new JSONObject();
		result.put("item", data);
		/*
		 * Date    : 2017/01/11
		 * Author  : lee
		 * Explain : it's mistake logic when get Hospital Data
		 */
		//List<ExamHospital> hosArray = localOrgManage.getHospital();
		//Integer count = hosArray.size();
		if(data.getHospital_Id() != null && data.getHospital_Id() > 0L) {
			ExamHospital host = new ExamHospital();
			host.setId(data.getHospital_Id());
			host = localExamPropValFacade.getHospitalById(host);
			
			result.put("hospital", host);
		}
		
		//result.put("count", count);
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}
	
	
	protected String update(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OrgForm aform = (OrgForm)actionForm;
		PeixunOrg item = aform.getModel();
		if(item.getName() != null && item.getName() != "")
		{	
			PeixunOrg org = localOrgManage.getItemById(item.getId());
			//当机构账号修改时，更新账号信息
			if (!org.getAccountName().equals(item.getAccountName()) || !org.getName().equals(item.getName())) {
				SystemUser user = localSystemUserManage.getItemById(item.getAccountId());
				user.setAccountName(item.getAccountName());
				user.setRealName(item.getName());
				localSystemUserManage.update(user);
			}
			
			Long orgId = item.getId();
			 //Logo图片
			item.setFilePath(aform.getLogoPath());
			//机构封面图片
			item.setPhotoPath(aform.getOrgPath());
//			item.setRegion1_Id();
			/*FormFile filedata = aform.getModelfile();
			item.setId(orgId);
			if (filedata != null && !filedata.getFileName().equals(""))
			{
				item.setFilePath(FilesUtils.fileUpload(filedata, request, fileDir, orgId, item.getName(), ""));
				item.setFilePath(fileDir+"/"+orgId);
				
			}
			FormFile photodata = aform.getPhotofile();
			if (photodata != null && !photodata.getFileName().equals(""))
			{
				FilesUtils.fileUpload(photodata, request, fileDir, orgId, item.getName(), "photo");
				item.setPhotoPath(fileDir+"/photo/"+orgId);
			}*/
			localOrgManage.updatePeixunOrg(item);
			
		}
 		//response.sendRedirect("peixunOrglist.do?method=list");
		 Map<String,String> map = new HashMap<String,String>();
			map.put("message", "success");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	
	protected String setstate(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		localOrgManage.setState(id);	
		StrutsUtil.renderText(response, "success");
		
		return null;
	}	
}












