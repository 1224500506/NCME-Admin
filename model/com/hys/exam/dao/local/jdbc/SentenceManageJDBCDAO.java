package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.SentenceManageDAO;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemSite;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.xiangyi.model.XiangYiProvince;

public class SentenceManageJDBCDAO extends BaseDao implements SentenceManageDAO {

	@Override
	public Sentence getSentenceById(Long id) {
		// TODO Auto-generated method stub
		Sentence sentence = new Sentence();
		String sql = "select * from content_sentence where id=?";
		sentence = getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class), id);
		return sentence;
	}

	@Override
	public List<Sentence> getSentenceList(Sentence sentence) {
		
		List <Object>values = new ArrayList<Object>();
		List<Sentence> list=new ArrayList<Sentence>();
		
		if(sentence.getId() != null){
			StringBuilder sql = new StringBuilder();
			sql.append( "select  DISTINCT cs.* FROM content_sentence cs left join content_sentence_site ss on cs.id=ss.SENTENCE_ID where"); 
			sql.append(" cs.id = ?");
			values.add(sentence.getId());
			
			String sql_sites = "SELECT css.site_id as id FROM content_sentence_site css WHERE css.SENTENCE_ID=" + sentence.getId();
			List<SystemSite> siteList = getJdbcTemplate().query(sql_sites,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
			
			String sql_provinces = "select csp.province_id as provinceId from content_sentence_province csp where csp.SENTENCE_ID=" + sentence.getId();
			List<XiangYiProvince> xiangYiProvinceList = getJdbcTemplate().query(sql_provinces,ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
			
			list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class),values.toArray());
			
			list.get(0).setSiteList(siteList);
			list.get(0).setXiangYiProvinceList(xiangYiProvinceList);
		}		
		
		
		if(sentence.getId() == null) {
				StringBuilder query= new StringBuilder( "select  DISTINCT cs.* FROM content_sentence cs left join content_sentence_site ss on cs.id=ss.SENTENCE_ID where  cs.id>0 "); 
				
				
				if(sentence.getType()!=null &&sentence.getType()!=0 ){
					query.append(" AND  cs.type = ?");  
					values.add(sentence.getType());
				}
				
			 	if(sentence.getTitle()!=null && !sentence.getTitle().equals("")){
			 		query.append(" AND cs.title like ?");
			 		values.add("%"+sentence.getTitle()+"%");
				}
				 if(sentence.getState()!=null&&sentence.getState()!=0 ){
					 query.append(" AND cs.state=?");
					values.add(sentence.getState());
				}
				if(sentence.getSite_id()!=null && sentence.getSite_id() > 0 ){
					query.append(" AND ss.site_id=?");
					values.add(sentence.getSite_id());
				}
				if(sentence.getDeli_date()!=null && !sentence.getDeli_date().equals("") ){
					query.append(" AND cs.deli_date=?"); 
					
					values.add(DateUtil.parse(sentence.getDeli_date(), "yyyy-MM-dd"));
				}
				query.append(" order by ordernum asc, id desc") ;
				
				 list =  getJdbcTemplate().query(query.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class),values.toArray());	
				
				for (Sentence s:list) {
					String sql_site = "select t.* from SYSTEM_SITE t, CONTENT_SENTENCE_SITE t2 where t.ID=t2.SITE_ID and t2.SENTENCE_ID=" + s.getId();
					List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
					
					if(siteList.size() > 0)
						s.setSiteList(siteList);
				}		
		}
		return list;
 }

	@Override
	public  boolean addSentence(Sentence sentence) {
		 
		sentence.setId(this.getSequence("CONTENT_SENTENCE"));
		String sql="insert into CONTENT_SENTENCE (ID,TITLE,TYPE,STATE,CONTENT,SOURCE) values(:id,:title,:type,:state,:content,:source)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sentence));
		String site_sql;
		if (sentence.getSiteList()!=null)
			for(int i=0;i<sentence.getSiteList().size();i++){
				 site_sql="insert into content_sentence_site (sentence_id,site_id) values(?,?)";
				 getJdbcTemplate().update(site_sql, sentence.getId(),sentence.getSiteList().get(i).getId());
			}
		//getJdbcTemplate().query(sql,  list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class));
		String sentence_province_sql;
		if (sentence.getXiangYiProvinceList()!=null)
			for(int i=0;i<sentence.getXiangYiProvinceList().size();i++){
				sentence_province_sql="insert into content_sentence_province (sentence_id,province_id) values(?,?)";
				String province_sql = "select * from xiangyi_province where province_id="+sentence.getXiangYiProvinceList().get(i).getProvinceId();
				
				List<XiangYiProvince> proList = getJdbcTemplate().query(province_sql,ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
				
				 getJdbcTemplate().update(sentence_province_sql, sentence.getId(),Integer.valueOf(proList.get(0).getProvinceCode()));
			}
		return true;
	}

	@Override
	public boolean deleteSentenceById(Long id) {

		String sql = "delete from CONTENT_SENTENCE_SITE where SENTENCE_ID=" + id;
		getJdbcTemplate().execute(sql);
		
	    sql = "delete from CONTENT_SENTENCE_PROVINCE where SENTENCE_ID=" + id;
		getJdbcTemplate().execute(sql);
		
		sql = "delete from CONTENT_SENTENCE where ID=" + id;
		getJdbcTemplate().execute(sql);
		
		return true;
	}

//	Date today=new Date();
	@Override
	
	public boolean updateState(Long id, int state) {
	   if(state==1){
		String sql = "update CONTENT_SENTENCE set STATE=" + state + ", DELI_DATE=null where ID=" + id;
		getJdbcTemplate().execute(sql);
		return true;
	   }
	   else{
		String sql = "update CONTENT_SENTENCE set STATE=" + state + ", DELI_DATE='"+DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")+"' where ID=" + id;
		getJdbcTemplate().execute(sql);
		return true;
	   }
		
	}
	public boolean resortOrderNum(String orderstr)
	{
	String item[] = orderstr.split(";");
		
		String SORT_SQL = "update content_sentence set ordernum=? where id=?";
		
		for (int i = 0; i < item.length; i++){
			Long id = 0L;
			Long orderNum = null;
			try{
				String val[] = item[i].split("_");
				id = Long.valueOf(val[0]);
				if(val.length==1){
					orderNum = Long.valueOf("999999999");
				}else{
					orderNum = Long.valueOf(val[1]);
				}
			}catch(Exception e){;}

			getJdbcTemplate().update(SORT_SQL, orderNum, id);
		}
		return true;
	}

	@Override
	public boolean updateSentence(Sentence sentence) {
		String sql = "update content_sentence set title=?, type=?, state=?, content=?, source=? where id=?";
		getSimpleJdbcTemplate().update(sql, sentence.getTitle(), sentence.getType(), sentence.getState(), sentence.getContent(), sentence.getSource(),sentence.getId());
		sql = "delete from CONTENT_SENTENCE_SITE where SENTENCE_ID=" + sentence.getId();
		getJdbcTemplate().execute(sql);
		sql = "delete from CONTENT_SENTENCE_PROVINCE where SENTENCE_ID=" + sentence.getId();
		getJdbcTemplate().execute(sql);
		
		if (sentence.getSiteList()!=null)
			for(SystemSite systemSite: sentence.getSiteList()){
				sql = "insert into CONTENT_SENTENCE_SITE (sentence_id, site_id) values (" + sentence.getId()+ "," + systemSite.getId()+")"; 
				getJdbcTemplate().update(sql);
			}
		
		
		
	/*	if (sentence.getXiangYiProvinceList()!=null)
			for(int i=0;i<sentence.getXiangYiProvinceList().size();i++){
				sentence_province_sql="insert into content_sentence_province (sentence_id,province_id) values(?,?)";
				String province_sql = "select * from xiangyi_province where province_id="+sentence.getXiangYiProvinceList().get(i).getProvinceId();
				
				List<XiangYiProvince> proList = getJdbcTemplate().query(province_sql,ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
				
				 getJdbcTemplate().update(sentence_province_sql, sentence.getId(),Integer.valueOf(proList.get(0).getProvinceCode()));
			}*/
		
		
		
		
		if (sentence.getXiangYiProvinceList()!=null)
			for(XiangYiProvince xiangYiProvince: sentence.getXiangYiProvinceList()){
				String province_sql = "select * from xiangyi_province where province_id="+xiangYiProvince.getProvinceId();
				
				List<XiangYiProvince> proList = getJdbcTemplate().query(province_sql,ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
				
				sql = "insert into CONTENT_SENTENCE_PROVINCE (sentence_id, province_id) values (" + sentence.getId()+ "," + Integer.valueOf(proList.get(0).getProvinceCode())+")"; 
				getJdbcTemplate().update(sql);
			}
		return true;
	}

	@Override
	public void getSentencePageList(PageList pl, Sentence sentence) {
		List <Object>values = new ArrayList<Object>();
		List<Sentence> list=new ArrayList<Sentence>();
		
		StringBuilder query= new StringBuilder( "select  DISTINCT cs.* FROM content_sentence cs left join content_sentence_site ss on cs.id=ss.SENTENCE_ID where  cs.id>0 "); 
		
		if(sentence.getType()!=null &&sentence.getType()!=0 ){
			query.append(" AND  cs.type = ?");  
			values.add(sentence.getType());
		}
		
	 	if(sentence.getTitle()!=null && !sentence.getTitle().equals("")){
	 		query.append(" AND cs.title like ?");
	 		values.add("%"+sentence.getTitle()+"%");
		}
		 if(sentence.getState()!=null&&sentence.getState()!=0 ){
			 query.append(" AND cs.state=?");
			values.add(sentence.getState());
		}
		if(sentence.getSite_id()!=null && sentence.getSite_id() > 0 ){
			query.append(" AND ss.site_id=?");
			values.add(sentence.getSite_id());
		}
		
		if(sentence.getStart_date()!=null && !sentence.getStart_date().equals("")){
			query.append(" AND cs.deli_date>'"+sentence.getStart_date()+"'");
//			values.add(DateUtil.parse(sentence.getStart_date(), "yyyy-MM-dd HH:mm:dd"));
		}
		if(sentence.getEnd_date()!=null && !sentence.getEnd_date().equals("")){
			query.append(" AND cs.deli_date<'"+sentence.getEnd_date()+"'");
//			values.add(DateUtil.parse(sentence.getEnd_date(), "yyyy-MM-dd HH:mm:dd"));
		}
		
//		if(sentence.getDeli_date()!=null && !sentence.getDeli_date().equals("") ){
//			query.append(" AND cs.deli_date=?");
//			
//			values.add(DateUtil.parse(sentence.getDeli_date(), "yyyy-MM-dd"));
//		}

		// paging code added by han.
		if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			query.append(" order by ").append(pl.getSortCriterion());
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				query.append(" desc");
		}
		
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(query.toString()), values.toArray());
		pl.setFullListSize(fullListSize);

		list = getJdbcTemplate().query(PageUtil.getPageSql(query.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class),values.toArray());
		
		for (Sentence s:list) {
			String sql_site = "select t.* from SYSTEM_SITE t, CONTENT_SENTENCE_SITE t2 where t.ID=t2.SITE_ID and t2.SENTENCE_ID=" + s.getId();
			List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
			
			if(siteList.size() > 0)
				s.setSiteList(siteList);
			
			String deliDate = s.getDeli_date();
			if(deliDate!=null && !deliDate.equals(""))
				s.setDeli_date(deliDate.substring(0, deliDate.indexOf(".")));
		}
		
		pl.setList(list);
	}

	/**
	 * 根据标题查询文章
	 * @param title
	 * @return
	 */
	@Override
	public int getSentenceByTitle(String title) {
		String sql = "select count(*) from content_sentence t where t.title = ?";
		int count = 1;
		try {
			count = getJdbcTemplate().queryForInt(sql, title);
		} catch (DataAccessException e) {
		}
		return count;
	}
}
