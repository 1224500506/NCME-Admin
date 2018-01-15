package com.hys.exam.struts.action.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemIndustryAbility;
import com.hys.exam.service.remote.SystemPropManage;
import com.hys.exam.struts.action.AppBaseAction;

/**
*
* <p>Description: 行业能力</p>
* @author chenlaibin
* @version 1.0 2013-12-13 下午9:07:49
*/

public class SystemIndustryAbilityAction extends AppBaseAction{

	private SystemPropManage systemPropManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = RequestUtil.getParameter(request, "method");
		
		//行业能力
		if (method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("add") || method.equals("update")){
			return this.update(mapping, form, request, response);
		}else if(method.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else if(method.equals("getCountCourses")){
			return this.getCountCourses(mapping, form, request, response);
		}else if(method.equals("save")){
			return this.save(mapping, form, request, response);
		}
		else if(method.equals("courseList")){				//能力下课程列表
			return this.courseList(mapping, form, request, response);
		}else if(method.equals("otherCourseList")){
			return this.otherCourseList(mapping, form, request, response);
		}
		else if(method.equals("deleteAbilityCourses")){	//删除能力下的课程
			return this.deleteAbilityCourses(mapping, form, request, response);
		}else if(method.equals("saveAbilityCourses")){		//为能力添加课程
			return this.saveAbilityCourses(mapping, form, request, response);
		}
		return null;
	}
	
	//查询行业下能力
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String industryId = request.getParameter("industryId");
		if(StringUtils.isNotBlank(industryId)){
			SystemIndustry sid = getSystemIndustry(Long.parseLong(industryId));
			List<SystemIndustryAbility> abilityList = this.systemPropManage.getSystemIndustryAbilityList(Long.parseLong(industryId));
			request.setAttribute("systemIndustry", sid);
			request.setAttribute("list", abilityList);
		}
		return "list";
	}
	
	//查询能力下的课程列表
	@SuppressWarnings("static-access")
	public String courseList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long abilityId = ParamUtils.getLongParameter(request, "abilityId", 0);
		if(abilityId >0){
			String courseName = ParamUtils.getParameter(request, "courseName");
			String teacher = ParamUtils.getParameter(request, "teacher");
			//来源 暂定
			//Page<StudyCourseVO> page = PageUtil.getPageByRequest(request);
			//Page<StudyCourseVO> page = PageUtil.createPage(request, "abilityCourse");
			Page<StudyCourseVO> page = this.createPage(request, "abilityCourse");
			this.systemPropManage.getStudyCourseListByAbilityId(page, abilityId, courseName, teacher);
			
			SystemIndustryAbility ability = this.systemPropManage.getSystemIndustryAbilityById(abilityId);
			request.setAttribute("ability", ability);
			
			request.setAttribute("courseName", courseName);
			request.setAttribute("teacher", teacher);
			request.setAttribute("page", page);
		}
		request.setAttribute("industryId", request.getParameter("industryId"));
		return "abilityCourseList";
	}
	
	//能力所没有关联的课程列表
	@SuppressWarnings("static-access")
	protected String otherCourseList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long abilityId = ParamUtils.getLongParameter(request, "abilityId", 0);
		Page<StudyCourseVO> page = this.createPage(request, "iStudyCourse");
		String courseName = ParamUtils.getParameter(request, "courseName");
		String teacher = ParamUtils.getParameter(request, "teacher");
		this.systemPropManage.getStudyCourseListBesidesAbilityId(page, abilityId, courseName, teacher);
		SystemIndustryAbility ability = this.systemPropManage.getSystemIndustryAbilityById(abilityId);
		request.setAttribute("page", page);
		request.setAttribute("courseName", courseName);
		request.setAttribute("teacher", teacher);
		request.setAttribute("ability", ability);
		request.setAttribute("industryId", request.getParameter("industryId"));
		return "otherCourseList";
	}
	
	//得到行业对象
	protected SystemIndustry getSystemIndustry(Long id){
		return this.systemPropManage.getSystemIndustryById(id);
	}
	
	//编辑能力
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		Long industryId = ParamUtils.getLongParameter(request, "industryId", 0);
		if(id >0){
			SystemIndustryAbility ability = this.systemPropManage.getSystemIndustryAbilityById(id);
			request.setAttribute("ability", ability);
		}
		if(industryId > 0){
			SystemIndustry industry = getSystemIndustry(industryId);
			request.setAttribute("industry", industry);
		}
		return "edit";
	}
	
	//save ability
	protected String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String abilityName = ParamUtils.getParameter(request, "abilityName");
		Long industryId = ParamUtils.getLongParameter(request, "industryId", 0);
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		int row = 10;
		if(StringUtils.isNotBlank(abilityName)){
			row = this.systemPropManage.saveSystemIndustryAbility(id, industryId, abilityName);
		}
		response.sendRedirect(request.getContextPath()+"/system/SystemIndustryAbility.do?method=list&industryId="+industryId+"&row="+row);
		return null;
	}
	
	//getSubCount
	protected String getCountCourses(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long abilityId = ParamUtils.getLongParameter(request, "abilityId", 0);
		Long count = 0L;
		if(abilityId > 0){
			count = this.systemPropManage.getCountAbilityCourseByAbilityId(abilityId);
		}
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//delete ability
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Long id = ParamUtils.getLongParameter(request, "abilityId", 0);
		Long industryId = ParamUtils.getLongParameter(request, "industryId", 0);
		int row = this.systemPropManage.deleteSystemIndustryAbility(id);
		if(row>0)
			request.setAttribute("meg", "删除成功!");
		else
			request.setAttribute("meg", "网络故障，删除不成功，请稍候重试!");
		
		response.sendRedirect(request.getContextPath()+"/system/SystemIndustryAbility.do?method=list&industryId="+industryId+"&deleRow="+row);
		return null;
	}
	
	//save ability courses
	protected String saveAbilityCourses(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long abilityId = ParamUtils.getLongParameter(request, "abilityId", 0);
		String selectedIds = ParamUtils.getParameter(request, "selectedIds");
		int count = 1;
		if(abilityId > 0 && StringUtils.isNotBlank(selectedIds)){
			String [] paramsArr = selectedIds.split(",");
			List<StudyCourseVO> list = new ArrayList<StudyCourseVO>();
			
			for(int i=0; i<paramsArr.length; i++){
				String arr = paramsArr[i];
				if(StringUtils.isNotBlank(arr)){
					String ids[] = arr.split("_");
					StudyCourseVO vo = new StudyCourseVO();
					vo.setId(new Long(ids[0]));
					vo.setCreditType(ids[1]);
					vo.setCreditNum(new Double(ids[2]));
					list.add(vo);
				}
			}
			count = this.systemPropManage.saveSystemAbilityCourse(abilityId, list);
		}
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//delete ability courses
	protected String deleteAbilityCourses(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long abilityId = ParamUtils.getLongParameter(request, "abilityId", 0);
		Long [] courseIds = getLongParameters(request, "courseIds", 0L);
		Long courseId = ParamUtils.getLongParameter(request, "courseId", 0L);
		int count = 0;
		if(abilityId > 0){
			if(courseId > 0){
				courseIds = new Long []{courseId};
			}
			count = this.systemPropManage.deleteSystemAbilityCourse(abilityId, courseIds);
		}
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	public static Long[] getLongParameters(HttpServletRequest request,
			String name, Long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new Long[0];
		}
		Long[] values = new Long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	public SystemPropManage getSystemPropManage() {
		return systemPropManage;
	}

	public void setSystemPropManage(SystemPropManage systemPropManage) {
		this.systemPropManage = systemPropManage;
	}

}


