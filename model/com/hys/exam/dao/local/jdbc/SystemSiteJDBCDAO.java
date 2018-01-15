package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.SystemSiteDAO;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemSiteCourseTypeExam;
import com.hys.exam.model.TrainContentSite;
import com.hys.exam.model.system.SystemAdminOrganVO;
import com.hys.exam.model.system.SystemSiteVO;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 JdbcDao
 * 
 * 说明:
 */
public class SystemSiteJDBCDAO extends AbstractJDBCDAO implements SystemSiteDAO {
	public SystemSiteJDBCDAO() {
		super(SystemSite.class, Constants.TABLE_SYSTEM_SITE);
	}
	
	//取得所有站点信息
	////final static private String getSystemSiteList_SQL = "select t.*,(select ap.application_name from system_application ap  where ap.status = 1 and ap.id = t.application_id) application_Name,(select c.client_name from system_client c where c.status =1 and c.id = t.client_id) client_name from system_site t where t.status=1 " ;
	final static private String getSystemSiteList_SQL = "select * from system_site t where t.status=1 " ;
	final static private String addSystemSite_SQL = " insert into system_site (id, domain_name, remark, client_id, application_id, status, role_id, alias_name) values (:id, :domainName, :remark, :clientId, :applicationId, :status, :roleId, :aliasName) ";
	final static private String getExamExaminationList_SQL = "select t2.* from system_site_course_type_exam t, exam_examination t2 where t.exam_id = t2.id and t.site_id = ? and t.course_type_id = ?";
	final static private String deleteSystemSiteCourseTypeExam_SQL = "delete from system_site_course_type_exam where site_id=? and course_type_id=?";
	final static private String addSystemSiteCourseTypeExam_SQL = "insert into system_site_course_type_exam(id, site_id, course_type_id, exam_id) values(null, :siteId, :courseTypeId, :examId)";
	final static private String addTrainContentSite_SQL = "insert into train_content_site (id, application_id, site_name, site_logo, site_visit, site_foot) values (null, :applicationId, :siteName, :siteLogo, :siteVisit, :siteFoot)";

	//增加学习卡类型与站点关联
	//删除虚席卡类型与站点关联
	
	//增加站点与继教地区关联
	//删除站点与继教地区关联
	
	
	@Override //取得所有站点信息
	public List<SystemSite> getListByItem(SystemSite item){
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemSiteList_SQL);

		List<Object> list = new ArrayList<Object>();

		/*if (item.getClientId() != null && item.getClientId()>0) {
			sql.append(" and t.client_id = ? ");
			list.add(item.getClientId() );
		}
		if (item.getApplicationId() != null && item.getApplicationId() >0) {
			sql.append(" and t.application_id = ? ");
			list.add(item.getApplicationId() );
		}
		if (item.getRoleId() != null && item.getRoleId() >0) {
			sql.append(" and t.role_Id = ? ");
			list.add(item.getRoleId() );
		}
		if (item.getClientName() != null && !"".equals(item.getClientName())) {
			sql.append(" and t.client_Name like ? ");
			list.add("%" + item.getClientName() + "%");
		}*/
		
		if (item.getDomainName() != null && !"".equals(item.getDomainName())) {
			sql.append(" and t.domain_Name like ? ");
			list.add("%" + item.getDomainName() + "%");
		}
		
		if (item.getAliasName() != null && !"".equals(item.getAliasName())) {
			sql.append(" and t.alias_name like ? ");
			list.add("%" + item.getAliasName() + "%");
		}

		if (item.getRemark() != null && !"".equals(item.getRemark())) {
			sql.append(" and t.remark like ? ");
			list.add("%" + item.getRemark() + "%");
		}
		// 查询结果集
		List<SystemSite> resList = getList(sql.toString(), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		return resList;
	}
	
	@Override
	public void querySystemSiteList(Page<SystemSite> page, SystemSite item) {

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemSiteList_SQL);

		List<Object> list = new ArrayList<Object>();

		/*if (item.getClientId() != null && item.getClientId()>0) {
			sql.append(" and t.client_id = ? ");
			list.add(item.getClientId() );
		}
		if (item.getApplicationId() != null && item.getApplicationId() >0) {
			sql.append(" and t.application_id = ? ");
			list.add(item.getApplicationId() );
		}
		if (item.getRoleId() != null && item.getRoleId() >0) {
			sql.append(" and t.role_Id = ? ");
			list.add(item.getRoleId() );
		}*/
		if (item.getDomainName() != null && !"".equals(item.getDomainName())) {
			sql.append(" and t.domain_Name like ? ");
			list.add("%" + item.getDomainName() + "%");
		}
		
		if (item.getAliasName() != null && !"".equals(item.getAliasName())) {
			sql.append(" and t.alias_name like ? ");
			list.add("%" + item.getAliasName() + "%");
		}

