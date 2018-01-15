package com.hys.exam.service.local.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hys.exam.dao.local.ExamExaminationDAO;
import com.hys.exam.dao.local.ExamQuestionKeyDAO;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamLog;
import com.hys.exam.model.ExamLogResult;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.service.local.ExamExaminationManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminationManageImpl extends BaseMangerImpl implements ExamExaminationManage {
	private ExamExaminationDAO localExamExaminationDAO;	
	private ExamQuestionKeyDAO localExamQuestionKeyDAO;
	
	/**
	 * 根据试题id获取试题答案，YHQ，2017-07-07
	 * @param questionId 试题id	  
	 */
	public List<ExamQuestionKey> getQuestionKeys(Long questionId) {
		return localExamQuestionKeyDAO.getQuestionKeys(questionId) ;
	}
	
	/**
	 * 考试是否使用，YHQ，2017-05-17
	 * @param ExamExamination exam
	 * @return 考试id
	 */
	@Override
	public boolean  usingExamination(ExamExamination exam) throws FrameworkRuntimeException {
		return localExamExaminationDAO.usingExamination(exam) ;
	}

	@Override
	public Long addExamination(ExamExamination exam) {
		return localExamExaminationDAO.addExamination(exam);
	}

	@Override
	public void deleteExamination(List<Long> id) {
		localExamExaminationDAO.deleteExamination(id);
	}

	@Override
	public ExamExamination getExamExaminationById(Long id) {
		return localExamExaminationDAO.getExamExaminationById(id);
	}

	@Override
	public List<ExamExamination> getExaminationList(
			ExamExaminationQuery examExaminationQuery) {
		return localExamExaminationDAO.getExaminationList(examExaminationQuery);
	}
	
	@Override
	public int getExaminationListSize(ExamExaminationQuery examExaminationQuery) {
		return localExamExaminationDAO.getExaminationListSize(examExaminationQuery);
	}

	@Override
	public void updateExaminationById(ExamExamination exam) {
		localExamExaminationDAO.updateExaminationById(exam);
	}	

	@Override
	public List<ExamExamination> getExaminationListByIds(
			ExamExaminationQuery query) {
		return localExamExaminationDAO.getExaminationListByIds(query);
	}

	@Override
	public int getExaminationListByIdsSize(ExamExaminationQuery query) {
		return localExamExaminationDAO.getExaminationListByIdsSize(query);
	}

	@Override
	public void updateExaminationTypeByExamId(Long typeId, Long id) {
		localExamExaminationDAO.updateExaminationTypeByExamId(typeId, id);
	}

	@Override
	public void deleteExaminationList(List<Long> id) {
		localExamExaminationDAO.deleteExaminationList(id);
	}
	
	//恢复
	@Override
	public void recoverExaminationList(List<Long> id){
		this.localExamExaminationDAO.recoverExaminationList(id);
	}

	@Override
	public void getExaminationList(PageList pl, ExamExaminationQuery query) {
		this.localExamExaminationDAO.getExaminationList(pl, query);
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   Long
	 * @time     2017-02-21
	 * 方法说明：  通过考试id查询对应的试卷id
	 */
	public Long queryPaperIdByExamId(Long examId){
		return this.localExamExaminationDAO.queryPaperIdByExamId(examId);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-21
	 * @param    Map<String,Object>
	 * @return   Long
	 * 方法说明： 自动发布考试方法(考试 考试试卷 考试类型等)
	 */
	@Transactional
	@Override
	public Long examAutomaticPublish(Map<String,Object> map){
		return this.localExamExaminationDAO.examAutomaticPublish(map);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    LogExam
	 * @return   Long
	 * 方法说明：  提交单个试题
	 */
	public Long subQuestionOne(ExamLog examLog){
		return this.localExamExaminationDAO.subQuestionOne(examLog);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * 方法说明： 根据试题id 试卷id 查询试题分数
	 */
	public Double queryQuestionScoreById(Long questionId,Long paperId){
		return this.localExamExaminationDAO.queryQuestionScoreById(questionId,paperId);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * @return   ExamExamination
	 * 方法说明：  根据考试Id查询考试信息
	 */
	public ExamExamination queryExamById(Long id){
		return this.localExamExaminationDAO.queryExamById(id);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long 
	 * @return   ExamLog
	 * 方法说明：  根据用户Id 考试Id查询是否参加过考试
	 */
	public List<ExamLog> queryExamLogIsExist(Long userId,Long examId){
		return this.localExamExaminationDAO.queryExamLogIsExist(userId,examId);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    ExamLog
	 * @return   void 
	 * 方法说明：  修改用户考试记录
	 */
	public void updateExamLog(ExamLog examLog){
		this.localExamExaminationDAO.updateExamLog(examLog);
	}
	
	/**
	 * @author 张建国
	 * @time   2017-02-22
	 * @param  ExamLogResult
	 * @return void 
	 * 方法说明：保存考试结果表
	 */
	public void saveQuestionResultLog(ExamLogResult examResultLog){
		this.localExamExaminationDAO.saveQuestionResultLog(examResultLog);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    ExamLogResult
	 * @return   void
	 * 方法说明： 修改考试结果表
	 */
	public void updateQuestionResultLog(ExamLogResult examResultLog){
		this.localExamExaminationDAO.updateQuestionResultLog(examResultLog);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * @return   ExamLogResult
	 * 方法说明： 查询是否存在考试结果记录
	 */
	public List<ExamLogResult> queryQuestionResultLogById(Long questionId,Long logExamId){
		return this.localExamExaminationDAO.queryQuestionResultLogById(questionId,logExamId);
	}
	
	
	/**
	 * 
	 * 删除考试记录
	 * 方法名：delExamLogResult
	 * 创建人：程宏业
	 * 时间：2017-3-21-下午6:16:18 
	 * 手机:15210211487
	 * @param examLogResult void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delExamLogResult(ExamLogResult examLogResult){
		localExamExaminationDAO.delExamLogResult(examLogResult);
	}
		
	public ExamExaminationDAO getLocalExamExaminationDAO() {
		return localExamExaminationDAO;
	}
	public void setLocalExamExaminationDAO(
			ExamExaminationDAO localExamExaminationDAO) {
		this.localExamExaminationDAO = localExamExaminationDAO;
	}

	public ExamQuestionKeyDAO getLocalExamQuestionKeyDAO() {
		return localExamQuestionKeyDAO;
	}
	public void setLocalExamQuestionKeyDAO(ExamQuestionKeyDAO localExamQuestionKeyDAO) {
		this.localExamQuestionKeyDAO = localExamQuestionKeyDAO;
	}
	
}
