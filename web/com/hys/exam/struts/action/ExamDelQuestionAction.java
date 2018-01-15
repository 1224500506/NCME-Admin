package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.StringUtils;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-28
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamDelQuestionAction extends BaseAction {


	private ExamQuestionFacade localExamQuestionFacade;
	
	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}

	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}



	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String idArr = request.getParameter("idArr") == null ? "" : request.getParameter("idArr");
		
		List<String> list = StringUtils.stringToArrayList(idArr, ",");
		
		if(!list.isEmpty()){
			List<Long>  id = new ArrayList<Long>();
			
			for(String tmp : list){
				id.add(Long.valueOf(tmp));
			}
			
			
			boolean flag = localExamQuestionFacade.deleteQuesitons(id);
			if(flag){
				StrutsUtil.renderText(response, "success");
			}else {
				StrutsUtil.renderText(response, "error");
			}
		}
		

		return null;
	}

}
