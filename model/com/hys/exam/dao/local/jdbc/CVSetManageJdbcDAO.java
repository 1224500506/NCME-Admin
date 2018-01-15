package com.hys.exam.dao.local.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.xml.security.utils.Constants;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.dao.local.CVSetManageDAO;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.dao.local.UserImageManageDAO;
import com.hys.exam.model.BaseVO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CvsetQualityHistory;
import com.hys.exam.model.ExCVSet;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.UserImage;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.exam.model.SystemSite;

public class CVSetManageJdbcDAO extends BaseDao implements CVSetManageDAO {
	private CVManageDAO localCVManageDAO;	
	private UserImageManageDAO localUserImageManageDAO;
	private ExpertManageDAO localExpertManageDAO;	
	
	SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @author   杨红强
	 * @time     2017-06-06	 
	 * @return   
	 * 方法说明： 保存项目中的班次安排
	 */
	@Override
	public void saveCVSetScheduleFaceTeach(List<CVSetScheduleFaceTeach> cvSetScheduleFaceTeachList, Long cvSetId) {
		if (cvSetId != null && cvSetId > 0) {
			String sql = "delete from cv_set_schedule_faceteach where cv_set_id = " + cvSetId ;
			getJdbcTemplate().update(sql) ;
		}		
		if (cvSetScheduleFaceTeachList != null && cvSetScheduleFaceTeachList.size() > 0) {
			String sql = "" ;
			int sequenceNum = 0 ;
			for (CVSetScheduleFaceTeach cssftObj : cvSetScheduleFaceTeachList) {
				sequenceNum++ ;
				String sql1 = "insert into cv_set_schedule_faceteach (cv_set_id,class_name,people_number,train_place," ;
				String sql2 = "route_way,contact_way,sequenceNum) values (" + cssftObj.getCv_set_id() + ",'第" +sequenceNum + "期'," + cssftObj.getPeople_number() + ",'" + cssftObj.getTrain_place() + "',";
				//sql = "insert into cv_set_schedule_faceteach (cv_set_id,class_name,people_number,train_place,train_starttime,train_endtime,contact_way,sequenceNum) values (?,?,?,?,?,?,?,?) " ;
				
				if(!StringUtils.checkNull(cssftObj.getTrainStartTime())) {
					sql1 += "train_starttime," ;
					sql2 += "'" + cssftObj.getTrainStartTime() + "'," ;
				}
				if(!StringUtils.checkNull(cssftObj.getTrainEndTime())) {
					sql1 += "train_endtime," ;
					sql2 += "'" + cssftObj.getTrainEndTime() + "'," ;
				}
				if(!StringUtils.checkNull(cssftObj.getLession_starttime())) {
					sql1 += "lession_starttime," ;
					sql2 += "'" + cssftObj.getLession_starttime() + "'," ;
				}
				if(!StringUtils.checkNull(cssftObj.getLession_endtime())) {
					sql1 += "lession_endtime," ;
					sql2 += "'" + cssftObj.getLession_endtime() + "'," ;
				}
				sql2 += "'" + cssftObj.getRoute_way() +"','" + cssftObj.getContact_way() + "'," + sequenceNum + ")";
				
				sql = sql1 + sql2 ;
				
				getJdbcTemplate().update(sql);
			}
		}
	}
	/**
	 * @author   杨红强
	 * @time     2017-06-06	 
	 * @return   
	 * 方法说明： 获取项目中的班次安排
	 */
	@Override
	public List<CVSetScheduleFaceTeach> queryCVSetScheduleFaceTeachByCVsetId(Long cvSetId) {
		List<CVSetScheduleFaceTeach> cssftObjList = null ;
		if (cvSetId != null && cvSetId > 0) {
			String sql = "select * from cv_set_schedule_faceteach where cv_set_id = " + cvSetId ;
			cssftObjList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CVSetScheduleFaceTeach.class));
		}
		
		return cssftObjList ;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-05-30
	 * @param    cvId
	 * @return   
	 * 方法说明： 保存项目中课程顺序
	 */
	public void saveScheduleSequence(Long cvSetId, String scheduleIds) {
		if (cvSetId != 0 && scheduleIds != null) {
			String[] scheduleIdAll = scheduleIds.split(",") ;
			if (scheduleIdAll != null && scheduleIdAll.length > 0) {
				String sql = "" ;
				for (int i = 0 ; i < scheduleIdAll.length ; i++) {
					sql = "update CV_SCHEDULE set sequenceNum = " + (i+1) + " where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID= " + cvSetId + ") and cv_id = " + scheduleIdAll[i] ;
					int resNum = getJdbcTemplate().update(sql) ;
				}
			}
		}
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-05-26
	 * @param    cvId
	 * @return   
	 * 方法说明： 从项目中删除课程
	 */
	public void deleteCVfromCvsetByCvId(Long cvId) {
		String sql_group_class_info = "delete from group_class_info  where class_parent_id = " + cvId ;
		String sql_cv_unit_ref_quality = "delete from cv_unit_ref_quality  where UNIT_ID in (select UNIT_ID from cv_ref_unit  where CV_ID = " + cvId +" ) " ;
		String sql_cv_unit = "delete from cv_unit  where ID in (select UNIT_ID from cv_ref_unit  where CV_ID = " + cvId + " )" ;
		String sql_cv_ref_unit = "delete from cv_ref_unit  where CV_ID = " + cvId  ;
		String sql_cv_set_schedule = "delete from cv_set_schedule where CV_SCHEDULE_ID in (select SCHEDULE_ID from cv_schedule where CV_ID = " + cvId + ")" ;
		String sql_cv_schedule = "delete from cv_schedule where CV_ID = " + cvId ;
		String sql_cv = "delete from cv where id = " + cvId ;
		
		String[] allSql = new String[7] ;
		
		allSql[0] = sql_group_class_info ; 
		allSql[1] = sql_cv_unit_ref_quality ;
		allSql[2] = sql_cv_unit ;
		allSql[3] = sql_cv_ref_unit ;
		allSql[4] = sql_cv_set_schedule ;
		allSql[5] = sql_cv_schedule ;
		allSql[6] = sql_cv ;
		//getJdbcTemplate().update("SET FOREIGN_KEY_CHECKS=0");		
		getJdbcTemplate().batchUpdate(allSql) ;
		//getJdbcTemplate().update("SET FOREIGN_KEY_CHECKS=1");
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-29
	 * @param    CVSet
	 * @return   List<CVSet>
	 * 方法说明： //YHQ，查询绑定学习卡类型的项目，2017-03-29
	 */
	@Override
	public List<CVSet> cvSetListByBindCardType(CVSet queryCVSet) {
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		
//		sql.append("SELECT t.* FROM system_card_type_cv_set sctcs ");
		sql.append("SELECT DISTINCT(t.id), t.* FROM system_card_type_cv_set sctcs ");
		sql.append(" left join cv_set  t  on t.ID = sctcs.CV_SET_ID ");
		// CHY 2017-04-21
		sql.append(" left join cv_set_user_image c  on t.ID=c.CV_SET_ID "); 
		sql.append(" left join qm_user_image_prop  u on  c.USERIMAGE_ID=u.USERIMAGE_ID "); 
		sql.append(" where t.STATUS = 5 ");
		
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t.name like ?");
			values.add("%"+queryCVSet.getName()+"%");
		}
		if (!StringUtils.checkNull(queryCVSet.getMaker())) {
			sql.append(" and t.maker like ?");
			values.add(queryCVSet.getMaker());
		}
		// CHY 2017-04-20
		if(queryCVSet.getId()!=null){
			sql.append(" and sctcs.CARDTYPE_ID = ? ");
			values.add(queryCVSet.getId());
		}
		
		//search by xueke
		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size()>0){			
			sql.append(" and u.PROP_ID IN (") ;			
			for(int i = 0; i<queryCVSet.getCourseList().size(); i++){
				if(i == (queryCVSet.getCourseList().size()-1)){
					sql.append(queryCVSet.getCourseList().get(i).getId());
				}else{
					sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
				}
			}
			sql.append(")");
		}
		
		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), values.toArray());
		List<CVSet> result_list = new ArrayList<CVSet>();
		
		for(CVSet cvSet:list){			
			String sql_exist = "select count(1) from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
			Long cnt =  getJdbcTemplate().queryForLong(sql_exist);					
			
			if (cnt > 0) {
				String sql_get = "select USERIMAGE_ID as id from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
				List<UserImage> userImageList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				List<UserImage> userImageList_ = new ArrayList<UserImage>();
				for(UserImage userImage: userImageList){
					/*userImage = localUserImageManageDAO.getUserImageList(userImage).get(0);
					userImageList_.add(userImage);
					*/
					//YHQ,2017-09-13,修复get(0)容易引发的数组越界异常
					List<UserImage> userImageListQuery = localUserImageManageDAO.getUserImageList(userImage) ;
					if (userImageListQuery != null && userImageListQuery.size() > 0) {
						userImage = userImageListQuery.get(0);
						userImageList_.add(userImage);
					}
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
				
				cvSet.setManagerList(managerList);				
			}
			
			result_list.add(cvSet);			
		}
		
		return result_list;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-29
	 * @param    CVSet
	 * @return   List<CVSet>
	 * 方法说明： YHQ，查询未绑定学习卡类型的项目，2017-03-29
	 */
	@Override
	public List<CVSet> cvSetListByUnBindCardType(CVSet queryCVSet) {
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		
		sql.append("SELECT DISTINCT t.* FROM cv_set t ");
		//CHY 2017-04-21
		sql.append(" left join cv_set_user_image c  on t.ID=c.CV_SET_ID "); 
		sql.append(" left join qm_user_image_prop  u on  c.USERIMAGE_ID=u.USERIMAGE_ID "); 
		sql.append(" where t.STATUS = 5 ");
						
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t.name like ?");
			values.add("%"+queryCVSet.getName()+"%");
		}
		if (!StringUtils.checkNull(queryCVSet.getMaker())) {
			sql.append(" and t.maker like ?");
			values.add(queryCVSet.getMaker());
		}
		
		//search by xueke
		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size()>0){			
			sql.append(" and u.PROP_ID IN (") ;			
			for(int i = 0; i<queryCVSet.getCourseList().size(); i++){
				if(i == (queryCVSet.getCourseList().size()-1)){
					sql.append(queryCVSet.getCourseList().get(i).getId());
				}else{
					sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
				}
			}
			sql.append(")");
		}
		
		Long cardTypeId = queryCVSet.getId() ;
		if (cardTypeId != null && cardTypeId > 0) {
			sql.append(" and t.ID not in (SELECT CV_SET_ID FROM system_card_type_cv_set where CARDTYPE_ID = " ) ;
			sql.append(cardTypeId + ") ");
		}
		
		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), values.toArray());
		List<CVSet> result_list = new ArrayList<CVSet>();
		
		for(CVSet cvSet:list){			
			String sql_exist = "select count(1) from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
			Long cnt =  getJdbcTemplate().queryForLong(sql_exist);					
			
			if (cnt > 0) {
				String sql_get = "select USERIMAGE_ID as id from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
				List<UserImage> userImageList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				List<UserImage> userImageList_ = new ArrayList<UserImage>();
				for(UserImage userImage: userImageList){
					/*userImage = localUserImageManageDAO.getUserImageList(userImage).get(0);
					userImageList_.add(userImage);*/
					//YHQ,2017-09-13,北软评测IndexOutOfBoundsException
					List<UserImage> userImageListQuery = localUserImageManageDAO.getUserImageList(userImage) ;
					if (userImageListQuery != null && userImageListQuery.size() > 0) {
						userImage = userImageListQuery.get(0);
						userImageList_.add(userImage);
					} else {
						System.out.println();
					}
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
				
				cvSet.setManagerList(managerList);				
			}
			
			result_list.add(cvSet);			
		}
		
		return result_list;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-18
	 * @param    CVSet
	 * @return   boolean
	 * 方法说明： YHQ，查询项目是否绑定学习卡类型，2017-03-18
	 */
	@Override
	public boolean bindCardTypeByCVSet(CVSet cvSet) {
		boolean resFlag = false ;
		if (cvSet != null && cvSet.getId() != 0L) {
			String sql = "select count(*) from system_card_type_cv_set where CV_SET_ID = " + cvSet.getId();
			
			//SCP 项目管理删除项目时无此ID
			if (cvSet.getCityId() != 0L) {
				sql += " and CARDTYPE_ID = " + cvSet.getCityId();
			}
			
			int rsNum = getJdbcTemplate().queryForInt(sql) ;
			if (rsNum > 0) resFlag = true ;
		}
		return resFlag ;
	}
	
	@Override
	public List<CVSet> getCVSetList(CVSet queryCVSet) {	
		
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("SELECT DISTINCT t.* FROM cv_set as t JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on t.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID");
		//添加根据站点查询条件
//		if(queryCVSet.getType()!=null && !queryCVSet.getType().equals("") && queryCVSet.getType()==-10){
//			sql.append("SELECT DISTINCT t.* FROM cv_set as t JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on t.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID");
//		}else{
//			sql.append("SELECT DISTINCT t.* FROM cv_set as t JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on t.ID=c.CV_SET_ID " +
////					"JOIN cv_set_system_site AS sp ON t.ID =  sp.CV_SET_ID JOIN system_site s ON sp.SYSTEM_SITE_ID = s.ID " +
//					" and c.USERIMAGE_ID=u.USERIMAGE_ID");
//		}
		//SELECT t.* FROM cv_set t, cv_set_user_image tu, qm_user_image u, qm_user_image_prop p WHERE u.id=p.USERIMAGE_ID and t.ID=tu.CV_SET_ID and tu.USERIMAGE_ID=p.USERIMAGE_ID and p.PROP_ID IN (147)
		if(queryCVSet.getId()>0){
			sql.append(" and t.id=").append(queryCVSet.getId());
		}else {
			if(!StringUtils.checkNull(queryCVSet.getName())){
				sql.append(" and t.name like ?");
				values.add("%"+queryCVSet.getName()+"%");
			}
			
			if(queryCVSet.getStatus() != null && queryCVSet.getStatus() > 0){
				sql.append(" and t.status ="+queryCVSet.getStatus());
			}else if(queryCVSet.getStatus() == 0){//项目审核里添加查看所有状态的查询情况---taoliang
				sql.append(" and t.status in (1,2,3,4,5,6)" );
			}else if(queryCVSet.getStatus() == -1){				//chenlb add  项目授权里，只要看到：已审核，已授权，已下线  这几种
				sql.append(" and t.status in (3,4,5,6)");
			}
		}
		if(queryCVSet.getType()!=null && !queryCVSet.getType().equals("") && queryCVSet.getType()==-10){
			//queryCVSet.getType()为-10时，是我的项目列表查询，什么也不做
		}else{
			if(queryCVSet.getSiteList() != null && queryCVSet.getSiteList().size() > 0){
				sql.append(" AND t.ID in (SELECT sp.CV_SET_ID from cv_set_system_site AS sp JOIN system_site s ON sp.SYSTEM_SITE_ID = s.ID " +
						" where s.DOMAIN_NAME LIKE ?)");

				values.add("%"+queryCVSet.getSiteList().get(0).getDomainName()+"%");
			}
		}
		if(!StringUtils.checkNull(queryCVSet.getMaker())){
//			if (queryCVSet.getId() != null && queryCVSet.getId() < 0) {
				sql.append(" and t.id in(select  cse.cv_set_id from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id  where ei.name like ? )");
				values.add("%"+queryCVSet.getMaker()+"%");
//			}
		/*	else if(queryCVSet.getFlag() !=null && queryCVSet.getFlag().equals("qualify")){
				sql.append(" and status in (2,3,4)  and t.id not in (select  cse.cv_set_id  from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id where ei.name like ? ) ");
				values.add("%"+queryCVSet.getMaker()+"%");
			}else if(queryCVSet.getFlag() !=null && queryCVSet.getFlag().equals("distribute")){
				sql.append(" and status = 4");
			}else{
				sql.append(" and status in (2,3,4)  and t.id not in(select  cse.cv_set_id  from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id  where ei.name like ? ) ");
				values.add("%"+queryCVSet.getMaker()+"%");
			}*/
		}
		//项目授课方式
		if(queryCVSet.getForma() != null && queryCVSet.getForma()>0){
			sql.append(" and t.forma=? ");
			values.add(queryCVSet.getForma());
		}
		if(!StringUtils.checkNull(queryCVSet.getMaker2())){
			sql.append(" and t.maker like ? ");
			values.add("%"+queryCVSet.getMaker2()+"%");
		}
		
		if(!StringUtils.checkNull(queryCVSet.getDeli_man())){
			sql.append(" and t.deli_man like ? ");
			values.add("%"+queryCVSet.getDeli_man()+"%");
		}
		
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t.name like ?");
			values.add("%"+queryCVSet.getName()+"%");
		}
		if(queryCVSet.getStatus() != null && queryCVSet.getStatus() > 0){
			sql.append(" and t.status ="+queryCVSet.getStatus());
		}
		if(queryCVSet.getSign() != null){
			if(queryCVSet.getSign().equals("1")){
				sql.append(" and t.sign like '%公需科目%'");
			}
			else if(queryCVSet.getSign().equals("2")){
				sql.append(" and t.sign like '%指南%'");
			}
		}
		if(queryCVSet.getLevel() != null){
			sql.append(" and t.level ="+queryCVSet.getLevel());
		}
		if(queryCVSet.getScore() != null){
			sql.append(" and t.score ="+queryCVSet.getScore());
		}
		//search by xueke
		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size()>0){			
			sql.append(" and t.ID=c.CV_SET_ID and u.PROP_ID IN (") ;			
					for(int i=0; i<queryCVSet.getCourseList().size(); i++){
						if(i == (queryCVSet.getCourseList().size()-1)){
							sql.append(queryCVSet.getCourseList().get(i).getId());
						}else{
							sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
						}
					}
					sql.append(")");
			
			//list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		}
		sql.append(" order by id desc ");
		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), values.toArray());
		List<CVSet> result_list = new ArrayList<CVSet>();
		
		/*String groupIds = "";
		if (queryCVSet.getCourseList() != null && !queryCVSet.getCourseList().isEmpty()) groupIds = queryCVSet.getCourseList().get(0).getName();
		if(groupIds != null && !groupIds.equals("") && groupIds.charAt(groupIds.length()-1) == ',')
		{
			groupIds = groupIds.substring(0, groupIds.length()-1);
		}*/
		
		for(CVSet cvSet:list){
			
			/*String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV_set t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+cvSet.getId();
			
			if(!groupIds.equals("")) {
				prop_sql += " and t1.prop_id in (" + groupIds +")";
			}
			
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));			
			if (!groupIds.equals("") && courseList.size() == 0) continue;			
			cvSet.setCourseList(courseList);*/	
			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			//chenlb deleted      [2017-07-08修改--taoliang]
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
				for(UserImage userImage: userImageList){
					/*userImage = localUserImageManageDAO.getUserImageList(userImage).get(0);
					userImageList_.add(userImage);*/
					//YHQ,2017-09-11
					List<UserImage> userImageListQuery = localUserImageManageDAO.getUserImageList(userImage) ;
					if (userImageListQuery != null && userImageListQuery.size() > 0) {
						userImage = userImageListQuery.get(0);
						userImageList_.add(userImage);
					}
				}
				cvSet.setUserImageList(userImageList_);
				/*UserImage image = new UserImage();
				Long image_id = getJdbcTemplate().queryForLong(sql_get);
				if (image_id != null) {
					image.setId(image_id);
					List<UserImage> userImageList = localUserImageManageDAO.getUserImageList(image);
					cvSet.setUserImageList(userImageList);
				}*/
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
			
			/* YHQ，2017-05-30，注释掉
			String sql_scheID = "select CV_SCHEDULE_ID as schedule_id from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId();
			List<CVSchedule> scheduleListID = getJdbcTemplate().query(sql_scheID, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
			*/
			
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
			/*for(CVSchedule schedule:scheduleListID){
				schedule.getSchedule_id();
				scheduleList.get(0).setSchedule_id(schedule.getSchedule_id());
			}*/
			
			/*  YHQ，2017-05-30，注释掉
			for(int i=0;i<scheduleListID.size();i++){
				scheduleList.get(i).setSchedule_id(scheduleListID.get(i).getSchedule_id());
			}
			*/
	
			cvSet.setCVScheduleList(scheduleList);
			
			//YHQ，2017-05-15
			if (queryCVSet.getId() != null && queryCVSet.getId() > 0) {
				String bvoString = "select cv_set_id as id1, book_name as key1, book_url as value1, source_id as source_id  from cv_set_refereebook where cv_set_id = " + queryCVSet.getId() ;
				List<BaseVO> refereeBookList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseVO.class));
				cvSet.setRefereeBookList(refereeBookList);
				
				bvoString = "select cv_set_id as id1, knowledgebase_name as key1, knowledgebase_url as value1, source_id as source_id    from cv_set_knowledgebase where cv_set_id = " + queryCVSet.getId() ;
				List<BaseVO> knowledgeBaseList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseVO.class));
				cvSet.setKnowledgeBaseList(knowledgeBaseList);
				
				bvoString = "select cv_set_id as id1, reference_name as key1, reference_url as value1, source_id as source_id    from cv_set_referencelately where cv_set_id = " + queryCVSet.getId() ;
				List<BaseVO> referenceLatelyList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseVO.class));
				cvSet.setReferenceLatelyList(referenceLatelyList);				
			}
			
			result_list.add(cvSet);
			
		}
		
		return result_list;
	}
	
	//可以审核的项目列表  chenlb add
	@Override
	public List<CVSet> getCVSetListForQualify(CVSet queryCVSet){
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT t.* FROM cv_set as t JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on t.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID");
		
		if(queryCVSet.getStatus() == 0){
			sql.append(" and t.status =2 ");
		}else if(queryCVSet.getStatus() == 10){
			sql.append(" and t.status in(2,3,4) ");
		}else{
			sql.append(" and t.status ="+queryCVSet.getStatus());
		}
		
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t.name like '%"+queryCVSet.getName().trim()+"%'");
		}
		
		//规避原则：创建者本人不可以审核自己的项目
		if(!StringUtils.checkNull(queryCVSet.getMaker())){
			if(queryCVSet.getFlag() !=null && queryCVSet.getFlag().equals("qualify")){
				sql.append(" and t.maker != '"+queryCVSet.getMaker()+"'");
			} 
		}
		 
		if(queryCVSet.getLevel() != null){
			sql.append(" and t.level ="+queryCVSet.getLevel());
		}
		if(queryCVSet.getScore() != null){
			sql.append(" and t.score ="+queryCVSet.getScore());
		}
		//search by xueke
		//审核和学科没有关系
