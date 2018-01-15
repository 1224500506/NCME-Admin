package com.hys.exam.struts.action.exam.paper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.framework.web.action.BaseAction;

public class PaperCountLabelIdAction extends BaseAction{
	private ExamPaperFacade examPaperFacade ;
private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	

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

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String paperIds = ParamUtils.getParameter(request, "paperIds") ;
		String[] idArr = paperIds.split("_");
		JSONArray result = new JSONArray();
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		Map <Integer, Integer> resMap = new HashMap<Integer, Integer>();
		
		String idsStr = paperIds.replace("_", ",");
		
		if (idsStr.substring(idsStr.length()-1, idsStr.length()).equals(","))
			idsStr = idsStr.substring(0, idsStr.length()-1);
		
		for(ExamQuestionLabel label : questLabel){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("label", label);
			jsonObj.put("count", examPaperFacade.getExamCountByPaperIds(label.getId(),idsStr));
		
			result.add(jsonObj);
		}
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}

}
