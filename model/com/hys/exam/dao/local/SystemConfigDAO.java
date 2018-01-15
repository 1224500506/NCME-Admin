/**
 * SystemConfigDAO.java
 * com.hys.exam.dao.local
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月14日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.exam.dao.local;


import com.hys.exam.model.system.SystemConfigVO;

/**
 * ClassName:SystemConfigDAO
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月14日	上午11:14:39
 *
 * @see 	 
 *  
 */

public interface SystemConfigDAO {
	
	SystemConfigVO getConfig(); 
	
	int updateConfig(String time,String num, String lockTime);

}
