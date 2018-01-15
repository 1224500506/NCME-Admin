package com.hys.exam.struts.action.exam.paper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.converters.LongArrayConverter;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.auth.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.model.ExamSource;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class ExamPaperQuestionFind extends BaseAction {

	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamPropValFacade localExamPropValFacade;
	

	public ExamQuestionTypeFacade getLocalExamQuestionTypeFacade() {
		return localExamQuestionTypeFacade;
	}



	public void setLocalExamQuestionTypeFacade(
			ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}



	public ExamQuestionLabelFacade getLocalExamQuestionLabelFacade() {
		return localExamQuestionLabelFacade;
	}



	public void setLocalExamQuestionLabelFacade(
			ExamQuestionLabelFacade localExamQuestionLabelFacade) {
		this.localExamQuestionLabelFacade = localExamQuestionLabelFacade;
	}



	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}



	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}



	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}



	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {			
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		
		ExamQuestionForm qform = new ExamQuestionForm();
		
		ExamQuestionQuery query  = new ExamQuestionQuery();
		
		//属性处理
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
		
		String course = request.getParameter("propIds")!=null ? request.getParameter("propIds") : "";
		if (!course.equals("")) {
			String[] courseIds = course.split(",");
			List<Long> ids = new ArrayList<Long>();
			for (int i=0; i<courseIds.length; i++){
				Long courseId = Long.valueOf(courseIds[i].trim());
				ids.add(courseId);
				
				//Get  the next prop list from selected id. 
				ExamPropQuery propQuery  = new ExamPropQuery();
				propQuery.setSysPropId(Long.valueOf(courseId));
				
				ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(propQuery);
				List<ExamProp> nextPropList = rprop.getReturnList();
				
				if (nextPropList != null && nextPropList.size() >0){
					for (ExamProp prop : nextPropList){
						ids.add(prop.getId());
						propQuery.setSysPropId(prop.getId());
						rprop = localExamPropValFacade.getNextLevelProp(propQuery);
						List<ExamProp> nextNextPropList = rprop.getReturnList();
						if (nextNextPropList != null){
							for (ExamProp nextProp : nextNextPropList){
								ids.add(nextProp.getId());
							}
						}						
					}
				}				
			}
			Long[] tempList = new Long[ids.size()];
			ids.toArray(tempList);
			qform.setQuestUnit_4(tempList);
		}
			
		String src = request.getParameter("laiIds")!=null ? request.getParameter("laiIds") : "";
		if (!src.equals("")) {
			String[] srcIds = src.split(",");
			Long[] ids = new Long[srcIds.length];
			for (int i=0; i<srcIds.length; i++) ids[i]= Long.valueOf(srcIds[i].trim()); 
			qform.setQuestSource_10(ids);
		}
		
		
		String cognize = request.getParameter("levelIds")!=null ? request.getParameter("levelIds") : "";
		if (!cognize.equals("")) {
			String[] corIds = cognize.split(",");
			 Long[] ids = new Long[corIds.length];
			 for (int i=0; i<corIds.length; i++) ids[i]= Long.valueOf(corIds[i].trim()); 
			 	qform.setQuestCognize_8(ids);
		}
		Integer grade = ParamUtils.getIntParameter(request, "grade", 0);
		qform.setGrade(grade);
		qform.setState(-1);
		qform.setIsnot_multimedia(-1);
		
		if(qform.getQuestion_label_id() != null){
			if(qform.getQuestion_label_id().intValue() == -1){
				query.setQuestion_label_ids(null);
			}else {
				query.setQuestion_label_ids(qform.getQuestion_label_id().toString());
			}
		}
		// 试题状态
		if(qform.getState() == null || qform.getState() == -1){
			
			//2:审核合格
			query.setState(2);
		}else{
			query.setState(qform.getState());
		}
		// 试题难度
		if(qform.getGrade() != null &&  qform.getGrade().intValue() != -1){
			query.setGrade(qform.getGrade());
		}
		
		setPropVal(qform.getQuestUnit_4(), Constants.EXAM_PROP_UNIT, questionPropMap);
		setPropVal(qform.getQuestPoint_5(), Constants.EXAM_PROP_POINT, questionPropMap);
		setPropVal(qform.getQuestPoint2_7(),Constants.EXAM_PROP_POINT2,questionPropMap);
		setPropVal(qform.getQuestCognize_8(),Constants.EXAM_PROP_COGNIZE,questionPropMap);
		setPropVal(qform.getQuestTitle_9(), Constants.EXAM_PROP_TITLE,questionPropMap);
		setPropVal(qform.getQuestSource_10(), Constants.EXAM_PROP_SOURCE,questionPropMap);
		
		query.setQuestionPropMap(questionPropMap);
		JSONObject result = new JSONObject();
		JSONArray result1 = new JSONArray();
		for(ExamQuestionLabel label : questLabel)
		{
			query.setQuestion_label_id(label.getId());
			int count = localExamQuestionFacade.getQuestionSize(query);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("label", label);
			jsonObj.put("count", count);
			result1.add(jsonObj);
		}
		
		result.put("list1", result1);
		
		if (!course.equals("")) {
			JSONObject jsonObj = new JSONObject();	
			String[] courseIds = course.split(",");
			for (int i=0; i<courseIds.length; i++)
			{				
				List<Long> propList = new ArrayList<Long>();
				Long courseId = Long.valueOf(courseIds[i].trim());
				propList.add(courseId);
				
				ExamPropQuery propQuery  = new ExamPropQuery();
				propQuery.setSysPropId(Long.valueOf(courseId));
				
				ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(propQuery);
				List<ExamProp> nextPropList = rprop.getReturnList();
				
				if (nextPropList != null && nextPropList.size() >0){
					for (ExamProp prop : nextPropList){
						propList.add(prop.getId());
						propQuery.setSysPropId(prop.getId());
						rprop = localExamPropValFacade.getNextLevelProp(propQuery);
						List<ExamProp> nextNextPropList = rprop.getReturnList();
						if (nextNextPropList != null){
							for (ExamProp nextProp : nextNextPropList){
								propList.add(nextProp.getId());
							}
						}						
					}
				}				
				Long[] tempList = new Long[propList.size()];
				propList.toArray(tempList);
				qform.setQuestUnit_4(tempList);
				setPropVal(qform.getQuestUnit_4(), Constants.EXAM_PROP_UNIT, questionPropMap);
				query.setQuestion_label_id(null);
				query.setQuestionPropMap(questionPropMap);
				int count = localExamQuestionFacade.getQuestionSize(query);
				jsonObj.put(courseId, count);
			}
			result.put("propInfo", jsonObj);
		}
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
	private void setPropVal(Long[] propVal,String propKey,Map<String,List<ExamQuestionProp>> questPropMap){
	if(propVal!=null){
		List<ExamQuestionProp> list = new ArrayList<ExamQuestionProp>();
		for(int i=0;i<propVal.length;i++){
			ExamQuestionProp prop = new ExamQuestionProp();
			prop.setProp_val_id(propVal[i]);
			list.add(prop);
		}
		questPropMap.put(propKey, list);
	}
}
}