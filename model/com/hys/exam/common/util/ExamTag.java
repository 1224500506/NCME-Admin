package com.hys.exam.common.util;

import com.hys.exam.constants.Constants;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-03-13
 * 
 * 描述：试题专用标签
 * 
 * 说明: 
 */
public class ExamTag {

	/**
	 * 根据题型ID 取得题型内容
	 * @param questionLabelId
	 * @return
	 */
	public static String getQuestTypes(Integer questionLabelId){
		if(questionLabelId == null || questionLabelId <= 0){
			return StringPool.BLANK ;
		}
		
		String labelName = Constants.questLabelMap.get(questionLabelId) ;
		if(labelName == null || "".equals(labelName)){
			return StringPool.BLANK ;
		}
		
		return labelName ;
	}

	/**
	 * 取得出题策略名称
	 * @param pageMode
	 * @return
	 */
	public static String getPageMode(Integer pageMode){
		if(pageMode == null || pageMode <= 0){
			return StringPool.BLANK ;
		}
		
		String modeName = Constants.pageModeMap.get(pageMode) ;
		if(modeName == null || "".equals(modeName)){
			return StringPool.BLANK ;
		}
		
		return modeName ;
	}
	
}