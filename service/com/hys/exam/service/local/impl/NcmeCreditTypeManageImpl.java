package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.NcmeCreditTypeDAO;
import com.hys.exam.model.NcmeCreditType;
import com.hys.exam.service.local.NcmeCreditTypeManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCreditTypeManageImpl extends BaseMangerImpl implements NcmeCreditTypeManage {
	private NcmeCreditTypeDAO ncmeCreditTypeDAO;

	public void setNcmeCreditTypeDAO(NcmeCreditTypeDAO ncmeCreditTypeDAO) {
		this.ncmeCreditTypeDAO = ncmeCreditTypeDAO;
	}

	@Override
	public List<NcmeCreditType> getNcmeCreditTypeList() {
		return ncmeCreditTypeDAO.getNcmeCreditTypeList();
	}
}
