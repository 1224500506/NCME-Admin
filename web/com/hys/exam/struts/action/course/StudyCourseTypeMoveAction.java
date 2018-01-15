package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程分类 进行上移与下移操作
 * 
 * 说明:
 */
public class StudyCourseTypeMoveAction extends BaseAction {
	
	private StudyCourseTypeFacade studyCourseTypeFacade;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//课程父分类ID
		Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0) ;
		//课程分类ID
		Long moveId = ParamUtils.getLongParameter(request, "moveId", 0) ;
		//上移与下移标记 空(null) 表示上移 非空(not null) 表示下移
		String moveFlag = ParamUtils.getParameter(request, "moveFlag", null) ;
		
		if(this.isTokenValid(request)){
			//查询出需要上移或下移的组件信息
			StudyCourseType curType = studyCourseTypeFacade.getStudyCourseTypeById(moveId) ;
			
			int flag = 0 ;
			boolean updateFlag = true ;
			if(moveFlag == null){
				flag = 1 ;
				//进行上移操作
				if(curType.getSeq() <= 1){
					updateFlag = false ;
				}
			}else{
				//取得最大SEQ
				Integer maxSeq = studyCourseTypeFacade.getCurTypeMaxSeq(curTypeId) ;
				maxSeq = maxSeq == null ? 1 : maxSeq ;
				if(curType.getSeq() >= maxSeq){
					updateFlag = false ;
				}
				//进行下移操作
				flag = 2 ;
			}
			
			if(updateFlag){
				//最近一条数据
				StudyCourseType nearType = studyCourseTypeFacade.getCurTypeRecentlyData(curTypeId, curType.getSeq(), flag) ;
				if(nearType != null){
					int originalSeq = curType.getSeq() ; 
					curType.setSeq(nearType.getSeq()) ;
					nearType.setSeq(originalSeq) ;
					
					List<StudyCourseType> eleList = new ArrayList<StudyCourseType>() ;
					eleList.add(curType) ;
					eleList.add(nearType) ;
					
					studyCourseTypeFacade.updateCurTypeBatch(eleList) ;
				}
			}
			
			this.resetToken(request) ;
		}
		
		return Constants.SUCCESS;
	}

	public void setStudyCourseTypeFacade(StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}
}