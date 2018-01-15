package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.framework.service.BaseService;

public interface EditionManage extends BaseService {
	
	Edition getEditionById(Long id);
	
	List<Edition> getEditionList(Edition edtion);
	
    List<Edition> getEditionListView(Edition edition, CVSet queryCVSet);
	
	Long addEdition(Edition edition);
	
	public boolean resortOrderNum(String orderstr);
	
	boolean updateEdition(Edition edition);
	
	boolean deleteEditionById(Long id);
	
	public boolean resortOrderNumCV(String orderstr);
	
	//chenlb add
	//得到项目列表(绑定与否)
	public List<CVSet> getCVSetListByEdition(Long editionId, String cvsetName, boolean isBind);
	
	//chenlb add
	//得到项目下的课程列表(绑定与否)
	public List<CV> getCVListByEdition(Long editionId, String cvName, boolean isBind);
	
	//解绑项目
	//chenlb add
	public int saveEditionCVSetIds(Long id, Long [] cvsetIds);
	
	//绑定项目
	//chenlb add
	public int deleteEditionCVSetIds(Long id, Long [] cvsetIds);
	
	//解绑课程
	//chenlb add
	public int saveEditionCVIds(Long id, Long [] cvIds);
	
	//绑定课程
	//chenlb add
	public int deleteEditionCVIds(Long id, Long [] cvIds);
	
	//绑定专委会
	int saveEditionZWHIds(Long id, Long[] cvIds);
	//解绑专委会
	int deleteEditionZWHIds(Long id, Long[] cvIds);
}
