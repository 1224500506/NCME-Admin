package com.hys.auth.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.util.RequestUtil;

/**
 * 保存角色
 * 
 * @author zdz
 * 
 */
public class ProcessSaveRolesDataAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer roleId = Integer.valueOf(RequestUtil.getParameter(request, "roleId"));

		//属性管理的参数
		String propAuth1 = RequestUtil.getParameter(request, "yi11");
		if (propAuth1.equals("")) {
			propAuth1 = "null";
		}
		String propAuth2 = RequestUtil.getParameter(request, "yi12");
		if (propAuth2.equals("")) {
			propAuth2 = "null";
		}
		String yi1_1 = RequestUtil.getParameter(request, "yi1_1");
		if (yi1_1.equals("")) {
			yi1_1 = "null";
		}
		String yi1_2 = RequestUtil.getParameter(request, "yi1_2");
		if (yi1_2.equals("")) {
			yi1_2 = "null";
		}
		String yi1_3 = RequestUtil.getParameter(request, "yi1_3");
		if (yi1_3.equals("")) {
			yi1_3 = "null";
		}
		String yi1_4 = RequestUtil.getParameter(request, "yi1_4");
		if (yi1_4.equals("")) {
			yi1_4 = "null";
		}
		String yi1_5 = RequestUtil.getParameter(request, "yi1_5");
		if (yi1_5.equals("")) {
			yi1_5 = "null";
		}
		String yi1_6 = RequestUtil.getParameter(request, "yi1_6");
		if (yi1_6.equals("")) {
			yi1_6 = "null";
		}
		String yi1_7 = RequestUtil.getParameter(request, "yi1_7");
		if (yi1_7.equals("")) {
			yi1_7 = "null";
		}
		String propAuth3 = yi1_1 + "," + yi1_2 + "," + yi1_3 + "," + 
				yi1_4 + "," + yi1_5 + "," + yi1_6 + "," + yi1_7;
		List<String> propAuth = new ArrayList<String>();
		propAuth.add(0, propAuth1);
		propAuth.add(1, propAuth2);
		propAuth.add(2, propAuth3);

		//题库管理的参数
		String questAuth1 = RequestUtil.getParameter(request, "yi21");
		if (questAuth1.equals("")) {
			questAuth1 = "null";
		}
		String questAuth2 = RequestUtil.getParameter(request, "yi22");
		if (questAuth2.equals("")) {
			questAuth2 = "null";
		}
		String yi2_1 = RequestUtil.getParameter(request, "yi2_1");
		if (yi2_1.equals("")) {
			yi2_1 = "null";
		}
		String yi2_2 = RequestUtil.getParameter(request, "yi2_2");
		if (yi2_2.equals("")) {
			yi2_2 = "null";
		}
		String questAuth3 = yi2_1 + "," + yi2_2;
		List<String> questAuth = new ArrayList<String>();
		questAuth.add(0, questAuth1);
		questAuth.add(1, questAuth2);
		questAuth.add(2, questAuth3);

		//素材管理的参数
		String matiAuth1 = RequestUtil.getParameter(request, "yi31");
		if (matiAuth1.equals("")) {
			matiAuth1 = "null";
		}
		String matiAuth2 = RequestUtil.getParameter(request, "yi32");
		if (matiAuth2.equals("")) {
			matiAuth2 = "null";
		}
		String yi3_1 = RequestUtil.getParameter(request, "yi3_1");
		if (yi3_1.equals("")) {
			yi3_1 = "null";
		}
		String yi3_2 = RequestUtil.getParameter(request, "yi3_2");
		if (yi3_2.equals("")) {
			yi3_2 = "null";
		}
		String matiAuth3 = yi3_1 + "," + yi3_2;
		List<String> matiAuth = new ArrayList<String>();
		matiAuth.add(0, matiAuth1);
		matiAuth.add(1, matiAuth2);
		matiAuth.add(2, matiAuth3);
		
		//专家管理的参数
		String expertAuth1 = RequestUtil.getParameter(request, "yi41");
		if (expertAuth1.equals("")) {
			expertAuth1 = "null";
		}
		String expertAuth2 = RequestUtil.getParameter(request, "yi42");
		if (expertAuth2.equals("")) {
			expertAuth2 = "null";
		}
		String yi4_1 = RequestUtil.getParameter(request, "yi4_1");
		if (yi4_1.equals("")) {
			yi4_1 = "null";
		}
		String yi4_2 = RequestUtil.getParameter(request, "yi4_2");
		if (yi4_2.equals("")) {
			yi4_2 = "null";
		}
		String expertAuth3 = yi4_1 + "," + yi4_2;
		List<String> expertAuth = new ArrayList<String>();
		expertAuth.add(0, expertAuth1);
		expertAuth.add(1, expertAuth2);
		expertAuth.add(2, expertAuth3);
		
		//病例管理的参数
		String bingliAuth1 = RequestUtil.getParameter(request, "yi51");
		if (bingliAuth1.equals("")) {
			bingliAuth1 = "null";
		}
		String bingliAuth2 = RequestUtil.getParameter(request, "yi52");
		if (bingliAuth2.equals("")) {
			bingliAuth2 = "null";
		}
		String yi5_1 = RequestUtil.getParameter(request, "yi5_1");
		if (yi5_1.equals("")) {
			yi5_1 = "null";
		}
		String yi5_2 = RequestUtil.getParameter(request, "yi5_2");
		if (yi5_2.equals("")) {
			yi5_2 = "null";
		}
		String bingliAuth3 = yi5_1 + "," + yi5_2;
		List<String> bingliAuth = new ArrayList<String>();
		bingliAuth.add(0, bingliAuth1);
		bingliAuth.add(1, bingliAuth2);
		bingliAuth.add(2, bingliAuth3);
		
		//系统用户的参数
		String systemAuth1 = RequestUtil.getParameter(request, "yi61");
		if (systemAuth1.equals("")) {
			systemAuth1 = "null";
		}
		String systemAuth2 = RequestUtil.getParameter(request, "yi62");
		if (systemAuth2.equals("")) {
			systemAuth2 = "null";
		}
		String yi6_1 = RequestUtil.getParameter(request, "yi6_1");
		if (yi6_1.equals("")) {
			yi6_1 = "null";
		}
		String yi6_2 = RequestUtil.getParameter(request, "yi6_2");
		if (yi6_2.equals("")) {
			yi6_2 = "null";
		}
		String yi6_3 = RequestUtil.getParameter(request, "yi6_3");
		if (yi6_3.equals("")) {
			yi6_3 = "null";
		}
		String systemAuth3 = yi6_1 + "," + yi6_2 + "," + yi6_3;
		List<String> systemAuth = new ArrayList<String>();
		systemAuth.add(0, systemAuth1);
		systemAuth.add(1, systemAuth2);
		systemAuth.add(2, systemAuth3);

		HysDataRoles dataRoles = new HysDataRoles();
		dataRoles.setRoleId(roleId);
		dataRoles.setPropAuth(propAuth);
		dataRoles.setQuestAuth(questAuth);
		dataRoles.setMatiAuth(matiAuth);
		dataRoles.setExpertAuth(expertAuth);
		dataRoles.setBingliAuth(bingliAuth);
		dataRoles.setSystemAuth(systemAuth);

		String str ="";
		
		Boolean flag = true;
		
		List<HysDataDetail> hysDataList = new ArrayList<HysDataDetail>();
		hysDataList = facade.getRolesDataList();
		for (int i = 0; i < hysDataList.size(); i ++) {
			if (roleId.equals(hysDataList.get(i).getRoleId())) {
				facade.updateRolesData(dataRoles);
				flag = false;
				break;
			}
		}

		if (flag == true) {
			facade.saveRolesData(dataRoles);
		}

		Long key = 0L;
		if (key > 0) {
	//		RequestUtil.setAttribute(request, "msg", "角色[" + roleName + "]保存成功！");
			return Constants.SUCCESS;
		}
		return Constants.INPUT;
	}

}
