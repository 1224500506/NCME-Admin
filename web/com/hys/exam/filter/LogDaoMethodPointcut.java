package com.hys.exam.filter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class LogDaoMethodPointcut implements MethodInterceptor {

	private static final Logger logger = Logger.getLogger(LogDaoMethodPointcut.class);
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		logger.info("BeforeInvoke----" + arg0.getThis().getClass()+"---"+arg0.getMethod().getName());
        return arg0.proceed(); 
	}

}
