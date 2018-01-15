package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.ExamPropListByDirectManage;
import com.hys.framework.web.action.BaseAction;


public class ExamPropListByDirectAjaxAction extends BaseAction {
	
	public ExamPropListByDirectManage getLocalExamPropListByDirectManage() {
		return localExamPropListByDirectManage;
	}

	public void setLocalExamPropListByDirectManage(
			ExamPropListByDirectManage localExamPropListByDirectManage) {
		this.localExamPropListByDirectManage = localExamPropListByDirectManage;
	}

	private ExamPropListByDirectManage localExamPropListByDirectManage;

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String mode = request.getParameter("mode") == null ? "" : request.getParameter("mode");
		String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids");
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		ExamProp prop = new ExamProp();
		
		if (mode.equals("getICD")){
			Map<Long,String> resMap = new HashMap<Long,String>();
			
			String[] idsArray = ids.split(",");
			
			for (int p = 0; p < idsArray.length; p++){
				if(idsArray[p].equals("")){
					continue;
				}
				List<ExamProp> list;
				ExamPropQuery query  = new ExamPropQuery();
				query.setSysPropId(Long.valueOf(idsArray[p]));
				ExamReturnProp rprop = localExamPropListByDirectManage.getNextLevelProp(query);
				list = rprop.getReturnList();
				
				for(int i = 0; i<list.size(); i++){
					List<ExamICD> icdList = list.get(i).getIcdList();
					for (int j = 0; j<icdList.size(); j++){
						resMap.put(icdList.get(j).getId(), icdList.get(j).getName());
					}
				}
			}
			
			List<String> resIds = new ArrayList<String>();
			for (Long pid: resMap.keySet()){
				resIds.add(resMap.get(pid));
			}
			String resString = resIds.toString();
			resString = resString.substring(1,resString.length()-1);
			StrutsUtil.renderText(response, resString);
			return null;
		}
		
		List<ExamProp> list;
		if(id.equals("0")){
			prop.setType(Integer.valueOf(type));
			list = localExamPropListByDirectManage.getPropListByType(prop);
		}else{
			
			ExamPropQuery query  = new ExamPropQuery();
			query.setSysPropId(Long.valueOf(id));
			ExamReturnProp rprop = localExamPropListByDirectManage.getNextLevelProp(query);
			list = rprop.getReturnList();
		}
		JSONObject result = new JSONObject();
		
		result.put("list", list);
		StrutsUtil.renderText(response, result.toString());

		return null;
	}

}
