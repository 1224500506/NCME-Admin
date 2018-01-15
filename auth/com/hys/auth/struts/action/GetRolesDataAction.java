package com.hys.auth.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataList;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;

/**
 * 显示角色
 * 
 * @author zdz
 * 
 */
public class GetRolesDataAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer roleId = Integer.valueOf(RequestUtil.getParameter(request, "roleId"));
		
		List<HysDataDetail> hysDataList = new ArrayList<HysDataDetail>();
		hysDataList = facade.getRolesDataList();
		
		List<HysDataList> rolesList = new ArrayList<HysDataList>();
		
		for (int i = 0; i < hysDataList.size(); i ++) {
			if (hysDataList.get(i).getRoleId().equals(roleId)) {
				HysDataList list = new HysDataList();
				list.setControlAuth(hysDataList.get(i).getControlAuth());
				list.setManageKind(hysDataList.get(i).getManageKind());
				list.setStatisticsAuth(hysDataList.get(i).getStatisticsAuth());
				list.setVariety(hysDataList.get(i).getVariety());
				rolesList.add(list);
			}
		}
		
		JSONArray rolesData = new JSONArray();
		rolesData.add(rolesList);
		String role = rolesData.toString();
		StrutsUtil.renderText(response, role);
		return null;
	}

}
