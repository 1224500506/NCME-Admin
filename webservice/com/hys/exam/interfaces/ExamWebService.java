package com.hys.exam.interfaces;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamExaminType;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.returnObj.ExamReturnExamination;
import com.hys.exam.returnObj.ExamReturnPaper;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.exam.returnObj.ExamReturnQuestion;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.framework.exception.FrameworkRuntimeException;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-5-31
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamWebService extends BaseWebService{
	
	//---------------------------------------------试题属性接口--------------------------------------------------
	/**
	 * 试题属性
	 * 获取无关系属性
	 * @return Map<String,List<ExamPropVal>>
	 */
	public Map<String,List<ExamPropVal>> getBasePropVal(String key) throws FrameworkRuntimeException;
	
	/**
	 * 试题属性
	 * 获取关系属性
	 * @param type
	 * @return List<ExamPropValTemp>
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamPropValTemp> getBasePropValRal(String key,Integer type) throws FrameworkRuntimeException;
	
	
	/**
	 * 试题属性
	 * 获取试题类型
	 * @param type 0: 取所有; 1： 不是子试题题型
	 * @return
	 */
	public List<ExamQuestionLabel> getQuestionLabel(String key,int type)  throws FrameworkRuntimeException;
	//==============================================试题属性接口END===================================================
	
	
	//---------------------------------------------考试分类接口-------------------------------------------------------
	/**
	 * 考试分类
	 * 增加考试分类
	 * @param ExamExaminType etype
	 * @return ExamExaminType
	 */
	public ExamExaminType addExamExaminType(String key,ExamExaminType etype) throws FrameworkRuntimeException;
	
	/**
	 * 考试分类
	 * 修改考试分类
	 * @param ExamExaminType etype
	 * @return ExamExaminType
	 */
	public ExamExaminType updateExamExaminType(String key,ExamExaminType etype) throws FrameworkRuntimeException;
	
	/**
	 * 考试分类
	 * 通过分类ID删除考试分类
	 */
	public boolean deleteExamExaminTypeById(String key,ExamExaminType etype) throws FrameworkRuntimeException;
	
	/**
	 * 考试分类
	 * 通过分类ID取考试分类
	 * @param Long id
	 * @return ExamExaminType
	 */
	public ExamExaminType getExamExaminTypeById(String key,Long id) throws FrameworkRuntimeException;
	
	/**
	 * 考试分类
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param id
	 * @return List<ExamExaminType>
	 */
	public List<ExamExaminType> getExamExaminTypeRootBySysId(String key,Integer[] id) throws FrameworkRuntimeException;
	
	/**
	 * 考试分类
	 * 通过ID 查询 根结点
	 * @param id
	 * @return List<ExamExaminType>
	 */
	public List<ExamExaminType> getExamExaminTypeRootById(String key,Integer[] id) throws FrameworkRuntimeException;
	
	/**
	 * 考试分类
	 * 通过父ID 取子结点
	 * @param ExamExaminTypeQuery
	 * @return ExamReturnExaminType
	 */
	public ExamReturnExaminType getExamExaminTypeListByParentId(String key,ExamExaminTypeQuery query) throws FrameworkRuntimeException;
	
	
	/**
	 * 考试分类
	 * 移动节点
	 * @param parent_id,code 
	 * @return
	 */	
	public boolean updateMoveExaminType(String key,ExamExaminType etype)  throws FrameworkRuntimeException;	
	//=============================================考试分类接口END===================================================	

	//---------------------------------------------考试接口----------------------------------------------------------
	/**
	 * 考试
	 * 增加考场
	 * @param ExamExamination exam
	 * @return 考场ID
	 */
	public Long addExamExamination(String key,ExamExamination exam) throws FrameworkRuntimeException;
	
	/**
	 * 考试
	 * 修改考场
	 * @param ExamExamination exam
	 */
	public void updateExamExamination(String key,ExamExamination exam) throws FrameworkRuntimeException;

	/**
	 * 考试
	 * 考场列表
	 * @param query
	 * @return
	 */
	public ExamReturnExamination getExamExaminationList(String key,ExamExaminationQuery query) throws FrameworkRuntimeException;
	
	/**
	 * 考试
	 * 批量删除考场
	 * @param Long id
	 * @return
	 */
	public void deleteExamination(String key,List<Long> id) throws FrameworkRuntimeException;	

	/**
	 * 考试
	 * 获取考场信息和考场试卷
	 * @param id
	 * @return
	 */
	public ExamExamination getExamExaminationById(String key,Long id) throws FrameworkRuntimeException;	
	
	
	/**
	 * 考试
	 * 通过考试ID 数组取考试列表
	 * @param query
	 * @return
	 */
	public ExamReturnExamination getExaminationListByIds(String key,ExamExaminationQuery query) throws FrameworkRuntimeException;
	
	/**
	 * 考试
	 * 通过考试ID 修改考试分类
	 * @param typeId 分类ID
	 * @param id 考试ID
	 */
	public void updateExaminationTypeByExamId(String key,Long typeId,Long id) throws FrameworkRuntimeException;	
	//=============================================考试接口END===================================================
	
	//---------------------------------------------试卷分类接口---------------------------------------------------
	/**
	 * 试卷分类
	 * 增加试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType addExamPaperType(String key,ExamPaperType ptype) throws FrameworkRuntimeException;
	
	/**
	 * 试卷分类
	 * 修改试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType updateExamPaperType(String key,ExamPaperType ptype) throws FrameworkRuntimeException;
	
	/**
	 * 试卷分类
	 * 删除试卷分类
	 * @param ptype
	 */
	public boolean deleteExamPaperType(String key,ExamPaperType ptype) throws FrameworkRuntimeException;
	
	/**
	 * 试卷分类
	 * 通过分类ID取试卷分类
	 * @param id
	 * @return ExamPaperType
	 */
	public ExamPaperType getExamPaperTypeById(String key,Long id) throws FrameworkRuntimeException;

	/**
	 * 试卷分类
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(String key,Integer[] idArr) throws FrameworkRuntimeException;
	
	/**
	 * 试卷分类
	 * 通过父ID 取子结点
	 * @param ExamPaperTypeQuery
	 * @return ExamReturnPaperType
	 */
	public ExamReturnPaperType getExamPaperTypeListByParentId(String key,ExamPaperTypeQuery query) throws FrameworkRuntimeException;
	
	
	
	/**
	 * 试卷分类
	 * 移动节点
	 * @param parent_id,code 
	 * @return
	 */
	public boolean updateMovePaperType(String key,ExamPaperType ptype)  throws FrameworkRuntimeException;
	
	
	/**
	 * 通过ID 查询 根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListById(String key,Integer[] idArr) throws FrameworkRuntimeException;	
	//=============================================试卷分类接口END================================================

	//---------------------------------------------试卷接口------------------------------------------------------
	
	/**
	 * 试卷
	 * 新增试卷
	 * @param paper
	 * @return  paper ID
	 */
	public Long addExamPaper(String key,ExamPaper paper) throws FrameworkRuntimeException;
	
	/**
	 * 试卷
	 * 修改试卷
	 * @param paper
	 */
	public void updateExamPaper(String key,ExamPaper paper) throws FrameworkRuntimeException;
	
	/**
	 * 试卷
	 * 通过ID 取试卷
	 * @param id
	 * @return ExamPaper
	 */
	public ExamPaper getExamPaperById(String key,Long id) throws FrameworkRuntimeException;

	
	/**
	 * 试卷
	 * 查询试卷列表
	 * @param ExamPaperQuery query
	 * @return ExamReturnPaper
	 */
	public ExamReturnPaper getExamPaperList(String key,ExamPaperQuery query) throws FrameworkRuntimeException;
	

	
	/**
	 * 试卷
	 * 删除试卷
	 * @param id
	 */
	public void deleteExamPaper(String key,Long[] id) throws FrameworkRuntimeException;
	
	/**
	 * 试卷
	 * 通过考场ID 取 试卷列表
	 * @param examId
	 * @param paperId 上一次考过的试卷ID 没有则为 null 或 0
	 * @return
	 */
	public ExamReturnPaper getExamPaperListByExamId(String key,Long examId,Long paperId) throws FrameworkRuntimeException;	
	
	
	/**
	 * 试卷
	 * 删除快捷策略
	 * @param id
	 */
	public void deleteExamPaperFasterTactic(String key,Long id) throws FrameworkRuntimeException;
	
	/**
	 * 试卷
	 * 通过ID取策略模板
	 * @param id
	 * @return
	 */
	public ExamPaperFasterTactic getExamPaperFasterTacticById(String key,Long id) throws FrameworkRuntimeException;
	
	/**
	 * 试卷
	 * 通过试卷分类查询快捷策略列表
	 * @param tactic　
	 * @return
	 */
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(String key,ExamPaperFasterTactic tactic) throws FrameworkRuntimeException;

	/**
	 * 试卷
	 * 通过试卷ID修改试卷分类
	 * @param paper_type_id 试卷分类ID
	 * @param paperId	试卷ID
	 */
	public void updateExamePaperTypeByPaperId(String key,Long paper_type_id,Long paperId) throws FrameworkRuntimeException;
	
	/**
	 * 查询符合策略条件试题数
	 * @param paper
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public ExamPaper getQuestSizeByFasterTactic(String key,ExamPaper paper) throws FrameworkRuntimeException;
	
	/**
	 * 更换试卷试题
	 * @param paperId
	 * @param oldQuestionID
	 * @param newQuestionId
	 * @param score
	 */
	public void updateExamPaperQuestion(String key,Long paperId,Long oldQuestionID,Long newQuestionId,Double score) throws FrameworkRuntimeException;
	//=============================================试卷接口END===================================================
	
	//---------------------------------------------试题分类接口---------------------------------------------------
	
	/**
	 * 试题分类
	 * 增加试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType addExamQuestType(String key,ExamQuestionType qtype) throws FrameworkRuntimeException;
	
	/**
	 * 试题分类
	 * 修改试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType updateExamQuestTypeById(String key,ExamQuestionType qtype) throws FrameworkRuntimeException;
	
	/**
	 * 试题分类
	 * 通过分类ID删除试题分类
	 * @param ExamQuestionType qtype
	 */
	public boolean deleteExamQuestTypeById(String key,ExamQuestionType qtype) throws FrameworkRuntimeException;
	
	/**
	 * 试题分类
	 * 通过分类ID取试题分类
	 * @param Long id
	 * @return ExamQuestionType
	 */
	public ExamQuestionType getExamQuestionTypeById(String key,Long id) throws FrameworkRuntimeException;
	

	/**
	 * 试题分类
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param Integer[] id
	 * @return List<ExamQuestionType>
	 */
	public List<ExamQuestionType> getExamQuestionTypeRootBySysId(String key,Integer[] id) throws FrameworkRuntimeException;
	
	/**
	 * 试题分类
	 * 通过父ID查子节点
	 * @param ExamQuestionTypeQuery
	 * @return ExamReturnQuestionType
	 */
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(String key,ExamQuestionTypeQuery query) throws FrameworkRuntimeException;
	
	
	/**
	 * 试题分类
	 * 移动节点
	 * @param parent_id,code 
	 * @return
	 */
	public boolean updateMoveQuestionType(String key,ExamQuestionType qtype) throws FrameworkRuntimeException;
	
	
	/**
	 * 试题分类
	 * 取所有root结点
	 * @return
	 */
	public List<ExamQuestionType> getQuestionTypeRootById(String key,Integer[] idArr) throws FrameworkRuntimeException;
	//=============================================试题分类END===================================================	
	
	//---------------------------------------------试题接口-------------------------------------------------------
	/**
	 * 试题
	 * 新增试题
	 * 如果返回 null, 题型和内容已经在题库中存在.
	 * @param quest
	 * @return 
	 */
	public ExamQuestion addExamQuestion(String key,ExamQuestion quest) throws FrameworkRuntimeException;
	
	/**
	 * 试题
	 * 修改试题
	 * @param quest
	 * @return
	 */
	public void updateExamQuestionById(String key,ExamQuestion quest) throws FrameworkRuntimeException;
	
	/**
	 * 试题
	 * 通过ID取试题
	 * @param id
	 * @return
	 */
	public ExamQuestion getExamQuestionById(String key,Long id) throws FrameworkRuntimeException;
	
	
	/**
	 * 试题
	 * 修改试题状态
	 * @param 试题 List idArr
	 * @param 状态类型 int state
	 */
	public void updateExamQuestionStateByIds(String key,List<Long> idArr,int state) throws FrameworkRuntimeException;
	
	
	/**
	 * 试题
	 * 通过查询条件返回试题列表(String key,分页)
	 * @param query
	 * @return ExamReturnQuestion
	 */
	public ExamReturnQuestion getExamQuestionList(String key,ExamQuestionQuery query) throws FrameworkRuntimeException;

	
	/**
	 * 试题
	 * 通过ID数组取试题,只有试题答案和子试题
	 * @param idArr
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIdArr(String key,Long[] idArr) throws FrameworkRuntimeException;
	
	/**
	 * 试题
	 * 根据 试卷ID 取试题列表
	 * @param paperId 试卷ID
	 * @param order 0:不打乱顺序 1:打乱顺序
	 * @return 试题列表 ExamReturnQuestion
	 */
	public ExamReturnQuestion getQuestionListByPaperId(String key,Long paperId,int order) throws FrameworkRuntimeException;	
	
	
	/**
	 * 试题
	 * 通过策略取试题数
	 * @param paperTactic
	 * @return
	 */
	public int getQuestionListSize(String key,ExamPaperTactic paperTactic) throws FrameworkRuntimeException;		

	
	/**
	 * 试题
	 * 通过快捷策略取试题数
	 * @param paperTactic
	 * @return
	 */
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(String key,ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException;		
	
	//=============================================试题接口END===================================================	

}
