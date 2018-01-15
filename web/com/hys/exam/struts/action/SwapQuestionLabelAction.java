package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.framework.web.action.BaseAction;

public class SwapQuestionLabelAction extends BaseAction {

	
	private ExamPropValFacade localExamPropValFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}


	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	public ExamQuestionLabelFacade getLocalExamQuestionLabelFacade() {
		return localExamQuestionLabelFacade;
	}


	public void setLocalExamQuestionLabelFacade(
			ExamQuestionLabelFacade localExamQuestionLabelFacade) {
		this.localExamQuestionLabelFacade = localExamQuestionLabelFacade;
	}


	public ExamQuestionTypeFacade getLocalExamQuestionTypeFacade() {
		return localExamQuestionTypeFacade;
	}


	public void setLocalExamQuestionTypeFacade(
			ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}


	//添加试题
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		Map<String,List<ExamPropVal>> propMap = localExamPropValFacade.getSysPropValBySysId();
		List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
		for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
				questPropPoint2 = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
				questPropCognize = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
				questPropTitle =(List<ExamPropVal>)entry.getValue();
			}
		}
		
		int labelId = Integer.parseInt(request.getParameter("questionLabel"));
		
		String question_label_name = "";
		for (int i=0; i<questLabel.size(); i++) {
			if (questLabel.get(i).getId() == labelId) {
				question_label_name = questLabel.get(i).getName();
				break;
			}
		}
		
		//试题分类
		List<ExamQuestionType> questType = localExamQuestionTypeFacade.getQuestionTypeRootBySysId(null);
		
		request.setAttribute("questPropPoint2", questPropPoint2);
		request.setAttribute("cognizeList", questPropCognize);
		request.setAttribute("questType", questType);
		request.setAttribute("questPropTitle", questPropTitle);		
		request.setAttribute("questionLabel", labelId);		

		
		request.setAttribute("labelList", questLabel);	
		request.setAttribute("question_label_name", question_label_name);
		
		
		if (Constants.A1 == labelId || Constants.A2 == labelId) {
			return "questionA";
		}
		if (Constants.DXT == labelId) {
			return "questionB";
		}
		if (Constants.TK == labelId || Constants.JD == labelId
				|| Constants.MCJS == labelId  || Constants.NLFX == labelId) {
			return "questionC";
		}
		if (Constants.PD == labelId) {
			return "questionD";
		}

/*		if (Constants.B1 == labelId) {
			return "questionB1";
		}
		if(Constants.A3 == labelId){
			return "questionA3";
		}
*/		return "questionA";
	}

}
