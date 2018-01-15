package com.hys.exam.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 
 * @author Han
 * @data 2016-12-03
 */
public class PinyinUtil {

	public static String convPinyin(String hanyuStr){
		try{
			String pinyinStr = "";
			
			for (char c:hanyuStr.toCharArray()) {
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
				if (pinyinArray != null) 	pinyinStr += pinyinArray[0].substring(0, 1);
				else						pinyinStr += c;
			}
			return pinyinStr.toUpperCase();
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String generateCode(String keyStr, String code){
		try{
			String res = convPinyin(keyStr);
			if (res.length()>4)
				res = res.substring(0,4);
			Long id = Long.valueOf(code);
			String idstr = "0000000000" + id.toString();
			int remainlen = 9-res.length();
			idstr = idstr.substring(idstr.length()-remainlen,idstr.length());
			res += idstr;
			res += Math.abs(res.hashCode()%10);
			return res;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
