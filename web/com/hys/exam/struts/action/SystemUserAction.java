package com.hys.exam.struts.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.model.SystemClient;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.SystemRoleManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.SystemApplicationFacade;
import com.hys.exam.sessionfacade.local.SystemClientFacade;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;
import com.hys.exam.struts.form.SystemUserForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.FileLimitUtil;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;


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
public class SystemUserAction extends AppBaseAction {
        private final String[] editorRoleList = {"3"} ;//YHQ，编辑角色id，目前为1，2017-03-11
	//private SystemUserFacade systemUserFacade;
	private SystemClientFacade systemClientFacade;
	private SystemApplicationFacade systemApplicationFacade;
	private SystemRoleFacade systemRoleFacade;
	private SystemRoleManage localSystemRoleManage;
	
	private SystemUserManage systemUserManage ;

	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public SystemRoleManage getLocalSystemRoleManage() {
		return localSystemRoleManage;
	}

	public void setLocalSystemRoleManage(SystemRoleManage localSystemRoleManage) {
		this.localSystemRoleManage = localSystemRoleManage;
	}

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
		}else if (method.equals("insert")){
			
			return this.insert(actionMapping, actionForm, request, response);
		}else if (method.equals("edit")){
			
			return this.edit(actionMapping, actionForm, request, response);
		}else if (method.equals("update")){
			
			return this.update(actionMapping, actionForm, request, response);
		}else if (method.equals("delete")){
			
			return this.delete(actionMapping, actionForm, request, response);
		}else if (method.equals("view")){
			
			return this.view(actionMapping, actionForm, request, response);
		}else if (method.equals("info")){
			
			return this.info(actionMapping, actionForm, request, response);
		}else if (method.equals("setState")){
			
			return this.setState(actionMapping, actionForm, request, response);
		}else if (method.equals("setPass")){
			
			return this.setPass(actionMapping, actionForm, request, response);
		}else if (method.equals("health")){
			return getHealthData(actionMapping,actionForm,request,response);
		}else if (method.equals("updatenew")){		   //学员管理-编辑功能
			return updateUserNew(actionMapping,actionForm,request,response);
		}else if (method.equals("isDuplicate")){
			return isDuplicate(request, response);
		}else if(method.equals("checkIdCard")){
			return checkIdCard(actionMapping,actionForm, request, response);
		}else if(method.equals("checkIsUnique")){
			return checkIsUnique(actionMapping,actionForm, request, response);
		}else if(method.equals("code")){
			return checkUserCode(actionMapping,actionForm, request, response);
		}else if(method.equals("checkMobile")){
			return checkMobile(actionMapping,actionForm, request, response);
		}else if(method.equals("checkUserName")){
			return checkUserName(actionMapping,actionForm, request, response);
		}else if (method.equals("updateAccount")){     //帐号管理-编辑功能
			return updateAccount(actionMapping,actionForm,request,response);
		}
		else {
			
			return this.list(actionMapping, actionForm, request, response);
		}
	}
	
	private String getHealthData(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		Long Id = ParamUtils.getLongParameter(request, "id", 0L);
		List<PropUnit> list = systemUserManage.getHealthData(Id);
		JSONObject json = new JSONObject();
		json.put("result", list.get(0));
		StrutsUtil.renderText(response, json.toString());
		return null;
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
		
		
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
		//String searchIds =  request.getParameter("searchUnitIds");
		//item.setWorkUnit(searchIds);
		//String searchNames = request.getParameter("searchUnitNames");
		String propIds = request.getParameter("propIds");
		String propNames = request.getParameter("propNames");
		String jobIds = request.getParameter("jobIds");
		String jobNames = request.getParameter("jobNames");
		/*if(!StringUtil.checkNull(names)){
			String[] IDS = names.split(",");
			//Long[] propIDS = new Long[IDS.length];
			//for(int i=0;i<IDS.length;i++) propIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				//tempProp[i].setId(propIDS[i]);
				tempProp[i].setName(IDS[i].trim());
				item.setDeptName(tempProp[i].getName());
			}
				
		}*/
		
		item.setDeptName(propIds);
		item.setProfTitle(jobIds);
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		String sort = ParamUtils.getParameter(request, "sort", "u.reg_date");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion(sort);
		if (dir.equals("asc"))
			pl.setSortDirection(SortOrderEnum.ASCENDING);
		else
			pl.setSortDirection(SortOrderEnum.DESCENDING);
		systemUserManage.querySystemUserPageList(pl, item);

		//取得职称列表
		ExamProp prop = new ExamProp();
	
		//取得省列表
		prop.setType(Integer.valueOf(20));
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
		
		if(item.getUserConfig().getUserProvinceId() != null && !item.getUserConfig().getUserProvinceId().equals(0L))
		{
			ExamPropQuery query  = new ExamPropQuery();
			query.setSort("t.name");
			query.setSysPropId(Long.valueOf(item.getUserConfig().getUserProvinceId()));
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			List<ExamProp> region2List = rprop.getReturnList();
			request.setAttribute("region2List", region2List);
		}
		if(item.getUserConfig().getUserCityId() != null && !item.getUserConfig().getUserCityId().equals(0L))
		{
			ExamPropQuery query  = new ExamPropQuery();
			query.setSort("t.name");
			query.setSysPropId(Long.valueOf(item.getUserConfig().getUserCityId()));
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			List<ExamProp> region3List = rprop.getReturnList();
			request.setAttribute("region3List", region3List);
		}
		request.setAttribute("page", pl);
		request.setAttribute("item", item);
		//request.setAttribute("joblist", joblist);
		request.setAttribute("region1list", region1list);
		//request.setAttribute("searchUnitIds", searchIds);
		//request.setAttribute("searchUnitNames", searchNames);
		
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", propNames);
		request.setAttribute("jobIds", jobIds);
		request.setAttribute("jobNames", jobNames);
		
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
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
	//	item.setStatus(1);//正常
		if(this.isTokenValid(request,true)){
			systemUserManage.save(item);
				request.setAttribute("meg", "新增成功！");
		} else {
				request.setAttribute("meg", "重复提交！");
		}
		aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
	}
	
	protected String insert(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
		item.setStatus(1);
		SystemUser sysUser = new SystemUser();		
		sysUser.setRealName(item.getRealName());
				
		//2017/01/11, Add by lee
		//check 重复
		List<SystemUser> resList = systemUserManage.isDuplicate(sysUser);
		JSONObject result = new JSONObject();
		if(resList != null && resList.size() > 0)
		{
			result.put("errorMsg", "编辑姓名重复！");
			result.put("result", "fail");
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
		
		sysUser = systemUserManage.getItemByAccountName(item.getAccountName(),null);
		if(sysUser != null) {
			result.put("errorMsg", "此账号已存在！");
			result.put("result", "fail");
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
		if (item != null) {
			Integer userId = systemUserManage.insert(item); //YHQ，把编辑角色关联在一起，2017-03-11
                        systemUserManage.updateAccountRole(userId, editorRoleList) ;//YHQ，把编辑角色关联在一起，2017-03-11
			result.put("errorMsg", "添加成功！");
			result.put("result", "success");
			StrutsUtil.renderText(response, result.toString());
		} else {
			result.put("errorMsg", "重复提交！");
			result.put("result", "fail");
			StrutsUtil.renderText(response, result.toString());
		}
		return null;
/*		aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
*/	}
	
	protected String setPass(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String userId = request.getParameter("userId");
		String newPass = request.getParameter("newPass");
		String conPass = request.getParameter("conPass");
		
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
		
		if (item != null) {
			systemUserManage.insert(item);
			request.setAttribute("meg", "新增成功！");
		} else {
			request.setAttribute("meg", "重复提交！");
		}

		aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
	}

	//编辑页面
	protected String edit(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "userId", 0L);
		SystemUser item = systemUserManage.getItemById(id);
		request.setAttribute("item", item);
		
		referenceData(request);
		
		return "edit";
	}

	//查看页面
	protected String view(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Long type = ParamUtils.getLongParameter(request, "type", 0L);
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int rtype = ParamUtils.getIntParameter(request, "rtype", 0);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SystemUser item = systemUserManage.getItemByIdNew(id);
		if (item!=null && item.getRegDate()!=null) {
			item.setRegDatee(sdf.format(item.getRegDate()));
		}
		if (item!=null && item.getLastUpdateDate()!=null) {
			item.setLastUpdateDatee(sdf.format(item.getLastUpdateDate()));
		}
		
		SystemUserConfig config = this.systemUserManage.getSystemUserConfigByUserId(id);
		//Get Job
		//取得职称类型列表
		ExamProp jobclass = new ExamProp();
		jobclass.setType(24);
		List<ExamProp> jobclassList = localExamPropValFacade.getPropListByType(jobclass);

		//Get My Job
		ExamProp myJobClass = new ExamProp();
		myJobClass.setType(9);
		List<ExamProp> myJobClassList = localExamPropValFacade.getPropListByType(myJobClass);

		Integer workExtType = 0;
		String workExtTypeName = "";
		String userJobId = "";
		for(ExamProp examProp: myJobClassList) {
			userJobId = item.getJob_Id();
			String eId = String.valueOf(examProp.getId());
			if(userJobId != null && !userJobId.equals(eId)) {
				continue;
			}
			workExtType = examProp.getExt_type();
		}
		//根据workExtType再次获取(筛选)该职务下的职称
		myJobClass.setExt_type(workExtType);
		myJobClassList = localExamPropValFacade.getPropListByType(myJobClass); 
		
		/*  返回workExtType 职务(例：医疗人员,护理人员等),不需要返回职务名称
		for(ExamProp examProp: jobclassList) {
			if(workExtType != null && examProp.getCode().equals(String.valueOf(workExtType))) {
				workExtTypeName=examProp.getName();
				break;
			}
		}*/
		
		//Xueke levels
		Long propId = 0L,xueke2Id=0L,xueke1Id=0L;
		List<ExamProp> xueke1 = null;
		List<ExamProp> xueke2 = null;
		List<ExamProp> xueke3 = null;
		if (type ==1L || type == 2L) {
			if (item.getPropIds() != null && !item.getPropIds().equals("")) {
				propId = Long.valueOf(item.getPropIds());
				xueke2Id = localExamPropValFacade.getParentPropId(propId);
				xueke1Id = localExamPropValFacade.getParentPropId(xueke2Id);
			}		
		
			//Xueke 1 level List 
			ExamProp query0  = new ExamProp();
			query0.setType(1);
			xueke1 = localExamPropValFacade.getPropListByType(query0);
			
			//Xueke 2 level List
			ExamPropQuery query1  = new ExamPropQuery();
			query1.setSysPropId(xueke1Id);
			ExamReturnProp propOne = localExamPropValFacade.getNextLevelProp(query1);
			xueke2 = propOne.getReturnList();
			
			//Xueke 3 level List
			ExamPropQuery query2  = new ExamPropQuery();
			query2.setSysPropId(xueke2Id);
			ExamReturnProp propTwo = localExamPropValFacade.getNextLevelProp(query2);
			xueke3 = propTwo.getReturnList();
			
		}
		
		//Hospital
		ExamHospital host = new ExamHospital();
		List<ExamHospital> list2 = null ;
		if(item.getWork_unit_id() != null){		//添加自己录入医院名称后，Work_unit_id为空情况--taoliang
			//String userWorkID = String.valueOf(user.getWork_unit_id());
			host.setId(Long.valueOf(item.getWork_unit_id().toString()));
			list2 = localExamPropValFacade.getHospitalList(host);
			String hospitalAddress = null;
			
			// get user hospitalAddress 
			if (list2 != null && list2.size() > 0) {
				for (ExamHospital examHospital : list2){
					if (item.getWork_unit_id() == Integer.parseInt(examHospital.getId().toString())) {
						hospitalAddress = examHospital.getHospital_address();
						break;
					}
				}
				
				if (hospitalAddress != null) {
					request.setAttribute("hosAddress", hospitalAddress);
				} else {
					request.setAttribute("hosAddress", null);
				}
			} else {
				request.setAttribute("hosAddress", null);
			}
		}
		
		
		List<SystemUserType> userTypeList = new ArrayList<SystemUserType>();
		List<SystemRole> roleList = new ArrayList<SystemRole>();
		SystemRole newRole = new SystemRole();
		if (type == 3L || type == 4L) {
			//获取帐号类型
			userTypeList = systemUserManage.getUserTypeList();	
			
			//获取角色
			//设置角色的状态为启用
			newRole.setStatus(1);
			roleList = localSystemRoleManage.getListByItem(newRole);
			item.setRoleList(systemUserManage.getUserRoleList(item.getAccountId()));
		}
		if (rtype == 0) {
			request.setAttribute("item", item);
			request.setAttribute("config", config);
			request.setAttribute("workExtType", workExtType); //职务
			request.setAttribute("jobList", jobclassList);	  //职务列表
			request.setAttribute("userJobId", userJobId); 	  //职称
			request.setAttribute("myJobList", myJobClassList);//职称列表
			request.setAttribute("levelOne", xueke1Id);		  //返回学科
			request.setAttribute("levelTwo", xueke2Id);
			request.setAttribute("xueke1", xueke1);
			request.setAttribute("xueke2", xueke2);
			request.setAttribute("xueke3", xueke3);
			request.setAttribute("hospital", list2);
			request.setAttribute("hospitalAddress", item.getHospitalAddress());
			request.setAttribute("typeList", userTypeList);  //帐号类型
			request.setAttribute("roleList", roleList);		 //角色
			if (type == 1L) {     			//返回学员管理查看页面
				return "view";
			} else if (type == 2L) {    	//返回学员管理编辑页面
				return "edit";    		
			} else if (type == 3L) {		//返回帐号管理查看页面
				return "accountview";    		
			} else if (type == 4L) {		//返回帐号管理编辑页面
				return "accountedit";    		
			} else {						//默认返回编辑管理(编辑详情)页面
				JSONObject result = new JSONObject();
				result.put("workExtTypeName", workExtTypeName);
				result.put("item", item);
				result.put("config", config);
				StrutsUtil.renderText(response, result.toString());
				return null;
			}
		} else {
			request.setAttribute("item", item);
			request.setAttribute("config", config);
			request.setAttribute("workExtType", workExtType); //职务
			request.setAttribute("jobList", jobclassList);	  //职务列表
			request.setAttribute("userJobId", userJobId); 	  //职称
			request.setAttribute("myJobList", myJobClassList);//职称列表
			request.setAttribute("levelOne", xueke1Id);		  //返回学科
			request.setAttribute("levelTwo", xueke2Id);
			request.setAttribute("xueke1", xueke1);
			request.setAttribute("xueke2", xueke2);
			request.setAttribute("xueke3", xueke3);
			request.setAttribute("hospital", list2);
			request.setAttribute("hospitalAddress", item.getHospitalAddress());
			request.setAttribute("typeList", userTypeList);  //帐号类型
			request.setAttribute("roleList", roleList);		 //角色
			if (type == 1L) {     			//返回学员管理查看页面
				return "view";
			} else if (type == 2L) {    	//返回学员管理编辑页面
				return "edit";    		
			} else if (type == 3L) {		//返回帐号管理查看页面
				return "accountview";    		
			} else if (type == 4L) {		//返回帐号管理编辑页面
				return "accountedit";    		
			} else {						//默认返回编辑管理(编辑详情)页面
				JSONObject result = new JSONObject();
				result.put("workExtTypeName", workExtTypeName);
				result.put("item", item);
				result.put("config", config);
				StrutsUtil.renderText(response, result.toString());
				return null;
			}
		}
	}
	
	protected String info(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemUser item = systemUserManage.getItemById(id);

		JSONObject result = new JSONObject();
		result.put("item", item);
		if (item.getRegDate() != null) result.put("regDate", item.getRegDate().toString());
		
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
	
	//更新
	protected String update(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser newitem = aform.getModel();
		SystemUser item = systemUserManage.getItemById(newitem.getUserId());
		SystemUser sysUser = new SystemUser();
		sysUser.setUserId(newitem.getUserId());
		sysUser.setRealName(newitem.getRealName());
		
		//2017/01/11, Add by lee 
		//For 编辑姓名重复
		List<SystemUser> resList = systemUserManage.isDuplicate(sysUser);
		
		JSONObject result = new JSONObject();
		
		if(resList != null && resList.size() > 0)
		{
			result.put("errorMsg", "编辑姓名重复！");
			result.put("result", "fail");
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
		sysUser = systemUserManage.getItemByAccountName(newitem.getAccountName(),null);
		if(sysUser != null) {
			if(!sysUser.getUserId().equals(item.getUserId()))
			{
				result.put("errorMsg", "此账号已存在！");
				result.put("result", "fail");
				StrutsUtil.renderText(response, result.toString());
				return null;
			}
		}
			if (item.getUserId()!=null && item.getUserId()>0){
				item.setRealName(newitem.getRealName());
				item.setDeptName(newitem.getDeptName());
				item.setAccountName(newitem.getAccountName());
				item.setMobilPhone(newitem.getMobilPhone());
				item.setPropIds(newitem.getPropIds());
				systemUserManage.update(item);
				result.put("errorMsg", "修改成功！");
				result.put("result", "success");
			}else
			{
				result.put("errorMsg", "修改失败！");
				result.put("result", "fail");
			}
			StrutsUtil.renderText(response, result.toString());
			return null;
/*			aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
*/		//	return "list";
	}

	//修改状态
	protected String setState(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int state = ParamUtils.getIntParameter(request, "state", 0);
		String opinion = request.getParameter("opinion");
	 	SystemUser item = systemUserManage.getItemByIdNew(id);
			if (item.getUserId()!=null && item.getUserId()>0){
				item.setStatus(state);
				if (opinion == null || opinion.equals("")) {
					item.setReason(null);
					item.setReasondate(null);
				} else {
					item.setReason(opinion);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					item.setReasondate(sdf.format(new Date()));
				}
				systemUserManage.update(item);
				systemUserManage.updateSystemAccount(id,state);
				StrutsUtil.renderText(response, "success");
			}
		return null;
	}

	//删除 
	protected String delete(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long[] selIdArr = ParamUtils.getLongParameters(request, "selId", 0L);
		if(selIdArr!=null && selIdArr.length>0){
			for (long id : selIdArr ){
				systemUserManage.delete(id,"id");
			}

		}
		return this.list(actionMapping, actionForm, request, response);
	}

	//学员管理-编辑功能(新方法)
	protected String updateUserNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUserForm aform = (SystemUserForm)form;
		SystemUser regUserForm = aform.getModel();
		SystemUser user = systemUserManage.getItemById(regUserForm.getUserId());
		if(user == null) {
			return "fail";
		}
				
		SystemUser sysUser = new SystemUser();
		sysUser.setUserId(user.getUserId());										//学员ID
		sysUser.setRealName(regUserForm.getRealName());								//学员真实姓名
		sysUser.setAccountName(regUserForm.getAccountName());						//学员帐号
		
		sysUser.setCertificateType(regUserForm.getCertificateType());				//证件类别
		sysUser.setCertificateNo(regUserForm.getCertificateNo());					//证件号
		
		sysUser.setSex(regUserForm.getSex());
		sysUser.setEducation(regUserForm.getEducation());							//学历
		
		sysUser.setWorkUnit(regUserForm.getWorkUnit());								//单位名称
		sysUser.setWork_unit_id(regUserForm.getWork_unit_id());						//单位ID
		sysUser.setUserType(1);
		sysUser.setPropIds(String.valueOf(regUserForm.getXueke3()));				//学科
		
		sysUser.setWorkExtType(String.valueOf(regUserForm.getAccount_status()));    //职务类型ID
		sysUser.setJob_Id(regUserForm.getProfTitle());								//职称ID
		
		sysUser.setHospitalAddress(regUserForm.getHospitalAddress());				//单位地址
		
		sysUser.setMobilPhone(regUserForm.getMobilPhone());							//手机号码
		sysUser.setEmail(regUserForm.getEmail());									//邮箱地址
		
		sysUser.setGrassroot(regUserForm.getGrassroot());							//是否来自基层1:是   0:否
		sysUser.setHealth_certificate(regUserForm.getHealth_certificate());
		
		int no = systemUserManage.updatenew(sysUser);
		response.sendRedirect("systemUserStudent.do?method=list&model.userType=1");
		return null;
	}
	//帐号管理-编辑功能(新方法)
	protected String updateAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUserForm aform = (SystemUserForm)form;
		SystemUser regUserForm = aform.getModel();
		SystemUser user = systemUserManage.getItemById(regUserForm.getUserId());
		if(user == null) {
			return "fail";
		}
				
		SystemUser sysUser = new SystemUser();
		sysUser.setUserType(user.getUserType());									//用户帐号类型
		sysUser.setUserId(user.getUserId());										//用户ID
		sysUser.setRealName(regUserForm.getRealName());								//用户真实姓名
		sysUser.setAccountName(regUserForm.getAccountName());						//用户帐号
		sysUser.setAccountId(user.getAccountId());									//用户帐号ID
		
		sysUser.setCertificateType(regUserForm.getCertificateType());				//证件类别
		sysUser.setCertificateNo(regUserForm.getCertificateNo());					//证件号
		
		sysUser.setPropIds(String.valueOf(regUserForm.getPropIds()));				//学科(可能有多个学科ID)
		sysUser.setSex(regUserForm.getSex());										//性别
		
		sysUser.setMobilPhone(regUserForm.getMobilPhone());							//手机号码
		sysUser.setRoleIds(String.valueOf(regUserForm.getRoleIds()));				//角色(可能有多个角色ID)
		
		/*
		sysUser.setEducation(regUserForm.getEducation());							//学历
		sysUser.setWorkUnit(regUserForm.getWorkUnit());								//单位名称
		sysUser.setWork_unit_id(regUserForm.getWork_unit_id());						//单位ID
		sysUser.setWorkExtType(String.valueOf(regUserForm.getAccount_status()));    //职务类型ID
		sysUser.setJob_Id(regUserForm.getProfTitle());								//职称ID
		sysUser.setHospitalAddress(regUserForm.getHospitalAddress());				//单位地址
		*/
		
		int no = systemUserManage.updateAccount(sysUser);
		response.sendRedirect("/Admin/systemManage/getAccounts.do");
		return null;
	}
	
	/**
	 * Detail : Check the account name is existing.
	 */
	private String isDuplicate(HttpServletRequest request,
			HttpServletResponse response) {
		
		String userId = request.getParameter("userId");
		String accountName = request.getParameter("accountName");
		// Check user is existing.
		SystemUser checkUser = new SystemUser();
		if (!StringUtils.checkNull(userId)) {
			checkUser.setUserId(Long.valueOf(userId));
			checkUser.setAccountName(accountName);
			List<SystemUser> checkList = systemUserManage.isDuplicate(checkUser);
			if (checkList != null && checkList.size() > 0) {
				StrutsUtil.renderText(response, "exist");	
			} else {
				StrutsUtil.renderText(response, "no");
			}
		}		
		return null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2017-01-15
	 * @throws   Exception
	 * 方法说明： 检测该身份证号码是否已经注册
	 */
	protected String checkIdCard(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String idCard = request.getParameter("idCard");
		String userId = request.getParameter("userId");
		Map<String,Object> map = new HashMap<String,Object>();
		if (idCard!=null && !"".equals(idCard)) {
			// 检测该身份证号码是否已经注册
			int isExist = systemUserManage.checkIdCard(idCard,Integer.parseInt(userId));
			if (isExist==0) {
				map.put("message", "success");
			} else {
				map.put("message", "fail");
			}
		} else {
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	/**
	 * 方法说明： 检测该邮箱是否已经注册
	 */
	protected String checkIsUnique(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String email = request.getParameter("email");
		String userId = request.getParameter("userId");
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(email)){
			//检测该邮箱是否已经注册
			int isExist = systemUserManage.checkEmail(email,Integer.parseInt(userId));
			if (isExist==0) {
				map.put("message", "success");
			} else {
				map.put("message", "fail");
			}
		} else {
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	/**
	 * @throws 验证执业医师号是否存在
	 */
	protected String checkUserCode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String code = request.getParameter("code");
		String userId = request.getParameter("userId");

		int isExist = systemUserManage.checkUserCode(code,Integer.parseInt(userId));
		if (isExist==0) {
			map.put("message", "yes");
		} else {
			map.put("message", "no");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	/**
	 * 方法说明： 检测该手机号是否已经注册
	 */
	protected String checkMobile(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取要检测的手机号码
		String mobilPhone = request.getParameter("mobilPhone");
		String userId = request.getParameter("userId");
		Map<String,Object> map = new HashMap<String,Object>();
		if (mobilPhone!=null && !"".equals(mobilPhone)) {
			//根据手机号码查询是否存在
			int isExist = systemUserManage.checkMobile(mobilPhone, Integer.parseInt(userId));
			if (isExist==0) {
				map.put("message", "success");
			} else {
				map.put("message", "fail");
			}
		} else {
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	/**
	 * 方法说明：检测用户名是否可用
	 */
	
	protected String checkUserName(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accountName = request.getParameter("accountName");
		String userId = request.getParameter("userId");
		Map<String,Object> map = new HashMap<String,Object>();
		if(accountName!=null && !"".equals(accountName)){
			//根据用户名查询是否可用
			int isExist = systemUserManage.checkUserName(accountName, Integer.parseInt(userId));
			if(isExist==0){
				map.put("message", "success");
			}else{
				map.put("message", "fail");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
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

	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}

}
