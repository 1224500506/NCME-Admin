package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-11-8
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamAdSearchQuestionAction extends BaseAction {
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamPropValFacade localExamPropValFacade;

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


	public ExamQuestionTypeFacade getLocalExamQuestionTypeFacade() {
		return localExamQuestionTypeFacade;
	}


	public void setLocalExamQuestionTypeFacade(
			ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}


	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}


	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String handle = request.getParameter("handle") != null ? request.getParameter("handle"): "jump";
		String propType = request.getParameter("type");
		String id = request.getParameter("id");
		
		if(handle.equals("jump")){
			
			String jsonStr = "";
			
			
			if (propType.equals(Constants.EXAM_PROP_STUDY1) || propType.equals(Constants.EXAM_PROP_STUDY2) || propType.equals(Constants.EXAM_PROP_STUDY3) || propType.equals(Constants.EXAM_PROP_UNIT)) {
				
				List<ExamProp> propList = new ArrayList<ExamProp>();
				
				ExamProp prop = new ExamProp();
				if(propType.equals("1")){
					prop.setType(1);
					propList = localExamPropValFacade.getPropListByType(prop);
				}else{					
					ExamPropQuery query  = new ExamPropQuery();
					query.setSysPropId(Long.valueOf(id));
					ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
					propList =  rprop.getReturnList();
				}
				jsonStr = JSONArray.fromObject(propList).toString();
				
			} else {
				
				Map<String,List<ExamPropVal>> propMap = localExamPropValFacade.getSysPropValBySysId();	
				List<ExamPropVal> propList = new ArrayList<ExamPropVal>();
				
				for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					if(entry.getKey().equals(propType)){
						propList = (List<ExamPropVal>)entry.getValue();
					}
				}
				jsonStr = JSONArray.fromObject(propList).toString();
			}
			
			StrutsUtil.renderText(response, jsonStr);
			
			/*
			
			// 试题题型ID
			List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
			
			//试题分类
			List<ExamQuestionType> questType = localExamQuestionTypeFacade.getQuestionTypeRootBySysId(null);
			
			List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
			List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
			List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
			
			for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
					questPropPoint2 = (List<ExamPropVal>)entry.getValue();
				} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
					questPropCognize = (List<ExamPropVal>)entry.getValue();
				} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
					questPropTitle =(List<ExamPropVal>)entry.getValue();
				}
			}
			
			request.getSession().removeAttribute("ads_query");
			request.setAttribute("questPropPoint2", questPropPoint2);
			request.setAttribute("questPropCognize", questPropCognize);
			request.setAttribute("questPropTitle", questPropTitle);		
			request.setAttribute("questType", questType);
			request.setAttribute("labelList", questLabel);
			
			*/
			return "VIEW";
		}
		
		ExamQuestionQuery query  = (ExamQuestionQuery)request.getSession().getAttribute("ads_query");
		PageList pl = new PageList();
		
		if(query==null){
			
			ExamQuestionForm qform = (ExamQuestionForm)form;
			query  = new ExamQuestionQuery();
			
			if(qform.getQuestion_label_id() != null &&  qform.getQuestion_label_id().intValue() != -1){
				query.setQuestion_label_ids(qform.getQuestion_label_id().toString());
				request.setAttribute("questionLabelId", qform.getQuestion_label_id());
			}
			
			// 试题状态
			if(qform.getState() == null || qform.getState() == -1){
				query.setState(-1);
				request.setAttribute("state", qform.getState());
			}else{
				query.setState(qform.getState());
				request.setAttribute("state", qform.getState());			
			}
			
			//排序规则
			if(null!=qform.getOrderItem()){
				query.setOrderItem(qform.getOrderItem());
			}
			
	
			// 试题难度
			if(qform.getGrade() != null &&  qform.getGrade().intValue() != -1){
				query.setGrade(qform.getGrade());
			}
			// 试题区分度
			if(qform.getDiffer() != null &&  qform.getDiffer().intValue() != -1){
				query.setDiffer(qform.getDiffer());
			}
			// 试题内容
			if(qform.getContent() != null && !qform.getContent().trim().equals("")){
				query.setContent(qform.getContent());
				request.setAttribute("content", qform.getContent());
			}
			// 创建者
			if(qform.getAuthor() != null && !qform.getAuthor().trim().equals("")){
				query.setAuthor(qform.getAuthor());
				request.setAttribute("author", qform.getAuthor());
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
			
			if(qform.getQuestType() !=null){
				String types = "";
				for(Long type : qform.getQuestType()){
					if(types.equals("")){
						types+=type.toString();
					}else{
						types+=","+type;
					}
				}
				query.setSubTypeIds(types);
			}
			
			
			//属性处理
			Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
			String renderSelIds = qform.getRenderSelIds();
			
			List<ExamQuestionProp> list1 = new LinkedList<ExamQuestionProp>();
			List<ExamQuestionProp> list2 = new LinkedList<ExamQuestionProp>();
			List<ExamQuestionProp> list3 = new LinkedList<ExamQuestionProp>();
			List<ExamQuestionProp> list4 = new LinkedList<ExamQuestionProp>();
			List<ExamQuestionProp> list5 = new LinkedList<ExamQuestionProp>();
			
			if(!StringUtils.checkNull(renderSelIds)){
				String[] array = renderSelIds.split("\\+");
				for (int i = 0; i < array.length; ++i) {
					String[] propValId = array[i].split("\\-");
					
					for (int j = 0; j < propValId.length; j++) {
						ExamQuestionProp eqp = new ExamQuestionProp();
						eqp.setProp_val_id(Long.valueOf(propValId[j]));
						switch (j) {
						case 0:
							if(!list1.contains(eqp)) {
								list1.add(eqp);
							}
							break;
						case 1:
							if(!list2.contains(eqp)) {
								list2.add(eqp);
							}
							break;
						case 2:
							if(!list3.contains(eqp)) {
								list3.add(eqp);
							}
							break;
						case 3:
							if(!list4.contains(eqp)) {
								list4.add(eqp);
							}
							break;
						case 4:
							if(!list5.contains(eqp)) {
								list5.add(eqp);
							}
							break;
						}
					}
				}
				
				questionPropMap.put("1", list1);
				questionPropMap.put("2", list2);
				questionPropMap.put("3", list3);
				questionPropMap.put("4", list4);
				questionPropMap.put("5", list5);
			}
			
			setPropVal(qform.getQuestPoint2_7(),Constants.EXAM_PROP_POINT2,questionPropMap);
			setPropVal(qform.getQuestCognize_8(),Constants.EXAM_PROP_COGNIZE,questionPropMap);
			setPropVal(qform.getQuestTitle_9(),Constants.EXAM_PROP_TITLE,questionPropMap);
			
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
		} else {
			
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
		

		
		request.setAttribute("questionList", pl);
		//request.getSession().setAttribute("ads_query",query);
		return "SUCCESS";
	}

	
	private void setPropVal(Long[] propVal,String propKey,Map<String,List<ExamQuestionProp>> questPropMap){
		if(propVal!=null){
			List<ExamQuestionProp> list = new ArrayList<ExamQuestionProp>();
			for(int i=0;i<propVal.length;i++){
				ExamQuestionProp prop = new ExamQuestionProp();
				prop.setProp_val_id(propVal[i]);
				list.add(prop);
			}
			questPropMap.put(propKey, list);
		}
	}

}
