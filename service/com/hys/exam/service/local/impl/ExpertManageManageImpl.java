package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamOlemPropDAO;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.exam.service.local.ExamOlemPropManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 专家信息管理
 * @author Han
 *
 */
public class ExpertManageManageImpl extends BaseMangerImpl implements
	ExpertManageManage {

	private ExpertManageDAO localExpertManageDAO;

	public ExpertManageDAO getLocalExpertManageDAO() {
		return localExpertManageDAO;
	}

	public void setLocalExpertManageDAO(ExpertManageDAO localExpertManageDAO) {
		this.localExpertManageDAO = localExpertManageDAO;
	}

	@Override
	public List<ExpertInfo> getExpertList(ExpertInfo expert) {
		return localExpertManageDAO.getExpertList(expert);
	}

	@Override
	public ExpertInfo getExpertInfo(ExpertInfo expert) {
		return localExpertManageDAO.getExpertInfo(expert.getId());
	}
	
	@Override
	public ExpertInfo getExpertInfoByUsername(String username){
		return this.localExpertManageDAO.getExpertInfoByUsername(username);
	}
	
	@Override
	public List<ExpertInfo> getExpertInfoNameByCvSetId(Long id){
		return this.localExpertManageDAO.getExpertInfoNameByCvSetId(id);
	}

	@Override
	public ExpertInfo getExpertInfo(Long id) {
		return localExpertManageDAO.getExpertInfo(id);
	}

	@Override
	public boolean addExpertInfo(ExpertInfo expert) throws Exception {
		return localExpertManageDAO.addExpertInfo(expert);
	}

	@Override
	public boolean updateExpertInfo(ExpertInfo expert) throws Exception {
		return localExpertManageDAO.updateExpertInfo(expert);
	}

	@Override
	public boolean deleteExpertInfo(ExpertInfo expert) {
		return localExpertManageDAO.deleteExpertInfo(expert.getId());
	}

	@Override
	public boolean deleteExpertInfo(Long id) {
		return localExpertManageDAO.deleteExpertInfo(id);
	}
	/**
	 *根据项目id获取项目负责人 或授课老师
	 *type=1为项目负责人
	 *type=2为授课老师
	 */
	@Override
	public List<ExpertInfo> getExpertListByCvSetId(Long cvSetId,Integer type) {
		// TODO Auto-generated method stub
		return localExpertManageDAO.getExpertListByCvSetId(cvSetId, type);
	}
	/**
	 * 页面管理---名师
	 */
	@Override
	public void getExpertPageList(PageList pl, ExpertInfo expertInfo) {
		// TODO 页面管理---名师
		localExpertManageDAO.getExpertPageList(pl, expertInfo);
		
	}

	@Override
	public String lockExpertInfo(Long id, Integer checkState) {
		return localExpertManageDAO.lockExpertInfo(id, checkState);
	}

	@Override
	public void getExpertPageListView(PageList pl, ExpertInfo expertInfo) {
		localExpertManageDAO.getExpertPageListView(pl, expertInfo);
		
	}
	
	
}
