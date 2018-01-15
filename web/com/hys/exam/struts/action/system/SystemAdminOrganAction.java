/**
 *
 * <p>继教地区表</p>
 * @author chenlaibin
 * @version 1.0 2014-7-11
 */

package com.hys.exam.struts.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.NcmeAdminOrgan;
import com.hys.exam.service.local.NcmeAdminOrganManage;
import com.hys.exam.struts.action.AppBaseAction;

public class SystemAdminOrganAction extends AppBaseAction{

	private NcmeAdminOrganManage ncmeAdminOrganManage;


	public void setNcmeAdminOrganManage(NcmeAdminOrganManage ncmeAdminOrganManage) {
		this.ncmeAdminOrganManage = ncmeAdminOrganManage;
	}

	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = ParamUtils.getParameter(request, "method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else if(method.equals("update")){
			return this.update(mapping, form, request, response);
		}else if(method.equals("save")){
			return this.save(mapping, form, request, response);
		}else if(method.equals("view")){
			return this.view(mapping, form, request, response);
		}
		return this.list(mapping, form, request, response);
	}
	
	//查看列表
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String name = ParamUtils.getParameter(request, "name");
		NcmeAdminOrgan organ = new NcmeAdminOrgan();
		organ.setName(name);
		@SuppressWarnings("static-access")
		Page<NcmeAdminOrgan> page = this.createPage(request, "systemAdminOrgan");
		this.ncmeAdminOrganManage.getNcmeAdminOrganList(page, organ);
		request.setAttribute("page", page);
		request.setAttribute("name", name);
		return "list";
	}
	
	//得到子列表
	protected String getBJList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long parentOrgId = ParamUtils.getLongParameter(request, "parentOrgId", -100);
		List<NcmeAdminOrgan> list = this.ncmeAdminOrganManage.getNcmeAdminOrganList(parentOrgId);
		request.setAttribute("list", list);
		return "subList";
	}
	
	//删除
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return null;
	}
	
	//保存
	protected String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return null;
	}
	
	//修改
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return null;
	}
	
	//查看
	protected String view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return null;
	}
}


