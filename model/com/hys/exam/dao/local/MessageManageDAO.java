package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Advert;
import com.hys.exam.model.Message;
import com.hys.exam.model.MessageSendType;
import com.hys.exam.model.SystemMessageModel;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.MessageManage;
import com.hys.exam.utils.PageList;

public interface MessageManageDAO {

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
	//判断标题名是否重复
	int getMessagetByName(String title);
	//修改消息
	boolean updateMessage(Message message);
	//发送邮件
	boolean sendMessage(SystemMessageModel messageModel);
	List<SystemUser> getUserByType(Integer userType);
	SystemSite getMessagetBySite(String tempContextUrl);
	boolean insertSendType(MessageSendType sendType);
	List<MessageSendType> getSendCheck(Long id);
	
}
