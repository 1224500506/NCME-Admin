package com.hys.exam.service.remote;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.system.SystemCategory;
import com.hys.exam.model.system.SystemNews;

public interface SystemCategoryNewsManage {
	
	//得到 栏目列表
	public void getSystemCategoryList(Page<SystemCategory> page, SystemCategory systemCategory);
	
	//获取一个栏目
	public SystemCategory getSystemCategoryById(Long id);
	
	//添加栏目
	public void addSystemCategory(SystemCategory systemCategory);
	
	//修改栏目
	public void updateSystemCategory(SystemCategory systemCategory);
	
	//删除栏目
	public void deleteSystemCategory(Long ...id);
	
	//得到 新闻列表
	public void getSystemNewsList(Page<SystemNews> page, SystemNews systemNews);
	
	//获取一个新闻
	public SystemNews getSystemNewsById(Long id);
	
	//添加新闻
	public void addSystemNews(SystemNews systemNews);
	
	//修改新闻
	public void updateSystemNews(SystemNews systemNews);
	
	//删除新闻
	public void deleteSystemNews(Long ...id);
	
}
