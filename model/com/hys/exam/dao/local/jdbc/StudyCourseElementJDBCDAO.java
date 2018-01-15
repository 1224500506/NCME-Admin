package com.hys.exam.dao.local.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.StudyCourseElementDAO;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.exam.model.StudyCourseElementWare;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程组件 JdbcDao
 * 
 * 说明:
 */
public class StudyCourseElementJDBCDAO extends AbstractJDBCDAO implements StudyCourseElementDAO {
	
	//根据主键ID 查询课程组件信息
	final static private String getStudyCourseElementById_SQL = " select t.* from study_course_element t where t.id = ? and t.status = ? " ;
	
	//取得当前课程下 组件列表的最大Seq
	final static private String getCurElementMaxSeq_SQL = " select max(t.seq) from study_course_element t where t.course_id = ? and t.status = ? " ;
	
	//批量修改课程组件
	final static private String updateCurElementBatch_SQL = "update study_course_element set "
			+ "course_element_type = :courseElementType,"
			+ "seq = :seq,"
			+ "status = :status,"
			+ "last_update_date = sysdate() where id = :id " ;
	
	//判断当前课程下课程组件分类数量
	final static private String getJudgeCurElementNumber_SQL = "select t.course_element_type "
			+ "from study_course_element t where t.course_id = ? and t.status = ? "
			+ "group by t.course_element_type "
			+ "order by t.course_element_type " ;
	
	//添加课程 课件信息
	final static private String addStudyCourseElement_SQL = "insert into study_course_element "
			+ "( id, course_element_type, course_id,  seq,  status) values "
			+ "(:id,  :courseElementType, :courseId, :seq, :status)" ;

	//添加课程 课程组件课件
	final static private String addStudyCourseElementWare_SQL = "insert into study_course_element_ware (study_course_element_id, study_courseware_id) values (?, ?)" ;
	
	//添加课程 课程组件试题
	final static private String addStudyCourseElementQuestion_SQL = "insert into study_course_element_question ("
			+ "study_course_element_id, study_course_question_id, seq) values ("
			+ ":studyCourseElementId,   :studyCourseQuestionId,  :seq)" ;
	
	//根据课程组件ID 考试试题ID 列表
	final static private String getCurEleQuestByElement_SQL = "select t.* "
			+ "from study_course_element_question t "
			+ "join study_course_element t1 on t.study_course_element_id = t1.id "
			+ "where t1.status = ? and t1.id = ? order by t.seq" ;
	
	final private ParameterizedRowMapper<Integer> mapper = new ParameterizedRowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt("course_element_type") ;
        }
    };
	
	@Override //修改课程组件信息
	public int updateStudyCourseElement(StudyCourseElement element) {
		StringBuilder sql = new StringBuilder() ;
		sql.append(" update study_course_element t set ") ;
		
		//设置类别信息
		if(element.getCourseElementType() != null && element.getCourseElementType() > 0){
			sql.append(" t.course_element_type = :courseElementType, ") ;
		}

		//设置课程ID
		if(element.getCourseId() != null && element.getCourseId() > 0){
			sql.append(" t.course_id = :courseId, ") ;
		}

		//设置序列
		if(element.getSeq() != null && element.getSeq() > 0){
			sql.append(" t.seq = :seq, ") ;
		}
		
		//设置状态
		if(element.getStatus() != null){
			sql.append(" t.status = :status, ") ;
		}
		
		sql.append(" t.last_update_date = sysdate() where id = :id ") ;
		
		return getNamedParameterJdbcTemplate().update(sql.toString(), new BeanPropertySqlParameterSource(element)) ;
	}
	
	@Override //根据主键ID 查询课程组件信息
	public StudyCourseElement getStudyCourseElementById(Long elementId) {
		List<StudyCourseElement> eleList = getJdbcTemplate().query(
				getStudyCourseElementById_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseElement.class),
				elementId, Constants.STATUS_1);
		
		if(Utils.isListEmpty(eleList)){
			return null ;
		}
		
		return eleList.get(0) ;
	}
	
	@Override //取得当前课程下组件距离seq 最近的一条数据
	public StudyCourseElement getCurElementRecentlyData(Long courseId, Long seq, int flag) {
		StringBuilder sql = new StringBuilder() ;
		sql.append(" select ele.* from ( ") ;
		sql.append(" select t.* from study_course_element t where t.course_id = ? and t.status = ? ") ;
		
		//设置查询标记 1.向上寻找 2.向下寻找
		if(flag == 1){
			sql.append(" and t.seq < ? order by t.seq desc ") ;
		}else{
			sql.append(" and t.seq > ? order by t.seq asc ") ;
		}
		sql.append(" ) ele where rownum = 1 ") ;
		
		List<StudyCourseElement> eleList = getJdbcTemplate().query(sql.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseElement.class),
				courseId, Constants.STATUS_1, seq);
		
		if(Utils.isListEmpty(eleList)){
			return null ;
		}
		
		return eleList.get(0) ;
	}
	
	@Override //取得当前课程下 组件列表的最大Seq
	public Integer getCurElementMaxSeq(Long courseId) {
		return getJdbcTemplate().queryForInt(getCurElementMaxSeq_SQL, courseId, Constants.STATUS_1);
	}
	
	@Override //批量修改课程组件
	public int updateCurElementBatch(List<StudyCourseElement> eleList) {
		return saveList(updateCurElementBatch_SQL, eleList).length ;
	}
	

	@Override //判断当前课程下课程组件分类数量
	public List<Integer> getJudgeCurElementNumber(Long courseId) {
		return getJdbcTemplate().query(getJudgeCurElementNumber_SQL, mapper, courseId, Constants.STATUS_1) ; 
	}
	
	@Override //添加课程组件信息
	public int addStudyCourseElement(StudyCourseElement element) {
		element.setId(getSequence("STUDY_COURSE_ELEMENT_SEQ")) ;
		return getNamedParameterJdbcTemplate().update(addStudyCourseElement_SQL, new BeanPropertySqlParameterSource(element)) ;
	}
	
	@Override //添加课程组件课件信息
	public int addStudyCourseElementWare(StudyCourseElementWare ware) {
		return getJdbcTemplate().update(addStudyCourseElementWare_SQL, ware.getStudyCourseElementId(), ware.getStudyCoursewareId()) ;
	}
	
	@Override //添加课程 课程组件试题
	public int addStudyCourseElementQuestion(List<StudyCourseElementQuestion> questList) {
		return saveList(addStudyCourseElementQuestion_SQL, questList).length ; 
	}
	
	@Override //根据课程组件ID 考试试题ID 列表
	public List<StudyCourseElementQuestion> getCurEleQuestByElement(Long elementId) {
		return getJdbcTemplate().query(getCurEleQuestByElement_SQL,
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseElementQuestion.class),
				Constants.STATUS_1, elementId);
	}
}