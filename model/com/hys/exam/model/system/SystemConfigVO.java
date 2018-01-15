/**
 * SystemConfigVO.java
 * com.hys.exam.model.system
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月14日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.exam.model.system;

import java.io.Serializable;

/**
 * ClassName:SystemConfigVO
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月14日	上午11:18:59
 *
 * @see 	 
 *  
 */

public class SystemConfigVO implements Serializable{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since version 1.0
	 */
	
	private static final long serialVersionUID = 1666079565541942212L;
	
	private Integer id;
	/**
	 * time:TODO 时间限制
	 *
	 * @since version 1.0
	 */
	private Integer time;
	
	/**
	 * num:TODO 次数限制
	 *
	 * @since version 1.0
	 */
	private Integer loginNum;
	
	/**
	 * lockTime:TODO 锁定时间
	 *
	 * @since version 1.0
	 */
	private Integer lockTime;
	
	

	/**
	 * lockTime
	 *
	 * @return  the lockTime
	 * @since    Ver 1.0
	*/
	
	public Integer getLockTime() {
		return lockTime;
	}

	/**
	 * lockTime
	 *
	 * lockTime    the lockTime to set
	 * @since    Ver 1.0
	 */
	
	public void setLockTime(Integer lockTime) {
		this.lockTime = lockTime;
	}

	/**
	 * id
	 *
	 * @return  the id
	 * @since    Ver 1.0
	*/
	
	public Integer getId() {
		return id;
	}

	/**
	 * id
	 *
	 * id    the id to set
	 * @since    Ver 1.0
	 */
	
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * time
	 *
	 * @return  the time
	 * @since    Ver 1.0
	*/
	
	public Integer getTime() {
		return time;
	}

	/**
	 * time
	 *
	 * time    the time to set
	 * @since    Ver 1.0
	 */
	
	public void setTime(Integer time) {
		this.time = time;
	}

	/**
	 * loginNum
	 *
	 * @return  the loginNum
	 * @since    Ver 1.0
	*/
	
	public Integer getLoginNum() {
		return loginNum;
	}

	/**
	 * loginNum
	 *
	 * loginNum    the loginNum to set
	 * @since    Ver 1.0
	 */
	
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	
	


	
	

}
