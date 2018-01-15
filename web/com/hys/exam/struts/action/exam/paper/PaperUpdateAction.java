package com.hys.exam.struts.action.exam.paper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.googlecode.jsonplugin.JSONUtil;
import com.hys.auth.util.JsonUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.common.util.QuesCountUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamPaperQuestion;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.struts.form.OlemPaperForm;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class PaperUpdateAction extends BaseAction {

	private ExamPaperFacade examPaperFacade;

	private ExamQuestionLabelFacade examQuestionLabelFacade;
	
	private ExamPropValFacade localExamPropValFacade;
	
	private ExamPaperTypeFacade examPaperTypeFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	public ExamPaperTypeFacade getExamPaperTypeFacade() {
		return examPaperTypeFacade;
	}

	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}

	public ExamPaperFacade getExamPaperFacade() {
		return examPaperFacade;
	}

	public ExamQuestionLabelFacade getExamQuestionLabelFacade() {
		return examQuestionLabelFacade;
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}

	public void setExamQuestionLabelFacade(ExamQuestionLabelFacade examQuestionLabelFacade) {
		this.examQuestionLabelFacade = examQuestionLabelFacade;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}

	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}


	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String method = request.getParameter("method");
		String paperMode = request.getParameter("mode");
		String paperId = request.getParameter("paperId");
		
		if(!StringUtils.checkNull(method) && method.equals("update")) {
			return paperUpdate(mapping, form, request, response);
		}
		if (!StringUtils.checkNull(paperId)) {
			if (!StringUtils.checkNull("paperMode")) {
				return getPaperInfomationById(paperMode, paperId, request, form);
			}
		}
		return null;
	}

	private String getPaperInfomationById(String paperMode, String paperId, HttpServletRequest request, ActionForm form) {
		
		ExamPaper paper = examPaperFacade.getExamPaperById(Long.parseLong(paperId));
		
		ExamPaperType  paperType = examPaperTypeFacade.getExamPaperTypeById(paper.getType_id());
		
		ExamQuestionQuery query  = new ExamQuestionQuery();
		
		//属性处理
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
		
		if (paper.getExamQuestionList() != null) {
			
			List<ExamQuestion> quesList = paper.getExamQuestionList();
			
			request.setAttribute("paper", paper);
			JSONObject result = new JSONObject();
			result.put("list", quesList);
			request.setAttribute("quesList", result);
		}
		
		/**
		 * Get exam paper faster tactic.
		 * Add by Tiger.
		 */
		if (paper.getPaperFasterTactic() != null) {
			
			//Get tactic prop ids and names.
			String strPropIds = "";
			String strPropNames = "";
			List<Long> propIdList = new ArrayList<Long>();
			List<Long> sourceIdList = new ArrayList<Long>();
			List<Long> cognizeIdList = new ArrayList<Long>();
			
			
			for (ExamPaperFasterTactic2 tactic2 : paper.getPaperFasterTactic().getExamPaperFasterTactic2()) {
				if(tactic2 != null && tactic2.getUnit_id() != null) {
					//Get prop ids and names.
					strPropIds += tactic2.getUnit_id().toString() + ",";
					ExamProp prop = localExamPropValFacade.getExamPropValByPropId(tactic2.getUnit_id());
					tactic2.setUnit_name(prop.getName());
					strPropNames += prop.getName() + ",";
					
					//Make prop id list.
					propIdList.add(tactic2.getUnit_id());
					
					//Get  the next prop list from selected id. 
					ExamPropQuery propQuery  = new ExamPropQuery();
					propQuery.setSysPropId(tactic2.getUnit_id());
					
					ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(propQuery);
					List<ExamProp> nextPropList = rprop.getReturnList();
					
					if (nextPropList != null && nextPropList.size() >0){
						for (ExamProp secondProp : nextPropList){
							propIdList.add(secondProp.getId());
							propQuery.setSysPropId(secondProp.getId());
							rprop = localExamPropValFacade.getNextLevelProp(propQuery);
							List<ExamProp> nextNextPropList = rprop.getReturnList();
							if (nextNextPropList != null){
								for (ExamProp nextProp : nextNextPropList){
									propIdList.add(nextProp.getId());
								}
							}						
						}
					}
				}
			}
			if (strPropIds.length() > 0) {
				strPropIds = strPropIds.substring(0, strPropIds.length() - 1);
			}
			if (strPropNames.length() > 0) {
				strPropNames = strPropNames.substring(0, strPropNames.length() - 1);
			}
			
			String cognizeIds = "";
			String cognizeNames = "";
			//Get the cognize_id and cognize_name.
			for (ExamPaperFasterTactic3 tactic3 : paper.getPaperFasterTactic().getExamPaperFasterTactic3()) {
				if(tactic3 != null && tactic3.getCognize_id() != null) {
					cognizeIds +=  tactic3.getCognize_id().toString() + ",";
					ExamProp prop = localExamPropValFacade.getExamPropValByPropId( tactic3.getCognize_id());
					cognizeNames += prop.getName() + ",";
					
					//Make cognize id list.
					cognizeIdList.add(tactic3.getCognize_id());
				}
			}
			if (cognizeIds.length() > 0) {
				cognizeIds = cognizeIds.substring(0, cognizeIds.length() - 1);
			}
			if (cognizeNames.length() > 0) {
				cognizeNames = cognizeNames.substring(0, cognizeNames.length() - 1);
			}
			
			String sourceIds = "";
			String sourceNames = "";
			//Get the source_id and source_name.
			for (ExamPaperFasterTactic3 tactic4 : paper.getPaperFasterTactic().getExamPaperFasterTactic4()) {
				if(tactic4 != null && tactic4.getSourceId() != null) {
					sourceIds +=  tactic4.getSourceId().toString() + ",";
					ExamSource querySource = new ExamSource();
					querySource.setId(tactic4.getSourceId());
					
					List<ExamSource> sList = localExamPropValFacade.getSourceValList(querySource);
					if (sList != null && sList.size() > 0) {
						ExamSource  source = sList.get(0);
						sourceNames += source.getName() + ",";
						
						//Make source id list.
						sourceIdList.add(tactic4.getSourceId());
					}
				}
			}
			if (sourceIds.length() > 0) {
				sourceIds = sourceIds.substring(0, sourceIds.length() - 1);
			}
			if (sourceNames.length() > 0) {
				sourceNames = sourceNames.substring(0, sourceNames.length() - 1);
			}
					
			Long[] pIds = new Long[propIdList.size()];
			Long[] sIds = new Long[sourceIdList.size()];
			Long[] cIds = new Long[cognizeIdList.size()];
			propIdList.toArray(pIds);
			sourceIdList.toArray(sIds);
			cognizeIdList.toArray(cIds);
			setPropVal(pIds, Constants.EXAM_PROP_UNIT, questionPropMap);
			setPropVal(cIds, Constants.EXAM_PROP_COGNIZE,questionPropMap);
			setPropVal(sIds, Constants.EXAM_PROP_SOURCE,questionPropMap);
			
			//2:审核合格
			query.setState(2);
			// 试题难度
			if(paper.getGrade() != null &&  paper.getGrade().intValue() != -1){
				query.setGrade(paper.getGrade());
			}
			query.setQuestionPropMap(questionPropMap);
			
			if (!paper.getPaper_mode().equals(Constants.PAPER_MODE_JZJ)) { // Step over if 卷中卷 : 4
				for(ExamPaperFasterTactic1 tactic1 : paper.getPaperFasterTactic().getExamPaperFasterTactic1())
				{
					//Get question size.
					query.setQuestion_label_id(tactic1.getQuestion_label_id());
					int count = localExamQuestionFacade.getQuestionSize(query);
					tactic1.setLabelQuestionNum(count);
				}
			}
			
			JSONObject jsonPropObj = new JSONObject();	
			for (ExamPaperFasterTactic2 tactic2 : paper.getPaperFasterTactic().getExamPaperFasterTactic2()) {
				if(tactic2 != null && tactic2.getUnit_id() != null) {
					Map<String,List<ExamQuestionProp>> PropMap = new HashMap<String, List<ExamQuestionProp>>();
					
					//Get prop questions.
					List<Long> propList = new ArrayList<Long>();
					Long courseId = tactic2.getUnit_id();
					propList.add(courseId);
					
					
					ExamPropQuery propQuery  = new ExamPropQuery();
					propQuery.setSysPropId(Long.valueOf(courseId));
					
					ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(propQuery);
					List<ExamProp> nextPropList = rprop.getReturnList();
					
					if (nextPropList != null && nextPropList.size() >0){
						for (ExamProp prop : nextPropList){
							propList.add(prop.getId());
							propQuery.setSysPropId(prop.getId());
							rprop = localExamPropValFacade.getNextLevelProp(propQuery);
							List<ExamProp> nextNextPropList = rprop.getReturnList();
							if (nextNextPropList != null){
								for (ExamProp nextProp : nextNextPropList){
									propList.add(nextProp.getId());
								}
							}						
						}
					}				
					Long[] tempList = new Long[propList.size()];
					propList.toArray(tempList);
					setPropVal(tempList, Constants.EXAM_PROP_UNIT, PropMap);
					query.setQuestion_label_id(null);
					query.setQuestionPropMap(PropMap);
					int count = localExamQuestionFacade.getQuestionSize(query);
					jsonPropObj.put(courseId, count);
				}
			}

			List<ExamPaper> ref_paperList = new ArrayList<ExamPaper>();
			if (paper.getPaperIdArr() != null && paper.getPaperIdArr().length >0) {
			
				for (long id : paper.getPaperIdArr()) {
					ExamPaper ref_paper = examPaperFacade.getExamPaperById(id);
					ref_paperList.add(ref_paper);
				}
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("paper", paper);
			jsonObject.put("tactic1list", paper.getPaperFasterTactic().getExamPaperFasterTactic1());
			jsonObject.put("tactic2list", paper.getPaperFasterTactic().getExamPaperFasterTactic2());
			jsonObject.put("propInfo", jsonPropObj);

			//Set refereed papers information.
			jsonObject.put("paperList" , ref_paperList);
			
			request.setAttribute("jsonList", jsonObject);
			request.setAttribute("propIds", strPropIds);
			request.setAttribute("propNames", strPropNames);
			request.setAttribute("cognizeIds", cognizeIds);
			request.setAttribute("cognizeNames", cognizeNames);
			request.setAttribute("sourceIds", sourceIds);
			request.setAttribute("sourceNames", sourceNames);
			
		}
		request.setAttribute("pMode", paperMode);
		request.setAttribute("paperType", paperType);
		
		return "SUCCESS";
	}
	
	private void setPropVal(Long[] propVal,String propKey,Map<String,List<ExamQuestionProp>> questPropMap){
		if (propVal != null) {
			List<ExamQuestionProp> list = new ArrayList<ExamQuestionProp>();
			for(int i=0;i<propVal.length;i++){
				ExamQuestionProp prop = new ExamQuestionProp();
				prop.setProp_val_id(propVal[i]);
				list.add(prop);
			}
			questPropMap.put(propKey, list);
		}
	}
	private String paperUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		 // 3  : 手工?卷 
			OlemPaperForm pform = (OlemPaperForm) form;
			//Get paper mode.
			Integer pMode = pform.getPaperMode();
			ExamPaper paper = new ExamPaper();
			
			if (pMode.equals(Constants.PAPER_MODE_KJ1)) {   //1.快捷策略
				paper.setId(pform.getId());
				paper.setName(pform.getName());
				paper.setIsnot_save_tactic(pform.getTacticEditFlag());
				paper.setPaper_mode(pform.getPaperMode());
				paper.setType_id(pform.getTypeId());
				paper.setType_name(pform.getTypeName());
				paper.setPaper_score(pform.getPaperScore());
				paper.setQuestion_num(pform.getQuestionNum());
				paper.setState(1);
				paper.setIsnot_exp_paper(pform.getIsNotExp());
				paper.setIsnot_save_tactic(1);
				paper.setGrade(pform.getGrade());
				paper.setCreate_date(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

				ExamPaperFasterTactic pft = new ExamPaperFasterTactic();

				setPaperFasterTactic(pft, request);
				pft.setGrade(paper.getGrade());
				paper.setPaperFasterTactic(pft);

			} else if(pMode.equals(Constants.PAPER_MODE_JX)) {   //  2：精细策略

				paper.setId(pform.getId());
				paper.setName(pform.getName());
				paper.setIsnot_save_tactic(pform.getTacticEditFlag());
				paper.setPaper_mode(pform.getPaperMode());
				paper.setType_id(pform.getTypeId());
				paper.setType_name(pform.getTypeName());
				paper.setPaper_score(pform.getPaperScore());
				paper.setQuestion_num(pform.getQuestionNum());
				paper.setState(1);
				paper.setGrade(pform.getGrade());
				paper.setIsnot_exp_paper(pform.getIsNotExp());
				paper.setIsnot_save_tactic(1);
				paper.setCreate_date(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
				paper = setPaperFasterTactic(request, paper);
				

			} else if (pMode.equals(Constants.PAPER_MODE_SG)){			// 3  : 手工组卷 
				String seleQues = pform.getSeleQues(); 
			
				if (null != seleQues && !"".equals(seleQues)) {
				
					List<ExamPaperQuestion> pqList = new ArrayList<ExamPaperQuestion>();
					String[] quesIds = seleQues.split("\\_");
				
					for (int i = 0; i < quesIds.length; i++) {
					
						ExamPaperQuestion pq = new ExamPaperQuestion();

						String[] qId = quesIds[i].split("\\+");
						pq.setPaper_id(pform.getId());
						pq.setQuestion_id(Long.valueOf(qId[0]));
						pq.setQuestion_score(Double.valueOf(qId[1]));
						pq.setSeq(i);
					
						pqList.add(pq);
					}
				
					// set updateata
					paper.setId(pform.getId());
					paper.setName(pform.getName());
					paper.setPaper_mode(pform.getPaperMode());
					paper.setType_id(pform.getTypeId());
					paper.setType_name(pform.getTypeName());
					paper.setPaper_score(pform.getPaperScore());
					paper.setQuestion_num(pform.getQuestionNum()); 
					paper.setExamPaperQuestionList(pqList);
					paper.setState(1);
					paper.setIsnot_exp_paper(pform.getIsNotExp());
					paper.setCreate_date(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

				}
			} else if (pMode.equals(Constants.PAPER_MODE_JZJ)) { // 4  : 卷中卷

			paper.setId(pform.getId());
			paper.setName(pform.getName());
			paper.setIsnot_save_tactic(pform.getTacticEditFlag());
			paper.setPaper_mode(pform.getPaperMode());
			paper.setType_id(pform.getTypeId());
			paper.setType_name(pform.getTypeName());
			paper.setPaper_score(pform.getPaperScore());
			paper.setQuestion_num(pform.getQuestionNum());
			paper.setState(1);
			paper.setIsnot_exp_paper(pform.getIsNotExp());
			paper.setCreate_date(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			//父试卷id
			paper.setParent_id(0L);
			
			paper.setIsnot_save_tactic(1); // To Save the Tactic Information.
	
			List<ExamPaperAndPaper> epList = new ArrayList<ExamPaperAndPaper>();
			String papersIds = request.getParameter("papersIds");
			String midinfo = request.getParameter("midinfo");
	
			if (!"".equals(papersIds)) {
				String[] pId = papersIds.split("\\_");
				Long[] pIds = new Long[pId.length];
	
				for (int i = 0; i < pId.length; i++) {
					pIds[i] = Long.valueOf(pId[i].trim());
				}
				paper.setPaperIdArr(pIds);
			}
			if (!"".equals(midinfo)) {
				String[] mid = midinfo.split(",");
	
				for (int i = 0; i < mid.length; i++) {
					ExamPaperAndPaper epp = new ExamPaperAndPaper();
					String[] submid = mid[i].split("\\_");
					epp.setQuestion_label_id(Integer.parseInt(submid[0]));
					epp.setSelNum(Integer.parseInt(submid[1]));
					epp.setQuestion_score(Double.parseDouble(submid[2]));
	
					epList.add(epp);
				}
				paper.setPaperAndPaperList(epList);
			}
		}
		//update paper
		String result =  examPaperFacade.updateExamPaper(paper); 
		if (result.equals("duplicate")) {
			StrutsUtil.renderText(response, "duplicate");
		} else if(result.equals("success")){
			StrutsUtil.renderText(response, "success");	
		} else {
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	
	/**
	 * @author Tiger
	 * @time 2017-02-07
	 * @param pft
	 * @param request
	 * @Description : Set examPaperFasterTactic from request.
	 */
	private void setPaperFasterTactic(ExamPaperFasterTactic pft, HttpServletRequest request) {
		String questType_2 = request.getParameter("questType_2");
		String propIds = request.getParameter("propIds");
		String laiIds = request.getParameter("laiIds");
		String levelIds = request.getParameter("levelIds");
		String questionLabelIds = request.getParameter("questionLabelIds");

		if (null != questType_2 && !"".equals(questType_2)) {
			pft.setQuestion_type_id(questType_2);
		}

		//Set the tactic2.
		List<ExamPaperFasterTactic2> pft2List = new ArrayList<ExamPaperFasterTactic2>();
		if (null != propIds && !"".equals(propIds)) {
				String[] propValId = propIds.split(",");
				for (int j = 0; j < propValId.length; j++) {
					ExamPaperFasterTactic2 pft2 = new ExamPaperFasterTactic2();
					Long pvId = Long.valueOf(propValId[j]);
					pft2.setUnit_id(pvId);
					pft2List.add(pft2);
				}

			pft.setExamPaperFasterTactic2(pft2List);
		}
		List<ExamPaperFasterTactic1> pft1List = new ArrayList<ExamPaperFasterTactic1>();

		//Set the tactic1.
		if (!"".equals(questionLabelIds)) {
			String[] qArray = questionLabelIds.split(",");
			for (int i = 0; i < qArray.length; i++) {
				ExamPaperFasterTactic1 pft1 = new ExamPaperFasterTactic1();
				String[] obj = qArray[i].split("_");

				Integer qLable = Integer.valueOf(obj[0]);
				Integer qNum = Integer.valueOf(obj[1]);
				Double qScore = Double.valueOf(obj[2]);

				pft1.setQuestion_label_id(qLable);
				pft1.setNum(qNum);
				pft1.setScore(qScore);

				pft1List.add(pft1);
			}
		}
		pft.setExamPaperFasterTactic1(pft1List);
		
		//Set the tactic3.
		if (null != levelIds && !"".equals(levelIds)) {
			List<ExamPaperFasterTactic3> pft3List = new ArrayList<ExamPaperFasterTactic3>();
			String[] array = levelIds.split(",");
			for (int i = 0; i < array.length; ++i) {
				ExamPaperFasterTactic3 pft3 = new ExamPaperFasterTactic3();
				pft3.setCognize_id(Long.valueOf(array[i]));
				pft3List.add(pft3);
			}
			pft.setExamPaperFasterTactic3(pft3List);
		}
		if (null != laiIds && !"".equals(laiIds)) {
			List<ExamPaperFasterTactic3> pft4List = new ArrayList<ExamPaperFasterTactic3>();
			String[] array = laiIds.split(",");
			for (int i = 0; i < array.length; ++i) {
				ExamPaperFasterTactic3 pft4 = new ExamPaperFasterTactic3();
				pft4.setSourceId(Long.valueOf(array[i]));
				pft4List.add(pft4);
			}
			pft.setExamPaperFasterTactic4(pft4List);
		}
	}
	
	private ExamPaper setPaperFasterTactic(HttpServletRequest request, ExamPaper paper) {
		String questType_2 = request.getParameter("questType_2");
		String propIds = request.getParameter("propIds");
		String laiIds = request.getParameter("laiIds");
		String levelIds = request.getParameter("levelIds");
		String questionLabelIds = request.getParameter("questionLabelIds");
		String seleQues = request.getParameter("seleQues");

		ExamPaperFasterTactic pft = new ExamPaperFasterTactic();

		Map<String, Integer> shux = new HashMap<String, Integer>();

		if (null != seleQues && !"".equals(seleQues)) {
			String[] quesCount = seleQues.split("\\_");
			for (int i = 0; i < quesCount.length; i++) {
				String[] qId = quesCount[i].split("\\+");
				shux.put(qId[0], Integer.parseInt(qId[1]));
			}
		}

		if (null != questType_2 && !"".equals(questType_2)) {
			pft.setQuestion_type_id(questType_2);
		}

		List<ExamPaperFasterTactic2> pft2List = new ArrayList<ExamPaperFasterTactic2>();
		if (null != propIds && !"".equals(propIds)) {
				String[] propValId = propIds.split(",");
				for (int j = 0; j < propValId.length; j++) {
					ExamPaperFasterTactic2 pft2 = new ExamPaperFasterTactic2();
					Long pvId = Long.valueOf(propValId[j]);
					pft2.setUnit_id(pvId);
					pft2.setPercent(shux.get(pvId.toString()));
					pft2List.add(pft2);
					
				}

			pft.setExamPaperFasterTactic2(pft2List);
		}
		List<ExamPaperFasterTactic1> pft1List = new ArrayList<ExamPaperFasterTactic1>();
		Integer totQuesCount = 0;
		if (!"".equals(questionLabelIds)) {
			String[] qArray = questionLabelIds.split(",");
			for (int i = 0; i < qArray.length; i++) {
				ExamPaperFasterTactic1 pft1 = new ExamPaperFasterTactic1();
				String[] obj = qArray[i].split("_");

				Integer qLable = Integer.valueOf(obj[0]);
				Integer qNum = Integer.valueOf(obj[1]);
				Double qScore = Double.valueOf(obj[2]);

				pft1.setQuestion_label_id(qLable);
				pft1.setNum(qNum);
				pft1.setScore(qScore);
				totQuesCount = totQuesCount + qNum;
				pft1List.add(pft1);
			}
		}
		pft.setExamPaperFasterTactic1(pft1List);
		if (null != levelIds && !"".equals(levelIds)) {
			List<ExamPaperFasterTactic3> pft3List = new ArrayList<ExamPaperFasterTactic3>();
			String[] array = levelIds.split(",");
			for (int i = 0; i < array.length; ++i) {
				ExamPaperFasterTactic3 pft3 = new ExamPaperFasterTactic3();
				pft3.setCognize_id(Long.valueOf(array[i]));
				pft3List.add(pft3);
			}
			pft.setExamPaperFasterTactic3(pft3List);
		}
		if (null != laiIds && !"".equals(laiIds)) {
			List<ExamPaperFasterTactic3> pft4List = new ArrayList<ExamPaperFasterTactic3>();
			String[] array = laiIds.split(",");
			for (int i = 0; i < array.length; ++i) {
				ExamPaperFasterTactic3 pft4 = new ExamPaperFasterTactic3();
				pft4.setSourceId(Long.valueOf(array[i]));
				pft4List.add(pft4);
			}
			pft.setExamPaperFasterTactic4(pft4List);
		}

		List<ExamQuestionLabel> questLabels = examQuestionLabelFacade.getQuestionLabel(1);
		Map<Integer, String> lables = new HashMap<Integer, String>();
		for (Iterator<ExamQuestionLabel> iterator = questLabels.iterator(); iterator.hasNext();) {
			ExamQuestionLabel ql = iterator.next();
			lables.put(ql.getId(), ql.getName());
		}

		pft.setGrade(paper.getGrade());
		paper.setPaperFasterTactic(pft);
		

		paper = examPaperFacade.getQuestSizeByFasterTactic(paper);

		paper = QuesCountUtils.setQuesSize(paper, shux, totQuesCount);

		return paper;

	}
}
