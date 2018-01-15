package com.hys.exam.struts.action.ncme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.model.NcmeCreditType;
import com.hys.exam.service.local.NcmeCourseCreditManage;
import com.hys.exam.service.local.NcmeCreditTypeManage;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：修改授权课程信息
 * 
 * 作者：chenlaibin 2014-3-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseAuthorizeUpdateAction extends BaseAction {
	
	private NcmeCourseCreditManage ncmeCourseCreditManage;
	private NcmeCreditTypeManage ncmeCreditTypeManage;

	public void setNcmeCourseCreditManage(
			NcmeCourseCreditManage ncmeCourseCreditManage) {
		this.ncmeCourseCreditManage = ncmeCourseCreditManage;
	}
	
	public void setNcmeCreditTypeManage(NcmeCreditTypeManage ncmeCreditTypeManage) {
		this.ncmeCreditTypeManage = ncmeCreditTypeManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = ParamUtils.getParameter(request, "method");
		if(method.equals("toUpdate")){
			return this.toUpdate(mapping, form, request, response);
		}
		else if(method.equals("update")){
			return this.update(mapping, form, request, response);
		}else if(method.equals("updateDate")){
			return this.updateDate(mapping, form, request, response);
		}
		return null;
	}
	
	public String toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			String creditYear = ParamUtils.getParameter(request, "creditYear");
			Long courseId = ParamUtils.getLongParameter(request, "courseId", -1);
			////Long organId = ParamUtils.getLongParameter(request, "organId", -1);
			Long siteId = ParamUtils.getLongParameter(request, "siteId", -1);
			if(courseId >-1 && siteId >-1){
				NcmeCourseCredit credit = this.ncmeCourseCreditManage.getNcmeCourseCredit(creditYear, courseId, siteId);
				request.setAttribute("credit", credit);
			}
			List<NcmeCreditType> ncmeCreditTypeList = ncmeCreditTypeManage.getNcmeCreditTypeList();
			request.setAttribute("ncmeCreditTypeList", ncmeCreditTypeList);
			request.setAttribute("domainName", request.getParameter("domainName"));
			return "toUpdate";
	}
	
	public String updateDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Long siteId = ParamUtils.getLongParameter(request, "siteId", 0L);
		String creditYear = ParamUtils.getParameter(request, "creditYear");
		Long courseType = ParamUtils.getLongParameter(request, "courseType", 0L);
		Long courseId = ParamUtils.getLongParameter(request, "courseId", 0L);
		Date new_credit_date = ParamUtils.getDateParamenter(request, "new_credit_date", "yyyy-MM-dd HH:mm:ss");
		
		NcmeCourseCredit credit = new NcmeCourseCredit();
		credit.setSiteId(siteId);
		credit.setCreditYear(creditYear);
		credit.setCourseType(courseType.intValue());
		credit.setCourseId(courseId+"");
		credit.setCreditDate(new_credit_date);
		int row = this.ncmeCourseCreditManage.updateNcmeCourseCreditDate(credit);
		Utils.renderText(response, String.valueOf(row));
		return null;
	}
	
	public String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if (isTokenValid(request, true)) {
			String creditYear = ParamUtils.getParameter(request, "creditYear");
			String creditType = ParamUtils.getParameter(request, "creditType");
			Double creditNum = ParamUtils.getDoubleParameter(request, "creditNum", 0.0);
			Double creditHours = ParamUtils.getDoubleParameter(request, "creditHours", 0.0);
			String courseSerial = ParamUtils.getParameter(request, "courseSerial");
			Integer reStudyFlag  = ParamUtils.getIntParameter(request, "reStudyFlag", 0);
			String courseIds = ParamUtils.getParameter(request, "courseIds");
			Date startDate = ParamUtils.getDateParamenter(request, "startDate", "");
			Date endDate = ParamUtils.getDateParamenter(request, "endDate", "");

			//String[] organId = request.getParameterValues("organId");
			String siteId = ParamUtils.getParameter(request, "siteId");
			String[] courseIdList = courseIds.split(",");

			List<NcmeCourseCredit> list = new ArrayList<NcmeCourseCredit>();
			
			for (int j = 0; j < courseIdList.length; ++j) {
				if(StringUtils.isBlank(courseIdList[j])) continue;
				NcmeCourseCredit n = new NcmeCourseCredit();
				n.setCreditYear(creditYear);
				n.setCreditType(creditType);
				n.setCreditNum(creditNum);
				n.setCourseSerial(courseSerial);
				n.setReStudyFlag(reStudyFlag);
				n.setSiteId(new Long(siteId));
				n.setCourseId(courseIdList[j]);
				n.setStartDate(startDate);
				n.setEndDate(endDate);
				n.setCreditDate(new Date());
				n.setCreditHours(creditHours);
				list.add(n);
			}
			//保存
			ncmeCourseCreditManage.updateNcmeCourseCredit(list);
			
		} else {
			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}

}
