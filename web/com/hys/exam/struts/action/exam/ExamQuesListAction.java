package com.hys.exam.struts.action.exam;


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

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.common.util.QuesCountUtils;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.exam.struts.form.QuestionForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuesListAction extends BaseAction {

	private ExamPaperFacade examPaperFacade ;
	
	private ExamPropValFacade examPropValFacade ;
	
	private ExamQuestionFacade examQuestionFacade ;
	
	private ExamQuestionTypeFacade examQuestionTypeFacade ;
	
	private ExamQuestionLabelFacade examQuestionLabelFacade ;
	
	private ExamPropValFacade localExamPropValFacade;
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String method = ParamUtils.getParameter(request, "method", null) ;

		if(method.equals("quesPorp")){
			String propType = ParamUtils.getParameter(request, "propType", null);
			Map<String,List<ExamPropVal>> propMap = examPropValFacade.getSysPropValBySysId();
			
			List<ExamPropVal> propVal = new ArrayList<ExamPropVal>();
			propVal = propMap.get(propType);
			
			List<ExamPropValTemp> list = new ArrayList<ExamPropValTemp>();
			ExamPropValTemp temp = null;
			for (Iterator<ExamPropVal> iterator = propVal.iterator(); iterator.hasNext();) {
				ExamPropVal name = iterator.next();
				temp = new ExamPropValTemp();
				temp.setId(name.getId().toString());
				temp.setName(name.getName());
				list.add(temp);
			}
			
			String jsonstr = "";
			jsonstr = JSONArray.fromObject(list).toString();
			Utils.renderText(response, jsonstr);
			return null;
		}
		
		if(method.equals("quesLable")){
			String questType_2 = request.getParameter("questType_2");
			String renderSelIds = request.getParameter("renderSelIds");
			String questCognize_8_2 = request.getParameter("questCognize_8_2");
			String questTitle_9 = request.getParameter("questTitle_9");
			
			String flag = request.getParameter("flag");
			
			
			ExamPaperFasterTactic pft = new ExamPaperFasterTactic();
			
			
			if(null != questType_2 && !"".equals(questType_2)){
				pft.setQuestion_type_id(questType_2);
			}
			List<ExamPaperFasterTactic3> pft3List = new ArrayList<ExamPaperFasterTactic3>();
			if(null != questCognize_8_2 && !"".equals(questCognize_8_2)){
				String[] array = questCognize_8_2.split("\\,");
				for (int i = 0; i < array.length; ++i) {
					ExamPaperFasterTactic3 pft3 = new ExamPaperFasterTactic3();
					pft3.setCognize_id(Long.valueOf(array[i]));
					pft3List.add(pft3);
				}
			}
			if(null != questTitle_9 && !"".equals(questTitle_9)){
				String[] array = questTitle_9.split("\\,");
				for (int i = 0; i < array.length; ++i) {
					ExamPaperFasterTactic3 pft3 = new ExamPaperFasterTactic3();
					pft3.setTitle_id(Long.valueOf(array[i]));
					pft3List.add(pft3);
				}
			}
			pft.setExamPaperFasterTactic3(pft3List);
			
			List<ExamPaperFasterTactic1> pft1List = new ArrayList<ExamPaperFasterTactic1>();
			
			List<ExamPaperFasterTactic2> pft2List = new ArrayList<ExamPaperFasterTactic2>();
			
			if(null != renderSelIds && !"".equals(renderSelIds)){
				String[] array = renderSelIds.split("\\,");
				for (int i = 0; i < array.length; ++i) {
					ExamPaperFasterTactic2 pft2 = new ExamPaperFasterTactic2();
					String[] propValId = array[i].split("\\-");
					
					for (int j = 0; j < propValId.length; j++) {
						Long pvId = Long.valueOf(propValId[j]);
						switch (j) {
						case 0:
							pft2.setStudy1_id(pvId);
							break;
						case 1:
							pft2.setStudy2_id(pvId);
							break;
						case 2:
							pft2.setStudy3_id(pvId);
							break;
						case 3:
							pft2.setUnit_id(pvId);
							break;
						case 4:
							pft2.setPoint_id(pvId);
							break;
						}
					}
					pft2List.add(pft2);
				}
				
				pft.setExamPaperFasterTactic2(pft2List);
			}
			
			List<ExamQuestionLabel> questLabel = examQuestionLabelFacade.getQuestionLabel(1);
			for (Iterator<ExamQuestionLabel> iterator = questLabel.iterator(); iterator.hasNext();) {
				ExamQuestionLabel ql = iterator.next();
				ExamPaperFasterTactic1 pft1 = new ExamPaperFasterTactic1();
				pft1.setQuestion_label_id(ql.getId());
				
				pft1List.add(pft1);
			}
			
			pft.setExamPaperFasterTactic1(pft1List);
			if(null == flag){
				pft = examQuestionFacade.getQuestionSizeByFasterTactic(pft);
			}
			
			pft1List = pft.getExamPaperFasterTactic1();
			
			List<ExamPropValTemp> list = new ArrayList<ExamPropValTemp>();
			ExamPropValTemp temp = null;
			for (Iterator<ExamQuestionLabel> iterator = questLabel.iterator(); iterator.hasNext();) {
				ExamQuestionLabel name = iterator.next();
				temp = new ExamPropValTemp();
				for (Iterator<ExamPaperFasterTactic1> iterator2 = pft1List.iterator(); iterator2.hasNext();) {
					ExamPaperFasterTactic1 pft1 = iterator2.next();
					if(pft1.getQuestion_label_id().equals(name.getId())){
						temp.setQuesCount(pft1.getNum());
						break;
					}
				}
				temp.setId(name.getId().toString());
				temp.setName(name.getName());
				list.add(temp);
			}
			
			String jsonstr = "";
			jsonstr = JSONArray.fromObject(list).toString();
			//System.out.println(jsonstr);
			Utils.renderText(response, jsonstr);
			return null;
		}
		//取行业字段
		if(method.equals("getLoc")){
			List<ExamPropVal> list= localExamPropValFacade.getSysPropValBySysId().get("7");
			List<ExamPropValTemp> list_temp = new ArrayList<ExamPropValTemp>(); 
			for(ExamPropVal v : list){
				ExamPropValTemp a = new ExamPropValTemp();
				a.setId(v.getId().toString());
				a.setName(v.getName());
				list_temp.add(a);
			}
			Utils.renderText(response, JSONArray.fromObject(list_temp).toString());
			return null;
		}
		if(method.equals("countQues")){//计算题量
			String questType_2 = request.getParameter("questType_2");
			String renderSelIds = request.getParameter("renderSelIds");
			String questCognize_8_2 = request.getParameter("questCognize_8_2");
			String questTitle_9 = request.getParameter("questTitle_9");
			String question_label_ids = request.getParameter("question_label_ids");
			String flag = request.getParameter("flag");
			
			ExamPaper paper = new ExamPaper();
			
			ExamPaperFasterTactic pft = new ExamPaperFasterTactic();
				
			if(null != questType_2 && !"".equals(questType_2)){
				pft.setQuestion_type_id(questType_2);
			}
			List<ExamPaperFasterTactic3> pft3List = new ArrayList<ExamPaperFasterTactic3>();
			if(null != questCognize_8_2 && !"".equals(questCognize_8_2)){
				String[] array = questCognize_8_2.split("\\,");
				for (int i = 0; i < array.length; ++i) {
					ExamPaperFasterTactic3 pft3 = new ExamPaperFasterTactic3();
					pft3.setCognize_id(Long.valueOf(array[i]));
					pft3List.add(pft3);
				}
			}
			if(null != questTitle_9 && !"".equals(questTitle_9)){
				String[] array = questTitle_9.split("\\,");
				for (int i = 0; i < array.length; ++i) {
					ExamPaperFasterTactic3 pft3 = new ExamPaperFasterTactic3();
					pft3.setTitle_id(Long.valueOf(array[i]));
					pft3List.add(pft3);
				}
			}
			pft.setExamPaperFasterTactic3(pft3List);
			
			List<ExamPaperFasterTactic1> pft1List = new ArrayList<ExamPaperFasterTactic1>();
			
			List<ExamPaperFasterTactic2> pft2List = new ArrayList<ExamPaperFasterTactic2>();
			Map<String,Integer> shux = new HashMap<String,Integer>();
			if(null != renderSelIds && !"".equals(renderSelIds)){
				String[] array = renderSelIds.split("\\,");
				for (int i = 0; i < array.length; ++i) {
					ExamPaperFasterTactic2 pft2 = new ExamPaperFasterTactic2();
					shux.put(array[i], 0);
					String[] propValId = array[i].split("\\-");
					
					for (int j = 0; j < propValId.length; j++) {
						Long pvId = Long.valueOf(propValId[j]);
						switch (j) {
						case 0:
							pft2.setStudy1_id(pvId);
							break;
						case 1:
							pft2.setStudy2_id(pvId);
							break;
						case 2:
							pft2.setStudy3_id(pvId);
							break;
						case 3:
							pft2.setUnit_id(pvId);
							break;
						case 4:
							pft2.setPoint_id(pvId);
							break;
						}
					}
					pft2List.add(pft2);
				}
				
				pft.setExamPaperFasterTactic2(pft2List);
			}
			
			List<ExamQuestionLabel> questLabels = examQuestionLabelFacade.getQuestionLabel(1);
			Map<Integer,String> lables = new HashMap<Integer,String>();
			for (Iterator<ExamQuestionLabel> iterator = questLabels.iterator(); iterator.hasNext();) {
				ExamQuestionLabel ql = iterator.next();
				lables.put(ql.getId(), ql.getName());
			}
			
			if(!"".equals(question_label_ids)){
				String [] quesLables = question_label_ids.split("\\,");
				for (int i = 0; i < quesLables.length; i++) {
					ExamPaperFasterTactic1 pft1 = new ExamPaperFasterTactic1();
					String [] quesLable = quesLables[i].split("\\_");
					
					Integer qLable = NumberUtil.parseInteger(quesLable[0], 0) ;
					Integer qNum = NumberUtil.parseInteger(quesLable[1], 0) ;
					Double qScore = NumberUtil.parseDouble(quesLable[2]);
					
					pft1.setQuestion_label_id(qLable);
					pft1.setNum(qNum);
					pft1.setScore(qScore);
					
					pft1List.add(pft1);
				}
			}
			
			pft.setExamPaperFasterTactic1(pft1List);
			
			paper.setPaperFasterTactic(pft);
			
			//pft = olemQuesFacade.getQuestionSizeByFasterTactic(pft);
			
			paper = examPaperFacade.getQuestSizeByFasterTactic(paper);
			
			
			if(null != flag && !"".equals(flag)){
				//检查试题数量是否满足
				Map<Integer,Boolean> checkNum = QuesCountUtils.checkQuesSize(paper);
				
				StringBuffer str = new StringBuffer();
				for (Integer name : checkNum.keySet()) {
					if(checkNum.get(name)){
						str.append(lables.get(name));
						str.append("  ");
					}
				}
				
				if(str.length()>0){
					str.append("试题数量不足,请重新选择！");
					Utils.renderText(response, str.toString());
					return null;
				}else {
					Utils.renderText(response, "");
					return null;
				}
			}else {
				
				shux = QuesCountUtils.getQuesSize(paper, shux);			
				String jsonstr = "";
				jsonstr = JSONArray.fromObject(shux).toString();
//				System.out.println(jsonstr);
				Utils.renderText(response, jsonstr);
				return null;
			}
			
		}
		
		if(method.equals("quesTypes")){
			List<ExamQuestionType> qtList = examQuestionTypeFacade.getQuestionTypeRootBySysId(Constants.XSJ_QUESTION_ID);
			List<ExamPropValTemp> list = new ArrayList<ExamPropValTemp>();
			ExamPropValTemp temp2 = null;
			for (Iterator<ExamQuestionType> iterator = qtList.iterator(); iterator.hasNext();) {
				ExamQuestionType name = iterator.next();
				temp2 = new ExamPropValTemp();
				temp2.setId(name.getId().toString());
				temp2.setName(name.getName());
				list.add(temp2);
			}
			
			String jsonstr = "";
			jsonstr = JSONArray.fromObject(list).toString();
			Utils.renderText(response, jsonstr);
			return null;
		}
		
		if(method.equals("quesList")){
			QuestionForm qform = (QuestionForm)form; 
			String sign = request.getParameter("sign") == null ? "false": request.getParameter("sign");
			request.setAttribute("sign", sign);
			
			String sysId = request.getParameter("sysId");
			
			ExamQuestionQuery query = new ExamQuestionQuery();
			boolean isSearch = false;
			
			if(qform.getQuestType_2() !=null && !"".equals(qform.getQuestType_2())){
				isSearch = true;
				if("-1000".equals(qform.getQuestType_2())){
					Integer [] hysQueId = Constants.XSJ_QUESTION_ID ;
					StringBuffer ids = new StringBuffer();
					if(hysQueId.length>0){
						for (int i = 0; i < hysQueId.length; i++) {
							ids.append(hysQueId[i].toString());
							if(i+1 != hysQueId.length){
								ids.append(",");
							}
						}
					}
					query.setSubTypeIds(ids.toString());
				}else {
					query.setSubTypeIds(qform.getQuestType_2());
				}
				query.setQuestion_label_ids(qform.getQuestion_label_ids());
			}
			if(qform.getSubTypeId() != null && !"".equals(qform.getSubTypeId())){
				if(-1000 == qform.getSubTypeId()){
					Integer [] hysQueId = Constants.XSJ_QUESTION_ID ;
					StringBuffer ids = new StringBuffer();
					if(hysQueId.length>0){
						for (int i = 0; i < hysQueId.length; i++) {
							ids.append(hysQueId[i].toString());
							if(i+1 != hysQueId.length){
								ids.append(",");
							}
						}
					}
					query.setSubTypeIds(ids.toString());
				}else {
					query.setSubTypeIds(qform.getSubTypeId().toString());
				}
			}
			
			// 试题内容
			if(qform.getContent() != null && !qform.getContent().trim().equals("")){
				query.setContent(qform.getContent());
				request.setAttribute("content", qform.getContent());
			}
			// 试题难度
			if(qform.getGrade() != null &&  qform.getGrade().intValue() != -1){
				query.setGrade(qform.getGrade());
			}
			// 是否多媒体试题 
			if(qform.getIsnot_multimedia()!= null && qform.getIsnot_multimedia() != 2){
				// 非请选择状态添加结果查询 请选择状态不添加
				query.setIsnot_multimedia(qform.getIsnot_multimedia());
			}
			
			query.setState(2);//已审核试题
			query.setQuestionPropMap(setProp(qform));//属性处理
			
			Page<ExamQuestion> page = PageUtil.getPageByRequest(request) ;
			query.setPageNo(page.getCurrentPage());
			query.setPageSize(page.getPageSize());
			
			List<ExamQuestion> questList = examQuestionFacade.getQuestionList(query);
			int totalCount = examQuestionFacade.getQuestionSize(query);
			
			page.setList(questList);
			page.setCount(totalCount);
			
			if(null != sysId && !"".equals(sysId)){
				Integer sId = NumberUtil.parseInteger(sysId);
				if(sId==7){
					request.setAttribute("questionList", page);
				}
			}
			if(isSearch) {
				request.setAttribute("questionList", page);
				request.setAttribute("search", "true");
			}
			
			request.setAttribute("quesnums", page.getCount());
			request.setAttribute("orgQuesTypeId", Constants.XSJ_QUESTION_ID);
			request.setAttribute("subTypeId", qform.getSubTypeId());
			request.setAttribute("qform", qform);
			return "quesList";
		}
		
		//手工选题
		if(method.equals("quesListHand")){
			String flag = request.getParameter("flag");
			QuestionForm qform = (QuestionForm)form; 
			
			String seleQues = ParamUtils.getParameter(request, "seleQues");
			
			ExamQuestionQuery query = new ExamQuestionQuery();
			boolean isSearch = false;
			
			if(qform.getQuestType_2() !=null && !"".equals(qform.getQuestType_2())){
				isSearch = true;
				query.setSubTypeIds(qform.getQuestType_2().replace("+", ","));
				//query.setQuestion_label_id(qform.getQuestion_label_id());
				query.setQuestion_label_ids(qform.getQuestion_label_id().toString());
			}
			
			if(isSearch) {
				// 试题内容
				if(qform.getContent() != null && !qform.getContent().trim().equals("")){
					query.setContent(qform.getContent());
					request.setAttribute("content", qform.getContent());
				}
				// 试题难度
				if(qform.getGrade() != null &&  qform.getGrade().intValue() != -1){
					query.setGrade(qform.getGrade());
				}
				
				query.setState(2);//已审核试题
				query.setQuestionPropMap(setProp(qform));//属性处理
				
				//过滤已选试题
				if(!"".equals(seleQues)) {
					
				}
				
				Page<ExamQuestion> page = PageUtil.getPageByRequest(request) ;
				query.setPageNo(page.getCurrentPage());
				query.setPageSize(page.getPageSize());
				
				List<ExamQuestion> questList = examQuestionFacade.getQuestionList(query);
				int totalCount = examQuestionFacade.getQuestionSize(query);
				
				page.setList(questList);
				page.setCount(totalCount);
				
				request.setAttribute("questionList", page);
			}
			
			request.setAttribute("subTypeId", qform.getSubTypeId());
			request.setAttribute("qform", qform);
			
			if(null == flag){
				return "quesListHand";
			}else {
				Integer qla = ParamUtils.getIntParameter(request, "qLable", 0) ;
				List<ExamQuestionLabel> questLabel = examQuestionLabelFacade.getQuestionLabel(1);
				for (Iterator<ExamQuestionLabel> iterator = questLabel.iterator(); iterator.hasNext();) {
					ExamQuestionLabel ql = iterator.next();
					if(ql.getId().equals(qla)){
						request.setAttribute("qLable", ql);
						break;
					}
				}
				if("true".equals(flag)){
					return "quesListForUpda";
				}else {
					return "quesListForUpda2";
				}
			}
		}
		
		if(method.equals("quesListByIds")){
			String seleQues = ParamUtils.getParameter(request, "seleQues");
			String pName = ParamUtils.getParameter(request, "name");
			Long [] idArr = null;
			Map<Long ,Double> map = new HashMap<Long,Double>();
			if(!"".equals(seleQues)){
				String [] quesStr = seleQues.split("\\_");
				idArr = new Long[quesStr.length];
				for (int i = 0; i < quesStr.length; i++) {
					String str = quesStr[i];
					String [] subStr = str.split("\\+");
					idArr[i] = Long.valueOf((subStr[0]));
					map.put(Long.valueOf((subStr[0])), Double.valueOf((subStr[1])));
				}
			}
			
			List<ExamQuestion> quesList = examQuestionFacade.getQuestionIdByIdArr(idArr);
			for (Iterator<ExamQuestion> iterator = quesList.iterator(); iterator.hasNext();) {
				ExamQuestion q = (ExamQuestion) iterator.next();
				q.setQuestion_score(map.get(q.getId()));
			}
			
			request.setAttribute("name", pName);
			request.setAttribute("result", Constants.RESULT);
			request.setAttribute("quesList", quesList);
			return "quesListByIds";
		}
		
		if(method.equals("quesListForUpda")){
			Integer qla = ParamUtils.getIntParameter(request, "qLable", 0) ;
			List<ExamQuestionLabel> questLabel = examQuestionLabelFacade.getQuestionLabel(1);
			for (Iterator<ExamQuestionLabel> iterator = questLabel.iterator(); iterator.hasNext();) {
				ExamQuestionLabel ql = iterator.next();
				if(ql.getId().equals(qla)){
					request.setAttribute("qLable", ql);
					break;
				}
			}
			
			return "quesListForUpda";
		}
		
		//修改试卷
		if(method.equals("quesListForUpda2")){
			Integer qla = ParamUtils.getIntParameter(request, "qLable", 0) ;
			List<ExamQuestionLabel> questLabel = examQuestionLabelFacade.getQuestionLabel(1);
			for (Iterator<ExamQuestionLabel> iterator = questLabel.iterator(); iterator.hasNext();) {
				ExamQuestionLabel ql = iterator.next();
				if(ql.getId().equals(qla)){
					request.setAttribute("qLable", ql);
					break;
				}
			}
			
			return "quesListForUpda2";
		}
		
		return null;
	}
	
	private Map<String,List<ExamQuestionProp>> setProp(QuestionForm qform) {
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
		String renderSelIds = qform.getRenderSelIds();
		
//		List<ExamQuestionProp> list1 = new LinkedList<ExamQuestionProp>();
//		List<ExamQuestionProp> list2 = new LinkedList<ExamQuestionProp>();
//		List<ExamQuestionProp> list3 = new LinkedList<ExamQuestionProp>();
//		List<ExamQuestionProp> list4 = new LinkedList<ExamQuestionProp>();
//		List<ExamQuestionProp> list5 = new LinkedList<ExamQuestionProp>();
		
/*		if(!StringUtil.checkNull(renderSelIds)){
			String[] array = renderSelIds.split("\\,");
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
*/			
//			questionPropMap.put("1", list1);
//			questionPropMap.put("2", list2);
//			questionPropMap.put("3", list3);
//			questionPropMap.put("4", list4);
//			questionPropMap.put("5", list5);
//		}
		
		//setPropVal(qform.getQuestPoint2_7_2(),Constants.EXAM_PROP_POINT2,questionPropMap);
		setPropVal(qform.getRenderSelIds(),Constants.EXAM_PROP_UNIT,questionPropMap);
//		setPropVal(qform.getQuestPoint2_7_2(),Constants.EXAM_PROP_POINT2,questionPropMap);
		setPropVal(qform.getQuestCognize_8_2(),Constants.EXAM_PROP_COGNIZE,questionPropMap);
		setPropVal(qform.getQuestTitle_9_2(),Constants.EXAM_PROP_TITLE,questionPropMap);
		
		return questionPropMap;
	}
	
	private void setPropVal(String propVal,String propKey,Map<String,List<ExamQuestionProp>> questPropMap){
		if(propVal!=null && !"".equals(propVal)){
			List<ExamQuestionProp> list = new ArrayList<ExamQuestionProp>();
			String[] array = propVal.split("\\,");
			for (int i = 0; i < array.length; ++i) {
				ExamQuestionProp prop = new ExamQuestionProp();
				prop.setProp_val_id(Long.valueOf(array[i]));
				list.add(prop);
				
				if (propKey.equals(Constants.EXAM_PROP_UNIT)){
					ExamPropQuery propQuery  = new ExamPropQuery();
					propQuery.setSysPropId(Long.valueOf(array[i]));
					
					ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(propQuery);
					List<ExamProp> nextPropList = rprop.getReturnList();
					
					if (nextPropList != null && nextPropList.size() >0){
						for (ExamProp curProp : nextPropList){
							ExamQuestionProp tempProp = new ExamQuestionProp();
							tempProp.setProp_val_id(curProp.getId());
							list.add(tempProp);
							
							propQuery.setSysPropId(curProp.getId());
							rprop = localExamPropValFacade.getNextLevelProp(propQuery);
							List<ExamProp> nextNextPropList = rprop.getReturnList();
							if (nextNextPropList != null){
								for (ExamProp nextProp : nextNextPropList){
									ExamQuestionProp temp2Prop = new ExamQuestionProp();
									temp2Prop.setProp_val_id(nextProp.getId());
									list.add(temp2Prop);
								}
							}						
						}
					}
				}
			}
			
			questPropMap.put(propKey, list);
		}
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
	
	public void setExamPropValFacade(ExamPropValFacade examPropValFacade) {
		this.examPropValFacade = examPropValFacade;
	}

	public void setExamQuestionFacade(ExamQuestionFacade examQuestionFacade) {
		this.examQuestionFacade = examQuestionFacade;
	}

	public void setExamQuestionTypeFacade(ExamQuestionTypeFacade examQuestionTypeFacade) {
		this.examQuestionTypeFacade = examQuestionTypeFacade;
	}
	
	public void setExamQuestionLabelFacade(ExamQuestionLabelFacade examQuestionLabelFacade) {
		this.examQuestionLabelFacade = examQuestionLabelFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
}
