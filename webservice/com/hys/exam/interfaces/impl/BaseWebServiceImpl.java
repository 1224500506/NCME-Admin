package com.hys.exam.interfaces.impl;

import com.hys.exam.dao.ConnectionMap;
import com.hys.exam.interfaces.BaseWebService;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-21
 * 
 * 描述：
 * 
 * 说明:
 */
public class BaseWebServiceImpl implements BaseWebService {
	
	
	
    /**
     * 手动开始一个事务
     * @return String
     * @throws Exception
     */
    public String beginTransaction() throws Exception {
        return ConnectionMap.beginTransaction();
    }

    /**
     * 手动提交一个事务
     * @param key String
     * @throws Exception
     */
    public void commit(String key) throws Exception {
        ConnectionMap.commit(key);
    }

    /**
     * 手动回滚一个事务
     * @param key String
     * @throws Exception
     */
    public void rollback(String key) throws Exception {
        ConnectionMap.rollback(key);
    }

}
