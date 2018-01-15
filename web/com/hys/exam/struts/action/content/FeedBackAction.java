package com.hys.exam.struts.action.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.Advert;
import com.hys.exam.model.Feedback;
import com.hys.exam.model.Reply;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.local.FeedbackManage;
import com.hys.exam.service.local.ReplyManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.struts.form.BannerForm;
import com.hys.exam.struts.form.FeedbackForm;
import com.hys.exam.struts.form.ReplyForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;

import net.sf.json.JSONObject;

public class FeedBackAction extends AppBaseAction{
	//反馈
	private FeedbackManage feedbackManage;
	//站点
	private SystemSiteManage localSystemSiteManage;
	//回复表
	private ReplyManage replyManage;
	public ReplyManage getReplyManage() {
		return replyManage;
	}
	public void setReplyManage(ReplyManage replyManage) {
		this.replyManage = replyManage;
	}
	public FeedbackManage getFeedbackManage() {
		return feedbackManage;
	}
	public void setFeedbackManage(FeedbackManage feedbackManage) {
		this.feedbackManage = feedbackManage;
	}
	public SystemSiteManage getLocalSystemSiteManage() {
		return localSystemSiteManage;
	}
	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SystemUser user = LogicUtil.getSystemUser(request);
		if(user == null)
		{
			return "fail";
		}
		
		String method = request.getParameter("method");
		if(method.equals("list")){
			return list(mapping, form, request, response);
		}else if(method.equals("search")){
			return  list(mapping, form, request, response);
		}else if(method.equals("delete")){
			return  delete(mapping, form, request, response);
		}else if(method.equals("edit")){
			return  edit(mapping, form, request, response);
		}else if(method.equals("replyAdd")){
			return  replyAdd(mapping, form, request, response);
		}else{
			return list(mapping, form, request, response);
		}
	}
	//回复操作
	private String replyAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "fid", -1L);
		ReplyForm replyForm = (ReplyForm) form;
		Reply reply = replyForm.getModel();
		
		//查看时，把修改状态为已回复      1是未回复   2 是已回复
		int state = Integer.parseInt(ParamUtils.getParameter(request, "state", "-1").toString());
		logger.info("------------------接收状态-------------"+state);
		if (id > 0 && state > 0){
				feedbackManage.updateState(id, state);
			}else {
				/*feedbackManage.updateState(id, state);
				StrutsUtil.renderText(response, "success");*/
			}
		//默认系统消息类型     1是系统平台  2其他
		Integer reply_type = 1;
		
		replyManage.addReply(reply);
		
		return null;
	}
	private String edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<Feedback> feedbacks = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		
//		SystemSite site = new SystemSite();
		//获取站点
		/*List<SystemSite> siteListByFeebackId = feedbackManage.getSiteListByFeebackId(id);
		for (SystemSite systemSite : siteListByFeebackId) {
			site.setDomainName(systemSite.getDomainName());
		}*/
		Feedback feedback = feedbackManage.getfeedbackyId(id);
		List<Reply> replyList = replyManage.getReplyById(feedback.getId());
 		map.put("feedback", feedback);  //反馈数据
 		map.put("replyList", replyList); //回复数据  list集合
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}

	private String delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		if (id !=null && id >0) {
			if (id > 0) {
				boolean b = feedbackManage.deleteFeedbackById(id);
				if (b) {
					StrutsUtil.renderText(response, "success");
				}else {
					StrutsUtil.renderText(response, "fail");
				}
			}
			
		}
		long[] selIdArr = ParamUtils.getLongParametersFromString(request, "typeIds", 0);
		int res = 0;
		if(selIdArr!=null && selIdArr.length>0){ 
			for (long sid : selIdArr ){
				boolean b = feedbackManage.deleteFeedbackById(sid);
				if(b){
					StrutsUtil.renderText(response, "success");
				}else {
					StrutsUtil.renderText(response, "fail");
				}
				
			}
			
		}
		return null;
	}


	private String list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sdate = request.getParameter("start_date");
		String edate = request.getParameter("end_date");
		FeedbackForm feedbackform = (FeedbackForm) form;
		Feedback feedback = feedbackform.getModel();
		feedback.setStart_date(sdate);
		feedback.setEnd_date(edate);
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		/*String sort = ParamUtils.getParameter(request, "sort", "ordernum asc, id");
		String dir = ParamUtils.getParameter(request, "dir", "desc");*/
		
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(pageSize);
		
		feedbackManage.getFeedbackPageList(pl, feedback);
//		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
		request.setAttribute("list", pl);
 		request.setAttribute("model", feedback);
 		request.setAttribute("system", feedback.getSystem());
 		request.setAttribute("count", feedback.getContent());
 		request.setAttribute("state", feedback.getState());
		return "success";
	}

}
