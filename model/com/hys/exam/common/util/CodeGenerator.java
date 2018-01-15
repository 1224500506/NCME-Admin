package com.hys.exam.common.util;
/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class CodeGenerator {

	/**
	 * code生成器
	 * 
	 * @param parentCode
	 *            父节点code
	 * @param maxCode
	 *            平级最大的code
	 * 
	 * @return
	 */
	public static String genratorCode(String parentCode, String maxCode) {
		int seq = 0;
		if(maxCode == null || maxCode.equals("0")){
			seq = 1;
			maxCode = parentCode + "00000000";
		}
		
		// maxCode的下一seq
		if(parentCode.equals("0")){
			seq = Integer.parseInt(maxCode) + 1;
		} else {
			seq = Integer.parseInt(maxCode.substring(parentCode.length())) + 1;
		}
		// 根据顺序算出最终的code
		String seqStr = String.valueOf(seq);

		int len = seqStr.length();
		
		for (int i = 0; i < 8 - len; i++) 
			seqStr ="0" + seqStr;
		

		// 没有父节点，那肯定是第一级的
		seqStr = parentCode.equals("0") ? seqStr : parentCode + seqStr;

		return seqStr;
	}

	public static void main(String[] str) {
		String code = "000000010000001200000123";
		System.out.println(code.substring(code.length()-8));
		System.out.println(genratorCode("0", "10000000"));
	}
}
