package com.hys.exam.struts.action.content;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.Reply;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.FeedbackManage;
import com.hys.exam.service.local.ReplyManage;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.struts.form.ReplyForm;

public class ReplyAction extends AppBaseAction {

	// 反馈
	private FeedbackManage feedbackManage;
	// 回复表
	private ReplyManage replyManage;

//	private 
	
	public FeedbackManage getFeedbackManage() {
		return feedbackManage;
	}

	public void setFeedbackManage(FeedbackManage feedbackManage) {
		this.feedbackManage = feedbackManage;
	}

	public ReplyManage getReplyManage() {
		return replyManage;
	}

	public void setReplyManage(ReplyManage replyManage) {
		this.replyManage = replyManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String method = request.getParameter("method");
		if (method.equals("list")) {
			return list(mapping, form, request, response);
		} else if (method.equals("replyAdd")) {
			return replyAdd(mapping, form, request, response);
		} else {
			return list(mapping, form, request, response);
		}
	}

	private String replyAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "fid", -1L);
		ReplyForm replyForm = (ReplyForm) form;
		Reply reply = replyForm.getModel();
		String reply_content = request.getParameter("reply_content");
		reply.setReply_content(reply_content);
		reply.setFid(id);
		if (reply_content !=null && !reply_content.equals("")) {
			// 查看时，把修改状态为已回复 1是未回复 2 是已回复
			int state = Integer.parseInt(ParamUtils.getParameter(request, "state", "-1").toString());
			if (id > 0 && state > 0) {
				feedbackManage.updateState(id, state);
			} else {
				/*
				 * feedbackManage.updateState(id, state);
				 * StrutsUtil.renderText(response, "success");
				 */
			}
			SystemUser user = LogicUtil.getSystemUser(request);
			if(user == null)
			{
				return "fail";
			}
			reply.setReplyer(user.getAccountName());
			
			// 默认系统消息类型 1是系统平台 2其他
			Integer reply_type = 1;
			reply.setReply_type(reply_type);
			
			boolean b = replyManage.addReply(reply);
			if (b) {
				StrutsUtil.renderText(response, "success");
			}else {
				StrutsUtil.renderText(response, "fail");
			}
		}else {
			StrutsUtil.renderText(response, "fail");
		}
	
		
		return null;
	}

	private String list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
}
