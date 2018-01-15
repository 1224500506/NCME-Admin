package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVSetAuthorizationDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetAuthorCheck;
import com.hys.exam.model.CVSetAuthorQuery;
import com.hys.exam.model.CVSetAuthorization;
import com.hys.exam.service.local.CVSetAuthorizationManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CVSetAuthorizationManageImpl extends BaseMangerImpl implements CVSetAuthorizationManage {
	private CVSetAuthorizationDAO cVSetAuthorizationDAO;
	
	public CVSetAuthorizationDAO getcVSetAuthorizationDAO() {
		return cVSetAuthorizationDAO;
	}

	public void setcVSetAuthorizationDAO(CVSetAuthorizationDAO cVSetAuthorizationDAO) {
		this.cVSetAuthorizationDAO = cVSetAuthorizationDAO;
	}

	@Override
	public CVSet getCVSetForAuthorization(Long cvSetId, Long authorizationId) {
		return cVSetAuthorizationDAO.getCVSetForAuthorization(cvSetId, authorizationId);
	}
	
	@Override
	public CVSet getCVSetForAddAuthorization(Long cvSetId) {
		return cVSetAuthorizationDAO.getCVSetForAddAuthorization(cvSetId);
	}
	
	@Override
	public int saveCVSetAuthorization(List<Object> saveParams) {
		return cVSetAuthorizationDAO.saveCVSetAuthorization(saveParams);
	}

	@Override
	public List<CVSetAuthorQuery> getCVSetListForAuthor(CVSet record) {
		return cVSetAuthorizationDAO.getCVSetListForAuthor(record);
	}

	@Override
	public int updateCVSetAuthorization(List<Object> saveParams) {
		return cVSetAuthorizationDAO.updateCVSetAuthorization(saveParams);
	}

	@Override
	public List<CVSet> getCVSetListForRelease(CVSet queryCVSet, Long flag) {
		return cVSetAuthorizationDAO.getCVSetListForRelease(queryCVSet, flag);
	}

	@Override
	public CVSetAuthorCheck getAuthorBeforeCheck(Long cvSetId) {
		return cVSetAuthorizationDAO.getAuthorBeforeCheck(cvSetId);
	}

	@Override
	public int deleteAuthor(Long cvSetId, Long authorizationId) {
		return cVSetAuthorizationDAO.deleteAuthor(cvSetId, authorizationId);
	}

	@Override
	public int updateCVSetForRelease(Long [] cvSetIds, Long flag) {
		int row = 0;
		if(null != cvSetIds && cvSetIds.length >0){
			for(Long cvSetId : cvSetIds){
				row = cVSetAuthorizationDAO.updateCVSetForRelease(cvSetId, flag);
			}
		}
		return row;
	}

	@Override
	public int getFaceteachCount(Long cvSetId) {
		return cVSetAuthorizationDAO.getFaceteachCount(cvSetId);
	}

	@Override
	public void SendMessageForUser(CVSet cvset, Long flag) {
		cVSetAuthorizationDAO.SendMessageForUser(cvset, flag);
	}

	@Override
	public List<CVSet> getCVSetCheckForRelease(String _cvSetIds, Long flag) {
		return cVSetAuthorizationDAO.getCVSetCheckForRelease(_cvSetIds, flag);
	}

}
