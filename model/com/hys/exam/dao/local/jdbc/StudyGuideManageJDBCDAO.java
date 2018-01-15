package com.hys.exam.dao.local.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.StudyGuideManageDAO;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.model.MaterialProp;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

public class StudyGuideManageJDBCDAO extends BaseDao implements
		StudyGuideManageDAO {

	private UserImageManageJDBCDAO userImageManageJDBCDAO;
	
	private QualityModelManageJDBCDAO qualityModelManageJDBCDAO;
	
	public UserImageManageJDBCDAO getUserImageManageJDBCDAO() {
		return userImageManageJDBCDAO;
	}

	public void setUserImageManageJDBCDAO(
			UserImageManageJDBCDAO userImageManageJDBCDAO) {
		this.userImageManageJDBCDAO = userImageManageJDBCDAO;
	}
	
	public QualityModelManageJDBCDAO getQualityModelManageJDBCDAO() {
		return qualityModelManageJDBCDAO;
	}

	public void setQualityModelManageJDBCDAO(QualityModelManageJDBCDAO qualityModelManageJDBCDAO) {
		this.qualityModelManageJDBCDAO = qualityModelManageJDBCDAO;
	}
	
	@Override
	public List<StudyGuide> getStudyGuideList(StudyGuide queryGuide) {
		
		String sql = "";
		List<StudyGuide> guideList = new ArrayList<StudyGuide>();
		if(queryGuide.getId()!= null){
			sql = "select *  from QM_GUIDE where id= "+queryGuide.getId()+" order by id desc";

			 List<StudyGuide> tempList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			List<UserImage> userImageList = new ArrayList<UserImage>();
			for(StudyGuide guide:tempList){
				String sql_select_userImage = "select USERIMAGE_ID as ID from qm_guide_image_prop where GUIDE_ID=" + queryGuide.getId();
			
				userImageList = getJdbcTemplate().query(sql_select_userImage, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				for (int i=0; i<userImageList.size(); i++) {
				
				//try-catch() !!!
					UserImage image = userImageManageJDBCDAO.getUserImageList(userImageList.get(i)).get(0);
				
					userImageList.set(i, image);
					guide.setUserImageList(userImageList);
					guideList.add(guide);
				}
			
			}
		}
		 if (queryGuide.getLevel() != null && queryGuide.getLevel() == 1L) {
			//Start Search By Using 人物画像。。。。 Add IE
			if(queryGuide.getUserImage() != null ){
				
				sql = "select id from qm_user_image where name like '%"+ queryGuide.getUserImage().getName()+"%'";
				String sql1="";
				List<PropUnit> propList = new ArrayList<PropUnit>();
				List<PropUnit> prop = new ArrayList<PropUnit>();
				propList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				if(propList != null && propList.size()>0){
					sql1 = "select  guide_id as id from qm_guide_image_prop where userimage_id in (";
					for(int i=0;i<propList.size();i++){
						
						if(i>0) sql1 +=",";
						sql1 += propList.get(i).getId();
					}
					sql1 += ")";
					prop = getJdbcTemplate().query(sql1, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					for(int i=0;i<prop.size();i++){
						sql = "select  * from qm_guide where id="+prop.get(i).getId();
						List<StudyGuide> tmp_list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
					
						for (StudyGuide guide:tmp_list) {
					
							List<UserImage> userImageList = new ArrayList<UserImage>();
						
							String sql_select_userImage = "select USERIMAGE_ID as ID from qm_guide_image_prop where GUIDE_ID=" + guide.getId();
						
							userImageList = getJdbcTemplate().query(sql_select_userImage, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
							for (int j=0; j<userImageList.size(); j++) {
							
							//try-catch() !!!
								UserImage image = userImageManageJDBCDAO.getUserImageList(userImageList.get(j)).get(0);
								userImageList.set(j, image);
							}
						
							guide.setUserImageList(userImageList);
						/*UserImage queryImage = new UserImage();
						//queryImage.setId(image_id);
						if (guide.getUserImage() != null && !StringUtils.checkNull(guide.getUserImage().getName())) 
							queryImage.setName(guide.getUserImage().getName());
						
						List<UserImage> userImageList = userImageManageJDBCDAO.getUserImageList(queryImage);
						
						guide.setUserImage(userImageList.get(0));
						
						*/
						
							guideList.add(guide);
						}
					}
				} 
				//End of Search By Using 人物画像
			}else {
			sql = "select *  from QM_GUIDE where LEVEL= "+queryGuide.getLevel()+" order by id desc";

			List<StudyGuide> tmp_list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:tmp_list) {
			
				List<UserImage> userImageList = new ArrayList<UserImage>();
				
				String sql_select_userImage = "select USERIMAGE_ID as ID from qm_guide_image_prop where GUIDE_ID=" + guide.getId();
				
				userImageList = getJdbcTemplate().query(sql_select_userImage, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				for (int i=0; i<userImageList.size(); i++) {
					
					//try-catch() !!!
					UserImage image = userImageManageJDBCDAO.getUserImageList(userImageList.get(i)).get(0);
					userImageList.set(i, image);
				}
				
				guide.setUserImageList(userImageList);
				/*UserImage queryImage = new UserImage();
				//queryImage.setId(image_id);
				if (guide.getUserImage() != null && !StringUtils.checkNull(guide.getUserImage().getName())) 
					queryImage.setName(guide.getUserImage().getName());
				
				List<UserImage> userImageList = userImageManageJDBCDAO.getUserImageList(queryImage);
				
				guide.setUserImage(userImageList.get(0));
				
				*/
				
				guideList.add(guide);
			}
			
		}
		 }	else if (queryGuide.getLevel() != null && queryGuide.getLevel() == 4L) {
			
			sql = "select * from QM_GUIDE where PARENT_ID=";
			sql += queryGuide.getParent_id();
			sql += " and QUALITY_ID=";
			sql += queryGuide.getQuality().getId();
			if (queryGuide.getName() != null) sql += " and name like '%" + queryGuide.getName() + "%'";
			sql += " order by id desc";
			guideList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:guideList) {

				sql = "select ID, NAME from EXAM_ICD_PROP where ID in (select PROP_ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getId() + ")";
				List<PropUnit> icdPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				guide.setIcdPropList(icdPropList);
			}
			
			
		} else if (queryGuide.getLevel() != null && queryGuide.getLevel() == 5L) {
			
			if (queryGuide.getParent_id() == null)
				sql = "select * from QM_GUIDE where ID=" + queryGuide.getId();
			else 
				sql = "select * from QM_GUIDE where PARENT_ID=" + queryGuide.getParent_id();
			
			if (!StringUtils.checkNull(queryGuide.getName())){
				sql += " and NAME like '%" + queryGuide.getName() + "%'" + " order by id desc";
			} else {
				sql += " order by id desc ";
			}
				
			guideList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:guideList) {

				sql = "select ID, NAME from EXAM_ICD_PROP where ID in (select PROP_ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getId() + ")";
				List<PropUnit> icdPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				guide.setIcdPropList(icdPropList);
			}
		} else {}
		
		return guideList;
	}

	@Override
	public boolean addStudyGuide(StudyGuide guide) {		
		
		Long id = this.getNextId("QM_GUIDE");
		guide.setId(id);
		String sql_count = "select count(1) from qm_guide where name='" + guide.getName() + "'";
		if(guide.getParent_id() != null){
			sql_count += " and parent_id ="+guide.getParent_id();
		}else{
			sql_count += "";
		}
		int count = getJdbcTemplate().queryForInt(sql_count);
		if(count >0) return false;
		
		String sql = "insert into QM_GUIDE (ID, NAME, QUALITY_ID, LEVEL, PARENT_ID) values (?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, guide.getId(), guide.getName(), guide.getQuality().getId(), guide.getLevel(), guide.getParent_id());
 		
				
 		
 		if(guide.getUserImageList() != null && guide.getUserImageList().size()>0){
 			String sql_insert_userImage = "insert into qm_guide_image_prop (guide_id,userimage_id) values (?,?)";
 			for(int i=0;i<guide.getUserImageList().size();i++) getJdbcTemplate().update(sql_insert_userImage,guide.getId(),guide.getUserImageList().get(i).getId());
 		}
 		/*if (guide.getUserImageList() != null && guide.getQuality() != null)
			getJdbcTemplate().update(sql, guide.getId(), guide.getName(), guide.getQuality().getId(), guide.getLevel(), guide.getParent_id());*/
 		//else if (guide.getUserImageList() != null)
 		//	getJdbcTemplate().update(sql,guide.getId(),guide.getName(),null,guide.getLevel(),guide.getParent_id());
 		
/*		if (guide.getUserImage() != null && guide.getQuality() != null)
			getJdbcTemplate().update(sql, guide.getId(), guide.getName(), guide.getUserImage().getId(), guide.getQuality().getId(), guide.getLevel(), guide.getParent_id());
		else if (guide.getUserImage() != null) 
			getJdbcTemplate().update(sql, guide.getId(), guide.getName(), guide.getUserImage().getId(), null, guide.getLevel(), guide.getParent_id());
		else if (guide.getQuality() != null)
			getJdbcTemplate().update(sql, guide.getId(), guide.getName(), null, guide.getQuality().getId(), guide.getLevel(), guide.getParent_id());*/
		if(guide.getParent_id() > 0){
			/*sql = "select USERIMAGE_ID as id from QM_GUIDE_image_prop where guide_ID=" + guide.getParent_id();
			//Long image_id = getJdbcTemplate().queryForLong(sql); 
			List<PropUnit> userImageProp = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			List<PropUnit> icdPropList = new ArrayList<PropUnit>();
			if (userImageProp != null && userImageProp.size() > 0)
			{
				for(PropUnit imageProp:userImageProp){
					sql = "select t0.ID, t0.NAME from EXAM_ICD_PROP t0, EXAM_ICD_PROP_VAL t1, QM_USER_IMAGE_PROP t2, SUB_SYS_PROP_VAL t3 where t2.PROP_ID=t3.PROP_VAL_ID and t3.ID=t1.PROPID and t1.ICDID=t0.ID and t2.USERIMAGE_ID=" + imageProp.getId();;
					icdPropList=getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				}
			}
			else{
				sql = "select PROP_ID as ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getParent_id();
		
			 	icdPropList= getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			}
			guide.setIcdPropList(icdPropList);*/
			addICDProp(guide);
		}
		return true;
	}
	public boolean addImageID(StudyGuide guide) {		
		
		Long id = this.getNextId("QM_GUIDE");
		guide.setId(id);
		String sqlBySetID="insert into QM_GUIDE_IMAGE_PROP (GUIDE_ID,USERIMAGE_ID) values(?,?)";
 		if(guide.getUserImage() != null)
		{
 				getJdbcTemplate().update(sqlBySetID,guide.getId(),guide.getUserImage().getId());
 				
		}
		 
		return true;
	}

	@Override
	public boolean updateStudyGuide(StudyGuide guide) {		
		
		String sql_count= " select count(1) from qm_guide where name = '" + guide.getName() + "' and id !=" + guide.getId();
		if(guide.getParent_id() != null && guide.getParent_id() != 0L){
			sql_count += " and parent_id = " + guide.getParent_id();
		}else{
			
		}
		int count = getJdbcTemplate().queryForInt(sql_count);
		if(count > 0 ) return false;
		
		if(guide.getIcdPropList() != null && guide.getIcdPropList().size()>0){
			deleteICDProp(guide);
			addICDProp(guide);
		}
		if (!StringUtils.checkNull(guide.getName())) {
			String sql = "update QM_GUIDE set NAME = :name where Id = :id";
			getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(guide));
		}
		
		if(guide.getUserImageList() != null ){
			String sql_deleteProp ="delete from qm_guide_image_prop where guide_id="+guide.getId();
			getSimpleJdbcTemplate().update(sql_deleteProp);
			for(int i=0;i<guide.getUserImageList().size();i++){
				String sql_update_image_prop = "insert into qm_guide_image_prop (guide_id,userimage_id) values (?,?)";
				getSimpleJdbcTemplate().update(sql_update_image_prop, guide.getId(),guide.getUserImageList().get(i).getId());
			}
			
		}
		return true;
	}

	private void deleteICDProp(StudyGuide guide) {
		String sql = "delete from qm_guide_prop where guide_id="+guide.getId();
		getJdbcTemplate().update(sql);	
	}

	@Override
	public boolean deleteStudyGuide(StudyGuide guide) {
		
		//String sql = "select count(1) from QM_GUIDE where PARENT_ID=" + guide.getId();
		//Long cnt = getJdbcTemplate().queryForLong(sql);		
		//if (cnt > 0L){
			String sql_userImage_Id = "delete from qm_guide_image_prop where guide_id="+guide.getId();
			getJdbcTemplate().update(sql_userImage_Id);
			String sql_delete_icd = "delete from qm_guide_prop where guide_id="+guide.getId();
			getJdbcTemplate().update(sql_delete_icd);
			String sql_delete = "delete from qm_guide where parent_id="+guide.getId();
			getJdbcTemplate().update(sql_delete);
			//return false;
	//	}
		
		String sql = "delete from QM_GUIDE where ID=" + guide.getId();
		getJdbcTemplate().update(sql);
		return true;
	}

	public void addICDProp(StudyGuide guide) {
		String sql = "insert into QM_GUIDE_PROP (GUIDE_ID, PROP_ID) values (?, ?)";
		for (PropUnit prop:guide.getIcdPropList()) {
			getJdbcTemplate().update(sql, guide.getId(), prop.getId());
		}
	}

	@Override
	public boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr) {
		
		String sql = "select count(1) from QM_GUIDE_PROP where GUIDE_ID=" + guideId + " and PROP_ID=" + icdPropId;
		Long p = getJdbcTemplate().queryForLong(sql);
		
		if (p != null && p > 0) {
			sql = "delete from QM_GUIDE_PROP where GUIDE_ID=" + guideId + " and PROP_ID=" + icdPropId;
		} else {
			sql = "insert into QM_GUIDE_PROP (GUIDE_ID, PROP_ID) values (" + guideId + ", " + icdPropId + ")";
		}
		
		getJdbcTemplate().update(sql);
		
		return true;
	}

	@Override
	public List<ExamICD> getIcdList(ExamICD icd) {
		List<ExamICD> icdList = new ArrayList<ExamICD>();
		String sql_select_icd="";
		if(icd.getId() == null){
		 sql_select_icd= "SELECT ID,NAME FROM EXAM_ICD_PROP WHERE TYPE="+icd.getType();
		}else if(icd.getId() != null){
			sql_select_icd = "SELECT T1.ID,T1.NAME FROM qm_guide_prop T2,exam_icd_prop T1 WHERE T2.GUIDE_ID="+icd.getId()+" AND T2.PROP_ID=T1.ID";
		}
		if(icd.getName() != null){
			sql_select_icd = "SELECT ID,NAME FROM EXAM_ICD_PROP WHERE TYPE="+icd.getType() + " and name like '%" + icd.getName() + "%'";
		}
		icdList = getJdbcTemplate().query(sql_select_icd,ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class));
		return icdList;
	}
		@Override
	public List<StudyGuide> UpdataData(StudyGuide guide) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<StudyGuide> getStudyGuideListById(StudyGuide queryGuide) {
		String sql_select_studyGuide ="select * from qm_guide where id="+queryGuide.getId();
		List<StudyGuide> studyGuideList = new ArrayList<StudyGuide>();
		studyGuideList = getJdbcTemplate().query(sql_select_studyGuide,ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
		
		String sql_select_userImage = "select t.* from qm_user_image t,qm_guide_image_prop t2 where t.id=t2.userimage_id and t2.guide_id="+queryGuide.getId();
		List<UserImage> userImageList = getJdbcTemplate().query(sql_select_userImage,ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
		studyGuideList.get(0).setUserImageList(userImageList);
		
		return studyGuideList;
	}

	@Override
	public List<ExamICD> getIcdListByKey(ExamICD icd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamICD> getEditICDList(Long id) {
		List<ExamICD> icdList = new ArrayList<ExamICD>();
		String getEditICD = "SELECT T1.ID,T1.NAME FROM qm_guide_prop T2,exam_icd_prop T1 WHERE T2.GUIDE_ID="+id+" AND T2.PROP_ID=T1.ID";
		icdList = getJdbcTemplate().query(getEditICD,ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class));
		return icdList;
	}

	@Override
	public List<StudyGuide> getNextStudyGuide(long id, int level) {
		
		String sql = "";
		List<StudyGuide> list = new ArrayList<StudyGuide>();
		
		switch (level) {
		case 2:
		{	
			sql = "select * from qm_guide where QUALITY_ID=" + id;
			list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			break;
		}
		case 3:
		{
			sql = "select * from qm_guide where PARENT_ID=" + id;
			list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			break;
		}
		default:break;
		}
		
		for (StudyGuide guide:list)
			guide.setLevel((long)level+1);
		
		return list;
	}

	@Override
	public void getStudyGuidePageList(PageList pl, StudyGuide queryGuide) {
		String sql = "";
		List<StudyGuide> guideList = new ArrayList<StudyGuide>();
		if(queryGuide.getId()!= null){
			sql = "select *  from QM_GUIDE where  id= "+queryGuide.getId()+" order by id desc";
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			List<StudyGuide> tempList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			List<UserImage> userImageList = new ArrayList<UserImage>();
			for(StudyGuide guide:tempList){
				String sql_select_userImage = "select USERIMAGE_ID as ID from qm_guide_image_prop where GUIDE_ID=" + queryGuide.getId();
			
				userImageList = getJdbcTemplate().query(sql_select_userImage, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				for (int i=0; i<userImageList.size(); i++) {
				
				//try-catch() !!!
					UserImage image = userImageManageJDBCDAO.getUserImageList(userImageList.get(i)).get(0);
				
					userImageList.set(i, image);
					guide.setUserImageList(userImageList);
					guideList.add(guide);
				}
			
			}
			pl.setList(guideList);
		}
		 if (queryGuide.getLevel() != null && queryGuide.getLevel() == 1L) {
			//Start Search By Using 人物画像。。。。 Add IE
			if(queryGuide.getUserImage() != null ){
				
				sql = "select id from qm_user_image where name like ?";
				String sql1="";
				List<PropUnit> propList = new ArrayList<PropUnit>();
				List<PropUnit> prop = new ArrayList<PropUnit>();
				propList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class), "%"+queryGuide.getUserImage().getName()+"%");
				if(propList != null && propList.size()>0){
					sql1 = "select  guide_id as id from qm_guide_image_prop where userimage_id in (";
					for(int i=0;i<propList.size();i++){
						
						if(i>0) sql1 +=",";
						sql1 += propList.get(i).getId();
					}
					sql1 += ")";
					prop = getJdbcTemplate().query(sql1, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					for(int i=0;i<prop.size();i++){

						if(null!=queryGuide.getName()&&!"".equals(queryGuide.getName())){
							sql = sql = "select  * from qm_guide where id="+prop.get(i).getId()+" and NAME LIKE '%"+queryGuide.getName().trim()+"%' order by id desc";
						} else {
							sql = "select  * from qm_guide where id="+prop.get(i).getId();
						}
						Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
						pl.setFullListSize(fullListSize);
						List<StudyGuide> tmp_list = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
						
					
						for (StudyGuide guide:tmp_list) {
					
							List<UserImage> userImageList = new ArrayList<UserImage>();
						
							String sql_select_userImage = "select USERIMAGE_ID as ID from qm_guide_image_prop where GUIDE_ID=" + guide.getId();
						
							userImageList = getJdbcTemplate().query(sql_select_userImage, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
							for (int j=0; j<userImageList.size(); j++) {
							
							//try-catch() !!!
								UserImage image = userImageManageJDBCDAO.getUserImageList(userImageList.get(j)).get(0);
								userImageList.set(j, image);
							}
						
							guide.setUserImageList(userImageList);
						
						
							guideList.add(guide);
						}
					}
					pl.setList(guideList);
				} 
				//End of Search By Using 人物画像
			}else {
				if(null!=queryGuide.getName()&&!"".equals(queryGuide.getName())){
					sql = "select *  from QM_GUIDE where LEVEL= "+queryGuide.getLevel()+" and NAME LIKE '%"+queryGuide.getName().trim()+"%' order by id desc";
				} else {
					sql = "select *  from QM_GUIDE where LEVEL= "+queryGuide.getLevel()+" order by id desc";
				}

			
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			List<StudyGuide> tmp_list = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:tmp_list) {
			
				List<UserImage> userImageList = new ArrayList<UserImage>();
				
				String sql_select_userImage = "select USERIMAGE_ID as ID from qm_guide_image_prop where GUIDE_ID=" + guide.getId();
				
				userImageList = getJdbcTemplate().query(sql_select_userImage, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				for (int i=0; i<userImageList.size(); i++) {
					
					/*try-catch() !!! 知道了也不改
					UserImage image = userImageManageJDBCDAO.getUserImageList(userImageList.get(i)).get(0);
					userImageList.set(i, image);
					*/
					
					//YHQ,2017-09-11,IndexOutOfBoundsException
					List<UserImage> userImageListQuery = userImageManageJDBCDAO.getUserImageList(userImageList.get(i)) ;
					if (userImageListQuery != null && userImageListQuery.size() > 0) {
						UserImage image = userImageListQuery.get(0);
						userImageList.set(i, image);
					}
					
				}
				
				guide.setUserImageList(userImageList);
				/*UserImage queryImage = new UserImage();
				//queryImage.setId(image_id);
				if (guide.getUserImage() != null && !StringUtils.checkNull(guide.getUserImage().getName())) 
					queryImage.setName(guide.getUserImage().getName());
				
				List<UserImage> userImageList = userImageManageJDBCDAO.getUserImageList(queryImage);
				
				guide.setUserImage(userImageList.get(0));
				
				*/
				
				guideList.add(guide);
			}
			pl.setList(guideList);
		}
		 }	else if (queryGuide.getLevel() != null && queryGuide.getLevel() == 4L) {
			
			sql = "select * from QM_GUIDE where PARENT_ID=";
			sql += queryGuide.getParent_id();
			sql += " and QUALITY_ID=";
			sql += queryGuide.getQuality().getId();
			if (queryGuide.getName() != null) sql += " and name like '%" + queryGuide.getName() + "%'";
			sql += " order by id desc";
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			guideList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:guideList) {

				sql = "select ID, NAME from EXAM_ICD_PROP where ID in (select PROP_ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getId() + ")";
				List<PropUnit> icdPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				guide.setIcdPropList(icdPropList);
			}
			
			pl.setList(guideList);
		} else if (queryGuide.getLevel() != null && queryGuide.getLevel() == 5L) {
			
			if (queryGuide.getParent_id() == null)
				sql = "select * from QM_GUIDE where ID=" + queryGuide.getId();
			else 
				sql = "select * from QM_GUIDE where PARENT_ID=" + queryGuide.getParent_id();
			
			if (!StringUtils.checkNull(queryGuide.getName()))
				sql += " and NAME like '%" + queryGuide.getName() + "%'" + " order by id desc";
			
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			guideList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:guideList) {

				sql = "select ID, NAME from EXAM_ICD_PROP where ID in (select PROP_ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getId() + ")";
				List<PropUnit> icdPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				guide.setIcdPropList(icdPropList);
			}
			pl.setList(guideList);
		} else {}
		
	}
}
