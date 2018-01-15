package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamPaperFasterTacticDAO;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;

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
public class ExamPaperFasterTacticJDBCDAO extends BaseDao implements
		ExamPaperFasterTacticDAO {

	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC = "Insert Into exam_paper_tactic (Id, tactic_name, question_type_id, exam_paper_type_id) Values (?, ?, ?, ?)";

	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC1 = "Insert Into exam_paper_tactic1 (tactic_id, question_label_id, num, score) Values (?, ?, ?, ?)";
	
	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC2 = "Insert Into exam_paper_tactic2 (tactic_id, study1_id, study1_name, study2_id, study2_name, study3_id, study3_name, unit_id, unit_name, point_id, point_name) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC3 = "Insert Into exam_paper_tactic3 (tactic_id, point2_id, point2_name, cognize_id, cognize_name, title_id, title_name) Values (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC = "Delete From exam_paper_tactic Where Id = ?";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC1 = "Delete From exam_paper_tactic1 Where tactic_id = ?";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC2 = "Delete From exam_paper_tactic2 Where tactic_id = ?";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC3 = "Delete From exam_paper_tactic3 Where tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_ID = "select * From exam_paper_tactic Where Id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC1_BY_ID = "select * From exam_paper_tactic1 t1 Where t1.tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC2_BY_ID = "select * From exam_paper_tactic2 t2 Where t2.tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC3_BY_ID = "select * From exam_paper_tactic3 t3 Where t3.tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_PAPERTYPEID = "Select * From exam_paper_tactic t Where Exists (Select * From Exam_Paper_Type T1 Where t.exam_paper_type_id = T1.Id And T1.Code Like Concat((Select T2.Code From Exam_Paper_Type T2 Where Id = ?),'%'))";
	
	
	
	@Override
	public void addExamPaperFasterTactic(String key,
			ExamPaperFasterTactic tactic) throws SQLException {
		Long id = getSeqNextvalForLong(key, "exam_paper_tactic");
		tactic.setId(id);
		update(key, SQL_ADD_EXAM_PAPER_FASTER_TACTIC, new Object[] {
				tactic.getId(), tactic.getTactic_name(),
				tactic.getQuestion_type_id(), tactic.getExam_paper_type_id() });
		
		List<ExamPaperFasterTactic1> tactic1List =  tactic.getExamPaperFasterTactic1();
		
		List<ExamPaperFasterTactic2> tactic2List =  tactic.getExamPaperFasterTactic2();
		
		List<ExamPaperFasterTactic3> tactic3List =  tactic.getExamPaperFasterTactic3();
		
		
		//保存　策略　题型
		for(ExamPaperFasterTactic1 t1: tactic1List){
			t1.setTactic_id(id);
			t1.setNum(null);
			t1.setScore(null);
		}
		addExamPaperFasterTactic1(key,tactic1List);
		
		//保存　策略　级联属性
		for(ExamPaperFasterTactic2 t2 : tactic2List){
			t2.setTactic_id(id);
		}
		addExamPaperFasterTactic2(key,tactic2List);

		//保存　策略　非级联属性
		if((tactic3List!=null) && (!tactic3List.isEmpty())){
			for(ExamPaperFasterTactic3 t3 : tactic3List){
				t3.setTactic_id(id);
			}
			addExamPaperFasterTactic3(key,tactic3List);
		}
	}

	@Override
	public void deleteExamPaperFasterTactic(String key, Long id)
			throws SQLException {
		Object[] params = new Object[]{id};
		update(key, DELETE_EXAM_PAPER_FASTER_TACTIC, params);
		update(key, DELETE_EXAM_PAPER_FASTER_TACTIC1, params);
		update(key, DELETE_EXAM_PAPER_FASTER_TACTIC2, params);
		update(key, DELETE_EXAM_PAPER_FASTER_TACTIC3, params);
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(String key,
			Long id) throws SQLException {
		Object[] params = new Object[]{id};
		
		ParameterizedRowMapper<ExamPaperFasterTactic> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperFasterTacticListMappper);
		List<ExamPaperFasterTactic> tacticList = queryForList(key, SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_ID,params,mapper);
		
		
		if(null!=tacticList && !tacticList.isEmpty()){
			
			ExamPaperFasterTactic tactic = tacticList.get(0);
			
			ParameterizedRowMapper<ExamPaperFasterTactic1> mapper1 = getMapper(RowMapper.class, RowMapper.getExamPaperFasterTactic1ListMapper);
			tactic.setExamPaperFasterTactic1(queryForList(key,SQL_GET_EXAM_PAPER_FASTER_TACTIC1_BY_ID, params, mapper1));
			
			ParameterizedRowMapper<ExamPaperFasterTactic2> mapper2 = getMapper(RowMapper.class, RowMapper.getExamPaperFasterTactic2ListMapper);
			tactic.setExamPaperFasterTactic2(queryForList(key,SQL_GET_EXAM_PAPER_FASTER_TACTIC2_BY_ID, params, mapper2));
			
			ParameterizedRowMapper<ExamPaperFasterTactic3> mapper3 = getMapper(RowMapper.class, RowMapper.getExamPaperFasterTactic3ListMapper);
			tactic.setExamPaperFasterTactic3(queryForList(key,SQL_GET_EXAM_PAPER_FASTER_TACTIC3_BY_ID, params, mapper3));
			
			return tactic;
			
		}else{
			return null;
		}
		
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			String key, ExamPaperFasterTactic tactic) throws SQLException {
		ParameterizedRowMapper<ExamPaperFasterTactic> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperFasterTacticListMappper);
		return queryForList(key,SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_PAPERTYPEID, new Object[]{tactic.getExam_paper_type_id()}, mapper);
	}

	/**
	 * 保存　策略　题型
	 * @param tactic1List
	 * @throws SQLException 
	 */
	private void addExamPaperFasterTactic1(String key, List<ExamPaperFasterTactic1> tactic1List) throws SQLException{
		List<Object[]> batch = new ArrayList<Object[]>();

		for (int i = 0; i < tactic1List.size(); i++) {
			ExamPaperFasterTactic1 t = tactic1List.get(i);
			Object[] values = new Object[] { 
					t.getTactic_id(),
					t.getQuestion_label_id(),
					t.getNum(),
					t.getScore()
			};
			batch.add(values);
		}

		batchUpdate(key,SQL_ADD_EXAM_PAPER_FASTER_TACTIC1, batch);
	}

	/**
	 * 保存　策略　级联属性
	 * @param tactic1List
	 * @throws SQLException 
	 */
	private void addExamPaperFasterTactic2(String key, List<ExamPaperFasterTactic2> tactic2List) throws SQLException{
		
		List<Object[]> batch = new ArrayList<Object[]>();

		for (int i = 0; i < tactic2List.size(); i++) {
			ExamPaperFasterTactic2 t = tactic2List.get(i);
			Object[] values = new Object[] { 
					t.getTactic_id(),
					t.getStudy1_id(),
					t.getStudy1_name(),
					t.getStudy2_id(),
					t.getStudy2_name(),
					t.getStudy3_id(),
					t.getStudy3_name(),
					t.getUnit_id(),
					t.getUnit_name(),
					t.getPoint_id(),
					t.getPoint_name()
			};
			batch.add(values);
		}
		batchUpdate(key,SQL_ADD_EXAM_PAPER_FASTER_TACTIC2, batch);
	}
	
	/**
	 * 保存　策略　非级联属性
	 * @param tactic1List
	 * @throws SQLException 
	 */
	
	private void addExamPaperFasterTactic3(String key, List<ExamPaperFasterTactic3> tactic3List) throws SQLException{
		List<Object[]> batch = new ArrayList<Object[]>();

		for (int i = 0; i < tactic3List.size(); i++) {
			ExamPaperFasterTactic3 t = tactic3List.get(i);
			Object[] values = new Object[] { 
					t.getTactic_id(),
					t.getPoint2_id(),
					t.getPoint2_name(),
					t.getCognize_id(),
					t.getCognize_name(),
					t.getTitle_id(),
					t.getTitle_name()
			};
			batch.add(values);
		}
		batchUpdate(key,SQL_ADD_EXAM_PAPER_FASTER_TACTIC3, batch);
	}
	
}
