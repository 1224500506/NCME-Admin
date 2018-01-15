package com.hys.exam.service.local;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.CvsetQualityHistory;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.Qualify;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface CVSetManage extends BaseService {
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
	
	Long addCVSet(CVSet cvSet);
	
	boolean updateCVSet(CVSet cvSet);
	
	boolean deleteCVSet(CVSet cvSet);
	
	boolean editCVS(CVSet cvSet);
	
	public CVSet getCVSetById(Long Id);
	
	public int updateDistribute(List<Object> saveParams);

	boolean updateUnit_ref_Quality(long id, List<QualityModel> qm_list);
	
	List<Qualify> getQualifyList(Qualify qualify);
	//上传至对象存储
	public String saveObject(FormFile file,HttpServletRequest request, HttpServletResponse response)throws Exception;

	void getCVSetPageList(PageList pl, CVSet queryCVSet);
	
	//可以审核的项目列表  chenlb add
	public List<CVSet> getCVSetListForQualify(CVSet queryCVSet, SystemUser currentUser);
	
	//添加审核记录	chenlb add
	public int saveCvsetQualityHistory(CvsetQualityHistory history);
	
	//根据项目和专家得到其审核历史记录 chenlb add
	public CvsetQualityHistory getCvsetQualityHistoryByCvsetAndExpert(Long cvsetId, Long expertId);
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @param    CVSet
	 * @return   List
	 * 方法说明： 根据用户id查询所属专委会对应的学科信息
	 */
	public List<PropUnit> getPropByUserId(Long userId);
	
	public CVSet getCVSetByCode(String code);

	public List<CvsetQualityHistory> getCvsetQualityHistoryByCvsetAndExpertInfo(Long cvsetId);
	
	boolean bindCardTypeByCVSet(CVSet cvSet);//YHQ，查询项目是否绑定学习卡类型，2017-03-18
	public List<CVSet> cvSetListByBindCardType(CVSet queryCVSet);//YHQ，查询绑定学习卡类型的项目，2017-03-29
	public List<CVSet> cvSetListByUnBindCardType(CVSet queryCVSet);//YHQ，查询未绑定学习卡类型的项目，2017-03-29
	
	/**
	 * @author taoliang
	 * @param cvsetId
	 * @return
	 * @desc 统计当前项目的总面授人数
	 */
	public int getFaceteachCount(Long cvsetId);
	public void getCVSetListByPage(PageList pl, CVSet queryCVSet);
	
	/**
	 * 项目授权中显示的项目列表学科是重复的，但是dao层被另一个地方引用着，所以再复制一份，修改
	 * @param queryCVSet
	 * @return
	 */
	List<CVSet> getCVSetListDuplicateRemove(CVSet queryCVSet);
	
	/**
	 * @author   taoliang
	 * @return   CVSet 
	 * @param    Long
	 * 方法说明      根据课程Id查询项目信息
	 */
	public CVSet queryCVSetListByCvId(Long cvId);
	
	public List<PropUnit> getAreaByCode(String propIds);
	
	/**
	 * @author taoLiang
	 * @param propIds
	 * @return
	 * @desc 新授权地区查找
	 */
	public List<PropUnit> getAreaForAuthor(String propIds);
}
