package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamQuestionDAO;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamPaperFasterTacticX;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 10, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionJDBCDAO extends BaseDao implements ExamQuestionDAO {

	
	private static final String SQL_ADD_QUESTION = "insert into exam_question (id, question_label_id, parent_id, content, state, grade, differ, analyse, source, seq, author, online_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,to_date(?,'yyyy-mm-dd hh24miss'))";
	
	private static final String SQL_DEL_QUESTION = "delete exam_question t where t.id = ? or t.parent_id = ?";
	
	private static final String SQL_GET_QUESTION_BY_ID = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date,  t.author, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date from exam_question t where t.id = ?";
	
	private static final String SQL_GET_CHILD_QUESTION_BY_PARENT_ID = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date,  t.author, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date from exam_question t where t.parent_id = ?";
	
	private static final String SQL_DEL_CHILD_QUESTION = "delete exam_question t where t.parent_id = ?";
	
	private static final String SQL_UPDATE_QUESTION_STATE = "update exam_question set state = ? where id = ? or parent_id = ?";
	
	private static final String SQL_UPDATE_QUESTION_CREATE_DATE = "update exam_question set create_date = sysdate() where id = ? or parent_id = ?";
	
	private static final String SQL_GET_QUESTION_LIST = "select distinct t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date, t.author, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date from exam_question t, exam_question_type t1, exam_sys_quest_type_quest t2 where t.id = t2.question_id and t1.id = t2.sub_type_id and t.parent_id = 0 and t2.state = 1 ";
	
	private static final String SQL_GET_QUESTION_LIST_SIZE = "select count(distinct t.id) from exam_question t, exam_question_type t1, exam_sys_quest_type_quest t2 where t.id = t2.question_id and t1.id = t2.sub_type_id and t.parent_id = 0 and t2.state = 1 ";
	
	private static final String SQL_ISNOT_EXIST_QUESTION = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date, t.author, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date from exam_question t where t.question_label_id=? and t.content=? and t.parent_id = 0";
	
	private static final String SQL_ADD_QUESTION_TYPE = "insert into exam_sys_quest_type_quest (sub_type_id, question_id, sub_sys_id, state) values (?, ?, ?, ?)";
	
	private static final String SQL_DEL_QUESTION_TYPE = "delete from exam_sys_quest_type_quest where exists (select * from exam_question q where q.id = exam_sys_quest_type_quest.question_id and (q.parent_id = ? or q.id = ?))";
	
	private static final String SQL_GET_QUESTION_TYPE = "select t.question_id,t.sub_type_id,t.sub_sys_id,t1.name as sub_type_name,t2.name as sub_sys_name from exam_sys_quest_type_quest t, exam_question_type t1, system t2 where t1.id = t.sub_type_id and t2.id = t.sub_sys_id and t.question_id = ?";
	
	private static final String SQL_GET_QUESTIONLIST_BY_TACTIC =  "select (select count(1) from EXAM_QUESTION where parent_id = q.id) as childQuestionNum, q.* from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 4 and t.state = 1 and q.question_label_id = ? ";
	
	private static final String SQL_GET_QUESTIONLIST_BY_TACTIC_SIZE =  "select count(1) from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 4 and t.state = 1 and q.question_label_id = ? ";
	
	private static final String SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC = "select count(distinct q.id) from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 4 and t.state = 1 ";

	private static final String SQL_GET_QUESTION_LIST_BY_FASTER_TACTIC = "select q.* from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 4 and t.state = 1 ";
	
	
	@SuppressWarnings("unchecked")
	public ExamQuestion addQuestion(String key,ExamQuestion quest,int saveParent) throws SQLException {
		
		//试题答案列表
		List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();
		
		//试题分类列表
		List<ExamSubSysQuestType> typeList = new ArrayList<ExamSubSysQuestType>();
		
		//试题属性列表
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
		
		//试题列表
		List<ExamQuestion> batch = new ArrayList<ExamQuestion>();
		
		if (saveParent == 1) {
			quest.setId(getSeqNextvalForLong(key,Constants.TABLE_EXAM_QUESTION));
			quest.getQuestPropValCascade().setQuestion_id(quest.getId());
		}
		
		//主试题分类处理
		if(quest.getSubSysQuestTypeList()!= null && !quest.getSubSysQuestTypeList().isEmpty()){
			for(ExamSubSysQuestType type : quest.getSubSysQuestTypeList()){
				type.setQuestion_id(quest.getId());
				typeList.add(type);
			}
		}
		
		
		//主试题答案处理
		if (quest.getQuestionKeyList() != null
				&& !quest.getQuestionKeyList().isEmpty()) {
			for (int i = 0; i < quest.getQuestionKeyList().size(); i++) {
				ExamQuestionKey k = quest.getQuestionKeyList().get(i);
				k.setQuestion_id(quest.getId());
				keyList.add(k);
			}
		}
		
		//主试题属性
		Map<String,List<ExamQuestionProp>> propMap = quest.getQuestionPropMap();
		
		for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			List<ExamQuestionProp> propList = (List<ExamQuestionProp>) entry.getValue();
			for(int i=0;i<propList.size();i++){
				ExamQuestionProp questProp = propList.get(i);
				questProp.setQuestion_id(quest.getId());
			}
			questionPropMap.put(entry.getKey().toString(), propList);
		}
		
		if (saveParent == 1) {
			batch.add(quest);
		}
		
		List<ExamQuestion> child = quest.getChildQuestionList();
		
		//子试题
		if(child!=null){
			
			for(int i=0;i<child.size();i++){
				ExamQuestion childQuestion = child.get(i);
				childQuestion.setParent_id(quest.getId());
				childQuestion.setId(getSeqNextvalForLong(key,Constants.TABLE_EXAM_QUESTION));
				batch.add(childQuestion);
				
				
				if(childQuestion.getSubSysQuestTypeList() != null && !childQuestion.getSubSysQuestTypeList().isEmpty()){
					for(ExamSubSysQuestType childType : childQuestion.getSubSysQuestTypeList()){
						childType.setQuestion_id(childQuestion.getId());
						typeList.add(childType);
					}
				}
				
				//子试题答案处理
				if (childQuestion.getQuestionKeyList() != null
						&& !childQuestion.getQuestionKeyList().isEmpty()) {
					for (int j = 0; j < childQuestion.getQuestionKeyList()
							.size(); j++) {
						ExamQuestionKey childkey = childQuestion
								.getQuestionKeyList().get(j);
						childkey.setQuestion_id(childQuestion.getId());
						keyList.add(childkey);
					}
				}
				
				//子试题属性处理
				Map<String,List<ExamQuestionProp>> childPropMap = childQuestion.getQuestionPropMap();
				
				for (Iterator iter = childPropMap.entrySet().iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					List<ExamQuestionProp> propList = (List<ExamQuestionProp>) entry.getValue();
					for(int j=0;j<propList.size();j++){
						ExamQuestionProp questProp = propList.get(j);
						questProp.setQuestion_id(childQuestion.getId());
					}
					
					if(questionPropMap.containsKey(entry.getKey().toString())){
						questionPropMap.get(entry.getKey().toString()).addAll(propList);
					}else{
						questionPropMap.put(entry.getKey().toString(), propList);
					}
				}
				
			}
		}
		
		quest.setQuestionKeyList(keyList);
		quest.setQuestionPropMap(questionPropMap);
		quest.setSubSysQuestTypeList(typeList);
		saveQuestion(key,batch);
		return quest;
	}
	
	
	private void saveQuestion(String key,List<ExamQuestion> questionList) throws SQLException{
		List<Object[]> batch = new ArrayList<Object[]>();
		
		for(ExamQuestion q : questionList){
			Object[] values = new Object[] {
				q.getId(),
				q.getQuestion_label_id(),
				q.getParent_id(),
				q.getContent(),
				q.getState(),
				q.getGrade(),
				q.getDiffer(),
				q.getAnalyse(),
				q.getSource(),
				q.getSeq(),
				q.getAuthor(),
				q.getOnline_date()
			};
			batch.add(values);
		}
		batchUpdate(key, SQL_ADD_QUESTION, batch);
	}

	public void deleteQuesitons(String key, List<Object[]> id) throws SQLException {
		batchUpdate(key, SQL_DEL_QUESTION, id);
	}

	public ExamQuestion getQuestionById(String key,Long id) throws SQLException {
		ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionListMapper);
		return queryForList(key, SQL_GET_QUESTION_BY_ID, new Object[] { id },
				mapper).get(0);
	}

	@SuppressWarnings("unchecked")
	public void updateQuestionById(String key,ExamQuestion quest) throws SQLException {
		List values = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_question set ");

		if (!StringUtils.checkNull(quest.getContent())) {
			sql.append("content = ?, ");
			values.add(quest.getContent());
		}
		if (null != quest.getState()) {
			sql.append("state = ?, ");
			values.add(quest.getState());
		}
		if (null != quest.getGrade()) {
			sql.append("grade = ?, ");
			values.add(quest.getGrade());
		}
		if (null != quest.getDiffer()) {
			sql.append("differ = ?, ");
			values.add(quest.getDiffer());
		}
		if (!StringUtils.checkNull(quest.getAnalyse())) {
			sql.append("analyse = ?, ");
			values.add(quest.getAnalyse());
		}
		if (!StringUtils.checkNull(quest.getAuthor())) {
			sql.append("author = ?, ");
			values.add(quest.getAuthor());
		}
		if (!StringUtils.checkNull(quest.getSource())) {
			sql.append("source = ?, ");
			values.add(quest.getSource());
		}

		sql.append("create_date = sysdate() where id = ?");
		values.add(quest.getId());

		update(key,sql.toString(), values.toArray());
	}

	public List<ExamQuestion> getChildQuestionByParentId(String key,Long parentId) throws SQLException {
		ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionListMapper);
		return queryForList(key, SQL_GET_CHILD_QUESTION_BY_PARENT_ID, new Object[] { parentId }, mapper);
	}

	public void deleteChildQuestionByParentId(String key,Long parentId) throws SQLException {
		update(SQL_DEL_CHILD_QUESTION, new Object[] { parentId });
	}

	public void updateQuestionStateByIdArr(String key, List<Long> id, int state) throws SQLException {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); i++) {
			Object[] values = new Object[] { state,id.get(i),id.get(i) };
			batch.add(values);
		}
		batchUpdate(key, SQL_UPDATE_QUESTION_STATE, batch);
	}

	public List<ExamQuestion> getQuestionList(String key, ExamQuestionQuery questQuery) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = setParams(SQL_GET_QUESTION_LIST, questQuery, list);
		ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionListMapper);
		return queryForList(key, PageUtil.getPageSql(sql, questQuery.getPageNo(), questQuery
				.getPageSize()), list,  mapper);
		
	}
	
	public int getQuestionSize(String key, ExamQuestionQuery questQuery)  throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = setParams(SQL_GET_QUESTION_LIST_SIZE, questQuery, list);
		return queryForInt(key, sql, list.toArray());
	}
	
	@SuppressWarnings("unchecked")
	private String setParams(String sql_, ExamQuestionQuery query,
			List<Object> list){
		StringBuffer sql = new StringBuffer();
		sql.append(sql_);
		// 题型
		if (!StringUtils.checkNull(query.getQuestion_label_ids())) {
			sql.append(" and t.question_label_id in ("
					+ query.getQuestion_label_ids() + ")");
		}
		
		//区分度
		if (null != query.getDiffer()) {
			list.add(query.getDiffer());
			sql.append(" and t.differ = ? ");
		}

		//难度
		if (null != query.getGrade()) {
			list.add(query.getGrade());
			sql.append(" and t.grade = ? ");
		}
		
		//状态
		if(null != query.getState()){
			if(-1 == query.getState()){
				list.add(6); //有属性试题
				sql.append(" and t.state != ? ");
			}else{
				list.add(query.getState());
				sql.append(" and t.state = ? ");
			}
		}

		//内容
		if (!StringUtils.checkNull(query.getContent())) {
			list.add("%"+ query.getContent() + "%");
			sql.append(" and t.content like ? ");
		}

		//来源
		if (!StringUtils.checkNull(query.getSource())) {
			list.add("%"+ query.getSource() + "%");
			sql.append(" and t.source like ? ");
		}

		//创建者
		if (!StringUtils.checkNull(query.getAuthor())) {
			list.add(query.getAuthor());
			sql.append(" and t.author = ?");
		}
		
		//解析
		if (null != query.getIsExistAnalyse()) {
			if (1 == query.getIsExistAnalyse()) {
				sql.append(" and t.analyse is not null ");
			} else {
				sql.append(" and t.analyse is null ");
			}
		}
		
		//创建时间
		if(null != query.getCreate_date()){
			list.add(query.getCreate_date());
			sql.append(" and to_char(t.create_date,'yyyy-MM-dd') = ? ");
		}
		
		//审核时间
		if(null != query.getOnline_date()){
			list.add(query.getOnline_date());
			sql.append("and to_char(t.online_date,'yyyy-MM-dd') = ? ");
		}
		
		if(null != query.getIdArr()){
			String ids = "";
			for(Long id : query.getIdArr()){
				ids += id+",";
				ids = ids.substring(0, ids.length()-1);				
			}
			sql.append("and t.id not in ("+ids+") ");
		}
		
		Map<String,List<ExamQuestionProp>> questionPropMap = query.getQuestionPropMap();
		
		if(null != questionPropMap){
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp_sql = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						ExamQuestionProp questProp = propValList.get(i);
						tmp_sql += questProp.getProp_val_id()+",";
					}
					tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
					sql.append(" and t.id in (select question_id from "+Constants.getPropNameAll().get(entry.getKey().toString())+" where prop_val_id in ("+tmp_sql+"))");
//					sql.append(" and exists (select question_id from "+Constants.getPropNameAll().get(entry.getKey().toString())+" where t.id = question_id and prop_val_id in ("+tmp_sql+") group by question_id having count(question_id)>="+propValList.size()+")");
				}
			}
		}
		
		
		if(query.getSubTypeIds()!=null && !StringUtils.checkNull(query.getSubTypeIds())){
			sql.append("and exists (SELECT 1 FROM exam_question_type tx where tx.id=t2.sub_type_id START WITH tx.ID in ("+query.getSubTypeIds()+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID)");
		} else if(query.getSubTypeId()!=null){
			list.add(query.getSubTypeId());
			sql.append("and exists (SELECT 1 FROM exam_question_type tx where tx.id=t2.sub_type_id START WITH tx.ID =? CONNECT BY PRIOR tx.ID = tx.PARENT_ID)");
		}
		
		if(null!=query.getOrderItem()){
			String order  = "";
			for(int i=0;i<query.getOrderItem().length;i++){
				if(query.getOrderItem()[i]==1){
					order += "question_label_id,";
				} else if(query.getOrderItem()[i]==2){
					order += "content,";
				} else if(query.getOrderItem()[i]==3){
					order += "create_date,";
				} else if(query.getOrderItem()[i]==4){
					order += "online_date,";
				}
			}
			sql.append(" order by "+order+"t.id desc");
		}else{
			sql.append(" order by t.id desc");
		}
		return sql.toString();
	}

	public List<ExamQuestion> getQuestionIdByIds(String key, List<Long> ids) throws SQLException {
		String tmp_sql = "";
		if(null!=ids){
			for(int i=0;i<ids.size();i++){
				tmp_sql += ids.get(i)+",";
			}
			tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
		}
		String sql = "select t.* from exam_question t where t.parent_id in ("+tmp_sql+") or t.id in ("+tmp_sql+")";
		
		ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionListMapper);
		return queryForList(key,sql,mapper);
	}

	@Override
	public String[] getQuestionPropList(String key,Long id) throws SQLException {
		
		String tmp[] = new String[2];
		StringBuffer sql = new StringBuffer();
		sql.append("select v.id||'-'||v1.id||'-'||v2.id||'-'||v3.id||'-'||v4.id as code,v.name||'-'||v1.name||'-'||v2.name||'-'||v3.name||'-'||v4.name as name ");	
		sql.append("from sub_sys_prop_val l ");	
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");	
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");	
		sql.append("join prop_val_ref f1 on  l1.id = f1.prop_val1 ");	
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
		sql.append("where l4.prop_val_id in (select p.prop_val_id from exam_question_point p where p.question_id = ?)");
		
		ParameterizedRowMapper<ExamPropVal> mapper = getMapper(RowMapper.class, RowMapper.getQuestionPropListMapper);
		List<ExamPropVal> list = queryForList(key, sql.toString(),
				new Object[] { id }, mapper);
		String _id="";
		String name="";
		
		for (ExamPropVal v : list) {
			if(_id!="") {
				_id += "+" + v.getCode();
				name += "+" + v.getName();
			}else {
				_id += v.getCode();
				name += v.getName();
			}
			
		}
		tmp[0] = _id;
		tmp[1] = name;
		return tmp;
	}

	public void updateQuestionCreateDate(String key, List<Long> id) throws SQLException {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); i++) {
			Object[] values = new Object[] {id.get(i),id.get(i) };
			batch.add(values);
		}
		batchUpdate(key, SQL_UPDATE_QUESTION_CREATE_DATE, batch);
	}

	public List<ExamQuestion> getQuestionByContentAndLabel(String key, ExamQuestion quest) throws SQLException {
		ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionListMapper);
		return queryForList(key,SQL_ISNOT_EXIST_QUESTION,new Object[]{quest.getQuestion_label_id(),quest.getContent()},mapper);
	}

	public void addQuestionSubSysType(String key, ExamQuestion quest) throws SQLException {
		List<ExamSubSysQuestType> list = quest.getSubSysQuestTypeList();
		
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			ExamSubSysQuestType t = list.get(i);
			
			Object[] values = new Object[] { 
					t.getSub_type_id(),
					t.getQuestion_id(),
					t.getSub_sys_id(),
					t.getState()
			};
			
			batch.add(values);
		}
		
		batchUpdate(key,SQL_ADD_QUESTION_TYPE, batch);
	}

	@Override
	public void deleteQuestionSubSysType(String key, List<Object[]> id) throws SQLException {
		batchUpdate(key, SQL_DEL_QUESTION_TYPE, id);
	}

	
	@Override
	public List<ExamSubSysQuestType> getExamSubSysQuestTypeById(String key, Long id) throws SQLException {
		ParameterizedRowMapper<ExamSubSysQuestType> mapper = getMapper(RowMapper.class, RowMapper.getExamSubSysQuestTypeByIdMapper);
		return queryForList(key, SQL_GET_QUESTION_TYPE, new Object[]{id}, mapper);
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIdArr(String key,Long[] idArr) throws SQLException {
		
		String idStr = "";
		if (idArr == null || idArr.length <= 0) {
			return null;
		} else {
			for (int i = 0; i < idArr.length; i++) {
				if ("".equals(idStr)) {
					idStr = String.valueOf(idArr[i]);
				} else {
					idStr += "," + String.valueOf(idArr[i]);
				}
			}
		}
		
		String sql = "select t.*, (select count(1) from exam_question t1 where t1.parent_id = t.id) as childQuestionNum from exam_question t where t.id  in ("+idStr+") order by t.id desc";
		
		ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionChildNumListMapper);
		
		List<ExamQuestion> quesitonList = queryForList(key, sql, mapper);
		
		if (quesitonList != null && quesitonList.size() > 0) {
			for(ExamQuestion q : quesitonList){
				if(q.getChildQuestionNum()>0){
					q.setChildQuestionList(getChildQuestionByParentId(key, q.getId()));
				}
			}
		}
		
		return quesitonList;
	}

	@Override
	public List<ExamQuestion> getQuestionListByPaperId(String key, Long paperId) throws SQLException {
		String sql = "select t1.*,t2.question_score,(select count(1) from exam_question x where x.parent_id = t1.id) as childQuestionNum from exam_testpaper t,exam_question t1,exam_paper_question t2 where t.id = t2.paper_id and t1.id = t2.question_id and t.id = ? order by t1.question_label_id,t2.seq";
		ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperQuestionAndKeyListMapper);
		List<ExamQuestion> quesitonList = queryForList(key,sql, new Object[]{paperId}, mapper);
		
		if (quesitonList != null && quesitonList.size() > 0) {
			for(ExamQuestion q : quesitonList){
				if(q.getChildQuestionNum()>0){
					q.setChildQuestionList(getChildQuestionByParentId(key, q.getId()));
				}
			}
		}
		
		return quesitonList;
	}

	public List<ExamQuestion> getQuestionList(String key, ExamPaperTactic paperTactic,
			int pageSize, int currentPage) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append(SQL_GET_QUESTIONLIST_BY_TACTIC);
		List<Object> list = getParamList(paperTactic, sql);
		
		int countNum = getQuestionListSize(key, paperTactic);
		
		if(countNum<paperTactic.getAmount()){
			return null;
		}
		
		
		if(countNum==paperTactic.getAmount()){
			ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionChildNumListMapper);
			return queryForList(key,sql.toString(),list.toArray(), mapper);
		}
		
		int current = getCurrentPage(countNum,paperTactic.getAmount());
		
		StringBuffer sql_ = new StringBuffer();
		sql_.append("select * from(select row_.*, rownum rownum_ from(");
		sql_.append(sql);
		sql_.append(") row_ where rownum<");
		sql_.append(current+paperTactic.getAmount());
		sql_.append(") where rownum_>=");
		sql_.append(current);
		ParameterizedRowMapper<ExamQuestion> qmapper = getMapper(RowMapper.class, RowMapper.getExamQuestionChildNumListMapper);
		
		return queryForList(key,sql_.toString(), list.toArray(), qmapper);
		
	}

	public int getQuestionListSize(String key,ExamPaperTactic paperTactic) throws SQLException {
		StringBuffer sql = new StringBuffer();

		sql.append(SQL_GET_QUESTIONLIST_BY_TACTIC_SIZE);

		List<Object> list = getParamList(paperTactic, sql);

		return queryForInt(key,sql.toString(),list.toArray());
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Object> getParamList(ExamPaperTactic paperTactic,
			StringBuffer sql) {
		List<Object> list = new ArrayList<Object>();
		
		list.add(paperTactic.getQuestion_label_id());
		
		if(paperTactic.getQuesiton_type_id()!=null && !StringUtils.checkNull(paperTactic.getQuesiton_type_id())){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+paperTactic.getQuesiton_type_id()+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		
		Map<String,List<ExamQuestionProp>> questionPropMap = paperTactic.getQuestionPropMap();
		
		if(null != questionPropMap){
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp_sql = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						ExamQuestionProp questProp = propValList.get(i);
						tmp_sql += questProp.getProp_val_id()+",";
					}
					tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
					sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get(entry.getKey().toString())+" where prop_val_id in ("+tmp_sql+"))");
				}
			}
		}
		
		
		return list;
	}
	
	/**
	 * 
	 * @param num 当前策略的试题总数
	 * @param size 所抽取的试题数量
	 * @return
	 */
	private static int getCurrentPage(int num,int size){
		Random random = new Random();
		int c = random.nextInt((num-size)+1);
		if(c<1){
			return getCurrentPage(num,size);
		}else{
			return c;
		}		
	}

	
	
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(String key,ExamPaperFasterTactic paperTactic) throws SQLException {
		
		StringBuffer sql = new  StringBuffer();
		sql.append(SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC);
		
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
		//题型,数量,分数
		List<ExamPaperFasterTactic1> examPaperFasterTactic1 = paperTactic.getExamPaperFasterTactic1();
		//级联属性
		List<ExamPaperFasterTactic2> examPaperFasterTactic2 = paperTactic.getExamPaperFasterTactic2();
		setProp(examPaperFasterTactic2,null,questionPropMap,2);
		//非级联属性
		List<ExamPaperFasterTactic3> examPaperFasterTactic3 = paperTactic.getExamPaperFasterTactic3();
		setProp(null,examPaperFasterTactic3,questionPropMap,3);
		
		if(paperTactic.getQuestion_type_id()!=null && !StringUtils.checkNull(paperTactic.getQuestion_type_id())){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+paperTactic.getQuestion_type_id()+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		
		setParam(questionPropMap,sql);
		
		for(ExamPaperFasterTactic1 t : examPaperFasterTactic1){
			StringBuffer sql_ = new StringBuffer();
			sql_.append(sql);
			sql_.append(" and q.question_label_id = ? ");
			t.setNum(queryForInt(key, sql_.toString(), new Object[]{t.getQuestion_label_id()}));
		}
		
		return paperTactic;
	}
	
	@SuppressWarnings("unchecked")
	private void setParam(Map<String,List<ExamQuestionProp>> questionPropMap,StringBuffer sql){
		
		if(null != questionPropMap){
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp_sql = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						ExamQuestionProp questProp = propValList.get(i);
						tmp_sql += questProp.getProp_val_id()+",";
					}
					tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
					sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get(entry.getKey().toString())+" where prop_val_id in ("+tmp_sql+")) ");
				}
			}
		}
	}
	
	private void setProp(List<ExamPaperFasterTactic2> tactic2,
			List<ExamPaperFasterTactic3> tactic3,
			Map<String, List<ExamQuestionProp>> questionPropMap, int type) {

		if (type == 2) {
			if (tactic2 != null && !tactic2.isEmpty()) {
				// 一级学科
				List<ExamQuestionProp> prop1 = new ArrayList<ExamQuestionProp>();
				// 二级学科
				List<ExamQuestionProp> prop2 = new ArrayList<ExamQuestionProp>();
				// 三级学科
				List<ExamQuestionProp> prop3 = new ArrayList<ExamQuestionProp>();
				// 单元
				List<ExamQuestionProp> prop4 = new ArrayList<ExamQuestionProp>();
				// 知识点
				List<ExamQuestionProp> prop5 = new ArrayList<ExamQuestionProp>();

				for (ExamPaperFasterTactic2 t : tactic2) {
					if (t.getStudy1_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getStudy1_id());
						if (!prop1.contains(p)) {
							prop1.add(p);
						}
					}
					if (t.getStudy2_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getStudy2_id());
						if (!prop2.contains(p)) {
							prop2.add(p);
						}
					}
					if (t.getStudy3_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getStudy3_id());
						if (!prop3.contains(p)) {
							prop3.add(p);
						}
					}
					if (t.getUnit_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getUnit_id());
						if (!prop4.contains(p)) {
							prop4.add(p);
						}
					}
					if (t.getPoint_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getPoint_id());
						if (!prop5.contains(p)) {
							prop5.add(p);
						}
					}
				}

				if (prop1 != null && !prop1.isEmpty()) {
					questionPropMap.put("1", prop1);
				}
				if (prop2 != null && !prop2.isEmpty()) {
					questionPropMap.put("2", prop2);
				}
				if (prop3 != null && !prop3.isEmpty()) {
					questionPropMap.put("3", prop3);
				}
				if (prop4 != null && !prop4.isEmpty()) {
					questionPropMap.put("4", prop4);
				}
				if (prop5 != null && !prop5.isEmpty()) {
					questionPropMap.put("5", prop5);
				}
			}
			
		} else {
			if (tactic3 != null  && !tactic3.isEmpty()) {
				// 副知识点
				List<ExamQuestionProp> prop1 = new ArrayList<ExamQuestionProp>();
				// 认知水平
				List<ExamQuestionProp> prop2 = new ArrayList<ExamQuestionProp>();
				// 职称
				List<ExamQuestionProp> prop3 = new ArrayList<ExamQuestionProp>();

				for (ExamPaperFasterTactic3 t : tactic3) {

					if (t.getPoint2_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getPoint2_id());
						prop1.add(p);
					}

					if (t.getCognize_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getCognize_id());
						prop2.add(p);
					}

					if (t.getTitle_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getTitle_id());
						prop3.add(p);
					}
				}

				if (prop1 != null && !prop1.isEmpty()) {
					questionPropMap.put("7", prop1);
				}
				if (prop2 != null && !prop2.isEmpty()) {
					questionPropMap.put("8", prop2);
				}
				if (prop3 != null && !prop3.isEmpty()) {
					questionPropMap.put("9", prop3);
				}
			}
		}

	}

	
	@Override
	public void getQuestSizeByFasterTactic(String key, List<ExamPaperFasterTacticX> tXList) throws SQLException {
		
		for(ExamPaperFasterTacticX tx : tXList){
			
			List<Object> list = new ArrayList<Object>();
			StringBuffer sql = new  StringBuffer();
			sql.append(SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC);
			
			if(tx.getQuestion_type_id()!=null && !StringUtils.checkNull(tx.getQuestion_type_id())){
				sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+tx.getQuestion_type_id()+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
			}
			sql.append(" and q.question_label_id = ? ");
			list.add(tx.getQuestion_label_id());
			
			Map<String,List<ExamQuestionProp>> questionPropMap = tx.getQuestionPropMap();
			
			setParam(questionPropMap,sql);
			
			
			List<ExamPaperFasterTactic2> tactic2List = tx.getExamPaperFasterTactic2();
			
			List<ExamPaperFasterTactic2> tactic2TmpList = new ArrayList<ExamPaperFasterTactic2>();
			
			for(int i=0;i<tactic2List.size();i++){
				
				ExamPaperFasterTactic2 tmpObja = new ExamPaperFasterTactic2();
				List<Object> query = new ArrayList<Object>();
				StringBuffer sql_  = new StringBuffer();
				
				ExamPaperFasterTactic2 tmpObj = tactic2List.get(i);
				
				query.addAll(list);
				sql_.append(sql);
				setProp(tmpObj,sql_,query);				
				tmpObj.setAllQuestionNum(queryForInt(key,sql_.toString(), query.toArray()));
				BeanUtils.copyProperties(tmpObj,tmpObja);
				tactic2TmpList.add(tmpObja);
			}
			tx.setExamPaperFasterTactic2(tactic2TmpList);
						
		}
		
	}
	
	private void setProp(ExamPaperFasterTactic2 t2,StringBuffer sql,List<Object> list){
		
		if(t2.getStudy1_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("1")+" where prop_val_id = ?) ");
			list.add(t2.getStudy1_id());
		}
		if(t2.getStudy2_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("2")+" where prop_val_id = ?) ");
			list.add(t2.getStudy2_id());
		}
		if(t2.getStudy3_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("3")+" where prop_val_id = ?) ");
			list.add(t2.getStudy3_id());
		}
		if(t2.getUnit_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("4")+" where prop_val_id = ?) ");
			list.add(t2.getUnit_id());
		}
		if(t2.getPoint_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("5")+" where prop_val_id = ?) ");
			list.add(t2.getPoint_id());
		}		
		
	}

	@Override
	public List<ExamQuestion> getQuestionListByByFasterTactic(String key,
			int questionLableId, String questionTypeId,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap) throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new  StringBuffer();
		sql.append(SQL_GET_QUESTION_LIST_BY_FASTER_TACTIC);
		
		if(questionTypeId!=null && !StringUtils.checkNull(questionTypeId)){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+questionTypeId+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		sql.append(" and q.question_label_id = ? ");
		list.add(questionLableId);
		
		setParam(questionPropMap,sql);
		
		setProp(t2,sql,list);

		
		int countNum = getQuestionListByByFasterTacticSize(key,questionLableId,questionTypeId,t2,questionPropMap);
		
		if(countNum<t2.getSelQuestionNum()){
			return null;
		}
		
		
		if(countNum==t2.getSelQuestionNum()){
			
			ParameterizedRowMapper<ExamQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamQuestionListMapper);
			
			return queryForList(key, sql.toString(), list.toArray(),mapper);
		}
		
		int current = getCurrentPage(countNum,t2.getSelQuestionNum());
		
		StringBuffer sql_ = new StringBuffer();
		sql_.append("select * from(select row_.*, rownum rownum_ from(");
		sql_.append(sql);
		sql_.append(") row_ where rownum<");
		sql_.append(current+t2.getSelQuestionNum());
		sql_.append(") where rownum_>=");
		sql_.append(current);
		
		ParameterizedRowMapper<ExamQuestion> qmapper = getMapper(RowMapper.class, RowMapper.getExamQuestionListMapper);
		
		return queryForList(key,
				sql_.toString(),list.toArray(), qmapper);
	}
	
	
	private int getQuestionListByByFasterTacticSize(String key,
			int questionLableId, String questionTypeId,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap) throws SQLException{
		
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new  StringBuffer();
		sql.append(SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC);
		
		if(questionTypeId!=null && !StringUtils.checkNull(questionTypeId)){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+questionTypeId+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		sql.append(" and q.question_label_id = ? ");
		list.add(questionLableId);
		
		setParam(questionPropMap,sql);
		
		setProp(t2,sql,list);

		return queryForInt(key, sql.toString(), list.toArray());
	}
	

}
