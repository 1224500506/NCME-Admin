package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.model.SystemClient;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CustomerServiceManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.SystemApplicationFacade;
import com.hys.exam.sessionfacade.local.SystemClientFacade;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;
import com.hys.exam.struts.form.SystemUserForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

import net.sf.json.JSONObject;


/**
 * 
 * 标题：客服管理
 * 
 * 作者：吕智
 * 
 * 描述：客服信息管理
 * 
 * 说明:
 */
public class CustomerServiceAction extends AppBaseAction {
        private final String[] editorRoleList = {"13"} ;//客服角色id，目前为13
	//private SystemUserFacade systemUserFacade;
	private SystemClientFacade systemClientFacade;
	private SystemApplicationFacade systemApplicationFacade;
	private SystemRoleFacade systemRoleFacade;
	
	private CustomerServiceManage customerServiceManage ;

	private ExamPropValFacade localExamPropValFacade;
	
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
		}else if (method.equals("insert")){
			
			return this.insert(actionMapping, actionForm, request, response);
		}else if (method.equals("update")){
			
			return this.update(actionMapping, actionForm, request, response);
		}else if (method.equals("view")){
			
			return this.view(actionMapping, actionForm, request, response);
		}else if (method.equals("info")){
			
			return this.info(actionMapping, actionForm, request, response);
		}else if (method.equals("setState")){
			
			return this.setState(actionMapping, actionForm, request, response);
		}else {
			
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
		
		
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
		String propIds = request.getParameter("propIds");
		String propNames = request.getParameter("propNames");
		String jobIds = request.getParameter("jobIds");
		String jobNames = request.getParameter("jobNames");
		
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
		customerServiceManage.querySystemUserPageList(pl, item);

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
			customerServiceManage.save(item);
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
		List<SystemUser> resList = customerServiceManage.isDuplicate(sysUser);
		JSONObject result = new JSONObject();
		if(resList != null && resList.size() > 0)
		{
			result.put("errorMsg", "客服姓名重复！");
			result.put("result", "fail");
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
		
		sysUser = customerServiceManage.getItemByAccountName(item.getAccountName(),null);
		if(sysUser != null) {
			result.put("errorMsg", "此账号已存在！");
			result.put("result", "fail");
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
		if (item != null) {
			Integer userId = customerServiceManage.insert(item); //YHQ，把编辑角色关联在一起，2017-03-11
                        customerServiceManage.updateAccountRole(userId, editorRoleList) ;//YHQ，把编辑角色关联在一起，2017-03-11
			result.put("errorMsg", "添加成功！");
			result.put("result", "success");
			StrutsUtil.renderText(response, result.toString());
		} 
		return null;
	}
	

	//编辑页面
	protected String edit(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "userId", 0L);
		SystemUser item = customerServiceManage.getItemById(id);
		request.setAttribute("item", item);
		
		referenceData(request);
		
		return "edit";
	}

	//查看页面
	protected String view(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int rtype = ParamUtils.getIntParameter(request, "rtype", 0);
		SystemUser item = customerServiceManage.getItemById(id);
		
		SystemUserConfig config = this.customerServiceManage.getSystemUserConfigByUserId(id);

		if (rtype == 0){
			request.setAttribute("item", item);
			request.setAttribute("config", config);
			return "view";
		}
		else{
			JSONObject result = new JSONObject();
			result.put("item", item);
			result.put("config", config);
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
	}
	
	protected String info(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemUser item = customerServiceManage.getItemById(id);

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
		SystemUser item = customerServiceManage.getItemById(newitem.getUserId());
		SystemUser sysUser = new SystemUser();
		sysUser.setUserId(newitem.getUserId());
		sysUser.setRealName(newitem.getRealName());
		
		//2017/01/11, Add by lee 
		//For 编辑姓名重复
		List<SystemUser> resList = customerServiceManage.isDuplicate(sysUser);
		
		JSONObject result = new JSONObject();
		
		if(resList != null && resList.size() > 0)
		{
			result.put("errorMsg", "编辑姓名重复！");
			result.put("result", "fail");
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
		sysUser = customerServiceManage.getItemByAccountName(newitem.getAccountName(),null);
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
				customerServiceManage.update(item);
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
	 	SystemUser item = customerServiceManage.getItemById(id);
			if (item.getUserId()!=null && item.getUserId()>0){
				item.setStatus(state);
				customerServiceManage.update(item);
				customerServiceManage.updateSystemAccount(id,state);
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
				customerServiceManage.delete(id,"id");
			}

		}
		return this.list(actionMapping, actionForm, request, response);
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

	public void setCustomerServiceManage(CustomerServiceManage customerServiceManage) {
		this.customerServiceManage = customerServiceManage;
	}

}
