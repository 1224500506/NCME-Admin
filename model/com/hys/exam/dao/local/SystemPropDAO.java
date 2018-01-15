package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemAbilityCourse;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemIndustryAbility;

/**
*
* <p>Description: 系统属性</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午9:50:12
*/
public interface SystemPropDAO {

	//===================行业
	public List<SystemIndustry> getSystemIndustryList(Long parentId);
	
	//得到本身对象
	public SystemIndustry getSystemIndustryById(Long id);
	
	//得到下级个数
	public Long getSubCount(Long parentId);
	
	//delete
	public int deleteSystemIndustry(Long id);
	
	//save
	public int saveSystemIndustry(Long id, Long parentId, String industryName, Long level, Integer seq);
	
	//===================能力
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
	
	//能力所没有关联的课程列表
	public void getStudyCourseListBesidesAbilityId(Page<StudyCourseVO> page, Long abilityId, String courseName, String teacher);
	
	//得到能力课程
	public List<SystemAbilityCourse> getSystemAbilityCourseList(Long abilityId, Long courseId);
	
	//得到课程授权
	public StudyCourseVO getStudyCourseCredit(Long courseId);
	
	//得到课程
	public StudyCourse getStudyCourseById(Long id);
	//save
	public int saveSystemAbilityCourse(Long abilityId, Long courseId, String creditType, Double creditNum, Integer isObligatory);
	//delete
	public int deleteSystemAbilityCourse(Long abilityId, Long courseId);
	//delete
	public int deleteSystemAbilityCourse(Long abilityId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询属性列表
	 * 
	 * @param type
	 * @return
	 */
	public List<ExamPropVal> getExamPropValList(int type);
}


