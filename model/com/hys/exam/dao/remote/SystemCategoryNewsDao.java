package com.hys.exam.dao.remote;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.system.SystemCategory;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemNews;

public interface SystemCategoryNewsDao {
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
	
	/**
	 * 根据新闻ID 获取行业或者岗位
	 * @param id
	 * @return
	 */
	public List<SystemIndustry> getSystemIndustryByNewsId(Long id);
	/**
	 * 删除新闻 和 行业 的关系
	 * @param id
	 */
	public void deleteSystemIndustryByNewsId(Long id);
	/**
	 * 增加 新闻和行业的关系
	 * @param newsId
	 * @param systemIndustryIds
	 */
	
	public void insertSystemIndustry(Long newsId,Long[] systemIndustryIds);
	
	//获取一个新闻
	public SystemNews getSystemNewsById(Long id);
	
	//添加新闻
	public void addSystemNews(SystemNews systemNews);
	
	//修改新闻
	public void updateSystemNews(SystemNews systemNews);
	
	//删除新闻
	public void deleteSystemNews(Long ...id);
}
