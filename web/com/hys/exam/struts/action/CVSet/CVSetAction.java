package com.hys.exam.struts.action.CVSet;

import com.google.gson.Gson;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.*;
import com.hys.exam.service.local.*;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.CVS_Form;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class CVSetAction extends BaseAction {

	private CVSetManage localCVSetManage;	
	private ExpertManageManage localExpertManageManage;	
	private MaterialManageManage localMaterialManage;	
	private CVManage localCVManage;
	private QualityModelManage localQualityModelManage;	
	private ExamPropValFacade localExamPropValFacade;	
	private SystemUserManage localSystemUserManage;		

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = RequestUtil.getParameter(request, "mode");		
		
		if(mode.equals("get_CVS")){
			//1：项目详情
			return get_CVS(mapping, form, request, response);
		} else if(mode.equals("CVS_add")){
			//2: 项目添加
			return add(mapping, form, request, response);
		} else if(mode.equals("del")){
			//3：项目删除
			return del(mapping, form, request, response);
		} else if (mode.equals("updateState")) {
			return updateState(mapping, form, request, response);
		} else if(mode.equals("getByAjax")){
			return getByAjax(mapping, form, request, response);
		}else if(mode.equals("union")){
			return Union(mapping, form, request, response);
		}else if(mode.equals("pinyin")){
			return getHanyuPinyinString(mapping, form, request, response);
		}else if(mode.equals("myXiangMu")) {
			//我的项目
			return myXiangMuList(mapping, form, request, response);
		}else if(mode.equals("myXiangMu_p")) {
			//我的项目分页
			return myXiangMuList_p(mapping, form, request, response);
		}else if(mode.equals("myXueKe")){
			return myXueKeList(mapping, form, request, response);
		}else if(mode.equals("getDeepQuality")){
			return getDeepQuality(mapping, form, request, response);
		}else if(mode.equals("save_guide_ref")){
			return save_guide_ref(mapping, form, request, response);
		}else if(mode.equals("edit")){
			//项目编辑
			return editCVS(mapping, form, request, response);
		}else if(mode.equals("qualify")){
			//项目审核   chenlb add
			return this.qualify(mapping, form, request, response);
		}else if(mode.equals("deleteCVfromCvset")){
			//YHQ，2017-05-26，从项目中删除课程
			return this.deleteCVfromCvset(mapping, form, request, response);
		}else if(mode.equals("saveScheduleSequence")){
			//YHQ，2017-05-30，保存项目中课程顺序
			return this.saveScheduleSequence(mapping, form, request, response);
		}else if(mode.equals("queryCVSetScheduleFaceTeachByCVsetId")){
			//YHQ，2017-06-07，获取项目中班次安排
			return this.queryCVSetScheduleFaceTeachByCVsetId(mapping, form, request, response);
		}else if(mode.equals("saveCVSetScheduleFaceTeach")){
			//YHQ，2017-06-07，保存项目中班次安排
			return this.saveCVSetScheduleFaceTeach(mapping, form, request, response);
		}else if(mode.equalsIgnoreCase("preView")){
			String s=get_CVS(mapping, form, request, response);
			return "preView";
		}else {
			List<CVSet> list = new ArrayList<CVSet>();
			Long id = ParamUtils.getLongParameter(request, "id", 0L);
			String propIds = ParamUtils.getParameter(request, "propIds");
			String propNames = ParamUtils.getParameter(request, "propNames");
			String creater = request.getParameter("creater");
			String CVSetName = request.getParameter("CVSetName");
			Long CVSetStatus = ParamUtils.getLongParameter(request, "CVSetStatus", 0L);
			int forma = ParamUtils.getIntParameter(request, "forma", 0); //项目授课类型

			CVSet queryCVSet = new CVSet();
			queryCVSet.setForma(forma);
			queryCVSet.setMaker(creater);
			/******getting relation xueke***********/

			SystemUser currentUser = localSystemUserManage.getItemByAccountName(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString(), null);
			Integer currentUserType = currentUser.getUserType() ;//YHQ,2017-09-21,有为空的老数据
			if(currentUserType != null && currentUserType == 3){
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setName(currentUser.getRealName());
				List<ExpertInfo> expertInfoList = localExpertManageManage.getExpertList(expertInfo);
				expertInfo.setId(expertInfoList.get(0).getId());
				ExpertInfo expert_xueke =  localExpertManageManage.getExpertInfo(expertInfo);
				request.setAttribute("expertXueke", expert_xueke);
			}
			/***********************/

			if(mode.equals("qualify")){
				queryCVSet.setMaker(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
				queryCVSet.setFlag(mode);
			}
			List<PropUnit> courseList = new ArrayList<PropUnit>();
		/*if(propIds!=null && propIds[0] !=-1){
			//List<PropUnit> courseList = new ArrayList<PropUnit>();
			PropUnit propUnit = new PropUnit();
			for(long propid:propIds){
				propUnit.setId(propid);
				courseList.add(propUnit);
			}
			queryCVSet.setCourseList(courseList);
		}*/
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

			queryCVSet.setId(id);
			queryCVSet.setName(CVSetName);

			//queryCVSet.setCourseList(xueke);
			//queryCVSet.set
			queryCVSet.setStatus(CVSetStatus);
			queryCVSet.setType(-11);//“项目管理”查询标识，只做传参，不进入数据库

			PageList pl = new PageList();
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
		 	String sort = ParamUtils.getParameter(request, "sort", "u.reg_date");
			String dir = ParamUtils.getParameter(request, "dir", "desc");

			pl.setPageNumber(currentPage);
			pl.setObjectsPerPage(pageSize);
			pl.setSortCriterion(sort);

			localCVSetManage.getCVSetListByPage(pl,queryCVSet);

			List<CVSet> setList = pl.getList();
			//查询项目审核人信息
			if (setList != null && setList.size()>0) {
				for (CVSet cs:setList) {
					List<ExpertInfo> persion = localExpertManageManage.getExpertInfoNameByCvSetId(cs.getId());
					String deli_man = "";
					if (persion != null && persion.size()>0) {
						for (ExpertInfo e:persion) {
							deli_man += e.getName()+" ";
						}
					}
					cs.setDeli_man(deli_man);
				}
			}

			//屏蔽写死的路径
		/*for (CVSet cvSet:list) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}*/

			request.setAttribute("sname", queryCVSet.getName());
			request.setAttribute("propIds", propIds);
			request.setAttribute("propNames",propNames);
			//request.setAttribute("xueke", queryCVSet.getName());
			request.setAttribute("creater", creater);
			request.setAttribute("status", CVSetStatus);
			request.setAttribute("forma", forma);
			request.setAttribute("CVSet", pl);
			request.setAttribute("user", currentUser);

			return "list";
		}
	}
		
	//YHQ，2017-06-07，保存项目中班次安排
	private String saveCVSetScheduleFaceTeach(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0);
		List<CVSetScheduleFaceTeach> cssftList = null ;
		//期数
		String[] class_name = request.getParameterValues("class_name") ;
		//招生人数
		String[] people_number = request.getParameterValues("people_number") ;
		//起止时间
		String[] train_starttime = request.getParameterValues("train_starttime") ;
		String[] train_endtime = request.getParameterValues("train_endtime") ;
		//上课时间
		String[] lession_starttime = request.getParameterValues("lession_starttime") ;
		String[] lession_endtime = request.getParameterValues("lession_endtime") ;
		//培训地点
		String[] train_place = request.getParameterValues("train_place") ;
		//联系方式
		String[] contact_way = request.getParameterValues("contact_way") ;
		//乘车路线
		String[] route_way = request.getParameterValues("route_way") ;

		String resultStr = "success" ;
		if (cvSetId > 0 ) {
			if (class_name != null && class_name.length > 0) {
				cssftList = new ArrayList() ;
				for (int i = 0 ; i < class_name.length ; i++) {
					CVSetScheduleFaceTeach cssftObj = new CVSetScheduleFaceTeach() ;
					cssftObj.setCv_set_id(cvSetId.intValue());
					cssftObj.setClass_name(class_name[i]);
					if(!StringUtils.checkNull(people_number[i])) cssftObj.setPeople_number(Integer.parseInt(people_number[i]));
					//起止时间
					if(!StringUtils.checkNull(train_starttime[i])) cssftObj.setTrainStartTime(train_starttime[i]);
					if(!StringUtils.checkNull(train_endtime[i])) cssftObj.setTrainEndTime(train_endtime[i]);
					//上课时间
					if(!StringUtils.checkNull(lession_starttime[i])) cssftObj.setLession_starttime(lession_starttime[i]);
					if(!StringUtils.checkNull(lession_endtime[i])) cssftObj.setLession_endtime(lession_endtime[i]);
					cssftObj.setTrain_place(train_place[i]);
					cssftObj.setContact_way(contact_way[i]);
					cssftObj.setRoute_way(route_way[i]);

					cssftList.add(cssftObj) ;
				}
			}
			localCVSetManage.saveCVSetScheduleFaceTeach(cssftList, cvSetId); 
		} else {
			resultStr = "argsIsBlank" ;
		}				

		JSONObject json = new JSONObject();
		json.put("result", resultStr);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	
	//YHQ，2017-06-07，获取项目中班次安排
	private String queryCVSetScheduleFaceTeachByCVsetId(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0);		
		List<CVSetScheduleFaceTeach> cssftList = null ;
		if (cvSetId > 0 ) {
			cssftList = localCVSetManage.queryCVSetScheduleFaceTeachByCVsetId(cvSetId); 
		}				

		JSONObject json = new JSONObject();
		json.put("result", cssftList);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	
	//YHQ，2017-05-30，保存项目中课程顺序
	private String saveScheduleSequence(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0);
		String scheduleIds = request.getParameter("scheduleIds") ;
		String resultStr = "success" ;
		if (cvSetId > 0 && scheduleIds != null) {
			localCVSetManage.saveScheduleSequence(cvSetId, scheduleIds); 
		} else {
			resultStr = "argsIsBlank" ;
		}				

		JSONObject json = new JSONObject();
		json.put("result", resultStr);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	
	//YHQ，2017-05-26，从项目中删除课程
	private String deleteCVfromCvset(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		String resMsg = "success" ;
		long cvId = ParamUtils.getLongParameter(request, "cvId", -1L);
		if (cvId != -1) {
			localCVSetManage.deleteCVfromCvsetByCvId(cvId);
		}		
		JSONObject json = new JSONObject();
		json.put("result", resMsg);
		StrutsUtil.renderText(response, json.toString());
		return null;		
	}
	
	//项目审核列表 chenlb add
	private String qualify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String CVSetName = request.getParameter("CVSetName");
		Long CVSetStatus = ParamUtils.getLongParameter(request, "CVSetStatus", 0L);		
		String propIds = ParamUtils.getParameter(request, "propIds");
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();		
		queryCVSet.setName(CVSetName);
		queryCVSet.setStatus(CVSetStatus);
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
		/******getting relation xueke***********/
		SystemUser currentUser = localSystemUserManage.getItemByAccountName(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString(), null);
		if(currentUser.getUserType() == 3){
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setName(currentUser.getRealName());
			List<ExpertInfo> expertInfoList = localExpertManageManage.getExpertList(expertInfo);
			expertInfo.setId(expertInfoList.get(0).getId());
			ExpertInfo expert_xueke =  localExpertManageManage.getExpertInfo(expertInfo);
			request.setAttribute("expertXueke", expert_xueke);
		}
		queryCVSet.setFlag("qualify");
		queryCVSet.setMaker(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
		list = localCVSetManage.getCVSetListForQualify(queryCVSet, currentUser);
		request.setAttribute("CVSet", list);
		request.setAttribute("sname", CVSetName);		
		request.setAttribute("status", CVSetStatus);
		request.setAttribute("propIds", propIds);
		return "list";
	}

	private String save_guide_ref(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		long id = ParamUtils.getLongParameter(request, "id", -1L);
		String params = ParamUtils.getParameter(request, "id_levels");
		String uncheckId_levels = ParamUtils.getParameter(request, "uncheckId_levels");
		if (id < 0 || StringUtil.checkNull(params)) return null;
		
		String[] id_levels = params.split(",");
		
		List<QualityModel> qm_list = new ArrayList<QualityModel>();
		
		for (String param:id_levels) {
			if (param.equals("")) continue;
			String[] arr = param.split("_");
			
			QualityModel qm = new QualityModel();
			qm.setId(Long.parseLong(arr[0]));
			qm.setLevel(Long.parseLong(arr[1]));
			qm.setParentId(1L);
			qm_list.add(qm);
		}
		
		if (!StringUtil.checkNull(uncheckId_levels)) {
			String[] uncheck_id_levels = uncheckId_levels.split(",");
			for (String unParam : uncheck_id_levels) {
				if (unParam.equals("")) continue;
				String[] arr = unParam.split("_");
				
				QualityModel qm = new QualityModel();
				qm.setId(Long.parseLong(arr[0]));
				qm.setLevel(Long.parseLong(arr[1]));
				qm.setParentId(0L);
				qm_list.add(qm);
			}
		}
		
		localCVSetManage.updateUnit_ref_Quality(id, qm_list);
		
		StrutsUtil.renderText(response, "SUCCESS");
		
		return null;
	}

	/**
	 * 
	 * @param
	 * @return  String
	 * @time    2017-01-08
	 * @author  张建国
	 * 方法说明：查询我的学科信息
	 */
	private String myXueKeList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		List<CVSet> list = new ArrayList<CVSet>();
		//保存筛选后的结果信息
		List<CVSet> reesult = new ArrayList<CVSet>();
		Set<CVSet> ts = new HashSet<CVSet>();
		List<CVSet> r1 = new ArrayList<CVSet>();
		List<PropUnit> ppList = new ArrayList<PropUnit>();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		//项目授课类型
		int lessonType = ParamUtils.getIntParameter(request, "lessonType", 0);
		String xueke = request.getParameter("xueke");
		String propIds = ParamUtils.getParameter(request, "propIds");
		String propNames = ParamUtils.getParameter(request, "propNames");
		String creater = request.getParameter("creater");
		String CVSetName = request.getParameter("CVSetName");
		String display_propIds = null;
		Long CVSetStatus = ParamUtils.getLongParameter(request, "CVSetStatus", 0L);		
		//获取个人信息
		SystemUser currentUser = localSystemUserManage.getItemByAccountName(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString(), null);
		if(currentUser.getUserType() == 3){
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setName(currentUser.getRealName());
			List<ExpertInfo> expertInfoList = localExpertManageManage.getExpertList(expertInfo);
			expertInfo.setId(expertInfoList.get(0).getId());
			ExpertInfo expert_xueke =  localExpertManageManage.getExpertInfo(expertInfo);
			display_propIds = expert_xueke.getPropIds();
		}
		CVSet queryCVSet = new CVSet();		
		queryCVSet.setId(id);
		queryCVSet.setForma(lessonType);
		queryCVSet.setName(CVSetName);
		queryCVSet.setMaker(creater);
		//queryCVSet.setCourseList(xueke);
		//queryCVSet.set
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
		if(!StringUtil.checkNull(display_propIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = display_propIds.split(",");
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
		//屏蔽此处，释放查询全部状态情况----taoliang
		//设置项目状态 -- 查询已经发布的项目	
	    /*if(CVSetStatus!=null && CVSetStatus>0){
	    	queryCVSet.setStatus(CVSetStatus);
	    }else{
	    	queryCVSet.setStatus(5L);
	    }*/
		//查询的学科和专家的学科相同时才查询
		if(StringUtil.checkNull(propIds) || (!StringUtil.checkNull(display_propIds) && propIds.contains(display_propIds))){
			queryCVSet.setStatus(CVSetStatus);
			list = localCVSetManage.getCVSetList(queryCVSet);
			
			//查询用户对应专委会的学科
			ppList = localCVSetManage.getPropByUserId(currentUser.getUserId());
			//第一步、查询项目和人物画像的关系
			for(int i=0;i<list.size();i++){
				CVSet cvset = list.get(i);
				List<UserImage> um = cvset.getUserImageList();
				//第二步、查询任务画像和学科的关系
				for(int j=0;j<um.size();j++){
					List<PropUnit> pp = um.get(j).getDepartmentPropList();
					for(int z=0;z<pp.size();z++){
						PropUnit pu = pp.get(z);
						for(int l=0;l<ppList.size();l++){
							PropUnit up = ppList.get(l);
							//如果炫目的学科与专委会的学科相同，则返回
							if(pu.getId().longValue()==up.getId().longValue()){
								reesult.add(cvset);
							}
						}
					}
				}	
			}
			//对重复数据进行率重
			ts.addAll(reesult);
			for (CVSet student : ts) {
				r1.add(student)  ;
			}
		}
		
		//屏蔽写死路径
		/*
		for (CVSet cvSet:list) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}*/
		request.setAttribute("sname", CVSetName);
		//request.setAttribute("xueke", queryCVSet.getName());
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", propNames);
		request.setAttribute("creater", creater);
		request.setAttribute("status", CVSetStatus);
		//reesult-->r1
		request.setAttribute("CVSet", r1);
		return "myXueKe";
	}

	private String myXiangMuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			
		String CVSetName = request.getParameter("CVSetName");
		String page = (null==request.getParameter("page"))?"0":request.getParameter("page");
		int pageSize = (null==request.getParameter("pageSize"))?0:Integer.valueOf(request.getParameter("pageSize"));
		Long CVSetStatus = ParamUtils.getLongParameter(request, "CVSetStatus", 0L);
		//项目授课类型
		int forma = ParamUtils.getIntParameter(request, "forma", 0);
		String propIds = ParamUtils.getParameter(request, "propIds");
		String propNames = ParamUtils.getParameter(request, "propNames");
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();		
		queryCVSet.setForma(forma);
		queryCVSet.setName(CVSetName);
		queryCVSet.setStatus(CVSetStatus);
		
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
		/******getting relation xueke***********/
		
		SystemUser currentUser = localSystemUserManage.getItemByAccountName(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString(), null);
		if(currentUser.getUserType() == 3){
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setName(currentUser.getRealName());
			List<ExpertInfo> expertInfoList = localExpertManageManage.getExpertList(expertInfo);
			expertInfo.setId(expertInfoList.get(0).getId());
			ExpertInfo expert_xueke =  localExpertManageManage.getExpertInfo(expertInfo);
			request.setAttribute("expertXueke", expert_xueke);
		}
		/***********************/
	
		queryCVSet.setId(-1L);
		queryCVSet.setMaker2(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
		queryCVSet.setType(-10);//我的项目查询标识，只做传参，不进入数据库
		list = localCVSetManage.getCVSetList(queryCVSet);
		
		/*for (CVSet cvSet:list) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}*/
		Gson gson=new Gson();
		String json=gson.toJson(list);
		List curPageList=new ArrayList();

		int begin=Integer.valueOf(page)*pageSize;
		int end=((Integer.valueOf(page)+1)*pageSize<=list.size())?(Integer.valueOf(page)+1)*pageSize:list.size();
		for (int i = begin; i <end ; i++) {
			curPageList.add(list.get(i));
		}
		request.setAttribute("CVSetNum", list.size());
		request.setAttribute("CVSet", list);
		request.setAttribute("sname", CVSetName);
		request.setAttribute("status", CVSetStatus);
		request.setAttribute("forma", forma);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", propNames);
		return "myXiangMu";
	}
	private String myXiangMuList_p(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) {


		String CVSetName = request.getParameter("CVSetName");
		String page = (null==request.getParameter("page"))?"0":request.getParameter("page");
		int pageSize = (null==request.getParameter("pageSize"))?0:Integer.valueOf(request.getParameter("pageSize"));
		Long CVSetStatus = ParamUtils.getLongParameter(request, "CVSetStatus", 0L);
		//项目授课类型
		int forma = ParamUtils.getIntParameter(request, "forma", 0);
		String propIds = ParamUtils.getParameter(request, "propIds");
		String propNames = ParamUtils.getParameter(request, "propNames");
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();
		queryCVSet.setForma(forma);
		queryCVSet.setName(CVSetName);
		queryCVSet.setStatus(CVSetStatus);

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
		/******getting relation xueke***********/

		SystemUser currentUser = localSystemUserManage.getItemByAccountName(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString(), null);
		if(currentUser.getUserType() == 3){
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setName(currentUser.getRealName());
			List<ExpertInfo> expertInfoList = localExpertManageManage.getExpertList(expertInfo);
			expertInfo.setId(expertInfoList.get(0).getId());
			ExpertInfo expert_xueke =  localExpertManageManage.getExpertInfo(expertInfo);
			request.setAttribute("expertXueke", expert_xueke);
		}
		/***********************/

		queryCVSet.setId(-1L);
		queryCVSet.setMaker2(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
		queryCVSet.setType(-10);//我的项目查询标识，只做传参，不进入数据库
		list = localCVSetManage.getCVSetList(queryCVSet);


		Gson gson=new Gson();
		String json=gson.toJson(list);
		List<CVSet> curPageList=new ArrayList<CVSet>();

		int begin=Integer.valueOf(page)*pageSize;
		int end=((Integer.valueOf(page)+1)*pageSize<=list.size())?(Integer.valueOf(page)+1)*pageSize:list.size();
		for (int i = begin; i <end ; i++) {
			curPageList.add(list.get(i));
		}
		StrutsUtil.renderText(response, gson.toJson(curPageList));
		return null;
	}

	private String Union(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet queryCVSet  = new CVSet(); 
		queryCVSet.setId(id);
		list = localCVSetManage.getCVSetList(queryCVSet);
		request.setAttribute("list", list);
		JSONObject result = new JSONObject();
		result.put("CVS", list);
		StrutsUtil.renderText(response, result.toString());		
		return null;
	}

	private String getByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet cvset = new CVSet();
		cvset.setId(id);
		list = localCVSetManage.getCVSetList(cvset);
		String username = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		ExpertInfo expertInfo = this.localExpertManageManage.getExpertInfoByUsername(username);
		JSONObject result = new JSONObject();		
		result.put("list", list);
		result.put("expertInfo", expertInfo);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	/*private String add_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String handle = RequestUtil.getParameter(request, "handle");
		if (handle.equals("first")){
			request.setAttribute("handle", "first")
		}else if(handle.equals("second"))
		return "add_page";
	}*/

	private String updateState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//旧版，审核就一次，通过就通过，不通过就不通过
		/*
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long state = ParamUtils.getLongParameter(request, "state", 0L);
		//long[] opinion_types = ParamUtils.getLongParameters(request, "opinion_types", 0L);
		String opinionType = request.getParameter("opinionType");
		String opinion = ParamUtils.getParameter(request, "opinion", "");
		
		CVSet cvSet = new CVSet();
		cvSet.setId(id);
		List<CVSet> list = localCVSetManage.getCVSetList(cvSet);
		cvSet = list.get(0);
		cvSet.setOpinionType(opinionType);
		cvSet.setStatus(state);
		cvSet.setOpinion(opinion);
		
		localCVSetManage.updateCVSet(cvSet);*/
		
		
		//新版，前三次审核，只要有两次通过，就算通过。只要有2次不通过就算不通过
		//20170101 chenlb更新
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);		//cvset id
		Integer state = ParamUtils.getIntParameter(request, "state", 0);
		String opinionType = request.getParameter("opinionType");
		String opinion = ParamUtils.getParameter(request, "opinion", "");
		String code= request.getParameter("code"); //项目编号
		Long hisCvSetId = ParamUtils.getLongParameter(request, "hisCvSetId", 0L);  //审核表中的cvsetid
		CVSet cvSet = new CVSet();
		cvSet=localCVSetManage.getCVSetByCode(code);
		if(0 == id || 0==hisCvSetId){
			//当前专家的id
//			String username = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
//			ExpertInfo expertInfo = this.localExpertManageManage.getExpertInfoByUsername(username);
//			if(null != expertInfo){
				CvsetQualityHistory history = new CvsetQualityHistory();
				history.setCvSetId(cvSet.getId());
				history.setStatus(1);
				history.setQualifyStatus(2);
				history.setOpinionType(cvSet.getOpinionType());
				history.setOpinion(cvSet.getOpinion());
//				history.setExpertId(expertInfo.getId());
				localCVSetManage.saveCvsetQualityHistory(history);
//			}
		}else{
			//修改“审核不通过”的数据，点击下一步会变成未提交，点击提交审核才会修改状态为审核中
			if(cvSet.getStatus()==1){
				cvSet.setStatus(2L);
				localCVSetManage.updateCVSet(cvSet);
			}
			//当前专家的id
			String username = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
			ExpertInfo expertInfo = this.localExpertManageManage.getExpertInfoByUsername(username);
			if(null != expertInfo){
				CvsetQualityHistory history = new CvsetQualityHistory();
				history.setCvSetId(id);
				history.setStatus(1);
				history.setQualifyStatus(state);
				history.setOpinionType(opinionType);
				history.setOpinion(opinion);
			
				history.setExpertId(expertInfo.getId());
				localCVSetManage.saveCvsetQualityHistory(history);
			}
		}
		
		
		
		StrutsUtil.renderText(response, "success");
		
		return null;
	}

	private String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long del_id = ParamUtils.getLongParameter(request, "id", 0L);
		CVSet del_CVS = new CVSet();
		del_CVS=localCVSetManage.getCVSetById(del_id);
		
			//SCP 查询项目是否绑定卡类型
			del_CVS.setCityId(0L);
			boolean i = localCVSetManage.bindCardTypeByCVSet(del_CVS);
			if(i){
				StrutsUtil.renderText(response, "bangDing");
			}else{
				//只有未提交状态的项目才能进行删除操作
				if(del_CVS.getStatus()==1){
					boolean flag = localCVSetManage.deleteCVSet(del_CVS);
					if(flag){
						StrutsUtil.renderText(response, "success");
					}else{
						StrutsUtil.renderText(response, "fail");
					}
				}else{
					StrutsUtil.renderText(response, "fail");
				}
			}
				
		return null;
	}

	private String add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cvschedule_scheduleId = request.getParameter("cvscheduleIds"); 
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String report = request.getParameter("report");
		String projectfrom = request.getParameter("projectfrom");

		CVS_Form cvs_form = (CVS_Form)form;
		CVSet cvset = new CVSet();	
		cvset.setId(id);
		cvset.setName(cvs_form.getName());
		cvset.setForma(cvs_form.getForma());
		cvset.setReport(report);
		//保存项目路径
		cvset.setFile_path(cvs_form.getCover());
		if(!StringUtils.checkNull(cvschedule_scheduleId)){		
			CVSchedule cvsche = new CVSchedule();
			String[] cvscheduleIds =  request.getParameter("cvscheduleIds").split(",");
			List<CVSchedule> cvschedule = new ArrayList<CVSchedule>();
			for(String id_:cvscheduleIds){
				try{
				CVSchedule cvsc = new CVSchedule();
				cvsc.setSchedule_id(Long.parseLong(id_.trim()));
				cvschedule.add(cvsc);
				}
				catch(Exception e){;}
			}
			cvset.setCVScheduleList(cvschedule);
		}
		//add organization
		long[] orgIds = ParamUtils.getLongParametersFromString(request, "organization", 0); 
		List<PeixunOrg> OrganizationList = new ArrayList<PeixunOrg>();
		if(orgIds!=null && orgIds[0] != 0){			
			for(long orgid:orgIds){
				PeixunOrg org = new PeixunOrg();
				org.setId(orgid);
				OrganizationList.add(org);
			}
			cvset.setOrganizationList(OrganizationList);
		}
		//cvset.setUserImageList(userImageList);
		String[] userImages = cvs_form.getUserImage().split(",");
		List<UserImage> userImageList = new ArrayList<UserImage>();
		if(userImages != null && userImages.length > 0){
			for(String item:userImages){
				try{
				UserImage userImage = new UserImage();
				userImage.setId(Long.parseLong(item.trim()));
				userImageList.add(userImage);
				}
				catch(Exception e){;}
			}
			cvset.setUserImageList(userImageList);
		}else{}
		cvset.setCode(cvs_form.getCode());
		//cvset.setManagerList
		String[] experts = cvs_form.getManager().split(",");
		List<ExpertInfo> mangerList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			try{
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			mangerList.add(expertInfo);
			}
			catch(Exception e){;}
		}
		cvset.setManagerList(mangerList);
		
		//cvset.setOtherTeacherList
		experts = cvs_form.getGeneralTeacher().split(",");
		List<ExpertInfo> otherTeacherList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			try{
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			otherTeacherList.add(expertInfo);
			}
			catch(Exception e){;}
		}
		cvset.setOtherTeacherList(otherTeacherList);
		//LessonTeacher
		experts = cvs_form.getLessonTeacher().split(",");
		List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			try{
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			teacherList.add(expertInfo);
			}
			catch(Exception e){;}
		} 
		cvset.setTeacherList(teacherList);
		cvset.setIntroduction(cvs_form.getIntroduction());
		cvset.setAnnouncement(cvs_form.getAnnouncement());
		cvset.setKnowledge_needed(cvs_form.getKnowledge_needed());
		cvset.setKnowledge_base(cvs_form.getKnowledge_base());
		cvset.setReference(cvs_form.getReference());
		cvset.setReference_lately(cvs_form.getReference_lately());
		cvset.setMaker(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
		
		String str_cvIds = ParamUtils.getParameter(request, "cvIds");
		String[] cvIds = str_cvIds.split(",");
		
		List<Long> cvIdsList = new ArrayList<Long>();
		for (String cvId:cvIds){
			try{
			cvIdsList.add(Long.parseLong(cvId.trim()));
			}
			catch(Exception e){;}
		}
		
		List<CVSchedule> scheduleList = new ArrayList<CVSchedule>();
		for (Long cvIdList:cvIdsList){
			CVSchedule schedule = new CVSchedule();
			schedule.setId(cvIdList);
			scheduleList.add(schedule);
		}
		cvset.setCVScheduleList(scheduleList);

        String[]  chooseSourseBookID =(null==request.getParameter("chooseSourseBookID"))?null: request.getParameter("chooseSourseBookID").toString().split(",");

        List<BaseVO> refereeBookList = new ArrayList() ; ;
        if (chooseSourseBookID != null && chooseSourseBookID.length > 0) {
            for (int i = 0 ; i < chooseSourseBookID.length ; i++) {
                if (!stringIsBlank(chooseSourseBookID[i]) ) {
                    BaseVO rbObj = new BaseVO() ;
                    rbObj.setSource_id(chooseSourseBookID[i].trim());
                    refereeBookList.add(rbObj) ;
                }
            }
        }
        cvset.setRefereeBookList(refereeBookList);
        String[]  chooseSourseKnowledgeID =(null==request.getParameter("chooseSourseKnowledgeID"))?null: request.getParameter("chooseSourseKnowledgeID").toString().split(",");

        List<BaseVO> knowledgeBaseList = new ArrayList() ; ;
        if (chooseSourseKnowledgeID != null && chooseSourseKnowledgeID.length > 0) {
            for (int i = 0 ; i < chooseSourseKnowledgeID.length ; i++) {
                if (!stringIsBlank(chooseSourseKnowledgeID[i]) ) {
                    BaseVO rbObj = new BaseVO() ;
                    rbObj.setSource_id(chooseSourseKnowledgeID[i].trim());
                    knowledgeBaseList.add(rbObj) ;
                }
            }
        }
        cvset.setKnowledgeBaseList(knowledgeBaseList);
        String[]  chooseSourseReferenceID =(null==request.getParameter("chooseSourseReferenceID"))?null: request.getParameter("chooseSourseReferenceID").toString().split(",");
        List<BaseVO> referenceLatelyList = new ArrayList() ; ;
        if (chooseSourseReferenceID != null && chooseSourseReferenceID.length > 0) {
            for (int i = 0 ; i < chooseSourseReferenceID.length ; i++) {
                if (!stringIsBlank(chooseSourseReferenceID[i]) ) {
                    BaseVO rbObj = new BaseVO() ;
                    rbObj.setSource_id(chooseSourseReferenceID[i].trim());
                    referenceLatelyList.add(rbObj) ;
                }
            }
        }
        cvset.setReferenceLatelyList(referenceLatelyList);
		cvset.setCv_set_type(cvs_form.getCv_set_type()); //YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
		
	    Long id_ = localCVSetManage.addCVSet(cvset);
	    Map<String,Object> map = new HashMap<String,Object>();
	    if(id_>0){
	    	map.put("message", "success");
	    	map.put("projectfrom", projectfrom);
	    	map.put("proId", id_);
	    }else{
	    	map.put("message", "fail");
	    }
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @param    
	 * @return   String
	 * @author   张建国
	 * @time     2017-01-06
	 * @throws   Exception
	 * 方法说明： 修改项目信息
	 */
	private String editCVS(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		String cvschedule_scheduleId = request.getParameter("cvscheduleIds"); 
		Long cVSetStatus = ParamUtils.getLongParameter(request, "cVSetStatus", 0);
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String report = request.getParameter("report");
		CVS_Form cvs_form = (CVS_Form)form;
		CVSet cvset = new CVSet();	
		cvset.setId(id);
		cvset.setName(cvs_form.getName());
		cvset.setForma(cvs_form.getForma());
		cvset.setReport(report);
		//设置项目封面
		cvset.setFile_path(cvs_form.getCover());
		if(cVSetStatus > 0 ){
			cvset.setStatus(cVSetStatus);
		}
		if(!StringUtils.checkNull(cvschedule_scheduleId)){		
			//CVSchedule cvsche = new CVSchedule();
			String[] cvscheduleIds =  request.getParameter("cvscheduleIds").split(",");
			List<CVSchedule> cvschedule = new ArrayList<CVSchedule>();
			for(String id_:cvscheduleIds){
				try{
				CVSchedule cvsc = new CVSchedule();
				cvsc.setSchedule_id(Long.parseLong(id_.trim()));
				cvschedule.add(cvsc);
				}
				catch(Exception e){;}
			}
			cvset.setCVScheduleList(cvschedule);
		}
		//add organization
		long[] orgIds = ParamUtils.getLongParametersFromString(request, "organization", 0); 
		List<PeixunOrg> OrganizationList = new ArrayList<PeixunOrg>();
		if(orgIds!=null && orgIds[0] != 0){
			for(long orgid:orgIds){
				try{
				PeixunOrg org = new PeixunOrg();
				org.setId(orgid);
				OrganizationList.add(org);
				}
				catch(Exception e){;}
			}
			cvset.setOrganizationList(OrganizationList);
		}
		//cvset.setUserImageList(userImageList);
		String[] userImages = cvs_form.getUserImage().split(",");
		List<UserImage> userImageList = new ArrayList<UserImage>();
		if(userImages != null && userImages.length > 0){
			for(String item:userImages){
				try{
				UserImage userImage = new UserImage();
				userImage.setId(Long.parseLong(item.trim()));
				userImageList.add(userImage);
				}
				catch(Exception e){;}
			}
			cvset.setUserImageList(userImageList);
		}
		cvset.setCode(cvs_form.getCode());
		//cvset.setManagerList
		String[] experts = cvs_form.getManager().split(",");
		List<ExpertInfo> mangerList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			try{
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			mangerList.add(expertInfo);
			}
			catch(Exception e){;}
		}
		cvset.setManagerList(mangerList);
		//cvset.setOtherTeacherList
		experts = cvs_form.getGeneralTeacher().split(",");
		List<ExpertInfo> otherTeacherList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			try{
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			otherTeacherList.add(expertInfo);
			}
			catch(Exception e){;}
		}
		cvset.setOtherTeacherList(otherTeacherList);
		//LessonTeacher
		experts = cvs_form.getLessonTeacher().split(",");
		List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			try{
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			teacherList.add(expertInfo);
			}
			catch(Exception e){;}
		} 
		cvset.setTeacherList(teacherList);
		cvset.setIntroduction(cvs_form.getIntroduction());
		cvset.setAnnouncement(cvs_form.getAnnouncement());
		cvset.setKnowledge_needed(cvs_form.getKnowledge_needed());
		cvset.setKnowledge_base(cvs_form.getKnowledge_base());
		cvset.setReference(cvs_form.getReference());
		cvset.setReference_lately(cvs_form.getReference_lately());
		cvset.setMaker(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
		String str_cvIds = ParamUtils.getParameter(request, "cvIds");
		String[] cvIds = str_cvIds.split(",");
		List<Long> cvIdsList = new ArrayList<Long>();
		for (String cvId:cvIds){
			try{
			cvIdsList.add(Long.parseLong(cvId.trim()));
			}
			catch(Exception e){;}
		}
		List<CVSchedule> scheduleList = new ArrayList<CVSchedule>();
		for (Long cvIdList:cvIdsList){
			CVSchedule schedule = new CVSchedule();
			schedule.setId(cvIdList);
			scheduleList.add(schedule);
		}
		cvset.setCVScheduleList(scheduleList);
		
		//YHQ，2017-05-16，begin
		String[] book_name = request.getParameterValues("book_name") ;
		String[] book_url = request.getParameterValues("book_url") ;										
		List<BaseVO> refereeBookList = new ArrayList() ; 
		if (book_name != null && book_name.length > 0) {
			for (int i = 0 ; i < book_name.length ; i++) {
				if (!stringIsBlank(book_name[i]) ) {
//				if (!stringIsBlank(book_name[i]) && !stringIsBlank(book_url[i])) {
					BaseVO rbObj = new BaseVO() ;
					rbObj.setKey1(book_name[i].trim());
					rbObj.setValue1(book_url[i].trim());
					refereeBookList.add(rbObj) ;					
				}
			}
		}
		cvset.setRefereeBookList(refereeBookList);
		
		String[] knowledgebase_name = request.getParameterValues("knowledgebase_name") ;
		String[] knowledgebase_url = request.getParameterValues("knowledgebase_url") ;
		List<BaseVO> knowledgeBaseList = new ArrayList() ; ;
		if (knowledgebase_name != null && knowledgebase_name.length > 0) {
			for (int i = 0 ; i < knowledgebase_name.length ; i++) {
				if (!stringIsBlank(knowledgebase_name[i]) ) {
//				if (!stringIsBlank(knowledgebase_name[i]) && !stringIsBlank(knowledgebase_url[i])) {
					BaseVO rbObj = new BaseVO() ;
					rbObj.setKey1(knowledgebase_name[i].trim());
					rbObj.setValue1(knowledgebase_url[i].trim());
					knowledgeBaseList.add(rbObj) ;					
				}
			}
		}
		cvset.setKnowledgeBaseList(knowledgeBaseList);
		
		String[] reference_name = request.getParameterValues("reference_name") ;
		String[] reference_url = request.getParameterValues("reference_url") ;
		List<BaseVO> referenceLatelyList = new ArrayList() ; ;
		if (reference_name != null && reference_name.length > 0) {
			for (int i = 0 ; i < reference_name.length ; i++) {
				if (!stringIsBlank(reference_name[i]) ) {
//				if (!stringIsBlank(reference_name[i]) && !stringIsBlank(reference_url[i])) {
					BaseVO rbObj = new BaseVO() ;
					rbObj.setKey1(reference_name[i].trim());
					rbObj.setValue1(reference_url[i].trim());
					referenceLatelyList.add(rbObj) ;					
				}
			}
		}
		cvset.setReferenceLatelyList(referenceLatelyList);
		//YHQ，2017-05-16，over
		
		cvset.setCv_set_type(cvs_form.getCv_set_type());//YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
		
		 boolean flag = localCVSetManage.editCVS(cvset);
		 if(flag == true){
			 //返回成功
			 map.put("message", "success");
			 //response.sendRedirect("CVSetManage.do");
			 //StrutsUtil.renderText(response, "success");
		 }else{
			 //返回失败
			 map.put("message", "fail");
			 //StrutsUtil.renderText(response, "fail");
			 //return null;
		 }
		 response.getWriter().write(JsonUtil.map2json(map));
		 response.getWriter().flush();
		 response.getWriter().close();
		 return null;
	}
	
	private boolean stringIsBlank(String strVal) {
		if (strVal == null) return true ;
		if (strVal.trim().equals("")) return true ;
		return false ;
	}

	private String get_CVS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		List<CVSet> View = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();	
		queryCVSet.setId(id);
		queryCVSet.setType(-10);
		View = localCVSetManage.getCVSetList(queryCVSet);
		request.setAttribute("View", View);
		
		List<UserImage> imageList = View.get(0).getUserImageList();
		String userImageNames = "";
		String userImageIds = "";
		if(imageList !=null && imageList.size() > 0 ){ 
			for (UserImage image:imageList) {
				userImageNames += image.getName() + ",";
				userImageIds += image.getId() + ",";
			}
		}else{}
		List<ExpertInfo> managerlist = View.get(0).getManagerList();
		String manager = "";
		String manager_id = "";
		if(managerlist!=null && managerlist.size()>0){
			for(ExpertInfo expertInfo: managerlist){
				manager += expertInfo.getName() + ",";
				manager_id += expertInfo.getId() + ",";
			}
		}
		List<ExpertInfo> teacherlist = View.get(0).getTeacherList();
		String teacher = "";
		String teacher_id = "";
		if(teacherlist!=null && teacherlist.size()>0){
			for(ExpertInfo expertInfo: teacherlist){
				teacher += expertInfo.getName() + ",";
				teacher_id += expertInfo.getId() + ",";
			}
		}
		
		List<ExpertInfo> otherTeacherlist = View.get(0).getOtherTeacherList();
		String otherTeacher = "";
		String otherTeacher_id = "";
		if(otherTeacherlist!=null && otherTeacherlist.size()>0){
			for(ExpertInfo expertInfo: otherTeacherlist){
				otherTeacher += expertInfo.getName() + ",";
				otherTeacher_id += expertInfo.getId() + ",";
			}
		}
		List<CVSchedule> cvScheduleList = View.get(0).getCVScheduleList();
		String cvschedule = "";
		String cvschedule_id = "";
		String cvschedule_scheduleId = "";
		if(cvScheduleList!=null && cvScheduleList.size()>0){
			for(CVSchedule cvshe: cvScheduleList){
				cvschedule += cvshe.getName() + ",";
				cvschedule_id += cvshe.getId() + ",";
				cvschedule_scheduleId += cvshe.getSchedule_id() + ",";
			}
		}
		List<PeixunOrg> orgList = new ArrayList<PeixunOrg>();
		orgList = View.get(0).getOrganizationList();
		String orgNames = "";
		String orgIds = "";
		if(orgList!=null && orgList.size()>0){
			for(PeixunOrg peixunOrg:orgList){		
				orgNames += peixunOrg.getName()+",";
				orgIds += peixunOrg.getId() + ",";
				
			}
		}
		userImageNames = StringUtils.substring(userImageNames, 0, userImageNames.length() -1);
		manager = StringUtils.substring(manager, 0, manager.length() -1);
		teacher = StringUtils.substring(teacher, 0, teacher.length() -1);
		orgNames = StringUtils.substring(orgNames, 0, orgNames.length() - 1);
		otherTeacher = StringUtils.substring(otherTeacher, 0, otherTeacher.length() -1);
		request.setAttribute("orgList", orgList);
		request.setAttribute("orgNames", orgNames);
		request.setAttribute("orgIds", orgIds);
		request.setAttribute("userImageNames", userImageNames);
		request.setAttribute("userImageIds", userImageIds);
		request.setAttribute("manager", manager);
		request.setAttribute("manager_id", manager_id);
		request.setAttribute("teacher", teacher);
		request.setAttribute("teacher_id", teacher_id);
		request.setAttribute("otherTeacher", otherTeacher);
		request.setAttribute("otherTeacher_id", otherTeacher_id);
		request.setAttribute("cvschedule", cvschedule);
		request.setAttribute("cvschedule_id", cvschedule_id);
		request.setAttribute("cvschedule_scheduleId", cvschedule_scheduleId);
		request.setAttribute("proId", id);
		
		request.setAttribute("imgFile", View.get(0).getFile_path());
		
		//判断当前用户是否审核过
		//根据项目id和登录的专家的id得到其审核记录
		String username = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		ExpertInfo expertInfo = this.localExpertManageManage.getExpertInfoByUsername(username);
		if(null != expertInfo){
			CvsetQualityHistory historyOwn = this.localCVSetManage.getCvsetQualityHistoryByCvsetAndExpert(id, expertInfo.getId());
			request.setAttribute("historyOwn", historyOwn);
		}
		List history=localCVSetManage.getCvsetQualityHistoryByCvsetAndExpertInfo(id);
		request.setAttribute("cvsetQualityHistory", history);
		if(history != null && history.size()>0){
			CvsetQualityHistory o = (CvsetQualityHistory)history.get(history.size()-1);
			request.setAttribute("optionType", o.getOpinionType());
		}
		List persion = localExpertManageManage.getExpertInfoNameByCvSetId(id);
		request.setAttribute("cvsetQualityPersion", persion);
		//查询排期信息
		List<CVSetScheduleFaceTeach> cvSetScheduleTeachList = localCVSetManage.queryCVSetScheduleFaceTeachByCVsetId(id);
		request.setAttribute("cvSetScheduleTeachList", cvSetScheduleTeachList);

		//YHQ，2017-05-15
		List<BaseVO> refereeBookList     = View.get(0).getRefereeBookList() ;
		List<BaseVO> knowledgeBaseList   = View.get(0).getKnowledgeBaseList() ;
		List<BaseVO> referenceLatelyList = View.get(0).getReferenceLatelyList() ;

		request.setAttribute("refereeBookList", getExamSourceIDs(refereeBookList));
		request.setAttribute("knowledgeBaseList", getExamSourceIDs(knowledgeBaseList));
		request.setAttribute("referenceLatelyList", getExamSourceIDs(referenceLatelyList));
		
		return "edit_page";
	}

	private List<ExamSource> getExamSourceIDs(List<BaseVO> referenceLatelyList) {
		List<ExamSource> list=new ArrayList<ExamSource>();
		String ids="";
			for (BaseVO baseVO : referenceLatelyList) {
			ids+=","+baseVO.getSource_id();
		}
		if(ids.length()>0){
			ids=ids.substring(1);
		}
		return localExamPropValFacade.getSourceValList(ids)  ;
	}

	private String getHanyuPinyinString(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String hanyuStr = ParamUtils.getParameter(request, "hanyuStr");
		String pinyinStr = "";
		
		for (char c:hanyuStr.toCharArray()) {
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
			if (pinyinArray != null) 	pinyinStr += pinyinArray[0].substring(0, 1).toUpperCase();
			else						pinyinStr += c;
		}
		
		StrutsUtil.renderText(response, pinyinStr);
		
		return null;
	}

	private String getDeepQuality(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		long unit_id = ParamUtils.getLongParameter(request, "unit_id", -1L);
		long[] image_ids = ParamUtils.getLongParametersFromString(request, "image_ids", -1L);

		/*
		if (unit_id < 0 || image_ids[0] < 0) return null;
		
		List<Long> subjectIdList = new ArrayList<Long>();
		List<Long> materialIdList = localCVManage.getMaterialIds(unit_id);
		for (Long m_id:materialIdList) {
			List<Long> ids = localMaterialManage.getMaterialPropById(m_id);
			for (Long id:ids) {
				ExamProp prop = localExamPropValFacade.getSysPropVal(id);
				if (prop != null && prop.getType() == 7 && !subjectIdList.contains(id)) subjectIdList.add(id);
			}
		}
		
		List<QualityModel> qualityList = new ArrayList<QualityModel>();
		List<QualityModel> qualityList1 = localQualityModelManage.getQualityModelListByZutiIds(subjectIdList);
		List<QualityModel> qualityList2 = localQualityModelManage.getDeepQualityModelListFromImageIds(image_ids);
		
		for (QualityModel qm1:qualityList1)
		for (QualityModel qm2:qualityList2)
			if (qm1.getId().equals(qm2.getId())) {
				qm1.setLevel(2L);
				qualityList.add(qm1);
				break;
			}
		
		List<QualityModel> stock = localCVManage.getQualityList(unit_id);
		
		JSONObject result = new JSONObject();
		result.put("list", qualityList);
		result.put("stock", stock);
		StrutsUtil.renderText(response, result.toString());
		*/
		
		long level = ParamUtils.getLongParameter(request, "level", -1L) ;
		long id = ParamUtils.getLongParameter(request, "id", -1L);
		
		if (level == 0L) {//能力模型第1级
			List<Long> userMageIdList = new ArrayList<Long>() ;
			if (image_ids != null && image_ids.length > 0) {
				for (int i = 0 ; i < image_ids.length ; i++) {
					userMageIdList.add(image_ids[i]) ;
				}
				
				Map argsLevel0 = new HashMap() ;
				argsLevel0.put("userMageIdList", userMageIdList) ;
				argsLevel0.put("unit_id", unit_id) ;
				List<QualityModel> qualityList = localQualityModelManage.getProjectScheduleUnitQualityModelLevelOneListByUserImage(argsLevel0) ;
				
				JSONObject result = new JSONObject();
				result.put("list", qualityList);		
				StrutsUtil.renderText(response, result.toString());
				return null ;
			}
		}else if (level == 1) { //能力模型第2级			
			if (id != -1L) {
				Map argsLevelOne = new HashMap() ;
				argsLevelOne.put("id", id) ;
				argsLevelOne.put("unit_id", unit_id) ;
				
				List<QualityModel> qualityList = localQualityModelManage.getProjectScheduleUnitQualityModelLevelTwoListByLevelOne(argsLevelOne) ;
				
				JSONObject result = new JSONObject();
				result.put("list", qualityList);		
				StrutsUtil.renderText(response, result.toString());
				return null ;
			}            
		}else if (level == 2) {//能力模型第3级
			if (id != -1L && image_ids != null && image_ids.length > 0) {
				List<Long> userMageIdList = new ArrayList<Long>() ;
				for (int i = 0 ; i < image_ids.length ; i++) {
					userMageIdList.add(image_ids[i]) ;
				}
				
				Map argsLevelTwo = new HashMap() ;
				argsLevelTwo.put("id", id) ;
				argsLevelTwo.put("unit_id", unit_id) ;
				argsLevelTwo.put("userMageIdList", userMageIdList) ;
				
                List<QualityModel> qualityList = localQualityModelManage.getProjectScheduleUnitQualityModelLevelThreeListByLevelTwoAndUserImage(argsLevelTwo) ;
				
				JSONObject result = new JSONObject();
				result.put("list", qualityList);		
				StrutsUtil.renderText(response, result.toString());
				return null ;
			}
		}else if (level == 3) {//能力模型第4级
			if (id != -1L) {
				Map argsLevelThree = new HashMap() ;
				argsLevelThree.put("id", id) ;
				argsLevelThree.put("unit_id", unit_id) ;				
				
				List<QualityModel> qualityList = localQualityModelManage.getProjectScheduleUnitQualityModelLevelFourListByLevelThree(argsLevelThree) ;
				
				JSONObject result = new JSONObject();
				result.put("list", qualityList);		
				StrutsUtil.renderText(response, result.toString());
				return null ;
			}            
		}
		
		
				
		return null;
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
	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}
	public MaterialManageManage getLocalMaterialManage() {
		return localMaterialManage;
	}
	public void setLocalMaterialManage(MaterialManageManage localMaterialManage) {
		this.localMaterialManage = localMaterialManage;
	}
	public CVManage getLocalCVManage() {
		return localCVManage;
	}
	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	public QualityModelManage getLocalQualityModelManage() {
		return localQualityModelManage;
	}
	public void setLocalQualityModelManage(QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}
	
	
}
