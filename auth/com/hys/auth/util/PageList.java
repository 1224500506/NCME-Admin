package com.hys.auth.util;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author zdz
 * 
 *   fullListSize 所有条目数目 pageNumber 当前所在页号 objectsPerPage 每页显示条数 list
 *   本页所需要显示的数据
 * 
 */
@SuppressWarnings("unchecked")
public class PageList implements PaginatedList {

	private List list;
	private int pageNumber = 1;
	private int objectsPerPage = 20;
	private int fullListSize = 0;
	private String sortCriterion;
	private SortOrderEnum sortDirection;
	private String searchId;


	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public int getFullListSize() {
		return fullListSize;
	}

	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}


	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("pageNumber", pageNumber).append("objectsPerPage",
						objectsPerPage).append("fullListSize", fullListSize)
				.append("sortCriterion", sortCriterion).append("sortDirection",
						sortDirection).append("searchId", searchId).toString();
	}
}
