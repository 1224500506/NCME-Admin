package com.hys.auth.util;

/*
 * Created on 2005-3-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author sunan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DateUtils {

	static Calendar cld;

	public static final long YEAR = 10000000000l;

	public static final long MONTH = 100000000;

	public static final long DAY = 1000000;

	public static final long HOUR = 10000;

	public static final long MINUTE = 100;

	public static long newDate() {
		long date = 0;
		return date;
	}

	public static long setYear(long date, long year) {
		date = year * YEAR + date % YEAR;
		return date;
	}

	public static long setYear(long date, String year) {
		long thisYear = 0;
		try {
			thisYear = Long.parseLong(year);
		} catch (Exception e) {
			return date;
		}

		return setYear(date, thisYear);
	}

	public static long getYear(long date) {

		return date / YEAR;
	}

	public static long setMonth(long date, long month) {
		long year = getYear(date);
		date = date - year * YEAR;
		date = month * MONTH + date % MONTH;

		return year * YEAR + date;
	}

	public static long setMonth(long date, String month) {
		long thisMonth = 0;
		try {
			thisMonth = Long.parseLong(month);
		} catch (Exception e) {
			return date;
		}

		return setMonth(date, thisMonth);
	}

	public static long getMonth(long date) {
		long year = getYear(date);
		date = date - year * YEAR;

		return date / MONTH;
	}

	public static long setDay(long date, long day) {
		long year = getYear(date);
		long month = getMonth(date);
		date = (date - year * YEAR) - month * MONTH;
		date = day * DAY + date % DAY;

		return year * YEAR + month * MONTH + date;
	}

	public static long setDay(long date, String day) {
		long thisDay = 0;
		try {
			thisDay = Long.parseLong(day);
		} catch (Exception e) {
			return date;
		}

		return setDay(date, thisDay);
	}

	public static long getDay(long date) {
		long year = getYear(date);
		long month = getMonth(date);
		date = date - year * YEAR - month * MONTH;

		return date / DAY;
	}

	public static long setHour(long date, long hour) {
		long thisDate = 0;
		long year = getYear(date);
		long month = getMonth(date);
		long day = getDay(date);

		thisDate = date % HOUR;
		thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
				+ thisDate;

		return thisDate;
	}

	public static long setHour(long date, String hour) {
		long thisHour = 0;
		try {
			thisHour = Long.parseLong(hour);
		} catch (Exception e) {
			return date;
		}

		return setHour(date, thisHour);

	}

	public static long getHour(long date) {
		long hour = 0;
		hour = date % DAY;
		hour = hour / HOUR;
		return hour;
	}

	public static long setMinute(long date, long minute) {
		long thisDate = 0;
		long year = getYear(date);
		long month = getMonth(date);
		long day = getDay(date);
		long hour = getHour(date);

		thisDate = date % MINUTE;
		thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
				+ minute * MINUTE + thisDate;

		return thisDate;
	}

	public static long setMinute(long date, String minute) {
		long thisMinute = 0;
		try {
			thisMinute = Long.parseLong(minute);
		} catch (Exception e) {
			return date;
		}

		return setMinute(date, thisMinute);
	}

	public static long getMinute(long date) {
		long minute = 0;

		minute = date % HOUR;
		minute = minute / MINUTE;

		return minute;
	}

	public static long setSecond(long date, long second) {
		long thisDate = 0;
		long year = getYear(date);
		long month = getMonth(date);
		long day = getDay(date);
		long hour = getHour(date);
		long minute = getMinute(date);

		thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
				+ minute * MINUTE + second;

		return thisDate;
	}

	public static long setSecond(long date, String second) {
		long thisSecond = 0;
		try {
			thisSecond = Long.parseLong(second);
		} catch (Exception e) {
			return date;
		}

		return setSecond(date, thisSecond);
	}

	public static long getSecond(long date) {
		long second = 0;
		second = date % MINUTE;

		return second;
	}

	public static long getNowDate() {
		long date = 0;
		Date nowDate = new Date();
		DateFormat df = DateFormat.getDateInstance();
		String sDate = df.format(nowDate);
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}
		start = end + 1;
		day = sDate.substring(start);
		/**
		 * debug  start
		 */
		//                System.out.println("year: " + year + "/month: " + month + "/day: " + day);
		/**
		 * debug  end
		 */
		date = setYear(date, year);
		date = setMonth(date, Long.parseLong(month));
		date = setDay(date, day);

		return date;
	}

	public static long getNowDateTime() {
		long date = 0;

		Date nowDate = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		String sDate = df.format(nowDate);
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		String second = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(" ", start);
		if (end > 0) {
			day = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			hour = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			minute = sDate.substring(start, end);
		}

		start = end + 1;
		second = sDate.substring(start);

		/**
		 * debug  start
		 */
		//                System.out.println("year: " + year + "/month: " + month + "/day: " + day + "/hour: " + hour + "/minute: " + minute + "/second: " + second + "\n" + sDate);
		/**
		 * debug  end
		 */

		date = setYear(date, year);
		date = setMonth(date, month);
		date = setDay(date, day);
		date = setHour(date, hour);
		date = setMinute(date, minute);
		date = setSecond(date, second);
		return date;
	}

	public static String getDate(long date, String separator) {
		String returnDate = null;
		String day = null;
		String month = null;
		String year = null;
		year = Long.toString(getYear(date));
		if (getMonth(date) < 10) {
			month = "0" + Long.toString(getMonth(date));
		} else {
			month = Long.toString(getMonth(date));
		}

		if (getDay(date) < 10) {
			day = "0" + Long.toString(getDay(date));
		} else {
			day = Long.toString(getDay(date));
		}
		returnDate = year + separator + month + separator + day;

		return returnDate;
	}

	public static String getDate(long date) {
		String returnDate = "";
		String day = null;
		String month = null;
		String year = null;
		year = Long.toString(getYear(date));
		if (getMonth(date) < 10) {
			month = "0" + Long.toString(getMonth(date));
		} else {
			month = Long.toString(getMonth(date));
		}

		if (getDay(date) < 10) {
			day = "0" + Long.toString(getDay(date));
		} else {
			day = Long.toString(getDay(date));
		}
		returnDate = year + "-" + month + "-" + day;

		return returnDate;
	}

	public static String getDateCn(long date) {
		String returnDate = "";
		String day = null;
		String month = null;
		String year = null;
		String hour = null;
		String minute = null;
		String second = null;

		year = Long.toString(getYear(date));
		if (getMonth(date) < 10) {
			month = "0" + Long.toString(getMonth(date));
		} else {
			month = Long.toString(getMonth(date));
		}

		if (getDay(date) < 10) {
			day = "0" + Long.toString(getDay(date));
		} else {
			day = Long.toString(getDay(date));
		}

		if (getHour(date) < 10) {
			hour = "0" + Long.toString(getHour(date));
		} else {
			hour = Long.toString(getHour(date));
		}

		if (getMinute(date) < 10) {
			minute = "0" + Long.toString(getMinute(date));
		} else {
			minute = Long.toString(getMinute(date));
		}

		if (getSecond(date) < 10) {
			second = "0" + Long.toString(getSecond(date));
		} else {
			second = Long.toString(getSecond(date));
		}

		returnDate = year + "年" + month + "月" + day + "日" + hour + "点" + minute
				+ "分" + second + "秒";

		return returnDate;

	}

	public static long getDateTime(Date someDate) {
		long date = 0;

		Date nowDate = someDate;
		DateFormat df = DateFormat.getDateTimeInstance();
		String sDate = df.format(nowDate);
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		String second = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(" ", start);
		if (end > 0) {
			day = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			hour = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			minute = sDate.substring(start, end);
		}

		start = end + 1;
		second = sDate.substring(start);

		/**
		 * debug  start
		 */
		//                System.out.println("year: " + year + "/month: " + month + "/day: " + day + "/hour: " + hour + "/minute: " + minute + "/second: " + second + "\n" + sDate);
		/**
		 * debug  end
		 */

		date = setYear(date, year);
		date = setMonth(date, month);
		date = setDay(date, day);
		date = setHour(date, hour);
		date = setMinute(date, minute);
		date = setSecond(date, second);
		return date;
	}

	public static long stringToLong(String someDate) {
		long date = 0;

		String sDate = someDate;
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		String second = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(" ", start);
		if (end > 0) {
			day = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			hour = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			minute = sDate.substring(start, end);
		}

		start = end + 1;
		second = sDate.substring(start);

		/**
		 * debug  start
		 */
		//                System.out.println("year: " + year + "/month: " + month + "/day: " + day + "/hour: " + hour + "/minute: " + minute + "/second: " + second + "\n" + sDate);
		/**
		 * debug  end
		 */

		date = setYear(date, year);
		date = setMonth(date, month);
		date = setDay(date, day);
		date = setHour(date, hour);
		date = setMinute(date, minute);
		date = setSecond(date, second);
		return date;
	}

	public static Date longToDate(Long param) {
		if (cld == null) {
			cld = new GregorianCalendar();
		}
		if (param != null) {
			cld.clear();
			cld.setTimeInMillis(param);
			return cld.getTime();
		} else {
			return null;
		}
	}

	public static long dateToLong(Date param) {
		long date = param.getTime();
		return date;
	}

	public static long dateToLong(int day) {
		Date date = new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:ss");
		//        String dateString = formatter.format(date);
		//        System.out.println(dateString);
		return date.getTime();
	}

	public static long dateToLong(Date date, int day) {
		//Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		return date.getTime();
	}
	
	public static Date dateAdd(Date date, int day) {
		//Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		return date;
	}

	/**
	 * 月份添加
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateAddByMonth(Date date, int month) {
		//Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, month);// 把日期往后增加月份.整数往后推,负数往前移动
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * Parse a datetime string.
	 * @param param datetime string, pattern: "yyyy-MM-dd HH:mm:ss".
	 * @return java.util.Date
	 */
	public static Date parse(String param) {
		Date date = null;
		if (param == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse(param);
			} catch (ParseException ex) {
			}
			return date;
		}
	}
	
	/**
	 * 格式化时间字符串
	 * @param param  
	 * @param dateFormat
	 * @return
	 */
	public static Date parse(String param,String dateFormat) {
		Date date = null;
		if (param == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			try {
				date = sdf.parse(param);
			} catch (ParseException ex) {
			}
			return date;
		}
	}

	 /**    
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12   
     * @param date2 被比较的时间  为空(null)则为当前时间    
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年    
     * @return    
     * 举例：  
     * compareDate("2009-09-12", null, 0);//比较天  
     * compareDate("2009-09-12", null, 1);//比较月  
     * compareDate("2009-09-12", null, 2);//比较年  
     */    
	public static int compareDate(String startDateStr,String endDateStr,int stype){     
	    int n = 0;     
	    String[] u = {"天","月","年"};     
	    String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";     
	         
	    endDateStr = endDateStr==null?getCurrentDate("yyyy-MM-dd"):endDateStr;     
	         
	    DateFormat df = new SimpleDateFormat(formatStyle);
	    
	    Calendar c1 = Calendar.getInstance();     
	    Calendar c2 = Calendar.getInstance();     
	    try {     
	        c1.setTime(df.parse(startDateStr));     
	        c2.setTime(df.parse(endDateStr));     
	    } catch (Exception e3) {     
	        System.out.println("wrong occured");     
	    }     
	    //List list = new ArrayList();     
	    while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果     
	        //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来     
	        n++;     
	        if(stype==1){     
	            c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1     
	        }     
	        else{     
	            c1.add(Calendar.DATE, 1);           // 比较天数，日期+1     
	        }     
	    }     
	    n = n-1;     
	    if(stype==2){     
	        n = (int)n/365;     
	    }        
