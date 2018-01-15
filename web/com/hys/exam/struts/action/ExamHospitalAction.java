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
public class ExamHospitalAction extends BaseAction {
	
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
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		String examiner = request.getParameter("examiner") == null ? "" : request.getParameter("examiner");
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String propId = request.getParameter("propid") == null ? "0" : request.getParameter("propid");
		String propName = request.getParameter("propName") == null ? "" : request.getParameter("propName");

		ExamHospital host = new ExamHospital();
		host.setPropId(Long.valueOf(type));
		host.setRegionId(Long.valueOf(propId));
		host.setId(Long.valueOf(id));
		host.setExaminer(examiner);
		host.setName(propName);
		
		boolean flag = false;
		if (host.getId() == 0L)
			flag = localExamPropValFacade.addHospital(host);
		else
			flag = localExamPropValFacade.updateHospital(host);
			
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

		ExamHospital host = new ExamHospital();
		host.setId(Long.valueOf(id));

		boolean flag = localExamPropValFacade.deleteHospital(host);
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

		ExamHospital host = new ExamHospital();
		String[] idss = ids.split(",");
		Integer dels = 0;
		for (int i = 0; i < idss.length; i++){
			host.setId(Long.valueOf(idss[i].trim()));
			boolean flag = localExamPropValFacade.deleteHospital(host);
			if (flag) dels++;
		}
		StrutsUtil.renderText(response, dels.toString());
		return null;
	}
	
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");

		ExamProp prop = new ExamProp();
		prop.setType(20);
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);

		ExamHospital host = new ExamHospital();
		{
			host.setPropId(Long.valueOf(type));
			host.setName(sname);
			List<ExamHospital> list = localExamPropValFacade.getHospitalList(host);
			
			request.setAttribute("type", type);
			request.setAttribute("propList", list);
			request.setAttribute("region1list", region1list);
			request.setAttribute("sname", sname);
		}		
		return "SUCCESS";
	}

}
