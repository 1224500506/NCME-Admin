package com.hys.exam.returnObj;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2010-1-8
 * 
 * 描述：
 * 
 * 说明:
 * 
 * @param <T>
 */
public abstract class AbstractReturn<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3116258999521598691L;

	private int statusCode;

	public int getStatusCode() {
		return statusCode;
	}
	
	private int total_count;
	
	private List<T> returnList;

	public List<T> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<T> returnList) {
		this.returnList = returnList;
	}
	
	public int getTotal_count() {
		return total_count;
	}



	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}



	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
