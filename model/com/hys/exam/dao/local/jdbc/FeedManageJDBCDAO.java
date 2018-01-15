package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.FeedManageDAO;
import com.hys.exam.model.Advert;
import com.hys.exam.model.Feedback;
import com.hys.exam.model.SystemSite;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

public class FeedManageJDBCDAO  extends BaseDao implements FeedManageDAO {

	@Override
	public void getFeedbackPageList(PageList pl, Feedback feedback) {
		
		List<Object> values = new ArrayList<Object>();  
		List<Feedback> list=new ArrayList<>();
		StringBuilder query= new StringBuilder( " select  * FROM feedback where 1=1 "); 
		//操作系统分类     
		if(feedback.getSystem() !=null && !feedback.getSystem().equals("") ){
			query.append(" AND  system like ?");  
			values.add("%"+feedback.getSystem()+"%");
		}
		
		//类型 0:未发布  1:已发布
		 if(feedback.getState()!=null&&feedback.getState()!=0 ){
			 query.append(" AND state= '"+feedback.getState()+"'");
		}
		//内容
		/* if(sentence.getTitle()!=null && !sentence.getTitle().equals("")){
		 		query.append(" AND cs.title like ?");
		 		values.add("%"+sentence.getTitle()+"%");
			}*/
		 if(feedback.getContent() !=null && !feedback.getContent().equals("") ){
			 query.append(" AND CONTENT like ?");
			 values.add("%"+feedback.getContent()+"%");
		} 
		 
		//发布开始时间
		if(feedback.getStart_date()!=null && !feedback.getStart_date().equals("")){
			query.append(" AND backtime>'"+feedback.getStart_date()+"'");
		}
		//发布结束时间
		if(feedback.getEnd_date()!=null && !feedback.getEnd_date().equals("")){
			query.append(" AND backtime<'"+feedback.getEnd_date()+"'");
		}
			query.append("");
		//排序-------->
		/*if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			query.append(" order by backtime ");
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				query.append(" desc");
		}*/
		query.append("order by backtime desc");
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(query.toString()), values.toArray());
		pl.setFullListSize(fullListSize);
		list = getJdbcTemplate().query(PageUtil.getPageSql(query.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(Feedback.class),values.toArray());
		pl.setList(list);
	}


	//查询站点
	@Override
	public List<SystemSite> getSiteListByFeebackId(long id) {
		String sql_site = "SELECT ss.* FROM system_site ss,feedback_site fs where ss.ID=fs.SID AND fs.FID=" + id;
		List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		return siteList;
	}


	@Override
	public Feedback getfeedbackyId(Long id) {
		Feedback feedback = new Feedback();
		String sql = "select * from feedback where ID=" + id;
		feedback = getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Feedback.class));
		return feedback;
	}


	@Override
	public boolean deleteFeedbackById(Long id) {
		String sql = "delete from feedback where ID=" + id;
		getJdbcTemplate().execute(sql);
		
		return true;
	}


	@Override
	public boolean updateState(Long id, int state) {
		 if(state == 1  && id >0){
				String sql = "update feedback set STATE=2 where id=" + id;
				getJdbcTemplate().execute(sql);
				return true;
			   }
		   else{
			String sql = "update feedback set STATE=  2   where id=" + id;
			getJdbcTemplate().execute(sql);
			return true;
		   }
	}


	@Override
	public int deleteFeedbackType(Long id) {
		return this.getJdbcTemplate().update("delete from feedback where id = ?", id);
	}

}
