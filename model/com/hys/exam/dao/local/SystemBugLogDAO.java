/**
 *
 * <p>系统bug日志</p>
 * @author chenlaibin
 * @version 1.0 2014-4-1
 */

package com.hys.exam.dao.local;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.system.SystemBugLog;

public interface SystemBugLogDAO {

	public void getSystemBugLogList(Page<SystemBugLog> page, SystemBugLog log);
	
	public SystemBugLog getSystemBugLogById(Long id);
	
	public int saveSystemBugLog(SystemBugLog log);
	
	public int deleteSystemBugLog(Long id);
}


