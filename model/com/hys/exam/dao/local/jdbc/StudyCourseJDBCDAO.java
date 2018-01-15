package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.StudyCourseDAO;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseware;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程信息 JdbcDao
 * 
 * 说明:
 */
public class StudyCourseJDBCDAO extends AbstractJDBCDAO implements StudyCourseDAO {
	
	//查询课程分类下的课程信息
	/*final static private String getStudyCourseList_SQL = "select t.*, "
			+ "(select count(1) from study_course x "
			+ "join study_course_type_course x1 on x.id = x1.study_course_id "
			+ "join study_course_element x2 on x2.id = x1.study_course_id "
			+ "where x2.status = ? and x2.course_element_type < 11 "
			+ "and t.id = x.id and x1.study_course_type_id = t1.study_course_type_id) coursePractice, "
			
			+ "(select count(1) from study_course x "
			+ "join study_course_type_course x1 on x.id = x1.study_course_id "
			+ "join study_course_element x2 on x2.id = x1.study_course_id "
			+ "where x2.status = ? and x2.course_element_type >= 11 "
			+ "and t.id = x.id and x1.study_course_type_id = t1.study_course_type_id) courseKnowledge "
			
		+ "from study_course t "
		+ "join study_course_type_course t1 on t1.study_course_id = t.id "
		+ "join study_course_type t2 on t2.id = t1.study_course_type_id "
		+ "where t2.status = ? and t2.id = ? " ;*/
	
	//查询所有的课程信息
	final static private String getStudyCourseList_SQL = "select t.*, "
			+ "(select count(1) from study_course x "
			+ "join study_course_element x2 on x2.course_id = x.id "
			+ "where x2.status = ? and x2.course_element_type < 11 "
			+ "and t.id = x.id ) coursePractice, "
			
			+ "(select count(1) from study_course x "
			+ "join study_course_element x2 on x2.course_id = x.id "
			+ "where x2.status = ? and x2.course_element_type >= 11 "
			+ "and t.id = x.id ) courseKnowledge "
			
		+ "from study_course t "
		+ "where 1=1 " ;
	
	//添加课程信息
	final static private String addStudyCourse_SQL = "insert into study_course ("
		+ " id, study_course_name, teacher, key_word, study_course_type, times,  summary,  description,  review, class_hours,course_number,difficulty,remark) values ("
		+ ":id, :studyCourseName, :teacher, :keyWord, :studyCourseType, :times, :summary, :description, :review, :classHours,:courseNumber,:difficulty,:remark)" ;
	
	//添加课程与课程分类关联关系
	final static private String addStudyCourseTypeCourse_SQL = "insert into study_course_type_course (study_course_type_id, study_course_id) values (?, ?)" ;
	
	//修改课程信息
	final static private String updateStudyCourse_SQL = "update study_course t set "
			+ "t.study_course_name = :studyCourseName,"
			+ "t.teacher = :teacher,"
			+ "t.key_word = :keyWord,"
			+ "t.study_course_type = :studyCourseType,"
			+ "t.times = :times,"
			+ "t.summary = :summary,"
			+ "t.description = :description,"
			+ "t.review = :review,"
			+ "t.status = :status,"
			+ "t.class_hours = :classHours,"
			+ "t.pub_date = :pubDate, "
			+ "t.difficulty = :difficulty, "
			+ "t.remark = :remark, "
			+ "t.last_update_date = sysdate(), "
			+ "t.course_img_path = :courseImgPath "
			+ "where t.id = :id " ;
	
	//根据课程ID 查询指定课程信息
	final static private String getStudyCourseById_SQL = "select t.* from study_course t where t.id = ? and t.status >= ? " ;
	
	//根据课程ID 查询课程下课件信息
	final static private String getStudyCourseElementByCourse_SQL = "select t.* from study_course_element t where t.course_id = ? and t.status = ? order by t.seq" ;
	
	//
	final static private String getCourseEleWareByElement_SQL = "select t.* "
			+ "from study_courseware t "
			+ "join study_course_element_ware t1 on t.id = t1.study_courseware_id "
			+ "where t.status = ? and t1.study_course_element_id = ? " ;
	
