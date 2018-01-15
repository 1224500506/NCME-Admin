package com.hys.exam.struts.action.talk;

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
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.filter.SensitivewordFilter;
import com.hys.exam.model.CvSetUnitDiscuss;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.TopicDiscussion;
import com.hys.exam.service.local.TopicDiscussionManage;
import com.hys.framework.web.action.BaseAction;
import com.mysql.fabric.xmlrpc.base.Data;

import net.sf.json.JSONObject;

/**
 * 
 * 标题：话题讨论
 * 
 * 作者：张建国 2017-02-23
 * 
 * 描述：
 * 
 * 说明: 
 */
public class TopicDiscussionAction extends BaseAction {

	TopicDiscussionManage topicDiscussionManage;

	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String handle = request.getParameter("handle");
		//查询一条讨论点信息
		if(handle!=null && handle.equals("queryOne")){
			return queryOne(mapping,form,request,response);
		}
		//插入一条讨论点信息
		else if(handle!=null && handle.equals("saveOneTopic")){
			return saveOneTopic(mapping,form,request,response);
		}
		List<TopicDiscussion> list = topicDiscussionManage.queryTopicDiscussion();
		request.setAttribute("list", list);
		return "SUCCESS";
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-23
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 根据id查询讨论点信息
	 */
	private String queryOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		Long talkId = ParamUtils.getLongParameter(request, "talkId", 0L) ;
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		TopicDiscussion t = topicDiscussionManage.queryTopicDiscussionById(talkId);
		//查询讨论点对应的评论
		List<CvSetUnitDiscuss> discussList = topicDiscussionManage.queryTalkDiscuss(talkId,unitId);
		//对主题讨论进行敏感词过滤
		SensitivewordFilter filter = new SensitivewordFilter();
		for (CvSetUnitDiscuss discuss:discussList) {
			String content = discuss.getDiscussContent();
			String discussContent = filter.replaceSensitiveWord(content, 1, "*");
			discuss.setDiscussContent(discussContent);
		}
		t.setCvSetUnitDiscussList(discussList);
		request.setAttribute("topicDiscussion", t);
		request.setAttribute("unitId", unitId);
		return "queryOne";
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-23
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 插入一条讨论点信息
	 */
	private String saveOneTopic(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		// 当前的系统用户
		SystemUser user = LogicUtil.getSystemUser(request);	
		//获取讨论点标题
		String talkContent = request.getParameter("talkContent");
		//获取讨论点解析
		String talkAnsy = request.getParameter("talkAnsy");
		/*//获取讨论点创建时间
		String createDate = request.getParameter("createDate");
		//获取讨论点创建人名称
		String createUser = request.getParameter("createUser");*/
		//实例化讨论点对象
		TopicDiscussion t = new TopicDiscussion();
		t.setTalkContent(talkContent);
		t.setCreateDate(DateUtil.format(new Date(), DateUtil.FORMAT_SHORT));
		t.setCreateUser(user.getRealName());
		t.setTalkAnsy(talkAnsy);
		//执行保存
		Long id = topicDiscussionManage.saveTopicDiscussionById(t);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("message", "success");
		jsonObj.put("talkId", id);
		StrutsUtil.renderText(response, jsonObj.toString());
		return null;
	}
	
	public TopicDiscussionManage getTopicDiscussionManage() {
		return topicDiscussionManage;
	}

	public void setTopicDiscussionManage(TopicDiscussionManage topicDiscussionManage) {
		this.topicDiscussionManage = topicDiscussionManage;
	}
	
	
	
	
	
	
	
}
