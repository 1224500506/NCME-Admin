package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class MaterialAddAction extends BaseAction {
	
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamPropValFacade localExamPropValFacade;
	
	public ExamQuestionTypeFacade getLocalExamQuestionTypeFacade() {
		return localExamQuestionTypeFacade;
	}
	
	public void setLocalExamQuestionTypeFacade(
			ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}



	public ExamQuestionLabelFacade getLocalExamQuestionLabelFacade() {
		return localExamQuestionLabelFacade;
	}



	public void setLocalExamQuestionLabelFacade(
			ExamQuestionLabelFacade localExamQuestionLabelFacade) {
		this.localExamQuestionLabelFacade = localExamQuestionLabelFacade;
	}



	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}



	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}



	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}



	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		//试题分类
		List<ExamQuestionType> questType = localExamQuestionTypeFacade.getQuestionTypeRootBySysId(null);
		
		String type = request.getParameter("type") != null ? request.getParameter("type"): "";
		if(type.equals("clear")){
			request.getSession().removeAttribute("query");
		}
		
		ExamQuestionForm qform = (ExamQuestionForm)form;
		
		ExamQuestionQuery query  = (ExamQuestionQuery)request.getSession().getAttribute("query");
		
		PageList pl = new PageList();
		
		Map<String,List<ExamPropVal>> propMap = localExamPropValFacade.getSysPropValBySysId();
		List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
		
		for (Iterator<?> iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
				questPropPoint2 = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
				questPropCognize = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
				questPropTitle =(List<ExamPropVal>)entry.getValue();
			}
		}
		
		if(query==null){
			
			query  = new ExamQuestionQuery();
			if(qform.getQuestion_label_id() != null &&  qform.getQuestion_label_id().intValue() != -1){
				query.setQuestion_label_ids(qform.getQuestion_label_id().toString());
			}
			
			//排序规则
			if(null!=qform.getOrderItem()){
				query.setOrderItem(qform.getOrderItem());
			}
			
			// 试题状态
			if(qform.getState() == null || qform.getState() == -1){
				query.setState(-1);
			}else{
				query.setState(qform.getState());
			}
			// 试题难度
			if(qform.getGrade() != null &&  qform.getGrade().intValue() != -1){
				query.setGrade(qform.getGrade());
			}
			// 多媒体试题
			if(qform.getIsnot_multimedia() != null &&  qform.getIsnot_multimedia().intValue() != -1){
				query.setIsnot_multimedia(qform.getIsnot_multimedia());
			}		
			// 试题区分度
			if(qform.getDiffer() != null &&  qform.getDiffer().intValue() != -1){
				query.setDiffer(qform.getDiffer());
			}
			// 试题内容
			if(qform.getContent() != null && !qform.getContent().trim().equals("")){
				query.setContent(qform.getContent());
			}
			// 创建者
			if(qform.getAuthor() != null && !qform.getAuthor().trim().equals("")){
				query.setAuthor(qform.getAuthor());
			}
			// 试题来源
			if(qform.getSource() != null && !qform.getSource().trim().equals("")){
				query.setSource(qform.getSource());
			}
			// 是否含有试题分析
			if(qform.getIsExistAnalyse() != null && qform.getIsExistAnalyse().intValue() != -1){
				query.setIsExistAnalyse(qform.getIsExistAnalyse());
			}
			
			//创建时间
			if(!StringUtils.checkNull(qform.getCreate_date())){
				query.setCreate_date(qform.getCreate_date());
			}
			
			//审核时间
			if(!StringUtils.checkNull(qform.getOnline_date())){
				query.setOnline_date(qform.getOnline_date());
			}
			
			Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
					
			query.setQuestionPropMap(questionPropMap);
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			
			query.setPageNo(currentPage);
			query.setPageSize(pageSize);
			
			int count = localExamQuestionFacade.getQuestionSize(query);
			if(count <=0){
				pl.setList(null);
				pl.setFullListSize(0);
			}else{
				query.setPageSize(20);
				pl.setList(localExamQuestionFacade.getQuestionList(query));
				pl.setObjectsPerPage(pageSize);
				pl.setPageNumber(currentPage);
				pl.setFullListSize(count);
			}
			
		} else {
			
			if(qform.getQuestion_label_id() != null){
				if(qform.getQuestion_label_id().intValue() == -1){
					query.setQuestion_label_ids(null);
				}else {
					query.setQuestion_label_ids(qform.getQuestion_label_id().toString());
				}
			}
			
			//排序规则
			if(null!=qform.getOrderItem()){
				query.setOrderItem(qform.getOrderItem());
			}
			
			// 试题状态
			if(qform.getState() != null){
				query.setState(qform.getState());
			}
	
			// 试题难度
			if(qform.getGrade() != null &&  qform.getGrade().intValue() != -1){
				query.setGrade(qform.getGrade());
			}
			// 多媒体试题
			if(qform.getIsnot_multimedia() != null){
				if(qform.getIsnot_multimedia() == -1){
					query.setIsnot_multimedia(null);
				}else{
					query.setIsnot_multimedia(qform.getIsnot_multimedia());
				}
			}		
			// 试题区分度
			if(qform.getDiffer() != null &&  qform.getDiffer().intValue() != -1){
				query.setDiffer(qform.getDiffer());
			}
			// 试题内容
			if(qform.getContent() != null){
				if(qform.getContent().trim().equals("")){
					query.setContent("");
				}else {
					query.setContent(qform.getContent());
				}
			}
			// 创建者
			if(qform.getAuthor() != null){
				if(qform.getAuthor().trim().equals("")){
					query.setAuthor("");
				}else{
					query.setAuthor(qform.getAuthor());
				}
			}
			// 试题来源
			if(qform.getSource() != null){
				if(qform.getSource().trim().equals("")){
					query.setSource("");
				}else{
					query.setSource(qform.getSource());
				}
				
			}
			// 是否含有试题分析
			if(qform.getIsExistAnalyse() != null && qform.getIsExistAnalyse().intValue() != -1){
				query.setIsExistAnalyse(qform.getIsExistAnalyse());
			}
			
			//创建时间
			if(!StringUtils.checkNull(qform.getCreate_date())){
				query.setCreate_date(qform.getCreate_date());
			}
			
			//审核时间
			if(!StringUtils.checkNull(qform.getOnline_date())){
				query.setOnline_date(qform.getOnline_date());
			}
			
			Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
					
			query.setQuestionPropMap(questionPropMap);
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			
			query.setPageNo(currentPage);
			query.setPageSize(pageSize);
			
			int count = localExamQuestionFacade.getQuestionSize(query);
			if(count <=0){
				pl.setList(null);
				pl.setFullListSize(0);
			}else{
				pl.setList(localExamQuestionFacade.getQuestionList(query));
				pl.setObjectsPerPage(pageSize);
				pl.setPageNumber(currentPage);
				pl.setFullListSize(count);
			}
		}
		request.getSession().setAttribute("query",query);
		request.setAttribute("questType", questType);
		request.setAttribute("questPropPoint2", questPropPoint2);
		request.setAttribute("questPropCognize", questPropCognize);
		request.setAttribute("questPropTitle", questPropTitle);		
		request.setAttribute("labelList", questLabel);
		request.setAttribute("questionList", pl);
		
		return "SUCCESS";
	}

}
