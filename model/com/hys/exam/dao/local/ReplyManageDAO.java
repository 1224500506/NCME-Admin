package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Reply;

public interface ReplyManageDAO {
	//添加
		boolean addReply(Reply reply);
		//查询所有的集合
		List<Reply> getReplyById(Long id);

}
