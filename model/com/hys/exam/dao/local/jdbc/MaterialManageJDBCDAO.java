package com.hys.exam.dao.local.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.MaterialManageDAO;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.model.MaterialProp;
import com.hys.exam.utils.StringUtils;

public class MaterialManageJDBCDAO extends BaseDao implements
	MaterialManageDAO {

	@SuppressWarnings("unchecked")
	public List<MaterialInfo> getMaterialList(MaterialInfo material, String[] upload_date, String[] deli_date, int orderBy) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from material_info where id>0 ");
		List list = new ArrayList();
		
		if(null != material.getId() && material.getId() != 0){
			sql.append(" and id = ? ");
			list.add(material.getId());
		}
		if(!StringUtils.checkNull(material.getCode())){
			sql.append("%"+" and code like ? "+"%");
			list.add(material.getCode());
		}
		if(!StringUtils.checkNull(material.getName())){
			sql.append(" and name like ? ");
			list.add("%"+material.getName()+"%");
		}
		if(!StringUtils.checkNull(material.getSurname())){
			sql.append(" and surname like ? ");
			list.add("%"+material.getSurname()+"%");
		}
		if(null != material.getType() && 0 != material.getType()){
			sql.append(" and type = ? ");
			list.add(material.getType());
		}
		if(!StringUtils.checkNull(material.getFormat())){
			sql.append(" and format like ? ");
			list.add("%"+material.getFormat()+"%");
		}
		if(!StringUtils.checkNull(material.getFileName())){
			sql.append(" and fileName like ? ");
			list.add("%"+material.getFileName()+"%");
		}
		if(!StringUtils.checkNull(material.getDuration())){
			sql.append(" and duration like ? ");
			list.add("%"+material.getDuration()+"%");
		}
		if(!StringUtils.checkNull(material.getSavePath())){
			sql.append(" and savePath like ? ");
			list.add("%"+material.getSavePath()+"%");
		}
		if(null != material.getFps() && 0 != material.getFps()){
			sql.append(" and fps = ? ");
			list.add(material.getFps());
		}
		if(!StringUtils.checkNull(material.getResolution())){
			sql.append(" and resolution like ? ");
			list.add("%"+material.getResolution()+"%");
		}		
		if(null != material.getNational_state() && 0 != material.getNational_state()){
			sql.append(" and national_state = ? ");
			list.add(material.getNational_state());
		}
		if(!StringUtils.checkNull(material.getSummary())){
			sql.append(" and summary like ? ");
			list.add("%"+material.getSummary()+"%");
		}
		if(!StringUtils.checkNull(material.getOtherSource())){
			sql.append(" and other_source like ? ");
			list.add("%"+material.getOtherSource()+"%");
		}
		if(!StringUtils.checkNull(material.getExpertName())){
			sql.append(" and expertName like ? ");
			list.add("%"+material.getExpertName()+"%");
		}
		
		if (null != material.getCognize() && material.getCognize() != 0) {
			sql.append(" and cognize=? ");
			list.add(material.getCognize());
		}
		
		if(null != material.getState() && 0 != material.getState()){
			sql.append(" and state = ? ");
			list.add(material.getState());
		}
		
		if(!StringUtils.checkNull(upload_date[0])){
			sql.append(" and upload_date >= ? ");
			list.add(Date.valueOf(upload_date[0]));
		}
		if(!StringUtils.checkNull(upload_date[1])){
			sql.append(" and upload_date <= ? ");
			Long time = Date.valueOf(upload_date[1]).getTime() + 24*60*60*1000;
			list.add(new Date(time));
		}

		if(!StringUtils.checkNull(deli_date[0])){
			sql.append(" and deli_date >= ? ");
			list.add(Date.valueOf(deli_date[0]));
		}
		if(!StringUtils.checkNull(deli_date[1])){
			sql.append(" and deli_date <= ? ");
			Long time = Date.valueOf(deli_date[1]).getTime() + 24*60*60*1000;
			list.add(new Date(time));
		}

		
		if(!StringUtils.checkNull(material.getDeli_opinion())){
			sql.append(" and deli_opinion like ? ");
			list.add("%"+material.getDeli_opinion()+"%");
		}
		if(!StringUtils.checkNull(material.getDeli_man())){
			sql.append(" and deli_man = ? ");
			list.add(material.getDeli_man());
		}
						
		Map<String,List<MaterialProp>> questionPropMap = material.getProp_map();
		
		if(null != questionPropMap){
			int count = 0;
			if(questionPropMap.size() >0){
				sql.append(" and ( ");
			}
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp_sql = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<MaterialProp> propValList = (List<MaterialProp>) entry.getValue();
				
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						MaterialProp questProp = propValList.get(i);
						tmp_sql += questProp.getProp_val_id()+",";
					}
					tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
					if(count>0){
						sql.append(" and ");
					}
					sql.append(" id in (select material_id from material_prop_val where prop_id in ("+tmp_sql+"))");
					count++;
				}
				
			}
			if(questionPropMap.size()>0){
				sql.append(") ");
			}
		}
		
		if (orderBy == 1) sql.append("order by upload_date");
		else if (orderBy == 2) sql.append("order by deli_date");
		else sql.append("order by id");
		//else sql.append("order by code");
		
		
		List<MaterialInfo> materialInfoList =  getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(MaterialInfo.class));
		
		for(MaterialInfo materialInfo:materialInfoList){

			Map<String,List<MaterialProp>> propMap = new HashMap<String,List<MaterialProp>>();
		
			String t_sql = "select t1.material_id, t1.prop_id as prop_val_id, t2.type as prop_type, t2.name as prop_val_name from material_prop_val t1, exam_prop_val t2, SUB_SYS_PROP_VAL t3 where t1.prop_id= t3.id and t3.prop_val_id= t2.id and t1.material_id=?";
			List<MaterialProp> propValList = getJdbcTemplate().query(t_sql, ParameterizedBeanPropertyRowMapper.newInstance(MaterialProp.class), materialInfo.getId());
			
			for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				
				List<MaterialProp> pList = new ArrayList<MaterialProp>();
				for (MaterialProp prop : propValList) {
					if (entry.getKey().toString().equals(prop.getProp_type().toString()))
						pList.add(prop);
				}
				if (pList.size() > 0) propMap.put(entry.getKey().toString(), pList);
				
			}		
			
			t_sql = "select t1.material_id, t1.source_id as prop_val_id, t2.type as prop_type, t2.name as prop_val_name from material_source_val t1, exam_prop_val t2, SUB_SYS_PROP_VAL t3 where t1.source_id= t3.id and t3.prop_val_id= t2.id and t1.material_id=?";
			propValList = getJdbcTemplate().query(t_sql, ParameterizedBeanPropertyRowMapper.newInstance(MaterialProp.class), materialInfo.getId());
			
			List<MaterialProp> pList = new ArrayList<MaterialProp>();
			for (MaterialProp prop : propValList) {
				prop.setProp_type(Integer.parseInt(Constants.EXAM_PROP_SOURCE));
				pList.add(prop);
			}
			if (pList.size() > 0) propMap.put(Constants.EXAM_PROP_SOURCE, pList);
			
			materialInfo.setProp_map(propMap);
			
		}
		
		return materialInfoList;
	}

	@Override
	public MaterialInfo getMaterialInfo(Long id) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from material_info where id=? ");
		MaterialInfo materialInfo = getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(MaterialInfo.class), id);
		
		Map<String,List<MaterialProp>> propMap = new HashMap<String,List<MaterialProp>>();
	
		String t_sql = "select t1.material_id, t1.prop_id as prop_val_id, t2.type as prop_type, t2.name as prop_val_name from material_prop_val t1, exam_prop_val t2, SUB_SYS_PROP_VAL t3 where t1.prop_id= t3.id and t3.prop_val_id= t2.id and t1.material_id=?";
		List<MaterialProp> propValList = getJdbcTemplate().query(t_sql, ParameterizedBeanPropertyRowMapper.newInstance(MaterialProp.class), materialInfo.getId());
		
		for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			
			List<MaterialProp> pList = new ArrayList<MaterialProp>();
			for (MaterialProp prop : propValList) {
				if (entry.getKey().toString().equals(prop.getProp_type().toString()))
					pList.add(prop);
			}
			if (pList.size() > 0) propMap.put(entry.getKey().toString(), pList);
			
		}		
		
		t_sql = "select t1.material_id, t1.source_id as prop_val_id, t2.type as prop_type, t2.name as prop_val_name from material_source_val t1, exam_prop_val t2, SUB_SYS_PROP_VAL t3 where t1.source_id= t3.id and t3.prop_val_id= t2.id and t1.material_id=?";
		propValList = getJdbcTemplate().query(t_sql, ParameterizedBeanPropertyRowMapper.newInstance(MaterialProp.class), materialInfo.getId());
		
		List<MaterialProp> pList = new ArrayList<MaterialProp>();
		for (MaterialProp prop : propValList) {
			prop.setProp_type(Integer.parseInt(Constants.EXAM_PROP_SOURCE));
			pList.add(prop);
		}
		if (pList.size() > 0) propMap.put(Constants.EXAM_PROP_SOURCE, pList);
		
		materialInfo.setProp_map(propMap);
		
		return materialInfo;
	}

	@Override
	public MaterialInfo getMaterialInfo(String materialName) {
		
		MaterialInfo materialInfo = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from material_info where name=? ");
		
		try {
			materialInfo = getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(MaterialInfo.class), materialName);
		} catch (Exception e) {}
		
		return materialInfo;
	}
	
	@Override
	public boolean addMaterialInfo(MaterialInfo material) {
		Long next_id = getNextId("material_info");
		material.setId(next_id);
		String ADD_SQL = "insert into material_info (id, name, surname, type, format, fileName, duration, savePath, fps, resolution, national_state, summary, other_source, expertName, state, upload_date, cognize) values (:id, :name, :surname, :type, :format, :fileName, :duration, :savePath, :fps, :resolution, :national_state, :summary, :otherSource, :expertName, :state, sysdate(), :cognize)";
		getSimpleJdbcTemplate().update(ADD_SQL, new BeanPropertySqlParameterSource(material));
		return true;
	}
	
	@Override
	public boolean addMaterialProp(MaterialInfo material) {

		String del_sql = "delete from MATERIAL_PROP_VAL WHERE MATERIAL_ID=" + material.getId();
		getJdbcTemplate().execute(del_sql);
		
		Map<String,List<MaterialProp>> propMap = material.getProp_map();
		List<Long> ids = new ArrayList<Long>();
		List<Long> src_ids = new ArrayList<Long>();
		
		
		if (propMap != null && !propMap.isEmpty()) {
			
			for (Iterator iter = Constants.getPropNameAll().entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (propMap.get(entry.getKey()) == null) continue;
				for (int i=0; i<propMap.get(entry.getKey()).size(); i++) {
					if (entry.getKey().equals(Constants.EXAM_PROP_SOURCE))
						src_ids.add(propMap.get(entry.getKey()).get(i).getProp_val_id());
					else
						ids.add(propMap.get(entry.getKey()).get(i).getProp_val_id());
				}
			}	
			if(ids.size() > 0){
				for (int i=0; i<ids.size(); i++) {
					StringBuffer sql = new StringBuffer();
					sql.append("insert into material_prop_val (material_id, prop_id) values (");
					sql.append(material.getId());
					sql.append(",");
					
					try{
						sql.append(ids.get(i));
						sql.append(")");
						getJdbcTemplate().execute(sql.toString());
					} catch (Exception e){}
				}
			}
			if(src_ids.size() > 0){
				for (int i=0; i<src_ids.size(); i++) {
					StringBuffer sql = new StringBuffer();
					sql.append("insert into material_source_val (material_id, source_id) values (");
					sql.append(material.getId());
					sql.append(",");
					
					try{
						sql.append(src_ids.get(i));
						sql.append(")");
						getJdbcTemplate().execute(sql.toString());
					} catch (Exception e){}
				}
			}
		}	
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean updateMaterialInfo(MaterialInfo material) {
	
		StringBuffer sql = new StringBuffer();
		sql.append("update material_info set ");
		sql.append("deli_date = sysdate() ");
		List list = new ArrayList();
		
		if(!StringUtils.checkNull(material.getCode())){
			sql.append(", code = ? ");
			list.add(material.getCode());
		}
		if(!StringUtils.checkNull(material.getName())){
			sql.append(", name = ? ");
			list.add(material.getName());
		}
		if(!StringUtils.checkNull(material.getSurname())){
			sql.append(", surname = ? ");
			list.add(material.getSurname());
		}
		if(null != material.getType()  && 0 != material.getType()){
			sql.append(", type = ? ");
			list.add(material.getType());
		}
		if(!StringUtils.checkNull(material.getFormat())){
			sql.append(", format = ? ");
			list.add(material.getFormat());
		}
		if(!StringUtils.checkNull(material.getFileName())){
			sql.append(", fileName = ? ");
			list.add(material.getFileName());
		}
		if(!StringUtils.checkNull(material.getDuration())){
			sql.append(", duration = ? ");
			list.add(material.getDuration());
		}
		else
		{
			sql.append(", duration = ? ");
			list.add("");
		}
		if(!StringUtils.checkNull(material.getSavePath())){
			sql.append(", savePath = ? ");
			list.add(material.getSavePath());
		}
		if(null != material.getFps() && 0 != material.getFps()){
			sql.append(", fps = ? ");
			list.add(material.getFps());
		}
		else
		{
			sql.append(", fps = ? ");
			list.add(0);
		}
		if(!StringUtils.checkNull(material.getResolution())){
			sql.append(", resolution = ? ");
			list.add(material.getResolution());
		}		
		if(null != material.getNational_state()){
			sql.append(", national_state = ? ");
			list.add(material.getNational_state());
		}
		if(!StringUtils.checkNull(material.getSummary())){
			sql.append(", summary = ? ");
			list.add(material.getSummary());
		}
		if(!StringUtils.checkNull(material.getOtherSource())){
			sql.append(", other_source = ? ");
			list.add(material.getOtherSource());
		}
		if(!StringUtils.checkNull(material.getExpertName())){
			sql.append(", expertName = ? ");
			list.add(material.getExpertName());
		}
		if(null != material.getState() && 0 != material.getState()){
			sql.append(", state = ? ");
			list.add(material.getState());
		}
		
		if(null != material.getCognize() && 0 != material.getCognize()){
			sql.append(", cognize = ? ");
			list.add(material.getCognize());
		}
		/*
		if(!StringUtils.checkNull(material.getDeli_opinion())){
			sql.append(", deli_opinion = ? ");
			list.add(material.getDeli_opinion());
		}
		if(!StringUtils.checkNull(material.getDeli_man())){
			sql.append(", deli_man = ? ");
			list.add(material.getDeli_man());
		}
		*/
		sql.append(", deli_opinion = ? , deli_man = ?");
		list.add("");
		list.add("");

		sql.append(", id = id where id = ?");
		list.add(material.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		return true;
	}

	@Override
	public boolean updateMaterialProp(MaterialInfo material) {
		
		String del_sql = "delete from MATERIAL_PROP_VAL WHERE MATERIAL_ID=" + material.getId();
		getJdbcTemplate().execute(del_sql);
		
		del_sql = "delete from MATERIAL_SOURCE_VAL WHERE MATERIAL_ID=" +  + material.getId();
		getJdbcTemplate().execute(del_sql);
		
		addMaterialProp(material);
		
		return true;
	}

	
	@Override
	public boolean deleteMaterialInfo(Long id) {

		String SQL_DEL = "delete from material_prop_val where material_id = ?";
		getJdbcTemplate().update(SQL_DEL,id);
		
		SQL_DEL = "delete from material_source_val where material_id = ?";
		getJdbcTemplate().update(SQL_DEL,id);
		
		SQL_DEL = "delete from material_info where id = ?";
		getJdbcTemplate().update(SQL_DEL,id);
		return true;
	}

	@Override
	public boolean updateStatesOfMaterial(Long[] ids, int state, String opinion, String userName) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update material_info set ");
		sql.append("state = ");
		sql.append(state);
		sql.append(", deli_date = sysdate() ");
		sql.append(", deli_man = ?");
		if (opinion != null ){
			sql.append(", DELI_OPINION='").append(opinion).append("'");
		}
		sql.append(", id = id where id in (");
		
		for (int i=0; i<ids.length; i++) {
			if (i > 0) sql.append(",");
			sql.append(ids[i]);
		}
		
		sql.append(")");
		
		getSimpleJdbcTemplate().update(sql.toString(), userName);
		return true;
	}

	@Override
	public List<MaterialInfo> getMaterialList(MaterialInfo material) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getMaterialPropById(Long id) {
		String sql = "select prop_id as value from material_prop_val where material_id = ?";
		return getJdbcTemplate().queryForList(sql, Long.class, id);
	}

	@Override
	public void updateMaterialSourceId(Long oldId, Long newId) {
		try{
			String sql = "update material_source_val set source_id=? where source_id=?";
			getJdbcTemplate().update(sql, newId, oldId);
		}catch(Exception e){;}
	}
}