//		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size()>0){			
//			sql.append(" and t.ID=c.CV_SET_ID and u.PROP_ID IN (") ;			
//					for(int i=0; i<queryCVSet.getCourseList().size(); i++){
//						if(i == (queryCVSet.getCourseList().size()-1)){
//							sql.append(queryCVSet.getCourseList().get(i).getId());
//						}else{
//							sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
//						}
//					}
//					sql.append(")");
//			
//			//list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
//		}
		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		List<CVSet> result_list = new ArrayList<CVSet>();
		
		/*String groupIds = "";
		if (queryCVSet.getCourseList() != null && !queryCVSet.getCourseList().isEmpty()) groupIds = queryCVSet.getCourseList().get(0).getName();
		if(groupIds != null && !groupIds.equals("") && groupIds.charAt(groupIds.length()-1) == ',')
		{
			groupIds = groupIds.substring(0, groupIds.length()-1);
		}*/
		
		for(CVSet cvSet:list){
			
			/*String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV_set t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+cvSet.getId();
			
			if(!groupIds.equals("")) {
				prop_sql += " and t1.prop_id in (" + groupIds +")";
			}
			
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));			
			if (!groupIds.equals("") && courseList.size() == 0) continue;			
			cvSet.setCourseList(courseList);*/	
			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			if (queryCVSet.getSiteList() != null && !queryCVSet.getSiteList().isEmpty()) {

				String site_sql = "";
				if(queryCVSet.getSiteList().get(0).getDomainName() != null && !queryCVSet.getSiteList().get(0).getDomainName().equals("")) 
					site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId() + " and s.DOMAIN_NAME like  '%" + queryCVSet.getSiteList().get(0).getDomainName() + "%'";
				else
					site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId();

				List<SystemSite> systemSite = getJdbcTemplate().query(site_sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
								
				if (systemSite.size() == 0) continue;
				cvSet.setSiteList(systemSite);
			}
			
			String sql_exist = "select count(1) from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
			Long cnt =  getJdbcTemplate().queryForLong(sql_exist);					
			
			if (cnt > 0) {
				String sql_get = "select USERIMAGE_ID as id from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
				List<UserImage> userImageList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				List<UserImage> userImageList_ = new ArrayList<UserImage>();
				for(UserImage userImage: userImageList){
					/*userImage = localUserImageManageDAO.getUserImageList(userImage).get(0);
					userImageList_.add(userImage);*/
					
					//YHQ,2017-09-13,修复get(0)容易引发的数组越界异常
					List<UserImage> userImageListQuery = localUserImageManageDAO.getUserImageList(userImage) ;
					if (userImageListQuery != null && userImageListQuery.size() > 0) {
						userImage = userImageListQuery.get(0);
						userImageList_.add(userImage);
					}
				}
				cvSet.setUserImageList(userImageList_);
				/*UserImage image = new UserImage();
				Long image_id = getJdbcTemplate().queryForLong(sql_get);
				if (image_id != null) {
					image.setId(image_id);
					List<UserImage> userImageList = localUserImageManageDAO.getUserImageList(image);
					cvSet.setUserImageList(userImageList);
				}*/
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
			
			//YHQ，2017-06-13
			//String sql_scheID = "select CV_SCHEDULE_ID as schedule_id from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId();
			//List<CVSchedule> scheduleListID = getJdbcTemplate().query(sql_scheID, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));						
			
			String sql_schedule = "select CV_ID as ID, START_DATE, END_DATE, SCHEDULE_ID from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId() + ")";			
			List<CVSchedule> _scheduleList = getJdbcTemplate().query(sql_schedule, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
			List<CVSchedule> scheduleList = new ArrayList<CVSchedule>();
			
			for(CVSchedule schedule:_scheduleList){				
				
				List<CV> cvList = new ArrayList<CV>();
				CV  queryCV = new CV();
				
				queryCV.setId(schedule.getId());
				cvList = localCVManageDAO.getCVList(queryCV);
				
				//YHQ，2017-06-13
				if (cvList != null && cvList.size() > 0) {
					CVSchedule sch = new CVSchedule(cvList.get(0));
					
					sch.setSchedule_id(schedule.getSchedule_id());
					
					sch.setId(schedule.getId());
					sch.setStart_date(schedule.getStart_date());
					sch.setEnd_date(schedule.getEnd_date());				
					scheduleList.add(sch);
				}				
			}
			/*for(CVSchedule schedule:scheduleListID){
				schedule.getSchedule_id();
				scheduleList.get(0).setSchedule_id(schedule.getSchedule_id());
			}			
			for(int i=0;i<scheduleListID.size();i++){
				scheduleList.get(i).setSchedule_id(scheduleListID.get(i).getSchedule_id());
			}*/
	
			cvSet.setCVScheduleList(scheduleList);
			
			result_list.add(cvSet);
			
		}
		
		return result_list;
	}
	
	
	//根据项目id得到所有专家
	//项目下--所有专家, 项目下有人物画像，人物画像下有学科。 学科下对应的委员会。 委员会下有学组，学组下有专家。
	@Override
	public List<ExpertInfo> getExpertInfoListByCVSetId(Long cvsetId){
		List<ExpertInfo> expertInfoList = new ArrayList<ExpertInfo>();
		//得到学科
		//String prop_sql = "select * from exam_prop_val t1 " +   //qm_user_image_prop表因分布式需要，增加一个主键ID,此处语句修改如下
		String prop_sql = "select t1.*,t2.USERIMAGE_ID,t2.PROP_ID from exam_prop_val t1 " +
				" left join qm_user_image_prop t2 on t1.ID=t2.PROP_ID" +
				" where t2.USERIMAGE_ID in " +
				" ( select t2.id from cv_set_user_image t1 " +
				" left join qm_user_image t2 on t1.USERIMAGE_ID = t2.id " +
				" where t1.CV_SET_ID = ?" +
				" )";
		
		List<ExamPropVal> propList = getJdbcTemplate().query(prop_sql, 
				ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class), cvsetId);
		
		if(!Utils.isListEmpty(propList)){		//学科列表
			Object [] values = new Object[propList.size()];
			
			StringBuffer expert_sql=new StringBuffer();
			expert_sql.append("select t6.* from expert_info t6 where t6.SUBGROUPID in ( select t3.id from EXPERT_GROUP t3 left join  EXPERT_GROUP_PROP_VAL t4 on t3.PARENT= t4.GROUPID where t4.PROPID in (") ;
			for(int i=0; i<propList.size(); i++){
				if(i == (propList.size()-1)){
					expert_sql.append(propList.get(i).getId());
				}else{
					expert_sql.append(propList.get(i).getId()).append(",");
				}
			}
			expert_sql.append("))");
			expert_sql.append(" union ");
			expert_sql.append("select t6.* from expert_info t6 where t6.GROUPID in ( select t3.parent from EXPERT_GROUP t3 left join  EXPERT_GROUP_PROP_VAL t4 on t3.PARENT= t4.GROUPID where t4.PROPID in (");
			for(int i=0; i<propList.size(); i++){
				if(i == (propList.size()-1)){
					expert_sql.append(propList.get(i).getId());
				}else{
					expert_sql.append(propList.get(i).getId()).append(",");
				}
			}
			expert_sql.append("))");
			expertInfoList = getJdbcTemplate().query(expert_sql.toString(), 
					ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		}
	
		return expertInfoList;
	}
	
	@Override
	public Long addCVSet(CVSet cvSet) {
		
		if (cvSet.getId() > 0 ) {
			this.deleteCVSet(cvSet);
		} else {					
			cvSet.setId(this.getNextId("CV_SET"));	
			cvSet.setStatus(Long.parseLong("1"));
		}
		//YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
		String sql_add = "INSERT INTO CV_SET (id, name, forma, code, file_path, introduction, announcement, knowledge_needed, knowledge_base, reference, reference_lately, status, create_date, maker, opinion, opinion_type, report, cv_set_type) VALUES (:id, :name, :forma, :code, :file_path, :introduction, :announcement, :knowledge_needed, :knowledge_base, :reference, :reference_lately, :status, sysdate(), :maker, :opinion, :opinionType, :report, :cv_set_type)";
		getSimpleJdbcTemplate().update(sql_add, new BeanPropertySqlParameterSource(cvSet));
		
		List<UserImage> userImageList = cvSet.getUserImageList();
		if (userImageList != null) {
			for (UserImage image:userImageList) {
				sql_add = "insert into CV_SET_USER_IMAGE (CV_SET_ID, USERIMAGE_ID) values (?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), image.getId());
			}
		}
		
		List<ExpertInfo> managerList = cvSet.getManagerList();
		if (managerList != null) {
			for (ExpertInfo expert:managerList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 1);
			}
			
		}
		List<ExpertInfo> teacherList = cvSet.getTeacherList();
		if (teacherList != null) {
			for (ExpertInfo expert:teacherList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 2);
			}
			
		}
		List<PeixunOrg> orgs = cvSet.getOrganizationList();
		if(orgs != null){
			for(PeixunOrg org:orgs){
				sql_add = "insert into CV_SET_ORG (CV_ID, ORG_ID) values (?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), org.getId());
			}
		}
		
		
		List<ExpertInfo> otherTeacherList = cvSet.getOtherTeacherList();
		if (otherTeacherList != null) {
			for (ExpertInfo expert:otherTeacherList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 3);
			}
			
		}
		
		List<CVSchedule> scheduleList = cvSet.getCVScheduleList();
		if (scheduleList != null) {			
			
			/*if(cvSet.getId()>0){
				for (CVSchedule schedule:scheduleList) {					
					sql_add = "insert into CV_SCHEDULE (SCHEDULE_ID, CV_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, schedule.getSchedule_id(), schedule.getId());
					
					sql_add = "insert into CV_SET_SCHEDULE (CV_SET_ID, CV_SCHEDULE_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, cvSet.getId(), schedule.getSchedule_id());
				}
			}else{*/
				Long schedule_ID;
				for (CVSchedule schedule:scheduleList) {				
					schedule_ID = this.getNextId("CV_SCHEDULE");
					sql_add = "insert into CV_SCHEDULE (SCHEDULE_ID, CV_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, schedule_ID, schedule.getId());
					sql_add = "insert into CV_SET_SCHEDULE (CV_SET_ID, CV_SCHEDULE_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, cvSet.getId(), schedule_ID);
				//}	
			}			
		}
		
		//YHQ，2017-05-16，begin
		List<BaseVO> refereeBookList = cvSet.getRefereeBookList() ;
		List<BaseVO> knowledgeBaseList = cvSet.getKnowledgeBaseList() ;
		List<BaseVO> referenceLatelyList = cvSet.getReferenceLatelyList() ;
		
		String bvoSql = "delete from cv_set_refereebook where cv_set_id =" + cvSet.getId() ;
		getJdbcTemplate().update(bvoSql) ;
		if (refereeBookList != null && refereeBookList.size() > 0) {
			for (BaseVO bvoObj : refereeBookList) {
				bvoSql = "insert into cv_set_refereebook (cv_set_id,source_id) values (?, ?) " ;
				getJdbcTemplate().update(bvoSql, cvSet.getId(), bvoObj.getSource_id());
			}			
		}
		
		bvoSql = "delete from cv_set_knowledgebase where cv_set_id =" + cvSet.getId() ;
		getJdbcTemplate().update(bvoSql) ;
		if (knowledgeBaseList != null && knowledgeBaseList.size() > 0) {
			for (BaseVO bvoObj : knowledgeBaseList) {
//				bvoSql = "insert into cv_set_knowledgebase (cv_set_id,knowledgebase_name,knowledgebase_url) values (?, ?, ?) " ;
				bvoSql = "insert into cv_set_knowledgebase (cv_set_id,source_id) values (?, ?) " ;
				getJdbcTemplate().update(bvoSql, cvSet.getId(), bvoObj.getSource_id());
			}			
		}
		
		bvoSql = "delete from cv_set_referencelately where cv_set_id =" + cvSet.getId() ;
		getJdbcTemplate().update(bvoSql) ;
		if (referenceLatelyList != null && referenceLatelyList.size() > 0) {
			for (BaseVO bvoObj : referenceLatelyList) {
//				bvoSql = "insert into cv_set_referencelately (cv_set_id,reference_name,reference_url) values (?, ?, ?) " ;
				bvoSql = "insert into cv_set_referencelately (cv_set_id,source_id) values (?,?) " ;
				getJdbcTemplate().update(bvoSql, cvSet.getId(), bvoObj.getSource_id());
			}			
		}		
		//YHQ，2017-05-16，over
		
		return cvSet.getId();
	}

	@Override
	public boolean updateCVSet(CVSet cvSet) {
		if(cvSet.getStatus() == 2){
			String sql_edit = "update cv_set set status=? where id="+ cvSet.getId();
			getJdbcTemplate().update(sql_edit, cvSet.getStatus());
		}else{
			String sql_edit = "update cv_set set opinion=?, opinion_type=?, status=? where id="+ cvSet.getId();
			getJdbcTemplate().update(sql_edit, cvSet.getOpinion(), cvSet.getOpinionType(), cvSet.getStatus());
		}
		return true;
	}
	
	//添加项目审核记录历史表	chenlb add
	@Override
	public int saveCvsetQualityHistory(CvsetQualityHistory history){
		String sql = "insert into cvset_qualify_history (cv_set_id, expert_id, qualify_status, opinion, opinion_type, status, create_date) " +
				" values (?,?,?,?,?,?,date(now()))";
		//将审核人名称更新至cv_set 表dell_man字段中
		String chemansql="update cv_set s set s.DELI_MAN = CONCAT(ifnull(s.DELI_MAN,''),' ',(select name from expert_info e where e.id = '"+history.getExpertId()+"')),s.DELI_DATE=sysdate() where s.ID = "+history.getCvSetId();
		getJdbcTemplate().update(chemansql);
		
		return this.getJdbcTemplate().update(sql, history.getCvSetId(), history.getExpertId(), history.getQualifyStatus(), 
				history.getOpinion(), history.getOpinionType(), history.getStatus());
	}
	
	//得到项目下同一个审核状态的 列表
	@Override
	public List<CvsetQualityHistory> getCvsetQualityHistoryListByCvsetId(Long cvsetId, Integer qualifyStatus){
		String sql =  "select * from cvset_qualify_history t where t.cv_set_id = ? and t.qualify_status = ? and t.status = 1";
		return  getJdbcTemplate().query(sql, 
				ParameterizedBeanPropertyRowMapper.newInstance(CvsetQualityHistory.class), cvsetId, qualifyStatus);
	}
	
	//根据项目和专家得到其审核历史记录 chenlb add 
	@Override
	public CvsetQualityHistory getCvsetQualityHistoryByCvsetAndExpert(Long cvsetId, Long expertId){
		String sql = "select * from cvset_qualify_history t where t.cv_set_id = ? and t.expert_id = ? and t.status = 1 ";
		List<CvsetQualityHistory> list = getJdbcTemplate().query(sql, 
				ParameterizedBeanPropertyRowMapper.newInstance(CvsetQualityHistory.class), cvsetId, expertId);
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
		
	}
	
	//根据项目和专家得到其审核历史记录 chenlb add 
	@Override
	public List<CvsetQualityHistory> getCvsetQualityHistoryByCvsetAndExpertInfo(Long cvsetId){
		String sql = "select * from cvset_qualify_history t where t.cv_set_id = ? and t.status = 1 ";
		List<CvsetQualityHistory> list = getJdbcTemplate().query(sql, 
				ParameterizedBeanPropertyRowMapper.newInstance(CvsetQualityHistory.class), cvsetId);
		if(!Utils.isListEmpty(list)){
			return list;
		}
		return null;
		
	}

	@Override
	public boolean deleteCVSet(CVSet cvSet) {
		
		Long del_id = cvSet.getId();
		
		Connection conn;
		try {
			conn = (Connection) getJdbcTemplate().getDataSource().getConnection();
			try{
	
				conn.setAutoCommit(false);
				Statement conn_statement = (Statement) conn.createStatement();
				
				String sql_del = "delete from CV_SET_USER_IMAGE where CV_SET_ID=" + del_id;
				conn_statement.executeUpdate(sql_del);
				
				sql_del = "delete from CV_SET_ORG where CV_ID=" + del_id;
				conn_statement.executeUpdate(sql_del);
				
				sql_del = "delete from CV_SET_EXPERT where CV_SET_ID=" + del_id;
				conn_statement.executeUpdate(sql_del);
				
				sql_del = "delete from CV_SET_SCHEDULE where CV_SET_ID=" + del_id;
				conn_statement.executeUpdate(sql_del);
				
				sql_del = "delete from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID =" + del_id + ")";
				conn_statement.executeUpdate(sql_del);
				
				sql_del = "delete from LOG_STUDY_CV_SET where CV_SET_ID=" + del_id;
				conn_statement.executeUpdate(sql_del);
				
				sql_del = "delete from CV_SET where id="+del_id;
				conn_statement.executeUpdate(sql_del);
				
				conn_statement.close();
				conn.commit();
				conn.setAutoCommit(true);
				
			} catch(SQLException e2) {
				try {
					conn.rollback();
					conn.setAutoCommit(true);
				} catch (Exception e3) {
					return false;
				}
				return false;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	public CVSet getCVSetById(Long id)
	{
		CVSet result;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cv_set where id = ").append(id);
		result = getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		
		sql.delete(0, sql.length());
		sql.append("select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = ").append(result.getId());
		List<SystemSite> systemSite = getJdbcTemplate().query(sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		result.setSiteList(systemSite);
		
		sql.delete(0, sql.length());
		sql.append("select cs.SCHEDULE_ID as id,cv.Name,cv.serial_number,cv.brand,cv.introduction,cv.announcement,cv.file_path,cv.create_date,cv.creator,cs.start_date,cs.end_date from cv_set_schedule css LEFT JOIN cv_schedule cs on css.CV_SCHEDULE_ID = cs.SCHEDULE_ID LEFT JOIN cv as cv on cs.CV_ID = cv.ID where css.CV_SET_ID = ").append(result.getId()).append(" order by cs.start_date desc");
		List<CVSchedule> scheduleList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));	
		result.setCVScheduleList(scheduleList);
		
		//查询地域
		String prop_sql = "SELECT v.id AS id,t.name from exam_prop_val t,sub_sys_prop_val v,sub_sys_prop p,exam_prop_type tt,cv_region cr,cv_set cs where t.id = v.prop_val_id AND p.id = v.sys_prop_id AND tt.prop_type = t.c_type AND cr.region_id = v.id AND cs.id = cr.cv_set_id AND cs.id = "+result.getId();
		List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		result.setCourseList(courseList);
		return result;
	}

	public int updateDistribute(List<Object> saveParams)
	{
		CVSet saveCV = (CVSet)saveParams.get(0);
		String siteIds = (String)saveParams.get(1);
		
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update cv_set set ");
		if(null != saveCV.getProvinceId()){
			sql.append("PROVINCEID=?,");
			values.add(saveCV.getProvinceId());
		}
		if(null != saveCV.getCityId()){
			sql.append("CITYID=?,");
			values.add(saveCV.getCityId());
		}
		if(null != saveCV.getLevel()){
			sql.append("LEVEL=?,");
			values.add(saveCV.getLevel());
		}
		if(null != saveCV.getScore()){
			sql.append("SCORE=?,");
			values.add(saveCV.getScore());
		}
		/*if(!StringUtils.checkNull(saveCV.getSerial_number())){
			sql.append("SERIAL_NUMBER=?,");
			values.add(saveCV.getSerial_number());
		}*/
	
		sql.append("SERIAL_NUMBER=?,");
		values.add(saveCV.getSerial_number());
		
		sql.append("SIGN=?,");
		values.add(saveCV.getSign());
		
		if(null != saveCV.getCost()){
			sql.append("cost=?,");
			values.add(saveCV.getCost());
		}
		
		//加入费用类型
		if(null != saveCV.getCost_type()){
			sql.append("cost_type=?,");
			values.add(saveCV.getCost_type());
		}
		
		if(null != saveCV.getBreak_days()){
			sql.append("BREAK_DAYS=?,");
			values.add(saveCV.getBreak_days());
		}
		if(saveCV.getStart_date() != null){
			/*sql.append("start_date = to_date(?,'yyyy-mm-dd hh:mm:ss'),");
			values.add(saveCV.getStart_date());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			/*String[] funcSQL = FuncMySQL.longDateForUpdate(saveCV.getStart_date()) ;
			sql.append("start_date = " + funcSQL[0] +" ,");
			values.add(funcSQL[1]);*/
			sql.append(" start_date = " + FuncMySQL.longDateForUpdateNoArg(saveCV.getStart_date()) +" ,");						
			
		}
		if(null != saveCV.getEnd_date()){
			/*sql.append("end_date=to_date(?,'yyyy-mm-dd hh:mm:ss'),");
			values.add(saveCV.getEnd_date());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库			
			sql.append(" end_date = " + FuncMySQL.longDateForUpdateNoArg(saveCV.getEnd_date()) + " ,");
		}
		if(saveCV.getStatus() != null){
			sql.append("status=?,");
			values.add(saveCV.getStatus());
		}
		sql.append(" id = ? where id = ?");
		values.add(saveCV.getId());
		values.add(saveCV.getId());
		
		
		int result = getJdbcTemplate().update(sql.toString(),values.toArray());
		if(result > 0)
		{	
			for(CVSchedule schedule : saveCV.getCVScheduleList())
			{
				sql.delete(0, sql.length());
				/*
				sql.append("update cv_schedule set START_DATE = to_date(?,'yyyy-mm-dd hh:mm:ss'), END_DATE = to_date(?,'yyyy-mm-dd hh:mm:ss') where schedule_id = ?");
				List sval = new ArrayList();
				sval.add(schedule.getStart_date());
				sval.add(schedule.getEnd_date());
				sval.add(schedule.getId());
				*/
				
				//YHQ,2017-06-22,函数替换，迁移到分布式数据库
				String startDateSQL = FuncMySQL.longDateForUpdateNoArg(schedule.getStart_date()) ;
				String EndDateSQL   = FuncMySQL.longDateForUpdateNoArg(schedule.getEnd_date()) ;
				sql.append("update cv_schedule set START_DATE = " + startDateSQL + " , END_DATE = " + EndDateSQL + " where schedule_id = ?");
				List sval = new ArrayList();				
				sval.add(schedule.getId());
				
				result = getJdbcTemplate().update(sql.toString(),sval.toArray());
			}
		}
		if(!StringUtil.checkNull(siteIds))
		{
			if(siteIds.charAt(siteIds.length() - 1) == ',')
			{
				siteIds = siteIds.substring(0, siteIds.length()-1);
			}
			String[] siteArr = siteIds.split(",");
			sql.delete(0, sql.length());
			sql.append("delete from cv_set_system_site where cv_set_id = ").append(saveCV.getId());
			result = getJdbcTemplate().update(sql.toString());
			for(String siteId : siteArr)
			{
				if(StringUtils.isEmpty(siteId))
					continue;
				sql.delete(0, sql.length());
				sql.append("insert into cv_set_system_site set cv_set_id =").append(saveCV.getId());
				sql.append(",system_site_id=").append(siteId);
				getJdbcTemplate().update(sql.toString());
			}
		}
		
		List<PropUnit> prop_course = saveCV.getCourseList();
		if(prop_course != null && prop_course.size()>0){
			String sql_prop_course = "DELETE FROM CV_REGION WHERE CV_SET_ID = ?";
			getSimpleJdbcTemplate().update(sql_prop_course, saveCV.getId());
			for(int i=0; i<prop_course.size();i++){
				sql_prop_course = "INSERT INTO CV_REGION (CV_SET_ID,REGION_ID) VALUES (?,?)";
				getSimpleJdbcTemplate().update(sql_prop_course, saveCV.getId(),prop_course.get(i).getId());
			}
		}
		return result;
	}

	public CVManageDAO getLocalCVManageDAO() {
		return localCVManageDAO;
	}

	public void setLocalCVManageDAO(CVManageDAO localCVManageDAO) {
		this.localCVManageDAO = localCVManageDAO;
	}

	public UserImageManageDAO getLocalUserImageManageDAO() {
		return localUserImageManageDAO;
	}

	public void setLocalUserImageManageDAO(UserImageManageDAO localUserImageManageDAO) {
		this.localUserImageManageDAO = localUserImageManageDAO;
	}

	public ExpertManageDAO getLocalExpertManageDAO() {
		return localExpertManageDAO;
	}

	public void setLocalExpertManageDAO(ExpertManageDAO localExpertManageDAO) {
		this.localExpertManageDAO = localExpertManageDAO;
	}

	@Override
	public boolean updateUnit_ref_Quality(long id, List<QualityModel> qm_list) {
		
		/*
		String sql = "delete from cv_unit_ref_quality where unit_id=" + id;
		getJdbcTemplate().update(sql);
		
		sql = "insert into cv_unit_ref_quality (unit_id, prop_id, level) values (?, ?, ?)";
		for (QualityModel qm:qm_list) {
			getJdbcTemplate().update(sql, id, qm.getId(), qm.getLevel());
		}*/
		
		//YHQ 2017-02-24
		if (id > 0 && qm_list != null && qm_list.size() > 0 ) {
			String sql = "select count(1) from cv_unit_ref_quality where unit_id = ?" ;
			Integer curqNum = getJdbcTemplate().queryForInt(sql.toString(), id);
			if (curqNum > 0) {
				sql = "update cv_unit_ref_quality set state = ? where unit_id = ? and prop_id = ? and level = ?" ;				
			} else {
				sql = "insert into cv_unit_ref_quality (state, unit_id, prop_id, level) values (?, ?, ?,?)";
			}
			for (QualityModel qm:qm_list) {
				getJdbcTemplate().update(sql, qm.getParentId(),id, qm.getId(), qm.getLevel());
			}
		}
		
		
		return true;
	}
	@Override
	public boolean editCVS(CVSet cvSet) {
		Long del_id = cvSet.getId();
		//审核不合格修改后变成未审核
		if(cvSet.getStatus()==4){
			cvSet.setStatus(1L);
			cvSet.setDeli_man(null);
			cvSet.setDeli_date(null);
			//删除审核信息
			getJdbcTemplate().update("delete from cvset_qualify_history where CV_SET_ID=" + del_id);
			
			getJdbcTemplate().update("delete from cvset_qualify where CV_SET_ID=" + del_id);
		}
		
		String sql_del = "delete from CV_SET_USER_IMAGE where CV_SET_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		sql_del = "delete from CV_SET_ORG where CV_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		sql_del = "delete from CV_SET_EXPERT where CV_SET_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		sql_del = "delete from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID form CV_SET_SCHEDULE where CV_SET_ID =" + del_id + ")";
		
		sql_del = "delete from CV_SET_SCHEDULE where CV_SET_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		String sql_add =new String();
		List<UserImage> userImageList = cvSet.getUserImageList();
		if (userImageList != null) {
			for (UserImage image:userImageList) {
				sql_add = "insert into CV_SET_USER_IMAGE (CV_SET_ID, USERIMAGE_ID) values (?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), image.getId());
			}
		}
		
		List<ExpertInfo> managerList = cvSet.getManagerList();
		if (managerList != null) {
			for (ExpertInfo expert:managerList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 1);
			}
			
		}
		List<ExpertInfo> teacherList = cvSet.getTeacherList();
		if (teacherList != null) {
			for (ExpertInfo expert:teacherList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 2);
			}
			
		}
		List<PeixunOrg> orgs = cvSet.getOrganizationList();
		if(orgs != null){
			for(PeixunOrg org:orgs){
				sql_add = "insert into CV_SET_ORG (CV_ID, ORG_ID) values (?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), org.getId());
			}
		}
		
		
		List<ExpertInfo> otherTeacherList = cvSet.getOtherTeacherList();
		if (otherTeacherList != null) {
			for (ExpertInfo expert:otherTeacherList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 3);
			}
			
		}
		
		List<CVSchedule> scheduleList = cvSet.getCVScheduleList();
		if (scheduleList != null) {			
			
			/*if(cvSet.getId()>0){
				for (CVSchedule schedule:scheduleList) {					
					sql_add = "insert into CV_SCHEDULE (SCHEDULE_ID, CV_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, schedule.getSchedule_id(), schedule.getId());
					
					sql_add = "insert into CV_SET_SCHEDULE (CV_SET_ID, CV_SCHEDULE_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, cvSet.getId(), schedule.getSchedule_id());
				}
			}else{*/
				Long schedule_ID;
				int sequenceNum = 0 ;//YHQ，2017-05-30，添加sequenceNum
				for (CVSchedule schedule:scheduleList) {				
					sequenceNum++ ;
					schedule_ID = this.getNextId("CV_SCHEDULE");
					sql_add = "insert into CV_SCHEDULE (SCHEDULE_ID, CV_ID, sequenceNum) values (?, ?, ?)"; //YHQ，2017-05-30，添加sequenceNum
					getJdbcTemplate().update(sql_add, schedule_ID, schedule.getId(),sequenceNum);
					sql_add = "insert into CV_SET_SCHEDULE (CV_SET_ID, CV_SCHEDULE_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, cvSet.getId(), schedule_ID);
				//}	
			}			
		}
		
		//YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
		String sql_edit = "update cv_set set name=?,deli_man=?,deli_date=?, forma=?, introduction=?, announcement=?, knowledge_needed=?, knowledge_base=?, reference=?, reference_lately=?, report=?, status=?,file_path=?,cv_set_type=? where id="+cvSet.getId();
		getJdbcTemplate().update(sql_edit, cvSet.getName(),cvSet.getDeli_man(),cvSet.getDeli_date(), cvSet.getForma(), cvSet.getIntroduction(), cvSet.getAnnouncement(), cvSet.getKnowledge_needed(), cvSet.getKnowledge_base(),
				cvSet.getReference(), cvSet.getReference_lately(), cvSet.getReport(), cvSet.getStatus(),cvSet.getFile_path(), cvSet.getCv_set_type());
		
		//YHQ，2017-05-16，begin
		List<BaseVO> refereeBookList = cvSet.getRefereeBookList() ;
		List<BaseVO> knowledgeBaseList = cvSet.getKnowledgeBaseList() ;
		List<BaseVO> referenceLatelyList = cvSet.getReferenceLatelyList() ;
		
		String bvoSql = "delete from cv_set_refereebook where cv_set_id =" + cvSet.getId() ;
		getJdbcTemplate().update(bvoSql) ;
		if (refereeBookList != null && refereeBookList.size() > 0) {
			for (BaseVO bvoObj : refereeBookList) {
				bvoSql = "insert into cv_set_refereebook (cv_set_id,book_name,book_url) values (?, ?, ?) " ;
				getJdbcTemplate().update(bvoSql, cvSet.getId(),bvoObj.getKey1(), bvoObj.getValue1());
			}			
		}
		
		bvoSql = "delete from cv_set_knowledgebase where cv_set_id =" + cvSet.getId() ;
		getJdbcTemplate().update(bvoSql) ;
		if (knowledgeBaseList != null && knowledgeBaseList.size() > 0) {
			for (BaseVO bvoObj : knowledgeBaseList) {
				bvoSql = "insert into cv_set_knowledgebase (cv_set_id,knowledgebase_name,knowledgebase_url) values (?, ?, ?) " ;
				getJdbcTemplate().update(bvoSql, cvSet.getId(),bvoObj.getKey1(), bvoObj.getValue1());
			}			
		}
		
		bvoSql = "delete from cv_set_referencelately where cv_set_id =" + cvSet.getId() ;
		getJdbcTemplate().update(bvoSql) ;
		if (referenceLatelyList != null && referenceLatelyList.size() > 0) {
			for (BaseVO bvoObj : referenceLatelyList) {
				bvoSql = "insert into cv_set_referencelately (cv_set_id,reference_name,reference_url) values (?, ?, ?) " ;
				getJdbcTemplate().update(bvoSql, cvSet.getId(),bvoObj.getKey1(), bvoObj.getValue1());
			}			
		}		
		//YHQ，2017-05-16，over		
		
		return true;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @param    CVSet
	 * @return   List
	 * 方法说明： 根据用户id查询所属专委会对应的学科信息
	 */
	public List<PropUnit> getPropByUserId(Long userId){
		/*String sql = " select * from exam_prop_val where id in ( "
	                 +" select propid from expert_group_prop_val where groupid in ( "
                       +" select parent from expert_group where id in ( "
                         +" select groupid from expert_group_val where expertid = ( "
				         +" select id from expert_info where username = ( "
				         +" select account_name from system_account where user_id= "+userId
				            +" ) "
                          +" ) "
		                +" ) "
	                  +" ) "
                    +" ) ";*/
		String sql="select epv.* from exam_prop_val epv "+
					"join expert_group_prop_val egpv on epv.id = egpv.propid " +
					"join expert_group eg on eg.parent = egpv.groupid " +
					"join expert_group_val egv on eg.id = egv.groupid " +
					"join expert_info ei on egv.expertid = ei.id " +
					"join system_account sa on ei.username=sa.account_name and sa.user_id="+userId;
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
	}
	
	public CVSet getCVSetByCode(String code)
	{
		CVSet result;
		String sql = "select * from cv_set where code = '" + code 
				+"' ";
		result = getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		return result;
	}
	@Override
	public int getFaceteachCount(Long cvsetId) {
		String sql = "SELECT SUM(t.people_number) AS people_number  FROM cv_set_schedule_faceteach t WHERE t.cv_set_id="+cvsetId;
		return getJdbcTemplate().queryForInt(sql);
	}
	@Override
	public void getCVSetListByPage(PageList pl,CVSet queryCVSet) {
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		//添加根据站点查询条件
		if(queryCVSet.getType()!=null && !queryCVSet.getType().equals("") && queryCVSet.getType()==-11){
			sql.append("SELECT DISTINCT t.* FROM cv_set as t JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on t.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID");
		}else{
			sql.append("SELECT DISTINCT t.* FROM cv_set as t JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on t.ID=c.CV_SET_ID JOIN cv_set_system_site AS sp ON t.ID =  sp.CV_SET_ID JOIN system_site s ON sp.SYSTEM_SITE_ID = s.ID and c.USERIMAGE_ID=u.USERIMAGE_ID");
		}
		//SELECT t.* FROM cv_set t, cv_set_user_image tu, qm_user_image u, qm_user_image_prop p WHERE u.id=p.USERIMAGE_ID and t.ID=tu.CV_SET_ID and tu.USERIMAGE_ID=p.USERIMAGE_ID and p.PROP_ID IN (147)
		if(queryCVSet.getId()>0){
			sql.append(" and t.id=").append(queryCVSet.getId());
		}else {
			if(!StringUtils.checkNull(queryCVSet.getName())){
				sql.append(" and t.name like ?");
				values.add("%"+queryCVSet.getName().trim()+"%");
			}
			
			if(queryCVSet.getStatus() != null && queryCVSet.getStatus() > 0){
				sql.append(" and t.status ="+queryCVSet.getStatus());
			}else if(queryCVSet.getStatus() == 0){//项目审核里添加查看所有状态的查询情况---taoliang
				sql.append(" and t.status in (1,2,3,4,5,6)" );
			}else if(queryCVSet.getStatus() == -1){				//chenlb add  项目授权里，只要看到：已审核，已授权，已下线  这几种
				sql.append(" and t.status in (3,4,5,6)");
			}
		}
		if(queryCVSet.getType()!=null && !queryCVSet.getType().equals("") && queryCVSet.getType()==-11){
			//queryCVSet.getType()为-11时，是“项目管理”列表查询，什么也不做
		}else{
			if (queryCVSet.getSiteList() != null && !queryCVSet.getSiteList().isEmpty()) {
				sql.append(" AND s.DOMAIN_NAME LIKE ?");
				values.add("%"+queryCVSet.getSiteList().get(0).getDomainName()+"%");
			}
		}
		if(!StringUtils.checkNull(queryCVSet.getMaker())){
//			if (queryCVSet.getId() != null && queryCVSet.getId() < 0) {
				sql.append(" and t.id in(select  cse.cv_set_id from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id  where ei.name like ? and cse.type = 1)");//2017.8.17 xh 项目管理 ->只根据"项目目负责人"(cse.type=1)时查询; type=2:授课教师
				values.add("%"+queryCVSet.getMaker()+"%");
//			}
		/*	else if(queryCVSet.getFlag() !=null && queryCVSet.getFlag().equals("qualify")){
				sql.append(" and status in (2,3,4)  and t.id not in (select  cse.cv_set_id  from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id where ei.name like ? ) ");
				values.add("%"+queryCVSet.getMaker()+"%");
			}else if(queryCVSet.getFlag() !=null && queryCVSet.getFlag().equals("distribute")){
				sql.append(" and status = 4");
			}else{
				sql.append(" and status in (2,3,4)  and t.id not in(select  cse.cv_set_id  from cv_set_expert cse left join expert_info ei  on cse.EXPERT_ID = ei.id  where ei.name like ? ) ");
				values.add("%"+queryCVSet.getMaker()+"%");
			}*/
		}
		
		if(!StringUtils.checkNull(queryCVSet.getDeli_man())){
			sql.append(" and t.deli_man like ? ");
			values.add("%"+queryCVSet.getDeli_man()+"%");
		}
		
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t.name like ?");
			values.add("%"+queryCVSet.getName().trim()+"%");
		}
