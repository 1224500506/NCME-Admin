package com.hys.exam.service.remote.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.local.SystemCardDAO;
import com.hys.exam.model.CVSet;
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
import com.hys.exam.model.systemQuery.SystemCardOrderQuery;
import com.hys.exam.model.systemQuery.SystemCardQuery;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.utils.SystemCardUtil;
import com.hys.exam.constants.Constants ;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-14
*/
public class SystemCardManageImpl implements SystemCardManage {
	
	private SystemCardDAO systemCardDAO;
	
	/**
	 * @author   杨红强
	 * @time     2017-03-26	
	 * @return   boolean
	 * 方法说明： YHQ，批量分配卡类型，2017-03-26
	 */
	@Override
	public int saveSystemCardAllotCardTypeByNum(Long typeId, Long cardId, Date usefulDate, Long faceValue,String cardNumber,String cardNumberEnd){
		return this.systemCardDAO.saveSystemCardAllotCardTypeByNum(typeId, cardId, usefulDate, faceValue,cardNumber,cardNumberEnd) ;
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
		return this.systemCardDAO.bindCardTypeToCVSet(cardTypeId, cvSetList) ;
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
		return this.systemCardDAO.unBindCardTypeToCVSet(cardTypeId, cvSetList) ;
	}
	
	//得到学习卡列表
	@Override
	public void getSystemCardList(Page<SystemCardQuery> page,  boolean isAll, Long cardTypeId, String cardNumber, String cardNumberEnd){
		this.systemCardDAO.getSystemCardList(page, isAll, cardTypeId, cardNumber, cardNumberEnd);
	}
	
	//制卡记录列表
	@Override
	public void getSystemCardCreateRecordList(Page<SystemCardCreateRecord> page){
		this.systemCardDAO.getSystemCardCreateRecordList(page);
	}
	
	//通过制卡记录id得到学习卡列表
	@Override
	public List<SystemCard> getSystemCardListByRecordId(Long recordId){
		return this.systemCardDAO.getSystemCardListByRecordId(recordId);
	}
	
	//得到学习卡
	@Override
	public SystemCardQuery getSystemCardById(Long id){
		return this.systemCardDAO.getSystemCardById(id);
	}
	
	//删除学习卡
	@Override
	public int delSystemCard(Long [] cardIds){
		int rows = 0;
		if(null != cardIds && cardIds.length>0){
			for(Long id : cardIds){
				rows = this.systemCardDAO.delSystemCardById(id);
			}
		}
		return rows;
	}
	
	//保存学习卡
	@Override
	public int saveSysgemCard(SystemCard systemCard){
		
		//把卡类型对应的截止时间赋值给学习卡的有效时间
		/*SystemCardTypeQuery type = this.systemCardDAO.getSystemCardTypeById(systemCard.getCardType());
		if(null != type){
			if(null != type.getEndDate()){
				systemCard.setUsefulDate(type.getEndDate());
			}
			Long creditSum = type.getCreditSum();
			if(null != creditSum && creditSum>0){
				systemCard.setFaceValue(creditSum.intValue());
			}
		}*/
		
		return this.systemCardDAO.saveSystemCard(systemCard);
	}
	
	//批量导入学习卡
	@Override
	public int importSystemCard(List<SystemCard> list){
		return this.systemCardDAO.importSystemCard(list);
	}
	
	//卡对应的用户
	@Override
	public SystemUser getSystemCardUserByCardId(Long cardId){
		return this.systemCardDAO.getSystemCardUserByCardId(cardId);
	}
	
	//得到卡号流水号最大的一个
	@Override
	public String getSystemCardSerialNumber(Integer sellStyle){
		String cardNumber = "";
		String sm = "";
		SystemCard card = this.systemCardDAO.getSystemCardMaxSerialNumber();
		if(null != card){
			cardNumber = card.getCardNumber();
			cardNumber = cardNumber.substring(4,10);	//6位流水号
			sm = SystemCardUtil.getSystemCardNumber(cardNumber, sellStyle);
		}
		return sm;
	}
	
