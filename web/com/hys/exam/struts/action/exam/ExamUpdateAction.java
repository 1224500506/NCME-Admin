package com.hys.exam.struts.action.exam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.SystemSite;

/**
 * 
 * 标题：修改考试信息
 * 
 * 作者：陈明凯 2013-3-8
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamUpdateAction extends ExamBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		
		String examId = request.getParameter("examId");
		if(examId != null && !examId.equals(""))
		{
			ExamExamination examin = examExaminationFacade.getExamExaminationById(Long.valueOf(examId));
			JSONObject info = new JSONObject();
			info.put("papers", examin.getExaminPaperList());
			info.put("users", examin.getExaminUserList());
			info.put("considers", examin.getExaminConsierList());
			info.put("orgs", examin.getExaminOrgList());
			
			request.setAttribute("examExamination", examin);
			request.setAttribute("info", info);
			if(examin.getExam_type().equals(1))
			{
				return "exam";
			}
			else if(examin.getExam_type().equals(2))
			{
				return "exercise";
			}
		}
		return "fail";
	}
}
