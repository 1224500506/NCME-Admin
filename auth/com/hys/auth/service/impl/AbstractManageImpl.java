package com.hys.auth.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.hys.auth.dao.AbstractDAO;
import com.hys.auth.service.AbstractManage;
import com.hys.auth.util.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public abstract class AbstractManageImpl extends BaseMangerImpl implements AbstractManage {

	public abstract AbstractDAO getDao();

	public int delete(Integer id) throws DataAccessException {
		return getDao().delete(id);
	}

	public <T> T get(Integer id) throws DataAccessException {
		return getDao().get(id);
	}

	public <T> List<T> getListAll() {
		return getDao().getListAll();
	}

	public <T> List<T> getListWithPagination(String[] columns, PageList pageList) {
		return getDao().getListWithPagination(columns, pageList);
	}

	public <T> Integer save(T t) throws DataAccessException {
		return getDao().save(t);
	}

}
