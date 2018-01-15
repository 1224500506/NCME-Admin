package com.es;

import java.io.File;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2016 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   ESTest.java
 *     
 *     Description       :   操作ES对象存储测试
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2016-11-22                                    张建国	           Created
 ********************************************************************************
 */

public class ESTest {
	
	public static void main(String[] args) throws Exception {
		//System.out.println(ESUtil.deleteObject("zhangjianguo", "ceshi/????????(5).txt"));	
		
		System.out.println(ESUtil.getObjectList("zhangjianguo", "ceshi/"));	
		
		
		//File file = new File("D:\\apache-tomcat-7.0.53\\webapps\\admin\\upload\\zjg");
		
		//System.out.println(file.isDirectory());
		
		//System.out.println(ESUtil.updateFile(new File("D:\\zjg.txt"), "ceshi/", "zhangjianguo"));

	}
	
	
}
