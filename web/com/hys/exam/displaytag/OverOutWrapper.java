package com.hys.exam.displaytag;

import java.util.List;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamSubSysQuestType;


public class OverOutWrapper extends TableDecorator {   
	
	public String getLink2(){
		ExamQuestion quest = (ExamQuestion)getCurrentRowObject();
		return quest.getContent();
		/*
		ExamQuestion quest = (ExamQuestion)getCurrentRowObject();
		Map<String,List<ExamQuestionProp>> questionPropMap = quest.getQuestionPropMap();
		//试题分类
		List<ExamSubSysQuestType> subSysQuestTypeList = quest.getSubSysQuestTypeList();
		
		//一级学科
		List<ExamQuestionProp> study1List = questionPropMap.get(Constants.EXAM_PROP_STUDY1);
		//二级学科
		List<ExamQuestionProp> study2List = questionPropMap.get(Constants.EXAM_PROP_STUDY2);
		//三级学科
		List<ExamQuestionProp> study3List = questionPropMap.get(Constants.EXAM_PROP_STUDY3);
		//单元
		List<ExamQuestionProp> unitList = questionPropMap.get(Constants.EXAM_PROP_UNIT);
		//知识点
		List<ExamQuestionProp> pointList = questionPropMap.get(Constants.EXAM_PROP_POINT);
		//副知识点
		List<ExamQuestionProp> point2List = questionPropMap.get(Constants.EXAM_PROP_POINT2);
		//认知水平
		List<ExamQuestionProp> cognizeList = questionPropMap.get(Constants.EXAM_PROP_COGNIZE);
		


		String study1Str = setProp(study1List);
		String study2Str = setProp(study2List);
		String study3Str = setProp(study3List);
		String unitStr= setProp(unitList);
		String pointStr = setProp(pointList);
		String point2Str = setProp(point2List);
		String cognizeStr = setProp(cognizeList);
		
		String questTypeStr="";
		if(null!=subSysQuestTypeList && !subSysQuestTypeList.isEmpty()){
			for(ExamSubSysQuestType questType: subSysQuestTypeList){
				if(questTypeStr.equals("")){
					questTypeStr = questType.getSub_type_name();
				}else{
					questTypeStr +="-"+questType.getSub_type_name();
				}
			}	
		}else{
			questTypeStr = "null";
		}
		
		StringBuffer title = new StringBuffer();
		String tmp = study1Str+"_"+study2Str+"_"+study3Str+"_"+unitStr+"_"+pointStr+"_"+point2Str+"_"+cognizeStr+"_"+questTypeStr;
		if(unitStr.equals("null") 
				&& point2Str.equals("null") 
				&& questTypeStr.equals("null")
				&& study1Str.equals("null")
				&& study2Str.equals("null")
				&& study3Str.equals("null")
				&& pointStr.equals("null")
				&& cognizeStr.equals("null")){
			title.append(quest.getContent());
		}else{
			title.append("<span title=\""+tmp+"\" >" );
			title.append(quest.getContent());
			title.append("</span>");
		}
		return title.toString();
		*/
	}
	
	private String setProp(List<ExamQuestionProp> propList){
		String str = "";
		if(null!=propList && !propList.isEmpty()){
			for(ExamQuestionProp prop: propList){
				str +=prop.getProp_val_name()+"-";
			}	
			str = str.substring(0, str.length()-1);
		}else{
			str = "null";
		}
		return str;
	}
}
