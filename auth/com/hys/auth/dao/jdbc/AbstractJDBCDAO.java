package com.hys.auth.dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.hys.auth.dao.AbstractDAO;
import com.hys.auth.util.PageList;
import com.hys.auth.util.PageUtil;

/**
 * @author chenmk
 * @author zdz
 */
public abstract class AbstractJDBCDAO extends SimpleJdbcDaoSupport implements AbstractDAO {

	protected final Log log = LogFactory.getLog(getClass());
	private SimpleJdbcInsert insert;
	@SuppressWarnings("unchecked")
	private Class clazz;
	private String tableName;

	@SuppressWarnings("unchecked")
	public AbstractJDBCDAO(Class clazz, String tableName) {
		this.clazz = clazz;
		this.tableName = tableName;
	}

	/**
	 * 泛型保存操作, 保存完毕返回新生成的ID
	 * 
	 * @param t
	 * @return long
	 */
	public <T> Integer save(T t) {
		insert = new SimpleJdbcInsert(this.getDataSource()).withTableName(tableName).usingGeneratedKeyColumns("ID");
		Number newKey = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(t));
		return newKey.intValue();
	}

	/**
	 * 泛型通过id获取实体对象,返回实体类
	 * 
	 * @param id
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append(tableName);
		sql.append(" WHERE ID = ?");
		return (T) getSimpleJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(clazz), id);
	}

	/**
	 * 泛型通过id删除相关数据,返回删除的数据条数
	 * 
	 * @param id
	 * @return int
	 */
	public int delete(Integer id) {
		Long selId = Long.valueOf(id);
		
		String delUserDataSql = "DELETE FROM HYS_USER_DATA WHERE USERID = ?";
		getSimpleJdbcTemplate().update(delUserDataSql, new Object[] {selId});
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		sql.append(tableName);
		sql.append(" WHERE ID = ?");
		return getSimpleJdbcTemplate().update(sql.toString(), new Object[] { selId });
		
	}

	/**
	 * 泛型查询所有数据
	 * 
	 * @param <T>
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getListAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append(tableName);
		return (List<T>) getSimpleJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(clazz));
	}

	public int getCount(String sql) {
		String countSql = getCountSql(sql);
		logger.info("计数：" + countSql);
		return getSimpleJdbcTemplate().queryForInt(countSql, new Object[] {});
	}

	private String getCountSql(String sql) {
		StringBuilder countSql = new StringBuilder();
		countSql.append("SELECT ID FROM ").append(tableName);
		return new StringBuilder("select count(ID) as count from (").append((sql == null ? countSql.toString() : sql)).append(") c").toString();
	}

	/**
	 * DISPLAYTAG分页查询
	 * 
	 * @param <T>
	 * @param columns
	 * @param pageList
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getListWithPagination(String[] columns, PageList pageList) {
		int fullListSize = getCount(null);
		pageList.setFullListSize(fullListSize);
		String sql = getPaginationSql(columns, pageList);
		int pageNumber = pageList.getPageNumber();
		int objectsPerPage = pageList.getObjectsPerPage();
		int start = (pageNumber * objectsPerPage) - (objectsPerPage - 1);
		int end = pageNumber * objectsPerPage;
		return (List<T>) getSimpleJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(clazz), new Object[] { end, start });
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListWithPagination(String sql, PageList pageList) {
		int fullListSize = getCount(sql);
		pageList.setFullListSize(fullListSize);
		sql = PageUtil.getPageSql(sql, pageList.getPageNumber() - 1, pageList.getObjectsPerPage());
		logger.info(sql);
		return (List<T>) getSimpleJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(clazz));
	}

	private String getPaginationSql(String[] columns, PageList pageList) {
		String orderItem = pageList.getSortCriterion();
		String dir = pageList.getSortDirection().getName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT").append(" ");
		for (int i = 0; i < columns.length; i++) {
			if (i > 0) {
				sql.append(",");
			}
			sql.append(columns[i]);
		}
		sql.append(" FROM ").append(tableName);
		if (orderItem != null) {
			sql.append(" ORDER BY ").append(orderItem);

			if (dir != null) {
				sql.append(" ").append(dir.equals("ascending") ? "ASC" : "DESC");
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT *");
		sb.append(" FROM (SELECT t.*,ROWNUM as myrow FROM (");
		sb.append(sql);
		sb.append(") t WHERE ROWNUM <= ?");
		sb.append(") WHERE myrow>= ?");
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 动态生成参数list
	 * 
	 * @param <T>
	 * @param sql
	 * @param list
	 * @param rowMapper
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String sql, List<Object> list, ParameterizedRowMapper<T> rowMapper) {

		return (List<T>) getJdbcTemplate().query(sql, list.toArray(), rowMapper);
	}

	protected void deleteList(List<Long> list, String sql) {
		List<Object[]> batch = new ArrayList<Object[]>();

		for (int i = 0; i < list.size(); ++i) {

			Object[] values = new Object[] { list.get(i) };

			batch.add(values);
		}

		getSimpleJdbcTemplate().batchUpdate(sql, batch);
	}
	
	protected Long getSequence() {
		Map<String, Object> resultMap = getSimpleJdbcTemplate().queryForMap("show table status like '"+tableName+"'");
		return Long.valueOf(resultMap.get("Auto_increment").toString());
		//String sql = "SELECT " + tableName + "_SEQ.NEXTVAL+0 FROM DUAL";

		//return getSimpleJdbcTemplate().queryForLong(sql);
	}

}
