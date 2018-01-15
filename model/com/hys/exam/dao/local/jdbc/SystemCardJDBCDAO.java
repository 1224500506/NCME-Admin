package com.hys.exam.dao.local.jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.SystemCardDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.model.SystemCardOrderEntity;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemAdminOrgan;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.model.system.SystemCardCreateRecord;
import com.hys.exam.model.system.SystemCardOrder;
import com.hys.exam.model.system.SystemCardType;
import com.hys.exam.model.system.SystemCardUser;
import com.hys.exam.model.system.SystemCreditType;
import com.hys.exam.model.system.SystemPayCardCourse;
import com.hys.exam.model.systemQuery.SystemCardOrderQuery;
import com.hys.exam.model.systemQuery.SystemCardQuery;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.SystemCardUtil;

/**
*
* <p>Description: 学习卡</p>
* @author chenlaibin
* @version 1.0 2013-12-14
*/
public class SystemCardJDBCDAO extends AbstractJDBCDAO implements SystemCardDAO {
	
	//卡
	private String saveSystemCard_SQL = "insert into system_card (id, card_number, card_password, card_type, import_date, useful_date, face_value, create_user_id, create_date, status, isselled,sell_style, balance, cost)" +
					" values (:id, :cardNumber, :cardPassword, :cardType, :importDate, :usefulDate, :faceValue, :createUserId, :createDate, :status, :isselled, :sellStyle, :balance, :cost)";

	//制卡记录
	private String saveSystemCardCreateRecord_SQL = "insert into system_card_create_record (id, CARD_START_INT, CARD_END_INT, card_sum, card_userd_sum, create_date, description)" +
					" values (:id, :cardStartInt, :cardEndInt, :cardSum, :cardUserdSum, :createDate, :description)"; //YHQ，2017-03-23
	
	//卡绑定用户
	private String saveSystemCardUser_SQL = "insert into system_card_user (card_id, user_id, site_id, bind_date) " +
				" values (:cardId, :userId, :siteId, :bindDate)";
	
	/*private String deleteSystemCardUser_SQL = "delete from system_card_user t where t.card_id = ? and t.user_id = ?";*/
	private String deleteSystemCardUser_SQL = "delete from system_card_user where card_id = ? and user_id = ?";
	
	//卡类型
	private String saveSystemCardType_SQL = "insert into system_card_type(id, card_type_name, start_date, end_date, credit_scope, credit_sum, price, evp_value, is_netpay, is_sdsync)" +
				" values (:id, :cardTypeName, :startDate, :endDate, :creditScope, :creditSum, :price, :evpValue, :isNetpay, :isSdsync)";
	
	//得到学习卡列表
	@Override
	/*public void getSystemCardList(Page<SystemCardQuery> page, boolean isAll, Long cardTypeId, String cardNumber, String cardNumberEnd){
		String sql  = "select t.*,(select ct.card_type_name from system_card_type ct where ct.id = t.card_type) cardTypeName from system_card t where 1=1 ";
		List<Object> pl = new ArrayList<Object>();
		if(!isAll){
			sql += "and t.status = ? ";
			pl.add(Constants.SYSTEM_NORMAL_STATUS);
		}
		if(cardTypeId >0){
			sql += " and t.card_type = ?";
			pl.add(cardTypeId);
		}
		
		if(StringUtils.isNotBlank(cardNumber) && StringUtils.isBlank(cardNumberEnd)){
			//sql += " and t.card_number like ?";
			sql += " and t.card_number >= ?";
			pl.add(cardNumber.trim());
		}else if(StringUtils.isNotBlank(cardNumberEnd) && StringUtils.isBlank(cardNumber)){
			sql += " and t.card_number <= ?";
			pl.add(cardNumberEnd.trim());
		}else if(StringUtils.isNotBlank(cardNumber) && StringUtils.isNotBlank(cardNumberEnd)){
			sql += " and t.card_number between ? and ?";
			pl.add(cardNumber.trim());
			pl.add(cardNumberEnd.trim());
		}
		sql += " order by t.id";
		List<SystemCardQuery> list = this.getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardQuery.class));
		Integer totalCount = getCount(sql, pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}*/	
	public void getSystemCardList(Page<SystemCardQuery> page, boolean isAll, Long cardTypeId, String cardNumber, String cardNumberEnd){
	    /*	
            String sql  = "select t.*,(select ct.card_type_name from system_card_type ct where ct.id = t.card_type) cardTypeName from system_card t where 1=1 ";
		List<Object> pl = new ArrayList<Object>();
		if(!isAll){
			sql += "and t.status = ? ";
			pl.add(Constants.SYSTEM_NORMAL_STATUS);
		}
		
		if(cardTypeId >0){
			sql += " and t.card_type = ?";
			pl.add(cardTypeId);
		}
		sql = PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()) ;
		List<SystemCardQuery> ori_list = this.getList(sql, pl, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardQuery.class));
		
		List<SystemCardQuery> list = new ArrayList<SystemCardQuery>();
		
		if(StringUtils.isNotBlank(cardNumber) && StringUtils.isBlank(cardNumberEnd)){
			for (SystemCardQuery card:ori_list) {
				if (Long.parseLong(card.getCardNumber()) >= Long.parseLong(cardNumber))
					list.add(card);
			}
		}else if(StringUtils.isNotBlank(cardNumberEnd) && StringUtils.isBlank(cardNumber)){
			for (SystemCardQuery card:ori_list) {
				if (Long.parseLong(card.getCardNumber()) <= Long.parseLong(cardNumberEnd))
					list.add(card);
			}
		}else if(StringUtils.isNotBlank(cardNumber) && StringUtils.isNotBlank(cardNumberEnd)){
			for (SystemCardQuery card:ori_list) {
				if (Long.parseLong(card.getCardNumber()) >= Long.parseLong(cardNumber) && Long.parseLong(card.getCardNumber()) <= Long.parseLong(cardNumberEnd))
					list.add(card);
			}
		} else {
			list = ori_list;
		}
		
		page.setList(list);
		page.setCount(list.size());
                */
            
            String sql  = " from system_card t where 1=1 ";
            List<Object> pl = new ArrayList<Object>();
            if(!isAll){
                    sql += "and t.status = ? ";
                    pl.add(Constants.SYSTEM_NORMAL_STATUS);
            }
            if(cardTypeId >0){
                    sql += " and t.card_type = ?";
                    pl.add(cardTypeId);
            }
            if(StringUtils.isNotBlank(cardNumber)) {
                sql += " and CAST(t.card_number AS SIGNED) >= ?";
                    pl.add(cardNumber);
            }
            if(StringUtils.isNotBlank(cardNumberEnd)) {
                sql += " and CAST(t.card_number AS SIGNED) <= ?";
                    pl.add(cardNumberEnd);
            }
            
            String sql2 = "select t.*,(select ct.card_type_name from system_card_type ct where ct.id = t.card_type) cardTypeName " + sql ;
            sql2 = PageUtil.getPageSql(sql2, page.getCurrentPage(),page.getPageSize()) ;
            List<SystemCardQuery> ori_list = this.getList(sql2, pl, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardQuery.class));
            
            String sql3 = "select count(*) " + sql ;
            Integer totalCount = getJdbcTemplate().queryForInt(sql3, pl.toArray()) ;
            
            page.setList(ori_list);
	    page.setCount(totalCount);


