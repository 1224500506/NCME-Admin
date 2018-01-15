package com.hys.exam.sessionfacade.local.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamPaperFasterTacticX;
import com.hys.exam.model.ExamPaperQuestion;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.service.local.ExamPaperManage;
import com.hys.exam.service.local.ExamPropValManage;
import com.hys.exam.service.local.ExamQuestionManage;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.util.OrderedMap;
import com.hys.exam.utils.PageList;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;


/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-9
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperFacadeImpl extends BaseSessionFacadeImpl implements ExamPaperFacade {

	private static Map<String, LinkedList<ExamQuestion>> QUESTION_CACHE = new HashMap<String, LinkedList<ExamQuestion>>();

	private static Map<String, Integer> PAGE_CACHE = new HashMap<String, Integer>();

	final private static int PAGE_SIZE = 100;

	private static Random random = new Random();

	private ExamPaperManage localExamPaperManage;
	
	private ExamQuestionManage localExamQuestionManage;
	
	private ExamPropValManage localExamPropValManage;
	
	/**
	 * 试卷是否使用，YHQ，2017-05-17
	 * @param ExamPaper examPaper
	 * @return 试卷id
	 */
	@Override
	public boolean  usingExamPaper(ExamPaper examPaper) throws FrameworkRuntimeException {
		return localExamPaperManage.usingExamPaper(examPaper) ;
	}

	@Override
	public Long addExamPaper(ExamPaper paper) throws FrameworkRuntimeException {
		Long paperId;
		try {
			paperId = localExamPaperManage.getExamPaperId();
			// 试卷ID
			paper.setId(paperId);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("获取试卷ID出现异常!");
		}
		
		//快捷策略2
		if(paper.getPaper_mode() == Constants.PAPER_MODE_FT2){ 
			
			boolean flag = getPaperQuestion(paper);
			
			if(!flag){
				logger.error("快捷策略2--抽取试题错误!!");
				throw new FrameworkRuntimeException("抽取试题错误!!");
			}
			
			
			if(paper.getChild_paper_num()>0){
				
				String pname = paper.getName();
				paper.setName(pname+"_1");
				
				// 存放子试卷的List
				List<ExamPaper> childList = new ArrayList<ExamPaper>();
				// 设置子试卷
				for (int i = 0; i < paper.getChild_paper_num(); i++) {
					ExamPaper childExamPaper = new ExamPaper();
					// 复制子试卷对象
					BeanUtils.copyProperties(paper,childExamPaper);
					// 子试卷ID
					childExamPaper.setId(localExamPaperManage.getExamPaperId());
					// 父试卷ID
					childExamPaper.setParent_id(paperId);
					// 子试卷名称
					childExamPaper.setName(pname + "_" + (i+2));
					// 子试卷个数
					childExamPaper.setChild_paper_num(0);
					// 试卷试题列表
					childExamPaper.setExamPaperQuestionList(null);
					
					
					// 根据随机策略生成随机试题
					flag = getPaperQuestion(childExamPaper);
					
					if(!flag){
						throw new FrameworkRuntimeException("抽取试题错误!!");
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
		
		//快捷策略
		if(paper.getPaper_mode() == Constants.PAPER_MODE_FT){  
			
			ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
			paperFasterTactic.setGrade(paper.getGrade());
						
			List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
			
			setProp(paperFasterTactic,tXList);
			
			if(tXList==null || tXList.isEmpty()){
				//throw new FrameworkRuntimeException("快捷策略列表错误！");
				//没有试卷的情况 
				return -1L; 
			}
			
			//查询符合策略条件试题数
			localExamQuestionManage.getQuestSizeByFasterTactic(tXList);
			
			//System.out.println(tXList.size());
			
			try{
				//分配试题数
				for(ExamPaperFasterTacticX tx : tXList){
					setQuestionNum(tx.getExamPaperFasterTactic2(),tx.getNum()/tx.getExamPaperFasterTactic2().size(),tx.getNum());
				}
			}catch(Exception e){
				logger.error("分配错误",e);
				throw new FrameworkRuntimeException("分配错误",e);
			}
			
			paper.setPaperFasterTacticx(tXList);
			
			boolean flag = getPaperQuestion(paper);
			
			if(!flag){
				throw new FrameworkRuntimeException("抽取试题错误!!");
			}
			
			
			if(paper.getChild_paper_num()>0){
				String pname = paper.getName();
				paper.setName(pname+"_1");
				
				// 存放子试卷的List
				List<ExamPaper> childList = new ArrayList<ExamPaper>();
				// 设置子试卷
				for (int i = 0; i < paper.getChild_paper_num(); i++) {
					ExamPaper childExamPaper = new ExamPaper();
					// 复制子试卷对象
					BeanUtils.copyProperties(paper,childExamPaper);
					// 子试卷ID
					childExamPaper.setId(localExamPaperManage.getExamPaperId()+(i+1));
					// 父试卷ID
					childExamPaper.setParent_id(paperId);
					// 子试卷名称
					childExamPaper.setName(pname + "_" + (i+2));
					// 子试卷个数
					childExamPaper.setChild_paper_num(0);
					// 试卷试题列表
					childExamPaper.setExamPaperQuestionList(null);
					
					
					// 根据随机策略生成随机试题
					flag = getPaperQuestion(childExamPaper);
					
					if(!flag){
						throw new FrameworkRuntimeException("抽取试题错误!!");
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
			ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
			paperFasterTactic.setGrade(paper.getGrade());
			
			List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
			
			setProp(paperFasterTactic,tXList);
			
			if(tXList==null || tXList.isEmpty()){
				throw new FrameworkRuntimeException("快捷策略列表错误!!");
			}
			boolean flag = getPaperQuestion(paper);
			if (!flag) {
	            throw new RuntimeException("抽取试题错误!!");
	        }
			// 如果存在子试卷 设置子试卷信息
			if(paper.getChild_paper_num()>0){
				
				String pname = paper.getName();
				paper.setName(pname+"_1");
				
				// 存放子试卷的List
				List<ExamPaper> childList = new ArrayList<ExamPaper>();
				// 设置子试卷
				for (int i = 0; i < paper.getChild_paper_num(); i++) {
					ExamPaper childExamPaper = new ExamPaper();
					// 复制子试卷对象
					BeanUtils.copyProperties(paper,childExamPaper);
					// 子试卷ID
					childExamPaper.setId(localExamPaperManage.getExamPaperId()+(i+1));
					// 父试卷ID
					childExamPaper.setParent_id(paperId);
					// 子试卷名称
					childExamPaper.setName(pname + "_" + (i+2));
					// 子试卷个数
					childExamPaper.setChild_paper_num(0);
					// 试卷试题列表
					childExamPaper.setExamPaperQuestionList(null);
					// 根据随机策略生成随机试题
					getPaperQuestion(childExamPaper);
					
					//子试卷试题数量
					childExamPaper.setQuestion_num(paper.getQuestion_num());
					
					// 添加到子试卷列表
					childList.add(childExamPaper);
				}
				// 子试卷列表
				paper.setChildExamPaperList(childList);
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
		
		// 卷中卷
		if(paper.getPaper_mode().equals(Constants.PAPER_MODE_JZJ)){
			List<ExamPaperQuestion> examPaperQuestionList = new ArrayList<ExamPaperQuestion>();
			List<ExamPaperAndPaper> plist = paper.getPaperAndPaperList();
			if(plist==null){
				throw new FrameworkRuntimeException("卷中卷题型，试题列表错误!!");
				
			}
			double paperScore = 0;
			for(ExamPaperAndPaper p : plist){
				List<ExamQuestion> qlist = localExamQuestionManage.getQuestionByPaperIDArr(paper.getPaperIdArr(), p.getQuestion_label_id());
				int selnum = p.getSelNum();
				if(qlist==null || qlist.isEmpty() || selnum>qlist.size()){
					throw new FrameworkRuntimeException("卷中卷题型，试题列表错误!!");
				}
				for(int i=0;i<selnum;i++){
					int rannum=(int)(Math.random()*qlist.size());
					ExamPaperQuestion pq = new ExamPaperQuestion();
					pq.setQuestion_id(qlist.get(rannum).getId());
					pq.setQuestion_score(p.getQuestion_score());
					pq.setPaper_id(paper.getId());
					examPaperQuestionList.add(pq);
					paperScore += pq.getQuestion_score();
					qlist.remove(rannum);
				}
			}
			paper.setExamPaperQuestionList(examPaperQuestionList);
			paper.setPaper_score(paperScore);
			paper.setQuestion_num(examPaperQuestionList.size());
		}

		try {
			// 新增试卷信息
			return localExamPaperManage.addExamPaper(paper);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("新增试卷出现异常!");
		}

	}

	@Override
	public void deleteExamPaper(Long[] id) throws FrameworkRuntimeException {
		try {
			localExamPaperManage.deleteExamPaper(id);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000);
		}

	}

	@Override
	public ExamPaper getExamPaperById(Long id) throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return localExamPaperManage.getExamPaperById(id);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!");
		}
	}

	@Override
	public Long getExamPaperId() throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return localExamPaperManage.getExamPaperId();
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!");
		}
	}

	@Override
	public List<ExamPaper> getExamPaperList(PageList pl, ExamPaperQuery examPaperQuery, String createDateFrom, String createDateTo)
			throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return localExamPaperManage.getExamPaperList(pl, examPaperQuery, createDateFrom, createDateTo);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!");
		}
	}

	@Override
	public void updateBatchExamPaper(ExamPaper paper, Long[] id)
			throws FrameworkRuntimeException {

	}

	@Override
	public String updateExamPaper(ExamPaper paper)
			throws FrameworkRuntimeException {

		//快捷策略
		if((paper.getPaper_mode().equals(Constants.PAPER_MODE_FT)) && (paper.getIsnot_save_tactic().equals(1))) {  
			
				ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
				paperFasterTactic.setGrade(paper.getGrade());
							
				List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
				
				setProp(paperFasterTactic,tXList);
				
				if(tXList==null || tXList.isEmpty()){
					//throw new FrameworkRuntimeException("快捷策略列表错误！");
					//没有试卷的情况 
					return "fail"; 
				}
				
				//查询符合策略条件试题数
				localExamQuestionManage.getQuestSizeByFasterTactic(tXList);
				
				//System.out.println(tXList.size());
				
				try{
					//分配试题数
					for(ExamPaperFasterTacticX tx : tXList){
						setQuestionNum(tx.getExamPaperFasterTactic2(),tx.getNum()/tx.getExamPaperFasterTactic2().size(),tx.getNum());
					}
				}catch(Exception e){
					logger.error("分配错误",e);
					throw new FrameworkRuntimeException("分配错误",e);
				}
				
				paper.setPaperFasterTacticx(tXList);
				
				boolean flag = getPaperQuestion(paper);
				
				if(!flag){
					throw new FrameworkRuntimeException("抽取试题错误!!");
				}				
		} else if ((paper.getPaper_mode().equals(Constants.PAPER_MODE_RM)) && (paper.getIsnot_save_tactic().equals(1))) {
			ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
			paperFasterTactic.setGrade(paper.getGrade());
			
			List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
			
			setProp(paperFasterTactic,tXList);
			
			if(tXList==null || tXList.isEmpty()){
				throw new FrameworkRuntimeException("快捷策略列表错误!!");
			}
			boolean flag = getPaperQuestion(paper);
			if (!flag) {
	            throw new RuntimeException("抽取试题错误!!");
	        }
		} else if ((paper.getPaper_mode().equals(Constants.PAPER_MODE_JZJ)) && (paper.getIsnot_save_tactic().equals(1))){
			
			List<ExamPaperQuestion> examPaperQuestionList = new ArrayList<ExamPaperQuestion>();
			List<ExamPaperAndPaper> plist = paper.getPaperAndPaperList();
			
			if(plist==null){
				throw new FrameworkRuntimeException("卷中卷题型，试题列表错误!!");
				
			}
			double paperScore = 0;
			for(ExamPaperAndPaper p : plist){
				List<ExamQuestion> qlist = localExamQuestionManage.getQuestionByPaperIDArr(paper.getPaperIdArr(), p.getQuestion_label_id());
				int selnum = p.getSelNum();
				if(qlist==null || qlist.isEmpty() || selnum>qlist.size()){
					throw new FrameworkRuntimeException("卷中卷题型，试题列表错误!!");
				}
				for(int i=0;i<selnum;i++){
					int rannum=(int)(Math.random()*qlist.size());
					ExamPaperQuestion pq = new ExamPaperQuestion();
					pq.setQuestion_id(qlist.get(rannum).getId());
					pq.setQuestion_score(p.getQuestion_score());
					pq.setPaper_id(paper.getId());
					examPaperQuestionList.add(pq);
					paperScore += pq.getQuestion_score();
					qlist.remove(rannum);
				}
			}
			paper.setExamPaperQuestionList(examPaperQuestionList);
			paper.setPaper_score(paperScore);
			paper.setQuestion_num(examPaperQuestionList.size());
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
		try {
			// 修改试卷信息
			return localExamPaperManage.updateExamPaper(paper);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("修改试卷出现异常!",e);
		}
	}

	@Override
	public int getExamPaperListSize(ExamPaperQuery examPaperQuery)
			throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return localExamPaperManage.getExamPaperListSize(examPaperQuery);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!",e);
		}
	}

	@Override
	public List<ExamPaper> getExamPaperListByExamId(Long examId)
			throws FrameworkRuntimeException {
		try {
			// 查询试卷
			return localExamPaperManage.getExamPaperListByExamId(examId);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException("查询试卷出现异常!",e);
		}
	}

	//精细策略
	private boolean getPaperQuestion(ExamPaper examPaper)
			throws FrameworkRuntimeException {
		OrderedMap<Long, ExamPaperQuestion> questionList = new OrderedMap<Long, ExamPaperQuestion>();
		
		boolean b = getExamQuestionMap(examPaper, questionList);
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
	private boolean getExamQuestionMap(ExamPaper examPaper,
			OrderedMap<Long, ExamPaperQuestion> questionList)
			throws FrameworkRuntimeException {

		if(examPaper.getPaper_mode() == Constants.PAPER_MODE_FT || examPaper.getPaper_mode() == Constants.PAPER_MODE_RM){
			
			List<ExamPaperFasterTacticX> paperFasterTactic = examPaper.getPaperFasterTacticx();
			int totalCount = examPaper.getQuestion_num();
			//只有一级学科时,穷尽二级科
			avgStudy2QuestionNum(paperFasterTactic);
            List<Long> seledIds = new ArrayList<Long>();
            
			int seq = 0;
			for (int i = 0; i < paperFasterTactic.size(); i++) {
				List<Long> questIds = new ArrayList<Long>();
				// 题型 试题分类 试题数量 试题分数 是否是多媒体试题
				ExamPaperFasterTacticX paperTactic = paperFasterTactic.get(i);
				// 级联属性 总试题数 所抽取试题数
				List<ExamPaperFasterTactic2> t2List = paperTactic.getExamPaperFasterTactic2();
				
				for (int j = 0; j < t2List.size(); j++) {
					ExamPaperFasterTactic2 t2 = t2List.get(j);
					for(int k = 0 ; k < t2.getExamQuestionIds().size() ; k++)
					{
						if(examPaper.getPaper_mode() == Constants.PAPER_MODE_FT)
						{
							questIds.add(t2.getExamQuestionIds().get(k));
						}
						else
						{	
							if(k >= t2.getSelQuestionNum())
							{
								continue;
							}
							questIds.add(t2.getExamQuestionIds().get(k));
						}
					}
				}
				for (int j = 0; j < t2List.size(); j++) {
					
					ExamPaperFasterTactic2 t2 = t2List.get(j);
/*					// 获取策略KEY
					String key = getKeys(t2, paperTactic.getQuestion_label_id());
					// 取符合策略的试题列表
					LinkedList<ExamQuestion> qlist = getQuestionListByTactic(key);
	*/				
					for (int k = 0; k < t2.getSelQuestionNum(); k++) {
						++seq;
						// 试卷试题
						ExamPaperQuestion paperQuestion = new ExamPaperQuestion();
						ExamQuestion eq = new ExamQuestion();
						// 查找试题ID
/*				
						ExamQuestion examQuestion = findQuestion(qlist,
								questionList, key, paperTactic
										.getQuestion_label_id(), paperTactic
										.getQuestion_type_id(), t2, paperTactic
										.getQuestionPropMap(),paperTactic.getIsnot_multimedia());
						
						if(examQuestion == null){
							return false;
						}
	*/					
                       Long beSelId = null;
						try {
							beSelId = getseLId(seledIds, questIds);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        if (beSelId == null) {
                            continue;
                        }
                        eq.setId(beSelId);
						// 试卷ID
						paperQuestion.setPaper_id(examPaper.getId());
						// 试题ID
						paperQuestion.setQuestion_id(eq.getId());
						// 顺序
						//paperQuestion.setSeq(k+1);
						// 试卷试题分数
						paperQuestion.setQuestion_score(paperTactic.getScore());
						
						questionList.put(eq.getId(), paperQuestion);
					}
				}
			}
		} else {
			List<ExamPaperTactic> examPaperTactic = examPaper.getPaperTacticList();
			for (int i = 0; i < examPaperTactic.size(); i++) {
				ExamPaperTactic paperTactic = examPaperTactic.get(i);
				// 获取策略KEY
				String key = getKey(paperTactic);
				// 取符合策略的试题列表
				LinkedList<ExamQuestion> qlist = getQuestionListByTactic(key);
				// 获取试题
				for (int j = 0; j < paperTactic.getAmount(); j++) {
					// 试卷试题
					ExamPaperQuestion paperQuestion = new ExamPaperQuestion();
					// 查找试题ID
					ExamQuestion examQuestion = findQuestion(qlist,paperTactic,key,questionList);
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
	private String getKeys(ExamPaperFasterTactic2 t2,int question_label_id){
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
	private String getKey(ExamPaperTactic paperTactic){
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
	 * @param key 策略KEY
	 */
	private synchronized LinkedList<ExamQuestion> getQuestionListByTactic(String key) 
			 throws FrameworkRuntimeException {
		LinkedList<ExamQuestion> list = QUESTION_CACHE.get(key);
		if (list == null) {
			list = new LinkedList<ExamQuestion>();
			QUESTION_CACHE.put(key, list);
		}
		return list;
	}	

	
	/**
	 * 
	 * 精细策略对象
	 * @param list 试题列表
	 * @param paperTactic 精细策略对象
	 * @param key 策略key
	 * @param OrderedMap<Long, ExamPaperQuestion> questionList 试卷试题Map
	 */
	private ExamQuestion findQuestion(LinkedList<ExamQuestion> list,
			ExamPaperTactic paperTactic, String key,
			OrderedMap<Long, ExamPaperQuestion> questionList)
			throws FrameworkRuntimeException {
		
			// 检查是否有数据
			if (!checkList(list, paperTactic, key)) {
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
				return findQuestion(list, paperTactic, key, questionList);
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
	private ExamQuestion findQuestion(LinkedList<ExamQuestion> list,
			OrderedMap<Long, ExamPaperQuestion> questionList, String key,
			int question_label_id, String question_type_id,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap,Integer isnot_multimedia) {
		
			
		// 检查是否有数据
		if (!checkList(list,key,question_label_id,question_type_id,t2,questionPropMap,isnot_multimedia)) {
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
			return findQuestion(list, questionList, key, question_label_id, question_type_id, t2, questionPropMap,isnot_multimedia);
		}
		return examQuestion;
	}
	
	/**
	 * 
	 * 精细策略检查试题是否存在
	 * @param list 试题列表
	 * @param paperTactic 策略对象
	 * @param key 策略key
	 * @throws FrameworkRuntimeException
	 * @return: boolean
	 */
	private synchronized boolean checkList(LinkedList<ExamQuestion> list,
			ExamPaperTactic paperTactic,String key) throws FrameworkRuntimeException {
		if (list.size() == 0) {
			List<ExamQuestion> newList = null;
			int currentPage =0;
			try {
					currentPage = getCurrentPage(paperTactic, key);
					// 从数据库中取出下一页的数据
					newList = localExamQuestionManage.getQuestionList(paperTactic,PAGE_SIZE, currentPage);
					if(newList == null || (newList != null && newList.size()< paperTactic.getAmount())){
						return false;
					}
			} catch (Exception e) {
				throw new FrameworkRuntimeException("检查试题是否存在出现异常！");
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
	private synchronized boolean checkList(LinkedList<ExamQuestion> list,
			String key, int question_label_id, String question_type_id,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap,Integer isnot_multimedia)
			throws FrameworkRuntimeException {
		if (list.size() == 0) {
			List<ExamQuestion> newList = null;
			try {
				// 从数据库中取出下一页的数据
				newList = localExamQuestionManage.getQuestionListByByFasterTactic(question_label_id, question_type_id, t2, questionPropMap,isnot_multimedia);
				if(newList == null || (newList != null && newList.size()<t2.getSelQuestionNum())){
					logger.error("快捷检查试题错误,试题数小于被选数");
					return false;
				}
			} catch (Exception e) {
				logger.error("快捷检查试题是否存在出现异常！");
				throw new FrameworkRuntimeException("快捷检查试题是否存在出现异常！", e);
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
		return random.nextInt(number);
	}
	
	/**
	 * 查询当前页
	 * 
	 * @param paper
	 * @param key
	 * @return
	 */
	private synchronized int getCurrentPage(ExamPaperTactic paperTactic,String key) {
		Integer currentPage = PAGE_CACHE.get(key);

		if (currentPage == null) {
			currentPage = 0;
		} else {
			currentPage++;
		}

		int count = localExamQuestionManage.getQuestionListSize(paperTactic);
		

		if (currentPage >= getPageCount(count, PAGE_SIZE)) {
			currentPage = 0;
		}

		PAGE_CACHE.put(key, currentPage);

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
		
		List<ExamPaperFasterTactic3> examPaperFasterTactic4 = tactic.getExamPaperFasterTactic4();
		
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
		
		setProp(examPaperFasterTactic3,questionPropMap);
		setProp(examPaperFasterTactic4,questionPropMap);
		
				
		for(ExamPaperFasterTactic1 t1 : examPaperFasterTactic1){
			if(t1.getNum()>0){
				ExamPaperFasterTacticX tx = new ExamPaperFasterTacticX();
				tx.setQuestion_label_id(t1.getQuestion_label_id());
				tx.setNum(t1.getNum());
				tx.setQuestion_type_id(tactic.getQuestion_type_id());
				tx.setScore(t1.getScore());
				tx.setIsnot_multimedia(tactic.getIsnot_multimedia());
				
				if(examPaperFasterTactic2!=null){
					List<ExamPaperFasterTactic2> tmpList = new ArrayList<ExamPaperFasterTactic2>();
					tmpList.addAll(examPaperFasterTactic2);
					tx.setExamPaperFasterTactic2(tmpList);
				}
				if(examPaperFasterTactic3!=null){
					tx.setQuestionPropMap(questionPropMap);
				}
				tx.setGrade(tactic.getGrade());
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
	 * @param avg 分配试题数
	 * @param qnum 试题总数
	 * 
	 */
	public void setQuestionNum(List<ExamPaperFasterTactic2> t, int avg,int qnum){
		try{
			
			//记录每条策略的试题总数
			int all = 0;
			//记录每条策略的已配试题总数
			int sel = 0;
			
			for(int i=0;i<t.size();i++){
				
				if(qnum<1){
					break;
				}
				
				
				if((t.get(i).getAllQuestionNum()>=avg)){
					if((t.get(i).getSelQuestionNum()+avg)<=t.get(i).getAllQuestionNum()){
						t.get(i).setSelQuestionNum(t.get(i).getSelQuestionNum()+avg);
						qnum = qnum - avg;
						all += t.get(i).getAllQuestionNum();
						sel += t.get(i).getSelQuestionNum();
					}
				}else{
					if(t.get(i).getAllQuestionNum()>t.get(i).getSelQuestionNum()){
						t.get(i).setSelQuestionNum(t.get(i).getAllQuestionNum());
						qnum = qnum - t.get(i).getAllQuestionNum();
						all += t.get(i).getAllQuestionNum();
						sel += t.get(i).getSelQuestionNum();
					}
				}
				
			}
			if(qnum>=1 && all!=sel){
				int tmp = qnum/t.size();
				if(tmp==0){
					setQuestionNum(t,1,qnum);
				}else{
					setQuestionNum(t,tmp,qnum);
				}
			}
		
			
		}catch(Exception e){
			logger.error("分配错误"+e);
			throw new FrameworkRuntimeException("分配错误"+e);
		}
	}

	@Override
	public void deleteExamPaperFasterTactic(Long id)
			throws FrameworkRuntimeException {
		try {
			localExamPaperManage.deleteExamPaperFasterTactic(id);
		} catch (Exception e) {
			logger.error("删除快捷策略出现异常:"+e);
			throw new FrameworkRuntimeException("删除快捷策略出现异常!");
		}
		
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(Long id)
			throws FrameworkRuntimeException {
		try {
			return localExamPaperManage.getExamPaperFasterTacticById(id);
		} catch (Exception e) {
			logger.error("获取快捷策略出现异常"+e);
			throw new FrameworkRuntimeException("获取快捷策略出现异常!");
		}
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			ExamPaperFasterTactic tactic) throws FrameworkRuntimeException {
		try {
			return localExamPaperManage.getExamPaperFasterTacticByPaperTypeId(tactic);
		} catch (Exception e) {
			logger.error("获取快捷策略列表出现异常"+e);
			throw new FrameworkRuntimeException("获取快捷策略列表出现异常!");
		}
	}

	@Override
	public void updateExamePaperTypeByPaperId(Long paperTypeId, Long paperId)
			throws FrameworkRuntimeException {
		try {
			localExamPaperManage.updateExamePaperTypeByPaperId(paperTypeId, paperId);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000);
		}
	}

	@Override
	public ExamPaper getQuestSizeByFasterTactic(ExamPaper paper)
			throws FrameworkRuntimeException {
		try{
			ExamPaperFasterTactic paperFasterTactic = paper.getPaperFasterTactic();
			
			List<ExamPaperFasterTacticX> tXList = new ArrayList<ExamPaperFasterTacticX>();
			
			setProp(paperFasterTactic,tXList);
			
			if(tXList==null || tXList.isEmpty()){
				throw new FrameworkRuntimeException("快捷策略列表错误！");
			}
			
			//查询符合策略条件试题数
			localExamQuestionManage.getQuestSizeByFasterTactic(tXList);
			
			paper.setPaperFasterTacticx(tXList);
		} catch (Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000);
		}
		
		return paper;
	}

	@Override
	public void updateExamPaperQuestion(Long paperId, Long oldQuestionID,
			Long newQuestionId, Double score) throws FrameworkRuntimeException {
		try {
			localExamPaperManage.updateExamPaperQuestion(paperId, oldQuestionID, newQuestionId, score);
		} catch (Exception e) {
			logger.error("",e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000);
		}
	}
	
	/**
	 * 如果级联属性只填有一级学科。取题平均分配到所属的二级学科
	 * 
	 * @param txList
	 */
	private void avgStudy2QuestionNum(List<ExamPaperFasterTacticX> txList) {

		for (int i = 0; i < txList.size(); i++) {
			ExamPaperFasterTacticX tacticX = txList.get(i);
			
			List<ExamPaperFasterTactic2> t2List = tacticX.getExamPaperFasterTactic2();
			
			for (int j = 0; j < t2List.size(); j++) {
				// 如果有有一级学科
				if (t2List.get(j).getStudy1_id() != null
						&& t2List.get(j).getStudy2_id() == null
						&& t2List.get(j).getStudy3_id() == null
						&& t2List.get(j).getPoint_id() == null
						&& t2List.get(j).getUnit_id() == null) {
					
					
					
					// 删除原有的一级学科策略
					ExamPaperFasterTactic2 t2 = t2List.remove(j);
					
					int num = t2.getSelQuestionNum();

					// 取二级学科
					List<ExamProp> propList = localExamPropValManage
							.getNextLevelProp(t2.getStudy1_id());

					List<ExamPaperFasterTactic2> tmpT2List = new ArrayList<ExamPaperFasterTactic2>();
					// 写入二级学科
					for (int k = 0; k < propList.size(); k++) {
						ExamPaperFasterTactic2 nt2 = new ExamPaperFasterTactic2();
						nt2.setStudy1_id(t2.getStudy1_id());
						nt2.setStudy1_name(t2.getStudy1_name());
						nt2.setStudy2_id(propList.get(k).getId());
						nt2.setStudy2_name(propList.get(k).getName());
						tmpT2List.add(nt2);
					}
					
					// 写入试题总数
					List<ExamPaperFasterTactic2> newT2List = localExamQuestionManage.getQuestSizeByFasterTactic(tacticX
							.getQuestion_label_id(), tacticX
							.getQuestion_type_id(), tmpT2List, tacticX
							.getQuestionPropMap(), tacticX
							.getIsnot_multimedia());
					
					
					
					for(int l=0;l<newT2List.size();l++){
						if(newT2List.get(l).getAllQuestionNum()==0){
							newT2List.remove(l);
							l = l -1;
						}
					}
					
					if(!newT2List.isEmpty()){
						//平均分配试题
						setQuestionNum(newT2List,num/newT2List.size(),num);
						//删除无分配试题数的策略
						for(int l=0;l<newT2List.size();l++){
							if(newT2List.get(l).getSelQuestionNum()==0){
								newT2List.remove(l);
								l = l -1;
							}
						}
	
						//加入分配后的策略列表
						t2List.addAll(j,newT2List);
					}
				}
			}
		}
	}
    /**
     * 随机取题位置
     */
    @SuppressWarnings("deprecation")
	private int randomNum(Integer size) {
    	Date now = new Date();
        return (int) ((Math.random()* now.getSeconds()) % size);
    }
    /**
     * 递归取不重复试题ID
     */
    private Long getseLId(List<Long> seledIds, List<Long> ids) throws Exception {
        if (ids.size() == 0) {
            return null;
        } else {
            Long beSelId = ids.get(randomNum(ids.size()));
            if (seledIds.contains(beSelId)) {
                ids.remove(beSelId);
                return getseLId(seledIds, ids);
            } else {
                seledIds.add(beSelId);
                return beSelId;
            }
        }
    }

	@Override
	public int getExamCountByPaperIds(Integer labelId, String paperIds)
			throws FrameworkRuntimeException {
		if (paperIds != null && !paperIds.equals("")){
			return localExamPaperManage.getExamCountByPaperIds(labelId, paperIds);
		}
		else{
			return 0;
		}
	}

	@Override
	public boolean updateContral(ExamPaperQuery paper) {
		return localExamPaperManage.updateContral(paper);
	}

	@Override
	public List<ExamPaper> getExamPaperList(ExamPaperQuery examPaperQuery)
			throws FrameworkRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ExamPaperManage getLocalExamPaperManage() {
		return localExamPaperManage;
	}
	public void setLocalExamPaperManage(ExamPaperManage localExamPaperManage) {
		this.localExamPaperManage = localExamPaperManage;
	}
	public ExamQuestionManage getLocalExamQuestionManage() {
		return localExamQuestionManage;
	}
	public void setLocalExamQuestionManage(
			ExamQuestionManage localExamQuestionManage) {
		this.localExamQuestionManage = localExamQuestionManage;
	}
	public ExamPropValManage getLocalExamPropValManage() {
		return localExamPropValManage;
	}
	public void setLocalExamPropValManage(ExamPropValManage localExamPropValManage) {
		this.localExamPropValManage = localExamPropValManage;
	}
	
	
}
