package com.hys.exam.dao.local.jdbc;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.model.*;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class CVManageJdbcDAO extends BaseDao implements CVManageDAO {
	/**
	 * @author  杨红强
	 * @param   String
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    删除课程单元
	 */
	@Override
	public boolean delCvUnit(String cvId, String unitId) {
		boolean resFlag = true ;
		if (cvId != null && unitId != null) {
			String sql = "delete from CV_REF_UNIT where CV_ID = " + cvId + " and UNIT_ID = " + unitId;
			int cvNum = getJdbcTemplate().update(sql) ;
			sql = "delete from CV_UNIT where ID = " + unitId ;
			cvNum = getJdbcTemplate().update(sql) ;
		}
		return resFlag ;
	}
	
	/**
	 * @author  杨红强
	 * @param   String
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    判断课程是否可以删除：true可以删除，false不能删除
	 */
	@Override
	public boolean cvDelFlag(String cvId) {
		boolean resFlag = true ;
		if (cvId != null) {
			String sql = "select count(cs.id) from cv_set cs left join cv_set_schedule cvs on cvs.CV_SET_ID=cs.id left join cv_schedule cve  on cve.SCHEDULE_ID =cvs.CV_SCHEDULE_ID where cve.cv_id = " + cvId + " and cs.STATUS not in (1,6)" ;
			int cvNum = getJdbcTemplate().queryForInt(sql) ;
			if (cvNum > 0) resFlag = false ;
		}
		return resFlag ;
	}
	
	/**
	 * @author  杨红强
	 * @param   String[]
	 * @return  boolean
	 * @time    2017-05-24
	 * 方法说明    保存课程单元的顺序————按照String[]的顺序保存单元的顺序
	 */
	@Override
	public boolean saveUnitSequence(String[] unitId) {
		boolean resFlag = true ;
		if (unitId != null && unitId.length > 0) {
			String sql = "" ;
			for (int i = 0 ; i < unitId.length ; i++) {
				sql = "update CV_UNIT set sequencenum = " + (i+1) + " where id = " + unitId[i] ;
				int resNum = getJdbcTemplate().update(sql) ;
				//System.out.println("resNum---" + resNum) ;
			}
		}
		
		return resFlag ;
	}
	
	//新增-分页
	@Override
	public void getCVListPage(PageList pl, CV queryCV) {
		List<CV> cvList = new ArrayList<CV>();
		StringBuilder sql1 = new StringBuilder();
		sql1.append("select t.*,cs.name as cvsetName from cv t left join cv_schedule cve on t.ID= cve.cv_id left join cv_set_schedule cvs on  cve.SCHEDULE_ID =cvs.CV_SCHEDULE_ID left join cv_set cs on cvs.CV_SET_ID=cs.id left join CV_REF_PROP_COURSE t1 on t1.cv_id=t.id where t.id>0");
		
		if(queryCV.getName()!=null && !"".equals(queryCV.getName())){   	//课程名称查询
			sql1.append(" and t.name like '%" + queryCV.getName() + "%' ");
		}
		
		if ( !StringUtil.checkNull(queryCV.getCreator())){
			sql1.append(" and t.creator like '%" + queryCV.getCreator() + "%' ");
		}
		
		if ( queryCV.getCourseList() != null && queryCV.getCourseList().size() >0 ){
			sql1.append(" and t1.cv_id=t.id and t1.prop_id in (");
			for(int i=0;i < queryCV.getCourseList().size() ; i++ ){
				if( i > 0 ) sql1.append(",");
				sql1.append(queryCV.getCourseList().get(i).getId());
			}
			sql1.append(")");
		}
		sql1.append(" group by t.id order by t.CREATE_DATE desc ");
		cvList=getJdbcTemplate().query(PageUtil.getPageSql(sql1.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CV.class));	
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql1.toString()));
        pl.setFullListSize(fullListSize);
		
		for ( CV simpleCV:cvList ) {
			String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			simpleCV.setCourseList(courseList);
			String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
			List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
			simpleCV.setTeacherList(teacherList);
			
			String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
			List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
			simpleCV.setOtherTeacherList(otherTeacherList);
			if (!StringUtil.checkNull(simpleCV.getCvsetName())) {
				List<PropUnit> usingItems = new ArrayList() ;
				PropUnit puObj = new PropUnit() ;
				puObj.setName(simpleCV.getCvsetName());
				usingItems.add(puObj);
				simpleCV.setUsingItem(usingItems);
			}
		}			
		pl.setList(cvList);
	}
	
	
	@Override
	public List<CV> getCVList(CV queryCV) {
		List<CV> cvList = new ArrayList<CV>();
	
		if ( queryCV.getId() == null){
			StringBuilder sql1 = new StringBuilder();
			if ( queryCV.getCourseList() != null && queryCV.getCourseList().size() >0 ){
				
				sql1.append("select t.*,cs.name as cvsetName from cv t left join cv_schedule cve on t.ID= cve.cv_id left join cv_set_schedule cvs on  cve.SCHEDULE_ID =cvs.CV_SCHEDULE_ID left join cv_set cs on cvs.CV_SET_ID=cs.id left join CV_REF_PROP_COURSE t1 on t1.cv_id=t.id where t.id>0");
				
				if(!StringUtil.checkNull(queryCV.getFile_path())) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）,项目为远程传：0,2，其它为1
					sql1.append(" and t.cv_type in (" + queryCV.getFile_path() + ") ");
				}
				if(!StringUtil.checkNull(queryCV.getAnnouncement())) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）,为项目类型：0,1	
					sql1.append(" and cs.cv_set_type=" + queryCV.getAnnouncement() + " ");
				}
				if(!StringUtil.checkNull(queryCV.getCvsetName())) {   //2017-06-26，增加项目名称查询	
					sql1.append(" and cs.name like '%" + queryCV.getCvsetName() + "%' ");
				}
				
				sql1.append(" and t1.cv_id=t.id and t1.prop_id in (");
				for(int i=0;i < queryCV.getCourseList().size() ; i++ ){
					if( i > 0 ) sql1.append(",");
						sql1.append(queryCV.getCourseList().get(i).getId());
				}
				sql1.append(") order by t.CREATE_DATE desc ");
				cvList=getJdbcTemplate().query(sql1.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CV.class));	
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					//YHQ，2017-05-23，xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
					/*
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					*/
					if (!StringUtil.checkNull(simpleCV.getCvsetName())) {
						List<PropUnit> usingItems = new ArrayList() ;
						PropUnit puObj = new PropUnit() ;
						puObj.setName(simpleCV.getCvsetName());
						usingItems.add(puObj);
						simpleCV.setUsingItem(usingItems);
					}
					
					/*String date_sql = "select to_char(create_date,'yyyy-mm-dd') as name from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));*/
					
					
				}			
			} 
			else if ( !StringUtil.checkNull(queryCV.getCreator())){
				String sql_creator= "select * from cv where creator like ? order by CREATE_DATE desc ";	
				
				if(!StringUtil.checkNull(queryCV.getFile_path())) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）,项目为远程传：0,2，其它为1
					sql_creator= "select * from cv where cv_type in (" + queryCV.getFile_path() + ") and creator like ?  order by CREATE_DATE desc ";						
				}	
				
				cvList = getJdbcTemplate().query(sql_creator, ParameterizedBeanPropertyRowMapper.newInstance(CV.class), "%"+queryCV.getCreator()+"%");
				
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					
					/*String date_sql = "select create_date from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));*/
					
					
				}			
			} 
			else if( ! StringUtil.checkNull(queryCV.getName())){  //YHQ，2017-05-23，注释：com.hys.exam.struts.action.CVSet.CVAction里name(课程名称)、itemName(项目名称)都传到setName艹艹艹艹艹
				List<PropUnit> cv_set_schedule_propList = new ArrayList<PropUnit>();
				List<PropUnit> cv_schedule_propList = new ArrayList<PropUnit>();
				String sql_select_cvSet = "select id from cv_set where cv_set.name like ?";
								
				if(!StringUtil.checkNull(queryCV.getAnnouncement())) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）,为项目类型：0,1						
					sql_select_cvSet = "select id from cv_set where cv_set_type=" + queryCV.getAnnouncement() + " and cv_set.name like ?";
				}
				
				List<PropUnit> propList = getJdbcTemplate().query(sql_select_cvSet, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class), "%"+queryCV.getName()+"%");
				if(propList != null && propList.size() >0 ){
					String sql_select_cvSet_schedule = "select cv_schedule_id as id from cv_set_schedule where cv_set_id in (";
					for( int i=0;i<propList.size();i++){
						if(i>0) sql_select_cvSet_schedule += ",";
						sql_select_cvSet_schedule += propList.get(i).getId();
					}
					sql_select_cvSet_schedule += ")";
					cv_set_schedule_propList = getJdbcTemplate().query(sql_select_cvSet_schedule, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				}
				if(cv_set_schedule_propList != null && cv_set_schedule_propList.size()>0){
					String sql_select_cvSchedule = "select cv_id as id from cv_schedule where schedule_id in (";
					for(int i=0;i<cv_set_schedule_propList.size();i++){
						if(i>0) sql_select_cvSchedule +=",";
						sql_select_cvSchedule += cv_set_schedule_propList.get(i).getId();
					}
					sql_select_cvSchedule += ")";
					cv_schedule_propList = getJdbcTemplate().query(sql_select_cvSchedule,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				}
				if(cv_schedule_propList != null && cv_schedule_propList.size() >0 ){
					String sql_select_cv = "select * from cv where cv.id in (";
					
					if(!StringUtil.checkNull(queryCV.getFile_path())) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）,项目为远程传：0,2，其它为1
						sql_select_cv= "select * from cv where cv_type in (" + queryCV.getFile_path() + ") and cv.id in ( ";						
					}
					
					for(int i=0;i<cv_schedule_propList.size();i++){
						if(i>0) sql_select_cv += ",";
						sql_select_cv += cv_schedule_propList.get(i).getId();
					}
					sql_select_cv +=") order by CREATE_DATE desc ";
					cvList = getJdbcTemplate().query(sql_select_cv, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
				}
				if(queryCV.getName()!=null && !"".equals(queryCV.getName())){
					//YHQ，2017-05-23，distinct(t.id),  -------------------com.hys.exam.struts.action.CVSet.CVAction里name(课程名称)、itemName(项目名称)都传到setName艹艹艹艹艹
					String select_name_cv="";
					if(queryCV.getCvsetName()!=null && !"".equals(queryCV.getName())){
						select_name_cv = "select distinct(t.id),t.*,cs.name as cvsetName from cv t left join cv_schedule cve on t.ID= cve.cv_id left join cv_set_schedule cvs on cve.SCHEDULE_ID =cvs.CV_SCHEDULE_ID left join cv_set cs on cvs.CV_SET_ID=cs.id left join CV_REF_PROP_COURSE t1 on t1.cv_id=t.id where cs.name like ? order by t.CREATE_DATE desc ";
					}else{
						select_name_cv = "select distinct(t.id),t.*,cs.name as cvsetName from cv t left join cv_schedule cve on t.ID= cve.cv_id left join cv_set_schedule cvs on cve.SCHEDULE_ID =cvs.CV_SCHEDULE_ID left join cv_set cs on cvs.CV_SET_ID=cs.id left join CV_REF_PROP_COURSE t1 on t1.cv_id=t.id where t.name like ? order by t.CREATE_DATE desc ";
					}
					
					if(!StringUtil.checkNull(queryCV.getFile_path())) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）,项目为远程传：0,2，其它为1						
						select_name_cv = "select distinct(t.id),t.*,cs.name as cvsetName from cv t left join cv_schedule cve on t.ID= cve.cv_id left join cv_set_schedule cvs on cve.SCHEDULE_ID "
								       + " =cvs.CV_SCHEDULE_ID left join cv_set cs on cvs.CV_SET_ID=cs.id left join CV_REF_PROP_COURSE t1 on t1.cv_id=t.id where "
								       + " t.cv_type in (" + queryCV.getFile_path() + ") and cs.cv_set_type= " + queryCV.getAnnouncement() + " and t.name like ? order by t.CREATE_DATE desc ";
					}
					
					cvList = getJdbcTemplate().query(select_name_cv, ParameterizedBeanPropertyRowMapper.newInstance(CV.class), "%"+queryCV.getName()+"%");
				}
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					//YHQ，2017-05-23，xxxxxxxxxxxxxxxxxx
					/*
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					*/					
					if (!StringUtil.checkNull(simpleCV.getCvsetName())) {
						List<PropUnit> usingItems = new ArrayList() ;
						PropUnit puObj = new PropUnit() ;
						puObj.setName(simpleCV.getCvsetName());
						usingItems.add(puObj);
						simpleCV.setUsingItem(usingItems);
					}
					
					/*String date_sql = "select to_char(create_date,'yyyy-mm-dd') from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));*/
					
					
				}			
				
			} else {
				String sql="select * from cv where id>0 order by CREATE_DATE desc ";
				
				if(!StringUtil.checkNull(queryCV.getFile_path())) {//YHQ，2017-06-05，0点播，1面授，2直播（默认的老课程都是点播）,项目为远程传：0,2，其它为1					
					sql="select * from cv where cv_type in (" + queryCV.getFile_path() + ") and id>0 order by CREATE_DATE desc ";
				}
				
				cvList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					
				/*	String date_sql = "select to_char(create_date,'yyyy-mm-dd') from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));*/
					
					
				}			
			} 
		
		} else if (queryCV.getId() != null) {
			
			Long ID = queryCV.getId();
			String sql="select * from cv where id=" + queryCV.getId();
			cvList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
			for(CV simpleCV:cvList){

				List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
				String sql_teacher = "select t1.expert_id as id, t2.name from cv t, cv_ref_teacher t1, expert_info t2 where t.id=t1.cv_id and t.id="+ID +" and t1.expert_id=t2.id and t1.type="+Constants.TeacherType;
				teacherList = getJdbcTemplate().query(sql_teacher, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
				simpleCV.setTeacherList(teacherList);
				
				List<ExpertInfo> other_TeacherList = new ArrayList<ExpertInfo>();
				String sql_otherTeacherList = "select t1.expert_id as id, t2.name from cv t, cv_ref_teacher t1, expert_info t2 where t.id=t1.cv_id and t.id="+ID +" and t1.expert_id=t2.id and t1.type="+Constants.OtherTeacherType;
				other_TeacherList = getJdbcTemplate().query(sql_otherTeacherList, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
				simpleCV.setOtherTeacherList(other_TeacherList);
				
				List<PropUnit> propList = new ArrayList<PropUnit>();
				String sql_propList ="select t1.prop_id as id,t2.name from CV t, cv_ref_prop_course t1,exam_prop_val t2,sub_sys_prop_val t3 where t.id=t1.cv_id and t1.prop_id=t3.PROP_VAL_ID and t3.PROP_VAL_ID=t2.id and t.id="+ ID; 
				propList = getJdbcTemplate().query(sql_propList, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				simpleCV.setCourseList(propList);
				
				String sql_cvUnitList = "";
				List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
				sql_cvUnitList = "select t2.* from cv t,cv_ref_unit t1,cv_unit t2 where t.id=t1.cv_id and t1.unit_id=t2.id and t.id="+ID + " order by t2.sequencenum asc "; //YHQ，2017-05-24，添加按顺序排序
				//判断该课程是否为直播课程
				String sql_live = "SELECT * FROM cv_live t WHERE t.cv_id = "+queryCV.getId();
				List<CvLive> livelist = getJdbcTemplate().query(sql_live, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
				if(livelist != null && livelist.size() > 0)
					sql_cvUnitList = "select t2.* from cv t,cv_ref_unit t1,cv_unit t2 where t.id=t1.cv_id and t1.unit_id=t2.id and t.id="+ID + " AND t2.unit_make_type = 2 order by t2.sequencenum asc ";
				cvUnitList = getJdbcTemplate().query(sql_cvUnitList, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
				simpleCV.setUnitList(cvUnitList);
			}
			
		} 
		return cvList;
	}
	
	
	@Override
	public Long addCV(CV cv) {
		
		Long Id = cv.getId();
		if(Id == null) Id = this.getNextId("CV");
		cv.setId(Id);
		
		String courseName = cv.getName();
		String serial = cv.getSerial_number();
		List<PropUnit> prop_course = cv.getCourseList();
		String brand = cv.getBrand();
		List<ExpertInfo> teacher = cv.getTeacherList();
		List<ExpertInfo> other_Teacher = cv.getOtherTeacherList();
		String introduction = cv.getIntroduction();
		String filePath=cv.getFile_path();
		String announcement = cv.getAnnouncement();
		String creator = cv.getCreator();
		List<CVUnit> cvUnitList = cv.getUnitList();
		
		
		String sql = "INSERT INTO CV (ID,NAME,SERIAL_NUMBER,BRAND,INTRODUCTION,ANNOUNCEMENT,FILE_PATH,CREATE_DATE,cv_type,is_add_out_chain,cv_url,cv_address,is_need_apply,apply_num) " +
				"VALUES(?,?,?,?,?,?,?,SYSDATE(),?,?,?,?,?,?)";
		getSimpleJdbcTemplate().update(sql, Id,courseName,serial,brand,introduction,announcement,filePath,cv.getCv_type(),cv.getIs_add_out_chain(),cv.getCv_url(),cv.getCv_address(),cv.getIs_need_apply(),cv.getApply_num());//YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）
		String sql_creator = "UPDATE CV SET CREATOR='"+creator.toString() +"' where id="+Id;
		getSimpleJdbcTemplate().update(sql_creator);
//Insert Teacher
		
		if(teacher != null && teacher.size()>0){
			
			for(int i=0;i<teacher.size();i++){
				String sql_teacher = "INSERT INTO CV_REF_TEACHER (CV_ID,EXPERT_ID,TYPE) VALUES(?,?,?)";
				getSimpleJdbcTemplate().update(sql_teacher, Id,teacher.get(i).getId(),Constants.TeacherType);
			}
		}
//Insert OtherTeacher
		if(other_Teacher != null && other_Teacher.size()>0){
			for(int i=0;i<other_Teacher.size();i++){
				String sql_other_teacher = "INSERT INTO CV_REF_TEACHER (CV_ID,EXPERT_ID,TYPE) VALUES (?,?,?)";
				getSimpleJdbcTemplate().update(sql_other_teacher, Id,other_Teacher.get(i).getId(),Constants.OtherTeacherType);
			}
		}
		
//Insert Course	
		if(prop_course != null && prop_course.size()>0){
			for(int i=0; i<prop_course.size();i++){
				String sql_prop_course = "INSERT INTO CV_REF_PROP_COURSE (CV_ID,PROP_ID) VALUES (?,?)";
				getSimpleJdbcTemplate().update(sql_prop_course, Id,prop_course.get(i).getId());
			}
		}
		
//Insert CVUnit
		
		/*if(cvUnitList != null && cvUnitList.size()>0){
			for(int i=0;i<cvUnitList.size();i++){
				String sql_CVRefUnit = "INSERT INTO CV_REF_UNIT (CV_ID,UNIT_ID) VALUES (?,?)";
				getSimpleJdbcTemplate().update(sql_CVRefUnit, Id,cvUnitList.get(i).getId());
				String sql_cvUnit = "UPDATE CV_UNIT SET ISBOUND=1 WHERE ISBOUND=0";
				getSimpleJdbcTemplate().update(sql_cvUnit);
				if(cvUnitList.get(i).getContent()!=null){
					String sql_cvUnit_Content = "update cv_unit set content='"+cvUnitList.get(i).getContent() +"' where id="+cvUnitList.get(i).getId();
					getSimpleJdbcTemplate().update(sql_cvUnit_Content);
				}
			}
		}*/
		return Id;
	}

	@Override
	public boolean updateCV(CV cv) {
		Long id=cv.getId();
		String courseName = cv.getName();
		String serial = cv.getSerial_number();
		List<PropUnit> prop_course = cv.getCourseList();
		String brand = cv.getBrand();
		List<ExpertInfo> teacher = cv.getTeacherList();
		List<ExpertInfo> other_Teacher = cv.getOtherTeacherList();
		String introduction = cv.getIntroduction();
		String filePath=cv.getFile_path();
		String announcement = cv.getAnnouncement();
		String creator = cv.getCreator();
		List<CVUnit> cvUnitList = cv.getUnitList();
		List values = new ArrayList();
		
		//YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）
		String sql = "UPDATE CV SET NAME=?, SERIAL_NUMBER=?, BRAND=?, INTRODUCTION=?, FILE_PATH=?,ANNOUNCEMENT=?, CREATE_DATE=SYSDATE(), CREATOR=?,cv_type=? WHERE ID="+cv.getId();
		values.add(courseName);
		values.add(serial);
		values.add(brand);
		values.add(introduction);
		values.add(filePath);
		values.add(announcement);
		values.add(creator.toString());
		values.add(cv.getCv_type()) ; //YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）
		
		getSimpleJdbcTemplate().update(sql, values.toArray());
		
//Insert Teacher
		
		if(teacher != null && teacher.size()>0){
			String delete_teacher = "delete from cv_ref_teacher where type="+Constants.TeacherType+" and cv_id="+cv.getId();
			getSimpleJdbcTemplate().update(delete_teacher);
			for(int i=0;i<teacher.size();i++){
				String sql_teacher="insert into cv_ref_teacher (cv_id,expert_id,type) values (?,?,?)";
				
				getSimpleJdbcTemplate().update(sql_teacher,cv.getId(),teacher.get(i).getId(),Constants.TeacherType);
			}
		}
		
		if(other_Teacher != null && other_Teacher.size()>0){
			String delete_other_Teacher = "delete from cv_ref_teacher where type="+Constants.OtherTeacherType+" and cv_id="+cv.getId();
			getSimpleJdbcTemplate().update(delete_other_Teacher);
			for(int i=0;i<other_Teacher.size();i++){
				String sql_other_teacher = "insert into cv_ref_teacher (cv_id,expert_id,type) values (?,?,?)";
				getSimpleJdbcTemplate().update(sql_other_teacher,cv.getId(),other_Teacher.get(i).getId(),Constants.OtherTeacherType);
			}
		}
		
//Insert Course	
		if(prop_course != null && prop_course.size()>0){
			String delete_prop_course = "delete from CV_REF_PROP_COURSE where CV_ID="+cv.getId();
			getSimpleJdbcTemplate().update(delete_prop_course);
			for(int i=0; i<prop_course.size();i++){
//				String sql_prop_course = "UPDATE CV_REF_PROP_COURSE SET PROP_ID="+prop_course.get(i).getId()+" WHERE CV_ID="+cv.getId();
//				getSimpleJdbcTemplate().update(sql_prop_course);
				String sql_prop_course = "insert into CV_REF_PROP_COURSE (cv_id,prop_id) values (?,?)";
				getSimpleJdbcTemplate().update(sql_prop_course,cv.getId(),prop_course.get(i).getId());
				
			}
		}
		
		
		//暂时屏蔽修改课程时联动单元信息
		
		
        /*//Insert CVUnit
		
		if(cvUnitList != null && cvUnitList.size()>0){
			String sql_select = "select count(1) from cv_ref_unit where cv_id="+cv.getId();
			int count = getJdbcTemplate().queryForInt(sql_select);
			if(count < cvUnitList.size()){
				for(int i=0;i<count;i++){
					String sql_cvUnit = "UPDATE CV_UNIT t,CV t1,CV_REF_UNIT t2 SET t.ISBOUND=1 WHERE t.ISBOUND=0 AND t2.CV_ID="+cv.getId()+" and t2.unit_id="+cvUnitList.get(i).getId();
					getSimpleJdbcTemplate().update(sql_cvUnit);
				}
				for(int i=count;i<cvUnitList.size();i++){
					String sql_CVRefUnit = "INSERT INTO CV_REF_UNIT (CV_ID,UNIT_ID) VALUES (?,?)";
					getSimpleJdbcTemplate().update(sql_CVRefUnit, cv.getId(),cvUnitList.get(i).getId());
					String sql_cvUnit = "UPDATE CV_UNIT SET ISBOUND=1 WHERE ISBOUND=0";
					getSimpleJdbcTemplate().update(sql_cvUnit);
					if(cvUnitList.get(i).getContent()!=null){
						String sql_cvUnit_Content = "update cv_unit set content=? where id="+cvUnitList.get(i).getId();
						getSimpleJdbcTemplate().update(sql_cvUnit_Content, cvUnitList.get(i).getContent());
					}
				}
			}
			for(int i=0;i<cvUnitList.size();i++){
				String sql_CVRefUnit = "UPDATE CV_REF_UNIT SET UNIT_ID="+cvUnitList.get(i).getId()+" WHERE CV_ID="+cv.getId();
				getSimpleJdbcTemplate().update(sql_CVRefUnit);
				String sql_cvUnit = "UPDATE CV_UNIT t,CV t1,CV_REF_UNIT t2 SET t.ISBOUND=1 WHERE t.ISBOUND=0 AND t2.CV_ID="+cv.getId()+" and t2.unit_id="+cvUnitList.get(i).getId();
				getSimpleJdbcTemplate().update(sql_cvUnit);
				
				if(cvUnitList.get(i).getPoint() == 1 ){
					
					getSimpleJdbcTemplate().update("update cv_unit set point = 1 where cv_unit.id="+cvUnitList.get(i).getId());
				}
				if(cvUnitList.get(i).getState() == 1 )
					getSimpleJdbcTemplate().update("update cv_unit set state = 1 where cv_unit.id="+cvUnitList.get(i).getId());
				
				if(cvUnitList.get(i).getContent()!=null){
					String sql_cvUnit_Content = "update cv_unit set content="+cvUnitList.get(i).getContent() +" where id="+cvUnitList.get(i).getId();
					getSimpleJdbcTemplate().update(sql_cvUnit_Content);
				}
			}

		}*/
		
		
		return true;
	}
	
	@Override
	public void deleteTeacher(CV cv){
		String delete_teacher = "delete from cv_ref_teacher where type="+Constants.TeacherType+" and cv_id="+cv.getId();
		getSimpleJdbcTemplate().update(delete_teacher);
	}
	
	@Override
	public void deleteTeacherO(CV cv){
		String delete_other_Teacher = "delete from cv_ref_teacher where type="+Constants.OtherTeacherType+" and cv_id="+cv.getId();
		getSimpleJdbcTemplate().update(delete_other_Teacher);
	}
	
	@Override
	public boolean deleteCV(CV cv) {
		String sql_none = "select prop_id as id from cv_ref_prop_course where cv_ref_prop_course.cv_id="+cv.getId();
		List<PropUnit> propCourse = getJdbcTemplate().query(sql_none, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		
		if(propCourse !=null && propCourse.size()>0){
			String sql_prop_course_delete = "DELETE FROM CV_REF_PROP_COURSE WHERE CV_REF_PROP_COURSE.CV_ID="+cv.getId();
			getSimpleJdbcTemplate().update(sql_prop_course_delete);
		}
		String sql_teacher_none = "select expert_id as id from cv_ref_teacher where cv_ref_teacher.cv_id="+cv.getId();
		List<ExpertInfo> teacherInfo = getJdbcTemplate().query(sql_teacher_none, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		
		if(teacherInfo != null && teacherInfo.size()>0){
			String sql_teacher_delete = "DELETE FROM CV_REF_TEACHER WHERE CV_REF_TEACHER.CV_ID="+cv.getId();
			getSimpleJdbcTemplate().update(sql_teacher_delete);
		}	
		
		String sql_cvUnit_ref_none = "SELECT UNIT_ID AS ID FROM CV_REF_UNIT WHERE CV_REF_UNIT.CV_ID="+cv.getId();
		List<PropUnit> cvUnit_ref = getJdbcTemplate().query(sql_cvUnit_ref_none, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		List<PropUnit> cvUnit_List = new ArrayList<PropUnit>();
		if(cvUnit_ref != null && cvUnit_ref.size() > 0){
		
			for(int i=0;i<cvUnit_ref.size();i++){
				
				String sql_cvUnit_none = "SELECT T1.ID FROM CV_UNIT T1,CV_REF_UNIT T2, CV T3 WHERE T1.ID="+cvUnit_ref.get(i).getId()+" AND T2.CV_ID="+cv.getId();
				cvUnit_List = getJdbcTemplate().query(sql_cvUnit_none, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			}
			String sql_delete_cvUnit_ref = "DELETE FROM CV_REF_UNIT WHERE CV_REF_UNIT.CV_ID="+cv.getId();
			getSimpleJdbcTemplate().update(sql_delete_cvUnit_ref);
			
		}
		
		if(cvUnit_List !=null && cvUnit_List.size()>0){
			for(int i=0;i<cvUnit_List.size();i++){
				String sql_delete_cvUnit = "DELETE FROM CV_UNIT WHERE CV_UNIT.ID="+cvUnit_List.get(i).getId()/*+" AND CV_REF_UNIT.CV_ID="+cv.getId()*/;
				getSimpleJdbcTemplate().update(sql_delete_cvUnit);
			}
			
		}
		
		String sql_delete = "DELETE FROM CV WHERE ID="+cv.getId();
		getSimpleJdbcTemplate().update(sql_delete);
		
		return true;
	}

	@Override
	public Long addCVUnit(CVUnit cvUnit) {
			
		Long cvUnit_Id = cvUnit.getId();
		if (cvUnit_Id == null || cvUnit_Id < 1) {
			cvUnit_Id = this.getNextId("CV_UNIT");
			cvUnit.setId(cvUnit_Id);
		}
		//YHQ，2017-03-11，添加克隆时把指标也克隆过去；2017-05-25，添加克隆时把在课程中出现的顺序也克隆过去
		String sql_addUnit = "INSERT INTO CV_UNIT (ID,NAME,TYPE,`POINT`,STATE,CONTENT,ISBOUND,quota,sequencenum,unit_make_type) VALUES (?,?,?,?,?,?,?,?,?,?)"; 
		getSimpleJdbcTemplate().update(sql_addUnit, cvUnit_Id,cvUnit.getName(),cvUnit.getType(),cvUnit.getPoint(),cvUnit.getState(),cvUnit.getContent(),cvUnit.getIsBound(),cvUnit.getQuota(),cvUnit.getSequenceNum(),cvUnit.getUnitMakeType());
		
		return cvUnit_Id;
	}
	
	/**
	 * @author  ZJG
	 * @param   int
	 * @return  Long
	 * @time    2016-12-27
	 * 方法说明    保存课程单元关联表信息
	 */
	@Override
	public Long addCvRefUnit(int cvId,int unitId) {
		String sql_addCvRefUnit = "INSERT INTO CV_REF_UNIT (CV_ID,UNIT_ID) VALUES (?,?)";
		long id = getSimpleJdbcTemplate().update(sql_addCvRefUnit,cvId,unitId);
		return id;
	}
	@Override
	public Long addUnionRefSource(int cvId,String extend_read,String chooseSourseIDs,int key_nums,String key_words) {
		//更新原有单元信息
		String sql1 = "UPDATE cv_unit SET CONTENT=?,quota=?,key_words=?  WHERE ID=?";
		long id = getSimpleJdbcTemplate().update(sql1,extend_read,key_nums,key_words,cvId);

		String sql3 = "DELETE FROM cv_unit_ref_source WHERE UNIT_ID=?";
		getSimpleJdbcTemplate().update(sql3,cvId);
		//2：添加单元与来源关联
		String sql2 = "INSERT INTO cv_unit_ref_source (UNIT_ID, SOURCE_ID) VALUES (?,?);";
		String[] sourseID=chooseSourseIDs.split(",");
		for (String sourse : sourseID) {
			getSimpleJdbcTemplate().update(sql2,cvId,sourse);
		}
		return id;
	}
	@Override
	public 	List<CvUnitRefSource> getCVUnitRefSourceList(Long unit_id){
		List<CvUnitRefSource> cvUnitRefSourceList=new ArrayList<>();
		String sql = "SELECT * from cv_unit_ref_source c where c.UNIT_ID=?";
		cvUnitRefSourceList= getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvUnitRefSource.class),unit_id);
		return cvUnitRefSourceList;
	}
	@Override
	public CVUnit getCVUnit(Long unit_id){
		CVUnit cvUnit=new CVUnit();
		List<CVUnit> cvUnitRefSourceList=new ArrayList<>();
		String sql = "SELECT * from cv_unit c where c.ID=?";
		cvUnitRefSourceList= getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class),unit_id);
		if(cvUnitRefSourceList.size()>0){
			cvUnit=cvUnitRefSourceList.get(0);
		}
		return cvUnit;
	}

	@Override
	public List<CVUnit> getCVUnitList(CVUnit cvUnit) {
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		String sql = "SELECT * FROM CV_UNIT WHERE ID>0";
		if(cvUnit.getId() != null)
		{
			
		}
		else if (cvUnit.getIsBound() == 0) {
			sql += " and ISBOUND=0";
		} else {
			sql += " and ISBOUND=1";
		}
	
		if(cvUnit.getId()!=null)
			sql += " and id="+cvUnit.getId();
		
		cvUnitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
	
		
		return cvUnitList;
	}

	@Override
	public List<CVUnit> getCloneCVUnitList(CV queryCV) {
		Long Id = queryCV.getId();
		String name = queryCV.getName();
		String sql_cloneCopyUnit = "select t1.* from cv_unit t1,cv_ref_unit t2 where t2.unit_id=t1.id";
		if(Id>0){
			sql_cloneCopyUnit +=" and t2.cv_id="+Id;
		}
		if(!StringUtils.checkNull(name)){
			sql_cloneCopyUnit +=" and t2.cv_id="+Id;
		}
		List<CVUnit> cvCloneUnitList = new ArrayList<CVUnit>();
		cvCloneUnitList = getJdbcTemplate().query(sql_cloneCopyUnit, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
		
		
		
		return cvCloneUnitList;
	}

	@Override
	public List<CV> getCloneCVList(CV queryCV) {
		Long Id = queryCV.getId();
		String sql_cloneCopyCV = "select * from cv where cv.id="+Id;
		List<CV> cvCloneList = getJdbcTemplate().query(sql_cloneCopyCV, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
		return cvCloneList;
	}

	@Override
	public List<ExpertInfo> getTeacherList() {
		String getTeacher = "select id,name from expert_info where id>0 and personage=1 and lockstate=1";
		List<ExpertInfo> teacherList = getJdbcTemplate().query(getTeacher, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		return teacherList;
	}

	@Override
	public void addUnionUpdate(CV queryCV) {
		Long parentId = queryCV.getId();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		cvUnitList = queryCV.getUnitList();
		for(CVUnit simpleUnit:cvUnitList){
			Long cvUnit_Id = simpleUnit.getId();
			if(cvUnit_Id == null) cvUnit_Id = this.getNextId("CV_UNIT");
			String sql_addUnit = "INSERT INTO CV_UNIT (ID,NAME,TYPE,`POINT`,STATE,CONTENT,ISBOUND) VALUES (?,?,?,?,?,?,?)";
			getSimpleJdbcTemplate().update(sql_addUnit, cvUnit_Id,simpleUnit.getName(),simpleUnit.getType(),simpleUnit.getPoint(),simpleUnit.getState(),simpleUnit.getContent(),simpleUnit.getIsBound());
			String sql_ref_Unit = "INSERT INTO CV_REF_UNIT (CV_ID,UNIT_ID) VALUES(?,?)";
			getJdbcTemplate().update(sql_ref_Unit,parentId,cvUnit_Id);
			
		}
		
	}

	@Override
	public int cloneCVUnitList(CV queryCV) {
		List<CVUnit> list = this.getCloneCVUnitList(queryCV);
		for (CVUnit cvUnit:list) {
			cvUnit.setId(-1L);
			cvUnit.setIsBound(0);
			this.addCVUnit(cvUnit);
		}
		return list.size();
	}

	@Override
	public boolean deleteUnit(Long id) {
		String sql_select_unit_ref = "DELETE FROM CV_REF_UNIT WHERE UNIT_ID="+id;
		getSimpleJdbcTemplate().update(sql_select_unit_ref);
		String sql_delete_Unit = "DELETE FROM CV_UNIT WHERE ID="+id;
		getSimpleJdbcTemplate().update(sql_delete_Unit);
		return true;
	}

	@Override
	public void swapCVUnit(CVUnit unit1, CVUnit unit2) {
		Long unit1_id = unit1.getId();
		Long unit2_id = unit2.getId();
		String sql_unit1_to_unit2 = "update cv_unit set cv_unit.name=?, cv_unit.`point`=?, cv_unit.state=?, cv_unit.content=?, cv_unit.isbound=? where cv_unit.id="+unit2_id;		
		getSimpleJdbcTemplate().update(sql_unit1_to_unit2, unit1.getName(), unit1.getPoint(), unit1.getState(), unit1.getContent(), unit1.getIsBound());
		String sql_unit2_to_unit1 = "update cv_unit set cv_unit.name=?, cv_unit.`point`=?, cv_unit.state=?, cv_unit.content=?, cv_unit.isbound=? where cv_unit.id="+unit1_id;
		getSimpleJdbcTemplate().update(sql_unit2_to_unit1, unit2.getName(), unit2.getPoint(), unit2.getState(), unit2.getContent(), unit2.getIsBound());
	}

	@Override
	public void updateCVUnit(CVUnit cvUnit) {
		if(cvUnit.getPoint() != null){ //YHQ，2017-04-04
			String sql_update_point = "update cv_unit set cv_unit.`point`="+cvUnit.getPoint()+" where cv_unit.id="+cvUnit.getId();
			getSimpleJdbcTemplate().update(sql_update_point);
		}
		
		if(cvUnit.getState() != null){ //YHQ，2017-04-04
			String sql_update_state = "update cv_unit set cv_unit.state="+cvUnit.getState()+" where cv_unit.id="+cvUnit.getId();
			getSimpleJdbcTemplate().update(sql_update_state);
		}
		
	}

	@Override
	public void updateUnion(CVUnit cvUnit) {
		
		String sql_Update_CVUnit_Content = "UPDATE CV_UNIT SET CONTENT=? WHERE ID="+cvUnit.getId();
		getSimpleJdbcTemplate().update(sql_Update_CVUnit_Content, cvUnit.getContent());
	}

	@Override
	public List<CVUnit> getCVUnitList(CV queryCV) {
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		
		String sql_select_cvUnit_ID = "SELECT UNIT_ID AS ID FROM CV_REF_UNIT WHERE CV_REF_UNIT.CV_ID="+queryCV.getId();
		List<PropUnit> prop_for_cvUnit = getJdbcTemplate().query(sql_select_cvUnit_ID, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		
		if(prop_for_cvUnit != null && prop_for_cvUnit.size() >0 ){
			String sql_select_cvUnit = "SELECT * FROM CV_UNIT WHERE CV_UNIT.ID IN(";
			for(int i=0; i<prop_for_cvUnit.size();i++){
				if(i>0) sql_select_cvUnit += ",";
				sql_select_cvUnit += prop_for_cvUnit.get(i).getId();
			}
			sql_select_cvUnit += ")";
			cvUnitList = getJdbcTemplate().query(sql_select_cvUnit, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
		}
		
		return cvUnitList;
	}

	@Override
	public List<Long> getMaterialIds(long unit_id) {
		String sql = "select material_id as value from cv_unit_ref_material where unit_id = ?";
		return getJdbcTemplate().queryForList(sql, Long.class, unit_id);
	}

	@Override
	public List<QualityModel> getQualityList(long unit_id) {
		String sql = "select PROP_ID as ID, LEVEL from cv_unit_ref_quality where unit_id=" + unit_id;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
	}
	
	/**
	 * @author  张建国
	 * @time    2017-01-05
	 * @param   List
	 * @return  List
	 * 方法说明：查询医师信息
	 */
	@Override
	public List<ExpertInfo> getTeacherList(List<PropUnit> tempList) {
		List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
		String getTeacher=" select id,name from expert_info where 1=1 and lockstate=1 ";
		if(tempList!=null && tempList.size()>0){
			getTeacher = getTeacher + " and id in ( ";
			for(int i=0;i<tempList.size();i++){
				if(i<tempList.size()-1){
					getTeacher = getTeacher + tempList.get(i).getId() +",";
				}else if(i==tempList.size()-1){
					getTeacher = getTeacher + tempList.get(i).getId();
				}
			}
			getTeacher = getTeacher + ")";
		}
		teacherList = getJdbcTemplate().query(getTeacher, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		return teacherList;
	}

	@Override
	public Long addCVSchedule(CVSchedule cvschedule) {
		Long schedule_ID = this.getNextId("CV_SCHEDULE");
		String sql_add = "insert into CV_SCHEDULE (SCHEDULE_ID, CV_ID) values (?, ?)";
		getJdbcTemplate().update(sql_add, schedule_ID, cvschedule.getSchedule_id());
		return schedule_ID;
	}

	@Override
	public Long addCVSetSchedule(int proId, int cvId) {
		String sql_add = "insert into CV_SET_SCHEDULE (CV_SET_ID, CV_SCHEDULE_ID) values (?, ?)";
		getJdbcTemplate().update(sql_add, proId, cvId);
		
		//YHQ，2017-05-30，克隆课程后保存课程顺序
		String sql_update = "update CV_SCHEDULE set sequenceNum = schedule_id where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID = " + proId + " ) order by schedule_id asc  " ;
		getJdbcTemplate().update(sql_update) ;
		
		Long result = 1L;
		return result;
	}
	
	@Override
	public List<CV> queryCVList(int proId){
		String sql_query = " select * from cv c where c.ID in ( "
                             +" select cve.CV_ID from cv_schedule cve where cve.SCHEDULE_ID in ( "
                               +" select cvs.CV_SCHEDULE_ID from cv_set_schedule cvs where cvs.CV_SET_ID = "+proId
	                           +" ) "
                           +")";
		List<CV> cvCloneList = getJdbcTemplate().query(sql_query, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
		return cvCloneList;
	}
	
	/**
	 * @author   ZJG
	 * @time     2017-01-05
	 * @param    String
	 * @return   List
	 * 方法说明： 根据教师姓名查询教师集合
	 */
	@Override
	public List<ExpertInfo> getTeacherListByName(String name){
		String getTeacher = "select id,name from expert_info where name like ? and lockstate=1";
		List<ExpertInfo> teacherList = getJdbcTemplate().query(getTeacher, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), "%"+name+"%");
		return teacherList;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-02-28
	 * @param    Log
	 * @return   Boolean
	 * 方法说明：YHQ 添加项目时克隆课程把源课程单元下的能力模型也一并克隆过去，2017-02-28
	 */
	@Override
	public boolean cloneAddCVUnit(Long oldUnitId, Long newUnitId) {
		String sql = "INSERT INTO cv_unit_ref_quality(UNIT_ID,PROP_ID,LEVEL,STATE,PARENT_PROP_ID) SELECT " + newUnitId + ",PROP_ID,LEVEL,STATE,PARENT_PROP_ID FROM cv_unit_ref_quality where UNIT_ID = " + oldUnitId ;
		int insetNum = getJdbcTemplate().update(sql) ;
		return true ;
	}

	@Override
	public Long saveCvLive(CvLive cvl) {
		Long Id = cvl.getId();
		if(Id == null) Id = this.getNextId("cv_live");
		
		String sql = "insert into cv_live (id,class_no,class_name,number," +
				"teacher_token,assistant_token,student_token,studentClient_token," +
				"start_date,invalid_date,teacher_join_url,student_join_url,is_web_join,is_client_join," +
				"scene,cv_id,create_date,modify_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE(),?)";
		getSimpleJdbcTemplate().update(sql, Id,cvl.getClass_no(),cvl.getClass_name(),cvl.getNumber(),cvl.getTeacher_token(),
				cvl.getAssistant_token(),cvl.getStudent_token(),cvl.getStudentClient_token(),
				cvl.getStart_date(),cvl.getInvalid_date(),cvl.getTeacher_join_url(),
				cvl.getStudent_join_url(),cvl.getIs_web_join(),cvl.getIs_client_join(),
				cvl.getScene(),cvl.getCv_id(),cvl.getModify_date());
		return Id;
	}

	@Override
	public List<CvLive> queryCvLiveList(Long cvId) {
		String sql = "SELECT * FROM cv_live t WHERE t.cv_id = "+cvId;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
	}

	@Override
	public void updateLive(CvLive cvl) {
		List values = new ArrayList();
		String sql = "UPDATE cv_live SET class_no=?, " +
				"class_name=?, number=?, teacher_token=?, " +
				"assistant_token=?,student_token=?, studentClient_token=?," +
				"start_date=?,invalid_date=?, teacher_join_url=?," +
				"student_join_url=?,is_web_join=?, is_client_join=?," +
				"scene=?, course_make_type=?, modify_date=SYSDATE() WHERE cv_id="+cvl.getCv_id();
		values.add(cvl.getClass_no());
		values.add(cvl.getClass_name());
		values.add(cvl.getNumber());
		values.add(cvl.getTeacher_token());
		values.add(cvl.getAssistant_token());
		values.add(cvl.getStudent_token());
		values.add(cvl.getStudentClient_token());
		values.add(cvl.getStart_date());
		values.add(cvl.getInvalid_date());
		values.add(cvl.getTeacher_join_url());
		values.add(cvl.getStudent_join_url());
		values.add(cvl.getIs_web_join());
		values.add(cvl.getIs_client_join());
		values.add(cvl.getScene());
		values.add(cvl.getCourse_make_type());
		
		getSimpleJdbcTemplate().update(sql, values.toArray());
	}

	@Override
	public void getCVListByPage(PageList pl, CV queryCV) {

		List<CV> cvList = new ArrayList<CV>();
		StringBuilder sql1 = new StringBuilder();
		sql1.append("select t.*,cs.name as cvsetName from cv t left join cv_schedule cve on t.ID= cve.cv_id left join cv_set_schedule cvs on  cve.SCHEDULE_ID =cvs.CV_SCHEDULE_ID left join cv_set cs on cvs.CV_SET_ID=cs.id left join CV_REF_PROP_COURSE t1 on t1.cv_id=t.id where t.id>0 ");
		
		if(queryCV.getCv_type()!=null && !queryCV.getCv_type().equals("")){
			sql1.append(" and t.cv_type = '"+queryCV.getCv_type()+"'");
		}
		
		if(queryCV.getBrand()!=null && !queryCV.getBrand().equals("")){
			String [] code = queryCV.getBrand().split(",");
			if(code.length > 1){
				sql1.append(" and (");
				for(int i=0; i<code.length; i++){
					if(i==0){
						sql1.append("find_in_set('"+code[i]+"',t.BRAND)");
					}else{
						sql1.append(" or find_in_set('"+code[i]+"',t.BRAND)");
					}
				}
				sql1.append(")");
			}else{
				sql1.append(" and find_in_set('"+code[0]+"',t.BRAND)");
			}
		}
		
		if(queryCV.getName()!=null && !"".equals(queryCV.getName())){   	//课程名称查询
			sql1.append(" and t.name like '%" + queryCV.getName() + "%' ");
		}
		
		if ( !StringUtil.checkNull(queryCV.getCreator())){
			sql1.append(" and t.creator like '%" + queryCV.getCreator() + "%' ");
		}
		
		if ( queryCV.getCourseList() != null && queryCV.getCourseList().size() >0 ){
			sql1.append(" and t1.cv_id=t.id and t1.prop_id in (");
			for(int i=0;i < queryCV.getCourseList().size() ; i++ ){
				if( i > 0 ) sql1.append(",");
				sql1.append(queryCV.getCourseList().get(i).getId());
			}
			sql1.append(")");
		}
		
		if(!StringUtil.checkNull(queryCV.getCvsetName())){
			sql1.append(" and cs.name like  '%" + queryCV.getCvsetName() + "%'");
		}
		
		sql1.append(" group by t.id order by t.CREATE_DATE desc ");
		cvList=getJdbcTemplate().query(PageUtil.getPageSql(sql1.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CV.class));	
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql1.toString()));

//        int fullListSize = cvList.size();
        pl.setFullListSize(fullListSize);
		

		for ( CV simpleCV:cvList ) {
			String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, sub_sys_prop_val t4, CV t2 where t1.prop_id=t4.PROP_VAL_ID and t3.id=t4.PROP_VAL_ID and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			simpleCV.setCourseList(courseList);
			String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
			List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
			simpleCV.setTeacherList(teacherList);
			
			String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
			List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
			simpleCV.setOtherTeacherList(otherTeacherList);

			//查询引用项目的名称
			String querySetName_sql = "select t2.name from cv_set t2 where t2.id in (select t1.CV_SET_ID from cv_set_schedule t1 where t1.CV_SCHEDULE_ID in (select t.SCHEDULE_ID from cv_schedule t where t.CV_ID = ? ))";
			String setName = null;
			
			try {
				setName = getJdbcTemplate().queryForObject(querySetName_sql, String.class, simpleCV.getId());
			} catch (DataAccessException e) {
			}
			//如果能查出来引用项目的名称，则设置其值
			if (!StringUtil.checkNull(setName)) {
				List<PropUnit> usingItems = new ArrayList() ;
				PropUnit puObj = new PropUnit() ;
				puObj.setName(setName);
				usingItems.add(puObj);
				simpleCV.setUsingItem(usingItems);
			}
			
		}	
		

		pl.setList(cvList);
	}

	@Override
	public boolean delCvLive(CvLive cvlive) {
		boolean resFlag = true ;
		if (cvlive.getCv_id() != null && !"".equals(cvlive.getCv_id())) {
			String sql = "delete from cv_live  where cv_id = " + cvlive.getCv_id() ;
			getJdbcTemplate().update(sql) ;
		}
		return resFlag ;
	}

	@Override
	public int updateCvUnitForLive(CVUnit cvUnit) {
		String sql = "UPDATE CV_UNIT SET NAME=? WHERE ID="+cvUnit.getId();
		return getSimpleJdbcTemplate().update(sql, cvUnit.getName());
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.dao.local.CVManageDAO#updateCVUnitName(com.hys.exam.model.CVUnit)
	 */
	@Override
	public int updateCVUnitName(CVUnit cvUnit) {
		
		
			String sql_update_point = "update cv_unit set cv_unit.name='"+cvUnit.getName()+"' where cv_unit.id="+cvUnit.getId();
			return getSimpleJdbcTemplate().update(sql_update_point);
		
	
		
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.dao.local.CVManageDAO#updateCVUnitType(com.hys.exam.model.CVUnit)
	 */
	@Override
	public int updateCVUnitType(CVUnit cvUnit) {
		
		String sql_update_point = "update cv_unit set cv_unit.type="+cvUnit.getType()+" where cv_unit.id="+cvUnit.getId();
		return getSimpleJdbcTemplate().update(sql_update_point);
		
	}
}
