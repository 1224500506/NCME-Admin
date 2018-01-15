package com.hys.exam.dao.local;


import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetAuthorCheck;
import com.hys.exam.model.CVSetAuthorQuery;
import com.hys.exam.model.CVSetAuthorization;

public interface CVSetAuthorizationDAO {
	
	public CVSet getCVSetForAddAuthorization(Long cvSetId);
	
	public CVSet getCVSetForAuthorization(Long cvSetId, Long authorizationId);
	
	public int saveCVSetAuthorization(List<Object> saveParams);
	
	public int updateCVSetAuthorization(List<Object> saveParams);
	
	List<CVSetAuthorQuery> getCVSetListForAuthor(CVSet record);
	
	List<CVSet> getCVSetListForRelease(CVSet queryCVSet, Long flag);
	
	CVSetAuthorCheck getAuthorBeforeCheck(Long cvSetId);
	
	int deleteAuthor(Long cvSetId, Long authorizationId);
	
	int updateCVSetForRelease(Long cvSetId, Long flag);
	
	int getFaceteachCount(Long cvSetId);
	
	void SendMessageForUser(CVSet cvset, Long flag);
	
	List<CVSet> getCVSetCheckForRelease(String _cvSetIds, Long flag);
}
