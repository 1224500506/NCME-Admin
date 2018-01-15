package com.hys.exam.struts.action.exam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
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

/**
 * 医院级别
 * @author Alisa
 * @creat 2017-01-25
 *
 */
public class ExamHospitalAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;
	

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}


	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	/**
	 * @author Alisa
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String
	 * @Description get hospitallist for exanManage
	 * 
	 * */
	
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String type = request.getParameter("type") == null ? "23" : request.getParameter("type");
		String parentId = request.getParameter("parentId") == null ? "0" : request.getParameter("parentId");
		String name = request.getParameter("name");
		
		PageList pl = new PageList();		
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		pl.setObjectsPerPage(pageSize);
		pl.setPageNumber(currentPage);
		
		// get HospitalTypeList
		
		ExamProp parentHos = new ExamProp();
		parentHos.setType(Integer.valueOf(type)); 
		List<ExamProp> parentHospitalList = localExamPropValFacade.getPropListByType(parentHos); 
		request.setAttribute("hosTypeList", parentHospitalList);
		
		// get HospitalList by Type
		
		ExamHospital hospital = new ExamHospital();
		hospital.setPropId(Long.valueOf(parentId)); // set Hospital Type
		hospital.setName(name);
		localExamPropValFacade.getHospitalList(pl, hospital);
		
		request.setAttribute("Page", pl);
		request.setAttribute("name", name);
		request.setAttribute("type", parentId);
		return "SUCCESS";
	}

}
