package com.hys.exam.dao.local.jdbc;

import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.LogStudyCVUnitManageDAO;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.LogStudyCVUnitVideo;
import com.hys.exam.utils.StringUtils;


/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnitJDBCDAO.java
 *     
 *     Description       :   学习记录(单元)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVUnitJDBCDAO extends BaseDao implements LogStudyCVUnitManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	@Override
	public Long addLogStudyCVUnit(LogStudyCVUnit log) {
		Long Id = log.getId();
		if(Id == null) Id = this.getNextId("log_study_cv_unit");
		String sql = " insert into log_study_cv_unit(id,log_study_cv_set_id,system_user_id,cv_id,cv_unit_id,status,last_update_date) values(?,?,?,?,?,1,sysdate()) ";
		int count = getJdbcTemplate().update(sql,Id,log.getLogStudyCVSetId(), log.getSystemUserId(),log.getCvId(),log.getCvUnitId());
		if(count < 1){
			Id = 0L;
		}
		return Id;
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记录是否存在
	 */
	public List<LogStudyCVUnit> queryLogStudyCVUnitByUnitId(LogStudyCVUnit cvs){
		String sql = " select * from log_study_cv_unit where cv_unit_id =? and system_user_id = ? ";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class),cvs.getCvUnitId(),cvs.getSystemUserId());
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记修改单元学习记录————taoliang，2017-07-30，更新个人的记录不是更新所有的
	 */
    public void updLogStudyCVUnit(Long unitId, Long userId){
		String sql = " update log_study_cv_unit set last_update_date = sysdate()  where cv_unit_id = ? and system_user_id = ? ";
		getJdbcTemplate().update(sql,unitId,userId);	
	}
    


	@Override
	public void updLogStudyCVUnitById(LogStudyCVUnit cvs) {
		// TODO Auto-generated method stub
		String sql = " update log_study_cv_unit set status = "+cvs.getStatus()+" where cv_unit_id="+cvs.getCvUnitId() + " and system_user_id=" + cvs.getSystemUserId(); //YHQ，2017-06-06，不加这个所有学习这个单元的人全通过考试！！！
		getJdbcTemplate().update(sql);
		
		
	}

	@Override
	public Long addLogStudyCVUnitVideo(LogStudyCVUnitVideo record) {
		Long Id = record.getId();
		if(Id == null) Id = this.getNextId("log_study_cv_unit_video");
		String sql = "insert into log_study_cv_unit_video (id,LOG_STUDY_CV_UNIT_ID,SYSTEM_USER_ID,CV_ID," +
				"CV_UNIT_ID,videoLength,videoPlayLength,START_DATE,END_DATE) values(?,?,?,?,?,?,0,SYSDATE(),SYSDATE())";
		int count = getSimpleJdbcTemplate().update(sql, Id,record.getLogStudyCvUnitId(),record.getSystemUserId(),
				record.getCvId(),record.getCvUnitId(),record.getVideoLength());
		if(count < 1){
			Id = 0L;
		}
		return Id;
	}

	@Override
	public void addBatchLogStudyCVUnit(List<LogStudyCVUnit> recordList) {
		String sql = "insert into log_study_cv_unit (id,log_study_cv_set_id,system_user_id,cv_id,cv_unit_id,status,last_update_date) values (:id, :logStudyCVSetId, :systemUserId, :cvId, :cvUnitId, 1, sysdate())";
		saveList(sql,recordList);
	}

	@Override
	public List<LogStudyCVUnit> getLogStudyCVUnitRecord(LogStudyCVUnit record) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from log_study_cv_unit t where 1=1");
		if(record.getLogStudyCVSetId() != null && record.getLogStudyCVSetId() > 0L){
			sql.append(" and t.LOG_STUDY_CV_SET_ID = "+record.getLogStudyCVSetId());
		}
		if(record.getCvId() != null && record.getCvId() > 0L){
			sql.append(" and t.CV_ID = "+record.getCvId());
		}
		if(record.getCvUnitId() != null && record.getCvUnitId() > 0L){
			sql.append(" and t.CV_UNIT_ID = "+record.getCvUnitId());
		}
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class));
	}

	@Override
	public void addBatchLogStudyCVUnitVideo(List<LogStudyCVUnitVideo> recordList) {
		String sql = "insert into log_study_cv_unit_video (id,LOG_STUDY_CV_UNIT_ID,SYSTEM_USER_ID,CV_ID,CV_UNIT_ID,videoLength,videoPlayLength,START_DATE,END_DATE) values (:id, :logStudyCvUnitId, :systemUserId, :cvId, :cvUnitId, :videoLength, :videoPlayLength, sysdate(), sysdate())";
		saveList(sql,recordList);
	}
    
}
