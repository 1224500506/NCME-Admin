package com.hys.exam.struts.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.system.SystemNews;
import com.hys.exam.service.remote.SystemCategoryNewsManage;
import com.hys.exam.service.remote.SystemPropManage;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.struts.form.SystemNewsForm;
import com.hys.exam.utils.FilesUtils;

public class SystemNewsAction extends AppBaseAction {
	
	private SystemCategoryNewsManage systemCategoryNewsManage;
	
	private SystemPropManage systemPropManage;
	
	private SystemSiteFacade systemSiteFacade;

	public SystemCategoryNewsManage getSystemCategoryNewsManage() {
		return systemCategoryNewsManage;
	}

	public void setSystemCategoryNewsManage(
			SystemCategoryNewsManage systemCategoryNewsManage) {
		this.systemCategoryNewsManage = systemCategoryNewsManage;
	}

	public SystemPropManage getSystemPropManage() {
		return systemPropManage;
	}

	public void setSystemPropManage(SystemPropManage systemPropManage) {
		this.systemPropManage = systemPropManage;
	}
	public SystemSiteFacade getSystemSiteFacade() {
		return systemSiteFacade;
	}

	public void setSystemSiteFacade(SystemSiteFacade systemSiteFacade) {
		this.systemSiteFacade = systemSiteFacade;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		setData(request);
		String method = request.getParameter("method");
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		if("delete".equals(method)){
			return this.delete(mapping, form, request, response);
		}else if("updateUI".equals(method)){
			return this.updateUI(mapping, form, request, response);
		}else if("update".equals(method)){
			return this.update(mapping, form, request, response);
		}else if("addUI".equals(method)){
			return this.addUI(mapping, form, request, response);
		}else if("add".equals(method)){
			return this.add(mapping, form, request, response);
		}else{
			return this.list(mapping, form, request, response);
		}
	}
	//栏目列表
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String categoryId = request.getParameter("categoryId");
		request.setAttribute("categoryId", categoryId);
		SystemNews systemNews = new SystemNews();
		systemNews.setCategoryId(Long.valueOf(categoryId));
		
		String title = request.getParameter("title");
		if(title != null && !"".equals(title)){
			systemNews.setTitle(title);
			request.setAttribute("title", title);
		}
		Page<SystemNews> page = createPage(request,"systemNews");
		systemCategoryNewsManage.getSystemNewsList(page, systemNews);
		request.setAttribute("page", page);
		return "list";
	}
	//栏目列表
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String[] ids = request.getParameterValues("systemNewsIds");
		if(ids!=null && ids.length!=0){
			Long[] longIds = new Long[ids.length];
			int i = 0;
			for(String id : ids){
				longIds[i++] = Long.valueOf(id.trim());
			}
			systemCategoryNewsManage.deleteSystemNews(longIds);
			request.setAttribute("message", "删除成功!");
		}else{
			request.setAttribute("message", "没有选择要删除的新闻!");
		}
		return this.list(mapping, form, request, response);
	}
	//更新栏目UI
	protected String updateUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("industryList", systemPropManage.getSystemIndustryList(0l));
		
		String newsId = request.getParameter("newsId");
		if(null!=newsId && !newsId.equals("")){
			request.setAttribute("systemNews", systemCategoryNewsManage.getSystemNewsById(Long.valueOf(newsId)));
		}
		return "update";
	}
	//更新栏目
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try{	
			SystemNews systemNews = new SystemNews();
			systemNews.setId(Long.valueOf(request.getParameter("id")));
			systemNews.setTitle(request.getParameter("title"));
			systemNews.setType(Integer.valueOf(request.getParameter("type")));
			systemNews.setAuthor(request.getParameter("author"));
			systemNews.setOriginal(request.getParameter("original"));
			systemNews.setContent(request.getParameter("content"));
			systemNews.setCategoryId(Long.valueOf(request.getParameter("categoryId")));
			if(systemNews.getType() == 3){//外链栏目
				systemNews.setUrl(request.getParameter("url"));
			}else if(systemNews.getType() == 2 && ((SystemNewsForm)form).getFile() != null){//下载栏目
				String fileName = FilesUtils.upload(((SystemNewsForm)form).getFile(),request,Constants.UPLOAD_FILE_PATH);
				String filePath = fileName == null ? null : Constants.UPLOAD_FILE_PATH+ "/" + fileName;
				systemNews.setUrl(filePath);
				systemNews.setContent(((SystemNewsForm)form).getFile().getFileName());
			}
			
			String[] ids = request.getParameterValues("systemIndustryIds");
			if(ids!=null && ids.length!=0){
				Long[] longIds = new Long[ids.length];
				int i = 0;
				for(String id : ids){
					longIds[i++] = Long.valueOf(id.trim());
				}
				systemNews.setSystemIndustryIds(longIds);
			}
			systemCategoryNewsManage.updateSystemNews(systemNews);
			request.setAttribute("message", "添加栏目成功!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "添加栏目失败!");
		}
		request.setAttribute("message", "更新栏目成功!");
		return this.list(mapping, form, request, response);
	}
	//添加新闻UI
	protected String addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("industryList", systemPropManage.getSystemIndustryList(0l));
		return "add";
	}
	//添加 栏目
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try{	
			SystemNews systemNews = new SystemNews();
			systemNews.setTitle(request.getParameter("title"));
			systemNews.setType(Integer.valueOf(request.getParameter("type")));
			systemNews.setAuthor(request.getParameter("author"));
			systemNews.setOriginal(request.getParameter("original"));
			systemNews.setContent(request.getParameter("content"));
			systemNews.setCategoryId(Long.valueOf(request.getParameter("categoryId")));
			if(systemNews.getType() == 3){//外链栏目
				systemNews.setUrl(request.getParameter("url"));
			}else if(systemNews.getType() == 2){//下载栏目
				String fileName = FilesUtils.upload(((SystemNewsForm)form).getFile(),request,Constants.UPLOAD_FILE_PATH);
				String filePath = fileName == null ? null : Constants.UPLOAD_FILE_PATH+ "/" + fileName;
				systemNews.setUrl(filePath);
				systemNews.setContent(((SystemNewsForm)form).getFile().getFileName());
			}
			
			String[] ids = request.getParameterValues("systemIndustryIds");
			if(ids!=null && ids.length!=0){
				Long[] longIds = new Long[ids.length];
				int i = 0;
				for(String id : ids){
					longIds[i++] = Long.valueOf(id.trim());
				}
				systemNews.setSystemIndustryIds(longIds);
			}
			systemCategoryNewsManage.addSystemNews(systemNews);
			request.setAttribute("message", "添加栏目成功!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "添加栏目失败!");
		}
		return this.list(mapping, form, request, response);
	}
	private void setData(HttpServletRequest request){
		List<SystemSite> siteList = systemSiteFacade.getListByItem(new SystemSite());
		request.setAttribute("siteList", siteList);
	}

	
}
