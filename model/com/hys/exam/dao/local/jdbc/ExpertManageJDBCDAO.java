package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
/**
 * 专家委员会管理
 * @author Han
 *
 */
public class ExpertManageJDBCDAO extends BaseDao implements
	ExpertManageDAO {
	 
	/**
	 * 查询专家列表
	 */
	@SuppressWarnings("unchecked")
	public List<ExpertInfo> getExpertList(ExpertInfo expert) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select e.* from expert_info e ");
		
		//添加学科条件
		if(!StringUtils.checkNull(expert.getPropIds())){
			String ids = expert.getPropIds().replace('"', ' ');
			sql.append("where e.id in (select expertid from EXPERT_PROP_VAL where PROPID in (").append(ids).append(")) ");
		}
		else
			sql.append("where e.id>0 ");
		
		//添加加入委员会条件
		if(!StringUtils.checkNull(expert.getGroupIds())){
			String ids = expert.getGroupIds().replace('"', ' ');
			sql.append(" and e.id in (select expertid from EXPERT_GROUP_VAL where GROUPID in (").append(ids).append(")) ");
		}
		
		//添加其他条件
		List list = new ArrayList();
		if(!StringUtils.checkNull(expert.getName())){
			sql.append(" and e.name like ?");
			list.add("%"+expert.getName()+"%");
		}
		if(null != expert.getGroupId() && -1 != expert.getGroupId()){
			sql.append(" and e.groupid = ? ");
			list.add(expert.getGroupId());
		}
		if(null != expert.getSubGroupId() && 0 != expert.getSubGroupId()){
			sql.append(" and e.subgroupid = ? ");
			list.add(expert.getSubGroupId());
		}
		if(null != expert.getOffice() && 0 != expert.getOffice()){
			sql.append(" and e.office = ? ");
			list.add(expert.getOffice());
		}
		if(null != expert.getJob() && 0 != expert.getJob()){
			sql.append(" and e.job = ? ");
			list.add(expert.getJob());
		}
		if(null != expert.getTerm() && 0 != expert.getTerm()){
			sql.append(" and e.term = ? ");
			list.add(expert.getTerm());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append(" and e.workunit like ?");
			list.add("%"+expert.getWorkUnit()+"%");
		}
		if(null != expert.getState() && 0 != expert.getState()){
			sql.append(" and e.state = ? ");
			list.add(expert.getState());
		}
		if(null != expert.getLockState() && 0 != expert.getLockState()){
			sql.append(" and e.lockState = ? ");
			list.add(expert.getLockState());
		}
		sql.append("order by e.code");
		
		return getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
	}

	/**
	 * 取得被选id的专家信息
	 */
	@Override
	public ExpertInfo getExpertInfo(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from expert_info where id=? ");
		ExpertInfo expert =  getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), id);
		
		//取得关联学科
		String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXPERT_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where e.EXPERTID=?";
		List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), id);
		expert.setProp(propList);
	
		//取得关联学科
		String GET_GROUP = "SELECT g.* FROM EXPERT_GROUP g LEFT JOIN EXPERT_GROUP_VAL v ON g.id=v.groupid where v.EXPERTID=?";
		List<ExpertGroup> groupList = getJdbcTemplate().query(GET_GROUP, ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class), id);
		expert.setGroup(groupList);
		
		return expert;
	}
	
	//chelb add
	@Override
	public ExpertInfo getExpertInfoByUsername(String username){
		String sql = "select * from expert_info where username = ? ";
		List<ExpertInfo>  list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), username);
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	//chelb add
	@Override
	public List<ExpertInfo> getExpertInfoNameByCvSetId(Long id){
		String sql = "select e.* from expert_info e left join cvset_qualify_history c on c.EXPERT_ID=e.ID where c.CV_SET_ID = ? and c.qualify_status!=2 ";
		List<ExpertInfo>  list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), id);
		if(!Utils.isListEmpty(list)){
			return list;
		}
		return null;
	}

	/**
	 * 添加专家
	 */
	
	public boolean existExpertCode(ExpertInfo prop)
	{
		if (prop == null || prop.getCode() == null ) return false;
		String getcode = "";
		if(prop.getId() != null)
		{
			getcode = "select count(1) from expert_info where code='" + prop.getCode() + "' and id<>"+prop.getId();
		}
		else
		{
			getcode = "select count(1) from expert_info where code='" + prop.getCode() +"'";
		}
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	@Override
	public boolean addExpertInfo(ExpertInfo expert) throws Exception {
		if (existExpertCode(expert))  throw new Exception();
		Long next_id = getNextId("expert_info");
		expert.setId(next_id);
		expert.setLockState(1);
		
		
		String ADD_SQL = "insert into expert_info (id, name, code, groupid, subgroupid, office, term, job, state, breakdate, lockstate, workunit, phone1, phone2, photo, email, clerkname, clerkphone, bank, bankcard, identitynum, summary, username, isnation) values (:id, :name, :code, :groupId, :subGroupId, :office, :term, :job, :state, :breakDate, :lockState, :workUnit, :phone1, :phone2, :photo, :email, :clerkName, :clerkPhone, :bank, :bankCard, :identityNum, :summary, :userName, :isNation)";
		getSimpleJdbcTemplate().update(ADD_SQL, new BeanPropertySqlParameterSource(expert));
		
		//添加专家关联学科
		try{
		String[] propIds = expert.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("expert_prop_val");
			String ADD_PROP = "insert into expert_prop_val (id, expertid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, expert.getId(), pid);
		}
		}catch(Exception e){;}

		//添加关联委员会
		try{
		String[] groupIds = expert.getGroupIds().split(",");
		for(String ps: groupIds){
			Long gid = Long.valueOf(ps);
			String ADD_PROP = "insert into expert_group_val (expertid, groupid) values (?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, expert.getId(), gid);
		}
		}catch(Exception e){;}
		return true;
	}

	/**
	 * 修改专家信息
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public boolean updateExpertInfo(ExpertInfo expert) throws Exception {
		if (existExpertCode(expert))  throw new Exception();
		StringBuffer sql = new StringBuffer();
		sql.append("update expert_info set ");
		List list = new ArrayList();
		
		//设置查询条件信息
		
		if(!StringUtils.checkNull(expert.getName())){
			sql.append("name = ?,");
			list.add(expert.getName());
		}
		if(!StringUtils.checkNull(expert.getCode())){
			sql.append("code = ?,");
			list.add(expert.getCode());
		}
		if(null != expert.getGroupId()){
			sql.append("groupid = ?,");
			list.add(expert.getGroupId());
		}
		if(null != expert.getSubGroupId()){
			sql.append("subgroupid = ?,");
			list.add(expert.getSubGroupId());
		}
		if(null != expert.getOffice()){
			sql.append("office = ?,");
			list.add(expert.getOffice());
		}
		if(null != expert.getTerm()){
			sql.append("term = ?,");
			list.add(expert.getTerm());
		}
		if(null != expert.getJob()){
			sql.append("job = ?,");
			list.add(expert.getJob());
		}
		if(null != expert.getState()){
			sql.append("state = ?,");
			list.add(expert.getState());
		}
		if(null != expert.getBreakDate()){
			sql.append("breakdate = ?,");
			list.add(expert.getBreakDate());
		}
		if(null != expert.getLockState()){
			sql.append("lockstate = ?,");
			list.add(expert.getLockState());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append("workunit = ?,");
			list.add(expert.getWorkUnit());
		}
		if(!StringUtils.checkNull(expert.getPhone1())){
			sql.append("phone1 = ?,");
			list.add(expert.getPhone1());
		}
		if(!StringUtils.checkNull(expert.getPhone2())){
			sql.append("phone2 = ?,");
			list.add(expert.getPhone2());
		}
		if(!StringUtils.checkNull(expert.getPhoto())){
			sql.append("photo = ?,");
			list.add(expert.getPhoto());
		}
		if(!StringUtils.checkNull(expert.getEmail())){
			sql.append("email = ?,");
			list.add(expert.getEmail());
		}
		if(!StringUtils.checkNull(expert.getClerkName())){
			sql.append("clerkname = ?,");
			list.add(expert.getClerkName());
		}
		if(!StringUtils.checkNull(expert.getClerkPhone())){
			sql.append("clerkphone = ?,");
			list.add(expert.getClerkPhone());
		}
		if(!StringUtils.checkNull(expert.getBank())){
			sql.append("bank = ?,");
			list.add(expert.getBank());
		}
		if(!StringUtils.checkNull(expert.getBankCard())){
			sql.append("bankcard = ?,");
			list.add(expert.getBankCard());
		}
		if(!StringUtils.checkNull(expert.getIdentityNum())){
			sql.append("identitynum = ?,");
			list.add(expert.getIdentityNum());
		}
		if(!StringUtils.checkNull(expert.getSummary())){
			sql.append("summary = ?,");
			list.add(expert.getSummary());
		}
		if(!StringUtils.checkNull(expert.getUserName())){
			sql.append("username = ?,");
			list.add(expert.getUserName());
		}
		if(null != expert.getIsNation()){
			sql.append("isnation = ?,");
			list.add(expert.getIsNation());
		}

		sql.append(" id = id where id = ?");
		list.add(expert.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		
		if (expert.getPropIds() != null){
			//删除旧的学科信息
			String DEL_PROP = "delete from expert_prop_val where expertid=?";
			getSimpleJdbcTemplate().update(DEL_PROP, expert.getId());
			
			//添加新的学科信息
			try{
			String[] propIds = expert.getPropIds().split(",");
			for(String ps: propIds){
				Long pid = Long.valueOf(ps);
				Long vid = getNextId("expert_prop_val");
				String ADD_PROP = "insert into expert_prop_val (id, expertid, propid) values (?,?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, vid, expert.getId(), pid);
			}
			}catch(Exception e){;}
		}
		
		if (expert.getGroupIds() != null){
			//删除旧的委员会信息
			String DEL_PROP = "delete from expert_group_val where expertid=?";
			getSimpleJdbcTemplate().update(DEL_PROP, expert.getId());
			
			//添加关联委员会
			try{
			String[] groupIds = expert.getGroupIds().split(",");
			for(String ps: groupIds){
				Long gid = Long.valueOf(ps);
				String ADD_PROP = "insert into expert_group_val (expertid, groupid) values (?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, expert.getId(), gid);
			}
			}catch(Exception e){;}
		}
		return true;
	}

	/**
	 * 删除专家
	 */
	@Override
	public boolean deleteExpertInfo(Long id) {

		//删除旧的学科信息
		String DEL_PROP = "delete from expert_prop_val where expertid=?";
		getSimpleJdbcTemplate().update(DEL_PROP, id);
		
		//删除旧的委员会信息
		DEL_PROP = "delete from expert_group_val where expertid=?";
		getSimpleJdbcTemplate().update(DEL_PROP, id);

		String SQL_DEL = "delete from expert_info where id = ?";
		getJdbcTemplate().update(SQL_DEL,id);
		return true;
	}
	/**
	 *根据项目id获取项目负责人 或授课老师
	 *type=1为项目负责人
	 *type=2为授课老师
	 */
	@Override
	public List<ExpertInfo> getExpertListByCvSetId(Long cvSetId,Integer type) {
		String sql="select e.* from cv_set_expert rr left join expert_info e on rr.expert_id=e.id  where rr.cv_set_id=? and rr.type=?";
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(cvSetId);
		paramList.add(type);
		return getJdbcTemplate().query(sql, paramList.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
	}
	//-------------------页面管理  名师----------------------------
	/**
	 * 页面管理--名师/查看名师的
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void getExpertPageList(PageList pl, ExpertInfo expert) {
		// TODO 页面管理---名师
		StringBuffer sql = new StringBuffer();
		
		sql.append("select e.* from expert_info e ");
		
		//添加学科条件
		if(!StringUtils.checkNull(expert.getPropIds())){
			String ids = expert.getPropIds().replace('"', ' ');
			sql.append("where e.id in (select expertid from EXPERT_PROP_VAL where PROPID in (").append(ids).append(")) ");
		}
		else
			sql.append("where 1=1 ");
		
		sql.append(" and e.id not in (select c.expert_info_id from content_edition_ref c where c.edition_id=9 and c.check_state=1) ");
		
		//添加加入委员会条件
		if(!StringUtils.checkNull(expert.getGroupIds())){
			String ids = expert.getGroupIds().replace('"', ' ');
			sql.append("and e.id in (select expertid from EXPERT_GROUP_VAL where GROUPID in (").append(ids).append(")) ");
		}
		//一定是资源平台把启动，lockstate=1，方可查到
		sql.append("and e.lockState = 1 ");
		//筛选出未加入委员会的专家    该字段仅供学组添加成员使用
		/*if(!StringUtils.checkNull(expert.getSearchGroup())){
			sql.append("and (e.groupid is null or (e.groupid is not null and e.subgroupid is null)) ");
		}*/
		//添加其他条件
		List list = new ArrayList();
		if(!StringUtils.checkNull(expert.getName())){
			sql.append("and e.name like '%").append(expert.getName()).append("%' ");
		}
		if(null != expert.getGroupId() && -1 != expert.getGroupId()){
			sql.append("and e.groupid = ? ");
			list.add(expert.getGroupId());
		}
		if(null != expert.getSubGroupId() && 0 != expert.getSubGroupId()){
			sql.append("and e.subgroupid = ? ");
			list.add(expert.getSubGroupId());
		}
		if(null != expert.getOffice() && 0 != expert.getOffice()){
			sql.append("and e.id in (select distinct expertid from expert_office_val where officeid = ? ) ");
			list.add(expert.getOffice());
		}
		if(null != expert.getJob() && 0 != expert.getJob()){
			sql.append("and e.job = ? ");
			list.add(expert.getJob());
		}
		if(null != expert.getTerm() && 0 != expert.getTerm()){
			sql.append("and e.term = ? ");
			list.add(expert.getTerm());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append("and e.workunit like '%").append(expert.getWorkUnit()).append("%' ");
		}
		if(null != expert.getState() && 0 != expert.getState()){
			sql.append("and e.state = ? ");
			list.add(expert.getState());
		}
		/*if(null != expert.getLockState() && 0 != expert.getLockState()){
			sql.append("and e.lockstate = ? ");
			list.add(expert.getLockState());
		}*/
		
		
		
		if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			sql.append(" order by "+pl.getSortCriterion());
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				sql.append(" desc");
		}
		
		
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()), list.toArray());
		pl.setFullListSize(fullListSize);

		List<ExpertInfo> expertList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		
		for (ExpertInfo e: expertList){
			//取得关联职务
			String GET_OFFICE = "SELECT * FROM EXAM_PROP_VAL e LEFT JOIN EXPERT_OFFICE_VAL o ON e.id=o.officeId where o.EXPERTID=?";
			List<ExamProp> officeList = getJdbcTemplate().query(GET_OFFICE, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), e.getId());
			e.setOfficeNew(officeList);
		}
		pl.setList(expertList);
		
	}

	
	/**
	 * 页面管理--名师/查看绑定
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void getExpertPageListView(PageList pl, ExpertInfo expert) {
		// TODO 页面管理---名师
		StringBuffer sql = new StringBuffer();
		
		sql.append("select e.* from expert_info e ");
		
		//添加学科条件
		if(!StringUtils.checkNull(expert.getPropIds())){
			String ids = expert.getPropIds().replace('"', ' ');
			sql.append("where e.id in (select expertid from EXPERT_PROP_VAL where PROPID in (").append(ids).append(")) ");
		}
		else
			sql.append("where 1=1 ");
		sql.append(" and  e.id in (select c.expert_info_id from content_edition_ref c where c.edition_id=9 and c.check_state=1) ");
		sql.append("and e.lockState = 1 ");
		//添加加入委员会条件
		if(!StringUtils.checkNull(expert.getGroupIds())){
			String ids = expert.getGroupIds().replace('"', ' ');
			sql.append("and e.id in (select expertid from EXPERT_GROUP_VAL where GROUPID in (").append(ids).append(")) ");
		}
		//添加加入页面管理中content_edition_ref条件
		/*if(!StringUtils.checkNull(expert.getGroupIds())){
			
		}*/
