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
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.StrutsUtil;
import com.hys.auth.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.MaterialInfo;
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
 * 作者：Tony 2010-10-19
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionManageAction extends BaseAction {

	
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


	//题库管理
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String handle = request.getParameter("handle") != null ? request.getParameter("handle"): "";
		String propType = request.getParameter("type") != null ? request.getParameter("type") : "";
		String id = request.getParameter("id");
		
		
		//查看题库属性
		if(handle.equals("jump")){
			
			String jsonStr = "";
			
			if (propType.equals(Constants.EXAM_PROP_STUDY1) || propType.equals(Constants.EXAM_PROP_STUDY2) || propType.equals(Constants.EXAM_PROP_STUDY3) || propType.equals(Constants.EXAM_PROP_UNIT) || propType.equals(Constants.EXAM_PROP_POINT)) {
				
				List<ExamProp> propList = new ArrayList<ExamProp>();
				
				ExamProp prop = new ExamProp();
				if(propType.equals("1")){
					prop.setType(1);
					propList = localExamPropValFacade.getPropListByType(prop);
				}else{					
					ExamPropQuery query  = new ExamPropQuery();
					
					String[] ids = id.split(", ");
					for (int i=0; i<ids.length; i++) {
						query.setSysPropId(Long.valueOf(ids[i]));
						ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
						for (int k=0; k<rprop.getReturnList().size(); k++)
							propList.add(rprop.getReturnList().get(k));
					}
				}
				jsonStr = JSONArray.fromObject(propList).toString();
				
			} else if (propType.equals(Constants.EXAM_PROP_SOURCE)) {
				
				ExamSource prop = new ExamSource();
				//prop.setPropIds(id);
				
				List<ExamSource> propList = localExamPropValFacade.getSourceValList(prop);
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
			return "VIEW";
		}
		
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		//试题分类
		List<ExamQuestionType> questType = localExamQuestionTypeFacade.getQuestionTypeRootBySysId(null);
		
		String type = request.getParameter("type") != null ? request.getParameter("type"): "";
		if(type.equals("clear")){
			request.getSession().removeAttribute("query");
		}
		
		ExamQuestionForm qform = (ExamQuestionForm)form;
		
		//ExamQuestionQuery query  = (ExamQuestionQuery)request.getSession().getAttribute("query");
		ExamQuestionQuery query  = new ExamQuestionQuery();
		
/*		String orderBy = request.getParameter("orderBy")!=null ? request.getParameter("orderBy") : "3";
		if (!orderBy.equals("")) {
			Integer[] orderItems = {Integer.parseInt(orderBy)};
			qform.setOrderItem(orderItems);
		}
*/		
		String question_label_name = request.getParameter("question_label_name")!=null ? request.getParameter("question_label_name") : "";
		if (!question_label_name.equals("") && !question_label_name.equals("请选择")) {
			for (int i=0; i<questLabel.size(); i++) {
				if (questLabel.get(i).getName().equals(question_label_name)) {
					qform.setQuestion_label_id(questLabel.get(i).getId());
					break;
				}
			}
		}
		
		String state = request.getParameter("state")!=null ? request.getParameter("state") : "";
		if (!state.equals("")) {
			String[] stateLabel = {"请选择", "未审核", "合格", "不合格", "禁用"};
			for (int i=0; i<stateLabel.length; i++) {
				if (state.equals(stateLabel[i])) {
					if (i==0) 	qform.setState(-1);
					else	qform.setState(i);
					break;
				}
			}
		}
		
		String isMedia = request.getParameter("isMedia")!=null ? request.getParameter("isMedia") : "";
		if (!isMedia.equals("")) {
			String[] mediaState = {"请选择", "不", "是"};
			for (int i=0; i<mediaState.length; i++){
				if (mediaState[i].equals(isMedia)) {
					qform.setIsnot_multimedia(i-1);
					break;
				}
			}
		}
		
		String content = request.getParameter("contentParam")!=null ? request.getParameter("contentParam") : "";
		if (!content.equals("")) {
			qform.setContent(content);
		}
		
		String author = request.getParameter("authorParam")!=null ? request.getParameter("authorParam") : "";
		if (!author.equals("")) {
			qform.setAuthor(author);
		}
		
		String create_date1 = request.getParameter("create_date1")!=null ? request.getParameter("create_date1") : "";
		if (!create_date1.equals("")) {
			qform.setCreate_date(create_date1);
		}
		
		String create_date2 = request.getParameter("create_date3")!=null ? request.getParameter("create_date3") : "";
		if (!create_date2.equals("")) {
			qform.setCreate_date2(create_date2);
		}
		
		String online_date1 = request.getParameter("online_date1")!=null ? request.getParameter("online_date1") : "";
		if (!online_date1.equals("")) {
			qform.setOnline_date(online_date1);
		}
		
		String online_date2 = request.getParameter("online_date3")!=null ? request.getParameter("online_date3") : "";
		if (!online_date2.equals("")) {
			qform.setOnline_date2(online_date2);
		}
		
		String course = request.getParameter("propIds")!=null ? request.getParameter("propIds") : "";
		if (!course.equals("")) {
			String[] courseIds = course.split(",");
			Long[] ids = new Long[courseIds.length];
			for (int i=0; i<courseIds.length; i++) ids[i]= Long.valueOf(courseIds[i].trim()); 
			qform.setQuestUnit_4(ids);
		}
			
		String src = request.getParameter("laiIds")!=null ? request.getParameter("laiIds") : "";
		if (!src.equals("")) {
			String[] srcIds = src.split(",");
			Long[] ids = new Long[srcIds.length];
			for (int i=0; i<srcIds.length; i++) ids[i]= Long.valueOf(srcIds[i].trim()); 
			qform.setQuestSource_10(ids);
		}
		
		String subject = request.getParameter("zutiIds")!=null ? request.getParameter("zutiIds") : "";
		if (!subject.equals("")) {
			String[] subjectIds = subject.split(",");
			Long[] ids = new Long[subjectIds.length];
			for (int i=0; i<subjectIds.length; i++) ids[i]= Long.valueOf(subjectIds[i].trim()); 
			qform.setQuestPoint2_7(ids);
		}
		
		String sector = request.getParameter("dutyIds")!=null ? request.getParameter("dutyIds") : "";
		if (!sector.equals("")) {
			String[] sectorIds = sector.split(",");
			Long[] ids = new Long[sectorIds.length];
			for (int i=0; i<sectorIds.length; i++) ids[i]= Long.valueOf(sectorIds[i].trim()); 
			qform.setQuestTitle_9(ids);
		}
		
		Map<String,List<ExamPropVal>> propMap1 = localExamPropValFacade.getSysPropValBySysId();
		List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
		
		for (Iterator iter = propMap1.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
				questPropPoint2 = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
				questPropCognize = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
				questPropTitle =(List<ExamPropVal>)entry.getValue();
			}
		}
		
		String cognize = request.getParameter("cognize")!=null ? request.getParameter("cognize") : "";
		if (!cognize.equals("") && !cognize.equals("请选择")) {
			for (int i=0; i<questPropCognize.size(); i++) {
				if (questPropCognize.get(i).getName().equals(cognize)) {
					 Long[] ids = new Long[1];
					 ids[0] = questPropCognize.get(i).getId();
					 qform.setQuestCognize_8(ids);
					 break;
				}
			}
		}
		
		PageList pl = new PageList();
		
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
			if(qform.getChecker() != null && !qform.getChecker().trim().equals("")){
				query.setChecker(qform.getChecker());
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
			if(!StringUtils.checkNull(qform.getCreate_date2())){
				query.setCreate_date2(qform.getCreate_date2());
			}
			
			//审核时间
			if(!StringUtils.checkNull(qform.getOnline_date())){
				query.setOnline_date(qform.getOnline_date());
			}			
			if(!StringUtils.checkNull(qform.getOnline_date2())){
				query.setOnline_date2(qform.getOnline_date2());
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
				List<ExamQuestion> qList = localExamQuestionFacade.getQuestionList(query);
				for (ExamQuestion questionInfo :qList) {
					questionInfo.setOnline_date(Utils.dateFormat(Utils.str2Date(questionInfo.getOnline_date(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm"));
					questionInfo.setCreate_date(Utils.dateFormat(Utils.str2Date(questionInfo.getCreate_date(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm"));
				}
				query.setPageSize(20);
				pl.setList(qList);
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
			
			if(qform.getChecker() != null){
				if(qform.getChecker().trim().equals("")){
					query.setChecker("");
				}else{
					query.setChecker(qform.getChecker());
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
			if(!StringUtils.checkNull(qform.getCreate_date2())){
				query.setCreate_date2(qform.getCreate_date2());
			}			
			
			//审核时间
			if(!StringUtils.checkNull(qform.getOnline_date())){
				query.setOnline_date(qform.getOnline_date());
			}
			if(!StringUtils.checkNull(qform.getOnline_date2())){
				query.setOnline_date2(qform.getOnline_date2());
			}			

			if(qform.getQuestType() !=null){
				String types = "";
				for(Long iType : qform.getQuestType()){
					if(types.equals("")){
						types += iType.toString();
					}else{
						types += "," + iType;
					}
				}
				query.setSubTypeIds(types);
			}
			
			//属性处理
			Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
			
			setPropVal(qform.getQuestUnit_4(), Constants.EXAM_PROP_UNIT, questionPropMap);
			setPropVal(qform.getQuestPoint_5(), Constants.EXAM_PROP_POINT, questionPropMap);
			setPropVal(qform.getQuestPoint2_7(),Constants.EXAM_PROP_POINT2,questionPropMap);
			setPropVal(qform.getQuestCognize_8(),Constants.EXAM_PROP_COGNIZE,questionPropMap);
			setPropVal(qform.getQuestTitle_9(), Constants.EXAM_PROP_TITLE,questionPropMap);
			setPropVal(qform.getQuestSource_10(), Constants.EXAM_PROP_SOURCE,questionPropMap);
			
			query.setQuestionPropMap(questionPropMap);
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);

			String sort = request.getParameter("sort")!=null ? request.getParameter("sort") : "";
			query.setSort(sort);
			String dir = request.getParameter("dir")!=null ? request.getParameter("dir") : "";
			query.setDir(dir);
			pl.setSortCriterion(sort);
			if (dir.equals("asc"))
				pl.setSortDirection(SortOrderEnum.ASCENDING);
			else
				pl.setSortDirection(SortOrderEnum.DESCENDING);
			
			query.setPageNo(currentPage);
			query.setPageSize(pageSize);
			
			int count = localExamQuestionFacade.getQuestionSize(query);
			if(count <=0){
				pl.setList(null);
				pl.setFullListSize(0);
			}else{				
				List<ExamQuestion> qList = localExamQuestionFacade.getQuestionList(query);
				for (ExamQuestion questionInfo :qList) {
					questionInfo.setOnline_date(Utils.dateFormat(Utils.str2Date(questionInfo.getOnline_date(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm"));
					questionInfo.setCreate_date(Utils.dateFormat(Utils.str2Date(questionInfo.getCreate_date(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm"));
				}
				query.setPageSize(20);
				pl.setList(qList);
				pl.setObjectsPerPage(pageSize);
				pl.setPageNumber(currentPage);
				pl.setFullListSize(count);
			}
		}
		request.getSession().setAttribute("query",query);
		request.setAttribute("questType", questType);
		request.setAttribute("cognizeList", questPropCognize);		
		request.setAttribute("labelList", questLabel);
		request.setAttribute("questionList", pl);
		
		request.setAttribute("question_label_name", question_label_name);
		request.setAttribute("state", state);
		request.setAttribute("isMedia", isMedia);
		request.setAttribute("propIds", course);
		request.setAttribute("propNames", request.getParameter("propNames"));
		request.setAttribute("ICD", request.getParameter("ICD"));
		request.setAttribute("zutiIds", subject);
		request.setAttribute("zutiNames", request.getParameter("zutiNames"));
		/*request.setAttribute("laiIds", sector);
		request.setAttribute("laiNames", request.getParameter("laiNames"));*/
//		request.setAttribute("orderBy", orderBy);
		
		if (!handle.equals("")) return handle;
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
