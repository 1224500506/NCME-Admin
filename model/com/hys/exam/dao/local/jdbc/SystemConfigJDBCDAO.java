/**
 * SystemConfigJDBCDAO.java
 * com.hys.exam.dao.local.jdbc
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月14日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.SystemConfigDAO;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.model.system.SystemConfigVO;

/**
 * ClassName:SystemConfigJDBCDAO
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月14日	上午11:16:38
 *
 * @see 	 
 *  
 */

public class SystemConfigJDBCDAO  extends BaseDao
implements  SystemConfigDAO{

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.dao.local.SystemConfigDAO#getConfig()
	 */
	@Override
	public SystemConfigVO getConfig() {
		
		String sql = "select *  from system_login_limit";
		List<SystemConfigVO> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemConfigVO.class));
		return list.get(0);
		
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.dao.local.SystemConfigDAO#editConfig(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateConfig(String time, String num,String lockTime) {
		String sql = "update system_login_limit set time = ?,login_num = ?,lock_time = ?";

		
		return getJdbcTemplate().update(sql, Integer.valueOf(time),Integer.valueOf(num),Integer.valueOf(lockTime));
		
	}

}
