package com.hys.exam.interfaces.impl;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hys.exam.interfaces.ExamWebService;
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
 * 作者：Tony Jun 14, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamWebServiceClient {
	
	private String url;
	
	private static HessianProxyFactory hessianFactory;
	
	private static ExamWebService examWebService;
	
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		initService();
	}

	private void initService() {
        try {
            System.out.println("远程服务初始化");
            hessianFactory = new HessianProxyFactory();
            examWebService = (ExamWebService) hessianFactory.create(ExamWebService.class,
                    url + "examWebService");
        } catch (MalformedURLException e) {
            throw new RuntimeException("远程服务初始化失败");
        }    	
    }
	
    /**
     *手动开始一个事务
     */
    public static String beginTransaction() throws Exception {
        System.out.println("=====transaction is begin=====" );
        String key = examWebService.beginTransaction();
        threadLocal.set(key);
        return key;
    }

    /**
     *手动提交一个事务
     */
    public static void commitTransaction() throws Exception {
        System.out.println("=====transaction is commit=====" );
        examWebService.commit(threadLocal.get());
        threadLocal.remove();
    }

    /**
     *手动回滚一个事务
     */
    public static void rollbackTransaction() throws Exception {
        System.out.println("=====transaction is rollback=====" );
        examWebService.rollback(threadLocal.get());
        threadLocal.remove();
    }

    
	//---------------------------------------------试题属性接口--------------------------------------------------
	/**
	 * 试题属性
	 * 获取无关系属性
	 * @return Map<String,List<ExamPropVal>>
	 */
	public Map<String,List<ExamPropVal>> getBasePropVal() throws FrameworkRuntimeException {
		return examWebService.getBasePropVal(threadLocal.get());
	}
	
	/**
	 * 试题属性
	 * 获取关系属性
	 * @param type
	 * @return List<ExamPropValTemp>
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamPropValTemp> getBasePropValRal(Integer type) throws FrameworkRuntimeException{
		return examWebService.getBasePropValRal(threadLocal.get(), type);
	}
	
	
	/**
	 * 试题属性
	 * 获取试题类型
	 * @param type 0: 取所有; 1： 不是子试题题型
	 * @return
	 */
	public List<ExamQuestionLabel> getQuestionLabel(int type)  throws FrameworkRuntimeException {
		return examWebService.getQuestionLabel(threadLocal.get(), type);
	}
	//==============================================试题属性接口END===================================================
	
	
	//---------------------------------------------考试分类接口-------------------------------------------------------
	/**
	 * 考试分类
	 * 增加考试分类
	 * @param ExamExaminType etype
	 * @return ExamExaminType
	 */
	public ExamExaminType addExamExaminType(ExamExaminType etype) throws FrameworkRuntimeException {
		return examWebService.addExamExaminType(threadLocal.get(), etype);
	}
	
	/**
	 * 考试分类
	 * 修改考试分类
	 * @param ExamExaminType etype
	 * @return ExamExaminType
	 */
	public ExamExaminType updateExamExaminType(ExamExaminType etype) throws FrameworkRuntimeException {
		return examWebService.updateExamExaminType(threadLocal.get(), etype);
	}
	
	/**
	 * 考试分类
	 * 通过分类ID删除考试分类
	 */
	public boolean deleteExamExaminTypeById(ExamExaminType etype) throws FrameworkRuntimeException {
		return examWebService.deleteExamExaminTypeById(threadLocal.get(), etype);
	}
	
	/**
	 * 考试分类
	 * 通过分类ID取考试分类
	 * @param Long id
	 * @return ExamExaminType
	 */
	public ExamExaminType getExamExaminTypeById(Long id) throws FrameworkRuntimeException {
		return examWebService.getExamExaminTypeById(threadLocal.get(), id);
	}
	
	/**
	 * 考试分类
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param id
	 * @return List<ExamExaminType>
	 */
	public List<ExamExaminType> getExamExaminTypeRootBySysId(Integer[] idArr) throws FrameworkRuntimeException {
		return examWebService.getExamExaminTypeRootBySysId(threadLocal.get(), idArr);
	}
	
	/**
	 * 考试分类
	 * 通过ID 查询 根结点
	 * @param id
	 * @return List<ExamExaminType>
	 */
	public List<ExamExaminType> getExamExaminTypeRootById(Integer[] id) throws FrameworkRuntimeException {
		return examWebService.getExamExaminTypeRootById(threadLocal.get(), id);
	}
	
	/**
	 * 考试分类
	 * 通过父ID 取子结点
	 * @param ExamExaminTypeQuery
	 * @return ExamReturnExaminType
	 */
	public ExamReturnExaminType getExamExaminTypeListByParentId(ExamExaminTypeQuery query) throws FrameworkRuntimeException {
		return examWebService.getExamExaminTypeListByParentId(threadLocal.get(), query);
	}
	
	
	/**
	 * 考试分类
	 * 移动节点
	 * @param parent_id,code 
	 * @return
	 */	
	public boolean updateMoveExaminType(ExamExaminType etype)  throws FrameworkRuntimeException {
		return examWebService.updateMoveExaminType(threadLocal.get(), etype);
	}
	//=============================================考试分类接口END===================================================	

	//---------------------------------------------考试接口----------------------------------------------------------
	/**
	 * 考试
	 * 增加考场
	 * @param ExamExamination exam
	 * @return 考场ID
	 */
	public Long addExamExamination(ExamExamination exam) throws FrameworkRuntimeException {
		return examWebService.addExamExamination(threadLocal.get(), exam);
	}
	
	/**
	 * 考试
	 * 修改考场
	 * @param ExamExamination exam
	 */
	public void updateExamExamination(ExamExamination exam) throws FrameworkRuntimeException {
		examWebService.updateExamExamination(threadLocal.get(), exam);
	}

	/**
	 * 考试
	 * 考场列表
	 * @param query
	 * @return
	 */
	public ExamReturnExamination getExamExaminationList(ExamExaminationQuery query) throws FrameworkRuntimeException {
		return examWebService.getExamExaminationList(threadLocal.get(), query);
	}
	
	/**
	 * 考试
	 * 批量删除考场
	 * @param List<Long> idArr
	 * @return
	 */
	public void deleteExamination(List<Long> idArr) throws FrameworkRuntimeException {
		examWebService.deleteExamination(threadLocal.get(), idArr);
	}

	/**
	 * 考试
	 * 获取考场信息和考场试卷
	 * @param id
	 * @return
	 */
	public ExamExamination getExamExaminationById(Long id) throws FrameworkRuntimeException {
		return examWebService.getExamExaminationById(threadLocal.get(), id);
	}
	
	
	/**
	 * 考试
	 * 通过考试ID 数组取考试列表
	 * @param query
	 * @return
	 */
	public ExamReturnExamination getExaminationListByIds(ExamExaminationQuery query) throws FrameworkRuntimeException {
		return examWebService.getExaminationListByIds(threadLocal.get(), query);
	}
	
	/**
	 * 考试
	 * 通过考试ID 修改考试分类
	 * @param typeId 分类ID
	 * @param id 考试ID
	 */
	public void updateExaminationTypeByExamId(Long typeId,Long id) throws FrameworkRuntimeException {
		examWebService.updateExaminationTypeByExamId(threadLocal.get(),typeId, id);
	}
	//=============================================考试接口END===================================================
	
	//---------------------------------------------试卷分类接口---------------------------------------------------
	/**
	 * 试卷分类
	 * 增加试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType addExamPaperType(ExamPaperType ptype) throws FrameworkRuntimeException {
		return examWebService.addExamPaperType(threadLocal.get(),ptype);
	}
	
	/**
	 * 试卷分类
	 * 修改试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType updateExamPaperType(ExamPaperType ptype) throws FrameworkRuntimeException{
		return examWebService.updateExamPaperType(threadLocal.get(),ptype);
	}
	
	/**
	 * 试卷分类
	 * 删除试卷分类
	 * @param ptype
	 */
	public boolean deleteExamPaperType(ExamPaperType ptype) throws FrameworkRuntimeException {
		return examWebService.deleteExamPaperType(threadLocal.get(), ptype);
	}
	
	/**
	 * 试卷分类
	 * 通过分类ID取试卷分类
	 * @param id
	 * @return ExamPaperType
	 */
	public ExamPaperType getExamPaperTypeById(Long id) throws FrameworkRuntimeException {
		return examWebService.getExamPaperTypeById(threadLocal.get(), id);
	}

	/**
	 * 试卷分类
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(Integer[] idArr) throws FrameworkRuntimeException {
		return examWebService.getExamPaperTypeRootListBySysId(threadLocal.get(), idArr);
	}
	
	/**
	 * 试卷分类
	 * 通过父ID 取子结点
	 * @param ExamPaperTypeQuery
	 * @return ExamReturnPaperType
	 */
	public ExamReturnPaperType getExamPaperTypeListByParentId(ExamPaperTypeQuery query) throws FrameworkRuntimeException {
		return examWebService.getExamPaperTypeListByParentId(threadLocal.get(),query);
	}
	
	
	
	/**
	 * 试卷分类
	 * 移动节点
	 * @param parent_id,code 
	 * @return
	 */
	public boolean updateMovePaperType(ExamPaperType ptype)  throws FrameworkRuntimeException {
		return examWebService.updateMovePaperType(threadLocal.get(), ptype);
	}
	
	
	/**
	 * 通过ID 查询 根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListById(Integer[] idArr) throws FrameworkRuntimeException {
		return examWebService.getExamPaperTypeRootListById(threadLocal.get(), idArr);
	}
	//=============================================试卷分类接口END================================================

	//---------------------------------------------试卷接口------------------------------------------------------
	
	/**
	 * 试卷
	 * 新增试卷
	 * @param paper
	 * @return  paper ID
	 */
	public Long addExamPaper(ExamPaper paper) throws FrameworkRuntimeException {
		return examWebService.addExamPaper(threadLocal.get(), paper);
	}
	
	/**
	 * 试卷
	 * 修改试卷
	 * @param paper
	 */
	public void updateExamPaper(ExamPaper paper) throws FrameworkRuntimeException {
		examWebService.updateExamPaper(threadLocal.get(), paper);
	}
	
	/**
	 * 试卷
	 * 通过ID 取试卷
	 * @param id
	 * @return ExamPaper
	 */
	public ExamPaper getExamPaperById(Long id) throws FrameworkRuntimeException {
		return examWebService.getExamPaperById(threadLocal.get(), id);
	}

	
	/**
	 * 试卷
	 * 查询试卷列表
	 * @param ExamPaperQuery query
	 * @return ExamReturnPaper
	 */
	public ExamReturnPaper getExamPaperList(ExamPaperQuery query) throws FrameworkRuntimeException {
		return examWebService.getExamPaperList(threadLocal.get(), query);
	}
	

	
	/**
	 * 试卷
	 * 删除试卷
	 * @param id
	 */
	public void deleteExamPaper(Long[] id) throws FrameworkRuntimeException {
		examWebService.deleteExamPaper(threadLocal.get(), id);
	}
	
	/**
	 * 试卷
	 * 通过考场ID 取 试卷列表
	 * @param examId
	 * @param paperId 上一次考过的试卷ID 没有则为 null 或 0
	 * @return
	 */
	public ExamReturnPaper getExamPaperListByExamId(Long examId,Long paperId) throws FrameworkRuntimeException {
		return examWebService.getExamPaperListByExamId(threadLocal.get(), examId, paperId);
	}
	
	
	/**
	 * 试卷
	 * 删除快捷策略
	 * @param id
	 */
	public void deleteExamPaperFasterTactic(Long id) throws FrameworkRuntimeException {
		examWebService.deleteExamPaperFasterTactic(threadLocal.get(), id);
	}
	
	/**
	 * 试卷
	 * 通过ID取策略模板
	 * @param id
	 * @return
	 */
	public ExamPaperFasterTactic getExamPaperFasterTacticById(Long id) throws FrameworkRuntimeException {
		return examWebService.getExamPaperFasterTacticById(threadLocal.get(), id);
	}
	
	/**
	 * 试卷
	 * 通过试卷分类查询快捷策略列表
	 * @param tactic　
	 * @return
	 */
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(ExamPaperFasterTactic tactic) throws FrameworkRuntimeException {
		return examWebService.getExamPaperFasterTacticByPaperTypeId(threadLocal.get(), tactic);
	}

	/**
	 * 试卷
	 * 通过试卷ID修改试卷分类
	 * @param paper_type_id 试卷分类ID
	 * @param paperId	试卷ID
	 */
	public void updateExamePaperTypeByPaperId(Long paper_type_id,Long paperId) throws FrameworkRuntimeException {
		examWebService.updateExamePaperTypeByPaperId(threadLocal.get(), paper_type_id, paperId);
	}
	
	/**
	 * 查询符合策略条件试题数
	 * @param paper
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public ExamPaper getQuestSizeByFasterTactic(ExamPaper paper) throws FrameworkRuntimeException {
		return examWebService.getQuestSizeByFasterTactic(threadLocal.get(), paper);
	}
	
	/**
	 * 更换试卷试题
	 * @param paperId
	 * @param oldQuestionID
	 * @param newQuestionId
	 * @param score
	 */
	public void updateExamPaperQuestion(Long paperId,Long oldQuestionID,Long newQuestionId,Double score) throws FrameworkRuntimeException {
		examWebService.updateExamPaperQuestion(threadLocal.get(), paperId, oldQuestionID, newQuestionId, score);
	}
	//=============================================试卷接口END===================================================
	
	//---------------------------------------------试题分类接口---------------------------------------------------
	
	/**
	 * 试题分类
	 * 增加试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType addExamQuestType(ExamQuestionType qtype) throws FrameworkRuntimeException {
		return examWebService.addExamQuestType(threadLocal.get(), qtype);
	}
	
	/**
	 * 试题分类
	 * 修改试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType updateExamQuestTypeById(ExamQuestionType qtype) throws FrameworkRuntimeException {
		return examWebService.updateExamQuestTypeById(threadLocal.get(), qtype);
	}
	
	/**
	 * 试题分类
	 * 通过分类ID删除试题分类
	 * @param ExamQuestionType qtype
	 */
	public boolean deleteExamQuestTypeById(ExamQuestionType qtype) throws FrameworkRuntimeException {
		return examWebService.deleteExamQuestTypeById(threadLocal.get(), qtype);
	}
	
	/**
	 * 试题分类
	 * 通过分类ID取试题分类
	 * @param Long id
	 * @return ExamQuestionType
	 */
	public ExamQuestionType getExamQuestionTypeById(Long id) throws FrameworkRuntimeException {
		return examWebService.getExamQuestionTypeById(threadLocal.get(), id);
	}
	

	/**
	 * 试题分类
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param Integer[] id
	 * @return List<ExamQuestionType>
	 */
	public List<ExamQuestionType> getExamQuestionTypeRootBySysId(Integer[] id) throws FrameworkRuntimeException {
		return examWebService.getExamQuestionTypeRootBySysId(threadLocal.get(), id);
	}
	
	/**
	 * 试题分类
	 * 通过父ID查子节点
	 * @param ExamQuestionTypeQuery
	 * @return ExamReturnQuestionType
	 */
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(ExamQuestionTypeQuery query) throws FrameworkRuntimeException {
		return examWebService.getExamQuestionTypeListByParentId(threadLocal.get(), query);
	}
	
	
	/**
	 * 试题分类
	 * 移动节点
	 * @param parent_id,code 
	 * @return
	 */
	public boolean updateMoveQuestionType(ExamQuestionType qtype) throws FrameworkRuntimeException {
		return examWebService.updateMoveQuestionType(threadLocal.get(), qtype);
	}
	
	
	/**
	 * 试题分类
	 * 取所有root结点
	 * @return
	 */
	public List<ExamQuestionType> getQuestionTypeRootById(Integer[] idArr) throws FrameworkRuntimeException {
		return examWebService.getQuestionTypeRootById(threadLocal.get(), idArr);
	}
	//=============================================试题分类END===================================================	
	
	//---------------------------------------------试题接口-------------------------------------------------------
	/**
	 * 试题
	 * 新增试题
	 * 如果返回 null, 题型和内容已经在题库中存在.
	 * @param quest
	 * @return 
	 */
	public ExamQuestion addExamQuestion(ExamQuestion quest) throws FrameworkRuntimeException {
		return examWebService.addExamQuestion(threadLocal.get(), quest);
	}
	
	/**
	 * 试题
	 * 修改试题
	 * @param quest
	 * @return
	 */
	public void updateExamQuestionById(ExamQuestion quest) throws FrameworkRuntimeException {
		examWebService.updateExamQuestionById(threadLocal.get(), quest);
	}
	
	/**
	 * 试题
	 * 通过ID取试题
	 * @param id
	 * @return
	 */
	public ExamQuestion getExamQuestionById(Long id) throws FrameworkRuntimeException {
		return examWebService.getExamQuestionById(threadLocal.get(), id);
	}
	
	
	/**
	 * 试题
	 * 修改试题状态
	 * @param 试题 List idArr
	 * @param 状态类型 int state
	 */
	public void updateExamQuestionStateByIds(List<Long> idArr,int state) throws FrameworkRuntimeException {
		examWebService.updateExamQuestionStateByIds(threadLocal.get(), idArr, state);
	}
	
	
	/**
	 * 试题
	 * 通过查询条件返回试题列表(String key,分页)
	 * @param query
	 * @return ExamReturnQuestion
	 */
	public ExamReturnQuestion getExamQuestionList(ExamQuestionQuery query) throws FrameworkRuntimeException {
		return examWebService.getExamQuestionList(threadLocal.get(), query);
	}

	
	/**
	 * 试题
	 * 通过ID数组取试题,只有试题答案和子试题
	 * @param idArr
	 * @return
	 */
	public List<ExamQuestion> getQuestionIdByIdArr(Long[] idArr) throws FrameworkRuntimeException {
		return examWebService.getQuestionIdByIdArr(threadLocal.get(), idArr);
	}
	
	/**
	 * 试题
	 * 根据 试卷ID 取试题列表
	 * @param paperId 试卷ID
	 * @param order 0:不打乱顺序 1:打乱顺序
	 * @return 试题列表 ExamReturnQuestion
	 */
	public ExamReturnQuestion getQuestionListByPaperId(Long paperId,int order) throws FrameworkRuntimeException{
		return examWebService.getQuestionListByPaperId(threadLocal.get(), paperId, order);
	}
	
	
	/**
	 * 试题
	 * 通过策略取试题数
	 * @param paperTactic
	 * @return
	 */
	public int getQuestionListSize(ExamPaperTactic paperTactic) throws FrameworkRuntimeException {
		return examWebService.getQuestionListSize(threadLocal.get(), paperTactic);
	}

	
	/**
	 * 试题
	 * 通过快捷策略取试题数
	 * @param paperTactic
	 * @return
	 */
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException {
		return examWebService.getQuestionSizeByFasterTactic(threadLocal.get(), paperTactic);
	}
	
	//=============================================试题接口END===================================================	


}
