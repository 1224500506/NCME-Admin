package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemClientDAO;
import com.hys.exam.model.SystemClient;
import com.hys.exam.service.local.SystemClientManage;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemClientManageImpl implements SystemClientManage {

	
	private SystemClientDAO systemClientDAO;

	public void setSystemClientDAO(SystemClientDAO systemClientDAO) {
		this.systemClientDAO = systemClientDAO;
	}

	public List<SystemClient> getListByItem(SystemClient item){
		return this.systemClientDAO.getListByItem(item);
	}
	
	public void getSystemClientList(Page<SystemClient> page,
			SystemClient item) {
		this.systemClientDAO.getSystemClientList(page, item);
	}
	
	
	public int update(SystemClient item) {
		return this.systemClientDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return this.systemClientDAO.deleteList(ids,idColName);
	}

	public int delete(long id, String idColName) {
		return this.systemClientDAO.delete(id,idColName);
	}
	
	public SystemClient get(int id) {
		return this.systemClientDAO.get(id);
	}

	
	public Integer save(SystemClient item) {
		return this.systemClientDAO.save(item);
	}
	
	

}
