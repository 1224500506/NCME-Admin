package com.hys.exam.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 根据输入的时间格式判断是否是日期类型的字符串
	 * 
	 * @param str
	 *            �?要判断的字符�?
	 * @param format
	 *            判断是否匹配对应的格�?
	 * @return 返回匹配结果
	 */
	public static boolean isDate(String str, String format) {
		if (StringUtils.isEmpty(str))
			return false;
		if (StringUtils.isEmpty(format))
			return false;
		try {
			DateFormat df = new SimpleDateFormat(format);
			df.parse(str);
		} catch (Exception ex) {
			return false;
		}

		return true;
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

	public static String toUnicode(String strText, String code)
			throws UnsupportedEncodingException {
		char c;
		String strRet = "";
		int intAsc;
		String strHex;
		// strText = new String(strText.getBytes("iso8859_1"), code);
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			if (intAsc > 128) {
				strHex = Integer.toHexString(intAsc);
				strRet = strRet + "&#x" + strHex + ";";
			} else {
				strRet = strRet + c;
			}
		}
		return strRet;
	}

	/**
	 * 变成unicode的�??
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str) {
		return loadConvert(str.toCharArray(), 0, str.length(), new char[0])
				.toUpperCase();
	}

	public static String loadConvert(char[] in, int off, int len,
			char[] convtBuf) {
		String ret = "";
		try {
			for (int i = 0; i < in.length; i++) {
				char c = in[i];
				if (c <= 128) {
					char[] r = Hex.encodeHex(new byte[] { (byte) c });
					ret += "&#x";
					for (int j = 0; j < r.length; j++) {
						ret += r[j];
					}
					ret += ";";
				} else {
					int intc = (int) c;
					byte[] r2 = new byte[2];
					r2[0] = (byte) (intc >> 8);
					r2[1] = (byte) intc;
					char[] r = Hex.encodeHex(r2);
					ret += "&#x";
					for (int j = 0; j < r.length; j++) {
						ret += r[j];
					}
					ret += ";";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String ChineseToUnicode(String s) {
		StringBuffer bu = new StringBuffer(s);
		String unicode = "";
		for (int i = 0; i < bu.length(); i++) {
			String tmp = Integer.toHexString((int) bu.charAt(i));
			unicode = unicode + "\\u" + tmp;
		}
		return unicode;
	}

	public static String isoToChinese(String str) {
		if (str == null) {
			return null;
		}

		try {
			str = new String(str.getBytes("iso-8859-1"), "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str;
	}

	public static boolean checkNull(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static void main(String argv[]) throws Exception {
		String test = "123";
		System.out.println(ChineseToUnicode(test));

	}

}
