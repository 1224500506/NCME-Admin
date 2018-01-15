package com.hys.exam.struts.action.content;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.Feedback;
import com.hys.exam.model.Message;
import com.hys.exam.model.MessageSendType;
import com.hys.exam.model.MobileMessage;
import com.hys.exam.model.Reply;
import com.hys.exam.model.SystemMessageModel;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.MessageManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.struts.form.MessageForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.VCUtil;
import com.hys.framework.web.action.BaseAction;

public class MessageAction extends BaseAction{
	
	//system_user
	private SystemUserManage systemUserManage;
	
	private MessageManage messageManage;
	
	//站点
	private SystemSiteManage localSystemSiteManage;
	
	
	
	public SystemUserManage getSystemUserManage() {
		return systemUserManage;
	}
	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}
	public SystemSiteManage getLocalSystemSiteManage() {
		return localSystemSiteManage;
	}
	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}
	public MessageManage getMessageManage() {
		return messageManage;
	}
	public void setMessageManage(MessageManage messageManage) {
		this.messageManage = messageManage;
	}
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getParameter("method");
		if(method.equals("list")){
			return list(mapping, form, request, response);
		}else if(method.equals("showView")){
			return  showView(mapping, form, request, response);
		}else if(method.equals("add")){
			return  add(mapping, form, request, response);
		}else if(method.equals("search")){
			return  list(mapping, form, request, response);
		}else if(method.equals("save")){
			return  save(mapping, form, request, response);
		}else if(method.equals("edit")){
			return  edit(mapping, form, request, response);
		}else if(method.equals("delete")){
			return  delete(mapping, form, request, response);
		}else if(method.equals("update")){
			return  update(mapping, form, request, response);
		}else if(method.equals("updateState")){
			return  updateState(mapping, form, request, response);
		}else if(method.equals("sendMessage")){
			return  sendMessage(mapping, form, request, response);
		}else{
			return list(mapping, form, request, response);
		}

	}
	//弹出框
	private String showView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		Map<String, Object> map = new HashMap<String, Object>();
		Message message = messageManage.getMessageById(id);
 		map.put("message", message);  
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	//发送消息    邮件/站内消息
	private String sendMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//发件箱id
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		Message message = messageManage.getMessageById(id);
		//发件箱状态修改为2已发送
		Integer sendState=2;
		//站内邮件发送
		SystemMessageModel messageModel = new SystemMessageModel();
		//获取所有学员   根据id获取学员集合
		SystemUser item =new SystemUser();
		Integer userType=1;   //学员
		List<SystemUser> userList= messageManage.getUserByType(userType);
		//判断发送方式
		List<MessageSendType>  sendchecks =	messageManage.getSendCheck(id);
		for (MessageSendType messageSendType : sendchecks) {
			if(messageSendType.getSendtype().equals("1")|| messageSendType.getSendtype().equals("2")){
				//站内邮件发送
				for (SystemUser systemUser : userList) {
					//获取user_id
					item.setAccountId(systemUser.getId());
					messageModel.setSystem_user_id(item.getAccountId());
					//获取邮件email
					item.setEmail(systemUser.getEmail());
					//发送内容
					messageModel.setMessage_content("您好！"+message.getContent());  
					//状态：未读
					Integer is_read=1;
					messageModel.setIs_read(is_read);
					boolean b = messageManage.sendMessage(messageModel);
					if (b) {
						//修改发件状态：已发送
						boolean c = messageManage.updateState(id, sendState);
						StrutsUtil.renderText(response, "success");
						
					}else {
						StrutsUtil.renderText(response, "fail");
						return  null;
					}
					
				//短信发送
				StringBuffer content = new StringBuffer("您好！"+"[+"+message.getTitle()+"+],"+message.getContent()+"【NCME】");
				//获取发送的手机号
				String mobile_phone =systemUser.getMobilPhone();
				String sendResult = VCUtil.sendSMS(mobile_phone, content.toString(), "135", "");
				if(sendResult!=null && !"".equals(sendResult)){
					if("success".equals(sendResult)){
						//修改发件状态：已发送
						boolean c = messageManage.updateState(id, sendState);
						if (c) {
							MobileMessage mobileMessage = new MobileMessage();
							mobileMessage.setCONTENT(message.getContent());
							 
							Integer valueOf = Integer.valueOf(mobile_phone);
							mobileMessage.setMOBILE_PHONE(valueOf);
							//状态  1：发送失败   2发送成功
							mobileMessage.setSEND_STATUS(2);
							//发送成功
							mobileMessage.setSTATUS(2);
							//站点
							StringBuffer url = request.getRequestURL();  
							String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
							SystemSite site =	messageManage.getMessagetBySite(tempContextUrl);
							mobileMessage.setSITE_ID(site.getId());
							StrutsUtil.renderText(response, "success");
						}else {
							MobileMessage mobileMessage = new MobileMessage();
							mobileMessage.setCONTENT(message.getContent());
							//手机号
							Integer valueOf = Integer.valueOf(mobile_phone);
							mobileMessage.setMOBILE_PHONE(valueOf);
							//状态  1：发送失败   2发送成功
							mobileMessage.setSEND_STATUS(1);
							//发送失败
							mobileMessage.setSTATUS(1);
							//站点
							StringBuffer url = request.getRequestURL();  
							String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
							SystemSite site =	messageManage.getMessagetBySite(tempContextUrl);
							mobileMessage.setSITE_ID(site.getId());
							StrutsUtil.renderText(response, "fail");
						}
						
					}else{
						StrutsUtil.renderText(response, "fail");
					}
					return  null;
				}
			}
		}else if (messageSendType.getSendtype().equals("1")) {
					//站内邮件发送
					for (SystemUser systemUser : userList) {
						//获取user_id
						item.setAccountId(systemUser.getId());
						messageModel.setSystem_user_id(item.getAccountId());
						//获取邮件email
						item.setEmail(systemUser.getEmail());
						//发送内容
						messageModel.setMessage_content("您好！"+"["+message.getTitle()+"],"+message.getContent());  
						//状态：未读
						Integer is_read=1;
						messageModel.setIs_read(is_read);
						boolean b = messageManage.sendMessage(messageModel);
						if (b) {
							//修改发件状态：已发送
							boolean c = messageManage.updateState(id, sendState);
							StrutsUtil.renderText(response, "success");
							
						}else {
							StrutsUtil.renderText(response, "fail");
						}
					}
					return  null;
					
				}else if (messageSendType.getSendtype().equals("2")) {
					/*//短信发送
					StringBuffer content = new StringBuffer("您好！"+"["+message.getTitle()+"],"+message.getContent()+"【NCME】");
					//获取发送的手机号
					String mobile_phone = "15896829085";
					
					String sendResult = VCUtil.sendSMS(mobile_phone, content.toString(), "135", "");
					if(sendResult!=null && !"".equals(sendResult)){
						if("success".equals(sendResult)){
							//修改发件状态：已发送
							boolean c = messageManage.updateState(id, sendState);
							if (c) {
								MobileMessage mobileMessage = new MobileMessage();
								mobileMessage.setCONTENT(message.getContent());
								 
								Integer valueOf = Integer.valueOf(mobile_phone);
								mobileMessage.setMOBILE_PHONE(valueOf);
								//状态  1：发送失败   2发送成功
								mobileMessage.setSEND_STATUS(2);
								//发送成功
								mobileMessage.setSTATUS(2);
								//站点
								StringBuffer url = request.getRequestURL();  
								String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
								SystemSite site =	messageManage.getMessagetBySite(tempContextUrl);
								mobileMessage.setSITE_ID(site.getId());
								StrutsUtil.renderText(response, "success");
							}else {
								MobileMessage mobileMessage = new MobileMessage();
								mobileMessage.setCONTENT(message.getContent());
								//手机号
								Integer valueOf = Integer.valueOf(mobile_phone);
								mobileMessage.setMOBILE_PHONE(valueOf);
								//状态  1：发送失败   2发送成功
								mobileMessage.setSEND_STATUS(1);
								//发送失败
								mobileMessage.setSTATUS(1);
								//站点
								StringBuffer url = request.getRequestURL();  
								String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
								SystemSite site =	messageManage.getMessagetBySite(tempContextUrl);
								mobileMessage.setSITE_ID(site.getId());
								StrutsUtil.renderText(response, "fail");
							}
							
						}else{
							StrutsUtil.renderText(response, "fail");
						}
						return  null;
					}*/
					//***********************************
					//测试时，可以关闭       上线一定要打开
					//*************************************
					StringBuffer content = new StringBuffer("您好！"+"["+message.getTitle()+"],"+message.getContent()+"【NCME】");
					for (SystemUser systemUser : userList) {
						//获取手机号
						String mobile_phone =systemUser.getMobilPhone();
						if(mobile_phone!=null && !"".equals(mobile_phone)){
							//调用发送短信接口
							String sendResult = VCUtil.sendSMS(mobile_phone, content.toString(), "135", "");
							if(sendResult!=null && !"".equals(sendResult)){
								if("success".equals(sendResult)){
									//修改发件状态：已发送
									boolean c = messageManage.updateState(id, sendState);
									if (c) {
										MobileMessage mobileMessage = new MobileMessage();
										mobileMessage.setCONTENT(message.getContent());
										 
										Integer valueOf = Integer.valueOf(mobile_phone);
										mobileMessage.setMOBILE_PHONE(valueOf);
										//状态  1：发送失败   2发送成功
										mobileMessage.setSEND_STATUS(2);
										//发送成功
										mobileMessage.setSTATUS(2);
										//站点
										StringBuffer url = request.getRequestURL();  
										String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
										SystemSite site =	messageManage.getMessagetBySite(tempContextUrl);
										mobileMessage.setSITE_ID(site.getId());
										StrutsUtil.renderText(response, "success");
									}else {
										MobileMessage mobileMessage = new MobileMessage();
										mobileMessage.setCONTENT(message.getContent());
										//手机号
										Integer valueOf = Integer.valueOf(mobile_phone);
										mobileMessage.setMOBILE_PHONE(valueOf);
										//状态  1：发送失败   2发送成功
										mobileMessage.setSEND_STATUS(1);
										//发送失败
										mobileMessage.setSTATUS(1);
										//站点
										StringBuffer url = request.getRequestURL();  
										String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
										SystemSite site =	messageManage.getMessagetBySite(tempContextUrl);
										mobileMessage.setSITE_ID(site.getId());
										StrutsUtil.renderText(response, "fail");
									}
									
								}else{
									StrutsUtil.renderText(response, "fail");
								}
							}else{
								StrutsUtil.renderText(response, "fail");
							}
						}else{
							StrutsUtil.renderText(response, "fail");
						}
						return  null;
					}
			}
		}
		return null;
	}
	/**
	 * 编辑   id
	 */
	private String edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		
		Message message = messageManage.getMessageById(id);
		List<MessageSendType>  sendchecks =	messageManage.getSendCheck(message.getId());
		String type="0";
		if(null!=sendchecks){
			if(sendchecks.size()==2){
				type="3";
			}else{
				MessageSendType dbtype=sendchecks.get(0);
				if("1".equals(dbtype.getSendtype())){
					type="1";
				}else{
					type="2";
				}
			}
		}
		
		request.setAttribute("type", type);
		request.setAttribute("model", message);
		
		int count = localSystemSiteManage.getSystemSiteList().size();
		request.setAttribute("id", id);
 		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
 		Message messageSite=new Message();
		messageSite.setId(id);
		List<Message> list = messageManage.getMessageList(messageSite);
 		request.setAttribute("checkSite", list.get(0).getSiteList());
 		
		return "edit";
	}
	//查询列表
	private String list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String sdate = request.getParameter("start_date");
		String edate = request.getParameter("end_date");

		MessageForm mform = (MessageForm) form;
		Message message = mform.getModel();
		message.setStart_date(sdate);
		message.setEnd_date(edate);
		
		PageList pl = new PageList();
	
		messageManage.getMessagePageList(pl, message);
