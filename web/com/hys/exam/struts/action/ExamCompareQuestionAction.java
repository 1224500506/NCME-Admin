package com.hys.exam.struts.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionImportTemplate;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-11-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamCompareQuestionAction extends BaseAction {
	
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

		DynaActionForm uploadForm = (DynaActionForm) form;

		FormFile file = (FormFile) uploadForm.get("uploadfile");
		
		String type = request.getParameter("type");
		
		if(null != file){
				
		if(file.getFileSize()>1024000){
			request.setAttribute("errMsg", "对比文件不能大于1M！");					
			return "FAILURE";
		}
		
		InputStream is = file.getInputStream();

		Workbook rwb = Workbook.getWorkbook(is);
		
		int errRow = 0;

		try {
			if (rwb.getNumberOfSheets() > 0) {
				
				List<ExamQuestion> questionList = new ArrayList<ExamQuestion>();
				
				List<ExamQuestionImportTemplate> examQuestionImportTemplateList = new ArrayList<ExamQuestionImportTemplate>();
				
				Sheet sheet = rwb.getSheet(0);
				// sheet 列数
				int sheetCount = getSheetColumnsLength(sheet);
				// sheet 行数
				int rowNum = getSheetRowLength(sheet);				
				
				if(sheetCount != 22){
					
					request.setAttribute("errMsg", "文件格试有误！");					
					return "FAILURE";
				}
				if(rowNum > 1000){
					
					request.setAttribute("errMsg", "总试数不能大于1000道！");					
					return "FAILURE";
				}

				for (int i = 1; i < rowNum; i++) {
					
					errRow = i;
					
					ExamQuestion examQuestion = new ExamQuestion();
					
					ExamQuestionImportTemplate qt = new ExamQuestionImportTemplate();
					
					// 试题ID
					Long id = sheet.getCell(0,i).getContents().trim().equals("")? 0: Long.parseLong(sheet.getCell(0,i).getContents().trim());
					
					// 父试题ID
					Long parentId = sheet.getCell(1,i).getContents().trim().equals("")? 0: Long.parseLong(sheet.getCell(1,i).getContents());
				
					// 题型ID
					int questionLabel = sheet.getCell(2,i).getContents().trim().equals("")? 0:Integer.parseInt(sheet.getCell(2,i).getContents().trim());
					
					if(questionLabel == 0){
						request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID有误，导入失败！");
						return "FAILURE";
					}
					
					// 题干
					String content = replaceBlank(sheet.getCell(3,i).getContents().trim());
					
					String answerA = sheet.getCell(4,i).getContents().trim();
					String answerB = sheet.getCell(5,i).getContents().trim();
					String answerC = sheet.getCell(6,i).getContents().trim();
					String answerD = sheet.getCell(7,i).getContents().trim();
					String answerE = sheet.getCell(8,i).getContents().trim();
					String answerF = sheet.getCell(9,i).getContents().trim();
					String answer = sheet.getCell(10,i).getContents().trim();
					
					String analyse = sheet.getCell(11,i).getContents().trim();
					
					String grade = sheet.getCell(12,i).getContents().trim();
					
					String differ = sheet.getCell(13,i).getContents().trim();
					
					String source = sheet.getCell(14,i).getContents().trim();
					
					String author = sheet.getCell(15,i).getContents().trim();
					
					String question_type = sheet.getCell(16,i).getContents().trim();
					
					String point2 = sheet.getCell(17,i).getContents().trim();
					
					String cognize = sheet.getCell(18,i).getContents().trim();
					
					String title = sheet.getCell(19,i).getContents().trim();
					
					String point = sheet.getCell(20,i).getContents().trim();
					

					String isnot_multimedia = sheet.getCell(21,i).getContents().trim();

					
					qt.setId(id);
					qt.setParent_id(parentId);
					qt.setQuestionLabel(questionLabel);
					qt.setContent(content);
					qt.setAnswerA(answerA);
					qt.setAnswerB(answerB);
					qt.setAnswerC(answerC);
					qt.setAnswerD(answerD);
					qt.setAnswerE(answerE);
					qt.setAnswerF(answerF);
					qt.setAnswer(answer);
					qt.setAnalyse(analyse);
					qt.setGrade(grade);
					qt.setDiffer(differ);
					qt.setSource(source);
					qt.setAuthor(author);
					qt.setType(question_type);
					qt.setPoint2(point2);
					qt.setCognize(cognize);
					qt.setTitle(title);
					qt.setPoint(point);
					qt.setStatus(0);
					qt.setIsnot_multimedia(isnot_multimedia);
					examQuestionImportTemplateList.add(qt);
					
					if(parentId.intValue() == 0){
						
						//行号
						examQuestion.setId((long)(errRow+1));
						// 试题类型
						examQuestion.setQuestion_label_id(Integer.valueOf(questionLabel));
						// 父试题ID
						examQuestion.setParent_id(0L);
						
						// 题干内容
						if(sheet.getCell(3,i).getContents().trim().equals("")){
							
							request.setAttribute("errMsg", "第" + (errRow+1) + "行试题内容不能为空，导入失败！");
							return "FAILURE";
						}
						examQuestion.setContent(content);
						
						//标识为检查试题
						examQuestion.setState(99);
						// 加入试题列表
						questionList.add(examQuestion);
						
					}
				}
				
				if(type.equals("F")){
					
					localExamQuestionFacade.getCompareQuestion2(examQuestionImportTemplateList);
					
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition","attachment;filename=\"examQuestionList.xls\"");
					OutputStream os = response.getOutputStream();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					
					//新建一张表
					WritableSheet wsheet = wwb.createSheet("Sheet", 0);
				
					Label label = new Label(0, 0, "系统编号(必填)");
					wsheet.addCell(label);
					
					label = new Label(1, 0, "父试题编号");
					wsheet.addCell(label);
					
					label = new Label(2, 0, "题型ID(必填)");
					wsheet.addCell(label);
					
					label = new Label(3, 0, "试题内容(必填)");
					wsheet.addCell(label);
					
					label = new Label(4, 0, "可选答案A");
					wsheet.addCell(label);
					
					label = new Label(5, 0, "可选答案B");
					wsheet.addCell(label);
					
					label = new Label(6, 0, "可选答案C");
					wsheet.addCell(label);
					
					label = new Label(7, 0, "可选答案D");
					wsheet.addCell(label);
					
					label = new Label(8, 0, "可选答案E");
					wsheet.addCell(label);	
					
					label = new Label(9, 0, "可选答案F");
					wsheet.addCell(label);		
					
					label = new Label(10, 0, "答案(必填)");
					wsheet.addCell(label);	
					
					label = new Label(11, 0, "试题分析");
					wsheet.addCell(label);	
					
					label = new Label(12, 0, "难度");
					wsheet.addCell(label);		
					
					label = new Label(13, 0, "区分度");
					wsheet.addCell(label);
					
					label = new Label(14, 0, "试题来源");
					wsheet.addCell(label);			
					
					label = new Label(15, 0, "创建者(必填)");
					wsheet.addCell(label);	
					
					label = new Label(16, 0, "被收录题库");
					wsheet.addCell(label);				
					
					label = new Label(17, 0, "副知识点");
					wsheet.addCell(label);	
					
					label = new Label(18, 0, "认识不平");
					wsheet.addCell(label);			
					
					label = new Label(19, 0, "职称");
					wsheet.addCell(label);	
					
					label = new Label(20, 0, "知识点");
					wsheet.addCell(label);	
					
					label = new Label(21, 0, "是否为多媒体试题");
					wsheet.addCell(label);	
					
					for(int i=0;i<examQuestionImportTemplateList.size();i++){
						ExamQuestionImportTemplate qit = examQuestionImportTemplateList.get(i);
						getQuestion(wsheet, qit);
					}
					
					wwb.write();
					wwb.close();
					os.close();
					response.flushBuffer();
					
					return null;
					
				} else {
					if(questionList!= null && questionList.size()>0){
						
						List<ExamQuestion> resultList = localExamQuestionFacade.getCompareQuestion(questionList);
						
						if(null==resultList){
							request.setAttribute("errMsg", "没有匹配的数据！");	
							return "FAILURE";
						}else{
							request.setAttribute("srcQuestionList", questionList);
							request.setAttribute("resultList", resultList);
						}
						
					}else{
						request.setAttribute("errMsg", "没有找到可用的数据！");
						return "FAILURE";
					}
				}
			}
			
		} catch (Exception e) {
			request.setAttribute("errMsg", "第" +  (errRow+1) + "行数据格式错误，导入失败！");
			return "FAILURE";
		} finally {
			is.close();
			rwb.close();
		}
		
	}
		
		return "SUCCESS";
	}

	private void getQuestion(WritableSheet wsheet, ExamQuestionImportTemplate q)
			throws WriteException, RowsExceededException {
		Label label;
		int rowNum = wsheet.getRows();
		int labelId = q.getQuestionLabel();

		
		jxl.write.WritableCellFormat child = new jxl.write.WritableCellFormat();
		child.setBackground(jxl.format.Colour.AQUA);
		
		jxl.write.WritableCellFormat cf = new jxl.write.WritableCellFormat();
		cf.setBackground(jxl.format.Colour.RED);
		

		if(q.getStatus() == 1){
			// 系统编号
			label = new Label(0, rowNum, String.valueOf(q.getId()), cf);
			wsheet.addCell(label);
		} else {
			// 系统编号
			label = new Label(0, rowNum, String.valueOf(q.getId()));
			wsheet.addCell(label);
		}
		
		// 父编号
		label = new Label(1, rowNum, String.valueOf(q.getParent_id()));
		wsheet.addCell(label);
		
		// 题型
		label = new Label(2, rowNum, String.valueOf(labelId));
		wsheet.addCell(label);
		
		// 试题内容
		label = new Label(3, rowNum, q.getContent());
		wsheet.addCell(label);

		//A
		label = new Label(4, rowNum, q.getAnswerA());
		wsheet.addCell(label);
		
		//B
		label = new Label(5, rowNum, q.getAnswerB());
		wsheet.addCell(label);
		
		//C
		label = new Label(6, rowNum, q.getAnswerC());
		wsheet.addCell(label);
		
		//D
		label = new Label(7, rowNum, q.getAnswerD());
		wsheet.addCell(label);
		
		//E
		label = new Label(8, rowNum, q.getAnswerE());
		wsheet.addCell(label);
		
		//F
		label = new Label(9, rowNum, q.getAnswerF());
		wsheet.addCell(label);
		
		//Answer
		label = new Label(10, rowNum, q.getAnswer());
		wsheet.addCell(label);
		
		label = new Label(11, rowNum, q.getAnalyse());
		wsheet.addCell(label);
		
		label = new Label(12, rowNum, q.getGrade());
		wsheet.addCell(label);
		
		label = new Label(13, rowNum, q.getDiffer());
		wsheet.addCell(label);
		
		label = new Label(14, rowNum, q.getSource());
		wsheet.addCell(label);
		
		label = new Label(15, rowNum, q.getAuthor());
		wsheet.addCell(label);
		
		label = new Label(16, rowNum, q.getType());
		wsheet.addCell(label);
		
		label = new Label(17, rowNum, q.getPoint2());
		wsheet.addCell(label);
		
		label = new Label(18, rowNum, q.getCognize());
		wsheet.addCell(label);
		
		label = new Label(19, rowNum, q.getTitle());
		wsheet.addCell(label);
		
		label = new Label(20, rowNum, q.getPoint());
		wsheet.addCell(label);
		
		label = new Label(21, rowNum, q.getIsnot_multimedia());
		wsheet.addCell(label);
		
		
	}

	/**
	 * 计算sheet的行数
	 * 
	 * @param sheet
	 *            Excel 工作表
	 * @return
	 * @return: int
	 */
	private int getSheetRowLength(Sheet sheet) {
		for (int i = 1; i < sheet.getRows(); i++) {
			if (sheet.getCell(0, i).getContents().trim().equals("")) {
				return i;
			}
		}
		return sheet.getRows();
	}

	/**
	 * 
	 * 计算sheet的列数
	 * 
	 * @param sheet
	 *            Excel工作表
	 * @return: int
	 */
	private int getSheetColumnsLength(Sheet sheet) {
		for (int i = 1; i < sheet.getColumns(); i++) {
			if (sheet.getCell(i, 0).getContents().trim().equals("")) {
				return i;
			}
		}
		return sheet.getColumns();
	}

	private static String replaceBlank(String str) {
		Pattern p = Pattern.compile("\r|\n");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}
	
	private static boolean compareQuestion(List<ExamQuestionImportTemplate> questionList){
		boolean flag = false;
		Map<String,String> em = new HashMap<String,String>();
		
		for(ExamQuestionImportTemplate q : questionList) {
			String key = q.getContent()+"_"+q.getQuestionLabel();
			if(em.containsKey(key)){
				flag = true;
				break;
			}else{
				em.put(key, "");
			}
		}
		return flag;
	}
}
