package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.SystemPropDAO;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemAbilityCourse;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemIndustryAbility;
import com.hys.exam.utils.PageUtil;

/**
*
* <p>Description: 系统属性</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午9:50:12
*/
public class SystemPropJDBCDAO extends AbstractJDBCDAO implements SystemPropDAO {

	final static private String getSystemIndustry_SQL = "select t.* from system_industry t " +
			" where t.parent_id = ? and t.status = ? order by t.seq";
	
	final static private String getSystemIndustryById_SQL = "select * from system_industry t where t.id = ?";
	
	//根据上级id得到下级列表
	@Override
	public List<SystemIndustry> getSystemIndustryList(Long parentId) {
		List<SystemIndustry> list = getList(getSystemIndustry_SQL, 
				ParameterizedBeanPropertyRowMapper.newInstance(SystemIndustry.class), 
				parentId, Constants.SYSTEM_NORMAL_STATUS);
		return list;
	}
	
	//得到本身对象
	@Override
	public SystemIndustry getSystemIndustryById(Long id){
		return this.getJdbcTemplate().queryForObject(getSystemIndustryById_SQL, 
				ParameterizedBeanPropertyRowMapper.newInstance(SystemIndustry.class), id);
	}
	
	//得到下级个数
	@Override
	public Long getSubCount(Long parentId){
		return this.getJdbcTemplate().queryForLong("select count(*) count from system_industry t where t.parent_id = ? and t.status = ?", 
				parentId, Constants.SYSTEM_NORMAL_STATUS);
	}
	
	//delete
	@Override
	public int deleteSystemIndustry(Long id){
		return this.getJdbcTemplate().update("update system_industry t set t.status = ? where t.id = ?", Constants.SYSTEM_DELETE_STATUS, id);
	}
	
	//save
	@Override
	public int saveSystemIndustry(Long id, Long parentId, String industryName, Long level, Integer seq){
		String sql = "";
		if(null != id && id>0){
			sql = "update system_industry t set t.industry_name = ?, seq = ? where t.id = ?";
			return this.getJdbcTemplate().update(sql, industryName, seq,  id);
		}else{
			sql = "insert into system_industry(id, parent_id, industry_name, status, seq)" +
					" values (null, ?,?,?,?)";
			return this.getJdbcTemplate().update(sql, level == 1?Constants.SYSTEM_INDUSTRY_ROOT_ID:parentId,
					industryName, Constants.SYSTEM_NORMAL_STATUS, seq);
		}
	}
	
	//===================能力
	//得到能力列表
	@Override
	public List<SystemIndustryAbility> getSystemIndustryAbilityList(Long parentId){
		String sql = "select * from system_industry_ability t where t.status = ?";
		List<Object> obj = new ArrayList<Object>();
		obj.add(Constants.SYSTEM_NORMAL_STATUS);
		if(null != parentId && parentId >0){
			sql += " and t.industry_id = ? ";
			obj.add(parentId);
		}
		List<SystemIndustryAbility> list = this.getList(sql, obj, 
				ParameterizedBeanPropertyRowMapper.newInstance(SystemIndustryAbility.class));
		return list;
	}
	//得到能力
	@Override
	public SystemIndustryAbility getSystemIndustryAbilityById(Long id){
		List<SystemIndustryAbility> list = this.getList("select * from system_industry_ability t where t.id = ?", 
				ParameterizedBeanPropertyRowMapper.newInstance(SystemIndustryAbility.class), id);
		if(!Utils.isListEmpty(list))
			return list.get(0);
		return null;
	}
	//得到能力下的课程数
	@Override
	public Long getCountAbilityCourseByAbilityId(Long abilityId){
		String sql = " select count(*) count from system_ability_course t where t.ability_id = ?";
		int count = this.getJdbcTemplate().queryForInt(sql, abilityId);
		if(count >0)
			return new Long(count);
		return 0L;
	}
	//save
	@Override
	public int saveSystemIndustryAbility(Long id, Long industryId, String abilityName){
		int row = 0;
		if(null != id && id>0){
			String sql = "update system_industry_ability t set t.ability_name = ? where t.id = ?";
			row = this.getJdbcTemplate().update(sql, abilityName, id);
		}else{
			String sql = "insert into system_industry_ability (id, industry_id, ability_name, status) values (null,?,?,?)";
			row = this.getJdbcTemplate().update(sql, industryId, abilityName, Constants.SYSTEM_NORMAL_STATUS);
		}
		return row;
	}
	//delete
	@Override
	public int deleteSystemIndustryAbility(Long id){
		return this.getJdbcTemplate().update("update system_industry_ability t set t.status = ? where t.id = ?",Constants.SYSTEM_DELETE_STATUS, id);
	}
	
