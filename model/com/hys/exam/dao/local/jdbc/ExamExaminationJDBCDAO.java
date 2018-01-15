package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamExaminationDAO;
import com.hys.exam.model.ExamExaminOrg;
import com.hys.exam.model.ExamExaminPaper;
import com.hys.exam.model.ExamExaminUser;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamLog;
import com.hys.exam.model.ExamLogResult;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.exception.FrameworkRuntimeException;

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
public class ExamExaminationJDBCDAO extends BaseDao implements ExamExaminationDAO {

	/**
	 * 考试是否使用，YHQ，2017-05-17
	 * @param ExamExamination exam
	 * @return 考试id
	 */
	@Override
	public boolean  usingExamination(ExamExamination exam) throws FrameworkRuntimeException {
		boolean resFlag = false ;
		if (exam != null && exam.getId() != null) {
			String resSql = "select count(*) from group_class_info where media_id = ? and media_type = 'paper' " ;
			List<Object> argsList = new ArrayList<Object>();
			argsList.add(exam.getId()) ;
			int resNum = getSimpleJdbcTemplate().queryForInt(resSql,argsList.toArray()) ;
			if (resNum > 0) resFlag = true ;
		}		
		return resFlag ;
	}
	
	@Override
	public Long addExamination(ExamExamination exam) {
		Long id = getNextId("exam_examination");
		exam.setId(id);
		//String sql = "insert into exam_examination (id, exam_type_id, name, exam_times, exam_time, exam_nature, isnot_see_result, pass_condition, pass_value, state, isnot_see_test_report, start_time, end_time, isnot_decide, isnot_online, exam_type, exam_num, is_cut_screen, cut_screen_num, paper_display_mode, question_display_mode, single_scoring, paper_scoring, create_time, remark, zyy_exam_type, isopen_nextlevel, site_id) values (:id, :exam_type_id, :name, :exam_times, :exam_time, :exam_nature, :isnot_see_result, :pass_condition, :pass_value, :state, :isnot_see_test_report, to_date(:start_time,'yyyy-mm-dd hh24:mi:ss'), to_date(:end_time,'yyyy-mm-dd hh24:mi:ss'), :isnot_decide, :isnot_online, :exam_type, :exam_num, :is_cut_screen, :cut_screen_num, :paper_display_mode, :question_display_mode, :single_scoring, :paper_scoring, sysdate(), :remark, :zyy_exam_type, :isopen_nextlevel, :siteId)";
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库	
		//这个特殊艹艹艹艹艹用的是长日期，数据库实际是短日期格式
		String sql = "insert into exam_examination (id, exam_type_id, name, exam_times, exam_time, exam_nature, isnot_see_result, pass_condition, pass_value, state, isnot_see_test_report, start_time, end_time, isnot_decide, isnot_online, exam_type, exam_num, is_cut_screen, cut_screen_num, paper_display_mode, question_display_mode, single_scoring, paper_scoring, create_time, remark, zyy_exam_type, isopen_nextlevel, site_id) values (:id, :exam_type_id, :name, :exam_times, :exam_time, :exam_nature, :isnot_see_result, :pass_condition, :pass_value, :state, :isnot_see_test_report, " 
		           + FuncMySQL.shortDateForUpdate(exam.getStart_time()) +" , " + FuncMySQL.shortDateForUpdate(exam.getEnd_time()) + ", :isnot_decide, :isnot_online, :exam_type, :exam_num, :is_cut_screen, :cut_screen_num, :paper_display_mode, :question_display_mode, :single_scoring, :paper_scoring, sysdate(), :remark, :zyy_exam_type, :isopen_nextlevel, :siteId)";
		
		getSimpleJdbcTemplate().update(sql,	new BeanPropertySqlParameterSource(exam));
		
		List<ExamExaminPaper> examPaperList = exam.getExaminPaperList();
		if(examPaperList != null)
		{
			for(ExamExaminPaper ep : examPaperList){
				ep.setExam_id(id);
			}
			saveExamTestPaper(examPaperList);	
		}
		
		List<ExamExaminUser> examUser = exam.getExaminUserList();
		if(examUser != null)
		{
			for(ExamExaminUser user : examUser){
				user.setExam_id(id);
			}
			saveExamUser(examUser);	
		}
		List<ExamExaminUser> examConsider = exam.getExaminConsierList();
		if(examConsider != null)
		{
			for(ExamExaminUser con : examConsider){
				con.setExam_id(id);
			}
			saveExamUser(examConsider);	
		}
		List<ExamExaminOrg> examOrg = exam.getExaminOrgList();
		if(examOrg != null)
		{
			for(ExamExaminOrg org : examOrg){
				org.setExam_id(id);
			}		
			saveExamOrg(examOrg);	
		}				
		return id;
	}

