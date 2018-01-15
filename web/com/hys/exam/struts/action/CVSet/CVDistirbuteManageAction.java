package com.hys.exam.struts.action.CVSet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.FutureTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetAuthorCheck;
import com.hys.exam.model.CVSetAuthorQuery;
import com.hys.exam.model.CVSetAuthorization;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.system.SystemSiteVO;
import com.hys.exam.service.local.CVSetAuthorizationManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.struts.form.CVS_Form;
import com.hys.exam.utils.FilesUtils;
import com.hys.framework.web.action.BaseAction;

public class CVDistirbuteManageAction extends BaseAction {

	private CVSetManage localCVSetManage;
	
	private SystemSiteManage localSystemSiteManage;
	
	private ExamPropValFacade localExamPropValFacade;

	private Object localUserImageManage;	
	
	private CVSetAuthorizationManage cVSetAuthorizationManage;
	
	private SystemSiteManage systemSiteManage;

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	public SystemSiteManage getLocalSystemSiteManage() {
		return localSystemSiteManage;
	}

	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		String mode = RequestUtil.getParameter(request, "method");	
		Long flag = ParamUtils.getLongParameter(request, "flag", 0L);
		
		if(mode.equals("dist")){
			return dist(mapping, form, request, response);
		}else if(mode.equals("save")){
			return save(mapping, form, request, response);
		}else if(mode.equals("del")){
			return del(mapping, form, request, response);
		}else if(mode.equals("preView")){
			return preView(mapping,form,request,response);
		}else if(mode.equals("region1list")){
			return region1list(mapping,form,request,response);
		}else if(mode.equals("addAuthorizationView")){
			return addAuthorizationView(mapping,form,request,response);
		}else if(mode.equals("saveAuthorization")){
			return saveAuthorization(mapping,form,request,response);
		}else if(mode.equals("authorList")){
			return authorList(mapping,form,request,response);
		}else if(mode.equals("releaseList")){
			return releaseList(mapping,form,request,response,flag);
		}else if(mode.equals("alreadyReleaseList")){
			return releaseList(mapping,form,request,response,flag);
		}else if(mode.equals("checkAuthorization")){
			return checkAuthorization(mapping,form,request,response);
		}else if(mode.equals("authorDel")){
			return deleteAuthor(mapping,form,request,response);
		}else if(mode.equals("cvSetReleaseAndOffline")){
			return cvSetReleaseAndOffline(mapping,form,request,response,flag);
		}else{
			return list(mapping, form, request, response);
		}
	}
	private String preView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "cvId", 0L);
		CVSet view;	
		view = localCVSetManage.getCVSetById(id);
		List<SystemSite> list = new ArrayList<SystemSite>();
		//授权站点名称
		String sqName = "";
		//授权站点id
		String sqIds = "";
		//拼接项目授权站点
		if(view!=null && view.getSiteList()!=null){
			list = view.getSiteList();
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					sqName = sqName + list.get(i).getDomainName()+";";
					sqIds = sqIds + list.get(i).getId()+";";
				}
			}
		}
		SystemSite querySite = new SystemSite();
		List<SystemSite> systemSite = localSystemSiteManage.getListByItem(querySite);
		//取得职称列表
		ExamProp prop = new ExamProp();		
		//取得省列表
		prop.setType(Integer.valueOf(20));
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
		request.setAttribute("View", view);
		request.setAttribute("siteList", systemSite);
		request.setAttribute("region1list", region1list);
		request.setAttribute("sqName", sqName);
		request.setAttribute("sqIds", sqIds);
		return "preView";
	}

	private String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		List<CVSet> list = new ArrayList<CVSet>();
		String CVSetCourseIds = ParamUtils.getParameter(request, "groupIds");
		String CVSetCourseName = request.getParameter("groupNames"); 
		String CVSetName = request.getParameter("name");
		String CVSetMaker = request.getParameter("maker");
		Integer forma = request.getParameter("forma") == null ? 0 : Integer.valueOf(request.getParameter("forma")); 
		String CVSetLevel = request.getParameter("level");
		String CVSetScore = request.getParameter("score");	
		String CVSetSign = request.getParameter("sign");
		String propIds = request.getParameter("propIds");		
		String CVSetSiteUrl = request.getParameter("sitelist");
		
		PropUnit propUnit = new PropUnit();
		if(CVSetCourseIds !=null && !CVSetCourseIds.equals("")){
			propUnit.setId(Long.valueOf(CVSetCourseIds));
		}
		
		List<SystemSite> siteList = new ArrayList<SystemSite>();
		SystemSite systemSite = null;
		if(CVSetSiteUrl != null && !"-1".equals(CVSetSiteUrl)){
			systemSite = new SystemSite();
			systemSite.setId(Long.valueOf(CVSetSiteUrl));
			siteList.add(systemSite);
		}
		
		CVSet queryCVSet = new CVSet();
		try{
			if(propUnit.getId() != null && !propUnit.getId().equals("")){
				List<PropUnit> propUnitList = new ArrayList<PropUnit>();
				propUnitList.add(propUnit);
				//项目授权根据学科查询的条件
				queryCVSet.setCourseList(propUnitList);
			}
			queryCVSet.setMaker(CVSetMaker);
			queryCVSet.setName(CVSetName);
			queryCVSet.setForma(forma);
			if(CVSetLevel != null && !CVSetLevel.equals("-1")){
				queryCVSet.setLevel(Integer.valueOf(CVSetLevel));	
			}
			if(CVSetScore != null && !CVSetScore.equals("")){
				queryCVSet.setScore(Double.valueOf(CVSetScore));	
			}
			if(CVSetSign != null && !CVSetSign.equals("-1")){
				queryCVSet.setSign(CVSetSign);
			}
			queryCVSet.setSiteList(siteList);
				
			
			//授权区域查询
			if(propIds != null && !propIds.equals("")){
				queryCVSet.setPropIds(propIds);
			}
		}
		catch(Exception e){e.printStackTrace();}
		//项目授权中显示的项目列表学科是重复的，但是dao层被另一个地方引用着，所以再复制一份，修改
		list = localCVSetManage.getCVSetListDuplicateRemove(queryCVSet);
		
		if(propIds!=null&&!propIds.equals("")){
			String propNames = "";
			List<PropUnit> propList = localCVSetManage.getAreaForAuthor(propIds);
			if(propList != null){
				for (PropUnit propU : propList) {
					propNames += propU.getName()+ ",";
				}
				request.setAttribute("propNames", propNames!=null?propNames.substring(0,propNames.length()-1):"");
			}
		}
		for(int i=0;i<list.size();i++){
			String sign1=list.get(i).getSign();
			if(!StringUtil.checkNull(sign1)){
				if(sign1.charAt(sign1.length() - 1) == ',')
				{
					sign1 = sign1.substring(0, sign1.length()-1);
					list.get(i).setSign(sign1);
				}
			}
			/*if(list.get(i).getEnd_date()!=null){
				Date d = list.get(i).getEnd_date();
				long millionSeconds = d.getTime();//毫秒
				list.get(i).setEndTime(millionSeconds);
				Date nowDate = new Date();
				if(nowDate.after(list.get(i).getEnd_date())){
					CVSet svset=list.get(i);
					svset.setStatus(new Long(6));
					localCVSetManage.updateCVSet(svset);
				}
			}*/
		}
		
		//取得站点信息
		List<SystemSiteVO> siteArr = this.systemSiteManage.getSystemSiteList();
		
		request.setAttribute("groupIds", CVSetCourseIds);
		request.setAttribute("groupNames", CVSetCourseName);
		request.setAttribute("maker", queryCVSet.getMaker());
		request.setAttribute("name", queryCVSet.getName());
		request.setAttribute("forma", queryCVSet.getForma());
		request.setAttribute("level", queryCVSet.getLevel());
		request.setAttribute("score", queryCVSet.getScore());
		request.setAttribute("sign", queryCVSet.getSign());
		request.setAttribute("propIds", propIds);
		request.setAttribute("siteArr", siteArr);
		request.setAttribute("siteP", CVSetSiteUrl);
		request.setAttribute("CVSet", list);
		return "list";
	}
	private String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long del_id = ParamUtils.getLongParameter(request, "id", 0L);	
		CVSet del_CVS = new CVSet();
		del_CVS.setId(del_id);
		boolean flag = localCVSetManage.deleteCVSet(del_CVS);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}

	private String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	Long id = ParamUtils.getLongParameter(request, "cvId", 0L);
	String propIds = request.getParameter("propIds");
		if(id == null)
		{
			request.setAttribute("msg", "fail");
		}
		else
		{
			CVSet currentCV = localCVSetManage.getCVSetById(id);
			String siteIds = request.getParameter("siteIds");
			String pId = request.getParameter("provinceId");
			String cId = request.getParameter("cityId");
			String serial = request.getParameter("serialNumber");
			String level = request.getParameter("level");
			String score = request.getParameter("score");
			String sign = request.getParameter("signStr");
			String cost = request.getParameter("cost");
			String breakDays = request.getParameter("break_days");
			String sdate = request.getParameter("start_date_submit") == null ?
					request.getParameter("start_date") : request.getParameter("start_date_submit");
			String edate = request.getParameter("end_date_submit") == null ? 
					request.getParameter("end_date") : request.getParameter("end_date_submit");
			Integer costType = request.getParameter("costType") == null ?
					0 : Integer.parseInt(request.getParameter("costType"));
			
			//地域存储
			if(!StringUtil.checkNull(propIds)){
				List<PropUnit> tempList = new ArrayList<PropUnit>();
				String[] IDS = propIds.split(",");
				Long[] PropIDS = new Long[IDS.length];
				for(int i=0;i<IDS.length;i++) PropIDS[i] = Long.valueOf(IDS[i].trim());
				PropUnit[] tempProp = new PropUnit[IDS.length];
				for(int i=0;i<IDS.length;i++){
					tempProp[i] = new PropUnit();
					tempProp[i].setId(PropIDS[i]);
					tempList.add(tempProp[i]);
				}
				currentCV.setCourseList(tempList);
			}
			if(pId != null && !pId.equals(""))
			{
				currentCV.setProvinceId(Long.valueOf(pId));	
			}
			if(cId != null && !cId.equals(""))
			{
				currentCV.setCityId(Long.valueOf(cId));
			}
			/*if(serial != null && !serial.equals(""))
			{
				currentCV.setSerial_number(serial);
			}*/
			currentCV.setSerial_number(serial);
			if(level != null && !level.equals(""))
			{
				currentCV.setLevel(Integer.valueOf(level));
				if(Integer.valueOf(level) == 6){
					currentCV.setScore(Double.valueOf(0));	
				}
			}
			if(score != null && !score.equals(""))
			{
				currentCV.setScore(Double.valueOf(score));	
			}
			
			currentCV.setSign(sign);
			
			if(cost != null && !cost.equals(""))
			{
				currentCV.setCost(Double.valueOf(cost));	
			}
			if(breakDays != null && !breakDays.equals(""))
			{
				currentCV.setBreak_days(Long.parseLong(breakDays));
			}
			if(sdate != null && !sdate.equals(""))
			{
				Date startDate = DateUtils(sdate);
				currentCV.setStart_date(startDate);
			}
			if(edate != null && !edate.equals(""))
			{
				Date endDate = DateUtils(edate);
				currentCV.setEnd_date(endDate);
				Date date = new Date();
				
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//				long millionSeconds = endDate.getTime();//传入毫秒
//				long xt = date.getTime() ;//系统毫秒
				if(date.after(endDate)){
					currentCV.setStatus((long) 6);//已过期
				}else{
					currentCV.setStatus((long) 5);//已发布
				}
			}
			for(CVSchedule schedule :  currentCV.getCVScheduleList())
			{
				if(schedule.getId() != null)
				{
					String sId = "item_"+schedule.getId().toString() + "_start";
					String eId = "item_"+schedule.getId().toString() + "_end";
					String scheduleStartDate = request.getParameter(sId);
					String scheduleEndDate = request.getParameter(eId);
					if(scheduleStartDate != null && !scheduleStartDate.equals(""))
					{
						schedule.setStart_date(DateUtils(scheduleStartDate));
					}else{
						sId = "item_"+schedule.getId().toString() + "_start_submit";
						scheduleStartDate = request.getParameter(sId);
						schedule.setStart_date(DateUtils(scheduleStartDate));
					}
					if(scheduleEndDate != null && !scheduleEndDate.equals(""))
					{
						schedule.setEnd_date(DateUtils(scheduleEndDate));
					}else{
						eId = "item_"+schedule.getId().toString() + "_end_submit";
						scheduleEndDate = request.getParameter(eId);
						schedule.setEnd_date(DateUtils(scheduleEndDate));
					}
				}				
			}
			
			currentCV.setCost_type(costType);
			List<Object> saveParams = new ArrayList();
			saveParams.add(currentCV);
			saveParams.add(siteIds);
			int result = localCVSetManage.updateDistribute(saveParams);
/*			if(result > 0 )
			{
				request.setAttribute("msg", "success");
			}
			else
			{
				request.setAttribute("msg", "fail");
			}
*/			
		}
		response.sendRedirect("CVDistribute.do?method=list");
		return null;
	}
	
	
	//新授权------授权信息列表----taoLiang
	private String authorList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		List<CVSetAuthorQuery> list = new ArrayList<CVSetAuthorQuery>();
		
		String CVSetCourseIds = ParamUtils.getParameter(request, "groupIds");
		String CVSetCourseName = request.getParameter("groupNames"); 
		String CVSetName = request.getParameter("name");
		String CVSetMaker = request.getParameter("maker");
		Integer forma = request.getParameter("forma") == null ? 0 : Integer.valueOf(request.getParameter("forma"));
		String authorStatus = request.getParameter("authorStatus");
		String CVSetLevel = request.getParameter("level");
		String CVSetScore = request.getParameter("score");	
		String CVSetSign = request.getParameter("sign");
		String propIds = request.getParameter("propIds");		
		String CVSetSiteUrl = request.getParameter("sitelist");
		
		PropUnit propUnit = new PropUnit();
		if(CVSetCourseIds !=null && !CVSetCourseIds.equals("")){
			propUnit.setId(Long.valueOf(CVSetCourseIds));
		}
		
		List<SystemSite> siteList = new ArrayList<SystemSite>();
		SystemSite systemSite = null;
		if(CVSetSiteUrl != null && !"-1".equals(CVSetSiteUrl)){
			systemSite = new SystemSite();
			systemSite.setId(Long.valueOf(CVSetSiteUrl));
			siteList.add(systemSite);
		}
		
		CVSet queryCVSet = new CVSet();
		try{
			if(propUnit.getId() != null && !propUnit.getId().equals("")){
				List<PropUnit> propUnitList = new ArrayList<PropUnit>();
				propUnitList.add(propUnit);
				//项目授权根据学科查询的条件
				queryCVSet.setCourseList(propUnitList);
			}
			queryCVSet.setMaker(CVSetMaker);
			queryCVSet.setName(CVSetName);
			queryCVSet.setForma(forma);
			if(authorStatus != null && !authorStatus.equals("-1")){
				queryCVSet.setAuthorStatus(Integer.valueOf(authorStatus));	
			}
			if(CVSetLevel != null && !CVSetLevel.equals("-1")){
				queryCVSet.setLevel(Integer.valueOf(CVSetLevel));	
			}
			if(CVSetScore != null && !CVSetScore.equals("")){
				queryCVSet.setScore(Double.valueOf(CVSetScore));	
			}
			if(CVSetSign != null && !CVSetSign.equals("-1")){
				queryCVSet.setSign(CVSetSign);
			}
			queryCVSet.setSiteList(siteList);
				
			
			//授权区域查询
			if(propIds != null && !propIds.equals("")){
				queryCVSet.setPropIds(propIds);
			}
		}
		catch(Exception e){e.printStackTrace();}

		list = cVSetAuthorizationManage.getCVSetListForAuthor(queryCVSet);
		
		request.setAttribute("groupIds", CVSetCourseIds);
		request.setAttribute("groupNames", CVSetCourseName);
		request.setAttribute("maker", queryCVSet.getMaker());
		request.setAttribute("deli_man", queryCVSet.getDeli_man());
		request.setAttribute("name", queryCVSet.getName());
		request.setAttribute("status", queryCVSet.getStatus());
		request.setAttribute("propIds", propIds);
		if(propIds!=null&&!propIds.equals("")){
			String propNames = "";
			List<PropUnit> propList = localCVSetManage.getAreaForAuthor(propIds);
			if(propList != null){
				for (PropUnit propU : propList) {
					propNames += propU.getName()+ ",";
				}
				request.setAttribute("propNames", propNames!=null?propNames.substring(0,propNames.length()-1):"");
			}
		}
		for(int i=0;i<list.size();i++){
			String sign1=list.get(i).getSign();
			if(!StringUtil.checkNull(sign1)){
				if(sign1.charAt(sign1.length() - 1) == ',')
				{
					sign1 = sign1.substring(0, sign1.length()-1);
					list.get(i).setSign(sign1);
				}
			}
			/*if(list.get(i).getEndDate() != null){
				Date d = list.get(i).getEndDate();
				long millionSeconds = d.getTime();//毫秒
				list.get(i).setEndTime(millionSeconds);
				Date nowDate = new Date();
				if(nowDate.after(list.get(i).getEnd_date())){
					CVSet svset=list.get(i);
					svset.setStatus(new Long(6));
					localCVSetManage.updateCVSet(svset);
				}
			}*/
		}
		
		//取得站点信息
		List<SystemSiteVO> siteArr = this.systemSiteManage.getSystemSiteList();
		
		request.setAttribute("siteArr", siteArr);
		request.setAttribute("sign", queryCVSet.getSign());
		request.setAttribute("level", queryCVSet.getLevel());
		request.setAttribute("score", queryCVSet.getScore());
		request.setAttribute("siteP", CVSetSiteUrl);
		request.setAttribute("CVSet", list);
		return "authorList";
	}
	
	//*************************新的授权保存----taoLiang***************************************
	private String saveAuthorization(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);
	Long authorizationId = ParamUtils.getLongParameter(request, "authorizationId", 0L);
	String propIds = request.getParameter("propIds");
		if(cvSetId == null){
			request.setAttribute("msg", "fail");
		}
		else{
			CVSet currentCVS = cVSetAuthorizationManage.getCVSetForAddAuthorization(cvSetId);
			String siteIds = request.getParameter("siteIds");
			String serial = request.getParameter("serialNumber");
			String level = request.getParameter("level");
			String score = request.getParameter("score");
			String sign = request.getParameter("signStr");
			String cost = request.getParameter("cost");
			String breakDays = request.getParameter("break_days");
			String cvSetRegistrationStop = request.getParameter("cvSetRegistrationStop");
			
			String sdate = request.getParameter("start_date_submit") == null ?
					request.getParameter("start_date") : request.getParameter("start_date_submit");
			String edate = request.getParameter("end_date_submit") == null ? 
					request.getParameter("end_date") : request.getParameter("end_date_submit");
			Integer costType = request.getParameter("costType") == null ?
					0 : Integer.parseInt(request.getParameter("costType"));
			
			//地域存储
			if(!StringUtil.checkNull(propIds)){
				List<PropUnit> tempList = new ArrayList<PropUnit>();
				String[] IDS = propIds.split(",");
				Long[] PropIDS = new Long[IDS.length];
				for(int i=0;i<IDS.length;i++) PropIDS[i] = Long.valueOf(IDS[i].trim());
				PropUnit[] tempProp = new PropUnit[IDS.length];
				for(int i=0;i<IDS.length;i++){
					tempProp[i] = new PropUnit();
					tempProp[i].setId(PropIDS[i]);
					tempList.add(tempProp[i]);
				}
				currentCVS.setCourseList(tempList);
			}
			
			CVSetAuthorization CVSAuthor = new CVSetAuthorization();
			if(authorizationId > 0L){
				CVSAuthor.setId(authorizationId);
			}
			CVSAuthor.setCvSetSerialNumber(serial);
			if(level != null && !level.equals("")){
				CVSAuthor.setCvSetLevel(Integer.valueOf(level));
				if(Integer.valueOf(level) == 6){
					CVSAuthor.setCvSetScore(Double.valueOf(0));	
				}
			}
			if(score != null && !score.equals("")){
				CVSAuthor.setCvSetScore(Double.valueOf(score));	
			}
			
			CVSAuthor.setCvSetSign(sign);
			
			if(cost != null && !cost.equals("")){
				CVSAuthor.setCvSetCost(Double.valueOf(cost));	
			}
			if(breakDays != null && !breakDays.equals("")){
				CVSAuthor.setCvSetBreakDays(Integer.valueOf(breakDays));
			}
			if(cvSetRegistrationStop != null && !cvSetRegistrationStop.equals("")){
				CVSAuthor.setCvSetRegistrationStop(Integer.valueOf(cvSetRegistrationStop));
			}
			if(sdate != null && !sdate.equals("")){
				java.sql.Date startDate =  new java.sql.Date(
													new SimpleDateFormat("yyyy-MM-dd").parse(sdate).getTime()
												);
				CVSAuthor.setCvSetStartDate(startDate);
			}
			if(edate != null && !edate.equals("")){
				java.sql.Date endDate =  new java.sql.Date(
													new SimpleDateFormat("yyyy-MM-dd").parse(edate).getTime()
												);
				CVSAuthor.setCvSetEndDate(endDate);
			}
			for(CVSchedule schedule :  currentCVS.getCVScheduleList()){
				if(schedule.getId() != null){
					String sId = "item_"+schedule.getId().toString() + "_start";
					String eId = "item_"+schedule.getId().toString() + "_end";
					String scheduleStartDate = request.getParameter(sId);
					String scheduleEndDate = request.getParameter(eId);
					if(scheduleStartDate != null && !scheduleStartDate.equals("")){
						schedule.setStart_date(DateUtils(scheduleStartDate+" 00:00:00"));
					}else{
						sId = "item_"+schedule.getId().toString() + "_start_submit";
						scheduleStartDate = request.getParameter(sId);
						schedule.setStart_date(DateUtils(scheduleStartDate+" 00:00:00"));
					}
					if(scheduleEndDate != null && !scheduleEndDate.equals("")){
						schedule.setEnd_date(DateUtils(scheduleEndDate+" 00:00:00"));
					}else{
						eId = "item_"+schedule.getId().toString() + "_end_submit";
						scheduleEndDate = request.getParameter(eId);
						schedule.setEnd_date(DateUtils(scheduleEndDate+" 00:00:00"));
					}
				}				
			}
			
			CVSAuthor.setCvSetCostType(costType);
			currentCVS.setcVSetAuthorization(CVSAuthor);
			
			List<Object> saveParams = new ArrayList<Object>();
			saveParams.add(currentCVS);
			saveParams.add(siteIds);
			
			if(null != CVSAuthor.getId() && CVSAuthor.getId() > 0L){
				cVSetAuthorizationManage.updateCVSetAuthorization(saveParams);
				response.sendRedirect("CVDistribute.do?method=authorList");
			}else{
				cVSetAuthorizationManage.saveCVSetAuthorization(saveParams);
				response.sendRedirect("CVDistribute.do?method=list");
			}
			
		}
		return null;
	}
	
	
	//授权之前授权地区等元素检查
	public String checkAuthorization(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);//项目ID
		
		List<ExamProp> examPropList = null;
		CVSetAuthorCheck check = cVSetAuthorizationManage.getAuthorBeforeCheck(cvSetId);
		
		JSONObject result = new JSONObject();
		
		result.put("flag", check.getNumericConstants());
		result.put("list", check.getExamPropList());
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
	
	//授权---项目列表授权
	private String addAuthorizationView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);//项目ID
		CVSet view = null;	
		view = cVSetAuthorizationManage.getCVSetForAddAuthorization(cvSetId);
		
		SystemSite querySite = new SystemSite();
		List<SystemSite> systemSite = localSystemSiteManage.getListByItem(querySite);
		
		//获取该项目的面授人数统计---当项目类型为面授时
		int faceCount = 0;
		if(view.getForma() != null && view.getForma() == 3){
			faceCount = cVSetAuthorizationManage.getFaceteachCount(cvSetId);
		}
		request.setAttribute("View", view);
		request.setAttribute("siteList", systemSite);
		request.setAttribute("faceCount",faceCount);
		return "distribute";
	}

	//授权--------列表页点击授权改造：{taoLiang}
	private String dist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);//项目ID
		Long authorizationId = ParamUtils.getLongParameter(request, "authorizationId", 0L);//授权ID
		Long flag = ParamUtils.getLongParameter(request, "flag", 0L);//项目ID
		
		CVSet view = null;	
		view = cVSetAuthorizationManage.getCVSetForAuthorization(cvSetId, authorizationId);
		
		List<PropUnit> courseList = new ArrayList<PropUnit>();
		String propIds = "";
		String propNames = "";
		if(view != null)
		{
			courseList = view.getCourseList();
			for (PropUnit prop:courseList) {
			propIds += prop.getId() + ",";
			propNames += prop.getName()+ ",";
			}
		}
		SystemSite querySite = new SystemSite();
		List<SystemSite> systemSite = localSystemSiteManage.getListByItem(querySite);
		
		//取得职称列表
		//取得省列表
