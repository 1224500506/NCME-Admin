package com.hys.exam.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.hys.exam.common.util.Log;



public abstract class BaseDao {

    private JdbcTemplate jdbcTemplate;
    
    private SimpleJdbcTemplate simpleJdbcTemplate;
    
    private NamedParameterJdbcDaoSupport namedParameterJdbcDaoSupport;

    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
	public NamedParameterJdbcDaoSupport getNamedParameterJdbcDaoSupport() {
		return namedParameterJdbcDaoSupport;
	}

	public void setNamedParameterJdbcDaoSupport(
			NamedParameterJdbcDaoSupport namedParameterJdbcDaoSupport) {
		this.namedParameterJdbcDaoSupport = namedParameterJdbcDaoSupport;
	}
	
	public Long getSequence(String sequence) {
		Map<String, Object> resultMap = jdbcTemplate.queryForMap("show table status like '"+sequence+"'");
		return Long.valueOf(resultMap.get("Auto_increment").toString());
//		return getJdbcTemplate().queryForLong("select " + sequence + ".nextval from dual");
	}

	/**
     * 数组参数查询返回Object，且返回值不能为空
     * @param sql String
     * @param params Object[]
     * @param mapper ParameterizedRowMapper
     * @return Object
     */
    public <T> Object queryForObject(String sql, Object params[], ParameterizedRowMapper<T> mapper) {
//        Log.info(sql);
//        this.logParams(params);
        if (params == null || params.length == 0) {
            return jdbcTemplate.queryForObject(sql, mapper);
        } else {
            return jdbcTemplate.queryForObject(sql, params, mapper);
        }
    }

    /**
     * list参数查询返回Object，且Object不能为空
     * @param sql String
     * @param params List
     * @param mapper ParameterizedRowMapper
     * @return Object
     */
    public <T> Object queryForObject(String sql, List params, ParameterizedRowMapper<T> mapper) {
        if (params == null || params.size() == 0) {
            return this.queryForObject(sql, mapper);
        } else {
            return this.queryForObject(sql, params.toArray(), mapper);
        }
    }

    /**
     * 无参数查询返回Object，且返回值不能为空
     * @param sql String
     * @param mapper ParameterizedRowMapper
     * @return Object
     */
    public <T> Object queryForObject(String sql, ParameterizedRowMapper<T> mapper) {
        Object[] params = null;
        return this.queryForObject(sql, params, mapper);
    }

    /**
     * 数组参数查询返回list
     * @param sql String
     * @param params Object[]
     * @param mapper ParameterizedRowMapper
     * @return List
     */
    public <T> List<T> queryForList(String sql, Object[] params, ParameterizedRowMapper<T> mapper) {
//        Log.info(sql);
//        this.logParams(params);
        if (params == null || params.length == 0) {
            return jdbcTemplate.query(sql, mapper);
        } else {
            return jdbcTemplate.query(sql, params, mapper);
        }
    }

    /**
     * list参数查询返回list
     * @param sql String
     * @param params List
     * @param mapper ParameterizedRowMapper
     * @return List
     */
    public <T> List<T> queryForList(String sql, List params, ParameterizedRowMapper<T> mapper) {
        if (params == null || params.size() == 0) {
            return this.queryForList(sql, mapper);
        } else {
            return this.queryForList(sql, params.toArray(), mapper);
        }
    }

    /**
     * 返回list分页查询
     * @param sql String 原生sql
     * @param params List 参数list 执行后自动添加 页数和页长度 两个参数
     * @param pageIndex int 页数
     * @param pageSize int 页长度
     * @param mapper ParameterizedRowMapper
     * @return List
     */
    public <T> List<T> queryForListPage(String sql, List params, int pageIndex, int pageSize, ParameterizedRowMapper<T> mapper) {
        sql = "select *  from (select ROW_.*, rownum ROWNUM_ from (" + sql + ") ROW_) where ROWNUM_ > ? and ROWNUM_ <= ?";
        params.add((pageIndex - 1) * pageSize);
        params.add(pageIndex * pageSize);
        return this.queryForList(sql, params, mapper);
    }

    /**
     * 无参数查询返回list
     * @param sql String
     * @param mapper ParameterizedRowMapper
     * @return List
     */
    public <T> List<T> queryForList(String sql, ParameterizedRowMapper<T> mapper) {
        Object[] params = null;
        return this.queryForList(sql, params, mapper);
    }

