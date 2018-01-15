/**
 *
 * <p>Description: 描述</p>
 * @author chenlaibin
 * @version 1.0 2014-4-1
 */

package com.hys.exam.service.local;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.system.SystemBugLog;

public interface SystemBugLogManage {

	public void getSystemBugLogList(Page<SystemBugLog> page, SystemBugLog log);
	
	public SystemBugLog getSystemBugLogById(Long id);
	
	public int saveSystemBugLog(SystemBugLog log);
	
	public int deleteSystemBugLog(Long[] ids);
}


