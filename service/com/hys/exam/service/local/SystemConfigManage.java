/**
 * SystemConfigManage.java
 * com.hys.exam.service.local
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月14日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.exam.service.local;

import java.util.Map;

import com.hys.exam.model.system.SystemConfigVO;
import com.hys.framework.service.BaseService;

/**
 * ClassName:SystemConfigManage
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月14日	上午10:42:36
 *
 * @see 	 
 *  
 */

public interface SystemConfigManage extends BaseService {
	
	/**
	 * getConfig:得到登录限制配置，目前就一条数据
	 * TODO 得到登录限制配置，目前就一条数据
	 *
	 * @param   
	 * @return Map<String,String>    
	 * @throws 
	 * @since  　version 1.0
	*/
	public SystemConfigVO getConfig();
	
	/**
	 * editConfig:编辑配置
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * @param lockTime 
	 *
	 * @param      
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	public int updateConfig(String time,String num, String lockTime);

}
