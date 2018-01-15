package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemOrgDAO;
import com.hys.exam.model.SystemOrg;
import com.hys.exam.service.local.SystemOrgManage;

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
public class SystemOrgManageImpl implements SystemOrgManage {

	
	private SystemOrgDAO systemOrgDAO;

	public void setSystemOrgDAO(SystemOrgDAO systemOrgDAO) {
		this.systemOrgDAO = systemOrgDAO;
	}

	public List<SystemOrg> getListAll() {
		return this.systemOrgDAO.getListAll();
	}
}
