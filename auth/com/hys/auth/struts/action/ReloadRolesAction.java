package com.hys.auth.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterSecurityInterceptor;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hys.auth.springsecurity.FilterInvocationDefinitionSourceFactoryBean;
import com.hys.auth.util.StrutsUtil;

/**
 * 刷新角色
 * 每次添加角色的时候，已经刷新。提供该action是防止以后系统改成分布式的，对外暴露刷新权限的接口
 * @author lvzhi
 * 
 */
public class ReloadRolesAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//修复每次添加角色后需要重启角色才能生效的问题
		try {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			FilterInvocationDefinitionSourceFactoryBean factoryBean = (com.hys.auth.springsecurity.FilterInvocationDefinitionSourceFactoryBean)ctx.getBean("&customFilterInvocationDefinationSource");
			FilterInvocationDefinitionSource fids = (FilterInvocationDefinitionSource) factoryBean.getObject();
			FilterSecurityInterceptor filter = (FilterSecurityInterceptor) ctx.getBean("filterSecurityInterceptor");
			filter.setObjectDefinitionSource(fids);
		} catch (Exception e) {
			//如果出现异常，不会影响其他地方，打印错误信息，以备以后查看日志定位原因
			StrutsUtil.renderText(response, "error");
			return null;
		}
		StrutsUtil.renderText(response, "success!");
		return null;
	}
	
}