    /**
     * 数组参数查询返回int
     * @param sql String
     * @param params Object[]
     * @return int
     */
    public int queryForInt(String sql, Object[] params) {
        ParameterizedRowMapper<Integer> mapper = new ParameterizedRowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        };
        List<Integer> list;
        if (params == null || params.length == 0) {
            list = this.queryForList(sql, mapper);
        } else {
            list = this.queryForList(sql, params, mapper);
        }
        if (list != null && list.size() > 0 && list.get(0) != null) {
            return Integer.parseInt(list.get(0).toString());
        } else {
            return 0;
        }
    }

    /**
     * list参数查询返回int
     * @param sql String
     * @param params List
     * @return int
     */
    public int queryForInt(String sql, List params) {
        if (params == null && params.size() == 0) {
            return this.queryForInt(sql);
        } else {
            return this.queryForInt(sql, params.toArray());
        }
    }

    /**
     * 无参数查询返回int
     * @param sql String
     * @return int
     */
    public int queryForInt(String sql) {
        Object[] params = null;
        return this.queryForInt(sql, params);
    }

    /**
     * 取得序列currval
     * @param seqName String
     * @return int
     */
    public int getSeqCurrval(String seqName) {
        String sql = "select " + seqName + ".currval as NUM from dual";
        return this.queryForInt(sql);
    }

    /**
     * 取得序列nextval
     * @param seqName String
     * @return int
     */
    public int getSeqNextval(String seqName) {
        String sql = "select " + seqName + ".nextval as NUM from dual";
        return this.queryForInt(sql);
    }

    /**
     * 数组参数查询返回Object，且返回值不能为空
     * @param sql String
     * @param params Object[]
     * @param mapper ParameterizedRowMapper
     * @return Object
     */
    public <T> Object queryForObject(String key, String sql, Object params[], ParameterizedRowMapper<T> mapper) throws SQLException {
        Object obj = null;
        PreparedStatement ps = this.preparedStatement(key, sql, params);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            obj = mapper.mapRow(rs, rs.getRow());
        }
        rs.close();
        ps.close();
        return obj == null ? null : (T) obj;
    }

    /**
     * list参数查询返回Object，且Object不能为空
     * @param sql String
     * @param params List
     * @param mapper ParameterizedRowMapper
     * @return Object
     */
    public <T> Object queryForObject(String key, String sql, List params, ParameterizedRowMapper<T> mapper) throws SQLException {
        if (params == null || params.size() == 0) {
            return this.queryForObject(key, sql, mapper);
        } else {
            return this.queryForObject(key, sql, params.toArray(), mapper);
        }
    }

    /**
     * 无参数查询返回Object，且返回值不能为空
     * @param sql String
     * @param mapper ParameterizedRowMapper
     * @return Object
     */
    public <T> Object queryForObject(String key, String sql, ParameterizedRowMapper<T> mapper) throws SQLException {
        Object[] params = null;
        return this.queryForObject(key, sql, params, mapper);
    }

    /**
     *
     * @param key String
     * @param sql String
     * @param params Object[]
     * @param mapper ParameterizedRowMapper
     * @return List
     * @throws SQLException
     */
    public <T> List<T> queryForList(String key, String sql, Object[] params, ParameterizedRowMapper<T> mapper) throws SQLException {
        List<T> list = new ArrayList<T>();
        PreparedStatement ps = this.preparedStatement(key, sql, params);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapper.mapRow(rs, rs.getRow()));
        }
        rs.close();
        ps.close();
        return list;
    }

    /**
     *
     * @param key String
     * @param sql String
     * @param params List
     * @param mapper ParameterizedRowMapper
     * @return List
     * @throws SQLException
     */
    public <T> List<T> queryForList(String key, String sql, List params, ParameterizedRowMapper<T> mapper) throws SQLException {
        if (params == null || params.size() == 0) {
            return this.queryForList(key, sql, mapper);
        } else {
            return this.queryForList(key, sql, params.toArray(), mapper);
        }
    }

    /**
     *
     * @param key String
     * @param sql String
     * @param params List
     * @param pageIndex int
     * @param pageSize int
     * @param mapper ParameterizedRowMapper
     * @return List
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> queryForListPage(String key, String sql, List params, int pageIndex, int pageSize, ParameterizedRowMapper<T> mapper) throws
            SQLException {
        sql = "select *  from (select ROW_.*, rownum ROWNUM_ from (" + sql + ") ROW_) where ROWNUM_ > ? and ROWNUM_ <= ?";
        params.add((pageIndex - 1) * pageSize);
        params.add(pageIndex * pageSize);
        return this.queryForList(key, sql, params, mapper);
    }

    /**
     *
     * @param key String
     * @param sql String
     * @param mapper ParameterizedRowMapper
     * @return List
     * @throws SQLException
     */
    public <T> List<T> queryForList(String key, String sql, ParameterizedRowMapper<T> mapper) throws SQLException {
        Object[] params = null;
        return this.queryForList(key, sql, params, mapper);
    }

    /**
     * 数组参数查询返回int
     * @param sql String
     * @param params Object[]
     * @return int
     */
    public int queryForInt(String key, String sql, Object[] params) throws SQLException {
        ParameterizedRowMapper<Integer> mapper = new ParameterizedRowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        };
        List<Integer> list;
        if (params == null || params.length == 0) {
            list = this.queryForList(key, sql, mapper);
        } else {
            list = this.queryForList(key, sql, params, mapper);
        }
        if (list != null && list.size() > 0 && list.get(0) != null) {
            return Integer.parseInt(list.get(0).toString());
        } else {
            return 0;
        }
    }

    /**
     * list参数查询返回int
     * @param sql String
     * @param params List
     * @return int
     */
    @SuppressWarnings("unchecked")
    public int queryForInt(String key, String sql, List params) throws SQLException {
        if (params == null && params.size() == 0) {
            return this.queryForInt(key, sql);
        } else {
            return this.queryForInt(key, sql, params.toArray());
        }
    }

    /**
     * 无参数查询返回int
     * @param sql String
     * @return int
     */
    public int queryForInt(String key, String sql) throws SQLException {
        Object[] params = null;
        return this.queryForInt(key, sql, params);
    }

    /**
     * 取得序列currval
     * @param seqName String
     * @return int
     */
    public int getSeqCurrval(String key, String seqName) throws SQLException {
        String sql = "select " + seqName + ".currval as NUM from dual";
        return this.queryForInt(key, sql);
    }

    /**
     * 取得序列nextval
     * @param seqName String
     * @return int
     */
    public int getSeqNextval(String key, String seqName) throws SQLException {
        String sql = "select " + seqName + ".nextval as NUM from dual";
        return this.queryForInt(key, sql);
    }

    /**
     * 数组参数更新操作
     * @param sql String
     * @param params Object[]
     * @return int
     */
    public int update(String sql, Object params[]) {
//        Log.info(sql);
//        this.logParams(params);
        if (params == null || params.length == 0) {
            return jdbcTemplate.update(sql, params);
        } else {
            return jdbcTemplate.update(sql);
        }
    }

    /**
     * list参数更新操作
     * @param sql String
     * @param params List
     * @return int
     */
    @SuppressWarnings("unchecked")
    public int update(String sql, List params) {
        if (params == null || params.size() == 0) {
            return this.update(sql);
        } else {
            return this.update(sql, params.toArray());
        }
    }

    /**
     * 无参数更新操作
     * @param sql String
     * @return int
     */
    public int update(String sql) {
        Object[] params = null;
        return this.update(sql, params);
    }

    /**
     *
     * @param connection Connection
     * @param sql String
     * @param params Object[]
     * @return int
     * @throws SQLException
     */
    public int update(String key, String sql, Object[] params) throws SQLException {
        int num = 0;
        PreparedStatement ps = this.preparedStatement(key, sql, params);
        num = ps.executeUpdate();
        ps.close();
        return num;
    }
    
    /**
     *
     * @param key String
     * @param sql String
     * @param params List
     * @return int
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public int update(String key, String sql, List params) throws SQLException {
        if (params == null || params.size() == 0) {
            return this.update(key, sql);
        } else {
            return this.update(key, sql, params.toArray());
        }
    }

    /**
     *
     * @param key String
     * @param sql String
     * @return int
     * @throws SQLException
     */
    public int update(String key, String sql) throws SQLException {
        Object[] params = null;
        return this.update(key, sql, params);
    }

    /**
     * 调用存储过程
     * @param sql String
     * @param inParams Object[]
     * @param inTypes int[]
     * @param outParams Object[]
     * @param outTypes int[]
     * @return Object[]
     */
    @SuppressWarnings("unchecked")
    public Object[] callableStatement(final String sql, final Object[] inParams, final int[] inTypes, final Object[] outParams, final int[] outTypes) {
//        Log.info(sql);
        CallableStatementCallback cb = new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                Object[] inParam = inParams;
                int inLen = inParam.length;
                int outLen = outParams.length;
                int i;
                logParams(inParams);
                for (i = 0; i < inLen; i++) {
                    cs.setObject(i + 1, inParam[i], inTypes[i]);
                }
                for (i = inLen; i < inLen + outLen; i++) {
                    cs.registerOutParameter(i + 1, outTypes[i - inLen]);
                }
                cs.execute();
                for (i = 0; i < outLen; i++) {
                    outParams[i] = cs.getObject(inLen + i + 1);
                }
                return outParams;
            }
        };
        Object obj = jdbcTemplate.execute(sql, cb);
        return (Object[]) obj;
    }

    /**
     * 反射相应的method
     * @param clazz Class
     * @param methodName String
     * @return ParameterizedRowMapper
     */
    @SuppressWarnings("unchecked")
    public <T> ParameterizedRowMapper<T> getMapper(final Class clazz, final String methodName) {
        ParameterizedRowMapper<T> mapper = new ParameterizedRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) {
                Object obj = null;
                try {
                    obj = invoke(clazz, methodName, rs);
                } catch (Exception ex) {
                    Log.error(ex.toString());
                    ex.printStackTrace();
                } finally {
                    return obj;
                }
            }
        };
        return mapper;
    }

    /**
     * 调用相应的方法
     * @param clazz Class
     * @param methodName String
     * @param rs ResultSet
     * @return Object
     */
    @SuppressWarnings("unchecked")
	private Object invoke(Class clazz, String methodName, ResultSet rs) {
        Object obj = null;
        try {
            Method method = clazz.getMethod(methodName, ResultSet.class);
            obj = method.invoke(clazz.newInstance(), rs);
        } catch (SecurityException ex) {
//            Log.error(ex.toString());
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
//            Log.error(ex.toString());
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
//            Log.error(ex.toString());
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
//            Log.error(ex.toString());
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
//            Log.error(ex.toString());
            ex.printStackTrace();
        } catch (Exception ex) {
//            Log.error(ex.toString());
            ex.printStackTrace();
        } finally {
            return obj;
        }
    }

    /**
     * 记录sql参数
     * @param params Object[]
     */
    private void logParams(Object[] params) {
        if (params != null) {
            for (Object obj : params) {
//                Log.info(obj);
            }
        }
    }
    
    private PreparedStatement preparedStatement(String key, String sql, Object[] params) throws SQLException {
        Log.info("key is " + key + ">>>>>>>>>>" + sql);
//        this.logParams(params);
        Connection con = ConnectionMap.getConnection(key);
        PreparedStatement ps = con.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                Object param = params[i];
                if (param == null) {
                    ps.setObject(i + 1, null, Types.NULL);
                } else if (param instanceof String) {
                    ps.setObject(i + 1, param, Types.VARCHAR);
                } else if (param instanceof Integer) {
                    ps.setObject(i + 1, param, Types.INTEGER);
                } else if (param instanceof Double) {
                    ps.setObject(i + 1, param, Types.DOUBLE);
                } else if (param instanceof Float) {
                    ps.setObject(i + 1, param, Types.FLOAT);
                } else if (param instanceof Date) {
                    ps.setObject(i + 1, new java.sql.Date(((java.util.Date) param).getTime()), Types.DATE);
                } else {
                    ps.setObject(i + 1, param);
                }
            }
        }
        return ps;
    } 
    
    //*******************************************************************************************************************
 
    /**
     * 批理更新
     * @param key
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int[] batchUpdate(String key,String sql,List<Object[]> params) throws SQLException {
//        Log.info(sql);
        int num[] = null;
        Connection connection = ConnectionMap.getConnection(key);
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
            	for(int i=0;i<params.size();i++){
            		for(int j=0;j<params.get(i).length;j++){
                      Object param = params.get(i)[j];
                      preparedStatement.setObject(j + 1, param);
            		}
            		preparedStatement.addBatch();
            	}
            }
            num = preparedStatement.executeBatch();
            preparedStatement.close();
        }
        return num;
    }    
    
	/**
	 * 泛型通过删除相关数据,返回删除的数据条数
	 * 
	 * @param id
	 * @return int
	 */
	protected int delete(String sql, Object... params)
			throws DataAccessException {
		return this.simpleJdbcTemplate.update(sql, params);
	}

	/**
	 * 泛型批量更新，通过List<Object[]>这种形式传递参数
	 * 
	 * @param ids
	 * @return
	 */
	protected int[] updateBatch(String sql, List<Object[]> params) {
		List<Object[]> batch = new ArrayList<Object[]>();
		int size = params.size();
		for (int i = 0; i < size; i++) {
			batch.add(params.get(i));
		}
		return simpleJdbcTemplate.batchUpdate(sql, batch);
	}

	protected Long getNextId(String seqName) {
		Map<String, Object> resultMap = jdbcTemplate.queryForMap("show table status like '"+seqName+"'");
		return Long.valueOf(resultMap.get("Auto_increment").toString());
/*		StringBuilder sql = new StringBuilder();
		sql.append("SELECT " + seqName + "_SEQ.NEXTVAL FROM DUAL");
		return jdbcTemplate.queryForLong(sql.toString());
*/	}

	protected <T> List<T> getList(String sql, List<Object> list,
			ParameterizedRowMapper<T> rowMapper) {
		return (List<T>) jdbcTemplate
				.query(sql, list.toArray(), rowMapper);
	}
	
	/**
	 * 
	 * Description: batchUpdate 操作List
	 * @param sql 添加的insert语句，参数用(:id,:name)形式
	 * @param list 存放对象的List 用于以SqlParameterSource进行转换
	 * @return: int[]
	 */
	protected int[] saveList(String sql,List list){
		SqlParameterSource[] batchArgs = new SqlParameterSource[list.size()];
		for (int i = 0; i < list.size() ; i++) {
			batchArgs[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		return simpleJdbcTemplate.batchUpdate(sql, batchArgs);
	} 
	
    /**
     * 数组参数查询返回Long
     * @param sql String
     * @param params Object[]
     * @return Long
     */
    public Long queryForLong(String key, String sql, Object[] params) throws SQLException {
        ParameterizedRowMapper<Long> mapper = new ParameterizedRowMapper<Long>() {
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        };
        List<Long> list;
        if (params == null || params.length == 0) {
            list = this.queryForList(key, sql, mapper);
        } else {
            list = this.queryForList(key, sql, params, mapper);
        }
        if (list != null && list.size() > 0 && list.get(0) != null) {
            return Long.parseLong(list.get(0).toString());
        } else {
            return null;
        }
    }
    
    /**
     * 取得序列getSeqNextvalForLong
     * @param seqName String
     * @return Long
     */
    public Long getSeqNextvalForLong(String key, String seqName) throws SQLException {
		Map<String, Object> resultMap = jdbcTemplate.queryForMap("show table status like '"+seqName+"'");
		return Long.valueOf(resultMap.get("Auto_increment").toString());
//        String sql = "select " + seqName + "_SEQ.NEXTVAL FROM DUAL";
//        return this.queryForLong(key, sql);
    }
    
    /**
     * 无参数查询返回Long
     * @param sql String
     * @return Long
     */
    public Long queryForLong(String key, String sql) throws SQLException {
        Object[] params = null;
        return this.queryForLong(key, sql, params);
    }
    
    
    /**
     * 数组参数查询返回String
     * @param sql String
     * @param params Object[]
     * @return String
     */
    public String queryForString(String key, String sql, Object[] params) throws SQLException {
        ParameterizedRowMapper<String> mapper = new ParameterizedRowMapper<String>() {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        };
        List<String> list;
        if (params == null || params.length == 0) {
            list = this.queryForList(key, sql, mapper);
        } else {
            list = this.queryForList(key, sql, params, mapper);
        }
        if (list != null && list.size() > 0 && list.get(0) != null) {
            return list.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * 无参数查询返回String
     * @param sql String
     * @return String
     */
    public String queryForString(String key, String sql) throws SQLException {
        Object[] params = null;
        return this.queryForString(key, sql, params);
    }
    


}