//	    System.out.println(startDateStr+" -- "+endDateStr+" 相差多少"+u[stype]+":"+n);           
	    return n;     
	}      

	/**
	 * 当前格式化时间 
	 * @param format "yyyy-MM-dd"
	 * @return
	 */
	public static String getCurrentDate(String format){   
	   Calendar day=Calendar.getInstance();    
	   day.add(Calendar.DATE,0);    
	   SimpleDateFormat sdf=new SimpleDateFormat(format);//"yyyy-MM-dd"   
	   
	   return sdf.format(day.getTime());   
	} 
	
	
	/***
	 * 
	 * 日期转字符串---taoLiang
	 * @param date
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String DateToString(Date date){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		 return sdf.format(date);  
	 
	}
	
	public static void main(String[] args) {
			Date nextMonthDate = dateAddByMonth(new Date(), 2);
//			System.out.println("date--"+nextMonthDate);
		//		long lastEditTime = 10101010101l;
		//		System.out.println(lastEditTime);
		//		System.out.println(DateUtils.longToDate(lastEditTime));
		//		System.out.println(DateUtils.dateToLong(new Date()));
		//		System.out.println(DateUtils.longToDate(new Long("1215402693041")));
		//		System.out.println(dateToLong(0));
				System.out.println(compareDate("2009-08-10", null, 2));
	}
}
