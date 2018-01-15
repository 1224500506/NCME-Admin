package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;

public interface EditionManageDAO {
	
	Edition getEditionById(Long id);
	
	List<Edition> getEditionList(Edition edtion);
	
	List<Edition> getEditionListView(Edition edition, CVSet queryCVset);
	
	Long addEdition(Edition edition);
	
	boolean updateEdition(Edition edition);
	
	boolean deleteEditionById(Long id);

	boolean resortOrderNum(String orderstr);
	
	boolean resortOrderNumCV(String orderstr);
	
	//chenlb add
	//得到项目列表(绑定与否)
	public List<CVSet> getCVSetListByEdition(Long editionId, String cvsetName, boolean isBind);
	
	//chenlb add
	//得到课程列表(绑定与否)
	public List<CV> getCVListByEdition(Long editionId, String cvName, boolean isBind);
	
	//绑定
	//chenlb add
	public int saveEditionCVSetIds(Long id, Long [] cvsetIds);
	
	//解绑
	//chenlb add
	public int deleteEditionCVSetIds(Long id, Long [] cvsetIds);
	
	//解绑课程
	//chenlb add
	public int saveEditionCVIds(Long id, Long [] cvIds);
	
	//绑定课程
	//chenlb add
	public int deleteEditionCVIds(Long id, Long [] cvIds);
	//绑定专委会
	int saveEditionZHWIds(Long id, Long[] cvIds);
	//解绑专委会
	int deleteEditionZHWIds(Long id, Long[] cvIds);

}
