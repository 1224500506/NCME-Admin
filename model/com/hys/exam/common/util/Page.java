package com.hys.exam.common.util;

import java.util.List;
import java.util.Map;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 * 
 * 标题：住院医师
 * 
 * 作者：张伟清 2012-03-14
 * 
 * 描述：分页工具类
 * 
 * 说明:
 */
public class Page<T> implements PaginatedList{
	private Map otherResVal ; //YHQ，2017-0326，其他附加返回值			
	public Map getOtherResVal() {
		return otherResVal;
	}
	public void setOtherResVal(Map otherResVal) {
		this.otherResVal = otherResVal;
	}

	public static int PAGESIZE = 20;

	/**
	 * 查询结果
	 */
	private List<T> list ;
	
	/**
	 * 每页显示条数
	 */
	private int pageSize = PAGESIZE;

	/**
	 * 总页数
	 */
	private int pageCount = 0;

	/**
	 * 总记录数
	 */
	private int count = 0;

	/**
	 * 当前页
	 */
	private int currentPage = 0;

	/**
	 * 起始页数
	 */
	private int begin = 0;

	/**
	 * 结束页数
	 */
	private int end = 0;

	/**
	 * 当前跳转页数
	 */
	private int currentSkip = 1;
	
	/**
	 * 跳转每页显示条数
	 */
	private int skipSize = 10;

	/**
	 * 取得当前页
	 * 
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 取得总页数
	 * 
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 取得每页显示条数
	 * 
	 * @return
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 得到总记录数
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 得到起始页数
	 * 
	 * @return
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * 得到最后一页
	 * 
	 * @return
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		if (count <= 0)
			return;
		this.count = count;
		this.pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		if (currentPage > pageCount)
			currentPage = pageCount;
		if (currentPage <= 0)
			currentPage = 1;

		begin = (currentPage - 1) * pageSize;
		end = currentPage * pageSize;
		if (end >= count)
			end = count;

		currentSkip = (currentPage / skipSize) * skipSize + 1;
		if (currentPage % skipSize == 0)
			currentSkip = currentSkip - skipSize;
	}

	/**
	 * 是否可以回到首页
	 * 
	 * @return boolean
	 */
	public boolean getCanGoFirst() {
		return (this.currentPage > 1);
	}

	/**
	 * 是否可以上一页
	 * 
	 * @return boolean
	 */
	public boolean getCanGoPrevious() {
		return (this.currentPage > 1);
	}

	/**
	 * 是否可以继续下一页
	 * 
	 * @return boolean
	 */
	public boolean getCanGoNext() {
		return (this.currentPage <= this.pageCount);
	}

	/**
	 * 是否到达尾页
	 * 
	 * @return boolean
	 */
	public boolean getCanGoLast() {
		return (this.currentPage < this.pageCount);
	}

	/**
	 * 上一页
	 * 
	 * @return int
	 */
	public int getPrevious() {
		if (this.currentPage > 1)
			return this.currentPage - 1;
		else
			return 1;
	}

	/**
	 * 下一页
	 * 
	 * @return int
	 */
	public int getNext() {
		if (this.currentPage < this.pageCount)
			return this.currentPage + 1;
		else
			return this.pageCount;
	}

	/**
	 * 跳转到选中页面
	 * 
	 * @return boolean
	 */
	public boolean getCanSkipPrevious() {
		return getSkipPrevious() > 0;
	}

	/**
	 * 返回到上一页面
	 * 
	 * @return int
	 */
	public int getSkipPrevious() {
		return this.currentSkip - skipSize;
	}

	/**
	 * 返回到下一个页面
	 * 
	 * @return boolean
	 */
	public boolean getCanSkipNext() {
		return (getSkipNext() <= this.pageCount);
	}

	/**
	 * 跳转到下一页面
	 * 
	 * @return int
	 */
	public int getSkipNext() {
		return this.currentSkip + skipSize;
	}

	/**
	 * 生成下拉页数列表
	 * 
	 * @return int[]
	 */
	public int[] getCurrentSkipPageNumbers() {
		int count = skipSize;
		if (currentSkip + skipSize > pageCount)
			count = pageCount - currentSkip + 1;
		int[] Result = new int[count];
		for (int i = 0; i < count; i++) {
			Result[i] = currentSkip + i;
		}
		return Result;
	}

	/**
	 * 下一页
	 */
	public void goNext() {
		if (getCanGoNext()) {
			currentPage++;
			begin = (currentPage - 1) * pageSize;
			end = currentPage * pageSize;
		}
	}

	/**
	 * 上一页
	 */
	public void goPrevious() {
		if (getCanGoPrevious()) {
			currentPage--;
			begin = (currentPage - 1) * pageSize;
			end = currentPage * pageSize;
		}
	}

	//取出分页SQL
	public String getPageSql_oracle(String sql) {
		int startIndex = (currentPage - 1) * pageSize + 1;
		int endIndex = 0;
		if (currentPage == pageCount)
			endIndex = count;
		else
			endIndex = currentPage * pageSize;
		
		StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
		paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		paginationSQL.append(sql);
		paginationSQL.append("  ) temp where ROWNUM <= " + endIndex);
		paginationSQL.append(" ) WHERE  num >= " + startIndex);
		System.out.println(paginationSQL.toString());

		return paginationSQL.toString();
	}
	
	public Page() {
		this.pageSize = PAGESIZE;
	}
	
	public Page(int defaultPageSize) {
		this.pageSize = defaultPageSize;
	}

	public Page(int defaultPageSize, int page) {
		this.pageSize = defaultPageSize;
		this.currentPage = page;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFullListSize() {
		return count;
	}

	@Override
	public int getObjectsPerPage() {
		return pageSize;
	}

	@Override
	public int getPageNumber() {
		return currentPage;
	}

	@Override
	public String getSearchId() {
		return null;
	}

	private String sortCriterion;
	private SortOrderEnum SortDirection;
	
	public String getSortCriterion() {
		return sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return SortDirection;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		SortDirection = sortDirection;
	}
}
