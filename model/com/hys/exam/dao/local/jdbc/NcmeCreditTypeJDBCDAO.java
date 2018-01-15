package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.NcmeCreditTypeDAO;
import com.hys.exam.model.NcmeCreditType;

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
public class NcmeCreditTypeJDBCDAO extends BaseDao implements NcmeCreditTypeDAO {

	final static private String getNcmeCreditTypeList_SQL = "select * from SYSTEM_Credit_Type order by weight";

	@Override
	public List<NcmeCreditType> getNcmeCreditTypeList() {
		return getJdbcTemplate().query(
				getNcmeCreditTypeList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(NcmeCreditType.class));
	}
}
