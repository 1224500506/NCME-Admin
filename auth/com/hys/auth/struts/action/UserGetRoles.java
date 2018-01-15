package com.hys.auth.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysRoles;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.HysUserRoleProp;

/**
 * 
 * 标题：用户管理-更新用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class UserGetRoles extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("user");
		
		List<HysRoles> temproles = facade.getRolesList();
		List<HysRoles> roles = new ArrayList<HysRoles>();
		for(int i = 0 ; i < temproles.size() ; i++){
			if(temproles.get(i).getStatus() == 1){
				roles.add(temproles.get(i));
			}
		}
		JSONObject result = new JSONObject();
		
		result.put("list", roles);
		StrutsUtil.renderText(response, result.toString());

		return null;
	}
}