	@Override
	public void deleteExamination(List<Long> id) {
		String sql = "delete from exam_examination where id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		deleteExamTestPaperByExamId(id);
		updateBatch(sql, batch);
	}

	@Override
	public void deleteExaminationList(List<Long> id) {
		String sql = "update exam_examination set state=? where id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { Constants.STATUS_2, id.get(i) };
			batch.add(values);
		}
		deleteExamTestPaperByExamId(id);
		deleteExamOrgByExamId(id);
		deleteExamUserByExamId(id);
		updateBatch(sql, batch);
	}
	
	//恢复
	@Override
	public void recoverExaminationList(List<Long> id){
		String sql = "update exam_examination set state=? where id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { Constants.STATUS_1, id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}

	@Override
	public ExamExamination getExamExaminationById(Long id) {
		ExamExamination exam = new ExamExamination();
		try{
			//String sql = "select t.id,t.exam_type_id,t1.name as exam_type_name,t.name,t.exam_times,t.exam_time,t.exam_nature,t.isnot_see_result,t.pass_condition,t.pass_value,t.state,t.isnot_see_test_report,to_char(t.start_time, 'yyyy-mm-dd hh:mi:ss') as start_time,to_char(t.end_time, 'yyyy-mm-dd hh:mi:ss') as end_time,t.isnot_decide,t.isnot_online,t.exam_type,t.exam_num,t.is_cut_screen,t.cut_screen_num,t.paper_display_mode,t.question_display_mode,t.single_scoring,t.paper_scoring, to_char(t.create_time, 'yyyy-mm-dd hh:mi:ss') as create_time, t.remark from exam_examination t,exam_exam_type t1 where t.exam_type_id = t1.id and t.id = ?";
			String sql_ = "select t.*,t1.name as exam_type_name, t2.alias_name, t2.domain_name " +
					" from exam_examination t left join exam_exam_type t1 on t.exam_type_id = t1.id" +
					" left join system_site t2 on t2.id = t.site_id " +
					" where t.id = ?";
			exam = getJdbcTemplate().queryForObject(sql_, ParameterizedBeanPropertyRowMapper.newInstance(ExamExamination.class),id);
			exam.setExaminPaperList(getExamTestPaperByExamId(id));
			exam.setExaminUserList(getExamUserByExamId(id));
			exam.setExaminConsierList(getExamConsiderByExamId(id));
			exam.setExaminOrgList(getExamOrgByExamId(id));
		}catch(Exception e){
			System.out.println(e);
		}
		return exam;
	}

	@Override
	public List<ExamExamination> getExaminationList(ExamExaminationQuery examExaminationQuery) {
		StringBuffer sql = new StringBuffer("");
		
		String[] commonTypeCode = null;
		
		if(examExaminationQuery.getExam_type_id() != null){
			commonTypeCode = examExaminationQuery.getExam_type_id().split(",");
		}
		
		sql.append("SELECT t.*,t2.NAME as exam_type_name FROM EXAM_EXAMINATION t join EXAM_EXAM_TYPE t2 on t.exam_type_id=t2.id WHERE 1=1");		
		if (commonTypeCode != null && commonTypeCode.length > 0) {
            for (int i = 0; i < commonTypeCode.length; i++) {
                if (i == 0) {
                    sql.append(" and  t2.id = " + commonTypeCode[i]);
                } else {
                    sql.append(" or t2.id = " + commonTypeCode[i]);
                }
            }
        }
		// 拼查询条件
		List<Object> list = new ArrayList<Object>();
		
		if(!StringUtils.checkNull(examExaminationQuery.getName())){
			sql.append(" and t.name like ? ");
			list.add("%"+examExaminationQuery.getName()+"%");
		}
		
		if(!StringUtils.checkNull(examExaminationQuery.getCreate_time())){
			/*sql.append(" and to_char(t.create_time,'yyyy-mm-dd') = '?' ");
			list.add(examExaminationQuery.getCreate_time());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append(" and t.create_time = " + FuncMySQL.shortDateForUpdate(examExaminationQuery.getCreate_time()) + " ");
		}
		
		if(examExaminationQuery.getExam_type() != null && examExaminationQuery.getExam_type() > 0){
			sql.append(" and t.exam_type = ? ");
			list.add(examExaminationQuery.getExam_type());
		}
		
		if(examExaminationQuery.getState() != null && examExaminationQuery.getState() >= 0){
			sql.append(" and t.state = ? ");
			list.add(examExaminationQuery.getState());
		}else{
			sql.append(" and t.state != ? ");
			list.add("-1");
		}
		
		sql.append(" order by t.create_time desc");

		return getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), examExaminationQuery.getPageNo(), examExaminationQuery.getPageSize()),
				ParameterizedBeanPropertyRowMapper.newInstance(ExamExamination.class), list.toArray());		
	}
	

	public int getExaminationListSize(ExamExaminationQuery examExaminationQuery) {
		
		String[] commonTypeCode = null;
		if(examExaminationQuery.getExam_type_id() != null)
		{
			commonTypeCode = examExaminationQuery.getExam_type_id().split(",");
		}
		StringBuffer sql = new StringBuffer("SELECT count(t.id) as exam_type_name FROM EXAM_EXAMINATION t join EXAM_EXAM_TYPE t2 on t.exam_type_id=t2.id WHERE 1=1");		
		
		if (commonTypeCode != null && commonTypeCode.length > 0) {
            for (int i = 0; i < commonTypeCode.length; i++) {
                if (i == 0) {
                    sql.append(" and  t2.id = " + commonTypeCode[i]);
                } else {
                    sql.append(" or t2.id = " + commonTypeCode[i]);
                }
            }
        }
		//setParams(sql, examExaminationQuery, list);
		List<Object> list = new ArrayList<Object>();
		
		if(!StringUtils.checkNull(examExaminationQuery.getName())){
			sql.append(" and t.name like ? ");
			list.add("%"+examExaminationQuery.getName()+"%");
		}
		
		if(!StringUtils.checkNull(examExaminationQuery.getCreate_time())){
			/*sql.append(" and to_char(t.create_time,'yyyy-mm-dd') = '?' ");
			list.add(examExaminationQuery.getCreate_time());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append(" and t.create_time = " + FuncMySQL.shortDateForUpdate(examExaminationQuery.getCreate_time()) + " ");
		}
		
		if(examExaminationQuery.getExam_type() != null && examExaminationQuery.getExam_type() > 0){
			sql.append(" and t.exam_type = ? ");
			list.add(examExaminationQuery.getExam_type());
		}
		
		if(examExaminationQuery.getState() != null && examExaminationQuery.getState() >= 0){
			sql.append(" and t.state = ? ");
			list.add(examExaminationQuery.getState());
		}else{
			sql.append(" and t.state != ? ");
			list.add("-1");
		}
		
		return getSimpleJdbcTemplate().queryForInt(sql.toString(), list.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public void updateExaminationById(ExamExamination exam) {
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update exam_examination set ");
		if(!StringUtils.checkNull(exam.getName())){
			sql.append("name = ?,");
			values.add(exam.getName());
		}
		
		if (null != exam.getExam_type_id()){
			sql.append("exam_type_id = ?,");
			values.add(exam.getExam_type_id());
		}
		
		if (null != exam.getExam_times()){
			sql.append("exam_times = ?,");
			values.add(exam.getExam_times());
		}
		if (null != exam.getExam_time()){
			sql.append("exam_time=?,");
			values.add(exam.getExam_time());
		}
		if (null != exam.getExam_nature()){
			sql.append("exam_nature = ?,");
			values.add(exam.getExam_nature());
		}
		if (null != exam.getIsnot_see_result()){
			sql.append("isnot_see_result = ?,");
			values.add(exam.getIsnot_see_result());
		}
		if (null != exam.getIs_cut_screen()){
			sql.append("is_cut_screen = ?,");
			values.add(exam.getIs_cut_screen());
		}
		if (null != exam.getIsnot_see_test_report()){
			sql.append("isnot_see_test_report=?,");
			values.add(exam.getIsnot_see_test_report());
		}
		if (null != exam.getPass_condition()){
			sql.append("pass_condition = ?,");
			values.add(exam.getPass_condition());
		}
		if (null != exam.getPass_value()){
			sql.append("pass_value=?,");
			values.add(exam.getPass_value());
		}
		if (null != exam.getState()){
			sql.append("state=?,");
			values.add(exam.getState());
		}
		if (!StringUtils.checkNull(exam.getStart_time())){
			/*sql.append("start_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),");
			values.add(exam.getStart_time());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("start_time = " + FuncMySQL.longDateForUpdate() + " ,");
			values.add(exam.getStart_time());
		}
		if (!StringUtils.checkNull(exam.getEnd_time())){
			/*sql.append("end_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),");
			values.add(exam.getEnd_time());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("end_time = " + FuncMySQL.longDateForUpdate() + " ,");
			values.add(exam.getEnd_time());
		}
		if (null != exam.getIsnot_decide()){
			sql.append("isnot_decide=?,");
			values.add(exam.getIsnot_decide());
		}
		if (null != exam.getIsnot_online()){
			sql.append("isnot_online=?,");
			values.add(exam.getIsnot_online());
		}
		if (null != exam.getExam_type()){
			sql.append("exam_type=?,");
			values.add(exam.getExam_type());
		}
		if (null != exam.getExam_num()){
			sql.append("exam_num=?,");
			values.add(exam.getExam_num());
		}
		if (null != exam.getCut_screen_num()){
			sql.append("cut_screen_num=?,");
			values.add(exam.getCut_screen_num());
		}
		if (null != exam.getPaper_display_mode()){
			sql.append("paper_display_mode=?,");
			values.add(exam.getPaper_display_mode());
		}
		if (null != exam.getQuestion_display_mode()){
			sql.append("question_display_mode=?,");
			values.add(exam.getQuestion_display_mode());
		}
		if (null != exam.getSingle_scoring()){
			sql.append("single_scoring=?,");
			values.add(exam.getSingle_scoring());
		}
		if (null != exam.getPaper_scoring()){
			sql.append("paper_scoring=?,");
			values.add(exam.getPaper_scoring());
		}
		
		if (exam.getRemark() != null){
			sql.append("remark = ?,");
			values.add(exam.getRemark());
		}
		
		if (null != exam.getIsopen_nextlevel()){
			sql.append("isopen_nextlevel = ?,");
			values.add(exam.getIsopen_nextlevel());
		}
		
		if (null != exam.getSiteId() && exam.getSiteId()>0){
			sql.append("site_id = ?,");
			values.add(exam.getSiteId());
		}
		
		sql = new StringBuffer(sql.substring(0, sql.length()-1));
		sql.append(" where id = ?");
		values.add(exam.getId());
		
		
		List<Long> examId = new ArrayList<Long>();
		examId.add(exam.getId());
		deleteExamTestPaperByExamId(examId);
		deleteExamOrgByExamId(examId);
		deleteExamUserByExamId(examId);
		
		List<ExamExaminPaper> examPaperList = exam.getExaminPaperList();
		if(examPaperList != null)
		{
			for(ExamExaminPaper ep : examPaperList){
				ep.setExam_id(exam.getId());
			}
			saveExamTestPaper(examPaperList);	
		}
		
		List<ExamExaminUser> examUser = exam.getExaminUserList();
		if(examUser != null)
		{
			for(ExamExaminUser user : examUser){
				user.setExam_id(exam.getId());
			}
			saveExamUser(examUser);	
		}
		List<ExamExaminUser> examConsider = exam.getExaminConsierList();
		if(examConsider != null)
		{
			for(ExamExaminUser con : examConsider){
				con.setExam_id(exam.getId());
			}
			saveExamUser(examConsider);	
		}
		List<ExamExaminOrg> examOrg = exam.getExaminOrgList();
		if(examOrg != null)
		{
			for(ExamExaminOrg org : examOrg){
				org.setExam_id(exam.getId());
			}		
			saveExamOrg(examOrg);	
		}				
				
		getJdbcTemplate().update(sql.toString(),values.toArray());
	}

	
	@Override
	public List<ExamExamination> getExaminationListByExamTypeId(Long examTypeId) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ExamExamination> getExaminationListByIds(
			ExamExaminationQuery query) {
		
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


		return getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), query.getPageNo(), query.getPageSize()),
				ParameterizedBeanPropertyRowMapper.newInstance(ExamExamination.class), list.toArray());
		
		
	}

	@SuppressWarnings("unchecked")
	public int getExaminationListByIdsSize(ExamExaminationQuery query) {
		
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
		
		return getSimpleJdbcTemplate().queryForInt(sql.toString(), list.toArray());
	}	
	
	private void deleteExamTestPaperByExamId(List<Long> id){
		String sql = "delete from exam_exam_paper where exam_id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}
	private void deleteExamOrgByExamId(List<Long> id){
		String sql = "delete from exam_exam_org where exam_id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}
		
	private void deleteExamUserByExamId(List<Long> id){
		String sql = "delete from exam_exam_user where exam_id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}
	private void saveExamTestPaper(List<ExamExaminPaper> examinPaperList){
		String sql = "insert into exam_exam_paper (exam_id, paper_id, seq) values (?, ?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < examinPaperList.size(); i++) {
			Object[] params = null;
			ExamExaminPaper examPaper = examinPaperList.get(i);
			if(examPaper.getChild_paper_num()!=null && examPaper.getChild_paper_num()>0){
				List<ExamPaper> childPaperList = getExamTestPaperChildIdByParentId(examPaper.getPaper_id());
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
		updateBatch(sql, batch);
	}
	private void saveExamUser(List<ExamExaminUser> examinUserList){
		String sql = "insert into exam_exam_user (exam_id, system_user_id, system_user_type) values (?, ?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < examinUserList.size(); i++) {
			Object[] params = null;
			ExamExaminUser examUser = examinUserList.get(i);
			params = new Object[]{examUser.getExam_id(),examUser.getSystem_user_id(),examUser.getSystem_user_type()};
			batch.add(params);
		}
		updateBatch(sql, batch);
	}
	private void saveExamOrg(List<ExamExaminOrg> examinOrgList){
		String sql = "insert into exam_exam_org (exam_id, org_id) values (?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < examinOrgList.size(); i++) {
			Object[] params = null;
			ExamExaminOrg examOrg  = examinOrgList.get(i);
			params = new Object[]{examOrg.getExam_id(),examOrg.getOrg_id()};
			batch.add(params);
		}
		updateBatch(sql, batch);
	}
	private List<ExamPaper> getExamTestPaperChildIdByParentId(Long id){
		String sql = "select t.id from exam_testpaper t where t.id = ? or t.parent_id = ?";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class), id ,id);
	}
	
	private List<ExamExaminPaper> getExamTestPaperByExamId(Long id) {
		String sql = "select t.exam_id,t.paper_id,t.seq,t1.name as paper_name,t1.paper_mode as paper_mode,t1.paper_score as paper_score,t1.question_num as question_num,t1.create_date as create_date,t1.child_paper_num from exam_exam_paper t,exam_testpaper t1 where t.paper_id = t1.id and t.exam_id = ? ";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminPaper.class), id);
	}
	private List<ExamExaminUser> getExamUserByExamId(Long id){
		String sql = "select t.* from exam_exam_user as t where t.exam_id=? and t.system_user_type = 1";
		List<ExamExaminUser> result =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminUser.class), id);
		for(ExamExaminUser eu : result)
		{
			//String u_sql = "select u.*,a.* from system_user as u left join system_account as a on u.id = a.user_id where id = ?";
			//SystemUser user = getJdbcTemplate().queryForObject(u_sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), eu.getSystem_user_id());
			SystemUser user = new SystemUser();
			user = this.returnUserWithProp(eu.getSystem_user_id());
			eu.setUser(user);
		}
		return result;
	}
	private List<ExamExaminUser> getExamConsiderByExamId(Long id){
		String sql = "select t.* from exam_exam_user as t where t.exam_id=? and t.system_user_type = 2";
		List<ExamExaminUser> result =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminUser.class), id);
		for(ExamExaminUser eu : result)
		{
			SystemUser user = new SystemUser();
			user = this.returnUserWithProp(eu.getSystem_user_id());
			eu.setUser(user);
		}
		return result;
	}
	private SystemUser returnUserWithProp(Long userId){
		String u_sql = "select u.*,a.* from system_user as u left join system_account as a on u.id = a.user_id where id = ?";
		SystemUser user = getJdbcTemplate().queryForObject(u_sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), userId);
		
		user.setDeptName("");
		String getPropName = "SELECT p.*  from exam_prop_val p left join sub_sys_prop_val s on p.ID=s.PROP_VAL_ID LEFT JOIN system_user_prop_val v on v.PROP_ID=s.id where v.USER_ID="+user.getUserId();
		String propName = "";
		String propIds = "";
		List<ExamProp> prop = this.getJdbcTemplate().query(getPropName, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class));
		if (prop!=null && prop.size()>0){
			for(int i = 0 ; i < prop.size() ; i++){
				if(i != 0){
					propName += "," + prop.get(i).getName();
					propIds += "," + prop.get(i).getId();
				}else{
					propName += prop.get(i).getName();
					propIds += prop.get(i).getId();
				}
			}
		}
		user.setDeptName(propName);
		user.setPropIds(propIds);
		
		if(user.getWork_unit_id() != null && !user.getWork_unit_id().equals(0L)){
			String hospSql = "select * from exam_hospital where id = " + user.getWork_unit_id(); 
			List<ExamHospital> hosp = getJdbcTemplate().query(hospSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class));
			if(hosp != null && hosp.size() > 0){
				user.getUserConfig().setAddress(hosp.get(0).getHospital_address());
				user.setWorkUnit(hosp.get(0).getName());
			}
		}
		return user;
	}
	private List<ExamExaminOrg> getExamOrgByExamId(Long id){
		String sql = "select t.*,o.* from exam_exam_org as t left join exam_hospital as o on t.org_id = o.id where t.exam_id=?";
		List<ExamExaminOrg> result =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminOrg.class), id);
		for(ExamExaminOrg eo : result)
		{
			String u_sql = "select * from exam_hospital where id = ?";
			ExamHospital org = getJdbcTemplate().queryForObject(u_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class), eo.getOrg_id());
			eo.setOrg(org);
		}
		return result;
	}
	@Override
	public void updateExaminationTypeByExamId(Long typeId, Long id) {
		String sql = "update exam_examination set exam_type_id = ? where id = ?";
		getJdbcTemplate().update(sql,typeId,id); 
	}

	@Override
	public void getExaminationList(PageList pl, ExamExaminationQuery query) {
		
			StringBuffer sql = new StringBuffer("");
			String[] commonTypeCode = null;
			if(query.getExam_type_id() != null){
				commonTypeCode = query.getExam_type_id().split(",");
			}
			
			sql.append("SELECT t.*,t2.NAME as exam_type_name FROM EXAM_EXAMINATION t join EXAM_EXAM_TYPE t2 on t.exam_type_id=t2.id WHERE 1=1");		
			
			String getFullSize = "select count(1) FROM EXAM_EXAMINATION t join EXAM_EXAM_TYPE t2 on t.exam_type_id=t2.id WHERE 1=1";		
						
			if (commonTypeCode != null && commonTypeCode.length > 0) {
	            for (int i = 0; i < commonTypeCode.length; i++) {
	                if (i == 0) {
	                    sql.append(" and t2.id in (" + commonTypeCode[i]);
	                    getFullSize += " and t2.id in (" + commonTypeCode[i];
	                } else {
	                    sql.append(", " + commonTypeCode[i]);
	                    getFullSize +=", " + commonTypeCode[i];
	                }
	            }
	            sql.append(")");
	            getFullSize += ")";
	        }
			// 拼查询条件
			if(!StringUtils.checkNull(query.getName())){
				sql.append(" and t.name like '%"+query.getName()+"%'");
				getFullSize +=" and t.name like '%"+query.getName()+"%'";
			}
			
			if(!StringUtils.checkNull(query.getCreate_time())){
				/*sql.append(" and to_char(t.create_time,'yyyy-mm-dd')="+query.getCreate_time());
				getFullSize +=" and to_char(t.create_time,'yyyy-mm-dd')="+query.getCreate_time();*/
				
				//YHQ,2017-06-22,函数替换，迁移到分布式数据库
				sql.append(" and t.create_time = " + FuncMySQL.shortDateForUpdate(query.getCreate_time()) + " ");
				getFullSize +=" and t.create_time =" + FuncMySQL.shortDateForUpdate(query.getCreate_time()) ;
			}
			
			if(query.getExam_type() != null && query.getExam_type() > 0){
				sql.append(" and t.exam_type ="+query.getExam_type());
				getFullSize +=" and t.exam_type ="+query.getExam_type();
			}
			
			if(query.getState() != null && query.getState() >= 0){
				sql.append(" and t.state ="+query.getState());
				getFullSize +=" and t.state ="+query.getState();
			}
			else
			{
					sql.append(" and t.state != -1");
					getFullSize +=" and t.state !=-1";
			}
			
			sql.append(" order by t.create_time desc");
			Integer fullListSize = getJdbcTemplate().queryForInt(getFullSize);
			pl.setFullListSize(fullListSize);
			List<ExamExamination> result =getList(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), new ArrayList<Object>(), ParameterizedBeanPropertyRowMapper.newInstance(ExamExamination.class));
			
			for(ExamExamination item : result){
				item.setExaminUserList(this.getExamUserByExamId(item.getId()));
				if(item.getExaminUserList() == null)
					item.setCountUsers("0");
				else item.setCountUsers(String.valueOf(item.getExaminUserList().size()));
			}
			pl.setList(result);
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   Long
	 * @time     2017-02-21
	 * 方法说明：  通过考试id查询对应的试卷id
	 */
	public Long queryPaperIdByExamId(Long examId){
		String sql = " select paper_id from exam_exam_paper where exam_id = ? ";
		Long result = getJdbcTemplate().queryForLong(sql, examId);
		return (result==null?null:result); 
		
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-21
	 * @param    Map<String,Object>
	 * @return   Long
	 * 方法说明： 自动发布考试方法(考试 考试试卷 考试类型等)
	 */
	@Transactional
	public Long examAutomaticPublish(Map<String,Object> map){
		//第一步 自动生成考试
		Long examId = getNextId("exam_examination");
		String exam_sql = " insert into exam_examination (exam_type_id,name,exam_times,exam_time,isnot_see_result,pass_condition,pass_value,"
				        + " state,isnot_see_test_report,start_time,end_time,isnot_decide,exam_type,is_cut_screen,paper_display_mode,"
				        + " question_display_mode,single_scoring,paper_scoring,create_time) "
                        + " values(4742,?,60,1,0,1,60,1,1,curdate(),'2018-12-01',1,1,1,1,2,1,2,sysdate()); ";
		getJdbcTemplate().update(exam_sql, map.get("examName"));
		
		//第二步 自动生成考试与试卷的关系
		String exam_paper_sql = " insert into exam_exam_paper (exam_id,paper_id,seq) values(?,?,1)";
		getJdbcTemplate().update(exam_paper_sql, examId,map.get("paperId"));
		return examId;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * 方法说明： 根据试题id 试卷id 查询试题分数
	 */
	public Double queryQuestionScoreById(Long questionId,Long paperId){
		String sql = " select question_score from exam_paper_question where question_id = ? and paper_id = ? ";		
		Double questionScore = (Double)getJdbcTemplate().queryForObject(sql, Double.class, questionId,paperId);
		return questionScore ;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    LogExam
	 * @return   void
	 * 方法说明： 提交单个试题
	 */
	public Long subQuestionOne(ExamLog examLog){
		Long id = getNextId("log_exam");
		examLog.setId(id);
		String sql = " insert into log_exam (id,user_id,site_id,start_date,end_date,last_date,use_time,state,result,isnot_pass,ip,exam_name,exam_type_id,"
				   + " examination_id,exam_type,testpaper_id,right_rate,exam_sub_type_id,correcting_mode,isnot_decide,is_lock)"
                   + " values(?,?,1,sysdate(),sysdate(),sysdate(),0,4 ,?,?,?,?,?,?,?,?,0,0,1,0,0) ";
		getJdbcTemplate().update(sql,examLog.getId(),examLog.getUserId(),examLog.getResult(),examLog.getIsnotPass(),examLog.getIp(),examLog.getExamName(),examLog.getExamTypeId(),examLog.getExaminationId(),examLog.getExamType(),examLog.getTestpaperId());
	    return examLog.getId();
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    ExamLog
	 * @return   void 
	 * 方法说明：  修改用户考试记录
	 */
	public void updateExamLog(ExamLog examLog){
		String sql = " update log_exam set end_date=sysdate(),last_date=sysdate(),isnot_pass = ?,result = ? where user_id = ? and examination_id =? ";
		getJdbcTemplate().update(sql,examLog.getIsnotPass(),examLog.getResult(),examLog.getUserId(),examLog.getExaminationId());
	}
	
	/**
	 * @author 张建国
	 * @time   2017-02-22
	 * @param  ExamLogResult
	 * @return void 
	 * 方法说明：保存考试结果表
	 */
	public void saveQuestionResultLog(ExamLogResult examResultLog){
		String sql = " insert into log_exam_result (log_exam_id,question_id,isnot_right,questionlabel_id,seq,parent_id,select_result,right_count,score,REALITYSCORE) "
                   + " values(?,?,?,?,?,?,?,?,?,?) ";
		getJdbcTemplate().update(sql,examResultLog.getExamLogId(),examResultLog.getQuestionId(),examResultLog.getIsnotRight(),examResultLog.getQuestionLabelId(),examResultLog.getSeq(),examResultLog.getParentId(),examResultLog.getSelectResult(),examResultLog.getRightCount(),examResultLog.getScore(),examResultLog.getRealityscore());
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    ExamLogResult
	 * @return   void
	 * 方法说明： 修改考试结果表
	 */
	public void updateQuestionResultLog(ExamLogResult examResultLog){
		String sql = " update log_exam_result set isnot_right=?,select_result=?,score=?,REALITYSCORE ="+examResultLog.getRealityscore()+" where question_id=? and log_exam_id=? ";
		getJdbcTemplate().update(sql,examResultLog.getIsnotRight(),examResultLog.getSelectResult(),examResultLog.getScore(),examResultLog.getQuestionId(),examResultLog.getExamLogId());
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * @return   ExamLogResult
	 * 方法说明： 查询是否存在考试结果记录
	 */
	public List<ExamLogResult> queryQuestionResultLogById(Long questionId,Long logExamId){
		//根据日志ID统计答题总分
		if(questionId==null){
			String sql = " select * from log_exam_result where log_exam_id=? ";
			List<ExamLogResult> list = new ArrayList<ExamLogResult>();
			list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamLogResult.class),logExamId);
			return list;		
		}
		
		String sql = " select * from log_exam_result where question_id=? and log_exam_id=? ";
		List<ExamLogResult> list = new ArrayList<ExamLogResult>();
		list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamLogResult.class), questionId,logExamId);
		return list;		
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long
	 * @return   ExamExamination
	 * 方法说明：  根据考试Id查询考试信息
	 */
	public ExamExamination queryExamById(Long id){
		String sql = " select * from exam_examination where id = ? ";
		return getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExamination.class), id);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-22
	 * @param    Long 
	 * @return   List<ExamLog>
	 * 方法说明：  根据用户Id 考试Id查询是否参加过考试
	 */
	public List<ExamLog> queryExamLogIsExist(Long userId,Long examId){
		String sql = " select * from log_exam where  user_id = ? and examination_id = ? ";
		List<ExamLog> list = new ArrayList<ExamLog>();
		list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamLog.class), userId,examId);
	    return list;
	}

	@Override
	public void delExamLogResult(ExamLogResult examLogResult) {
		// TODO Auto-generated method stub
		String sql ="DELETE from  log_exam_result where log_exam_id ="+examLogResult.getExamLogId()+"";
		getJdbcTemplate().update(sql);
	}
}
