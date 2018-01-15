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
import com.hys.exam.dao.local.NcmeCourseCreditDAO;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.model.StudyCourseSetting;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseCreditJDBCDAO extends AbstractJDBCDAO implements NcmeCourseCreditDAO {

	
	//final static private String getNcmeCourseCreditList_SQL = "select t.*, t.org_id as organId, t2.*,t3.name as organName,t3.description as organDescription,t4.type_name as courseTypeName,t5.credit_name as creditName from system_course_credit t,study_course t2, SYSTEM_ADMIN_ORGAN t3, SYSTEM_COURSE_TYPE t4, SYSTEM_CREDIT_TYPE t5  where t.credit_year = ? and t.course_id = t2.id and t.org_id = t3.organ_id and t.course_type = t4.course_type and t.credit_type = t5.credit_type";
	//final static private String getNcmeCourseCreditList_SQL = "select t.*, t.org_id as organId, t2.*,t3.name as organName,t3.description as organDescription, t5.credit_name as creditName from system_course_credit t,study_course t2, SYSTEM_ADMIN_ORGAN t3, SYSTEM_CREDIT_TYPE t5  where t.credit_year = ? and t.course_id = t2.id and t.org_id = t3.organ_id and t.credit_type = t5.credit_type";
	
	final static private String getNcmeCourseCreditList_SQL = "select t.credit_year, t.course_id,t.site_id as siteId, t.credit_type, t.credit_num, t.credit_hours, t.credit_date,t.course_type," +
			" t3.study_course_name, t3.summary, t3.status, t3.class_hours,  " +
			" t5.credit_name as creditName, t4.domain_name, " +
			" wmsys.wm_concat(t2.course_type_name) course_type_names" +
			" from system_course_credit t " +
			" left join study_course_type t2 on t.course_type = t2.id" +
			" left join study_course t3 on t.course_id = t3.id" +
			//" left  join system_admin_organ t4 on t.org_id = t4.organ_id" +
			" left join system_site t4 on t4.id = t.site_id" +
			" left join system_credit_type t5 on t.credit_type = t5.credit_type" +
			" where t.credit_year = ? and t3.status = 1";
	
	//得到授权对象
	final static private String getNcmeCourseCredit_SQL = "select t.credit_year, t.course_id, t.site_id as siteId, wmsys.wm_concat(t2.id) course_type_ids ," +
			" wmsys.wm_concat(t2.course_type_name) course_type_names, t.credit_type, t.credit_num, t3.credit_name, t.start_date, t.end_date, t4.study_course_name, t4.class_hours," +
			" t.course_serial, t.re_study_flag, t.credit_date, t.credit_hours" +
			" from system_course_credit t " +
			" left join study_course_type t2 on t.course_type = t2.id" +
			" left join system_credit_type t3 on t.credit_type = t3.credit_type" +
			" left join study_course t4 on t.course_id = t4.id " +
			" where t.credit_year = ? and t.course_id = ? and t.site_id = ? " +
			" group by t.credit_year, t.course_id, t.site_id, t.credit_type, t.credit_num, t3.credit_name, t.start_date, t.end_date, t4.study_course_name, t4.class_hours," +
			" t.course_serial, t.re_study_flag, t.credit_date, t.credit_hours";
			

	final static private String addNcmeCourseCredit_SQL = "insert into system_course_credit(credit_year, course_id, site_id, credit_type, course_type, credit_num, course_serial, request, re_study_flag, credit_date, start_date, end_date) values(:creditYear, :courseId, :siteId, :creditType, :courseType, :creditNum, :courseSerial, :request, :reStudyFlag, :creditDate, :startDate, :endDate)";
	
	final static private String deleteNcmeCourseCredit_SQL = "delete from system_course_credit where credit_year=? and course_id=? and site_id=?";

	final static private String getNcmeCourseCreditById_SQL = "select * from system_course_credit where credit_year=? and course_id=? and site_id = ? and course_type = ?";

	@Override
	public void getNcmeCourseCreditList(Page<NcmeCourseCredit> page,
			NcmeCourseCredit ncmeCourseCredit) {
		StringBuilder sql = new StringBuilder();
		sql.append(getNcmeCourseCreditList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(ncmeCourseCredit.getCreditYear())) {
			list.add(ncmeCourseCredit.getCreditYear());
		}else{
			list.add(DateUtil.getYear());
		}
		
		if (StringUtils.isNotBlank(ncmeCourseCredit.getCreditType())){
			sql.append(" and t.credit_type=?  ");
			list.add(ncmeCourseCredit.getCreditType());
		}

		if(ncmeCourseCredit.getCourseType() != null && ncmeCourseCredit.getCourseType() != -1){
			sql.append(" and t.course_type=?  ");
			list.add(ncmeCourseCredit.getCourseType());
		}

		if(ncmeCourseCredit.getCourseId() != null && !"".equals(ncmeCourseCredit.getCourseId())){
			sql.append(" and t.course_id=?  ");
			list.add(ncmeCourseCredit.getCourseId());
		}

		if (StringUtils.isNotBlank(ncmeCourseCredit.getStudyCourseName())) {
			sql.append(" and t3.study_course_name like ? ");
			list.add("%" + ncmeCourseCredit.getStudyCourseName() + "%");
		}

		/*if (ncmeCourseCredit.getOrganId() != null && !"".equals(ncmeCourseCredit.getOrganId())) {
			sql.append(" and t.org_id=? ");
			list.add(ncmeCourseCredit.getOrganId());
		}*/
		if (ncmeCourseCredit.getSiteId() != null && ncmeCourseCredit.getSiteId()>0) {
			sql.append(" and t.site_id=? ");
			list.add(ncmeCourseCredit.getSiteId());
		}

		if (ncmeCourseCredit.getStartDate() != null) {
			sql.append(" and t.credit_date >= ? ");
			list.add(ncmeCourseCredit.getStartDate());
		}

		if (ncmeCourseCredit.getEndDate() != null) {
			sql.append(" and t.credit_date <= ? ");
			list.add(ncmeCourseCredit.getEndDate());
		}
		
		sql.append(" group by t.credit_year, t.site_id, t.course_id, t.credit_type, t.credit_num, t.credit_hours, t.credit_date,t.course_type," +
				" t3.study_course_name, t3.summary, t3.status, t3.class_hours, t4.domain_name, t5.credit_name");

		sql.append(" order by t.credit_date desc ");
		
		List<NcmeCourseCredit> ncmeCourseCreditList = getList(
				PageUtil.getPageSql(sql.toString(), page.getPageSize(), page.getCurrentPage()), list,
				ParameterizedBeanPropertyRowMapper.newInstance(NcmeCourseCredit.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(ncmeCourseCreditList);
		page.setCount(totalCount);

	}
	
	//根据课程分类得到其授权的课程列表  ////20141118此方法已停用
	@Override
	public List<NcmeCourseCredit> getNcmeCourseCreditListByCourseType(
			Page<NcmeCourseCredit> page, NcmeCourseCredit credit, Long cardTypeId){
		StringBuilder sql = new StringBuilder();
		
		//已授权课程
		/*sql.append(" select t2.*,t.credit_year, t.credit_num, t3.credit_name, t4.org_name " +
				" from system_course_credit t " +
				" left join study_course t2 on t.course_id = t2.id" +
				" left join system_credit_type t3 on t3.credit_type = t.credit_type" +
				" left join system_org t4 on t4.id = t.org_id" +
				" where t.credit_year = ? and t.org_id = ? and t.course_type = ?");*/
		
		//已授权课程未与卡类型绑定的
		sql.append("select t2.*,t.credit_year, t.credit_num, t3.credit_name, t5.course_type_name" +
				" from system_course_credit t " +
				" left join study_course t2 on t.course_id = t2.id" +
				" left join system_credit_type t3 on t3.credit_type = t.credit_type " +
				" left join study_course_type t5 on t5.id = t.course_type" +
				" where t.course_type = ? and t.course_id not in" +
				" (select c.course_id from system_paycard_course c where c.card_type_id = ?)" +
				" and t.credit_year = ? ");
		
		List<Object> obj = new ArrayList<Object>();
		
		obj.add(credit.getCourseType());
		obj.add(cardTypeId);
		
		if (StringUtils.isNotBlank(credit.getCreditYear())) {
			obj.add(credit.getCreditYear());
		}else{
			obj.add(DateUtil.getYear());
		}
		
		if(StringUtils.isNotBlank(credit.getStudyCourseName())){
			sql.append(" and t2.study_course_name like ?");
			obj.add("%" + credit.getStudyCourseName() + "%");
		}
		List<NcmeCourseCredit> list = getList(
				PageUtil.getPageSql(sql.toString(), page.getPageSize(), page.getCurrentPage()), obj,
				ParameterizedBeanPropertyRowMapper.newInstance(NcmeCourseCredit.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), obj.toArray());
		page.setList(list);
		page.setCount(totalCount);
		return list;
	}

	@Override
	public int addNcmeCourseCredit(NcmeCourseCredit n) {
		return getNamedParameterJdbcTemplate().update(addNcmeCourseCredit_SQL, new BeanPropertySqlParameterSource(n));
	}
	
	/**
	 * 
	 * 得到授权 object
	 * @param year
	 * @param courseId
	 * @param siteId
	 * @return
	 */
	@Override
	public NcmeCourseCredit getNcmeCourseCredit(String year, Long courseId, Long siteId){
		List<NcmeCourseCredit> list = getList(getNcmeCourseCredit_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(NcmeCourseCredit.class), year, courseId, siteId);
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 * 修改授权
	 * @param n
	 */
	@Override
	public void updateNcmeCourseCredit(NcmeCourseCredit credit){
		if(null != credit){
			List<Object> params = new ArrayList<Object>();
			StringBuffer sql = new StringBuffer();
			sql.append("update system_course_credit set credit_type = ?");
			params.add(credit.getCreditType());
			if(null != credit.getCreditNum() && credit.getCreditNum()>0){
				sql.append(" , credit_num = ?");
				params.add(credit.getCreditNum());
			}
			if(null != credit.getCreditDate()){
				sql.append(" , credit_date = ?");
				params.add(credit.getCreditDate());
			}
			if(null != credit.getCreditHours() && credit.getCreditHours()>0){
				sql.append(" , credit_hours = ?");
				params.add(credit.getCreditHours());
			}
			if(StringUtils.isNotBlank(credit.getCourseSerial())){
				sql.append(" , course_serial = ?");
				params.add(credit.getCourseSerial());
			}
			if(null != credit.getReStudyFlag()){
				sql.append(" , re_study_flag = ?");
				params.add(credit.getReStudyFlag());
			}
			if(null != credit.getStartDate()){
				sql.append(" , start_date = ?");
				params.add(credit.getStartDate());
			}
			if(null != credit.getEndDate()){
				sql.append(" , end_date = ?");
				params.add(credit.getEndDate());
			}
			sql.append(" where credit_year = ? and course_id = ? and site_id = ?");
			params.add(credit.getCreditYear());
			params.add(credit.getCourseId());
			params.add(credit.getSiteId());
			this.getJdbcTemplate().update(sql.toString(), params.toArray());
		}
	}
	
	//修改授权时间
	public int updateNcmeCourseCreditDate(NcmeCourseCredit credit){
		String sql = "update system_course_credit t set t.credit_date = ? " +
				" where t.credit_year = ? and t.course_id = ? and t.site_id = ? and t.course_type = ?";
		return this.getJdbcTemplate().update(sql, credit.getCreditDate(),
				credit.getCreditYear(), new Long(credit.getCourseId()), credit.getSiteId(), credit.getCourseType() );
	}

	@Override
	public int deleteNcmeCourseCredit(NcmeCourseCredit n) {
		return getJdbcTemplate().update(deleteNcmeCourseCredit_SQL, n.getCreditYear(), n.getCourseId(), n.getSiteId());
	}

	@Override
	public NcmeCourseCredit getNcmeCourseCreditById(String creditYear, String courseId, Long siteId, Integer courseType) {
		List<NcmeCourseCredit> list = getJdbcTemplate().query(
				getNcmeCourseCreditById_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(NcmeCourseCredit.class), 
				creditYear, courseId, siteId, courseType);

		if (list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}
	
	//得到课件播放时间
	public StudyCourseSetting getStudyCourseSetting(){
		String sql = "select * from study_course_setting t ";
		List<StudyCourseSetting> list = getJdbcTemplate().query(
				sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseSetting.class));
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	//保存课件播放时间
	public int saveStudyCourseSetting(Long id, Double time){
		String sql = "update study_course_setting t set t.time = ?, t.update_date =sysdate() where id = ?";
		return this.getJdbcTemplate().update(sql, time, id);
	}
}
