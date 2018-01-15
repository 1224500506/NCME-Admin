package com.hys.exam.dao.local.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.hys.auth.util.PageList;
import com.hys.auth.util.PageUtil;
import com.hys.exam.common.util.StringPool;
import com.hys.exam.dao.AbstractDAO;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2011-1-5
 * 
 * 描述：
 * 
 * 说明:
 */
public abstract class AbstractJDBCDAO extends NamedParameterJdbcDaoSupport implements AbstractDAO{

	public final static Logger log = Logger.getLogger(AbstractJDBCDAO.class);
	
	// 此方法将clob转为String
	public String clobToString(Clob clob) throws SQLException, IOException {
		String reString = "";
		if (clob == null || clob.getCharacterStream() == null)
			return "";
		Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		return reString;
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
	protected <T> List<T> getList(String sql, List<Object> list,
			ParameterizedRowMapper<T> rowMapper) {

		return (List<T>) getJdbcTemplate()
				.query(sql, list.toArray(), rowMapper);
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
	protected <T> List<T> getList(String sql , ParameterizedRowMapper<T> rowMapper, Object... args) {

		return (List<T>) getJdbcTemplate().query(sql,  rowMapper,args);
	}

	protected void deleteList(List<Long> list, String sql) {
		SqlParameterSource[] batchArgs = new SqlParameterSource[list.size()];
		for (int i = 0; i < list.size(); i++) {
			batchArgs[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		getNamedParameterJdbcTemplate().batchUpdate(sql, batchArgs);
	}

	/**
	 * Description: batchUpdate 操作List
	 * @param sql 添加的insert语句，参数用(:id,:name)形式
	 * @param list 存放对象的List 用于以SqlParameterSource进行转换
	 * @return: int[]
	 */
	protected <T> int[] saveList(String sql, List<T> list) {
		SqlParameterSource[] batchArgs = new SqlParameterSource[list.size()];
		for (int i = 0; i < list.size(); i++) {
			batchArgs[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		return getNamedParameterJdbcTemplate().batchUpdate(sql, batchArgs);
	}

	public Long getSequence(String sequence) {
		Map<String, Object> resultMap = getJdbcTemplate().queryForMap("show table status like ?", sequence);
		return Long.valueOf(resultMap.get("Auto_increment").toString());

//		return getJdbcTemplate().queryForLong(
//				"select " + sequence + ".nextval from dual");

	}

	/**
	 * 查询总数
	 * 
	 * @param sql
	 * @return
	 */
	public int getCount(final String sql) {
		return getJdbcTemplate().queryForInt(getCountSql(sql), new Object[] {});
	}

	public int getCount(final String sql, Object... params) {
		return getJdbcTemplate().queryForInt(getCountSql(sql), params);
	}

	/**
	 * 生成总数SQL
	 * 
	 * @param sql
	 * @return
	 */
	public String getCountSql(final String sql) {
		return new StringBuilder("select count(1) as count from (").append(sql)
				.append(") c").toString();
	}

	public Long generateId(String idGenerator) {
		String sql = "select " + idGenerator + " from dual";
		return this.getJdbcTemplate().queryForLong(sql);
	}
	
	/**
	 * 
	 * Description: 执行自定义SQL
	 * @param sql
	 * @param Object...
	 * @return
	 */
	public int updateNativeSqlStr(String sql,Object...  args) {
		return this.getJdbcTemplate().update(sql, args);
	}

	abstract class SqlBuilder {

		protected String tableName;

		protected String idColumnName;

		protected String idAttrName;

		protected List<String> columnFields = new ArrayList<String>();

		protected List<String> attrFields = new ArrayList<String>();

		public void addUpdateColumn(String columnName, String attrName) {
			this.columnFields.add(columnName);
			this.attrFields.add(attrName);
		}

		public void execute(Object entity) {
			if (!columnFields.isEmpty() && !attrFields.isEmpty())
				AbstractJDBCDAO.this.getNamedParameterJdbcTemplate().update(
						toSQL(), new BeanPropertySqlParameterSource(entity));
			;
		}

		public abstract String toSQL();

	}

	class UpdateBuilder extends SqlBuilder {

		/**
		 * 
		 * @param tableName
		 * @param idColumnName
		 * @param idAttrName
		 */
		public UpdateBuilder(String tableName, String idColumnName,
				String idAttrName) {
			this.tableName = tableName;
			this.idColumnName = idColumnName;
			this.idAttrName = idAttrName;
		}

		public String toSQL() {
			StringBuilder sb = new StringBuilder("UPDATE ");
			sb.append(this.tableName);
			sb.append(" SET ");

			// SET

			for (int i = 0; i < columnFields.size(); i++) {
				String columnName = columnFields.get(i);
				String attrName = attrFields.get(i);
				sb.append(columnName).append("=:").append(attrName);
				if (i != columnFields.size() - 1)
					sb.append(StringPool.COMMA);
			}

			// WHERE
			sb.append(" WHERE ").append(this.idColumnName).append("=:").append(
					this.idAttrName);

			return sb.toString();
		}
	}

	class InsertBuilder extends SqlBuilder {

		public InsertBuilder(String tableName, String idColumnName,
				String idAttrName) {
			this.tableName = tableName;
			this.idColumnName = idColumnName;
			this.idAttrName = idAttrName;
		}

		@Override
		public String toSQL() {
			StringBuilder sb = new StringBuilder("INSERT INTO ");
			sb.append(this.tableName).append("(").append(this.idColumnName);

			for (int i = 0; i < columnFields.size(); i++) {
				String columnName = columnFields.get(i);
				sb.append(StringPool.COMMA).append(columnName);
			}
			sb.append(") VALUES(").append(":").append(this.idAttrName);

			// SET

			for (int i = 0; i < columnFields.size(); i++) {
				String attrName = attrFields.get(i);
				sb.append(StringPool.COMMA).append(":").append(attrName);
			}

			// END 
			sb.append(")");

			return sb.toString();
		}

	}
	
	
	private SimpleJdbcInsert insert;
	@SuppressWarnings("unchecked")
	private Class clazz;
	private String tableName;

	@SuppressWarnings("unchecked")
	public AbstractJDBCDAO(Class clazz, String tableName) {
		this.clazz = clazz;
		this.tableName = tableName;
	}
	public AbstractJDBCDAO(){}

	/**
	 * 泛型保存操作, 保存完毕返回新生成的ID
	 * 
	 * @param t
	 * @return long
	 
	public <T> Integer save(T t) {
		insert = new SimpleJdbcInsert(this.getDataSource()).withTableName(tableName).usingGeneratedKeyColumns("ID");
		Number newKey = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(t));
		return newKey.intValue();
	}*/

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
		return (T) this.getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(clazz), id);
	}

	/**
	 * 泛型通过id删除相关数据,返回删除的数据条数
	 * 
	 * @param id,idColName
	 * @return int
	 */
	public int delete(Long id,String idColName ) {
		String sql = "update "+ "SYSTEM_USER" + " set status=-1 WHERE "+idColName+" = ? ";
		return this.getJdbcTemplate().update(sql , new Object[] { id });
	}
	/**
	 * 泛型通过ids批量删除相关数据,返回删除的数据条数
	 * 
	 * @param id,idColName
	 * @return int
	 */
	public int deleteList(long[] ids,String idColName ) {
		for (long id : ids){
			if (id>0){
				this.delete(id, idColName);
			}
		}
		
		return 1;
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
		sql.append(" WHERE STATUS = 1 ");
		return (List<T>) this.getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(clazz));
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
		return (List<T>) this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(clazz), new Object[] { end, start });
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListWithPagination(String sql, PageList pageList) {
		int fullListSize = getCount(sql);
		pageList.setFullListSize(fullListSize);
		sql = PageUtil.getPageSql(sql, pageList.getPageNumber() - 1, pageList.getObjectsPerPage());
		logger.info(sql);
		return (List<T>) this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(clazz));
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

}
