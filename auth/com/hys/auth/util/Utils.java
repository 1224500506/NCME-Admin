package com.hys.auth.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

 
/**
 * 常用函数的工具类
 * @author  李劲华
 *
 */
public class Utils {

private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
/**
 * byte chang to ASCI
 * @param b
 * @return
 * @throws IOException
 */
	public String byteToASCI(byte[] b) throws IOException{
		String tepStr = null;
		String ASCString = null;
		for (int i = 0; i < b.length; i++)
		{
			tepStr = Character.toString((char) b[i]);
			ASCString = ASCString + tepStr;
		}
		return ASCString;
	}
	
	/**
	 * 生成字母和数字组成的随机字符串
	 * @param len ：生成的字符串长度
	 * @return
	 */
	public static String genRandomNum(int len){
		if(len <= 0)
			return "";
		final int  maxNum = 36;
		int i;  //生成的随机数��ɵ������
		int count = 0;//生成的密码的长度��ɵ�����ĳ���
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		
		StringBuffer rtn = new StringBuffer("");
		Random r = new Random();
		while(count < len){
			//生成随机数，取绝对值，防止生成负数，Math.abs()取绝对值防止生成负数
			i = Math.abs(r.nextInt(maxNum));//生成的数最大为36-1
			//System.out.println(i); 测试的时候用了的
			if (i >= 0 && i < str.length) {
				rtn.append(str[i]);
				count ++;
			}
		}
		
		return rtn.toString();
	}

	  /**
	 * 字符串转换为整型
	 * @param str
	 * @return int
	 */
	public static int strToInt(String str){
		if(str == null || str.trim().length() == 0)
			return 0;
		try{
			return Integer.parseInt(str);
		}catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 字符串转换为整型
	 * @param str
	 * @return Integer
	 */
	public static Integer strToInteger(String str){
		if(str == null || str.trim().length() == 0)
			return null;
		try{
			return new Integer(str);
		}catch (Exception e) {
			return null;
		}
	}
	/**
	 * 字符串转换为长整型
	 * @param str
	 * @return
	 */
	public static Long strToLong(String str){
		if(str == null || str.trim().length() == 0)
			return null;
		try{
			return Long.parseLong(str);
		}catch (Exception e) {
			return null;
		}
	}
	/**
	 * 字符串转换为浮点型
	 * @param str
	 * @return
	 */
	public static Float strToFloat(String str){
		if(str == null || str.trim().length() == 0)
			return null;
		try{
			return Float.parseFloat(str);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Date类型foramt
	 * @param obj 需要格式化的object,如果不是Date类型，将直接调用该类的toString()方法返回。
	 * @param parten format格式，如果为空，则默认为 "yyyy-MM-dd"
	 * @return String
	 */
	public static String dateFormat(Object obj, String parten){
		if(obj == null)
    		return null;
		if(parten == null || parten.trim().length() == 0)
			parten = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(parten);
    	if (obj instanceof java.sql.Date) {
			return dateFormat.format(obj);			
		}else if(obj instanceof java.util.Date) {
			return dateFormat.format(obj);	
		}else{
			return obj.toString();
		}
	}
	
	/**
	 * Decimal类型foramt
	 * @param obj 需要格式化的object,如果不是BigDecimal或Float类型，将直接调用该类的toString()方法返回。
	 * @param parten format格式，如果为空，则默认为 "###,##0.00"
	 * @return String
	 */
	public static String bigDicimalFormat(Object obj, String parten){
		if(obj == null)
    		return null;
		if(parten == null || parten.trim().length() == 0)
			parten = "###,##0.00";
		DecimalFormat decimalFormat = new DecimalFormat(parten);
		if(obj instanceof BigDecimal) {
			return decimalFormat.format(obj);
		}else if(obj instanceof Float){
			return decimalFormat.format(obj);
		}else{
			return obj.toString();
		}
	}
	
	/**
	 * 字符串日期转换成Decimal
	 * @param strbigdecimal:要转换的字符串(Decimal)
	 * @return
	 */
	public static BigDecimal str2BigDicimal(String strbigdecimal){
		if(strbigdecimal == null || strbigdecimal.trim().length() == 0)
    		return null;
		BigDecimal bd = null;
		try {
			bd = new BigDecimal(strbigdecimal);
		} catch (Exception e) {
			return null;
		}
		return bd;
	}
	
	/**
	 * 字符串日期转换成Date日期
	 * @param strDate 要转换的字符串日期
	 * @param parten 日期格式，默认为 "yyyy-MM-dd"
	 * @return Date
	 */
	public static Date str2Date(String strDate, String parten){
		Date dt=null;
		if(parten == null)
			parten = "yyyy-MM-dd";
			
		SimpleDateFormat sdf = new SimpleDateFormat(parten);
		try{
			dt = sdf.parse(strDate);
		}catch(Exception e){
			return null;
		}
		return dt;
	}
	
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			int n = b[i];
			resultSb.append(hexDigits[n>>> 4 & 0xf] + hexDigits[n& 0xf]);
		}
		return resultSb.toString();
	}

	/**MD5加密*/
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}
	
