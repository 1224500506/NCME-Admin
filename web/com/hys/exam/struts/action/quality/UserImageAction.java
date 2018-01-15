package com.hys.exam.struts.action.quality;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.*;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.service.local.SystemLogManage;
import com.hys.exam.utils.CusAccessObjectUtil;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;


import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;

import com.hys.exam.service.local.ExamPropValManage;
import com.hys.exam.service.local.UserImageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;


import com.hys.framework.web.action.BaseAction;

public class UserImageAction extends BaseAction{

	private UserImageManage localUserImageManage;
	private ExamPropValFacade localExamPropValFacade;
	private SystemLogManage systemLogManage;
	public SystemLogManage getSystemLogManage() {
		return systemLogManage;
	}

	public void setSystemLogManage(SystemLogManage systemLogManage) {
		this.systemLogManage = systemLogManage;
	}
	@SuppressWarnings("null")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long dutyId =ParamUtils.getLongParameter(request, "duty", -1L);
		
		Long pageId = ParamUtils.getLongParameter(request, "pageId",-1L) ;
		String returnStr = "";
		String method=RequestUtil.getParameter(request, "mode");
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		String sname = request.getParameter("sname");
		String propIds = request.getParameter("propIds"); 
		
		String departmentName = request.getParameter("propNames");
		
		if(method.equals("delete")){
			return del(mapping,form,request,response);
		}else if(method.equals("update")){
			return update(mapping,form,request,response);
		}else if(method.equals("generalImageAdd")){
			return getGeneralUserImageInfoByAjax(mapping,form,request,response);
		}else if(method.equals("userImageDuty")){
			return getUserImageDuty(mapping,form,request,response);
		}else if(method.equals("add")){
			return addUserImage(mapping,form,request,response);
		}if(method.equals("special")){
			return getSpecialUserImage(mapping,form,request,response);
		}else if(method.equals("getSpecialImage")){
			return getSpecialUserImageInfo(mapping,form,request,response);
		}
		else if(method.equals("uhaha")){
			return uhaha(mapping,form,request,response);
		}else if(method.equals("guide")){
			return getUserTypeByAjax(mapping,form,request,response);
		}else if(method.equals("guide_userImage")){
			return getUserImageByAjax(mapping,form,request,response);
		}else if(method.equals("xiangmu")){
			return xiangmu(mapping,form,request,response);
		}else if(method.equals("getGeneralImage")){
			return getGeneralImage(mapping,form,request,response);
		}else if(method.equals("getXueke")){
			return getXueke(mapping,form,request,response);
		}

		UserImage userImage = new UserImage();
		List<PropUnit> dutyList = new ArrayList<PropUnit>();
		if(!StringUtil.checkNull(sname)) {
			ModelType modelType = new ModelType();
			modelType.setName(sname);
			userImage.setType(modelType);
		}
		
	
		if(id != -1 && pageId == -1)
		{
			pageId = id;
		}
		if(pageId != -1){
			id = pageId;
		}
		if (id != -1) {
			ModelType modelType = new ModelType();
			modelType.setId(id);
			
			//modelType.setName(sname);
			userImage.setName(sname);
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			if(!StringUtil.checkNull(propIds)){
				String[] IDS = propIds.split(",");
				Long[] departmentIDS = new Long[IDS.length];
				for(int i =0;i<IDS.length;i++) departmentIDS[i] = Long.valueOf(IDS[i].trim());
//				String[] departmentNames = departmentName.split(",");
				PropUnit[] tempProp = new PropUnit[IDS.length];
				for(int i=0;i<IDS.length;i++)
				{
					tempProp[i] = new PropUnit();
					tempProp[i].setId(departmentIDS[i]);
//					tempProp[i].setName(departmentNames[i]);
					tempList.add(tempProp[i]);
				}
				userImage.setDepartmentPropList(tempList);
			}
			if(dutyId != -1){
				GeneralUserImage  generalUserImage = new GeneralUserImage();
				SpecialUserImage specialUserImage = new SpecialUserImage();
				List<PropUnit> dutyPropList = new ArrayList<PropUnit>();
				PropUnit temp = new PropUnit();
				temp.setId(dutyId);
				dutyPropList.add(temp);
				generalUserImage.setDutyPropList(dutyPropList);
				specialUserImage.setDutyPropList(dutyPropList);
				userImage.setGeneralUserImage(generalUserImage);
				//userImage.setSpecialUserImage(specialUserImage);
			}
			userImage.setType(modelType);
			dutyList = localUserImageManage.getDutyList(id);
			returnStr = "list2";
		}
		
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		String sort = ParamUtils.getParameter(request, "sort", "id");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion(sort);
		if (dir.equals("asc"))
			pl.setSortDirection(SortOrderEnum.ASCENDING);
		else
			pl.setSortDirection(SortOrderEnum.DESCENDING);
		
		
		 localUserImageManage.getUserImagePageList(pl,userImage);
		/*if(list.size()==0 ) {
			userImage = null;
			
			list.add(userImage);
		}*/
//		List<PropUnit> dutyList = localUserImageManage.getDutyList();
		request.setAttribute("dutyList", dutyList);
		
