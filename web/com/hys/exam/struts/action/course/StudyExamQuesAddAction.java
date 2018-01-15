package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.exam.struts.form.StudyCourseForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyExamQuesAddAction extends BaseAction {
	
	private StudyCourseElementFacade studyCourseElementFacade ;
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StudyCourseForm curForm = (StudyCourseForm) form ;
		
		if(this.isTokenValid(request)){
			String examIds = ParamUtils.getParameter(request, "examIds", null) ;
			if(examIds != null || !"".equals(examIds)){
				String[] tempExamIds = examIds.split(",") ;
				
				StudyCourseElement element = new StudyCourseElement() ;
				element.setCourseElementType(curForm.getCourseElementType()) ;
				element.setCourseId(curForm.getCourseId()) ;
				
				List<StudyCourseElementQuestion> questList = new ArrayList<StudyCourseElementQuestion>() ;
				element.setQuestList(questList) ;
				
				StudyCourseElementQuestion quest = null ;
				for (int i = 0; i < tempExamIds.length; i++) {
					Long examId = NumberUtil.parseLong(tempExamIds[i], 0) ;
					if(examId == 0){
						continue ;
					}
					quest = new StudyCourseElementQuestion() ;
					quest.setStudyCourseQuestionId(examId) ;
					questList.add(quest) ;
				}
				
				studyCourseElementFacade.addStudyCourseElementQuestion(element) ;
			}
			this.resetToken(request) ;
		}
		
		request.setAttribute("succFlag", "add") ;
		
		return Constants.SUCCESS ;
	}

	public void setStudyCourseElementFacade(StudyCourseElementFacade studyCourseElementFacade) {
		this.studyCourseElementFacade = studyCourseElementFacade;
	}
}
