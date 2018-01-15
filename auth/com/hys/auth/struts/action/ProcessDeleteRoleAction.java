package com.hys.auth.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysRoles;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.SystemRole;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;

/**
 * 删除角色
 * 
 * @author zdz
 * 
 */
public class ProcessDeleteRoleAction extends BaseActionSupport {

	private SystemRoleFacade systemRoleFacade;
	
	public void setSystemRoleFacade(SystemRoleFacade systemRoleFacade) {
		this.systemRoleFacade = systemRoleFacade;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getParameter(request, "rid");
		SystemRole curRole = new SystemRole();
		
		if (StringUtils.isBlank(id)) {
			StrutsUtil.renderText(response, "fail");
			return null;
		}
		else{
			curRole = systemRoleFacade.getItemById(Long.valueOf(id));
			if(curRole == null)
			{
				StrutsUtil.renderText(response, "fail");
				return null;
			}
			if(curRole.getStatus().equals(1))
			{
				curRole.setStatus(0);
			}
			else
			{
				curRole.setStatus(1);
			}
			int result = systemRoleFacade.update(curRole);
			if(result != 0)
			{
				StrutsUtil.renderText(response, "success");
			}
			else
			{
				StrutsUtil.renderText(response, "fail");
			}
		}
		return null;
  }
}