	//生成学习卡
	@Override
	public int createSystemCard(int num, SystemCard c, SystemCardCreateRecord record, Integer sellStyle){
		int row = 0;
		String cardNumber = "";
		//SystemCard card = this.systemCardDAO.getSystemCardMaxSerialNumber();	//当前系统中流水号最大的卡号
		SystemCard card = this.systemCardDAO.getSystemCardMaxSerialNumberBysellStyle(sellStyle);
        if (card == null) {
            card = this.systemCardDAO.getSystemCardMaxSerialNumber();	//当前系统中流水号最大的卡号，YHQ，2017-03-23
        }
		if(null != card){
			cardNumber = card.getCardNumber();
			//cardNumber = cardNumber.substring(4,10);	//6位流水号
            cardNumber = cardNumber.substring(4,Constants.SYSTEM_CARD_LENGTH); //流水号，YHQ，2017-03-23
		}else{		    
                    cardNumber="000000";
		}
		
                /*
		//把卡类型对应的截止时间,总学时 赋值给学习卡
		SystemCardTypeQuery type = this.systemCardDAO.getSystemCardTypeById(c.getCardType());
		if(null != type){
			if(null != type.getEndDate()){
				c.setUsefulDate(type.getEndDate());
			}
			Long creditSum = type.getCreditSum();
			if(null != creditSum && creditSum>0){
				c.setFaceValue(creditSum.intValue());
			}
		}*/
		
		List<SystemCard> list = new ArrayList<SystemCard>();
		if(num >0 && c.getCardType() >0){
			for(int i=1; i<=num; i++){
				SystemCard systemCard = new SystemCard();
				BeanUtils.copyProperties(c, systemCard);
				long s = Long.parseLong(cardNumber);
				String cn = SystemCardUtil.df.format(s);
				cn = SystemCardUtil.getSystemCardNumber(cardNumber, sellStyle);
				if(i == 1){
					record.setCardStartInt(cn);
				}
				else if(i==num){
					record.setCardEndInt(cn);
				}
				//cardNumber = cn.substring(4,10);	//把本次的流水号赋给第一个
				cardNumber = cn.substring(4,Constants.SYSTEM_CARD_LENGTH);	//把本次的流水号赋给第一个，YHQ，2017-03-23
				systemCard.setCardNumber(cn);
				systemCard.setCardPassword(SystemCardUtil.get6Random());
				list.add(systemCard);
			}
			
			row = this.systemCardDAO.saveSystemCardBatch(list);
		}
		
		//同时保存制卡记录
		row += this.systemCardDAO.saveSystemCardCreateRecord(record);
		return row;
	}
	
	//学习卡类型列表
	@Override
	public void getSystemCardTypeList(Page<SystemCardTypeQuery> page, String cardTypeName, String startDate, String endDate){
		this.systemCardDAO.getSystemCardTypeList(page, cardTypeName, startDate, endDate);
		if(null != page.getList()){
			for(SystemCardTypeQuery query : page.getList()){
				query.setAllNum(this.systemCardDAO.getSystemCardNumByCardType(query.getId(), 1));
				query.setUserdNum(this.systemCardDAO.getSystemCardNumByCardType(query.getId(), 2));
				query.setRemainNum(query.getAllNum() - query.getUserdNum()) ; //YHQ，2017-03-26
				//query.setRemainNum(this.systemCardDAO.getSystemCardNumByCardType(query.getId(), 3));
			}
		}
	}
	
	//学习卡类型列表
	@Override
	public List<SystemCardType> getSystemCardTypeList(){
		return this.systemCardDAO.getSystemCardTypeList();
	}
	
	//查看卡类型
	@Override
	public SystemCardTypeQuery getSystemCardTypeById(Long id){
		SystemCardTypeQuery query = this.systemCardDAO.getSystemCardTypeById(id);
		if(null != query){
			query.setAllNum(this.systemCardDAO.getSystemCardNumByCardType(query.getId(), 1));
			query.setUserdNum(this.systemCardDAO.getSystemCardNumByCardType(query.getId(), 2));
			query.setRemainNum(this.systemCardDAO.getSystemCardNumByCardType(query.getId(), 3));
		}
		return query;
	}
	
