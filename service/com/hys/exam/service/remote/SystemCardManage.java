package com.hys.exam.service.remote;

import java.util.Date;
import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.SystemCardOrderEntity;
import com.hys.exam.model.SystemCardTypeCvSet;
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
public interface SystemCardManage {

	//======卡管理
	
	//得到学习卡列表
	public void getSystemCardList(Page<SystemCardQuery> page, boolean isAll, Long cardTypeId, String cardNumber, String cardNumberEnd);
	
	//制卡记录列表
	public void getSystemCardCreateRecordList(Page<SystemCardCreateRecord> page);
	
	//通过制卡记录id得到学习卡列表
	public List<SystemCard> getSystemCardListByRecordId(Long recordId);
	
	//得到学习卡
	public SystemCardQuery getSystemCardById(Long id);
	
	//删除学习卡
	public int delSystemCard(Long [] cardIds);
	
	//保存学习卡
	public int saveSysgemCard(SystemCard systemCard);
	
	//批量导入学习卡
	public int importSystemCard(List<SystemCard> list);
	
	//卡对应的用户
	public SystemUser getSystemCardUserByCardId(Long cardId);
	
	//得到卡号流水号最大的一个
	public String getSystemCardSerialNumber(Integer sellStyle);
	
	//生成学习卡
	public int createSystemCard(int num, SystemCard c, SystemCardCreateRecord record, Integer sellStyle);
	
	//=========卡类型管理
	
	//卡类型列表
	public void getSystemCardTypeList(Page<SystemCardTypeQuery> page, String cardTypeName, String startDate, String endDate);
	
	//卡类型列表
	public List<SystemCardType> getSystemCardTypeList();
	
	//查看卡类型
	public SystemCardTypeQuery getSystemCardTypeById(Long id);
	
	//更新卡类型
	public int saveSystemCardType(SystemCardType type, String [] creditTypes);
	
	//删除卡类型
	public int deleteSystemCardType(Long [] typeIds);
	
	//得到授权/未授权 的课程列表
	public List<StudyCourseVO> getStudyCourse(Page<StudyCourseVO> page, Long typeId, boolean isAuthorized, String name);
	
	//得到授权/未授权 的机构列表
	public List<SystemAdminOrgan> getSystemOrgList(Page<SystemAdminOrgan> page, Long typeId, boolean isAuthorized, String name);
	
	//得到授权类型列表
	public List<SystemCreditType> getSystemCreditTypeList(Long typeId);
	
	//授权机构
	public int saveSystemPaycardOrgan(Long typeId, Long [] orgIds);
	
	//授权课程
	public int saveSystemPaycardCourse(Long cardTypeId, Long [] courseIds, Long courseType, String creditYear, String name);
	
	//批量授权课程
	public int batchCourseAuthorized(String courseTypeIds, Long cardTypeId);
	
	//删除卡类型 课程
	public int deleteCourseAuthorized(Long typeId, Long courseId);
	
	//删除卡类型 机构
	public int deleteOrgAuthorized(Long typeId, Long orgId);
	
	//根据名称查询卡类型
	public int getSystemCardTypeByName(String name);
	
	//=========卡类型分配管理
	
	public int saveSystemCardAllotCardType(Long typeId, Long [] cardIds);
	
	//批量分配
	public int saveSystemCardTypeAllotByNum(Long beforeTypeId, Long afterTypeId, Integer allotNum, String cardNumber, String cardNumberEnd);
	
	//得到卡类型下的空白卡
	public List<SystemCard> getSystemCardNoBindListByCardType(Long cardTypeId, Integer quantity);
	
	//=========卡订单
	//学习卡订单列表
	public void getSystemCardOrderList(Page<SystemCardOrderQuery> page, SystemCardOrderQuery q);
	
	//卡订单对象
	public SystemCardOrderQuery getSystemCardOrderById(Long id);
	
	//卡订单修改
	public int updateSystemCardOrder(SystemCardOrder order);
	
	//卡订单删除
	public int deleteSystemCardOrder(Long [] orderIds);
	
	//根据订单id得到该订单对应的学习卡
	public List<SystemCardQuery> getSystemCardListByOrderId(Long orderId, Page<SystemCardQuery> page);
	
	//给订单绑定学习卡
	public int updateSystemCardByCardTypeAndOrder(Long cardTypeId, Long orderId, Integer quantity);
	
	//=========卡状态管理
	//更改状态,重置余额
	public int updateSystemCard(Long [] cardIds, Integer status, Double balance);
	
	public int updateSystemCardSellStyle(Long [] cardIds, Integer status);
	
	public int updateSystemCardSelled(Long [] cardIds, Integer status);
	
	//绑定用户
	public int addSystemCardUserBind(Long cardId, Long [] userIds, Long siteId);
	
	//解绑用户
	public int deleteSystemCardUserBind(Long cardId, Long [] userIds);
	
	//得到用户(绑定/未绑定)
	public void getSystemUserList(Page<SystemUser> page, Long cardId, SystemUser user, boolean isBind);
	
	public void getIcmeSystemUserList(Page<SystemUser> page, String dbname, String domainName, Long cardId, SystemUser user, boolean isBind);
	
	//得到学习卡列表
	public void getSystemCardStatusList(Page<SystemCardQuery> page, boolean isAll, String cardNumber, String cardNumberStart,String cardNumberEnd);
	
	boolean bindCardTypeToCVSet(Long cardTypeId, Long cvSetList[]) ;//YHQ，绑定学习卡类型到项目，2017-03-18
	boolean unBindCardTypeToCVSet(Long cardTypeId, Long cvSetList[]) ;//YHQ，解绑定学习卡类型下的项目，2017-03-18
	public int saveSystemCardAllotCardTypeByNum(Long typeId, Long cardId, Date usefulDate, Long faceValue,String cardNumber,String cardNumberEnd);//YHQ，批量分配卡类型，2017-03-26
	
	/**
     * 
     * 通过卡号和密码查询学习卡---taoLiang
     * @param cardNumber
     * @param password
     * @return SystemCard
     * @exception 
     * @since  1.0.0
     */
    
    public  SystemCard findCardByCardID(Long cardID);
    
	/***
	 * 
	 * 根据卡类型查询相关联的项目----taoLiang
	 * @param proid
	 * @return List<SystemCardTypeCvSet>
	 * @exception 
	 * @since  1.0.0
	 */
    public List<SystemCardTypeCvSet> findListByCardType(Long cardtype);
    
    /**
	 * 
	 * 通过用户id和项目id查询订单是否存在---taoLiang
	 * @param uid
	 * @param proid
	 * @return List<SystemCardOrder>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<SystemCardOrderEntity> findByUidProid(Long uid,Long proid,String CardNumeber);
	
	
	/***
	 * 
	 * 添加订单信息---taoLiang
	 * @param systemCardOrder void
	 * @exception 
	 * @since  1.0.0
	 */
	
	public void AddCardOrder(SystemCardOrderEntity systemCardOrder);
	
	/***
     * 
     * 绑定卡号之后保存绑卡用户的信息--taoLiang
     * @param systemCardUser void
     * @exception 
     * @since  1.0.0
     */
    public void SaveBindUserinfor(SystemCardUser systemCardUser);
    
    
    /**
     * 
     * 更新学习卡状态--taoLiang
     * @param systemCard void
     * @exception 
     * @since  1.0.0
     */
    public void UpdateCard(SystemCard systemCard);

    /***
	 * 
	 *通过项目ID查询项目的价格--taoLiang
	 * @param id
	 * @return List<CVSet>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<CVSet> toCostById(Long id);

}


