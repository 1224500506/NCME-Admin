package com.hys.exam.struts.action.content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.displaytag.properties.SortOrderEnum;


import com.es.ESConfig;
import com.es.ESUtil;
import com.hys.auth.util.DateUtils;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.Advert;
import com.hys.exam.model.AdvertImage;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.UserImage;
import com.hys.exam.service.local.BannerManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.struts.form.BannerForm;
import com.hys.exam.struts.form.BannerImage_Form;
import com.hys.exam.struts.form.CVS_Form;
import com.hys.exam.struts.form.SentenceForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.FileLimitUtil;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;
import com.hys.xiangyi.model.XiangYiProvince;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * banner管理
 * @author weeho
 *
 */
public class BannerAction extends BaseAction{
	//站点
	private SystemSiteManage localSystemSiteManage;
	//banner
	private BannerManage localBannerManage;
	
	private SystemUser systemUser;
	
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}
	public void setLocalBannerManage(BannerManage localBannerManage) {
		this.localBannerManage = localBannerManage;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getParameter("method");
		if(method.equals("list")){
			return list(mapping, form, request, response);
		}else if(method.equals("add")){
			return  add(mapping, form, request, response);
		}else if(method.equals("search")){
			return  list(mapping, form, request, response);
		}else if(method.equals("save")){
			return  save(mapping, form, request, response);
		}else if(method.equals("setorder")){
			return  setorder(mapping, form, request, response);
		}else if(method.equals("delete")){
			return  delete(mapping, form, request, response);
		}else if(method.equals("update")){
			return  updateBanner(mapping, form, request, response);
		}else if(method.equals("updateState")){
			return  updateState(mapping, form, request, response);
		}else{
			return list(mapping, form, request, response);
		}
	}
	//	stateMath
	/**
	 * 修改发布状态
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private String updateState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		String parameter = request.getParameter("state");
		Integer state = Integer.parseInt(parameter);
		String type1 = request.getParameter("type");
		Integer type = Integer.parseInt(type1);
		
		
		//当type=1时，pc端  state=2只能发布5张
		if(type==1){
			int i = localBannerManage.getAdvertByState(2,type);
			if (id>0 && state >0) {
				if (i<5) {
					localBannerManage.updateState(id, state);
			    	StrutsUtil.renderText(response, "success");
				}else if(i==5 && state == 2){ //只能取消发布
					localBannerManage.updateState(id, 1);
			    	StrutsUtil.renderText(response, "fail");
				}else if(i==5 && state == 1){
					localBannerManage.updateState(id, 1);//取消发布
					StrutsUtil.renderText(response, "success");
				}else {
					StrutsUtil.renderText(response, "fail");
				}
			}else {
				StrutsUtil.renderText(response, "fail");
			}
		}else {//type=2时，移动端     state=2
			int i = localBannerManage.getAdvertByState(2,type);
			if (id>0 && state >0) {
				if (i<5) {
					localBannerManage.updateState(id, state);
			    	StrutsUtil.renderText(response, "success");
				}else if(i==5 && state == 2){ //只能取消发布
					localBannerManage.updateState(id, 1);
			    	StrutsUtil.renderText(response, "fail");
				}else if(i==5 && state == 1){
					localBannerManage.updateState(id, 1);
					StrutsUtil.renderText(response, "success");
				}else {
					StrutsUtil.renderText(response, "fail");
				}
			}else {
				StrutsUtil.renderText(response, "fail");
			}
		}
		/**
		 * 查出来type
		 *//*
		
		
		int i = localBannerManage.getAdvertByState(2,type);
		//接收到id/state=1 发布
		    if(id > 0 && state >0 ){ //2  5   1  5
		    	if (i<5 ) { 
		    		localBannerManage.updateState(id, state);
			    	StrutsUtil.renderText(response, "success");
		    		
				}else if(i==5 && state == 2){ //只能取消发布
					localBannerManage.updateState(id, 1);
			    	StrutsUtil.renderText(response, "fail");
				}else if(i==5 && state == 1){
					localBannerManage.updateState(id, 1);
					StrutsUtil.renderText(response, "success");
				}else {
					StrutsUtil.renderText(response, "fail");
				}
		    	
		    }		*/
		return null;
	}
	//修改
	private String updateBanner(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		Advert advert2 = localBannerManage.getAdvertById(id);
		request.setAttribute("model", advert2);
		int count = localSystemSiteManage.getSystemSiteList().size();
		request.setAttribute("id", id);
 		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
 		request.setAttribute("url", advert2.getUrl());
 		request.setAttribute("cover", advert2.getUrl());
 		request.setAttribute("target_url", advert2.getTarget_url());
 		request.setAttribute("remark", advert2.getRemark());
 		request.setAttribute("name", advert2.getName());
 		request.setAttribute("type", advert2.getType());
 		Advert advert = new Advert();
 		advert.setId(id);
 		List<Advert> list =	localBannerManage.getAdvertList(advert);
 		request.setAttribute("checkSite",  list.get(0).getSiteList());
		return "edit" ;//"add";
		
	}
	/**
	 * 删除操作
	 */
	private String delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		if (id > 0)
			localBannerManage.deleteAdvertById(id);
		StrutsUtil.renderText(response, "success");
		return null;
	}

	/**
	 * 排序
	 */
	private String setorder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		String orderstr = ParamUtils.getParameter(request, "orderstr", "");
		
		boolean flag = localBannerManage.resortOrderNum(orderstr);
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}
	
	/**
	 * 保存
	 * @throws Exception 
	 */
	private String save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		boolean flag = false;
		
		BannerForm bform = (BannerForm) form;
		Advert advert = bform.getModel();	
		if (advert.getCover()!=null) {
			//保存新的url
			advert.setUrl(advert.getCover());
		}else {//修改时，保存旧的图片的url
			Advert advert2 = localBannerManage.getAdvertById(id);
			String url2 = advert2.getUrl();
			advert.setUrl(url2);
		}
		
	/*	Long fileName = Long.valueOf(DateUtils.getNowDateTime());
		if(!StringUtil.checkNull(bform.getMatFile().getFileName())) {
			
		     JSONObject jsonObject = FileLimitUtil.validate(bform.getMatFile());
		     if(!((Integer)jsonObject.get("code")==1)){
		     	response.setCharacterEncoding("UTF-8");  
		     	response.setContentType("application/json; charset=utf-8");
		         response.getWriter().write(jsonObject.toString());
		         response.getWriter().flush();
		         response.getWriter().close();
		     	return null;
		     }
			String sourceKey = FilesUtils.fileUploadToCloud(bform.getMatFile(), request,fileName);
			logger.info("-上传图片返回值----"+sourceKey);
			advert.setUrl(sourceKey);
		}else {
			Advert advert2 = localBannerManage.getAdvertById(id);
			String url2 = advert2.getUrl();
//			String url = null==advert.getUrl()?advert.getUrl():advert.getUrl();
			advert.setUrl(url2);
		}*/
		
		//保存用户信息
		SystemUser user = LogicUtil.getSystemUser(request);
		if(user == null) {
			return "fail";
		}
		advert.setCreator(user.getAccountName());
		
		//保存站点
		long[] site_array = ParamUtils.getLongParameters(request, "site", -1L);
		List<SystemSite> systemSiteList = new ArrayList<SystemSite>();
		
		if(site_array != null) {
			for(int i=0; i<site_array.length; i++) {
				SystemSite systemSite = new SystemSite();
				systemSite.setId(site_array[i]);
				systemSiteList.add(systemSite);			
			}
			
			advert.setSiteList(systemSiteList);
		}
		//设置为   未发布
		advert.setState(1);
		if(id!=null && id > 0L){
			
			Advert article = localBannerManage.getAdvertById(id);
			if(!advert.getName().equals(article.getName())){
				//保存前校验banner标题是否重复,如果重复直接返回
				int count = localBannerManage.getAdvertByName(advert.getName());
				if(count > 0){
					StrutsUtil.renderText(response, "fail");
					return null;
				}
			}
			advert.setId(id);//修改标题
			flag = localBannerManage.updateAdvert(advert);
			logger.info("-修改后的返回值-flag-->"+flag);
		}
		else{
			//保存前校验banner标题是否重复,如果重复直接返回
			int count = localBannerManage.getAdvertByName(advert.getName());
			if(count > 0){
				StrutsUtil.renderText(response, "fail");
				return null;
			}
			flag = localBannerManage.addAdvert(advert);
		}
		
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;

		}

	/**
	 * 添加
	 */
	private String add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//添加--站点
		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
		return "add";
	}
	/**
	 * 查询列表
	 */
	private String list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sdate = request.getParameter("start_date");
		String edate = request.getParameter("end_date");

		BannerForm bform = (BannerForm) form;
		Advert advert = bform.getModel();
		advert.setStart_date(sdate);
		advert.setEnd_date(edate);
		
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		String sort = ParamUtils.getParameter(request, "sort", "ordernum asc, id");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
		
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		pl.setSortCriterion(sort);
		
		if (dir.equals("asc"))
			pl.setSortDirection(SortOrderEnum.ASCENDING);
		else
			pl.setSortDirection(SortOrderEnum.DESCENDING);
		
		localBannerManage.getAdvertPageList(pl, advert);
		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
		request.setAttribute("list", pl);
 		request.setAttribute("model", advert);
		
		return "success";
	}
	}
	
	