//		if(queryCVSet.getStatus() != null && queryCVSet.getStatus() > 0){
//			sql.append(" and t.status ="+queryCVSet.getStatus());
//		}
		if(queryCVSet.getSign() != null){
			if(queryCVSet.getSign().equals("1")){
				sql.append(" and t.sign like '%公需科目%'");
			}
			else if(queryCVSet.getSign().equals("2")){
				sql.append(" and t.sign like '%指南%'");
			}
		}
		//项目授课方式
		if(queryCVSet.getForma() != null && queryCVSet.getForma()>0){
			sql.append(" and t.forma=? ");
			values.add(queryCVSet.getForma());
		}
		if(queryCVSet.getLevel() != null){
			sql.append(" and t.level ="+queryCVSet.getLevel());
		}
		if(queryCVSet.getScore() != null){
			sql.append(" and t.score ="+queryCVSet.getScore());
		}
		//search by xueke
		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size()>0){			
			sql.append(" and t.ID=c.CV_SET_ID and u.PROP_ID IN (") ;			
					for(int i=0; i<queryCVSet.getCourseList().size(); i++){
						if(i == (queryCVSet.getCourseList().size()-1)){
							sql.append(queryCVSet.getCourseList().get(i).getId());
						}else{
							sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
						}
					}
					sql.append(")");
			
			//list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		}
		sql.append(" order by id desc ");
		
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()),values.toArray());

