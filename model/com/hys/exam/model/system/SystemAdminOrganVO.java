/**
 *
 * <p>继教地区</p>
 * @author chenlaibin
 * @version 1.0 2014-7-15
 */

package com.hys.exam.model.system;

import com.hys.exam.model.NcmeAdminOrgan;

@SuppressWarnings("serial")
public class SystemAdminOrganVO extends NcmeAdminOrgan{

	private Long siteId;

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

}