	//更新卡类型
	@Override
	public int saveSystemCardType(SystemCardType type, String [] creditTypes){
		return this.systemCardDAO.saveSystemCardType(type, creditTypes);
	}
	
	//删除卡类型
	@Override
	public int deleteSystemCardType(Long [] typeIds){
		int row = 0;
		if(null != typeIds && typeIds.length>0){
			for(Long id : typeIds){
				//先删除子表
				this.systemCardDAO.deleteSystemPaycardCredit(id);
				this.systemCardDAO.deleteSystemPaycardOrgan(id);
				this.systemCardDAO.deleteSystemPaycardCourse(id);
				row = this.systemCardDAO.deleteSystemCardType(id);
			}
		}
		return row;
	}
	
	//得到授权/未授权 的课程列表
	@Override
	public List<StudyCourseVO> getStudyCourse(Page<StudyCourseVO> page, Long typeId, boolean isAuthorized, String courseName){
		return this.systemCardDAO.getStudyCourse(page, typeId, isAuthorized, courseName);
	}
	
	//得到授权/未授权 的机构列表
	@Override
	public List<SystemAdminOrgan> getSystemOrgList(Page<SystemAdminOrgan> page, Long typeId, boolean isAuthorized, String orgName){
		return this.systemCardDAO.getSystemOrgList(page, typeId, isAuthorized, orgName);
	}
	
	//得到授权类型列表
	@Override
	public List<SystemCreditType> getSystemCreditTypeList(Long typeId){
		return this.systemCardDAO.getSystemCreditTypeList(typeId);
	}
	
	//授权机构
	@Override
	public int saveSystemPaycardOrgan(Long typeId, Long [] orgIds){
		return this.systemCardDAO.saveSystemPaycardOrgan(typeId, orgIds);
	}
	
	//授权课程
	@Override
	public int saveSystemPaycardCourse(Long cardTypeId, Long [] courseIds, Long courseType, String creditYear, String name){
		/*if(Utils.isArrayEmpty(courseIds))
			return this.systemCardDAO.saveSystemPaycardCourseByCourseType(cardTypeId, courseType, creditYear, name, "");
		else*/
		if(!Utils.isArrayEmpty(courseIds)){
			return this.systemCardDAO.saveSystemPaycardCourse(cardTypeId, courseIds);
		}
		return 0;
	}
	
	//批量授权课程
	@Override
	public int batchCourseAuthorized(String courseTypeIds, Long cardTypeId){
		int row = 0;
		
		//根据卡类型得到授权站点
		List<SystemSite> list = this.systemCardDAO.getSystemSiteListByCardTypeId(cardTypeId);
		StringBuffer sb = new StringBuffer();
		String siteIds = "";
		if(!Utils.isListEmpty(list)){
			for(SystemSite site : list){
				sb.append(site.getId()+",");
			}
			siteIds = sb.toString();
		}
		
		String [] ids = courseTypeIds.split(",");
		for(int i=0; i<ids.length; i++){
			Long courseType = new Long(ids[i]);
			row += this.systemCardDAO.saveSystemPaycardCourseByCourseType(cardTypeId, courseType, "", "", siteIds);
		}
		return row;
	}
	
	//删除卡类型 课程
	@Override
	public int deleteCourseAuthorized(Long typeId, Long courseId){
		return this.systemCardDAO.deleteCourseAuthorized(typeId, courseId);
	}
		
	//删除卡类型 机构
	@Override
	public int deleteOrgAuthorized(Long typeId, Long orgId){
		return this.systemCardDAO.deleteOrgAuthorized(typeId, orgId);
	}
	
	//根据名称查询卡类型
	@Override
	public int getSystemCardTypeByName(String name) {
		return this.systemCardDAO.getSystemCardTypeByName(name);
	};
	
