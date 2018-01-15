package com.hys.exam.struts.action.exam;

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
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;


public class ExamHospitalListAjaxAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;
	

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}


	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		PageList pl = new PageList();
	
		String type = request.getParameter("parentId") == null ? "0" : request.getParameter("parentId");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		ExamProp prop = new ExamProp();
		prop.setType(20);
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);

		ExamHospital host = new ExamHospital();
		{
			host.setPropId(Long.valueOf(type));
			host.setStartName(sname.trim());
			
			List<ExamHospital> list = null;
			/*Integer count = localExamPropValFacade.getHospitalListSize(host);
			if (count<100)*/{
				list = localExamPropValFacade.getHospitalList(host);
			}
			
/*			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			
			query.setPageNo(currentPage);
			query.setPageSize(pageSize);
*/			
			JSONObject result = new JSONObject();
		
			result.put("list", list);
			result.put("state","closed");
			StrutsUtil.renderText(response, result.toString());
	//	request.setAttribute("type", type);
	//	request.setAttribute("prop_val1", id);
//		request.setAttribute("propList", list);
		}

		return null;
	}
}
