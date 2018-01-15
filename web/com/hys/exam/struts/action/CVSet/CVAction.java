package com.hys.exam.struts.action.CVSet;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.*;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.GroupClassInfoManage;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.CVForm;
import com.hys.exam.struts.form.CVUnitForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unused")
public class CVAction extends BaseAction {

	private CVManage localCVManage;	
	private GroupClassInfoManage groupClassInfoManage;
	private QualityModelManage qualityModelManage ;//YHQ 2017-03-02 ，保存克隆的课程单元id， 增加能力模型
	private CvLiveManage localCvLiveManage;
    private ExamPropValFacade localExamPropValFacade;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CV queryCV = new CV();
		
		String mode = ParamUtils.getParameter(request, "mode", "");
		String propIds = request.getParameter("propIds");
		String propNames = request.getParameter("propNames");
		String sname = request.getParameter("sname");
		String itemName = request.getParameter("item");
		String proId = request.getParameter("proId");
		String cvType = request.getParameter("cvType"); //YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）
		String cvSetType = request.getParameter("cvSetType"); //YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）
		String CurrentPage=request.getParameter("page");
		//课程名称
		String name = request.getParameter("name");
		if (mode.equals("add")) {
			return add(mapping,form,request,response);
		}else if(mode.equals("edit")){
			return edit(mapping,form,request,response);
		}else if(mode.equals("save")){
			return save(mapping,form,request,response);
		}else if(mode.equals("delete")){
			return delete(mapping,form,request,response);
		}else if(mode.equals("addUnion")){
			return addUnion(mapping,form,request,response);
		}else if(mode.equals("unionEdit")){
			return unionEdit(mapping,form,request,response);
		}else if(mode.equals("updateUnion")){
			return updateUnion(mapping,form,request,response);
		}else if(mode.equals("unitContentEdit")){
			return unitContentEdit(mapping,form,request,response);
		}else if(mode.equals("clone")){
			return courseClone(mapping,form,request,response);
		}else if(mode.equals("cloneCopy")){
			return cloneCopy(mapping,form,request,response);
		}else if(mode.equals("teacher")){
			return teacher(mapping,form,request,response);
		}else if(mode.equals("teacherSearch")){
			return teacherSearch(mapping,form,request,response);
		}else if(mode.equals("getCVInfoByAjax")){
			return getCVInfoByAjax(mapping,form,request,response);
		}else if(mode.equals("addUnionUpdate")){
			return addUnionUpdate(mapping,form,request,response);
		}else if(mode.equals("updateSave")){
			return updateSave(mapping,form,request,response);
		} else if (mode.equals("cloneUnitList")) {
			return cloneUnitList(mapping,form,request,response);
		} else if (mode.equals("swapUnit")) {
			return swapUnit(mapping,form,request,response);
		} else if(mode.equals("updatePoint")){
			return updatePoint(mapping,form,request,response);
		}else if(mode.equals("getUnit")){
			return getUnit(mapping,form,request,response);
		}else if(mode.equals("getUnitForCV")){
			return getUnitForCV(mapping,form,request,response);
		}else if(mode.equals("addUnionRef")){
			//保存课程与单元级联关系
			return addUnionRef(mapping,form,request,response);
		}else if(mode.equals("addUnionRefSource")){
			return addUnionRefSource(mapping,form,request,response);
		}else if(mode.equals("getTargetUnit")){
            return getTargetUnit(mapping,form,request,response);
        }else if(mode.equals("view")){
			return view(mapping,form,request,response);
		}else if(mode.equals("saveUnitSequence")){
			return saveUnitSequence(mapping,form,request,response);//YHQ，2017-05-24，保存单元的顺序
		}else if(mode.equals("cvDelFlag")){
			return cvDelFlag(mapping,form,request,response);//YHQ，2017-05-24，判断课程是否可以删除：true可以删除，false不能删除
		}else if(mode.equals("delCvUnit")){
			return delCvUnit(mapping,form,request,response);//YHQ，2017-05-24，删除课程单元
		}else if(mode.equals("updateName")){
			return updateName(mapping,form,request,response);//YHQ，2017-05-24，删除课程单元
		}else if(mode.equals("updateType")){
			return updateType(mapping,form,request,response);//YHQ，2017-05-24，删除课程单元
		}
		
		if(!StringUtils.checkNull(name)){
			queryCV.setName(name);
		}
		if(!StringUtils.checkNull(sname)){
			queryCV.setCreator(sname);
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
			queryCV.setCourseList(tempList);
		}

		if(!StringUtil.checkNull(itemName))
		{
			queryCV.setCvsetName(itemName);
//			queryCV.setName(itemName);
		}
		
		if(!StringUtil.checkNull(cvType)) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）
			queryCV.setFile_path(cvType);//YHQ，2017-06-05，这个字段很少用，项目为远程传：0,2，其它为1	
			request.setAttribute("cvType", cvType);
		}
		CVForm simpleForm = (CVForm)form;
		if(simpleForm.getCv_type()!=null && !simpleForm.getCv_type().equals("")) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）
			if(simpleForm.getCv_type() != -1){
				queryCV.setCv_type(simpleForm.getCv_type());//YHQ，2017-06-05，这个字段很少用，项目为远程传：0,2，其它为1	
				request.setAttribute("cvType2", simpleForm.getCv_type());
			}
		}
		
		if(simpleForm.getBrand()!=null && !simpleForm.getBrand().equals("")){
			queryCV.setBrand(simpleForm.getBrand());
			request.setAttribute("brand", simpleForm.getBrand());
		}
		
		if(!StringUtil.checkNull(cvSetType)) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）
			queryCV.setAnnouncement(cvSetType);//YHQ，2017-06-05，这个字段很少用，为项目类型：0,1
			queryCV.setCv_type(Integer.valueOf(cvSetType));
			request.setAttribute("cvSetType", cvSetType);
		}	
		
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		String sort = ParamUtils.getParameter(request, "sort", "u.reg_date");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion(sort);
		
		
		List<CV> list = new ArrayList<CV>();
//		pl.setFullListSize(list.size());
		localCVManage.getCVListByPage(pl,queryCV);
//		pl.setList(list);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", propNames);
		request.setAttribute("list", pl);
