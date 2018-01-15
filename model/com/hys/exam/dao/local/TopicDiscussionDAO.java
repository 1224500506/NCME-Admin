package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CvSetUnitDiscuss;
import com.hys.exam.model.TopicDiscussion;


public interface TopicDiscussionDAO {
	
	/**
	 * @author   张建国
	 * @time     2017-02-23
	 * @return   TopicDiscussion
	 * 方法说明：  查询所有话题讨论的讨论点
	 */
	public List<TopicDiscussion> queryTopicDiscussion();
	
	/**
	 * @author 张建国
	 * @time   2017-02-23
	 * @param  Long 
	 * @return TopicDiscussion
	 * 方法说明：根据讨论点Id查询讨论点信息
	 */
	public TopicDiscussion queryTopicDiscussionById(Long id);
	
	/**
	 * @author 张建国
	 * @param  Long
	 * @return List<CvSetUnitDiscuss>
	 * @time   2017-02-20
	 * 方法说明： 根据讨论点Id和讨论点Id查询评论记录，添加单元id（讨论点被克隆）
	 */
	public List<CvSetUnitDiscuss> queryTalkDiscuss(Long discussId, Long unitId);
	
	/**
	 * @author   张建国
	 * @time     2017-02-24
	 * @param    TopicDiscussion
	 * @return   Long
	 * 方法说明： 添加一条讨论点
	 */
	public Long saveTopicDiscussionById(TopicDiscussion t);
}
