package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.model.SystemAccount;
import com.hys.framework.service.BaseService;

public interface CvLiveManage extends BaseService {
	/**
	 * @author taoliang
	 * @param cvId
	 * @return
	 */
	List<CvLive> queryCvLiveList(Long cvId);
	/**
	  * @author taoliang
	  * @return
	  * @desc 添加直播回放课件信息
	  */
	 Long addCvLiveCourseware(CvLiveCourseware record);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @return
	  * @desc 获取课件信息
	  */
	 List<CvLiveCourseware> getCvLiveCoursewareList(CvLiveCourseware record);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @return
	  * @desc 修改课件信息表
	  */
	 int updateCvLiveCoutsewareInfo(CvLiveCourseware record);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @return
	  * @desc 添加直播课件和单元表关系
	  */
	 Long addCvLiveRefUnit(CvLiveRefUnit record);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @return
	  */
	 List<CvLiveRefUnit> getCvLiveRefUnitList(CvLiveRefUnit record);
	 
	 /**
	  * @author taoliang
	  * @param classNo
	  * @return
	  * @desc 获取观看过直播课程的用户
	  */
	 public List<SystemAccount> getViewLiveUser(String classNo);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @desc 查询直播间中间表信息
	  */
	 List<CvLiveStudyRef> queryCvLiveStudyRef(CvLiveStudyRef record);
}