//		request.setAttribute("list", list);
		request.setAttribute("sname",sname);
		request.setAttribute("name", name);
		request.setAttribute("item", itemName);
		request.setAttribute("proId", proId);
		return "list";
	}
	
	/**
	 * updateType:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param mapping
	 * @param  @param form
	 * @param  @param request
	 * @param  @param response
	 * @param  @return    
	 * @return String    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private String updateType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		Long id = Long.valueOf(request.getParameter("id"));
		Integer type = Integer.valueOf(request.getParameter("type"));
		
		
		String name = request.getParameter("") ;
		
		
		CVUnit cvUnit = new CVUnit();
		
		cvUnit.setId(id);
		cvUnit.setType(type);
	
		
		localCVManage.updateCVUnitType(cvUnit);
		
		return null;
		
	}

	/**
	 * updateUnit:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param mapping
	 * @param  @param form
	 * @param  @param request
	 * @param  @param response
	 * @param  @return    
	 * @return String    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private String updateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = Long.valueOf(request.getParameter("id"));
		
		
		String name = request.getParameter("name") ;
		
		
		CVUnit cvUnit = new CVUnit();
		
		cvUnit.setId(id);
		cvUnit.setName(name);
	
		
		localCVManage.updateCVUnitName(cvUnit);
		
		return null;
		
	}

	//YHQ，2017-05-24，删除课程单元
	private String delCvUnit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		String cvId   = request.getParameter("cvId") ;
		String unitId = request.getParameter("unitId") ;
		String resultStr = "success" ;
		if (cvId != null && unitId != null) {
			boolean updateFlag = localCVManage.delCvUnit(cvId, unitId) ;
		} else {
			resultStr = "argsIsBlank" ;
		}
		
		JSONObject json = new JSONObject();
		json.put("result", resultStr);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	
	//YHQ，2017-05-24，判断课程是否可以删除：true可以删除，false不能删除
	private String cvDelFlag(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		String cvId = request.getParameter("cvId") ;
		String resultStr = "yes" ;
		if (cvId != null) {
			boolean updateFlag = localCVManage.cvDelFlag(cvId) ;
			if (!updateFlag) resultStr = "no" ;
		} else {
			resultStr = "argsIsBlank" ;
		}
		
		JSONObject json = new JSONObject();
		json.put("result", resultStr);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
		
	//YHQ，2017-05-24，保存单元的顺序
	private String saveUnitSequence(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		String unid = request.getParameter("unid") ;
		String resultStr = "success" ;
		if (unid != null) {
			String[] allUid = unid.split(",") ;
			if (allUid != null && allUid.length > 0) {
				boolean updateFlag = localCVManage.saveUnitSequence(allUid) ;
			} else {
				resultStr = "argsIsBlank" ;
			}
		} else {
			resultStr = "argsIsBlank" ;
		}
		
		JSONObject json = new JSONObject();
		json.put("result", resultStr);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	
	private String getUnitForCV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		long[] cvIds = ParamUtils.getLongParametersFromString(request, "id", 0);
		CV queryCV = new CV();
		CV cv = new CV();
		List<CV> cvList = new ArrayList<CV>();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		for(long cvId:cvIds){
			queryCV.setId(cvId);
			cv = localCVManage.getCVList(queryCV).get(0);
			cvList.add(cv);
		}
		
		JSONObject json = new JSONObject();
		json.put("result", cvList);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	private String getUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			Long CV_ID = ParamUtils.getLongParameter(request, "id",-1L);
			
			CV queryCV = new CV();
			queryCV.setId(CV_ID);
			List<CVUnit> cvUnitList = localCVManage.getCVUnitList(queryCV);
			JSONObject json = new JSONObject();
			json.put("result", cvUnitList);
			StrutsUtil.renderText(response, json.toString());
		return null;
	}


	private String updatePoint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Integer point = Integer.valueOf(ParamUtils.getIntParameter(request, "pointMode", 0));
		Integer state = Integer.valueOf(ParamUtils.getIntParameter(request, "stateMode", 0));
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		//YHQ，2017-04-04
		String pointMode = request.getParameter("pointMode") ;
		String stateMode = request.getParameter("stateMode") ;
		
		
		CVUnit cvUnit = new CVUnit();
		
		cvUnit.setId(id);
		if(!StringUtil.checkNull(pointMode)) cvUnit.setPoint(point); //YHQ，2017-04-04
		if(!StringUtil.checkNull(stateMode)) cvUnit.setState(state); //YHQ，2017-04-04
		
		localCVManage.updateCVUnit(cvUnit);
		
		return null;
	}


	private String swapUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id1 = ParamUtils.getLongParameter(request, "src_id", -1L);
		Long id2 = ParamUtils.getLongParameter(request, "target_id", -1L);
		
		if (id1 + id2 == -2) return null;
		
		if (id1.equals(id2)) {
			CVUnit unit = new CVUnit();		
			unit.setId(id1);
			List<CVUnit> list = localCVManage.getCVUnitList(unit);
			localCVManage.deleteUnit(id1);
		} else {
			
			CVUnit unit1 = new CVUnit();
			
			CVUnit temp = new CVUnit();
			
			unit1.setId(id1);
			List<CVUnit> list = localCVManage.getCVUnitList(unit1);
			
			unit1 = list.get(0);
			
			
			CVUnit unit2 = new CVUnit();
			unit2.setId(id2);
			list = localCVManage.getCVUnitList(unit2);
			
			unit2 = list.get(0);
			temp = unit1;
			unit1=unit2;
			unit2=temp;
			unit1.setId(id2);
			unit2.setId(id1);
			
			localCVManage.swapCVUnit(unit1,unit2);
			
		}
		
		StrutsUtil.renderText(response, "success");
		
		return null;
	}


	private String cloneUnitList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//克隆的课程id
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		//需要克隆的课程id
		Long classId = ParamUtils.getLongParameter(request, "classId", -1L);
		List<CV> cvList = new ArrayList<CV>();
		CV queryCV = new CV();
		queryCV.setId(id);
		cvList = localCVManage.getCVList(queryCV);
		List<CVUnit> result = localCVManage.getCloneCVUnitList(queryCV);
		List<CVUnit> resultC = new ArrayList<CVUnit>();
		//执行克隆
		if(result!=null && result.size()>0){
			for(int i=0;i<result.size();i++){
				List<GroupClassInfo> groupList = groupClassInfoManage.queryGroupClassContent(result.get(i).getId().intValue());
				//保存单元信息
				CVUnit c = new CVUnit();
				c.setIsBound(result.get(i).getIsBound());
				c.setContent(result.get(i).getContent());
				c.setName(result.get(i).getName());
				c.setPoint(result.get(i).getPoint());
				c.setState(result.get(i).getState());
				c.setType(result.get(i).getType());
				c.setQuota(result.get(i).getQuota());
				//执行保存
				Long unitId = localCVManage.addCVUnit(c);
				//保存课程与单元关联信息
				localCVManage.addCvRefUnit(classId.intValue(), unitId.intValue());	
				//保存组课信息
				if(groupList!=null && groupList.size()>0){
					GroupClassInfo group = groupList.get(0);
					if(group!=null && group.getId()>0){
						//设置单元id
						group.setClassId(unitId);
						//设置单元名称
						group.setClassName(c.getName());
						//设置课程名称
						if(cvList!=null && cvList.size()>0){
							//设置课程id
							group.setClassParentId(classId.intValue());
							//设置课程名称
							group.setClassParentName(cvList.get(0).getName());
						}
						groupClassInfoManage.addGroupClassInfo(group);
					}
				}
			}
		}
		
		
		
		
		//更新检索信息
		queryCV.setId(classId);
		resultC = localCVManage.getCloneCVUnitList(queryCV);
		//取集合的交集
		for(int i=0;i<result.size();i++){
			for(int j=0;j<resultC.size();j++){
				if(result.get(i).getName().equals(resultC.get(j).getName())){
					result.get(i).setId(resultC.get(j).getId());
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put("list", result);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}

	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2017-01-04
	 * @author   张建国
	 * @throws   Exception
	 * 方法说明：修改课程信息
	 */
	private String updateSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Long Id = Long.valueOf(request.getParameter("id"));
		String propIds = request.getParameter("propIds");
		String teacher_ids = request.getParameter("teacherIds");
		String other_Teacher_ids = request.getParameter("otherTeacherIds");
		JSONObject json = new JSONObject();
		CV queryCV= new CV();
		CVForm simpleForm = (CVForm)form;
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		List<CV> cvList = new ArrayList<CV>();
		CVUnit cvUnit = new CVUnit(); 
		//Long Id = simpleForm.getId();
		String courseName = simpleForm.getName();
		String serial = simpleForm.getSerial_number();
		String brand = simpleForm.getBrand();
		String introduction = simpleForm.getIntroduction();
		String filePath=simpleForm.getFile_path();
		String announcement = simpleForm.getAnnouncement();
		String creator = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
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
			queryCV.setCourseList(tempList);
		}
		if(!StringUtil.checkNull(other_Teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = other_Teacher_ids.split(",");
			Long[] other_TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) other_TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(other_TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setOtherTeacherList(tempList);
		}
		if(!StringUtil.checkNull(teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = teacher_ids.split(",");
			Long[] TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setTeacherList(tempList);
		}
		queryCV.setId(Id);
		queryCV.setName(courseName);
		queryCV.setSerial_number(serial);
		queryCV.setIntroduction(introduction);
		queryCV.setBrand(brand);
		queryCV.setAnnouncement(announcement);
		queryCV.setCreator(creator);
		//保存封面信息
		queryCV.setFile_path(filePath);
		cvUnit.setIsBound(0);
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		queryCV.setUnitList(cvUnitList);
		
		if(queryCV.getTeacherList()!=null && queryCV.getTeacherList().size()>0){
			//删除信息
			localCVManage.deleteTeacher(queryCV);
		}
		
		if(queryCV.getOtherTeacherList()!=null && queryCV.getOtherTeacherList().size()>0){
			//删除信息
			localCVManage.deleteTeacherO(queryCV);
		}
		
		queryCV.setCv_type(simpleForm.getCv_type());//YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）
		boolean flag=localCVManage.updateCV(queryCV);
		//修改直播关联表直播信息---taoliang
		try{
			Integer cvType = simpleForm.getCv_type();
			if(cvType != null){
				if(cvType == 2){//判断是否为直播的课程
					CvLive cvl = new CvLive();
					cvl.setCv_id(Id);
					cvl.setClass_name(courseName);
					/*cvl.setStart_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
							parse(request.getParameter("start_date")));
					cvl.setInvalid_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
							parse(request.getParameter("invalid_date")));*/
					String starttimeStr = request.getParameter("start_date");
					String invalidtimeStr = request.getParameter("invalid_date");
					try{
						cvl.setStart_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
							parse(starttimeStr));
					}catch(Exception ex){
						map.put("message", "errortime");
						StrutsUtil.renderText(response, JsonUtil.map2json(map));
						return null;
					}
					
					try{
						cvl.setInvalid_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
							parse(invalidtimeStr));
					}catch(Exception ex){
						map.put("message", "errortime");
						StrutsUtil.renderText(response, JsonUtil.map2json(map));
						return null;
					}
					cvl.setClass_no(request.getParameter("class_no")==null?
							"":request.getParameter("class_no").trim());
					cvl.setTeacher_join_url(request.getParameter("teacher_url"));
					cvl.setStudent_join_url(request.getParameter("student_url")==null?
							"":request.getParameter("student_url").trim());
					cvl.setTeacher_token(request.getParameter("teacher_token"));
					cvl.setAssistant_token(request.getParameter("assistant_token"));
					cvl.setStudent_token(request.getParameter("student_token")==null?
							"":request.getParameter("student_token").trim());
					cvl.setCourse_make_type(request.getParameter("makeType")==null?
							0:Integer.parseInt(request.getParameter("makeType")));
					localCVManage.updateLive(cvl);
				}
			}
		}catch(Exception ex){
			System.out.println("CVAction里【updateSave】方法修改当前课程直播信息异常......");
		}
		//********************************************************
		if(flag==true){
			map.put("message", "success");
			map.put("id", Id);
		}else{
			map.put("message", "fail");
		}
		json.put("result", map);
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		//cvList = localCVManage.getCVList(queryCV);
		//request.setAttribute("list", cvList);
		//String path = request.getContextPath() ;
		//response.sendRedirect(path +"/CVSet/CVManage.do?mode=list");
		return null;
	}


	private String addUnionUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long Id = Long.valueOf(request.getParameter("id"));
		
		CVUnitForm cvUnitForm = (CVUnitForm)form;
		CVUnit cvUnit = new CVUnit();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		CV queryCV = new CV();
		String name = cvUnitForm.getName();
		Integer state = cvUnitForm.getState();
		Integer point = cvUnitForm.getPoint();
		Integer type = cvUnitForm.getType();
		queryCV.setId(Id);
		cvUnit.setName(name);
		cvUnit.setState(state);
		cvUnit.setPoint(point);
		cvUnit.setIsBound(0);
		cvUnit.setType(type);
		List<CvLive> livelist = localCvLiveManage.queryCvLiveList(Id);
		if(livelist != null && livelist.size() > 0){//如果此课程为直播课程，则设置当前单元为直播课程的点播单元
			cvUnit.setUnitMakeType(2);
			//将该直播类型设置为点播类型
			CvLive live = livelist.get(0);
			live.setCourse_make_type(2);
			localCVManage.updateLive(live);
		}
		cvUnitList.add(cvUnit);
		Long newUnitId = localCVManage.addCVUnit(cvUnit);
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject json = new JSONObject();
		if(newUnitId  != null){
			CVUnit neUnit = new CVUnit();
			neUnit.setId(newUnitId);
			//neUnit.setIsBound(0);
			//int result = localCVManage.cloneCVUnitList(queryCV);
			List<CVUnit> result = localCVManage.getCVUnitList(neUnit);
			json.put("list", result);
			map.put("message", "success");
			map.put("unitId", newUnitId);
		}else{
			map.put("message", "fail");
		}
		queryCV.setUnitList(cvUnitList);
		
