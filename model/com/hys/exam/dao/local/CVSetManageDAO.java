package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.CvsetQualityHistory;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.utils.PageList;

public interface CVSetManageDAO {
	/**
	 * @author   杨红强
	 * @time     2017-06-06	 
	 * @return   
	 * 方法说明： 保存项目中的班次安排
	 */
	public void saveCVSetScheduleFaceTeach(List<CVSetScheduleFaceTeach> cvSetScheduleFaceTeachList, Long cvSetId);
	/**
	 * @author   杨红强
	 * @time     2017-06-06	 
	 * @return   
	 * 方法说明： 获取项目中的班次安排
	 */
	public List<CVSetScheduleFaceTeach> queryCVSetScheduleFaceTeachByCVsetId(Long cvSetId);
	
	/**
	 * @author   杨红强
	 * @time     2017-05-30
	 * @param    cvId
	 * @return   
	 * 方法说明： 保存项目中课程顺序
	 */
	public void saveScheduleSequence(Long cvSetId, String scheduleIds);
	
	/**
	 * @author   杨红强
	 * @time     2017-05-26
	 * @param    cvId
	 * @return   
	 * 方法说明： 从项目中删除课程
	 */
	public void deleteCVfromCvsetByCvId(Long cvId);
	
	List<CVSet> getCVSetList(CVSet queryCVSet);
	
	//可以审核的项目列表  chenlb add
	public List<CVSet> getCVSetListForQualify(CVSet queryCVSet);
	
	//得到项目下所有可以的专家
	public List<ExpertInfo> getExpertInfoListByCVSetId(Long cvsetId);
	
	//添加项目审核记录历史表	chenlb add
	public int saveCvsetQualityHistory(CvsetQualityHistory history);
	
	//得到项目下同一个审核状态的 列表
	public List<CvsetQualityHistory> getCvsetQualityHistoryListByCvsetId(Long cvsetId, Integer qualifyStatus);
	
	//根据项目和专家得到其审核历史记录 chenlb add 
	public CvsetQualityHistory getCvsetQualityHistoryByCvsetAndExpert(Long cvsetId, Long expertId);
	
	//根据项目和专家得到其审核历史记录 chenlb add 
	public List<CvsetQualityHistory> getCvsetQualityHistoryByCvsetAndExpertInfo(Long cvsetId);
	
	
	Long addCVSet(CVSet cvSet);
	
	boolean updateCVSet(CVSet cvSet);
	
	boolean deleteCVSet(CVSet cvSet);
	
	boolean editCVS(CVSet cvSet);
	
	public CVSet getCVSetById(Long id);
	
	public int updateDistribute(List<Object> params);
	
	boolean updateUnit_ref_Quality(long id, List<QualityModel> qm_list);
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @param    CVSet
	 * @return   List
	 * 方法说明： 根据用户id查询所属专委会对应的学科信息
	 */
	List<PropUnit> getPropByUserId(Long userId);
	
	public CVSet getCVSetByCode(String code);
	
	boolean bindCardTypeByCVSet(CVSet cvSet);//YHQ，查询项目是否绑定学习卡类型，2017-03-18
	public List<CVSet> cvSetListByBindCardType(CVSet queryCVSet);//YHQ，查询绑定学习卡类型的项目，2017-03-29
	public List<CVSet> cvSetListByUnBindCardType(CVSet queryCVSet);//YHQ，查询未绑定学习卡类型的项目，2017-03-29
	
	public int getFaceteachCount(Long cvsetId);
	public void getCVSetListByPage(PageList pl, CVSet queryCVSet);
	
	/**
	 * 项目授权中显示的项目列表学科是重复的，但是dao层被另一个地方引用着，所以再复制一份，修改
	 * @param queryCVSet
	 * @return
	 */
	public List<CVSet> getCVSetListDuplicateRemove(CVSet queryCVSet);
	
	public List<PropUnit> getAreaByCode(String propIds);
	
	/**
	 * @author   taoliang
	 * @return   CVSet 
	 * @param    Long
	 * 方法说明      根据课程Id查询项目信息
	 */
	public CVSet queryCVSetListByCvId(Long cvId);
	
	public List<PropUnit> getAreaForAuthor(String propIds);
}
