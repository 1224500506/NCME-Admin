package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemSiteCourseTypeDAO;
import com.hys.exam.model.SystemSiteCourseType;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 JdbcDao
 * 
 * 说明:
 */
public class SystemSiteCourseTypeJDBCDAO extends AbstractJDBCDAO implements SystemSiteCourseTypeDAO {
	public SystemSiteCourseTypeJDBCDAO() {
		super(SystemSiteCourseType.class, "SYSTEM_SITE_COURSE_TYPE");
	}
	
	//取得所有站点信息
	final static private String getSystemSiteCourseTypeList_SQL = "select t.*,t2.course_type_name,t2.introduction from system_site_course_type t,study_course_type t2 where t.course_type_id=t2.id " ;
	final static private String addSystemSiteCourseType_SQL = " insert into system_site_course_type (site_id, course_type_id) values (:siteId, :courseTypeId) ";
	
	@Override //取得所有站点信息
	public List<SystemSiteCourseType> getListByItem(SystemSiteCourseType item){
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemSiteCourseTypeList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getSiteId() != null && item.getSiteId()>0) {
			sql.append(" and t.site_Id = ? ");
			list.add(item.getSiteId() );
		}
		// 查询结果集
		List<SystemSiteCourseType> resList = getList(sql.toString(), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemSiteCourseType.class));
		return resList;
	}
	
	@Override
	public void querySystemSiteCourseTypeList(Page<SystemSiteCourseType> page, SystemSiteCourseType item) {

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemSiteCourseTypeList_SQL);

		List<Object> list = new ArrayList<Object>();
		
		if (item.getSiteId() != null && item.getSiteId()>0) {
			sql.append(" and t.site_Id = ? ");
			list.add(item.getSiteId() );
		}
		// 查询结果集
		List<SystemSiteCourseType> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemSiteCourseType.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}

	public Integer save(SystemSiteCourseType item) {
		getNamedParameterJdbcTemplate().update(addSystemSiteCourseType_SQL,
				new BeanPropertySqlParameterSource(item));
		return  item.getSiteId().intValue();
	}
	
	public SystemSiteCourseType getItemById(Long id) {
		return this.get(id.intValue());
	}
	

	@Override
	public int delete(long id, String idColName) {
		return super.delete(id, idColName);
	}

	@Override
	public int update(SystemSiteCourseType item) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean deleteBySiteId(Long siteId){
		this.getJdbcTemplate().update("delete system_site_course_type t where t.site_id = ? ",siteId);
		return true;
	}
}