package com.hys.exam.dao.local.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.dao.local.CVSetAuthorizationDAO;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.dao.local.UserImageManageDAO;
import com.hys.exam.model.BaseVO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetAuthorCheck;
import com.hys.exam.model.CVSetAuthorQuery;
import com.hys.exam.model.CVSetAuthorization;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemMessageModel;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.UserImage;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.StringUtils;


public class CVSetAuthorizationJdbcDAO extends BaseDao implements CVSetAuthorizationDAO {
	
	private CVManageDAO localCVManageDAO;
	private UserImageManageDAO localUserImageManageDAO;
	private ExpertManageDAO localExpertManageDAO;
	
	public UserImageManageDAO getLocalUserImageManageDAO() {
		return localUserImageManageDAO;
	}

	public void setLocalUserImageManageDAO(
			UserImageManageDAO localUserImageManageDAO) {
		this.localUserImageManageDAO = localUserImageManageDAO;
	}

	public ExpertManageDAO getLocalExpertManageDAO() {
		return localExpertManageDAO;
	}

	public void setLocalExpertManageDAO(ExpertManageDAO localExpertManageDAO) {
		this.localExpertManageDAO = localExpertManageDAO;
	}

	public CVManageDAO getLocalCVManageDAO() {
		return localCVManageDAO;
	}

	public void setLocalCVManageDAO(CVManageDAO localCVManageDAO) {
		this.localCVManageDAO = localCVManageDAO;
	}

	@Override
	public CVSet getCVSetForAuthorization(Long cvSetId, Long authorizationId) {
		
		CVSet result;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cv_set where id = ").append(cvSetId);
		result = getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		
		sql.delete(0, sql.length());
		sql.append("SELECT t1.* FROM cv_set_authorization_system_site t LEFT JOIN system_site t1 ON t1.ID = t.SYSTEM_SITE_ID WHERE t.AUTHORIZATION_ID =  ").append(authorizationId);
		List<SystemSite> systemSite = getJdbcTemplate().query(sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		result.setSiteList(systemSite);
		
		sql.delete(0, sql.length());
		sql.append("select cs.SCHEDULE_ID AS id,cv.`NAME`,cv.cv_type,t.START_DATE,t.END_DATE ")
				.append(" from cv_set_schedule css LEFT JOIN cv_schedule cs on css.CV_SCHEDULE_ID = cs.SCHEDULE_ID LEFT JOIN cv as cv on cs.CV_ID = cv.ID ")
						.append(" LEFT JOIN cv_set_authorization_cv_schedule t ON t.CV_SCHEDULE_ID = cs.SCHEDULE_ID ")
								.append(" where css.CV_SET_ID = ")
										.append(result.getId())
												.append(" GROUP BY cs.SCHEDULE_ID ")
														.append(" order by id desc");
		
		List<CVSchedule> scheduleList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));	
		result.setCVScheduleList(scheduleList);
		
		//查询地域
		String prop_sql = "SELECT t.id AS id, t. NAME FROM exam_prop_val t, cv_set_authorization_region t1 WHERE t.id = t1.PROP_VAL_ID AND t1.AUTHORIZATION_ID = " + authorizationId;
		List<PropUnit> regionList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		result.setCourseList(regionList);
		
