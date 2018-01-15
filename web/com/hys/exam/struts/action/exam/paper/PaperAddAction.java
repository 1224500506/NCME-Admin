package com.hys.exam.struts.action.exam.paper;

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
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.struts.form.OlemPaperForm;
import com.hys.framework.web.action.BaseAction;

public class PaperAddAction extends BaseAction {

	private ExamPaperFacade examPaperFacade;

	private ExamQuestionLabelFacade examQuestionLabelFacade;
	
	private ExamPropValFacade localExamPropValFacade;

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		OlemPaperForm pform = (OlemPaperForm) form;
		String method = ParamUtils.getParameter(request, "method", null);

		if ("forward".equals(method)) {
			List<ExamQuestionLabel> questLabel = examQuestionLabelFacade.getQuestionLabel(1);
			request.setAttribute("questLableList", questLabel);
			return "FORWARD";
		}

		if ("save".equals(method)) {
			Integer pMode = pform.getPaperMode();

			if (pMode.equals(Constants.PAPER_MODE_JX)) {	//  2：精细策略

				ExamPaper paper = new ExamPaper();
				paper.setName(pform.getName());
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
				//父试卷id
				paper.setParent_id(0L);
				//试卷数量
				if (pform.getChildPaperNum().equals(1)) {
					paper.setChild_paper_num(0);
				} else {
					paper.setChild_paper_num(pform.getChildPaperNum() - 1);
				}

				paper = setPaperFasterTactic(request, paper);
				
				Long result = examPaperFacade.addExamPaper(paper);
				if (result != null && result > 0) {
					StrutsUtil.renderText(response, "success");
				} else if(result == 0L) {
					StrutsUtil.renderText(response, "duplicate");
				}
				else {
					StrutsUtil.renderText(response, "fail");
				}
				return null;

			} else if (pMode.equals(Constants.PAPER_MODE_KJ1)) {	//1.快捷策略
				ExamPaper paper = new ExamPaper();
				paper.setName(pform.getName());
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
				//父试卷id
				paper.setParent_id(0L);
				//试卷数量
				if (pform.getChildPaperNum().equals(1)) {
					paper.setChild_paper_num(0);
				} else {
					paper.setChild_paper_num(pform.getChildPaperNum() - 1);
				}

				ExamPaperFasterTactic pft = new ExamPaperFasterTactic();

				setPaperFasterTactic(pft, request);
				pft.setGrade(paper.getGrade());
				paper.setPaperFasterTactic(pft);

				Long returnResult = examPaperFacade.addExamPaper(paper);
				if (returnResult != null && returnResult > 0) {
					StrutsUtil.renderText(response, "success");
				} else if(returnResult == 0L) {
					StrutsUtil.renderText(response, "duplicate");
				}
				else {
					StrutsUtil.renderText(response, "fail");
				}
			} else if (pMode.equals(Constants.PAPER_MODE_JZJ)) { // 4  : 卷中卷

				ExamPaper paper = new ExamPaper();
				paper.setName(pform.getName());
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
				//试卷数量
				if (pform.getChildPaperNum().equals(1)) {
					paper.setChild_paper_num(0);
				} else {
					paper.setChild_paper_num(pform.getChildPaperNum() - 1);
				}
				
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

				Long returnId = examPaperFacade.addExamPaper(paper);
				
				if (returnId != null && returnId > 0) {
					StrutsUtil.renderText(response, "success");
				} else if(returnId == 0L) {
					StrutsUtil.renderText(response, "duplicate");
				}
				else {
					StrutsUtil.renderText(response, "fail");
				}
					
			} else if (pMode.equals(Constants.PAPER_MODE_SG)) {  // 3  : 手工组卷 

				String seleQues = pform.getSeleQues();
				if (null != seleQues && !"".equals(seleQues)) {
					List<ExamPaperQuestion> pqList = new ArrayList<ExamPaperQuestion>();
					String[] quesIds = seleQues.split("\\_");
					for (int i = 0; i < quesIds.length; i++) {
						ExamPaperQuestion pq = new ExamPaperQuestion();

						String[] qId = quesIds[i].split("\\+");
						pq.setQuestion_id(Long.valueOf(qId[0]));
						pq.setQuestion_score(Double.valueOf(qId[1]));
						pq.setSeq(i);

						pqList.add(pq);
					}

					ExamPaper paper = new ExamPaper();
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

					//父试卷id
					paper.setParent_id(0L);

					Long returnId = examPaperFacade.addExamPaper(paper);
					
					if (returnId != null && returnId > 0) {
						StrutsUtil.renderText(response, "success");
					} else if(returnId == 0L) {
						StrutsUtil.renderText(response, "duplicate");
					}
					else {
						StrutsUtil.renderText(response, "fail");
					}
				}
			}
		}
/*		String path = request.getContextPath();
		String url = path + "/paperManage/paperList.do";
		response.sendRedirect(url);
		return Constants.SUCCESS;*/
		return null;
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
					pft2List.add(pft2);
					pft2.setPercent(shux.get(pvId.toString()));
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

	private void setPaperFasterTactic(ExamPaperFasterTactic pft, HttpServletRequest request) {
		String questType_2 = request.getParameter("questType_2");
		String propIds = request.getParameter("propIds");
		String laiIds = request.getParameter("laiIds");
		String levelIds = request.getParameter("levelIds");
		String questionLabelIds = request.getParameter("questionLabelIds");

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
					pft2List.add(pft2);
				}

			pft.setExamPaperFasterTactic2(pft2List);
		}
		List<ExamPaperFasterTactic1> pft1List = new ArrayList<ExamPaperFasterTactic1>();

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
	
	
}
