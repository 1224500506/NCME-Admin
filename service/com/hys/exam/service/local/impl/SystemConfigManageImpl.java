/**
 * SystemConfigManageImpl.java
 * com.hys.exam.service.local.impl
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月14日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.exam.service.local.impl;

import java.util.Map;

import com.hys.exam.dao.local.SystemClientDAO;
import com.hys.exam.dao.local.SystemConfigDAO;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.service.local.SystemConfigManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * ClassName:SystemConfigManageImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月14日	上午10:48:13
 *
 * @see 	 
 *  
 */

public class SystemConfigManageImpl extends BaseMangerImpl implements SystemConfigManage{

	private SystemConfigDAO systemConfigDAO;
	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.service.local.SystemConfigManage#getConfig()
	 */
	@Override
	public SystemConfigVO getConfig() {
		
		// TODO Auto-generated method stub
		return this.systemConfigDAO.getConfig();
		
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.service.local.SystemConfigManage#editConfig()
	 */
	@Override
	public int updateConfig(String time,String num,String lockTime) {
		return this.systemConfigDAO.updateConfig(time, num,lockTime);
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * systemConfigDAO
	 *
	 * @return  the systemConfigDAO
	 * @since    Ver 1.0
	*/
	
	public SystemConfigDAO getSystemConfigDAO() {
		return systemConfigDAO;
	}

	/**
	 * systemConfigDAO
	 *
	 * systemConfigDAO    the systemConfigDAO to set
	 * @since    Ver 1.0
	 */
	
	public void setSystemConfigDAO(SystemConfigDAO systemConfigDAO) {
		this.systemConfigDAO = systemConfigDAO;
	}
	
	
	
	

}
