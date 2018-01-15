package com.hys.exam.struts.action.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamLog;
import com.hys.exam.model.ExamLogResult;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.exam.service.local.ExamExaminationManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.util.JsonUtil;

public class ExamExaminationAction extends ExamBaseAction {		
	private LogStudyCVUnitManage localLogStudyCVUnitManage;		
	private CVUnitManage cvUnitManage;	
	private ExamExaminationManage examExaminationManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String method = request.getParameter("method");
		//long unitId = Long.parseLong(request.getParameter("unitId").trim());
		
		if(method.equals("subQuestionOne")){
			return subQuestionOne(mapping,form,request,response);
		}
	   if(method.equals("CountScore")){
		  return CountScore(mapping,form,request,response);  
	   }
	   if(method.equals("IsPass")){
		   return IsPass(mapping,form,request,response);  
	   }
		
	   return null;
	}
	
	
	/**
	 * 
	 * 判断是否通过达标指标——————交卷
	 * 方法名：CountScore
	 * 创建人：程宏业
	 * 时间：2017-3-15-上午10:25:22 
	 * 手机:15210211487
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 * @exception 
	 * @since  1.0.0
	 */
	public String CountScore(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0);
		Long examId = ParamUtils.getLongParameter(request, "examId", 0);
		Long userid = ParamUtils.getLongParameter(request, "userId", 0);
		
		/*YHQ，2017-06-06，前台调用无须登录
		SystemUser currentUser = (SystemUser)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(currentUser!=null){
			userid =currentUser.getUserId();
		}
		*/
		
		// 总分数
		Double score =0.0;
		LogStudyCVUnit cvs= new LogStudyCVUnit();
		cvs.setCvUnitId(unitId);	
		cvs.setSystemUserId(userid);
		LogStudyCVUnit cvunit =null;
		if(localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs).size()>0){
			 cvunit = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs).get(0);
		}
		// 判断是否参加过这次考试
		List<ExamLog> examLogis = examExaminationManage.queryExamLogIsExist(userid,examId);
		List<ExamLogResult> examLogResultis = examExaminationManage.queryQuestionResultLogById(null,examLogis.get(0).getId());
		
		int questRightNum = 0, questNotRightNum = 0 ;
		
		if(examLogResultis!=null && examLogis.size()>0 ){
			for (ExamLogResult examLog2 : examLogResultis) {
				Integer isNotRight = examLog2.getIsnotRight() ;
				if (isNotRight != null && isNotRight == 1) {
					questRightNum++ ;
					score += examLog2.getRealityscore();
				} else {
					questNotRightNum++ ;
				}								
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		CVUnit cvu = new CVUnit();
		cvu.setId(cvunit!=null?cvunit.getCvUnitId():null);
		CVUnit cvus = cvUnitManage.findCvunit(cvu);
		
		ExamLog elObj = new ExamLog() ;
    	elObj.setUserId(userid);
    	elObj.setExaminationId(examId);
    	elObj.setIsnotPass(-1);
    	elObj.setResult(score);
		
		if(cvus != null && cvus.getPoint().equals(1)){//只需判断是否任务点即可---cvus.getType().equals(3) &&
			if (cvus.getQuota() != null) {
				if(score >= cvus.getQuota()){
					LogStudyCVUnit  units = new LogStudyCVUnit();
					units.setCvUnitId(cvunit.getCvUnitId());
					units.setStatus(2);
					units.setSystemUserId(userid);//YHQ，2017-06-06，不加这个所有学习这个单元的人全通过考试！！！
					localLogStudyCVUnitManage.updLogStudyCVUnitById(units);
									
					elObj.setIsnotPass(0);
					
					map.put("message", "success");
					map.put("score", score.toString());					
				}else{
					map.put("message", "error");
					map.put("score", score.toString());
					/**
					 * 没通过删除考试记录				 
					ExamLogResult res = new ExamLogResult();
					res.setExamLogId(examLogis.get(0).getId());
					examExaminationManage.delExamLogResult(res);*/
				}
			} else {
				LogStudyCVUnit  units = new LogStudyCVUnit();
				units.setCvUnitId(cvunit.getCvUnitId());
				units.setStatus(2);
				units.setSystemUserId(userid);//YHQ，2017-06-06，不加这个所有学习这个单元的人全通过考试！！！
				localLogStudyCVUnitManage.updLogStudyCVUnitById(units);
								
				elObj.setIsnotPass(0);
				
				map.put("message", "success");
				map.put("score", score.toString());
			}						
		}else{
			LogStudyCVUnit  units = new LogStudyCVUnit();
			units.setCvUnitId(cvunit.getCvUnitId());
			units.setStatus(2);
			units.setSystemUserId(userid);//YHQ，2017-06-06，不加这个所有学习这个单元的人全通过考试！！！
			localLogStudyCVUnitManage.updLogStudyCVUnitById(units);
						
        	elObj.setIsnotPass(0);
        	
			map.put("message", "success");
			map.put("score", score.toString());
		}
		
		map.put("questRightNum", questRightNum +"");
		map.put("questNotRightNum", questNotRightNum +"");
		
		examExaminationManage.updateExamLog(elObj) ;
		
		response.getWriter().write(JsonUtil.map2json(map));
	    response.getWriter().flush();
	    response.getWriter().close();
		return null;		
	}
		
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   张建国
	 * @time     2017-02-21
	 * 方法说明：  提交单个试题————答题
	 */
	private String subQuestionOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取前台参数
		//考试Id
		Long examId = ParamUtils.getLongParameter(request, "examId", 0);
		//试卷Id
		Long paperId = ParamUtils.getLongParameter(request, "paperId", 0);
		//试题Id
		Long questionId = ParamUtils.getLongParameter(request, "questionId", 0);
		//选项Id
		Long keyId = ParamUtils.getLongParameter(request, "keyId", 0);
		
		//提供的答案
		String keyIsTrue = request.getParameter("keyIsTrue");
		//试题类别
		//String questionLabel = request.getParameter("questionLabel");
		int questionLabel = ParamUtils.getIntParameter(request, "questionLabel", 0) ;
		
		//用户Id
		Long userId = ParamUtils.getLongParameter(request, "userId", 0); 
		/*YHQ，2017-07-06，前台调用无需登录
    	SystemUser currentUser = (SystemUser)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
    	if(currentUser!=null){
    		userId=currentUser.getUserId()+"";
    	}
    	*/
		
		//返回数据集合
		Map<String,Object> map = new HashMap<String,Object>();
		/*YHQ，2017-07-08，前台调用无需登录
		if(userId==null || "".equals(userId)){
			map.put("message", "noLogin");
			response.getWriter().write(JsonUtil.map2json(map));
		    response.getWriter().flush();
		    response.getWriter().close();
			return null;
		}*/
		
		
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0);
		
		LogStudyCVUnit cvs= new LogStudyCVUnit();
		cvs.setCvUnitId(unitId);	
		cvs.setSystemUserId(userId);
		LogStudyCVUnit cvunit =null;
		List<LogStudyCVUnit> logSCVUlist = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs) ;
        if(logSCVUlist != null && logSCVUlist.size() > 0) {
        	cvunit = logSCVUlist.get(0);
        }
        if(cvunit != null && cvunit.getStatus().equals(2)){//已通过
        	map.put("message", "success");
    		response.getWriter().write(JsonUtil.map2json(map));
    	    response.getWriter().flush();
    	    response.getWriter().close();
    		return null;
        }
		
		//实例化考试日志试题类
		ExamLog examLog = new ExamLog();
		//设置用户Id
		examLog.setUserId(userId);
		//设置系统Id 默认 24
		examLog.setSubSystemId(24L);
		//通过试题id 试卷id 获取试题分数
		Double score = examExaminationManage.queryQuestionScoreById(questionId,paperId);
		//判断是否答对
		//通过试题id获取答案
		List<ExamQuestionKey> eqkList = examExaminationManage.getQuestionKeys(questionId) ;
		boolean questResult = false ;
		String selectResult = "" ;
		
		if (questionLabel == 1 || questionLabel == 2) {//单选题
			selectResult = keyId.toString() ;
			if (eqkList != null && eqkList.size() > 0) {
				for (ExamQuestionKey eqkObj : eqkList) {
					if (eqkObj.getIsnot_true() == 1 && eqkObj.getId().equals(keyId)) {
						questResult = true ;
						break ;
					}
				}
			}
		}
		
		if (questionLabel == 11) {//复选题
			selectResult = keyIsTrue ;
			String[] userQuestKey = keyIsTrue.split(",") ;
			
			if (eqkList != null && eqkList.size() > 0) {
				int keyIsTrueNum = 0 ;
				int userQuestKeyNum = 0 ;
				for (ExamQuestionKey eqkObj : eqkList) {
					if (eqkObj.getIsnot_true() == 1) {
						keyIsTrueNum ++ ;
						for (int i = 0 ; i < userQuestKey.length ; i++) {
							String tmpEqkId = eqkObj.getId().toString() ;
							if (userQuestKey[i].equals(tmpEqkId)) userQuestKeyNum++ ;
						}
					}
				}
				
				if (keyIsTrueNum == userQuestKeyNum) questResult = true ;
			}
		}
		
		if (questionLabel == 13) {//判断题
			selectResult = keyId.toString() ;
			if (eqkList != null && eqkList.size() > 0) {
				for (ExamQuestionKey eqkObj : eqkList) {
					String tmpEqkId = eqkObj.getIsnot_true().toString() ;
					if (tmpEqkId.equals(keyIsTrue)) questResult = true ;
				}
			}
		}
		
		if (questionLabel == 12 || questionLabel == 14 || questionLabel == 18 || questionLabel == 20) {//12填空题,14简答题,18名词解释,20案例分析题
			selectResult = keyIsTrue ;
			//只要不填就算对
			if (!keyIsTrue.trim().equals("")) questResult = true ;
		}
		
		if (questResult) {			
			examLog.setResult(score);//设置试题分数
			examLog.setIsnotPass(0);
		} else {
			examLog.setResult(0.0);	
			examLog.setIsnotPass(-1);
		}		
		
		//获取客户Ip
		examLog.setIp(getIpAddr(request));
		//根据考试id查询考试信息
		ExamExamination exam = examExaminationManage.queryExamById(examId);
		//设置考试Id
		examLog.setExaminationId(exam.getId());
		//设置考试名称
		examLog.setExamName(exam.getName());
		//设置考试类别id
		examLog.setExamTypeId(exam.getExam_type_id());
		//设置考试分类
		examLog.setExamType(exam.getExam_type());
		//设置试卷Id
		examLog.setTestpaperId(paperId);
		//定义考试记录Id
		Long examLogId = 0L;
		
		//判断是否参加过这次考试
		List<ExamLog> examLogis = examExaminationManage.queryExamLogIsExist(userId,examId);
								
		if(examLogis != null && examLogis.size()>0){
			//执行修改操作
			//修改总分数
//			examLogis.get(0).setResult(examLogis.get(0).getResult()+ examLog.getResult());//答题分数累加导致超出数据库字段长度报错
			examLogis.get(0).setResult(examLog.getResult());			
			//修改是否通过
			examLogis.get(0).setIsnotPass(examLog.getIsnotPass());
			examExaminationManage.updateExamLog(examLogis.get(0));
			examLogId = examLogis.get(0).getId();
		}else{
			//执行保存试题日志
			examLogId = examExaminationManage.subQuestionOne(examLog);
		}

		//实例化试题答案实体类
		ExamLogResult examLogResult = new ExamLogResult();
		//设置考试日志Id
		examLogResult.setExamLogId(examLogId);
		//设置试题Id
		examLogResult.setQuestionId(questionId);	
		//设置是否正确
		//examLogResult.setIsnotRight(Integer.parseInt(keyIsTrue));
		if (questResult) examLogResult.setIsnotRight(1) ;
		else examLogResult.setIsnotRight(0) ;
		
		//设置试题类型
		examLogResult.setQuestionLabelId(questionLabel);
		//设置父试题Id
		examLogResult.setParentId(0L);
		//设置分数
		examLogResult.setScore(score);
		//设置选项Id
		examLogResult.setSelectResult(selectResult);
		// 真实分数
		examLogResult.setRealityscore(examLog.getResult());
		
		//判断是否有新的试题答案记录
		List<ExamLogResult> examLogResultis = examExaminationManage.queryQuestionResultLogById(questionId, examLogId);
		if(examLogResultis!=null && examLogResultis.size()>0){
			examLogResult.setId(examLogResultis.get(0).getId());
			//执行修改操作
			examExaminationManage.updateQuestionResultLog(examLogResult);
		}else{
			//执行保存操作
			examExaminationManage.saveQuestionResultLog(examLogResult);
		}
		
		//重新计算总分数，更新总成绩,YHQ,2017-07-19
		Double totalRealityscore = queryTotalRealityscore(userId , examId) ;
		ExamLog totalScoreExamLog = new ExamLog() ;
		totalScoreExamLog.setUserId(userId);
		totalScoreExamLog.setExaminationId(examId);
		totalScoreExamLog.setResult(totalRealityscore);
		totalScoreExamLog.setIsnotPass(-1);	//设置是否通过,试题通过Pass状态为0————答题时全为-1，交卷后判定是否通过	
		examExaminationManage.updateExamLog(totalScoreExamLog) ;
		
		map.put("message", "success");
		response.getWriter().write(JsonUtil.map2json(map));
	    response.getWriter().flush();
	    response.getWriter().close();
		return null;
	}
	
	//获取目前答题的总分数，YHQ,2017-07-19
	public Double queryTotalRealityscore(Long userId, Long examId) {
		Double score = 0.0 ;
				
		//取答题记录
		List<ExamLog> examLogis = examExaminationManage.queryExamLogIsExist(userId,examId);
		if(examLogis != null && examLogis.size() > 0){
			Long examLogId = examLogis.get(0).getId() ;
			if (examLogId != null) {
				List<ExamLogResult> examLogResultis = examExaminationManage.queryQuestionResultLogById(null,examLogId);				
				if(examLogResultis != null && examLogis.size() > 0){
					for (ExamLogResult examLogResultObj : examLogResultis) {
						Integer isNotRight = examLogResultObj.getIsnotRight() ;
						if (isNotRight != null && isNotRight == 1) {					
							Double realityscore = examLogResultObj.getRealityscore() ;
							if (realityscore != null) {
								score += realityscore ;
							}							
						}								
					}
				}
			}
		}
		
		return score ;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    HttpServletRequest
	 * @return   String
	 * 方法说明： 获取客户端IP
	 */
	public String getIpAddr(HttpServletRequest request) { 
       String ip = request.getHeader("x-forwarded-for"); 
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
           ip = request.getHeader("Proxy-Client-IP"); 
       } 
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
           ip = request.getHeader("WL-Proxy-Client-IP"); 
       } 
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
           ip = request.getRemoteAddr(); 
       } 
       return ip; 
    }
	
	/**
	 * 
	 * 判断是否通过考试————废弃！！！
	 * 方法名：IsPass
	 * 创建人：程宏业
	 * 时间：2017-3-15-下午3:11:58 
	 * 手机:15210211487
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 * @exception 
	 * @since  1.0.0
	 */
	public String IsPass(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		long unitId = Long.parseLong(request.getParameter("unitId").trim());
		long userid = Long.parseLong(request.getParameter("userId").trim());
		
		/*YHQ，2017-07-06，前台调用无需登录
		SystemUser currentUser = (SystemUser)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(currentUser!=null){
			userid =currentUser.getUserId();
		}
		*/
		
		// 总分数
		LogStudyCVUnit cvs= new LogStudyCVUnit();
		cvs.setCvUnitId(unitId);	
		cvs.setSystemUserId(userid);
		LogStudyCVUnit cvunit =null;
        if(localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs).size()>0)
		 cvunit = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs).get(0);
		// 判断是否参加过这次考试
        Map<String,String> map = new HashMap<String,String>();
        if(cvunit!=null && cvunit.getStatus().equals(2)){
        	map.put("message", "success");
        }else{
        	map.put("message", "error");
        }
        
		response.getWriter().write(JsonUtil.map2json(map));
	    response.getWriter().flush();
	    response.getWriter().close();
		return null;		
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

	public void setLocalLogStudyCVUnitManage(
			LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}

	public CVUnitManage getCvUnitManage() {
		return cvUnitManage;
	}

	public void setCvUnitManage(CVUnitManage cvUnitManage) {
		this.cvUnitManage = cvUnitManage;
	}
	
	
	

}