package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamPaperDAO;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperQuestion;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

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

	public Long addExamPaper(String key,ExamPaper examPaper) throws SQLException{

		// 子试卷列表
		List<ExamPaper> childPaperList = examPaper.getChildExamPaperList();
		// 试卷试题列表
		List<ExamPaperQuestion> questionList = examPaper.getExamPaperQuestionList();
		
		//设定试卷试题顺序
		if (examPaper.getPaper_mode() == Constants.PAPER_MODE_RM
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_FT
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_FT2) {
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
		//保存试卷和子试卷
		saveExamPaper(key,childPaperList);
		
		//精细试卷
		if(examPaper.getPaper_mode()==Constants.PAPER_MODE_RM){
			List<ExamPaperTactic> paperTacticList = examPaper.getPaperTacticList();
			if(paperTacticList!=null && paperTacticList.size()>0){
				//保存策略
				saveExamPaperTactic(key,paperTacticList);
			}
		}
		
		// 试题试题列表不为空
		
		if(questionList!= null && questionList.size()>0){
			saveExamPaperQuestion(key,questionList);
		}
		return examPaper.getId();
	}

	private void saveExamPaper(String key,List<ExamPaper> examPaperList)  throws SQLException{
		String sql = "insert into exam_testpaper (id, parent_id, child_paper_num, type_id, name, paper_score, paper_mode, question_num, create_date, grade, state, useful_date, paper_plan_type, examination_time, redo_num, isnot_view_score, isnot_exp_paper) values " +
				"(?, ?, ?, ?, ?, ?, ?, ?, to_date(?,'yyyy-mm-dd hh24:mi:ss'), ?, ?, to_date(?,'yyyy-mm-dd hh24:mi:ss'), ?, ?, ?, ?, ?)";
		List<Object[]> params = new ArrayList<Object[]>();
		for(ExamPaper p : examPaperList){
			params.add(			
				new Object[] {
				p.getId(),
				p.getParent_id(),
				p.getChild_paper_num(),
				p.getType_id(),
				p.getName(),
				p.getPaper_score(),
				p.getPaper_mode(),
				p.getQuestion_num(),
				p.getCreate_date(),
				p.getGrade(),
				p.getState(),
				p.getUseful_date(),
				p.getPaper_plan_type(),
				p.getExamination_time(),
				p.getRedo_num(),
				p.getIsnot_view_score(),
				p.getIsnot_exp_paper()
				}
			);
		}
		
		batchUpdate(key, sql, params);
	}
	
	private void saveExamPaperQuestion(String key,List<ExamPaperQuestion> examPaperQuestionList) throws SQLException {
		String sql = "insert into exam_paper_question (id, paper_id, question_id, question_score, seq) values " +
				"(null, ?, ?, ?, ?)";
		
		List<Object[]> params = new ArrayList<Object[]>();
		for(ExamPaperQuestion p : examPaperQuestionList){
			params.add(
				new Object[] {
				p.getPaper_id(),
				p.getQuestion_id(),
				p.getQuestion_score(),
				p.getSeq()
				}
			);
		}
		
		batchUpdate(key, sql, params);
	}
	
	@Override
	public void deleteExamPaper(String key,Long[] idArr) throws SQLException{
		List<Object[]> list = new ArrayList<Object[]>();
		if(idArr != null && idArr.length > 0){
			List<ExamPaper> paperlist = getPaperAndChildPaper(key,idArr);
			if(paperlist != null && paperlist.size() > 0){
				for (int i = 0; i < paperlist.size(); i++) {
					list.add(new Object[] { paperlist.get(i).getId() });
				}
			}
			// 删除试卷试题列表
			deleteExamPaperQuestion(key,list);
			// 删除试卷策略列表
			deleteExamPaperTactic(key,list);
			// 删除试卷列表
			deleteExamPaper(key,list);
		}
	}

	@Override
	public Long getExamPaperId(String key)  throws SQLException{
		return getSeqNextvalForLong(key,"exam_testpaper");
	}

	@Override
	public ExamPaper getExamPaperById(String key,Long id)  throws SQLException{
		String sql = "select t.* from exam_testpaper t, exam_paper_type t1 where t.type_id = t1.id and (t.id=? or t.parent_id=?)";
		List<ExamPaper> list = new ArrayList<ExamPaper>();
		// 试卷列表
		ParameterizedRowMapper<ExamPaper> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperListMapper);
		list = queryForList(key, sql, new Object[] { id, id }, mapper);
		
		if(list!=null && list.size()>0){
			if(list.size()==1 && list.get(0).getParent_id()!=0){
				// 子试卷试题列表
				list.get(0).setExamQuestionList(getExamQuestionListByPaperId(key,list.get(0).getId()));
				// 子试卷试卷试题
				list.get(0).setExamPaperQuestionList(getExamPaperQuestionList(key,list.get(0).getId()));
				return list.get(0);
			}

			ExamPaper examPaper = null;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getParent_id() == 0){
					// 主试卷信息
					examPaper = list.remove(i);
					// 试卷试题列表
					examPaper.setExamPaperQuestionList(getExamPaperQuestionList(key,id));
					
					//随机试卷
					if(examPaper.getPaper_mode() == Constants.PAPER_MODE_RM){
						// 试卷策略列表
						examPaper.setPaperTacticList(getExamPaperTacticList(key,id));
					}
					// 子试卷列表
					examPaper.setChildExamPaperList(list);
					// 试题列表
					examPaper.setExamQuestionList(getExamQuestionListByPaperId(key,id));
					
				}else{
					// 子试卷试题列表
					list.get(i).setExamQuestionList(getExamQuestionListByPaperId(key,list.get(i).getId()));
					// 子试卷试卷试题
					list.get(i).setExamPaperQuestionList(getExamPaperQuestionList(key,list.get(i).getId()));
				}
			}
			return examPaper;			
		}
		return null;
	}

	@Override
	public List<ExamPaper> getExamPaperList(String key, ExamPaperQuery examPaperQuery) throws SQLException{
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		sql.append("select * from exam_testpaper t where exists (select * from exam_paper_type t1 where t.type_id = t1.id and t1.code like CONCAT((select t2.code from exam_paper_type t2 where id = ?),'%'))");
		list.add(examPaperQuery.getType_id());
		
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
			sql.append("and t.state = ?");
		}
		
		if(!StringUtils.checkNull(examPaperQuery.getCreate_date())){
			sql.append(" and to_char(t.create_date,'yyyy-mm-dd') = ? ");
			list.add(examPaperQuery.getCreate_date());
		}		
		
		sql.append(" order by t.id");
		ParameterizedRowMapper<ExamPaper> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperListMapper);
		return queryForList(key, PageUtil.getPageSql(sql.toString(), examPaperQuery.getPageNo(), examPaperQuery
				.getPageSize()), list.toArray(), mapper);
	}

	
	public int getExamPaperListSize(String key,ExamPaperQuery examPaperQuery) throws SQLException {
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		sql.append("select count(1) from exam_testpaper t where exists (select * from exam_paper_type t1 where t.type_id = t1.id and t1.code like CONCAT((select t2.code from exam_paper_type t2 where id = ?),'%'))");
		list.add(examPaperQuery.getType_id());
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
			sql.append("and t.state = ?");
		}
		
		if(!StringUtils.checkNull(examPaperQuery.getCreate_date())){
			sql.append(" and to_char(t.create_date,'yyyy-mm-dd') = ? ");
			list.add(examPaperQuery.getCreate_date());
		}	
		return queryForInt(key, sql.toString(), list.toArray());
	}
	
	@Override
	public void updateExamPaper(String key,ExamPaper examPaper) throws SQLException{
		// 子试卷列表
		List<ExamPaper> childPaperList = examPaper.getChildExamPaperList();
		// 试卷试题列表
		List<ExamPaperQuestion> questionList = examPaper.getExamPaperQuestionList();
		// 存放试卷ID和主试卷ID
		List<Object[]> paperIdList = new ArrayList<Object[]>();
		
		paperIdList.add(new Object[] {examPaper.getId()});
		
		//随机试卷
		if(examPaper.getPaper_mode().intValue() == Constants.PAPER_MODE_RM){
			List<ExamPaperTactic> paperTacticList = examPaper.getPaperTacticList();
			if(paperTacticList!=null){
				//删除策略
				deleteExamPaperTactic(key,paperIdList);
				//保存策略
				saveExamPaperTactic(key,paperTacticList);
			}
			
			if(childPaperList!= null && childPaperList.size()>0){
				for (int i = 0; i < childPaperList.size(); i++) {
					if(childPaperList.get(i).getExamPaperQuestionList()!= null){
						// 将子试卷试卷试题放入试题列表
						questionList.addAll(childPaperList.get(i).getExamPaperQuestionList());
						// 将子试卷ID放入删除操作参数中
						paperIdList.add(new Object[] {childPaperList.get(i).getId()});
					}
				}
			}			
		}
		
		if(questionList != null){
			// 删除试卷试题列表
			deleteExamPaperQuestion(key,paperIdList);
			// 添加试卷试题列表
			saveExamPaperQuestion(key,questionList);
		}
		
		
		// 修改试卷信息
		if(childPaperList != null && childPaperList.size() >0 ){
			childPaperList.add(examPaper);
		}else{
			childPaperList = new ArrayList<ExamPaper>();
			childPaperList.add(examPaper);
		}
		updateExamPaperInfo(key,childPaperList);
	}
	
	/**
	 * 精细策略列表
	 * @param key
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	private List<ExamPaperTactic> getExamPaperTacticList(String key,Long paperId) throws SQLException{
		String sql = "select * from exam_paper_base_tactic t where t.paper_id = ?";
		ParameterizedRowMapper<ExamPaperTactic> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperTacticListMapper);
		return queryForList(key, sql, new Object[]{paperId},  mapper);
	}
	
	
	/**
	 * 保存精细策略
	 * @param paperTacticList
	 */
	private void saveExamPaperTactic(String key,List<ExamPaperTactic> paperTacticList) throws SQLException{
		String sql ="insert into exam_paper_base_tactic " +
				"(id, paper_id, question_label_id, grade, amount, question_score, question_type_id, question_type_name, " +
				"cascade_id, cascade_name, prop_point2_id, prop_point2_name, prop_cognize_id, prop_cognize_name, prop_title_id, prop_title_name) " +
				"values " +
				"(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		List<Object[]> params = new ArrayList<Object[]>();
		for(ExamPaperTactic p : paperTacticList){
			params.add(			
				new Object[] {
					p.getPaper_id(),
					p.getQuestion_label_id(),
					p.getGrade(),
					p.getAmount(),
					p.getQuestion_score(),
					p.getQuesiton_type_id(),
					p.getQuestion_type_name(),
					p.getCascade_id(),
					p.getCascade_name(),
					p.getProp_point2_id(),
					p.getProp_point2_name(),
					p.getProp_cognize_id(),
					p.getProp_cognize_name(),
					p.getProp_title_id(),
					p.getProp_title_name()
				}
			);
		}
		
		batchUpdate(key, sql, params);
	}

	/**
	 * 删除试卷
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaper(String key,List<Object[]> batchArgs) throws SQLException {
		String sql = "delete from exam_testpaper where id = ?";
		batchUpdate(key, sql, batchArgs);
	}
	
	/**
	 * 删除试卷试题
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaperQuestion(String key,List<Object[]> batchArgs) throws SQLException{
		String sql = "delete from exam_paper_question where paper_id = ?";
		batchUpdate(key, sql, batchArgs);
	}
	
	/**
	 * 删除试卷精细策略
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaperTactic(String key,List<Object[]> batchArgs) throws SQLException {
		String sql = "delete from exam_paper_base_tactic where paper_id = ?";
		batchUpdate(key, sql, batchArgs);
	}
	
	/**
	 * 通过试卷ID取主试卷和子试卷
	 * @param idArr
	 * @return
	 */
	private List<ExamPaper> getPaperAndChildPaper(String key,Long[] idArr)  throws SQLException{
		String idStr = "";
		for (int i = 0; i < idArr.length; i++) {
			idStr += idArr[i] + ",";
		}
		if(idStr != null && !idStr.trim().equals("")){
			idStr = idStr.substring(0, idStr.length()-1);
			String sql = "SELECT * FROM exam_testpaper WHERE ID in (" + idStr + ") or PARENT_ID in (" + idStr + ")";
			ParameterizedRowMapper<ExamPaper> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperListMapper);
			return queryForList(key, sql, mapper);
		}else{
			return null;
		}
	}	
	
	
	private void updateExamPaperInfo(String key,List<ExamPaper> examPaperList) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_testpaper set ");
		sql.append("parent_id = ?,");
		sql.append("type_id = ?,");
		sql.append("name = ?,");
		sql.append("paper_score = ?,");
		sql.append("question_num = ?,");
		sql.append("grade = ?,");
		sql.append("state = ?,");
		sql.append("useful_date = to_date(?,'yyyy-MM-dd'),");
		sql.append("paper_plan_type = ?,");
		sql.append("examination_time = ?,");
		sql.append("isnot_view_score = ?,");
		sql.append("isnot_exp_paper = ?,");
		sql.append("redo_num = ?");
		sql.append(" where id = :id");
		
		List<Object[]> params = new ArrayList<Object[]>();
		for(ExamPaper p : examPaperList){
			params.add(			
				new Object[] {
					p.getParent_id(),
					p.getType_id(),
					p.getName(),
					p.getPaper_score(),
					p.getQuestion_num(),
					p.getGrade(),
					p.getState(),
					p.getUseful_date(),
					p.getPaper_plan_type(),
					p.getExamination_time(),
					p.getIsnot_view_score(),
					p.getIsnot_exp_paper(),
					p.getRedo_num(),
					p.getId()
				}
			);
		}
		
		batchUpdate(key, sql.toString(), params);
		
	}
	
	private List<ExamQuestion> getExamQuestionListByPaperId(String key,Long paperId) throws SQLException{
		String sql = "select t.*, (select count(1) from exam_question t2 where t2.parent_id = t.id) as childQuestionNum, t1.question_score from exam_question t, exam_paper_question t1 where t.id = t1.question_id and t1.paper_id = ? order by t.question_label_id, t1.seq";
		String sqlKey = "select * from exam_question_key t where t.question_id = ? order by t.seq";
		List<ExamQuestion> questionList = new ArrayList<ExamQuestion>();
		//主试题列表
		ParameterizedRowMapper<ExamQuestion> questionMapper = getMapper(RowMapper.class, RowMapper.getExamPaperQuestionAndKeyListMapper);
		questionList = queryForList(key, sql, new Object[] { paperId },
				questionMapper);
		
		if(questionList!=null && questionList.size()>0){
			for(int i=0;i<questionList.size();i++){
				// 如果是父试题
				if(questionList.get(i).getParent_id() == 0){
					// 查询子试题
					String childSql = "select * from exam_question q where parent_id = ?";
					ParameterizedRowMapper<ExamQuestion> childQuestionMapper = getMapper(RowMapper.class, RowMapper.getExamPaperQuestionAndKeyListMapper);
					List<ExamQuestion> childQuestionList = queryForList(key, childSql, new Object[]{questionList.get(i).getId()}, childQuestionMapper);
					if(childQuestionList!=null && childQuestionList.size()>0){
						for (int j = 0; j < childQuestionList.size(); j++) {
							// 子试题答案选项
							ParameterizedRowMapper<ExamQuestionKey> childQKmapper = getMapper(RowMapper.class, RowMapper.getExamQuestionKeyListMapper);
							childQuestionList.get(j).setQuestionKeyList(
									queryForList(key, sqlKey,
											new Object[] { childQuestionList
													.get(j).getId() },
											childQKmapper));
						}
					}
					questionList.get(i).setChildQuestionList(childQuestionList);
				}	
				// 主试题答案选项
				ParameterizedRowMapper<ExamQuestionKey> qkmapper = getMapper(RowMapper.class, RowMapper.getExamQuestionKeyListMapper);
				questionList.get(i).setQuestionKeyList(
						queryForList(key, sqlKey, new Object[] { questionList
								.get(i).getId() }, qkmapper));
			}
		}
		
		return questionList;
	}
	
	/**
	 * 试卷试题列表
	 * @param paperId
	 * @return
	 */
	private List<ExamPaperQuestion> getExamPaperQuestionList(String key,Long paperId) throws SQLException{
		String sql = "SELECT * FROM exam_paper_question WHERE PAPER_ID = ? ORDER BY SEQ";
		ParameterizedRowMapper<ExamPaperQuestion> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperQuestionListMapper);
		return queryForList(key, sql, new Object[]{paperId}, mapper);
	}

	public List<ExamPaper> getExamPaperListByExamId(String key,Long examId)  throws SQLException {
		String sql = "select t.* from exam_testpaper t,exam_examination t1,exam_exam_paper t2 where t1.id = t2.exam_id and t2.paper_id = t.id and t.state = 1 and (t.useful_date is null or TO_DATE(TO_CHAR(t.useful_date, 'YYYY-MM-DD'), 'YYYY-MM-DD') >= TO_DATE(TO_CHAR(sysdate(), 'YYYY-MM-DD'), 'YYYY-MM-DD')) and t1.id = ? order by t2.seq";
		ParameterizedRowMapper<ExamPaper> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperListMapper);
		return queryForList(key, sql,  new Object[]{examId}, mapper);
	}

	public List<ExamPaper> getExamPaperAndChildPaper(String key,Long[] idArr)  throws SQLException{
		String idStr = "";
		for (int i = 0; i < idArr.length; i++) {
			idStr += idArr[i] + ",";
		}
		if(idStr != null && !idStr.trim().equals("")){
			idStr = idStr.substring(0, idStr.length()-1);
			String sql = "SELECT * FROM exam_testpaper WHERE ID in (" + idStr + ") or PARENT_ID in (" + idStr + ")";
			ParameterizedRowMapper<ExamPaper> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperListMapper);
			return queryForList(key, sql, mapper);
			
		}else{
			return null;
		}
	}

	@Override
	public void updateExamePaperTypeByPaperId(String key,Long paperTypeId, Long paperId) throws SQLException {
		String sql = "update exam_testpaper t set t.type_id = ? where t.id = ? or t.parent_id = ?";
		update(key, sql, new Object[] { paperTypeId, paperId, paperId });
	}

	@Override
	public void updateExamPaperQuestion(String key,Long paperId, Long oldQuestionID,
			Long newQuestionId, Double score)  throws SQLException {
		
		String seq_sql = "select max(seq) from exam_paper_question where paper_id=? and question_id=?";
		String del_sql = "delete from exam_paper_question where paper_id=? and question_id=?";
		String add_sql = "insert into exam_paper_question (id, paper_id, question_id, question_score, seq) values " +
				"(null, ?, ?, ?, ?)";
		int seq = queryForInt(key, seq_sql, new Object[] { paperId,
				oldQuestionID });
		update(key, del_sql, new Object[] { paperId, oldQuestionID });
		
		ExamPaperQuestion pq = new ExamPaperQuestion();
		pq.setPaper_id(paperId);
		pq.setQuestion_id(newQuestionId);
		pq.setQuestion_score(score);
		pq.setSeq(seq);
		update(key, add_sql,
				new Object[] { paperId, newQuestionId, score, seq });
	}	
}