	/**
	 * 计算2个日期之间相差的年数
	 * @param first
	 * @param last
	 * @return int
	 */
	public static int calYearBetween(Date first, Date last){
		int rtn = 0;
		Calendar calf = Calendar.getInstance();
		calf.setTime(first);
		Calendar call = Calendar.getInstance();
		call.setTime(last);
		rtn = ((call.get(Calendar.YEAR)*12+call.get(Calendar.MONTH)) - (calf.get(Calendar.YEAR)*12+calf.get(Calendar.MONTH)))/12;
		return rtn;
	}
	
	/**
	 * 计算2个时间之间的间隔
	 * @param first
	 * @param last
	 * @return 返回格式(时:分:秒)
	 */
	public static String calTimeBetween(Date first, Date last){
		String rtn = "";
		Calendar calf = Calendar.getInstance();
		calf.setTime(first);
		long fsrstl = calf.getTimeInMillis();
		calf.setTime(last);
		long time = (calf.getTimeInMillis() - fsrstl)/1000;
		
		int xiaoshi = (int)time/60/60;
		int mil = (int)(time - 60*60*xiaoshi)/60;
		int miss = (int)(time - 60*60*xiaoshi - 60*mil);
		if(xiaoshi < 10)
			rtn = "0" + xiaoshi + ":";
		else
			rtn = xiaoshi + ":";
		if(mil < 10)
			rtn += "0" + mil + ":";
		else
			rtn += mil + ":";
		if(miss < 10)
			rtn += "0" + miss;
		else
			rtn += miss;
    	return rtn;
	}
	
	/**
	 * 把字符串的从ISO编码转换成GBK编码
	 * @param str
	 * @return String str为空或出错返回""
	 */
	public static String isoToGBK(String str){
		if(str == null || str.trim().length() == 0)
			return "";
		try{
			return new String(str.getBytes("ISO-8859-1"),"GBK");
		}catch (Exception e) {
			return "";
		}
	}
	
