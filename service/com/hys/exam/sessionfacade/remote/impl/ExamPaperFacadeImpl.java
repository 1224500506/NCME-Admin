package com.hys.exam.sessionfacade.remote.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamPaperFasterTacticX;
import com.hys.exam.model.ExamPaperQuestion;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.service.remote.ExamPaperManage;
import com.hys.exam.service.remote.ExamQuestionManage;
import com.hys.exam.sessionfacade.remote.ExamPaperFacade;
import com.hys.exam.util.OrderedMap;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 13, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperFacadeImpl extends BaseSessionFacadeImpl implements
		ExamPaperFacade {
	
	private static Map<String, LinkedList<ExamQuestion>> QUESTION_CACHE = new HashMap<String, LinkedList<ExamQuestion>>();

	private static Map<String, Integer> PAGE_CACHE = new HashMap<String, Integer>();

	final private static int PAGE_SIZE = 100;

	private static Random random = new Random();

	private ExamPaperManage remoteExamPaperManage;
	
	private ExamQuestionManage remoteExamQuestionManage;
	
	public ExamPaperManage getRemoteExamPaperManage() {
		return remoteExamPaperManage;
	}

	public void setRemoteExamPaperManage(ExamPaperManage remoteExamPaperManage) {
		this.remoteExamPaperManage = remoteExamPaperManage;
	}
	
	public ExamQuestionManage getRemoteExamQuestionManage() {
		return remoteExamQuestionManage;
	}

	public void setRemoteExamQuestionManage(
			ExamQuestionManage remoteExamQuestionManage) {
		this.remoteExamQuestionManage = remoteExamQuestionManage;
	}

	@Override
	public Long addExamPaper(String key, ExamPaper paper)
			throws FrameworkRuntimeException {
		Long paperId;
		try {
			paperId = remoteExamPaperManage.getExamPaperId(key);
			// 试卷ID
			paper.setId(paperId);
		} catch (Exception e) {
			logger.error("获取试卷ID出现异常!",e);
			throw new FrameworkRuntimeException("获取试卷ID出现异常!",e);
		}
		
		//快捷策略2
		if(paper.getPaper_mode() == Constants.PAPER_MODE_FT2){
			
			ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
			
			List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
			
			setProp(paperFasterTactic,tXList);
			
			if(tXList==null || tXList.isEmpty()){
				throw new FrameworkRuntimeException("快捷策略列表错误!!");
			}
			
		}
		
		//快捷策略
		if(paper.getPaper_mode() == Constants.PAPER_MODE_FT){
			
			ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
						
			List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
			
			setProp(paperFasterTactic,tXList);
			
			if(tXList==null || tXList.isEmpty()){
				throw new FrameworkRuntimeException("快捷策略列表错误!!");
			}
			
			//查询符合策略条件试题数
			try{
				remoteExamQuestionManage.getQuestSizeByFasterTactic(key, tXList);
			} catch (Exception e) {
				logger.error("快捷策略-查询符合策略条件试题数",e);
				throw new FrameworkRuntimeException("快捷策略-查询符合策略条件试题数",e);
			}
			
			try{
				//分配试题数
				for(ExamPaperFasterTacticX tx : tXList){
					setQuestionNum(tx.getExamPaperFasterTactic2(),tx.getNum()/tx.getExamPaperFasterTactic2().size(),tx.getNum());
				}
			}catch(Exception e){
				logger.error("快捷策略-分配错误"+e);
				throw new FrameworkRuntimeException("快捷策略-分配错误"+e);
			}
			
			paper.setPaperFasterTacticx(tXList);
			
			boolean flag = getPaperQuestion(key, paper);
			
			if(!flag){
				throw new FrameworkRuntimeException("快捷策略-抽取试题错误!!");
			}
			
			
			if(paper.getChild_paper_num()>0){
				
				// 存放子试卷的List
				List<ExamPaper> childList = new ArrayList<ExamPaper>();
				// 设置子试卷
				for (int i = 0; i < paper.getChild_paper_num(); i++) {
					ExamPaper childExamPaper = new ExamPaper();
					// 复制子试卷对象
					BeanUtils.copyProperties(paper,childExamPaper);
					// 子试卷ID
					try{
						childExamPaper.setId(remoteExamPaperManage.getExamPaperId(key));
					}catch(Exception e){
						logger.error("快捷策略-获取试卷ID出现异常!",e);
						throw new FrameworkRuntimeException("快捷策略-获取试卷ID出现异常!",e);
					}
					// 父试卷ID
					childExamPaper.setParent_id(paperId);
					// 子试卷名称
					childExamPaper.setName(childExamPaper.getName() + " " + (i+1) + "#");
					// 子试卷个数
					childExamPaper.setChild_paper_num(0);
					// 试卷试题列表
					childExamPaper.setExamPaperQuestionList(null);
					
					
					// 根据随机策略生成随机试题
					flag = getPaperQuestion(key,childExamPaper);
					
					if(!flag){
						throw new FrameworkRuntimeException("快捷策略-抽取试题错误!!");
					}
					
					//子试卷试题数量
					childExamPaper.setQuestion_num(paper.getQuestion_num());
					
					// 添加到子试卷列表
					childList.add(childExamPaper);
				}
				// 子试卷列表
				paper.setChildExamPaperList(childList);
				
			}
			
		}
		
		
		// 精细策略
		if (paper.getPaper_mode() == Constants.PAPER_MODE_RM) {

			List<ExamPaperTactic> tacticList = paper.getPaperTacticList();

			if (tacticList != null && (!tacticList.isEmpty())) {
				int queAmount = 0;
				for (int i = 0; i < tacticList.size(); i++) {
					tacticList.get(i).setPaper_id(paperId);
					queAmount += tacticList.get(i).getAmount();
				}
				//试卷试题数量
				paper.setQuestion_num(queAmount);
				
				getPaperQuestion(key,paper);
				
				// 如果存在子试卷 设置子试卷信息
				if(paper.getChild_paper_num()>0){
					// 存放子试卷的List
					List<ExamPaper> childList = new ArrayList<ExamPaper>();
					// 设置子试卷
					for (int i = 0; i < paper.getChild_paper_num(); i++) {
						ExamPaper childExamPaper = new ExamPaper();
						// 复制子试卷对象
						BeanUtils.copyProperties(paper,childExamPaper);
						// 子试卷ID
						try{
							// 子试卷ID
							childExamPaper.setId(remoteExamPaperManage.getExamPaperId(key));
						}catch(Exception e){
							logger.error("精细策略取ID错误!",e);
							throw new FrameworkRuntimeException("精细策略取ID错误!",e);
						}						
						// 父试卷ID
						childExamPaper.setParent_id(paperId);
						// 子试卷名称
						childExamPaper.setName(childExamPaper.getName() + " " + (i+1) + "#");
						// 子试卷个数
						childExamPaper.setChild_paper_num(0);
						// 试卷试题列表
						childExamPaper.setExamPaperQuestionList(null);
						// 根据随机策略生成随机试题
						getPaperQuestion(key,childExamPaper);
						
						//子试卷试题数量
						childExamPaper.setQuestion_num(queAmount);
						
						// 添加到子试卷列表
						childList.add(childExamPaper);
					}
					// 子试卷列表
					paper.setChildExamPaperList(childList);
				}				
			}

		}

		// 固定试题
		if(paper.getPaper_mode() == Constants.PAPER_MODE_FIX){
			
			List<ExamPaperQuestion> examPaperQuestionList = paper
					.getExamPaperQuestionList();
			if (examPaperQuestionList != null
					&& examPaperQuestionList.size() > 0) {
				double paperScore = 0;
				for (ExamPaperQuestion pq : examPaperQuestionList) {
					pq.setPaper_id(paperId);
					paperScore += pq.getQuestion_score();
				}
				// 试卷试题数量
				paper.setQuestion_num(examPaperQuestionList.size());
				// 试卷分数
				paper.setPaper_score(paperScore);
			} else {
				throw new FrameworkRuntimeException("试题数量不足");
			}			
		}

		try {
			// 新增试卷信息
			return remoteExamPaperManage.addExamPaper(key,paper);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("新增试卷出现异常!");
		}
	}

	@Override
	public void deleteExamPaper(String key, Long[] id)
			throws FrameworkRuntimeException {
		try {
			remoteExamPaperManage.deleteExamPaper(key,id);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void deleteExamPaperFasterTactic(String key, Long id)
			throws FrameworkRuntimeException {
		try {
			remoteExamPaperManage.deleteExamPaperFasterTactic(key,id);
		} catch (Exception e) {
			logger.error("删除快捷策略出现异常:",e);
			throw new FrameworkRuntimeException("删除快捷策略出现异常!",e);
		}
	}

	@Override
	public ExamPaper getExamPaperById(String key, Long id)
			throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return remoteExamPaperManage.getExamPaperById(key,id);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!",e);
		}
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(String key,
			Long id) throws FrameworkRuntimeException {
		try {
			return remoteExamPaperManage.getExamPaperFasterTacticById(key,id);
		} catch (Exception e) {
			logger.error("获取快捷策略出现异常",e);
			throw new FrameworkRuntimeException("获取快捷策略出现异常!",e);
		}
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			String key, ExamPaperFasterTactic tactic)
			throws FrameworkRuntimeException {
		try {
			return remoteExamPaperManage.getExamPaperFasterTacticByPaperTypeId(key,tactic);
		} catch (Exception e) {
			logger.error("获取快捷策略列表出现异常",e);
			throw new FrameworkRuntimeException("获取快捷策略列表出现异常!",e);
		}
	}

	@Override
	public List<ExamPaper> getExamPaperList(String key,
			ExamPaperQuery examPaperQuery) throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return remoteExamPaperManage.getExamPaperList(key,examPaperQuery);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!",e);
		}
	}

	@Override
	public List<ExamPaper> getExamPaperListByExamId(String key, Long examId)
			throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return remoteExamPaperManage.getExamPaperListByExamId(key,examId);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!");
		}
	}

	@Override
	public int getExamPaperListSize(String key,ExamPaperQuery examPaperQuery)
			throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return remoteExamPaperManage.getExamPaperListSize(key,examPaperQuery);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!",e);
		}
	}

	@Override
	public ExamPaper getQuestSizeByFasterTactic(String key, ExamPaper paper)
			throws FrameworkRuntimeException {
		try{
			ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
			
			List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
			
			setProp(paperFasterTactic,tXList);
			
			if(tXList==null || tXList.isEmpty()){
				throw new FrameworkRuntimeException("快捷策略列表错误!!");
			}
			
			//查询符合策略条件试题数
			remoteExamQuestionManage.getQuestSizeByFasterTactic(key,tXList);
			
			paper.setPaperFasterTacticx(tXList);
		} catch (Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
		
		return paper;
	}

	@Override
	public void updateExamPaper(String key, ExamPaper paper)
			throws FrameworkRuntimeException {
		try {
			// 修改试卷信息
			if (paper.getPaper_mode() == Constants.PAPER_MODE_RM) {
				
				List<ExamPaperTactic> tacticList = paper.getPaperTacticList();
	
				if (tacticList != null && (!tacticList.isEmpty())) {
					int queAmount = 0;
					for (int i = 0; i < tacticList.size(); i++) {
						tacticList.get(i).setPaper_id(paper.getId());
						queAmount += tacticList.get(i).getAmount();
					}
					paper.setQuestion_num(queAmount);
					
					getPaperQuestion(key, paper);
				}		
				// 取试卷和子试卷列表
				List<ExamPaper> paperList = remoteExamPaperManage.getExamPaperAndChildPaper(key, new Long[]{paper.getId()});
				
				if(paperList != null && paperList.size()>0){
					// 存放子试卷的List
					List<ExamPaper> childList = new ArrayList<ExamPaper>();
					for (int i = 0; i < paperList.size(); i++) {
						// 如果是子试卷
						if(paperList.get(i).getParent_id() != 0){
							ExamPaper childExamPaper = new ExamPaper();
							// 复制子试卷对象
							BeanUtils.copyProperties(paper,childExamPaper);
							// 子试卷ID
							childExamPaper.setId(paperList.get(i).getId());
							// 父试卷ID
							childExamPaper.setParent_id(paper.getId());
							// 子试卷名称
							childExamPaper.setName(childExamPaper.getName() + " " + (i+1) + "#");
							// 子试卷个数
							childExamPaper.setChild_paper_num(0);
							// 试卷试题列表
							childExamPaper.setExamPaperQuestionList(null);
							// 随机策略列表不为空
							if(tacticList!= null){
								// 根据随机策略生成随机试题
								getPaperQuestion(key, childExamPaper);
							}
							// 添加到子试卷列表
							childList.add(childExamPaper);
						}
					}
					// 子试卷列表
					paper.setChildExamPaperList(childList);
				}
				
				
			} else {
				List<ExamPaperQuestion> examPaperQuestionList = paper
						.getExamPaperQuestionList();
				if (examPaperQuestionList != null
						&& examPaperQuestionList.size() > 0) {
					double paperScore = 0;
					for (ExamPaperQuestion pq : examPaperQuestionList) {
						paperScore += pq.getQuestion_score();
					}
					paper.setPaper_score(paperScore);
					paper.setQuestion_num(examPaperQuestionList.size());
				}
			}
			remoteExamPaperManage.updateExamPaper(key,paper);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("修改试卷出现异常!");
		}
	}

	@Override
	public void updateExamPaperQuestion(String key, Long paperId,
			Long oldQuestionID, Long newQuestionId, Double score)
			throws FrameworkRuntimeException {
		try {
			remoteExamPaperManage.updateExamPaperQuestion(key,paperId, oldQuestionID, newQuestionId, score);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void updateExamePaperTypeByPaperId(String key, Long paperTypeId,
			Long paperId) throws FrameworkRuntimeException {
		try {
			remoteExamPaperManage.updateExamePaperTypeByPaperId(key,paperTypeId, paperId);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	

	//精细策略
	private boolean getPaperQuestion(String key, ExamPaper examPaper)
			throws FrameworkRuntimeException {
		OrderedMap<Long, ExamPaperQuestion> questionList = new OrderedMap<Long, ExamPaperQuestion>();
		
		boolean b = getExamQuestionMap(key, examPaper, questionList);
		// 试卷试题列表
		examPaper.setExamPaperQuestionList(questionList.toList());
		
		return b;
	}


	/**
	 * 
	 * Description: 根据策略生成试题并存放在questionList中
	 * @param examPaper 试卷对象
	 * @param examPaperTactic 随机策略列表
	 * @param questionList 存放试卷试题的OrderedMap
	 * @throws FrameworkRuntimeException
	 * @return: boolean
	 */
	private boolean getExamQuestionMap(String key,ExamPaper examPaper,
			OrderedMap<Long, ExamPaperQuestion> questionList)
			throws FrameworkRuntimeException {

		if(examPaper.getPaper_mode() == Constants.PAPER_MODE_FT){
			
			List<ExamPaperFasterTacticX> paperFasterTactic = examPaper.getPaperFasterTacticx();
			
			for (int i = 0; i < paperFasterTactic.size(); i++) {
				
				ExamPaperFasterTacticX paperTactic = paperFasterTactic.get(i);
				
				List<ExamPaperFasterTactic2> t2List = paperTactic.getExamPaperFasterTactic2();
				
				for(int j=0;j<t2List.size();j++){
					
					ExamPaperFasterTactic2 t2 = t2List.get(j);
					// 获取策略KEY
					String tacticKey  = getTacticKeys(t2,paperTactic.getQuestion_label_id());
					// 取符合策略的试题列表
					LinkedList<ExamQuestion> qlist = getQuestionListByTactic(tacticKey);
					
					for(int k=0;k<t2.getSelQuestionNum();k++){
						// 试卷试题
						ExamPaperQuestion paperQuestion = new ExamPaperQuestion();
						// 查找试题ID
				
						ExamQuestion examQuestion = findQuestion(key, qlist,
								questionList, tacticKey, paperTactic
										.getQuestion_label_id(), paperTactic
										.getQuestion_type_id(), t2, paperTactic
										.getQuestionPropMap());
						
						if(examQuestion == null){
							return false;
						}
						
						// 试卷ID
						paperQuestion.setPaper_id(examPaper.getId());
						// 试题ID
						paperQuestion.setQuestion_id(examQuestion.getId());
						// 顺序
						//paperQuestion.setSeq(k+1);
						// 试卷试题分数
						paperQuestion.setQuestion_score(paperTactic.getScore());
						
						questionList.put(examQuestion.getId(), paperQuestion);
					}
				}
			}
		} else {
			List<ExamPaperTactic> examPaperTactic = examPaper.getPaperTacticList();
			for (int i = 0; i < examPaperTactic.size(); i++) {
				ExamPaperTactic paperTactic = examPaperTactic.get(i);
				// 获取策略KEY
				String tacticKey = getTacticKey(paperTactic);
				// 取符合策略的试题列表
				LinkedList<ExamQuestion> qlist = getQuestionListByTactic(tacticKey);
				// 获取试题
				for (int j = 0; j < paperTactic.getAmount(); j++) {
					// 试卷试题
					ExamPaperQuestion paperQuestion = new ExamPaperQuestion();
					// 查找试题ID
					ExamQuestion examQuestion = findQuestion(key,qlist,paperTactic,tacticKey,questionList);
					if(examQuestion == null){
						return false;
					}
					// 试卷ID
					paperQuestion.setPaper_id(examPaper.getId());
					// 试题ID
					paperQuestion.setQuestion_id(examQuestion.getId());
					// 顺序
					//paperQuestion.setSeq(j+1);
					// 试卷试题分数
					paperQuestion.setQuestion_score(paperTactic.getQuestion_score());
					
					questionList.put(examQuestion.getId(), paperQuestion);
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * 快捷策略生成key
	 * @param t2
	 * @param question_label_id
	 * @return
	 */
	private String getTacticKeys(ExamPaperFasterTactic2 t2,int question_label_id){
		StringBuffer sb = new StringBuffer();
		sb.append(question_label_id + "-");
		if (t2.getStudy1_id() != null) {
			sb.append(t2.getStudy1_id() + "-");
		}else{
			sb.append("#-");
		}
		if (t2.getStudy2_id() != null) {
			sb.append(t2.getStudy2_id() + "-");
		}else{
			sb.append("#-");
		}
		if (t2.getStudy3_id() != null) {
			sb.append(t2.getStudy3_id() + "-");
		}else{
			sb.append("#-");
		}
		if (t2.getUnit_id() != null) {
			sb.append(t2.getUnit_id() + "-");
		}else{
			sb.append("#-");
		}
		if (t2.getPoint_id() != null) {
			sb.append(t2.getPoint_id() + "-");
		}else{
			sb.append("#-");
		}	
		return sb.toString();
	}
	
	
	/**
	 * 
	 * Description: 根据随机策略生成key
	 * @param paperTactic 精细随机策略对象
	 */
	@SuppressWarnings("unchecked")
	private String getTacticKey(ExamPaperTactic paperTactic){
		StringBuffer sb = new StringBuffer();
		sb.append(paperTactic.getQuestion_label_id() + "-");
		sb.append(paperTactic.getGrade() + "-");
		Map<String,List<ExamQuestionProp>> questionPropMap = paperTactic.getQuestionPropMap();
		
		if(null != questionPropMap){
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						ExamQuestionProp questProp = propValList.get(i);
						tmp += questProp.getProp_val_id()+",";
					}
					sb.append("-"+entry.getKey()+"#prop="+tmp);
				}
			}
		}
		return sb.toString();
	}	
	
	/**
	 * 
	 * Description: 根据策略KEY获取试题列表
	 * @param tacticKey 策略tacticKey
	 */
	private synchronized LinkedList<ExamQuestion> getQuestionListByTactic(String tacticKey) 
			 throws FrameworkRuntimeException {
		LinkedList<ExamQuestion> list = QUESTION_CACHE.get(tacticKey);
		if (list == null) {
			list = new LinkedList<ExamQuestion>();
			QUESTION_CACHE.put(tacticKey, list);
		}
		return list;
	}	

	
	/**
	 * 
	 * 精细策略对象
	 * @param list 试题列表
	 * @param paperTactic 精细策略对象
	 * @param tacticKey 策略tacticKey
	 * @param OrderedMap<Long, ExamPaperQuestion> questionList 试卷试题Map
	 */
	private ExamQuestion findQuestion(String key, LinkedList<ExamQuestion> list,
			ExamPaperTactic paperTactic, String tactickey,
			OrderedMap<Long, ExamPaperQuestion> questionList)
			throws FrameworkRuntimeException {
		
			// 检查是否有数据
			if (!checkList(key, list, paperTactic, tactickey)) {
				return null;
			}
	
			ExamQuestion examQuestion = null;
			if (list.size() == 1) {
				// 如果数据只有一条 直接取得第一条数据
				examQuestion = list.remove();
			} else {
				examQuestion = list.remove(random(list.size()));
			}
	
			if (questionList.get(examQuestion.getId()) != null) {
				// 如果已经这道题目 递归取得下一题 保证试卷中不会有重复试题
				return findQuestion(key, list, paperTactic, tactickey, questionList);
			}
			return examQuestion;
		
	}
	
	/**
	 * 
	 * @param list	试题列表
	 * @param OrderedMap<Long, ExamPaperQuestion> questionList 试卷试题Map
	 * @param 策略key
	 * @param question_label_id		题型ID
	 * @param question_type_id		试题分类ID
	 * @param t2		级联属性
	 * @param questionPropMap	非级联属性
	 * @return
	 */
	private ExamQuestion findQuestion(String key,LinkedList<ExamQuestion> list,
			OrderedMap<Long, ExamPaperQuestion> questionList, String tacticKey,
			int question_label_id, String question_type_id,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap) {
		
			
		// 检查是否有数据
		if (!checkList(key,list,tacticKey,question_label_id,question_type_id,t2,questionPropMap)) {
			return null;
		}

		ExamQuestion examQuestion = null;
		if (list.size() == 1) {
			// 如果数据只有一条 直接取得第一条数据
			examQuestion = list.remove();
		} else {
			examQuestion = list.remove(random(list.size()));
		}

		if (questionList.get(examQuestion.getId()) != null) {
			// 如果已经这道题目 递归取得下一题 保证试卷中不会有重复试题
			return findQuestion(key, list, questionList, tacticKey, question_label_id, question_type_id, t2, questionPropMap);
		}
		
		return examQuestion;
	}
	
	/**
	 * 
	 * 精细策略检查试题是否存在
	 * @param list 试题列表
	 * @param paperTactic 策略对象
	 * @param tacticKey 策略tacticKey
	 * @throws FrameworkRuntimeException
	 * @return: boolean
	 */
	private synchronized boolean checkList(String key,LinkedList<ExamQuestion> list,
			ExamPaperTactic paperTactic,String tacticKey) throws FrameworkRuntimeException {
		if (list.size() == 0) {
			List<ExamQuestion> newList = null;
			int currentPage =0;
			try {
					currentPage = getCurrentPage(key,paperTactic, tacticKey);
					// 从数据库中取出下一页的数据
					newList = remoteExamQuestionManage.getQuestionList(key,paperTactic,PAGE_SIZE, currentPage);
					if(newList == null || (newList != null && newList.size()< paperTactic.getAmount())){
						return false;
					}
			} catch (Exception e) {
				logger.error("精细策略检查试题是否存在",e);
				throw new FrameworkRuntimeException("精细策略检查试题是否存在出现异常！",e);
			}
			// 缓存中加入试题信息
			list.addAll(newList);
			
		}
		return true;
	}
	
	/**
	 * 快捷策略检查试题是否存在
	 * @param list
	 * @param key
	 * @param question_label_id		题型ID
	 * @param question_type_id		试题分类ID
	 * @param t2		级联属性
	 * @param questionPropMap	非级联属性
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	private synchronized boolean checkList(String key,LinkedList<ExamQuestion> list,
			String tacticKey, int question_label_id, String question_type_id,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap)
			throws FrameworkRuntimeException {
		if (list.size() == 0) {
			List<ExamQuestion> newList = null;
			try {
				// 从数据库中取出下一页的数据
				newList = remoteExamQuestionManage.getQuestionListByByFasterTactic(key, question_label_id, question_type_id, t2, questionPropMap);
				if(newList == null || (newList != null && newList.size()< t2.getSelQuestionNum())){
					return false;
				}
			} catch (Exception e) {
				logger.error("快捷检查试题是否存在出现异常！",e);
				throw new FrameworkRuntimeException("快捷检查试题是否存在出现异常！",e);
			}
			// 缓存中加入试题信息
			list.addAll(newList);
			
		}
		return true;		
		
	}

	/**
	 * 随机取整数
	 * 
	 * @param number
	 * @return
	 */
	private static int random(int number) {
		return random.nextInt(number - 1);
	}
	
	/**
	 * 查询当前页
	 * 
	 * @param paper
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	private synchronized int getCurrentPage(String key, ExamPaperTactic paperTactic,String tacticKey) throws Exception {
		Integer currentPage = PAGE_CACHE.get(tacticKey);

		if (currentPage == null) {
			currentPage = 0;
		} else {
			currentPage++;
		}

		int count = remoteExamQuestionManage.getQuestionListSize(key,paperTactic);
		

		if (currentPage >= getPageCount(count, PAGE_SIZE)) {
			currentPage = 0;
		}

		PAGE_CACHE.put(tacticKey, currentPage);

		return currentPage;
	}
	
	/**
	 * 根据总数和每页数 计算总页数
	 * 
	 * @param count
	 * @param pageSize
	 * @return
	 */
	private int getPageCount(int count, int pageSize) {
		int page = count / pageSize;

		if ((count % pageSize) > 0) {
			page++;
		}

		return page;
	}
	

	/**
	 * 
	 * @param tactic
	 * @param txList
	 */
	private void setProp(ExamPaperFasterTactic tactic,List<ExamPaperFasterTacticX> txList){

		//题型,数量,分数
		List<ExamPaperFasterTactic1> examPaperFasterTactic1 = tactic.getExamPaperFasterTactic1();
		//级联属性
		List<ExamPaperFasterTactic2> examPaperFasterTactic2 = tactic.getExamPaperFasterTactic2();
		//非级联属性
		List<ExamPaperFasterTactic3> examPaperFasterTactic3 = tactic.getExamPaperFasterTactic3();
		
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
		
		
		setProp(examPaperFasterTactic3,questionPropMap);
		
				
		for(ExamPaperFasterTactic1 t1 : examPaperFasterTactic1){
			if(t1.getNum()>0){
				ExamPaperFasterTacticX tx = new ExamPaperFasterTacticX();
				tx.setQuestion_label_id(t1.getQuestion_label_id());
				tx.setNum(t1.getNum());
				tx.setQuestion_type_id(tactic.getQuestion_type_id());
				tx.setScore(t1.getScore());
				
				if(examPaperFasterTactic2!=null){
					List<ExamPaperFasterTactic2> tmpList = new ArrayList<ExamPaperFasterTactic2>();
					tmpList.addAll(examPaperFasterTactic2);
					tx.setExamPaperFasterTactic2(tmpList);
				}
				if(examPaperFasterTactic3!=null){
					tx.setQuestionPropMap(questionPropMap);
				}
				txList.add(tx);
			}
		}

	}
	
	private void setProp(List<ExamPaperFasterTactic3> tactic3,Map<String,List<ExamQuestionProp>> questionPropMap) {
		
		if (tactic3 != null  && !tactic3.isEmpty()) {
			// 副知识点
			List<ExamQuestionProp> prop1 = new ArrayList<ExamQuestionProp>();
			// 认知水平
			List<ExamQuestionProp> prop2 = new ArrayList<ExamQuestionProp>();
			// 职称
			List<ExamQuestionProp> prop3 = new ArrayList<ExamQuestionProp>();

			for (ExamPaperFasterTactic3 t : tactic3) {

				if (t.getPoint2_id() != null) {
					ExamQuestionProp p = new ExamQuestionProp();
					p.setProp_val_id(t.getPoint2_id());
					prop1.add(p);
				}

				if (t.getCognize_id() != null) {
					ExamQuestionProp p = new ExamQuestionProp();
					p.setProp_val_id(t.getCognize_id());
					prop2.add(p);
				}

				if (t.getTitle_id() != null) {
					ExamQuestionProp p = new ExamQuestionProp();
					p.setProp_val_id(t.getTitle_id());
					prop3.add(p);
				}
			}

			if (prop1 != null && !prop1.isEmpty()) {
				questionPropMap.put("7", prop1);
			}
			if (prop2 != null && !prop2.isEmpty()) {
				questionPropMap.put("8", prop2);
			}
			if (prop3 != null && !prop3.isEmpty()) {
				questionPropMap.put("9", prop3);
			}
		}
		
	}
	
	
	/**
	 * 
	 * @param t
	 * @param fnum 分配试题数
	 * @param qnum　试题总数
	 * 
	 */
	private void setQuestionNum(List<ExamPaperFasterTactic2> t, int fnum,int qnum){
		try{
			int ss = qnum;
			for(int i=0;i<t.size();i++){
				if(t.get(i).getAllQuestionNum()>fnum && (t.get(i).getAllQuestionNum()>t.get(i).getSelQuestionNum())){
					if((ss - fnum)>=0){
						t.get(i).setSelQuestionNum(t.get(i).getSelQuestionNum()+fnum);
						ss = ss -fnum;
					}
	//				System.out.println(t.get(i).getAllQuestionNum() + "\t" + t.get(i).getSelQuestionNum()+"\t"+ss+"\t"+a);
				}else{
					if(t.get(i).getAllQuestionNum()!=t.get(i).getSelQuestionNum()){
						ss = ss -t.get(i).getAllQuestionNum();
					}
					t.get(i).setSelQuestionNum(t.get(i).getAllQuestionNum());
	//				System.out.println(t.get(i).getS1() + "\t" + t.get(i).getS2()+"\t"+ss);
				}
				
			}
	//		System.out.println(ss+"-----"+qnum+"------"+t.size());
			if(ss>0){
				int tmp = ss/t.size();
				if(tmp==0){
					setQuestionNum(t,1,ss);
				}else{
					setQuestionNum(t,tmp,ss);
				}
			}
		
			
		}catch(Exception e){
			logger.error("分配错误"+e);
			throw new FrameworkRuntimeException("分配错误"+e);
		}
	}

}
