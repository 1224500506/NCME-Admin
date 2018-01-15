/**
 *
 * <p>Description: 描述</p>
 * @author chenlaibin
 * @version 1.0 2014-4-1
 */

package com.hys.exam.service.local.impl;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemBugLogDAO;
import com.hys.exam.model.system.SystemBugLog;
import com.hys.exam.service.local.SystemBugLogManage;

public class SystemBugLogManageImpl implements SystemBugLogManage {

	private SystemBugLogDAO systemBugLogDAO;
	
	@Override
	public void getSystemBugLogList(Page<SystemBugLog> page, SystemBugLog log) {
		this.systemBugLogDAO.getSystemBugLogList(page, log);
	}

	@Override
	public SystemBugLog getSystemBugLogById(Long id) {
		return this.systemBugLogDAO.getSystemBugLogById(id);
	}

	@Override
	public int saveSystemBugLog(SystemBugLog log) {
		return this.systemBugLogDAO.saveSystemBugLog(log);
	}

	@Override
	public int deleteSystemBugLog(Long[] ids) {
		int row = 0;
		if(null != ids && ids.length>0){
			for(Long id: ids){
				if(id >0){
					row = this.systemBugLogDAO.deleteSystemBugLog(id);
				}
			}
		}
		return row;
	}

	public SystemBugLogDAO getSystemBugLogDAO() {
		return systemBugLogDAO;
	}

	public void setSystemBugLogDAO(SystemBugLogDAO systemBugLogDAO) {
		this.systemBugLogDAO = systemBugLogDAO;
	}

}


