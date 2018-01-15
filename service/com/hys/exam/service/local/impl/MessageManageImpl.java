package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.MessageManageDAO;
import com.hys.exam.model.Message;
import com.hys.exam.model.MessageSendType;
import com.hys.exam.model.SystemMessageModel;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.MessageManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class MessageManageImpl extends BaseMangerImpl implements MessageManage {

	private MessageManageDAO messageManageDAO;
	
	public MessageManageDAO getMessageManageDAO() {
		return messageManageDAO;
	}

	public void setMessageManageDAO(MessageManageDAO messageManageDAO) {
		this.messageManageDAO = messageManageDAO;
	}

	@Override
	public Message getMessageById(Long id) {
		return messageManageDAO.getMessageById(id);
	}

	@Override
	public List<Message> getMessageList(Message message) {
		return messageManageDAO.getMessageList(message);
	}

	@Override
	public boolean addMessage(Message message) {
		if(messageManageDAO.addMessage(message))
			return true;
		else
			return false;
	}

	@Override
	public boolean updateState(Long id, int state) {
		return messageManageDAO.updateState(id, state);
	}

	@Override
	public boolean deleteMessageById(Long id) {
		return messageManageDAO.deleteMessageById(id);
	}

	@Override
	public void getMessagePageList(PageList pl, Message message) {
		messageManageDAO.getMessagePageList(pl, message);
	}

	@Override
	public List<SystemSite> getSiteListByMessageId(long id) {
		return messageManageDAO.getSiteListByMessageId(id);
	}

	@Override
	public int getMessagetByName(String title) {
		return messageManageDAO.getMessagetByName(title);
	}

	@Override
	public boolean updateMessage(Message message) {
		return messageManageDAO.updateMessage(message);
	}

	@Override
	public boolean sendMessage(SystemMessageModel messageModel) {
		return messageManageDAO.sendMessage(messageModel);
	}

	@Override
	public List<SystemUser> getUserByType(Integer userType) {
		return messageManageDAO.getUserByType(userType);
	}

	@Override
	public SystemSite getMessagetBySite(String tempContextUrl) {
		return messageManageDAO.getMessagetBySite(tempContextUrl);
	}

	@Override
	public boolean insertSendType(MessageSendType sendType) {
		
		return messageManageDAO.insertSendType(sendType);
	}

	@Override
	public List<MessageSendType> getSendCheck(Long id) {
		return messageManageDAO.getSendCheck(id);
	}

}
