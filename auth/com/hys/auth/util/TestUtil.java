package com.hys.auth.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hys.auth.dao.ResourceDAO;
import com.hys.auth.model.HysResources;


public class TestUtil {


	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext_jndi.xml",
						"src/applicationContext_dao.xml" });
		ResourceDAO dao = (ResourceDAO) ctx.getBean("resourceDAO");
		
		HysResources u = new HysResources();
		u.setName("aaa");
		u.setType(1);
		u.setValue("usdfs");
		
		dao.save(u);
		
		
		
//		List list = new ArrayList();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		System.out.println(org.apache.commons.lang.StringUtils.join(list.iterator(), ","));
		
		

	}

}
