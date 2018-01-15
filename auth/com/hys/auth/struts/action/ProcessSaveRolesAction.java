package com.hys.auth.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.service.local.SystemLogManage;
import com.hys.exam.utils.CusAccessObjectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterSecurityInterceptor;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hys.auth.springsecurity.FilterInvocationDefinitionSourceFactoryBean;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.SystemRole;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;
import com.hys.exam.util.NcmeProperties;

import net.sf.json.JSONObject;

/**
 * 保存角色
 * 
 * @author zdz
 * 
 */
public class ProcessSaveRolesAction extends BaseActionSupport {
	
	//日志打印
	private static final Logger logger=Logger.getLogger(ProcessSaveRolesAction.class);
	
	private SystemRoleFacade systemRoleFacade;
	public void setSystemRoleFacade(SystemRoleFacade systemRoleFacade) {
		this.systemRoleFacade = systemRoleFacade;
	}

	private  SystemLogManage systemLogManage;
	public SystemLogManage getSystemLogManage() {
		return systemLogManage;
	}

	public void setSystemLogManage(SystemLogManage systemLogManage) {
		this.systemLogManage = systemLogManage;
	}
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleName = RequestUtil.getParameter(request, "name");
		String roleNameDesc = RequestUtil.getParameter(request, "nameDesc"); 
		Integer key = 0;
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(roleNameDesc)) {
			SystemRole tempRole = new SystemRole();
			List<SystemRole> list = systemRoleFacade.getListByItem(tempRole);
			for (SystemRole role : list) {
				//校验角色名称是否重复
				if (role.getRoleName().equals(roleName)) {
					json.put("msg", "该角色名称已经存在！");
					StrutsUtil.renderText(response, json.toString());
					return null;
				}
				
				//校验角色标识是否重复
				if (role.getRoleNameDesc().equals(roleNameDesc)) {
					json.put("msg", "该角色标识已经存在！");
					StrutsUtil.renderText(response, json.toString());
					return null;
				}
			}
			SystemRole roles = new SystemRole();
			roles.setRoleName(roleName);
			roles.setRoleNameDesc(roleNameDesc);
			roles.setStatus(1);
			key = systemRoleFacade.save(roles);
			
			//添加角色的时候直接在system_role_resource中添加一条数据，保证所有角色和用户关联后都能登录
			if(key > 0){
				key = systemRoleFacade.saveRoleResourceRelation(key);
			}
			//修复每次添加角色后需要重启角色才能生效的问题
			try {
				ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
				FilterInvocationDefinitionSourceFactoryBean factoryBean = (com.hys.auth.springsecurity.FilterInvocationDefinitionSourceFactoryBean)ctx.getBean("&customFilterInvocationDefinationSource");
				FilterInvocationDefinitionSource fids = (FilterInvocationDefinitionSource) factoryBean.getObject();
				FilterSecurityInterceptor filter = (FilterSecurityInterceptor) ctx.getBean("filterSecurityInterceptor");
				filter.setObjectDefinitionSource(fids);
			} catch (Exception e) {
				//如果出现异常，不会影响其他地方，打印错误信息，以备以后查看日志定位原因
				logger.error("刷新权限异常", e);
			}
			
			//发送请求，刷新资源管理平台的权限	--移除到前端触发
			/*CloseableHttpClient httpClient = null;
			HttpGet httpGet = null;
			CloseableHttpResponse httpResponse = null;
			try {
				httpClient =  HttpClients.createDefault();
				httpGet = new HttpGet(NcmeProperties.getContextPropertyValue("adminURL") + "/system/reloadrole.do");
				httpResponse = httpClient.execute(httpGet);
			} catch (Exception e) {
					logger.error("刷新资源管理权限异常", e);
			} finally {
				//关闭打开的资源
				if (httpGet != null) {
					httpGet.releaseConnection();
				}
				
				if (httpResponse != null) {
					httpResponse.close();
				}
				
				if(httpClient != null) {
					httpClient.close();
				}
			}*/
			
			
			
		} else {
			json.put("msg", "请填写角色标识！");
			
		}	

		if (key > 0) {
			
			json.put("msg",  "角色[" + roleName + "]保存成功！");
			json.put("roleId",key);
			StrutsUtil.renderText(response, json.toString());
			SystemUser currentUser = (SystemUser)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
			SystemLog systemLog = new SystemLog();
			if (null != currentUser) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				systemLog.setUser_id(String.valueOf(currentUser.getUserId()));
				systemLog.setOperate_login_name(currentUser.getRealName());
				systemLog.setModule_name("角色管理");
				systemLog.setOperate_type("add");
				systemLog.setOperate_context("添加角色成功！");
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
			return null;
		}
		return null;
	}

}
