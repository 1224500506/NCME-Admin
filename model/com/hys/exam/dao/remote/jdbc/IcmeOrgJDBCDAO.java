/**
 *
 * @author chenlaibin
 * @version 1.0 2014-9-19
 */

package com.hys.exam.dao.remote.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.local.jdbc.AbstractJDBCDAO;
import com.hys.exam.dao.remote.IcmeOrgDAO;
import com.hys.exam.model.IcmeOrg;

public class IcmeOrgJDBCDAO extends AbstractJDBCDAO implements IcmeOrgDAO {

	@Override
	public List<IcmeOrg> getIcmeGovOrgList() {
		String sql = " select * from  icme_org t where t.layer = 2 and t.post_id is not null ";
		return getList(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(IcmeOrg.class));
	}

}
