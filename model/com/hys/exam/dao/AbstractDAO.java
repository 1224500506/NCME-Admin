package com.hys.exam.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.hys.auth.util.PageList;

/**
 * 抽象DataAccessObject
 * 
 * @author zdz
 * 
 */
public interface AbstractDAO {

	/**
	 * 泛型通过id获取实体对象,返回实体类
	 * 
	 * @param id
	 * @return T
	 */
	public <T> T get(Integer id) throws DataAccessException;
	/**
	 * 泛型通过ids批量删除相关数据,返回删除的数据条数
	 * 
	 * @param id
	 * @return int
	 */
	public int deleteList(long[] ids,String idColName) throws DataAccessException;;
	

	/**
	 * 泛型通过id删除相关数据,返回删除的数据条数
	 * 
	 * @param id
	 * @return int
	 */
	public int delete(Long id,String idColName) throws DataAccessException;;

	/**
	 * 泛型查询所有数据
	 * 
	 * @param <T>
	 * @return List<T>
	 */
	public <T> List<T> getListAll();

	/**
	 * DISPLAYTAG分页查询
	 * 
	 * @param <T>
	 * @param columns
	 * @param pageList
	 * @return List<T>
	 */
	public <T> List<T> getListWithPagination(String[] columns, PageList pageList);
}
