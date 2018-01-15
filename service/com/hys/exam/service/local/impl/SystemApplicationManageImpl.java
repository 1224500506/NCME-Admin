package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemApplicationDAO;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.model.SystemClient;
import com.hys.exam.service.local.SystemApplicationManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 manageImpl
 * 
 * 说明:
 */
public class SystemApplicationManageImpl extends BaseMangerImpl implements SystemApplicationManage {

	private SystemApplicationDAO systemApplicationDAO ;

	public void setSystemApplicationDAO(SystemApplicationDAO systemApplicationDAO) {
		this.systemApplicationDAO = systemApplicationDAO;
	}
	
	@Override 
	public List<SystemApplication> getListByItem(SystemApplication item) {
		return systemApplicationDAO.getListByItem(item) ;
	}
	public void querySystemApplicationList(Page<SystemApplication> page,
			SystemApplication item) {
		systemApplicationDAO.querySystemApplicationList(page, item);
	}
	
	
	public int update(SystemApplication item) {
		return systemApplicationDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return systemApplicationDAO.deleteList(ids,idColName);
	}

	
	public SystemApplication get(int id) {
		return systemApplicationDAO.get(id);
	}

	
	public Integer save(SystemApplication item) {
		return systemApplicationDAO.save(item);
	}

}