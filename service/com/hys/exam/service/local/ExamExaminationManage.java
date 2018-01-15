package com.hys.exam.service.local;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamLog;
import com.hys.exam.model.ExamLogResult;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.utils.PageList;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.service.BaseService;

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
public interface ExamExaminationManage extends BaseService {
	/**
	 * 根据试题id获取试题答案，YHQ，2017-07-07
	 * @param questionId 试题id	  
	 */
	public List<ExamQuestionKey> getQuestionKeys(Long questionId) ;
	
	/**
	 * 考试是否使用，YHQ，2017-05-17
	 * @param ExamExamination exam
	 * @return 考试id
	 */
	public boolean  usingExamination(ExamExamination exam) throws FrameworkRuntimeException;
	
	/**
	 * 增加考场
	 * @param ExamExamination exam
	 * @return 考场ID
	 */
	public Long  addExamination(ExamExamination exam);
	
	/**
	 * 批量删除考场
	 * @param Long id
	 * @return
	 */
	public void deleteExamination(List<Long> id);

	/**
	 * 批量删除考场
	 * 
	 * @param id
	 */
	public void deleteExaminationList(List<Long> id);
	
	//恢复
	public void recoverExaminationList(List<Long> id);

	/**
	 * 修改考场含考场试卷
	 * @param ExamExamination exam
	 */
	public void updateExaminationById(ExamExamination exam);
	
	/**
	 * 获取考场信息和考场试卷
	 * @param id
	 * @return
	 */
	public ExamExamination getExamExaminationById(Long id);
	
	/**
	 * 
	 * FunName:getExaminationList
	 * Description: 查询考试列表
	 * @param examExaminationQuery 考试查询对象
	 * @return: List<ExamExamination>
	 */
	public List<ExamExamination> getExaminationList(ExamExaminationQuery examExaminationQuery);

	/**
	 * 
	 * FunName:getExaminationListSize
	 * Description: 查询考试列表总条数
	 * @param examExaminationQuery 考试查询对象
	 * @return: int
	 */
	public int getExaminationListSize(ExamExaminationQuery examExaminationQuery);
	
	
	
	/**
	 * 通过考试ID 数组取考试列表
	 * @param query
	 * @return
	 */
	public List<ExamExamination> getExaminationListByIds(ExamExaminationQuery query);
	
	/**
	 * 通过考试ID 数组取size
	 * @param query
	 * @return
	 */
	public int getExaminationListByIdsSize(ExamExaminationQuery query);
	
	
	/**
	 * 通过考试ID 修改考试分类
	 * @param typeId 分类ID
	 * @param id 考试ID
	 */
	public void updateExaminationTypeByExamId(Long typeId,Long id);

	public void getExaminationList(PageList pl, ExamExaminationQuery query);
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   Long
	 * @time     2017-02-21
	 * 方法说明：  通过考试id查询对应的试卷id
	 */
	public Long queryPaperIdByExamId(Long examId);
	
	/**
	 * @author   张建国
	 * @time     2017-02-21
	 * @param    Map<String,Object>
	 * @return   Long
	 * 方法说明： 自动发布考试方法(考试 考试试卷 考试类型等)
	 */
	@Transactional
	public Long examAutomaticPublish(Map<String,Object> map);
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    LogExam
	 * @return   Long
	 * 方法说明： 提交单个试题
	 */
	public Long subQuestionOne(ExamLog examLog);
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Double
	 * 方法说明： 根据试题id 试卷id 查询试题分数
	 */
	public Double queryQuestionScoreById(Long questionId,Long paperId);
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * @return   ExamExamination
	 * 方法说明：  根据考试Id查询考试信息
	 */
	public ExamExamination queryExamById(Long id);
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long 
	 * @return   ExamLog
	 * 方法说明：  根据用户Id 考试Id查询是否参加过考试
	 */
	public List<ExamLog> queryExamLogIsExist(Long userId,Long examId);
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    ExamLog
	 * @return   void 
	 * 方法说明：  修改用户考试记录
	 */
	public void updateExamLog(ExamLog examLog);
	
	/**
	 * @author 张建国
	 * @time   2017-02-22
	 * @param  ExamLogResult
	 * @return void 
	 * 方法说明：保存考试结果表
	 */
	public void saveQuestionResultLog(ExamLogResult examResultLog);
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    ExamLogResult
	 * @return   void
	 * 方法说明： 修改考试结果表
	 */
	public void updateQuestionResultLog(ExamLogResult examResultLog);
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * @return   ExamLogResult
	 * 方法说明： 查询是否存在考试结果记录
	 */
	public List<ExamLogResult> queryQuestionResultLogById(Long questionId,Long logExamId);
	
	
	
	/***
	 * 
	 * 删除考试记录
	 * 方法名：delExamLogResult
	 * 创建人：程宏业
	 * 时间：2017-3-21-下午6:17:44 
	 * 手机:15210211487
	 * @param examLogResult void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delExamLogResult(ExamLogResult examLogResult);
}
