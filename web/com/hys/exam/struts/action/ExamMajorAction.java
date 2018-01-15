package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamMajorOrder;
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
 * @author Han
 *
 */
public class ExamMajorAction extends BaseAction {
	
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
		String method = RequestUtil.getParameter(request, "mode");
	
		if (method.equals("save")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("batchdel")){
			return batchdel(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}

	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String majorName = request.getParameter("major") == null ? "" : request.getParameter("major");
		String hospital = request.getParameter("hospital") == null ? "" : request.getParameter("hospital");
		String order = request.getParameter("order") == null ? "" : request.getParameter("order");
		String classid = request.getParameter("cls") == null ? "0" : request.getParameter("cls");
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");

		ExamMajorOrder major = new ExamMajorOrder();
		major.setClassId(Integer.valueOf(classid));
		major.setId(Long.valueOf(id));
		major.setHospital(hospital);
		major.setMajor(majorName);
		major.setOrderName(order);

		boolean flag = false;
		if (major.getId() == 0L)
			flag = localExamPropValFacade.addMajorOrder(major);
		else
			flag = localExamPropValFacade.updateMajorOrder(major);
			
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");

		ExamMajorOrder major = new ExamMajorOrder();
		major.setId(Long.valueOf(id));

		boolean flag = localExamPropValFacade.deleteMajorOrder(major);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	protected String batchdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids");

		ExamMajorOrder major = new ExamMajorOrder();
		String[] idss = ids.split(",");
		Integer dels = 0;
		for (int i = 0; i < idss.length; i++){
			major.setId(Long.valueOf(idss[i].trim()));
			boolean flag = localExamPropValFacade.deleteMajorOrder(major);
			if (flag) dels++;
		}
		StrutsUtil.renderText(response, dels.toString());
		return null;
	}
	
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String smajor = request.getParameter("smajor") == null ? "" : request.getParameter("smajor");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		String sclassid = request.getParameter("sclassid") == null ? "0" : request.getParameter("sclassid");
		ExamMajorOrder major = new ExamMajorOrder();
		{
			major.setMajor(smajor);
			major.setClassId(Integer.valueOf(sclassid));
			major.setHospital(sname);
			List<ExamMajorOrder> list = localExamPropValFacade.getMajorOrderList(major);
			
			ExamHospital host = new ExamHospital();
			List<ExamHospital> hospitalList = localExamPropValFacade.getHospitalList(host);
			
			request.setAttribute("propList", list);
			request.setAttribute("hospList", hospitalList);
			request.setAttribute("smajor", smajor);
			request.setAttribute("sname", sname);
			request.setAttribute("sclassid", sclassid);
		}		
		return "SUCCESS";
	}

}
