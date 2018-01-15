package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.NcmeCourseTypeDAO;
import com.hys.exam.model.NcmeCourseType;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseTypeJDBCDAO extends BaseDao implements NcmeCourseTypeDAO {

	final static private String getNcmeCourseTypeList_SQL = "select * from system_course_type order by course_type";

	@Override
	public List<NcmeCourseType> getNcmeCourseTypeList() {
		return getJdbcTemplate().query(
				getNcmeCourseTypeList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(NcmeCourseType.class));
	}

}
