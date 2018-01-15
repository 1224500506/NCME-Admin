package com.hys.exam.struts.action.course;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.exam.sessionfacade.local.StudyCourseFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-2-27
 * 
 * 描述：课程课件预览
 * 
 * 说明:
 */
public class StudyCoursePreviewAction extends BaseAction {

	private StudyCourseFacade studyCourseFacade ;
	
	private ExamQuestionFacade examQuestionFacade ;
	
	private StudyCourseElementFacade studyCourseElementFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String forward = "" ;
		Integer eleIndex = ParamUtils.getIntParameter(request, "eleIndex", 1) ;//取得当前课程学习下标
		Long curId = ParamUtils.getLongParameter(request, "id", 0) ;//取得课程ID
		
		StudyCourse cur = new StudyCourse() ;
		cur.setId(curId) ;
		
		//查询课程下的课件信息
		List<StudyCourseElement> eleList = studyCourseFacade.getStudyCourseElementByCourse(curId) ;
		cur.setEleList(eleList) ;
		
		//取得页面所需组件信息
		StudyCourseElement ele = checkcourseEle(cur, eleIndex) ;
		
		if(ele == null){
			request.setAttribute("errorMsg", "当前课程下没有组件存在,请添加!") ;
			return Constants.SUCCESS_2 ;
		}
		
		if(ele.getCourseElementType() < 11){
			//查询指定组件信息
			StudyCourseware courseWare = studyCourseFacade.getCourseEleWareByElement(ele.getId()) ;
			
			request.setAttribute("courseWare", courseWare) ;
			//跳转到查看视频页面
			
			forward = Constants.SUCCESS ; 
		}else{
			
			//试题返回列表
			List<ExamQuestion> retList = null ;
			List<StudyCourseElementQuestion> questList = studyCourseElementFacade.getCurEleQuestByElement(ele.getId()) ;
			if(!Utils.isListEmpty(questList)){
				
				Long[] idArr = getQuestionToLong(questList) ;
				
				if(!Utils.isArrayEmpty(idArr)){
					retList = examQuestionFacade.getQuestionIdByIdArr(idArr) ;
					
					//将SEQ 进行赋值操作
					retList = getEleQuestForExamQuest(questList, retList) ;
					
					//创建比较器信息
					Comparator<ExamQuestion> questAsc = new Comparator<ExamQuestion>(){
						@Override
						public int compare(ExamQuestion exam_1, ExamQuestion exam_2) {
							return exam_1.getSeq().intValue() - exam_2.getSeq().intValue();
						}
					};
					//对试题列表进行排序
					Collections.sort(retList, questAsc) ;
				}
			}
			
			request.setAttribute("retList", retList) ;
			
			//跳转到查看考试页面
			forward = Constants.SUCCESS_1 ; 
		}
		
		request.setAttribute("eleIndex", eleIndex) ;
		
		request.setAttribute("curEle", cur) ;
		request.setAttribute("courseEle", ele) ;
		
		return forward ;
	}
	
	/**
	 * 设置课程中 课件显示序号
	 * 与根据页面传递序列取得对应组件信息
	 * @param cur		课程对象
	 * @param eleIndex	页面传递序列
	 * @return	对应组件信息
	 */
	private StudyCourseElement checkcourseEle(StudyCourse cur, Integer eleIndex){
		int wareIndex = 0 ;//课件序列下标
		int quesIndex = 0 ;//试题序列下标
		
		StudyCourseElement element = null ;
		int length = cur.getEleList().size() ;
		for (int i = 0; i < length; i++) {
			StudyCourseElement ele = cur.getEleList().get(i) ;
			if(i == (eleIndex-1)){
				element = ele ;
			}
			
			if(ele.getCourseElementType() < 11){
				wareIndex += 1 ;
				ele.setEleIndex(wareIndex) ;
			}else{
				quesIndex += 1 ;
				ele.setEleIndex(quesIndex) ;
			}
		}
		
		cur.setWareNumber(wareIndex) ;
		cur.setQuesNumber(quesIndex) ;
		
		return element ;
	}
	
	/**
	 * 将课件下的试题对象数组转换为Long数组
	 * @param questList
	 * @return
	 */
	private Long[] getQuestionToLong(List<StudyCourseElementQuestion> questList){
		if(Utils.isListEmpty(questList)){
			return new Long[0] ;
		}
		
		int length = questList.size() ;
		Long[] idArr = new Long[length] ;
		for (int i = 0; i < length; i++) {
			StudyCourseElementQuestion quest = questList.get(i) ; 
			idArr[i] = quest.getStudyCourseQuestionId() ; 
		}
		return idArr ;
	}
	
	/**
	 * 将课件试题SEQ 赋值给ExamQuest的SEQ
	 * @param questList 课件试题列表
	 * @param retList	考试试题列表
	 * @return
	 */
	private List<ExamQuestion> getEleQuestForExamQuest(List<StudyCourseElementQuestion> questList, List<ExamQuestion> retList){
		
		for (ExamQuestion examQues : retList) {
			
			for (Iterator<StudyCourseElementQuestion> iterator = questList.iterator(); iterator.hasNext();) {
				StudyCourseElementQuestion quest = iterator.next();
				if(quest.getStudyCourseQuestionId().equals(examQues.getId())){
					examQues.setSeq(quest.getSeq()) ;
					iterator.remove() ;
					break ;
				}
			}
		}
		
		return retList ;
	}
	
	public void setStudyCourseFacade(StudyCourseFacade studyCourseFacade) {
		this.studyCourseFacade = studyCourseFacade;
	}
	
	public void setExamQuestionFacade(ExamQuestionFacade examQuestionFacade) {
		this.examQuestionFacade = examQuestionFacade;
	}
	
	public void setStudyCourseElementFacade(StudyCourseElementFacade studyCourseElementFacade) {
		this.studyCourseElementFacade = studyCourseElementFacade;
	}
}
