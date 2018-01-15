package com.hys.exam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 标题：资源管理平台
 * 
 * 作者：chenlaibin 2015-09-25
 * 
 * 描述：读取配置文件
 * 
 * 说明:
 */
@SuppressWarnings("serial")
public class DBConfiger extends Properties {

	private static DBConfiger instance;

	private static InputStream in;

	private DBConfiger() {
		try {
			in = DBConfiger.class.getClassLoader().getResourceAsStream("site.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static DBConfiger getInstance() {
		if (instance == null) {
			instance = new DBConfiger();
			try {
				instance.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public static void refresh() {
		DBConfiger newInstance = new DBConfiger();
		try {
			newInstance.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		instance = newInstance;
	}
	
}