		if (item.getRemark() != null && !"".equals(item.getRemark())) {
			sql.append(" and t.remark like ? ");
			list.add("%" + item.getRemark() + "%");
		}
		sql.append(" order by id desc" );
		// 查询结果集
		List<SystemSite> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}

	public Integer save(SystemSite item) {
		Long id = item.getId();
		if(null == id || id == 0){
			id = this.getSequence("SYSTEM_SITE");
		}
		getNamedParameterJdbcTemplate().update(addSystemSite_SQL, new BeanPropertySqlParameterSource(item));
		return  id.intValue();
	}
	
	public SystemSite getItemById(Long id) {
		return this.get(id.intValue());
	}
	
	@Override
	public int update(SystemSite item) {
		StringBuffer sqlBf = new StringBuffer(" update "+ Constants.TABLE_SYSTEM_SITE+" set id = :id ");  
		Long id = item.getId(); 
		Integer status = item.getStatus(); 
		String remark = item.getRemark(); 
		//Long applicationId = item.getApplicationId(); 
		String domainName = item.getDomainName(); 
		String aliasName = item.getAliasName();
		//Long clientId = item.getClientId(); 
		//Long roleId = item.getRoleId(); 
		if (id==null || "".equals(id) || id==0){ 
		}else{ 
			if (status!=null && !"".equals(status)){ 
				sqlBf.append(",status = :status "); 
			} 
			if (remark!=null && !"".equals(remark.trim())){ 
				sqlBf.append(",remark = :remark "); 
			} 
			/*if (applicationId!=null && !"".equals(applicationId)){ 
				sqlBf.append(",application_Id = :applicationId "); 
			} */
			if (domainName!=null && !"".equals(domainName.trim())){ 
				sqlBf.append(",domain_Name = :domainName "); 
			} 
			if(aliasName != null && !"".equals(aliasName)){
				sqlBf.append(",alias_Name = :aliasName "); 
			}
			/*if (clientId!=null && !"".equals(clientId)){ 
				sqlBf.append(",client_Id = :clientId "); 
			} 
			if (roleId!=null && !"".equals(roleId)){ 
				sqlBf.append(",role_Id = :roleId "); 
			} */
		sqlBf.append(" where id = :id "); 
		return this.getNamedParameterJdbcTemplate().update(sqlBf.toString(), new BeanPropertySqlParameterSource(item)); 
			} 
		
		return 0;
	}

	@Override
	public int delete(long id, String idColName) {
		// 2017/01/11, Add by lee
		// For delete by Id
		//查询是否被其他表关联，关联则不能删除 liupf
		int ssct = getJdbcTemplate().queryForInt("SELECT COUNT(1) from system_site_course_type where SITE_ID ="+id);
		if(ssct>0){
			return 0;
		}
		int sscte = getJdbcTemplate().queryForInt("SELECT COUNT(1) from system_site_course_type_exam where SITE_ID ="+id);
		if(sscte>0){
			return 0;
		}
		int sso = getJdbcTemplate().queryForInt("SELECT COUNT(1) from system_site_organ where SITE_ID ="+id);
		if(sso>0){
			return 0;
		}
		int sou = getJdbcTemplate().queryForInt("SELECT COUNT(1) from system_org_unit where SITE_ID ="+id);
		if(sou>0){
			return 0;
		}
		int srs = getJdbcTemplate().queryForInt("SELECT COUNT(1) from system_resource_site where SITE_ID ="+id);
		if(srs>0){
			return 0;
		}
		StringBuffer sqlBf = new StringBuffer("delete from "+ Constants.TABLE_SYSTEM_SITE+" where id="+id);  
		//getJdbcTemplate().update("set foreign_key_checks=0");//删除忽略外键约束
		int res = getJdbcTemplate().update(sqlBf.toString());
		return res;
	}

	@Override
	public List<ExamExamination> getExamExaminationList(Long siteId,
			Long courseTypeId) {
		return getJdbcTemplate().query(
				getExamExaminationList_SQL,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExamination.class), siteId,
				courseTypeId);
	}

	@Override
	public int deleteSystemSiteCourseTypeExam(Long siteId, Long courseTypeId) {
		return getJdbcTemplate().update(deleteSystemSiteCourseTypeExam_SQL,	siteId, courseTypeId);
	}

	@Override
	public int addSystemSiteCourseTypeExam(SystemSiteCourseTypeExam s) {
		return getNamedParameterJdbcTemplate().update(addSystemSiteCourseTypeExam_SQL, new BeanPropertySqlParameterSource(s));
	}

	@Override
	public int addTrainContentSite(TrainContentSite t) {
		return getNamedParameterJdbcTemplate().update(addTrainContentSite_SQL, new BeanPropertySqlParameterSource(t));

	}
	
	@Override
	public List<SystemSiteVO> getSystemSiteList(){
		////String sql = "select * from system_site t left join system_card_type_site t2 on t.id = t2.site_id where t.status = 1";
		String sql = "select * from system_site t where t.status = 1";
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemSiteVO.class));
	}
	
	//得到绑定/未绑定站点
	@Override
	public List<SystemSiteVO> getSystemSiteListByTypeId(Long typeId, boolean flag){
		String sql = "";
		if(flag){
			sql = "select t2.* from system_site t2 where t2.id in " +
					" (select t1.site_id from system_card_type_site t1 where t1.cardtype_id = ?) and t2.status = 1 ";
		}else{
			sql = "select t2.* from system_site t2 where t2.id not in " +
					" (select t1.site_id from system_card_type_site t1 where t1.cardtype_id = ?) and t2.status = 1 ";
		}
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemSiteVO.class), typeId);
	}
	
	@Override
	public List<SystemAdminOrganVO> getSystemAdminOrganList(Long pid){
		String sql = "select t.* from system_admin_organ t left join system_org t2 on t.org_id = t2.id " +
				" where t2.parent_org_id = ?  or t2.id = ?";
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemAdminOrganVO.class), pid, pid);
	}
	
	//得到已绑定/未绑定的地区
	@Override
	public List<SystemAdminOrganVO> getSystemAdminOrganList(Long siteId, boolean flag){
		String sql = "";
		if(flag){
			sql = "select sao.* from  system_admin_organ sao where sao.organ_id in " +
				" (select t.organ_id from system_site_organ t where t.site_id = ?) and sao.status = ?";
		}else{
			sql = "select sao.* from  system_admin_organ sao where sao.organ_id not in " +
				" (select t.organ_id from system_site_organ t where t.site_id = ?) and sao.status = ?";
		}
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemAdminOrganVO.class), siteId, Constants.STATUS_1);
	}
	
	@Override
	public int saveSystemCardTypeSite(Long typeId, Long siteId){
		deleteSystemCardTypeSite(typeId, siteId);
		String sql = "insert into system_card_type_site (cardtype_id, site_id) values (?,?)";
		int row = this.getJdbcTemplate().update(sql, typeId, siteId);
		return row ;
	}
	
	@Override
	public int deleteSystemCardTypeSite(Long typeId, Long siteId){
		return this.getJdbcTemplate().update("delete from system_card_type_site where cardtype_id = ? and site_id = ?", typeId, siteId);
	}
	
	@Override
	public int saveSystemSiteOrgan(Long siteId, Long organId){
		this.deleteSystemSiteOrgan(siteId, organId);
		String sql = "insert into system_site_organ(site_id, organ_id) values(?,?)";
		return this.getJdbcTemplate().update(sql, siteId, organId);
	}
	
	@Override
	public int deleteSystemSiteOrgan(Long siteId, Long organId){
		return this.getJdbcTemplate().update("delete from system_site_organ  where site_id = ? and organ_id = ?", siteId, organId);
	}
	
	//得到学习卡已绑定的地区
	@Override
	public List<SystemAdminOrganVO> getSystemAdminOrganListByCardType(Long typeId, boolean flag){
		String sql = "";
		if(flag){
			sql = " select sao.* from  system_admin_organ sao where sao.org_id >0 " +
				"  and sao.organ_id in (select org_id from system_paycard_organ t where t.card_type_id = ?) and sao.status = ?";
		}else{
			sql = " select sao.* from  system_admin_organ sao where sao.org_id >0 " +
					"  and sao.organ_id not in (select org_id from system_paycard_organ t where t.card_type_id = ?) and sao.status = ?";
		}
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemAdminOrganVO.class), typeId, Constants.STATUS_1);
	}
	
	@Override
	public int savePaycardOrgan(Long typeId, Long organId, Integer orgType){
		this.deleteSystemSiteOrgan(typeId, organId);
		String sql = "insert into system_paycard_organ (card_type_id, org_id, org_type) values(?,?, ?)";
		return this.getJdbcTemplate().update(sql, typeId, organId, orgType);
	}
	
	@Override
	public int deletePaycardOrgan(Long typeId, Long organId){
		return this.getJdbcTemplate().update("delete from system_paycard_organ  where card_type_id = ? and org_id = ?", typeId, organId);
	}

	
	/**
	 * 新增站点的时候，查询改站点是否存在
	 * @param site
	 * @return
	 */
	@Override
	public List<SystemSite> getListByItem(String site, Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemSiteList_SQL);

		List<Object> list = new ArrayList<Object>();
		
		//这个地方必须有值，如果没有值其实就是异常的
		if (site != null && !"".equals(site)) {
			sql.append(" and t.domain_Name = ? ");
			list.add(site);
		}
		
		if(id != null){
			sql.append(" and t.id != ? ");
			list.add(id);
		}
		// 查询结果集
		List<SystemSite> resList = getList(sql.toString(), 
				list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		return resList;
	}
	
}