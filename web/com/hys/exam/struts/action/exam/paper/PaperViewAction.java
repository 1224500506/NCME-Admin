package com.hys.exam.struts.action.exam.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamLog;
import com.hys.exam.model.ExamLogResult;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.service.local.ExamExaminationManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.framework.web.action.BaseAction;

import net.sf.json.JSONObject;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：查询试卷详细信息
 * 
 * 说明:
 */
public class PaperViewAction extends BaseAction {
	private LogStudyCVUnitManage localLogStudyCVUnitManage;
	private ExamPaperFacade examPaperFacade;	
	private ExamExaminationManage examExaminationManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String handle = request.getParameter("handle");
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L); //Long.parseLong(request.getParameter("unitId").trim());YHQ，2017-03-15
	 
		Long paperId = 0L;
		Long examId = 0L;
		//显示试卷
		if(handle.equals("exam")){			
			//考试Id
			examId = ParamUtils.getLongParameter(request, "paperId", 0) ;
			
			//通过考试id查询对应的试卷Id
			paperId = examExaminationManage.queryPaperIdByExamId(examId);
			request.setAttribute("examId", examId);
			request.setAttribute("paperId", paperId);
			request.setAttribute("unitId", unitId);
			
			request.setAttribute("mode", request.getParameter("mode"));
			
			
			//YHQ，2017-07-06，增加前台调用是否通过考试判断
			Long userId = ParamUtils.getLongParameter(request, "userId", 0L);
			LogStudyCVUnit cvs= new LogStudyCVUnit();
			cvs.setCvUnitId(unitId);	
			cvs.setSystemUserId(userId);
			LogStudyCVUnit cvunit =null;
			List<LogStudyCVUnit> logSCVUlist = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs) ;
	        if(logSCVUlist != null && logSCVUlist.size() > 0) {
	        	cvunit = logSCVUlist.get(0);
	        }
	        if(cvunit != null && cvunit.getStatus().equals(2)){//已通过
	        	request.setAttribute("isPassExam", true);
	        }else {
	        	request.setAttribute("isPassExam", false);
	        	/**
				 * 没通过删除考试记录，防止计分重复
				 
	        	List<ExamLog> examLogis = examExaminationManage.queryExamLogIsExist(userId,examId);
	        	if(examLogis!=null && examLogis.size()>0){
	        		Long examLogId = examLogis.get(0).getId();
	        		ExamLogResult examLogResult = new ExamLogResult() ;
	        		examLogResult.setExamLogId(examLogId);
	        		examExaminationManage.delExamLogResult(examLogResult) ;
	        	}	        		        		        	
	        	
	        	ExamLog elObj = new ExamLog() ;
	        	elObj.setUserId(userId);
	        	elObj.setExaminationId(examId);
	        	elObj.setIsnotPass(-1);
	        	elObj.setResult(0.0);
	        	examExaminationManage.updateExamLog(elObj) ;
	        	*/
	        }
	        request.setAttribute("userId", userId);			
		}else{
			//考试Id
			paperId = ParamUtils.getLongParameter(request, "paperId", 0) ;
		}
		
		//自动发布考试
		if (handle.equals("examAutomaticPublish")){
			//考试Id
			request.setAttribute("examId", examId);
			return examAutomaticPublish(mapping,form,request,response);
		}
		
		ExamPaper paper = examPaperFacade.getExamPaperById(paperId);
		if(paper.getExamQuestionList() != null){
			List<ExamQuestion> quesList = paper.getExamQuestionList();
			request.setAttribute("result", Constants.RESULT);
			request.setAttribute("paper", paper);
			request.setAttribute("quesList", quesList);
			request.setAttribute("questionCount", quesList.size());
			request.setAttribute("unitId", unitId);
			
		}
		if (handle.equals("viewPaper")){
			return Constants.SUCCESS;
		}
		if (handle.equals("view")){
				return Constants.SUCCESS_1;
		}
		
		//外部查看试卷
		if (handle.equals("exam")){
			return Constants.SUCCESS_3;
		}
		return Constants.SUCCESS_2;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   张建国
	 * @time     2017-02-21
	 * 方法说明： 通过组课功能自动发布考试
	 */
	private String examAutomaticPublish(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//获取前台参数
		String examName = request.getParameter("examName");
		String paperId = request.getParameter("paperId");
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject result = new JSONObject();
		map.put("examName", examName);
		map.put("paperId", Long.parseLong(paperId));
		Long examId = examExaminationManage.examAutomaticPublish(map);
		if(examId!=null && examId!=0){
			result.put("message", "success");
			result.put("examId", examId);
		}else{
			result.put("message", "fail");
		}
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	public ExamPaperFacade getExamPaperFacade() {
		return examPaperFacade;
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}

	public ExamExaminationManage getExamExaminationManage() {
		return examExaminationManage;
	}

	public void setExamExaminationManage(ExamExaminationManage examExaminationManage) {
		this.examExaminationManage = examExaminationManage;
	}

	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}
	public void setLocalLogStudyCVUnitManage(LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}
	
	
	
}