//		localCVManage.addUnionUpdate(queryCV);
		json.put("result", map);
		StrutsUtil.renderText(response, json.toString());
		
		return null;
	}


	private String teacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String teacherId = ParamUtils.getParameter(request, "teahcerId");
		List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
		if(!StringUtil.checkNull(teacherId)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = teacherId.split(",");
			Long[] TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempInfo = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new PropUnit();
				tempInfo[i].setId(TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			teacherList = localCVManage.getTeacherList(tempList);
			
		}
		else{
			
			teacherList = localCVManage.getTeacherList();
			
		}
		JSONObject json = new JSONObject();
		json.put("result",teacherList);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}


	@Transactional
	private String getCVInfoByAjax(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Long> newCvUnitIdList = new ArrayList<Long>() ; //YHQ 2017-03-02 ，保存克隆的课程单元id
		
		CV queryCV = new CV();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long proId = ParamUtils.getLongParameter(request, "proId", 0L);
		List<CV> list = new ArrayList<CV>();
		if (id > 0) {
			queryCV.setId(id);
			list = localCVManage.getCVList(queryCV);
		}
		//新课程id
		Long cvId = null;
		//新单元Id
		Long cvUnitId = null;
		//课程与单元级联信息
		Long cvRefUnitId = null;
		//课程表信息
		Long scheduleId = null;
		//执行结果
		Long result = null;
		if(proId!=null && proId!=0l){
		    //执行克隆操作
			if(list!=null && list.size()>0){
				//1.第一步保存需要克隆的课程信息
				CV cv = list.get(0);
				cv.setId(null);
				cvId = localCVManage.addCV(cv);
				//因此处课程被克隆，所以为了保持一致直播相关信息也进行克隆--taol
				try{
					List<CvLive> liveList = localCVManage.queryCvLiveList(id);//拿到原始课程对应的直播信息
					if(liveList != null && liveList.size() > 0){
						CvLive live = liveList.get(0);
						live.setId(null);
						live.setCv_id(cvId);
						Long newCvLiveId = localCVManage.addCvLive(live);//克隆一份信息
						//当直播课程信息添加成功后，给课程初始化一个单元
						{
							if(newCvLiveId > 0){
								CVUnit cvliveCvUnit = new CVUnit();
								cvliveCvUnit.setName(cv.getName());
								cvliveCvUnit.setPoint(1);
								cvliveCvUnit.setIsBound(0);
								cvliveCvUnit.setType(7);//设置单元的教学类型为7标识它为直播课程单元
								cvliveCvUnit.setQuota(80d);
								cvliveCvUnit.setUnitMakeType(0);
								//保存单元信息
								Long newUnitId = localCVManage.addCVUnit(cvliveCvUnit);
								if(newUnitId > 0){
									localCVManage.addCvRefUnit(Integer.valueOf(cv.getId().toString()), 
										Integer.valueOf(newUnitId.toString()));
								}
							}
						}
					}
				}catch(Exception ex){
					System.out.println("查询直播信息异常=========");
				}
				//********************************************
				//2.第二步保存需要克隆的单元信息
				if(list.get(0).getUnitList()!=null && list.get(0).getUnitList().size()>0){
					for(int i=0;i<list.get(0).getUnitList().size();i++){
						CVUnit cvUnit = list.get(0).getUnitList().get(i);
						List<GroupClassInfo> groupList = groupClassInfoManage.queryGroupClassContent(cvUnit.getId().intValue());												
						
						cvUnit.setId(null);
						cvUnitId = localCVManage.addCVUnit(cvUnit);
						//3.第三步保存需要克隆的课程与单元级联信息
						cvRefUnitId = localCVManage.addCvRefUnit(cvId.intValue(), cvUnitId.intValue());	
						//4.保存组课信息
						if(groupList!=null && groupList.size()>0){
							GroupClassInfo group = groupList.get(0);
							if(group!=null && group.getId()>0){
								//设置单元id
								group.setClassId(cvUnitId);
								//设置单元名称
								group.setClassName(cvUnit.getName());
								//设置课程id
								group.setClassParentId(cvId);
								//设置课程名称
								group.setClassParentName(cv.getName());
								groupClassInfoManage.addGroupClassInfo(group);
							}
						}
						
						newCvUnitIdList.add(cvUnitId) ; //YHQ 2017-03-02 ，保存克隆的课程单元id
					}
				}
				//5.保存课程信息表
				CVSchedule cvsc = new CVSchedule();
				cvsc.setSchedule_id(cvId);
				scheduleId = localCVManage.addCVSchedule(cvsc);
				//6.保存项目与课程信息
				result = localCVManage.addCVSetSchedule(proId.intValue(),scheduleId.intValue());
				
				//YHQ 2017-03-02 ，保存克隆的课程单元id， 增加能力模型
				for (Long newCvUnitId : newCvUnitIdList) {
					List<GroupClassInfo> newCvUnitGroupList = groupClassInfoManage.queryGroupClassContent(newCvUnitId.intValue()) ;
					if (newCvUnitGroupList != null && newCvUnitGroupList.size() > 0) {
						GroupClassInfo cvUnitGroupObj = newCvUnitGroupList.get(0);
						QualityModel qualityModel = new QualityModel() ;						
						
						if (cvUnitGroupObj != null) {
							String content = cvUnitGroupObj.getClassContent() ;
							if (!StringUtils.checkNull(content)) {
								qualityModel = new QualityModel() ;
								qualityModel.setId(newCvUnitId);
								qualityModel.setName(content);
								qualityModelManage.updateCvUnitRefQualityByGroupClass(qualityModel) ;//再更新
							} else {
								qualityModel.setId(newCvUnitId);
								qualityModelManage.insertCvUnitRefQualityByGroupClass(qualityModel) ;//先插入
							}
						}						
					}					
				}
				
			}
		}
        if(result!=null && result>0){
        	//根据项目id查询项目对应的课程及其单元信息
        	queryCV.setId(cvId);
			list = localCVManage.getCVList(queryCV);
        }else{
        	queryCV.setId(id);
			list = localCVManage.getCVList(queryCV);
        }
		//返回数据
		JSONObject jsonObj = new JSONObject();
		if (list != null && list.size() > 0){
			jsonObj.put("info", list.get(0));
		}
		StrutsUtil.renderText(response, jsonObj.toString());
		return null;
	}


	private String cloneCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		CVUnit cvUnit = new CVUnit();
		CV queryCV = new CV();
		List<CVUnit> cvUnitCloneList = new ArrayList<CVUnit>();
		List<CV> cvCloneList = new ArrayList<CV>();
		queryCV.setId(id);
		queryCV.setUnitList(cvUnitCloneList);
		cvCloneList = localCVManage.getCloneCVList(queryCV);
		cvUnitCloneList = localCVManage.getCloneCVUnitList(queryCV);
		
		JSONObject json =new JSONObject();
		json.put("cloneUnit", cvUnitCloneList);
		json.put("cloneCV", cvCloneList);
		json.put("id", id);
		StrutsUtil.renderText(response, json.toString());		
		
		return null;
	}

	private String courseClone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws FrameworkException {
		
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		//String sort = ParamUtils.getParameter(request, "sort", "u.reg_date");
		//String dir = ParamUtils.getParameter(request, "dir", "desc");
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion("id");
		pl.setSortDirection(SortOrderEnum.DESCENDING);
		
		//原课程id
		String propIds = request.getParameter("propIds");
		String propNames = request.getParameter("propNames");
		String sname = request.getParameter("sname");
		String name = request.getParameter("name");
		
		String classId = request.getParameter("classId");
		CV queryCV = new CV();
		if(!StringUtils.checkNull(name)){
			queryCV.setName(name);
		}
		if(!StringUtils.checkNull(sname)){
			queryCV.setCreator(sname);
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
			queryCV.setCourseList(tempList);
		}
		//List<CV> list = new ArrayList<CV>();
		//list = localCVManage.getCVList(queryCV);
		//request.setAttribute("clone",list);
		
		//新增-分页
		localCVManage.getCVListPage(pl, queryCV);
		request.setAttribute("clone", pl);
		request.setAttribute("classId", classId);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", propNames);
		request.setAttribute("sname",sname);
		request.setAttribute("name", name);
		return "clone";
	}

	private String unitContentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long Id = ParamUtils.getLongParameter(request, "id", -1L);
		CVUnit cvUnit = new CVUnit();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		cvUnit.setId(Id);
		
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		JSONObject Json = new JSONObject();
		Json.put("result", cvUnitList);
		StrutsUtil.renderText(response, Json.toString());
		return null;
	}

	private String updateUnion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			String tmp_content = request.getParameter("content");
			Long Id  = Long.valueOf(request.getParameter("id"));
			
			List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
			CVUnit cvUnit = new CVUnit();
			cvUnit.setId(Id);
			cvUnitList = localCVManage.getCVUnitList(cvUnit);
			cvUnit = cvUnitList.get(0);	

			if(tmp_content!=null && !tmp_content.equals("")){
				cvUnit.setContent(tmp_content.substring(3, tmp_content.length()-4));
				localCVManage.updateUnion(cvUnit);
				StrutsUtil.renderText(response, "success");
			}else{
				StrutsUtil.renderText(response, "contentIsNull");
			}
			
		return null;
	}

	private String unionEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		Long Id = ParamUtils.getLongParameter(request, "id",0L);
		CVUnit cvUnit = new CVUnit();
		
		CV queryCV = new CV();
		if(Id >0 ){
			queryCV.setId(Id);
			cvUnit.setIsBound(1);
			cvUnitList = localCVManage.getCVUnitList(queryCV);
		}else{
			cvUnit.setIsBound(0);
			cvUnitList = localCVManage.getCVUnitList(cvUnit);
		}
		
		
		request.setAttribute("list", cvUnitList);
		
		return "unionEdit";
	}

	private String addUnion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("name");
		Integer type =Integer.valueOf(request.getParameter("type"));
		Integer point = Integer.valueOf(request.getParameter("point"));
		
		CVUnit cvUnit = new CVUnit();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		cvUnit.setName(name);
		cvUnit.setPoint(point);
		cvUnit.setIsBound(0);
		cvUnit.setType(type);
		//保存单元信息
		Long newUnitId = localCVManage.addCVUnit(cvUnit);
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject json = new JSONObject();
		/*cvUnitList = localCVManage.getCVUnitList(cvUnit);
		request.setAttribute("unionList", cvUnitList);*/
