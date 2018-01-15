package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPropListByDirectDAO;
import com.hys.exam.dao.local.ExamPropValDAO;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamMajorOrder;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropExport;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.model.ExamRelationProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

public class ExamPropListByDirectJDBCDAO extends BaseDao
		implements ExamPropListByDirectDAO {
	
	public Map<String, List<ExamPropVal>> getSysPropValBySysId() {
		
		//String sql = "select t.type, t2.id, t2.name,t2.code from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id and t.relation = 0 and t.up_level is null";
		String sql = "select t.type, t2.id, t2.name,t2.code,t1.id as refId from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id";

		Map<String, List<ExamPropVal>> m = new HashMap<String, List<ExamPropVal>>();

		List<ExamPropVal> propValList = getJdbcTemplate().query(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPropVal.class));
		
		for (ExamPropVal propval : propValList) {
			if (m.containsKey(propval.getType().toString())) {
				List<ExamPropVal> list = m.get(propval.getType().toString());
				list.add(propval);
			} else {
				List<ExamPropVal> lists = new ArrayList<ExamPropVal>();
				lists.add(propval);
				m.put(propval.getType().toString(), lists);
			}
		}
		
		return m;
	}

	@Override
	public List<ExamProp> getPropListByType(ExamProp prop) {

		String sql = "select t.*, t.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.type = ? and t.name like ? order by t.code";
		
		String searchName = prop.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamProp> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),prop.getType(), searchName);
		
		for(ExamProp p :list){
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
		}
		
		return list;
	}

	@Override
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery) {
		
		ExamReturnProp r_prop = new ExamReturnProp();
		
		String sql_prop_val_ref = "select DISTINCT(r.prop_val1) from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val1 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.id = ? order by t.id";
		
		Long prop_val_ref = -1L;
		
		try {
			prop_val_ref = getJdbcTemplate().queryForLong(sql_prop_val_ref, propQuery.getSysPropId());
		} catch(Exception e) {
			r_prop.setReturnList(new ArrayList<ExamProp>());
			r_prop.setTotal_count(0);
			return r_prop;
		}
				
		String sql = "select t.*, t.id as prop_val_id,p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val2 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and r.prop_val1 = ? and t.name like ? order by t.id";

		String sql_size = "select count(1) from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val2 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and r.prop_val1 = ? and t.name like ?";

		String searchName = propQuery.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamProp> returnList = getJdbcTemplate().query(PageUtil.getPageSql(sql, propQuery.getPageNo(), propQuery
				.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop_val_ref, searchName);
		
		for(ExamProp p :returnList){
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
			
			String propSql = "select i.* from EXAM_ICD_PROP i LEFT JOIN EXAM_ICD_PROP_VAL p on i.ID=p.ICDID where p.PROPID=?";
			p.setIcdList(getJdbcTemplate().query(propSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class), p.getProp_val_id()));
		}
		
		r_prop.setReturnList(returnList);
		r_prop.setTotal_count(getJdbcTemplate().queryForInt(sql_size,propQuery.getSysPropId(), searchName));
		return r_prop;
	}

	public boolean existPropCode(ExamProp prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;
		String getcode = "select count(1) from exam_prop_val where ((code='" + prop.getCode() + "') or (name='"+prop.getName()+"' and type='"+prop.getType()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}

	@Override
	public List<ExamPropType> getExamPropTypeList() {
		String sql = "select * from exam_prop_type t order by t.prop_type";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropType.class));
	}

	public List<ExamProp> getNextLevelProp(Long propValId) {

		String sql = "select v.* from sub_sys_prop_val t, prop_val_ref r, sub_sys_prop_val tt,exam_prop_val v where t.prop_val_id = ? and r.prop_val1 = t.id and tt.id = r.prop_val2 and v.id = tt.prop_val_id";
		
		return getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),
				propValId);
	}

	public ExamProp getSysPropVal(Long id) {
		String sql = "select t.*,v.id as prop_val_id from exam_prop_val t,sub_sys_prop_val v where t.id = v.prop_val_id and t.id = ?";
		return getJdbcTemplate().queryForObject(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamProp.class), id);
	}

	@SuppressWarnings("unchecked")
	public List<ExamICD> getIcdListByType(ExamICD prop) {
		String sql = "select * from exam_icd_prop where type=? and name like ?";
		String searchName = prop.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List array = new ArrayList();
		array.add(prop.getType());
		array.add(searchName);
		
		if (prop.getId()!=null && prop.getId() !=0){
			sql+= " and id=?";
			array.add(prop.getId());
		}
		sql += " order by code";
		
		//取得关联学科列表
		List<ExamICD> list = getJdbcTemplate().query(sql, array.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class));
		for (ExamICD s: list){
			String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXAM_ICD_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where p.id>0 and e.ICDID=?";
			List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setProp(propList);

		}
		return list;
	}

	public boolean existIcdVal(ExamICD prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;
		String getcode = "select count(1) from exam_icd_prop where ((code='" + prop.getCode() + "') or (name='"+prop.getName()+"' and type='"+prop.getType()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	@Override
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop) {
		String sql = "select * from exam_source_type where id!=0 and type_name like ? order by code";
		
		String searchName = prop.getType_name();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamSourceType> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamSourceType.class), searchName);
		return list;
	}

	public boolean existSourceType(ExamSourceType prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getType_name() == null) return false;
		String getcode = "select count(1) from exam_source_type where ((code='" + prop.getCode() + "') or (type_name='"+prop.getType_name()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	@Override
	public List<ExamSource> getSourceValList(ExamSource prop) {
		String sql = "select t.*, s.type_name as typename from exam_source_val t left join exam_source_type s on t.type=s.id where t.name like ? "; // and s.type_name like ? 
		
		if (prop.getId()!=null && prop.getId() > 0)
			sql += " and t.id=" + prop.getId();
		
		if (prop.getType()!=null && prop.getType() > 0)
			sql += " and t.type=" + prop.getType();
		
		StringBuffer propsql = new StringBuffer();
		if(!StringUtils.checkNull(prop.getPropIds())){
			String ids = prop.getPropIds().replace('"', ' ');
			propsql.append(" and t.id in (select sourceid from EXAM_SOURCE_PROP_VAL where PROPID in (").append(ids).append(")) ");
			sql += propsql.toString();
		}
		
		sql += " order by t.code";

		String searchName = prop.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
/*		String searchTypeName = prop.getTypeName();
		if (searchTypeName == null) searchTypeName = "%";
		else searchTypeName = "%" + searchTypeName + "%";
		
*/		//取得所有的所属学科和主题
		List<ExamSource> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamSource.class), searchName);
		for (ExamSource s: list){
			String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXAM_SOURCE_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where p.id>0 and e.SOURCEID=? and p.type<=5";
			List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setProp(propList);

			String GET_ZHUTI = "SELECT p.*, s.id as prop_val_id FROM EXAM_SOURCE_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where p.id>0 and e.SOURCEID=? and p.type=7";
			propList = getJdbcTemplate().query(GET_ZHUTI, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setZhuti(propList);

		}
		return list;

	}

	@Override
	public List<ExamHospital> getHospitalList(ExamHospital host) {
		String sql = "select t.* from exam_hospital t where ";
		
		List plist = new ArrayList();
		if (host.getPropId() != null){
			sql += "t.propid = ? and ";
			plist.add(host.getPropId());
		}

		String searchName = host.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";

		sql += "t.name like ? order by t.id";
		plist.add(searchName);
		
		List<ExamHospital> list =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),plist.toArray());

		try{
		for(ExamHospital e: list){
			String GET_REGION = "select t.*, r.* from EXAM_PROP_VAL t left join SUB_SYS_PROP_VAL s on t.id=s.PROP_VAL_ID LEFT JOIN PROP_VAL_REF r on r.prop_val2=s.id where s.id=?";
			ExamProp prop;
			prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), e.getRegionId());
			
			if (prop.getType() == 22){
				e.setRegion3(prop.getName());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 21){
				e.setRegion2(prop.getName());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 20){
				e.setRegion1(prop.getName());
			}
		}
		}catch(Exception e){;}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from exam_major_order t where t.id<>0");

		List list = new ArrayList();
		if(!StringUtils.checkNull(major.getMajor())){
			sql.append(" and t.major like '%").append(major.getMajor()).append("%'");
		}
		if(!StringUtils.checkNull(major.getHospital())){
			sql.append(" and t.hospital like '%").append(major.getHospital()).append("%'");
		}
		if(major.getClassId() != null && major.getClassId() != 0 ){
			sql.append(" and t.classid=?");
			list.add(major.getClassId());
		}
		
		return getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExamMajorOrder.class));
	}


	@Override
	public Long getParentPropId(Long id) {
		String sql = "select prop_val1 from prop_val_ref where prop_val2=?";
		Long parentId = 0L;
		try{
			parentId = getJdbcTemplate().queryForLong(sql, id);
		}catch(Exception e){;}
		return parentId;
	}
	
	private  int getQuestionNumByPropId(Long prop_val_id,int type){
		String sql_p1 = "select count(distinct t.id) from exam_question t, exam_question_study1 p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p2 = "select count(distinct t.id) from exam_question t, exam_question_study2 p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p3 = "select count(distinct t.id) from exam_question t, exam_question_study3 p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p4 = "select count(distinct t.id) from exam_question t, exam_question_unit p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p5 = "select count(distinct t.id) from exam_question t, exam_question_point p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		int num = 0;
		
		switch (type) {
			case 1:
				num = getJdbcTemplate().queryForInt(sql_p1, prop_val_id);
				break;
			case 2:
				num = getJdbcTemplate().queryForInt(sql_p2, prop_val_id);
				break;
			case 3:
				num = getJdbcTemplate().queryForInt(sql_p3, prop_val_id);
				break;
			case 4:
				num = getJdbcTemplate().queryForInt(sql_p4, prop_val_id);
				break;
			case 5:
				num = getJdbcTemplate().queryForInt(sql_p5, prop_val_id);
				break;
		}
		
		return num;
	}
	
}
