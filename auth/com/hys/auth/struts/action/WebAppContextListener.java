package com.hys.auth.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hys.auth.util.LicenseManage;
import com.hys.exam.constants.Constants;
import com.hys.exam.util.NcmeProperties;

public class WebAppContextListener implements ServletContextListener {
	private ServletContext context = null;
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		context = null;
		System.out.println("关闭信息！");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		Constants.validateLicense = true;
		event.getServletContext().setAttribute("ctxPropertiesMap", NcmeProperties.getCtxPropertiesMap()); //YHQ，2017-05-12
		
//		
//		//验证license
//		if (!LicenseManage.validateLicense()){
//			System.out.println("许可证无效,请重新导入许可证!");
//		}else{
//			Constants.validateLicense = true;
//			System.out.println("许可证通过,感谢使用本产品!");
//		}
		
	}
	
}