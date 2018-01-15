package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CvLiveDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.model.SystemAccount;

public class CvLiveJdbcDAO extends BaseDao implements CvLiveDAO {
	
	@Override
	public Long addCvLiveCourseware(CvLiveCourseware record) {//courseware_create_time,duration ,record.getDuration()
		Long Id = record.getId();
		if(Id == null) Id = this.getNextId("cv_live_courseware");
		String sql = "INSERT INTO cv_live_courseware (id,courseware_no,courseware_num,courseware_url," +
				"courseware_token,subject,class_no,courseware_create_time,screenshot,recordId,description,cv_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		int count = getSimpleJdbcTemplate().update(sql, Id,record.getCourseware_no(),record.getCourseware_num(),record.getCourseware_url(),record.getCourseware_token(),
				record.getSubject(),record.getClass_no(),record.getCourseware_create_time(),record.getScreenshot(),record.getRecordId(),
				record.getDescription(),record.getCv_id());
		if(count < 1){
			Id = 0L;
		}
		return Id;
	}

	@Override
	public List<CvLiveCourseware> getCvLiveCoursewareList(
			CvLiveCourseware record) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cv_live_courseware t where 1=1");
		if(record.getClass_no() != null && !"".equals(record.getClass_no())){
			sql.append(" and t.class_no = '"+record.getClass_no()+"'");
		}
		if(record.getCourseware_no() != null && !"".equals(record.getCourseware_no())){
			sql.append(" and t.courseware_no = '"+record.getCourseware_no()+"'");
		}
		if(record.getCv_id() != null && record.getCv_id() > 0L){
			sql.append(" and t.cv_id = "+record.getCv_id());
		}
		if(record.getId() != null && record.getId() > 0L){
			sql.append(" and t.id = "+record.getId());
		}
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CvLiveCourseware.class));
	}

	@Override
	public int updateCvLiveCoutsewareInfo(CvLiveCourseware record) {
		List values = new ArrayList();
		String sql = "UPDATE cv_live_courseware SET " +
				"courseware_num=?, courseware_url=?, courseware_token=?, " +
				"subject=?,class_no=?, courseware_create_time=?," +
				"duration=?,screenshot=?, recordId=?," +
				"description=?,cv_id=? WHERE courseware_no='"+record.getCourseware_no()+"'";
		values.add(record.getCourseware_num());
		values.add(record.getCourseware_url());
		values.add(record.getCourseware_token());
		values.add(record.getSubject());
		values.add(record.getClass_no());
		values.add(record.getCourseware_create_time());
		values.add(record.getDuration());
		values.add(record.getScreenshot());
		values.add(record.getRecordId());
		values.add(record.getDescription());
		values.add(record.getCv_id());
		
		return getSimpleJdbcTemplate().update(sql, values.toArray());
	}

	@Override
	public List<CvLive> queryCvLiveList(Long cvId) {
		String sql = "SELECT * FROM cv_live t WHERE t.cv_id = "+cvId;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
	}

	@Override
	public Long addCvLiveRefUnit(CvLiveRefUnit record) {
		Long Id = record.getId();
		if(Id == null) Id = this.getNextId("cv_live_ref_unit");
		String sql = "INSERT INTO cv_live_ref_unit (id,cv_id,unit_id,courseware_no,class_no) VALUES(?,?,?,?,?)";
		int count = getSimpleJdbcTemplate().update(sql, Id,record.getCv_id(),record.getUnit_id(),record.getCourseware_no(),record.getClass_no());
		if(count < 1){
			Id = 0L;
		}
		return Id;
	}

	@Override
	public List<CvLiveRefUnit> getCvLiveRefUnitList(CvLiveRefUnit record) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cv_live_ref_unit t where 1=1");
		if(record.getClass_no() != null && !"".equals(record.getClass_no())){
			sql.append(" and t.class_no = '"+record.getClass_no()+"'");
		}
		if(record.getCourseware_no() != null && !"".equals(record.getCourseware_no())){
			sql.append(" and t.courseware_no = '"+record.getCourseware_no()+"'");
		}
		if(record.getCv_id() != null && record.getCv_id() > 0L){
			sql.append(" and t.cv_id = "+record.getCv_id());
		}
		if(record.getUnit_id() != null && record.getUnit_id() > 0L){
			sql.append(" and t.unit_id = "+record.getUnit_id());
		}
		if(record.getId() != null && record.getId() > 0L){
			sql.append(" and t.id = "+record.getId());
		}
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CvLiveRefUnit.class));
	}

	@Override
	public List<SystemAccount> getViewLiveUser(String classNo) {
		String sql = "SELECT t.USER_ID AS accountId FROM system_account t, cv_live_study t1 WHERE t.ACCOUNT_NAME = t1.nickname AND t1.class_no = '"+classNo+"'";
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemAccount.class));
	}
	
	@Override
	public List<CvLiveStudyRef> queryCvLiveStudyRef(CvLiveStudyRef record) {
		String querySql = "select * from cv_live_study_ref t where t.class_no = '"+record.getClass_no()+"' ORDER BY t.end_time DESC";
		return getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudyRef.class));
	}
}