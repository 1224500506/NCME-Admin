package com.hys.auth.struts.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysRoles;
import com.hys.auth.util.RequestUtil;

/**
 * 预保存用户
 * 
 * @author zdz
 * 
 */
public class PrepareSaveUserAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HysRoles> roles = facade.getRolesList();
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			HysRoles name = (HysRoles) iterator.next();
			if(1==name.getId()) {
				iterator.remove();
			}
		}
		RequestUtil.setAttribute(request, "roles", roles);
		return Constants.SUCCESS;
	}

}
