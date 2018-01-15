package com.hys.exam.service.local.impl;

import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.model.*;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

import java.util.List;

public class CVManageImpl extends BaseMangerImpl implements CVManage {

	private CVManageDAO localCVManageDAO;
	
	/**
	 * @author  杨红强
	 * @param   String
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    删除课程单元
	 */
	@Override
	public boolean delCvUnit(String cvId, String unitId) {
		return localCVManageDAO.delCvUnit(cvId, unitId) ;
	}
	
	/**
	 * @author  杨红强
	 * @param   String
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    判断课程是否可以删除
	 */
	@Override
	public boolean cvDelFlag(String cvId) {
		return localCVManageDAO.cvDelFlag(cvId) ;
	}
	
	/**
	 * @author  杨红强
	 * @param   String[]
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    保存课程单元的顺序————按照String[]的顺序保存单元的顺序
	 */
	@Override
	public boolean saveUnitSequence(String[] unitId) {
		return localCVManageDAO.saveUnitSequence(unitId) ;
	}
	
	@Override
	public List<CV> getCVList(CV queryCV) {
		return localCVManageDAO.getCVList(queryCV);
	}

	//新增-分页
	@Override
	public void getCVListPage(PageList pl, CV queryCV) {
		localCVManageDAO.getCVListPage(pl, queryCV);
		
	}

	@Override
	public Long addCV(CV cv) {
		return localCVManageDAO.addCV(cv);
	}

	@Override
	public boolean updateCV(CV cv) {
		return localCVManageDAO.updateCV(cv);
	}

	@Override
	public boolean delete(CV cv) {
		return localCVManageDAO.deleteCV(cv);
	}

	public CVManageDAO getLocalCVManageDAO() {
		return localCVManageDAO;
	}

	public void setLocalCVManageDAO(CVManageDAO localCVManageDAO) {
		this.localCVManageDAO = localCVManageDAO;
	}

	@Override
	public Long addCVUnit(CVUnit cvUnit) {
		
		return localCVManageDAO.addCVUnit(cvUnit);
	}

	@Override
	public List<CVUnit> getCVUnitList(CVUnit cvUnit) {
		
		return localCVManageDAO.getCVUnitList(cvUnit);
	}

	@Override
	public List<CVUnit> getCloneCVUnitList(CV queryCV) {
		
		return localCVManageDAO.getCloneCVUnitList(queryCV);
	}

	@Override
	public List<CV> getCloneCVList(CV queryCV) {
		return localCVManageDAO.getCloneCVList(queryCV);
	}

	@Override
	public List<ExpertInfo> getTeacherList() {

		return localCVManageDAO.getTeacherList();
	}

	@Override
	public void addUnionUpdate(CV queryCV) {
		localCVManageDAO.addUnionUpdate(queryCV);
		
	}

	@Override
	public int cloneCVUnitList(CV queryCV) {
		return localCVManageDAO.cloneCVUnitList(queryCV);
	}

	@Override
	public boolean deleteUnit(Long id) {
		return localCVManageDAO.deleteUnit(id);
	}

	@Override
	public void swapCVUnit(CVUnit unit1, CVUnit unit2) {
		localCVManageDAO.swapCVUnit(unit1,unit2);
		
	}

	@Override
	public void updateCVUnit(CVUnit cvUnit) {
		localCVManageDAO.updateCVUnit(cvUnit);
		
	}

	@Override
	public void updateUnion(CVUnit cvUnit) {
		localCVManageDAO.updateUnion(cvUnit);
		
	}

	@Override
	public List<CVUnit> getCVUnitList(CV queryCV) {
		
		return localCVManageDAO.getCVUnitList(queryCV);
	}

	@Override
	public List<Long> getMaterialIds(long unit_id) {
		return localCVManageDAO.getMaterialIds(unit_id);
	}

	@Override
	public List<QualityModel> getQualityList(long unit_id) {
		return localCVManageDAO.getQualityList(unit_id);
	}

	@Override
	public List<ExpertInfo> getTeacherList(List<PropUnit> tempList) {
		return localCVManageDAO.getTeacherList(tempList);
	}

