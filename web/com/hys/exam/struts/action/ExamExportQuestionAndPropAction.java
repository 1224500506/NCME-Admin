package com.hys.exam.struts.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
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
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
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
public class ExamExportQuestionAndPropAction extends BaseAction {

	private ExamQuestionFacade localExamQuestionFacade;
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	private ExamPropValFacade localExamPropValFacade;
	
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
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

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
        WritableFont fontred = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.RED);//设置字体样式  颜色为红色
        WritableCellFormat cellFormatred = new WritableCellFormat(fontred);
        WritableFont fontpunk = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置字体样式 颜色为黑色
        WritableCellFormat cellFormatpunk = new WritableCellFormat(fontpunk);

//        ExamQuestionForm qform = (ExamQuestionForm)form;
		
		List<ExamQuestion> list = new ArrayList<ExamQuestion>();
		
		String[] idsArray = request.getParameterValues("idArr");
		if(idsArray!=null){
			Long[] ids = new Long[idsArray.length];
			for (int i=0; i<idsArray.length; i++){ 
				ids[i] = (Long.parseLong(idsArray[i]));
				list.add(localExamQuestionFacade.getQuestionById(ids[i]));
			}
		}
		
		
		{
			
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition",	"attachment;filename=\"examQuestionList.xls\"");
			
			OutputStream os = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
		
			int ncout = list.size();
			int maxnum = 50000; // 一次最多写入量
			int times = Math.max(1,(ncout + maxnum - 1) / maxnum);
			for (int t = 0; t < times; t++) {
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("示例数据" + (t + 1), t);
			
				Label label = new Label(0, 0, "编号", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(1, 0, "父试题ID", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(2, 0, "题型ID", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(3, 0, "试题内容", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(4, 0, "可选答案A", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(5, 0, "可选答案B", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(6, 0, "可选答案C", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(7, 0, "可选答案D", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(8, 0, "可选答案E", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(9, 0, "可选答案F", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(10, 0, "答案", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(11, 0, "试题分析", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(12, 0, "学科IDs", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(13, 0, "主题IDs", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(14, 0, "来源IDs", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(15, 0, "其他来源", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(16, 0, "篇名", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(17, 0, "职称IDs", cellFormatred);
				wsheet.addCell(label);
				
				label = new Label(18, 0, "认知水平IDs", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(19, 0, "难度", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(20, 0, "上传", cellFormatpunk);
				wsheet.addCell(label);
				
				label = new Label(21, 0, "是多媒体", cellFormatpunk);
				wsheet.addCell(label);
								
				if(list!=null && !list.isEmpty()){
					for (int i = t*50000; i < Math.min(((t+1)*50000),ncout); i++) {
						ExamQuestion q = list.get(i);
						getQuestion(wsheet, q);
					}
				}
			}	
			
			//xueke data
			if(list==null || list.isEmpty()){
				//题型
				{
					WritableSheet wsheet = wwb.createSheet("题型", times+1);
					
					Label label = new Label(0, 0, "题型ID");
					wsheet.addCell(label);

					label = new Label(1, 0, "名称");
					wsheet.addCell(label);

					List<ExamQuestionLabel>labelList = localExamQuestionLabelFacade.getQuestionLabel(1);
					int i = 1;
					for(ExamQuestionLabel e: labelList){
						label = new Label(0, i, e.getId().toString());
						wsheet.addCell(label);

						label = new Label(1, i, e.getName());
						wsheet.addCell(label);
						i++;
					}
				}
				ExamProp prop = new ExamProp();
				//试题主题
				prop.setType(7);
				List<ExamProp> p7list = localExamPropValFacade.getPropListByType(prop);
				
				//试题认知水平
				prop.setType(8);
				List<ExamProp> p8list = localExamPropValFacade.getPropListByType(prop);
				
				//试题职称表
				prop.setType(9);
				List<ExamProp> p9list = localExamPropValFacade.getPropListByType(prop);
				
				List<ExamSource> pSlist = localExamPropValFacade.getSourceValList(new ExamSource());
				
				//学科
				{
					WritableSheet wsheet = wwb.createSheet("学科", times+2);
					
					Label label = new Label(0, 0, "学科ID");
					wsheet.addCell(label);

					label = new Label(1, 0, "级别");
					wsheet.addCell(label);

					label = new Label(2, 0, "名称");
					wsheet.addCell(label);

					int i = 0;
					//一级学科
					prop.setType(1);
					List<ExamProp> p1list = localExamPropValFacade.getPropListByType(prop);
					for (ExamProp p1: p1list){
						ExamPropQuery query1  = new ExamPropQuery();
						query1.setSysPropId(Long.valueOf(p1.getProp_val_id()));
						ExamReturnProp rprop1 = localExamPropValFacade.getNextLevelProp(query1);
						
						List<ExamProp> p2list = rprop1.getReturnList();
						
						for (ExamProp p2: p2list){
							ExamPropQuery query2  = new ExamPropQuery();
							query2.setSysPropId(Long.valueOf(p2.getProp_val_id()));
							ExamReturnProp rprop2 = localExamPropValFacade.getNextLevelProp(query2);
							
							List<ExamProp> p3list = rprop2.getReturnList();
							
							for (ExamProp p3: p3list){
								ExamPropQuery query3  = new ExamPropQuery();
								query3.setSysPropId(Long.valueOf(p3.getProp_val_id()));
								ExamReturnProp rprop3 = localExamPropValFacade.getNextLevelProp(query3);
								
								List<ExamProp> p4list = rprop3.getReturnList();
								
								i++;
								
								label = new Label(0, i, p3.getProp_val_id().toString());
								wsheet.addCell(label);

								label = new Label(1, i, "三级学科");
								wsheet.addCell(label);
								
								
								label = new Label(2, i, p3.getName());
								wsheet.addCell(label);

								for (ExamProp p4: p4list){
									ExamPropQuery query4  = new ExamPropQuery();
									query4.setSysPropId(Long.valueOf(p4.getProp_val_id()));
									ExamReturnProp rprop4 = localExamPropValFacade.getNextLevelProp(query4);
									
									List<ExamProp> p5list = rprop4.getReturnList();
									
									i++;
									
									label = new Label(0, i, p4.getProp_val_id().toString());
									wsheet.addCell(label);

									label = new Label(1, i, "单元");
									wsheet.addCell(label);
									
									
									label = new Label(2, i, p4.getName());
									wsheet.addCell(label);

									for (ExamProp p5: p5list){
										i++;
										
										label = new Label(0, i, p5.getProp_val_id().toString());
										wsheet.addCell(label);

										label = new Label(1, i, "知识点");
										wsheet.addCell(label);
										
										
										label = new Label(2, i, p5.getName());
										wsheet.addCell(label);
									}
									
								}
								
							}
							
						}
						
					}
					
				}
				//主题
				{
					WritableSheet wsheet = wwb.createSheet("主题", times+3);
					
					Label label = new Label(0, 0, "主题ID");
					wsheet.addCell(label);

					label = new Label(1, 0, "名称");
					wsheet.addCell(label);

					int i = 1;
					for(ExamProp e: p7list){
						label = new Label(0, i, e.getProp_val_id().toString());
						wsheet.addCell(label);

						label = new Label(1, i, e.getName());
						wsheet.addCell(label);
						i++;
					}
				}
				//来源
				{
					WritableSheet wsheet = wwb.createSheet("来源", times+4);
					
					Label label = new Label(0, 0, "来源ID");
					wsheet.addCell(label);

					label = new Label(1, 0, "名称");
					wsheet.addCell(label);

					int i = 1;
					for(ExamSource e: pSlist){
						label = new Label(0, i, e.getProp_val_id().toString());
						wsheet.addCell(label);

						label = new Label(1, i, e.getName());
						wsheet.addCell(label);
						i++;
					}
				}
				//职称
				{
					WritableSheet wsheet = wwb.createSheet("职称", times+5);
					
					Label label = new Label(0, 0, "职称ID");
					wsheet.addCell(label);

					label = new Label(1, 0, "名称");
					wsheet.addCell(label);

					int i = 1;
					for(ExamProp e: p9list){
						label = new Label(0, i, e.getProp_val_id().toString());
						wsheet.addCell(label);

						label = new Label(1, i, e.getName());
						wsheet.addCell(label);
						i++;
					}
				}
				//认知水平
				{
					WritableSheet wsheet = wwb.createSheet("认知水平", times+6);
					
					Label label = new Label(0, 0, "认知水平ID");
					wsheet.addCell(label);

					label = new Label(1, 0, "名称");
					wsheet.addCell(label);

					int i = 1;
					for(ExamProp e: p8list){
						label = new Label(0, i, e.getProp_val_id().toString());
						wsheet.addCell(label);

						label = new Label(1, i, e.getName());
						wsheet.addCell(label);
						i++;
					}
				}
			}
			
			wwb.write();
			wwb.close();
			os.close();
			response.flushBuffer();
		}
		
		return null;
	}

	private void getQuestion(WritableSheet wsheet, ExamQuestion q)
			throws WriteException, RowsExceededException {
		Label label;
		int rowNum = wsheet.getRows();

		label = new Label(0, rowNum, q.getId().toString());
		wsheet.addCell(label);

		label = new Label(1, rowNum, q.getParent_id().toString());
		wsheet.addCell(label);

		label = new Label(2, rowNum, q.getQuestion_label_id().toString());
		wsheet.addCell(label);
		
		label = new Label(3, rowNum, q.getContent());
		wsheet.addCell(label);
		
		String answerStr = "";
		Integer questionLabel = q.getQuestion_label_id();
		List<ExamQuestionKey> keyList = q.getQuestionKeyList();
		
		if(questionLabel == Constants.A1|| questionLabel == Constants.A2	// 单选题
				|| questionLabel == Constants.DXT) { // 多选题
			for(int i = 0; i < keyList.size(); i++){
				label = new Label(4+i, rowNum, keyList.get(i).getContent());
				wsheet.addCell(label);
				
				if (keyList.get(i).getIsnot_true() == 1){
					answerStr += String.valueOf((char)(65+i)).trim();
				}
			}
		}else if (questionLabel == Constants.TK
				|| questionLabel == Constants.JD
				|| questionLabel == Constants.MCJS
				|| questionLabel == Constants.NLFX) { // 填空题
			for(int i = 0; i < keyList.size(); i++){
				answerStr = keyList.get(i).getContent();
				break;
			}
		}else if (questionLabel == Constants.PD) { // 判断题
			for(int i = 0; i < keyList.size(); i++){
				answerStr = keyList.get(i).getIsnot_true().toString();
				break;
			}
		}
			
		label = new Label(10, rowNum, answerStr);
		wsheet.addCell(label);
		
		label = new Label(11, rowNum, q.getAnalyse());
		wsheet.addCell(label);
		
		Map<String, List<ExamQuestionProp>> propMap =  q.getQuestionPropMap();
		String propStr = getQuestionPropIds(propMap.get("1"));

		propStr += getQuestionPropIds(propMap.get("2"));
		propStr += getQuestionPropIds(propMap.get("3"));
		propStr += getQuestionPropIds(propMap.get("4"));
		propStr += getQuestionPropIds(propMap.get("5"));

		label = new Label(12, rowNum, propStr);
		wsheet.addCell(label);
		
		propStr = getQuestionPropIds(propMap.get("7"));
		label = new Label(13, rowNum, propStr);
		wsheet.addCell(label);
		
		propStr = getQuestionPropIds(propMap.get("10"));
		label = new Label(14, rowNum, propStr);
		wsheet.addCell(label);
		
		label = new Label(15, rowNum, q.getSource());
		wsheet.addCell(label);
		
		label = new Label(16, rowNum, q.getSurname());
		wsheet.addCell(label);
		
		propStr =  getQuestionPropIds(propMap.get("9"));
		label = new Label(17, rowNum, propStr);
		wsheet.addCell(label);
		
		propStr = getQuestionPropIds(propMap.get("8"));
		label = new Label(18, rowNum, propStr);
		wsheet.addCell(label);
		
		label = new Label(19, rowNum, q.getGrade().toString());
		wsheet.addCell(label);

		label = new Label(20, rowNum, q.getState()>1?"1":"0");
		wsheet.addCell(label);

		label = new Label(21, rowNum, q.getIsnot_multimedia().toString());
		wsheet.addCell(label);


	}
	
	@SuppressWarnings("unused")
	private String getQuestionPropIds(List<ExamQuestionProp>propList){
		
		if (propList == null) return "";
		List<Long> idArray = new ArrayList<Long>();
		for (ExamQuestionProp p: propList){
			idArray.add(p.getProp_val_id());
		}
		String res = idArray.toString();
		res = res.substring(1,res.length()-1);
		res = res.replaceAll(" ", "");
		return res;
	}
	
	@SuppressWarnings("unused")
	private void setPropValPoint(Map<String,List<ExamQuestionProp>> questionPropMap, int rownum,
			WritableSheet wsheet, String qid) throws WriteException,
			RowsExceededException {
		
		Label label;
		
		String[] propItems = {Constants.EXAM_PROP_UNIT, Constants.EXAM_PROP_ICD10, Constants.EXAM_PROP_POINT2, Constants.EXAM_PROP_COGNIZE, Constants.EXAM_PROP_SOURCE, Constants.EXAM_PROP_TITLE};
		
		for ( int i=0; i<propItems.length; i++) {
			List<ExamQuestionProp> propList = questionPropMap.get(propItems[i]);
			if (propList == null || propList.isEmpty()) continue;
			String propValPointId="";
			for(ExamQuestionProp prop : propList){
				if(propValPointId.equals("")){
					propValPointId = ""+prop.getProp_val_name();
				}else{
					propValPointId +=","+prop.getProp_val_name();
				}
			}
			label = new Label(8+i, rownum, propValPointId);
			wsheet.addCell(label);
		}
	}
}
