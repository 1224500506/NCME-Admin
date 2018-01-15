package com.hys.exam.sessionfacade.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionImportTemplate;
import com.hys.exam.model.ExamQuestionLabelNum;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamSubSysQuestType;
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
	public ExamQuestion addQuestion(ExamQuestion quest)
			throws FrameworkRuntimeException;

	/**
	 * 删除试题
	 * 
	 * @param 试题ID
	 *            List<Object[]> id
	 */
	public boolean deleteQuesitons(List<Long> id)
			throws FrameworkRuntimeException;

	/**
	 * 取试题
	 * 
	 * @param 试题ID
	 *            Long id
	 * @return
	 */
	public ExamQuestion getQuestionById(Long id)
			throws FrameworkRuntimeException;

	/**
	 * 导入试题
	 * 
	 * @param List
	 *            <ExamQuestion> questList
	 */
	public void addImportQuestion(List<ExamQuestion> questList)
			throws FrameworkRuntimeException;

	/**
	 * 修改试题
	 * 
	 * @param ExamQuestion
	 *            quest
	 * @return
	 */
	public void updateQuestionById(ExamQuestion quest)
			throws FrameworkRuntimeException;

	/**
	 * 查询试题列表
	 * 
	 * @param quest
	 * @return
	 */
	public List<ExamQuestion> getQuestionList(ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException;
	
	/**
	 * 查询试题列表含答案选项(ZYY)
	 * 
	 * @param quest
	 * @return
	 */
	public List<ExamQuestion> getQuestionListForZyy(ExamQuestionQuery questQuery);

	/**
	 * 查询试题列表 count 总数
	 * 
	 * @param questQuery
	 * @return
	 */
	public int getQuestionSize(ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException;

	/**
	 * 修改试题状态
	 * 
	 * @param id
	 * @param state
	 * @param author
	 */
	public int updateQuestionStateByIdArr(List<Long> id, int state, String author, String opinion)
			throws FrameworkRuntimeException;

	/**
	 * 通过试题ID list 父子试题ID
	 * 
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIds(List<Long> ids)
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
	public void addQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal, int status)
			throws FrameworkRuntimeException;

	/**
	 * 通过题干,题型 检查试题是否存在
	 * 
	 * @param quest
	 * @return
	 */
	public List<ExamQuestion> getCompareQuestion(List<ExamQuestion> questList)
			throws FrameworkRuntimeException;
	
	
	/**
	 * 通过题干,题型 检查试题是否存在
	 * 
	 * @param quest
	 * @return
	 */
	public List<ExamQuestionImportTemplate> getCompareQuestion2(List<ExamQuestionImportTemplate> questList)
			throws FrameworkRuntimeException;

	/**
	 * 通过ID数组取试题,只有试题答案和子试题,
	 * 
	 * @param idArr
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIdArr(Long[] idArr)
			throws FrameworkRuntimeException;

	/**
	 * 根据 试卷ID 取试题列表
	 * 
	 * @param paperId
	 *            试卷ID
	 * @return 试题列表 List<ExamQuestion>
	 */
	public List<ExamQuestion> getQuestionListByPaperId(Long paperId)
			throws FrameworkRuntimeException;

	/**
	 * 通过策略取试题
	 * 
	 * @param paperTactic
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<ExamQuestion> getQuestionList(ExamPaperTactic paperTactic,
			int pageSize, int currentPage) throws FrameworkRuntimeException;

	/**
	 * 通过策略取试题数
	 * 
	 * @param paperTactic
	 * @return
	 */
	public int getQuestionListSize(ExamPaperTactic paperTactic)
			throws FrameworkRuntimeException;

	/**
	 * 通过快捷策略取试题数
	 * 
	 * @param paperTactic
	 * @return
	 */
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(
			ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException;
	
	/**
	 * 
	 * @param questionPropMap 试题属性
	 * @param id	试题ID列表
	 * @param questPropVal	关联属性Key
	 * @param status 试题状态 0：不修改试题状态
	 */
	public void addImportQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal,int status) throws FrameworkRuntimeException;
	
	
	/**
	 * 卷中卷
	 * 通过试卷ID数组，取试卷试题的题型试题总数
	 * @param paperIdArr
	 * @return
	 */
	public List<ExamPaperAndPaper> getQuestionLableNumByPaperIDArr(Long[] paperIdArr) throws FrameworkRuntimeException;
	
	/**
	 * 取试题包含子试题，答案，属性
	 * 
	 * @param ExamQuestionQuery questQuery
	 * @return
	 */
	public List<ExamQuestion> getQuestionAllList(ExamQuestionQuery questQuery) throws FrameworkRuntimeException;
	
	
	/**
	 * 取错误试题属性
	 * 
	 * @param Integer type
	 * @return
	 */
	public List<ExamQuestion> getQuestionListErrorPropId(Integer type) throws FrameworkRuntimeException;
	
	/**
	 * 通过题型，题干查试题
	 * @param quest
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamQuestion> getQuestionByContentAndLabel(ExamQuestion quest) throws FrameworkRuntimeException;
	
	/**
	 * 添加试题分类
	 * 
	 * @param subSysQuestTypeList
	 */
	public void addQuestionSubSysType(List<ExamSubSysQuestType> subSysQuestTypeList) throws FrameworkRuntimeException;
	
	
	/**
	 * 通过属性id和属性级别取试题
	 * @param propId
	 * @param type
	 * @return
	 */
	public List<ExamQuestion> getQuestionListByPropVal(Long propId,Integer type)  throws FrameworkRuntimeException;
	
	/**
	 * 通过系统id,试题id查找试题
	 * @param SysId
	 * @param questionId
	 * @return
	 */
	public boolean getQuestionSubSysTypeBySysId(Long SysId,Long questionId) throws FrameworkRuntimeException;
	
	
	/**
	 * 通过属性id和属性级别取试题题型的试题总数
	 * @param propId
	 * @return
	 */
	public List<ExamQuestionLabelNum> getQuestionLabelNumByPropId(Long propId,Integer type) throws FrameworkRuntimeException;

	public void updateQuestionSourceId(Long oldId, Long newId);
	
	
}
