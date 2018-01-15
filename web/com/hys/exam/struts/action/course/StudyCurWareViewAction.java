package com.hys.exam.struts.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.exam.sessionfacade.local.StudyCoursewareFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：查询课程组件列表信息
 * 
 * 说明:
 */
public class StudyCurWareViewAction extends BaseAction {
	
	private ExamPaperFacade examPaperFacade ;
	
	private StudyCoursewareFacade studyCoursewareFacade ;
	
	private StudyCourseElementFacade studyCourseElementFacade ; 

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.saveToken(request) ;
		//课程ID
		Long courseId = ParamUtils.getLongParameter(request, "courseId", 0) ; 
		
		//查询课程信息
		Page<StudyCourseware> page = PageUtil.getPageByRequest(request);
		
		//查询课程列表信息
		StudyCourseware curware = new StudyCourseware() ;
		curware.setCourseId(courseId) ;
		
		studyCoursewareFacade.getCourseWareByCourse(page, curware) ;
		setExamPaperName(page) ;
		
		Integer maxSeq = studyCourseElementFacade.getCurElementMaxSeq(courseId) ;
		
		request.setAttribute("page", page) ;

		request.setAttribute("maxSeq", maxSeq) ;

		return "SUCCESS";
	}
	
	private void setExamPaperName(Page<StudyCourseware> page){
		if(!Utils.isListEmpty(page.getList())){
			for (StudyCourseware curWare : page.getList()) {
				if(curWare.getCourseElementType() >= 11){
					List<StudyCourseElementQuestion> questList = studyCourseElementFacade.getCurEleQuestByElement(curWare.getCourseElementId()) ;
					if(!Utils.isListEmpty(questList)){
						ExamPaper examPaper = examPaperFacade.getExamPaperById(questList.get(0).getStudyCourseQuestionId()) ;
						if(examPaper != null){
							curWare.setName(examPaper.getName()) ;
						}
					}
				}
			}
		}
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
	
	public void setStudyCoursewareFacade(StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}

	public void setStudyCourseElementFacade(StudyCourseElementFacade studyCourseElementFacade) {
		this.studyCourseElementFacade = studyCourseElementFacade;
	}
}