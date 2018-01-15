package com.hys.auth.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataList;
import com.hys.auth.model.HysRoles;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.PageList;
import com.hys.auth.util.PageUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.StringUtils;
import com.hys.exam.model.HysUserRoleProp;

/**
 * 
 * 标题：用户管理-获取用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class UserManageGetUsersAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		
		if (method.equals("list")) {
			return search(actionMapping, actionForm, request, response);
		} else if (method.equals("search")) {
			return search(actionMapping, actionForm, request, response);
		} else if (method.equals("setPass")) {
			return setPass(actionMapping, actionForm, request, response);
		}else if (method.equals("modify")) {
			return modify(actionMapping, actionForm, request, response);
		} else {
			return search(actionMapping, actionForm, request, response);
		}
	}
	
	/**
	 * 取得系统用户信息
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String columnValue = RequestUtil.getParameter(request, "cvalue");
		logger.info("得到参数：cvalue=" + columnValue);
		PageList pageList = new PageList();
		int pageNumber = PageUtil.getPageIndex(request);
		pageList.setPageNumber(pageNumber);

		List<HysUsers> results = facade.getUsersList(columnValue, pageList);
		pageList.setList(results);
		request.setAttribute("page", pageList);
		return Constants.SUCCESS;
	}

	/**
	 * 查询
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String search(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		List<String> searchList = new ArrayList<String>();
		searchList.add(request.getParameter("realName"));
		searchList.add(request.getParameter("sex"));
		searchList.add(request.getParameter("workUnit"));
		searchList.add(request.getParameter("status"));
		searchList.add(request.getParameter("loginName"));
		searchList.add(request.getParameter("deptNames"));
		
		String userType = (request.getParameter("serchRoleIds"));
		String columnValue = RequestUtil.getParameter(request, "cvalue");
		logger.info("得到参数：cvalue=" + columnValue);
		PageList pageList = new PageList();
		int pageNumber = PageUtil.getPageIndex(request);
		pageList.setPageNumber(pageNumber);

		List<HysRoles> temproles = facade.getRolesList();
		List<HysRoles> roles = new ArrayList<HysRoles>();
		for(int i = 0 ; i < temproles.size() ; i++){
			if(temproles.get(i).getStatus() == 1){
				roles.add(temproles.get(i));
			}
		}
		
//		List<HysUsers> results = facade.getUsersList(columnValue, pageList);
		List<HysUsers> tempResults = facade.getSearchList(searchList, pageList);
		List<HysUsers> results = new ArrayList<HysUsers>();
		for(HysUsers e : tempResults)
		{
			List<HysUserRoleProp> userRoles = facade.getUserRoleList(e.getId());
			String roleName = "";
			String roleIds = "";
			for(HysUserRoleProp userRole : userRoles)
			{
				for(int i = 0 ; i < temproles.size() ; i++)
				{
					if(userRole.getRoleid().equals(temproles.get(i).getId()))
					{
						roleIds += temproles.get(i).getId() + ",";
						roleName += temproles.get(i).getNameDesc() + ",";
					}
				}
			}
			if(!roleName.equals(""))
			{
				roleName = roleName.substring(0, roleName.length() - 1);
			}
			if(!roleIds.equals(""))
			{
				roleIds = roleIds.substring(0, roleIds.length() - 1);
			}
			e.setRoleName(roleName);
			e.setRoleIds(roleIds);
			if(userType != null  && !userType.equals("请选择")){
				if(e.getRoleName().contains(userType)) 
				{
					results.add(e);
				}
			}
			else
			{
				results.add(e);
			}
		}
		pageList.setList(results);
		
		
		request.setAttribute("realName", request.getParameter("realName"));
		request.setAttribute("userType", request.getParameter("serchRoleIds"));
		request.setAttribute("sex", request.getParameter("sex"));
		request.setAttribute("workUnit", request.getParameter("workUnit"));
		request.setAttribute("status", request.getParameter("status"));
		request.setAttribute("loginName", request.getParameter("loginName"));
		request.setAttribute("deptNames", request.getParameter("deptNames"));
		request.setAttribute("page", pageList);
		RequestUtil.setAttribute(request, "roles", roles);
		return Constants.SUCCESS;
	}
	
	/**
	 * 设置密码
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String setPass(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		Long id = Long.valueOf(request.getParameter("userId"));
		String newPass = request.getParameter("newPass");
		
		HysUsers user = new HysUsers();
		user.setId(id);
		user.setPassword(newPass);

		int result = facade.updatePassword(user);
		if(result != 0)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");	
		}
		return null;
	}
	
	/**
	 * 修改
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String modify(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		Long id = Long.valueOf(request.getParameter("userId"));
		String newPass = request.getParameter("newPass");
		
		HysUsers user = new HysUsers();
		user.setId(id);
		user.setPassword(newPass);

		facade.updatePassword(user);

		String columnValue = RequestUtil.getParameter(request, "cvalue");
		logger.info("得到参数：cvalue=" + columnValue);
		PageList pageList = new PageList();
		int pageNumber = PageUtil.getPageIndex(request);
		pageList.setPageNumber(pageNumber);

		List<HysUsers> results = facade.getUsersList(columnValue, pageList);
		pageList.setList(results);
		request.setAttribute("page", pageList);
		return Constants.SUCCESS;
	}
	
}
