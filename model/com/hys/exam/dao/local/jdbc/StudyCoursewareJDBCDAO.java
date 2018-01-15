package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.StudyCoursewareDAO;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourseware;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCoursewareJDBCDAO extends AbstractJDBCDAO implements StudyCoursewareDAO {

	final static private String getStudyCoursewareList_SQL = "select * from study_courseware t ";

	//查询课程下的课件信息
	final static private String getCourseWareByCourse_SQL = "select t.id courseElementId,t.course_element_type,t.course_id,t.seq,"
			+ "t3.name,t3.times,t3.class_hours,t3.teacher_name,t3.type,t3.id "
			+ "from study_course_element t "
			+ "join study_course t1 on t1.id = t.course_id "
			+ "left join study_course_element_ware t2 on t.id = t2.study_course_element_id "
			+ "left join study_courseware t3 on t2.study_courseware_id = t3.id and t3.status = ? "
			+ "where t.status = ? and t1.status >= ? and t.course_id = ? order by t.seq " ;
	
	final static private String addStudyCourseware_SQL = "insert into study_courseware (id, code, name, type, times, class_hours, summary, key_word, teacher_name, teacher_title, teacher_unit, make_year, remark, meaning, gains, attentions, path,as_path, status, file_path, publish_path, is_publish, relative_address,last_update_user_id,create_user_id) values (:id, :code, :name, :type, :times, :classHours, :summary, :keyWord, :teacherName, :teacherTitle, :teacherUnit, :makeYear, :remark, :meaning, :gains, :attentions, :path, :asPath, :status, :filePath, :publishPath, :isPublish, :relativeAddress,:lastUpdateUserId,:createUserId)";

	final static private String updateStudyCourseware_SQL = "update study_courseware set name = :name, type = :type,times = :times,class_hours = :classHours,summary = :summary,key_word = :keyWord,teacher_name = :teacherName,teacher_title = :teacherTitle,teacher_unit = :teacherUnit,make_year = :makeYear,remark = :remark,meaning = :meaning,gains = :gains,attentions = :attentions,path = :path,file_path = :filePath,publish_path = :publishPath,is_publish = :isPublish,relative_address = :relativeAddress,last_update_date=sysdate(),last_update_user_id=:lastUpdateUserId  where id = :id";

	final static private String getStudyCoursewareById_SQL = "select * from study_courseware t where t.status=1 and t.id=?";

	final static private String deleteStudyCourseware_SQL = "update study_courseware t set t.last_update_date=sysdate(),t.last_update_user_id=:lastUpdateUserId,t.status=-1 where t.id= :id";

	//根据课程组件ID 查询课件信息
	final static private String getStudyCoursewareByEleId_SQL = "select t.* from study_courseware t "
			+ "join study_course_element_ware t1 on t.id = t1.study_courseware_id "
			+ "join study_course_element t2 on t2.id = t1.study_course_element_id "
			+ "where t.status = ? and t2.status = ? and t2.id = ? " ;
	
	@Override
	public void getStudyCoursewareList(Page<StudyCourseware> page,
			StudyCourseware courseware) {

		StringBuilder sql = new StringBuilder();
		sql.append(getStudyCoursewareList_SQL);
		List<Object> list = new ArrayList<Object>();

		if (courseware.getIndustryList() != null
				&& !courseware.getIndustryList().isEmpty()) {
			sql.append(" join study_courseware_industry t2 on t.id=t2.courseware_id and t2.industry_id in ( ");
			for (int i = 0; i < courseware.getIndustryList().size(); ++i) {
				if (i == (courseware.getIndustryList().size() - 1)) {
					sql.append("?");
				} else {
					sql.append("?,");
				}
				list.add(courseware.getIndustryList().get(i).getId());
			}
			sql.append(" ) ");
		}

		if (courseware.getApplicableList() != null
				&& !courseware.getApplicableList().isEmpty()) {
			sql.append(" join study_courseware_title t3 on t.id=t3.courseware_id and t3.prop_val_id in ( ");
			for (int i = 0; i < courseware.getApplicableList().size(); ++i) {
				if (i == (courseware.getApplicableList().size() - 1)) {
					sql.append("?");
				} else {
					sql.append("?,");
				}
				list.add(courseware.getApplicableList().get(i).getId());
			}
			sql.append(" ) ");
		}

		if (courseware.getKnowList() != null
				&& !courseware.getKnowList().isEmpty()) {
			sql.append(" join study_courseware_cognize t4 on t.id=t4.courseware_id and t4.prop_val_id in ( ");
			for (int i = 0; i < courseware.getKnowList().size(); ++i) {
				if (i == (courseware.getKnowList().size() - 1)) {
					sql.append("?");
				} else {
					sql.append("?,");
				}
				list.add(courseware.getKnowList().get(i).getId());
			}
			sql.append(" ) ");
		}

		sql.append(" where t.status=1 ");

		if (courseware.getName() != null && !"".equals(courseware.getName())) {
			sql.append(" and t.name like ? ");
			list.add("%" + courseware.getName() + "%");
		}

		if (courseware.getTeacherName() != null
				&& !"".equals(courseware.getTeacherName())) {
			sql.append(" and teacher_name like ? ");
			list.add("%" + courseware.getTeacherName() + "%");
		}

		if (courseware.getKeyWord() != null
				&& !"".equals(courseware.getKeyWord())) {
			sql.append(" and t.key_word like ? ");
			list.add("%" + courseware.getKeyWord() + "%");
		}

		if (courseware.getMakeYear() != null
				&& !"".equals(courseware.getMakeYear())) {
			sql.append(" and t.make_year = ? ");
			list.add(courseware.getMakeYear());
		}

		//sql.append(" order by t.id desc ");
		//sql.append(" order by to_char(t.create_date, 'yyyy-MM-dd') desc, t.name");
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		sql.append(" order by t.create_date desc, t.name");
		
		// 查询结果集
		List<StudyCourseware> coursewareList = getList(
				PageUtil.getPageSql(sql.toString(), page.getPageSize(), page.getCurrentPage()),
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseware.class));

		for (StudyCourseware s : coursewareList) {
			s.setIndustryList(getStudyCoursewareIndustryList(s.getId()));
			s.setApplicableList(getStudyCoursewareApplicableList(s.getId()));
			s.setKnowList(getStudyCoursewareKnowList(s.getId()));
		}

		//取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(coursewareList);
		page.setCount(totalCount);
	}

	@Override //查询课程下的课件信息
	public void getCourseWareByCourse(Page<StudyCourseware> page, StudyCourseware curware){
		List<Object> params = new ArrayList<Object>();
		params.add(Constants.STATUS_1) ; params.add(Constants.STATUS_1) ;
		params.add(Constants.STATUS_3) ; params.add(curware.getCourseId()) ;

		//查询结果集
		List<StudyCourseware> wareList = getList(
				PageUtil.getPageSql(getCourseWareByCourse_SQL, page.getPageSize(), page.getCurrentPage()),
				params,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseware.class));

		//取得总记录数
		Integer count = getCount(getCourseWareByCourse_SQL, params.toArray());
		
		page.setList(wareList);
		page.setCount(count);
	}

	@Override
	public int addStudyCourseware(StudyCourseware s) {
		s.setId(getSequence("study_courseware_seq")) ;
		s.setCode(DateUtil.getYear()
				+ com.hys.exam.utils.StringUtils.leftPad(String.valueOf(s.getId()), 9, '0'));

		getNamedParameterJdbcTemplate().update(addStudyCourseware_SQL, new BeanPropertySqlParameterSource(s));
		addStudyCoursewareIndustryList(s);
		addStudyCoursewareApplicableList(s);
		addStudyCoursewareKnowList(s);

		return 1;
	}
	
	//修改课件path
	@Override
	public int updateStudyCoursewareById(StudyCourseware s){
		String sql = "update study_courseware set name = :name, path = :path, as_path = :asPath, last_update_date=sysdate(), last_update_user_id=:lastUpdateUserId  where id = :id";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(s));
	}

	@Override
	public int updateStudyCourseware(StudyCourseware s) {
		getNamedParameterJdbcTemplate().update(updateStudyCourseware_SQL, new BeanPropertySqlParameterSource(s));
		deleteStudyCoursewareIndustryList(s);
		deleteStudyCoursewareApplicableList(s);
		deleteStudyCoursewareKnowList(s);

		addStudyCoursewareIndustryList(s);
		addStudyCoursewareApplicableList(s);
		addStudyCoursewareKnowList(s);
		return 1;
	}

	@Override
	public StudyCourseware getStudyCoursewareById(Long id) {
		List<StudyCourseware> list = getJdbcTemplate().query(
				getStudyCoursewareById_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseware.class),
				id);

		if (!list.isEmpty()) {
			StudyCourseware s = list.get(0);
			s.setIndustryList(getStudyCoursewareIndustryList(s.getId()));
			s.setApplicableList(getStudyCoursewareApplicableList(s.getId()));
			s.setKnowList(getStudyCoursewareKnowList(s.getId()));

			return s;
		}

		return null;
	}

	@Override
	public int deleteStudyCourseware(StudyCourseware s) {
		return getNamedParameterJdbcTemplate().update(deleteStudyCourseware_SQL, new BeanPropertySqlParameterSource(s));
	}

	@Override //根据课程组件ID 查询课件信息
	public StudyCourseware getStudyCoursewareByEleId(Long elementId) {
		List<StudyCourseware> list = getJdbcTemplate().query(
				getStudyCoursewareByEleId_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseware.class),
				Constants.STATUS_1, Constants.STATUS_1, elementId);

		if(Utils.isListEmpty(list)){
			return null ;
		}

		return list.get(0);
	}

	final static private String addStudyCoursewareIndustryList_SQL = "insert into study_courseware_industry(courseware_id, industry_id) values (?, ?)";

	@Override
	public void addStudyCoursewareIndustryList(StudyCourseware s) {

		if (s != null && s.getIndustryList() != null) {
			for (ExamPropVal industry : s.getIndustryList()) {
				getJdbcTemplate().update(addStudyCoursewareIndustryList_SQL, s.getId(), industry.getId());
			}
		}
	}

	final static private String addStudyCoursewareApplicableList_SQL = "insert into study_courseware_title(courseware_id, prop_val_id) values (?, ?)";

	@Override
	public void addStudyCoursewareApplicableList(StudyCourseware s) {
		if (s != null && s.getApplicableList() != null) {
			for (ExamPropVal applicable : s.getApplicableList()) {
				getJdbcTemplate().update(addStudyCoursewareApplicableList_SQL, s.getId(), applicable.getId());
			}
		}
	}

	final static private String addStudyCoursewareKnowList_SQL = "insert into study_courseware_cognize(courseware_id, prop_val_id) values (?, ?)";

	@Override
	public void addStudyCoursewareKnowList(StudyCourseware s) {
		if (s != null && s.getKnowList() != null) {
			for (ExamPropVal know : s.getKnowList()) {
				getJdbcTemplate().update(addStudyCoursewareKnowList_SQL, s.getId(), know.getId());
			}
		}
	}

	//final static private String getStudyCoursewareIndustryList_SQL = "select t2.* from study_courseware_industry t ,system_industry t2 where t.industry_id=t2.id and t.courseware_id=?";
	//课件里的行业读取的是属性里的行业
	final static private String getStudyCoursewareIndustryList_SQL = 
			" select t2.* from study_courseware_industry t, EXAM_PROP_VAL t2 " +
			" where t.industry_id = t2.id and t.courseware_id = ? and t2.type = ?";
	
	
	public List<ExamPropVal> getStudyCoursewareIndustryList(Long studyCoursewareId) {
		return getJdbcTemplate().query(
				getStudyCoursewareIndustryList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPropVal.class), studyCoursewareId, Constants.SYSTEM_PROP_TYPE_INDUSTRY);
	}

	final static private String getStudyCoursewareApplicableList_SQL = "select t2.* from STUDY_COURSEWARE_TITLE t, EXAM_PROP_VAL t2 where t.prop_val_id = t2.id and t.courseware_id = ?";

	public List<ExamPropVal> getStudyCoursewareApplicableList(Long studyCoursewareId) {
		return getJdbcTemplate().query(
				getStudyCoursewareApplicableList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPropVal.class), studyCoursewareId);
	}

	final static private String getStudyCoursewareKnowList_SQL = "select t2.* from STUDY_COURSEWARE_COGNIZE t, EXAM_PROP_VAL t2 where t.prop_val_id = t2.id and t.courseware_id = ?";

	public List<ExamPropVal> getStudyCoursewareKnowList(Long studyCoursewareId) {
		return getJdbcTemplate().query(
				getStudyCoursewareKnowList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPropVal.class), studyCoursewareId);
	}

	final static private String deleteStudyCoursewareIndustryList_SQL = "delete from study_courseware_industry where courseware_id=?";

	public void deleteStudyCoursewareIndustryList(StudyCourseware s) {
		if (s != null) {
			getJdbcTemplate().update(deleteStudyCoursewareIndustryList_SQL, s.getId());
		}
	}

	final static private String deleteStudyCoursewareApplicableList_SQL = "delete from study_courseware_title where courseware_id=?";

	public void deleteStudyCoursewareApplicableList(StudyCourseware s) {
		if (s != null) {
			getJdbcTemplate().update(deleteStudyCoursewareApplicableList_SQL, s.getId());
		}
	}

	final static private String deleteStudyCoursewareKnowList_SQL = "delete from study_courseware_cognize where courseware_id=?";

	public void deleteStudyCoursewareKnowList(StudyCourseware s) {
		if (s != null) {
			getJdbcTemplate().update(deleteStudyCoursewareKnowList_SQL, s.getId());
		}
	}
}
