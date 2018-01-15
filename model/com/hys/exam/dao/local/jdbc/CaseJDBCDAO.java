package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.common.util.DateUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CaseDAO;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;


public class CaseJDBCDAO extends BaseDao
		implements CaseDAO {
	
	public Long addPateint(CasePatient newPatient) {
		// TODO Auto-generated method stub
		Long id = getNextId("CASE_PATIENT");
		newPatient.setId(id);
		//String sql = "insert into CASE_PATIENT (ID,DIAGDATE, PNAME, SEX,BIRTHDAY,CERTIFICATE,NATIONAL_STATE,NUMBER1TYPE,NUMBER1,NUMBER2TYPE,NUMBER2,PHONENUMBER1,PHONENUMBER2,DIAGNAME) values (:id, to_date(:DiagDate,'yyyy-mm-dd'), :pName, :sex,to_date(:birthday,'yyyy-mm-dd'), :Certificate, :NationalState, :Number1Type, :Number1, :Number2Type, :Number2, :phoneNumber1, :phoneNumber2, :diagName)";
				
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		String diagDateSQL = FuncMySQL.shortDateForInsert(newPatient.getDiagDate(), "DiagDate") ;			
		String birthdaySQL = FuncMySQL.shortDateForInsert(newPatient.getBirthday(), "birthday") ;						
		String sql = "insert into CASE_PATIENT (ID,DIAGDATE, PNAME, SEX,BIRTHDAY,CERTIFICATE,NATIONAL_STATE,NUMBER1TYPE,NUMBER1,NUMBER2TYPE,NUMBER2,PHONENUMBER1,PHONENUMBER2,DIAGNAME) values (:id, " 
		           + diagDateSQL + ", :pName, :sex," + birthdaySQL + ", :Certificate, :NationalState, :Number1Type, :Number1, :Number2Type, :Number2, :phoneNumber1, :phoneNumber2, :diagName)";
		
		Integer ids = getSimpleJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(newPatient));
		
		return id;
	}
	public Long addPatientProp(CasePatientPropVal newProp){
		Long id = getNextId("CASE_PATIENT_PROP_VAL");
		newProp.setId(id);
		String sql = "insert into CASE_PATIENT_PROP_VAL (ID,PATIENTID,PROPID) values (:id, :patientId, :propId)";
		int result = getSimpleJdbcTemplate().update(sql,
				new BeanPropertySqlParameterSource(newProp));
		return id;
	}
	public Long addPatientDiagnosis(CasePatientDiagnosis newDiagnosis) {
		// TODO Auto-generated method stub
		Long id = getNextId("CASE_PATIENT_DIAGNOSIS");
		newDiagnosis.setId(id);
		String sql = "insert into CASE_PATIENT_DIAGNOSIS (ID,PATIENTID,DIAGNOSIS) values (:id, :patientId, :diagnosis)";
		getSimpleJdbcTemplate().update(sql,
				new BeanPropertySqlParameterSource(newDiagnosis));
		
		return id;
	}
	public Long addDisease(CaseDisease newDisease) {
		// TODO Auto-generated method stub
		Long id = getNextId("CASE_COURSE_DISEASE");
		newDisease.setId(id);
		//String sql = "insert into CASE_COURSE_DISEASE (ID,DISEASEDATE,DISEASETYPE,COMPLAINT,CURRENTDISEASE,HISTORY1,HISTORY2,HISTORY3,HISTORY4,BODYSTATE1TYPE,BODYSTATE1,BODYSTATE2TYPE,BODYSTATE2,BODYSTATE3TYPE,BODYSTATE3,BODYSTATE4TYPE,BODYSTATE4,BODYSTATE5TYPE,BODYSTATE5,BODYSTATE6TYPE,BODYSTATE6,BODYSTATE7TYPE,BODYSTATE7,BODYSTATE8TYPE,BODYSTATE8,ASSAY,ASSAY_FILE,IMAGE,IMAGE_FILE,REST,REST_FILE,PLAN,PLAN_FILE,FUTURESTATE) values (:id, to_date(:diseaseDate,'yyyy-mm-dd'), :diseaseType, :complaint,:currentDisease, :history1, :history2, :history3, :history4, :bodyState1Type, :bodyState1, :bodyState2Type, :bodyState2,:bodyState3Type, :bodyState3,:bodyState4Type, :bodyState4,:bodyState5Type, :bodyState5,:bodyState6Type, :bodyState6,:bodyState7Type, :bodyState7,:bodyState8Type, :bodyState8, :assay, :assayFile,:image,:imageFile, :rest, :restFile, :plan, :planFile, :futureState)";
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		String diseaseDateSQL = FuncMySQL.shortDateForUpdate(newDisease.getDiseaseDate()) ; 		
		String sql = "insert into CASE_COURSE_DISEASE (ID,DISEASEDATE,DISEASETYPE,COMPLAINT,CURRENTDISEASE,HISTORY1,HISTORY2,HISTORY3,HISTORY4,BODYSTATE1TYPE,BODYSTATE1,BODYSTATE2TYPE,BODYSTATE2,BODYSTATE3TYPE,BODYSTATE3,BODYSTATE4TYPE,BODYSTATE4,BODYSTATE5TYPE,BODYSTATE5,BODYSTATE6TYPE,BODYSTATE6,BODYSTATE7TYPE,BODYSTATE7,BODYSTATE8TYPE,BODYSTATE8,ASSAY,ASSAY_FILE,IMAGE,IMAGE_FILE,REST,REST_FILE,PLAN,PLAN_FILE,FUTURESTATE) values (:id, " 
		           + diseaseDateSQL + ", :diseaseType, :complaint,:currentDisease, :history1, :history2, :history3, :history4, :bodyState1Type, :bodyState1, :bodyState2Type, :bodyState2,:bodyState3Type, :bodyState3,:bodyState4Type, :bodyState4,:bodyState5Type, :bodyState5,:bodyState6Type, :bodyState6,:bodyState7Type, :bodyState7,:bodyState8Type, :bodyState8, :assay, :assayFile,:image,:imageFile, :rest, :restFile, :plan, :planFile, :futureState)";
		
		getSimpleJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(newDisease));
		
		return id;
	}
	public Long addCourseDiagnosis(CaseDiseaseDiagnosis newDiagnosis) {
		// TODO Auto-generated method stub
		Long id = getNextId("CASE_COURSE_DIAGNOSIS");
		newDiagnosis.setId(id);
		String sql = "insert into CASE_COURSE_DIAGNOSIS (ID,DISEASEID,DIAGNOSIS) values (:id, :diseaseId, :diagnosis)";
		
		getSimpleJdbcTemplate().update(sql,
				new BeanPropertySqlParameterSource(newDiagnosis));
		
		return id;
	}
	public Long addCourseDisscuss(CaseDiseaseDiscuss newDiscuss){
		// TODO Auto-generated method stub
		Long id = getNextId("CASE_COURSE_DISCUSS");
		newDiscuss.setId(id);
		String sql = "insert into CASE_COURSE_DISCUSS (ID,DISEASEID,PROP,ANALYSIS) values (:id, :diseaseId, :prop, :analysis)";
		
		getSimpleJdbcTemplate().update(sql,
				new BeanPropertySqlParameterSource(newDiscuss));
		
		return id;
	}
	public Long addCase(CaseCase newCase){
		Long id = getNextId("CASE_CASE");
		newCase.setId(id);
		//String sql = "insert into CASE_CASE (ID,P_ID,DISEASE_ID,CREATE_DATE,ONLINE_DATE,STATE,DELI_OPINION,DELI_MAN) values (:id, :pId, :diseaseId, to_date(:createDate,'yyyy-mm-dd'), to_date(:onlineDate,'yyyy-mm-dd'), :state, :deliOpinion,:deliMan)";
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		String createDateSQL = FuncMySQL.shortDateForUpdate(newCase.getCreateDate()) ;
		String onlineDateSQL = FuncMySQL.shortDateForUpdate(newCase.getOnlineDate()) ;
		String sql = "insert into CASE_CASE (ID,P_ID,DISEASE_ID,CREATE_DATE,ONLINE_DATE,STATE,DELI_OPINION,DELI_MAN) values (:id, :pId, :diseaseId, " 
		             + createDateSQL + ", " + onlineDateSQL + ", :state, :deliOpinion,:deliMan)";
		
		getSimpleJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(newCase));
		
		return id;
	}
	public CasePatient getPatientById(Long id){
		CasePatient patient = new CasePatient();
		try{
			//String sql = "select t.id,t.exam_type_id,t1.name as exam_type_name,t.name,t.exam_times,t.exam_time,t.exam_nature,t.isnot_see_result,t.pass_condition,t.pass_value,t.state,t.isnot_see_test_report,to_char(t.start_time, 'yyyy-mm-dd hh:mi:ss') as start_time,to_char(t.end_time, 'yyyy-mm-dd hh:mi:ss') as end_time,t.isnot_decide,t.isnot_online,t.exam_type,t.exam_num,t.is_cut_screen,t.cut_screen_num,t.paper_display_mode,t.question_display_mode,t.single_scoring,t.paper_scoring, to_char(t.create_time, 'yyyy-mm-dd hh:mi:ss') as create_time, t.remark from exam_examination t,exam_exam_type t1 where t.exam_type_id = t1.id and t.id = ?";
			String sql_ = "select * from CASE_PATIENT where id = ?";
			patient = getJdbcTemplate().queryForObject(sql_, ParameterizedBeanPropertyRowMapper.newInstance(CasePatient.class),id);
		}catch(Exception e){
			System.out.println(e);
		}
		return patient;
	}
	public CaseCase getCaseById(Long id){
		CaseCase curCase = new CaseCase();
		try{
			//String sql = "select t.id,t.exam_type_id,t1.name as exam_type_name,t.name,t.exam_times,t.exam_time,t.exam_nature,t.isnot_see_result,t.pass_condition,t.pass_value,t.state,t.isnot_see_test_report,to_char(t.start_time, 'yyyy-mm-dd hh:mi:ss') as start_time,to_char(t.end_time, 'yyyy-mm-dd hh:mi:ss') as end_time,t.isnot_decide,t.isnot_online,t.exam_type,t.exam_num,t.is_cut_screen,t.cut_screen_num,t.paper_display_mode,t.question_display_mode,t.single_scoring,t.paper_scoring, to_char(t.create_time, 'yyyy-mm-dd hh:mi:ss') as create_time, t.remark from exam_examination t,exam_exam_type t1 where t.exam_type_id = t1.id and t.id = ?";
			//String sql_ = "select P_ID,DISEASE_ID,to_char(CREATE_DATE,'yyyy-MM-dd'),to_char(ONLINE_DATE,'yyyy-MM-dd'),STATE,DELI_OPINION,DELI_MAN from CASE_CASE where id = ?";
			String sql_ = "select * from CASE_CASE where id = ?";
			curCase = getJdbcTemplate().queryForObject(sql_, ParameterizedBeanPropertyRowMapper.newInstance(CaseCase.class),id);
			String createDate = curCase.getCreateDate();
			createDate = createDate.substring(0, createDate.length()-11);
			curCase.setCreateDate(createDate);
		}catch(Exception e){
			System.out.println(e);
		}
		return curCase;
	}
	public CaseDisease getDiseaseById(Long id){
		CaseDisease curDisease = new CaseDisease();
		try{
			//String sql = "select t.id,t.exam_type_id,t1.name as exam_type_name,t.name,t.exam_times,t.exam_time,t.exam_nature,t.isnot_see_result,t.pass_condition,t.pass_value,t.state,t.isnot_see_test_report,to_char(t.start_time, 'yyyy-mm-dd hh:mi:ss') as start_time,to_char(t.end_time, 'yyyy-mm-dd hh:mi:ss') as end_time,t.isnot_decide,t.isnot_online,t.exam_type,t.exam_num,t.is_cut_screen,t.cut_screen_num,t.paper_display_mode,t.question_display_mode,t.single_scoring,t.paper_scoring, to_char(t.create_time, 'yyyy-mm-dd hh:mi:ss') as create_time, t.remark from exam_examination t,exam_exam_type t1 where t.exam_type_id = t1.id and t.id = ?";
			String sql_ = "select * from CASE_COURSE_DISEASE where id = ?";
			curDisease = getJdbcTemplate().queryForObject(sql_, ParameterizedBeanPropertyRowMapper.newInstance(CaseDisease.class),id);
		}catch(Exception e){
			System.out.println(e);
		}
		return curDisease;
	}
	public List<CaseCase> getCaseList(CaseCase searchCase){
		String sql = "select * from CASE_CASE order by CREATE_DATE desc";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CaseCase.class));
	}
	public List<CasePatientPropVal> getCasePropByPatientId(Long PatientId){
		String sql = "select p.id, p.patientid, p.propid,i.NAME as propName,i.TYPE from CASE_PATIENT_PROP_VAL p left join sub_sys_prop_val s on " +
				"s.id = p.propid left join exam_prop_val i on i.id = s.prop_val_id where p.PATIENTID=?";
		Object[] values = new Object[] {PatientId};
		List<CasePatientPropVal> newPropList = new ArrayList<CasePatientPropVal>();
		newPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CasePatientPropVal.class),PatientId);
		return newPropList;
	}
	public CasePatientPropVal getCasePropByPatientIdAndPropId(Long PatientId,Long PropId){
		String sql = "select * from CASE_PATIENT_PROP_VAL where PATIENTID=? and PROPID=?";
		Object[] values = new Object[] {PatientId,PropId};
		CasePatientPropVal newProp = getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(CasePatientPropVal.class),PatientId,PropId);
		return newProp;
	}
	public void deleteCasePropVal(Long patientid){
		String sql = "delete from CASE_PATIENT_PROP_VAL where PATIENTID=?";
		List<Object[]> batch = new ArrayList<Object[]>();
		Object[] values = new Object[] {patientid};
		batch.add(values);
		updateBatch(sql, batch);
	}
	public List<CaseCase> getCaseList(List<String> searchCase){
		
		String patientName = searchCase.get(0);
		String examTime = searchCase.get(1);
		String examination = searchCase.get(2);
		String stateVal = searchCase.get(3);
		String Zuti = searchCase.get(5);
		String PropId = searchCase.get(4);
		Integer state = null;
		List values = new ArrayList();
		
		if (stateVal != null && !stateVal.equals("")) {
			state  = Integer.valueOf(stateVal);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT c.* from CASE_CASE c LEFT JOIN CASE_PATIENT p on c.P_ID=p.ID LEFT JOIN CASE_PATIENT_DIAGNOSIS d on p.ID=d.patientid LEFT JOIN CASE_PATIENT_PROP_VAL v on v.patientid=p.id WHERE p.ID>0");		
		
		if (patientName != null && !patientName.equals("")) {
			sql.append(" AND p.PNAME like ?");
			values.add("%" + patientName.trim() + "%");
		}
		/*
		if (examTime != null && !examTime.equals("")) {
			sql.append(" AND p.DIAGDATE =TO_DATE('").append(examTime).append("', 'yyyy-MM-dd')");
		}*/
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		if (StringUtils.isNotBlank(examTime)) {
			sql.append(" AND p.DIAGDATE = " + FuncMySQL.shortDateForUpdate(examTime) + " ");
		}
		
		if (examination != null && !examination.equals("")) {
			sql.append(" AND d.DIAGNOSIS like ?");
			values.add("%"+examination.trim()+"%");
		}
		if (state != null ) {
			sql.append(" AND c.STATE = ?");
			values.add(state);
		}
		
		if (PropId!=null && !PropId.equals("")) {
			sql.append(" AND v.propid in (?)");
			values.add(PropId);
		}
		if (Zuti!=null && !Zuti.equals("")) {
			sql.append(" AND v.propid in (?)");
			values.add(Zuti);
		}
		
		sql.append(" order by CREATE_DATE desc");

		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CaseCase.class), values.toArray());
	}
	
	
	
	public Boolean deleteCase(Long id){
		CaseCase curCase = getCaseById(id);
		if(curCase.getState().equals(0) ||
			curCase.getState().equals(1) || 
			curCase.getState().equals(4)){
			String sql = "update CASE_CASE set state=? where id = ?";
			List<Object[]> batch = new ArrayList<Object[]>();
			Object[] values = new Object[] { 5, id };
			batch.add(values);
			updateBatch(sql, batch);	
			return true;
		}
		return false;
	}
	public Boolean setCaseState(Long id,Integer state,String userName,String deliOpinion){
		CaseCase curCase = getCaseById(id);
		
		if(state.equals(4)){
			if(curCase.getState()>0)
			{
				String sql = "update CASE_CASE set state=? where id = ?";
				List<Object[]> batch = new ArrayList<Object[]>();
				Object[] values = new Object[] { state, id };
				batch.add(values);
				updateBatch(sql, batch);
				return true;	
			}			
		}
		else if(state.equals(3)){
			if(curCase.getState()<=0) 
				return false;
			Date now = new Date();
			curCase.setOnlineDate(DateUtils.formatDate(now, "yyyy-MM-dd"));
			curCase.setDeliMan(userName);
			curCase.setDeliOpinion("");
			updateCase(curCase);
			String sql = "update CASE_CASE set state=?,DELI_OPINION = ? where id = ?";
			List<Object[]> batch = new ArrayList<Object[]>();
			Object[] values = new Object[] { state, "", id };
			batch.add(values);
			updateBatch(sql, batch);
			return true;
		}
		else if(state.equals(2)){
			if(curCase.getState()<=0) 
				return false;
			String sql = "update CASE_CASE set state=? , DELI_OPINION = ? where id = ?";
			List<Object[]> batch = new ArrayList<Object[]>();
			Object[] values = new Object[] { state, deliOpinion ,id};
			batch.add(values);
			updateBatch(sql, batch);
			return true;
		}
		else {
			String sql = "update CASE_CASE set state=? , DELI_OPINION = ? where id = ?";
			List<Object[]> batch = new ArrayList<Object[]>();
			Object[] values = new Object[] { state, deliOpinion ,id};
			batch.add(values);
			updateBatch(sql, batch);
			return true;
		}
		return false;
	}
	public  List<CasePatientDiagnosis> getPatientDiagnosisById(Long patientId){
		List<CasePatientDiagnosis> resultDiagnosis = new ArrayList<CasePatientDiagnosis>();
		String sql_ = "select * from CASE_PATIENT_DIAGNOSIS where PATIENTID = ?";
		resultDiagnosis = getJdbcTemplate().query(sql_,ParameterizedBeanPropertyRowMapper.newInstance(CasePatientDiagnosis.class),patientId);
		return resultDiagnosis;
	}
	public  List<CaseDiseaseDiscuss> getDiseaseDiscussById(Long diseaseId){
		List<CaseDiseaseDiscuss> resultDiscuss = new ArrayList<CaseDiseaseDiscuss>();
		String sql_ = "select * from CASE_COURSE_DISCUSS where DISEASEID = ?";
		resultDiscuss = getJdbcTemplate().query(sql_,ParameterizedBeanPropertyRowMapper.newInstance(CaseDiseaseDiscuss.class),diseaseId);
		return resultDiscuss;
	}
	@SuppressWarnings("unchecked")
	public void updateCase(CaseCase newCase){
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update CASE_CASE set ");
		if(null != newCase.getpId()){
			sql.append("P_ID=?,");
			values.add(newCase.getpId());
		}
		if(null != newCase.getDiseaseId()){
			sql.append("DISEASE_ID=?,");
			values.add(newCase.getDiseaseId());
		}
		if(!StringUtils.checkNull(newCase.getCreateDate())){
			/*sql.append("CREATE_DATE = to_date(?,'yyyy-MM-dd'),");
			values.add(newCase.getCreateDate());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("CREATE_DATE = " + FuncMySQL.shortDateForUpdate(newCase.getCreateDate()) + " ,"); 
		}
		if(!StringUtils.checkNull(newCase.getOnlineDate())){
			/*sql.append("ONLINE_DATE = to_date(?,'yyyy-MM-dd'),");
			values.add(newCase.getOnlineDate());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("ONLINE_DATE = " + FuncMySQL.shortDateForUpdate(newCase.getOnlineDate()) + " ,"); 
		}
		if(null != newCase.getState()){
			sql.append("STATE=?,");
			values.add(newCase.getState());
		}
		if(!StringUtils.checkNull(newCase.getDeliOpinion())){
			sql.append("DELI_OPINION,");
			values.add(newCase.getDeliOpinion());
		}
		if(!StringUtils.checkNull(newCase.getDeliMan())){
			sql.append("DELI_MAN = ?");
			values.add(newCase.getDeliMan());
		}
		sql.append("where id = ?");
		values.add(newCase.getId());
		
		getJdbcTemplate().update(sql.toString(),values.toArray());
	}
	@SuppressWarnings("unchecked")
	public void updateCasePatient(CasePatient patient){
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update CASE_PATIENT set ");
		if(!StringUtils.checkNull(patient.getDiagDate())){
			/*sql.append("DIAGDATE = to_date(?,'yyyy-MM-dd'),");
			values.add(patient.getDiagDate());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("DIAGDATE = " + FuncMySQL.shortDateForUpdate(patient.getDiagDate()) + " ,"); 
		}
		
		if(!StringUtils.checkNull(patient.getPName())){
			sql.append("PNAME = ?,");
			values.add(patient.getPName());
		}
		if(null != patient.getSex()){
			sql.append("SEX=?,");
			values.add(patient.getSex());
		}
		if(!StringUtils.checkNull(patient.getBirthday())){
			/*sql.append("BIRTHDAY = to_date(?,'yyyy-MM-dd'),");
			values.add(patient.getBirthday());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("BIRTHDAY = " + FuncMySQL.shortDateForUpdate(patient.getBirthday()) + " ,"); 
		}
		if(!StringUtils.checkNull(patient.getCertificate())){
			sql.append("CERTIFICATE = ?,");
			values.add(patient.getCertificate());
		}
		if(null != patient.getNationalState()){
			sql.append("NATIONAL_STATE=?,");
			values.add(patient.getNationalState());
		}
		if(null != patient.getNumber1Type()){
			sql.append("NUMBER1TYPE = ?,");
			values.add(patient.getNumber1Type());
		}
		if(!StringUtils.checkNull(patient.getNumber1())){
			sql.append("NUMBER1=?,");
			values.add(patient.getNumber1());
		}
		if(null != patient.getNumber1Type()){
			sql.append("NUMBER2TYPE = ?,");
			values.add(patient.getNumber2Type());
		}
		if(!StringUtils.checkNull(patient.getNumber1())){
			sql.append("NUMBER2=?,");
			values.add(patient.getNumber2());
		}
		if(!StringUtils.checkNull(patient.getPhoneNumber1())){
			sql.append("PHONENUMBER1=?,");
			values.add(patient.getPhoneNumber1());
		}
		if(!StringUtils.checkNull(patient.getPhoneNumber2())){
			sql.append("PHONENUMBER2=?,");
			values.add(patient.getPhoneNumber2());
		}
		if(!StringUtils.checkNull(patient.getDiagName())){
			sql.append("DIAGNAME=?");
			values.add(patient.getDiagName());
		}
		if(sql.charAt(sql.length()-1)  == ',')
		{
			sql.append(" id=?");	
		}
		else
		{
			sql.append(", id=?");
		}
		
		values.add(patient.getId());
		sql.append(" where id = ?");
		values.add(patient.getId());
		
		getJdbcTemplate().update(sql.toString(),values.toArray());
	}
	public  List<CaseDiseaseDiagnosis> getDiseaseDiagnosisById(Long diseaseId){
		List<CaseDiseaseDiagnosis> resultDiagnosis = new ArrayList<CaseDiseaseDiagnosis>();
		String sql_ = "select * from CASE_COURSE_DIAGNOSIS where DISEASEID = ?";
		resultDiagnosis = getJdbcTemplate().query(sql_,ParameterizedBeanPropertyRowMapper.newInstance(CaseDiseaseDiagnosis.class),diseaseId);
		return resultDiagnosis;
	}
	@SuppressWarnings("unchecked")
	public void updateCaseDisease(CaseDisease disease){
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update CASE_COURSE_DISEASE set ");
		if(!StringUtils.checkNull(disease.getDiseaseDate())){
			/*sql.append("DISEASEDATE = to_date(?,'yyyy-MM-dd'),");
			values.add(disease.getDiseaseDate());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("DISEASEDATE = " + FuncMySQL.shortDateForUpdate(disease.getDiseaseDate()) + " ,"); 
		}
		
		if(disease.getDiseaseType() != null){
			sql.append("DISEASETYPE = ?,");
			values.add(disease.getDiseaseType());
		}
		if(!StringUtils.checkNull(disease.getComplaint())){
			sql.append("COMPLAINT = ?,");
			values.add(disease.getComplaint());
		}
		if(!StringUtils.checkNull(disease.getCurrentDisease())){
			sql.append("CURRENTDISEASE = ?,");
			values.add(disease.getCurrentDisease());
		}
		if(!StringUtils.checkNull(disease.getHistory1())){
			sql.append("HISTORY1 = ?,");
			values.add(disease.getHistory1());
		}
		if(!StringUtils.checkNull(disease.getHistory2())){
			sql.append("HISTORY2 = ?,");
			values.add(disease.getHistory2());
		}
		if(!StringUtils.checkNull(disease.getHistory3())){
			sql.append("HISTORY3 = ?,");
			values.add(disease.getHistory3());
		}
		if(!StringUtils.checkNull(disease.getHistory4())){
			sql.append("HISTORY4 = ?,");
			values.add(disease.getHistory4());
		}
		if(null != disease.getBodyState1Type()){
			sql.append("BODYSTATE1TYPE=?,");
			values.add(disease.getBodyState1Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState1())){
			sql.append("BODYSTATE1 = ?,");
			values.add(disease.getBodyState1());
		}
		if(null != disease.getBodyState2Type()){
			sql.append("BODYSTATE2TYPE=?,");
			values.add(disease.getBodyState2Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState2())){
			sql.append("BODYSTATE2 = ?,");
			values.add(disease.getBodyState2());
		}
		if(null != disease.getBodyState3Type()){
			sql.append("BODYSTATE3TYPE=?,");
			values.add(disease.getBodyState3Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState3())){
			sql.append("BODYSTATE3 = ?,");
			values.add(disease.getBodyState3());
		}
		if(null != disease.getBodyState4Type()){
			sql.append("BODYSTATE4TYPE=?,");
			values.add(disease.getBodyState4Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState4())){
			sql.append("BODYSTATE4 = ?,");
			values.add(disease.getBodyState4());
		}
		if(null != disease.getBodyState5Type()){
			sql.append("BODYSTATE5TYPE=?,");
			values.add(disease.getBodyState5Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState5())){
			sql.append("BODYSTATE5 = ?,");
			values.add(disease.getBodyState5());
		}
		if(null != disease.getBodyState6Type()){
			sql.append("BODYSTATE6TYPE=?,");
			values.add(disease.getBodyState6Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState6())){
			sql.append("BODYSTATE6 = ?,");
			values.add(disease.getBodyState6());
		}
		if(null != disease.getBodyState7Type()){
			sql.append("BODYSTATE7TYPE=?,");
			values.add(disease.getBodyState7Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState7())){
			sql.append("BODYSTATE7 = ?,");
			values.add(disease.getBodyState7());
		}
		if(null != disease.getBodyState8Type()){
			sql.append("BODYSTATE8TYPE=?,");
			values.add(disease.getBodyState8Type());
		}
		if(!StringUtils.checkNull(disease.getBodyState8())){
			sql.append("BODYSTATE8 = ?,");
			values.add(disease.getBodyState8());
		}
		if(!StringUtils.checkNull(disease.getAssay())){
			sql.append("ASSAY=?,");
			values.add(disease.getAssay());
		}
		if(!StringUtils.checkNull(disease.getAssayFile())){
			sql.append("ASSAY_FILE=?,");
			values.add(disease.getAssayFile());
		}
		if(!StringUtils.checkNull(disease.getImage())){
			sql.append("IMAGE=?,");
			values.add(disease.getImage());
		}
		if(!StringUtils.checkNull(disease.getImageFile())){
			sql.append("IMAGE_FILE=?,");
			values.add(disease.getImageFile());
		}
		if(!StringUtils.checkNull(disease.getRest())){
			sql.append("REST=?,");
			values.add(disease.getRest());
		}
		if(!StringUtils.checkNull(disease.getRestFile())){
			sql.append("REST_FILE=?,");
			values.add(disease.getRestFile());
		}
		if(!StringUtils.checkNull(disease.getPlan())){
			sql.append("PLAN=?,");
			values.add(disease.getPlan());
		}
		if(!StringUtils.checkNull(disease.getPlanFile())){
			sql.append("PLAN_FILE=?,");
			values.add(disease.getPlanFile());
		}
		if(!StringUtils.checkNull(disease.getFutureState())){
			sql.append("FUTURESTATE=? ,");
			values.add(disease.getFutureState());
		}
		sql.append(" id = id where id = ?");
		values.add(disease.getId());
		
		getJdbcTemplate().update(sql.toString(),values.toArray());
	}
}
