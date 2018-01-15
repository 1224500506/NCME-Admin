package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.StudyCourseTypeDAO;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.model.StudyCourseTypeCourse;


/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-26
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseTypeJDBCDAO extends AbstractJDBCDAO implements StudyCourseTypeDAO {

	//查询课程分类信息
	final static private String getCourseTypeByParent_SQL = "select t.* from study_course_type t where t.status = ? and t.parent_course_type_id = ? order by t.id" ;

	final static private String getCourseTypeByParent_SQL_2 = "select t.*,(select count(1) from study_course_type_course t2,study_course t3 where t2.study_course_type_id=t.id and t2.study_course_id=t3.id and t3.status=?) as studyCourse from study_course_type t where t.status = ? and t.parent_course_type_id = ?";

	//添加课程分类信息
	final static private String addStudyCourseType_SQL = "insert into study_course_type ("
		+ " id, course_type_name, parent_course_type_id, seq, all_class_hours, type, examine_require, train_principles,  introduction, status,  create_date, last_update_date,is_last_level,last_update_user_id,create_user_id,subject_id,subject_name,subject2_id,subject2_name,guide,exp_id,key_guide,prac_guide) values ("
		+ ":id,  :courseTypeName,  :parentCourseTypeId, :seq, :allClassHours, :type, :examineRequire, :trainPrinciples, :introduction, :status, sysdate(), sysdate(), :isLastLevel,:lastUpdateUserId,:createUserId,:subjectId,:subjectName,:subject2Id,:subject2Name,:guide,:expId,:keyGuide,:pracGuide)" ;

	final static private String getStudyCourseTypeListById_SQL = "select * from study_course_type t where t.status=? start with id=? connect by prior parent_course_type_id = id order by t.seq,id";

	final static private String updateStudyCourseTypeLastLevel_SQL = "update study_course_type t set t.is_last_level=? where t.id=?";

	final static private String getStudyCourseTypeById_SQL = "select id, course_type_name, parent_course_type_id, seq, all_class_hours, type, examine_require, train_principles, introduction, status, create_date, last_update_date, is_last_level, last_update_user_id, create_user_id, is_free, layer, subject_id, subject_name, subject2_id as subject2Id, subject2_name as subject2Name, guide, exp_id, key_guide, prac_guide from study_course_type t where t.status=? and t.id=?";

	final static private String deleteStudyCourseType_SQL = "update study_course_type t set t.last_update_date=sysdate(),t.last_update_user_id=?,t.status=? where t.id=?";

	final static private String getStudyCourseList_SQL = "select t.*, "
			+ "(select count(1) from study_course x "
			+ "join study_course_type_course x1 on x.id = x1.study_course_id "
			+ "join study_course_element x2 on x2.id = x1.study_course_id "
			+ "where x2.status = ? and x2.course_element_type < 11 "
			+ "and t.id = x.id) coursePractice, "

			+ "(select count(1) from study_course x "
			+ "join study_course_type_course x1 on x.id = x1.study_course_id "
			+ "join study_course_element x2 on x2.id = x1.study_course_id "
			+ "where x2.status = ? and x2.course_element_type >= 11 "
			+ "and t.id = x.id) courseKnowledge "
			+ "from study_course t where t.status >= ?  ";

	final static private String addStudyCourseTypeCourse_SQL = "insert into study_course_type_course (study_course_type_id, study_course_id) values (:studyCourseTypeId, :studyCourseId)";

	//取得当前类别下 子类别列表的最大Seq
	final static private String getCurTypeMaxSeq_SQL = "select max(t.seq) from study_course_type t where t.parent_course_type_id = ? and t.status = ?" ;
	
	//批量修改课程分类信息
	final static private String updateCurTypeBatch_SQL = "update study_course_type set "
		+ "course_type_name      = :courseTypeName,"
		+ "parent_course_type_id = :parentCourseTypeId,"
		+ "seq                   = :seq,"
		+ "all_class_hours       = :allClassHours,"
		+ "type                  = :type,"
		+ "examine_require       = :examineRequire,"
		+ "train_principles      = :trainPrinciples,"
		+ "introduction          = :introduction,"
		+ "status                = :status,"
		+ "is_free               = :isFree,"
		+ "last_update_date      = sysdate(),"
		+ "last_update_user_id   = :lastUpdateUserId "
		+ "where id = :id" ;
	
	//根据分类ID 查询所有子分类信息
	final static private String getAllChildCurTypeByTypeId_SQL = "select t.* "
		+ "from study_course_type t "
		+ "where t.status = ? "
		+ "start with t.parent_course_type_id = ? connect by prior t.id = t.parent_course_type_id "
		+ "order siblings by t.id" ;
	
	@Override //查询课程分类信息
	public List<StudyCourseType> getCourseTypeByParent(Long parentId) {
		return getJdbcTemplate().query(getCourseTypeByParent_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseType.class),
				Constants.STATUS_1, parentId);
	}

	@Override //添加课程分类信息
	public int addStudyCourseType(StudyCourseType courseType) {
		courseType.setId(getSequence("STUDY_COURSE_TYPE_SEQ")) ;
		return getNamedParameterJdbcTemplate().update(addStudyCourseType_SQL,
				new BeanPropertySqlParameterSource(courseType));
	}

	@Override //修改课程分类
	public int updateStudyCourseType(StudyCourseType courseType) {
		StringBuilder sql = new StringBuilder() ;
		sql.append(" update study_course_type t set ") ;

		//设置课程分类名称
		if(courseType.getCourseTypeName() != null && !"".equals(courseType.getCourseTypeName())){
			sql.append(" t.course_type_name = :courseTypeName, ") ;
		}

		//设置课程分类父ID
		if(courseType.getParentCourseTypeId() != null && courseType.getParentCourseTypeId() > 0){
			sql.append(" t.parent_course_type_id = :parentCourseTypeId, ") ;
		}

		//设置课程分类序列
		if(courseType.getSeq() != null && courseType.getSeq() > 0){
			sql.append(" t.seq = :seq, ") ;
		}

		//设置课程分类总学时
		if(courseType.getAllClassHours() != null && courseType.getAllClassHours() > 0){
			sql.append(" t.all_class_hours = :allClassHours, ") ;
		}

		//设置课程分类类型
		if(courseType.getType() != null && courseType.getType() > 0){
			sql.append(" t.type = :type, ") ;
		}
		
		//设置课程分类考核要求
		if(courseType.getExamineRequire() != null && !"".equals(courseType.getExamineRequire())){
			sql.append(" t.examine_require = :examineRequire, ") ;
		}

		//设置课程分类培训大纲
		if(courseType.getTrainPrinciples() != null && !"".equals(courseType.getTrainPrinciples())){
			sql.append(" t.train_principles = :trainPrinciples, ") ;
		}

		//设置课程分类介绍
		if(courseType.getIntroduction() != null && !"".equals(courseType.getIntroduction())){
			sql.append(" t.introduction = :introduction, ") ;
		}
		
		//设置课程分类类型
		if(courseType.getStatus() != null){
			sql.append(" t.status = :status, ") ;
		}

		//设置课程分类类型
		if(courseType.getLastUpdateUserId() != null){
			sql.append(" t.last_update_user_id = :lastUpdateUserId, ") ;
		}

		//三级学科I D
		if(courseType.getSubjectId() != null){
			sql.append(" t.subject_id = :subjectId, ") ;
		}

		//三级学科名称
		if(courseType.getSubjectName() != null){
			sql.append(" t.subject_name = :subjectName, ") ;
		}

		//二级学科代码
		if(courseType.getSubject2Id() != null){
			sql.append(" t.subject2_id = :subject2Id, ") ;
		}

		//二级学科名称
		if(courseType.getSubject2Name() != null){
			sql.append(" t.subject2_name = :subject2Name, ") ;
		}

		//学习建议
		if(courseType.getGuide() != null){
			sql.append(" t.guide = :guide, ") ;
		}

		//推荐专家ID
		if(courseType.getExpId() != null){
			sql.append(" t.expId = :expId, ") ;
		}

		//关键点导读
		if(courseType.getKeyGuide() != null){
			sql.append(" t.key_guide = :keyGuide, ") ;
		}

		//虚拟实践导读
		if(courseType.getPracGuide() != null){
			sql.append(" t.prac_guide = :pracGuide, ") ;
		}
		
		sql.append(" t.last_update_date = sysdate() where id = :id ") ;
		
		return getNamedParameterJdbcTemplate().update(sql.toString(),
				new BeanPropertySqlParameterSource(courseType));
	}

	@Override
	public void getCourseTypeByParent(
			Page<StudyCourseType> page, Long parentId, StudyCourseType query) {

		List<Object> list = new ArrayList<Object>();
		list.add(Constants.STATUS_1);
		list.add(Constants.STATUS_1);
		list.add(parentId);

		StringBuilder sql = new StringBuilder();
		sql.append(getCourseTypeByParent_SQL_2);

		if (query.getCourseTypeName() != null && !"".equals(query.getCourseTypeName())) {
			sql.append(" and course_type_name like ? ");
			list.add("%" + query.getCourseTypeName() + "%");
		}

		sql.append(" order by t.seq,id ");

		// 查询结果集
		List<StudyCourseType> StudyCourseTypeList = getList(
				PageUtil.getPageSql(sql.toString(), page.getPageSize(), page.getCurrentPage()),
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseType.class));

		//取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(StudyCourseTypeList);
		page.setCount(totalCount);
	}

	@Override
	public List<StudyCourseType> getStudyCourseTypeListById(Long id) {
		return getJdbcTemplate().query(
				getStudyCourseTypeListById_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseType.class),
				Constants.STATUS_1,
				id);
	}

	@Override
	public int updateStudyCourseTypeLastLevel(Long id, int status) {
		return getJdbcTemplate().update(updateStudyCourseTypeLastLevel_SQL, status, id);
	}

	@Override
	public StudyCourseType getStudyCourseTypeById(Long id) {
		List<StudyCourseType> list = getJdbcTemplate().query(
				getStudyCourseTypeById_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseType.class),
				Constants.STATUS_1, 
				id);

		if(!list.isEmpty()){
			return list.get(0);
		}

		return null;
	}

	@Override
	public void deleteStudyCourseType(StudyCourseType s) {
		getJdbcTemplate().update(deleteStudyCourseType_SQL,
				s.getLastUpdateUserId(), Constants.STATUS_2, s.getId());
	}

	@Override
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course) {
		List<Object> params = new ArrayList<Object>() ;

		StringBuilder sql = new StringBuilder();
		sql.append(getStudyCourseList_SQL);
		params.add(Constants.STATUS_1);
		params.add(Constants.STATUS_1);
		params.add(Constants.STATUS_3);

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
		if(course.getStudyCourseType() != null && course.getStudyCourseType() != 0){
			sql.append(" and t.study_course_type = ? ") ;
			params.add(course.getStudyCourseType()) ;
		}

		sql.append("and not exists (select 1 from study_course_type_course t2 where t2.study_course_id = t.id and t2.study_course_type_id = ?) order by t.id desc");
		params.add(course.getStudyCourseTypeId());

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

	@Override
	public void addStudyCourseTypeCourse(StudyCourseTypeCourse s) {
		getNamedParameterJdbcTemplate().update(addStudyCourseTypeCourse_SQL, new BeanPropertySqlParameterSource(s));
	}
	
	@Override //取得当前类别下 子类别列表的最大Seq
	public Integer getCurTypeMaxSeq(Long curTypeId) {
		return getJdbcTemplate().queryForInt(getCurTypeMaxSeq_SQL, curTypeId, Constants.STATUS_1);
	}
	
	@Override //取得距离当前分类SEQ 最近的一条数据
	public StudyCourseType getCurTypeRecentlyData(Long curTypeId, Integer seq, int flag) {
		StringBuilder sql = new StringBuilder() ;
		sql.append(" select cur.* from ( ") ;
		sql.append(" select t.* from study_course_type t where t.parent_course_type_id = ? and t.status = ? ") ;
		
		//设置查询标记 1.向上寻找 2.向下寻找
		if(flag == 1){
			sql.append(" and t.seq < ? order by t.seq desc ") ;
		}else{
			sql.append(" and t.seq > ? order by t.seq asc ") ;
		}
		sql.append(" ) cur where rownum = 1 ") ;
		
		List<StudyCourseType> typeList = getJdbcTemplate().query(sql.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseType.class),
				curTypeId, Constants.STATUS_1, seq);
		
		if(Utils.isListEmpty(typeList)){
			return null ;
		}
		
		return typeList.get(0) ;
	}
	
	@Override //批量修改课程分类
	public int updateCurTypeBatch(List<StudyCourseType> curTypeList) {
		return saveList(updateCurTypeBatch_SQL, curTypeList).length ;
	}
	
	@Override //根据分类ID 查询所有子分类信息
	public List<StudyCourseType> getAllChildCurTypeByTypeId(Long curTypeId) {
		return getJdbcTemplate().query(getAllChildCurTypeByTypeId_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseType.class),
				Constants.STATUS_1, curTypeId);
	}
}
