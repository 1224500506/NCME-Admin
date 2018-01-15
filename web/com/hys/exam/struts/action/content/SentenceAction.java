package com.hys.exam.struts.action.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemSite;
import com.hys.exam.service.local.SentenceManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.struts.form.SentenceForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;
import com.hys.xiangyi.model.XiangYiProvince;
import com.hys.xiangyi.service.local.XiangYiProvinceService;

public class SentenceAction extends BaseAction {

	private SystemSiteManage localSystemSiteManage;
	
	private SentenceManage localSentenceManage;
	
	private XiangYiProvinceService xiangYiProvinceService;
	
	public SentenceManage getLocalSentenceManage() {
		return localSentenceManage;
	}

	public void setLocalSentenceManage(SentenceManage localSentenceManage) {
		this.localSentenceManage = localSentenceManage;
	}

	public SystemSiteManage getLocalSystemSiteManage() {
		return localSystemSiteManage;
	}

	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}
	
	public XiangYiProvinceService getXiangYiProvinceService() {
		return xiangYiProvinceService;
	}

	public void setXiangYiProvinceService(
			XiangYiProvinceService xiangYiProvinceService) {
		this.xiangYiProvinceService = xiangYiProvinceService;
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
			return  updateSentence(mapping, form, request, response);
		}else if(method.equals("updateState")){
			return  updateState(mapping, form, request, response);
		}else{
			return list(mapping, form, request, response);
		}
	}
	
	private String setorder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 
		String orderstr = ParamUtils.getParameter(request, "orderstr", "");
		
		boolean flag = localSentenceManage.resortOrderNum(orderstr);
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
		 
	 
	}

	private String updateSentence(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		Sentence sentence = new Sentence();
		sentence.setId(id);
		List<Sentence> list = localSentenceManage.getSentenceList(sentence);
		int count = localSystemSiteManage.getSystemSiteList().size();
 		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
 		request.setAttribute("provinceList",xiangYiProvinceService.getXiangYiProvinceList());
 		request.setAttribute("content", list.get(0).getContent());
 		request.setAttribute("title", list.get(0).getTitle());
 		request.setAttribute("type", list.get(0).getType());
 		request.setAttribute("checkSite", list.get(0).getSiteList());
 		
 		List<XiangYiProvince> xiangYiProvinceList = list.get(0).getXiangYiProvinceList();
 		
 		for (int i = 0; i < xiangYiProvinceList.size(); i++) {
 			XiangYiProvince xiangYiProvince = xiangYiProvinceService.getXiangYiProvinceByProvinceCode(xiangYiProvinceList.get(i).getProvinceId().toString());
			xiangYiProvinceList.get(i).setProvinceId(xiangYiProvince.getProvinceId());
		}
 		
 		request.setAttribute("checkProvince", xiangYiProvinceList);
 		request.setAttribute("source", list.get(0).getSource());
 		request.setAttribute("id", id);
 		//request.setAttribute("yn", ParamUtils.getParameter(request, "yn", ""));
		return "edit" ;//"add";
	}

	private String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		String sdate = request.getParameter("start_date");
		String edate = request.getParameter("end_date");
		
		SentenceForm  aform = (SentenceForm)form;
		Sentence sentence = aform.getModel();
		Long site_id = ParamUtils.getLongParameter(request, "site_id", 0L);
		if (site_id > 0) {
			sentence.setSite_id(site_id);
		}
		sentence.setStart_date(sdate);
		sentence.setEnd_date(edate);
		// paging code added by han.
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
		
		// end.
		
 		//List<Sentence> list=localSentenceManage.getSentenceList(sentence);
 		
		localSentenceManage.getSentencePageList(pl, sentence);
		
 		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
 		request.setAttribute("list", pl);
 		request.setAttribute("model", sentence);
 		request.setAttribute("site_id", site_id);
 		//request.setAttribute("yn", ParamUtils.getParameter(request, "yn", ""));
		return "success";
	}

	private String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)	{
		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
		request.setAttribute("provinceList", xiangYiProvinceService.getXiangYiProvinceList());
		//request.setAttribute("yn", ParamUtils.getParameter(request, "yn", ""));
		return "add";
	}
	
	private String save(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)	{
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		boolean flag = false;
		
		SentenceForm  aform = (SentenceForm)form;
		Sentence sentence = aform.getModel();
		
		long[] site_array = ParamUtils.getLongParameters(request, "site", -1L);
		
		long[] province_array = ParamUtils.getLongParameters(request, "province", -1L);
		
		List<SystemSite> systemSiteList = new ArrayList<SystemSite>();
		
		List<XiangYiProvince> xiangYiProvinceList = new ArrayList<XiangYiProvince>();
		
		if(site_array != null) {
			for(int i=0; i<site_array.length; i++) {
				SystemSite systemSite = new SystemSite();
				systemSite.setId(site_array[i]);
				systemSiteList.add(systemSite);			
			}
			
			sentence.setSiteList(systemSiteList);
		}
		
		if(province_array != null) {
			for(int i=0; i<province_array.length; i++) {
				XiangYiProvince xiangYiProvince1 = new XiangYiProvince();
				Integer provinceId = (int) province_array[i];
				xiangYiProvince1.setProvinceId(provinceId);
				xiangYiProvinceList.add(xiangYiProvince1);			
			}
			
			sentence.setXiangYiProvinceList(xiangYiProvinceList);
		}
		
		sentence.setState(1);
		
		/*if(sentence.getContent()==null ||sentence.getType()==0|| sentence.getTitle()==null){
			 
		}
		else */
		if(id!=null && id > 0L){
			Sentence article = localSentenceManage.getSentenceById(id);
			if(!sentence.getTitle().equals(article.getTitle())){
				//保存前校验文章标题是否重复,如果重复直接返回
				int count = localSentenceManage.getSentenceByTitle(sentence.getTitle());
				if(count > 0){
					StrutsUtil.renderText(response, "fail");
					return null;
				}
			}
			sentence.setId(id);
			flag = localSentenceManage.updateSentence(sentence);
		}
		else{
			//保存前校验文章标题是否重复,如果重复直接返回
			int count = localSentenceManage.getSentenceByTitle(sentence.getTitle());
			if(count > 0){
				StrutsUtil.renderText(response, "fail");
				return null;
			}
			
			flag = localSentenceManage.addSentence(sentence);
		}
		
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");

		return null;
	}

	private String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)	{
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		if (id > 0)
			localSentenceManage.deleteSentenceById(id);
		StrutsUtil.renderText(response, "success");
		return null;
	}

	private String updateState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)	{
		
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		int state = Integer.parseInt(ParamUtils.getParameter(request, "state", "-1").toString());

		if (id > 0 && state > 0)
			localSentenceManage.updateState(id, state);
		StrutsUtil.renderText(response, "success");
	
		return null;
	}
}
