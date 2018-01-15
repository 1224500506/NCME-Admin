package com.hys.exam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hys.exam.utils.StringUtils;

//YHQ,2017-06-22,ncme3个函数to_date、to_char、to_number迁移到分布式数据库采用mysql标准的函数代替
public class FuncMySQL {
	private static final SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String[] longDateForUpdate(Date argDate) {
		String[] argVal = new String[2] ;
		argVal[0] = " str_to_date(?,'%Y-%m-%d %H:%i:%S') " ;
		argVal[1] = longDateFormat.format(argDate) ;
		
		return argVal ;
	}
	
	public static String longDateForUpdateNoArg(Date argDate) {
		String argVal = " str_to_date('" + longDateFormat.format(argDate) + "','%Y-%m-%d %H:%i:%S') " ;		
		return argVal ;
	}
	
	public static String longDateForUpdate() {
		String argVal = " str_to_date(?,'%Y-%m-%d %H:%i:%S') " ;		
		return argVal ;
	}
	
	public static String longDateForInsert(String dbFiledName) {
		String argVal = " str_to_date(:" + dbFiledName + ",'%Y-%m-%d %H:%i:%S') " ;		
		return argVal ;
	}
	
	public static String shortDateForInsert(String dbFiledName) {
		String resSQL = " date(:" + dbFiledName + ") " ;				
		return resSQL ;
	}
	
	public static String shortDateForInsert(String argDateVal, String dbFiledName) {
		String resSQL = "" ;
		
		if (StringUtils.isNotBlank(argDateVal)) {
			resSQL = " date(:" + dbFiledName + ") " ;
		} else {
			resSQL = " date(null) " ;
		}
		
		return resSQL ;
	}
	
	public static String shortDateForUpdate(String argDateVal) {
		String resSQL = "" ;
		
		if (StringUtils.isNotBlank(argDateVal)) {
			resSQL = " date('" + argDateVal.trim() + "') " ;
		} else {
			resSQL = " date(null) " ;
		}
		
		return resSQL ;
	}
}
