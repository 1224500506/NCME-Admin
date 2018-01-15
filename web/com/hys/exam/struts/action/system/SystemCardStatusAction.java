package com.hys.exam.struts.action.system;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.SystemCardOrderEntity;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemAdminOrgan;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.model.system.SystemCardUser;
import com.hys.exam.model.systemQuery.SystemCardQuery;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.DBConfiger;

/**
*
* <p>Description: 卡状态管理</p>
* 状态,余额,绑定用户,解绑用户
* @author chenlaibin
* @version 1.0 2013-12-19
*/
public class SystemCardStatusAction extends AppBaseAction{

	private SystemCardManage systemCardManage;
	
	private SystemSiteManage systemSiteManage;
	
	private ExamPropValFacade localExamPropValFacade;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("updateStatus")){
			return this.updateStatus(mapping, form, request, response);
		}else if(method.equals("bindUser")){
			return this.bindUser(mapping, form, request, response);
		}else if(method.equals("unBindUser")){
			return this.unBindUser(mapping, form, request, response);
		}else if(method.equals("getBindUser")){
			return this.getBindUser(mapping, form, request, response);
		}else if(method.equals("viewDetail")){
			return this.viewDetail(mapping, form, request, response);
		}else if(method.equals("updateSelled")){
			return this.updateSelled(mapping, form, request, response);
		}else if(method.equals("updateSellStyle")){
			return this.updateSellStyle(mapping, form, request, response);
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		boolean isAll = true;
		String cardNumber = ParamUtils.getParameter(request, "cardNumber", "");
		String cardNumberStart = ParamUtils.getParameter(request, "cardNumberStart", "");
		String cardNumberEnd = ParamUtils.getParameter(request, "cardNumberEnd", "");
		Page<SystemCardQuery> page = this.createPage_temp_2017(request, "systemCard");
		this.systemCardManage.getSystemCardStatusList(page, isAll, cardNumber, cardNumberStart, cardNumberEnd);
		request.setAttribute("page", page);
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cardNumberStart", cardNumberStart);
		request.setAttribute("cardNumberEnd", cardNumberEnd);
		return "list";
	}
	
	//更改卡状态/重置余额
	protected String updateStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		int count = 0;
		Integer status = ParamUtils.getIntParameter(request, "status", -100);
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0, _cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = this.switchStringtoLongArray(ids);
		Double balance = ParamUtils.getDoubleParameter(request, "balance", Constants.SYSTEM_CARD_DEFAULT_BALANCE);
		
		Map<String, String> map = new HashMap<String, String>();
		try{
			//过滤已使用的的学习卡，只修改为使用的
			if(status == -2){
				List<Long> list = new ArrayList<Long>();  
				String jinStr = "";
				if(null != cardIds && cardIds.length >0){
					for(int i=0;i<cardIds.length;i++){
						SystemCard newcard = systemCardManage.findCardByCardID(cardIds[i]);
						if(newcard.getIsnotBind() == 1 || newcard.getStatus() == 2){
							jinStr += cardIds[i]+",";
							continue;
						}
						list.add(cardIds[i]);
					}
				}
				cardIds = list.toArray(new Long[1]);
				count = this.systemCardManage.updateSystemCard(cardIds, status, balance);
				System.out.println("jin:"+ jinStr);
				map.put("disCard", jinStr);
			}else{
				count = this.systemCardManage.updateSystemCard(cardIds, status, balance);
			}
		}catch(Exception ex){
			map.put("msg", "fail");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//Utils.renderText(response, String.valueOf(count));
		map.put("msg", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	//更改销售方式
	protected String updateSellStyle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		int count = 0;
		Integer status = ParamUtils.getIntParameter(request, "status", -100);
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0, _cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = this.switchStringtoLongArray(ids);
		count = this.systemCardManage.updateSystemCardSellStyle(cardIds, status);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//更改售出状态
	protected String updateSelled(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		int count = 0;
		Integer status = ParamUtils.getIntParameter(request, "status", -100);
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0, _cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = this.switchStringtoLongArray(ids);
		count = this.systemCardManage.updateSystemCardSelled(cardIds, status);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//绑定用户
	protected String bindUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		Map<String, String> map = new HashMap<String, String>();
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		String _userIds = ParamUtils.getParameter(request, "userIds");
		Long siteId = ParamUtils.getLongParameter(request, "siteId", Constants.AJPX_SYSTEM_SITE_ID);
		_userIds = _userIds.substring(0,_userIds.length()-1);
		String [] ids = _userIds.split(",");
		Long [] userIds = switchStringtoLongArray(ids);
		Long bindUserId = 0L;
		if(userIds != null && userIds.length > 0){
			bindUserId = userIds[0];
		}
		//############重新写绑卡逻辑--taoLiang#########
		if(bindUserId <1){//进行用户是否存在拦截
			map.put("message", "userno");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		
		try {
			if (!"".equals(cardId.toString()) && cardId != null) {
				SystemCard newcard = systemCardManage.findCardByCardID(cardId);

				if (newcard != null) {
					//判断学习卡类型是否为空卡，如果为空卡，返回 该卡未绑定项目，无法添加成功
					if(Constants.SystemCard_TYPE_NULL==newcard.getCardType()){
						map.put("message", "typeno");
						StrutsUtil.renderText(response, JsonUtil.map2json(map));
						return null;
					}
					//添加学习卡被禁用拦截
					if(newcard.getStatus() != null && -2 == newcard.getStatus()){
						map.put("message", "typedisable");
						StrutsUtil.renderText(response, JsonUtil.map2json(map));
						return null;
					}
					if (1 != newcard.getIsnotBind() && 1==newcard.getStatus() && newcard.getUsefulDate() != null) {				
						SystemCard card = newcard;
						// 判断学习卡是否过期
						String startDateStr = DateUtils.DateToString(card.getUsefulDate());
						String endDateStr = DateUtils.DateToString(new Date());
						int time = DateUtils.compareDate(startDateStr,endDateStr, 0);
						if (time > 0) {
							// 项目已经过期
							map.put("message", "time");
						} else {
							// 卡号存在且没有过期

							// 获取卡号类型匹配卡类型
							List<SystemCardTypeCvSet> cvsetlit = systemCardManage.findListByCardType(Long.parseLong(card.getCardType().toString()));
								// 卡类型有关联的项目
								for (SystemCardTypeCvSet systemCardTypeCvSet : cvsetlit) {
									// 查询表中是否存在
									List<SystemCardOrderEntity> orderlist = systemCardManage.findByUidProid(bindUserId,Long.parseLong(systemCardTypeCvSet.getCV_SET_ID().toString()),card.getCardNumber());
									if (orderlist != null && orderlist.size() > 0)											
										continue; // 如果存在跳出本次循环
									
									SystemCardOrderEntity cardOrder = new SystemCardOrderEntity();
									cardOrder.setCARD_TYPE_ID(Integer.parseInt(card.getCardType().toString()));// 卡类型
									cardOrder.setUSER_ID(bindUserId);// 用户id
									cardOrder.setUSEFUL_DATE(card.getUsefulDate());// 订单日期
									
									Integer cardMoney = card.getFaceValue() ;
									if (cardMoney == null) cardMoney = card.getCost()==null?0:Integer.valueOf(card.getCost().toString()) ;
									if (cardMoney == null) cardMoney = 0 ;
									
									cardOrder.setPRICE(cardMoney + 0.0);// 价格
									
									
									cardOrder.setAMOUNT(1);// 数量
									cardOrder.setPAY_DATE((new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));// 支付日期
									cardOrder.setPAYMODE_CODE("绑卡");// 订单类型
																	// 支付宝/微信/网银
									cardOrder.setORDER_NUMBER(System.currentTimeMillis() + "");
									cardOrder.setSTATUS(1);
									cardOrder.setORDER_TYPE(1);
									cardOrder.setCARD_NUMBER(card.getCardNumber());
									cardOrder.setCV_SET_ID(Long.parseLong(systemCardTypeCvSet.getCV_SET_ID().toString()));

									if (systemCardManage.toCostById(cardOrder.getCV_SET_ID()).size() > 0) {
										cardOrder.setITEM_NAME(systemCardManage.toCostById(cardOrder.getCV_SET_ID()).get(0).getName());
									} else {
										cardOrder.setITEM_NAME("");
									}
									systemCardManage.AddCardOrder(cardOrder);
									//用户卡绑定表中 --- 移至for循环外 2017.9.27  避免重复向system_card_user表插入相同的数据
								}
								// 用户卡绑定表中 
								SystemCardUser systemuser = new SystemCardUser();
								systemuser.setBindDate2(DateUtil.getNowDate());
								systemuser.setUserId(bindUserId);
								systemuser.setCardId(card.getId());
								systemuser.setSiteId(siteId);
								systemCardManage.SaveBindUserinfor(systemuser);
								// 更改绑卡状态
								card.setStatus(2);
								systemCardManage.UpdateCard(card);

								// 添加成功
								map.put("message", "success");
						}
					} else {
						// 卡状态不可用
						map.put("message", "noenable");
					}
				} else {
					// 卡不存在
					map.put("message", "notfind");
				}

			}
		} catch (Exception e) {
			map.put("message", "Exception");
			e.printStackTrace();
		}
		//int count = this.systemCardManage.addSystemCardUserBind(cardId, userIds, siteId);
		//Utils.renderText(response, String.valueOf(count));
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	//解绑用户
	protected String unBindUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		String _userIds = ParamUtils.getParameter(request, "userIds");
		_userIds = _userIds.substring(0,_userIds.length()-1);
		String [] ids = _userIds.split(",");
		Long [] userIds = switchStringtoLongArray(ids);
		int count = this.systemCardManage.deleteSystemCardUserBind(cardId, userIds);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//得到绑定用户/未绑定用户
	@SuppressWarnings("static-access")
	protected String getBindUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		String userName = ParamUtils.getParameter(request, "name");
		String certificateNo = ParamUtils.getParameter(request, "certificateNo");
		Long isBind = ParamUtils.getLongParameter(request, "isBind", 0);
		String domainName = ParamUtils.getParameter(request, "domainName");
		Page<SystemUser> page = this.createPage(request, "p");
		SystemUser user = new SystemUser();
		user.setRealName(userName);
		user.setCertificateNo(certificateNo);
		//YHQ，2017-04-04
		this.systemCardManage.getSystemUserList(page, cardId, user, isBind==1?true:(isBind==-1));
		/*
		if("".equals(domainName) || domainName.equals(Constants.ANQUAN100_SITE)){
			this.systemCardManage.getSystemUserList(page, cardId, user, isBind==1?true:(isBind==-1));
		}else{	//获取其他平台
			String domainnames = DBConfiger.getInstance().getProperty("domainnames");
			String databases = DBConfiger.getInstance().getProperty("databases");
			String databaseName = "";
			if(StringUtils.isNotBlank(domainnames) && StringUtils.isNotBlank(databases) ){
				String [] domainnames_arr = domainnames.split(";");
				String [] databases_arr = databases.split(";");
				if(domainnames_arr.length == databases_arr.length){
					List<String> list = Arrays.asList(domainnames_arr);
					int index = list.indexOf(domainName);
					if(index >-1)
						databaseName = databases_arr[index];
					else 
						databaseName = "icme";
				}
				this.systemCardManage.getIcmeSystemUserList(page, databaseName, domainName, cardId, user, isBind==1?true:(isBind==-1));
			}
		}*/
		request.setAttribute("name", userName);
		request.setAttribute("certificateNo", certificateNo);
		request.setAttribute("page", page);
		request.setAttribute("cardId", cardId);
		request.setAttribute("isBind", isBind);
		
		//得到站点列表
		SystemSite ss = new SystemSite();
		List<SystemSite> siteList = this.systemSiteManage.getListByItem(ss);
		request.setAttribute("siteList", siteList);
		
		Long siteId = Constants.AJPX_SYSTEM_SITE_ID;
		for(SystemSite site : siteList){
			if(site.getDomainName().equals(domainName)){
				siteId = site.getId();
			}
		}
		try {
			request.setAttribute("cardTypeName", URLDecoder.decode(request.getParameter("cardTypeName")==
					null?"":request.getParameter("cardTypeName").toString(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {}
		request.setAttribute("cardTypeId", request.getParameter("cardTypeId"));
		request.setAttribute("siteId", siteId);
		
		return "userBindList";
	}
	
	//查看学习卡详细
	protected String viewDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		//卡信息
		SystemCardQuery card = this.systemCardManage.getSystemCardById(cardId);
		request.setAttribute("card", card);
		if(card != null){
			Long cardTypeId = card.getCardType();
			//卡类型信息
			SystemCardTypeQuery cardType = this.systemCardManage.getSystemCardTypeById(cardTypeId);
			request.setAttribute("cardType", cardType);
			
			//卡类型授权课程列表
			@SuppressWarnings("static-access")
			Page<StudyCourseVO> course_page = this.createPage(request, "iaStudyCourse");
			List<StudyCourseVO> course_list = this.systemCardManage.getStudyCourse(course_page, cardTypeId, true, "");
			request.setAttribute("course_list", course_list);
			
			//卡类型授权地区列表
			@SuppressWarnings("static-access")
			Page<SystemAdminOrgan> org_page = this.createPage(request, "iSystemOrg");
			List<SystemAdminOrgan> org_list = this.systemCardManage.getSystemOrgList(org_page, cardTypeId, true, "");
			request.setAttribute("org_list", org_list);
		}
		
		//卡用户
		SystemUser userInfo = this.systemCardManage.getSystemCardUserByCardId(cardId);
		
		if(userInfo != null){
			if(userInfo.getWork_unit_id() != null && !"".equals(userInfo.getWork_unit_id())){
				ExamHospital host = new ExamHospital();
				host.setId(Long.valueOf(userInfo.getWork_unit_id()));
				ExamHospital exam = localExamPropValFacade.getHospitalById(host);
				if(exam != null){
					userInfo.setWorkUnit(exam.getName());
				}else{
					userInfo.setWorkUnit(null);
				}
			}else{
				userInfo.setWorkUnit(userInfo.getOtherHospitalName());
			}
		}
		request.setAttribute("userInfo", userInfo);
		
		return "viewDetail";
	}
	
	public SystemCardManage getSystemCardManage() {
		return systemCardManage;
	}

	public void setSystemCardManage(SystemCardManage systemCardManage) {
		this.systemCardManage = systemCardManage;
	}

	public SystemSiteManage getSystemSiteManage() {
		return systemSiteManage;
	}

	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
}


