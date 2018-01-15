package com.hys.exam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test1 {


	public static void main(String[] args) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM EXAM_EXAMINATION WHERE exists (select * from exam_exam_type t1 where exam_type_id = t1.id and code like CONCAT((select code from exam_exam_type where id=?),'%'))");		
		sql.append(" order by create_time desc");	
		
		System.out.print(replaceLikeParam(sql.toString(), "name", 0));

	}
	
	
	private static boolean isEnna(String p) {
		int length = p.length();
		boolean b = true;
		for (int i = 0; i < length; i++) {
			char c = p.charAt(i);
			if (c > 255) {
				b = false;
				break;
			}
		}
		return b;
	}
	
	public static String getWebRealPath(){
		String web_path = Test1.class.getResource("/").getPath();
		System.out.println(web_path);
		System.out.println(System.getProperty("os.name"));
		web_path=web_path.substring(1, web_path.indexOf("WEB-INF"));
		return web_path;
	}

	
	public static Connection getConnection(String url, String user,
			String password) {
		try {
			if (url.startsWith("jdbc:oracle")) {
				// Oracle8/8i/9i
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			} else if (url.startsWith("jdbc:db2")) {
				// DB2
				Class.forName("com.ibm.db2.jdbc.app.DB2Driver ").newInstance();
			} else if (url.startsWith("jdbc:microsoft")) {
				// Sql Server2000
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver")
						.newInstance();
			} else if (url.startsWith("jdbc:mysql")) {
				// MySQL
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			}
			return DriverManager.getConnection(url, user, password);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String replaceLikeParam(String sql,String paramName,int type){
		switch (type) {
		case 1:
			sql = sql.toString().replaceAll("( " + paramName.toLowerCase() + " = \\?){1}", " " + paramName + " like CONCAT(?,'%')");
			break;
		case 2:
			sql = sql.toString().replaceAll("( " + paramName.toLowerCase() + " = \\?){1}", " " + paramName + " like CONCAT('%',?)");
			break;
		default:
			sql = sql.toString().replaceAll("( " + paramName.toLowerCase() + " = \\?){1}", " " + paramName + " like CONCAT('%',CONCAT(?,'%'))");
			break;
		}
		return sql;
	}	

}
