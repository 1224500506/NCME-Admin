package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.Reply;
import com.hys.framework.service.BaseService;



public interface ReplyManage extends BaseService{
	//添加
	boolean addReply(Reply reply);
	//查询所有的集合
	List<Reply> getReplyById(Long id);
}
