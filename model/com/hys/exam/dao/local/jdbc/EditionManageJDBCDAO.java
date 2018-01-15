package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.dao.local.CVSetManageDAO;
import com.hys.exam.dao.local.EditionManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.exam.model.ExCVSet;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.UserImage;
import com.hys.exam.service.local.CVSetManage;

/**
 * Lee 2016-11-28
 */

public class EditionManageJDBCDAO extends BaseDao implements EditionManageDAO {

	private EditionManageDAO localEditionManageDAO;
	
	private CVSetManageDAO localCVSetManageDAO;
	
	private CVManageDAO localCVManageDAO;			//chenlb add
	
	public EditionManageDAO getLocalEditionManageDAO() {
		return localEditionManageDAO;
	}

	public void setLocalEditionManageDAO(EditionManageDAO localEditionManageDAO) {
		this.localEditionManageDAO = localEditionManageDAO;
	}

	public CVSetManageDAO getLocalCVSetManageDAO() {
		return localCVSetManageDAO;
	}

	public void setLocalCVSetManageDAO(CVSetManageDAO localCVSetManageDAO) {
		this.localCVSetManageDAO = localCVSetManageDAO;
	}

	@Override
	public Edition getEditionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean resortOrderNum(String orderstr) {
		String item[] = orderstr.split(";");
		
		String SORT_SQL = "update content_edition_ref set sort=? where ref_id=?";
		
		try{
			for (int i = 0; i < item.length; i++){
				String val[] = item[i].split("_");
				Long id = Long.valueOf(val[0]);
				Long sort = Long.valueOf(val[1]);
				getSimpleJdbcTemplate().update(SORT_SQL, sort, id);
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//chenlb add
	@Override
	public boolean resortOrderNumCV(String orderstr){
		String item[] = orderstr.split(";");
		String SORT_SQL = "update content_edition_ref set sort=? where cv_id=?";
		try{
			for (int i = 0; i < item.length; i++){
				String val[] = item[i].split("_");
				Long id = Long.valueOf(val[0]);
				Long sort = Long.valueOf(val[1]);
				getJdbcTemplate().update(SORT_SQL, sort, id);
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	@Override
	public List<Edition> getEditionList(Edition edtion) {
		// TODO Auto-generated method stub
		Long id = edtion.getId();
		int nType = edtion.getType();
		
		List<Edition> lst_Edition = new ArrayList<Edition>();
		
		if(nType == 1) {
			
			List<Edition> result_list = new ArrayList<Edition>();
			String get_cvset_sql = "select ref_id as id, ordernum from content_edition_ref where edition_id=" + id +" order by ordernum asc";
			
			List<ExCVSet> queryIdList = getJdbcTemplate().query(get_cvset_sql,ParameterizedBeanPropertyRowMapper.newInstance(ExCVSet.class));
						
			if(queryIdList.size() > 0) {
				CVSet queryCVSet = new CVSet();
				for(int i=0; i<queryIdList.size(); i++) {
					Edition edition = new Edition();
					queryCVSet.setId(queryIdList.get(i).getId());
					
					if(!StringUtil.checkNull(edtion.getCvSet().getName())) {
						queryCVSet.setName(edtion.getCvSet().getName());
					}
					if(!StringUtil.checkNull(edtion.getCvSet().getDeli_man())) {
						queryCVSet.setDeli_man(edtion.getCvSet().getDeli_man());
					}
					String str_status = String.valueOf(edtion.getCvSet().getStatus());
					if(!StringUtil.checkNull(str_status)) {
						queryCVSet.setStatus(edtion.getCvSet().getStatus());
					}
					List<CVSet> cvSetList = localCVSetManageDAO.getCVSetList(queryCVSet);
					
					List<ExCVSet> ex_cvSetList = new ArrayList<ExCVSet>();
					for (CVSet cvs:cvSetList) {
						ExCVSet ex_cvs = new ExCVSet(cvs);
						ex_cvs.setOrdernum(queryIdList.get(i).getOrdernum());
						ex_cvSetList.add(ex_cvs);
					}
					
					edition.setId((long) queryIdList.get(i).getOrdernum());
					edition.setCvSetList(ex_cvSetList);
					
					result_list.add(edition);
				}
			}
			
			return result_list;
		} else if(nType == 2){
			String bName = edtion.getName(); //首页
			String tName = edtion.getTitle(); //栏目名
			String kName = edtion.getKind();   //
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM content_edition Where id>0 ");
			
			if(!StringUtil.checkNull(bName)) {
				if(bName.equals("1"))
					sql.append(" AND name like '%首页%'");
				else if(bName.equals("2"))
					sql.append(" AND name like '%募课%'");
			}
			if(!StringUtil.checkNull(tName)) {
				if(tName.equals("1"))
					sql.append(" AND title like '%推荐项目%'");
				else if(tName.equals("2"))
					sql.append(" AND title like '%推荐课程%'");
				else if(tName.equals("3"))
					sql.append(" AND title like '%优质慕课%'");
				else if(tName.equals("4"))
					sql.append(" AND title like '%名师课程%'");
				else if(tName.equals("5"))
					sql.append(" AND title like '%典型病例%'");
				else if(tName.equals("6"))
					sql.append(" AND title like '%精彩直播%'");
				else if(tName.equals("7"))
					sql.append(" AND title like '%指南解读%'");
				else if(tName.equals("8"))
					sql.append(" AND title like '%专项能力%'");
				else if(tName.equals("9"))
					sql.append(" AND title like '%名师%'");
				else if(tName.equals("10"))
					sql.append(" AND title like '%专委会%'");
				
			}
			if(!StringUtil.checkNull(kName)) {
				if(!kName.equals("0"))
					sql.append(" AND type="+kName);
			}
			
			lst_Edition = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Edition.class));
			
			for(Edition edition: lst_Edition) {
				if(lst_Edition != null && lst_Edition.size() > 0)
				{/*
					String sql_schedule = "SELECT count(ID) as n_ContentSize FROM content_edition as tbl_edition " +
										  "LEFT JOIN content_edition_ref as tbl_ref ON tbl_edition.ID=tbl_ref.EDITION_ID " +
										  "where tbl_ref.CHECK_STATE=1 and tbl_ref.EDITION_ID=" + edition.getId() ;*/
					String sql_schedule="";
					if(edition.getType()==1 ||edition.getType()==3 || edition.getType()==7){
						sql_schedule = "SELECT count(*) as n_ContentSize FROM content_edition as tbl_edition " +
										"LEFT JOIN content_edition_ref as tbl_ref ON tbl_edition.ID=tbl_ref.EDITION_ID " +
										"left join cv_set t on t.id = tbl_ref.ref_id " +
										"where t.status = 5 and tbl_ref.CHECK_STATE=1 and tbl_ref.EDITION_ID=?" ;
					}else if(edition.getType()==2||edition.getType()==4||edition.getType()==5||edition.getType()==6){
						sql_schedule = "select count(*) as n_ContentSize from content_edition as tbl_edition " +
										"LEFT JOIN content_edition_ref as tbl_ref ON tbl_ref.EDITION_ID = tbl_edition.id " +
										"left join cv s on s.id=tbl_ref.cv_id " +
										"where tbl_ref.CHECK_STATE=1 and cv_id is not null and s.id is not null and tbl_ref.EDITION_ID=?" ;
					}else if (edition.getType()==9) {
						sql_schedule ="select count(*) as n_ContentSize from content_edition as tbl_edition "+
								"LEFT JOIN content_edition_ref as tbl_ref ON tbl_ref.EDITION_ID = tbl_edition.id "+
								"left join expert_info e on e.id=tbl_ref.expert_id "+
								"where tbl_ref.CHECK_STATE=1 and tbl_ref.EDITION_ID=?";
					}else if (edition.getType()==10) {
						sql_schedule ="select count(*) as n_ContentSize from content_edition as tbl_edition "+
										"LEFT JOIN content_edition_ref as tbl_ref ON tbl_ref.EDITION_ID = tbl_edition.id "+
										"left join expert_group e on e.id=tbl_ref.expert_id "+
										"where tbl_ref.CHECK_STATE=1 and tbl_ref.EDITION_ID=?" ;
					}
					int count = getJdbcTemplate().queryForInt(sql_schedule,edition.getId());
					edition.setN_ContentSize(count);
					
				}
			}
			return lst_Edition;
		}else {

			String bName = edtion.getName(); //首页
			String tName = edtion.getTitle(); //栏目名
			String kName = edtion.getKind();   //
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM content_edition Where id>0 ");
			
			if(!StringUtil.checkNull(bName)) {
				if(bName.equals("1"))
					sql.append(" AND name like '%首页%'");
				else if(bName.equals("2"))
					sql.append(" AND name like '%募课%'");
				else if(bName.equals("9"))
					sql.append(" AND name like '%名师%'");
				else if(bName.equals("10"))
					sql.append(" AND name like '%专委会%'");
			}
			if(!StringUtil.checkNull(tName)) {
				if(tName.equals("1"))
					sql.append(" AND title like '%推荐项目%'");
				else if(tName.equals("2"))
					sql.append(" AND title like '%推荐课程%'");
				else if(tName.equals("3"))
					sql.append(" AND title like '%优质慕课%'");
				else if(tName.equals("4"))
					sql.append(" AND title like '%名师课程%'");
				else if(tName.equals("5"))
					sql.append(" AND title like '%典型病例%'");
				else if(tName.equals("6"))
					sql.append(" AND title like '%精彩直播%'");
				else if(tName.equals("7"))
					sql.append(" AND title like '%指南解读%'");
				else if(tName.equals("8"))
					sql.append(" AND title like '%专项能力%'");
				
			}
			if(!StringUtil.checkNull(kName)) {
				if(!kName.equals("0"))
					sql.append(" AND type="+kName);
			}
			
			lst_Edition = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Edition.class));
			
			for(Edition edition: lst_Edition) {
				if(lst_Edition != null && lst_Edition.size() > 0)
				{
					String sql_schedule="";
					if(edition.getType()==1 ||edition.getType()==3 || edition.getType()==7){
						sql_schedule = "SELECT count(*) as n_ContentSize FROM content_edition as tbl_edition " +
										"LEFT JOIN content_edition_ref as tbl_ref ON tbl_edition.ID=tbl_ref.EDITION_ID " +
										"left join cv_set t on t.id = tbl_ref.ref_id " +
										"where t.status = 5 and tbl_ref.CHECK_STATE=1 and tbl_ref.EDITION_ID=?" ;
					}else if(edition.getType()==2||edition.getType()==4||edition.getType()==5||edition.getType()==6){
						sql_schedule = "select count(*) as n_ContentSize from content_edition as tbl_edition " +
										"LEFT JOIN content_edition_ref as tbl_ref ON tbl_ref.EDITION_ID = tbl_edition.id " +
										"left join cv s on s.id=tbl_ref.cv_id " +
										"where tbl_ref.CHECK_STATE=1 and cv_id is not null and s.id is not null and tbl_ref.EDITION_ID=?" ;
					}else if (edition.getType()==9) {
						sql_schedule ="select count(*) as n_ContentSize from content_edition as tbl_edition "+
								"LEFT JOIN content_edition_ref as tbl_ref ON tbl_ref.EDITION_ID = tbl_edition.id "+
								"left join expert_info e on e.id=tbl_ref.expert_id "+
								"where tbl_ref.CHECK_STATE=1 and tbl_ref.EDITION_ID=?";
					}else if (edition.getType()==10) {
						sql_schedule ="select count(*) as n_ContentSize from content_edition as tbl_edition "+
										"LEFT JOIN content_edition_ref as tbl_ref ON tbl_ref.EDITION_ID = tbl_edition.id "+
										"left join expert_group e on e.id=tbl_ref.expert_id "+
										"where tbl_ref.CHECK_STATE=1 and tbl_ref.EDITION_ID=?" ;
					}
					int count = getJdbcTemplate().queryForInt(sql_schedule,edition.getId());
					edition.setN_ContentSize(count);
					
				}
			}
			return lst_Edition;
		
		}
		
	}
	
	//chenlb add
	//得到项目列表(绑定与否)
	@Override
	public List<CVSet> getCVSetListByEdition(Long editionId, String cvsetName, boolean isBind){
		String sql = "";
		List<Object> obj = new ArrayList<Object>();
		if(isBind){			//得到已绑定项目
			sql = "select t.*, t2.ordernum,t2.sort from cv_set t left join content_edition_ref t2 on t.id = t2.ref_id" +
					" where t.status = ? and t2.edition_id=? and t2.check_state = 1 ";
			obj.add(Constants.cvset_project_stauts_5);
			obj.add(editionId);
		}else{
			//得到未绑定项目-----taoLiang---2017-08-25修改
			if(editionId == 3){//优质慕课（因页面管理中的首页栏目可能会有调整，此处将特殊情况分开）
				sql = "select t.* from cv_set t where t.STATUS = ? " +
						" and t.id not in (select t2.ref_id from content_edition_ref t2 where t2.edition_id = ? and t2.check_state = 1) ";;
				obj.add(Constants.cvset_project_stauts_5);
				obj.add(editionId);
			}else{
				sql = "select t.* from cv_set t ,content_edition t3 where t.STATUS = ? " +
					" and t.id not in (select t2.ref_id from content_edition_ref t2 where t2.edition_id = ? and t2.check_state = 1) AND t.SIGN = t3.TITLE AND t3.ID = ? ";
				obj.add(Constants.cvset_project_stauts_5);
				obj.add(editionId);
				obj.add(editionId);
			}
		}
		
		
		
		if(StringUtils.isNotBlank(cvsetName)){
			sql += " and t.name like ? ";
			obj.add("%" + cvsetName + "%");
		}
		
		if(isBind){
			sql += " order by t2.sort asc ";
		}
		
		List<CVSet> list = getJdbcTemplate().query(sql.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), obj.toArray());
		 return list;
	}
	
	//chenlb add
	//得到课程列表(绑定与否)
	public List<CV> getCVListByEdition(Long editionId, String cvName, boolean isBind){
		StringBuffer sb = new StringBuffer();
		List<CV> list = new ArrayList<CV>();
		if(isBind){			//得到已绑定课程
			List<Object> obj = new ArrayList<Object>();
			
			sb.append(" select distinct t.*,t4.name as cvsetName, t2.START_DATE, t2.END_DATE, t5.ordernum,t5.sort from cv t ");
			sb.append(" left join content_edition_ref t5 on t.id = t5.CV_ID ");
			sb.append(" left join cv_schedule t2 on t.id = t2.CV_ID");
			sb.append(" left join cv_set_schedule t3 on t3.CV_SCHEDULE_ID=t2.SCHEDULE_ID ");
			sb.append(" left join cv_set t4 on t4.id = t3.CV_SET_ID ");
			sb.append(" where t5.EDITION_ID = ? and t5.CHECK_STATE = 1 ");
			
			obj.add(editionId);
			
			if(StringUtils.isNotBlank(cvName)){
				sb.append(" and t.name like ? ");
				obj.add("%" + cvName + "%");
			}
			sb.append(" group by t.name ");
			sb.append(" order by t5.sort asc ");
			
			list = getJdbcTemplate().query(sb.toString(),
					ParameterizedBeanPropertyRowMapper.newInstance(CV.class), obj.toArray());
		}else{				//得到未绑定课程
			List<Object> obj = new ArrayList<Object>();
			
			sb.append(" select distinct t1.*, t2.START_DATE, t2.END_DATE, t.name as cvsetName" +
					"  from cv t1 left join cv_schedule t2 on t1.id = t2.CV_ID ");
			sb.append(" left join cv_set_schedule t3 on t3.CV_SCHEDULE_ID = t2.SCHEDULE_ID");
			sb.append(" left join cv_set t on t.id = t3.CV_SET_ID");
			sb.append(" where t.STATUS = ?");
			sb.append(" and t1.id not in ( ");
			sb.append(" select ifnull(t5.CV_ID,0) from content_edition_ref  t5 where t5.EDITION_ID = ? and t5.CHECK_STATE = 1 ");
			sb.append(" ) AND t1.BRAND = ? ");
			//添加直播课程过滤---taoliang
			if(editionId == 6L){
				sb.append(" AND t1.cv_type = 2 ");
			}
			
			obj.add(Constants.cvset_project_stauts_5);
			obj.add(editionId);
			if(editionId == 4L){
				obj.add(4);
			}else if(editionId == 5L){
				obj.add(1);
			}else{
				obj.add(editionId);
			}
			
			
			if(StringUtils.isNotBlank(cvName)){
				sb.append(" and t1.name like ? ");
				obj.add("%" + cvName + "%");
			}
			
			list = getJdbcTemplate().query(sb.toString(),
					ParameterizedBeanPropertyRowMapper.newInstance(CV.class), obj.toArray());
		}
		
		//todo  查询条件
		
		return list;
		
	}
	
	//绑定项目
	@Override
	public int saveEditionCVSetIds(Long id, Long [] cvsetIds){
		int row = 0;
		//获取插入时生成的主键编号
		Long sort = this.getNextId("content_edition_ref");
		if(null != cvsetIds && cvsetIds.length>0){
			//2017-10-26 根据客户新提的需求，页面管理中只能绑定四项内容，已经推荐的4项内容，排序默认为空，若要填写序号进行排序，只能输入1、2、3、4，不可输入其他数值，不可重复
			String sql = "insert into content_edition_ref(edition_id, ref_id, check_state, ordernum,sort) values (?,?,?,0,?)";
			for(Long cvsetId : cvsetIds){
				//row = this.getJdbcTemplate().update(sql, id, cvsetId, 1,sort);
				row = this.getJdbcTemplate().update(sql, id, cvsetId, 1, null);
			}
		}
		
		
		return row;
	}
	
	//解绑项目
	@Override
	public int deleteEditionCVSetIds(Long id, Long [] cvsetIds){
		int row = 0;
		if(null != cvsetIds && cvsetIds.length>0){
			String sql = "update content_edition_ref t set t.check_state=-1 where t.edition_id = ? and t.ref_id = ?";
			for(Long cvsetId : cvsetIds){
				row = this.getJdbcTemplate().update(sql, id, cvsetId);
			}
		}
		return row;
	}
	
	//绑定课程
	//chenlb add
	@Override
	public int saveEditionCVIds(Long id, Long [] cvIds){
		int row = 0;
		//获取插入时生成的主键编号
		Long sort = this.getNextId("content_edition_ref");
		if(null != cvIds && cvIds.length>0){
			//2017-10-26 根据客户新提的需求，页面管理中只能绑定四项内容，已经推荐的4项内容，排序默认为空，若要填写序号进行排序，只能输入1、2、3、4，不可输入其他数值，不可重复
			String sql = "insert into content_edition_ref(edition_id, cv_id, check_state, ordernum,sort) values (?,?,?,0,?)";
			for(Long cvId : cvIds){
				//row = this.getJdbcTemplate().update(sql, id, cvId, 1, sort);
				row = this.getJdbcTemplate().update(sql, id, cvId, 1, null);
			}
		}
		return row;
	}
	
	//解绑课程
	//chenlb add
	@Override
	public int deleteEditionCVIds(Long id, Long [] cvIds){
		int row = 0;
		if(null != cvIds && cvIds.length>0){
			String sql = "update content_edition_ref t set t.check_state=-1 where t.edition_id = ? and t.cv_id = ?";
			for(Long cvId : cvIds){
				row = this.getJdbcTemplate().update(sql, id, cvId);
			}
		}
		return row;
	}

	@Override
	public List<Edition> getEditionListView(Edition edition, CVSet queryCVSet)
	{
		return null;
	}
	
	@Override
	public Long addEdition(Edition edition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEdition(Edition edition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEditionById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public CVManageDAO getLocalCVManageDAO() {
		return localCVManageDAO;
	}

	public void setLocalCVManageDAO(CVManageDAO localCVManageDAO) {
		this.localCVManageDAO = localCVManageDAO;
	}
	/**
	 * 绑定专委会
	 * @param id
	 * @param cvIds
	 * @return
	 */
	@Override
	public int saveEditionZHWIds(Long id, Long[] cvIds) {
		int row = 0;
		//获取插入时生成的主键编号 sort  edition=10   expert_id=? check_state=-1
		Long sort = this.getNextId("content_edition_ref");
		if(null != cvIds && cvIds.length>0){
			//2017-10-26 根据客户新提的需求，页面管理中只能绑定四项内容，已经推荐的4项内容，排序默认为空，若要填写序号进行排序，只能输入1、2、3、4，不可输入其他数值，不可重复
			String sql = "insert into content_edition_ref(EDITION_ID,EXPERT_ID,CHECK_STATE,ORDERNUM,sort) "
					+ "values (?,?,?,0,?)";
			for(Long cvId : cvIds){
				//row = this.getJdbcTemplate().update(sql, id, cvId, 1, sort);
				row = this.getJdbcTemplate().update(sql, id, cvId, 1, null);
			}
		}
		return row;
	}
	/**
	 * 解绑专委会
	 * @param id
	 * @param cvIds
	 * @return
	 */
	@Override
	public int deleteEditionZHWIds(Long id, Long[] cvIds) {
		int row = 0;
		if(null != cvIds && cvIds.length>0){
			String sql = "update content_edition_ref t set t.check_state=-1 where t.edition_id = ? and t.cv_id = ?";
			for(Long cvId : cvIds){
				row = this.getJdbcTemplate().update(sql, id, cvId);
			}
		}
		return row;
	}

}
