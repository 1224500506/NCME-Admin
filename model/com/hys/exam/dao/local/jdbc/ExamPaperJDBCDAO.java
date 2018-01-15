package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPaperDAO;
import com.hys.exam.dao.local.ExamPaperFasterTacticDAO;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamPaperQuestion;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.UserImage;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.exception.FrameworkRuntimeException;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-28
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperJDBCDAO extends BaseDao implements ExamPaperDAO {	
	private ExamPaperFasterTacticDAO localExamPaperFasterTacticDAO;		
		
	/**
	 * 试卷是否使用，YHQ，2017-05-17
	 * @param usingExamPaper examPaper
	 * @return 试卷id
	 */
	public boolean  usingExamPaper(ExamPaper examPaper) throws FrameworkRuntimeException {
		boolean resFlag = false ;
		if (examPaper != null && examPaper.getId() != null) {
			String resSql = "select count(*) from group_class_info where media_type = 'paper' and media_id in (select EXAM_ID from exam_exam_paper  where PAPER_ID = ?) " ;
			List<Object> argsList = new ArrayList<Object>();
			argsList.add(examPaper.getId()) ;
			int resNum = getJdbcTemplate().queryForInt(resSql,argsList.toArray()) ;
			if (resNum > 0) resFlag = true ;
		}		
		return resFlag ;
	}

	public Long addExamPaper(ExamPaper examPaper) {		
		// 子试卷列表
		List<ExamPaper> childPaperList = examPaper.getChildExamPaperList();
		// 试卷试题列表
		List<ExamPaperQuestion> questionList = examPaper.getExamPaperQuestionList();
		
		//设定试卷试题顺序
		if (examPaper.getPaper_mode() == Constants.PAPER_MODE_RM
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_FT
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_FT2
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_S) {
			for(int i=0;i<questionList.size();i++){
				questionList.get(i).setSeq(i+1);
			}
		}
		
		if(childPaperList != null && childPaperList.size() > 0 && examPaper.getChild_paper_num()>0){
			//设定试卷试题顺序
			for (int i = 0; i < childPaperList.size(); i++) {
				List<ExamPaperQuestion> childPaperQuestionList = childPaperList.get(i).getExamPaperQuestionList();
				for(int j=0;j<childPaperQuestionList.size();j++){
					childPaperQuestionList.get(j).setSeq(j+1);
				}
				questionList.addAll(childPaperQuestionList);
			}
			childPaperList.add(examPaper);
		}else{
			childPaperList = new ArrayList<ExamPaper>();
			childPaperList.add(examPaper);
		}
		
		//试卷名称重复检查
		if (isDuplicate(examPaper)) {
			return 0L;
		}
		//保存试卷和子试卷
		saveExamPaper(childPaperList);
		
		//精细试卷
		if(examPaper.getPaper_mode()==Constants.PAPER_MODE_RM){
			List<ExamPaperTactic> paperTacticList = examPaper.getPaperTacticList();
			if(paperTacticList!=null && paperTacticList.size()>0){
				//保存策略
				saveExamPaperTactic(paperTacticList);
			}
		}
		
		// 试题试题列表不为空
		if(questionList!= null && questionList.size()>0){
			saveExamPaperQuestion(questionList);
		}
		return examPaper.getId();
	}

	private void saveExamPaper(List<ExamPaper> examPaperList){
		//String sql = "insert into exam_testpaper (id, parent_id, child_paper_num, type_id, name, paper_score, paper_mode, question_num, create_date, grade, state, useful_date, paper_plan_type, examination_time, redo_num, isnot_view_score, isnot_exp_paper) values (:id, :parent_id, :child_paper_num, :type_id, :name, :paper_score, :paper_mode, :question_num, to_date(:create_date,'yyyy-mm-dd hh24:mi:ss'), :grade, :state, to_date(:useful_date,'yyyy-mm-dd hh24:mi:ss'), :paper_plan_type, :examination_time, :redo_num, :isnot_view_score, :isnot_exp_paper)";
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		//艹艹艹艹艹艹艹艹数据库实际存的是date艹艹艹艹艹艹艹
		String sql = "insert into exam_testpaper (id, parent_id, child_paper_num, type_id, name, paper_score, paper_mode, question_num, create_date, grade, state, useful_date, paper_plan_type, examination_time, redo_num, isnot_view_score, isnot_exp_paper) values (:id, :parent_id, :child_paper_num, :type_id, :name, :paper_score, :paper_mode, :question_num, " 
		       + FuncMySQL.shortDateForInsert("create_date") + " , :grade, :state, " + FuncMySQL.shortDateForInsert("useful_date") + " , :paper_plan_type, :examination_time, :redo_num, :isnot_view_score, :isnot_exp_paper)";
		saveList(sql, examPaperList);
	}
	
	private void saveExamPaperQuestion(List<ExamPaperQuestion> examPaperQuestionList){
		String sql = "insert into exam_paper_question (id, paper_id, question_id, question_score, seq) values (null, :paper_id, :question_id, :question_score, :seq)";
		saveList(sql, examPaperQuestionList);
	}
	
	@Override
	public void deleteExamPaper(Long[] idArr) {
		List<Object[]> list = new ArrayList<Object[]>();
		if(idArr != null && idArr.length > 0){
			List<ExamPaper> paperlist = getPaperAndChildPaper(idArr);
			if(paperlist != null && paperlist.size() > 0){
				for (int i = 0; i < paperlist.size(); i++) {
					list.add(new Object[] { paperlist.get(i).getId() });
				}
			}
			// 删除试卷试题列表
			deleteExamPaperQuestion(list);
			// 删除试卷策略列表
			deleteExamPaperTactic(list);
			// 删除试卷列表
			deleteExamPaper(list);
		}
	}

	@Override
	public Long getExamPaperId() {
		return getNextId("exam_testpaper");
	}

	@Override
	public ExamPaper getExamPaperById(Long id) {
		//String sql = "select t.*, t1.name as paper_type_name from exam_testpaper t, exam_paper_type t1 where t.type_id = t1.id and (t.id=? or t.parent_id=?)";
		String sql = "select t.*, t1.name as paper_type_name from exam_testpaper t, exam_paper_type t1 where t.type_id = t1.id and t.id=?";
		List<ExamPaper> list = new ArrayList<ExamPaper>();
		// 试卷列表
		list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class), id);
		
		if (list!=null && list.size()>0) {
			/*if (list.size()==1 && list.get(0).getParent_id()!=0) {
				// 子试卷试题列表
				list.get(0).setExamQuestionList(getExamQuestionListByPaperId(list.get(0).getId()));
				// 子试卷试卷试题
				list.get(0).setExamPaperQuestionList(getExamPaperQuestionList(list.get(0).getId()));
				return list.get(0);
			}*/

			ExamPaper examPaper = null;
			for (int i = 0; i < list.size(); i++) {
				// 主试卷信息
				examPaper = list.remove(i);
				// 试卷试题列表
				examPaper.setExamPaperQuestionList(getExamPaperQuestionList(id));
				
				//随机试卷
				if(examPaper.getPaper_mode().equals(Constants.PAPER_MODE_KJ1) ||
				   examPaper.getPaper_mode().equals(Constants.PAPER_MODE_JX)||
				   examPaper.getPaper_mode().equals(Constants.PAPER_MODE_JZJ)) {
					// 试卷策略列表
					getExamPaperFasterTactic(examPaper);
				}
				// 子试卷列表
				examPaper.setChildExamPaperList(list);
				// 试题列表
				examPaper.setExamQuestionList(getExamQuestionListByPaperId(id));					
			}
			return examPaper;			
		}
		return null;
	}

	@Override
	public List<ExamPaper> getExamPaperList(PageList pl,ExamPaperQuery examPaperQuery, String createDateFrom, String createDateTo) {
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		sql.append("select t.*, et.name as type_name from exam_testpaper t left join exam_paper_type et on t.type_id = et.id where t.id>0 ");
		
		String getPaperFullSize = "select t.id from exam_testpaper t where id>0 ";
		
		
		if(examPaperQuery.getType_id() != null)
		{
			sql.append(" and exists (select * from exam_paper_type t1 where t.type_id = t1.id and t1.code like CONCAT((select t2.code from exam_paper_type t2 where id = ?),'%'))");
			getPaperFullSize +=" and exists (select * from exam_paper_type t1 where t.type_id = t1.id and t1.code like CONCAT((select t2.code from exam_paper_type t2 where id = ?),'%'))";
			list.add(examPaperQuery.getType_id());
		}
		
		
		if(!StringUtils.checkNull(examPaperQuery.getName())){
			sql.append(" and t.name like ?");
			getPaperFullSize += " and t.name like ?";
			list.add("%" + examPaperQuery.getName().trim() + "%");
		}
		if(examPaperQuery.getPaper_mode()!=null && examPaperQuery.getPaper_mode()>0){
			sql.append(" and t.paper_mode = ?");
			getPaperFullSize +=" and t.paper_mode = ?";
			list.add(examPaperQuery.getPaper_mode());
		}
		if(examPaperQuery.getState()!=null && examPaperQuery.getState()>0){
			sql.append(" and t.state = ?");
			getPaperFullSize += " and t.state = ?";
			list.add(examPaperQuery.getState());
		}
		
		if(!StringUtils.checkNull(examPaperQuery.getCreate_date())){
			/*sql.append(" and to_char(t.create_date,'yyyy-mm-dd') = ?");
			getPaperFullSize += " and to_char(t.create_date,'yyyy-mm-dd')= ?";
			list.add("'"+examPaperQuery.getCreate_date()+"'");*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库			
			sql.append(" and t.create_date = " + FuncMySQL.shortDateForUpdate(examPaperQuery.getCreate_date()) + " ");
			getPaperFullSize += " and t.create_date = " + FuncMySQL.shortDateForUpdate(examPaperQuery.getCreate_date()) + " " ;
		}
		if(!StringUtils.checkNull(createDateFrom)){
			/*sql.append(" and to_char(t.create_date,'yyyy-mm-dd') >= ?");
			getPaperFullSize +=" and to_char(t.create_date,'yyyy-mm-dd') >= ?";
			list.add(createDateFrom);*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append(" and t.create_date >= " + FuncMySQL.shortDateForUpdate(createDateFrom) + " ");
			getPaperFullSize +=" and t.create_date >= " + FuncMySQL.shortDateForUpdate(createDateFrom) + " " ;
		}
		if(!StringUtils.checkNull(createDateTo)){
			/*sql.append(" and to_char(t.create_date,'yyyy-mm-dd') <= ?");
			getPaperFullSize +=" and to_char(t.create_date,'yyyy-mm-dd') <= ?";
			list.add(createDateTo);*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append(" and t.create_date <= " + FuncMySQL.shortDateForUpdate(createDateTo) + " ");
			getPaperFullSize +=" and t.create_date <= " + FuncMySQL.shortDateForUpdate(createDateTo) + " ";
		}
		if(!StringUtils.checkNull(examPaperQuery.getType_ids())){
			String[] typeids = examPaperQuery.getType_ids().split(",");
			for(int i=0; i<typeids.length; i++){
				if(i == 0){
					sql.append(" and (t.type_id ="+typeids[i]);
					getPaperFullSize +=" and (t.type_id ="+typeids[i];
				}
				else{
					sql.append(" or t.type_id="+typeids[i]);
					getPaperFullSize +=" or t.type_id="+typeids[i];
				}
			
			}
			sql.append(")");
			getPaperFullSize +=")";
		}
		
		sql.append(" order by t.id desc");
		List<ExamPaper> paperListSize = getJdbcTemplate().query(getPaperFullSize, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class), list.toArray());
		if (paperListSize != null && paperListSize.size() > 0)
		{
			pl.setFullListSize(paperListSize.size());
		}
		else pl.setFullListSize(0);
		List<ExamPaper> paperList = getList(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), list, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class)); 
		return paperList;
	}

	
	public int getExamPaperListSize(ExamPaperQuery examPaperQuery){
		StringBuffer sql = new StringBuffer();
		String sqlall="select count(1) from exam_testpaper where state=1";
		List<Object> list = new ArrayList<Object>();
 		if(examPaperQuery.getType_id()!=null && examPaperQuery.getType_id()>0){
			sql.append("select count(1) from exam_testpaper t where exists (select * from exam_paper_type t1 where t.type_id = t1.id and t1.code like CONCAT((select t2.code from exam_paper_type t2 where id = ?),'%'))");
	 		list.add(examPaperQuery.getType_id());
		}
		else{
			return 	getSimpleJdbcTemplate().queryForInt(sqlall);
			
		}
		if(!StringUtils.checkNull(examPaperQuery.getName())){
			list.add("%"+examPaperQuery.getName()+"%");
			sql.append(" and t.name like ? ");
		}
		if(examPaperQuery.getPaper_mode()!=null && examPaperQuery.getPaper_mode()>0){
			list.add(examPaperQuery.getPaper_mode());
			sql.append(" and t.paper_mode = ?");
		}
		if(examPaperQuery.getState()!=null && examPaperQuery.getState()>0){
			list.add(examPaperQuery.getState());
			sql.append(" and t.state = ?");
		}
		
		if(!StringUtils.checkNull(examPaperQuery.getCreate_date())){
			/*sql.append(" and to_char(t.create_date,'yyyy-mm-dd') = ? ");
			list.add(examPaperQuery.getCreate_date());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append(" and t.create_date = " + FuncMySQL.shortDateForUpdate(examPaperQuery.getCreate_date()) + " ");
		}	
		return getSimpleJdbcTemplate().queryForInt(sql.toString(), list.toArray());
	}
	/**
	 *@author Alisa's
	 *@param examPaper
	 *@description update paper Information by examPaper 
	 * */
	@Override
	public String updateExamPaper(ExamPaper paper) {
		
		List<ExamPaper> paperList = new ArrayList<ExamPaper>();
		paperList.add(paper);
		List<ExamPaperQuestion> questionList = paper.getExamPaperQuestionList();
		
		if (!isDuplicate(paper)) { // check paper duplicate
			//设定试卷试题顺序
			if (paper.getPaper_mode() == Constants.PAPER_MODE_RM
					|| paper.getPaper_mode() == Constants.PAPER_MODE_FT
					|| paper.getPaper_mode() == Constants.PAPER_MODE_FT2
					|| paper.getPaper_mode() == Constants.PAPER_MODE_S) {
				for(int i=0;i<questionList.size();i++){
					questionList.get(i).setSeq(i+1);
				}
			}
			
			updateExamPaperInfo(paperList);
			
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(new Object[] { paper.getId() });
			
			//精细试卷
			if (paper.getPaper_mode().equals(Constants.PAPER_MODE_KJ1) || paper.getPaper_mode().equals(Constants.PAPER_MODE_JX) || paper.getPaper_mode().equals(Constants.PAPER_MODE_JZJ)) {
				if (paper.getIsnot_save_tactic().equals(1)) {
					//delete old tactic info.
					deleteExamPaperTactic(list);
					//保存策略
					localExamPaperFasterTacticDAO.addExamPaperFasterTactic(paper);
				}
			}
			
			// 试题试题列表不为空
			if(questionList!= null && questionList.size()>0){
				// 删除试卷试题列表
				deleteExamPaperQuestion(list);
				saveExamPaperQuestion(questionList);
			}
			return "success";
		} else {
			return "duplicate";
		}
		

	}
	
	/**
	 *@author Alisa's
	 *@param paperName
	 *@description check paper's duplicate
	 * */
	private boolean isDuplicate(ExamPaper paper) {
		String sql = "select count(1) from exam_testpaper where id != ?";
		if (!StringUtils.checkNull(paper.getName())) {
			sql += " and name = ?";
		}
		// 按名称得到试卷
		int count = getJdbcTemplate().queryForInt(sql, paper.getId(), paper.getName());
		if (count > 0) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public void updateBatchExamPaper(ExamPaper paper, Long[] id) {
	}
	
	private List<ExamPaperTactic> getExamPaperTacticList(Long paperId){
		String sql = "select * from exam_paper_base_tactic t where t.paper_id = ?";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperTactic.class), paperId);
	}

	
	private void getExamPaperFasterTactic(ExamPaper paper) {
		
		//Get the tactic list.
		String sql = "select * from exam_paper_tactic where paper_id = ?";
		List<ExamPaperFasterTactic> tacticList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperFasterTactic.class), paper.getId());
		
		Long examFasterTacticId = null;
		if (tacticList != null && tacticList.size() > 0) {
			examFasterTacticId = tacticList.get(0).getId();
		}
		
		if (examFasterTacticId != null && examFasterTacticId != 0) {
			ExamPaperFasterTactic pft = new ExamPaperFasterTactic();
			
			//Get tactic1 list.
			String fasterTactic1_sql = "select * from exam_paper_tactic1 where tactic_id = ?";
			List<ExamPaperFasterTactic1> tactic1List = getJdbcTemplate().query(fasterTactic1_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperFasterTactic1.class),examFasterTacticId);
			
			for (ExamPaperFasterTactic1 tactic1: tactic1List) {
				//Get label by label id.
				String label_sql = "select * from exam_question_label where id = ?";
				List<ExamQuestionLabel> labelList = getJdbcTemplate().query(label_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionLabel.class), tactic1.getQuestion_label_id());
				if (labelList != null && labelList.size() > 0) {
					tactic1.setLabel(labelList.get(0));
				}
			}
			pft.setExamPaperFasterTactic1(tactic1List);
			
			String fasterTactic2_sql = "select * from exam_paper_tactic2 where tactic_id = ?";
			List<ExamPaperFasterTactic2> tactic2List = getJdbcTemplate().query(fasterTactic2_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperFasterTactic2.class),examFasterTacticId);
			pft.setExamPaperFasterTactic2(tactic2List);
			
			String fasterTactic3_sql = "select * from exam_paper_tactic3 where tactic_id = ? and cognize_id != 0";
			List<ExamPaperFasterTactic3> tactic3List = getJdbcTemplate().query(fasterTactic3_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperFasterTactic3.class),examFasterTacticId);
			pft.setExamPaperFasterTactic3(tactic3List);
			
			String fasterTactic4_sql = "select * from exam_paper_tactic3 where tactic_id = ? and source_id != 0";
			List<ExamPaperFasterTactic3> tactic4List = getJdbcTemplate().query(fasterTactic4_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperFasterTactic3.class),examFasterTacticId);
			pft.setExamPaperFasterTactic4(tactic4List);
			
			paper.setPaperFasterTactic(pft);
		}
		if (paper.getPaper_mode().equals(Constants.PAPER_MODE_S)) {  // 卷中卷		
			// To get the IDs of referred papers.
			String sql_getPaperIdArr = "select paper_id_ref from exam_paper_tactic1_paper where paper_id = ?";
			List<Long> paperIdList = getJdbcTemplate().queryForList(sql_getPaperIdArr, Long.class, paper.getId());
			
			Long paperIdArr[] = {};
			paperIdArr = paperIdList.toArray(paperIdArr);
			paper.setPaperIdArr(paperIdArr);
		}
	}
	
	
	/**
	 * 保存策略
	 * @param paperTacticList
	 */
	private void saveExamPaperTactic(List<ExamPaperTactic> paperTacticList){
		String sql ="insert into exam_paper_base_tactic (null, paper_id, question_label_id, grade, amount, question_score, question_type_id, cascade_id, cascade_name, prop_point2_id, prop_point2_name, prop_cognize_id, prop_cognize_name, prop_title_id, prop_title_name, question_type_name) values (:id, :paper_id, :question_label_id, :grade, :amount, :question_score, :question_type_id, :cascade_id, :cascade_name, :prop_point2_id, :prop_point2_name, :prop_cognize_id, :prop_cognize_name, :prop_title_id, :prop_title_name, :question_type_name)";
		saveList(sql, paperTacticList);
	}

	/**
	 * 删除试卷
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaper(List<Object[]> batchArgs){
		String d_sql = "delete from exam_exam_paper where paper_id=?";
		getSimpleJdbcTemplate().batchUpdate(d_sql, batchArgs);
		
		String sql = "delete from exam_testpaper where id = ?";
		getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 删除试卷试题
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaperQuestion(List<Object[]> batchArgs){
		String sql = "delete from exam_paper_question where paper_id = ?";
		getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 删除试卷策略
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaperTactic(List<Object[]> batchArgs){
		
		String sql = "delete from exam_paper_base_tactic where paper_id = ?";
		getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
		
		//Delete tactic.
		for (Object[] args:batchArgs) {
			
			for(Object arg : args) {
//				String sql_getTacticID = "select id from exam_paper_tactic where paper_id = ?";
				String sql_getTacticID = "select id from exam_paper_base_tactic where paper_id = ?";
				List<Long> tacticIds = getJdbcTemplate().queryForList(sql_getTacticID, Long.class, arg);
				
				if(tacticIds.size() > 0) {
					
					//delete tactic_paper.
					String delSql = "delete from exam_paper_tactic1_paper where paper_id = ?";
					getSimpleJdbcTemplate().update(delSql,  arg);
					
					delSql = "delete from exam_paper_tactic1 where tactic_id = ?";
					getSimpleJdbcTemplate().update(delSql, tacticIds.get(0));
					
					delSql = "delete from exam_paper_tactic2 where tactic_id = ?";
					getSimpleJdbcTemplate().update(delSql, tacticIds.get(0));
					
					delSql = "delete from exam_paper_tactic3 where tactic_id = ?";
					getSimpleJdbcTemplate().update(delSql, tacticIds.get(0));
					
					delSql = "delete from exam_paper_tactic where paper_id = ?";
					getSimpleJdbcTemplate().update(delSql,  arg);
				}
			}
		}
		
	}
	
	/**
	 * 通过试卷ID取主试卷和子试卷
	 * @param idArr
	 * @return
	 */
	private List<ExamPaper> getPaperAndChildPaper(Long[] idArr){
		String idStr = "";
		for (int i = 0; i < idArr.length; i++) {
			idStr += idArr[i] + ",";
		}
		if(idStr != null && !idStr.trim().equals("")){
			idStr = idStr.substring(0, idStr.length()-1);
			String sql = "SELECT * FROM exam_testpaper WHERE ID in (" + idStr + ")";
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class));
			
		}else{
			return null;
		}
	}	
	
	
	private void updateExamPaperInfo(List<ExamPaper> examPaperList){
		StringBuffer update_sql = new StringBuffer();
		update_sql.append("update exam_testpaper set parent_id = :parent_id, ");
		update_sql.append("child_paper_num = :child_paper_num, ");
		update_sql.append("type_id = :type_id, ");
		update_sql.append("name = :name, ");
		update_sql.append("paper_score = :paper_score, ");
		update_sql.append("grade = :grade, ");
		update_sql.append("question_num = :question_num, ");
		
		//update_sql.append("create_date = to_date(:create_date,'yyyy-MM-dd'), ");
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		update_sql.append("create_date = " + FuncMySQL.shortDateForInsert("create_date") + ", ");
		
		update_sql.append("state = :state, ");
		update_sql.append("examination_time = :examination_time, ");
		update_sql.append("isnot_view_score = :isnot_view_score, ");
		update_sql.append("isnot_exp_paper = :isnot_exp_paper ");
		update_sql.append(" where id = :id ");
		
		saveList(update_sql.toString(), examPaperList);
	}
	
	private List<ExamQuestion> getExamQuestionListByPaperId(Long paperId){
		String sql = "select t.*, (select count(1) from exam_question t2 where t2.parent_id = t.id) as childQuestionNum, t1.question_score from exam_question t, exam_paper_question t1 where t.id = t1.question_id and t1.paper_id = ? order by t.question_label_id, t1.seq";
		String sqlKey = "select * from exam_question_key t where t.question_id = ? order by t.seq";
		
		List<ExamQuestion> questionList = new ArrayList<ExamQuestion>();
		questionList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class),paperId);
		
		if(questionList!=null && questionList.size()>0){
			for(int i=0;i<questionList.size();i++){
				// 如果是父试题
				if(questionList.get(i).getParent_id() == 0){
					// 查询子试题
					String childSql = "select * from exam_question q where parent_id = ?";
					List<ExamQuestion> childQuestionList = getJdbcTemplate().query(childSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class), questionList.get(i).getId());
					if(childQuestionList!=null && childQuestionList.size()>0){
						for (int j = 0; j < childQuestionList.size(); j++) {
							// 子试题选项
							childQuestionList.get(j).setQuestionKeyList(getJdbcTemplate().query(sqlKey, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionKey.class), childQuestionList.get(j).getId()));
						}
					}
					questionList.get(i).setChildQuestionList(childQuestionList);
				}	
				// 试题选项
				questionList.get(i).setQuestionKeyList(getJdbcTemplate().query(sqlKey, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionKey.class), questionList.get(i).getId()));
			}
		}
		
		return questionList;
	}
	
	/**
	 * 试卷试题列表
	 * @param paperId
	 * @return
	 */
	private List<ExamPaperQuestion> getExamPaperQuestionList(Long paperId){
		String sql = "SELECT * FROM exam_paper_question WHERE PAPER_ID = ? ORDER BY SEQ";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperQuestion.class), paperId);
	}

	public List<ExamPaper> getExamPaperListByExamId(Long examId) {
		//String sql = "select t.* from exam_testpaper t,exam_examination t1,exam_exam_paper t2 where t1.id = t2.exam_id and t2.paper_id = t.id and t.state = 1 and (t.useful_date is null or TO_DATE(TO_CHAR(t.useful_date, 'YYYY-MM-DD'), 'YYYY-MM-DD') >= TO_DATE(TO_CHAR(sysdate(), 'YYYY-MM-DD'), 'YYYY-MM-DD')) and t1.id = ? order by t2.seq";
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		String sql = "select t.* from exam_testpaper t,exam_examination t1,exam_exam_paper t2 where t1.id = t2.exam_id and t2.paper_id = t.id and t.state = 1 and (t.useful_date is null or t.useful_date >= sysdate()) and t1.id = ? order by t2.seq";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class),examId);
	}

	public List<ExamPaper> getExamPaperAndChildPaper(Long[] idArr) {
		String idStr = "";
		for (int i = 0; i < idArr.length; i++) {
			idStr += idArr[i] + ",";
		}
		if(idStr != null && !idStr.trim().equals("")){
			idStr = idStr.substring(0, idStr.length()-1);
			String sql = "SELECT * FROM exam_testpaper WHERE ID in (" + idStr + ") or PARENT_ID in (" + idStr + ")";
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class));
			
		}else{
			return null;
		}
	}

	@Override
	public void updateExamePaperTypeByPaperId(Long paperTypeId, Long paperId) {
		String sql = "update exam_testpaper t set t.type_id = ? where t.id = ? or t.parent_id = ?";
		getJdbcTemplate().update(sql,paperTypeId,paperId,paperId);
	}

	@Override
	public void updateExamPaperQuestion(Long paperId, Long oldQuestionID,
			Long newQuestionId, Double score) {
		
		String seq_sql = "select max(seq) from exam_paper_question where paper_id=? and question_id=?";
		String del_sql = "delete from exam_paper_question where paper_id=? and question_id=?";
		String add_sql = "insert into exam_paper_question (id, paper_id, question_id, question_score, seq) values (null, :paper_id, :question_id, :question_score, :seq)";
		int seq = getJdbcTemplate().queryForInt(seq_sql);
		getJdbcTemplate().update(del_sql, paperId,oldQuestionID);
		
		ExamPaperQuestion pq = new ExamPaperQuestion();
		pq.setPaper_id(paperId);
		pq.setQuestion_id(newQuestionId);
		pq.setQuestion_score(score);
		pq.setSeq(seq);
		getSimpleJdbcTemplate().update(add_sql, new BeanPropertySqlParameterSource(pq));
	}

	@Override
	public int getExamCountByPaperIds(Integer labelId, String paperIds) {
		
		String GET_SQL = "select count(DISTINCT eq.id) from EXAM_TESTPAPER tp LEFT JOIN EXAM_PAPER_QUESTION pq on TP.ID = PQ.PAPER_ID LEFT JOIN EXAM_QUESTION eq on pq.question_id=eq.id where eq.question_label_id = ? and TP.id in (" + paperIds + ")";
			try
		{
			return getJdbcTemplate().queryForInt(GET_SQL, labelId);
		}catch(Exception e)
		{
			return 0;
		}
		
	}

	@Override
	public boolean updateContral(ExamPaperQuery paper) {
		
		String sql = "update exam_testpaper t set t.type_id = ? where t.id = ?";
		if(getJdbcTemplate().update(sql,paper.getType_id(),paper.getId())>0)
		    return true;	  
		else
			return false;
    }
	
	public ExamPaperFasterTacticDAO getLocalExamPaperFasterTacticDAO() {
		return localExamPaperFasterTacticDAO;
	}
	public void setLocalExamPaperFasterTacticDAO(ExamPaperFasterTacticDAO localExamPaperFasterTacticDAO) {
		this.localExamPaperFasterTacticDAO = localExamPaperFasterTacticDAO;
	}
	
}
