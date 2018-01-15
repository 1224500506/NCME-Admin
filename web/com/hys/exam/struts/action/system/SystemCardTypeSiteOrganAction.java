/**
 *
 * <p>卡类型与站点,站点与继教地区</p>
 * @author chenlaibin
 * @version 1.0 2014-7-14
 * 原来的卡类型直接与继教地区绑定
 * 现在改为卡类型和站点关联(1vN)，然后站点和继教地区关联(1vN)
 */

package com.hys.exam.struts.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.system.SystemAdminOrganVO;
import com.hys.exam.model.system.SystemSiteVO;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.struts.action.AppBaseAction;

public class SystemCardTypeSiteOrganAction extends AppBaseAction{

	private SystemSiteManage systemSiteManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = ParamUtils.getParameter(request, "method");
		
		if(method.equals("getSystemSiteList")){
			return this.getSystemSiteList(mapping, form, request, response);
		}else if(method.equals("getSystemOrganList")){					//站点地区关联--继教平台
			return this.getSystemOrganList(mapping, form, request, response);
		}else if(method.equals("saveSite")){
			return this.saveSite(mapping, form, request, response);
		}else if(method.equals("deleteSite")){
			return this.deleteSite(mapping, form, request, response);
		}else if(method.equals("saveOrgan")){
			return this.saveOrgan(mapping, form, request, response);
		}else if(method.equals("deleteOrgan")){
			return this.deleteOrgan(mapping, form, request, response);
		}else if(method.equals("getSystemPaycardOrganList")){			//学习卡地区关联--学习平台
			return this.getSystemPaycardOrganList(mapping, form, request, response);
		}else if(method.equals("savePaycardOrgan")){
			return this.savePaycardOrgan(mapping, form, request, response);
		}else if(method.equals("deletePaycardOrgan")){
			return this.deletePaycardOrgan(mapping, form, request, response);
		}
		return null;
	}
	
	//得到绑定/未绑定的站点
	protected String getSystemSiteList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		////List<SystemSiteVO> list = this.systemSiteManage.getSystemSiteList();
		List<SystemSiteVO> bindList = this.systemSiteManage.getSystemSiteListByTypeId(typeId, true);
		List<SystemSiteVO> unBindList = this.systemSiteManage.getSystemSiteListByTypeId(typeId, false);
		request.setAttribute("bindList", bindList);
		request.setAttribute("unBindList", unBindList);
		request.setAttribute("typeId", typeId);
		////request.setAttribute("parentOrgId", Constants.AJPX_ORG_BJ_ID);	//北京地区
		return "authorized-site-list";
	}
	
	//得到绑定/未绑定的继教地区
	protected String getSystemOrganList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long siteId = ParamUtils.getLongParameter(request, "siteId", 0);
		////Long parentOrgId = ParamUtils.getLongParameter(request, "parentOrgId", -100L);
		////List<SystemAdminOrganVO> list = new ArrayList<SystemAdminOrganVO>();
		
		////List<IcmeOrg> icmeOrgList = this.systemSiteManage.getIcmeGovOrgList();
		
		//icme的org优先,如果icmeOrgList有值,则只显示此机构列表
		/*if(!Utils.isListEmpty(icmeOrgList)){
			for(IcmeOrg org : icmeOrgList){
				SystemAdminOrganVO vo = new SystemAdminOrganVO();
				vo.setOrganId(org.getId()+"");
				vo.setName(org.getOrgName());
				list.add(vo);
			}
		}else{*/
			////list = this.systemSiteManage.getSystemAdminOrganList(parentOrgId);
		//}
				
		List<SystemAdminOrganVO> bindList = this.systemSiteManage.getSystemAdminOrganList(siteId, true);
		List<SystemAdminOrganVO> unBindList = this.systemSiteManage.getSystemAdminOrganList(siteId, false);
		
		request.setAttribute("bindList", bindList);
		request.setAttribute("unBindList", unBindList);
		request.setAttribute("siteId", siteId);
		request.setAttribute("typeId", request.getParameter("typeId"));
		return "authorized-organ-list";
	}
	
	//保存卡类型站点
	protected String saveSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long siteId = ParamUtils.getLongParameter(request, "siteId", 0);
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		int count = this.systemSiteManage.saveSystemCardTypeSite(typeId, siteId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//删除卡类型站点
	protected String deleteSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long siteId = ParamUtils.getLongParameter(request, "siteId", 0);
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		int count = this.systemSiteManage.deleteSystemCardTypeSite(typeId, siteId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//保存站点继教地区
	protected String saveOrgan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long siteId = ParamUtils.getLongParameter(request, "siteId", 0);
		Long organId = ParamUtils.getLongParameter(request, "organId", 0);
		int count = this.systemSiteManage.saveSystemSiteOrgan(siteId, organId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//删除站点继教地区
	protected String deleteOrgan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long siteId = ParamUtils.getLongParameter(request, "siteId", 0);
		Long organId = ParamUtils.getLongParameter(request, "organId", 0);
		int count = this.systemSiteManage.deleteSystemSiteOrgan(siteId, organId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	
	
	
	//学习卡继教地区列表
	protected String getSystemPaycardOrganList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
				
		List<SystemAdminOrganVO> bindList = this.systemSiteManage.getSystemAdminOrganListByCardType(typeId, true);
		List<SystemAdminOrganVO> unBindList = this.systemSiteManage.getSystemAdminOrganListByCardType(typeId, false);
		
		request.setAttribute("bindList", bindList);
		request.setAttribute("unBindList", unBindList);
		request.setAttribute("typeId", typeId);
		return "authorized-paycardorgan-list";
	}
	
	//保存学习卡继教地区
	protected String savePaycardOrgan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		Long organId = ParamUtils.getLongParameter(request, "organId", 0);
		Integer orgType = ParamUtils.getIntParameter(request, "orgType", 0);
		int count = this.systemSiteManage.savePaycardOrgan(typeId, organId, orgType);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//删除学习卡继教地区
	protected String deletePaycardOrgan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0);
		Long organId = ParamUtils.getLongParameter(request, "organId", 0);
		int count = this.systemSiteManage.deletePaycardOrgan(typeId, organId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}

	public SystemSiteManage getSystemSiteManage() {
		return systemSiteManage;
	}

	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}
}


