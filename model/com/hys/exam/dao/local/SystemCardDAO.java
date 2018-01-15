package com.hys.exam.dao.local;

import java.util.Date;
import java.util.List;

import com.hys.exam.common.util.Page;
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

/**
*
* <p>Description: 学习卡</p>
* @author chenlaibin
* @version 1.0 2013-12-14
*/
public interface SystemCardDAO {
	
	//=======卡管理

	//得到学习卡列表
	public void getSystemCardList(Page<SystemCardQuery> page, boolean isAll, Long cardTypeId, String cardNumber, String cardNumberEnd);
	
	//制卡记录列表
	public void getSystemCardCreateRecordList(Page<SystemCardCreateRecord> page);
	
	//通过制卡记录id得到学习卡列表
	public List<SystemCard> getSystemCardListByRecordId(Long recordId);
	
	//得到学习卡
	public SystemCardQuery getSystemCardById(Long id);
	
	//删除学习卡
	public int delSystemCardById(Long id);
	
	//保存学习卡
	public int saveSystemCard(SystemCard systemCard);
	
	//批量保存学习卡
	public int saveSystemCardBatch(List<SystemCard> list);
	
	//保存制卡记录
	public int saveSystemCardCreateRecord(SystemCardCreateRecord record);
	
	//批量导入学习卡
	public int importSystemCard(List<SystemCard> list);
	
	//卡对应的用户
	public SystemUser getSystemCardUserByCardId(Long cardId);
	
	//得到卡号流水号最大的一个
	public SystemCard getSystemCardMaxSerialNumber();
	
	//得到卡类型下的空白卡
	public List<SystemCard> getSystemCardNoBindListByCardType(Long cardTypeId, Integer quantity);
	
	//绑定学习卡和订单
	public int updateSystemCardBindOrderId(Long cardId, Long orderId);
	
	//========卡类型管理
	
	//学习卡类型列表
	public void getSystemCardTypeList(Page<SystemCardTypeQuery> page, String cardTypeName, String startDate, String endDate);
	
	//根据卡类型得到学习卡总数
	public Integer getSystemCardNumByCardType(Long typeId, int type);
	
	//学习卡类型列表
	public List<SystemCardType> getSystemCardTypeList();
	
	//查看卡类型
	public SystemCardTypeQuery getSystemCardTypeById(Long id);
	
	//更新卡类型
	public int saveSystemCardType(SystemCardType type, String [] creditTypes);
	
	//删除卡类型(同时删除下属三个表)
	public int deleteSystemCardType(Long id);
	
	public int deleteSystemPaycardCredit(Long typeId);	//学习卡授权类型表
	
	public int deleteSystemPaycardCourse(Long typeId);	//学习卡类型课程限制表
	
	public int deleteSystemPaycardOrgan(Long typeId);	//学习卡类型机构限制表
	
	//得到授权/未授权 的课程列表
	public List<StudyCourseVO> getStudyCourse(Page<StudyCourseVO> page, Long typeId, boolean isAuthorized, String name);
	
	//得到授权/未授权 的机构列表
	public List<SystemAdminOrgan> getSystemOrgList(Page<SystemAdminOrgan> page, Long typeId, boolean isAuthorized, String name);
	
	//得到授权类型列表
	public List<SystemCreditType> getSystemCreditTypeList(Long typeId);
	
	//授权机构
	public int saveSystemPaycardOrgan(Long typeId, Long [] orgIds);
	
	//授权课程
	public int saveSystemPaycardCourse(Long cardTypeId, Long [] courseIds);
	
	//根据卡类型得到授权站点
	public List<SystemSite> getSystemSiteListByCardTypeId(Long typeId);
	
	//授权课程分类下所有课程
	public int saveSystemPaycardCourseByCourseType(Long cardTypeId, Long courseType, String creditYear, String name, String siteIds);
	
	//删除卡类型 课程
	public int deleteCourseAuthorized(Long typeId, Long courseId);
		
	//删除卡类型 机构
	public int deleteOrgAuthorized(Long typeId, Long orgId);
	
	//根据名称查询卡类型
	public int getSystemCardTypeByName(String name);
	
	//=========卡类型分配管理
	
	public int saveSystemCardAllotCardType(Long typeId, Long cardId, Date usefulDate, Long faceValue);
	
	public List<SystemCard> getSystemCardListByAllotNum(Long typeId, Integer allotNum, String cardNumber, String cardNumberEnd);
	
	//==========卡订单
	
	//学习卡订单列表
	public void getSystemCardOrderList(Page<SystemCardOrderQuery> page, SystemCardOrderQuery q);

	//卡订单对象
	public SystemCardOrderQuery getSystemCardOrderById(Long id);
		
	//卡订单修改
	public int updateSystemCardOrder(SystemCardOrder order);

	//卡订单删除
	public int deleteSystemCardOrder(Long id);
	
	//根据订单id得到该订单对应的学习卡
	public List<SystemCardQuery> getSystemCardListByOrderId(Long orderId, Page<SystemCardQuery> page);
	
	//=========卡状态管理
	
	//更改状态,重置余额
	public int updateSystemCard(Long cardId, Integer status, Double balance);
	
	public int updateSystemCardSellStyle(Long id, Integer status);
	
	public int updateSystemCardSelled(Long id, Integer status);
	
	//绑定用户
	public int addSystemCardUserBind(Long cardId, Long [] userIds, Long siteId);
	
	//解绑用户
	public int deleteSystemCardUserBind(Long cardId, Long [] userIds);
	
	//得到用户(绑定/未绑定)
	public void getSystemUserList(Page<SystemUser> page, Long cardId, SystemUser user, boolean isBind);
	
	public void getIcmeSystemUserList(Page<SystemUser> page, String dbname, String domainName, Long cardId, SystemUser user, boolean isBind);
	
	//得到学习卡列表
	public void getSystemCardStatusList(Page<SystemCardQuery> page, boolean isAll, String cardNumber, String cardNumberStart,String cardNumberEnd);

	public SystemCard getSystemCardMaxSerialNumberBysellStyle(Integer sellStyle);
	
	boolean bindCardTypeToCVSet(Long cardTypeId, Long cvSetList[]) ;//YHQ，绑定学习卡类型到项目，2017-03-18
	boolean unBindCardTypeToCVSet(Long cardTypeId, Long cvSetList[]) ;//YHQ，解绑定学习卡类型下的项目，2017-03-18
	public int saveSystemCardAllotCardTypeByNum(Long typeId, Long cardId, Date usefulDate, Long faceValue,String cardNumber,String cardNumberEnd);//YHQ，批量分配卡类型，2017-03-26

	public  SystemCard findCardByCardID(Long cardID);//---taoLiang
	List<SystemCardTypeCvSet> findListByCardType(Long cardtype);//---taoLiang
	public List<SystemCardOrderEntity> find(Long userid,Long proid,String cardNumber);//---taoLiang
	public void AddCardOrder(SystemCardOrderEntity systemCardOrder);//---taoLiang
	public void SaveBindUserinfor(SystemCardUser systemCardUser);//---taoLiang
	public void UpdateCard(SystemCard systemCard);//----taoLiang
	public List<CVSet> toCostById(Long id );//--taoLiang
	
}


