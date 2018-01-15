package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.service.local.StudyCourseTypeManage;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseTypeAddAction extends BaseAction {
	private StudyCourseTypeFacade studyCourseTypeFacade;
	
	//课程分类
	private StudyCourseTypeManage studyCourseTypeManage;

	public void setStudyCourseTypeFacade(
			StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String method = request.getParameter("method");
		if(StringUtils.isNotBlank(method) && method.equals("selectCourseType")){
			return this.selectCourseType(mapping, form, request, response);
		}
		
		Long id = ParamUtils.getLongParameter(request, "curTypeId", -1L);

		if (id >= 0) {
			List<StudyCourseType> list = studyCourseTypeFacade.getStudyCourseTypeListById(id);

			request.setAttribute("StudyCourseTypeList", list);
		}

		saveToken(request);// 设置token
		return Constants.SUCCESS;
	}
	
	public String selectCourseType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		Long parentId = 0L;
		List<StudyCourseType> typeList = this.studyCourseTypeManage.getCourseTypeByParent(parentId);
		if(!Utils.isListEmpty(typeList)){
			List<StudyCourseType> subTypeList = new ArrayList<StudyCourseType>();
			for(StudyCourseType type : typeList){
				subTypeList = this.studyCourseTypeManage.getCourseTypeByParent(type.getId());
				type.setTypeList(subTypeList);
			}
		}
		
		request.setAttribute("typeList", typeList);
		return "selectCourseType";
	}
	
	public StudyCourseTypeManage getStudyCourseTypeManage() {
		return studyCourseTypeManage;
	}

	public void setStudyCourseTypeManage(StudyCourseTypeManage studyCourseTypeManage) {
		this.studyCourseTypeManage = studyCourseTypeManage;
	}

}