		sql.delete(0, sql.length());
		sql.append("SELECT * from peixun_org t LEFT JOIN cv_set_org t1 ON t1.ORG_ID = t.ID WHERE t1.CV_ID =  ").append(result.getId());
		List<PeixunOrg> organizationList = getJdbcTemplate().query(sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
		result.setOrganizationList(organizationList);
		
		sql.delete(0, sql.length());
		sql.append("select * from cv_set_authorization t where t.ID =  ").append(authorizationId);
		CVSetAuthorization aut = getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSetAuthorization.class));
		result.setcVSetAuthorization(aut);
		
		return result;
	}

	@Override
	public CVSet getCVSetForAddAuthorization(Long cvSetId) {
		CVSet result;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cv_set where id = ").append(cvSetId);
		result = getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		
		sql.delete(0, sql.length());
		sql.append(" select cs.SCHEDULE_ID as id,cv.name,cv.cv_type,cv.introduction,cv.announcement, ")
				.append(" cv.file_path,cv.create_date,cv.creator,cs.start_date,cs.end_date " )
						.append(" from cv_set_schedule css LEFT JOIN cv_schedule cs on css.CV_SCHEDULE_ID = cs.SCHEDULE_ID ")
								.append(" LEFT JOIN cv as cv on cs.CV_ID = cv.ID where css.CV_SET_ID = ")
										.append(result.getId())
												.append(" GROUP BY cs.SCHEDULE_ID ")
														.append(" order by cs.start_date desc");
		
		List<CVSchedule> scheduleList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));	
		result.setCVScheduleList(scheduleList);
		
		return result;
	}

	@Override
	public int saveCVSetAuthorization(List<Object> saveParams) {
		CVSet saveCV = (CVSet)saveParams.get(0);
		String siteIds = (String)saveParams.get(1);
		
		CVSetAuthorization CVSAuthor = saveCV.getcVSetAuthorization();
		
		Long authorId = this.getNextId("cv_set_authorization");//自增ID
		String addAuthorsql = "INSERT INTO cv_set_authorization (ID,NAME,CV_SET_ID,CV_SET_SERIAL_NUMBER," +
						"CV_SET_LEVEL,CV_SET_SCORE,CV_SET_COST_TYPE,CV_SET_COST,CV_SET_SIGN,CV_SET_START_DATE," +
							"CV_SET_END_DATE,CV_SET_REGISTRATION_STOP,CV_SET_BREAK_DAYS) " +
								"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int count = getSimpleJdbcTemplate().update(addAuthorsql, authorId,null,saveCV.getId(),CVSAuthor.getCvSetSerialNumber(),
					  CVSAuthor.getCvSetLevel(),CVSAuthor.getCvSetScore(),CVSAuthor.getCvSetCostType(),
					  	 CVSAuthor.getCvSetCost(),CVSAuthor.getCvSetSign(),CVSAuthor.getCvSetStartDate(),
					  	 	CVSAuthor.getCvSetEndDate(),CVSAuthor.getCvSetRegistrationStop(),
					  	 		CVSAuthor.getCvSetBreakDays());
		
		if(count > 0){
			for(CVSchedule schedule : saveCV.getCVScheduleList()){
				java.sql.Date startDate =  new java.sql.Date(
													schedule.getStart_date().getTime()
												);
				
				java.sql.Date endtDate =  new java.sql.Date(
													schedule.getEnd_date().getTime()
												);
				
				Long scheId = this.getNextId("cv_set_authorization_cv_schedule");//自增ID
				String addAuthorCVSchedule = "INSERT INTO cv_set_authorization_cv_schedule " +
												"(ID,AUTHORIZATION_ID,CV_SCHEDULE_ID,START_DATE," +
													"END_DATE) VALUES(?,?,?,?,?)";
				
				int count1 = getSimpleJdbcTemplate().update(addAuthorCVSchedule, scheId, authorId, schedule.getId(), startDate, endtDate);
			}
			
			//授权区域
			List<PropUnit> prop_course = saveCV.getCourseList();
			if(prop_course != null && prop_course.size()>0){
				String sql_prop_course = "DELETE FROM cv_set_authorization_region WHERE AUTHORIZATION_ID = ?";
				getSimpleJdbcTemplate().update(sql_prop_course, authorId);
				for(int i=0; i<prop_course.size();i++){
					Long propId = this.getNextId("cv_set_authorization_region");//自增ID
					sql_prop_course = "INSERT INTO cv_set_authorization_region (ID,AUTHORIZATION_ID,PROP_VAL_ID) VALUES (?,?,?)";
					getSimpleJdbcTemplate().update(sql_prop_course, propId,authorId,prop_course.get(i).getId());
				}
			}
			
			if(!StringUtil.checkNull(siteIds)){
				if(siteIds.charAt(siteIds.length() - 1) == ','){
					siteIds = siteIds.substring(0, siteIds.length()-1);
				}
				String[] siteArr = siteIds.split(",");
				String sitesql = "DELETE FROM cv_set_authorization_system_site WHERE AUTHORIZATION_ID = ?";
				getSimpleJdbcTemplate().update(sitesql, authorId);
				for(String siteId : siteArr){
					if(StringUtils.isEmpty(siteId))
						continue;
					Long authorSiteId = this.getNextId("cv_set_authorization_system_site");//自增ID
					sitesql = "INSERT INTO cv_set_authorization_system_site (ID,AUTHORIZATION_ID,SYSTEM_SITE_ID) VALUES (?,?,?)";
					getJdbcTemplate().update(sitesql,authorSiteId,authorId,siteId);
				}
			}
		}else{
			count = 0;
		}
		return count;
	}

	@Override
	public List<CVSetAuthorQuery> getCVSetListForAuthor(CVSet queryCVSet) {
		List<CVSetAuthorQuery> CVSetAuthorList = new ArrayList<CVSetAuthorQuery>();
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		
		 sql.append("SELECT DISTINCT")
			.append(" t.ID AS authorizationId,")
			.append(" t.CV_SET_ID AS cvSetId,")
			.append(" t1.`NAME` AS cvSetName,")
			.append(" t.CV_SET_SERIAL_NUMBER AS serialNumber,")
			.append(" t1.FORMA AS forma,")
			.append(" t.CV_SET_LEVEL AS LEVEL,")
			.append(" t.CV_SET_SCORE AS score,")
			.append(" t.CV_SET_COST_TYPE AS costType,")
			.append(" t1.MAKER AS maker,")
			.append(" t.CV_SET_START_DATE AS startDate,")
			.append(" t.CV_SET_END_DATE AS endDate,")
			.append(" t.CV_SET_SIGN AS sign,")
			.append(" t1.`STATUS` AS STATUS")
			.append(" FROM ")
				.append(" 	cv_set_authorization t ")
						.append(" LEFT JOIN cv_set AS t1 ON t1.ID = t.CV_SET_ID ")
								.append(" LEFT JOIN cv_set_user_image AS c ON t.ID = c.CV_SET_ID ")
										.append(" LEFT JOIN qm_user_image_prop AS u ON c.USERIMAGE_ID = u.USERIMAGE_ID ")
												.append(" LEFT JOIN CV_REGION AS cr ON t.ID = cr.CV_SET_ID ")
														.append(" WHERE ")
																.append(" 1 = 1 ");
		
		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size()>0){			
			sql.append(" and u.PROP_ID IN (") ;			
			for(int i=0; i<queryCVSet.getCourseList().size(); i++){
				if(i == (queryCVSet.getCourseList().size()-1)){
					sql.append(queryCVSet.getCourseList().get(i).getId());
				}else{
					sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
				}
			}
			sql.append(")");
			
		}
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t1.name like ?");
			values.add("%"+queryCVSet.getName()+"%");
		}
		if(!StringUtils.checkNull(queryCVSet.getMaker())){
			sql.append(" and t1.id in(select  cse.cv_set_id from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id  where ei.name like ? and cse.type = 1 )");
			values.add("%"+queryCVSet.getMaker()+"%");
		}
		if(queryCVSet.getForma() != null && queryCVSet.getForma() > 0){
			sql.append(" and t1.FORMA = "+ queryCVSet.getForma());
		}
		if(queryCVSet.getAuthorStatus() != null && queryCVSet.getAuthorStatus() > 0){
			if(queryCVSet.getAuthorStatus() == 1){
				sql.append(" AND t.CV_SET_END_DATE >= CURDATE() ");
			}else if(queryCVSet.getAuthorStatus() == 2){
				sql.append(" AND t.CV_SET_END_DATE < CURDATE() ");
			}
		}
		if(queryCVSet.getLevel() != null && queryCVSet.getLevel() > 0){
			sql.append(" and t1.LEVEL = "+ queryCVSet.getLevel());
		}
		if(queryCVSet.getScore() != null && !"".equals(queryCVSet.getScore())){
			sql.append(" and t1.SCORE = "+ queryCVSet.getScore());
		}
		if(queryCVSet.getSign() != null){
			if(queryCVSet.getSign().equals("1")){
				sql.append(" and t1.sign like '%公需科目%'");
			}else if(queryCVSet.getSign().equals("2")){
				sql.append(" and t1.sign like '%指南%'");
			}else if(queryCVSet.getSign().equals("3")){
				sql.append(" and t1.sign like '%海外%'");
			}else if(queryCVSet.getSign().equals("4")){
				sql.append(" and t1.sign like '%乡医%'");
			}
		}
		if(queryCVSet.getPropIds() != null && !queryCVSet.getPropIds().equals("")){
			sql.append(" and cr.REGION_ID IN (" + queryCVSet.getPropIds() + ")");
		}
		if(queryCVSet.getSiteList() != null && queryCVSet.getSiteList().size() > 0){
			sql.append(" AND t1.ID in ( ")
					.append(" SELECT t.CV_SET_ID ")
							.append(" FROM ")
									.append(" cv_set_authorization t ")
											.append(" LEFT JOIN	cv_set_authorization_system_site t1 ON t1.AUTHORIZATION_ID = t.ID ")
													.append(" LEFT JOIN system_site t2 ON t2.ID = t1.SYSTEM_SITE_ID ")
															.append(" WHERE ")
																	.append(" t2.ID = ? )");

			values.add(queryCVSet.getSiteList().get(0).getId());
		}
		
		sql.append(" ORDER BY t.modify_date DESC ");
		CVSetAuthorList = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSetAuthorQuery.class), values.toArray());
		
		for(CVSetAuthorQuery cvs : CVSetAuthorList){
			
			//授权站点
			String site_sql = "SELECT t1.DOMAIN_NAME AS domainName " +
							  		"FROM cv_set_authorization_system_site t " +
										"LEFT JOIN system_site t1 ON t1.ID = t.SYSTEM_SITE_ID " +
											"WHERE t.AUTHORIZATION_ID = "+cvs.getAuthorizationId();
			
			List<SystemSite> systemSite = getJdbcTemplate().query(site_sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
			if (systemSite.size() > 0) 		
				cvs.setSiteList(systemSite);
			
			//人物画像--学科
			String sql_exist = "select count(1) from CV_SET_USER_IMAGE where CV_SET_ID=" + cvs.getCvSetId();
			Long cnt =  getJdbcTemplate().queryForLong(sql_exist);					
			
			if (cnt > 0) {
				String sql_get = "select USERIMAGE_ID as id from CV_SET_USER_IMAGE where CV_SET_ID=" + cvs.getCvSetId();
				List<UserImage> userImageList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				List<UserImage> userImageList_ = new ArrayList<UserImage>();
				// 在这个地方处理，如果PropUnit的name重复，那么就把这个PropUnit的那么重置为""，否则就记录该name
				List<String> propList = new ArrayList<String>();//记录propUnit的name的集合
				for (UserImage userImage: userImageList) {
					//userImage = localUserImageManageDAO.getUserImageList(userImage).get(0);
					//YHQ,2017-09-11
					List<UserImage> userImageListQuery = localUserImageManageDAO.getUserImageList(userImage) ;
					if (userImageListQuery != null && userImageListQuery.size() > 0) {
						userImage = userImageListQuery.get(0);
					}
					
					if (userImage.getDepartmentPropList() != null && userImage.getDepartmentPropList().size() > 0) {
						//遍历该userImage中的DepartmentPropList，匹配name是否重复
						List<PropUnit> propUnitList = userImage.getDepartmentPropList();
						for(int index = 0; index < propUnitList.size(); index ++){
							if(propList.contains(propUnitList.get(index).getName())){
								userImage.getDepartmentPropList().get(index).setName("");
							} else {
								propList.add(userImage.getDepartmentPropList().get(index).getName());
							}
						}
						
						
					} 
					userImageList_.add(userImage);
						
				}
				cvs.setUserImageList(userImageList_);
			}
			
			
			sql_exist = "select count(1) from CV_SET_EXPERT where CV_SET_ID=" + cvs.getCvSetId();
			cnt = getJdbcTemplate().queryForLong(sql_exist);
			
			if (cnt > 0) {
				String sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 1 and CV_SET_ID=" + cvs.getCvSetId() + ")";				
				List<ExpertInfo> managerList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
				for (ExpertInfo info:managerList) {
					info = localExpertManageDAO.getExpertInfo(info.getId());
				}
				
				cvs.setManagerList(managerList);				
			}
			
			if(cvs.getEndDate() != null){
				//系统当前日期
				Date curtime = java.sql.Date.valueOf(new Date(System.currentTimeMillis()).toString());
				Date endtime = java.sql.Date.valueOf(cvs.getEndDate().toString());
				if(endtime.equals(curtime)){
					cvs.setAuthorStatus(1);
				}else if(endtime.before(curtime)){
					cvs.setAuthorStatus(2);
				}else if(endtime.after(curtime)){
					cvs.setAuthorStatus(1);
				}
			}
		}
		
		return CVSetAuthorList;
	}

	@Override
	public int updateCVSetAuthorization(List<Object> saveParams) {
		CVSet saveCV = (CVSet)saveParams.get(0);
		String siteIds = (String)saveParams.get(1);
		
		CVSetAuthorization CVSAuthor = saveCV.getcVSetAuthorization();
		
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("UPDATE cv_set_authorization SET ");
		if(null != CVSAuthor.getCvSetSerialNumber()){
			sql.append(" CV_SET_SERIAL_NUMBER = ?,");
			values.add(CVSAuthor.getCvSetSerialNumber());
		}
		if(null != CVSAuthor.getCvSetLevel()){
			sql.append(" CV_SET_LEVEL = ?,");
			values.add(CVSAuthor.getCvSetLevel());
		}
		if(null != CVSAuthor.getCvSetScore()){
			sql.append(" CV_SET_SCORE = ?,");
			values.add(CVSAuthor.getCvSetScore());
		}
		if(null != CVSAuthor.getCvSetCostType()){
			sql.append(" CV_SET_COST_TYPE = ?,");
			values.add(CVSAuthor.getCvSetCostType());
		}
		if(null != CVSAuthor.getCvSetCost()){
			sql.append(" CV_SET_COST = ?,");
			values.add(CVSAuthor.getCvSetCost());
		}
		if(null != CVSAuthor.getCvSetSign()){
			sql.append(" CV_SET_SIGN = ?,");
			values.add(CVSAuthor.getCvSetSign());
		}
		if(null != CVSAuthor.getCvSetStartDate()){
			sql.append(" CV_SET_START_DATE = ?,");
			values.add(CVSAuthor.getCvSetStartDate());
		}
		if(null != CVSAuthor.getCvSetEndDate()){
			sql.append(" CV_SET_END_DATE = ?,");
			values.add(CVSAuthor.getCvSetEndDate());
		}
		if(null != CVSAuthor.getCvSetRegistrationStop()){
			sql.append(" CV_SET_REGISTRATION_STOP = ?,");
			values.add(CVSAuthor.getCvSetRegistrationStop());
		}
		if(null != CVSAuthor.getCvSetBreakDays()){
			sql.append(" CV_SET_BREAK_DAYS = ?");
			values.add(CVSAuthor.getCvSetBreakDays());
		}
		sql.append(" WHERE ID = ?");
		values.add(CVSAuthor.getId());
		
		int count = getJdbcTemplate().update(sql.toString(),values.toArray());
		
		if(count > 0){
			if(saveCV.getCVScheduleList() != null && saveCV.getCVScheduleList().size()>0){
				String sql_del_sche = "DELETE FROM cv_set_authorization_cv_schedule WHERE AUTHORIZATION_ID = ?";
				getSimpleJdbcTemplate().update(sql_del_sche, CVSAuthor.getId());
				for(CVSchedule schedule : saveCV.getCVScheduleList()){
					java.sql.Date startDate =  new java.sql.Date(
														schedule.getStart_date().getTime()
													);
					
					java.sql.Date endtDate =  new java.sql.Date(
														schedule.getEnd_date().getTime()
													);
					
					Long scheId = this.getNextId("cv_set_authorization_cv_schedule");//自增ID
					String addAuthorCVSchedule = "INSERT INTO cv_set_authorization_cv_schedule " +
													"(ID,AUTHORIZATION_ID,CV_SCHEDULE_ID,START_DATE," +
														"END_DATE) VALUES(?,?,?,?,?)";
					
					int count1 = getSimpleJdbcTemplate().update(addAuthorCVSchedule, scheId, CVSAuthor.getId(), schedule.getId(), startDate, endtDate);
				}
			}
			
			//授权区域
			List<PropUnit> prop_course = saveCV.getCourseList();
			if(prop_course != null && prop_course.size()>0){
				String sql_prop_course = "DELETE FROM cv_set_authorization_region WHERE AUTHORIZATION_ID = ?";
				getSimpleJdbcTemplate().update(sql_prop_course, CVSAuthor.getId());
				for(int i=0; i<prop_course.size();i++){
					Long propId = this.getNextId("cv_set_authorization_region");//自增ID
					sql_prop_course = "INSERT INTO cv_set_authorization_region (ID,AUTHORIZATION_ID,PROP_VAL_ID) VALUES (?,?,?)";
					getSimpleJdbcTemplate().update(sql_prop_course, propId,CVSAuthor.getId(),prop_course.get(i).getId());
				}
			}
			
			if(!StringUtil.checkNull(siteIds)){
				if(siteIds.charAt(siteIds.length() - 1) == ','){
					siteIds = siteIds.substring(0, siteIds.length()-1);
				}
				String[] siteArr = siteIds.split(",");
				String sitesql = "DELETE FROM cv_set_authorization_system_site WHERE AUTHORIZATION_ID = ?";
				getSimpleJdbcTemplate().update(sitesql, CVSAuthor.getId());
				for(String siteId : siteArr){
					if(StringUtils.isEmpty(siteId))
						continue;
					Long authorSiteId = this.getNextId("cv_set_authorization_system_site");//自增ID
					sitesql = "INSERT INTO cv_set_authorization_system_site (ID,AUTHORIZATION_ID,SYSTEM_SITE_ID) VALUES (?,?,?)";
					getJdbcTemplate().update(sitesql,authorSiteId,CVSAuthor.getId(),siteId);
				}
			}
		}else{
			count = 0;
		}
		return count;
	}

	@Override
	public List<CVSet> getCVSetListForRelease(CVSet queryCVSet, Long flag) {
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		
		sql.append("SELECT DISTINCT t.* ")
			.append("FROM ")
			.append("cv_set AS t ")
			.append("LEFT JOIN cv_set_user_image AS c ON t.ID = c.CV_SET_ID ")
			.append("LEFT JOIN qm_user_image_prop AS u ON c.USERIMAGE_ID = u.USERIMAGE_ID ")
			.append("LEFT JOIN CV_REGION AS cr ON t.ID = cr.CV_SET_ID ")
			.append("LEFT JOIN cv_set_org sorg ON sorg.CV_ID = t.ID ")
			.append("LEFT JOIN peixun_org org ON org.ID = sorg.ORG_ID ")
			.append("WHERE ")
			.append("1 = 1 ");
			if(flag == 1)
				sql.append("AND t. STATUS IN(5) ");
			else
				sql.append("AND t. STATUS IN(3,6) ");
		sql.append("AND EXISTS( ")
			.append("SELECT * FROM cv_set_authorization t1 ")
			.append("WHERE  ")
			.append("t1.CV_SET_ID = t.ID ")
			.append("AND  ")
			.append("t1.CV_SET_END_DATE >= CURDATE() ")
			.append(") ");
		
		
		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size()>0){			
			sql.append(" and u.PROP_ID IN (");			
			for(int i=0; i<queryCVSet.getCourseList().size(); i++){
				if(i == (queryCVSet.getCourseList().size()-1)){
					sql.append(queryCVSet.getCourseList().get(i).getId());
				}else{
					sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
				}
			}
			sql.append(")");
			
		}
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t.name like ?");
			values.add("%"+queryCVSet.getName()+"%");
		}
		if(!StringUtils.checkNull(queryCVSet.getMaker())){
			sql.append(" and t.id in(select  cse.cv_set_id from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id  where ei.name like ? and cse.type = 1 )");
			values.add("%"+queryCVSet.getMaker()+"%");
		}
		if(queryCVSet.getForma() != null && queryCVSet.getForma() > 0){
			sql.append(" and t.FORMA = "+ queryCVSet.getForma());
		}
		if(queryCVSet.getStatus() != null && queryCVSet.getStatus() > 0){
			sql.append(" and t.STATUS = "+ queryCVSet.getStatus());
		}
		if(!StringUtils.checkNull(queryCVSet.getOrgName())){
			sql.append(" and org.`NAME` LIKE  ?");
			values.add("%"+queryCVSet.getOrgName()+"%");
		}
		if(queryCVSet.getLevel() != null && queryCVSet.getLevel() > 0){
			sql.append(" and t.LEVEL = "+ queryCVSet.getLevel());
		}
		if(queryCVSet.getScore() != null && !"".equals(queryCVSet.getScore())){
			sql.append(" and t.SCORE = "+ queryCVSet.getScore());
		}
		if(queryCVSet.getSign() != null){
			if(queryCVSet.getSign().equals("1")){
				sql.append(" and t.sign like '%公需科目%'");
			}else if(queryCVSet.getSign().equals("2")){
				sql.append(" and t.sign like '%指南%'");
			}else if(queryCVSet.getSign().equals("3")){
				sql.append(" and t.sign like '%海外%'");
			}else if(queryCVSet.getSign().equals("4")){
				sql.append(" and t.sign like '%乡医%'");
			}
		}
		if(queryCVSet.getPropIds() != null && !queryCVSet.getPropIds().equals("")){
			sql.append(" and cr.REGION_ID IN (" + queryCVSet.getPropIds() + ")");
		}
		sql.append(" ORDER BY t.CREATE_DATE DESC");
		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), values.toArray());
		
		List<CVSet> result_list = new ArrayList<CVSet>();
		
		for(CVSet cvSet:list){
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			String site_sql = "";
			if (queryCVSet.getSiteList() != null && !queryCVSet.getSiteList().isEmpty()) {
				if(queryCVSet.getSiteList().get(0).getDomainName() != null && !queryCVSet.getSiteList().get(0).getDomainName().equals("")) 
					site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId() + " and s.DOMAIN_NAME like  '%" + queryCVSet.getSiteList().get(0).getDomainName() + "%'";
				else
					site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId();
			}else{
				site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId();
			}
			List<SystemSite> systemSite = getJdbcTemplate().query(site_sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
			if (systemSite.size() > 0) 		
			cvSet.setSiteList(systemSite);
			
			String sql_exist = "select count(1) from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
			Long cnt =  getJdbcTemplate().queryForLong(sql_exist);					
			
			if (cnt > 0) {
				String sql_get = "select USERIMAGE_ID as id from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
				List<UserImage> userImageList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				List<UserImage> userImageList_ = new ArrayList<UserImage>();
				List<String> propList = new ArrayList<String>();//记录propUnit的name的集合
				for (UserImage userImage: userImageList) {
					List<UserImage> userImageListQuery = localUserImageManageDAO.getUserImageList(userImage) ;
					if (userImageListQuery != null && userImageListQuery.size() > 0) {
						userImage = userImageListQuery.get(0);
					}
					if (userImage.getDepartmentPropList() != null && userImage.getDepartmentPropList().size() > 0) {
						//遍历该userImage中的DepartmentPropList，匹配name是否重复
						List<PropUnit> propUnitList = userImage.getDepartmentPropList();
						for(int index = 0; index < propUnitList.size(); index ++){
							if(propList.contains(propUnitList.get(index).getName())){
								userImage.getDepartmentPropList().get(index).setName("");
							} else {
								propList.add(userImage.getDepartmentPropList().get(index).getName());
							}
						}
						
						
					} 
					userImageList_.add(userImage);
				}
				cvSet.setUserImageList(userImageList_);
			}
			
			sql_exist = "select count(1) from CV_SET_EXPERT where CV_SET_ID=" + cvSet.getId();
			cnt = getJdbcTemplate().queryForLong(sql_exist);
			
			if (cnt > 0) {
				String sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 1 and CV_SET_ID=" + cvSet.getId() + ")";				
				List<ExpertInfo> managerList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
				for (ExpertInfo info:managerList) {
					info = localExpertManageDAO.getExpertInfo(info.getId());
				}
				
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 2 and CV_SET_ID=" + cvSet.getId() + ")";
				List<ExpertInfo> teacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
				for (ExpertInfo info:teacherList) {
					info = localExpertManageDAO.getExpertInfo(info.getId());
				}
				
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 3 and CV_SET_ID=" + cvSet.getId() + ")";
				List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
				for (ExpertInfo info:otherTeacherList) {
					info = localExpertManageDAO.getExpertInfo(info.getId());
				}
				
				cvSet.setManagerList(managerList);
				cvSet.setTeacherList(teacherList);
				cvSet.setOtherTeacherList(otherTeacherList);
				
			}
			
			
			// YHQ，2017-05-30
			String sql_schedule = "select CV_ID as ID, START_DATE, END_DATE,schedule_id from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId() + ") order by sequenceNum asc";			
			List<CVSchedule> _scheduleList = getJdbcTemplate().query(sql_schedule, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
			List<CVSchedule> scheduleList = new ArrayList<CVSchedule>();
			
			for(CVSchedule schedule:_scheduleList){				
				
				List<CV> cvList = new ArrayList<CV>();
				CV  queryCV = new CV();
				
				queryCV.setId(schedule.getId());
				cvList = localCVManageDAO.getCVList(queryCV);
				
				//YHQ，2017-06-12
				if (cvList != null && cvList.size() > 0) {
					CVSchedule sch = new CVSchedule(cvList.get(0));
					
					sch.setId(schedule.getId());
					sch.setStart_date(schedule.getStart_date());
					sch.setEnd_date(schedule.getEnd_date());	
					sch.setSchedule_id(schedule.getSchedule_id());
					scheduleList.add(sch);
				}
				
			}
			cvSet.setCVScheduleList(scheduleList);
			
			//YHQ，2017-05-15
			if (queryCVSet.getId() != null && queryCVSet.getId() > 0) {
				String bvoString = "select cv_set_id as id1, book_name as key1, book_url as value1  from cv_set_refereebook where cv_set_id = " + queryCVSet.getId() ;
				List<BaseVO> refereeBookList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseVO.class));
				cvSet.setRefereeBookList(refereeBookList);
				
				bvoString = "select cv_set_id as id1, knowledgebase_name as key1, knowledgebase_url as value1  from cv_set_knowledgebase where cv_set_id = " + queryCVSet.getId() ;
				List<BaseVO> knowledgeBaseList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseVO.class));
				cvSet.setKnowledgeBaseList(knowledgeBaseList);
				
				bvoString = "select cv_set_id as id1, reference_name as key1, reference_url as value1  from cv_set_referencelately where cv_set_id = " + queryCVSet.getId() ;
				List<BaseVO> referenceLatelyList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseVO.class));
				cvSet.setReferenceLatelyList(referenceLatelyList);				
			}
			
			result_list.add(cvSet);
			
		}
		
		return result_list;
	}

	@Override
	public CVSetAuthorCheck getAuthorBeforeCheck(Long cvSetId) {
		
		CVSetAuthorCheck check = new CVSetAuthorCheck();
		int proviceNum = 0;
		int authorAllProNum = 0;
		List<ExamProp> allExamPropList = null;
		List<ExamProp> examPropList = null;
		//所有省数目
		String provice_sql = "SELECT * FROM exam_prop_val t WHERE t.TYPE = 20";
		allExamPropList =  getJdbcTemplate().query(provice_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
		
		//拿到项目所有授权过的省
		String cvSetAllPro_sql = "SELECT t3.* "+
								 " FROM "+
								 " 	cv_set_authorization t "+
								 " LEFT JOIN cv_set t1 ON t1.ID = t.CV_SET_ID "+
								 " LEFT JOIN cv_set_authorization_region t2 ON t2.AUTHORIZATION_ID = t.ID "+
								 " LEFT JOIN exam_prop_val t3 ON t3.ID = t2.PROP_VAL_ID "+
								 " LEFT JOIN sub_sys_prop_val t4 ON t4.PROP_VAL_ID = t3.ID "+
								 " WHERE "+
								 " 	t1.ID = ? "+
								 " AND t3.`NAME` IS NOT NULL "+
								 " GROUP BY t3.`NAME`";
		
		examPropList =  getJdbcTemplate().query(cvSetAllPro_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), cvSetId);
		
		if(null != allExamPropList && allExamPropList.size() > 0){
			authorAllProNum = allExamPropList.size();
		}
		if(null != examPropList && examPropList.size() > 0){
			proviceNum = examPropList.size();
		}
		if(authorAllProNum > 0){
			if(authorAllProNum == proviceNum){
				check.setNumericConstants(0);//全国
				check.setExamPropList(examPropList);
			}else if(authorAllProNum > proviceNum){
				check.setNumericConstants(1);
				List<ExamProp> difPro = getDiffrent(allExamPropList, examPropList);
				if(difPro != null && difPro.size() > 0){
					check.setExamPropList(difPro);
				}else{
					check.setExamPropList(examPropList);
				}
			}
		}else{
			check.setNumericConstants(-1);//全国
			check.setExamPropList(null);
		}
		return check;
	}
	
	private List<ExamProp> getDiffrent(List<ExamProp> exa1, List<ExamProp> exa2) {
        long st = System.nanoTime();
         List<ExamProp> maxList = exa1;
         List<ExamProp> minList = exa2;
         if(exa2.size()>exa1.size()){
             maxList = exa2;
             minList = exa1;
         }
         
         //筛选出未被覆盖的省/市
         for(int i = 0; i<maxList.size(); i++){
        	 for(int j = 0; j<minList.size(); j++){
    			if(minList.get(j).getId().equals(maxList.get(i).getId())){
    				maxList.remove(i);
    					i --;
    				break;
    			}
    		 }
	     }
        System.out.println("消耗时间： "+(System.nanoTime()-st));
        return maxList;   
    }

	@Override
	public int deleteAuthor(Long cvSetId, Long authorizationId) {
		
		int count = 1;
		//try{
			String author_sql = "DELETE FROM cv_set_authorization WHERE ID = ? ";
			String schedule_sql = "DELETE FROM cv_set_authorization_cv_schedule WHERE AUTHORIZATION_ID = ? ";
			String author_region_sql = "DELETE FROM cv_set_authorization_region WHERE AUTHORIZATION_ID = ? ";
			String author_site_sql = "DELETE FROM cv_set_authorization_system_site WHERE AUTHORIZATION_ID = ? ";
			
			//删除授权信息关联表信息
			getJdbcTemplate().update(author_sql, authorizationId);
			getJdbcTemplate().update(schedule_sql, authorizationId);
			getJdbcTemplate().update(author_region_sql, authorizationId);
			getJdbcTemplate().update(author_site_sql, authorizationId);
		//}catch(Exception ex){
		//	ex.printStackTrace();
		//	count = 0;
		//}
		return count;
	}

	@Override
	public int updateCVSetForRelease(Long cvSetId, Long flag) {
		
		String release_and_offline_sql = "";
		
		if(flag == 5L){//项目发布--status=5
			release_and_offline_sql = "UPDATE cv_set t SET t.`STATUS` = 5 WHERE t.ID = ?";
			return getJdbcTemplate().update(release_and_offline_sql, cvSetId);
		}else if(flag == 6L){//项目下线--status=6
			release_and_offline_sql = "UPDATE cv_set t SET t.`STATUS` = 6 WHERE t.ID = ?";
			return getJdbcTemplate().update(release_and_offline_sql, cvSetId);
		}
		
		return 0;
	}

	@Override
	public int getFaceteachCount(Long cvSetId) {
		int faceNum = 0;
		
		String sql = "SELECT SUM(t.people_number) FROM cv_set_schedule_faceteach t WHERE t.cv_set_id = " +cvSetId;
		faceNum = getJdbcTemplate().queryForInt(sql);
		
		return faceNum;
	}
	
	@Override
	public void SendMessageForUser(CVSet cvset, Long flag) {
		
		//根据当前项目拿到符合发送系统消息的用户
		String sqlStr = "SELECT t.SYSTEM_USER_ID AS systemUserId FROM log_study_cv_set t "+
					    	"LEFT JOIN cv_set t1 ON t1.ID = t.CV_SET_ID " +
					    		"WHERE t.CV_SET_ID = ? AND t.STATE = 1";
		
		List<LogStudyCVSet> cvsetList = getJdbcTemplate().query(sqlStr, 
				ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVSet.class), cvset.getId());
		
		String batchSql = "";
		batchSql = "INSERT INTO SYSTEM_MESSAGE (SYSTEM_USER_ID,MESSAGE_CONTENT,IS_READ) " +
				"values (:system_user_id, :message_content, :is_read)";
		List<SystemMessageModel> sysMsgList = new ArrayList<SystemMessageModel>();
		
		if(cvsetList != null && cvsetList.size() > 0){
			for(LogStudyCVSet logcvset : cvsetList ){
				SystemMessageModel systemMessage = new SystemMessageModel();
				//systemMessage.setId(getNextId("SYSTEM_MESSAGE"));
				systemMessage.setSystem_user_id(logcvset.getSystemUserId());
				if(flag == 5L){
					if(cvset.getStatus() != 6)//只有下过线，再次上线的项目才通知 
						continue;
					systemMessage.setMessage_content("您之前参与学习的“"+cvset.getName()+"”，经过编辑后已经重新上线，您可以继续学习啦~");
				}else if(flag == 6L){
					systemMessage.setMessage_content("您正在学习的“"+cvset.getName()+"”，已经下线，您将暂时无法继续学习，请耐心等待项目重新上线或其他通知~");
				}
				
				systemMessage.setIs_read(1);
				
				sysMsgList.add(systemMessage);
				
				System.out.println("==========用户："+logcvset.getSystemUserId()+"[项目]："+cvset.getId());
			}
			
			this.getSimpleJdbcTemplate()
										.batchUpdate(batchSql, SqlParameterSourceUtils.createBatch(sysMsgList.toArray()));
		}
		
	}
	
	 /**
       * * Create an array of BeanPropertySqlParameterSource objects populated
       * with data * from the values passed in. This will define what is included
       * in a batch operation. * @param beans object array of beans containing the
       * values to be used * @return an array of SqlParameterSource
	  */
	public static SqlParameterSource[] createBatch(Object[] beans) {
		BeanPropertySqlParameterSource[] batch = new BeanPropertySqlParameterSource[beans.length];
	      for (int i = 0; i < beans.length; i++) {
	         Object bean = beans[i];
	         batch[i] = new BeanPropertySqlParameterSource(bean);
	     }
	     return batch;
	}

	@Override
	public List<CVSet> getCVSetCheckForRelease(String _cvSetIds, Long flag) {
		
		
		List<CVSet> newList = new ArrayList<CVSet>();
		String cvset_sql = "SELECT t.* FROM cv_set t WHERE t.ID IN ("+_cvSetIds+")";
		List<CVSet> cvsetlist =  getJdbcTemplate().query(cvset_sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		if(flag == 5L){
			if(cvsetlist != null && cvsetlist.size() > 0){
				for(CVSet cvset : cvsetlist){
					if(cvset.getStatus() == 6L){
						newList.add(cvset);
					}
				}
			}
		}else{
			newList = cvsetlist;
		}
		
		return newList;
	}

}
