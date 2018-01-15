package com.hys.exam.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 作者: 赵 明
 * 
 * 时间: 2009-8-21 下午03:33:43
 * 
 * 描述:
 */
public class ApplicationContextUtils {
	private static ApplicationContext applicationContext;
	private static final String[] locations = {
			"applicationContext_transaction.xml",
			"applicationContext_service.xml", "applicationContext_jndi.xml",
			"applicationContext_dao.xml" };
	static {
		if (applicationContext == null)
			applicationContext = rebuildApplicationContext();
	}

	public static ApplicationContext rebuildApplicationContext() {

		return new ClassPathXmlApplicationContext(locations);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		rebuildApplicationContext();
		if (applicationContext == null) {
			System.out.println("ApplicationContext is null");
		} else {
			System.out.println("ApplicationContext is not null!");
		}
	}
}
