package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.UserImageManageDAO;

import com.hys.exam.model.ExamProp;
import com.hys.exam.model.GeneralUserImage;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SpecialUserImage;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.model.UserImage;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

public class UserImageManageJDBCDAO extends BaseDao implements
		UserImageManageDAO {

	@Override
	public List<UserImage> getUserImageList(UserImage userImage) {
		
		List<UserImage> userImageList = new ArrayList<UserImage>();
		StringBuffer sql = new StringBuffer();
		
		if (userImage.getId() != null) {
			sql.append("select * from QM_USER_IMAGE where ID=").append(userImage.getId());
			userImageList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
			
			for (UserImage image:userImageList) {
				
				String tmp_sql = "select TYPEID from QM_USER_IMAGE where ID=" + image.getId();
				Long type_id = getJdbcTemplate().queryForLong(tmp_sql);
				tmp_sql = "select id,name from exam_prop_val where ID=" + type_id+" and type=24";
				
				List<ModelType> modelTypeList = getJdbcTemplate().query(tmp_sql, ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));

				image.setType(modelTypeList.get(0));
//Display information of GeneralUserImage and SpecialUserImage where to update By IE
				String prop_sql = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1 left join SUB_SYS_PROP_VAL t2 ON t1.prop_id=t2.id left join EXAM_PROP_VAL t3 on t2.prop_val_id=t3.id  where t1.userimage_id=" + image.getId();
				List<PropUnit> departmentPropList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				image.setDepartmentPropList(departmentPropList);	
				
				String prop_sql1 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1 left join EXAM_PROP_VAL t3 on t1.prop_id=t3.id where t1.userimage_id=" + image.getId();
				//Display General Userimage sql statement
				String prop_sql2 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_GENERAL_PROP t1 left join EXAM_PROP_VAL t3 on t1.prop_id=t3.id where t1.image_id=" + image.getId();
				//Display Sepcial Userimage sql statement
				String prop_sql3 = "select t1.prop_id as id, t3.major as name from QM_USER_IMAGE_SPECIAL_PROP t1 left join EXAM_MAJOR_ORDER t3 on t1.prop_id=t3.id where t1.image_id=" + image.getId();
				String prop_sql5 = "select t1.prop_id as id, t1.prop_id as name from QM_USER_IMAGE_SPECIAL_PROP t1 where t1.image_id=" + image.getId();
				String prop_sql3_duty = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_SPECIAL_PROP t1 left join EXAM_PROP_VAL t3 on t1.prop_id=t3.id where t1.image_id=" + image.getId();
			
				//Start area of Display GeneralUserImage
			
				
				GeneralUserImage generalUserImage = new GeneralUserImage();
				
				//Display HospitalPropList
				String prop_sql_hospital =prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
				List<PropUnit> hospitalPropList = getJdbcTemplate().query(prop_sql_hospital,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				generalUserImage.setHospitalPropList(hospitalPropList);
			
				
				//Display AreaPropList
				String prop_sql_area = "select prop_id as id,t3.prop_type_name as name from qm_user_image_general_prop t1,exam_prop_type t3 where t1.prop_id=t3.prop_type and t1.image_id="+image.getId()+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
				
				List<PropUnit> areaPropList = getJdbcTemplate().query(prop_sql_area,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
				generalUserImage.setAreaPropList(areaPropList);
				
				
				//Display general DutyPropList
				String prop_sql_duty_general = prop_sql2 +" and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
				
				List<PropUnit> dutyPropList_general = getJdbcTemplate().query(prop_sql_duty_general,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
				generalUserImage.setDutyPropList(dutyPropList_general);
				image.setGeneralUserImage(generalUserImage);
				
				
				
				
			// End of Display GeneralUserImage
				
			//Start of Display SpecialUserImage
				
				SpecialUserImage specialUserImage = new SpecialUserImage();
				
				//Didplay majorPropList(专业级别)
				String prop_sql_majorLevel = prop_sql5 + " and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_MAJORLevel_TYPE;
				List<PropUnit> majorLevelPropList = getJdbcTemplate().query(prop_sql_majorLevel,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				specialUserImage.setMajorLevelPropList(majorLevelPropList);
				
				//Display majorPropList
				
				String prop_sql_major = prop_sql3 + " and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
				
				List<PropUnit> majorPropList = getJdbcTemplate().query(prop_sql_major,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				specialUserImage.setMajorPropList(majorPropList);
			
				
				//Display dutyPropList
				String prop_sql_duty_special= prop_sql3_duty+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
				List<PropUnit> dutyPropList_special = getJdbcTemplate().query(prop_sql_duty_special,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				specialUserImage.setDutyPropList(dutyPropList_special);
				image.setSpecialUserImage(specialUserImage);
				
			}
		} else if (userImage.getType() == null) {
		
			
			sql.append("select id,name from exam_prop_val where id>0 and type=24");
			List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
			for (ModelType modelType:modelTypeList) {
				UserImage model = new UserImage();
				modelType.setName(modelType.getName() + "人物类型");
				model.setType(modelType);
				userImageList.add(model);
			}
		} else {
			if (userImage.getType().getId() == null) {
				
				sql.append("select id,name  from exam_prop_val where id>0 and type=24 ");
				
				if(!StringUtil.checkNull(userImage.getType().getName())){
					sql.append(" and CONCAT(name, '人物类型') like '%").append(userImage.getType().getName()).append("%'");
				}
				List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
				
				for (ModelType modelType:modelTypeList) {
					UserImage model = new UserImage();
					modelType.setName(modelType.getName() + "人物类型");
					model.setType(modelType);				
					//if(modelType.getName().contains(userImage.getType().getName()))
					{
						userImageList.add(model);
					}
				}
			} else {
				
				sql.append("select DISTINCT t.* from QM_USER_IMAGE t left join QM_USER_IMAGE_PROP t1 on t.ID = t1.USERIMAGE_ID left join QM_USER_IMAGE_GENERAL_PROP t2 on t.ID=t2.IMAGE_ID left join QM_USER_IMAGE_SPECIAL_PROP t3 on t.ID=t3.IMAGE_ID where t.id>0 ");
				
				sql.append(" and t.TYPEID=").append(userImage.getType().getId());
			//Searching By UserImageName area
				if(!StringUtil.checkNull(userImage.getName())){
					sql.append(" and t.name like '%").append(userImage.getName()).append("%'");
				}
			//Searching By Department
				if(userImage.getDepartmentPropList()!=null && userImage.getDepartmentPropList().size()>0)
				{
					sql.append(" and t1.PROP_ID in (");
					for (int i=0; i<userImage.getDepartmentPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getDepartmentPropList().get(i).getId());
					}
					sql.append(")");
				}
				if (userImage.getGeneralUserImage() != null && userImage.getGeneralUserImage().getDutyPropList()!=null && userImage.getGeneralUserImage().getDutyPropList().size()>0)
				{
					sql.append(" and t2.PROP_ID in (");
					for (int i=0; i<userImage.getGeneralUserImage().getDutyPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getGeneralUserImage().getDutyPropList().get(i).getId());
					}
					sql.append(")");					
				}
				if (userImage.getSpecialUserImage() != null && userImage.getSpecialUserImage().getDutyPropList()!=null && userImage.getSpecialUserImage().getDutyPropList().size()>0) {
					if (userImage.getGeneralUserImage() != null && userImage.getGeneralUserImage().getDutyPropList()!=null && userImage.getGeneralUserImage().getDutyPropList().size()>0)
						sql.append("or t3.PROP_ID in(");
					else
						sql.append("and t3.PROP_ID in(");
					for (int i=0; i<userImage.getSpecialUserImage().getDutyPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getSpecialUserImage().getDutyPropList().get(i).getId());
					}
					sql.append(")");	
				}
				sql.append(" order by id desc");
				userImageList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				
				for (UserImage image:userImageList) {
					
					String type_sql = "select id,name from exam_prop_val where ID=" + userImage.getType().getId();
					List<ModelType> mTypeList = getJdbcTemplate().query(type_sql, ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
					
					image.setType(mTypeList.get(0));
					
					String prop_sql1 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.userimage_id=" + image.getId();
					//Display General Userimage sql statement
					String prop_sql2 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_GENERAL_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.image_id=" + image.getId();
					//Display Sepcial Userimage sql statement
					String prop_sql3 = "select t1.prop_id as id, t3.major as name from QM_USER_IMAGE_SPECIAL_PROP t1, EXAM_MAJOR_ORDER t3 where t1.prop_id=t3.id and t1.image_id=" + image.getId();
				
					//Start area of Display GeneralUserImage
				
					List<PropUnit> departmentPropList = getJdbcTemplate().query(prop_sql1, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					image.setDepartmentPropList(departmentPropList);
					GeneralUserImage generalUserImage = new GeneralUserImage();
					
					//Display HospitalPropList
					String prop_sql_hospital =prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
					List<PropUnit> hospitalPropList = getJdbcTemplate().query(prop_sql_hospital,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					generalUserImage.setHospitalPropList(hospitalPropList);
				
					
					//Display AreaPropList
					//By IE
					String prop_sql_area = "select prop_id as id,t3.prop_type_name as name from qm_user_image_general_prop t1,exam_prop_type t3 where t1.prop_id=t3.prop_type and t1.image_id="+image.getId()+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
					
					List<PropUnit> areaPropList = getJdbcTemplate().query(prop_sql_area,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
					generalUserImage.setAreaPropList(areaPropList);
					
					
					//Display general DutyPropList
					String prop_sql_duty_general = prop_sql2 +" and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
					
					List<PropUnit> dutyPropList_general = getJdbcTemplate().query(prop_sql_duty_general,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
					generalUserImage.setDutyPropList(dutyPropList_general);
					image.setGeneralUserImage(generalUserImage);
					
					
					
					
				// End of Display GeneralUserImage
					
				//Start of Display SpecialUserImage
					
					SpecialUserImage specialUserImage = new SpecialUserImage();
					
					
					//Display majorPropList
					
					String prop_sql_major = prop_sql3 + " and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
					
					List<PropUnit> majorPropList = getJdbcTemplate().query(prop_sql_major,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					specialUserImage.setMajorPropList(majorPropList);
				
					
					//Display dutyPropList
					String prop_sql_duty_special= prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
					List<PropUnit> dutyPropList_special = getJdbcTemplate().query(prop_sql_duty_special,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					specialUserImage.setDutyPropList(dutyPropList_special);
					image.setSpecialUserImage(specialUserImage);
					
					
				}
			}
		}
			
		return userImageList;
	}

	@Override
	public boolean addUserImage(UserImage userImage) {
		
		Long id = userImage.getId();
		if (id == null) id = this.getNextId("QM_USER_IMAGE");
		List<Object> list = new ArrayList<Object>();
		userImage.setId(id);
		String add_name_userImage = userImage.getName();
		ModelType modelType = userImage.getType();
		GeneralUserImage generalUserImage = userImage.getGeneralUserImage();
		SpecialUserImage specialUserImage = userImage.getSpecialUserImage();
		List<PropUnit> departmentList = userImage.getDepartmentPropList();
				
		String sql = "";
		if (modelType != null) {
			sql = "INSERT INTO QM_USER_IMAGE (ID,TYPEID,NAME) VALUES (?, ?, ?)";
			getSimpleJdbcTemplate().update(sql, id, modelType.getId(), add_name_userImage);
		} else {
			sql = "INSERT INTO QM_USER_IMAGE (ID,NAME) VALUES (?, ?)";
			getSimpleJdbcTemplate().update(sql, id, add_name_userImage);
		}
		
		if(departmentList != null && departmentList.size() > 0)
			for(int i=0;i<departmentList.size();i++) {
				sql = "insert into QM_USER_IMAGE_PROP (USERIMAGE_ID, PROP_ID) values (?, ?)";
				getSimpleJdbcTemplate().update(sql, id, departmentList.get(i).getId());
			}
		
		if (generalUserImage != null) {
			
			List<PropUnit> areaPropList = generalUserImage.getAreaPropList();
			List<PropUnit> hospitalPropList = generalUserImage.getHospitalPropList();
			List<PropUnit> dutyPropList =  generalUserImage.getDutyPropList();
			
			if(areaPropList!=null && areaPropList.size()>0)
			{
				for(int i=0;i<areaPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, areaPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE);
				}
			}
			
			if(hospitalPropList!=null && hospitalPropList.size()>0)
			{
				for(int i=0;i<hospitalPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, hospitalPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE);
				}
			}
			
			if(dutyPropList!=null && dutyPropList.size()>0)
			{
				for(int i=0;i<dutyPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, dutyPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
				}
			}
		}
		
		if (specialUserImage != null) {

			List<PropUnit> majorLevelPropList = specialUserImage.getMajorLevelPropList();
			List<PropUnit> majorPropList = specialUserImage.getMajorPropList();			
			List<PropUnit> dutyPropList =  specialUserImage.getDutyPropList();

			if(majorLevelPropList!=null && majorLevelPropList.size()>0)
			{
				for(int i=0;i<majorLevelPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_SPECIAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, majorLevelPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_MAJORLevel_TYPE);
				}
			}
			
			if(majorPropList!=null && majorPropList.size()>0)
			{
				for(int i=0;i<majorPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_SPECIAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, majorPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_MAJOR_TYPE);
				}
			}
			
			if(dutyPropList!=null && dutyPropList.size()>0)
			{
				for(int i=0;i<dutyPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_SPECIAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, dutyPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
				}
			}
		}

		
		return true;
	}

	@Override
	public boolean updateUserImage(UserImage userImage) {

		List<Object> list = new ArrayList<Object>();
		if(userImage.getName()!=null && !userImage.getName().equals("")){
			list.add(userImage.getName());
	 		String userImageName = "update QM_USER_IMAGE set name = ? where id="+userImage.getId();
			getSimpleJdbcTemplate().update(userImageName,list.toArray());
		}
		List<PropUnit> departmentList = userImage.getDepartmentPropList();
		String sql_update_userImageprop = null;
		if(departmentList != null && departmentList.size() >0){
			String sql_delete_depart = "delete from qm_user_image_prop where userimage_id="+userImage.getId();
			getSimpleJdbcTemplate().update(sql_delete_depart);
			for(int i=0;i<departmentList.size();i++){
				sql_update_userImageprop = "insert into QM_USER_IMAGE_PROP (userimage_id,prop_id) values(?,?)";
				getSimpleJdbcTemplate().update(sql_update_userImageprop,userImage.getId(),userImage.getDepartmentPropList().get(i).getId());
			}
			
		}
	
		if(userImage.getGeneralUserImage() != null )
		{
			List<PropUnit> hospital = userImage.getGeneralUserImage().getHospitalPropList();
			String sql_Compare_hospital = "delete from qm_user_image_general_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
			getJdbcTemplate().update(sql_Compare_hospital);
			if(hospital != null && hospital.size()>0){
				for(int i=0;i<hospital.size();i++){
					String sql_add = "INSERT INTO QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID,PROP_ID,TYPE) VALUES(?,?,?)";
					getSimpleJdbcTemplate().update(sql_add,userImage.getId(),hospital.get(i).getId(),com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE );
				}
			}
			List<PropUnit> duty = userImage.getGeneralUserImage().getDutyPropList();
			String sql_compare_duty = "delete from qm_user_image_general_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
			getJdbcTemplate().update(sql_compare_duty);
			if(duty != null && duty.size()>0){
				for(int i=0;i<duty.size();i++){
					String sql_add = "insert into qm_user_image_general_prop (image_id,prop_id,type) values (?,?,?)";
					getSimpleJdbcTemplate().update(sql_add,userImage.getId(),duty.get(i).getId(),com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
				}
			}
			
			List<PropUnit> area = userImage.getGeneralUserImage().getAreaPropList();
			String sql_compare_area = "delete from qm_user_image_general_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
			getJdbcTemplate().update(sql_compare_area);
			if(area != null && area.size()>0){
			{
				for(int i=0;i<area.size();i++){
					String sql_add = "insert into qm_user_image_general_prop (image_id,prop_id,type) values (?,?,?)";
					getSimpleJdbcTemplate().update(sql_add,userImage.getId(),area.get(i).getId(),com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE);
				}		
			}
			
		}
		
		if(userImage.getSpecialUserImage() != null)
		{
			String sql_compare_majorLevel = "delete from qm_user_image_special_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_MAJORLevel_TYPE;
			getJdbcTemplate().update(sql_compare_majorLevel);
			List<PropUnit> majorLevel = userImage.getSpecialUserImage().getMajorLevelPropList();
			if(majorLevel != null && majorLevel.size()>0){
				for(int i=0;i<majorLevel.size();i++){
					String sql_add = "insert into qm_user_image_special_prop (image_id,prop_id,type) values (?,?,?)";
					getSimpleJdbcTemplate().update(sql_add, userImage.getId(),majorLevel.get(i).getId(),com.hys.exam.constants.Constants.PROP_MAJORLevel_TYPE);
				}
			}
			
			String sql_compare_major = "delete from qm_user_image_special_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
			getJdbcTemplate().update(sql_compare_major);
			List<PropUnit> major = userImage.getSpecialUserImage().getMajorPropList();
			if(major != null && major.size()>0){
				for(int i=0;i<major.size();i++){
					String sql_add = "insert into qm_user_image_special_prop (image_id,prop_id,type) values (?,?,?)";
					getSimpleJdbcTemplate().update(sql_add, userImage.getId(),major.get(i).getId(),com.hys.exam.constants.Constants.PROP_MAJOR_TYPE);
				}
			}
			
			sql_compare_duty =  "delete from qm_user_image_special_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
			getJdbcTemplate().update(sql_compare_duty);
			List<PropUnit> spe_duty = userImage.getSpecialUserImage().getDutyPropList();
			
			if(spe_duty != null && spe_duty.size()>0){
				for(int i=0;i<spe_duty.size();i++){
					String sql_add = "insert into qm_user_image_special_prop (image_id,prop_id,type) values(?,?,?)";
					getSimpleJdbcTemplate().update(sql_add, userImage.getId(),spe_duty.get(i).getId(),com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
				}
			}
		}
	}
		return true;

	}

	@Override
	public boolean deleteUserImage(UserImage userImage) {
		
		String sql = "delete from QM_USER_IMAGE_PROP where USERIMAGE_ID=" + +userImage.getId();
		try{
		  getJdbcTemplate().update(sql);
		}catch(Exception e){;}
		sql = "delete from QM_USER_IMAGE_GENERAL_PROP where IMAGE_ID=" + +userImage.getId();
		getJdbcTemplate().update(sql);
		
		sql = "delete from QM_USER_IMAGE_SPECIAL_PROP where IMAGE_ID=" + +userImage.getId();
		getJdbcTemplate().update(sql);
		
		sql = "delete from QM_USER_IMAGE where id="+userImage.getId();
		getJdbcTemplate().update(sql);
		
		return true;
	}

	@Override
	public List<PropUnit> getHospitalList() {
		String sql= "select t.*, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.type = 23 and t.name like '%' order by t.code";
		List<ExamProp> hospital = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
		List<PropUnit> hospitalList = new ArrayList<PropUnit>();
		
		/*for (ExamProp hos:hospital){
		v	hosProp.setId(hos.getId());
			hosProp.setName(hos.getName());
			hospitalList.add(hosProp);
		}*/
		for( int i = 0; i<hospital.size();i++){
			PropUnit hosProp = new PropUnit();
			hosProp.setId(hospital.get(i).getId());
			hosProp.setName(hospital.get(i).getName());
			hospitalList.add(hosProp);
		}
		return hospitalList;
	}

	@Override
	public List<PropUnit> getAreaList() {
		String sql = "select prop_type as id,prop_type_name as name from EXAM_PROP_TYPE WHERE PROP_TYPE >2 AND PROP_TYPE<6";
		List<PropUnit> areaList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return areaList;
	}

	@Override
	public List<PropUnit> getDutyList() {
		//String sql = "select t.*, cast(t.code as signed) as codenum, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.type = 9 and t.name like '%'";
		String sql = "select * from exam_prop_val where type=9";
		List<PropUnit> dutyList = new ArrayList<PropUnit>();
		List<ExamProp> duty = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
		for( int i = 0;i<duty.size();i++){
			PropUnit prop = new PropUnit();
			prop.setId(duty.get(i).getId());
			prop.setName(duty.get(i).getName());
			dutyList.add(prop);
		}
		return dutyList;
	}
	public List<PropUnit> getDutyList(Long TypeID) {
		List<Object> list = new ArrayList<Object>();
		String sql_select_code = "select id, code as name  from exam_prop_val where id="+TypeID;
		List<PropUnit> dutyList = new ArrayList<PropUnit>();
		List<PropUnit> code = new ArrayList<PropUnit>();
		code = getJdbcTemplate().query(sql_select_code,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		list.add(code.get(0).getName());
		String sql = "select * from exam_prop_val where ext_type=? and type=9";
		List<ExamProp> duty = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),list.toArray());
		for( int i = 0;i<duty.size();i++){
			PropUnit prop = new PropUnit();
			prop.setId(duty.get(i).getId());
			prop.setName(duty.get(i).getName());
			dutyList.add(prop);
		}
		return dutyList;
	}
	@Override
	public List<PropUnit> getMajorList() {
		
		String sql = "select id,major as name from EXAM_MAJOR_ORDER";
		List<PropUnit> majorList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return majorList;
	}

	@Override
	public List<UserImage> getUserImageListByXueke(UserImage userImage) {
		String sql = new String();
		Long typeid = userImage.getType().getId();
		PropUnit m = new PropUnit();
		
		sql = "select distinct t.* from qm_user_image t, qm_user_image_prop tp where t.id = tp.USERIMAGE_ID";
				
		if(typeid != null && typeid > 1) {
			sql += " and t.typeid="+ typeid;
		}
		sql += " and tp.prop_id in (";
		for(int i=0; i<userImage.getDepartmentPropList().size(); i++){	
			if(i != (userImage.getDepartmentPropList().size()-1)){
				sql += userImage.getDepartmentPropList().get(i).getId()+",";
			}else{
				sql += userImage.getDepartmentPropList().get(i).getId();
			}
			
		}
		sql += ")";
		List<UserImage> xuekel = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
		return xuekel;
	}

	@Override
	public boolean compareName(UserImage userImage) {
		 
		int count=0;
		List<Long> imageIdListFromPropId = new ArrayList<Long>();
		List<Long> imageIdListFromGeneralInfo = new ArrayList<Long>();
		String compareSql="SELECT distinct(t0.USERIMAGE_ID) from ";
		String whereSql = " where ";
		String propSql = "";
		for(int i=0; i<userImage.getDepartmentPropList().size(); i++){
			if(i != 0)
			{
				if(userImage.getId() != null && !userImage.getId().equals(0L))
				{
					propSql += ",(select p.USERIMAGE_ID as USERIMAGE_ID from qm_user_image_prop p left join qm_user_image i on p.USERIMAGE_ID = i.ID where i.TYPEID=" + userImage.getType().getId() + " and i.id != " + userImage.getId()+ " and p.PROP_ID=" + userImage.getDepartmentPropList().get(i).getId() +") t" + i;	
				}
				else
				{
					propSql += ",(select p.USERIMAGE_ID as USERIMAGE_ID from qm_user_image_prop p left join qm_user_image i on p.USERIMAGE_ID = i.ID where i.TYPEID=" + userImage.getType().getId() + " and p.PROP_ID=" + userImage.getDepartmentPropList().get(i).getId() +") t" + i;
				}
				whereSql += " and t"+(i-1) +".USERIMAGE_ID=t"+ i +".USERIMAGE_ID"; 
			}
			else
			{
				if(userImage.getId() != null && !userImage.getId().equals(0L))
				{
						propSql += "(select p.USERIMAGE_ID USERIMAGE_ID from qm_user_image_prop p left join qm_user_image i on p.userimage_id = i.id  where i.TYPEID=" + userImage.getType().getId()+ " and i.id != " + userImage.getId() + " and p.PROP_ID=" + userImage.getDepartmentPropList().get(i).getId() +") t" + i;	
				}
				else
				{
					propSql += "(select p.USERIMAGE_ID USERIMAGE_ID from qm_user_image_prop p left join qm_user_image i on p.userimage_id = i.id  where i.TYPEID=" + userImage.getType().getId()+ " and p.PROP_ID=" + userImage.getDepartmentPropList().get(i).getId() +") t" + i;
				}
				
				whereSql += "t0.userimage_id>0";
			}
		}
		compareSql += propSql + whereSql;
		imageIdListFromPropId = getJdbcTemplate().queryForList(compareSql, Long.class);

		for(Long imageId : imageIdListFromPropId)
		{
			compareSql="SELECT count(t0.image_id) from ";
			whereSql = " where ";
			propSql = "";
			for(int i = 0; i < userImage.getGeneralUserImage().getDutyPropList().size(); i++){
				String objSql  = "select distinct f0.IMAGE_ID from (select IMAGE_ID from qm_user_image_general_prop where PROP_ID=" + userImage.getGeneralUserImage().getDutyPropList().get(i).getId() + " and type =" + Constants.PROP_DUTY_TYPE +	") f0,";
				objSql += "(select IMAGE_ID from qm_user_image_general_prop where PROP_ID=" + userImage.getGeneralUserImage().getAreaPropList().get(i).getId() + " and type =" + Constants.PROP_LOCALAREA_TYPE +	") f1,";
				objSql += "(select IMAGE_ID from qm_user_image_general_prop where PROP_ID=" + userImage.getGeneralUserImage().getHospitalPropList().get(i).getId() + " and type =" + Constants.PROP_HOSPITAL_TYPE +	") f2";
				objSql += " where f0.image_id = f1.image_id and f1.image_id = f2.image_id and f0.image_id=" + imageId;
				if(i != 0)
				{
					propSql += ",(" + objSql + ") t" + i;
					whereSql += " and t"+(i-1) +".IMAGE_ID=t"+ i +".IMAGE_ID"; 
				}
				else
				{
					propSql += "(" + objSql + ") t" + i;
					whereSql += "t0.image_id =" +imageId;
				}
			}
			compareSql += propSql + whereSql;
			int resultCount = getJdbcTemplate().queryForInt(compareSql);
			String testSizeSql = "select count(image_id) from qm_user_image_general_prop where type = " + Constants.PROP_DUTY_TYPE + " and image_id = " + imageId;
			int size = getJdbcTemplate().queryForInt(testSizeSql);
			if(size == userImage.getGeneralUserImage().getDutyPropList().size() && resultCount > 0)
			{
				count += resultCount;
			}
		}
		

		if(count >0 )
			return false;
		return true;
	}
	@Override
	public List<PropUnit> getAreaList( List<PropUnit> tempList) {
		List<PropUnit> list = new ArrayList<PropUnit>();
		for(int i=0;i<tempList.size();i++){
			List<PropUnit> temp = new ArrayList<PropUnit>();
			String sql = "select prop_type as id,prop_type_name as name from EXAM_PROP_TYPE WHERE PROP_TYPE ="+tempList.get(i).getId();
			temp = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			list.add(temp.get(0));
		}
		
		
		return list;
	}

	@Override
	public List<PropUnit> getDutyList( List<PropUnit> tempList) {
		List<PropUnit> list = new ArrayList<PropUnit>();
		for(int i=0;i<tempList.size();i++){
			List<ExamProp> temp = new ArrayList<ExamProp>();
			String sql = "select * from exam_prop_val where type=9 and id="+tempList.get(i).getId();
			temp = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
			if(temp.size()>0){
				PropUnit duty = new PropUnit();
				duty.setId(temp.get(0).getId());
				duty.setName(temp.get(0).getName());
				list.add(duty);
			}
			
		}
		return list;
	}
	@Override
	public List<PropUnit> getMajorList( List<PropUnit> tempList) {
		List<PropUnit> list = new ArrayList<PropUnit>();
		for(int i=0;i<tempList.size();i++){
			List<PropUnit> temp = new ArrayList<PropUnit>();
			String sql = "select id,major as name from EXAM_MAJOR_ORDER where id="+tempList.get(i).getId();
			temp = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			if(temp != null && temp.size() > 0)
			{
				list.add(temp.get(0));
			}
		}
		return list;
	}

	@Override
	public List<PropUnit> getMajorLevelList( List<PropUnit> tempList) {
		List<PropUnit> list = new ArrayList<PropUnit>();
		for(int i=0;i<tempList.size();i++){
			List<PropUnit> temp = new ArrayList<PropUnit>();
			String sql = "select t1.prop_id as id, t1.prop_id as name from qm_user_image_special_prop t1 where t1.type=5 and t1.prop_id="+tempList.get(i).getId();
			temp = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			if(temp != null && temp.size() > 0)
			{
				list.add(temp.get(0));
			}
		}
		return list;
	}
	
	@Override
	public List<PropUnit> getHospitalList( List<PropUnit> tempList) {
		List<PropUnit> list = new ArrayList<PropUnit>();
		for(int i=0;i<tempList.size();i++){
			List<ExamProp> temp = new ArrayList<ExamProp>();
			String sql = "select * from exam_prop_val where type=23 and id="+tempList.get(i).getId();
			temp = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
			PropUnit hospital = new PropUnit();
			hospital.setId(temp.get(0).getId());
			hospital.setName(temp.get(0).getName());
			list.add(hospital);
			
		}
		return list;
	}

	@Override
	public void getUserImagePageList(PageList pl, UserImage userImage) {
		List<UserImage> userImageList = new ArrayList<UserImage>();
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		
		if (userImage.getId() != null) {
			sql.append("select * from QM_USER_IMAGE where ID=").append(userImage.getId());
			if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
				sql.append(" order by ").append(pl.getSortCriterion());
				
				if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
					sql.append(" desc");
			}
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			userImageList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
			
			
			for (UserImage image:userImageList) {
				
				String tmp_sql = "select TYPEID from QM_USER_IMAGE where ID=" + image.getId();
				Long type_id = getJdbcTemplate().queryForLong(tmp_sql);
				tmp_sql = "select id,name from exam_prop_val where ID=" + type_id+" and type=24";
				
				List<ModelType> modelTypeList = getJdbcTemplate().query(tmp_sql, ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));

				image.setType(modelTypeList.get(0));
//Display information of GeneralUserImage and SpecialUserImage where to update By IE
				String prop_sql = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1 left join EXAM_PROP_VAL t3 on t1.prop_id=t3.id where t1.userimage_id=" + image.getId();
				List<PropUnit> departmentPropList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				image.setDepartmentPropList(departmentPropList);	
				
				String prop_sql1 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1 left join EXAM_PROP_VAL t3 on t1.prop_id=t3.id where t1.userimage_id=" + image.getId();
				//Display General Userimage sql statement
				String prop_sql2 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_GENERAL_PROP t1 left join EXAM_PROP_VAL t3 on t1.prop_id=t3.id where t1.image_id=" + image.getId();
				//Display Sepcial Userimage sql statement
				String prop_sql3 = "select t1.prop_id as id, t3.major as name from QM_USER_IMAGE_SPECIAL_PROP t1 left join EXAM_MAJOR_ORDER t3 on t1.prop_id=t3.id where t1.image_id=" + image.getId();
				String prop_sql3_duty = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_SPECIAL_PROP t1 left join EXAM_PROP_VAL t3 on t1.prop_id=t3.id where t1.image_id=" + image.getId();
			
				//Start area of Display GeneralUserImage
			
				
				GeneralUserImage generalUserImage = new GeneralUserImage();
				
				//Display HospitalPropList
				String prop_sql_hospital =prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
				List<PropUnit> hospitalPropList = getJdbcTemplate().query(prop_sql_hospital,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				generalUserImage.setHospitalPropList(hospitalPropList);
			
				
				//Display AreaPropList
				String prop_sql_area = "select prop_id as id,t3.prop_type_name as name from qm_user_image_general_prop t1,exam_prop_type t3 where t1.prop_id=t3.prop_type and t1.image_id="+image.getId()+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
				
				List<PropUnit> areaPropList = getJdbcTemplate().query(prop_sql_area,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
				generalUserImage.setAreaPropList(areaPropList);
				
				
				//Display general DutyPropList
				String prop_sql_duty_general = prop_sql2 +" and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
				
				List<PropUnit> dutyPropList_general = getJdbcTemplate().query(prop_sql_duty_general,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
				generalUserImage.setDutyPropList(dutyPropList_general);
				image.setGeneralUserImage(generalUserImage);
				
				
				
				
			// End of Display GeneralUserImage
				
			//Start of Display SpecialUserImage
				
				SpecialUserImage specialUserImage = new SpecialUserImage();
				
				
				//Display majorPropList
				
				String prop_sql_major = prop_sql3 + " and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
				
				List<PropUnit> majorPropList = getJdbcTemplate().query(prop_sql_major,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				specialUserImage.setMajorPropList(majorPropList);
			
				
				//Display dutyPropList
				String prop_sql_duty_special= prop_sql3_duty+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
				List<PropUnit> dutyPropList_special = getJdbcTemplate().query(prop_sql_duty_special,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				specialUserImage.setDutyPropList(dutyPropList_special);
				image.setSpecialUserImage(specialUserImage);
				
			}
			pl.setList(userImageList);
		} else if (userImage.getType() == null) {
		
			
			sql.append("select id,name from exam_prop_val where id>0 and type=24");
			if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
				sql.append(" order by ").append(pl.getSortCriterion());
				
				if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
					sql.append(" desc");
			}
			
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			List<ModelType> modelTypeList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
			for (ModelType modelType:modelTypeList) {
				UserImage model = new UserImage();
				modelType.setName(modelType.getName() + "人物类型");
				model.setType(modelType);
				userImageList.add(model);
			}
			pl.setList(userImageList);
		} else {
			if (userImage.getType().getId() == null) {
				
				sql.append("select id,name  from exam_prop_val where id>0 and type=24 ");
				
				if(!StringUtil.checkNull(userImage.getType().getName())){
					sql.append(" and CONCAT(name, '人物类型') like '%").append(userImage.getType().getName()).append("%'");
				}
				if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
					sql.append(" order by ").append(pl.getSortCriterion());
					
					if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
						sql.append(" desc");
				}
				
				Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
				pl.setFullListSize(fullListSize);
				List<ModelType> modelTypeList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
				
				
				for (ModelType modelType:modelTypeList) {
					UserImage model = new UserImage();
					modelType.setName(modelType.getName() + "人物类型");
					model.setType(modelType);				
					//if(modelType.getName().contains(userImage.getType().getName()))
					{
						userImageList.add(model);
					}
				}
				pl.setList(userImageList);
			} else {
				
				sql.append("select DISTINCT t.* from QM_USER_IMAGE t left join QM_USER_IMAGE_PROP t1 on t.ID = t1.USERIMAGE_ID left join QM_USER_IMAGE_GENERAL_PROP t2 on t.ID=t2.IMAGE_ID left join QM_USER_IMAGE_SPECIAL_PROP t3 on t.ID=t3.IMAGE_ID where t.id>0 ");
				
				sql.append(" and t.TYPEID=").append(userImage.getType().getId());
			//Searching By UserImageName area
				if(!StringUtil.checkNull(userImage.getName())){					
					sql.append(" and t.name like ?");
					
					list.add("%"+userImage.getName().trim()+"%" );
				}
			//Searching By Department
				if(userImage.getDepartmentPropList()!=null && userImage.getDepartmentPropList().size()>0)
				{
					sql.append(" and t1.PROP_ID in (");
					for (int i=0; i<userImage.getDepartmentPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getDepartmentPropList().get(i).getId());
					}
					sql.append(")");
				}
				if (userImage.getGeneralUserImage() != null && userImage.getGeneralUserImage().getDutyPropList()!=null && userImage.getGeneralUserImage().getDutyPropList().size()>0)
				{
					sql.append(" and t2.PROP_ID in (");
					for (int i=0; i<userImage.getGeneralUserImage().getDutyPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getGeneralUserImage().getDutyPropList().get(i).getId());
					}
					sql.append(")");					
				}
				if (userImage.getSpecialUserImage() != null && userImage.getSpecialUserImage().getDutyPropList()!=null && userImage.getSpecialUserImage().getDutyPropList().size()>0) {
					if (userImage.getGeneralUserImage() != null && userImage.getGeneralUserImage().getDutyPropList()!=null && userImage.getGeneralUserImage().getDutyPropList().size()>0)
						sql.append("or t3.PROP_ID in(");
					else
						sql.append("and t3.PROP_ID in(");
					for (int i=0; i<userImage.getSpecialUserImage().getDutyPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getSpecialUserImage().getDutyPropList().get(i).getId());
					}
					sql.append(")");	
				}
				if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
					sql.append(" order by ").append(pl.getSortCriterion());
					
					if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
						sql.append(" desc");
				}
				Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()),list.toArray());
				pl.setFullListSize(fullListSize);
				userImageList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()),list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				
				if(userImageList.size() == 0){
					
					UserImage image = new UserImage();
					
					String type_sql = "select id,name from exam_prop_val where ID=" + userImage.getType().getId();
					List<ModelType> mTypeList = getJdbcTemplate().query(type_sql, ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
					
					image.setType(mTypeList.get(0));
					userImageList.add(image);
				}
				for (UserImage image:userImageList) {
					
					String type_sql = "select id,name from exam_prop_val where ID=" + userImage.getType().getId();
					List<ModelType> mTypeList = getJdbcTemplate().query(type_sql, ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
					
					image.setType(mTypeList.get(0));
					
					String prop_sql1 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1, sub_sys_prop_val t2, EXAM_PROP_VAL t3 where t1.PROP_ID = t2.ID AND t2.PROP_VAL_ID = t3.ID and t1.userimage_id=" + image.getId();
					//Display General Userimage sql statement
					String prop_sql2 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_GENERAL_PROP t1, sub_sys_prop_val t2, EXAM_PROP_VAL t3 where t1.PROP_ID = t2.ID AND t2.PROP_VAL_ID = t3.ID and t1.image_id=" + image.getId();
					//Display Sepcial Userimage sql statement
					String prop_sql3 = "select t1.prop_id as id, t3.major as name from QM_USER_IMAGE_SPECIAL_PROP t1, EXAM_MAJOR_ORDER t3 where t1.prop_id=t3.id and t1.image_id=" + image.getId();
				
					//Start area of Display GeneralUserImage
				
					List<PropUnit> departmentPropList = getJdbcTemplate().query(prop_sql1, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					image.setDepartmentPropList(departmentPropList);
					GeneralUserImage generalUserImage = new GeneralUserImage();
					
					//Display HospitalPropList
					String prop_sql_hospital =prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
					List<PropUnit> hospitalPropList = getJdbcTemplate().query(prop_sql_hospital,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					generalUserImage.setHospitalPropList(hospitalPropList);
				
					
					//Display AreaPropList
					//By IE
					String prop_sql_area = "select prop_id as id,t3.prop_type_name as name from qm_user_image_general_prop t1,exam_prop_type t3 where t1.prop_id=t3.prop_type and t1.image_id="+image.getId()+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
					
					List<PropUnit> areaPropList = getJdbcTemplate().query(prop_sql_area,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
					generalUserImage.setAreaPropList(areaPropList);
					
					
					//Display general DutyPropList
					String prop_sql_duty_general = "SELECT t1.prop_id AS id,t3. NAME FROM QM_USER_IMAGE_GENERAL_PROP t1 LEFT JOIN EXAM_PROP_VAL t3 ON t1.prop_id = t3.id "
							+ "WHERE t1.image_id = "+image.getId()+" and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
					
					List<PropUnit> dutyPropList_general = getJdbcTemplate().query(prop_sql_duty_general,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
					generalUserImage.setDutyPropList(dutyPropList_general);
					image.setGeneralUserImage(generalUserImage);
					
					
					
					
				// End of Display GeneralUserImage
					
				//Start of Display SpecialUserImage
					
					SpecialUserImage specialUserImage = new SpecialUserImage();
					
					
					//Display majorPropList
					
					String prop_sql_major = prop_sql3 + " and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
					
					List<PropUnit> majorPropList = getJdbcTemplate().query(prop_sql_major,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					specialUserImage.setMajorPropList(majorPropList);
				
					
					//Display dutyPropList
					String prop_sql_duty_special= prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
					List<PropUnit> dutyPropList_special = getJdbcTemplate().query(prop_sql_duty_special,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					specialUserImage.setDutyPropList(dutyPropList_special);
					image.setSpecialUserImage(specialUserImage);
					
					
				}
				pl.setList(userImageList);
			}
		}
	}
//2017/01/11, Add by IE
//For get Major level
	@Override
	public List<PropUnit> getMajorLvlList() {
		List<PropUnit> majorLvlList = new ArrayList<PropUnit>();
		String sql = "select distinct classid as id from exam_major_order where id>0";
		majorLvlList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return majorLvlList;
	}

	@Override
	public List<PropUnit> getMajorList(String level) {
		List<PropUnit> majorList = new ArrayList<PropUnit>();
		
		String sql = "select id,major as name from exam_major_order where classId="+level;
		majorList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return majorList;
	}
//End
	/**
	 * 根据人物画像和id查询人物画像
	 */
	@Override
	public List<UserImage> getUserImageListByTypeIdAndName(UserImage userImage) {
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
			sql.append("select * from QM_USER_IMAGE where TYPEID=? and name=?");
			list.add(userImage.getType().getId());
			list.add(userImage.getName());
			return  getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class),list.toArray());
			
	}
}
