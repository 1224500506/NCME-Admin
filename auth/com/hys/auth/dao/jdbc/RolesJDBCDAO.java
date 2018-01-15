package com.hys.auth.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.hys.auth.constants.Constants;
import com.hys.auth.dao.RolesDAO;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.model.HysDataUpdate;
import com.hys.auth.model.HysRoles;

public class RolesJDBCDAO extends AbstractJDBCDAO implements RolesDAO {

	public RolesJDBCDAO() {
		super(HysRoles.class, Constants.TABLE_ROLES);
	}
	
	public int delete(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE HYS_ROLES SET ");
		sql.append("STATUS = abs(status-1)");
		sql.append("WHERE ID = ?");
		return getSimpleJdbcTemplate().update(sql.toString(), id);
	}
	public boolean deleteRow(Integer id) {
		String SQL_DEL = "delete from HYS_ROLES where ID = ?";
		getJdbcTemplate().update(SQL_DEL,id);
		return true;
	}
	//更新角色
	public int update(HysRoles role) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE HYS_ROLES SET NAME = :name,NAME_DESC = :nameDesc WHERE ID = :id");
		return getSimpleJdbcTemplate().update(sql.toString(), new BeanPropertySqlParameterSource(role));
	}

	public List<HysRoles> getRoleByResourceId(Integer resourceId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from HYS_ROLES t join role_resource rr on t.id = rr.role_id left join HYS_RESOURCES rs on rr.resource_id = rs.id where rs.id=?");
		return getSimpleJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(HysRoles.class), new Object[] { resourceId });
	}

	@Override
	public Long save(HysRoles roles) {
		roles.setId(getSequence());
		roles.setStatus(1);
		SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource())
				.withTableName(Constants.TABLE_ROLES);
		insert.execute(new BeanPropertySqlParameterSource(roles));
		return roles.getId();
	}
	
	//取得角色数据
	public List<HysDataDetail> getRolesDataList() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append("HYS_DATA_ROLES order by id");
		return getSimpleJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(HysDataDetail.class));
	}
	
	//添加角色数据
	@Override
	public Long saveData(HysDataRoles dataRoles) {
		
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(0, dataRoles.getPropAuth());
		data.add(1, dataRoles.getQuestAuth());
		data.add(2, dataRoles.getMatiAuth());
		data.add(3, dataRoles.getExpertAuth());
		data.add(4, dataRoles.getBingliAuth());
		data.add(5, dataRoles.getSystemAuth());
		
		for (int i = 0; i < 6; i ++) {
			Long id = getSequence();

			HysDataDetail role = new HysDataDetail();
			role.setId(id);
			role.setRoleId(dataRoles.getRoleId());
			role.setManageKind(i + 1);
			role.setControlAuth(data.get(i).get(0));
			role.setStatisticsAuth(data.get(i).get(1));
			role.setVariety(data.get(i).get(2));

			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource())
					.withTableName(Constants.TABLE_ROLES_DATA);
			insert.execute(new BeanPropertySqlParameterSource(role));
		}
		return dataRoles.getId();
	}
	
	//更新角色数据
	@Override
	public Long updateData(HysDataRoles dataRoles) {
		
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(0, dataRoles.getPropAuth());
		data.add(1, dataRoles.getQuestAuth());
		data.add(2, dataRoles.getMatiAuth());
		data.add(3, dataRoles.getExpertAuth());
		data.add(4, dataRoles.getBingliAuth());
		data.add(5, dataRoles.getSystemAuth());
		
		for (int i = 0; i < 6; i ++) {
			Long id = getSequence();

			HysDataDetail role = new HysDataDetail();
			HysDataUpdate update = new HysDataUpdate();
			
			update.setRoleId(dataRoles.getRoleId());
			update.setManageKind(i + 1);
			update.setControlAuth(data.get(i).get(0));
			update.setStatisticsAuth(data.get(i).get(1));
			update.setVariety(data.get(i).get(2));
			
			List values = new ArrayList();

			StringBuilder sql = new StringBuilder();

			sql.append("update HYS_DATA_ROLES SET CONTROL_AUTH = ?, STATISTICS_AUTH = ?, VARIETY = ? WHERE ROLEID = ? AND MANAGE_KIND = ?");
			values.add(update.getControlAuth());
			values.add(update.getStatisticsAuth());
			values.add(update.getVariety());
			values.add(update.getRoleId());
			values.add(update.getManageKind());
			getSimpleJdbcTemplate().update(sql.toString(), values.toArray());
		}

		return 1L;
	}

}