	    sql = "select count(*) from system_card where  CARD_TYPE = 21 ";//空白卡数量
	    totalCount = getJdbcTemplate().queryForInt(sql) ;
	    Map resMap = new HashMap() ;
	    resMap.put("blankCardNum", totalCount) ;
	    page.setOtherResVal(resMap);
	    
	}
	
	//制卡记录列表
	@Override
	public void getSystemCardCreateRecordList(Page<SystemCardCreateRecord> page){
		String sql = "select ID,CARD_START_INT,CARD_END_INT,CARD_SUM,CREATE_DATE,DESCRIPTION "
				   //+ ",(select count(*) from system_card c where c.CARD_TYPE <> 21 and c.card_number between r.CARD_START_INT and r.CARD_END_INT) as CARD_USERD_SUM "   杨红强,2017-08-10,分布式数据库不支持子查询
				   +" from system_card_create_record r  order by id desc ";
                sql = PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()) ;
		      
                List<SystemCardCreateRecord> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardCreateRecord.class)) ;
                
                //杨红强,2017-08-10,分布式数据库不支持子查询
                for (SystemCardCreateRecord sccrObj : list) {
                	String subSql = "select count(*) from system_card c where c.CARD_TYPE <> 21 and c.card_number between ? and ? " ;
                	int cardUserdSum = getJdbcTemplate().queryForInt(subSql, sccrObj.getCardStartInt(), sccrObj.getCardEndInt()) ;
                	sccrObj.setCardUserdSum(cardUserdSum);
                }
                
                
                
                String sql2 = "select count(*) from system_card_create_record" ;
		Integer totalCount =  getJdbcTemplate().queryForInt(sql2);
		if(list != null && list.size() > 0){
			for(SystemCardCreateRecord scc : list){
				String dateFt = "yyyy-MM-dd HH:mm:ss" ;
				SimpleDateFormat sdf = new SimpleDateFormat(dateFt) ;
				String createDate = sdf.format(scc.getCreateDate());
				scc.setCreateDateStr(createDate);
			}
		}
		page.setList(list);
		
		page.setCount(totalCount);
	}
	
	//通过制卡记录id得到学习卡列表
	@Override
	public List<SystemCard> getSystemCardListByRecordId(Long recordId){
		String record_sql = "select id, card_start_int as cardStartInt, card_end_int as cardEndInt, card_sum as cardSum, card_userd_sum as cardUserdSum, create_date as createDate, description from system_card_create_record t where t.id = ?";
		List<SystemCardCreateRecord> list = this.getJdbcTemplate().query(record_sql, 
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardCreateRecord.class), recordId);
		List<SystemCard> cardList = new ArrayList<SystemCard>();
		if(!Utils.isListEmpty(list)){
			SystemCardCreateRecord record = list.get(0);
			String sql = "select * from system_card card where card.card_number between ? and ?";
			cardList = this.getJdbcTemplate().query(sql, 
					ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class), record.getCardStartInt(), record.getCardEndInt());
					
		}
		return cardList;
	}
	
	//得到学习卡
	@Override
	public SystemCardQuery getSystemCardById(Long id){
		String sql  = "select * from system_card t where t.id = ? ";
		List<SystemCardQuery> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardQuery.class), id);
		if(!Utils.isListEmpty(list))
			return list.get(0);
		return null;
	}
	
	//删除学习卡
	@Override
	public int delSystemCardById(Long id){
		String sql  = "update system_card t set t.status = ?, t.last_update_date = sysdate() where t.id = ? ";
		
		return this.getJdbcTemplate().update(sql, Constants.SYSTEM_CARD_STATUS_DELETE, id);
	}
	
	//保存学习卡
	@Override
	public int saveSystemCard(SystemCard card){
		Long id = card.getId();
		if(null != id && id>0){
			StringBuffer sqlBf = new StringBuffer("update system_card set id = :id ");
			if(StringUtils.isNotBlank(card.getCardNumber())){
				sqlBf.append(",card_number = :cardNumber "); 
			}
			if(StringUtils.isNotBlank(card.getCardPassword())){
				sqlBf.append(",card_password = :cardPassword "); 
			}
			if(null != card.getCardType() && card.getCardType() >0){
				sqlBf.append(",card_type = :cardType "); 
			}
			if(null != card.getIsnotBind() && card.getIsnotBind() >0){
				sqlBf.append(",isnot_bind = :isnotBind "); 
			}
			if(null != card.getFaceValue() && card.getFaceValue() >0){
				sqlBf.append(",face_value = :faceValue "); 
			}
			if(null != card.getUsefulDate()){
				sqlBf.append(", useful_date = :usefulDate ");
			}
			if(null != card.getBindDate()){
				sqlBf.append(",bind_date = :bindDate "); 
			}
			if(null != card.getSellStyle()){
				sqlBf.append(",sell_style = :sellStyle "); 
			}
			if(null != card.getBalance() && card.getBalance() >0){
				sqlBf.append(",balance = :balance ");
			}
			sqlBf.append(" where id = :id ");
			return this.getNamedParameterJdbcTemplate().update(sqlBf.toString(), new BeanPropertySqlParameterSource(card));
		}else{
			card.setId(this.getSequence("system_card_seq"));
			List<SystemCard> list = new ArrayList<SystemCard>();
			list.add(card);
			return this.saveList(saveSystemCard_SQL, list).length;
		}
		
	}
	
	//批量保存学习卡
	@Override
	public int saveSystemCardBatch(List<SystemCard> list){
		int size = 0;
		if(!Utils.isListEmpty(list)){
			for(SystemCard c : list){
				c.setId(this.getSequence("system_card"));//YHQ，2017-03-18，_seq"));
				size += this.getNamedParameterJdbcTemplate().update(saveSystemCard_SQL, new BeanPropertySqlParameterSource(c));
			}
			//size = this.saveList(saveSystemCard_SQL, list).length;
		}
		return size;
	}
	
	//保存制卡记录
	public int saveSystemCardCreateRecord(SystemCardCreateRecord record){
		record.setId(this.getSequence("system_card_create_record"));//YHQ，2017-03-18，_seq"));
		/*List<SystemCardCreateRecord> list = new ArrayList<SystemCardCreateRecord>();
		list.add(record);
		return this.saveList(saveSystemCardCreateRecord_SQL, list).length; */
		
		return this.getNamedParameterJdbcTemplate().update(saveSystemCardCreateRecord_SQL, new BeanPropertySqlParameterSource(record));
	}
	
	//批量导入学习卡
	@Override
	public int importSystemCard(List<SystemCard> list){
		if(!Utils.isListEmpty(list)){
			for(SystemCard card : list){
				card.setId(this.getSequence("system_card_seq"));
			}
			return this.saveList(saveSystemCard_SQL, list).length;
		}
		return 0;
	}
	
	//卡对应的用户
	public SystemUser getSystemCardUserByCardId(Long cardId){
		String sql = "select t.*,t2.bind_date,t3.ACCOUNT_NAME as accountName from system_user t , system_card_user t2 , system_account t3" +
				" where t.id = t2.user_id AND t2.USER_ID = t3.USER_ID and t2.card_id = ?";
		List<SystemUser> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), cardId);
		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	//得到卡号流水号最大的一个
	@Override
	public SystemCard getSystemCardMaxSerialNumber(){	
 		/*String sql = " select * from system_card where length(card_number) = ? and substr(card_number,0,2) = ? " +
				" order by card_number desc ";*/
 		String sql = " select * from system_card where length(card_number) = ? and substr(card_number,1,2) = ? " +
				" order by card_number desc limit 0,1 ";//YHQ，2017-03-23
		List<SystemCard> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class), 
				Constants.SYSTEM_CARD_LENGTH, SystemCardUtil.getCurrentYear() );

		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	

	@Override
	public SystemCard getSystemCardMaxSerialNumberBysellStyle(Integer sellStyle) {		
		/*String sql = " select * from system_card where length(card_number) = ? and substr(card_number,0,2) = ? and substr(card_number,3,2) = ? " +
				" order by card_number desc ";*/
		String sql = " select * from system_card where length(card_number) = ? and substr(card_number,1,2) = ? and substr(card_number,3,2) = ? " +
				" order by card_number desc limit 0,1"; //YHQ，2017-03-23
		String selStyleCode = "";
		if (sellStyle == 1) 		selStyleCode = "01";
		else if (sellStyle == 2)	selStyleCode = "02";
		else {}
		
		List<SystemCard> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class), 
				Constants.SYSTEM_CARD_LENGTH, SystemCardUtil.getCurrentYear(), selStyleCode);

		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	//得到卡类型下的空白卡
	@Override
	public List<SystemCard> getSystemCardNoBindListByCardType(Long cardTypeId, Integer quantity){
		String sql = "select * from system_card t where t.card_type = ? and t.isnot_bind = 0 and t.face_value > 0 " +
				" and t.status = 1 and t.useful_date > sysdate() and t.isselled =0 and (t.sell_style is null or t.sell_style=1) and order_id is null";
		List<Object> params = new ArrayList<Object>();
		params.add(cardTypeId);
		if(null != quantity && quantity >0){
			sql += "  and rownum<= ? ";
			params.add(quantity);
		}
		sql += " order by t.id";
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class), params.toArray());
	}
	
	//绑定学习卡和订单
	public int updateSystemCardBindOrderId(Long cardId, Long orderId){
		String sql  = "update system_card set order_id = ? , ISSELLED = 1, last_update_date = sysdate() where id = ? ";
		System.out.println("sql:"+sql);
		return this.getJdbcTemplate().update(sql, orderId, cardId);
	}
	
	//学习卡类型列表
	@Override
	public void getSystemCardTypeList(Page<SystemCardTypeQuery> page, String cardTypeName, String startDate, String endDate){
		StringBuffer sb = new StringBuffer();
		List<Object> pl = new ArrayList<Object>();
		sb.append("select * from system_card_type t where 1=1");
		if(StringUtils.isNotBlank(cardTypeName)){
			sb.append(" and t.card_type_name like ?");
			pl.add("%" + cardTypeName.trim() + "%");
		}
		
		/*
		if(StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endDate)){
			
		}else if(StringUtils.isNotBlank(endDate) && StringUtils.isBlank(startDate)){
			
		}else if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
			
		}
		*/
		
		if(StringUtils.isNotBlank(startDate)){
			/*sb.append(" and to_char(t.start_date, 'yyyy-MM-dd') >= ?");
			pl.add(startDate);*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sb.append(" and t.start_date >= " + FuncMySQL.shortDateForUpdate(startDate) + " ");
		}
		if(StringUtils.isNotBlank(endDate)){
			/*sb.append(" and to_char(t.end_date, 'yyyy-MM-dd') <= ?");
			pl.add(endDate);*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sb.append(" and t.end_date <= " + FuncMySQL.shortDateForUpdate(endDate) + " ");
		}
		
		sb.append(" and t.is_sdsync >-1");
		sb.append(" order by id desc");
		List<SystemCardTypeQuery> list = this.getList(PageUtil.getPageSql(sb.toString(), page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardTypeQuery.class));
		Integer totalCount = getCount(sb.toString(), pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}
	
	//根据卡类型得到学习卡总数
	@Override
	public Integer getSystemCardNumByCardType(Long typeId, int type){
		List<Object> pl = new ArrayList<Object>();
		/*String sql = "select * from system_card  t where t.card_type = ? ";
		pl.add(typeId);
		if(type == 2){
			sql += " and t.isnot_bind = 1";
		}else if(type == 3){
			sql += " and t.isnot_bind = 0 and (t.isselled = 0 or t.isselled is null)";
		}
		return getCount(sql, pl.toArray());*/
		
		//YHQ，2017-03-26
		String sql = "select count(*) from system_card  t where t.card_type =  "+ typeId;
		Integer totalCount = 0 ;
		if (type == 1) { //总数
			totalCount = getJdbcTemplate().queryForInt(sql) ;
		}
		if (type == 2) { //已使用，已售出
			sql += " and t.isselled = 1 " ;
			totalCount = getJdbcTemplate().queryForInt(sql) ;
		}
		return totalCount ;		
	}
	
	//学习卡类型列表
	@Override
	public List<SystemCardType> getSystemCardTypeList(){
		String sql = "select * from system_card_type t";
		List<SystemCardType> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardType.class));
		if(!Utils.isListEmpty(list))
			return list;
		return null;
	}
	
	//查看卡类型
	@Override
	public SystemCardTypeQuery getSystemCardTypeById(Long id){
		String sql = "select * from system_card_type t where t.id = ?";
		List<SystemCardTypeQuery> list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardTypeQuery.class), id);
		if(!Utils.isListEmpty(list))
			return list.get(0);
		return null;
	}
	
	//更新卡类型
	@Override
	public int saveSystemCardType(SystemCardType type, String [] creditTypes){
		int row = 0;
		if(null != type.getId() && type.getId()>0){
			StringBuffer sqlBf = new StringBuffer("update system_card_type set id = :id ");
			if(StringUtils.isNotBlank(type.getCardTypeName())){
				sqlBf.append(",card_type_name = :cardTypeName "); 
			}
			if(null != type.getStartDate()){
				sqlBf.append(", start_date = :startDate "); 
			}
			if(null != type.getEndDate()){
				sqlBf.append(", end_date = :endDate "); 
			}
			if(StringUtils.isNotBlank(type.getCreditScope())){
				sqlBf.append(", credit_scope = :creditScope "); 
			}
			if(null != type.getCreditSum() && type.getCreditSum() >0){
				sqlBf.append(", credit_sum = :creditSum "); 
			}
			if(null != type.getPrice() && type.getPrice() >0){
				sqlBf.append(", price = :price "); 
			}
			if(null != type.getEvpValue() && type.getEvpValue() >0){
				sqlBf.append(", evp_value = :evpValue ");
			}
			if(null != type.getIsNetpay() && type.getIsNetpay() >0){
				sqlBf.append(", is_netpay = :isNetpay ");
			}
			if(null != type.getIsSdsync() && type.getIsSdsync() >0){
				sqlBf.append(", is_sdsync = :isSdsync ");
			}
			sqlBf.append(" where id = :id ");
			row = this.getNamedParameterJdbcTemplate().update(sqlBf.toString(), new BeanPropertySqlParameterSource(type));
			
			//更新授权类型,字表
			updateCreditType(type.getId(), creditTypes, type.getBalance());
			
			//更新学习卡有效时间
			updateSystemCardByCardType(type.getId(), type.getEndDate());
			
			//更新学员订单表卡有效时间-----taoLiang
			Date fullDate = null;
			try {
				String fullDateStr = new SimpleDateFormat("yyyy-MM-dd").format(type.getEndDate())+" 23:59:59";
				fullDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fullDateStr);
			} catch (ParseException e) {
				fullDate = type.getEndDate();
			}
			updateSystemCardSetOrderByCardType(type.getId(), fullDate);
			
			return row;
		}else{	//新增
			Long id = this.getSequence("system_card_type");//YHQ，2017-02-18   _seq");
			type.setId(id);
			row = this.getNamedParameterJdbcTemplate().update(saveSystemCardType_SQL, new BeanPropertySqlParameterSource(type));
			//更新授权类型
			updateCreditType(id, creditTypes, type.getBalance());
		}
		return row;
	}
	
	//更新卡类型的同时 更新学习卡
	private int updateSystemCardByCardType(Long typeId, Date usefDate){
		return this.getJdbcTemplate().update(
				"update system_card t set t.useful_date = ?, t.last_update_date = sysdate() where t.card_type = ?", usefDate, typeId);
	}
	
	//更新卡类型的同时 更新学员订单表-----taoLiang
	private int updateSystemCardSetOrderByCardType(Long typeId, Date usefDate){
		return this.getJdbcTemplate().update(
				"UPDATE system_card_set_order t SET t.USEFUL_DATE = ? WHERE t.CARD_TYPE_ID = ?", usefDate, typeId);
	}
	
	//更新授权类型
	private void updateCreditType(Long typeId, String [] creditTypes, String balance){
		//先删除原来的
		this.getJdbcTemplate().update("delete from system_paycard_credit where card_type_id = ?", typeId);
		//新增
		if(null != creditTypes && creditTypes.length>0){
			for(String creditType: creditTypes){
				this.getJdbcTemplate().update("insert into system_paycard_credit(card_type_id, credit_type, balance)values (?,?,?)",
						typeId, creditType, balance);
			}
		}
	}
	
	//删除卡类型
	@Override
	public int deleteSystemCardType(Long id){
		String sql = "delete from system_card_type where id = ?";
		return this.getJdbcTemplate().update(sql, id);
	}
	@Override
	public int deleteSystemPaycardCredit(Long typeId){	//学习卡授权类型表
		return this.getJdbcTemplate().update("delete from system_paycard_credit where card_type_id = ?", typeId);
	}
	@Override
	public int deleteSystemPaycardCourse(Long typeId){	//学习卡类型课程限制表
		return this.getJdbcTemplate().update("delete from system_paycard_organ where card_type_id = ?", typeId);
	}
	@Override
	public int deleteSystemPaycardOrgan(Long typeId){	//学习卡类型机构限制表
		return this.getJdbcTemplate().update("delete from system_paycard_course where card_type_id = ?", typeId);
	}
	
	//得到授权/未授权 的课程列表
	@Override
	public List<StudyCourseVO> getStudyCourse(Page<StudyCourseVO> page, Long typeId, boolean isAuthorized, String name){
		StringBuffer sb = new StringBuffer();
		List<Object> pl = new ArrayList<Object>();
		if(isAuthorized){
			sb.append("select t2.* from system_paycard_course t1 join study_course t2 on t1.course_id = t2.id where t1.card_type_id = ?");
			pl.add(typeId);
		}else{
			sb.append("select * from study_course t2 where t2.id not in" +
					" (select t1.course_id from system_paycard_course t1 where t1.card_type_id = ?)" +
					" and t2.status = ?");
			pl.add(typeId);
			pl.add(Constants.STUDY_COURSE_STATUS_PUBLISHED);
		}
		if(StringUtils.isNotBlank(name)){
			sb.append(" and t2.study_course_name like ?");
			pl.add("%" + name.trim() + "%");
		}
		List<StudyCourseVO> list = this.getList(PageUtil.getPageSql(sb.toString(), page.getCurrentPage(),page.getPageSize()),  pl, 
				ParameterizedBeanPropertyRowMapper.newInstance(StudyCourseVO.class));
		Integer totalCount = getCount(sb.toString(), pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
		return list;
	}
	
	//得到授权/未授权 的机构列表   ////20141118此方法已停用
	@Override
	public List<SystemAdminOrgan> getSystemOrgList(Page<SystemAdminOrgan> page, Long typeId, boolean isAuthorized, String name){
		StringBuffer sb = new StringBuffer();
		List<Object> pl = new ArrayList<Object>();
		if(isAuthorized){
			sb.append("select t2.* from system_paycard_organ t1 join system_admin_organ t2 on t1.org_id = t2.organ_id where t1.card_type_id = ?");
			pl.add(typeId);
		}else{
			sb.append("select t2.* from system_admin_organ t2 where t2.organ_id not in " +
					" (select t1.org_id from system_paycard_organ t1 where t1.card_type_id = ?)" +
					" and t2.organ_id = ?");
			pl.add(typeId);
			pl.add(Constants.AJPX_ADMIN_ORG_BJ_ID);
		}
		
		if(StringUtils.isNotBlank(name)){
			sb.append(" and t2.name like ?");
			pl.add("%" + name.trim() + "%");
		}
		List<SystemAdminOrgan> list = this.getList(PageUtil.getPageSql(sb.toString(), page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemAdminOrgan.class));
		Integer totalCount = getCount(sb.toString(), pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
		return list;
	}
	
	//得到授权类型列表
	@Override
	public List<SystemCreditType> getSystemCreditTypeList(Long typeId){
		String sql = "select * from system_credit_type t";
		List<SystemCreditType> list = null;
		if(typeId>0){
			sql += " where  t.credit_type in (select p.credit_type from system_paycard_credit p where p.card_type_id = ?)";
			list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCreditType.class), typeId);
		}else{
			list = this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCreditType.class));
		}
		
		if(!Utils.isListEmpty(list)){
			return list;
		}
		return null;
	}
	
	//授权机构
	@Override
	public int saveSystemPaycardOrgan(Long typeId, Long [] orgIds){
		int row = 0;
		int len = orgIds.length;
		String sql = "insert into system_paycard_organ(card_type_id, org_id) values (?,?)";
		for(Long orgId : orgIds){
			row += this.getJdbcTemplate().update(sql, typeId, orgId);
		}
		return row==len?1:0;
	}
	
	//授权课程
	@Override
	public int saveSystemPaycardCourse(Long cardTypeId, Long [] courseIds){
		int row = 0;
		String sql = "insert into system_paycard_course(card_type_id, course_id) values (?,?)";
		for(Long courseId: courseIds){
			String query_sql = "select * from system_paycard_course t where t.card_type_id = ? and t.course_id = ?";
			List<SystemPayCardCourse> list = this.getList(query_sql, 
					ParameterizedBeanPropertyRowMapper.newInstance(SystemPayCardCourse.class), cardTypeId, courseId);
			if(list.isEmpty())
				row += this.getJdbcTemplate().update(sql, cardTypeId, courseId);
		}
		return row;
	}
	
	//根据卡类型得到授权站点
	public List<SystemSite> getSystemSiteListByCardTypeId(Long typeId){
		String sql = "select t1.* from System_Site t1 " +
				" left join system_card_type_site t2 on t1.id = t2.site_id where t2.cardtype_id = ? and t1.status = ?";
		return this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class), typeId, Constants.STATUS_1);
	}
	
	//授权课程分类下的所有课程
	@Override
	public int saveSystemPaycardCourseByCourseType(Long cardTypeId, Long courseType, String creditYear, String studyCourseName, String siteIds){
		int row = 0;
		
		StringBuilder sql = new StringBuilder();
		
		//已授权课程未与卡类型绑定的
		sql.append("select distinct t2.id" +
				" from system_course_credit t " +
				" left join study_course t2 on t.course_id = t2.id" +
				" left join system_credit_type t3 on t3.credit_type = t.credit_type " +
				" left join system_org t4 on t4.id = t.org_id" +
				" left join study_course_type t5 on t5.id = t.course_type" +
				" where t.course_type = ? and t.course_id not in" +
				" (select c.course_id from system_paycard_course c where c.card_type_id = ?)" +
				" and t.credit_year = ?  ");
		
		List<Object> obj = new ArrayList<Object>();
		
		obj.add(courseType);
		obj.add(cardTypeId);
		
		if (StringUtils.isNotBlank(creditYear)) {
			obj.add(creditYear);
		}else{
			obj.add(DateUtil.getYear());
		}
		
		if(!"".equals(siteIds)){
			sql.append(" and t.site_id in ( ");
			String arr [] = siteIds.split(",");
			int i = 0;
			for(String id : arr){
				if(StringUtils.isNotBlank(id)){
					if(i == arr.length -1){
						sql.append(" ? ");
					}else{
						sql.append(" ?, ");
					}
					obj.add(new Long(id));
				}
				i++;
			}
			sql.append(" )");
		}
		
		
		if(StringUtils.isNotBlank(studyCourseName)){
			sql.append(" and t2.study_course_name like ?");
			obj.add("%" + studyCourseName + "%");
		}
		
		List<NcmeCourseCredit> list = getList(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(NcmeCourseCredit.class), obj.toArray());
		if(!Utils.isListEmpty(list)){
			for(NcmeCourseCredit credit : list){
				Long courseId = credit.getId();
				String insertSql = "insert into system_paycard_course(card_type_id, course_id) values (?,?)";
				row += this.getJdbcTemplate().update(insertSql, cardTypeId, courseId);
			}
		}else{
			row = -1;
		}

		return row;
	}
	
	//删除卡类型 课程
	@Override
	public int deleteCourseAuthorized(Long typeId, Long courseId){
		String  sql = "delete from system_paycard_course where card_type_id = ? and course_id = ?";
		return this.getJdbcTemplate().update(sql, typeId, courseId);
	}
			
	//删除卡类型 机构
	@Override
	public int deleteOrgAuthorized(Long typeId, Long orgId){
		String  sql = "delete from system_paycard_organ where card_type_id = ? and org_id = ?";
		return this.getJdbcTemplate().update(sql, typeId, orgId);
	}
	
	//根据名称查询卡类型
	@Override
	public int getSystemCardTypeByName(String name) {
		String sql = "select count(*) from system_card_type t where t.card_type_name = ?";
		int count = 1;
		try {
			count = getJdbcTemplate().queryForInt(sql, name);
		} catch (DataAccessException e) {
		}
		return count;
	};
	//=========卡类型分配管理
	@Override
	public int saveSystemCardAllotCardType(Long typeId, Long cardId, Date usefulDate, Long faceValue){
		StringBuilder sql = new StringBuilder() ;
		List<Object> params = new ArrayList<Object>();
		
		SystemCard card = this.getSystemCardById(cardId);
		if(null != card){
			//if(card.getBalance() == 0.0D){  //《培训平台后台管理系统需求规格说明书V1.3.0》中4.3.3.2需求:可按卡类型查询学习卡，更改其卡类型
		
				sql.append("update system_card t set t.card_type = ? ");
				params.add(typeId);
				
				sql.append(" , t.last_update_date = sysdate() ");
				
				sql.append(" , t.status = ?");
				params.add(Constants.SYSTEM_CARD_STATUS_EFFECTIVE);
				
				if(null != usefulDate){
					sql.append(" , t.useful_date = ? ");
					params.add(usefulDate);
				}
				if(null !=faceValue && faceValue >0){
					sql.append(" , t.face_value = ? ");
					params.add(faceValue);
					sql.append(" , t.balance = ?");
					params.add(faceValue);
				}
				sql.append(" where t.id = ? ");
				params.add(cardId);
				return this.getJdbcTemplate().update(sql.toString(), params.toArray());
			//} 
		} else {
			return -1 ;
		}					
	}
	
	//=========卡类型分配管理，批量
	@Override
	public int saveSystemCardAllotCardTypeByNum(Long typeId, Long cardId, Date usefulDate, Long faceValue,String cardNumber,String cardNumberEnd){
		StringBuilder sql = new StringBuilder() ;
		List<Object> params = new ArrayList<Object>();		
		
		sql.append("update system_card t set t.card_type = ? ");
		params.add(typeId);		
		sql.append(" , t.last_update_date = sysdate() ");		
		sql.append(" , t.status = ?");
		params.add(Constants.SYSTEM_CARD_STATUS_EFFECTIVE);
		
		if(null != usefulDate){
			sql.append(" , t.useful_date = ? ");
			params.add(usefulDate);
		}
		if(null !=faceValue && faceValue >0){
			sql.append(" , t.face_value = ? ");
			params.add(faceValue);
			sql.append(" , t.balance = ?");
			params.add(faceValue);
		}
		sql.append(" where t.card_type = 21 ");
		
		/*
		if(null != cardNumber && null != cardNumberEnd){
			sql.append(" and t.CARD_NUMBER >= ? ");
			params.add(cardNumber);
			sql.append(" and t.CARD_NUMBER <= ? ");
			params.add(cardNumberEnd);
		}
		*/
		
		//YHQ，2017-06-08
		if(StringUtils.isNotBlank(cardNumber)){
			sql.append(" and t.CARD_NUMBER >= ? ");
			params.add(cardNumber);
		}
		if(StringUtils.isNotBlank(cardNumberEnd)){
			sql.append(" and t.CARD_NUMBER <= ? ");
			params.add(cardNumberEnd);
		}
		
		//sql.append(" and t.BALANCE = 0  limit  " + cardId);
		sql.append(" limit " + cardId);
		return this.getJdbcTemplate().update(sql.toString(), params.toArray());		
	}
	
	@Override
	public List<SystemCard> getSystemCardListByAllotNum(Long typeId, Integer allotNum, String cardNumber, String cardNumberEnd){
		String sql = "select * from system_card where card_type = ? and status = 1 ";
		List<Object> pl = new ArrayList<Object>();
		pl.add(typeId);
		if(StringUtils.isNotBlank(cardNumber) && StringUtils.isNotBlank(cardNumberEnd)){
			sql += " and card_number between ? and ?";
			pl.add(cardNumber);
			pl.add(cardNumberEnd);
		}
		
		sql += " and ROWNUM <= ?";
		pl.add(allotNum);
		
		return this.getList(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class), pl.toArray());
	}
	
	//============卡订单
	
	//学习卡订单列表
	@Override
	public void getSystemCardOrderList(Page<SystemCardOrderQuery> page, SystemCardOrderQuery q){
		//订单和学习卡是一对多关系,暂不在此sql中体现
		StringBuffer sb = new StringBuffer();
		StringBuffer sbcount = new StringBuffer();
		List<Object> pl = new ArrayList<Object>();
		sb.append("select t.*, t2.real_name user_name, t3.card_type_name");
		sb.append(" from system_card_order t left join system_user t2 on t.user_id = t2.id");
		sb.append("	left join system_card_type t3 on t.card_type_id = t3.id where 1 = ?");
		
		sbcount.append("select count(1) as count");
		sbcount.append(" from system_card_order t left join system_user t2 on t.user_id = t2.id");
		sbcount.append(" left join system_card_type t3 on t.card_type_id = t3.id where 1 = ?");
		pl.add(1);
		if(null != q){
			
			/**t2表中无此字段，故先注解掉
			String accountName = q.getAccountName();
			if(StringUtils.isNotBlank(accountName)){
				sb.append(" and t2.account_name like ?");
				pl.add("%" + accountName.trim() + "%");
			}*/
			
			String userName = q.getUserName();
			if(StringUtils.isNotBlank(userName)){
				sb.append(" and t2.real_name like ?");
				sbcount.append(" and t2.real_name like ?");
				pl.add("%" + userName.trim() + "%");
			}
			
			Integer orderType = q.getOrderType();
			if(orderType != -100){
				sb.append(" and t.order_type = ?");
				sbcount.append(" and t.order_type = ?");
				pl.add(orderType);
			}
			
			String orderStart = q.getOrderDateStart();
			String orderEnd = q.getOrderDateEnd();
			if(StringUtils.isNotBlank(orderStart) && StringUtils.isBlank(orderEnd)){
				/*sb.append(" and t.order_date > to_date(?,'yyyy-mm-dd')");
				pl.add(orderStart);*/
				
				//YHQ,2017-06-22,函数替换，迁移到分布式数据库
				sb.append(" and t.order_date > " + FuncMySQL.shortDateForUpdate(orderStart) + " ") ;
				sbcount.append(" and t.order_date > " + FuncMySQL.shortDateForUpdate(orderStart) + " ") ;
			}else if(StringUtils.isBlank(orderStart) && StringUtils.isNotBlank(orderEnd)){
				/*sb.append(" and t.order_date < to_date(?,'yyyy-mm-dd')");
				pl.add(orderEnd);*/
				
				//YHQ,2017-06-22,函数替换，迁移到分布式数据库
				sb.append(" and t.order_date < " + FuncMySQL.shortDateForUpdate(orderEnd) + " ");
				sbcount.append(" and t.order_date < " + FuncMySQL.shortDateForUpdate(orderEnd) + " ");
			}else if(StringUtils.isNotBlank(orderStart) && StringUtils.isNotBlank(orderEnd)){
				/*sb.append(" and t.order_date > to_date(?,'yyyy-mm-dd') and t.order_date < to_date(?,'yyyy-mm-dd')");
				pl.add(orderStart);
				pl.add(orderEnd);*/				
				
				//YHQ,2017-06-22,函数替换，迁移到分布式数据库								
				sb.append(" and t.order_date > " + FuncMySQL.shortDateForUpdate(orderStart) + " and t.order_date < " + FuncMySQL.shortDateForUpdate(orderEnd) + " ");
				sbcount.append(" and t.order_date > " + FuncMySQL.shortDateForUpdate(orderStart) + " and t.order_date < " + FuncMySQL.shortDateForUpdate(orderEnd) + " ");
			}
			
			Integer status = q.getStatus();
			if(status != -100){
				sb.append(" and t.status = ?");
				sbcount.append(" and t.status = ?");
				pl.add(status);
			}
			
			String paymodeCode = q.getPaymodeCode();
			if(StringUtils.isNotBlank(paymodeCode)){
				sb.append(" and t.paymode_code = ?");
				sbcount.append(" and t.paymode_code = ?");
				pl.add(paymodeCode);
			}
			
			Integer invoiceStatus = q.getInvoiceStatus();
			if(invoiceStatus != -100){
				sb.append(" and t.invoice_status = ?");
				sbcount.append(" and t.invoice_status = ?");
				pl.add(invoiceStatus);
			}
			
			String orderNumber = q.getOrderNumber();
			if(StringUtils.isNotBlank(orderNumber)){
				sb.append(" and t.order_number like ?");
				sbcount.append(" and t.order_number like ?");
				pl.add("%" + orderNumber.trim() + "%");
			}
			
			String cardTypeName = q.getCardTypeName();
			if(StringUtils.isNotBlank(cardTypeName)){
				sb.append(" and t3.card_type_name like ?");
				sbcount.append(" and t3.card_type_name like ?");
				pl.add("%" + cardTypeName.trim() + "%");
			}
			Long cardTypeId = q.getCardTypeId();
			if(cardTypeId != null && !cardTypeId.equals(-1L)){
				sb.append(" and t.CARD_TYPE_ID = ?");
				sbcount.append(" and t.CARD_TYPE_ID = ?");
				pl.add(cardTypeId);
			}
			sb.append(" order by t.id desc ");
			sbcount.append(" order by t.id desc ");
			
		}
		List<SystemCardOrderQuery> list = this.getList(PageUtil.getPageSql(sb.toString(), page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrderQuery.class));
		//Integer totalCount = getCount(sbcount.toString(), pl.toArray());
		Integer totalCount = getJdbcTemplate().queryForInt(sbcount.toString(), pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}
	
	//根据订单id得到该订单对应的学习卡
	@Override
	public List<SystemCardQuery> getSystemCardListByOrderId(Long orderId, Page<SystemCardQuery> page){
		String sql = "select * from system_card sc where sc.order_id  = ? and sc.status = 1";
		List<Object> pl = new ArrayList<Object>();
		pl.add(orderId);
		List<SystemCardQuery> list = this.getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()), pl,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardQuery.class));
		Integer totalCount = getCount(sql, pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
		return list;
	}
	
	//卡订单对象
	@Override
	public SystemCardOrderQuery getSystemCardOrderById(Long id){
		String sql = "select t.*, (select u.real_name from system_user u where u.id = t.user_id) user_name," +
				" (select tp.card_type_name from system_card_type tp where tp.id = t.card_type_id) card_type_name" +
				" from system_card_order t where t.id = ?";
		List<SystemCardOrderQuery> list = this.getList(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrderQuery.class), id);
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
		
	//卡订单修改
	@Override
	public int updateSystemCardOrder(SystemCardOrder order){
		if(null != order.getId() && order.getId() >0){
			StringBuffer sqlBf = new StringBuffer("update system_card_order set id = :id ");
			if(null != order.getCardTypeId() && order.getCardTypeId() >0){
				sqlBf.append(", card_type_id = :cardTypeId "); 
			}
			if(null != order.getUserId() && order.getUserId() >0){
				sqlBf.append(", user_id = :userId "); 
			}
			if(null != order.getOrderDate()){
				sqlBf.append(", order_date = :orderDate "); 
			}
			if(null != order.getQuantity() && order.getQuantity() >0){
				sqlBf.append(", quantity = :quantity "); 
			}
			if(null != order.getPrice() && order.getPrice() >=0){
				sqlBf.append(", price = :price "); 
			}
			if(null != order.getAmount() && order.getAmount() >0){
				sqlBf.append(", amount = :amount "); 
			}
			if(null != order.getPayDate()){
				sqlBf.append(", pay_date = :payDate ");
			}
			if(StringUtils.isNotBlank(order.getOrderResource())){
				sqlBf.append(", order_resource = :orderResource "); 
			}
			if(StringUtils.isNotBlank(order.getPaymodeCode())){
				sqlBf.append(", paymode_code = :paymodeCode "); 
			}
			if(null != order.getInvoiceStatus() && order.getInvoiceStatus() >0){
				sqlBf.append(", invoice_status = :invoiceStatus "); 
			}
			if(StringUtils.isNotBlank(order.getInvoiceTitle())){
				sqlBf.append(", invoice_title = :invoiceTitle "); 
			}
			if(StringUtils.isNotBlank(order.getRecipient())){
				sqlBf.append(", recipient = :recipient "); 
			}
			if(StringUtils.isNotBlank(order.getTel())){
				sqlBf.append(", tel = :tel "); 
			}
			if(StringUtils.isNotBlank(order.getAddress())){
				sqlBf.append(", address = :address "); 
			}
			if(StringUtils.isNotBlank(order.getOrderOper())){
				sqlBf.append(", order_oper = :orderOper "); 
			}
			if(null != order.getOperDate()){
				sqlBf.append(", oper_date = :operDate "); 
			}
			if(StringUtils.isNotBlank(order.getRemark())){
				sqlBf.append(", remark = :remark "); 
			}
			if(StringUtils.isNotBlank(order.getPostCode())){
				sqlBf.append(", post_code = :postCode "); 
			}
			if(StringUtils.isNotBlank(order.getOrderNumber())){
				sqlBf.append(", order_number = :orderNumber "); 
			}
			if(order.getStatus() != null ){
				sqlBf.append(", STATUS = :status "); 
			}
			sqlBf.append(" where id = :id ");
			return this.getNamedParameterJdbcTemplate().update(sqlBf.toString(), new BeanPropertySqlParameterSource(order));
		}else{
			return 0;
		}
	}
	
	//卡订单删除
	public int deleteSystemCardOrder(Long id){
		//String sql = "delete system_card_order t where t.id = ?";
		String sql = "update system_card_order t set t.status = ? where t.id = ?";
		return this.getJdbcTemplate().update(sql, Constants.SYSTEM_NORMAL_STATUS, id);
	}
	
	//=========卡状态管理
	
	//更改状态,重置余额
	@Override
	public int updateSystemCard(Long id, Integer status, Double balance){
		int row = 0;
		List<Object> p = new ArrayList<Object>();
		String sql = "update system_card t set t.id = ?";
		p.add(id);
		if(balance >= 0){
			sql += " , t.balance = ?, t.last_update_date = sysdate() ";
			p.add(balance);
		}
		if(status > -100){
			sql += " , t.status = ? ";
			p.add(status);
		}
		
		if(status == 1){
			
			List<SystemUser> list = this.getJdbcTemplate().query("select distinct t2.id as user_id, t2.* from system_card_user t1 join system_user t2 on t1.user_id = t2.id where t1.card_id = ?", ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class),id);
			
			if(list.size()>0){
				sql+=",t.isnot_bind = 1";
			}else{
				
				sql+=",t.isnot_bind = 0";
			}
			
		}
		sql += " where t.id = ?";
		p.add(id);
		row = this.getJdbcTemplate().update(sql, p.toArray());
		return row; 
	}
	
	@Override
	public int updateSystemCardSellStyle(Long id, Integer status){
		List<Object> p = new ArrayList<Object>();
		String sql = "update system_card t set t.sell_style = ? where t.id = ?";
		p.add(status);
		p.add(id);
		return this.getJdbcTemplate().update(sql, p.toArray());
	}
	
	@Override
	public int updateSystemCardSelled(Long id, Integer status){
		List<Object> p = new ArrayList<Object>();
		String sql = "update system_card t set t.isselled = ? where t.id = ?";
		p.add(status);
		p.add(id);
		return this.getJdbcTemplate().update(sql, p.toArray());
	}
	
	//绑定用户
	//to 应该加上站点id
	@Override
	public int addSystemCardUserBind(Long cardId, Long [] userIds, Long siteId){
		if(null != userIds && userIds.length>0){
			List<SystemCardUser> scuList = new ArrayList<SystemCardUser>();
			for(Long userId : userIds){
				SystemCardUser scu = new SystemCardUser();
				scu.setCardId(cardId);
				scu.setUserId(userId);
				scu.setSiteId(siteId);
				
				//YHQ，2017-03-18
				Date date = new Date() ;
				String dateFt = "yyyy-MM-dd HH:mm:ss" ;
				SimpleDateFormat sdf = new SimpleDateFormat(dateFt) ;
				String nowSdf = sdf.format(date) ;		
				try {
					scu.setBindDate(new SimpleDateFormat(dateFt).parse(nowSdf));
				} catch (Exception e) {}
				
				//scu.setBindDate(new Date());
				scuList.add(scu);
			}
			//还需要把该卡置为已绑定
			String sql = "update system_card d set d.isnot_bind = 1,d.`STATUS` = 2, d.isselled = 1,  d.last_update_date = sysdate() where d.id = ?";
			this.getJdbcTemplate().update(sql, cardId);
			
			return this.saveList(saveSystemCardUser_SQL, scuList).length;
		}
		return 0;
	}
	
	//解绑用户
	@Override
	public int deleteSystemCardUserBind(Long cardId, Long [] userIds){
		int rows = 0;
		if(null != userIds && userIds.length>0){
			for(Long userId : userIds){
				rows = this.getJdbcTemplate().update(deleteSystemCardUser_SQL, cardId, userId);
				//删除该用户绑定该卡的订单信息
				String selectSysCardSql = "SELECT t.CARD_NUMBER FROM system_card t where t.ID = "+ cardId;
				List<SystemCard> cardList = getJdbcTemplate().query(selectSysCardSql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class));
				if(cardList.size() > 0){
					SystemCard card = cardList.get(0);
					if(card.getCardNumber() != null && !"".equals(card.getCardNumber())){
						String delOrserSql = "delete from system_card_set_order where CARD_NUMBER = ? and USER_ID = ?";
						rows = this.getJdbcTemplate().update(delOrserSql, card.getCardNumber(), userId);
					}
				}
			}
		}
		//还需要把该卡置为未绑定----taoliang
		String sql = "update system_card d set d.isnot_bind = 0,d.`STATUS` = 1, d.isselled = 0,  d.last_update_date = sysdate() where d.id = ?";
		this.getJdbcTemplate().update(sql, cardId);
		
		
		return rows;
	}
	
	//得到用户(绑定/未绑定)
	@Override
	public void getSystemUserList(Page<SystemUser> page, Long cardId, SystemUser user, boolean isBind){
		String sql = "",sqlcount="";
		List<Object> p = new ArrayList<Object>();
		if(isBind){
			sql = "select distinct t2.id as user_id, t2.* from system_card_user t1 join system_user t2 on t1.user_id = t2.id where t1.card_id = ?";
			sqlcount="select count(distinct t2.id) as count from system_card_user t1 join system_user t2 on t1.user_id = t2.id where t1.card_id = ?";
		}else{
			//sql = "select t2.id as user_id, t2.* from system_user t2 where t2.id not in (select u.user_id from system_card_user u where u.card_id=?)";
			sql="select t2.id as user_id, t2.* from system_user t2 left join system_card_user u on t2.id=u.user_id and u.card_id=? where u.user_id is null";
			sqlcount="select count(distinct t2.id) as count from system_user t2 left join system_card_user u on t2.id=u.user_id and u.card_id=? where u.user_id is null";
		}
		p.add(cardId);
		if(null != user){
			if(StringUtils.isNotBlank(user.getRealName())){
				sql += " and t2.real_name like ?";
				sqlcount += " and t2.real_name like ?";
				p.add("%" + user.getRealName().trim() + "%");
			}
			if(StringUtils.isNotBlank(user.getCertificateNo())){
				sql += " and t2.certificate_no like ?";
				sqlcount += " and t2.certificate_no like ?";
				p.add("%" + user.getCertificateNo().trim() + "%");
			}
		}
		List<SystemUser> list = this.getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  p,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		//Integer totalCount = getCount(sql, p.toArray());
		Integer totalCount=getJdbcTemplate().queryForInt(sqlcount,p.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}
	
	//得到其他站点用户(绑定/未绑定)
	@Override
	public void getIcmeSystemUserList(Page<SystemUser> page, String dbname, String domainName, Long cardId, SystemUser user, boolean isBind){
		String sql = "";
		List<Object> p = new ArrayList<Object>();
		if(isBind){
			sql = "select t2.id as user_id, t2.* from system_card_user t1 " +
					" join "+dbname+".icme_user t2 on t1.user_id = t2.id " +
					" left join "+dbname+".icme_site t3 on t3.id = t2.site_id "+
					" where t3.domain_name = ? t1.card_id = ?";
		}else{
			sql = "select t2.id as user_id, t2.* from "+dbname+".icme_user t2 left join "+
				   dbname+".icme_site t3 on t3.id = t2.site_id " +
				  " where t3.domain_name = ? and t2.id not in (select u.user_id from system_card_user u where u.card_id=?)";
		}
		p.add(domainName);
		p.add(cardId);
		if(null != user){
			if(StringUtils.isNotBlank(user.getRealName())){
				sql += " and t2.real_name like ?";
				p.add("%" + user.getRealName().trim() + "%");
			}
			if(StringUtils.isNotBlank(user.getCertificateNo())){
				sql += " and t2.certificate_no like ?";
				p.add("%" + user.getCertificateNo().trim() + "%");
			}
		}
		List<SystemUser> list = this.getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  p,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		Integer totalCount = getCount(sql, p.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}
	
	/*	
	//得到学习卡列表
	@Override
	public void getSystemCardStatusList(Page<SystemCardQuery> page, boolean isAll, 
				String cardNumber, String cardNumberStart, String cardNumberEnd){
		String sql  = "select t.*,(select ct.card_type_name from system_card_type ct where ct.id = t.card_type) cardTypeName from system_card t where 1=1 ";
		List<Object> pl = new ArrayList<Object>();
		if(!isAll){
			sql += "and t.status = ? ";
			pl.add(Constants.SYSTEM_NORMAL_STATUS);
		}
		
		if(StringUtils.isNotBlank(cardNumber)){
			sql += " and t.card_number = ?";
			pl.add(cardNumber.trim());
		}else if(StringUtils.isNotBlank(cardNumberStart) && StringUtils.isBlank(cardNumberEnd)){
			//sql += " and t.card_number like ?";
			sql += " and t.card_number >= ?";
			pl.add(cardNumberStart.trim());
		}else if(StringUtils.isNotBlank(cardNumberEnd) && StringUtils.isBlank(cardNumberStart)){
			sql += " and t.card_number <= ?";
			pl.add(cardNumberEnd.trim());
		}else if(StringUtils.isNotBlank(cardNumberStart) && StringUtils.isNotBlank(cardNumberEnd)){
			sql += " and t.card_number between ? and ?";
			pl.add(cardNumberStart.trim());
			pl.add(cardNumberEnd.trim());
		}
		List<SystemCardQuery> list = this.getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardQuery.class));
		Integer totalCount = getCount(sql, pl.toArray());
		page.setList(list);
		page.setCount(totalCount);
	}
	*/
	
	@Override
	public void getSystemCardStatusList(Page<SystemCardQuery> page, boolean isAll, 
				String cardNumber, String cardNumberStart, String cardNumberEnd){
		String sql  = "select t.*, ct.card_type_name from system_card t,system_card_type ct where ct.id = t.card_type  ";
		List<Object> pl = new ArrayList<Object>();
		if(!isAll){
			sql += "and t.status = ? ";
			pl.add(Constants.SYSTEM_NORMAL_STATUS);
		}
		
		if(StringUtils.isNotBlank(cardNumberStart)){
			sql += " and CAST(t.CARD_NUMBER AS SIGNED) >='" +cardNumberStart+"'";
		}
		if(StringUtils.isNotBlank(cardNumberEnd)){
			sql += " and CAST(t.CARD_NUMBER AS SIGNED) <='" +cardNumberEnd+"'";
		}
		//CHY 获取总页数2017-05-02;
		int listAllCount = getCount(sql, pl.toArray());
		
		List<SystemCardQuery> ori_list = this.getList(PageUtil.getPageSql(sql, page.getCurrentPage(),page.getPageSize()),  pl,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemCardQuery.class));
		page.setList(ori_list);
		page.setCount(listAllCount);
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-18
	 * @param    cardTypeId
	 * @param    cvSetList[]
	 * @return   boolean
	 * 方法说明： YHQ，绑定学习卡类型到项目，2017-03-18
	 */
	@Override
	public boolean bindCardTypeToCVSet(Long cardTypeId, Long cvSetList[]) {
		boolean resFlag = false ;
		if (cardTypeId != null && cardTypeId != 0L && cvSetList != null && cvSetList.length > 0) {
			String sql = "" ;
			Long cvSetId = 0L ;
			int rsNum = 0 ;
			for (int i = 0 ; i < cvSetList.length; i++) {
				cvSetId = cvSetList[i] ;
				if (cvSetId != null && cvSetId != 0L)
				sql = "select count(*) from system_card_type_cv_set where CARDTYPE_ID = " + cardTypeId + " and CV_SET_ID = " + cvSetId;
				rsNum = getJdbcTemplate().queryForInt(sql) ;
				if (rsNum == 0) {
					sql = "insert into system_card_type_cv_set (CARDTYPE_ID,CV_SET_ID) values(" + cardTypeId + "," + cvSetId + ") " ;
					rsNum = getJdbcTemplate().update(sql) ;
				}
				if (rsNum > 0) resFlag = true ;
			}
		}
		return resFlag ;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-18
	 * @param    cardTypeId
	 * @param    cvSetList[]
	 * @return   boolean
	 * 方法说明： //YHQ，解绑定学习卡类型下的项目，2017-03-18
	 */
	@Override
	public boolean unBindCardTypeToCVSet(Long cardTypeId, Long cvSetList[]) {
		boolean resFlag = false ;
		if (cardTypeId != null && cardTypeId != 0L && cvSetList != null && cvSetList.length > 0) {
			String sql = "" ;
			Long cvSetId = 0L ;
			int rsNum = 0 ;
			for (int i = 0 ; i < cvSetList.length; i++) {
				cvSetId = cvSetList[i] ;
				if (cvSetId != null && cvSetId != 0L) {
					sql = "delete from system_card_type_cv_set where CARDTYPE_ID = " + cardTypeId + " and CV_SET_ID = " + cvSetId;
					rsNum = getJdbcTemplate().update(sql) ;
				}
				if (rsNum > 0) resFlag = true ;
			}
		}
		return resFlag ;
	}
	
	
	@Override
	public SystemCard findCardByCardID(Long cardID) {
		
		String sql ="select * from  system_card as card WHERE  card.ID = "+ cardID;
		List<SystemCard> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class)); 
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<SystemCardTypeCvSet> findListByCardType(Long cardtype) {
		String sql ="select * from system_card_type_cv_set as type_cv WHERE type_cv.CARDTYPE_ID ="+cardtype+"";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardTypeCvSet.class));
	}
	
	@Override
	public List<SystemCardOrderEntity> find(Long userid, Long proid,String cardNumber) {

		if(StringUtils.isNotEmpty(cardNumber)){
			String sql ="select * from system_card_set_order as card_order WHERE  card_order.USER_ID ="+userid+" and card_order.CV_SET_ID ="+proid+" and card_order.CARD_NUMBER ='"+cardNumber+"'";
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrderEntity.class));
		}else{
			String sql ="select * from system_card_set_order as card_order WHERE  card_order.USER_ID ="+userid+" and card_order.CV_SET_ID ="+proid+"";
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrderEntity.class));
		}
	}
	
	@Override
	public void AddCardOrder(SystemCardOrderEntity T) {
	String sql = "insert into system_card_set_order (CARD_TYPE_ID,USER_ID," +
			"USEFUL_DATE,PRICE,AMOUNT,PAY_DATE,PAYMODE_CODE,ORDER_NUMBER," +
			"STATUS,ORDER_TYPE,CV_SET_ID,CARD_NUMBER,ITEM_NAME)VALUES("+T.getCARD_TYPE_ID()+"," +
					""+T.getUSER_ID()+",'"+(new SimpleDateFormat("yyyy-MM-dd").format(T.getUSEFUL_DATE())+" 23:59:59")+"'," +
							""+T.getPRICE()+","+T.getAMOUNT()+",'"+T.getPAY_DATE()+"'," +
									"'"+T.getPAYMODE_CODE()+"',"+T.getORDER_NUMBER()+","+T.getSTATUS()+","+T.getORDER_TYPE()+","+T.getCV_SET_ID()+","+T.getCARD_NUMBER()+",'"+T.getITEM_NAME()+"')";
	

		getJdbcTemplate().update(sql);

	}
	
	@Override
	public void SaveBindUserinfor(SystemCardUser systemCardUser) {
		String sql ="insert into system_card_user(CARD_ID,USER_ID,SITE_ID,BIND_DATE) VALUES("+systemCardUser.getCardId()+","+systemCardUser.getUserId()+","+systemCardUser.getSiteId()+",now())"; //"+systemCardUser.getBindDate2()+"
		getJdbcTemplate().update(sql);
	}
	
	@Override
	public void UpdateCard(SystemCard systemCard) {
		String sql ="UPDATE system_card set `STATUS` = 2,isnot_bind=1 WHERE CARD_NUMBER = '"+systemCard.getCardNumber()+"'";
		getJdbcTemplate().update(sql);
	}
	
	@Override
	public List<CVSet> toCostById(Long id) {
		String sql = "select * from cv_set where id = "+id+"";
    	return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
	}
}


