package com.hys.exam.dao.remote.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.RowMapper;
import com.hys.exam.dao.remote.ExamExaminationDAO;
import com.hys.exam.model.ExamExaminPaper;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-11
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminationJDBCDAO extends BaseDao implements
		ExamExaminationDAO {

	@Override
	public Long addExamination(String key,ExamExamination exam) throws SQLException {
		Long id = this.getSeqNextvalForLong(key, "exam_examination");
		exam.setId(id);
		String sql = "insert into exam_examination (id, exam_type_id, name, exam_times, exam_time, "
				+ "exam_nature, isnot_see_result, pass_condition, pass_value, state, "
				+ "isnot_see_test_report, start_time, end_time, isnot_decide, isnot_online, "
				+ "exam_type, exam_num, is_cut_screen, cut_screen_num, paper_display_mode, "
				+ "question_display_mode, single_scoring, paper_scoring, create_time, remark, zyy_exam_type, isopen_nextlevel) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, to_date(?,'yyyy-mm-dd hh24:mi:ss'), to_date(?,'yyyy-mm-dd hh24:mi:ss'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate(), ?, ?, ?)";
		Object[] params = {
			id,
			exam.getExam_type_id(),
			exam.getName(),
			exam.getExam_times(),
			exam.getExam_time(),
			exam.getExam_nature(),
			exam.getIsnot_see_result(),
			exam.getPass_condition(),
			exam.getPass_value(),
			exam.getState(),
			exam.getIsnot_see_test_report(),
			exam.getStart_time(),
			exam.getEnd_time(),
			exam.getIsnot_decide(),
			exam.getIsnot_online(),
			exam.getExam_type(),
			exam.getExam_num(),
			exam.getIs_cut_screen(),
			exam.getCut_screen_num(),
			exam.getPaper_display_mode(),
			exam.getQuestion_display_mode(),
			exam.getSingle_scoring(),
			exam.getPaper_scoring(),
			exam.getRemark(),
			exam.getZyy_exam_type(),
			exam.getIsopen_nextlevel()
		};
		
		update(key,sql,params);
		
		
		List<ExamExaminPaper> examPaperList = exam.getExaminPaperList();
		for(ExamExaminPaper ep : examPaperList){
			ep.setExam_id(id);
		}

		saveExamTestPaper(key,examPaperList);
		
		return id;
	}

	@Override
	public void deleteExamination(String key,List<Long> id) throws SQLException {
		String sql = "delete from exam_examination where id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		deleteExamTestPaperByExamId(key,id);
		
		batchUpdate(key, sql, batch);
	}

	@Override
	public ExamExamination getExamExaminationById(String key,Long id) throws SQLException {
		ExamExamination exam = new ExamExamination();
		String sql_ = "select t.* from exam_examination t,exam_exam_type t1 where t.exam_type_id = t1.id and t.id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		ParameterizedRowMapper<ExamExamination> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminationListMapper);
		exam = (ExamExamination) queryForObject(key, sql_, params, mapper);
		exam.setExaminPaperList(getExamTestPaperByExamId(key,id));
		return exam;
	}

	@Override
	public List<ExamExamination> getExaminationList(String key,
			ExamExaminationQuery examExaminationQuery)  throws SQLException {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM EXAM_EXAMINATION WHERE exists (select * from exam_exam_type t1 where exam_type_id = t1.id and code like CONCAT((select code from exam_exam_type where id=?),'%'))");
		list.add(examExaminationQuery.getExam_type_id());
		// 拼查询条件
		setParams(sql, examExaminationQuery, list);
		
		sql.append(" order by id desc");

		ParameterizedRowMapper<ExamExamination> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminationListMapper);
		
		return queryForList(key, PageUtil.getPageSql(sql.toString(),
						examExaminationQuery.getPageNo(),
						examExaminationQuery.getPageSize()), list, mapper);
	}
	

	public int getExaminationListSize(String key,ExamExaminationQuery examExaminationQuery) throws SQLException  {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(1) FROM EXAM_EXAMINATION WHERE exists (select * from exam_exam_type t1 where exam_type_id = t1.id and code like CONCAT((select code from exam_exam_type where id=?),'%'))");
		list.add(examExaminationQuery.getExam_type_id());
		// 拼查询条件
		setParams(sql, examExaminationQuery, list);
		
		return queryForInt(key,sql.toString(), list);
	}
	
	private void setParams(StringBuffer sql,ExamExaminationQuery query,List<Object> list) throws SQLException {
		
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and name like ? ");
			list.add("%"+query.getName()+"%");
		}
		
		if(query.getState()>0){
			sql.append(" and state = ? ");
			list.add(query.getState());
		}
		if(!StringUtils.checkNull(query.getCreate_time())){
			sql.append(" and to_char(create_time,'yyyy-mm-dd') = ? ");
			list.add(query.getCreate_time());
		}
	}

	@SuppressWarnings("unchecked")
	public void updateExaminationById(String key,ExamExamination exam)  throws SQLException {
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update exam_examination set ");
		if(!StringUtils.checkNull(exam.getName())){
			sql.append("name = ?,");
			values.add(exam.getName());
		}
		
		if(null != exam.getExam_times()){
			sql.append("exam_times = ?,");
			values.add(exam.getExam_times());
		}
		if(null != exam.getExam_time()){
			sql.append("exam_time=?,");
			values.add(exam.getExam_time());
		}
		if(null != exam.getExam_nature()){
			sql.append("exam_nature = ?,");
			values.add(exam.getExam_nature());
		}
		if(null != exam.getIs_cut_screen()){
			sql.append("is_cut_screen = ?,");
			values.add(exam.getIs_cut_screen());
		}
		if(null != exam.getIsnot_see_test_report()){
			sql.append("isnot_see_test_report=?,");
			values.add(exam.getIsnot_see_test_report());
		}
		if(null != exam.getPass_condition()){
			sql.append("pass_condition = ?,");
			values.add(exam.getPass_condition());
		}
		if(null != exam.getPass_value()){
			sql.append("pass_value=?,");
			values.add(exam.getPass_value());
		}
		if(null != exam.getState()){
			sql.append("state=?,");
			values.add(exam.getState());
		}
		if(!StringUtils.checkNull(exam.getStart_time())){
			sql.append("start_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),");
			values.add(exam.getStart_time());
		}
		if(!StringUtils.checkNull(exam.getEnd_time())){
			sql.append("end_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),");
			values.add(exam.getEnd_time());
		}
		if(null != exam.getIsnot_decide()){
			sql.append("isnot_decide=?,");
			values.add(exam.getIsnot_decide());
		}
		if(null != exam.getIsnot_online()){
			sql.append("isnot_online=?,");
			values.add(exam.getIsnot_online());
		}
		if(null != exam.getExam_type()){
			sql.append("exam_type=?,");
			values.add(exam.getExam_type());
		}
		if(null != exam.getExam_num()){
			sql.append("exam_num=?,");
			values.add(exam.getExam_num());
		}
		if(null != exam.getCut_screen_num()){
			sql.append("cut_screen_num=?,");
			values.add(exam.getCut_screen_num());
		}
		if(null != exam.getPaper_display_mode()){
			sql.append("paper_display_mode=?,");
			values.add(exam.getPaper_display_mode());
		}
		if(null != exam.getQuestion_display_mode()){
			sql.append("question_display_mode=?,");
			values.add(exam.getQuestion_display_mode());
		}
		if(null != exam.getSingle_scoring()){
			sql.append("single_scoring=?,");
			values.add(exam.getSingle_scoring());
		}
		if(null != exam.getPaper_scoring()){
			sql.append("paper_scoring=?,");
			values.add(exam.getPaper_scoring());
		}
		
		if(!StringUtils.checkNull(exam.getRemark())){
			sql.append("remark = ?,");
			values.add(exam.getRemark());
		}
		
		
		if(null != exam.getIsopen_nextlevel()){
			sql.append("isopen_nextlevel = ?,");
			values.add(exam.getIsopen_nextlevel());
		}
		
		sql.append("create_time = sysdate() where id = ?");
		values.add(exam.getId());
		
		
		List<Long> examId = new ArrayList<Long>();
		examId.add(exam.getId());
		deleteExamTestPaperByExamId(key,examId);
		saveExamTestPaper(key,exam.getExaminPaperList());
		update(key,sql.toString(),values);
	}

	
	@Override
	public List<ExamExamination> getExaminationListByExamTypeId(String key,Long examTypeId)  throws SQLException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ExamExamination> getExaminationListByIds(String key,
			ExamExaminationQuery query) throws SQLException  {
		
		StringBuffer sql = new StringBuffer();
		Long[] idArr = query.getIdArr();
		List list = new ArrayList();
		
		if(idArr == null || idArr.length <= 0){
			return null;
		}
		
		sql.append("select * from exam_examination");
		sql.append(" where id in (");
		for (int i = 0; i < idArr.length; i++) {
			if((idArr.length-1)==i)
				sql.append("?");
			else
				sql.append("?,");
			
			list.add(idArr[i]);
		}
		sql.append(")");
		
		if((!StringUtils.checkNull(query.getName())) && (!query.getName().equals(""))){
			sql.append(" and name like ?");
			list.add("%"+query.getName()+"%");
		}	
		
		if(null != query.getExam_type()){
			sql.append(" and exam_type = ? ");
			list.add(query.getExam_type());
		}
		
		if(null != query.getState()){
			sql.append(" and state = ? ");
			list.add(query.getState());
		}
		if(query.getQueryType()!= null && query.getQueryType().intValue() !=0){
			if(query.getQueryType().intValue() ==1){
				sql.append(" and end_time > sysdate() ");
			}else{
				sql.append(" and end_time <= sysdate() ");
			}
		}
		
		sql.append("order by id");

		ParameterizedRowMapper<ExamExamination> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminationListMapper);
		
		return queryForList(key, PageUtil.getPageSql(sql.toString(),
				query.getPageNo(),
				query.getPageSize()), list, mapper);		
		
	}

	@SuppressWarnings("unchecked")
	public int getExaminationListByIdsSize(String key,ExamExaminationQuery query)  throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		Long[] idArr = query.getIdArr();
		List list = new ArrayList();
		
		if(idArr == null || idArr.length <= 0){
			return 0;
		}		
		sql.append("select count(1) from exam_examination");
		sql.append(" where id in (");
		
		for (int i = 0; i < idArr.length; i++) {
			if((idArr.length-1)==i)
				sql.append("?");
			else
				sql.append("?,");
			
			list.add(idArr[i]);
		}
		sql.append(")");		
		
		if((!StringUtils.checkNull(query.getName())) && (!query.getName().equals(""))){
			sql.append(" and name like ?");
			list.add("%"+query.getName()+"%");
		}	
		
		if(null != query.getExam_type()){
			sql.append(" and exam_type = ?");
			list.add(query.getExam_type());
		}
		
		if(null != query.getState()){
			sql.append(" and state = ? ");
			list.add(query.getState());
		}
		if(query.getQueryType()!= null && query.getQueryType().intValue() !=0){
			if(query.getQueryType().intValue() ==1){
				sql.append(" and end_time > sysdate() ");
			}else{
				sql.append(" and end_time <= sysdate() ");
			}
		}
		return queryForInt(key,sql.toString(),list);
	}	
	
	private void deleteExamTestPaperByExamId(String key,List<Long> id) throws SQLException {
		String sql = "delete from exam_exam_paper where exam_id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		batchUpdate(key, sql, batch);
	}

	private void saveExamTestPaper(String key,List<ExamExaminPaper> examinPaperList) throws SQLException {
		String sql = "insert into exam_exam_paper (exam_id, paper_id, seq) values (?, ?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < examinPaperList.size(); i++) {
			Object[] params = null;
			ExamExaminPaper examPaper = examinPaperList.get(i);
			if(examPaper.getChild_paper_num()!=null && examPaper.getChild_paper_num()>0){
				List<ExamPaper> childPaperList = getExamTestPaperChildIdByParentId(key,examPaper.getPaper_id());
				for(int j=0;j<childPaperList.size();j++){
					ExamPaper childpaper = childPaperList.get(j);
					params = new Object[] { examPaper.getExam_id(), childpaper.getId(), i+1 };	
					batch.add(params);
				}
			}else{
				params = new Object[] { examPaper.getExam_id(),examPaper.getPaper_id(), i+1 };
				batch.add(params);
			}
		}
		batchUpdate(key, sql, batch);
	}
	
	private List<ExamPaper> getExamTestPaperChildIdByParentId(String key,Long id) throws SQLException {
		String sql = "select t.id from exam_testpaper t where t.id = ? or t.parent_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		params.add(id);
		ParameterizedRowMapper<ExamPaper> mapper = getMapper(RowMapper.class, RowMapper.getExamPaperListMapper);
		return queryForList(key, sql, params, mapper);
	}
	
	private List<ExamExaminPaper> getExamTestPaperByExamId(String key,Long id)  throws SQLException {
		String sql = "select t.exam_id,t.paper_id,t.seq,t1.name as paper_name,t1.paper_mode,t1.child_paper_num from exam_exam_paper t,exam_testpaper t1 where t.paper_id = t1.id and t.exam_id = ? and t1.parent_id = 0";
		List<Object> params = new ArrayList<Object>();
		ParameterizedRowMapper<ExamExaminPaper> mapper = getMapper(RowMapper.class, RowMapper.getExamExaminPaperListMapper);
		return queryForList(key,sql, params, mapper);
		
	}

	@Override
	public void updateExaminationTypeByExamId(String key,Long typeId, Long id)  throws SQLException {
		String sql = "update exam_examination set exam_type_id = ? where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(typeId);
		params.add(id);
		update(key,sql,params);
	}	
	
}
