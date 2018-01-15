package com.hys.exam.service.remote.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.local.SystemPropDAO;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemAbilityCourse;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemIndustryAbility;
import com.hys.exam.service.remote.SystemPropManage;

/**
*
* <p>Description: 系统属性</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午10:05:18
*/
public class SystemPropManageImpl implements SystemPropManage {

	private SystemPropDAO systemPropDAO;
	
	@Override
	public List<SystemIndustry> getSystemIndustryList(Long parentId) {
		return systemPropDAO.getSystemIndustryList(parentId);
	}
	
	//得到本身对象
	@Override
	public SystemIndustry getSystemIndustryById(Long id){
		return systemPropDAO.getSystemIndustryById(id);
	}
	
	//得到下级个数
	@Override
	public Long getSubCount(Long parentId){
		return systemPropDAO.getSubCount(parentId);
	}
	
	//delete
	@Override
	public int deleteSystemIndustry(Long id){
		return systemPropDAO.deleteSystemIndustry(id);
	}
	
	//save
	@Override
	public int saveSystemIndustry(Long id, Long parentId, String industryName, Long level, Integer seq){
		return systemPropDAO.saveSystemIndustry(id, parentId, industryName,level, seq);
	}
	
	//===================能力
	//得到能力列表
	public List<SystemIndustryAbility> getSystemIndustryAbilityList(Long parentId){
		List<SystemIndustryAbility> list = systemPropDAO.getSystemIndustryAbilityList(parentId); 
		if(!Utils.isListEmpty(list))
			return list;
		return null;
	}
	//得到能力
	public SystemIndustryAbility getSystemIndustryAbilityById(Long id){
		SystemIndustryAbility sa = systemPropDAO.getSystemIndustryAbilityById(id);
		if(null != sa)
			return sa;
		return null;
	}
	//得到能力下的课程数
	public Long getCountAbilityCourseByAbilityId(Long abilityId){
		return systemPropDAO.getCountAbilityCourseByAbilityId(abilityId);
	}
	//save
	public int saveSystemIndustryAbility(Long id, Long industryId, String abilityName){
		return systemPropDAO.saveSystemIndustryAbility(id, industryId, abilityName);
	}
	//delete
	public int deleteSystemIndustryAbility(Long id){
		return systemPropDAO.deleteSystemIndustryAbility(id);
	}
	
	//=====================能力课程
	//得到能力下的课程列表
	public List<StudyCourseVO> getStudyCourseListByAbilityId(Long abilityId, String courseName, String teacher){
		List<StudyCourseVO> list = systemPropDAO.getStudyCourseListByAbilityId(abilityId, courseName, teacher);
		if(!Utils.isListEmpty(list))
			return list;
		return null;
	}
	
	//同上  分页
	public void getStudyCourseListByAbilityId(Page<StudyCourseVO> page, Long abilityId, String courseName, String teacher){
		this.systemPropDAO.getStudyCourseListByAbilityId(page, abilityId, courseName, teacher);
	}
	
	//能力所没有关联的课程列表
	public void getStudyCourseListBesidesAbilityId(Page<StudyCourseVO> page, Long abilityId, String courseName, String teacher){
		this.systemPropDAO.getStudyCourseListBesidesAbilityId(page, abilityId, courseName, teacher);
	}
	
	//得到课程
	public StudyCourse getStudyCourseById(Long id){
		StudyCourse sc = systemPropDAO.getStudyCourseById(id);
		if(null != sc)
			return sc;
		return null;
	}
	//save
	public int saveSystemAbilityCourse(Long abilityId, List<StudyCourseVO> courseList){
		int rows = 0;
		if(!Utils.isListEmpty(courseList)){
			//before save, must delete the same records
			//systemPropDAO.deleteSystemAbilityCourse(abilityId);
			for(StudyCourseVO vo : courseList){
				//是否已经存在
				List<SystemAbilityCourse> list = this.systemPropDAO.getSystemAbilityCourseList(abilityId, vo.getId());
				if(Utils.isListEmpty(list)){
						String creditType = vo.getCreditType();
						Double creditNum = vo.getCreditNum();
						Integer isObligatory = 0;	//必修
						rows += systemPropDAO.saveSystemAbilityCourse(abilityId, vo.getId(), creditType, creditNum, isObligatory);
				}
			}
		}
		return rows;
	}
	//delete
	public int deleteSystemAbilityCourse(Long abilityId, Long [] courseIds){
		int rows = 0;
		if(null != courseIds && courseIds.length >0){
			for(Long courseId : courseIds){
				rows = systemPropDAO.deleteSystemAbilityCourse(abilityId, courseId);
			}
		}
		return rows; 
	}
	
	

	public SystemPropDAO getSystemPropDAO() {
		return systemPropDAO;
	}

	public void setSystemPropDAO(SystemPropDAO systemPropDAO) {
		this.systemPropDAO = systemPropDAO;
	}

	
	
	@Override
	public List<ExamPropVal> getExamPropValList(int type) {
		return systemPropDAO.getExamPropValList(type);
	}
}

