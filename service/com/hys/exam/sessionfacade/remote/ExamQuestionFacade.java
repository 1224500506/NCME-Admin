package com.hys.exam.sessionfacade.remote;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.BaseSessionFacade;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-18
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionFacade extends BaseSessionFacade {

	/**
	 * 添加试题
	 * 
	 * @param ExamQuestion
	 *            quest
	 * @param saveParent
	 * @return
	 */
	public ExamQuestion addQuestion(String key, ExamQuestion quest)
			throws FrameworkRuntimeException;

	/**
	 * 删除试题
	 * 
	 * @param 试题ID
	 *            List<Object[]> id
	 */
	public boolean deleteQuesitons(String key, List<Long> id)
			throws FrameworkRuntimeException;

	/**
	 * 取试题
	 * 
	 * @param 试题ID
	 *            Long id
	 * @return
	 */
	public ExamQuestion getQuestionById(String key, Long id)
			throws FrameworkRuntimeException;

	/**
	 * 修改试题
	 * 
	 * @param ExamQuestion
	 *            quest
	 * @return
	 */
	public void updateQuestionById(String key, ExamQuestion quest)
			throws FrameworkRuntimeException;

	/**
	 * 查询试题列表
	 * 
	 * @param quest
	 * @return
	 */
	public List<ExamQuestion> getQuestionList(String key,
			ExamQuestionQuery questQuery) throws FrameworkRuntimeException;

	/**
	 * 查询试题列表 count 总数
	 * 
	 * @param questQuery
	 * @return
	 */
	public int getQuestionSize(String key, ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException;

	/**
	 * 修改试题状态
	 * 
	 * @param id
	 * @param state
	 *            被修改状态为
	 */
	public void updateQuestionStateByIdArr(String key, List<Long> id, int state)
			throws FrameworkRuntimeException;

	/**
	 * 通过试题ID list 父子试题ID
	 * 
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIds(String key, List<Long> ids)
			throws FrameworkRuntimeException;

	/**
	 * 
	 * @param questionPropMap
	 *            试题属性
	 * @param id
	 *            试题ID列表
	 * @param questPropVal
	 *            关联属性Key
	 * @param status
	 *            试题状态 0：不修改试题状态
	 */
	public void addQuestionProp(String key,
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal, int status)
			throws FrameworkRuntimeException;

	/**
	 * 通过题干,题型 检查试题是否存在
	 * 
	 * @param quest
	 * @return
	 */
	public List<ExamQuestion> getCompareQuestion(String key,
			List<ExamQuestion> questList) throws FrameworkRuntimeException;

	/**
	 * 通过ID数组取试题,只有试题答案和子试题,
	 * 
	 * @param idArr
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIdArr(String key, Long[] idArr)
			throws FrameworkRuntimeException;

	/**
	 * 根据 试卷ID 取试题列表
	 * 
	 * @param paperId
	 *            试卷ID
	 * @return 试题列表 List<ExamQuestion>
	 */
	public List<ExamQuestion> getQuestionListByPaperId(String key, Long paperId)
			throws FrameworkRuntimeException;

	/**
	 * 通过策略取试题
	 * 
	 * @param paperTactic
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<ExamQuestion> getQuestionList(String key,
			ExamPaperTactic paperTactic, int pageSize, int currentPage)
			throws FrameworkRuntimeException;

	/**
	 * 通过策略取试题数
	 * 
	 * @param paperTactic
	 * @return
	 */
	public int getQuestionListSize(String key, ExamPaperTactic paperTactic)
			throws FrameworkRuntimeException;

	/**
	 * 通过快捷策略取试题数
	 * 
	 * @param paperTactic
	 * @return
	 */
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(String key,
			ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException;
}
