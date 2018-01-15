package com.hys.exam.struts.action.exam.paper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：导出试卷详细信息
 * 
 * 说明:
 */
public class PaperOutAction extends BaseAction {

	private ExamPaperFacade examPaperFacade;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long paperId = ParamUtils.getLongParameter(request, "paperId", 0);

		ExamPaper paper = examPaperFacade.getExamPaperById(paperId);

		if (null != paper) {

			request.setAttribute("paper", paper);
			request.setAttribute("result", Constants.RESULT);

			List<ExamQuestion> quesList = paper.getExamQuestionList();

			// 单选
			List<ExamQuestion> danxuan_list = new ArrayList<ExamQuestion>();
			// 多选
			List<ExamQuestion> duoxuan_list = new ArrayList<ExamQuestion>();
			// 判断
			List<ExamQuestion> panduan_list = new ArrayList<ExamQuestion>();
			// 填空
			List<ExamQuestion> tiankong_list = new ArrayList<ExamQuestion>();
			// 简答
			List<ExamQuestion> jianda_list = new ArrayList<ExamQuestion>();
			// 名词解析
			List<ExamQuestion> jieshi_list = new ArrayList<ExamQuestion>();
			// 案例分析
			List<ExamQuestion> anli_list = new ArrayList<ExamQuestion>();

			for (int i = 0; i < quesList.size(); i++) {
				ExamQuestion question = quesList.get(i);
				Integer labelId = question.getQuestion_label_id();
				if (labelId == Constants.A1 || labelId == Constants.A2 || labelId == Constants.A3 || labelId == Constants.B1) {
					danxuan_list.add(question);
				} else if (labelId == Constants.DXT) {
					duoxuan_list.add(question);
				} else if (labelId == Constants.PD || labelId == Constants.PDGC) {
					panduan_list.add(question);
				} else if (labelId == Constants.TK) {
					tiankong_list.add(question);
				} else if (labelId == Constants.JD) {
					jianda_list.add(question);
				} else if (labelId == Constants.MCJS) {
					jieshi_list.add(question);
				} else if (labelId == Constants.NLFX) {
					anli_list.add(question);
				}
			}

			if (null != danxuan_list && danxuan_list.size() > 0) {
				request.setAttribute("danxuan_list", danxuan_list);
			} else {
				request.setAttribute("danxuan_list", null);
			}
			
			if (null != duoxuan_list && duoxuan_list.size() > 0) {
				request.setAttribute("duoxuan_list", duoxuan_list);
			} else {
				request.setAttribute("duoxuan_list", null);
			}
			
			if (null != panduan_list && panduan_list.size() > 0)
				request.setAttribute("panduan_list", panduan_list);
			else
				request.setAttribute("panduan_list", null);
			
			if (null != tiankong_list && tiankong_list.size() > 0)
				request.setAttribute("tiankong_list", tiankong_list);
			else
				request.setAttribute("tiankong_list", null);
			
			if (null != jianda_list && jianda_list.size() > 0)
				request.setAttribute("jianda_list", jianda_list);
			else
				request.setAttribute("jianda_list", null);
			
			if (null != jieshi_list && jieshi_list.size() > 0)
				request.setAttribute("jieshi_list", jieshi_list);
			else
				request.setAttribute("jieshi_list", null);

			if (null != anli_list && anli_list.size() > 0)
				request.setAttribute("anli_list", anli_list);
			else
				request.setAttribute("anli_list", null);
		}

		return Constants.SUCCESS;
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
}