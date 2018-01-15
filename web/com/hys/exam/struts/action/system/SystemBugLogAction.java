/**
 *
 * <p>系统bug日志</p>
 * @author chenlaibin
 * @version 1.0 2014-4-2
 */

package com.hys.exam.struts.action.system;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.system.SystemBugLog;
import com.hys.exam.service.local.SystemBugLogManage;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.struts.form.SystemBugLogForm;
import com.hys.exam.utils.FilesUtils;

public class SystemBugLogAction extends AppBaseAction{

	private SystemBugLogManage systemBugLogManage;
	
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
	@SuppressWarnings("static-access")
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String title = ParamUtils.getParameter(request, "title", "");
		String content = ParamUtils.getParameter(request, "content", "");
		Integer type = ParamUtils.getIntParameter(request, "type", 1);
		SystemBugLog log = new SystemBugLog();
		log.setTitle(title);
		log.setContent(content);
		log.setType(type);
		Page<SystemBugLog> page = this.createPage(request, "systemBugLog");
		this.systemBugLogManage.getSystemBugLogList(page, log);
		request.setAttribute("page", page);
		request.setAttribute("type", type);
		return "list";
	}
	
	//查看详细
	protected String view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		SystemBugLog log = this.systemBugLogManage.getSystemBugLogById(id);
		request.setAttribute("log", log);
		return "view";
	}
	
	//修改页面
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		Long type = ParamUtils.getLongParameter(request, "type", 1);
		if(id >0){
			SystemBugLog log = this.systemBugLogManage.getSystemBugLogById(id);
			request.setAttribute("log", log);
		}
		request.setAttribute("id", id);
		request.setAttribute("type", type);
		return "edit";
	}
	
	//更新
	protected String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SystemBugLog log = new SystemBugLog();
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		if(id >0){
			log.setId(id);
			log.setFinisher(ParamUtils.getParameter(request, "finisher"));
			log.setUpdateDate(new Date());
			log.setReply(ParamUtils.getParameter(request, "reply"));
		}
		else{
			log.setId(0L);
			log.setCreator(ParamUtils.getParameter(request, "creator"));
			log.setCreateDate(new Date());
		}
		
		log.setTitle(ParamUtils.getParameter(request, "title"));
		log.setContent(ParamUtils.getParameter(request, "content"));
		log.setBugLevel(ParamUtils.getIntParameter(request, "bugLevel", -1));
		log.setBugStatus(ParamUtils.getIntParameter(request, "bugStatus", -1));
		log.setStatus(ParamUtils.getIntParameter(request, "status", 1));
		log.setType(ParamUtils.getIntParameter(request, "type", 1));
		if(((SystemBugLogForm)form).getFile1()!=null){
			log.setFilePath(FilesUtils.bugLogUpload(((SystemBugLogForm)form).getFile1(), request, Constants.UPLOAD_FILE_PATH_SYSTEM_BUGLOG));
		}
		if(((SystemBugLogForm)form).getFile2()!=null){
			log.setFilePathTwo(FilesUtils.bugLogUpload(((SystemBugLogForm)form).getFile2(), request, Constants.UPLOAD_FILE_PATH_SYSTEM_BUGLOG));	
		}
		if(((SystemBugLogForm)form).getFile3()!=null){
			log.setFilePathThree(FilesUtils.bugLogUpload(((SystemBugLogForm)form).getFile3(), request, Constants.UPLOAD_FILE_PATH_SYSTEM_BUGLOG));
		}
		
		this.systemBugLogManage.saveSystemBugLog(log);
		return this.list(mapping, form, request, response);
	}
	

	//删除
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String _bugIds = ParamUtils.getParameter(request, "ids");
		_bugIds = _bugIds.substring(0, _bugIds.length()-1);
		String [] _ids = _bugIds.split(",");
		Long [] ids = this.switchStringtoLongArray(_ids);
		int row = this.systemBugLogManage.deleteSystemBugLog(ids);
		Utils.renderText(response, String.valueOf(row));
		return null;
	}

	public void setSystemBugLogManage(SystemBugLogManage systemBugLogManage) {
		this.systemBugLogManage = systemBugLogManage;
	}
	
	public SystemBugLogManage getSystemBugLogManage() {
		return systemBugLogManage;
	}
}