//      int fullListSize = cvList.size();
		pl.setFullListSize(fullListSize);
//		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), values.toArray());
		list=getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class),values.toArray());	

		List<CVSet> result_list = new ArrayList<CVSet>();
		
		/*String groupIds = "";
		if (queryCVSet.getCourseList() != null && !queryCVSet.getCourseList().isEmpty()) groupIds = queryCVSet.getCourseList().get(0).getName();
		if(groupIds != null && !groupIds.equals("") && groupIds.charAt(groupIds.length()-1) == ',')
		{
			groupIds = groupIds.substring(0, groupIds.length()-1);
		}*/
		
		for(CVSet cvSet:list){
			
			/*String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV_set t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+cvSet.getId();
			
			if(!groupIds.equals("")) {
				prop_sql += " and t1.prop_id in (" + groupIds +")";
			}
			
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));			
			if (!groupIds.equals("") && courseList.size() == 0) continue;			
			cvSet.setCourseList(courseList);*/	
			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			//chenlb deleted
			if (queryCVSet.getSiteList() != null && !queryCVSet.getSiteList().isEmpty()) {


				String site_sql = "";
				if(queryCVSet.getSiteList().get(0).getDomainName() != null && !queryCVSet.getSiteList().get(0).getDomainName().equals("")) 
					site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId() + " and s.DOMAIN_NAME like  '%" + queryCVSet.getSiteList().get(0).getDomainName() + "%'";
				else
					site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId();

				List<SystemSite> systemSite = getJdbcTemplate().query(site_sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
								
				if (systemSite.size() > 0) 		
				cvSet.setSiteList(systemSite);
			}
			
			String sql_exist = "select count(1) from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
			Long cnt =  getJdbcTemplate().queryForLong(sql_exist);					
			
			if (cnt > 0) {
				String sql_get = "select USERIMAGE_ID as id from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
				List<UserImage> userImageList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				List<UserImage> userImageList_ = new ArrayList<UserImage>();
				// 在这个地方处理，如果PropUnit的name重复，那么就把这个PropUnit的那么重置为""，否则就记录该name
				List<String> propList = new ArrayList<String>();//记录propUnit的name的集合
				for(UserImage userImage: userImageList){//借用项目授权里学科名重复过滤-------taoLiang
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
				cvSet.setUserImageList(userImageList_);
				/*UserImage image = new UserImage();
				Long image_id = getJdbcTemplate().queryForLong(sql_get);
				if (image_id != null) {
					image.setId(image_id);
					List<UserImage> userImageList = localUserImageManageDAO.getUserImageList(image);
					cvSet.setUserImageList(userImageList);
				}*/
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
			
			/* YHQ，2017-05-30，注释掉
			String sql_scheID = "select CV_SCHEDULE_ID as schedule_id from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId();
			List<CVSchedule> scheduleListID = getJdbcTemplate().query(sql_scheID, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
			*/
			
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
			/*for(CVSchedule schedule:scheduleListID){
				schedule.getSchedule_id();
				scheduleList.get(0).setSchedule_id(schedule.getSchedule_id());
			}*/
			
			/*  YHQ，2017-05-30，注释掉
			for(int i=0;i<scheduleListID.size();i++){
				scheduleList.get(i).setSchedule_id(scheduleListID.get(i).getSchedule_id());
			}
			*/
	
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
			pl.setList(result_list);
			
		}
		
		
	}
	
	/**
	 * 项目授权中显示的项目列表学科是重复的，但是dao层被另一个地方引用着，所以再复制一份，修改
	 * @param queryCVSet
	 * @return
	 */
	@Override
	public List<CVSet> getCVSetListDuplicateRemove(CVSet queryCVSet) {
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("SELECT DISTINCT t.* FROM cv_set as t LEFT JOIN cv_set_user_image as c on t.ID=c.CV_SET_ID " )
				.append("LEFT JOIN qm_user_image_prop as u on c.USERIMAGE_ID=u.USERIMAGE_ID " )
						.append( "LEFT JOIN CV_REGION AS cr ON t.ID = cr.CV_SET_ID where 1=1 ")
								.append("and t.status in (3,5,6) " );//列表中的为项目状态是“审核合格”、“已下线”、“已发布”的项目
		
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
		if(queryCVSet.getSiteList() != null && queryCVSet.getSiteList().size() > 0){
			sql.append(" AND t.ID in ( ")
					.append(" SELECT t.CV_SET_ID ")
							.append(" FROM ")
									.append(" cv_set_authorization t ")
											.append(" LEFT JOIN	cv_set_authorization_system_site t1 ON t1.AUTHORIZATION_ID = t.ID ")
													.append(" LEFT JOIN system_site t2 ON t2.ID = t1.SYSTEM_SITE_ID ")
															.append(" WHERE ")
																	.append(" t2.ID = ? )");

			values.add(queryCVSet.getSiteList().get(0).getId());
		}
		
		sql.append(" ORDER BY t.CREATE_DATE DESC");
		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), values.toArray());
		List<CVSet> result_list = new ArrayList<CVSet>();
		
		for(CVSet cvSet:list){
			
			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			//chenlb deleted      [2017-07-08修改--taoliang]
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
	public List<PropUnit> getAreaByCode(String propIds){
		//查询地域
		String prop_sql = "SELECT id,name from exam_prop_val where id IN (" + propIds + ")";
		return getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
	}
	
	@Override
	public CVSet queryCVSetListByCvId(Long cvId){
		String sql = " select * from cv_set where id in ( "
	                +" select cv_set_id from cv_set_schedule where cv_schedule_id in ( " 
		            +" select SCHEDULE_id from cv_schedule where cv_id=? "
	                +" ) "
                    +")";
		List<CVSet> cvsetList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), cvId);
		if(cvsetList!=null && cvsetList.size()>0){
			return cvsetList.get(0);
		}else{
			return null;
		}
	}
	@Override
	public List<PropUnit> getAreaForAuthor(String propIds) {
		//查询地域
		String prop_sql = "SELECT t.ID,t.`NAME` FROM exam_prop_val t " +
								" LEFT JOIN sub_sys_prop_val t1 ON t1.PROP_VAL_ID = t.ID " +
									" WHERE t1.ID IN(" + propIds + ")";
		return getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
	}

}
