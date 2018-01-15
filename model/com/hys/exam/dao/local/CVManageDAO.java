package com.hys.exam.dao.local;

import com.hys.exam.model.*;
import com.hys.exam.utils.PageList;

import java.util.List;

public interface CVManageDAO {
	/**
	 * @author  杨红强
	 * @param   String
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    删除课程单元
	 */
	boolean delCvUnit(String cvId, String unitId);
	
	/**
	 * @author  杨红强
	 * @param   String
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    判断课程是否可以删除
	 */
	boolean cvDelFlag(String cvId);
	
	/**
	 * @author  杨红强
	 * @param   String[]
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    保存课程单元的顺序————按照String[]的顺序保存单元的顺序
	 */
	boolean saveUnitSequence(String[] unitId);
	
	List<CV> getCVList(CV queryCV);
	
	//新增-分页
	void getCVListPage(PageList pl, CV queryCV);

	Long addCV(CV cv);
	
	boolean updateCV(CV cv);
	
	boolean deleteCV(CV cv);

	Long addCVUnit(CVUnit cvUnit);

	List<CVUnit> getCVUnitList(CVUnit cvUnit);
	
	List<CVUnit> getCloneCVUnitList(CV queryCV);
	
	int cloneCVUnitList(CV queryCV);

	List<CV> getCloneCVList(CV queryCV);

	List<ExpertInfo> getTeacherList();

	void addUnionUpdate(CV queryCV);

	boolean deleteUnit(Long id);

	void swapCVUnit(CVUnit unit1, CVUnit unit2);

	void updateCVUnit(CVUnit cvUnit);

	void updateUnion(CVUnit cvUnit);

	List<CVUnit> getCVUnitList(CV queryCV);
	
	List<Long> getMaterialIds(long unit_id);

	List<QualityModel> getQualityList(long unit_id);

	List<ExpertInfo> getTeacherList(List<PropUnit> tempList);
	
	Long addCvRefUnit(int cvId,int unitId);

	Long addUnionRefSource(int cvId,String extend_read,String chooseSourseIDs,int key_nums,String key_words);

	List<CvUnitRefSource> getCVUnitRefSourceList(Long unit_id);

	CVUnit getCVUnit(Long unit_id);

	Long addCVSchedule(CVSchedule cvschedule);
	
	Long addCVSetSchedule(int proId, int cvId);
	
	List<CV> queryCVList(int proId);
	
	/**
	 * @author   ZJG
	 * @time     2017-01-05
	 * @param    String
	 * @return   List
	 * 方法说明： 根据教师姓名查询教师集合
	 */
	List<ExpertInfo> getTeacherListByName(String name);
	
	void deleteTeacher(CV cv);
	
	void deleteTeacherO(CV cv);
	
	boolean cloneAddCVUnit(Long oldUnitId, Long newUnitId) ;//YHQ 添加项目时克隆课程把源课程单元下的能力模型也一并克隆过去，2017-02-28

	
	/**
	 * @author taol
	 * @param cvl
	 * @return
	 * @desc 保存直播类课程信息
	 */
	Long saveCvLive(CvLive cvl);
	
	/**
	 * @author taoliang
	 * @param cvId
	 * @return
	 */
	List<CvLive> queryCvLiveList(Long cvId);
	
	/**
	 * @author taoliang
	 * @param cvl
	 * 修改直播课程信息
	 */
	void updateLive(CvLive cvl);
	
	/**
	 * @author taoliang
	 * @param cvlive
	 * 删除直播课程信息
	 */
	boolean delCvLive(CvLive cvlive);

	void getCVListByPage(PageList pl, CV queryCV);
	
	/**
	 * @author taoliang
	 * 当课件信息修改时同步更新单元信息
	 * @param cvUnit
	 * @return
	 */
	int updateCvUnitForLive(CVUnit cvUnit);

	/**
	 * updateCVUnitName:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param cvUnit
	 * @param  @return    
	 * @return int    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	int updateCVUnitName(CVUnit cvUnit);

	/**
	 * updateCVUnitType:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param cvUnit
	 * @param  @return    
	 * @return int    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	int updateCVUnitType(CVUnit cvUnit);
}
