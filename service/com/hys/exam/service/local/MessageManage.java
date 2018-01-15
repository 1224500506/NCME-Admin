package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.Advert;
import com.hys.exam.model.Message;
import com.hys.exam.model.MessageSendType;
import com.hys.exam.model.SystemMessageModel;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface MessageManage extends BaseService{
	//根据id查对象
	Message getMessageById(Long id);
	//根据对象查集合
	List<Message> getMessageList(Message message);
	//添加
	boolean addMessage(Message message);
	//修改状态
	boolean updateState(Long id,int state);
	//根据id删除
	boolean deleteMessageById(Long id);
	//列表查询
	void getMessagePageList(PageList pl, Message message);
	//查询站点
	public List<SystemSite> getSiteListByMessageId(long id);
	int getMessagetByName(String title);
	boolean updateMessage(Message message);
	//发送邮件
	boolean sendMessage(SystemMessageModel messageModel);
	//
	List<SystemUser> getUserByType(Integer userType);
	//获取站点
	SystemSite getMessagetBySite(String tempContextUrl);
	boolean insertSendType(MessageSendType sendType);
	List<MessageSendType> getSendCheck(Long id);
	
}
