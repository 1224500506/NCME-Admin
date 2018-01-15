/**
 *
 * <p>Description: 描述</p>
 * @author chenlaibin
 * @version 1.0 2014-7-15
 */

package com.hys.exam.model.system;

import com.hys.exam.model.SystemSite;

@SuppressWarnings("serial")
public class SystemSiteVO extends SystemSite{

	private Long cardtypeId;

	public Long getCardtypeId() {
		return cardtypeId;
	}

	public void setCardtypeId(Long cardtypeId) {
		this.cardtypeId = cardtypeId;
	}
}


