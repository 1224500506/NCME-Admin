package com.hys.exam.queryObj;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2010-10-15
 * 
 * 描述：
 * 
 * 说明:
 */
public abstract class AbstractQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8987955320451402460L;
	
	/**
	 * 当前页 默认为0
	 */
	private int pageNo = 1;

	/**
	 * 每页显示数量 默认为20
	 */
	private int pageSize = 20;

	/**
	 * 总数
	 */
	private int fullSize = 0;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFullSize() {
		return fullSize;
	}

	public void setFullSize(int fullSize) {
		this.fullSize = fullSize;
	}

}
