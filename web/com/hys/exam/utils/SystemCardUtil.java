/**
 *
 * <p>学习卡</p>
 * @author chenlaibin
 * @version 1.0 2014-2-25
 */

package com.hys.exam.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

import com.hys.exam.constants.Constants;

public class SystemCardUtil {
	static {
		String dfStr = "" ;
		for (int i = 0 ;i < Constants.SYSTEM_CARD_LENGTH ;i++) {
			dfStr += "0" ;
		}
		df = new DecimalFormat(dfStr) ;
	}//YHQ，2017-03-23
	
	public static DecimalFormat df ;//= new DecimalFormat("0000000");		//流水号为7位数，YHQ，2017-03-23
	
	private static int [] arr = {7,9,10,5,8,4,2,1,6,3,0};		//系数，升级，YHQ，2017-03-23，加个0，或者1-9 重复也可以
	
	private static String [] jyw_arr = {"1","0","9","8","7","6","5","4","3","2","0"};	//校验位

	public static String getSystemCardNumber(String sn, Integer sellStyle){
		int year = getCurrentYear();
		/*String number = year + "" + getCardCode(sellStyle)+ getSerialNumber(sn);		
		number += getCheckCode(number);		
		return number; */
		
		String number = year + "" + getCardCode(sellStyle)+ getSerialNumber(sn).substring(4,Constants.SYSTEM_CARD_LENGTH) ;//YHQ，2017-03-23
		Long resNum = Long.parseLong(number) + Long.parseLong(getCheckCode(number)) ;
		return String.valueOf(resNum) ;
	}
	
	
	//得到年度最后2位数
	public static int getCurrentYear(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		return year - 2000;
	}
	
	//得到卡号编码
	public static String getCardCode(Integer sellStyle){
		if(sellStyle == 1)
			return "01";	//北京网上默认01
		else 
			return "02";	//北京地面02
	}
	
	//得到流水号
	//读取数据库里流水号最大的一位
	public static String getSerialNumber(String sn){
		//在原来序列号基础上 + 5
		long s = Long.parseLong(sn);
		s += 5;
		return df.format(s);
	}

	
	//得到一位校验数
	public static String getCheckCode(String seq){
		int count = 0;
		for(int i=0; i<seq.length(); i++){
			char a = seq.charAt(i);
			int b = Integer.parseInt(String.valueOf(a))* arr[i];
			count += b;
		}
		int c = count%11;
		return jyw_arr[c];
	}
	
	//得到6位数随机密码
	public static String get6Random(){
		int[] array = {1,2,3,4,5,6,7,8,9};
        Random rand = new Random();
        for (int i = 9; i > 1; i--) {
           int index = rand.nextInt(i);
           int tmp = array[index];
           array[index] = array[i - 1];
           array[i - 1] = tmp;
        }
        int random = 0;
        for(int i=0; i<6; i++)
        	random = random * 10 + array[i];
        return String.valueOf(random);
	}

}


