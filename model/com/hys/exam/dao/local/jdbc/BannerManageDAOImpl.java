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
import com.hys.exam.dao.local.BannerManageDAO;
import com.hys.exam.model.Advert;
import com.hys.exam.model.AdvertImage;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.xiangyi.model.XiangYiProvince;

public class BannerManageDAOImpl extends BaseDao implements BannerManageDAO {

	@Override
	public Advert getAdvertById(Long id) {
		Advert advert = new Advert();
		String sql = "select * from advert where ID=" + id;
		advert = getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Advert.class));
		
		return advert;
	}
	
	@Override
	public boolean addAdvert(Advert advert) {
		
		advert.setId(this.getSequence("ADVERT"));
		String sql="insert into ADVERT (NAME,URL,TARGET_URL,REMARK,TYPE,STATE,CREATOR) values(:name,:url,:target_url,:remark,:type,:state,:creator)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(advert));
		String site_sql;
		if (advert.getSiteList()!=null)
			for(int i=0;i<advert.getSiteList().size();i++){
				 site_sql="insert into advert_site (AID,SID) values(?,?)";
				 getJdbcTemplate().update(site_sql, advert.getId(),advert.getSiteList().get(i).getId());
			}
		return true;
	}

	@Override
	public boolean updateState(Long id, int state) {

	 if(state==1){
			String sql = "update advert set STATE=" + state + ", createdate=null where ID=" + id;
			getJdbcTemplate().execute(sql);
			return true;
		   }
	   else{
		String sql = "update advert set STATE=" + state + ", createdate='"+DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")+"' where ID=" + id;
		getJdbcTemplate().execute(sql);
		return true;
	   }
	}

	@Override
	public boolean deleteAdvertById(Long id) {

		String sql = "delete from advert_site where AID=" + id;
		getJdbcTemplate().execute(sql);
		sql = "delete from advert where ID=" + id;
		getJdbcTemplate().execute(sql);
		
		return true;
	}

	@Override
	public boolean updateAdvert(Advert advert) {
		
		String sql = "update advert set name=?, type=?, url=?, target_url=?,REMARK=?,state=? where id=?";
		getSimpleJdbcTemplate().update(sql, advert.getName(), advert.getType(), advert.getUrl(), advert.getTarget_url(),advert.getRemark(),advert.getState(),advert.getId());
		sql = "delete from advert_site where AID=" + advert.getId();
		getJdbcTemplate().execute(sql);
	
		
		if (advert.getSiteList()!=null)
			for(SystemSite systemSite: advert.getSiteList()){
				sql = "insert into advert_site (aid, sid) values (" + advert.getId()+ "," + systemSite.getId()+")"; 
				getJdbcTemplate().update(sql);
			}
		return true;
		
	}

	//列表分页查询jdbc
	@Override
	public void getAdvertPageList(PageList pl, Advert advert) {
		List<Object> values = new ArrayList<Object>();  
		List<Advert> list=new ArrayList<>();
		StringBuilder query= new StringBuilder( " select  * FROM advert where 1=1 "); 
		//bannner分类     
		if(advert.getType()!=null &&advert.getType()!=0 ){
			query.append(" AND  type = '"+advert.getType()+"'");  
		}
		if(advert.getName()!=null && !advert.getName().equals("") ){
			query.append(" AND  name like ?");  
			values.add("%"+advert.getName()+"%");
		}
		
		
		//类型 0:未发布  1:已发布
		 if(advert.getState()!=null&&advert.getState()!=0 ){
			 query.append(" AND state='"+advert.getState()+"'");
//			values.add(advert.getState());
		}
		 
		//发布开始时间
		if(advert.getStart_date()!=null && !advert.getStart_date().equals("")){
			query.append(" AND createdate>'"+advert.getStart_date()+"'");
		}
		//发布结束时间
		if(advert.getEnd_date()!=null && !advert.getEnd_date().equals("")){
			query.append(" AND createdate<'"+advert.getEnd_date()+"'");
		}
			query.append("");
		//排序-------->
		if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			query.append(" order by ORDERNUM asc,id desc,createdate ");
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				query.append(" desc");
		}
		
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(query.toString()), values.toArray());
		pl.setFullListSize(fullListSize);
		list = getJdbcTemplate().query(PageUtil.getPageSql(query.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(Advert.class),values.toArray());
		pl.setList(list);
	}

	//查询站点
	@Override
	public List<SystemSite> getSiteListByBannerId(long id) {
		String sql_site = "SELECT t.* FROM system_site t,advert_site s where t.ID=s.SID AND s.AID=" + id;
		List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		return siteList;
	}

	@Override
	public int getAdvertByName(String name) {
		String sql = "select count(*) from advert where NAME = "+"'"+name+"'";
		int count = 1;
		try {
			count = getJdbcTemplate().queryForInt(sql);
		} catch (DataAccessException e) {
		}
		return count;
	}

	@Override
	public List<Advert> getAdvertList(Advert advert) {
		
		List <Object>values = new ArrayList<Object>();
		List<Advert> list=new ArrayList<>();
		
		if(advert.getId() != null){
			StringBuilder sql = new StringBuilder();  //SELECT a.* FROM advert a LEFT JOIN advert_site a_s on a.ID = a_s.AID where
			sql.append( "SELECT a.* FROM advert a LEFT JOIN advert_site a_s on a.ID = a_s.AID where"); 
			sql.append(" a.id = ?");
			values.add(advert.getId());
			
			String sql_sites = "select a_s.SID as ID FROM advert_site a_s where a_s.AID =" + advert.getId();
			List<SystemSite> siteList = getJdbcTemplate().query(sql_sites,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
			
			
			list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Advert.class),values.toArray());
			
			list.get(0).setSiteList(siteList);
		}		
		
		
			if(advert.getId() == null) {
				StringBuilder query= new StringBuilder( "SELECT a.* FROM advert a LEFT JOIN advert_site a_s on a.ID = a_s.AID where  1=1 "); 
				
				
				if(advert.getType()!=null &&advert.getType()!=0 ){
					query.append(" AND  a.type = ?");  
					values.add(advert.getType());
				}
				
			 	if(advert.getName()!=null && !advert.getName().equals("")){
			 		query.append(" AND a.title like ?");
			 		values.add("%"+advert.getName()+"%");
				}
				 if(advert.getState()!=null&&advert.getState()!=0 ){
					 query.append(" AND a.state=?");
					values.add(advert.getState());
				}
				if(advert.getSite_id()!=null && advert.getSite_id() > 0 ){
					query.append(" AND a_s.site_id=?");
					values.add(advert.getSite_id());
				}
				if(advert.getCreatedate()!=null && !advert.getCreatedate().equals("") ){
					query.append(" AND a.deli_date=?"); 
					
					values.add(DateUtil.parse(advert.getCreatedate(), "yyyy-MM-dd"));
				}
				query.append(" order by ordernum asc, id desc") ;
				
				 list =  getJdbcTemplate().query(query.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Advert.class),values.toArray());	
				
				for (Advert a:list) {
					String sql_site = "select t.* from SYSTEM_SITE t, advert_site t2 where t.ID=t2.SID and t2.AID=" + a.getId();
					List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
					
					if(siteList.size() > 0)
						a.setSiteList(siteList);
				}		
		}
		return list;

	}
	//排序操作
	@Override
	public boolean resortOrderNum(String orderstr) {
		
		String item[] = orderstr.split(";");
		
		String SORT_SQL = "update advert set ordernum=? where id=?";
		
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
	public void updateImageById(Long id, String url) {
		if(id != null){
			String sql = "update advert set url=" + url + ",  where ID=" + id;
			getJdbcTemplate().execute(sql);
//			return true;
		   }
	   else{
		String sql = "update advert set url=" + url + ",  where ID=" + id;
		getJdbcTemplate().execute(sql);
//		return true;
	   }
		
	}

	@Override
	public int getAdvertByState(Integer state,Integer type) {
		String sql = "select count(*) from advert where state = "+"'"+state+"'" +"and type="+"'"+type+"'";
		int count = 1;
		try {
			count = getJdbcTemplate().queryForInt(sql);
		} catch (DataAccessException e) {
		}
		return count;
	}


	

}
