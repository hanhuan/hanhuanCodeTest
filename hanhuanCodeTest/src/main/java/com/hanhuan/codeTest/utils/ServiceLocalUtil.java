package com.hanhuan.codeTest.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLocalUtil {
	private static ApplicationContext context = null;

	public static ApplicationContext getContext() {
		if (null == context) {
			context = new ClassPathXmlApplicationContext(
					new String[] { "/WEB-INF/spring/appServlet/servlet-context.xml" });
		}
		return context;
	}

	public static ApplicationContext getContext(String[] SERVICE_FACTORY_PATH) {
		if (null == context) {
			context = new ClassPathXmlApplicationContext(SERVICE_FACTORY_PATH);
		}
		return context;

	}
	
 
}