//		sql.append("and e.id in(select c.expert_info_id from content_edition_ref c where c.edition_id=9 and c.check_state=1) ");
		
		//筛选出未加入委员会的专家    该字段仅供学组添加成员使用
		/*if(!StringUtils.checkNull(expert.getSearchGroup())){
			sql.append("and (e.groupid is null or (e.groupid is not null and e.subgroupid is null)) ");
		}*/
		//添加其他条件
		List list = new ArrayList();
		if(!StringUtils.checkNull(expert.getName())){
			sql.append("and e.name like '%").append(expert.getName()).append("%' ");
		}
		if(null != expert.getGroupId() && -1 != expert.getGroupId()){
			sql.append("and e.groupid = ? ");
			list.add(expert.getGroupId());
		}
		if(null != expert.getSubGroupId() && 0 != expert.getSubGroupId()){
			sql.append("and e.subgroupid = ? ");
			list.add(expert.getSubGroupId());
		}
		if(null != expert.getOffice() && 0 != expert.getOffice()){
			sql.append("and e.id in (select distinct expertid from expert_office_val where officeid = ? ) ");
			list.add(expert.getOffice());
		}
		if(null != expert.getJob() && 0 != expert.getJob()){
			sql.append("and e.job = ? ");
			list.add(expert.getJob());
		}
		if(null != expert.getTerm() && 0 != expert.getTerm()){
			sql.append("and e.term = ? ");
			list.add(expert.getTerm());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append("and e.workunit like '%").append(expert.getWorkUnit()).append("%' ");
		}
		if(null != expert.getState() && 0 != expert.getState()){
			sql.append("and e.state = ? ");
			list.add(expert.getState());
		}
		/*if(null != expert.getLockState() && 0 != expert.getLockState()){
			sql.append("and e.lockstate = ? ");
			list.add(expert.getLockState());
		}*/
		
		
		
		if (pl.getSortCriterion()!=null && !pl.getSortCriterion().equals("")){
			sql.append(" order by "+pl.getSortCriterion());
			
			if (pl.getSortDirection() == SortOrderEnum.DESCENDING)
				sql.append(" desc");
		}
		
		Integer fullListSize = getJdbcTemplate().queryForInt(PageUtil.getCountSql(sql.toString()), list.toArray());
		pl.setFullListSize(fullListSize);

		List<ExpertInfo> expertList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		
		for (ExpertInfo e: expertList){
			//取得关联职务
			String GET_OFFICE = "SELECT * FROM EXAM_PROP_VAL e LEFT JOIN EXPERT_OFFICE_VAL o ON e.id=o.officeId where o.EXPERTID=?";
			List<ExamProp> officeList = getJdbcTemplate().query(GET_OFFICE, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), e.getId());
			e.setOfficeNew(officeList);
		}
		pl.setList(expertList);
		
	}
	//判断该专家是否有关联专委会  绑定的时候，lockstate=1
	//解绑or绑定    绑定是1      解绑是-1     check_state=-1  解绑    check_state=1 绑定
	@Override
	public String lockExpertInfo(Long id, Integer checkState) {
		if(checkState==1){
			//往中间表插入
			Long sort = this.getNextId("content_edition_ref");
			String sqledition = "insert into content_edition_ref(EDITION_ID,expert_info_id,CHECK_STATE,ORDERNUM,sort) "
					+ "values (?,?,?,?,?)";
			int insert = getSimpleJdbcTemplate().update(sqledition, 9,id, 1,sort, sort);
			if(insert>0){
				return "success";
			}
			return "error";
		}else {
			//绑定
			//往中间表插入 DELETE  from content_edition_ref where edition_id=9 and expert_info_id=
			//String sqledition = "update content_edition_ref t set t.check_state=-1 where t.edition_id = 9 and t.EDITION_ID = ? ";
			String sqledition="DELETE  from content_edition_ref where edition_id=9 and expert_info_id= ?";
			int v = getJdbcTemplate().update(sqledition, id);
			if(v>0){
				return "success";
			}
			return "error";
		}
			
	}

	
}
