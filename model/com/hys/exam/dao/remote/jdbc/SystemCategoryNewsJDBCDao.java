package com.hys.exam.dao.remote.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.jdbc.AbstractJDBCDAO;
import com.hys.exam.dao.remote.SystemCategoryNewsDao;
import com.hys.exam.model.system.SystemCategory;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemNews;
import com.hys.exam.utils.PageUtil;

public class SystemCategoryNewsJDBCDao extends AbstractJDBCDAO implements SystemCategoryNewsDao {
	final static private String getSystemCategoryList_SQL = "select t.title,t.id,t.create_Date,t.application_Id,t2.domain_name from train_content_category t left join system_site t2 on t2.id=t.application_Id where 1=1 ";
	final static private String addSystemCategory_SQL = "insert into train_content_category (id,title,create_Date,application_Id) values(:id,:title,sysdate(),:applicationId)";
	final static private String updateSystemCategory_SQL = "update train_content_category set title = :title,application_Id = :applicationId where id = :id";
	final static private String deleteSystemCategory_SQL = "delete from train_content_category where id=:id ";
	final static private String getSystemNewsList_SQL = "select * from TRAIN_CONTENT_NEWS t where t.CATEGORY_ID = ? ";
	final static private String getSystemIndustryByNewsId_SQL = "select t2.* from train_content_news_industry t join system_industry t2 on t.industry_id = t2.id where t.news_id = ?";
	final static private String deleteSystemIndustryByNewsId_SQL = "delete from train_content_news_industry where news_id = ? ";
	final static private String insertSystemIndustry_SQL = "insert into train_content_news_industry values(?,?)";
	final static private String getSystemNewsById_SQL = "select * from TRAIN_CONTENT_NEWS t where t.id = ? ";
	final static private String addSystemNews_SQL = 
			"insert into TRAIN_CONTENT_NEWS (id,title,type,content,original,author,create_Date,category_Id,url) " +
									 "values(:id,:title,:type,:content,:original,:author,:createDate,:categoryId,:url) ";
	final static private String updateSystemNews_SQL="update TRAIN_CONTENT_NEWS set title=:title,type=:type,content=:content,original=:original,author=:author,edit_Date=:editDate,url=:url where id=:id";
	final static private String deleteSystemNews_SQL = "delete from TRAIN_CONTENT_NEWS where id=:id ";
	
	final static private String getNewsCountByCategoryId_SQL = "select id from TRAIN_CONTENT_NEWS where category_Id = ? ";
	//得到 栏目列表
	public void getSystemCategoryList(Page<SystemCategory> page, SystemCategory systemCategory){
		StringBuilder sql = new StringBuilder(getSystemCategoryList_SQL);
		List<Object> list = new ArrayList<Object>();
		if(systemCategory.getApplicationId()!=null && !"".equals(systemCategory.getApplicationId())){
			sql.append("and t.APPLICATION_ID = ? ");
			list.add(systemCategory.getApplicationId());
		}
		if(systemCategory.getTitle()!=null && !"".equals(systemCategory.getTitle())){
			sql.append("and t.title like ? ");
			list.add("%"+systemCategory.getTitle().trim()+"%");
		}
		
		List<SystemCategory> resultList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCategory.class));
		if(resultList!=null && resultList.size() > 0){
			for(SystemCategory c : resultList){
				c.setNewsNum(getCount(getNewsCountByCategoryId_SQL,c.getId()));
			}
		}
		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setCount(totalCount);
		page.setList(resultList);
		
	}
	
	//获取一个栏目
	public SystemCategory getSystemCategoryById(Long id){
		SystemCategory c = null;
		StringBuilder sql = new StringBuilder(getSystemCategoryList_SQL);
		sql.append("and t.id = ? ");
		List<Object> list = new ArrayList<Object>();
		list.add(id);
		List<SystemCategory> resultList = getList(sql.toString(),list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCategory.class));
		if(resultList != null && resultList.size() > 0){
			c = resultList.get(0);
			c.setNewsNum(getCount(getNewsCountByCategoryId_SQL, c.getId()));
			return c;
		}
		return c;
	}
	
	//添加栏目
	public void addSystemCategory(SystemCategory systemCategory){
		Long id = this.getSequence("TRAIN_CONTENT_CATEGORY_SEQ");
		systemCategory.setId(id);
		getNamedParameterJdbcTemplate().update(addSystemCategory_SQL, new BeanPropertySqlParameterSource(systemCategory));
	}
	
	//修改栏目
	public void updateSystemCategory(SystemCategory systemCategory){
		getNamedParameterJdbcTemplate().update(updateSystemCategory_SQL, new BeanPropertySqlParameterSource(systemCategory));
	}
	
	//删除栏目
	public void deleteSystemCategory(Long ...id){
		for(Long i : id){
			SystemCategory systemCategory = new SystemCategory();
			systemCategory.setId(i);
			getNamedParameterJdbcTemplate().update(deleteSystemCategory_SQL, new BeanPropertySqlParameterSource(systemCategory));
		}
	}
	
	//得到 新闻列表
	public void getSystemNewsList(Page<SystemNews> page, SystemNews systemNews){
		StringBuilder sql = new StringBuilder(getSystemNewsList_SQL);
		List<Object> list = new ArrayList<Object>();
		list.add(systemNews.getCategoryId());
		if(systemNews.getTitle()!=null && !"".equals(systemNews.getTitle())){
			sql.append("and t.title like ? ");
			list.add("%"+systemNews.getTitle().trim()+"%");
		}
		List<SystemNews> resultList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemNews.class));
		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resultList);
		page.setCount(totalCount);
	}
	//根据新闻ID 获取行业或者岗位
	public List<SystemIndustry> getSystemIndustryByNewsId(Long id){
		return getList(getSystemIndustryByNewsId_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemIndustry.class),id);
	}
	//删除新闻 和 行业 的关系
	public void deleteSystemIndustryByNewsId(Long id){
		this.updateNativeSqlStr(deleteSystemIndustryByNewsId_SQL, id);
	}
	//增加 新闻和行业的关系
	public void insertSystemIndustry(Long newsId,Long[] systemIndustryIds){
		for(Long systemIndustryId: systemIndustryIds){
			if(systemIndustryId >-1){
				this.updateNativeSqlStr(insertSystemIndustry_SQL, newsId,systemIndustryId);
			}
		}
	}
	
	//获取一个新闻
	public SystemNews getSystemNewsById(Long id){
		StringBuilder sql = new StringBuilder(getSystemNewsById_SQL);
		List<Object> list = new ArrayList<Object>();
		list.add(id);
		List<SystemNews> resultList = getList(sql.toString(),list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemNews.class));
		if(resultList != null && resultList.size() > 0){
			return resultList.get(0);
		}
		return null;
	}
	
	//添加新闻
	public void addSystemNews(SystemNews systemNews){
		Long id = this.getSequence("TRAIN_CONTENT_NEWS_SEQ");
		systemNews.setId(id);
		getNamedParameterJdbcTemplate().update(addSystemNews_SQL, new BeanPropertySqlParameterSource(systemNews));
	}
	
	//修改新闻
	public void updateSystemNews(SystemNews systemNews){
		getNamedParameterJdbcTemplate().update(updateSystemNews_SQL, new BeanPropertySqlParameterSource(systemNews));
	}
	
	//删除新闻
	public void deleteSystemNews(Long ...id){
		for(Long i : id){
			SystemNews systemNews = new SystemNews();
			systemNews.setId(i);
			getNamedParameterJdbcTemplate().update(deleteSystemNews_SQL, new BeanPropertySqlParameterSource(systemNews));
		}
	}
}
