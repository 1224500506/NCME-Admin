package com.hys.exam.struts.action.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemAdminOrgan;
import com.hys.exam.model.system.SystemCardType;
import com.hys.exam.model.system.SystemCreditType;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.service.local.NcmeCourseCreditManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.struts.action.AppBaseAction;

/**
*
* <p>Description: 卡类型</p>
* @author chenlaibin
* @version 1.0 2013-12-14
*/
public class SystemCardTypeAction extends AppBaseAction{
	private SystemCardManage systemCardManage;	
	private NcmeCourseCreditManage courseCreditManage;
	
	private CVSetManage localCVSetManage;
	private SystemUserManage localSystemUserManage;
	private ExpertManageManage localExpertManageManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = ParamUtils.getParameter(request, "method");
		//String method = request.getParameter("method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("get")){
			return this.get(mapping, form, request, response);
		}else if(method.equals("view")){
			return this.view(mapping, form, request, response);
		}else if(method.equals("update")){
			return this.update(mapping, form, request, response);
		}else if(method.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else if(method.equals("save")){
			return this.save(mapping, form, request, response);
		}else if(method.equals("getStudyCourse")){
			return this.getStudyCourse(mapping, form, request, response);
		}else if(method.equals("getSystemOrgList")){
			return this.getSystemOrgList(mapping, form, request, response);
		}else if(method.equals("saveSystemPaycardOrgan")){
			return this.saveSystemPaycardOrgan(mapping, form, request, response);
		}else if(method.equals("saveSystemPaycardCourse")){
			return this.saveSystemPaycardCourse(mapping, form, request, response);
		}else if(method.equals("deleteCourseAuthorized")){
			return this.deleteCourseAuthorized(mapping, form, request, response);
		}else if(method.equals("deleteOrgAuthorized")){
			return this.deleteOrgAuthorized(mapping, form, request, response);
		}else if(method.equals("getUnCreditCourseList")){	
			return this.getUnCreditCourseList(mapping, form, request, response);
		}/*else if(method.equals("saveSystemPaycardCourseByCourseType")){
			return this.saveSystemPaycardCourseByCourseType(mapping, form, request, response);
		}*/
		else if(method.equals("batchCourseAuthorized")){
			return this.batchCourseAuthorized(mapping, form, request, response);
		}else if(method.equals("bindCvSet")){
			return this.bindCvSet(mapping, form, request, response);
		}else if(method.equals("unBindCvSet")){
			return this.unBindCvSet(mapping, form, request, response);
		}else if(method.equals("systemCardTypeCVlist")){
			return this.systemCardTypeCVlist(mapping, form, request, response);
		}else if(method.equals("selectCvBindCardType")){
			return this.selectCvBindCardType(mapping, form, request, response);
		}
		return null;
	}
	
	//YHQ，2017-03-29，学习卡类型要选择项目
	private String selectCvBindCardType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CVSet> list = new ArrayList<CVSet>();				
		String propIds = ParamUtils.getParameter(request, "propIds");
		String propNames = ParamUtils.getParameter(request, "propNames");
		String creater = request.getParameter("creater");
		String CVSetName = request.getParameter("CVSetName");
		Long CVSetStatus = 5L ;		
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0L);
		
		CVSet queryCVSet = new CVSet();	
		
		/******getting relation xueke***********/
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
			queryCVSet.setCourseList(tempList);
		}		
		if(!StringUtil.checkNull(creater)){
			queryCVSet.setMaker(creater);
		}
		if(!StringUtil.checkNull(CVSetName)){
			queryCVSet.setName(CVSetName);
		}						
		queryCVSet.setStatus(CVSetStatus);
		
		if (cardTypeId != 0L ) {
			queryCVSet.setId(cardTypeId);
		}

		list = localCVSetManage.cvSetListByUnBindCardType(queryCVSet) ;
		
		request.setAttribute("sname", queryCVSet.getName());
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames",propNames);		
		request.setAttribute("creater", creater);
		request.setAttribute("status", CVSetStatus);
		request.setAttribute("CVSet", list);
		request.setAttribute("cardTypeId", cardTypeId);
						
		return "selectCvBindCardType";
	}
	
	//YHQ，2017-03-18，学习卡类型要选择项目
	private String systemCardTypeCVlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CVSet> list = new ArrayList<CVSet>();
				
		String propIds = ParamUtils.getParameter(request, "propIds");
		String propNames = ParamUtils.getParameter(request, "propNames");
		String creater = request.getParameter("creater");
		String CVSetName = request.getParameter("CVSetName");
		Long CVSetStatus = 5L ;		
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0L);
		
		CVSet queryCVSet = new CVSet();	
		if(!StringUtil.checkNull(creater)){		
			queryCVSet.setMaker(creater);
		}		
		
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
			queryCVSet.setCourseList(tempList);
		}
		
		if(!StringUtil.checkNull(CVSetName)){		
			queryCVSet.setName(CVSetName);
		}
		if (cardTypeId != 0L ) {
			queryCVSet.setId(cardTypeId);
		}
				
		list = localCVSetManage.cvSetListByBindCardType(queryCVSet) ;
		
		request.setAttribute("sname", queryCVSet.getName());
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames",propNames);		
		request.setAttribute("creater", creater);		
		request.setAttribute("CVSet", list);		
		request.setAttribute("cardTypeId", cardTypeId);		
						
		return "systemCardTypeCVlist";
	}
	
	//YHQ，2017-03-18，学习卡类型绑定到项目
	protected String bindCvSet(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		Long typeId     = ParamUtils.getLongParameter(request, "typeId", 0L);
		String cvSetId = ParamUtils.getParameter(request, "cvSetId");		
		String [] cvSetIdsArray = cvSetId.split(",");
		Long [] cvSetIdList = this.switchStringtoLongArray(cvSetIdsArray);
		
		JSONObject result = new JSONObject() ;
		String resMsg = "fail" ;
		if (typeId != 0L && cvSetIdList.length >0) {
			boolean resBind = systemCardManage.bindCardTypeToCVSet(typeId, cvSetIdList) ;
			if (resBind) resMsg = "success" ;
		}
		
		result.put("message",resMsg);
		StrutsUtil.renderText(response, result.toString());		
		return null;
	}
	
	//YHQ，2017-03-18，学习卡类型解绑定到项目
	protected String unBindCvSet(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		Long typeId     = ParamUtils.getLongParameter(request, "typeId", 0L);
		String cvSetId = ParamUtils.getParameter(request, "cvSetId");		
		String [] cvSetIdsArray = cvSetId.split(",");
		Long [] cvSetIdList = this.switchStringtoLongArray(cvSetIdsArray);
		
		JSONObject result = new JSONObject() ;
		String resMsg = "fail" ;
		if (typeId != 0L && cvSetIdList.length >0) {
			systemCardManage.unBindCardTypeToCVSet(typeId, cvSetIdList) ;
			resMsg = "success" ;
		}
		
		result.put("message",resMsg);
		StrutsUtil.renderText(response, result.toString());		
		return null;
	}
	
	//查看卡类型列表
	@SuppressWarnings("static-access")
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String cardTypeName = ParamUtils.getParameter(request, "cardTypeName", "");
		String startDate = ParamUtils.getParameter(request, "startDate", "");
		String endDate = ParamUtils.getParameter(request, "endDate", "");
		Page<SystemCardTypeQuery> page = this.createPage(request, "p");
		this.systemCardManage.getSystemCardTypeList(page, cardTypeName, startDate, endDate);
		request.setAttribute("page", page);
		Integer row = (Integer)request.getAttribute("row");
		String meg = "";
		if(null != row){
			if(row >0){
				meg = "保存成功！";
			}else{
				meg = "网络原因，保存不成功,请稍后再试！";
			}
		}
		
		List<SystemCreditType> allList = this.systemCardManage.getSystemCreditTypeList(0L);
		request.setAttribute("allList",allList);
		request.setAttribute("cardTypeName", cardTypeName);
		request.setAttribute("startDate", startDate) ;
		request.setAttribute("endDate", endDate);
		request.setAttribute("meg", meg);
		return "list";
	}
	
	//查看学习卡类型
	protected String get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		List<SystemCreditType> list = null;
		JSONObject result = new JSONObject();

		if(id >0){
			SystemCardTypeQuery type = this.systemCardManage.getSystemCardTypeById(id);
//			request.setAttribute("type", type);
			result.put("type", type);
			result.put("startDate", DateUtil.format(type.getStartDate(), "yyyy-MM-dd"));
			result.put("endDate", DateUtil.format(type.getEndDate(), "yyyy-MM-dd"));
			list = this.systemCardManage.getSystemCreditTypeList(type.getId());
		}
		List<SystemCreditType> allList = this.systemCardManage.getSystemCreditTypeList(0L);
		for(SystemCreditType ct : allList){
			if(null != list && !list.isEmpty()){
				for(SystemCreditType t : list){
					if(ct.getCreditType().equals(t.getCreditType())){
						ct.setChecked(1);
					}
				}
			}
		}
