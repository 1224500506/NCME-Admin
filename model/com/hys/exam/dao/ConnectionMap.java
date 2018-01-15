package com.hys.exam.dao;


import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;
import java.util.Random;


import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.CannotCreateTransactionException;

public class ConnectionMap {
    private static Map<String, Connection> map = new HashMap<String, Connection>();
    private static DataSource dataSource;

    public ConnectionMap() {
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 返回唯一id
     * @param length int
     * @return String
     */
    private static String getUid(int length) {
        Random random = new Random();
        int num = 0;
        while (num > Math.pow(10, length) || num < Math.pow(10, length - 1)) {
            num = Math.abs(random.nextInt());
        }
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date) + num;
    }

    /**
     * 从缓冲池删除一个连接
     * @param key String
     * @throws SQLException
     */
    private static void removeConnection(String key) throws SQLException {
        Connection connection = map.get(key);
        if (connection != null) {
            connection.close();
            map.remove(key);
        }
    }

    /**
     * 获取一个连接
     * @param key String
     * @return Connection
     */
    public static Connection getConnection(String key) throws SQLException {
        Connection connection = map.get(key);
        if (connection == null) {
            throw new SQLException("=====connection is null=====");
        }
        return connection;
    } 

    /**
     * 手动开始一个事务
     * @return String
     * @throws SQLException
     */
    public static String beginTransaction() throws SQLException {
        try {
            String key = getUid(6);
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            new DefaultTransactionStatus(connection, true, true, false, true, null);
            map.put(key, connection);
            return key;
        } catch (Exception e) {
            throw new CannotCreateTransactionException("can't get connection for tx", e);
        }
    }

    /**
     * 手动提交一个事务
     * @param key String
     * @throws SQLException
     */
    public static void commit(String key) throws SQLException {
        Connection connection = map.get(key);
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new TransactionSystemException("commit failed with SQLException", e);
            } finally {
                try {
                    removeConnection(key);
                } catch (SQLException e) {
                    throw new TransactionSystemException("close connection failed with SQLException", e);
                }
            }
        }
    }

    /**
     * 手动回滚一个事务
     * @param key String
     * @throws SQLException
     */
    public static void rollback(String key) throws SQLException {
        Connection connection = map.get(key);
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new UnexpectedRollbackException("rollback failed with SQLException", e);
            } finally {
                try {
                    removeConnection(key);
                } catch (SQLException e) {
                    throw new TransactionSystemException("close connection failed with SQLException", e);
                }
            }
        }
    }

} 
