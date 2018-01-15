package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.QualityModelManageDAO;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

public class QualityModelManageJDBCDAO extends BaseDao implements
		QualityModelManageDAO {

	@Override
	public List<QualityModel> getQualityModelList(QualityModel qualityModel) {
		
		StringBuffer sql = new StringBuffer();		
		List<QualityModel> qualityModelList = new ArrayList<QualityModel>();
		/*Long id = qualityModel.getId()
		sql.append("select *  from QM_TYPE where id>0 ");
		//List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
		if (qualityModel.getType() != null && !StringUtil.checkNull(qualityModel.getType().getName())) {
			
			sql.append(" and name like '%").append(qualityModel.getType().getName()).append("%'");}
		
			List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
		
			for (ModelType modelType:modelTypeList) {
				QualityModel model = new QualityModel();
				model.setType(modelType);
				qualityModelList.add(model);
			}
		
		else{
			sql.append("select *  from QM_QUALITY_PROP where QUALITY_ID=").append(qualityModel.getId());
			List<PropUnit> propTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			QualityModel model = new QualityModel();
			model.setSubjectPropList(propTypeList);
			qualityModelList.add(model);
		}	*/
		
		if (qualityModel.getId() == null || qualityModel.getId() == 0) {
			
			sql.append("select id, name  from EXAM_PROP_VAL where type=24 ");			
			if (qualityModel.getType() != null && !StringUtil.checkNull(qualityModel.getType().getName())) 
				sql.append(" and CONCAT(name, '能力模型') like '%").append(qualityModel.getType().getName()).append("%'");
			List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
			for (ModelType modelType:modelTypeList) {
				QualityModel model = new QualityModel();
				model.setType(modelType);
				qualityModelList.add(model);
			}
			
		} else {
			sql.append("select * from QM_QUALITY where ID=").append(qualityModel.getId()).append(" order by id desc");
			qualityModelList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
			
			for (QualityModel quality:qualityModelList) {
				String prop_sql = "select QUALITY_ID, PROP_ID as ID from QM_QUALITY_PROP where QUALITY_ID=" + quality.getId();
				List<PropUnit> zuti_propList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				quality.setSubjectPropList(zuti_propList);
			}
		}
		
		return qualityModelList;
	}
	
	@Override
	public List<QualityModel> getNextQualityModelList(QualityModel qualityModel) {
		//ModelType modelType = new ModelType();
		List<QualityModel> modelList = new ArrayList<QualityModel>();
		StringBuffer sql = new StringBuffer();
		
		if (qualityModel.getType() != null && qualityModel.getType().getId() > 0) {			
			Long typeID=qualityModel.getType().getId();
			String searchname = qualityModel.getName();
			//sql.append("select *  from QM_TYPE where id").append(typeID);
			if(!StringUtil.checkNull(searchname)){
				sql.append("select * from QM_QUALITY where NAME like ").append("'%").append(searchname).append("%'").append(" and TYPEID=").append(typeID);
			}else{
				sql.append("select * from QM_QUALITY where TYPEID=").append(typeID);
			}
			sql.append(" order by id desc");
			modelList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
		} else if (qualityModel.getId() != null) {
			String searchname = qualityModel.getName();
			Long parentID=qualityModel.getId();
			if(!StringUtil.checkNull(searchname)){
				sql.append("select * from QM_QUALITY where NAME like").append("'%").append(searchname).append("%'").append(" and PARENTID=").append(parentID);;
			}else{
				sql.append("select * from QM_QUALITY where PARENTID=").append(parentID);
			}
			
			modelList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
			
			for (QualityModel model:modelList) {
				
				String prop_sql = "select t1.prop_id as id, t3.name from QM_QUALITY_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.QUALITY_ID=" + model.getId();
				List<PropUnit> subjectList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				model.setSubjectPropList(subjectList);
			}
		//	modelTypeList=+getJdbcTemplate().query(prop_sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		} else {}
		
		
		
		return modelList;
	}

	@Override
	public boolean addQualityModel(QualityModel qualityModel) {
		
		Long id = getNextId("QM_QUALITY");		
		Long add_type = qualityModel.getType().getId();
		String add_name = qualityModel.getName();
		Long parent_id = qualityModel.getParentId();
		
		String sql="";
		String sql_add = "";
		List values = new ArrayList();
		if (parent_id != null && parent_id > 0){ 
			sql_add = "INSERT INTO QM_QUALITY (ID,NAME,TYPEID,PARENTID) VALUES (?,?,?,?)";
			values.add(id);
			values.add(add_name);
			values.add(add_type);
			values.add(parent_id);
		}
		else{
			sql_add = "INSERT INTO QM_QUALITY (ID,NAME,TYPEID) VALUES (?,?,?)";
			values.add(id);
			values.add(add_name);
			values.add(add_type);
		}
		
		List<PropUnit> subjectPropList = qualityModel.getSubjectPropList();
		if (subjectPropList != null && subjectPropList.size() > 0) {
			String sql_add1 = "INSERT INTO QM_QUALITY_PROP (QUALITY_ID, PROP_ID) VALUES (?, ?)";
			for (int i=0;i<subjectPropList.size();i++) {
				getSimpleJdbcTemplate().update(sql_add1, id, subjectPropList.get(i).getId());
			}
		}
		getSimpleJdbcTemplate().update(sql_add, values.toArray());
		return true;
	
	}
		
	@Override
	public boolean updateQualityModel(QualityModel qualityModel) {
		Long update_id = qualityModel.getId();
		String update_name = qualityModel.getName();
		String sql=""; //change by IE
		sql="UPDATE QM_QUALITY SET name='"+update_name+"'WHERE id='"+update_id+"'";
		getSimpleJdbcTemplate().update(sql);
	if(qualityModel.getSubjectPropList() != null){
		sql = "DELETE from QM_QUALITY_PROP where QUALITY_ID="+update_id;
		getSimpleJdbcTemplate().update(sql);
		
		sql = "INSERT INTO QM_QUALITY_PROP (QUALITY_ID, PROP_ID) values (?, ?)";
		List<PropUnit> subjectPropList = qualityModel.getSubjectPropList();
		for(PropUnit prop:subjectPropList){
			getSimpleJdbcTemplate().update(sql, update_id, prop.getId());
		}
	}
		return true;		
	}

	@Override
	public boolean deleteQualityModel(QualityModel qualityModel) {
		
		String sqlcount = "select count(1) from QM_QUALITY where PARENTID = ?";
		Integer count = getJdbcTemplate().queryForInt(sqlcount, qualityModel.getId());
		if (count > 0) return false;
		String sql = "delete from QM_QUALITY where id="+qualityModel.getId();
		getJdbcTemplate().update(sql);
		String sql_prop = "delete from QM_QUALITY_PROP where QUALITY_ID="+qualityModel.getId();
		getJdbcTemplate().update(sql_prop);

		return true;
	}
	@Override
	public List<PropUnit> getZutiListByType() {
		String sql_zuti = "select t.*, cast(t.code as signed) as codenum, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.type = 7  ";
		List<PropUnit> zutilist = getJdbcTemplate().query(sql_zuti, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return zutilist;
	} 

	@Override
	public List<QualityModel> getQualityModelListByZutiIds(List<Long> idList) {
	
		String sql = "select * from qm_quality where id in (select DISTINCT QUALITY_ID from qm_quality_prop WHERE PROP_ID in (";
		
		for (Long id:idList) sql += id + ",";
		sql = sql.substring(0, sql.length()-1) + "))";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
	}

	@Override
	public List<QualityModel> getDeepQualityModelListFromImageIds(
			long[] image_ids) {
		
		String sql = "SELECT * FROM qm_quality WHERE PARENTID in (SELECT DISTINCT ID from qm_quality WHERE TYPEID in (SELECT TYPEID from qm_user_image WHERE ID in (";
		for (long id:image_ids) {
			sql += id + ",";
		}
		sql = sql.substring(0, sql.length()-1) + "))) ORDER BY ID";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
	}

	@Override
	public void getQualityPageList(PageList pl, QualityModel qualityModel) {
		List<QualityModel> qualityModelList = new ArrayList<QualityModel>();
		List<ModelType> modelTypeList =  new ArrayList<ModelType>();
		StringBuilder sql = new StringBuilder();
		if (qualityModel.getId() == null || qualityModel.getId() == 0) {
			
			
			sql.append("select id, name  from EXAM_PROP_VAL where type=24 ");
			
			
			
		
			if (qualityModel.getType() != null && !StringUtil.checkNull(qualityModel.getType().getName())) 
				sql.append(" and CONCAT(name, '能力模型') like '%").append(qualityModel.getType().getName()).append("%'");
			if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
				sql.append(" order by ").append(pl.getSortCriterion());
				
				if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
					sql.append(" desc");
			}
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			
			modelTypeList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
			
			for (ModelType modelType:modelTypeList) {
				QualityModel model = new QualityModel();
				model.setType(modelType);
				qualityModelList.add(model);
			}
			
			pl.setList(qualityModelList);
		} else {
			sql.append("select * from QM_QUALITY where ID=").append(qualityModel.getId()).append(" order by id desc");
			if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
				sql.append(" order by ").append(pl.getSortCriterion());
				
				
			}
			
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			qualityModelList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
			for (QualityModel quality:qualityModelList) {
				String prop_sql = "select QUALITY_ID, PROP_ID as ID from QM_QUALITY_PROP where QUALITY_ID=" + quality.getId();
				List<PropUnit> zuti_propList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				quality.setSubjectPropList(zuti_propList);
			}
			pl.setList(qualityModelList);
		}
		
	}

	@Override
	public void getNextQualityModelList(PageList pl, QualityModel qualityModel) {
		List<QualityModel> modelList = new ArrayList<QualityModel>();
		StringBuffer sql = new StringBuffer();
		
		if (qualityModel.getType() != null && qualityModel.getType().getId() > 0) {			
			Long typeID=qualityModel.getType().getId();
			String searchname = qualityModel.getName();
			//sql.append("select *  from QM_TYPE where id").append(typeID);
			if(!StringUtil.checkNull(searchname)){
				sql.append("select * from QM_QUALITY where NAME like ").append("'%").append(searchname).append("%'").append(" and TYPEID=").append(typeID);
			}else{
				sql.append("select * from QM_QUALITY where TYPEID=").append(typeID);
			}
			sql.append(" order by id desc");
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			modelList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
			pl.setList(modelList);
		} else if (qualityModel.getId() != null) {
			String searchname = qualityModel.getName();
			Long parentID=qualityModel.getId();
			if(!StringUtil.checkNull(searchname)){
				sql.append("select * from QM_QUALITY where NAME like").append("'%").append(searchname).append("%'").append(" and PARENTID=").append(parentID);;
			}else{
				sql.append("select * from QM_QUALITY where PARENTID=").append(parentID);
			}
			sql.append(" order by id desc");
			Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()));
			pl.setFullListSize(fullListSize);
			modelList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
			
			
			for (QualityModel model:modelList) {
				
				String prop_sql = "select t1.prop_id as id, t3.name from QM_QUALITY_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.QUALITY_ID=" + model.getId();
				List<PropUnit> subjectList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				model.setSubjectPropList(subjectList);
			}
			pl.setList(modelList);
		//	modelTypeList=+getJdbcTemplate().query(prop_sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		} else {}
		
	}
	// 2017/01/11 Add By IE
	// To Check Compare 能力模型
	@Override
	public boolean compareQualityModel(QualityModel qualityModel) { //YHQ，2017-06-01，判断能力模型二级名称是否重复
		boolean qmFlag = false ;
		String qmSql = "" ;
		
		Long parentId = qualityModel.getParentId() ;
		Long typeId   = qualityModel.getType().getId() ;
		if (parentId != null && parentId > 0) {
			qmSql = "select count(1) from qm_quality  where PARENTID = " + parentId + "  and name = '" + qualityModel.getName() + "' " ;
		} else {
			qmSql = "select count(1) from qm_quality  where TYPEID = " + typeId + "  and name = '" + qualityModel.getName() + "' " ;
		}
		
		
		int rsNum = getJdbcTemplate().queryForInt(qmSql);
		if (rsNum > 0) qmFlag = true ;
		return qmFlag ;
		
		/*YHQ，2017-06-01，重写
		String compareSql = "select count(t0.quality_id) from ";
		String whereSql = " where ";
		String propSql = "";
		int count = 0;
		if(qualityModel.getParentId() == 0L && qualityModel.getType().getId() != null )
		{
			String compareLevel1 = "select count(1) from qm_quality where name=? and typeid=" + qualityModel.getType().getId();
			count = getJdbcTemplate().queryForInt(compareLevel1, qualityModel.getName());
			if(count > 0)
				return false;
			else 
				return true;
		}else{
			String compareParentName = "select count(1) from qm_quality where name=? and id=" + qualityModel.getParentId();
			if(getJdbcTemplate().queryForInt(compareParentName, qualityModel.getName()) > 0){
				return false;
			}else{
				for(int i = 0; i < qualityModel.getSubjectPropList().size(); i++){
					if(i != 0){
						if(qualityModel.getId() != null && !qualityModel.getId().equals(0L)){
							propSql += " ,(select distinct p.quality_id as quality_id from qm_quality_prop p left join qm_quality i on p.quality_id = i.id where i.parentid="+ qualityModel.getParentId() + " and i.id=" + qualityModel.getId() + " and p.prop_id = " + qualityModel.getSubjectPropList().get(i).getId() + ") t" + i;
						}
						else {
							propSql += " ,(select distinct p.quality_id as quality_id from qm_quality_prop p left join qm_quality i on p.quality_id=i.id where i.parentid = " + qualityModel.getParentId() + " and p.prop_id = " + qualityModel.getSubjectPropList().get(i).getId() + ") t" + i;
						}
						whereSql += " and t" + (i-1) +".quality_id=t" + i + ".quality_id";
					}
					else{
						if(qualityModel.getId() != null && !qualityModel.getId().equals(0L)){
							propSql += "(select distinct p.quality_id as quality_id from qm_quality_prop p left join qm_quality i on p.quality_id = i.id where i.parentid="+ qualityModel.getParentId() + " and i.id=" + qualityModel.getId() + " and p.prop_id = " + qualityModel.getSubjectPropList().get(i).getId() + ") t" + i;
						}
						else {
							propSql += "(select distinct p.quality_id as quality_id from qm_quality_prop p left join qm_quality i on p.quality_id=i.id where i.parentid = " + qualityModel.getParentId() + " and p.prop_id = " + qualityModel.getSubjectPropList().get(i).getId() + ") t" + i;
						}
						whereSql += "t0.quality_id>0";
					}
				}
			}
		}
		compareSql += propSql + whereSql;
		count = getJdbcTemplate().queryForInt(compareSql);
		if(count >0 )
			return false;
		else
			return true;
		*/	
	} 
	
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelOneListByUserImage(Map args) {
		if (args != null) {
			List<Long> userMageIdList = (List<Long>) args.get("userMageIdList") ;
			Long unit_id = (Long) args.get("unit_id") ;
			List<QualityModel> qmLevelOneList = null ;
			String sql = "select cq.PROP_ID as id, qq.name, cq.state as level from cv_unit_ref_quality cq " 
					   + " left join qm_quality qq on cq.PROP_ID = qq.ID "
					   + " where cq.UNIT_ID = ? and cq.LEVEL = 1" ; 
			if (unit_id != -1L) {
				qmLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class),unit_id);
				if (qmLevelOneList != null && qmLevelOneList.size() > 0) return qmLevelOneList ;
			}
			
			if (userMageIdList != null && userMageIdList.size() > 0) {
				sql = "select distinct qq.* from QM_QUALITY qq left join QM_USER_IMAGE qi on qq.TYPEID = qi.TYPEID where qi.id in (" ;
				for (long id:userMageIdList) {
					sql += id + ",";
				}
				sql = sql.substring(0, sql.length()-1) + " ) order by id desc" ;
				qmLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
				return qmLevelOneList ;
			}
		}		
		
		return null ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelTwoListByLevelOne(Map args) {
		if (args != null) {
			Long id = (Long) args.get("id") ;
			Long unit_id = (Long) args.get("unit_id") ;
			List<QualityModel> qmLevelTwoList = null ;
			String sql = "select cq.PROP_ID as id, qq.name, cq.state as level from cv_unit_ref_quality cq " 
					   + " left join qm_quality qq on cq.PROP_ID = qq.ID "
					   + " where cq.UNIT_ID = ? and cq.parent_prop_id = ? and cq.LEVEL = 2" ; 
			if (unit_id != null && unit_id != -1L) {
				qmLevelTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class),unit_id,id);
				if (qmLevelTwoList != null && qmLevelTwoList.size() > 0) return qmLevelTwoList ;
			}
			
			if (id != null && id != -1L) {
				sql =  "select * from QM_QUALITY where PARENTID = ?" ;
				qmLevelTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class),id);
				return qmLevelTwoList ;
			}
		}
		
		return null ;
		
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级以后，再根据能力模型二级和项目的人物画像获取第三级能力模型，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelThreeListByLevelTwoAndUserImage(Map args) {
		if (args != null) {
			Long qualityModelLevelTwoID = (Long) args.get("id") ;
			List<Long> userMageIdList = (List<Long>) args.get("userMageIdList") ;			
			Long unit_id = (Long) args.get("unit_id") ;
			
			List<QualityModel> qmLevelThreeList = null ;
			String sql = "select cq.PROP_ID as id, qg.name, cq.state as level from cv_unit_ref_quality cq " 
					   + " inner join QM_GUIDE qg on qg.id = cq.PROP_ID   "
					   + " where cq.UNIT_ID = ? and cq.parent_prop_id = ? and cq.LEVEL = 3" ; 
			if (unit_id != null && unit_id != -1L && qualityModelLevelTwoID != null && qualityModelLevelTwoID != -1L) {
				qmLevelThreeList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class),unit_id,qualityModelLevelTwoID);
				if (qmLevelThreeList != null && qmLevelThreeList.size() > 0) return qmLevelThreeList ;
			}
			
			if (qualityModelLevelTwoID != null && userMageIdList != null && userMageIdList.size() > 0) {
				sql = "select distinct qg.* from QM_GUIDE qg inner join qm_guide_image_prop qi on qg.PARENT_ID = qi.GUIDE_ID where qi.USERIMAGE_ID in (" ;
				for (long id:userMageIdList) {
					sql += id + ",";
				}
				sql = sql.substring(0, sql.length()-1) + ") and QUALITY_ID = " + qualityModelLevelTwoID + " order by id desc " ;
				qmLevelThreeList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
				return qmLevelThreeList ;
			}
		}
		
		
		return null ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级以后，再根据能力模型二级和项目的人物画像获取第三级能力模型以后，再根据第三级获取第四级，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelFourListByLevelThree(Map args) {
		if (args != null) {
			Long qualityModelLevelThreeID = (Long) args.get("id") ;					
			Long unit_id = (Long) args.get("unit_id") ;
			List<QualityModel> qmLevelFourList = null ;
			String sql = "select cq.PROP_ID as id, qg.name, cq.state as level from cv_unit_ref_quality cq " 
					   + " inner join QM_GUIDE qg on qg.id = cq.PROP_ID  "
					   + " where cq.UNIT_ID = ? and cq.parent_prop_id = ? and cq.LEVEL = 4" ; 
			if (unit_id != null && unit_id != -1L && qualityModelLevelThreeID != null && qualityModelLevelThreeID != -1L) {
				qmLevelFourList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class),unit_id,qualityModelLevelThreeID);
				if (qmLevelFourList != null && qmLevelFourList.size() > 0) return qmLevelFourList ;
			}
			
			if (qualityModelLevelThreeID != null) {
				sql = "select * from QM_GUIDE where PARENT_ID = ?" ;
				qmLevelFourList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class),qualityModelLevelThreeID);
				return qmLevelFourList ;
			}
		}		
		return null ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-24
	 * 课程单元id
	 * 课程管理下课程单元富文本编辑中加入素材，YHQ 2017-02-24
	 */
	@Override
	public boolean insertCvUnitRefQualityByGroupClass(QualityModel qualityModel) {
		if (qualityModel != null && qualityModel.getId() != null && qualityModel.getId() > 0) {
			String sql = "select distinct qp.guide_id as id from   qm_guide_image_prop qp "
                       + " inner join CV_SET_USER_IMAGE ci on ci.USERIMAGE_ID = qp.USERIMAGE_ID "
                       + " inner join cv_set cs on cs.id = ci.CV_SET_ID "
                       + " inner join CV_SET_SCHEDULE s2 on cs.id = s2.CV_SET_ID "
                       + " inner join CV_SCHEDULE s1 on s1.SCHEDULE_ID = s2.CV_SCHEDULE_ID "
                       + " inner join cv t on t.id = s1.CV_ID "
                       + " inner join cv_ref_unit t1 on t.id=t1.cv_id " 
                       + " inner join cv_unit t2 on t1.unit_id=t2.id "
                       + " where t2.id = ? " ;
			List<QualityModel> guideList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qualityModel.getId()) ;						
			String guideIds = "" ;
			for (QualityModel gqmObj : guideList) {
				guideIds = gqmObj.getId() + "," + guideIds ;
			}
			if (guideList != null && guideList.size() > 0) {
				guideIds = guideIds.substring(0, guideIds.length() - 1) ;
			}
			
			sql = "delete from cv_unit_ref_quality where unit_id = ? " ;
			getJdbcTemplate().update(sql, qualityModel.getId()) ;
			
			sql = "select distinct qq.id from QM_QUALITY qq "
				+ " inner join QM_USER_IMAGE qi on qi.TYPEID = qq.TYPEID "
				+ " inner join CV_SET_USER_IMAGE ci on ci.USERIMAGE_ID = qi.id "
				+ " inner join cv_set cs on cs.id = ci.CV_SET_ID "
				+ " inner join CV_SET_SCHEDULE s2 on cs.id = s2.CV_SET_ID "
				+ " inner join CV_SCHEDULE s1 on s1.SCHEDULE_ID = s2.CV_SCHEDULE_ID "
				+ " inner join cv t on t.id = s1.CV_ID "
				+ " inner join cv_ref_unit t1 on t.id=t1.cv_id " 
				+ " inner join cv_unit t2 on t1.unit_id=t2.id "
				+ "where t2.id = ? " ;
			List<QualityModel> qmLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qualityModel.getId()) ;
			for (QualityModel qmLevelOneObj :qmLevelOneList) {
				String sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,LEVEL,state) VALUES (?,?,1,0)" ;
				getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelOneObj.getId()) ;
				
				sql = "select distinct qq.id from QM_QUALITY qq where PARENTID = ?" ;
				List<QualityModel> qmLevelTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qmLevelOneObj.getId()) ;
				for (QualityModel qmLevelTwoObj : qmLevelTwoList) {
					sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,2,0)" ;
					getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelTwoObj.getId(),qmLevelOneObj.getId()) ;
					
					if (!guideIds.equals("")) {
						sql = "select distinct id from QM_GUIDE where PARENT_ID in (" + guideIds + ") and QUALITY_ID = ? order by id desc " ;
						List<QualityModel> qmLevelThreeList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qmLevelTwoObj.getId()) ;
						for (QualityModel qmLevelThreeObj : qmLevelThreeList) {
							sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,3,0)" ;
							getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelThreeObj.getId(),qmLevelTwoObj.getId()) ;
							
							sql = "select distinct id from QM_GUIDE where PARENT_ID = ? " ;
							List<QualityModel> qmLevelFourList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qmLevelThreeObj.getId()) ;
							for (QualityModel qmLevelFourObj : qmLevelFourList) {
								sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,4,0)" ;
								getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelFourObj.getId(),qmLevelThreeObj.getId()) ;
							}														
						}
					}
				}
			}
			return true ;
		}
		return false ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-25
	 * 课程单元id
	 * 富文本内容
	 * 课程管理下课程单元富文本编辑中更新素材，YHQ 2017-02-25
	 */
	@Override
	public boolean updateCvUnitRefQualityByGroupClass(QualityModel qualityModel) {
		if (qualityModel != null) {
			Long UnitId = qualityModel.getId() ;
			String html = qualityModel.getName() ;
			if (UnitId != null && !StringUtils.checkNull(html)) {
				Document doc = Jsoup.parse(html);
				Elements allImg = doc.getElementsByTag("img") ;
				boolean haveContentFlag = false ;
				Hashtable sucaiMap = new Hashtable() ;
				Hashtable paperMap = new Hashtable() ;
				Hashtable bingliMap = new Hashtable() ;
				
				boolean haveBingZhongFlag = true ; //病种有数据
				Hashtable qmLevelOneMapBySuCaiBingZhong = new Hashtable() ;//通过素材的病种获取能力模型1级
				Hashtable qmLevelTwoMapBySuCaiBingZhong = new Hashtable() ;//通过素材的病种获取能力模型2级
				Hashtable qmLevelThreeMapBySuCaiBingZhong = new Hashtable() ;//通过素材的病种获取能力模型3级
				Hashtable qmLevelFourMapBySuCaiBingZhong = new Hashtable() ;//通过素材的病种获取能力模型4级
				
				Hashtable qmLevelOneMapBySuCaiZhuTi = new Hashtable() ;//通过素材的主题获取能力模型1级
				Hashtable qmLevelTwoMapBySuCaiZhuTi = new Hashtable() ;//通过素材的主题获取能力模型2级
				
				boolean haveBingLiBingZhongFlag = true ; //病例的病种有数据
				Hashtable qmLevelOneMapByBingLiBingZhong = new Hashtable() ;//通过病例的病种获取能力模型1级
				Hashtable qmLevelTwoMapByBingLiBingZhong = new Hashtable() ;//通过病例的病种获取能力模型2级
				Hashtable qmLevelThreeMapByBingLiBingZhong = new Hashtable() ;//通过病例的病种获取能力模型3级
				Hashtable qmLevelFourMapByBingLiBingZhong = new Hashtable() ;//通过病例的病种获取能力模型4级
				
				Hashtable qmLevelOneMapByBingLiZhuTi = new Hashtable() ;//通过病例的主题获取能力模型1级
				Hashtable qmLevelTwoMapByBingLiZhuTi = new Hashtable() ;//通过病例的主题获取能力模型2级
				
				boolean haveShiJuanBingZhongFlag = true ; //试卷的病种有数据
				Hashtable qmLevelOneMapByShiJuanBingZhong = new Hashtable() ;//通过试卷的病种获取能力模型1级
				Hashtable qmLevelTwoMapByShiJuanBingZhong = new Hashtable() ;//通过试卷的病种获取能力模型2级
				Hashtable qmLevelThreeMapByShiJuanBingZhong = new Hashtable() ;//通过试卷的病种获取能力模型3级
				Hashtable qmLevelFourMapByShiJuanBingZhong = new Hashtable() ;//通过试卷的病种获取能力模型4级
				
				Hashtable qmLevelOneMapByShiJuanZhuTi = new Hashtable() ;//通过试卷的主题获取能力模型1级
				Hashtable qmLevelTwoMapByShiJuanZhuTi = new Hashtable() ;//通过试卷的主题获取能力模型2级
				
				Hashtable qmLevel1MapByBZall = new Hashtable() ;//通过病种获取能力模型1级
				Hashtable qmLevel2MapByBZall = new Hashtable() ;//通过病种获取能力模型2级
				Hashtable qmLevel3MapByBZall = new Hashtable() ;//通过病种获取能力模型3级
				Hashtable qmLevel4MapByBZall = new Hashtable() ;//通过病种获取能力模型4级
				
				Hashtable qmLevel1MapByZTall = new Hashtable() ;//通过主题获取能力模型1级
				Hashtable qmLevel2MapByZTall = new Hashtable() ;//通过主题获取能力模型2级
				
				for (Element imgElt : allImg) {
					Attributes allProp = imgElt.attributes() ;
					String altAttr = allProp.get("alt") ;
					String urlAttr = allProp.get("_url") ;
					String onclickAttr = allProp.get("onclick") ; //play(this);
					
					if (!StringUtils.checkNull(urlAttr)) {
						if (!StringUtils.checkNull(altAttr)) {
							if (altAttr.equals("paper")) {//试卷
								paperMap.put(urlAttr, urlAttr) ;
								haveContentFlag = true ;
							}
							if (altAttr.equals("bingli")) {//病例
								bingliMap.put(urlAttr, urlAttr) ;
								haveContentFlag = true ;
							}
						} else { //素材
							sucaiMap.put(urlAttr, urlAttr) ;
							haveContentFlag = true ;
						}
					}
					
				}
				
				if (haveContentFlag) {
					if (sucaiMap.size() > 0) {
						String sucaiStr = "" ;
						Enumeration sucaiEp = sucaiMap.keys() ;
						while(sucaiEp.hasMoreElements() ){
							sucaiStr = "'" +sucaiEp.nextElement() + "'" + "," + sucaiStr ;
						}
						sucaiStr = sucaiStr.substring(0, sucaiStr.length()-1) ;
						if (!StringUtils.checkNull(sucaiStr)) {
							//通过素材的主题拿1级
							String sql = "select distinct qq.parentid as id from QM_QUALITY qq "
									   + " inner join QM_QUALITY_PROP t1 on t1.quality_id = qq.id "
									   + " inner join material_prop_val p on t1.prop_id = p.PROP_ID "
									   + " inner join material_info m  on p.MATERIAL_ID = m.ID "
									   + " inner join exam_prop_val t  on t.ID = t1.PROP_ID "
									   + " where t.type = 7 and m.SAVEPATH in (" + sucaiStr + ") " ;
							List<QualityModel> qmLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelOneObj : qmLevelOneList) {
								qmLevelOneMapBySuCaiZhuTi.put(qmLevelOneObj.getId(), qmLevelOneObj.getId()) ;
								qmLevel1MapByZTall.put(qmLevelOneObj.getId(), qmLevelOneObj.getId()) ;
							}
							//通过素材的主题拿2级
							sql = "select distinct t1.quality_id as id from QM_QUALITY_PROP t1 " 
							    + " inner join material_prop_val p on t1.prop_id = p.PROP_ID "
							    + " inner join material_info m  on p.MATERIAL_ID = m.ID "
							    + "inner join exam_prop_val t  on t.ID = t1.PROP_ID "
							    + " where t.type = 7 and m.SAVEPATH in (" + sucaiStr + ") " ;
							List<QualityModel> qmLevelTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelTwoObj : qmLevelTwoList) {
								qmLevelTwoMapBySuCaiZhuTi.put(qmLevelTwoObj.getId(), qmLevelTwoObj.getId()) ;
								qmLevel2MapByZTall.put(qmLevelTwoObj.getId(), qmLevelTwoObj.getId()) ;
							}
							
							//通过素材的病种拿1级
							sql = "select distinct qq.parentid as id from QM_QUALITY qq "
								+ " inner join QM_GUIDE g on g.quality_id = qq.id "
								+ " inner join QM_GUIDE_PROP q on q.guide_id = g.id "
								+ " inner join EXAM_ICD_PROP i on i.id = q.PROP_ID "
								+ " inner join EXAM_ICD_PROP_VAL p on i.ID=p.ICDID " 
								+ " inner join material_prop_val t1 on t1.prop_id = p.PROPID "
								+ " inner join material_info m  on m.ID = t1.MATERIAL_ID "
								+ " where m.SAVEPATH in (" + sucaiStr + ") " ;
							qmLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelOneObj : qmLevelOneList) {
								qmLevelOneMapBySuCaiBingZhong.put(qmLevelOneObj.getId(), qmLevelOneObj.getId()) ;
								qmLevel1MapByBZall.put(qmLevelOneObj.getId(), qmLevelOneObj.getId()) ;
							}
							//通过素材的病种拿2级
							sql = "select distinct g.quality_id as id from QM_GUIDE g " 
								+ " inner join QM_GUIDE_PROP q on q.guide_id = g.id "
								+ " inner join EXAM_ICD_PROP i on i.id = q.PROP_ID "
								+ " inner join EXAM_ICD_PROP_VAL p on i.ID=p.ICDID " 
								+ " inner join material_prop_val t1 on t1.prop_id = p.PROPID "
								+ " inner join material_info m  on m.ID = t1.MATERIAL_ID "
								+ " where m.SAVEPATH in (" + sucaiStr + ") " ;
							qmLevelTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelTwoObj : qmLevelTwoList) {
								qmLevelTwoMapBySuCaiBingZhong.put(qmLevelTwoObj.getId(), qmLevelTwoObj.getId()) ;
								qmLevel2MapByBZall.put(qmLevelTwoObj.getId(), qmLevelTwoObj.getId()) ;
							}
							//通过素材的病种拿3级
							sql = "select distinct q.guide_id as id from QM_GUIDE_PROP q " 
								+ " inner join EXAM_ICD_PROP i on i.id = q.PROP_ID "
								+ " inner join EXAM_ICD_PROP_VAL p on i.ID=p.ICDID " 
								+ " inner join material_prop_val t1 on t1.prop_id = p.PROPID "
								+ " inner join material_info m  on m.ID = t1.MATERIAL_ID "
								+ " where m.SAVEPATH in (" + sucaiStr + ") " ;
							List<QualityModel> qmLevelThreeList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelThreeObj : qmLevelThreeList) {
								qmLevelThreeMapBySuCaiBingZhong.put(qmLevelThreeObj.getId(), qmLevelThreeObj.getId()) ;
								qmLevel3MapByBZall.put(qmLevelThreeObj.getId(), qmLevelThreeObj.getId()) ;
							}
							//通过素材的病种拿4级
							sql = "select distinct g.id from QM_GUIDE g "
								+ " inner join QM_GUIDE_PROP q on q.guide_id = g.PARENT_ID "
								+ " inner join EXAM_ICD_PROP i on i.id = q.PROP_ID "
								+ " inner join EXAM_ICD_PROP_VAL p on i.ID=p.ICDID " 
								+ " inner join material_prop_val t1 on t1.prop_id = p.PROPID "
								+ " inner join material_info m  on m.ID = t1.MATERIAL_ID "
								+ " where m.SAVEPATH in (" + sucaiStr + ") " ;
							List<QualityModel> qmLevelFourList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelFourObj : qmLevelFourList) {
								qmLevelFourMapBySuCaiBingZhong.put(qmLevelFourObj.getId(), qmLevelFourObj.getId()) ;
								qmLevel4MapByBZall.put(qmLevelFourObj.getId(), qmLevelFourObj.getId()) ;
							}
							
						}
					}										
					
					//病例
					if (bingliMap.size() > 0) {
						String bingliStr = "" ;
						Enumeration bingliEp = bingliMap.keys() ;
						while(bingliEp.hasMoreElements() ){
							bingliStr = bingliEp.nextElement() + "," + bingliStr ;
						}
						bingliStr = bingliStr.substring(0, bingliStr.length()-1) ;
						if (!StringUtils.checkNull(bingliStr)) {
							//根据病例的主题找到能力的1、2级：
							String sql = "select distinct qq.* from QM_QUALITY qq "
									   + " inner join QM_QUALITY_PROP qp on qp.quality_id = qq.id "
									   + " inner join CASE_PATIENT_PROP_VAL p on p.propid = qp.prop_id "
									   + " left join sub_sys_prop_val s on s.id = p.propid " 
									   + " left join exam_prop_val i on i.id = s.prop_val_id " 
									   + " inner join CASE_CASE cc on cc.p_id = p.PATIENTID "
									   + " where cc.id in (" + bingliStr + ") and (i.TYPE > 5 or i.TYPE is null) " ; // ID 二级、PARENTID一级
							List<QualityModel> qmLevelOneTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelOneTwoObj : qmLevelOneTwoList) {
								qmLevelTwoMapByBingLiZhuTi.put(qmLevelOneTwoObj.getId(), qmLevelOneTwoObj.getId()) ;
								qmLevelOneMapByBingLiZhuTi.put(qmLevelOneTwoObj.getParentId(), qmLevelOneTwoObj.getParentId()) ;
								
								qmLevel2MapByZTall.put(qmLevelOneTwoObj.getId(), qmLevelOneTwoObj.getId()) ;
								qmLevel1MapByZTall.put(qmLevelOneTwoObj.getParentId(), qmLevelOneTwoObj.getParentId()) ;
							}							
							//病例的病种找到能力的1、2级：
							sql = "select distinct qq.* from qm_quality qq "
								+ " inner join QM_GUIDE qg on qg.quality_id = qq.id "
								+ " inner join QM_GUIDE_PROP qp  on qp.guide_id = qg.id "
								+ " inner join exam_icd_prop a on a.id = qp.PROP_ID "
								+ " inner join exam_icd_prop_val b on a.TYPE = 10 and a.id=b.icdid "
								+ " inner join CASE_PATIENT_PROP_VAL p on b.propid = p.propid "
								+ " inner join sub_sys_prop_val s on s.id = p.propid "
								+ " inner join exam_prop_val i on i.id = s.prop_val_id and i.TYPE <=5 " 
								+ " inner join CASE_CASE cc on cc.p_id = p.PATIENTID "
								+ " where  cc.id in (" + bingliStr + ") and qg.level = 4 " ;
							qmLevelOneTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelOneTwoObj : qmLevelOneTwoList) {
								qmLevelOneMapByBingLiBingZhong.put(qmLevelOneTwoObj.getParentId(), qmLevelOneTwoObj.getParentId()) ;
								qmLevel1MapByBZall.put(qmLevelOneTwoObj.getParentId(), qmLevelOneTwoObj.getParentId()) ;
								
								qmLevel2MapByBZall.put(qmLevelOneTwoObj.getId(), qmLevelOneTwoObj.getId()) ;
								qmLevelTwoMapByBingLiBingZhong.put(qmLevelOneTwoObj.getId(), qmLevelOneTwoObj.getId()) ;
							}
							//病例的病种找到能力的3级：
							sql = "select distinct qg.id from QM_GUIDE qg "
								+ " inner join QM_GUIDE_PROP qp  on qp.guide_id = qg.id "
								+ " inner join exam_icd_prop a on a.id = qp.PROP_ID "
								+ " inner join exam_icd_prop_val b on a.TYPE = 10 and a.id=b.icdid "
								+ " inner join CASE_PATIENT_PROP_VAL p on b.propid = p.propid "
								+ " inner join sub_sys_prop_val s on s.id = p.propid "
								+ " inner join exam_prop_val i on i.id = s.prop_val_id and i.TYPE <=5 " 
								+ " inner join CASE_CASE cc on cc.p_id = p.PATIENTID "
								+ " where  cc.id in (" + bingliStr + ") and qg.level = 4 " ;
							List<QualityModel> qmLevelThreeList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelThreeObj : qmLevelThreeList) {
								qmLevelThreeMapByBingLiBingZhong.put(qmLevelThreeObj.getId(), qmLevelThreeObj.getId()) ;
								qmLevel3MapByBZall.put(qmLevelThreeObj.getId(), qmLevelThreeObj.getId()) ;
							}
							//病例的病种找到能力的4级：
							sql = "select distinct qg2.id from QM_GUIDE qg2 "
								+ " inner join QM_GUIDE qg  on qg.id = qg2.PARENT_ID "
								+ " inner join QM_GUIDE_PROP qp  on qp.guide_id = qg.id "
								+ " inner join exam_icd_prop a on a.id = qp.PROP_ID "
								+ " inner join exam_icd_prop_val b on a.TYPE = 10 and a.id=b.icdid "
								+ " inner join CASE_PATIENT_PROP_VAL p on b.propid = p.propid "
								+ " inner join sub_sys_prop_val s on s.id = p.propid "
								+ " inner join exam_prop_val i on i.id = s.prop_val_id and i.TYPE <=5 " 
								+ " inner join CASE_CASE cc on cc.p_id = p.PATIENTID "
								+ " where  cc.id in (" + bingliStr + ") and qg.level = 4 " ;
							List<QualityModel> qmLevelFourList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelFourObj : qmLevelFourList) {
								qmLevelFourMapByBingLiBingZhong.put(qmLevelFourObj.getId(), qmLevelFourObj.getId()) ;
								qmLevel4MapByBZall.put(qmLevelFourObj.getId(), qmLevelFourObj.getId()) ;
							}
						}						
					}
					
					//试卷
					if (paperMap.size() > 0) {
						String paperStr = "" ;
						Enumeration paperEp = paperMap.keys() ;
						while(paperEp.hasMoreElements() ){
							paperStr = paperEp.nextElement() + "," + paperStr ;
						}
						paperStr = paperStr.substring(0, paperStr.length()-1) ;
						if (!StringUtils.checkNull(paperStr)) {
							//根据试卷的主题找到能力的1、2级：														
							String sql = "select distinct qq.* from  QM_QUALITY qq "
									   + " inner join QM_QUALITY_PROP qqp on qqp.quality_id = qq.id "
									   + " inner join EXAM_QUESTION_POINT2 s on qqp.prop_id = s.prop_val_id "
									   + " inner join exam_paper_question epq on epq.QUESTION_ID = s.question_id "
									   + " inner join exam_exam_paper eep on eep.PAPER_ID = epq.PAPER_ID "
									   + " where eep.EXAM_ID in (" + paperStr + ") " ;
							List<QualityModel> qmLevelOneTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelOneTwoObj : qmLevelOneTwoList) {
								qmLevelTwoMapByShiJuanZhuTi.put(qmLevelOneTwoObj.getId(), qmLevelOneTwoObj.getId()) ;
								qmLevelOneMapByShiJuanZhuTi.put(qmLevelOneTwoObj.getParentId(), qmLevelOneTwoObj.getParentId()) ;
								qmLevel2MapByZTall.put(qmLevelOneTwoObj.getId(), qmLevelOneTwoObj.getId()) ;
								qmLevel1MapByZTall.put(qmLevelOneTwoObj.getParentId(), qmLevelOneTwoObj.getParentId()) ;
							}							
														
							//根据试卷的病种找到能力的3、2级：
							sql = "select distinct qg.id, qg.QUALITY_ID as parentId from  QM_GUIDE qg "
								+ " inner join QM_GUIDE_PROP qgp on qgp.GUIDE_ID = qg.id "
								+ " inner join exam_icd_prop eip on eip.ID = qgp.PROP_ID and eip.TYPE= 10 " 
								+ " inner join exam_icd_prop_val eipv on eip.id = eipv.icdid "
								+ " inner join EXAM_QUESTION_UNIT equ on equ.prop_val_id = eipv.PROPID "
								+ " inner join exam_paper_question epq on epq.QUESTION_ID = equ.question_id "
								+ " inner join exam_exam_paper eep on eep.PAPER_ID = epq.paper_id "
								+ " where  eep.EXAM_ID in (" + paperStr + ")  and qg.level = 4 " ;
							List<QualityModel> qmLevelThreeTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
							for (QualityModel qmLevelThreeTwoObj : qmLevelThreeTwoList) {
								qmLevelThreeMapByShiJuanBingZhong.put(qmLevelThreeTwoObj.getId(), qmLevelThreeTwoObj.getId()) ;								
								qmLevelTwoMapByShiJuanBingZhong.put(qmLevelThreeTwoObj.getParentId(), qmLevelThreeTwoObj.getParentId()) ;
								qmLevel3MapByBZall.put(qmLevelThreeTwoObj.getId(), qmLevelThreeTwoObj.getId()) ;								
								qmLevel2MapByBZall.put(qmLevelThreeTwoObj.getParentId(), qmLevelThreeTwoObj.getParentId()) ;
							}
							//根据试卷的病种找到能力的4级：
							if (qmLevelThreeMapByShiJuanBingZhong.size() > 0) {								
								String level3Str = "" ;
								Enumeration level3Ep = qmLevelThreeMapByShiJuanBingZhong.keys() ;
								while(level3Ep.hasMoreElements() ){
									level3Str = level3Ep.nextElement() + "," + level3Str ;
								}
								level3Str = level3Str.substring(0, level3Str.length()-1) ;
								if (!StringUtils.checkNull(level3Str)) {
									sql = "select distinct id from QM_GUIDE where PARENT_ID in (" + level3Str + ") " ;
									List<QualityModel> qmLevelFourList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
									for (QualityModel qmLevelFourObj : qmLevelFourList) {										
										qmLevelFourMapByShiJuanBingZhong.put(qmLevelFourObj.getId(), qmLevelFourObj.getId()) ;			
										qmLevel4MapByBZall.put(qmLevelFourObj.getId(), qmLevelFourObj.getId()) ;
									}
								}								
							}
							//根据试卷的病种找到能力的1级：
							if (qmLevelTwoMapByShiJuanBingZhong.size() > 0) {	
								String level4Str = "" ;
								Enumeration level4Ep = qmLevelTwoMapByShiJuanBingZhong.keys() ;
								while(level4Ep.hasMoreElements() ){
									level4Str = level4Ep.nextElement() + "," + level4Str ;
								}
								level4Str = level4Str.substring(0, level4Str.length()-1) ;
								if (!StringUtils.checkNull(level4Str)) {
									sql = "select * from qm_quality q where q.id in (" + level4Str + ") " ;
									List<QualityModel> qmLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class)) ;
									for (QualityModel qmLevelOneObj : qmLevelOneList) {										
										qmLevelOneMapByShiJuanBingZhong.put(qmLevelOneObj.getParentId(), qmLevelOneObj.getParentId()) ;			
										qmLevel1MapByBZall.put(qmLevelOneObj.getParentId(), qmLevelOneObj.getParentId()) ;
									}
								}
							}
							
						}
					}
					
					if (qmLevel1MapByBZall.size() == 0 && qmLevel2MapByBZall.size() == 0 && qmLevel3MapByBZall.size() == 0 && qmLevel4MapByBZall.size() ==0) 
						haveBingZhongFlag = false ;
					
					if (qualityModel != null && qualityModel.getId() != null && qualityModel.getId() > 0) {
						String sql = "select distinct qp.guide_id as id from   qm_guide_image_prop qp "
			                       + " inner join CV_SET_USER_IMAGE ci on ci.USERIMAGE_ID = qp.USERIMAGE_ID "
			                       + " inner join cv_set cs on cs.id = ci.CV_SET_ID "
			                       + " inner join CV_SET_SCHEDULE s2 on cs.id = s2.CV_SET_ID "
			                       + " inner join CV_SCHEDULE s1 on s1.SCHEDULE_ID = s2.CV_SCHEDULE_ID "
			                       + " inner join cv t on t.id = s1.CV_ID "
			                       + " inner join cv_ref_unit t1 on t.id=t1.cv_id " 
			                       + " inner join cv_unit t2 on t1.unit_id=t2.id "
			                       + " where t2.id = ? " ;
						List<QualityModel> guideList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qualityModel.getId()) ;						
						String guideIds = "" ;
						for (QualityModel gqmObj : guideList) {
							guideIds = gqmObj.getId() + "," + guideIds ;
						}
						if (guideList != null && guideList.size() > 0) {
							guideIds = guideIds.substring(0, guideIds.length() - 1) ;
						}
						
						sql = "delete from cv_unit_ref_quality where unit_id = ? " ;
						getJdbcTemplate().update(sql, qualityModel.getId()) ;
						
						sql = "select distinct qq.id from QM_QUALITY qq "
							+ " inner join QM_USER_IMAGE qi on qi.TYPEID = qq.TYPEID "
							+ " inner join CV_SET_USER_IMAGE ci on ci.USERIMAGE_ID = qi.id "
							+ " inner join cv_set cs on cs.id = ci.CV_SET_ID "
							+ " inner join CV_SET_SCHEDULE s2 on cs.id = s2.CV_SET_ID "
							+ " inner join CV_SCHEDULE s1 on s1.SCHEDULE_ID = s2.CV_SCHEDULE_ID "
							+ " inner join cv t on t.id = s1.CV_ID "
							+ " inner join cv_ref_unit t1 on t.id=t1.cv_id " 
							+ " inner join cv_unit t2 on t1.unit_id=t2.id "
							+ "where t2.id = ? " ;
						List<QualityModel> qmLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qualityModel.getId()) ;
						for (QualityModel qmLevelOneObj :qmLevelOneList) {
							String sql1 = "" ;	
							boolean runFlag1 = true ;
							if (haveBingZhongFlag) { //病种有数据
								if (qmLevel1MapByBZall.containsKey(qmLevelOneObj.getId())) {
									sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,LEVEL,state) VALUES (?,?,1,1)" ;
								} else {
									sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,LEVEL,state) VALUES (?,?,1,0)" ;
								}
							} else {
								if (qmLevel1MapByZTall.containsKey(qmLevelOneObj.getId())) {
									sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,LEVEL,state) VALUES (?,?,1,1)" ;									
								} else {//else runFlag1 = false ;
                                                                    sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,LEVEL,state) VALUES (?,?,1,0)" ;
                                                                }
							}
							
							if (runFlag1) {
								getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelOneObj.getId()) ;
								sql = "select distinct qq.id from QM_QUALITY qq where PARENTID = ?" ;
								List<QualityModel> qmLevelTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qmLevelOneObj.getId()) ;
								for (QualityModel qmLevelTwoObj : qmLevelTwoList) {
									boolean runFlag2 = true ;
									if (haveBingZhongFlag) { //素材的病种有数据
										if (qmLevel2MapByBZall.containsKey(qmLevelTwoObj.getId())) {
											sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,2,1)" ;
										} else {
											sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,2,0)" ;
										}
									} else {
										if (qmLevel2MapByZTall.containsKey(qmLevelTwoObj.getId())) {
											sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,2,1)" ;									
										} else {//else runFlag2 = false ;
                                                                                    sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,2,0)" ;
                                                                                }
									}
									if (runFlag2) {
										getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelTwoObj.getId(),qmLevelOneObj.getId()) ;
										if (!guideIds.equals("")) {
											sql = "select distinct id from QM_GUIDE where PARENT_ID in (" + guideIds + ") and QUALITY_ID = ? order by id desc " ;
											List<QualityModel> qmLevelThreeList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qmLevelTwoObj.getId()) ;
											for (QualityModel qmLevelThreeObj : qmLevelThreeList) {
												if (qmLevel3MapByBZall.containsKey(qmLevelThreeObj.getId())) {
													sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,3,1)" ;
												} else {
													sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,3,0)" ;
												}												
												getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelThreeObj.getId(),qmLevelTwoObj.getId()) ;
												
												sql = "select distinct id from QM_GUIDE where PARENT_ID = ? " ;
												List<QualityModel> qmLevelFourList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qmLevelThreeObj.getId()) ;
												for (QualityModel qmLevelFourObj : qmLevelFourList) {
													if (qmLevel4MapByBZall.containsKey(qmLevelFourObj.getId())) {
														sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,4,1)" ;
													} else {
														sql1 = "insert into cv_unit_ref_quality (UNIT_ID,PROP_ID,parent_prop_id,LEVEL,state) VALUES (?,?,?,4,0)" ;
													}
													
													getJdbcTemplate().update(sql1, qualityModel.getId(),qmLevelFourObj.getId(),qmLevelThreeObj.getId()) ;
												}														
											}
										}
									}																																																		
							    }
							}							
						}
						return true ;
					}										
				} else {
					return this.insertCvUnitRefQualityByGroupClass(qualityModel) ;
				}				
			}			
		}		
		return false ;
	}
	
}
