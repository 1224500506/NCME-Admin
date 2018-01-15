package com.hys.exam.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * 标题：考试后台支撑系统
 * 
 * 作者：张伟清 2013-03-13
 * 
 * 描述：日期工具类
 * 
 * 说明:
 */
public class DateUtil {

	/**
	 * 英文简写（默认）如：2010-12
	 */
	public static String FORMAT_SHORTER = "yyyy-MM";
	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";

	/**
	 * 英文全称如：2010-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	/**
	 * 中文简写如：2010年12月01日
	 */
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

	/**
	 * 中文全称如：2010年12月01日23时15分06秒
	 */

	public static String FORMAT_LONG_CN = "yyyy年MM月dd日HH时mm分ss秒";

	/**
	 * 精确到毫秒的完整中文时间
	 */
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";

	/**
	 * 年
	 */
	public static String FORMAT_YEAR = "yyyy";

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	/**
	 * 根据预设格式返回当前日期
	 * @return
	 */
	public static String getNow() {
		return format(new Date());
	}

	/**
	 * 根据用户格式返回当前日期
	 * @param format
	 * @return
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}

	/**
	 * 使用预设格式格式化日期
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用用户格式格式化日期
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}

	/**
	 * 使用预设格式提取字符串日期
	 * @param strDate 日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	/**
	 * 使用用户格式提取字符串日期
	 * @param strDate 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		if (strDate == null || "".equals(strDate)) {
			return null;
		}

		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 使用用户格式提取字符串日期
	 * @param strDate 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date parse(Date strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			String tempDate = df.format(strDate) ;
			return df.parse(tempDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 在日期上增加数个整月
	 * @param date 日期
	 * @param n 要增加的月数
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加天数
	 * @param date 日期
	 * @param n 要增加的天数
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 获取时间戳
	 */

	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取日期年份
	 * @param date 日期
	 * @return
	 */
	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}

	/**
	 * 按默认格式的字符串距离今天的天数
	 * @param date 日期字符串
	 * @return
	 */
	public static int countDays(String date) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}
	
	public static int countMonths(Date d1, Date d2) {
		return Math.abs(calculateIntervalMonths(d1, d2));
	}

	/**
	 * 按用户格式字符串距离今天的天数
	 * @param date 日期字符串
	 * @param format 日期格式
	 * @return
	 */
	public static int countDays(String date, String format) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, format));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}
	
	/**
	 * 按用户格式字符串距离今天的天数
	 * @param date 日期
	 * @return
	 */
	public static int countDays(Date date) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}

	/**
	 * 计算结束时间距离开始时间天数
	 */
	public static int getStartAndEndDays(Date startTime, Date endTime) {
		Calendar start = Calendar.getInstance(); start.setTime(startTime);
		Calendar end = Calendar.getInstance(); end.setTime(endTime);
		long _start = start.getTime().getTime() ;
		long _end 	= end.getTime().getTime() ;
		return Math.abs((int) (_end / 1000 - _start / 1000) / 3600 / 24);
	}
	
	/**
	 * 取得当前月最大天数
	 * @return
	 */
	public static int getMonthMaxDay(){
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(new Date()) ;
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) ;
	}

	/**
	 * 取得当前月最小天数
	 * @return
	 */
	public static int getMonthMinDay(){
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(new Date()) ;
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) ;
	}
	
	/**
	 * 取得每月最大天数
	 * @param date
	 * @return
	 */
	public static int getMonthMaxDay(String date){
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(parse(date)) ;
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) ;
	}

	/**
	 * 取得每月最小天数
	 * @param date
	 * @return
	 */
	public static int getMonthMinDay(String date){
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(parse(date)) ;
		return calendar.getActualMinimum(Calendar.DAY_OF_MONTH) ;
	}
	
	/**
	 * 得到本月的第一天
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 得到本月的最后一天
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 按用户格式字符串取得每月最大天数
	 * @param date
	 * @return
	 */
	public static int getMonthMaxDay(String date, String format){
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(parse(date, format)) ;
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) ;
	}
	
	/**
	 * 根据开始与结束时间取得间隔月份
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int calculateIntervalMonths(Date startTime, Date endTime){
		Calendar start = Calendar.getInstance(); start.setTime(startTime);
		Calendar end = Calendar.getInstance(); end.setTime(endTime);
		
		int year = end.get(Calendar.YEAR) - start.get(Calendar.YEAR) ;
		int mont = end.get(Calendar.MONTH) - start.get(Calendar.MONTH) ;
		return year * 12 + mont ;
	}
	
	/**
	 * 计算月份有多少周
	 * @param filed		Calendar.MONDAY 中国计算方式 已周一为一周开始
	 * @param strDate	月份日期
	 * @param format	格式化方式
	 * @return
	 */
	public static int calculateMonthWeeks(int filed, String strDate, String format){
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.parse(strDate, format)) ;
		c.setFirstDayOfWeek(filed);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY) ;//每周从周一开始
		return c.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 计算月份有多少周
	 * @param filed		Calendar.MONDAY 中国周一为开始
	 * @param strDate	月份日期
	 * @param format	格式化方式
	 * @return
	 */
	public static int calculateMonthWeeksByDate(int filed, Date strDate, String format){
		return calculateMonthWeeks(filed, format(strDate, format), format) ;
	}
	
	/**
	 * 计算当前日期为本年度第几周
	 * @param startDay	Calendar.MONDAY 中国计算方式 已周一为一周开始
	 * @param weekFiled	Calendar.WEEK_OF_MONTH 本月第几周, Calendar.WEEK_OF_YEAR 本年度第几周 
	 * @param strDate	日期
	 * @return
	 */
	public static int calculateDateInWeek(int startDay, int weekFiled, String strDate){
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.parse(strDate, "yyyy-MM-dd")) ;
		c.setFirstDayOfWeek(startDay);
		//每周从周一开始
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY) ;
		return c.get(weekFiled);
	}

	/**
	 * 计算当前日期为本年度第几周
	 * @param startDay	Calendar.MONDAY 中国计算方式 已周一为一周开始
	 * @param weekFiled	Calendar.WEEK_OF_MONTH 本月第几周, Calendar.WEEK_OF_YEAR 本年度第几周 
	 * @param strDate	日期
	 * @return
	 */
	public static int calculateDateInWeekByDate(int filed, int weekFiled, Date strDate){
		return calculateDateInWeek(filed, weekFiled, format(strDate, "yyyy-MM-dd"));
	}
	
	/**
	 * 计算本月 第一个 星期一 是几号
	 * @param strDate
	 * @return
	 */
	public static Date calculateMonthFirstWeek(Date strDate){
		Date retDate = null ;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse(strDate, "yyyy-MM"));
		
		//取得本月最大天数
		int maxDate = getMonthMaxDay(format(calendar.getTime())) ;
		for (int i = 0; i <= maxDate; i++) {
			calendar.setTime(addDay(calendar.getTime(), (i == 0 ? 0 : 1))) ;
			if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				retDate = calendar.getTime() ; 
				break ;
			}
		}
		
		return retDate ;
	}
	
	/**
	 * 计算当前日期 第一个 星期一 是几号
	 * @param strDate
	 * @return
	 */
	public static Date calculateMonthFirstWeek(Date strDate, String format){
		Date retDate = null ;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse(strDate, format));
		
		//取得本月最大天数
		int maxDate = getMonthMaxDay(format(calendar.getTime())) ;
		for (int i = 0; i <= maxDate; i++) {
			calendar.setTime(addDay(calendar.getTime(), (i == 0 ? 0 : 1))) ;
			if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				retDate = calendar.getTime() ; 
				break ;
			}
		}
		
		return retDate ;
	}
	
	public static void set(Date date, int value, int field) {
		if(date == null)
			return;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(field, value);
		date.setTime(calendar.getTime().getTime());
	}

	/**
	 * 设置到本月的最后一天
	 * @param endTime
	 */
	public static void setLastDayOfMonth(Date date) {
		if(date == null)
			return;
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(date);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		date.setTime(calendar.getTime().getTime());
	}
	
	/**
	 * 传入时间是否在两个时间之间
	 * @param input
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean between(Date input, Date start, Date end) {
		 return compareTo(input, start) >= 0 && compareTo(input, end) <= 0;
	}
	
	public static Date now(String format) {
		return parse(new Date(), format);
	}

	public static int compareTo(Date date1, Date date2) {
        if(date1 == null && date2 == null) return 0;
        if(date1 == null && date2 != null) return -1;
        if(date1 != null && date2 == null) return 1;
        return (int)(date1.compareTo(date2));
	}

	public static boolean isSameDay(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	public static String getYear() {
		return format(new Date(), FORMAT_YEAR);
	}
	
	/**
	 * 
	 * 当前日期格式化--taoLiang
	 * @return Date
	 * @exception 
	 * @since  1.0.0
	 */
	public static Date getNowDate() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(currentTime);
		   ParsePosition pos = new ParsePosition(8);
		   Date currentTime_2 = formatter.parse(dateString, pos);
		   return currentTime_2;
		}

	public static void main(String[] args) {
		Date start = DateUtil.parse("2012-12-30", "yyyy-MM-dd") ;
		int val = DateUtil.calculateDateInWeekByDate(Calendar.MONDAY, Calendar.WEEK_OF_YEAR, start) ;
		System.out.println(val);
	}
}