	public static String chang2UTF8(String str){
		if(str == null || str.trim().length() == 0)
			return "";
		try{
			return new String(str.getBytes(),"UTF-8");
		}catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 把字符串的从GBK编码转换成ISO编码
	 * @param str
	 * @return String str为空或出错返回""
	 */
	public static String GBKToiso(String str){
		if(str == null || str.trim().length() == 0)
			return "";
		try{
			return new String(str.getBytes("GBK"),"ISO-8859-1");
		}catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * Float转换成Integer
	 * @param fval
	 * @return
	 */
	public static Integer float2Integer(Float fval){
		if(fval == null)
			return null;
		return new Integer(fval.intValue());
	}
	
	public static Number obj2Number(Object obj){
		if(obj == null)
			return 0;
		if (obj instanceof Number) {
			return (Number) obj;
		}else{
			return 0;
		}
	}
	
	 
	/**
	 * 整型换为字符串转
	 * @param str
	 * @return Integer
	 */
	public static String integerToStr(Integer integer){
		if(integer == null)
			return null;
		else return integer.toString();
	}
	
	public static String int2TwobitStr(int ivalue){
		if(ivalue < 0)
			return "00";
		if(ivalue< 10 && ivalue >= 0)
			return "0"+ivalue;
		if(ivalue >= 100)
			return "99";
		return ""+ivalue;
	}

	public static float[] calStartEndPos(Date start, Date end, String basedate) {
		float[] startend = new float[2];
	
		String startdate = Utils.dateFormat(start, "yyyy-MM-dd");
		basedate = startdate;
		String enddate = startdate;
		Calendar cald = Calendar.getInstance();
		if(basedate.equals(startdate)){
			cald.setTime(start);
			int starthour = cald.get(Calendar.HOUR_OF_DAY);//小时
			int startmi = cald.get(Calendar.MINUTE);//分��
			startend[0] = starthour * 4f;
			if(startmi != 0)
				startend[0] += startmi/15.0f + 0.5f;//startend[0] += (float)Math.floor(startmi/15.0 + 0.9999f);
			if(!startdate.equals(enddate)){
				startend[1] = 96f;
			}else{
				cald.setTime(end);
				int endhour = cald.get(Calendar.HOUR_OF_DAY);//小时
				int endmi = cald.get(Calendar.MINUTE);//分��
				startend[1] = endhour * 4f;
				if(endmi != 0)
					startend[1] += endmi/15.0f + 0.5f;//startend[0] += (float)Math.floor(startmi/15.0 + 0.9999f);
			}
		}else if(basedate.equals(enddate)){
			startend[0] = 1f;
			cald.setTime(end);
			int endhour = cald.get(Calendar.HOUR_OF_DAY);//小时
			int endmi = cald.get(Calendar.MINUTE);//分��
			startend[1] = endhour * 4f;
			if(endmi != 0)
				startend[1] += endmi/15.0f + 0.5f;//startend[1] += (float)Math.floor(endmi/15.0 + 0.9999f);
		}else{
			startend[0] = 1f;
			startend[1] = 96f;
		}
		if(startend[0] == startend[1])
			startend[1] += 0.5f;
		return startend;
	}
	public static String convertDate() {
		SimpleDateFormat dateFormt = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat dateFormt = new SimpleDateFormat("yyyy/mm/dd");
		return dateFormt.format(new Date());
	}
	
	public static void main(String[] args){
		/*
		Date d1 = Utils.str2Date("2007:02:01:04", "yyyy:MM:dd:HH"); //注意了下面的也要改动
		Date d2 = Utils.str2Date("2007-02-01 05:48:12", "yyyy-MM-dd HH:mm:ss");
		String s = dateFormat(d1, "yyyy:MM:dd:HH"); //和上面的要一样了
		
		String currentTime = Utils.dateFormat(new Date(),"yyyy-MM-dd_HH-mm-ss"); 
		String date = Utils.dateFormat(new Date(), "yyyy-MM-dd hh-mm-ss");
		//System.out.println(currentTime);
		System.out.println(date);
		
		/*
		System.out.println(s);
		String [] ss = s.split(":");
		 
		int j = Integer.parseInt(ss[0]);
		System.out.println(j);
		
		System.out.println(ss.length);
		 
		for(int i=0;i<ss.length;i++)
		{
			System.out.println(ss[i]);
		}
		 */
		
		//float[] rtn = Utils.calStartEndPos(d1, d2, "2007-02-01");
		//System.out.println(rtn[0]);
		//System.out.println(rtn[1]);
		//System.out.println(d1);
		//String s = convertDate();
		//System.out.println(s);
		//String s = genRandomNum(8);
	//	System.out.println(s);
	  //	Float f = strToFloat("886");
		//System.out.println(f.doubleValue());
		//String s = calTimeBetween(d1, d2);
		//System.out.println(s);
		 //String s = MD5Encode("李劲华");
		//System.out.println(s);
		
		
		String s = Utils.MD5Encode("523523");
		System.out.println(s);
	}
}

