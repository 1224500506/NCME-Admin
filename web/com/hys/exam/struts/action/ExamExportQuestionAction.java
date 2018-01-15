package com.hys.exam.struts.action;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.common.util.StringUtils;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-15
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExportQuestionAction extends BaseAction {

	private ExamQuestionFacade localExamQuestionFacade;
	
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
		
		ExamQuestionForm qform = (ExamQuestionForm)form;
		
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=\"examQuestionList.xls\"");
		OutputStream os = response.getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		if(qform.getIds()!=null && qform.getIds().length>0){
		
			List<ExamQuestion> list = localExamQuestionFacade.getQuestionIdByIdArr(qform.getIds());
			
			int ncout = list.size();
			int maxnum = 50000; // 一次最多写入量
			int times = (ncout + maxnum - 1) / maxnum;
			for (int t = 0; t < times; t++) {
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("Sheet" + (t + 1), t);
			
				Label label = new Label(0, 0, "系统编号(必填)");
				wsheet.addCell(label);
				label = new Label(1, 0, "题型ID");
				wsheet.addCell(label);
				label = new Label(2, 0, "试题内容");
				wsheet.addCell(label);
				
//				label = new Label(3, 0, "一级学科（属性名称|属性ID）");
//				wsheet.addCell(label);
//				
//				label = new Label(4, 0, "二级学科（属性名称|属性ID）");
//				wsheet.addCell(label);
//				
//				label = new Label(5, 0, "三级学科（属性名称|属性ID）");
//				wsheet.addCell(label);
//				
//				label = new Label(6, 0, "单元（属性名称|属性ID）");
//				wsheet.addCell(label);
				
				label = new Label(3, 0, "知识点");
				wsheet.addCell(label);				
				
				for (int i = 0; i < list.size(); i++) {
					ExamQuestion q = list.get(i);
					getQuestion(wsheet, q);
				}
			}	
			wwb.write();
			wwb.close();
			os.close();
			response.flushBuffer();
			
			return null;
		}
		
		return null;
	}

	private void getQuestion(WritableSheet wsheet, ExamQuestion q)
			throws WriteException, RowsExceededException {
		Label label;
		int rowNum = wsheet.getRows();
		int labelId = q.getQuestion_label_id().intValue();

		// 系统编号
		label = new Label(0, rowNum, String.valueOf(q.getId()));
		wsheet.addCell(label);
		// 题型
		label = new Label(1, rowNum, String.valueOf(labelId));
		wsheet.addCell(label);
		// 试题内容
		label = new Label(2, rowNum, q.getContent());
		wsheet.addCell(label);
		
		ExamQuestionPropValCascade propValCascade = q.getQuestPropValCascade();
		
		if(propValCascade!=null){
			String cid = propValCascade.getPropval_id();
			String cname = propValCascade.getPropval_name();
			//setPropVal(cid,cname,rowNum,wsheet,q.getId().toString());
			setPropValPoint(cid,cname,rowNum,wsheet,q.getId().toString());
		}
		
		
//		label = new Label(0, wsheet.getRows(),String.valueOf(q.getId()));
//		wsheet.addCell(label);
	}
	
	
	private void setPropVal(String propvalId, String propvalName, int rownum,
			WritableSheet wsheet, String qid) throws WriteException,
			RowsExceededException {
		List<String> propValIdList = StringUtils.stringToArrayList(propvalId,
				"+");
		List<String> propValNameList = StringUtils.stringToArrayList(
				propvalName, "+");

		for (int i = 0; i < propValIdList.size(); i++) {
			List<String> propValIdTmp = StringUtils.stringToArrayList(
					propValIdList.get(i), "-");
			List<String> propValNameTmp = StringUtils.stringToArrayList(
					propValNameList.get(i), "-");
			for (int j = 0; j < propValIdTmp.size(); j++) {
				Label label;
				if (propValNameTmp.get(j) != null
						&& propValIdTmp.get(j) != null) {
					switch (j) {
					case 0: // 一级学科
						label = new Label(0, rownum + i, qid);
						wsheet.addCell(label);
						label = new Label(3, rownum + i, propValNameTmp.get(j)
								+ "|" + propValIdTmp.get(j));
						wsheet.addCell(label);
						break;

					case 1: // 二级学科
						label = new Label(0, rownum + i, qid);
						wsheet.addCell(label);
						label = new Label(4, rownum + i, propValNameTmp.get(j)
								+ "|" + propValIdTmp.get(j));
						wsheet.addCell(label);
						break;
					case 2: // 三级学科
						label = new Label(0, rownum + i, qid);
						wsheet.addCell(label);
						label = new Label(5, rownum + i, propValNameTmp.get(j)
								+ "|" + propValIdTmp.get(j));
						wsheet.addCell(label);
						break;
					case 3: // 单元
						label = new Label(0, rownum + i, qid);
						wsheet.addCell(label);
						label = new Label(6, rownum + i, propValNameTmp.get(j)
								+ "|" + propValIdTmp.get(j));
						wsheet.addCell(label);
						break;
					case 4: // 知识点
						label = new Label(0, rownum + i, qid);
						wsheet.addCell(label);
						label = new Label(7, rownum + i, propValNameTmp.get(j)
								+ "|" + propValIdTmp.get(j));
						wsheet.addCell(label);
						break;
					}
				}

			}

		}
	}
	
	
	
	private void setPropValPoint(String propvalId, String propvalName, int rownum,
			WritableSheet wsheet, String qid) throws WriteException,
			RowsExceededException {
		Label label;
		List<String> propValIdList = StringUtils.stringToArrayList(propvalId,
				"+");
		String propValPointId="";
		for (int i = 0; i < propValIdList.size(); i++) {
			List<String> propValIdTmp = StringUtils.stringToArrayList(
					propValIdList.get(i), "-");
			
			for (int j = 0; j < propValIdTmp.size(); j++) {
				if (propValIdTmp.get(j) != null) {
					if(j==4){
						if(propValPointId.equals("")){
							propValPointId = propValIdTmp.get(j);
						}else{
							propValPointId +=","+propValIdTmp.get(j);
						}
					}
				}
			}
		}
		label = new Label(3, rownum,propValPointId);
		wsheet.addCell(label);
	}
	
	

}
