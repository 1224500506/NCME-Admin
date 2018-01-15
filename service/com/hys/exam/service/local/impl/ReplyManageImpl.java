package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ReplyManageDAO;
import com.hys.exam.model.Reply;
import com.hys.exam.service.local.ReplyManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * @author weeho
 *
 */
public class ReplyManageImpl  extends BaseMangerImpl implements ReplyManage {
	
	private ReplyManageDAO replyManageDAO;
	
	public ReplyManageDAO getReplyManageDAO() {
		return replyManageDAO;
	}

	public void setReplyManageDAO(ReplyManageDAO replyManageDAO) {
		this.replyManageDAO = replyManageDAO;
	}

	@Override
	public boolean addReply(Reply reply) {
		return replyManageDAO.addReply(reply);

	}

	@Override
	public List<Reply> getReplyById(Long id) {
		return replyManageDAO.getReplyById(id);
	}

}
