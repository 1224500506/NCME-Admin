package com.hys.exam.struts.action;

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
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.framework.web.action.BaseAction;


public class ExamListAjaxAction extends BaseAction {
	
	private ExamExaminTypeFacade examExaminTypeFacade;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long parentId = ParamUtils.getLongParameter(request, "parentId", Constants.TREE_PARENT_ID) ;
		
		ExamExaminTypeQuery query = new ExamExaminTypeQuery() ;
		query.setParent_id(parentId) ;
		query.setPageNo(1) ;
		query.setPageSize(1000) ;
		List<ExamExaminType> paperList = examExaminTypeFacade.getExamExaminTypeListByParentId(query).getReturnList();

		JSONArray array = new JSONArray();
		JSONObject jObject = null ;
		for (ExamExaminType paperType : paperList) {
			jObject = new JSONObject() ;
			jObject.put("id", paperType.getId()) ;
			jObject.put("text", paperType.getName());
			
			JSONObject attrs = new JSONObject() ;
			attrs.put("parentId", paperType.getParent_id()) ;
			jObject.put("attributes", attrs) ;
			
			if(paperType.getChildNum() > 0){
				jObject.put("state", "closed");
			}
			array.add(jObject) ;
		}
		
		Utils.renderText(response, array.toString()) ;
		
		return null ;
	}
	
	public void setExamPaperTypeFacade(ExamExaminTypeFacade examExaminTypeFacade) {
		this.examExaminTypeFacade = examExaminTypeFacade;
	}	

}
