package com.hys.exam.dao.local.jdbc;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPropValDAO;
import com.hys.exam.model.*;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.PinyinUtil;
import com.hys.exam.utils.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import java.util.*;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropValJDBCDAO extends BaseDao
		implements ExamPropValDAO {
	
	private static final String GETBASEPROPVAL_SQL = "select t2.id, t2.type, t2.name, t2.code, t2.c_type, t1.id as refId from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id and t2.type =? and t2.c_type = ? ";
	private static final String GETBASEPROPVAL_SQL2 = "select c.*,d.id as refId from sub_sys_prop_val b, exam_prop_val c,sub_sys_prop_val d where d.prop_val_id = c.id and b.prop_val_id = c.id and b.id in(select a.prop_val2 from PROP_VAL_REF a where a.prop_val1 =?)　and c.c_type = ?";

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

	@SuppressWarnings("unchecked")
	public List<ExamPropValTemp> getBasePropVal(Integer type) {
		
		List <ExamPropValTemp> rlist = new ArrayList<ExamPropValTemp>();
		List<ExamPropVal> list = null;
		

		list =  getJdbcTemplate().query(GETBASEPROPVAL_SQL, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class), 1, type);

		ExamPropValTemp pvt = null;
		
		if(null != list && list.size()>0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ExamPropVal name = (ExamPropVal) iterator.next();
				pvt = new ExamPropValTemp();
				pvt.setId(name.getId().toString());
				pvt.setName(name.getName());
				List<ExamPropValTemp>subItem = this.getExamPropVal(type,name.getRefId());
				pvt.setSubItems(subItem);
				
				rlist.add(pvt);
			}
		}
		return rlist;
	}
	
	
	@SuppressWarnings("unchecked")
	private List<ExamPropValTemp> getExamPropVal(Integer type, Long refId) {
		
		List<ExamPropValTemp>subItem = new ArrayList<ExamPropValTemp>();
		List<ExamPropVal> list2 = getJdbcTemplate().query(GETBASEPROPVAL_SQL2, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class), refId, type);
		ExamPropValTemp pvt2 = null;
		if(null != list2 && list2.size()>0) {
			for (Iterator iterator2 = list2.iterator(); iterator2.hasNext();) {
				ExamPropVal name2 = (ExamPropVal) iterator2.next();
				pvt2 = new ExamPropValTemp();
				pvt2.setId(name2.getId().toString());
				pvt2.setName(name2.getName());
				
				if(name2.getType()<6) {
					pvt2.setSubItems(this.getExamPropVal(type,name2.getRefId()));
				}
				
				subItem.add(pvt2);
			}
		}
		
		return subItem;
		
	}

	@Override
	public List<ExamPropVal> getBaseRelPorp(int type) {
		
		String sql_ = "select v.* from sub_sys_prop_val t,exam_prop_val v where t.sys_prop_id=1 and v.id =t.prop_val_id order by v.id desc";
		
		List<ExamPropVal> list = getJdbcTemplate().query(sql_, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class));
		
		for(int j=0;j<list.size();j++){

			
			ExamPropVal v = list.get(j);

			List<ExamPropExport> eplist =  new ArrayList<ExamPropExport>();
			if(type==0){
				eplist = getRelPorp(v.getId());
			}else{
				eplist = getSubSysRelPorp(v.getId());
			}

			v.setRef(eplist);
		}
		return list;
	}
	
	private List<ExamPropExport> getRelPorp(Long propId){
		StringBuffer sql = new StringBuffer();
		sql.append("select v.name||'('||v.id||')' as p1, v1.name||'('||v1.id||')' as p2, v2.name||'('||v2.id||')' as p3, v3.name||'('||v3.id||')' as p4, v4.name||'('||v4.id||')' as p5");
		sql.append(" from sub_sys_prop_val l ");
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");
		sql.append("join prop_val_ref f1 on l1.id = f1.prop_val1 ");
		sql.append("join sub_sys_prop_val l2 on f1.prop_val2 = l2.id ");
		sql.append("join prop_val_ref f2 on l2.id = f2.prop_val1 ");
		sql.append("join sub_sys_prop_val l3 on f2.prop_val2 = l3.id ");
		sql.append("join prop_val_ref f3 on l3.id = f3.prop_val1 ");
		sql.append("join sub_sys_prop_val l4 on f3.prop_val2 = l4.id ");
		sql.append("join exam_prop_val v on l.prop_val_id = v.id ");
		sql.append("join exam_prop_val v1 on l1.prop_val_id = v1.id ");
		sql.append("join exam_prop_val v2 on l2.prop_val_id = v2.id ");
		sql.append("join exam_prop_val v3 on l3.prop_val_id = v3.id ");
		sql.append("join exam_prop_val v4 on l4.prop_val_id = v4.id ");
		sql.append("where v.id = ?");
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamPropExport.class),propId);
	}
	
	private List<ExamPropExport> getSubSysRelPorp(Long propId){
		StringBuffer sql = new StringBuffer();
		sql.append("select v.name||'#'||v.id as p1, v1.name||'#'||v1.id as p2, v2.name||'#'||v2.id as p3, v3.name||'#'||v3.id as p4, v4.name||'#'||v4.id as p5");
		sql.append(" from sub_sys_prop_val l ");
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");
		sql.append("join prop_val_ref f1 on l1.id = f1.prop_val1 ");
		sql.append("join sub_sys_prop_val l2 on f1.prop_val2 = l2.id ");
		sql.append("join prop_val_ref f2 on l2.id = f2.prop_val1 ");
		sql.append("join sub_sys_prop_val l3 on f2.prop_val2 = l3.id ");
		sql.append("join prop_val_ref f3 on l3.id = f3.prop_val1 ");
		sql.append("join sub_sys_prop_val l4 on f3.prop_val2 = l4.id ");
		sql.append("join exam_prop_val v on l.prop_val_id = v.id ");
		sql.append("join exam_prop_val v1 on l1.prop_val_id = v1.id ");
		sql.append("join exam_prop_val v2 on l2.prop_val_id = v2.id ");
		sql.append("join exam_prop_val v3 on l3.prop_val_id = v3.id ");
		sql.append("join exam_prop_val v4 on l4.prop_val_id = v4.id ");
		sql.append("where v.id = ? order by v.id, v1.id, v2.id, v3.id, v4.id");
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamPropExport.class),propId);
	}	
	
	@Override
	public List<ExamPropVal> getCityList() {
		
		String sql = "select*from exam_prop_val where type=21 ORDER BY id DESC";
		
		List<ExamPropVal> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class));
		
		
		return list;
	}

	@Override
	public List<ExamProp> getPropListByType(ExamProp prop) {
		
		String sql = "select t.*, cast(t.code as signed) as codenum, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type";
		
		List<Object> params = new ArrayList<Object>();
		
		if (prop.getType()!=null){
//			sql += " AND (t.type = ? or t.type=21)";
			sql += " and t.type = ?";
			params.add(prop.getType());
		}
		if (prop.getC_type()!=null){
			sql += " and t.c_type = ?";
			params.add(prop.getC_type());
		}
		if (prop.getName()!=null && !prop.getName().equals("")){
			sql += " and t.name like ?";
			params.add("%" + prop.getName() + "%");
		}
		if (prop.getExt_type()!=null){
			sql += " and t.ext_type = ?";
			params.add(prop.getExt_type());
		}
		//2017.12.20 xh 学员修改页面传的值
		if(prop.getImg_type() != null && prop.getImg_type() > 0) {
			sql += " and t.img_type = ?";
			params.add(prop.getImg_type());
		}
		if (prop.getSort()!=null && !prop.getSort().equals("")){
			sql += " order by " + prop.getSort();
			if (prop.getDir() != null)
				sql += " " + prop.getDir();
		}
		else
			sql += " order by t.id desc ";
		
		List<ExamProp> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), params.toArray());
		
		for(ExamProp p :list){
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
		}
		
		return list;
	}

	@Override
	public List<ExamProp> getPropListUserImage(ExamProp prop,String flag) {
		String sql = "select t.*, cast(t.code as signed) as codenum, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type ";
		List<Object> params = new ArrayList<Object>();
		if (prop.getType()!=null){
			sql += " and t.type = ?";
			params.add(prop.getType());
		}
		if (prop.getC_type()!=null){
			sql += " and t.c_type = ?";
			params.add(prop.getC_type());
		}
		if (prop.getName()!=null && !prop.getName().equals("")){
			sql += " and t.name like ?";
			params.add("%" + prop.getName() + "%");
		}
		if (prop.getExt_type()!=null){
			sql += " and t.ext_type = ?";
			params.add(prop.getExt_type());
		}
		
		if (prop.getSort()!=null && !prop.getSort().equals("")){
			sql += " order by " + prop.getSort();
			if (prop.getDir() != null)
				sql += " " + prop.getDir();
		}
		else
			sql += " order by t.id desc ";
		
		List<ExamProp> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), params.toArray());
		
		for(ExamProp p :list){
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery) {
		
		ExamReturnProp r_prop = new ExamReturnProp();
		String sql = "select t.*, cast(t.code as signed) as codenum, v.id as prop_val_id,p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val2 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and r.prop_val1 = ? and t.name like ? ";
		String sql_size = "select count(1) from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val2 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and r.prop_val1 = ? and t.name like ?";

		String searchName = propQuery.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		List list = new ArrayList();
		list.add(propQuery.getSysPropId());
		list.add(searchName);
		if (propQuery.getType()!=null && propQuery.getType() == 1){
			sql += " and t.ext_type = ?";
			sql_size += " and t.ext_type = ?";
			list.add(propQuery.getType());
		}
		

		
		if (propQuery.getSort()!=null && !propQuery.getSort().equals("")){
			sql += " order by " + propQuery.getSort();
			if (propQuery.getDir() != null)
				sql += " " + propQuery.getDir();
		}
		else
			sql += " order by t.id desc ";
		
		List<ExamProp> returnList = getJdbcTemplate().query(PageUtil.getPageSql(sql, propQuery.getPageNo(), propQuery
				.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), list.toArray());
		
		for(ExamProp p :returnList){
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
			
			String propSql = "select i.* from EXAM_ICD_PROP i LEFT JOIN EXAM_ICD_PROP_VAL p on i.ID=p.ICDID where p.PROPID=?";
			p.setIcdList(getJdbcTemplate().query(propSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class), p.getProp_val_id()));
		}
		
		r_prop.setReturnList(returnList);
		r_prop.setTotal_count(getJdbcTemplate().queryForInt(sql_size,list.toArray()));
		return r_prop;
	}

	public boolean existPropCode(ExamProp prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;

		String getcode = "select count(1) from exam_prop_val where ((code='" + prop.getCode() + "' and type='"+prop.getType()+"') or (name='"+prop.getName()+"' and type='"+prop.getType()+"')) and id<>"+id.toString();

		if (prop.getType() == null){
			List<ExamProp> oldProp = getJdbcTemplate().query("select * from exam_prop_val where id="+id, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
			if(oldProp.size()>0)
				prop.setType(oldProp.get(0).getType());
		}
		
		Long parentId = 0L;
		if (prop.getProp_val1() != null)
			parentId = prop.getProp_val1();
		if (parentId == 0L)
		if (prop.getType() != null){// && (prop.getType() == 20 || prop.getType() == 21 || prop.getType() == 22 )
			String getParent = "select t.*, cast(t.code as signed) as codenum, v.id as prop_val_id from exam_prop_val t, sub_sys_prop_val v where t.id = v.prop_val_id and v.id=(select r.PROP_VAL1 from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r where t.id = v.prop_val_id and v.id = r.prop_val2 and t.id=?)";
			List<ExamProp> parentProp = getJdbcTemplate().query(getParent, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), id);
			
			if (parentProp.size()>0){
				parentId = parentProp.get(0).getProp_val_id();
			}
		}
		if (parentId == 0L)
			getcode= "select count(1) from exam_prop_val t, sub_sys_prop_val v where t.id = v.prop_val_id and ((t.code='"+prop.getCode()+"' and t.type='"+prop.getType()+"') or (t.name='"+prop.getName()+"' and t.type='"+prop.getType()+"')) and t.id<>"+id.toString();
		else
			getcode= "select count(1) from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r where t.id = v.prop_val_id and v.id = r.prop_val2 and ((t.code='"+prop.getCode()+"' and t.type='"+prop.getType()+"' and r.PROP_VAL1='"+parentId+"') or (t.name='"+prop.getName()+"' and t.type='"+prop.getType()+"' and r.PROP_VAL1='"+parentId+"')) and t.id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	@Override
	public ExamProp addPropVal(ExamProp prop) throws Exception {
		if (existPropCode(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_prop_val");
		prop.setId(prop_id);
		if (prop.getType()<6)
			prop.setCode(PinyinUtil.generateCode(prop.getName(), prop_id.toString()));
		String ADD_PROP_VAL = "insert into exam_prop_val (id, type, name, code, c_type, ext_type, img_type) values (:id, :type, :name, :code, :c_type, :ext_type, :img_type)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));
		return prop;
	}

	@Override
	public void addRel(ExamProp prop) {
		String sql = "insert into prop_val_ref (prop_val1, prop_val2) values (:prop_val1, :prop_val2)";
		getSimpleJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(prop));
	}

	public ExamProp addSysPropVal(ExamProp prop) {
		prop.setProp_val_id(prop.getId());
		Long id = getNextId("sub_sys_prop_val");
		prop.setId(id);
		prop.setProp_val2(id);
		String sql = "insert into sub_sys_prop_val (id, sys_prop_id, prop_val_id) values (:id, :type, :prop_val_id)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(prop));
		return prop;
	}

	public boolean deletePropVal(ExamProp prop) {
		String SQL_DEL_EXAM_PROP_VAL = "delete from exam_prop_val where id = ?";
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from sub_sys_prop_val where prop_val_id = ?";
		String SQL_DEL_PROP_VAL_REF = "delete from prop_val_ref where prop_val2 = ?";
		String SQL_PROP_VAL_REF = "select count(1) from prop_val_ref x where x.prop_val1 = ?";
		
		// force delete
		if (prop.getType()!=null && prop.getType() == -100){
			
			try{
			//getJdbcTemplate().update("SET FOREIGN_KEY_CHECKS=0");
			
			getJdbcTemplate().update(SQL_DEL_PROP_VAL_REF,prop.getProp_val_id());
			//删除系统属性
			getJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL,prop.getId());
			//删除属性
			getJdbcTemplate().update(SQL_DEL_EXAM_PROP_VAL,prop.getId());
			
			//getJdbcTemplate().update("SET FOREIGN_KEY_CHECKS=1");
			
			return true;
			}
			catch(Exception e){return false;}
		}
		else{
		
		int size = getJdbcTemplate().queryForInt(SQL_PROP_VAL_REF, prop.getProp_val_id());
		
		//检查是否有下级关联
		if(size==0){
			//删除关联上下级关联
			getJdbcTemplate().update(SQL_DEL_PROP_VAL_REF,prop.getProp_val_id());
			//删除系统属性
			getJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL,prop.getId());
			//删除属性
			getJdbcTemplate().update(SQL_DEL_EXAM_PROP_VAL,prop.getId());
			return true;
		}else{
			return false;
		}
		}
	}

	@SuppressWarnings("unchecked")
	public void updatePropVal(ExamProp prop) throws Exception {
		if (existPropCode(prop, prop.getId())) throw new Exception();
		StringBuffer sql = new StringBuffer();
		if (prop.getType()<6)
			prop.setCode(PinyinUtil.generateCode(prop.getName(), prop.getId().toString()));
		sql.append("update exam_prop_val set ");
		List list = new ArrayList();
		if(!StringUtils.checkNull(prop.getName())){
			sql.append("name = ?,");
			list.add(prop.getName());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		if(prop.getC_type()!=null){
			sql.append("c_type = ?,");
			list.add(prop.getC_type());
		}
		if(prop.getExt_type()!=null){
			sql.append("ext_type = ?,");
			list.add(prop.getExt_type());
		}
		if(prop.getImg_type()!=null){
			sql.append("img_type = ?,");
			list.add(prop.getImg_type());
		}
		sql.append(" type = type where id = ?");
		list.add(prop.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

	}

	@Override
	public List<ExamPropType> getExamPropTypeList() {
		String sql = "select * from exam_prop_type t order by t.prop_type";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropType.class));
	}

	@Override
	public List<ExamRelationProp> getExamRelationPropAll() {
		StringBuffer sql = new StringBuffer();

		sql.append("select v.id as study1_prop_id, v1.id as study2_prop_id, v2.id as study3_prop_id, v3.id as unit_prop_id, v4.id as point_prop_id,");
		sql.append("v.name || '-' || v1.name || '-' || v2.name || '-' || v3.name || '-' || v4.name as relationPropName,");
		sql.append("v.id || '-' || v1.id || '-' || v2.id || '-' || v3.id || '-' || v4.id as relationPropId");
		       
		sql.append(" from sub_sys_prop_val l ");
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");
		sql.append("join prop_val_ref f1 on l1.id = f1.prop_val1 ");
		sql.append("join sub_sys_prop_val l2 on f1.prop_val2 = l2.id ");
		sql.append("join prop_val_ref f2 on l2.id = f2.prop_val1 ");
		sql.append("join sub_sys_prop_val l3 on f2.prop_val2 = l3.id ");
		sql.append("join prop_val_ref f3 on l3.id = f3.prop_val1 ");
		sql.append("join sub_sys_prop_val l4 on f3.prop_val2 = l4.id ");
		sql.append("join exam_prop_val v on l.prop_val_id = v.id ");
		sql.append("join exam_prop_val v1 on l1.prop_val_id = v1.id ");
		sql.append("join exam_prop_val v2 on l2.prop_val_id = v2.id ");
		sql.append("join exam_prop_val v3 on l3.prop_val_id = v3.id ");
		sql.append("join exam_prop_val v4 on l4.prop_val_id = v4.id ");		       
		sql.append("where exists (select 1 from exam_prop_val x where x.id = v.id and x.type = 1)");
		
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamRelationProp.class));
	}

	/**
	 * 计算试题属性总数
	 * @param prop_val_id 属性id
	 * @param type	属性类型
	 * @return
	 */
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

	public List<ExamProp> getNextLevelProp(Long propValId) {

		String sql = "select v.* from sub_sys_prop_val t, prop_val_ref r, sub_sys_prop_val tt,exam_prop_val v where t.prop_val_id = ? and r.prop_val1 = t.id and tt.id = r.prop_val2 and v.id = tt.prop_val_id";
		
		return getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),
				propValId);
	}

	public ExamProp getSysPropVal(Long id) {
		String sql = "select t.*,v.id as prop_val_id from exam_prop_val t,sub_sys_prop_val v where t.id = v.prop_val_id and t.id = ?";
		if(id != null && !id.equals(0L))
		{
			List<ExamProp> prop = getJdbcTemplate().query(
					sql,
					ParameterizedBeanPropertyRowMapper
							.newInstance(ExamProp.class), id);
			if(prop != null && prop.size() != 0)
			{
				return prop.get(0);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public void updateMoveSysPropVal(Long targetId, Long currentId) {
		String del_sql = "delete from prop_val_ref where prop_val2 = ?";
		//删除要移动属性与上一级的关系
		getJdbcTemplate().update(del_sql,currentId);
		
		//添加要移动属性与新上一级的关系
		ExamProp prop = new ExamProp();
		prop.setProp_val1(targetId);
		prop.setProp_val2(currentId);
		addRel(prop);
	}

	/**
	 * 查询ICD属性
	 */
	@SuppressWarnings("unchecked")
	public void getIcdListByType(Page<ExamICD> page, ExamICD prop) {
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
		
		if (page.getSortCriterion()!=null && !page.getSortCriterion().equals(""))
		{
			String dir = " asc";
			if (page.getSortDirection() == SortOrderEnum.DESCENDING)
				dir = " desc";
			sql += " order by " + page.getSortCriterion() + dir;
		}
		else
			sql += " order by id desc";

		//取得关联学科列表
		List<ExamICD> list = getJdbcTemplate().query(PageUtil.getPageSql(sql, page.getCurrentPage(), page
				.getPageSize()), array.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class));
		for (ExamICD s: list){
			String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXAM_ICD_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where p.id>0 and e.ICDID=?";
			List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setProp(propList);
		}
		Integer count = getCount(sql.toString(), array.toArray()) ;
		page.setList(list);
		page.setCount(count);
	}

	public int getCount(String sql, Object... params) {
		return getJdbcTemplate().queryForInt(getCountSql(sql), params);
	}

	public String getCountSql(String sql) {
		return new StringBuilder("select count(1) as count from (").append(sql)
				.append(") c").toString();
	}

	public boolean existIcdVal(ExamICD prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;
		String getcode = "select count(1) from exam_icd_prop where ((code='" + prop.getCode() + "') or (name='"+prop.getName()+"' and type='"+prop.getType()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	/**
	 * 添加ICD属性
	 * @throws Exception 
	 */
	@Override
	public ExamICD addIcdVal(ExamICD prop) throws Exception {
		if (existIcdVal(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_icd_prop");
		prop.setId(prop_id);
//		prop.setCode(PinyinUtil.generateCode(prop.getName(), prop_id.toString()));
		String ADD_PROP_VAL = "insert into exam_icd_prop (id, type, name, code, name_en, hint) values (:id, :type, :name, :code, :nameEn, :hint)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));

		//添加关联学科
		try{
		String[] propIds = prop.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_icd_prop_val");
			String ADD_PROP = "insert into exam_icd_prop_val (id, icdid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}
		return prop;
	}

	/**
	 * 删除ICD属性
	 */
	@Override
	public boolean deleteIcdVal(ExamICD prop) {
		String SQL_DEL_EXAM_PROP_VAL = "delete from exam_icd_prop where id = ?";
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from exam_icd_prop_val where icdid = ?";
		String SQL_PROP_VAL_REF = "select count(1) from exam_icd_prop_val x where x.icdid = ?";
		
		int size = getJdbcTemplate().queryForInt(SQL_PROP_VAL_REF, prop.getId());
		size = 0;
		//检查是否有下级关联
		if(size==0){
			//删除系统属性
			getJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL,prop.getId());
			//删除属性
			getJdbcTemplate().update(SQL_DEL_EXAM_PROP_VAL,prop.getId());
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 修改ICD属性
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void updateIcdVal(ExamICD prop) throws Exception {
		if (existIcdVal(prop, prop.getId())) throw new Exception();
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_icd_prop set ");
//		prop.setCode(PinyinUtil.generateCode(prop.getName(), prop.getId().toString()));
		List list = new ArrayList();
		if(!StringUtils.checkNull(prop.getName())){
			sql.append("name = ?,");
			list.add(prop.getName());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		if(null!=prop.getNameEn()){
			sql.append("name_en = ?,");
			list.add(prop.getNameEn());
		}
		if(null!=prop.getHint()){
			sql.append("hint = ?,");
			list.add(prop.getHint());
		}
		sql.append(" type = type where id = ?");
		list.add(prop.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		
		//删除旧的学科
		String DEL_PROP = "delete from exam_icd_prop_val where icdid=?";
		getSimpleJdbcTemplate().update(DEL_PROP, prop.getId());

		//添加新的学科
		try{
		String[] propIds = prop.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_icd_prop_val");
			String ADD_PROP = "insert into exam_icd_prop_val (id, icdid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}
	}

	/**
	 * 查询来源类型
	 */
	@Override
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop) {
		String sql = "select * from exam_source_type where id!=0 and type_name like ? order by id desc";
		
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
	
	/**
	 * 添加来源类型
	 */
	@Override
	public ExamSourceType addSourceType(ExamSourceType prop) throws Exception {
		if (existSourceType(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_source_type");
		prop.setId(prop_id);
		//prop.setCode(PinyinUtil.generateCode(prop.getType_name(), prop_id.toString()));
		String ADD_PROP_VAL = "insert into exam_source_type (id, type_name, code) values (:id, :type_name, :code)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));
		return prop;
	}

	/**
	 * 删除来源类型
	 */
	@Override
	public boolean deleteSourceType(ExamSourceType prop) {
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from exam_source_type where id = ?";
		int res = getSimpleJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL, prop.getId());
		return res>0?true:false;
	}

	/**
	 * 修改来源类型
	 */
	@SuppressWarnings("unchecked")
	public void updateSourceType(ExamSourceType prop) throws Exception {
		if (existSourceType(prop, prop.getId())) throw new Exception();
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_source_type set ");
		List list = new ArrayList();
		//prop.setCode(PinyinUtil.generateCode(prop.getType_name(), prop.getId().toString()));
		if(!StringUtils.checkNull(prop.getType_name())){
			sql.append("type_name = ?,");
			list.add(prop.getType_name());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		sql.append(" id = id where id = ?");
		list.add(prop.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		
	}

	/**
	 * 查询来源
	 */
	@Override
	public List<ExamSource> getSourceValList(ExamSource prop) {
		String sql = "select t.*, s.type_name as typename from exam_source_val t left join exam_source_type s on t.type=s.id where t.name like ? "; // and s.type_name like ? 
		
		if (prop.getId()!=null && prop.getId() > 0)
			sql += " and t.id=" + prop.getId();
		
		if (prop.getType()!=null && prop.getType() > 0)
			sql += " and t.type=" + prop.getType();
		
		if (prop.getState()!=null && prop.getState() > 0)
			sql += " and t.state=" + prop.getState();
		
		StringBuffer propsql = new StringBuffer();
		if(!StringUtils.checkNull(prop.getPropIds())){
			String ids = prop.getPropIds().replace('"', ' ');
			propsql.append(" and t.id in (select sourceid from EXAM_SOURCE_PROP_VAL where PROPID in (").append(ids).append(")) ");
			sql += propsql.toString();
		}
		
		sql += " order by t.id desc";

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

	}	/**
	 * 查询来源
	 */
	@Override
	public List<ExamSource> getSourceValList(String ids) {
		ids=(ids.equals(""))?"''":ids;
		String sql = "select t.*, s.type_name as typename from exam_source_val t left join exam_source_type s on t.type=s.id where t.id in ("+ids+")  order by t.id desc";

	//取得所有的所属学科和主题
		List<ExamSource> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamSource.class));
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

	public boolean existSourceVal(ExamSource prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;
		String getcode = "select count(1) from exam_source_val where ((code='" + prop.getCode() + "') or (name='"+prop.getName()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	/**
	 * 添加来源
	 * @throws Exception 
	 */
	@Override
	public ExamSource addSourceVal(ExamSource prop) throws Exception {
		if (existSourceVal(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_source_val");
		prop.setId(prop_id);
		//prop.setCode(PinyinUtil.generateCode(prop.getName(), prop_id.toString()));
		String ADD_PROP_VAL = "insert into exam_source_val (id, type, name, code, source, old) values (:id, :type, :name, :code, :source, :old)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));
	
		//添加关联学科
		try{
		String[] propIds = prop.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_source_prop_val");
			String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}

		//添加主题
		try{
		String[] propIds = prop.getZhutiIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_source_prop_val");
			String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}
		return prop;
	}

	//删除来源
	@Override
	public boolean deleteSourceVal(ExamSource prop) {
		String SQL_DEL_EXAM_VAL = "delete from exam_question_source where prop_val_id = ?";
		String SQL_DEL_PROP_VAL = "delete from exam_source_prop_val where sourceid = ?";
		String SQL_DEL_MAT_VAL = "delete from material_source_val where source_id = ?";
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from exam_source_val where id = ?";
		
		int res = getSimpleJdbcTemplate().update(SQL_DEL_EXAM_VAL, prop.getId());
		res = getSimpleJdbcTemplate().update(SQL_DEL_PROP_VAL, prop.getId());
		res = getSimpleJdbcTemplate().update(SQL_DEL_MAT_VAL, prop.getId());
		res = getSimpleJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL, prop.getId());
		
		return res>0?true:false;
	}

	//修改来源
	@SuppressWarnings("unchecked")
	public boolean updateSourceVal(ExamSource prop) {
		if (existSourceVal(prop, prop.getId())) return false;
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_source_val set ");
		//prop.setCode(PinyinUtil.generateCode(prop.getName(), prop.getId().toString()));
		List list = new ArrayList();
		if(prop.getType()!=null){
			sql.append("type = ?,");
			list.add(prop.getType());
		}
		if(!StringUtils.checkNull(prop.getName())){
			sql.append("name = ?,");
			list.add(prop.getName());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		if(prop.getSource()!=null){
			sql.append("source = ?,");
			list.add(prop.getSource());
		}
		if(prop.getState()!=null){
			sql.append("state = ?,");
			list.add(prop.getState());
		}
		if(prop.getOld()!=null){
			sql.append("old = ?,");
			list.add(prop.getOld());
		}
		sql.append(" id = id where id = ?");
		list.add(prop.getId());
		
		int res =getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

		if (prop.getPropIds() != null){
			//删除旧的学科
			String DEL_PROP = "delete from EXAM_SOURCE_PROP_VAL where id in (select EXAM_SOURCE_PROP_VAL.id from SUB_SYS_PROP_VAL s LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.id where EXAM_SOURCE_PROP_VAL.PROPID=s.id and EXAM_SOURCE_PROP_VAL.SOURCEID=? and p.type<=5)";
			//"delete from exam_source_prop_val where sourceid=?";
			getSimpleJdbcTemplate().update(DEL_PROP, prop.getId());
	
			//添加新的学科
			try{
			String[] propIds = prop.getPropIds().split(",");
			for(String ps: propIds){
				Long pid = Long.valueOf(ps);
				Long vid = getNextId("exam_source_prop_val");
				String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
			}
			}catch(Exception e){;}
		}
		
		if (prop.getZhutiIds() != null){
			//删除旧的主题
			String DEL_ZHUTI = "delete from EXAM_SOURCE_PROP_VAL where id in (select EXAM_SOURCE_PROP_VAL.id from SUB_SYS_PROP_VAL s LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.id where EXAM_SOURCE_PROP_VAL.PROPID=s.id and EXAM_SOURCE_PROP_VAL.SOURCEID=? and p.type=7)";
			//"delete from exam_source_prop_val where sourceid=?";
			getSimpleJdbcTemplate().update(DEL_ZHUTI, prop.getId());
	
			//添加新的主题
			try{
			String[] propIds = prop.getZhutiIds().split(",");
			for(String ps: propIds){
				Long pid = Long.valueOf(ps);
				Long vid = getNextId("exam_source_prop_val");
				String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
			}
			}catch(Exception e){;}
		}
		return res>0?true:false;
	}

	@Override
	public List<ExamHospital> getHospitalList(ExamHospital host) {
		String sql = "select t.* from exam_hospital t where id > 0";
		
		List plist = new ArrayList();
		if (host.getId() != null && !host.getId().equals(0L)){
			sql += " and t.id = ?";
			plist.add(host.getId());
		}
		if (host.getPropId() != null && !host.getPropId().equals(0L)){
			sql += " and t.propid = ?";
			plist.add(host.getPropId());
		}
		if (host.getName()!=null && !host.getName().trim().equals("")){
			sql += " and t.name like ?";
			plist.add("%"+host.getName().trim()+"%");
		}
		if (host.getStartName()!=null && !host.getStartName().trim().equals("")){
			sql += " and t.name like ? order by t.name limit 0, 100";
			plist.add(host.getStartName().trim()+"%");
		}
		else
			sql += " order by t.id desc";
		
		
		List<ExamHospital> list =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),plist.toArray());

		/*for(ExamHospital e: list){
		try{
			String GET_REGION = "select t.*, cast(t.code as signed) as codenum, r.*, s.id as prop_val_id from EXAM_PROP_VAL t left join SUB_SYS_PROP_VAL s on t.id=s.PROP_VAL_ID LEFT JOIN PROP_VAL_REF r on r.prop_val2=s.id where s.id=?";
			ExamProp prop;
			prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), e.getRegionId());
			
			if (prop.getType() == 22){
				e.setRegion3(prop.getName());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 21){
				e.setRegion2(prop.getName());
				e.setRegionId2(prop.getProp_val_id());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 20){
				e.setRegion1(prop.getName());
				e.setRegionId1(prop.getProp_val_id());
			}
		}catch(Exception ex){;}
		}
		*/
		return list;
	}
	@Override
	public List<ExamHospitalVO> getHospitalList(String searchWord) {
		String sql = "SELECT t.ID,t.`NAME` title,city.id cityID,city.`NAME` cityName,prov.id provID,prov.`NAME` provName  " +
				"from exam_hospital t  " +
				"INNER JOIN PROP_VAL_REF c on t.REGIONID=c.PROP_VAL2 " +
				"INNER JOIN PROP_VAL_REF s on s.PROP_VAL2=c.PROP_VAL1 " +
				"INNER JOIN EXAM_PROP_VAL city on city.ID=c.PROP_VAL1 " +
				"INNER JOIN EXAM_PROP_VAL prov on prov.ID=s.PROP_VAL1   where 1=1   ";
		List plist = new ArrayList();
		String searchName ="";
		if (searchWord == null){
			searchName = "%";
		}else{
			searchName = "%" + searchWord + "%";
		}
		sql += " and t.name like ? ";
		plist.add(searchName);
		List<ExamHospitalVO> list =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospitalVO.class),plist.toArray());
		return list;
	}

	@Override
	public List<ExamHospital> getHospitalList(com.hys.exam.utils.PageList pl,	ExamHospital host) {
		String sql = "select t.* from exam_hospital t where id > 0 and ";
		
		List plist = new ArrayList();
		if (host.getPropId() != null && !host.getPropId().equals(0L)){
			sql += "t.propid = ? and ";
			plist.add(host.getPropId());
		}

		String searchName = host.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";

		sql += "t.name like ? ";
		plist.add(searchName);
		
		// paging code added by han.
		if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			sql += " order by "+pl.getSortCriterion();
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				sql += " desc";
		}
		
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql), plist.toArray());
		pl.setFullListSize(fullListSize);

		List<ExamHospital> list =  getJdbcTemplate().query(PageUtil.getPageSql(sql, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),plist.toArray());

		for(ExamHospital e: list){
		try{
			String GET_REGION = "select t.*, cast(t.code as signed) as codenum, r.*, s.id as prop_val_id from EXAM_PROP_VAL t left join SUB_SYS_PROP_VAL s on t.id=s.PROP_VAL_ID LEFT JOIN PROP_VAL_REF r on r.prop_val2=s.id where s.id=?";
			ExamProp prop;
			prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), e.getRegionId());
			
			if (prop.getType() == 22){
				e.setRegion3(prop.getName());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 21){
				e.setRegion2(prop.getName());
				e.setRegionId2(prop.getProp_val_id());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 20){
				e.setRegion1(prop.getName());
				e.setRegionId1(prop.getProp_val_id());
			}
		}catch(Exception ex){;}
		}
		
		pl.setList(list);
		return null;
	}

	@Override
	public void getSourceValPageList(com.hys.exam.utils.PageList pl, ExamSource prop) {
		String sql = "select t.*, s.type_name as typename from exam_source_val t left join exam_source_type s on t.type=s.id where t.name like ? "; // and s.type_name like ?

		if (prop.getId() != null && prop.getId() > 0)
			sql += " and t.id=" + prop.getId();

		if (prop.getType() != null && prop.getType() > 0)
			sql += " and t.type=" + prop.getType();

		if (prop.getState() != null && prop.getState() > 0)
			sql += " and t.state=" + prop.getState();

		StringBuffer propsql = new StringBuffer();
		if (!StringUtils.checkNull(prop.getPropIds())) {
			String ids = prop.getPropIds().replace('"', ' ');
			propsql.append(" and t.id in (select sourceid from EXAM_SOURCE_PROP_VAL where PROPID in (").append(ids).append(")) ");
			sql += propsql.toString();
		}


		String searchName = prop.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";

/*		String searchTypeName = prop.getTypeName();
		if (searchTypeName == null) searchTypeName = "%";
		else searchTypeName = "%" + searchTypeName + "%";

*/        //取得所有的所属学科和主题

		if (pl.getSortCriterion() != null && !pl.getSortCriterion().equals("")) {
			sql += " and t.state=1 order by " + pl.getSortCriterion();

			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				sql += " desc";
		}

		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql), searchName);
		pl.setFullListSize(fullListSize);

		List<ExamSource> list = getJdbcTemplate().query(PageUtil.getPageSql(sql, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ExamSource.class), searchName);
		for (ExamSource s : list) {
			String GET_PROP = "SELECT p.*, s.id AS prop_val_id FROM EXAM_SOURCE_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p ON s.prop_val_id=p.ID WHERE p.id>0 AND e.SOURCEID=? AND p.type<=5";
			List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setProp(propList);

			String GET_ZHUTI = "SELECT p.*, s.id AS prop_val_id FROM EXAM_SOURCE_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p ON s.prop_val_id=p.ID WHERE p.id>0 AND e.SOURCEID=? AND p.type=7";
			propList = getJdbcTemplate().query(GET_ZHUTI, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setZhuti(propList);

		}
		pl.setList(list);
	}

	@Override
	public boolean addHospital(ExamHospital host) {
		Long id = getNextId("exam_hospital");
		host.setId(id);
		String sql = "insert into exam_hospital (id, name, examiner, regionid, propid) values (:id, :name, :examiner, :regionId, :propId)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(host));
		return true;
	}

	@Override
	public boolean deleteHospital(ExamHospital host) {
		String SQL_DEL = "delete from exam_hospital where id = ?";
		int res = getSimpleJdbcTemplate().update(SQL_DEL, host.getId());
		return res>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public boolean updateHospital(ExamHospital host) {
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_hospital set ");
		List list = new ArrayList();
		if(host.getRegionId()!=null && host.getRegionId()>0){
			sql.append("regionid = ?,");
			list.add(host.getRegionId());
		}
		if(host.getPropId()!=null){
			sql.append("propid = ?,");
			list.add(host.getPropId());
		}
		if(!StringUtils.checkNull(host.getName())){
			sql.append("name = ?,");
			list.add(host.getName());
		}
		if(!StringUtils.checkNull(host.getExaminer())){
			sql.append("examiner = ?,");
			list.add(host.getExaminer());
		}
		sql.append(" id = id where id = ?");
		list.add(host.getId());
		
		int res =getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

		return res>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, t.major, t.ordername, t.classid, t.hospitalid, h.name as hospital from exam_major_order t left join exam_hospital h on t.hospitalid=h.id where t.id<>0");

		List list = new ArrayList();
		if(!StringUtils.checkNull(major.getMajor())){
			sql.append(" and t.major like '%").append(major.getMajor()).append("%'");
		}
		if(!StringUtils.checkNull(major.getHospital())){
			sql.append(" and h.name like '%").append(major.getHospital()).append("%'");
		}
		if(major.getClassId() != null && major.getClassId() != 0 ){
			sql.append(" and t.classid=?");
			list.add(major.getClassId());
		}
		sql.append(" order by t.id desc");
		return getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExamMajorOrder.class));
	}
	
	public boolean existMajorOrder(ExamMajorOrder major){
		if (major == null || major.getMajor() == null || major.getHospital() == null) return false;
		String getcode = "select count(1) from exam_major_order where major='" + major.getMajor() + "' and hospital='"+major.getHospital()+"' and id<>"+major.getId().toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}

	@Override
	public boolean addMajorOrder(ExamMajorOrder major) {
		if (existMajorOrder(major)) return false;
		Long id = getNextId("exam_major_order");
		major.setId(id);
		String sql = "insert into exam_major_order (id, major, hospitalid, ordername, classid) values (:id, :major, :hospitalId, :orderName, :classId)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(major));
		return true;
	}

	@Override
	public boolean deleteMajorOrder(ExamMajorOrder major) {
		String SQL_DEL = "delete from exam_major_order where id = ?";
		int res = getSimpleJdbcTemplate().update(SQL_DEL, major.getId());
		return res>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public boolean updateMajorOrder(ExamMajorOrder major) {
		if (existMajorOrder(major)) return false;
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_major_order set ");
		List list = new ArrayList();
		if(major.getClassId()!=null){
			sql.append("classid = ?,");
			list.add(major.getClassId());
		}
		if(!StringUtils.checkNull(major.getOrderName())){
			sql.append("ordername = ?,");
			list.add(major.getOrderName());
		}
		/*if(!StringUtils.checkNull(major.getHospital())){
			sql.append("hospital = ?,");
			list.add(major.getHospital());
		}*/
		if(major.getHospitalId()!=null && major.getHospitalId()!=0L){
			sql.append("hospitalid = ?,");
			list.add(major.getHospitalId());
		}
		if(!StringUtils.checkNull(major.getMajor())){
			sql.append("major = ?,");
			list.add(major.getMajor());
		}
		sql.append(" id = id where id = ?");
		list.add(major.getId());
		
		int res =getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

		return res>0?true:false;
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

	@Override
	public ExamProp getExamPropValByPropId(Long id) {
		String sql = "select t.*,v.id as prop_val_id from exam_prop_val t,sub_sys_prop_val v where t.id = v.prop_val_id and v.id = ?";
		return getJdbcTemplate().queryForObject(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamProp.class), id);
	}
	
	@Override
	public List<ExamPropVal> getIdByName(ExamPropVal name) {
		String sql ="SELECT * FROM EXAM_PROP_VAL WHERE NAME = '"+name.getName()+"' and type<6";
		
		List<ExamPropVal> test = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class));
		return test;
	}

	@Override
	public List<ExamICD> getICDListByPropIds(String ids) {
		if (ids==null || ids.equals("")) return null;
		String sql = "select DISTINCT a.* from exam_icd_prop a, exam_icd_prop_val b where a.id=b.icdid and b.PROPID in ("+ids+")";
		
		List<ExamICD> res = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class));
		return res;
	}

	/*
	 * Change Date: 2017/01/12
	 * Author     : Lee
	 * Explain    : get Hospital Info by Hospital ID
	 */
	@Override
	public ExamHospital getHospitalById(ExamHospital host) {
		ExamHospital examHospital = new ExamHospital();		
		String sql = "select t.* from exam_hospital t where id =?";
		//examHospital =  getJdbcTemplate().qqueryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),host.getId());
		List<ExamHospital> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class), host.getId());
		if(list != null && list.size() > 0){
			examHospital = list.get(0);
		}
		return examHospital;
	}
	
	@Override
	public List<ExamPropVal> getXuekeByImageName(UserImage userImage) {
		String sql = new String();
		List<Object> list1 = new ArrayList<Object>();
		list1.add(userImage.getName());
		List<ExamPropVal> list2 = new ArrayList<ExamPropVal>();
		sql = "select e.* from qm_user_image q LEFT JOIN qm_user_image_prop qui ON q.ID=qui.USERIMAGE_ID LEFT JOIN exam_prop_val e ON qui.PROP_ID=e.ID where q.NAME=?";
		list2=getJdbcTemplate().query(sql,list1.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class));
		return list2;
	}
	
	@Override
	public ExamProp getRegionListByPropId(Long id) {
		ExamProp examProp = new ExamProp();
		String sql = "SELECT t.* FROM exam_prop_val t,sub_sys_prop_val v,sub_sys_prop p,exam_prop_type tt WHERE	t.id = v.prop_val_id AND p.id = v.sys_prop_id AND tt.prop_type = t.c_type and v.PROP_VAL_ID = ?";
		examProp = getJdbcTemplate().queryForObject(sql,ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), id);
		examProp.setProp_val_id(id);
		return examProp;
	}

}
