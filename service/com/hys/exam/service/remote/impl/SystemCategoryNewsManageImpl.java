package com.hys.exam.service.remote.impl;

import java.util.Date;
import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.remote.SystemCategoryNewsDao;
import com.hys.exam.model.system.SystemCategory;
import com.hys.exam.model.system.SystemNews;
import com.hys.exam.service.remote.SystemCategoryNewsManage;

public class SystemCategoryNewsManageImpl implements SystemCategoryNewsManage{
	
	private SystemCategoryNewsDao systemCategoryNewsDAO;
	
	//得到 栏目列表
	public void getSystemCategoryList(Page<SystemCategory> page, SystemCategory systemCategory){
		systemCategoryNewsDAO.getSystemCategoryList(page, systemCategory);
	}
	
	//获取一个栏目
	public SystemCategory getSystemCategoryById(Long id){
		return systemCategoryNewsDAO.getSystemCategoryById(id);
	}
	
	//添加栏目
	public void addSystemCategory(SystemCategory systemCategory){
		systemCategoryNewsDAO.addSystemCategory(systemCategory);
	}
	
	//修改栏目
	public void updateSystemCategory(SystemCategory systemCategory){
		systemCategoryNewsDAO.updateSystemCategory(systemCategory);
	}
	
	//删除栏目
	public void deleteSystemCategory(Long ...id){
		systemCategoryNewsDAO.deleteSystemCategory(id);
	}
	
	//得到 新闻列表
	public void getSystemNewsList(Page<SystemNews> page, SystemNews systemNews){
		systemCategoryNewsDAO.getSystemNewsList(page, systemNews);
		List<SystemNews> list = page.getList();
		if(list!=null && list.size() > 0){
			for(SystemNews news : list){
				news.setSystemIndustryList(systemCategoryNewsDAO.getSystemIndustryByNewsId(news.getId()));
			}
		}
	}
	
	//获取一个新闻
	public SystemNews getSystemNewsById(Long id){
		SystemNews news = systemCategoryNewsDAO.getSystemNewsById(id);
		if(news != null){
			news.setSystemIndustryList(systemCategoryNewsDAO.getSystemIndustryByNewsId(news.getId()));
		}
		return news;
	}
	
	//添加新闻
	public void addSystemNews(SystemNews systemNews){
		systemNews.setCreateDate(new Date());
		systemCategoryNewsDAO.addSystemNews(systemNews);
		if(systemNews.getSystemIndustryIds()!=null && systemNews.getSystemIndustryIds().length > 0){
			systemCategoryNewsDAO.insertSystemIndustry(systemNews.getId(), systemNews.getSystemIndustryIds());
		}
	}
	
	//修改新闻
	public void updateSystemNews(SystemNews systemNews){
		systemCategoryNewsDAO.updateSystemNews(systemNews);
		if(systemNews.getSystemIndustryIds()!=null && systemNews.getSystemIndustryIds().length > 0){
			systemCategoryNewsDAO.deleteSystemIndustryByNewsId(systemNews.getId());
			systemCategoryNewsDAO.insertSystemIndustry(systemNews.getId(), systemNews.getSystemIndustryIds());
		}
	}
	
	//删除新闻
	public void deleteSystemNews(Long ...id){
		systemCategoryNewsDAO.deleteSystemNews(id);
		if(id!=null && id.length > 0){
			for(Long idd : id){
				systemCategoryNewsDAO.deleteSystemIndustryByNewsId(idd);
			}
		}
	}

	public void setSystemCategoryNewsDAO(SystemCategoryNewsDao systemCategoryNewsDAO) {
		this.systemCategoryNewsDAO = systemCategoryNewsDAO;
	}

	public SystemCategoryNewsDao getSystemCategoryNewsDAO() {
		return systemCategoryNewsDAO;
	}
	
}
