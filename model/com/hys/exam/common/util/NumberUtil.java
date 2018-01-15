package com.hys.exam.common.util;

import java.text.NumberFormat;

/**
 * 
 * 标题：学习
 * 
 * 时间: 2010 1 29
 * 
 * 描述：
 * 
 * 说明:
 */
public class NumberUtil {

	final static private Integer HOURS = 60 * 60;

	final static private String PERSENT_ZERO = "0%";

	final static private String PERSENT_ONE = "1%";

	final static private String PERSENT_HUNDRED = "100%";

	/**
	 * 把秒转换为小时
	 * 
	 * @param second
	 * @return
	 */
	public static String toHours(Long second) {

		// 为0的话 返回 0小时
		if (second == null || second.longValue() == 0) {
			return 0 + "小时";
		}

		// 小于60 返回时间加秒
		if (second < 60) {
			return second + "秒";
		}

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);

		// 大于秒 小于小时 返回分钟
		if (second > 60 && second < HOURS) {
			return nf.format((second / 60)) + "分钟";
		}

		double d1 = second;

		// 返回小时
		return nf.format(d1 / HOURS) + "小时";
	}

	/**
	 * 计算百分比
	 * 
	 * @param sum
	 * @param value
	 * @param reservedWords 保留字
	 * @return
	 */
	public static String toPercent(Long sum, Long value, int reservedWords) {

		if (sum == null || sum.longValue() == 0) {
			return PERSENT_ZERO;
		}

		if (value == null || value.longValue() == 0) {
			return PERSENT_ZERO;
		}

		if (value > sum) {
			return PERSENT_HUNDRED;
		}

		if (value < (sum / 100)) {
			return PERSENT_ONE;
		}

		double d1 = sum;
		double d2 = value;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(reservedWords);

		return nf.format(d2 / d1);
	}

	/**
	 * 计算百分比
	 * 
	 * @param sum
	 * @param value
	 * @param reservedWords 保留字
	 * @return
	 */
	public static String toPercent(int sum, int value, int reservedWords) {

		if (sum == 0) {
			return PERSENT_ZERO;
		}

		if (value == 0) {
			return PERSENT_ZERO;
		}

		if (value > sum) {
			return PERSENT_HUNDRED;
		}

		if (value < (sum / 100)) {
			return PERSENT_ONE;
		}

		double d1 = sum;
		double d2 = value;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(reservedWords);

		return nf.format(d2 / d1);
	}
	
	/**
	 * 格式化字符串为整型
	 * @param name 名称
	 * @return
	 */
	public static int parseInteger(String name) {
		if (name == null || "".equals(name)) {
			return 0;
		}
		
		try {
			return Integer.valueOf(name);
		} catch (NumberFormatException ne) {
			return 0;
		}
	}
	
	/**
	 * 格式化字符串为整型
	 * @param name		 名称
	 * @param defaultNum 缺省值
	 * @return
	 */
	public static int parseInteger(String name, int defaultNum) {
		if (name != null && !name.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.valueOf(name);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}
	
	/**
	 * 格式化字符串为长整型
	 * @param name 名称
	 * @return
	 */
	public static long parseLong(String name) {
		if (name == null || "".equals(name)) {
			return 0L;
		}

		try {
			return Long.valueOf(name);
		} catch (NumberFormatException ne) {
			return 0L;
		}
	}

	/**
	 * 格式化字符串为长整型
	 * @param name		 名称
	 * @param defaultNum 缺省值
	 * @return
	 */
	public static long parseLong(String name, long defaultNum) {
		if (name != null && !name.equals("")) {
			long num = defaultNum;
			try {
				num = Long.valueOf(name);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}
	
	/**
	 * 取得保留数字的数值 无小数 不会填0补位
	 * @param number		数值
	 * @param reservedWords	保留字
	 * @return
	 */
	public static String toNumber(double number, int reservedWords) {
		if (number == 0) {
			return "0";
		}

		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(reservedWords) ;
		return format.format(number);
	}

	/**
	 * @param string
	 * @return
	 */
	public static Double parseDouble(String string) {
		try {
			return Double.valueOf(string);
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static void main(String[] arg) {
		System.out.println(NumberUtil.toNumber(123, 2));
	}
}
