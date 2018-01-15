/**
 *
 * <p>系统bug日志</p>
 * @author chenlaibin
 * @version 1.0 2014-4-1
 */

package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.local.SystemBugLogDAO;
import com.hys.exam.model.system.SystemBugLog;
import com.hys.exam.utils.PageUtil;

public class SystemBugLogJDBCDAO extends AbstractJDBCDAO implements SystemBugLogDAO {

	@Override
	public void getSystemBugLogList(Page<SystemBugLog> page, SystemBugLog log) {
		StringBuffer sql = new StringBuffer("select * from system_bug_log t where 1=1 ");
		List<Object> obj = new ArrayList<Object>();
		if(null != log){
			if(StringUtils.isNotBlank(log.getTitle())){
				sql.append(" and t.title like ?");
				obj.add("%" + log.getTitle().trim() + "%");
			}
			if(StringUtils.isNotBlank(log.getContent())){
				sql.append(" and t.content like ?");
				obj.add("%" + log.getContent().trim() + "%");
			}
			if(log.getType() !=null && log.getType()>0){
				sql.append(" and t.type = ?");
				obj.add(log.getType());
			}
		}
		
		sql.append(" and t.status= 1");
		sql.append(" order by t.id desc ");
		List<SystemBugLog> list = this.getList(PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),  obj,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemBugLog.class));
		
		Integer totalCount = getCount(sql.toString(), obj.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}

	@Override
	public SystemBugLog getSystemBugLogById(Long id) {
		String sql = "select t.* from system_bug_log t where t.id = ?";
		List<SystemBugLog> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemBugLog.class), id);
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveSystemBugLog(SystemBugLog log) {
		int row = 0;
		if(null != log.getId() && log.getId()>0){
			String sql = "update system_bug_log t set title = :title, content = :content, reply =:reply, type = :type, " +
					" bug_level = :bugLevel, bug_status = :bugStatus, status = :status, finisher = :finisher, update_date = :updateDate";
			if(log.getFilePath()!=null)
				sql+=", file_path = :filePath";
			if(log.getFilePathTwo()!=null)
				sql+=", file_path_two = :filePathTwo";
			if(log.getFilePathThree()!=null)
				sql+=", file_path_three = :filePathThree";
			sql+=" where id = :id";
			row = this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(log));
		}else{
			log.setId(this.getSequence("system_bug_log_seq"));
			System.out.println(log.getType());
			
			String sql = "insert into system_bug_log(id, title, content, bug_level, reply, bug_status, status, creator, create_date, type, file_path, file_path_two, file_path_three)" +
					" values (:id, :title, :content, :bugLevel, :reply, :bugStatus, :status, :creator, :createDate, :type, :filePath, :filePathTwo, :filePathThree )";
			row = this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(log));
		}
		return row;
	}

	@Override
	public int deleteSystemBugLog(Long id) {
		String sql = "update system_bug_log set status = -1 where id = ?";
		return this.getJdbcTemplate().update(sql, id);
	}

}


