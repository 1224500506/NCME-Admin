package com.hys.exam.struts.action.system;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.auth.util.*;
import com.hys.exam.common.util.BrowerEncodeingSwitch;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemLog;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysUsers;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.exam.struts.action.AppBaseAction;

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
public class SystemMenuManageAction extends AppBaseAction {
	private SystemMenuManage localSystemMenuManage;

	public SystemMenuManage getLocalSystemMenuManage() {
		return localSystemMenuManage;
	}

	public void setLocalSystemMenuManage(SystemMenuManage localSystemMenuManage) {
		this.localSystemMenuManage = localSystemMenuManage;
	}

	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		
		if (method.equals("search")) {
			return search(actionMapping, actionForm, request, response);
		} else if (method.equals("add")) {
			return addMenu(actionMapping, actionForm, request, response);
		}
		else if (method.equals("update")) {
				return updateMenu(actionMapping, actionForm, request, response);
		}
		else if (method.equals("updateState")) {
			return updateState(actionMapping, actionForm, request, response);
		}
		else if (method.equals("systemlog")) {
			return systemlog(actionMapping, actionForm, request, response);
		}
		else if (method.equals("exportExecl")) {
			return exportExecl(actionMapping, actionForm, request, response);
		}
		else if(method.equals("delsystemlog")) {
			return del(actionMapping, actionForm, request, response);
		}else if(method.equals("getNewMenus")){
			return getNewMenus(actionMapping, actionForm, request, response);
		}else {
			return search(actionMapping, actionForm, request, response);
		}
	}
	private String del(ActionMapping mapping, ActionForm form,
					   HttpServletRequest request, HttpServletResponse response) {

		Long del_id = ParamUtils.getLongParameter(request, "id", 0L);
		boolean flag = localSystemMenuManage.deleteSystemLog(del_id);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
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

		SystemMenu searchMenu = new SystemMenu();
		searchMenu.setName(request.getParameter("menuName"));
		
		String system = request.getParameter("system_type");
		if(system != null && !system.equals("全部") && !system.equals(""))
		{
			searchMenu.setSystem_type(system);
		}
		searchMenu.setMenu_type(request.getParameter("menu_type"));
		String state = request.getParameter("state");
		if(state != null && !state.equals(""))
		{
			searchMenu.setState(Integer.valueOf(state));	
		}
		List<SystemMenu> menuList = localSystemMenuManage.getMenuList(searchMenu);
		
		//YHQ,2017-08-13
		SystemMenu allMenu = new SystemMenu();
		List<SystemMenu> allMenuList = localSystemMenuManage.getMenuList(allMenu);
		List<SystemMenu> peixunMenuList = new ArrayList<SystemMenu>() ;//后台的菜单
		List<SystemMenu> adminMenuList  = new ArrayList<SystemMenu>() ;//资源的菜单
		for (SystemMenu smObj : allMenuList) {
			String system_type = smObj.getSystem_type() ;
			String menu_type   = smObj.getMenu_type() ;
			if (system_type != null && system_type.trim().equals("资源管理系统")) {
				boolean haveTwoMenu = false ;
				for (SystemMenu adminSMobj : adminMenuList) {
					if (menu_type != null && menu_type.trim().equals(adminSMobj.getMenu_type())) {
						haveTwoMenu = true ;
						List<SystemMenu> adminMenuTwoList = adminSMobj.getSystemMenuList() ;
						if (adminMenuTwoList == null) {
							adminMenuTwoList  = new ArrayList<SystemMenu>() ;
						}
						adminMenuTwoList.add(smObj) ;
						adminSMobj.setSystemMenuList(adminMenuTwoList);
					}
				}
				if (!haveTwoMenu && menu_type != null) {
					SystemMenu adminSM = new SystemMenu() ;
					adminSM.setMenu_type(menu_type);
					List<SystemMenu> adminMenuTwoList = new ArrayList<SystemMenu>() ;
					adminMenuTwoList.add(smObj) ;
					adminSM.setSystemMenuList(adminMenuTwoList);					
					adminMenuList.add(adminSM) ;
				}
			} else if (system_type != null && system_type.trim().equals("后台管理系统")) {
				boolean haveTwoMenu = false ;
				for (SystemMenu peixunSMobj : peixunMenuList) {
					if (menu_type != null && menu_type.trim().equals(peixunSMobj.getMenu_type())) {
						haveTwoMenu = true ;
						List<SystemMenu> peixunMenuTwoList = peixunSMobj.getSystemMenuList() ;
						if (peixunMenuTwoList == null) {
							peixunMenuTwoList  = new ArrayList<SystemMenu>() ;
						}
						peixunMenuTwoList.add(smObj) ;
						peixunSMobj.setSystemMenuList(peixunMenuTwoList);
					}
				}
				if (!haveTwoMenu && menu_type != null) {
					SystemMenu peixunSM = new SystemMenu() ;
					peixunSM.setMenu_type(menu_type);
					List<SystemMenu> peixunMenuTwoList = new ArrayList<SystemMenu>() ;
					peixunMenuTwoList.add(smObj) ;
					peixunSM.setSystemMenuList(peixunMenuTwoList);					
					peixunMenuList.add(peixunSM) ;
				}
			}
		}
		request.setAttribute("adminMenuList", adminMenuList);
		request.setAttribute("peixunMenuList", peixunMenuList);
		
		request.setAttribute("page", menuList);
		request.setAttribute("menuName", searchMenu.getName());
		request.setAttribute("system_type", system);
		request.setAttribute("menu_type", searchMenu.getMenu_type());
		request.setAttribute("state", searchMenu.getState());
		request.setAttribute("totalCount", menuList.size());
		return Constants.SUCCESS;
	}

	/**
	 * 审查日志查询
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	public String systemlog(ActionMapping actionMapping, ActionForm actionForm,
							 HttpServletRequest request, HttpServletResponse response)throws Exception{


		String loginName=request.getParameter("loginName");
		String operatePlatform=request.getParameter("operatePlatform");
		String operateType=request.getParameter("operateType");
		String start_date=request.getParameter("start_date");
		String end_date=request.getParameter("end_date");

		SystemLog systemLog = new SystemLog();
		systemLog.setStart_date(start_date);
		systemLog.setEnd_date(end_date);
		systemLog.setOperate_type(operateType);
		systemLog.setOperate_login_name(loginName);
		systemLog.setOperate_platform(operatePlatform);

		List<SystemLog> menuList = localSystemMenuManage.getSystemLogList(systemLog);

		//YHQ,2017-08-13
		SystemMenu allMenu = new SystemMenu();
		List<SystemMenu> allMenuList = localSystemMenuManage.getMenuList(allMenu);
		List<SystemMenu> peixunMenuList = new ArrayList<SystemMenu>() ;//后台的菜单
		List<SystemMenu> adminMenuList  = new ArrayList<SystemMenu>() ;//资源的菜单
		for (SystemMenu smObj : allMenuList) {
			String system_type = smObj.getSystem_type() ;
			String menu_type   = smObj.getMenu_type() ;
			if (system_type != null && system_type.trim().equals("资源管理系统")) {
				boolean haveTwoMenu = false ;
				for (SystemMenu adminSMobj : adminMenuList) {
					if (menu_type != null && menu_type.trim().equals(adminSMobj.getMenu_type())) {
						haveTwoMenu = true ;
						List<SystemMenu> adminMenuTwoList = adminSMobj.getSystemMenuList() ;
						if (adminMenuTwoList == null) {
							adminMenuTwoList  = new ArrayList<SystemMenu>() ;
						}
						adminMenuTwoList.add(smObj) ;
						adminSMobj.setSystemMenuList(adminMenuTwoList);
					}
				}
				if (!haveTwoMenu && menu_type != null) {
					SystemMenu adminSM = new SystemMenu() ;
					adminSM.setMenu_type(menu_type);
					List<SystemMenu> adminMenuTwoList = new ArrayList<SystemMenu>() ;
					adminMenuTwoList.add(smObj) ;
					adminSM.setSystemMenuList(adminMenuTwoList);
					adminMenuList.add(adminSM) ;
				}
			} else if (system_type != null && system_type.trim().equals("后台管理系统")) {
				boolean haveTwoMenu = false ;
				for (SystemMenu peixunSMobj : peixunMenuList) {
					if (menu_type != null && menu_type.trim().equals(peixunSMobj.getMenu_type())) {
						haveTwoMenu = true ;
						List<SystemMenu> peixunMenuTwoList = peixunSMobj.getSystemMenuList() ;
						if (peixunMenuTwoList == null) {
							peixunMenuTwoList  = new ArrayList<SystemMenu>() ;
						}
						peixunMenuTwoList.add(smObj) ;
						peixunSMobj.setSystemMenuList(peixunMenuTwoList);
					}
				}
				if (!haveTwoMenu && menu_type != null) {
					SystemMenu peixunSM = new SystemMenu() ;
					peixunSM.setMenu_type(menu_type);
					List<SystemMenu> peixunMenuTwoList = new ArrayList<SystemMenu>() ;
					peixunMenuTwoList.add(smObj) ;
					peixunSM.setSystemMenuList(peixunMenuTwoList);
					peixunMenuList.add(peixunSM) ;
				}
			}
		}
		request.setAttribute("adminMenuList", adminMenuList);
		request.setAttribute("peixunMenuList", peixunMenuList);

		request.setAttribute("page", menuList);
		request.setAttribute("loginName", loginName);
		request.setAttribute("operateType", operateType);
		request.setAttribute("operatePlatform", operatePlatform);
		request.setAttribute("start_date", start_date);
		request.setAttribute("end_date", end_date);
		request.setAttribute("totalCount", menuList.size());

		return "systemlog";
	}
	public String exportExecl(ActionMapping actionMapping, ActionForm actionForm,
							HttpServletRequest request, HttpServletResponse response)throws Exception{
	String loginName=request.getParameter("loginName");
		String operateType=request.getParameter("operateType");
		String operatePlatform=request.getParameter("operatePlatform");
		String start_date=request.getParameter("start_date");
		String end_date=request.getParameter("end_date");

		SystemLog systemLog = new SystemLog();
		systemLog.setStart_date(start_date);
		systemLog.setEnd_date(end_date);
		systemLog.setOperate_login_name(loginName);
		systemLog.setOperate_type(operateType);
		systemLog.setOperate_platform(operatePlatform);
		request.setAttribute("loginName", loginName);
		request.setAttribute("operateType", operateType);
		request.setAttribute("operatePlatform", operatePlatform);
		request.setAttribute("start_date", start_date);
		request.setAttribute("end_date", end_date);
		// 如果文件名有中文，必须URL编码         HH:mm:ss
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

		String  fileName="审查日志"+simpleDateFormat.format(new Date())+".xls";

		String content_disposition= BrowerEncodeingSwitch.getContentDisposition(fileName,request);

		response.setHeader("Content-disposition",content_disposition);
		List<SystemLog> list = localSystemMenuManage.getSystemLogList(systemLog);
		HSSFWorkbook wb = localSystemMenuManage.export(list);
		response.setContentType("application/vnd.ms-excel");

		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
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
	public String addMenu(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		String menuName = request.getParameter("menuName");
		SystemMenu menu = new SystemMenu();
		menu.setSystem_type(request.getParameter("systemType"));
		menu.setMenu_type(request.getParameter("menuType"));
		menu.setName(menuName);
		menu.setUrl(request.getParameter("menuUrl"));
		menu.setState(1);
		Boolean flag = localSystemMenuManage.getSystemMenuByName(menuName);
		if(flag){
			Boolean result = localSystemMenuManage.addMenu(menu);
			if(result){
				StrutsUtil.renderText(response, "success");
			} else {
				StrutsUtil.renderText(response, "fail");
			}
		}else{
			StrutsUtil.renderText(response, "yes");
		}
		return null;
	}
	public String updateMenu(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		SystemMenu menu = new SystemMenu();
		String id = request.getParameter("id");
		if(id != null && !id.equals(""))
		{
			menu.setId(Integer.valueOf(id));
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
			return null;
		}
		menu.setSystem_type(request.getParameter("systemType"));
		menu.setMenu_type(request.getParameter("menuType"));
		menu.setName(request.getParameter("menuName"));
		menu.setUrl(request.getParameter("menuUrl"));
		String state = request.getParameter("state");
		if(state != null && !state.equals(""))
		{
			menu.setState(Integer.valueOf(state));	
		}		
		Boolean result = localSystemMenuManage.updateMenu(menu);
		if(result)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	
	public String updateState(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String temid = request.getParameter("id");
		Integer id = null;
		if(temid != null && !temid.equals(""))
		{
			id = Integer.valueOf(temid);
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
			return null;
		}
		String temstate = request.getParameter("oldState");
		Integer oldState = null;
		if(temstate != null && !temstate.equals(""))
		{
			oldState = Integer.valueOf(temstate);
			if(oldState == 1)
			{
				oldState = 2;
			}
			else
			{
				oldState = 1;
			}
		}		
		Boolean result = localSystemMenuManage.updateState(id,oldState);
		if(result)
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
	 * 启用停用菜单后重新刷新top上的菜单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private String getNewMenus(ActionMapping mapping, ActionForm form,
			   HttpServletRequest request, HttpServletResponse response) throws Exception{

		SystemUser currentUser = (SystemUser)request.getSession().getAttribute("SESSION_TRAIN_ADMIN_USER");
		//YHQ，2017-03-16，重新实现
		List<SystemMenu> menuResult = new ArrayList<SystemMenu>();
		Hashtable<String,List<SystemMenu>> oneLevelMenuHT = new Hashtable<String,List<SystemMenu>>() ;
		List<SystemMenu> userAllMenuList = localSystemMenuManage.getTwoLevelMenu("",currentUser.getUserId().longValue()) ;		
		for (SystemMenu smObj : userAllMenuList) {
			String oneLevelMenuStr = smObj.getMenu_type() ;
			if (!oneLevelMenuHT.containsKey(oneLevelMenuStr)) {
				List<SystemMenu> twoLevelMenuList = new ArrayList<SystemMenu>();
				twoLevelMenuList.add(smObj) ;
				smObj.setSystemMenuList(twoLevelMenuList);
				menuResult.add(smObj) ;
				oneLevelMenuHT.put(oneLevelMenuStr, twoLevelMenuList) ;
			} else {
				List<SystemMenu> twoLevelMenuList = oneLevelMenuHT.get(oneLevelMenuStr) ;
				twoLevelMenuList.add(smObj) ;
				for (SystemMenu mrObj: menuResult) {
					if (mrObj.getMenu_type().equals(oneLevelMenuStr)) {
						mrObj.setSystemMenuList(twoLevelMenuList);
					}
				}
			}
		}
		request.getSession().setAttribute("menu", menuResult);
		return search(mapping, form, request, response);
	}
}
