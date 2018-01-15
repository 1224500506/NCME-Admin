/**
 *
 * <p>课件</p>
 * @author chenlaibin
 * @version 1.0 2014-3-9
 */

package com.hys.exam.displaytag;

import java.util.List;

import org.displaytag.decorator.TableDecorator;

import com.hys.exam.common.util.Utils;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourseware;

public class StudyCoursewareDecorator extends TableDecorator{

	public String getDecoratorTitle(){
		StringBuffer title = new StringBuffer();
		
		StudyCourseware ware = (StudyCourseware)getCurrentRowObject();
		
		//行业
		List<ExamPropVal> industryList = ware.getIndustryList();
		
		//知识分类
		//List<ExamPropVal> knowList = ware.getKnowList();
		
		String industries = setIndValues(industryList);
		String displayContent = "";
		if(!Utils.isListEmpty(industryList)){
			int indistrySize = industryList.size();
			for(int i=0; i<(indistrySize>2?2:indistrySize); i++){
				ExamPropVal si = (ExamPropVal)industryList.get(i);
				displayContent += si.getName()+";";
			}
			if(indistrySize>2)
				displayContent += "...";
		}
		title.append("<span title=\"" + industries + "\" >" );
		title.append(displayContent);
		title.append("</span>");
		return title.toString();
	}
	
	private String setIndValues(List<ExamPropVal> indList){
		String str = "";
		if(!Utils.isListEmpty(indList)){
			for(ExamPropVal si: indList){
				str += si.getName() + "-";
			}
			str = str.substring(0, str.length()-1);
		}else{
			str = "暂无";
		}
		return str;
	}
}


