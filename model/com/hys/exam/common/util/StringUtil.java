package com.hys.exam.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.hys.common.util.StringUtils;


public class StringUtil {

	private static final String threeEggs = "000";
	private static final String twoEggs = "00";
	private static final String oneEgg = "0";

	public static String filterSQL(String str) {
		String filterStr = str;
		if (filterStr.indexOf("'") >= 0) {
			filterStr = filterStr.replaceAll("'", "");
		} else if (filterStr.indexOf("\"") >= 0) {
			filterStr = filterStr.replaceAll("\"", "");
		}
		return filterStr;
	}

	public static String generateNumber(String deptNo, String approveDate, String sequence) {
		int len = sequence.length();
		String resultNo = deptNo + approveDate;
		switch (len) {
		case 1:
			resultNo += threeEggs + sequence;
			break;
		case 2:
			resultNo += twoEggs + sequence;
			break;
		case 3:
			resultNo += oneEgg + sequence;
			break;
		case 4:
			resultNo += sequence;
			break;
		default:
			resultNo = null;
		}
		return resultNo;
	}
	
	public static String trimToMaxLength(String str, String encoding, int maxLength) throws UnsupportedEncodingException {
		String[] s = str.split("");
		int len = 0;
		int i;
		for (i = 1; i < s.length; i++) {
			try {
				int byteLength = s[i].getBytes(encoding).length; 
				if (len + byteLength <= maxLength) len += byteLength;
				else break;
			} catch (UnsupportedEncodingException e) {
			}
		}
		return new String(str.getBytes(encoding), 0, len, encoding);
	}
	
	public static boolean checkNull(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 将字符串str按reg的规则分解
	 * 
	 * @param str
	 * @param reg
	 *            如 '|'与':'等
	 * @return
	 */
	public static ArrayList<String> stringToArrayList(String str, String reg) {

		ArrayList<String> tmp = new ArrayList<String>();
		if (str == null)
			return tmp;
		String[] strArr = StringUtils.split(str, reg);
		if (str.length() > 0) {
			for (int i = 0; i < strArr.length; ++i) {

				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(generateNumber("333333", "0912", "2112"));
	}

}
