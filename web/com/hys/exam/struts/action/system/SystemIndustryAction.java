package com.hys.exam.struts.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.service.remote.SystemPropManage;
import com.hys.framework.web.action.BaseAction;

/**
*
* <p>Description: 系统属性维护</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午9:21:31
*/
public class SystemIndustryAction extends BaseAction{
	
	private SystemPropManage systemPropManage;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		
		//一级列表
		if (method.equals("list")){
			return this.list(mapping, form, request, response);
		//二级列表
		}else if(method.equals("subList")){
			return this.subList(mapping, form, request, response);
		}
		//添加
		else if(method.equals("add")){
			return this.add(mapping, form, request, response);
		}
		//更新
		else if(method.equals("update")){
			return this.update(mapping, form, request, response);
		}
		//删除
		else if(method.equals("delete")){
			return this.delete(mapping, form, request, response);
		}
		//得到下级个数
		else if(method.equals("getSubCount")){
			return this.getSubCount(mapping, form, request, response);
		}
		//保存
		else if(method.equals("save")){
			return this.save(mapping, form, request, response);
		}
		
		return null;
	}

	//查询
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "list";
	}
	
	//下级
	protected String subList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0);
		List<SystemIndustry> list = this.systemPropManage.getSystemIndustryList(parentId);
		
		JSONArray array = new JSONArray();
		JSONObject jsonObj = null;
		
		if(!Utils.isListEmpty(list)){
			for(SystemIndustry item : list){
				jsonObj = new JSONObject();
				jsonObj.put("id", item.getId());
				jsonObj.put("industryName", item.getIndustryName());
				jsonObj.put("parentId", item.getParentId());
				jsonObj.put("_parentId", item.getParentId());
				
				//一级
				if(item.getParentId() == Constants.SYSTEM_INDUSTRY_ROOT_ID)
					jsonObj.put("level", 1);
				else
					jsonObj.put("level", 2);
				
				List<SystemIndustry> subList = this.systemPropManage.getSystemIndustryList(item.getId());
				if(!Utils.isListEmpty(subList)){
					if(subList.size()>0)
						jsonObj.put("state", "closed");
				}
				
				array.add(jsonObj);
			}
		}
		
		jsonObj = new JSONObject();
		jsonObj.put("rows", array);
		Utils.renderText(response, jsonObj.toString());
		
		return null;
	}
	
	//add
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long level = ParamUtils.getLongParameter(request, "level", 1);
		if(level == 2){
			Long parentId = ParamUtils.getLongParameter(request, "parentId", 0);
			SystemIndustry industry = this.systemPropManage.getSystemIndustryById(parentId);
			request.setAttribute("parentName", industry.getIndustryName());
			request.setAttribute("parentId", parentId);
		}
		
		return "edit";
	}
	
	//update
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		SystemIndustry industry = null;
		if(id >0){
			industry = this.systemPropManage.getSystemIndustryById(id);
			request.setAttribute("id", id);
			request.setAttribute("level", request.getParameter("level"));
			request.setAttribute("industryName", industry.getIndustryName());
			request.setAttribute("seq", industry.getSeq());
		}
		return "edit";
	}
	
	//getSubCount
	protected String getSubCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long parentId = ParamUtils.getLongParameter(request, "parentId", -1);
		Long count = this.systemPropManage.getSubCount(parentId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//delete
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		int row = this.systemPropManage.deleteSystemIndustry(id);
		if(row>0)
			request.setAttribute("meg", "删除成功!");
		else
			request.setAttribute("meg", "网络故障，删除不成功，请稍候重试!");
		return this.list(mapping, form, request, response);
	}
	
	//save
	protected String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String industryName = ParamUtils.getParameter(request, "industryName");
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0);
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		Long level = ParamUtils.getLongParameter(request, "level", 0);
		Integer seq = ParamUtils.getIntParameter(request, "seq", 0);
		int row = 0;
		if(StringUtils.isNotBlank(industryName)){
			row = this.systemPropManage.saveSystemIndustry(id, parentId, industryName, level, seq);
		}
		if(row>0)
			request.setAttribute("meg", "保存成功!");
		else
			request.setAttribute("meg", "网络故障，保存不成功，请稍候重试!");
		
		return this.list(mapping, form, request, response);
	}
	

	public SystemPropManage getSystemPropManage() {
		return systemPropManage;
	}

	public void setSystemPropManage(SystemPropManage systemPropManage) {
		this.systemPropManage = systemPropManage;
	}
}


