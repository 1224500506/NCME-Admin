package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.EditionManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.EditionManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantObject;

public class EditionManageImpl extends BaseMangerImpl implements EditionManage {

	private EditionManageDAO localEditionManageDAO;
	//专家或教师
	private ExpertManageManage expertManageManage;
	public EditionManageDAO getLocalEditionManageDAO() {
		return localEditionManageDAO;
	}

	public void setLocalEditionManageDAO(EditionManageDAO localEditionManageDAO) {
		this.localEditionManageDAO = localEditionManageDAO;
	}

	public void setExpertManageManage(ExpertManageManage expertManageManage) {
		this.expertManageManage = expertManageManage;
	}

	@Override
	public Edition getEditionById(Long id) {
		return localEditionManageDAO.getEditionById(id);
	}

	@Override
	public boolean resortOrderNum(String orderstr) {
		boolean flag = false;
		
		try{
			flag = localEditionManageDAO.resortOrderNum(orderstr);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}
	
	@Override
	public boolean resortOrderNumCV(String orderstr) {
		boolean flag = false;
		
		try{
			flag = localEditionManageDAO.resortOrderNumCV(orderstr);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}
	
	@Override
	public List<Edition> getEditionList(Edition edtion) {
		return localEditionManageDAO.getEditionList(edtion);
	}
	
	//chenlb add
	//得到项目列表(绑定与否)
	@Override
	public List<CVSet> getCVSetListByEdition(Long editionId, String cvsetName, boolean isBind){
		List<CVSet> cvSetList=this.localEditionManageDAO.getCVSetListByEdition(editionId, cvsetName, isBind);
		//获取负责人信息
		for(CVSet cvSet:cvSetList){
			List<ExpertInfo> managerList=expertManageManage.getExpertListByCvSetId(cvSet.getId(), Constants.CVSET_TYPE_MANAGER);
			cvSet.setManagerList(managerList);
		}
			
		return cvSetList;
	}
	
	//chenlb add
	//得到项目下的课程列表(绑定与否)
	public List<CV> getCVListByEdition(Long editionId, String cvName, boolean isBind){
		return this.localEditionManageDAO.getCVListByEdition(editionId, cvName, isBind);
	}
	
	//绑定
	//chenlb add
	@Override
	public int saveEditionCVSetIds(Long id, Long [] cvsetIds){
		return this.localEditionManageDAO.saveEditionCVSetIds(id, cvsetIds);
	}
	
	//解绑
	//chenlb add
	@Override
	public int deleteEditionCVSetIds(Long id, Long [] cvsetIds){
		return this.localEditionManageDAO.deleteEditionCVSetIds(id, cvsetIds);
	}
	
	//解绑课程
	//chenlb add
	@Override
	public int saveEditionCVIds(Long id, Long [] cvIds){
		return this.localEditionManageDAO.saveEditionCVIds(id, cvIds);
	}
	
	//绑定课程
	//chenlb add
	@Override
	public int deleteEditionCVIds(Long id, Long [] cvIds){
		return this.localEditionManageDAO.deleteEditionCVIds(id, cvIds);
	}
	
	@Override
	public List<Edition> getEditionListView(Edition edtion, CVSet queryCVSet) {
		return localEditionManageDAO.getEditionListView(edtion, queryCVSet);
	}

	@Override
	public Long addEdition(Edition edition) {
		return localEditionManageDAO.addEdition(edition);
	}

	@Override
	public boolean updateEdition(Edition edition) {
		return localEditionManageDAO.updateEdition(edition);
	}

	@Override
	public boolean deleteEditionById(Long id) {
		return localEditionManageDAO.deleteEditionById(id);
	}
	//绑定专委会
	@Override
	public int saveEditionZWHIds(Long id, Long[] cvIds) {
		// TODO 绑定专委会
		return this.localEditionManageDAO.saveEditionZHWIds(id, cvIds);
	}
	//解绑专委会
	@Override
	public int deleteEditionZWHIds(Long id, Long[] cvIds) {
		// TODO 解绑专委会
		return this.localEditionManageDAO.deleteEditionZHWIds(id, cvIds);
	}
	
}
