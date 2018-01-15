package com.hys.exam.struts.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：查询课程列表信息h
 * 
 * 说明:
 */
public class StudyCourseMenuAction extends BaseAction {

	private StudyCourseTypeFacade studyCourseTypeFacade;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long parentId = ParamUtils.getLongParameter(request, "parentId", -1);

		//查询课程分类信息
		List<StudyCourseType> typeList = studyCourseTypeFacade.getCourseTypeByParent(parentId);
		
		JSONArray array = new JSONArray();
		JSONObject jObject = null ;
		for (StudyCourseType curType : typeList) {
			jObject = new JSONObject() ;
			jObject.put("id", curType.getId()) ;
			if (curType.getIntroduction() == null
					|| "".equals(curType.getIntroduction())) {
				jObject.put("text", curType.getCourseTypeName());
			} else {
				jObject.put(
						"text",
						curType.getCourseTypeName() + "("
								+ curType.getIntroduction() + ")");
			}

			JSONObject attrs = new JSONObject() ;
			attrs.put("parentId", curType.getParentCourseTypeId()) ;
			jObject.put("attributes", attrs) ;
			
			if(curType.getIsLastLevel() == null || curType.getIsLastLevel() == 0){
				jObject.put("state", "closed");
			}
			array.add(jObject) ;
		}
		
		Utils.renderText(response, array.toString()) ;
		return null;
	}

	public void setStudyCourseTypeFacade(StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}
}