//		String path = request.getContextPath() ;
//		response.sendRedirect(path +"/CVSet/CVManage.do?mode=add&view=2");
		if(newUnitId  != null){
		/*	StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}*/
			//Long id = ParamUtils.getLongParameter(request, "id", -1L);
			CVUnit neUnit = new CVUnit();
			neUnit.setId(newUnitId);
			//neUnit.setIsBound(0);
			//int result = localCVManage.cloneCVUnitList(queryCV);
			List<CVUnit> result = localCVManage.getCVUnitList(neUnit);
			json.put("list", result);
			map.put("message", "success");
			map.put("unitId", newUnitId);
		}else{
			map.put("message", "fail");
		}
		json.put("result", map);
		StrutsUtil.renderText(response, json.toString());
		/*cvUnitList.add(cvUnit);
		CV queryCV = new CV();
		queryCV.setUnitList(cvUnitList);
		localCVManage.addCV(queryCV);*/
		return null;
	}
	
	/**
	 * @author  ZJG
	 * @param
	 * @return  Long
	 * @time    2016-12-27
	 * 方法说明    保存课程单元关联表信息
	 */
	private String addUnionRef(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cvId = request.getParameter("cvId");
		String unitId =request.getParameter("unitId");
		int cvIdi = 0;
		int unitIdi = 0;
		Long newUnitId = null ;
		Map<String,String> map = new HashMap<String,String>();
		if(cvId!=null && !"".equals(cvId)){
			cvIdi = Integer.parseInt(cvId);
		}
		if(unitId!=null && !"".equals(unitId)){
			unitIdi = Integer.parseInt(unitId);
		}
		if(cvIdi!=0 && unitIdi!=0){
			//保存课程与单元关联信息
			newUnitId = localCVManage.addCvRefUnit(cvIdi, unitIdi);
		}
		if(newUnitId>0){
			map.put("message", "success");
		}else{
			map.put("message", "fail");
		}
		JSONObject json = new JSONObject();
		json.put("result", map);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	/**
	 * @author
	 * @param
	 * @return  Long
	 * @time    2017年12月27日
	 * 方法说明    保存课程单元关联来源信息
	 */
	private String addUnionRefSource(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cvId = request.getParameter("cv_unit_Id");
		String extend_read =request.getParameter("extend_read");
		String chooseSourseIDs =request.getParameter("chooseSourseIDs");
		String key_nums =request.getParameter("key_nums");
		String key_words =request.getParameter("key_words");
		int cvIdi = 0;
		int unitIdi = 0;
		Long newUnitId = null ;
		Map<String,String> map = new HashMap<String,String>();
		if(cvId!=null && !"".equals(cvId)){
			cvIdi = Integer.parseInt(cvId);
		}
		if(cvIdi!=0 ){
			newUnitId = localCVManage.addUnionRefSource(cvIdi, extend_read, chooseSourseIDs, Integer.parseInt(key_nums), key_words);
		}
		JSONObject json = new JSONObject();
		if(newUnitId>0){
			json.put("message", "success");
//			json.put("result", map);
		}else{
			json.put("message", "fail");
		}
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	/**
	 * @author
	 * @param
	 * @return  Long
	 * @time    2017年12月27日
	 * 方法说明    获取课程单元关联来源信息
	 */
	private String getTargetUnit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cvId = request.getParameter("cv_unit_Id");

		int cvIdi = 0;
		int unitIdi = 0;
		Long newUnitId = null ;
        JSONObject json = new JSONObject();
        CVUnit cvUnit=new CVUnit();
        cvUnit= localCVManage.getCVUnit(Long.valueOf(cvId));
        String chooseSourseIDs="";
		if(cvUnit!=null ){
            List<CvUnitRefSource> cvUnitRefSourceList = localCVManage.getCVUnitRefSourceList(cvUnit.getId());
            for (CvUnitRefSource cvUnitRefSource : cvUnitRefSourceList) {
                chooseSourseIDs+=cvUnitRefSource.getSource_id()+",";
            }
            json.put("sourceBaseList", getExamSourceIDs(cvUnitRefSourceList));
            json.put("extend_read", cvUnit.getContent());
            json.put("chooseSourseIDs", chooseSourseIDs);
            json.put("key_nums",cvUnit.getQuota());
            json.put("key_words", cvUnit.getKey_words());
            json.put("message", "success");
		}else{
            json.put("message", "fail");
        }
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
    private List<ExamSource> getExamSourceIDs(List<CvUnitRefSource> referenceLatelyList) {
        List<ExamSource> list=new ArrayList<ExamSource>();
        String ids="";
        for (CvUnitRefSource baseVO : referenceLatelyList) {
            ids+=","+baseVO.getSource_id();
        }
        if(ids.length()>0){
            ids=ids.substring(1);
        }
        return localExamPropValFacade.getSourceValList(ids)  ;
    }
	private String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		CV queryCV = new CV();
		queryCV.setId(id);
		boolean flag=localCVManage.delete(queryCV);
		if(flag == true){
			//添加删除直播相关信息
			try{
				CvLive cvlive = new CvLive();
				cvlive.setCv_id(id);
				localCVManage.delCvLive(cvlive);
			}catch(Exception ex){}
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2017-01-04
	 * @author   张建国
	 * @throws   Exception
	 * 方法说明： 保存课程信息
	 */
	private String save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String propIds = request.getParameter("propIds");
		String teacher_ids = request.getParameter("teacherIds");
		String other_Teacher_ids = request.getParameter("otherTeacherIds");
		CV queryCV= new CV();
		CVForm simpleForm = (CVForm)form;
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		List<CV> cvList = new ArrayList<CV>();
		CVUnit cvUnit = new CVUnit(); 
		Long Id = simpleForm.getId();
		String courseName = simpleForm.getName();
		String serial = simpleForm.getSerial_number();
		String brand = simpleForm.getBrand();
		String introduction = simpleForm.getIntroduction();
		//String filePath=simpleForm.getFile_path();
		String announcement = simpleForm.getAnnouncement();
		//外链
		Integer is_add_out_chain = simpleForm.getIs_add_out_chain();
		String cv_url=simpleForm.getCv_url();
		String cv_address=simpleForm.getCv_address();
		Integer is_need_apply=simpleForm.getIs_need_apply();
		Integer apply_num=simpleForm.getApply_num();
		String creator = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		FormFile formFile;
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
			queryCV.setCourseList(tempList);
		}
		if(!StringUtil.checkNull(teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = teacher_ids.split(",");
			Long[] TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setTeacherList(tempList);
		}
		if(!StringUtil.checkNull(other_Teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = other_Teacher_ids.split(",");
			Long[] other_TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) other_TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(other_TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setOtherTeacherList(tempList);
		}
		//保存课程封面
		queryCV.setFile_path(simpleForm.getFile_path());
		queryCV.setName(courseName);
		queryCV.setSerial_number(serial);
		queryCV.setBrand(brand);
		queryCV.setIntroduction(introduction);
		queryCV.setAnnouncement(announcement);
		queryCV.setCreator(creator);
		queryCV.setIs_add_out_chain(is_add_out_chain);
		queryCV.setCv_url(cv_url);
		queryCV.setCv_address(cv_address);
		queryCV.setIs_need_apply(is_need_apply);
		queryCV.setApply_num(apply_num);
		cvUnit.setIsBound(0);
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		queryCV.setUnitList(null);
		Map<String,Object> map = new HashMap<String,Object>();
		queryCV.setCv_type(simpleForm.getCv_type());//YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）
		Long id_=localCVManage.addCV(queryCV);
		//判断是否保存成功
		if(id_>0){
			//当课程保存成功后，判断此课程是否为直播课程，如果是，保存信息--taoliang
			Integer cvType = simpleForm.getCv_type();
			if(cvType != null){
				if(cvType == 2){//判断是否为直播的课程
					try{//目前先排除关联表引发的课程创建异常
						CvLive cvl = new CvLive();
						cvl.setClass_name(courseName);
						String starttimeStr = request.getParameter("start_date");
						String invalidtimeStr = request.getParameter("invalid_date");
						try{
							cvl.setStart_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
								parse(starttimeStr));
						}catch(Exception ex){
							map.put("message", "errortime");
							StrutsUtil.renderText(response, JsonUtil.map2json(map));
							return null;
						}
						
						try{
							cvl.setInvalid_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
								parse(invalidtimeStr));
						}catch(Exception ex){
							map.put("message", "errortime");
							StrutsUtil.renderText(response, JsonUtil.map2json(map));
							return null;
						}
						
						cvl.setClass_no(request.getParameter("class_no")==null?
								"":request.getParameter("class_no").trim());
						cvl.setTeacher_join_url(request.getParameter("teacher_url"));
						cvl.setStudent_join_url(request.getParameter("student_url")==null?
								"":request.getParameter("student_url").trim());
						cvl.setTeacher_token(request.getParameter("teacher_token"));
						cvl.setAssistant_token(request.getParameter("assistant_token"));
						cvl.setStudent_token(request.getParameter("student_token")==null?
								"":request.getParameter("student_token").trim());
						cvl.setCv_id(id_);
						
						Long _cvliveId = localCVManage.addCvLive(cvl);
						
						//当直播课程信息添加成功后，给课程初始化一个单元
						/*{
							if(_cvliveId > 0){
								CVUnit cvliveCvUnit = new CVUnit();
								cvliveCvUnit.setName(courseName);
								cvliveCvUnit.setPoint(1);
								cvliveCvUnit.setIsBound(0);
								cvliveCvUnit.setType(7);//设置单元的教学类型为7标识它为直播课程单元
								cvliveCvUnit.setQuota(80d);
								cvliveCvUnit.setUnitMakeType(0);
								//保存单元信息
								Long newUnitId = localCVManage.addCVUnit(cvliveCvUnit);
								if(newUnitId > 0){
									localCVManage.addCvRefUnit(Integer.parseInt(id_.toString()), 
										Integer.valueOf(newUnitId.toString()));
								}
							}else{
								map.put("message", "fail");
								StrutsUtil.renderText(response, JsonUtil.map2json(map));
								return null;
							}
							
						}*/
						
					}catch(Exception ex){
						System.out.println("保存课程信息时当课程选择为直播时【保存直播信息异常=====】"+ex);
						map.put("message", "errorforzhiboadd");
						StrutsUtil.renderText(response, JsonUtil.map2json(map));
						return null;
						
					}
				}
			}
			map.put("message", "success");
			map.put("id", id_);
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}

	private String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id =Long.valueOf(request.getParameter("id"));
		
		CV queryCV = new CV();
		List<CV> cvList = new ArrayList<CV>();
		queryCV.setId(id);
		cvList = localCVManage.getCVList(queryCV);
		
		List<PropUnit> courseList = new ArrayList<PropUnit>();
		String propIds = "";
		String propNames = "";
		if(cvList != null && cvList.size() > 0)
		{
			courseList = cvList.get(0).getCourseList();
			for (PropUnit prop:courseList) {
			propIds += prop.getId() + ",";
			propNames += prop.getName()+ ",";
			}
		}
		String teacherIds ="";
		String teacherNames = "";
		if(cvList != null && cvList.size() > 0){
			List<ExpertInfo> teacherList = cvList.get(0).getTeacherList();
			for(ExpertInfo teacher:teacherList){
				teacherIds += teacher.getId()+",";
				teacherNames += teacher.getName()+",";
			}
		}
		String otherTeacherIds = "";
		String otherTeacherNames ="";
		if(cvList != null && cvList.size() > 0){
			List<ExpertInfo> otherTeacherList = cvList.get(0).getOtherTeacherList();
			for(ExpertInfo other:otherTeacherList){
				otherTeacherIds +=other.getId()+",";
				otherTeacherNames +=other.getName()+",";
			} 
		}
		
		List<CVUnit> cvUnitList = cvList.get(0).getUnitList();
		
		//当课程类型为直播时拿到该课程关联的直播信息
		CvLive live = null;
		try{
			if(cvList.get(0).getCv_type()!= null && cvList.get(0).getCv_type() == 2){
				List<CvLive> liveList = localCVManage.queryCvLiveList(id);//拿到对应课程对应的直播信息
				if(liveList != null && liveList.size() > 0){
					live = liveList.get(0);
					request.setAttribute("live", live);
				}
			}
		}catch(Exception ex){
			System.out.println("CVAction里方法【edit】拿课程对应的直播信息异常");
		}
		
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames",propNames);
		request.setAttribute("teacherIds",teacherIds);
		request.setAttribute("teacher",teacherNames);
		request.setAttribute("otherTeacher", otherTeacherNames);
		request.setAttribute("otherTeacherIds",otherTeacherIds);
		request.setAttribute("unionList",cvUnitList);
		request.setAttribute("info", cvList.get(0));
		request.setAttribute("brand", cvList.get(0).getBrand());
		
		request.setAttribute("id", id);
		
		//判断该课程是否有直播回放----taoliang
		CvLiveCourseware record = new CvLiveCourseware();
		record.setCv_id(id);
		List<CvLiveCourseware> wareList = localCvLiveManage.getCvLiveCoursewareList(record);
		if(wareList != null && wareList.size() > 0){
			request.setAttribute("isExistWare", 1);//1.表示存在课件回放信息
			request.setAttribute("wareList", wareList);//1.表示存在课件回放信息
		}else{
			request.setAttribute("isExistWare", 0);//1.表示不存在课件回放信息
		}
		
		//屏蔽写死路径
		//request.setAttribute("imgFile", "\\" + Constants.UPLOAD_FILE_PATH_CV + "\\" + id);
		return "edit";
	}
	
	/**
	 * @param   ActionMapping
	 * @return  String
	 * @time    2017-01-04
	 * @author  张建国
	 * 方法说明：根据教师名称查询教师信息集合
	 */
	private String teacherSearch(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		String name = ParamUtils.getParameter(request, "name");
		List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
		if(!StringUtil.checkNull(name)){
			teacherList = localCVManage.getTeacherListByName(name);
		}else{
			teacherList = localCVManage.getTeacherList();
		}
		JSONObject json = new JSONObject();
		json.put("result",teacherList);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}

	private String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
	
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		CVUnit cvUnit = new CVUnit(); 
		cvUnit.setIsBound(0);
		
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		
		request.setAttribute("unionList", cvUnitList);
		//request.setAttribute("view", ParamUtils.getParameter(request, "view"));
		
		Date currentTime = new Date();
		SimpleDateFormat sdfObj = new SimpleDateFormat("yyyyMMddHHmmss");
		String serial_number = sdfObj.format(currentTime) ;
		serial_number += gainRandomByNum(4) ;
		request.setAttribute("serial_number", serial_number);
		return "add";
	}
	
	//浜х敓n浣嶆暟瀛楅殢鏈烘暟
	private String gainRandomByNum(int strLength) {
		Random rmObj = new Random();  	      
	    // 获得随机数  
	    double pross = (1 + rmObj.nextDouble()) * Math.pow(10, strLength);  	  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  	  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);						
	}

	public CVManage getLocalCVManage() {
		return localCVManage;
	}

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}
	public GroupClassInfoManage getGroupClassInfoManage() {
		return groupClassInfoManage;
	}
	public void setGroupClassInfoManage(GroupClassInfoManage groupClassInfoManage) {
		this.groupClassInfoManage = groupClassInfoManage;
	}
		
	public QualityModelManage getQualityModelManage() {//YHQ 2017-03-02 ，保存克隆的课程单元id， 增加能力模型
		return qualityModelManage;
	}
	public void setQualityModelManage(QualityModelManage qualityModelManage) {//YHQ 2017-03-02 ，保存克隆的课程单元id， 增加能力模型
		this.qualityModelManage = qualityModelManage;
	}
	
	/**
	 * @author scp 查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private String view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id =Long.valueOf(request.getParameter("id"));
		
		CV queryCV = new CV();
		List<CV> cvList = new ArrayList<CV>();
		queryCV.setId(id);
		cvList = localCVManage.getCVList(queryCV);
		
		List<PropUnit> courseList = new ArrayList<PropUnit>();
		String propIds = "";
		String propNames = "";
		if(cvList != null && cvList.size() > 0)
		{
			courseList = cvList.get(0).getCourseList();
			for (PropUnit prop:courseList) {
			propIds += prop.getId() + ",";
			propNames += prop.getName()+ ",";
			}
		}
		String teacherIds ="";
		String teacherNames = "";
		if(cvList != null && cvList.size() > 0){
			List<ExpertInfo> teacherList = cvList.get(0).getTeacherList();
			for(ExpertInfo teacher:teacherList){
				teacherIds += teacher.getId()+",";
				teacherNames += teacher.getName()+",";
			}
		}
		String otherTeacherIds = "";
		String otherTeacherNames ="";
		if(cvList != null && cvList.size() > 0){
			List<ExpertInfo> otherTeacherList = cvList.get(0).getOtherTeacherList();
			for(ExpertInfo other:otherTeacherList){
				otherTeacherIds +=other.getId()+",";
				otherTeacherNames +=other.getName()+",";
			} 
		}
		
		List<CVUnit> cvUnitList = cvList.get(0).getUnitList();
		
		//当课程类型为直播时拿到该课程关联的直播信息
		CvLive live = null;
		try{
			if(cvList.get(0).getCv_type()!= null && cvList.get(0).getCv_type() == 2){
				List<CvLive> liveList = localCVManage.queryCvLiveList(id);//拿到对应课程对应的直播信息
				if(liveList != null && liveList.size() > 0){
					live = liveList.get(0);
					request.setAttribute("live", live);
				}
			}
		}catch(Exception ex){
			System.out.println("CVAction里方法【edit】拿课程对应的直播信息异常");
		}
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames",propNames);
		request.setAttribute("teacherIds",teacherIds);
		request.setAttribute("teacher",teacherNames);
		request.setAttribute("otherTeacher", otherTeacherNames);
		request.setAttribute("otherTeacherIds",otherTeacherIds);
		request.setAttribute("unionList",cvUnitList);
		request.setAttribute("info", cvList.get(0));
		request.setAttribute("brand", cvList.get(0).getBrand());
		
		request.setAttribute("id", id);
		
		//判断该课程是否有直播回放----taoliang
		CvLiveCourseware record = new CvLiveCourseware();
		record.setCv_id(id);
		List<CvLiveCourseware> wareList = localCvLiveManage.getCvLiveCoursewareList(record);
		if(wareList != null && wareList.size() > 0){
			request.setAttribute("isExistWare", 1);//1.表示存在课件回放信息
			request.setAttribute("wareList", wareList);//1.表示存在课件回放信息
		}else{
			request.setAttribute("isExistWare", 0);//0.表示不存在课件回放信息
		}
		
		//屏蔽写死路径
		//request.setAttribute("imgFile", "\\" + Constants.UPLOAD_FILE_PATH_CV + "\\" + id);
		return "view";
	}

	public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}

	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}

    public ExamPropValFacade getLocalExamPropValFacade() {
        return localExamPropValFacade;
    }

    public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
    }
}