package com.hys.exam.struts.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-26
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseTypeListAction extends BaseAction {

	private StudyCourseTypeFacade studyCourseTypeFacade;

	public void setStudyCourseTypeFacade(StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0);

		Integer currentPage = PageUtil.getPageIndex(request);
		Page<StudyCourseType> page = new Page<StudyCourseType>();
		page.setCurrentPage(currentPage);

		StudyCourseType query = new StudyCourseType();
		query.setCourseTypeName(request.getParameter("queryStudyCourseTypeName"));

		studyCourseTypeFacade.getCourseTypeByParent(page, curTypeId, query);
		
		//重新初始化SEQ
		reinitCourseTypeSeq(page.getList()) ;
		
		Integer maxSeq = studyCourseTypeFacade.getCurTypeMaxSeq(curTypeId) ;
		
		request.setAttribute("StudyCourseTypeList", page);
		request.setAttribute("maxSeq", maxSeq);

		saveToken(request);// 设置token

		return Constants.SUCCESS;
	}
	
	/**
	 * 重新初始化课程分类Seq
	 * @param typeList
	 */
	private void reinitCourseTypeSeq(List<StudyCourseType> typeList){
		if(!Utils.isListEmpty(typeList)){
			int length = typeList.size() ;
			for (int i = 0; i < length; i++) {
				StudyCourseType curType = typeList.get(i) ;
				curType.setUpOrDownSeq(i+1) ;
			}
		}
	}
}