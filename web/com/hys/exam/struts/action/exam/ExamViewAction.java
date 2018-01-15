package com.hys.exam.struts.action.exam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;

/**
 * 
 * 标题：查看考试信息
 * 
 * 作者：陈明凯 2013-3-8
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamViewAction extends ExamBaseAction {
	
	private ExamPaperFacade examPaperFacade ;
	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		String examId = request.getParameter("examId");
		Long examinId = null;
		if(examId != null && !examId.equals(""))
		{
			examinId = Long.valueOf(examId);
		}
		ExamExamination examin = examExaminationFacade.getExamExaminationById(examinId);
		
		ExamPaper paper = new ExamPaper();
		List<ExamQuestion> quesList = new ArrayList<ExamQuestion>();
		
		if (examin.getExaminPaperList().size() > 0) {

			Long paperId = examin.getExaminPaperList().get(0).getPaper_id();
			paper = examPaperFacade.getExamPaperById(paperId);
			quesList = paper.getExamQuestionList();
		}
		
		request.setAttribute("result", Constants.RESULT);
		request.setAttribute("paper", paper);
		request.setAttribute("quesList", quesList);
		return "SUCCESS";
//		return getExam(request);
	}
	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
}