	@Override //查询课程分类下的课程信息
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course, String courseTypes) {
		List<Object> params = new ArrayList<Object>() ;
		params.add(Constants.STATUS_1) ;
		params.add(Constants.STATUS_1) ;
		///params.add(course.getStudyCourseTypeId()) ;
		
		StringBuilder sql = new StringBuilder() ;
		sql.append(getStudyCourseList_SQL) ;
		
		//设置课程名称
		if(course.getStudyCourseName() != null && !"".equals(course.getStudyCourseName())){
			sql.append(" and t.study_course_name like ? ") ;
			params.add("%" + course.getStudyCourseName() + "%") ;
		}

		//设置主讲老师
		if(course.getTeacher() != null && !"".equals(course.getTeacher())){
			sql.append(" and t.teacher like ? ") ;
			params.add("%" + course.getTeacher() + "%") ;
		}

		//设置关键字
		if(course.getKeyWord() != null && !"".equals(course.getKeyWord())){
			sql.append(" and t.key_word like ? ") ;
			params.add("%" + course.getKeyWord() + "%") ;
		}

		//设置类型
		/*if(course.getStudyCourseType() != null && course.getStudyCourseType() != 0){
			sql.append(" and t.study_course_type = ? ") ;
			params.add(course.getStudyCourseType()) ;
		}*/
		
		//设置类型,多选
		if(StringUtils.isNotBlank(courseTypes)){
			String [] arr = courseTypes.split(",");
			if(null != arr && arr.length>0){
				sql.append(" and (");
				for(int i=0; i<arr.length; i++){
					sql.append(" t.study_course_type = ? ") ;
					params.add(arr[i]) ;
					if(i<arr.length-1){
						sql.append(" or ");
					}
				}
				sql.append(" )");
			}
		}

		//设置状态
		if(course.getStatus() != null && course.getStatus() != -1){
			sql.append(" and t.status = ? ") ;
			params.add(course.getStatus()) ;
		}else{
			sql.append(" and t.status > ? ") ;
			params.add(Constants.STATUS_2) ;
		}

		//sql.append(" order by t.pub_date desc nulls last") ;
		sql.append(" order by t.create_date desc");
		
		//查询展示数据信息
		List<StudyCourse> curList = getJdbcTemplate().query(
				PageUtil.getPageSql(sql.toString(), page.getPageSize(), page.getPageNumber()),
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourse.class),
				params.toArray());
		
		//查询总记录数
		Integer count = getCount(sql.toString(), params.toArray()) ;
	
		page.setList(curList) ;
		page.setCount(count) ;
	}
	
	//授权课程列表
	@Override
	public void getStudyCreditCourseList(Page<StudyCourse> page, StudyCourse course, String courseTypeIds){
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder() ;
		sql.append("select * from study_course t where t.id in" +
				" (select c.course_id from system_course_credit c where c.course_type = ?)");
		params.add(course.getStudyCourseTypeId());
		
		//设置课程名称
		if(course.getStudyCourseName() != null && !"".equals(course.getStudyCourseName())){
			sql.append(" and t.study_course_name like ? ") ;
			params.add("%" + course.getStudyCourseName() + "%") ;
		}

		//设置主讲老师
		if(course.getTeacher() != null && !"".equals(course.getTeacher())){
			sql.append(" and t.teacher like ? ") ;
			params.add("%" + course.getTeacher() + "%") ;
		}

		//设置关键字
		if(course.getKeyWord() != null && !"".equals(course.getKeyWord())){
			sql.append(" and t.key_word like ? ") ;
			params.add("%" + course.getKeyWord() + "%") ;
		}
		
		//设置类型,多选
		if(StringUtils.isNotBlank(courseTypeIds)){
			String [] arr = courseTypeIds.split(",");
			if(null != arr && arr.length>0){
				sql.append(" and (");
				for(int i=0; i<arr.length; i++){
					sql.append(" t.study_course_type = ? ") ;
					params.add(arr[i]) ;
					if(i<arr.length-1){
						sql.append(" or ");
					}
				}
				sql.append(" )");
			}
		}

		//设置状态
		if(course.getStatus() != null && course.getStatus() != -1){
			sql.append(" and t.status = ? ") ;
			params.add(course.getStatus()) ;
		}else{
			sql.append(" and t.status >= ?  ") ;
			params.add(Constants.STATUS_3) ;
		}
		
		//查询展示数据信息
		List<StudyCourse> curList = getJdbcTemplate().query(
				PageUtil.getPageSql(sql.toString(), page.getPageSize(), page.getPageNumber()),
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourse.class),
				params.toArray());
		
		//查询总记录数
		Integer count = getCount(sql.toString(), params.toArray());
	
		page.setList(curList);
		page.setCount(count);
	}
	
	@Override //添加课程信息
	public int addStudyCourse(StudyCourse course) {
		course.setId(getSequence("study_course_seq")) ;
		course.setCourseNumber(DateUtil.getYear()
				+ com.hys.exam.utils.StringUtils.leftPad(String.valueOf(course.getId()), 9, '0'));

		return getNamedParameterJdbcTemplate().update(addStudyCourse_SQL, new BeanPropertySqlParameterSource(course)) ;
	}
	
	//批量更新课程状态
	@Override
	public int updateBatchStatus(Long id, Integer status){
		String sql = "update study_course t set t.status = ? where t.id = ?";
		return this.getJdbcTemplate().update(sql, status, id);
	}
	
	@Override //添加课程与课程分类关联关系
	public int addStudyCourseTypeCourse(Long curTypeId, Long courseId) {
		return getJdbcTemplate().update(addStudyCourseTypeCourse_SQL, curTypeId, courseId) ;
	}
	
	@Override  //修改课程信息
	public int updateStudyCourse(StudyCourse course) {
		return getNamedParameterJdbcTemplate().update(updateStudyCourse_SQL, new BeanPropertySqlParameterSource(course)) ;
	}
	
	@Override //修改课程信息2 StringBuilder 拼装修改
	public int updateStudyCourse2(StudyCourse course) {
		StringBuilder sql = new StringBuilder() ;
		sql.append(" update study_course t set ") ;
		
		//设置课程名称
		if(course.getStudyCourseName() != null && !"".equals(course.getStudyCourseName())){
			sql.append("t.study_course_name = :studyCourseName,") ;
		}

		//设置主讲老师
		if(course.getTeacher() != null && !"".equals(course.getTeacher())){
			sql.append("t.teacher = :teacher,") ;
		}

		//设置关键字
		if(course.getKeyWord() != null && !"".equals(course.getKeyWord())){
			sql.append("t.key_word = :keyWord,") ;
		}
		
		//设置课程类别
		if(course.getStudyCourseType() != null && course.getStudyCourseType() > 0){
			sql.append("t.study_course_type = :studyCourseType,") ;
		}

		//设置课程时长
		if(course.getTimes() != null && course.getTimes() > 0){
			sql.append("t.times = :times,") ;
		}
		
		//设置摘要
		if(course.getSummary() != null && !"".equals(course.getSummary())){
			sql.append("t.summary = :summary,") ;
		}

		//设置简述
		if(course.getDescription() != null && !"".equals(course.getDescription())){
			sql.append("t.description = :description,") ;
		}

		//设置导读
		if(course.getReview() != null && !"".equals(course.getReview())){
			sql.append("t.review = :review,") ;
		}
		
		//设置状态信息
		if(course.getStatus() != null){
			sql.append("t.status = :status,") ;
		}
		
		//设置学时
		if(course.getClassHours() != null && course.getClassHours() > 0){
			sql.append("t.class_hours = :classHours,") ;
		}
		
		sql.append(" t.last_update_date = sysdate() where t.id = :id ") ;
		
		return getNamedParameterJdbcTemplate().update(sql.toString(), new BeanPropertySqlParameterSource(course)) ;
	}
	
	@Override //根据课程ID 查询指定课程信息
	public StudyCourse getStudyCourseById(Long courseId) {
		List<StudyCourse> curList = getJdbcTemplate().query(getStudyCourseById_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourse.class),
				courseId, Constants.STATUS_3);
		
		if(Utils.isListEmpty(curList)){
			return null ;
		}
		
		return curList.get(0) ;
	}
	
	@Override  //根据课程ID 查询课程下课件信息
	public List<StudyCourseElement> getStudyCourseElementByCourse(Long courseId) {
		return getJdbcTemplate().query(getStudyCourseElementByCourse_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseElement.class),
				courseId, Constants.STATUS_1);
	}
	
	@Override //根据组件ID 查询课件信息
	public StudyCourseware getCourseEleWareByElement(Long elementId) {
		List<StudyCourseware> curList = getJdbcTemplate().query(getCourseEleWareByElement_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseware.class),
				Constants.STATUS_1, elementId);
		
		if(Utils.isListEmpty(curList)){
			return null ;
		}
		
		return curList.get(0) ;		
	}
}