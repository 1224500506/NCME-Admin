package com.hys.auth.struts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.service.local.SystemLogManage;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextImpl;
import org.springframework.security.userdetails.User;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.utils.CusAccessObjectUtil;
import com.hys.exam.utils.MD5Util;


/**
 * 
 * 标题：用户登录
 * 
 * 作者：zdz，2009 7 24
 * 
 * 描述：
 * 
 * 说明:
 */
public class LoginAction extends BaseActionSupport {

	private SystemUserManage localSystemUserManage;
	
	private SystemMenuManage localSystemMenuManage;

	private  SystemLogManage systemLogManage;

	public SystemMenuManage getLocalSystemMenuManage() {
		return localSystemMenuManage;
	}

	public void setLocalSystemMenuManage(SystemMenuManage localSystemMenuManage) {
		this.localSystemMenuManage = localSystemMenuManage;
	}

	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	public SystemLogManage getSystemLogManage() {
		return systemLogManage;
	}

	public void setSystemLogManage(SystemLogManage systemLogManage) {
		this.systemLogManage = systemLogManage;
	}

	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @return   String
	 * 方法说明： 系统登录方法
	 */
	protected String actionExecute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
        //if (!Constants.validateLicense) 
        //throw new Exception("许可证无效,请重新导入许可证!");
		SecurityContextImpl secturityContext = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = secturityContext.getAuthentication();
		User user = (User) auth.getPrincipal();
		
		/*** 用户解锁 ***/
		SystemUser item = new SystemUser();
		if(isNumeric(user.getUsername()) == true){
			item.setMobilPhone(user.getUsername());
		}else if(user.getUsername().indexOf("@")!= -1){
			item.setEmail(user.getUsername());
		}else{
			item.setAccountName(user.getUsername());
		}
		List<SystemUser> userList = localSystemUserManage.getListByItemCopy(item);
		if(userList==null||userList.size()==0){
			request.getSession().setAttribute("erro", "不存在此用户!");
			return "success";
    	}else{
    		SystemUser userC = userList.get(0);
    		SystemConfigVO vo = localSystemUserManage.getLoginLimtVo();
    		//做重置操作
    		long  now = System.currentTimeMillis();
    		Long last = userC.getLoginErrorLastTime();
    		
			//1.已锁定，但是超过限制时间可以重置限制
			if( last != 0 && ((now - last) > vo.getLockTime()*60*60*1000)){
				//重置
				userC.setLoginErrorFirstTime(0L);
				userC.setLoginErrorLastTime(0L);
				userC.setLoginErrorNum(0);
			}else if(last != 0 && ((now - last) <=vo.getLockTime()*60*60*1000)){
				request.getSession().setAttribute("erro", "用户被锁定，请"+vo.getLockTime()+"小时后再登录！");
				return "success";
			}else if(last == 0){
				//重置
				userC.setLoginErrorFirstTime(0L);
				userC.setLoginErrorLastTime(0L);
				userC.setLoginErrorNum(0);
			}
			localSystemUserManage.updateLoginErrors(userC);
    			
    	}
		/*** 用户解锁 ***/
		
		// 把用户id设置到session中
		request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME",user.getUsername());
		request.getSession().setAttribute("SPRING_SECURITY_LAST_PASSWORD",user.getPassword());
		//HysUsers currentUser = facade.valid(user.getUsername(), user.getPassword());
		SystemUser currentUser = localSystemUserManage.getItemByAccountName(user.getUsername(), user.getPassword());
		
		if(currentUser == null){
			request.getSession().setAttribute("erro", "不存在此用户!");
			return "success";
		}
		
		request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER, currentUser);
		SystemLog systemLog = new SystemLog();
		if (null != currentUser) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			systemLog.setUser_id(String.valueOf(currentUser.getUserId()));
			systemLog.setOperate_login_name(currentUser.getRealName());
			systemLog.setModule_name("登录");
			systemLog.setOperate_type("login");
			systemLog.setOperate_context("登录学习培训后台成功！");
			systemLog.setVisit_ip(CusAccessObjectUtil.getIpAddress(request));
			systemLog.setRequest_url(request.getRequestURI());
			systemLog.setOperate_platform("peixun");
			systemLog.setOperate_time(sdf.format(new Date()));
			systemLog.setCreate_time(sdf.format(new Date()));
			try {
				systemLogManage.addSystemLog(systemLog);
			} catch (Exception e){
				logger.error("添加审计日志期间发生异常:" + e.getMessage());
			}

		}

		/***********************  查询栏目信息开始    ***********************************/
		
		//第一步、查询需要显示的一级栏目
		/*
		List<SystemMenu> menuResult = new ArrayList<SystemMenu>();
		List<SystemMenu> menuOneList = localSystemMenuManage.getOneLevelMenu();
		for(int i=0;i<menuOneList.size();i++){
			SystemMenu one = menuOneList.get(i);
			//第二步、根据一级名称查询可用的二级菜单
			List<SystemMenu> secondMenuList = localSystemMenuManage.getTwoLevelMenu(one.getMenu_type(),currentUser.getUserId().longValue());
			//判断一级下是否包含子栏目
			if(secondMenuList!=null && secondMenuList.size()>0){
				one.setSystemMenuList(secondMenuList);
				menuResult.add(one);
			}
		}
		request.getSession().setAttribute("menu", menuResult);
		*/
		
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
		
		/***********************  查询栏目信息结束    ***********************************/
		
		return "success";
	}
	
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
	
}
