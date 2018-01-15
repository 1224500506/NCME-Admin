
package com.hys.exam.model.systemQuery;

import com.hys.exam.model.system.SystemCard;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-17
*/
@SuppressWarnings("serial")
public class SystemCardQuery extends SystemCard {

	/**
	 * 结束卡号
	 */
	private String cardNumberEnd;
	

	public String getCardNumberEnd() {
		return cardNumberEnd;
	}

	public void setCardNumberEnd(String cardNumberEnd) {
		this.cardNumberEnd = cardNumberEnd;
	}
}


