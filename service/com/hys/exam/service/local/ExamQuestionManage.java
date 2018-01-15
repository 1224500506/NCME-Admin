package com.hys.exam.service.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTacticX;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabelNum;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.framework.service.BaseService;

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
public interface ExamQuestionManage extends BaseService {

	/**
	 * 添加试题
	 * 
	 * @param ExamQuestion
	 *            quest
	 * @param saveParent
	 * @return
	 */
	public ExamQuestion addQuestion(ExamQuestion quest);

	/**
	 * 删除试题
	 * 
	 * @param 试题ID
	 *            List<Long> id
	 */
	public void deleteQuesitons(List<Long> id);

	/**
	 * 取试题
	 * 
	 * @param 试题ID
	 *            Long id
	 * @return
	 */
	public ExamQuestion getQuestionById(Long id);

	/**
	 * 取子试题
	 * 
	 * @param parentId
	 *            父试题ID
	 * @return
	 */
	public List<ExamQuestion> getChildQuestionByParentId(Long parentId);

	/**
	 * 导入试题
	 * 
	 * @param List
	 *            <ExamQuestion> questList
	 */
	public void addImportQuestion(List<ExamQuestion> questList);
	
	/**
	 * 修改试题
	 * 
	 * @param ExamQuestion
	 *            quest
	 * @return
	 */
	public void updateQuestionById(ExamQuestion quest);

	/**
	 * 查询试题列表
	 * 
	 * @param quest
	 * @return
	 */
	public List<ExamQuestion> getQuestionList(ExamQuestionQuery questQuery);
	
	/**
	 * 查询试题列表含试题答案选项(ZYY)
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
	public int getQuestionSize(ExamQuestionQuery questQuery);

	/**
	 * 修改试题状态
	 * 
	 * @param id
	 * @param state
	 * @param author
	 */
	public int updateQuestionStateByIdArr(List<Long> id, int state,String author, String opinion);

	/**
	 * 通过试题ID list 父子试题ID
	 * 
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIds(List<Long> ids);

	/**
	 * 
	 * @param questionPropMap 试题属性
	 * @param id	试题ID列表
	 * @param questPropVal	关联属性Key
	 * @param status 试题状态 0：不修改试题状态
	 */
	public void addQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal,int status);
	
	/**
	 * 通过题干,题型 检查试题是否存在
	 * @param quest
	 * @return
	 */
	public List<ExamQuestion> getQuestionByContentAndLabel(ExamQuestion quest);
	
	
	/**
	 * 通过ID数组取试题,只有试题答案和子试题,
	 * @param idArr
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIdArr(Long[] idArr);
	

	/**
	 * 根据 试卷ID 取试题列表
	 * @param paperId 试卷ID
	 * @return 试题列表 List<ExamQuestion>
	 */	
	public List<ExamQuestion> getQuestionListByPaperId(Long paperId);
	
	
	/**
	 * 通过策略取试题
	 * @param paperTactic
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<ExamQuestion> getQuestionList(ExamPaperTactic paperTactic,int pageSize, int currentPage);
	
	/**
	 * 通过策略取试题数
	 * @param paperTactic
	 * @return
	 */
	public int getQuestionListSize(ExamPaperTactic paperTactic);	
	
	
	/**
	 * 通过快捷策略取试题数
	 * @param paperTactic
	 * @return
	 */
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(ExamPaperFasterTactic paperTactic);	
	
	
	/**
	 * 通过快捷策略取试题数
	 * @param tXList
	 * @return
	 */
	public void getQuestSizeByFasterTactic(List<ExamPaperFasterTacticX> tXList);
	
	
	/**
	 * 通过快捷策略2取试题数
	 * @param question_lable_id
	 * @param question_type_id
	 * @param t2List
	 * @param questionPropMap
	 * @param isnot_multimedia
	 */
	public List<ExamPaperFasterTactic2> getQuestSizeByFasterTactic(
			int question_lable_id, String question_type_id,
			List<ExamPaperFasterTactic2> t2List,
			Map<String, List<ExamQuestionProp>> questionPropMap,Integer isnot_multimedia);	
	
	/**
	 * 通过快捷策略取试
	 * @param question_lable_id 题型
	 * @param question_type_id　分类ID
	 * @param t2	级联属性
	 * @param questionPropMap　非级联属性
	 * @return
	 */
	public List<ExamQuestion> getQuestionListByByFasterTactic(
			int question_lable_id, String question_type_id,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap,Integer isnot_multimedia);
	
	
	/**
	 * 
	 * @param questionPropMap 试题属性
	 * @param id	试题ID列表
	 * @param questPropVal	关联属性Key
	 * @param status 试题状态 0：不修改试题状态
	 */
	public void addImportQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal,int status);
	
	
	/**
	 * 卷中卷
	 * 通过试卷ID数组，取试卷试题的题型试题总数
	 * @param paperIdArr
	 * @return
	 */
	public List<ExamPaperAndPaper> getQuestionLableNumByPaperIDArr(Long[] paperIdArr);
	
	
	/**
	 * 卷中卷
	 * 通过试卷ID数组和题型，取试卷试题
	 * @param paperIdArr
	 * @return
	 */
	public List<ExamQuestion> getQuestionByPaperIDArr(Long[] paperIdArr,Integer question_label_id);
	
	/**
	 * 取试题包含子试题，答案，属性
	 * 
	 * @param ExamQuestionQuery questQuery
	 * @return
	 */
	public List<ExamQuestion> getQuestionAllList(ExamQuestionQuery questQuery);
	
	/**
	 * 取错误试题属性
	 * 
	 * @param Integer type
	 * @return
	 */
	public List<ExamQuestion> getQuestionListErrorPropId(Integer type);
	
	
	/**
	 * 添加试题分类
	 * 
	 * @param quest
	 */
	public void addQuestionSubSysType(List<ExamSubSysQuestType> subSysQuestTypeList);
	
	
	/**
	 * 通过属性id和属性级别取试题
	 * @param propId
	 * @param type
	 * @return
	 */
	public List<ExamQuestion> getQuestionListByPropVal(Long propId,Integer type);
	
	/**
	 * 通过系统id,试题id查找试题
	 * @param SysId
	 * @param questionId
	 * @return
	 */
	public boolean getQuestionSubSysTypeBySysId(Long SysId,Long questionId);
	
	
	/**
	 * 通过属性id和属性级别取试题题型的试题总数
	 * @param propId
	 * @return
	 */
	public List<ExamQuestionLabelNum> getQuestionLabelNumByPropId(Long propId,Integer type);

	public void updateQuestionSourceId(Long oldId, Long newId);
}