//		request.setAttribute("allList",allList);
		
		result.put("allList",allList);
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}
	
	//查看学习卡类型
	protected String view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		List<SystemCreditType> list = null;
		if(id >0){
			SystemCardTypeQuery type = this.systemCardManage.getSystemCardTypeById(id);
			request.setAttribute("type", type);
			list = this.systemCardManage.getSystemCreditTypeList(type.getId());
		}
		List<SystemCreditType> allList = this.systemCardManage.getSystemCreditTypeList(0L);
		for(SystemCreditType ct : allList){
			if(null != list && !list.isEmpty()){
				for(SystemCreditType t : list){
					if(ct.getCreditType().equals(t.getCreditType())){
						ct.setChecked(1);
					}
				}
			}
		}
		request.setAttribute("allList",allList);
		
		return "view";
	}
	
	//编辑学习卡类型
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		List<SystemCreditType> list = null;
		if(id >0){
			SystemCardTypeQuery type = this.systemCardManage.getSystemCardTypeById(id);
			request.setAttribute("type", type);
			list = this.systemCardManage.getSystemCreditTypeList(type.getId());
		}
		List<SystemCreditType> allList = this.systemCardManage.getSystemCreditTypeList(0L);
		for(SystemCreditType ct : allList){
			if(null != list && !list.isEmpty()){
				for(SystemCreditType t : list){
					if(ct.getCreditType().equals(t.getCreditType())){
						ct.setChecked(1);
					}
				}
			}
		}
		request.setAttribute("allList",allList);
		return "edit";
	}
	
	//更新卡类型
	protected String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SystemCardType type = new SystemCardType();
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		String name = ParamUtils.getParameter(request, "cardTypeName");
		if(id >0) {
			type.setId(id);
			SystemCardType cardType = this.systemCardManage.getSystemCardTypeById(id);
			if (!name.equals(cardType.getCardTypeName())) {
				//保存前校验卡类型名称是否重复,如果重复直接返回
				int count = this.systemCardManage.getSystemCardTypeByName(name);
				if(count > 0){
					StrutsUtil.renderText(response, "nameIsSame");
					return null;
				}
			}
		}else {
			type.setId(0L);
			//保存前校验卡类型名称是否重复,如果重复直接返回
			int count = this.systemCardManage.getSystemCardTypeByName(name);
			if(count > 0){
				StrutsUtil.renderText(response, "nameIsSame");
				return null;
			}
		}
		type.setCardTypeName(ParamUtils.getParameter(request, "cardTypeName"));
		type.setStartDate(ParamUtils.getDateParamenter(request, "startDate", "yyyy-MM-dd"));
		type.setEndDate(ParamUtils.getDateParamenter(request, "endDate", "yyyy-MM-dd"));
		type.setCreditScope(ParamUtils.getParameter(request, "creditScope"));
		type.setCreditSum(ParamUtils.getLongParameter(request, "creditSum", 0));
		type.setPrice(ParamUtils.getDoubleParameter(request, "price", 0));
		type.setEvpValue(ParamUtils.getLongParameter(request, "evpValue", 0));
		//type.setCreditType(ParamUtils.getParameter(request, "creditType"));
		String [] creditTypes = request.getParameterValues("creditType");
		type.setBalance(ParamUtils.getParameter(request, "creditScope")); //YHQ，2017-03-17替换 balance
		type.setIsNetpay(ParamUtils.getIntParameter(request, "isNetpay", 0));
		type.setIsSdsync(ParamUtils.getIntParameter(request, "isSdsync", 0));
		Integer row = this.systemCardManage.saveSystemCardType(type, creditTypes);
		if(row != null){
			if(row >0){
				StrutsUtil.renderText(response, "success");
//				meg = "保存成功！";
			}else{
				StrutsUtil.renderText(response, "fail");
//				meg = "网络原因，保存不成功,请稍后再试！";
			}
		}
		return null;
	}
	
	//删除卡类型
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		String cardIds = ParamUtils.getParameter(request, "typeIds");
		String [] ids = cardIds.split(",");
		Long [] typeIds = this.switchStringtoLongArray(ids);
		//Long [] typeIds = getLongParameters(request, "typeIds", 0L); 
		int row = this.systemCardManage.deleteSystemCardType(typeIds);
		Utils.renderText(response, String.valueOf(row));
		return null;