	/**
	 * @author  ZJG
	 * @param   int
	 * @return  Long
	 * @time    2016-12-27
	 * 方法说明    保存课程单元关联表信息
	 */
	@Override
	public Long addCvRefUnit(int cvId,int unitId) {
		return localCVManageDAO.addCvRefUnit(cvId, unitId);
	}
	@Override
	public Long addUnionRefSource(int cvId,String extend_read,String chooseSourseIDs,int key_nums,String key_words) {
		return localCVManageDAO.addUnionRefSource(cvId, extend_read, chooseSourseIDs, key_nums, key_words);
	}
	@Override
	public List<CvUnitRefSource> getCVUnitRefSourceList(Long unit_id) {
		return localCVManageDAO.getCVUnitRefSourceList(unit_id);
	}
	@Override
	public CVUnit getCVUnit(Long unit_id) {
		return localCVManageDAO.getCVUnit(unit_id);
	}
	
	/**
	 * @author  ZJG
	 * @param   int
	 * @return  Long
	 * @time    2016-12-28
	 * 方法说明   保存课程表信息
	 */
	@Override
	public Long addCVSchedule(CVSchedule cvschedule){
		return localCVManageDAO.addCVSchedule(cvschedule);
	}

	/**
	 * @author  ZJG
	 * @param   int
	 * @return  Long
	 * @time    2016-12-28
	 * 方法说明   保存项目与课程信息
	 */
	@Override
	public Long addCVSetSchedule(int proId, int cvId) {
		return localCVManageDAO.addCVSetSchedule(proId,cvId);
	}
	
	/**
	 * @author  ZJG
	 * @param   int
	 * @return  Long
	 * @time    2016-12-28
	 * 方法说明   根据项目id查询项目下的课程信息
	 */
	@Override
	public List<CV> queryCVList(int proId){
		return localCVManageDAO.queryCVList(proId);
	}
	
	/**
	 * @author   ZJG
	 * @time     2017-01-05
	 * @param    String
	 * @return   List
	 * 方法说明： 根据教师姓名查询教师集合
	 */
	@Override
	public List<ExpertInfo> getTeacherListByName(String name){
		return localCVManageDAO.getTeacherListByName(name);
	}

	@Override
	public void deleteTeacher(CV cv) {
		localCVManageDAO.deleteTeacher(cv);
	}

	@Override
	public void deleteTeacherO(CV cv) {
		localCVManageDAO.deleteTeacherO(cv);
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-02-28
	 * @param    Log
	 * @return   Boolean
	 * 方法说明：YHQ 添加项目时克隆课程把源课程单元下的能力模型也一并克隆过去，2017-02-28
	 */
	@Override
	public boolean cloneAddCVUnit(Long oldUnitId, Long newUnitId) {
		return localCVManageDAO.cloneAddCVUnit(oldUnitId, newUnitId) ;
	}
	
	
	@Override
	public Long addCvLive(CvLive cvl) {
		return localCVManageDAO.saveCvLive(cvl);
	}

	@Override
	public List<CvLive> queryCvLiveList(Long cvId) {
		return localCVManageDAO.queryCvLiveList(cvId);
	}

	@Override
	public void updateLive(CvLive cvl) {
		localCVManageDAO.updateLive(cvl);
	}

	@Override
	public void getCVListByPage(PageList pl, CV queryCV) {
		localCVManageDAO.getCVListByPage(pl, queryCV);
		
	}

	@Override
	public boolean delCvLive(CvLive cvlive) {
		return localCVManageDAO.delCvLive(cvlive);
	}

	@Override
	public int updateCvUnitForLive(CVUnit cvUnit) {
		return localCVManageDAO.updateCvUnitForLive(cvUnit);
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.service.local.CVManage#updateCVUnitName(com.hys.exam.model.CVUnit)
	 */
	@Override
	public int updateCVUnitName(CVUnit cvUnit) {
		
		// TODO Auto-generated method stub
		return localCVManageDAO.updateCVUnitName(cvUnit);
		
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.service.local.CVManage#updateCVUnitType(com.hys.exam.model.CVUnit)
	 */
	@Override
	public int updateCVUnitType(CVUnit cvUnit) {
		
		// TODO Auto-generated method stub
		return localCVManageDAO.updateCVUnitType(cvUnit);
		
	}

}
