package com.hys.auth.util;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(generateNumber("333333", "0912", "2112"));
	}

}
