package com.hys.exam.struts.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.exam.sessionfacade.local.StudyCoursewareFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程组件预览
 * 
 * 说明:
 */
public class StudyCurWarePreviewAction extends BaseAction {
	
	private ExamQuestionFacade examQuestionFacade ;
	
	private StudyCoursewareFacade studyCoursewareFacade ;
	
	private StudyCourseElementFacade studyCourseElementFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//课程组件类型
		Integer curEleType = ParamUtils.getIntParameter(request, "eleType", 0) ;
		//课程组件ID
		Long elementId = ParamUtils.getLongParameter(request, "eleId", 0) ;
		
		request.setAttribute("result", Constants.RESULT) ;
		if(curEleType < 11){
			StudyCourseware curWare = studyCoursewareFacade.getStudyCoursewareByEleId(elementId) ;
			
			request.setAttribute("curWare", curWare) ;
			
			return "SUCCESS";
		}else{
			//试题返回列表
			List<ExamQuestion> retList = null ;
			List<StudyCourseElementQuestion> questList = studyCourseElementFacade.getCurEleQuestByElement(elementId) ;
			if(!Utils.isListEmpty(questList)){
				retList = examQuestionFacade.getQuestionListByPaperId(questList
						.get(0).getStudyCourseQuestionId());
//				Long[] idArr = getQuestionToLong(questList) ;
//				
//				if(!Utils.isArrayEmpty(idArr)){
//					retList = examQuestionFacade.getQuestionIdByIdArr(idArr) ;
//					
//					//将SEQ 进行赋值操作
//					retList = getEleQuestForExamQuest(questList, retList) ;
//					
//					//创建比较器信息
//					Comparator<ExamQuestion> questAsc = new Comparator<ExamQuestion>(){
//						@Override
//						public int compare(ExamQuestion exam_1, ExamQuestion exam_2) {
//							return exam_1.getSeq().intValue() - exam_2.getSeq().intValue();
//						}
//					};
//					//对试题列表进行排序
//					Collections.sort(retList, questAsc) ;
//				}
			}

			request.setAttribute("retList", retList) ;
			
			return "SUCCESS_1";
		}
	}
//
//	/**
//	 * 将课件下的试题对象数组转换为Long数组
//	 * @param questList
//	 * @return
//	 */
//	private Long[] getQuestionToLong(List<StudyCourseElementQuestion> questList){
//		if(Utils.isListEmpty(questList)){
//			return new Long[0] ;
//		}
//		
//		int length = questList.size() ;
//		Long[] idArr = new Long[length] ;
//		for (int i = 0; i < length; i++) {
//			StudyCourseElementQuestion quest = questList.get(i) ; 
//			idArr[i] = quest.getStudyCourseQuestionId() ; 
//		}
//		return idArr ;
//	}
//	
//	/**
//	 * 将课件试题SEQ 赋值给ExamQuest的SEQ
//	 * @param questList 课件试题列表
//	 * @param retList	考试试题列表
//	 * @return
//	 */
//	private List<ExamQuestion> getEleQuestForExamQuest(List<StudyCourseElementQuestion> questList, List<ExamQuestion> retList){
//		
//		for (ExamQuestion examQues : retList) {
//			
//			for (Iterator<StudyCourseElementQuestion> iterator = questList.iterator(); iterator.hasNext();) {
//				StudyCourseElementQuestion quest = iterator.next();
//				if(quest.getStudyCourseQuestionId().equals(examQues.getId())){
//					examQues.setSeq(quest.getSeq()) ;
//					iterator.remove() ;
//					break ;
//				}
//			}
//		}
//		
//		return retList ;
//	}
//	
	public void setExamQuestionFacade(ExamQuestionFacade examQuestionFacade) {
		this.examQuestionFacade = examQuestionFacade;
	}
	
	public void setStudyCoursewareFacade(StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}

	public void setStudyCourseElementFacade(StudyCourseElementFacade studyCourseElementFacade) {
		this.studyCourseElementFacade = studyCourseElementFacade;
	}
}