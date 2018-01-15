package com.hys.exam.struts.action.ncme;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.sessionfacade.local.NcmeCourseCreditFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：保存授权课程信息
 * 
 * 作者：陈明凯 2013-5-24
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseAuthorizeSaveAction extends BaseAction {
	private NcmeCourseCreditFacade ncmeCourseCreditFacade;

	public void setNcmeCourseCreditFacade(
			NcmeCourseCreditFacade ncmeCourseCreditFacade) {
		this.ncmeCourseCreditFacade = ncmeCourseCreditFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		if (isTokenValid(request, true)) {
			String creditYear = ParamUtils.getParameter(request, "creditYear");
			//Integer courseType = ParamUtils.getIntParameter(request, "courseType", -1);
			String _courseTypeIds = ParamUtils.getParameter(request, "courseTypeIds");
			String creditType = ParamUtils.getParameter(request, "creditType");
			Double creditNum = ParamUtils.getDoubleParameter(request, "creditNum", 0.0);
			Double creditHours = ParamUtils.getDoubleParameter(request, "creditHours", 0.0);
			//Integer evpValue = ParamUtils.getIntParameter(request, "evpValue", 0);
			String courseSerial = ParamUtils.getParameter(request, "courseSerial");
			String requestString = ParamUtils.getParameter(request, "request");
			Integer reStudyFlag  = ParamUtils.getIntParameter(request, "reStudyFlag", 0);
			String courseIds = ParamUtils.getParameter(request, "courseIds");
			Date startDate = ParamUtils.getDateParamenter(request, "startDate", "");
			Date endDate = ParamUtils.getDateParamenter(request, "endDate", "");

			////String[] organId = request.getParameterValues("organId");
			String [] siteId = request.getParameterValues("siteId");
			String[] courseIdList = courseIds.split(",");
			String [] courseTypeIds = _courseTypeIds.split(",");

			List<NcmeCourseCredit> list = new ArrayList<NcmeCourseCredit>();

			for(int k= 0; k<courseTypeIds.length; k++){
				String typeId = courseTypeIds[k];
				if(StringUtils.isBlank(typeId)) continue;
				for (int i = 0; i < siteId.length; ++i) {
					if(StringUtils.isBlank(siteId[i])) continue;
					for (int j = 0; j < courseIdList.length; ++j) {
						if(StringUtils.isBlank(courseIdList[j])) continue;
						NcmeCourseCredit n = new NcmeCourseCredit();
						n.setCreditYear(creditYear);
						n.setCourseType(Integer.parseInt(typeId));
						n.setCreditType(creditType);
						n.setCreditNum(creditNum);
						//n.setEvpValue(evpValue);
						n.setCourseSerial(courseSerial);
						n.setRequest(requestString);
						n.setReStudyFlag(reStudyFlag);
						n.setSiteId(new Long(siteId[i]));
						n.setCourseId(courseIdList[j]);
						n.setCreditDate(new Date());
						n.setStartDate(startDate);
						n.setEndDate(endDate);
						n.setCreditHours(creditHours);
						list.add(n);
					}
				}
			}
			//保存
			ncmeCourseCreditFacade.addNcmeCourseCreditList(list);
			
		} else {
			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}

}
