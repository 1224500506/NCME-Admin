package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ReplyManageDAO;
import com.hys.exam.model.Reply;

public class ReplyManageJDBCDAO extends BaseDao implements ReplyManageDAO {

	/**
	 * 添加回复
	 */
	@Override
	public boolean addReply(Reply reply) {
		reply.setId(this.getSequence("reply"));
		String sql="INSERT into reply (reply_content,replyer,REPLY_TYPE,fid) VALUES (:reply_content,:replyer,:reply_type,:fid)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(reply));
		return true;
	}
	/**
	 * 根据反馈的id查回复的集合
	 */
	@Override
	public List<Reply> getReplyById(Long id) {
		String sql_reply = "SELECT r.* FROM reply r LEFT JOIN feedback f on r.fid=f.id where r.fid=" + id;
		List<Reply> replyList = getJdbcTemplate().query(sql_reply,ParameterizedBeanPropertyRowMapper.newInstance(Reply.class));
		return replyList;
		
	}

}
