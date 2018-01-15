package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.StringUtils;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.sessionfacade.local.ExamOlemPropFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemAddBasePropAction extends BaseAction {

	private ExamOlemPropFacade localExamOlemPropFacade;
	
	public ExamOlemPropFacade getLocalExamOlemPropFacade() {
		return localExamOlemPropFacade;
	}

	public void setLocalExamOlemPropFacade(
			ExamOlemPropFacade localExamOlemPropFacade) {
		this.localExamOlemPropFacade = localExamOlemPropFacade;
	}




	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String olemPropId = request.getParameter("olemPropId") == null ? "0" : request.getParameter("olemPropId");
		String propType = request.getParameter("propType") == null ? "0" : request.getParameter("propType");
		String idArr = request.getParameter("idArr") == null ? "" : request.getParameter("idArr");
		
		List<String> list = StringUtils.stringToArrayList(idArr, ",");
		
		if((!list.isEmpty()) && (!olemPropId.equals("0")) && (!propType.equals("0"))){
			List<ExamOlemPropRefBaseProp> propList = new ArrayList<ExamOlemPropRefBaseProp>();
			for(String tmp : list){
				ExamOlemPropRefBaseProp prop = new ExamOlemPropRefBaseProp();
				prop.setBase_prop_id(Long.valueOf(tmp));
				prop.setOlem_prop_id(Long.valueOf(olemPropId));
				propList.add(prop);
			}
			int flag = localExamOlemPropFacade.addExamOlemPropRefBaseProp(Long.valueOf(olemPropId), propList, Integer.valueOf(propType));
			StrutsUtil.renderText(response, flag+"");
		}		
		return null;
	}

}
