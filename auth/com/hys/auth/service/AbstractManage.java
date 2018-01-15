package com.hys.auth.service;

import java.util.List;

import com.hys.auth.util.PageList;

/**
 * 通用抽象接口Manange
 * @author zdz
 *
 */
public interface AbstractManage {
//	public Object getDao();
	/**
	 * 泛型保存操作
	 * 保存完毕返回新生成的ID
	 * return long
	 */
	public <T> Integer save(T t);
	
	/**
	 * 泛型通过id获取实体对象,返回实体类
	 * @param id
	 * @return T
	 */
	public <T> T get(Integer id);
	
	/**
	 * 泛型通过id删除相关数据,返回删除的数据条数
	 * @param id
	 * @return int
	 */
	public int delete(Integer id);
	
	/**
	 * 泛型查询所有数据
	 * @param <T>
	 * @return List<T>
	 */
	public <T> List<T> getListAll();
	
	
	/**
	 * DISPLAYTAG分页查询
	 * @param <T>
	 * @param columns
	 * @param pageList
	 * @return List<T>
	 */
	public <T> List<T> getListWithPagination(String[] columns, PageList pageList);
}