//		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
		request.setAttribute("list", pl);
 		request.setAttribute("model", message);
		
		return "success";
	}

	private String update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return null;
	}

	private String updateState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return null;
	}
	//单一删除
	private String delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		if (id > 0){
			messageManage.deleteMessageById(id);
		StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "fail");
		}
		
		long[] selIdArr = ParamUtils.getLongParametersFromString(request, "typeIds", 0);
		int res = 0;
		if(selIdArr!=null && selIdArr.length>0){ 
			for (long sid : selIdArr ){
				boolean b = messageManage.deleteMessageById(sid);
				if(b){
					StrutsUtil.renderText(response, "success");
				}else {
					StrutsUtil.renderText(response, "fail");
				}
				
			}
			
		}
		return null;
	}

	//添加内容
	private String save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		String[] types = ParamUtils.getStringParameters(request, "sendcheck");
//		ParamUtils.getIn
		boolean flag = false;
		MessageForm mform = (MessageForm) form;
		Message message = mform.getModel();
		//保存用户信息
		SystemUser user = LogicUtil.getSystemUser(request);
		if(user == null) {
			return "fail";
		}
		//操作人
		message.setCreater(user.getAccountName());
		//站点
		//保存站点
		long[] site_array = ParamUtils.getLongParameters(request, "site", -1L);
		List<SystemSite> systemSiteList = new ArrayList<SystemSite>();
		
		if(site_array != null) {
			for(int i=0; i<site_array.length; i++) {
				SystemSite systemSite = new SystemSite();
				systemSite.setId(site_array[i]);
				systemSiteList.add(systemSite);			
			}
			
			message.setSiteList(systemSiteList);
		}
		Integer sendState = 1;
		message.setSendState(sendState);

		//判断消息标题
		if(id!=null && id > 0L){
			Message article = messageManage.getMessageById(id);
			if(!message.getTitle().equals(article.getTitle())){
				//保存前校验消息标题是否重复,如果重复直接返回
				int count = messageManage.getMessagetByName(message.getTitle());
				if(count > 0){
					StrutsUtil.renderText(response, "fail");
					return null;
				}
			}
			message.setId(id);//修改标题
			flag = messageManage.updateMessage(message);
		}
		else{
			//保存前校验消息标题是否重复,如果重复直接返回
			int count = messageManage.getMessagetByName(message.getTitle());
			if(count > 0){
				StrutsUtil.renderText(response, "fail");
				return null;
			}
			flag = messageManage.addMessage(message);
		}
		
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}

	private String add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//添加--站点
		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
		return "add";
	}

	

}