	//=====================能力课程
	//得到能力下的课程列表
	@Override
	public List<StudyCourseVO> getStudyCourseListByAbilityId(Long abilityId, String courseName, String teacher){
		String sql = "select t1.credit_type, t1.credit_num, t1.is_obligatory, t2.*, t3.credit_name" +
				" from system_ability_course t1 join study_course t2 on t1.course_id = t2.id" +
				" left join system_credit_type t3 on t1.credit_type = t3.credit_type" +
				"  where t1.ability_id = ? and t2.status = 1 ";
		List<Object> pl = new ArrayList<Object>();
		pl.add(abilityId);
		if(!"".equals(courseName)){
			sql += " and t2.study_course_name like ?";
			pl.add("%"+ courseName.trim()+"%");
		}
		if(!"".equals(teacher)){
			sql += " and t2.teacher like ?";
			pl.add("%" + teacher.trim() + "%");
		}
		List<StudyCourseVO> list = this.getList(sql, pl,  ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseVO.class));
		if(!Utils.isListEmpty(list))
			return list;
		return null;
	}
	
	//同上  分页
	public void getStudyCourseListByAbilityId(Page<StudyCourseVO> page, Long abilityId, String courseName, String teacher){
		String sql = "select t1.credit_type, t1.credit_num, t1.is_obligatory, t2.*, t3.credit_name" +
				" from system_ability_course t1 join study_course t2 on t1.course_id = t2.id" +
				" left join system_credit_type t3 on t1.credit_type = t3.credit_type" +
				"  where t1.ability_id = ? ";
		List<Object> pl = new ArrayList<Object>();
		pl.add(abilityId);
		
		if(!"".equals(courseName)){
			sql += " and t2.study_course_name like ?";
			pl.add("%"+ courseName.trim()+"%");
		}
		if(!"".equals(teacher)){
			sql += " and t2.teacher like ?";
			pl.add("%" + teacher.trim() + "%");
		}
		
		sql+=" and t2.status = 1";
		
		// 查询结果集
		List<StudyCourseVO> list = getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseVO.class));

		// //取得总记录数
		Integer totalCount = getCount(sql, pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}
	
	//能力所没有关联的课程列表
	public void getStudyCourseListBesidesAbilityId(Page<StudyCourseVO> page, Long abilityId, String courseName, String teacher){
		//String sql = "select * from study_course t where t.status = ? and t.id not in " +
		//		" (select sac.course_id from system_ability_course sac where sac.ability_id = ?)";
		
		String sql = "select t2.*, t.credit_num, t.credit_type from system_course_credit t" +
				" left join study_course t2 on t.course_id = t2.id" +
				" left join system_org t4 on t4.id = t.org_id" +
				" left join study_course_type t5 on t5.id = t.course_type" +
				" where t.course_type = ? and t.course_id not in " +
				" (select c.course_id from system_ability_course c where c.ability_id = ?)" +
				" and t.credit_year = ? and t.site_id = ? and t2.status = 1 ";
		
		List<Object> pl = new ArrayList<Object>();
		pl.add(Constants.AJPX_SYSTEM_COURSE_TYPE_POSITION);
		pl.add(abilityId);
		pl.add(DateUtil.getYear());
		////pl.add(Constants.AJPX_ADMIN_ORG_BJ_ID);		//北京
		pl.add(Constants.ANQUAN100_SITE_ID);			//www.anquan100.com 站点Id
		
		if(!"".equals(courseName)){
			sql += " and t2.study_course_name like ?";
			pl.add("%"+ courseName.trim()+"%");
		}
		if(!"".equals(teacher)){
			sql += " and t2.teacher like ?";
			pl.add("%" + teacher.trim() + "%");
		}
		
		sql += " order by t.credit_date desc ";
		 
		// 查询结果集
		List<StudyCourseVO> list = getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseVO.class));

		// //取得总记录数
		Integer totalCount = getCount(sql, pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}
	
	//得到能力课程列表
	@Override
	public List<SystemAbilityCourse> getSystemAbilityCourseList(Long abilityId, Long courseId){
		String sql = "select tt.* from system_ability_course tt where tt.ability_id = ? and tt.course_id = ?";
		return this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemAbilityCourse.class), abilityId, courseId);
	}
	
	//得到课程授权
	@Override
	public StudyCourseVO getStudyCourseCredit(Long courseId){
		/*String sql = "select t.course_id, t2.credit_type, t2.credit_num  from system_ability_course t" +
				" left join system_course_credit t2 on t.course_id = t2.course_id" +
				" where  t.course_id = ?";*/
		String sql = "select t.id, t2.credit_type, t2.credit_num  from study_course t" +
				" left join system_course_credit t2 on t.id = t2.course_id where  t.id = ?";
		List<StudyCourseVO> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseVO.class), courseId);
		if(!Utils.isListEmpty(list))
			return list.get(0);
		return null;
	}
	
	//得到课程
	@Override
	public StudyCourse getStudyCourseById(Long id){
		String sql = "select * from study_course t where t.id = ? ";
		List<StudyCourse> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyCourse.class), id);
		if(!Utils.isListEmpty(list))
			return list.get(0);
		return null;
	}
	//save
	@Override
	public int saveSystemAbilityCourse(Long abilityId, Long courseId, String creditType, Double creditNum, Integer isObligatory){
		String sql = "insert into system_ability_course(ability_id, course_id, credit_type, credit_num, is_obligatory) values (?,?,?,?,?)";
		return this.getJdbcTemplate().update(sql, abilityId, courseId, creditType, creditNum, isObligatory);
	}
	//delete
	@Override
	public int deleteSystemAbilityCourse(Long abilityId, Long courseId){
		String sql = "delete system_ability_course t where t.ability_id = ? and t.course_id = ?";
		return this.getJdbcTemplate().update(sql, abilityId, courseId);
	}

	//delete
	@Override
	public int deleteSystemAbilityCourse(Long abilityId){
		String sql = "delete system_ability_course t where t.ability_id = ? ";
		return this.getJdbcTemplate().update(sql, abilityId);
	}

	
	final static private String getExamPropValList_SQL = "select * from EXAM_PROP_VAL t where t.type=? order by t.id";

	@Override
	public List<ExamPropVal> getExamPropValList(int type) {
		return getList(getExamPropValList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPropVal.class), type);
	}
}


