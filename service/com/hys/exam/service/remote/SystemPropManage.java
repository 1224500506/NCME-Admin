package com.hys.exam.service.remote;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemIndustryAbility;

/**
* <p>Description: 系统属性</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午10:04:03
*/
public interface SystemPropManage {
	//=================行业
	
	//根据上级id得到下级行业
	public List<SystemIndustry> getSystemIndustryList(Long parentId);
	
	//得到本身对象
	public SystemIndustry getSystemIndustryById(Long id);
	
	//得到下级个数
	public Long getSubCount(Long parentId);
	
	//delete
	public int deleteSystemIndustry(Long id);
	
	//save
	public int saveSystemIndustry(Long id, Long parentId, String industryName, Long level, Integer seq);
	
	//=====================行业能力
	//得到能力列表
	public List<SystemIndustryAbility> getSystemIndustryAbilityList(Long parentId);
	//得到能力
	public SystemIndustryAbility getSystemIndustryAbilityById(Long id);
	//得到能力下的课程数
	public Long getCountAbilityCourseByAbilityId(Long abilityId);
	//save
	public int saveSystemIndustryAbility(Long id, Long industryId, String abilityName);
	//delete
	public int deleteSystemIndustryAbility(Long id);
	
	//=====================能力课程
	//得到能力下的课程列表
	public List<StudyCourseVO> getStudyCourseListByAbilityId(Long abilityId, String courseName, String teacher);
	public void getStudyCourseListByAbilityId(Page<StudyCourseVO> page, Long abilityId, String courseName, String teacher);
	//得到课程
	public StudyCourse getStudyCourseById(Long id);
	//能力所没有关联的课程列表
	public void getStudyCourseListBesidesAbilityId(Page<StudyCourseVO> page, Long abilityId, String courseName, String teacher);
	//save
	public int saveSystemAbilityCourse(Long abilityId, List<StudyCourseVO> list);
	//delete
	public int deleteSystemAbilityCourse(Long abilityId, Long [] courseIds);
















































	/**
	 * 查询属性列表
	 * 
	 * @param type
	 * @return
	 */
	public List<ExamPropVal> getExamPropValList(int type);
}