	//===========卡类型分配管理
	@Override
	public int saveSystemCardAllotCardType(Long typeId, Long [] cardIds){
		//把卡类型对应的截止时间赋值给学习卡的有效时间
		SystemCardTypeQuery type = this.systemCardDAO.getSystemCardTypeById(typeId);
		Date usefulDate = null;
		Long faceValue = 0L;
		if(null != type){
			if(null != type.getEndDate()){
				//添加卡有效时间判断【如果改卡类型有效时间小于当前日期则无法给其分配卡】---taoliang
				Date fullDate = null;
				try {
					String fullDateStr = new SimpleDateFormat("yyyy-MM-dd").format(type.getEndDate())+" 23:59:59";
					fullDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fullDateStr);
				} catch (ParseException e) {
					fullDate = type.getEndDate();
				}
				if(fullDate.getTime() < new Date().getTime()){
					return -1;
				}
				usefulDate = type.getEndDate();
			}
			Long creditSum = type.getCreditSum();
			if(null != creditSum && creditSum>0){
				faceValue = creditSum;
			}
		}
		
		int row = 0;
		if(null != cardIds && cardIds.length>0){
			for(Long cardId : cardIds){
				row += this.systemCardDAO.saveSystemCardAllotCardType(typeId, cardId, usefulDate, faceValue);
			}
		}
		return row;
	}
	
	//批量分配
	@Override
	public int saveSystemCardTypeAllotByNum(Long beforeTypeId, Long afterTypeId, Integer allotNum, String cardNumber, String cardNumberEnd){
		int row = 0;
		/*List<SystemCard> list = this.systemCardDAO.getSystemCardListByAllotNum(beforeTypeId, allotNum, cardNumber, cardNumberEnd);
		if(!Utils.isListEmpty(list)){
			SystemCardType type = this.systemCardDAO.getSystemCardTypeById(afterTypeId);
			for(SystemCard card : list){
				Long cardId = card.getId();
				row += this.systemCardDAO.saveSystemCardAllotCardType(afterTypeId, cardId, type.getEndDate(), type.getCreditSum());
			}
		}*/
		
		//YHQ，2017-03-26
		SystemCardType newCardType = this.systemCardDAO.getSystemCardTypeById(afterTypeId);
		if (newCardType != null) {
			row += this.systemCardDAO.saveSystemCardAllotCardTypeByNum(afterTypeId, Long.valueOf(allotNum), newCardType.getEndDate(), newCardType.getCreditSum(),cardNumber,cardNumberEnd);
		}
		
		return row;
	}
	
	//得到卡类型下的空白卡
	@Override
	public List<SystemCard> getSystemCardNoBindListByCardType(Long cardTypeId, Integer quantity){
		return this.systemCardDAO.getSystemCardNoBindListByCardType(cardTypeId, quantity);
	}
	
	//==========卡订单
	
	//学习卡订单列表
	@Override
	public void getSystemCardOrderList(Page<SystemCardOrderQuery> page, SystemCardOrderQuery q){
		this.systemCardDAO.getSystemCardOrderList(page, q);
	}
	
	//卡订单对象
	@Override
	public SystemCardOrderQuery getSystemCardOrderById(Long id){
		return this.systemCardDAO.getSystemCardOrderById(id);
	}
	
	//卡订单修改
	@Override
	public int updateSystemCardOrder(SystemCardOrder order){
		return this.systemCardDAO.updateSystemCardOrder(order);
	}

	//卡订单删除
	public int deleteSystemCardOrder(Long [] orderIds){
		int row = 0;
		if(null != orderIds && orderIds.length >0){
			for(Long id : orderIds){
				row = this.systemCardDAO.deleteSystemCardOrder(id);
			}
		}
		return row;
	}
	
	//根据订单id得到该订单对应的学习卡
	@Override
	public List<SystemCardQuery> getSystemCardListByOrderId(Long orderId, Page<SystemCardQuery> page){
		return this.systemCardDAO.getSystemCardListByOrderId(orderId, page);
	}
	
	//给订单绑定学习卡
	@Override
	public int updateSystemCardByCardTypeAndOrder(Long cardTypeId, Long orderId, Integer quantity){
		int row = 0;
		if(quantity >0){
			List<SystemCard> list = this.systemCardDAO.getSystemCardNoBindListByCardType(cardTypeId, quantity);
			if(!Utils.isListEmpty(list)){
				for(SystemCard card : list){
					row += this.systemCardDAO.updateSystemCardBindOrderId(card.getId(), orderId);
				}
			}
		}
		return row;
	}
	
	//=========卡状态管理
	
	//更改状态,重置余额
	@Override
	public int updateSystemCard(Long [] cardIds, Integer status, Double balance){
		int row = 0;
		if(null != cardIds && cardIds.length >0){
			for(Long id : cardIds){
				row = this.systemCardDAO.updateSystemCard(id, status, balance);
			}
		}
		return row;
	}
	
	@Override
	public int updateSystemCardSellStyle(Long [] cardIds, Integer status){
		int row = 0;
		if(null != cardIds && cardIds.length >0){
			for(Long id : cardIds){
				row = this.systemCardDAO.updateSystemCardSellStyle(id, status);
			}
		}
		return row;
	}
	
	@Override
	public int updateSystemCardSelled(Long [] cardIds, Integer status){
		int row = 0;
		if(null != cardIds && cardIds.length >0){
			for(Long id : cardIds){
				row = this.systemCardDAO.updateSystemCardSelled(id, status);
			}
		}
		return row;
	}
	
	//绑定用户
	@Override
	public int addSystemCardUserBind(Long cardId, Long [] userIds, Long siteId){
		return this.systemCardDAO.addSystemCardUserBind(cardId, userIds, siteId);
	}
	
	//解绑用户
	@Override
	public int deleteSystemCardUserBind(Long cardId, Long [] userIds){
		return this.systemCardDAO.deleteSystemCardUserBind(cardId, userIds);
	}
	
	//得到用户(绑定/未绑定)
	@Override
	public void getSystemUserList(Page<SystemUser> page, Long cardId, SystemUser user, boolean isBind){
		this.systemCardDAO.getSystemUserList(page, cardId, user, isBind);
	}
	
	@Override
	public void getIcmeSystemUserList(Page<SystemUser> page, String dbname, String domainName, Long cardId, SystemUser user, boolean isBind){
		this.systemCardDAO.getIcmeSystemUserList(page, dbname, domainName, cardId, user, isBind);
	}
	
	//得到学习卡列表
	public void getSystemCardStatusList(Page<SystemCardQuery> page, boolean isAll, String cardNumber, String cardNumberStart,String cardNumberEnd){
		this.systemCardDAO.getSystemCardStatusList(page, isAll, cardNumber, cardNumberStart, cardNumberEnd);
	}

	public SystemCardDAO getSystemCardDAO() {
		return systemCardDAO;
	}

	public void setSystemCardDAO(SystemCardDAO systemCardDAO) {
		this.systemCardDAO = systemCardDAO;
	}
	
	@Override
	public SystemCard findCardByCardID(Long cardID) {
		return	systemCardDAO.findCardByCardID(cardID);
	}
	
	@Override
	public List<SystemCardTypeCvSet> findListByCardType(Long proid) {
		return systemCardDAO.findListByCardType(proid);
	}
	
	@Override
	public List<SystemCardOrderEntity> findByUidProid(Long uid, Long proid,String cardNumber) {
		return systemCardDAO.find(uid, proid,cardNumber);
		
	}
	
	@Override
	public void AddCardOrder(SystemCardOrderEntity systemCardOrder) {
		systemCardDAO.AddCardOrder(systemCardOrder);
	}
	
	@Override
	public void SaveBindUserinfor(SystemCardUser systemCardUser) {
		systemCardDAO.SaveBindUserinfor(systemCardUser);
	}
	
	@Override
	public void UpdateCard(SystemCard systemCard) {
		systemCardDAO.UpdateCard(systemCard);
	}
	
	@Override
	public List<CVSet> toCostById(Long id) {
		return systemCardDAO.toCostById(id);
	}
}


