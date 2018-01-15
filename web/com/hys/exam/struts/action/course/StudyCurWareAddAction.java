package com.hys.exam.struts.action.course;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementWare;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.exam.struts.form.StudyCourseForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：新增课程组件信息
 * 
 * 说明:
 */
public class StudyCurWareAddAction extends BaseAction {
	
	private StudyCourseElementFacade studyCourseElementFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		StudyCourseForm curForm = (StudyCourseForm) form ;
		
		if(this.isTokenValid(request)){
			List<StudyCourseElement> eleList = curForm.getEleList() ;
			
			for (Iterator<StudyCourseElement> iterator = eleList.iterator(); iterator.hasNext();) {
				StudyCourseElement element = iterator.next();
				if(element.getEleWareId() == null || element.getEleWareId() == 0){
					iterator.remove() ;
				}else{
					element.setStatus(Constants.STATUS_1) ;
					
					StudyCourseElementWare elementWare = new StudyCourseElementWare() ;
					elementWare.setStudyCoursewareId(element.getEleWareId()) ;
					
					element.setElementWare(elementWare) ;
				}
			}

			if(!Utils.isListEmpty(eleList)){
				studyCourseElementFacade.addStudyCourseElementWare(eleList, eleList.get(0).getCourseId()) ;
			}
			this.resetToken(request) ;
		}
		
		request.setAttribute("succFlag", "success") ;
		
		return Constants.SUCCESS;
	}

	public void setStudyCourseElementFacade(StudyCourseElementFacade studyCourseElementFacade) {
		this.studyCourseElementFacade = studyCourseElementFacade;
	}
}
