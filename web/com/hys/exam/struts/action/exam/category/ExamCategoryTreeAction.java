package com.hys.exam.struts.action.exam.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamCategoryTreeAction extends BaseAction {

	private ExamExaminTypeFacade examExaminTypeFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long parentId = ParamUtils.getLongParameter(request, "parentId", Constants.TREE_PARENT_ID) ;
		
		ExamExaminTypeQuery query = new ExamExaminTypeQuery() ;
		query.setParent_id(parentId) ;
		query.setPageNo(1) ;
		query.setPageSize(1000) ;
		List<ExamExaminType> typeList = examExaminTypeFacade.getExamExaminTypeListByParentId(query).getReturnList() ;

		JSONArray array = new JSONArray();
		JSONObject jObject = null ;
		for (ExamExaminType examType : typeList) {
			jObject = new JSONObject() ;
			jObject.put("id", examType.getId()) ;
			jObject.put("text", examType.getName());
			
			JSONObject attrs = new JSONObject() ;
			attrs.put("parentId", examType.getParent_id()) ;
			jObject.put("attributes", attrs) ;
			
			if(examType.getChildNum() > 0){
				jObject.put("state", "closed");
			}
			array.add(jObject) ;
		}
		
		Utils.renderText(response, array.toString()) ;
		
		return null ;
	}
	
	public void setExamExaminTypeFacade(ExamExaminTypeFacade examExaminTypeFacade) {
		this.examExaminTypeFacade = examExaminTypeFacade;
	}
}
