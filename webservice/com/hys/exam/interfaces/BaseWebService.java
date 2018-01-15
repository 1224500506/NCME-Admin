package com.hys.exam.interfaces;
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
public interface BaseWebService {
	
    /**
     *手动开始一个事务
     */
    public String beginTransaction() throws Exception;

    /**
     *手动提交一个事务
     */
    public void commit(String key) throws Exception;

    /**
     *手动回滚一个事务
     */
    public void rollback(String key) throws Exception;
    
}
