package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.NcmeCourseTypeDAO;
import com.hys.exam.model.NcmeCourseType;
import com.hys.exam.service.local.NcmeCourseTypeManage;
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
public class NcmeCourseTypeManageImpl extends BaseMangerImpl implements NcmeCourseTypeManage {
	private NcmeCourseTypeDAO ncmeCourseTypeDAO;

	public void setNcmeCourseTypeDAO(NcmeCourseTypeDAO ncmeCourseTypeDAO) {
		this.ncmeCourseTypeDAO = ncmeCourseTypeDAO;
	}

	@Override
	public List<NcmeCourseType> getNcmeCourseTypeList() {
		return ncmeCourseTypeDAO.getNcmeCourseTypeList();
	}

}
