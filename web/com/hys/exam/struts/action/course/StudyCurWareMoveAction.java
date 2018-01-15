package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程组件 进行上移与下移操作
 * 
 * 说明:
 */
public class StudyCurWareMoveAction extends BaseAction {
	
	private StudyCourseElementFacade studyCourseElementFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//课程ID
		Long courseId = ParamUtils.getLongParameter(request, "courseId", 0) ;
		//课程分类ID
		Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0) ;
		//课程组件ID
		Long elementId = ParamUtils.getLongParameter(request, "elementId", 0) ;
		//上移与下移标记 空(null) 表示上移 非空(not null) 表示下移
		String moveFlag = ParamUtils.getParameter(request, "moveFlag", null) ;
		
		if(this.isTokenValid(request)){
			//查询出需要上移或下移的组件信息
			StudyCourseElement element = studyCourseElementFacade.getStudyCourseElementById(elementId) ;
			
			int flag = 0 ;
			boolean updateFlag = true ;
			if(moveFlag == null){
				flag = 1 ;
				//进行上移操作
				if(element.getSeq() <= 1){
					updateFlag = false ;
				}
			}else{
				//取得最大SEQ
				Integer maxSeq = studyCourseElementFacade.getCurElementMaxSeq(courseId) ;
				maxSeq = maxSeq == null ? 1 : maxSeq ;
				if(element.getSeq() >= maxSeq){
					updateFlag = false ;
				}
				//进行下移操作
				flag = 2 ;
			}
			
			if(updateFlag){
				//最近一条数据
				StudyCourseElement nearEle = studyCourseElementFacade.getCurElementRecentlyData(courseId, element.getSeq(), flag) ;
				if(nearEle != null){
					long originalSeq = element.getSeq() ; 
					element.setSeq(nearEle.getSeq()) ;
					nearEle.setSeq(originalSeq) ;
					
					List<StudyCourseElement> eleList = new ArrayList<StudyCourseElement>() ;
					eleList.add(nearEle) ;
					eleList.add(element) ;
					
					studyCourseElementFacade.updateCurElementBatch(eleList) ;
				}
			}
			
			this.resetToken(request) ;
		}
		
		String path = request.getContextPath() ;
		response.sendRedirect(path + "/course/studyCurWareView.do?curTypeId="+curTypeId+"&courseId="+courseId);
		
		return null;
	}

	public void setStudyCourseElementFacade(StudyCourseElementFacade studyCourseElementFacade) {
		this.studyCourseElementFacade = studyCourseElementFacade;
	}
}