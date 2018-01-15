package com.hys.exam.struts.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropExport;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-10
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExportPropAction extends BaseAction {

	private ExamPropValFacade localExamPropValFacade;

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=\"examPropValList.xls\"");
		OutputStream os = response.getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
		
		// 导出有关联的属性值
		exportRelationVal(wwb,Integer.valueOf(type));
		
		// 导出无关联的属性值
		exportPropVal(wwb);
		
		wwb.write();
		wwb.close();
		os.close();
		response.flushBuffer();

		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void exportPropVal(WritableWorkbook wwb) throws WriteException, RowsExceededException {
		Map<String,List<ExamPropVal>> propMap = localExamPropValFacade.getSysPropValBySysId();
		List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
		for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
				questPropPoint2 = (List<ExamPropVal>)entry.getValue();
				createSheet(wwb,questPropPoint2,"行业");
			} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
				questPropCognize = (List<ExamPropVal>)entry.getValue();
				createSheet(wwb,questPropCognize,"知识分类");
			} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
				questPropTitle =(List<ExamPropVal>)entry.getValue();
				createSheet(wwb,questPropTitle,"适用对象");
			}
		}
	}
	
	private void createSheet(WritableWorkbook wwb,List<ExamPropVal> valList,String PorpName) throws WriteException, RowsExceededException {
		
		WritableSheet wsheet = wwb.createSheet(PorpName,wwb.getSheets().length);
		WritableFont wfc = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD);    
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);  
		wcfFC.setAlignment(Alignment.CENTRE);
		wcfFC.setBackground(Colour.SKY_BLUE);
		
		Label label = new Label(0, 0, PorpName + "ID",wcfFC);
		wsheet.addCell(label);
		label = new Label(1, 0, PorpName + "名称",wcfFC);
		wsheet.addCell(label);
		
		if(valList != null && valList.size()>0){
			for (int j = 0; j < valList.size(); j++) {
				ExamPropVal val = valList.get(j);
				wfc = new WritableFont(WritableFont.createFont("宋体"),10);    
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.RIGHT);
				label = new Label(0, j+1, String.valueOf(val.getId()),wcfFC);
				wsheet.addCell(label);
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.LEFT);
				label = new Label(1, j+1, val.getName(),wcfFC);
				wsheet.addCell(label);
			}
		}
		
	}

	private void exportRelationVal(WritableWorkbook wwb,int type) throws WriteException,
			RowsExceededException {
		List<ExamPropVal> list = localExamPropValFacade.getBaseRelPorp(type);

		for (int i = 0; i < list.size(); i++) {
			ExamPropVal tmp = list.get(i);
			// 新建一张表
			WritableSheet wsheet = wwb.createSheet(tmp.getName(), 0);

			WritableFont wfc = null;
			WritableCellFormat wcfFC = null;
			WritableCellFormat wcfFC1 = null;
			// 宋体 10号字 粗体
			wfc = new WritableFont(WritableFont.createFont("宋体"), 10,
					WritableFont.BOLD);
			wcfFC = new WritableCellFormat(wfc);
			// 设置水平居中
			wcfFC.setAlignment(Alignment.CENTRE);
			// 设置背景色为天蓝色
			wcfFC.setBackground(Colour.SKY_BLUE);

			Label label = new Label(0, 0, "");
			// 设置表头
			label = new Label(0, 0, "一级学科", wcfFC);
			wsheet.addCell(label);
			label = new Label(1, 0, "二级学科", wcfFC);
			wsheet.addCell(label);
			label = new Label(2, 0, "三级学科", wcfFC);
			wsheet.addCell(label);
			label = new Label(3, 0, "单元", wcfFC);
			wsheet.addCell(label);
			label = new Label(4, 0, "知识点", wcfFC);
			wsheet.addCell(label);
			
			// 宋体 10号字
			wfc = new WritableFont(WritableFont.createFont("宋体"),10);
			wcfFC = new WritableCellFormat(wfc);
			// 设置垂直靠上
			wcfFC.setVerticalAlignment(VerticalAlignment.TOP);
			// 设置水平靠左对齐
			wcfFC.setAlignment(Alignment.LEFT);
			
			wcfFC1 = new WritableCellFormat(wfc);
			// 设置垂直靠上
			wcfFC1.setVerticalAlignment(VerticalAlignment.TOP);
			// 设置水平靠右对齐
			wcfFC1.setAlignment(Alignment.RIGHT);
			
			if(tmp.getRef()!=null && tmp.getRef().size()>0){
				
				for (int j = 0; j < tmp.getRef().size(); j++) {
					ExamPropExport prop = tmp.getRef().get(j);
					label = new Label(0, j+1, prop.getP1(),wcfFC1);
					wsheet.addCell(label);
					label = new Label(1, j+1, prop.getP2(),wcfFC);
					wsheet.addCell(label);
					label = new Label(2, j+1, prop.getP3(),wcfFC1);
					wsheet.addCell(label);
					label = new Label(3, j+1, prop.getP4(),wcfFC);
					wsheet.addCell(label);
					label = new Label(4, j+1, prop.getP5(),wcfFC1);
					wsheet.addCell(label);
				}
			}
			
			int start = 1;
			for (int j = 0; j < wsheet.getColumns(); j++) {
				for (int k = 0; k < wsheet.getRows(); k++) {
					if(k>1 && !wsheet.getCell(j, k).getContents().trim().equals("") && wsheet.getCell(j, k).getContents().equals(wsheet.getCell(j, k-1).getContents())){
						if(k == wsheet.getRows()-1){
							wsheet.mergeCells(j, start, j, k);
						}
					}else{
						if(start < k){
							wsheet.mergeCells(j, start, j, k-1);
						}		
						if(k!=0){
							start = k;
						}
					}
				}
			}
			
		}
	}

}