//		prop.setType(Integer.valueOf(20));
//		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
		//获取该项目的面授人数统计
		int faceCount = 0;
		if(view.getForma() != null && view.getForma() == 3){
			faceCount = cVSetAuthorizationManage.getFaceteachCount(cvSetId);
		}
		request.setAttribute("View", view);
		request.setAttribute("siteList", systemSite);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames",propNames);
		request.setAttribute("faceCount",faceCount);
		request.setAttribute("authorizationId",authorizationId);
		
		if(flag == 1){//进入查看已授权信息页面
			return "distributeView";
		}
		return "distribute";
	}

	//---授权-------------项目发布列表{taoLiang}
	private String releaseList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,Long flag) throws ParseException
	{
		List<CVSet> list = new ArrayList<CVSet>();
		String CVSetCourseIds = ParamUtils.getParameter(request, "groupIds");
		String CVSetCourseName = request.getParameter("groupNames"); 
		String CVSetName = request.getParameter("name");
		String CVSetMaker = request.getParameter("maker");
		Integer forma = request.getParameter("forma") == null ? 0 : Integer.valueOf(request.getParameter("forma")); 
		Long status = ParamUtils.getLongParameter(request, "status", 0L);
		String orgName = request.getParameter("orgName");
		String CVSetLevel = request.getParameter("level");
		String CVSetScore = request.getParameter("score");	
		String CVSetSign = request.getParameter("sign");
		String propIds = request.getParameter("propIds");		
		//String CVSetSiteUrl = request.getParameter("sitelist");
		
		PropUnit propUnit = new PropUnit();
		if(CVSetCourseIds !=null && !CVSetCourseIds.equals("")){
			propUnit.setId(Long.valueOf(CVSetCourseIds));
		}
		
		/*List<SystemSite> siteList = new ArrayList<SystemSite>();
		SystemSite systemSite = null;
		if(CVSetSiteUrl != null && !"-1".equals(CVSetSiteUrl)){
			systemSite = new SystemSite();
			systemSite.setId(Long.valueOf(CVSetSiteUrl));
			siteList.add(systemSite);
		}*/
		
		CVSet queryCVSet = new CVSet();
		try{
			if(propUnit.getId() != null && !propUnit.getId().equals("")){
				List<PropUnit> propUnitList = new ArrayList<PropUnit>();
				propUnitList.add(propUnit);
				//项目授权根据学科查询的条件
				queryCVSet.setCourseList(propUnitList);
			}
			queryCVSet.setMaker(CVSetMaker);
			queryCVSet.setName(CVSetName);
			queryCVSet.setForma(forma);
			queryCVSet.setStatus(status);
			queryCVSet.setOrgName(orgName);
			
			if(CVSetLevel != null && !CVSetLevel.equals("-1")){
				queryCVSet.setLevel(Integer.valueOf(CVSetLevel));	
			}
			if(CVSetScore != null && !CVSetScore.equals("")){
				queryCVSet.setScore(Double.valueOf(CVSetScore));	
			}
			if(CVSetSign != null && !CVSetSign.equals("-1")){
				queryCVSet.setSign(CVSetSign);
			}
			//queryCVSet.setSiteList(siteList);
				
			
			//授权区域查询
			if(propIds != null && !propIds.equals("")){
				queryCVSet.setPropIds(propIds);
			}
		}
		catch(Exception e){e.printStackTrace();}
		
		list = cVSetAuthorizationManage.getCVSetListForRelease(queryCVSet, flag);
		
		if(propIds!=null&&!propIds.equals("")){
			String propNames = "";
			List<PropUnit> propList = localCVSetManage.getAreaForAuthor(propIds);
			if(propList != null){
				for (PropUnit propU : propList) {
					propNames += propU.getName()+ ",";
				}
				request.setAttribute("propNames", propNames!=null?propNames.substring(0,propNames.length()-1):"");
			}
		}
		for(int i=0;i<list.size();i++){
			String sign1=list.get(i).getSign();
			if(!StringUtil.checkNull(sign1)){
				if(sign1.charAt(sign1.length() - 1) == ',')
				{
					sign1 = sign1.substring(0, sign1.length()-1);
					list.get(i).setSign(sign1);
				}
			}
			/*if(list.get(i).getEnd_date()!=null){
				Date d = list.get(i).getEnd_date();
				long millionSeconds = d.getTime();//毫秒
				list.get(i).setEndTime(millionSeconds);
				Date nowDate = new Date();
				if(nowDate.after(list.get(i).getEnd_date())){
					CVSet svset=list.get(i);
					svset.setStatus(new Long(6));
					localCVSetManage.updateCVSet(svset);
				}
			}*/
		}
		
		//取得站点信息
		List<SystemSiteVO> siteArr = this.systemSiteManage.getSystemSiteList();
		
		request.setAttribute("groupIds", CVSetCourseIds);
		request.setAttribute("groupNames", CVSetCourseName);
		request.setAttribute("maker", queryCVSet.getMaker());
		request.setAttribute("name", queryCVSet.getName());
		request.setAttribute("forma", queryCVSet.getForma());
		request.setAttribute("level", queryCVSet.getLevel());
		request.setAttribute("score", queryCVSet.getScore());
		request.setAttribute("sign", queryCVSet.getSign());
		request.setAttribute("propIds", propIds);
		request.setAttribute("siteArr", siteArr);
		//request.setAttribute("siteP", CVSetSiteUrl);
		request.setAttribute("CVSet", list);
		if(flag == 1L){
			return "alreadyReleaseList";
		}
		return "releaseList";
	}
	
	//新授权------授权信息删除
	public String deleteAuthor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		JSONObject result = new JSONObject();
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);//项目ID
		Long authorizationId = ParamUtils.getLongParameter(request, "authorizationId", 0L);//项目ID
		
		int count = cVSetAuthorizationManage.deleteAuthor(cvSetId, authorizationId);
		
		if(count > 0){
			result.put("msg", "success");
		}else{
			result.put("msg", "fail");
		}
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
	
	//新授权------项目发布和下线
		//String数组转化为Long数组
		public Long [] switchStringtoLongArray(String [] strArr){
			if(null == strArr || strArr.length==0)
				return new Long[0];
			Long [] longArr = new Long[strArr.length];
			for(int i=0; i< strArr.length; i++){
				if(StringUtils.isNotBlank(strArr[i])){
					longArr[i]=Long.parseLong(strArr[i]);
				}
			}
			return longArr;		
		}
		public String cvSetReleaseAndOffline(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response, Long flag){
			
			JSONObject result = new JSONObject();
			Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);//项目ID
			String _cvSetIds = ParamUtils.getParameter(request, "cvSetIds");
			if(cvSetId <= 0 && "".equals(_cvSetIds )){
				result.put("msg", "fail");
				return null;
			}
			
			List<CVSet> msgIds = new ArrayList<CVSet>();
			Long [] cvSetIds = null;
			int count = 0;
			if(cvSetId > 0){//当单个发布时
				cvSetIds = new Long []{cvSetId};
				//因发布的项目在发送消息时限制为已下线再次上线的项目，所以此处做过滤操作
				msgIds = cVSetAuthorizationManage.getCVSetCheckForRelease(cvSetId.toString(),flag);
				
				
			}else{
				_cvSetIds = _cvSetIds.substring(0, _cvSetIds.length()-1);
				String [] ids = _cvSetIds.split(",");
				cvSetIds = this.switchStringtoLongArray(ids);
				//因发布的项目在发送消息时限制为已下线再次上线的项目，所以此处做过滤操作
				msgIds = cVSetAuthorizationManage.getCVSetCheckForRelease(_cvSetIds,flag);
				
			}
			
			count = cVSetAuthorizationManage.updateCVSetForRelease(cvSetIds, flag);
			
			if(count > 0){
				result.put("msg", "success");
				/*使用异步的方式进行系统消息推送数据处理*/
				CVSetAuthorSendMsgThread initCvLiveRecordThread = new CVSetAuthorSendMsgThread(msgIds, cVSetAuthorizationManage, flag);
				  new Thread(new FutureTask<String>(initCvLiveRecordThread)).start();
				
			}else{
				result.put("msg", "fail");
			}
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
	
	
	
	
	/**
	 * 
	 * (格式化日期)
	 * 方法名：DateUtils
	 * 创建人：程宏业
	 * 时间：2017-2-26-下午1:31:08 
	 * 手机:15210211487
	 * @param str
	 * @return Date
	 * @exception 
	 * @since  1.0.0
	 */
	public Date DateUtils (String str){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try {
			return formatter.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
		return null;
	} 
	
	private String region1list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//取得职称列表
		ExamProp prop = new ExamProp();
		//取得省列表
		prop.setType(Integer.valueOf(20));
		List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);
		JSONObject result = new JSONObject();
		result.put("list", list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	public CVSetAuthorizationManage getcVSetAuthorizationManage() {
		return cVSetAuthorizationManage;
	}

	public void setcVSetAuthorizationManage(
			CVSetAuthorizationManage cVSetAuthorizationManage) {
		this.cVSetAuthorizationManage = cVSetAuthorizationManage;
	}

	public SystemSiteManage getSystemSiteManage() {
		return systemSiteManage;
	}

	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}
}