		if(pl.getList().size()==1 &&StringUtil.checkNull(((UserImage)pl.getList().get(0)).getType().getName()) ){
			
			request.setAttribute("userImage", pl.getList().get(0));	
			//pl.setList(null); 哪位同事加的这一行，会导致 能力管理->人物画像->一级查询中，根据“人物类型”无法查询到数据
		}else{
			if( pl.getList().size() != 0){
				
				request.setAttribute("userImage", pl.getList().get(0));	
			}else{
				pl.setList(null);
			}
			
		}
			
			request.setAttribute("list", pl);
	
		request.setAttribute("sname",sname);
		request.setAttribute("pageId", pageId);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", departmentName);
		request.setAttribute("propNames03", request.getParameter("propNames02"));
		request.setAttribute("dutyId", dutyId);
		request.setAttribute("id", id);
		
		if (returnStr.equals("")) returnStr = "list1";
		return returnStr;
	}


	private String getGeneralImage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String area = ParamUtils.getParameter(request, "area");
		String hospital = ParamUtils.getParameter(request, "hospital");
		String duty = ParamUtils.getParameter(request,"duty");
		
		List<PropUnit> AreaList = new ArrayList<PropUnit>();
		List<PropUnit> HospitalList = new ArrayList<PropUnit>();
		List<PropUnit> DutyList = new ArrayList<PropUnit>();
		if(!StringUtils.checkNull(area)){
			String[] IDS = area.split(",");
			Long[] ids = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) ids[i] = Long.valueOf(IDS[i].trim());
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			PropUnit[] temp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				temp[i] = new PropUnit();
				temp[i].setId(ids[i]);
				tempList.add(temp[i]);
			}
			AreaList = localUserImageManage.getAreaList(tempList);
			
			
		}
		if(!StringUtils.checkNull(duty)){
			String[] IDS = duty.split(",");
			Long[] ids = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) ids[i] = Long.valueOf(IDS[i].trim());
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			PropUnit[] temp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				temp[i] = new PropUnit();
				temp[i].setId(ids[i]);
				tempList.add(temp[i]);
			}
			DutyList = localUserImageManage.getDutyList(tempList);
			
			
		}
		
		if(!StringUtils.checkNull(hospital)){
			String[] IDS = hospital.split(",");
			Long[] ids = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) ids[i] = Long.valueOf(IDS[i].trim());
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			PropUnit[] temp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				temp[i] = new PropUnit();
				temp[i].setId(ids[i]);
				tempList.add(temp[i]);
			}
			HospitalList = localUserImageManage.getHospitalList(tempList);
			
			
		}
		
		JSONObject json = new JSONObject();
		json.put("area",AreaList);
		json.put("duty", DutyList);
		json.put("hospital", HospitalList);
		StrutsUtil.renderText(response, json.toString());
		
		return null;
	}


	private String xiangmu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		UserImage userImage = new UserImage();
		userImage.setId(id);
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		JSONObject result = new JSONObject();
		result.put("result",list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getUserImageByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<UserImage> list = new ArrayList<UserImage>();
		Long id = ParamUtils.getLongParameter(request, "id",0L);
		String propIds = request.getParameter("propIds"); 
		UserImage userImage = new UserImage();
		ModelType modelType = new ModelType();		
		if(id != 0L)
			modelType.setId(id);
		userImage.setType(modelType);

		List<PropUnit> xuekel = new ArrayList<PropUnit>();
		
		if(!StringUtil.checkNull(propIds)){
			String[] IDS = propIds.split(",");
			Long[] departmentIDS = new Long[IDS.length];
			for(int i =0;i<IDS.length;i++) departmentIDS[i] = Long.valueOf(IDS[i].trim());
//			String[] departmentNames = departmentName.split(",");
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++)
			{
				tempProp[i] = new PropUnit();
				tempProp[i].setId(departmentIDS[i]);
//				tempProp[i].setName(departmentNames[i]);
				xuekel.add(tempProp[i]);
			}
			
			userImage.setDepartmentPropList(xuekel);
			list = localUserImageManage.getUserImageListByXueke(userImage);
		}else{
			list = localUserImageManage.getUserImageList(userImage);
		}
		JSONObject result = new JSONObject();
		result.put("result",list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getUserTypeByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserImage userImage = new UserImage();
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		JSONObject result = new JSONObject();
		result.put("userLeixing", list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getSpecialUserImage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long typeId = ParamUtils.getLongParameter(request, "typeid", 0L);
	//2017/01/11, Add By IE
		String level = ParamUtils.getParameter(request, "level");
		List<PropUnit> majorList = new ArrayList<PropUnit>();
		
		List<PropUnit> dutyList = localUserImageManage.getDutyList(typeId);
		List<PropUnit> majorLvlList = localUserImageManage.getMajorLvlList();
		if(!StringUtil.checkNull(level))
			majorList = localUserImageManage.getMajorList(level);
		else 
			majorList = localUserImageManage.getMajorList();
		JSONObject result = new JSONObject();
		result.put("majorLvl", majorLvlList);
		result.put("major", majorList);
		result.put("duty", dutyList);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
	private String getSpecialUserImageInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
		String majorLevel = ParamUtils.getParameter(request, "majorLevel");	
		String major = ParamUtils.getParameter(request, "major");
		String duty = ParamUtils.getParameter(request,"duty");
		
		List<PropUnit> DutyList = new ArrayList<PropUnit>();
		List<PropUnit> majorList = new ArrayList<PropUnit>();
		List<PropUnit> majorLevelList = new ArrayList<PropUnit>();
		
		if(!StringUtils.checkNull(majorLevel)){
			String[] IDS = majorLevel.split(",");
			Long[] ids = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) ids[i] = Long.valueOf(IDS[i].trim());
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			PropUnit[] temp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				temp[i] = new PropUnit();
				temp[i].setId(ids[i]);
				tempList.add(temp[i]);
			}
			majorLevelList = localUserImageManage.getMajorLevelList(tempList);	
		}		
		
		if(!StringUtils.checkNull(major)){
			String[] IDS = major.split(",");
			Long[] ids = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) ids[i] = Long.valueOf(IDS[i].trim());
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			PropUnit[] temp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				temp[i] = new PropUnit();
				temp[i].setId(ids[i]);
				tempList.add(temp[i]);
			}
			majorList = localUserImageManage.getMajorList(tempList);	
		}		
		if(!StringUtils.checkNull(duty)){
			String[] IDS = duty.split(",");
			Long[] ids = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) ids[i] = Long.valueOf(IDS[i].trim());
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			PropUnit[] temp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				temp[i] = new PropUnit();
				temp[i].setId(ids[i]);
				tempList.add(temp[i]);
			}
			DutyList = localUserImageManage.getDutyList(tempList);	
		}
		JSONObject result = new JSONObject();
		result.put("majorLevel", majorLevelList);
		result.put("major", majorList);
		result.put("duty", DutyList);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	private String getUserImageDuty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long typeId = ParamUtils.getLongParameter(request, "typeid", 0L);
		List<PropUnit> dutyList = localUserImageManage.getDutyList(typeId);
		JSONObject result = new JSONObject();
		result.put("dutyList", dutyList);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getGeneralUserImageInfoByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<PropUnit>  HospitalList = localUserImageManage.getHospitalList();
		List<PropUnit> areaList = localUserImageManage.getAreaList();
		JSONObject result = new JSONObject();
		result.put("hospital", HospitalList);
		result.put("area", areaList);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long typeId = ParamUtils.getLongParameter(request, "typeId", -1L);
		
		Long id =ParamUtils.getLongParameter(request,"id",0L);
		String name = request.getParameter("name");
		
		String PropIds = request.getParameter("propIds");
		String areaIds = request.getParameter("areaIds");
		String hospitalIds = request.getParameter("hospitalIds");
		String dutyIds = request.getParameter("dutyIds");
		String majorLevelIds = request.getParameter("majorLevelIds");
		String majorIds = request.getParameter("majorIds");
		String dutyIds_spec = request.getParameter("dutyIds_special");
		UserImage userImage = new UserImage();
		GeneralUserImage generalUserImage = new GeneralUserImage();
		SpecialUserImage specialUserImage = new SpecialUserImage();
		
		userImage.setId(id);
		userImage.setName(name);
		
		if (typeId > -1L) {
			ModelType modelType = new ModelType();
			modelType.setId(typeId);
			userImage.setType(modelType);
		}
		
	  	//根据人物画像与type查询人物画像，
		List<UserImage> userImageList=localUserImageManage.getUserImageListByTypeIdAndName(userImage);
		//根据人物画像id查询人物画像信息
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		//如果名称和原来的名称不一样，查找的list>0,说明修改的人物画像名称已经存在，不再修改，避免重复
	  	if(!name.equals(list.get(0).getName())){
	  		if(userImageList.size()>0){
				StrutsUtil.renderText(response, "error1");
				return null;
	  	    }
	  	}
		
		if(!StringUtil.checkNull(PropIds)){
			String[] IDS = PropIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] departmentIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) departmentIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(departmentIDS[i]);
				tempList.add(tempProp[i]);
			}
			userImage.setDepartmentPropList(tempList);
		}
		
		if(!StringUtil.checkNull(areaIds)){
			String[] IDS = areaIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] areaIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) areaIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(areaIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setAreaPropList(tempList);
		}
		
		if(!StringUtil.checkNull(hospitalIds)){
			String[] IDS = hospitalIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] hospitalIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) hospitalIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(hospitalIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setHospitalPropList(tempList);
		}
		
		if(!StringUtil.checkNull(dutyIds)){
			String[] IDS = dutyIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] dutyIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) dutyIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(dutyIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setDutyPropList(tempList);
		}
		
		userImage.setGeneralUserImage(generalUserImage);
		
		if(dutyIds!=null && dutyIds.indexOf("null")<0){
		  	if(!localUserImageManage.compareName(userImage))
			{
				StrutsUtil.renderText(response, "error1");
				return null;
			}
	  	}
 		if(!StringUtil.checkNull(majorLevelIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = majorLevelIds.split(",");
			Long[] majorLevelIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) majorLevelIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(majorLevelIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setMajorLevelPropList(tempList);
		}
 		
		if(!StringUtil.checkNull(majorIds)){
			String[] IDS = majorIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] majorIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) majorIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(majorIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setMajorPropList(tempList);
		}
		
		if(!StringUtil.checkNull(dutyIds_spec)){
			String[] IDS = dutyIds_spec.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] dutyIds_specIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) dutyIds_specIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(dutyIds_specIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setDutyPropList(tempList);
		}
		
		userImage.setSpecialUserImage(specialUserImage);
		
		boolean flag = localUserImageManage.updateUserImage(userImage);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	private String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		UserImage userImage = new UserImage();
		userImage.setId(id);
		
		boolean flag = localUserImageManage.deleteUserImage(userImage);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	public String addUserImage(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response){
	
		Long typeId = ParamUtils.getLongParameter(request, "typeId", -1L);
		String name = request.getParameter("name");
		
		String PropIds = request.getParameter("propIds");
		
		String areaIds = request.getParameter("areaIds");
		String hospitalIds = request.getParameter("hospitalIds");
		String dutyIds = request.getParameter("dutyIds");
		String majorLevelIds = request.getParameter("majorLevelIds");
		String majorIds = request.getParameter("majorIds");
		String dutyIds_spec = request.getParameter("dutyIds_special");
		
		UserImage userImage=new UserImage();
		userImage.setName(name);
	  	if (typeId > -1L) {
				ModelType modelType = new ModelType();
				modelType.setId(typeId);
				userImage.setType(modelType);
			}
	  	//根据人物画像与type查询人物画像，如果找到已经存在的，不添加，避免重复
	  	List<UserImage> userImageList=localUserImageManage.getUserImageListByTypeIdAndName(userImage);
	  	if(userImageList.size()>0){
				StrutsUtil.renderText(response, "error1");
				return null;
	  	}
		
	
		
		if(!StringUtil.checkNull(PropIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = PropIds.split(",");
			Long[] departmentIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) departmentIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(departmentIDS[i]);
				tempList.add(tempProp[i]);
			}
			userImage.setDepartmentPropList(tempList);
		}
		
		GeneralUserImage generalUserImage = new GeneralUserImage();
		SpecialUserImage specialUserImage = new SpecialUserImage();
		
		if(!StringUtil.checkNull(areaIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = areaIds.split(",");
			Long[] areaIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) areaIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(areaIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setAreaPropList(tempList);
		}		
		
		if(!StringUtil.checkNull(hospitalIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = hospitalIds.split(",");
			Long[] hospitalIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) hospitalIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(hospitalIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setHospitalPropList(tempList);
		}
		
		if(dutyIds != null && dutyIds.indexOf("null") < 0){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = dutyIds.split(",");
			Long[] dutyIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) dutyIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(dutyIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setDutyPropList(tempList);
		}
	 	userImage.setGeneralUserImage(generalUserImage);
 		
 		if(dutyIds!=null && dutyIds.indexOf("null")<0){
		  	if(!localUserImageManage.compareName(userImage))
			{
				StrutsUtil.renderText(response, "error1");
				return null;
			}
	  	}
 		if(!StringUtil.checkNull(majorLevelIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = majorLevelIds.split(",");
			Long[] majorLevelIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) majorLevelIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(majorLevelIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setMajorLevelPropList(tempList);
		}
 		if(!StringUtil.checkNull(majorIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = majorIds.split(",");
			Long[] majorIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) majorIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(majorIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setMajorPropList(tempList);
		}
	  	if(!StringUtil.checkNull(dutyIds_spec)){
				List<PropUnit> tempList = new ArrayList<PropUnit>();
				String[] IDS = dutyIds_spec.split(",");
				Long[] dutyIds_specIDS = new Long[IDS.length];
				for(int i=0;i<IDS.length;i++) dutyIds_specIDS[i] = Long.valueOf(IDS[i].trim());
				PropUnit[] tempProp = new PropUnit[IDS.length];
				for(int i=0;i<IDS.length;i++){
					tempProp[i] = new PropUnit();
					tempProp[i].setId(dutyIds_specIDS[i]);
					tempList.add(tempProp[i]);
				}
				specialUserImage.setDutyPropList(tempList);
			}
		userImage.setGeneralUserImage(generalUserImage);
	 	userImage.setSpecialUserImage(specialUserImage);
			 	
		boolean flag = localUserImageManage.addUserImage(userImage);
		if(flag == true){
			SystemUser currentUser = (SystemUser)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
			SystemLog systemLog = new SystemLog();
			if (null != currentUser) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				systemLog.setUser_id(String.valueOf(currentUser.getUserId()));
				systemLog.setOperate_login_name(currentUser.getRealName());
				systemLog.setModule_name("人物画像");
				systemLog.setOperate_type("add");
				systemLog.setOperate_context("添加人物画像色成功！");
				systemLog.setVisit_ip(CusAccessObjectUtil.getIpAddress(request));
				systemLog.setRequest_url(request.getRequestURI());
				systemLog.setOperate_platform("peixun");
				systemLog.setOperate_time(sdf.format(new Date()));
				systemLog.setCreate_time(sdf.format(new Date()));
				try {
					systemLogManage.addSystemLog(systemLog);
				} catch (Exception e){
					logger.error("添加审计日志期间发生异常:" + e.getMessage());
				}

			}
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	private String uhaha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserImage userImage = new UserImage();
		Long typeId  = ParamUtils.getLongParameter(request, "typeId", -1L);
		//ModelType modelType = new ModelType();
		//modelType.setId(typeId);
		//userImage.setType(modelType);
		userImage.setId(typeId);
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		JSONObject result_list = new JSONObject();
		result_list.put("update_display", list);
		StrutsUtil.renderText(response, result_list.toString());
		return null;
	}
	
	public UserImageManage getLocalUserImageManage() {
		return localUserImageManage;
	}

	public void setLocalUserImageManage(UserImageManage localUserImageManage) {
		this.localUserImageManage = localUserImageManage;
	}
	
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}


	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	private String getXueke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<ExamPropVal> list = new ArrayList<ExamPropVal>();
		String imageName = request.getParameter("imageName");
        String image[] = imageName.split(",");  
		UserImage userImage = new UserImage();
		userImage.setName(image[0]);

		list = localExamPropValFacade.getXuekeByImageName(userImage);
		JSONObject result = new JSONObject();
		result.put("result",list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
}
