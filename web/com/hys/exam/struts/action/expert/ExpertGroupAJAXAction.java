package com.hys.exam.struts.action.expert;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.LongUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.exam.struts.form.ExpertInfoForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * @author Han
 * 专家委员会管理
 */
public class ExpertGroupAJAXAction extends BaseAction {

	private ExpertGroupManage localExpertgroupManage;
	private ExpertManageManage localExpertManageManage;
	
	public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}
	
	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		ExpertGroup group = new ExpertGroup();
		
		//设置查询条件
		group.setLockState(1);
		group.setParent(Long.valueOf(id));
		
		List<ExpertGroup> list =  localExpertgroupManage.getExpertGroupList(group);
		
		for (ExpertGroup e: list){
			e.setType((e.getParent() == 0L)?1:2);
			e.setProp_val_id(e.getId());
		}
		JSONObject result = new JSONObject();
		
		result.put("list", list);
		StrutsUtil.renderText(response, result.toString());

		return null;
	}
	
}
