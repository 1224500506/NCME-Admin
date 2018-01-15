package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.TopicDiscussionDAO;
import com.hys.exam.model.CvSetUnitDiscuss;
import com.hys.exam.model.TopicDiscussion;
import com.hys.exam.util.FuncMySQL;


public class TopicDiscussionJDBCDAO extends BaseDao implements TopicDiscussionDAO {
	
	/**
	 * @author   张建国
	 * @time     2017-02-23
	 * @return   void
	 * 方法说明：  查询所有话题讨论的讨论点
	 */
	@Override
	public List<TopicDiscussion> queryTopicDiscussion(){
		String sql = " select * from topic_discussion ";
	    return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(TopicDiscussion.class));
	}
	
	/**
	 * @author 张建国
	 * @time   2017-02-23
	 * @param  Long 
	 * @return TopicDiscussion
	 * 方法说明：根据讨论点Id查询讨论点信息
	 */
	public TopicDiscussion queryTopicDiscussionById(Long id){
		String sql = " select * from topic_discussion where id = ? ";
		List<TopicDiscussion> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(TopicDiscussion.class),id);
		if(Utils.isListEmpty(list)){
			return null ;
		}
		return list.get(0);
	}
	
	/**
	 * @author 张建国
	 * @param  Long
	 * @return List<CvSetUnitDiscuss>
	 * @time   2017-02-20
	 * 方法说明： 根据讨论点Id和讨论点Id查询评论记录，添加单元id（讨论点被克隆）
	 */
	public List<CvSetUnitDiscuss> queryTalkDiscuss(Long discussId, Long unitId){
		String sql = " select discuss.*,u.REAL_NAME as systemUserName, u.user_avatar as userAvatar, u.sex from cv_set_unit_discuss discuss "
				     +" left join system_user u on discuss.system_user_id=u.id "
				     +" where discuss.discuss_id=? and discuss.CV_UNIT_ID=? and discuss.discuss_type=2 "
				     +" order by discuss.discuss_date desc ";
		List<CvSetUnitDiscuss> list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CvSetUnitDiscuss.class),discussId,unitId);
		return list;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-24
	 * @param    TopicDiscussion
	 * @return   Long
	 * 方法说明： 添加一条讨论点
	 */
	public Long saveTopicDiscussionById(TopicDiscussion t){
		Long id = getNextId("topic_discussion");
		t.setId(id);
		
		/*String sql = " insert into topic_discussion (id,talk_content,create_date,create_user,talk_ansy) values(?,?,to_date(?,'yyyy-MM-dd'),?,?) ";
		getJdbcTemplate().update(sql,t.getId(),t.getTalkContent(),t.getCreateDate(),t.getCreateUser(),t.getTalkAnsy());*/
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库		
		String sql = " insert into topic_discussion (id,talk_content,create_date,create_user,talk_ansy) values(?,?," + FuncMySQL.shortDateForUpdate(t.getCreateDate()) + ",?,?) ";
		
		getJdbcTemplate().update(sql,t.getId(),t.getTalkContent(),t.getCreateUser(),t.getTalkAnsy());
		return t.getId();
	}
}
