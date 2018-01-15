package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamPropValDAO;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 13, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropValJDBCDAO extends BaseDao implements ExamPropValDAO {

	private static final String GETBASEPROPVAL_SQL = "select t2.id, t2.type, t2.name, t2.code, t1.id as refId from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id and t2.type =?";
	private static final String GETBASEPROPVAL_SQL2 = "select c.*,d.id as refId from sub_sys_prop_val b, exam_prop_val c,sub_sys_prop_val d where d.prop_val_id = c.id and b.prop_val_id = c.id and b.id in(select a.prop_val2 from PROP_VAL_REF a where a.prop_val1 =?)";

	
	@Override
	public List<ExamPropValTemp> getBasePropVal(String key, Integer sysId,
			Integer type) throws SQLException {
		
		List <ExamPropValTemp> rlist = new ArrayList<ExamPropValTemp>();
		
		ParameterizedRowMapper<ExamPropVal> mapper = getMapper(RowMapper.class, RowMapper.getBasePropValListMapper);
		
		List<ExamPropVal> list =  queryForList(key, GETBASEPROPVAL_SQL, new Object[]{type}, mapper);
		
		ExamPropValTemp pvt = null;
		
		if(null != list && list.size()>0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ExamPropVal name = (ExamPropVal) iterator.next();
				pvt = new ExamPropValTemp();
				pvt.setId(name.getId().toString());
				pvt.setName(name.getName());
				
				List<ExamPropValTemp>subItem = this.getExamPropVal(key,name.getRefId());
				pvt.setSubItems(subItem);
				rlist.add(pvt);
			}
		}
		
		return rlist;
	}

	@Override
	public Map<String, List<ExamPropVal>> getSysPropValBySysId(String key) throws SQLException {
		
		String sql = "select t.type, t2.id, t2.name,t2.code from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id and t.relation = 0 and t.up_level is null";

		Map<String, List<ExamPropVal>> m = new HashMap<String, List<ExamPropVal>>();
		
		ParameterizedRowMapper<ExamPropVal> mapper = getMapper(RowMapper.class, RowMapper.getSysPropValBySysIdListMapper);
		
		List<ExamPropVal> propValList = queryForList(key,sql,mapper);
		
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
	private List<ExamPropValTemp> getExamPropVal(String key,Long refId)  throws SQLException {
		
		List<ExamPropValTemp>subItem = new ArrayList<ExamPropValTemp>();
		
		ParameterizedRowMapper<ExamPropVal> mapper = getMapper(RowMapper.class, RowMapper.getBasePropValListMapper);
	
		List<ExamPropVal> list2 = queryForList(key, GETBASEPROPVAL_SQL2, new Object[] {refId}, mapper);
		
		ExamPropValTemp pvt2 = null;
		if(null != list2 && list2.size()>0) {
			for (Iterator iterator2 = list2.iterator(); iterator2.hasNext();) {
				ExamPropVal name2 = (ExamPropVal) iterator2.next();
				pvt2 = new ExamPropValTemp();
				pvt2.setId(name2.getId().toString());
				pvt2.setName(name2.getName());
				
				if(name2.getType()<6) {
					pvt2.setSubItems(this.getExamPropVal(key,name2.getRefId()));
				}
				
				subItem.add(pvt2);
			}
		}
		
		return subItem;
		
	}	
	
}
