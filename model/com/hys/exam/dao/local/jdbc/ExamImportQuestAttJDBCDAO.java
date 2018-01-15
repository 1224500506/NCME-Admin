package com.hys.exam.dao.local.jdbc;

import java.util.List;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamImportQuestAttDAO;
import com.hys.exam.model.ExamQuestAtt;
import com.hys.exam.model.ExamQuestRelation;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamImportQuestAttJDBCDAO extends BaseDao implements
		ExamImportQuestAttDAO {

	private static final String SQL_ADD_QUEST_PROP = "insert into exam_prop_val (id, type, name) values (:id, :type, :name)";
	
	private static final String SQL_ADD_SYS_PROP_VAL = "insert into sub_sys_prop_val (id, sys_prop_id, prop_val_id) values (:sysPropValId, :type, :id)";
	
	private static final String SQL_ADD_PROP_VAL_RELATION = "insert into prop_val_ref (prop_val1, prop_val2) values (:id, :c_Id)";
	
	@Override
	public void addQuestProp(List<ExamQuestAtt> propList) {
		saveList(SQL_ADD_QUEST_PROP, propList);
	}

	@Override
	public void addSysPropVal(List<ExamQuestAtt> propList) {
		saveList(SQL_ADD_SYS_PROP_VAL,propList);
	}

	@Override
	public void addSyspropValRelation(List<ExamQuestRelation> relationList) {
		saveList(SQL_ADD_PROP_VAL_RELATION,relationList);
	}

}