//		request.setAttribute("row", row);
//		return this.list(mapping, form, request, response);
	}
	
	//得到授权/未授权的课程
	@SuppressWarnings("static-access")
	protected String getStudyCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long isAuthorized = ParamUtils.getLongParameter(request, "isAuthorized", 0);
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		String courseName = ParamUtils.getParameter(request, "courseName");
		List<StudyCourseVO> list = null;
		Page<StudyCourseVO> page = this.createPage(request, "isStudyCourse");
		String url = "";
		if(isAuthorized == 1){			//授权的课程
			list = this.systemCardManage.getStudyCourse(page, typeId, true, courseName);
			url = "authorized-course-list";
		}/*else if(isAuthorized == 0){		//未授权的课程
			list = this.systemCardManage.getStudyCourse(page, typeId, false, courseName);
			url = "authorized-not-course-list";
		}*/
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		request.setAttribute("isAuthorized", isAuthorized);
		request.setAttribute("typeId", typeId);
		return url;
	}
	
	//得到卡类型未授权的课程列表
	@SuppressWarnings("static-access")
	protected String getUnCreditCourseList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		NcmeCourseCredit n = new NcmeCourseCredit();
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", -1);
		Page<NcmeCourseCredit> page = this.createPage(request, "creditStudyCourse");
		Integer courseType = ParamUtils.getIntParameter(request, "courseType", -100);
		String creditYear = ParamUtils.getParameter(request, "creditYear");
		if(StringUtils.isBlank(creditYear)){
			creditYear = DateUtil.getYear();
		}
		n.setCourseType(courseType);
		String studyCourseName = ParamUtils.getParameter(request, "studyCourseName");
		n.setStudyCourseName(studyCourseName);
		n.setCreditYear(creditYear);
		//n.setSiteId(siteId);
		
		courseCreditManage.getNcmeCourseCreditListByCourseType(page, n, cardTypeId);
		
		request.setAttribute("page", page);
		request.setAttribute("creditYear", creditYear);
		
		request.setAttribute("courseType", courseType);
		request.setAttribute("cardTypeId", cardTypeId);
		request.setAttribute("studyCourseName", studyCourseName);
		return "authorized-not-course-list";
	}
	
	//得到授权/未授权的机构
	@SuppressWarnings("static-access")
	protected String getSystemOrgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long isAuthorized = ParamUtils.getLongParameter(request, "isAuthorized", 0);
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		String orgName = ParamUtils.getParameter(request, "orgName");
		Page<SystemAdminOrgan> page = this.createPage(request, "iSystemOrg");
		List<SystemAdminOrgan> list = null;
		String url = "";
		if(isAuthorized == 1){			//授权的课程
			list = this.systemCardManage.getSystemOrgList(page, typeId, true, orgName);
			url = "authorized-org-list";
		}else if(isAuthorized == 0){		//未授权的课程
			list = this.systemCardManage.getSystemOrgList(page, typeId, false, orgName);
			url = "authorized-not-org-list";
		}
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		request.setAttribute("isAuthorized", isAuthorized);
		request.setAttribute("typeId", typeId);
		return url;
	}
	
	//保存卡类型与机构
	protected String saveSystemPaycardOrgan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		String _orgIds = ParamUtils.getParameter(request, "orgIds");
		_orgIds = _orgIds.substring(0, _orgIds.length()-1);
		String [] ids = _orgIds.split(",");
		Long [] orgIds = this.switchStringtoLongArray(ids);
		int count = this.systemCardManage.saveSystemPaycardOrgan(typeId, orgIds);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//保存卡类型课程
	protected String saveSystemPaycardCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0);
		String _courseIds = ParamUtils.getParameter(request, "courseIds");
		_courseIds = _courseIds.substring(0, _courseIds.length()-1);
		String [] ids = _courseIds.split(",");
		Long [] courseIds = this.switchStringtoLongArray(ids);
		int count = this.systemCardManage.saveSystemPaycardCourse(cardTypeId, courseIds, -1L, "", "");
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	/*//保存卡类型课程(课程分类--分类下的所有授权的课程)
	protected String saveSystemPaycardCourseByCourseType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0);
		String creditYear = ParamUtils.getParameter(request, "creditYear");
		String name = ParamUtils.getParameter(request, "studyCourseName");
		Long courseType = ParamUtils.getLongParameter(request, "courseType", -1);
		int count = this.systemCardManage.saveSystemPaycardCourse(cardTypeId, null, courseType, creditYear, name);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}*/
	
	//根据一个或者多个课程类别来进行卡类型的授权
	public String batchCourseAuthorized(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String courseTypeIds = ParamUtils.getParameter(request, "courseTypeIds");
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", -1);
		int row = 0;
		if(StringUtils.isNotBlank(courseTypeIds) && cardTypeId >-1){
			row = this.systemCardManage.batchCourseAuthorized(courseTypeIds, cardTypeId);
		}
		Utils.renderText(response, String.valueOf(row));
		return null;
	}
	
	
	//删除卡类型和课程授权
	public String deleteCourseAuthorized(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		Long courseId = ParamUtils.getLongParameter(request, "courseId", 0);
		int count = this.systemCardManage.deleteCourseAuthorized(typeId, courseId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//删除卡类型和机构授权
	public String deleteOrgAuthorized(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		Long orgId = ParamUtils.getLongParameter(request, "orgId", 0);
		int count = this.systemCardManage.deleteOrgAuthorized(typeId, orgId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	

	public SystemCardManage getSystemCardManage() {
		return systemCardManage;
	}

	public void setSystemCardManage(SystemCardManage systemCardManage) {
		this.systemCardManage = systemCardManage;
	}

	public NcmeCourseCreditManage getCourseCreditManage() {
		return courseCreditManage;
	}

	public void setCourseCreditManage(NcmeCourseCreditManage courseCreditManage) {
		this.courseCreditManage = courseCreditManage;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}
	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}
	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}
	public void setLocalExpertManageManage(ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}
	
	
}


