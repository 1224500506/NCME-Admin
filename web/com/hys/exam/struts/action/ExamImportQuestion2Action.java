package com.hys.exam.struts.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.BeanUtils;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.model.ExamRelationProp;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-24
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamImportQuestion2Action extends BaseAction {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamPropValFacade localExamPropValFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
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
	
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm uploadForm = (DynaActionForm) form;

		FormFile file = (FormFile) uploadForm.get("uploadfile");

		if (file==null)
			return "FAILURE";
		
		if(file.getFileSize()>1024000){
			request.setAttribute("errMsg", "导入文件不能大于1M！");					
			return "FAILURE";
		}
		
		String fileName = file.getFileName();
		if(fileName.indexOf(".xlsx")>-1){
			request.setAttribute("errMsg", "请使用后缀名为.xls的文件！");					
			return "FAILURE";
		}
		
		InputStream is = file.getInputStream();

		Workbook rwb = Workbook.getWorkbook(is);
		
		int errRow = 0;

		try {

			if (rwb.getNumberOfSheets() > 0) {
				
				List<ExamQuestion> questionList = new ArrayList<ExamQuestion>();
				
				List<ExamQuestion> compareList = new ArrayList<ExamQuestion>();
				
				Sheet sheet = rwb.getSheet(0);
				// sheet 列数
				int sheetCount = getSheetColumnsLength(sheet);
				
				// sheet 行数
				int rowNum = getSheetRowLength(sheet);				
				
				if(sheetCount != 22){
 					request.setAttribute("errMsg", "导入文件格试有误！");					
					return "FAILURE";
				}
				if(rowNum > 1000){
					request.setAttribute("errMsg", "总导入试数不能大于1000道！");					
					return "FAILURE";
				}
				
				//关联属性
				//Map<String,ExamRelationProp> relPropMap = localExamPropValFacade.getExamRelationPropAll();
				
				//试题主题
				List<Long> p7list = localExamPropValFacade.getExamPropTypeList(7);
				
				//试题认知水平
				List<Long> p8list = localExamPropValFacade.getExamPropTypeList(8);
				
				//试题职称表
				List<Long> p9list = localExamPropValFacade.getExamPropTypeList(9);
				
//////////////////////////////////////////////////////////////////////
				for (int i = 1; i < rowNum; i++) {
					
					errRow = i;
					
					ExamQuestion examQuestion = new ExamQuestion();
					
					ExamQuestion compareQuestion = new ExamQuestion();
					
					ExamQuestionPropValCascade propValCascade = new ExamQuestionPropValCascade();
					
					List<ExamSubSysQuestType> subSysQuestTypeList = new ArrayList<ExamSubSysQuestType>();
					
					Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
					
					List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();					
					
					// 父试题ID
					Long parentId = sheet.getCell(1,i).getContents().trim().equals("")? 0: Long.parseLong(sheet.getCell(1,i).getContents().trim());
					// 题型ID
					int questionLabel = sheet.getCell(2,i).getContents().trim().equals("")? 0:Integer.parseInt(sheet.getCell(2,i).getContents().trim());
					
					//被收入题库
					String msg = "";
/*					String questType = sheet.getCell(16,i).getContents().trim();
					if(questType.equals("")){
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,试题分类未填写，导入失败！");
						return "FAILURE";
					}
					msg = setQuestType(questType,(errRow+1),subSysQuestTypeList);
					if(!msg.equals("")){
						request.setAttribute("errMsg", msg);
						return "FAILURE";							
					}					
*/					
					//主题
					String questPoint2 = sheet.getCell(13,i).getContents().trim();
					if(!questPoint2.equals("")){
						msg = setProp(questPoint2,(errRow+1),Constants.EXAM_PROP_POINT2, questionPropMap, p7list);
						if(!msg.equals("")){
							request.setAttribute("errMsg", msg);
							return "FAILURE";							
						}
					}
					
					
					//篇名
					String pianMing = sheet.getCell(16,i).getContents().trim();
					examQuestion.setSurname(pianMing);
					compareQuestion.setSurname(pianMing);
					
					//认知水平
					String questCognize = sheet.getCell(18,i).getContents().trim();
/*					if(questCognize.equals("")){
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,认知水平未填写，导入失败！");
						return "FAILURE";
					}
*/					msg = setProp(questCognize,(errRow+1),Constants.EXAM_PROP_COGNIZE, questionPropMap, p8list);
/*					if(!msg.equals("")){
						request.setAttribute("errMsg", msg);
						return "FAILURE";							
					}					
*/					
					
					//职称
					String questTitle = sheet.getCell(17,i).getContents().trim();
					if(questTitle.equals("")){
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,职称未填写，导入失败！");
						return "FAILURE";
					}
					msg = setProp(questTitle,(errRow+1),Constants.EXAM_PROP_TITLE, questionPropMap, p9list);
					if(!msg.equals("")){
						request.setAttribute("errMsg", msg);
						return "FAILURE";							
					}					
					
					//来源
					String questSource = sheet.getCell(14,i).getContents().trim();
					if(questSource.equals("")){
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,来源未填写，导入失败！");
						return "FAILURE";
					}
					msg = setProp(questSource,(errRow+1),Constants.EXAM_PROP_SOURCE, questionPropMap, p9list);
					if(!msg.equals("")){
						request.setAttribute("errMsg", msg);
						return "FAILURE";							
					}					
					
					//学科
					String questPoint = sheet.getCell(12,i).getContents().trim();
					if(questPoint.equals("")){
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,学科未填写，导入失败！");
						return "FAILURE";
					}
					msg = setRelProp(questPoint,(errRow+1), propValCascade, questionPropMap, null);
					if(!msg.equals("")){
						request.setAttribute("errMsg", msg);
						return "FAILURE";							
					}						
					
					
					if(questionLabel == 0){
						request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID有误，导入失败！");
						return "FAILURE";
					}
					
					if(parentId.intValue() == 0){
						// 试题选项
						String[] result= new String[6];
						// 选项个数
						int num = 0;
						for (int k = 0; k < result.length; k++) {
							Cell cell = sheet.getCell(k+4,i);
							result[k]= cell.getContents().trim();
							if(cell.getContents().trim().equals("")){
								num = k;
								break;
							}
							if(k == result.length -1){
								num = result.length;
							}
						}
						
						compareQuestion.setId((long)(errRow+1));
						
						// 试题类型
						examQuestion.setQuestion_label_id(Integer.valueOf(questionLabel));
						compareQuestion.setQuestion_label_id(Integer.valueOf(questionLabel));
						
						// 父试题ID
						examQuestion.setParent_id(0L);
						
						// 题干内容
						if(sheet.getCell(3,i).getContents().trim().equals("")){
							request.setAttribute("errMsg", "第" + (errRow+1) + "行试题内容不能为空，导入失败！");
							return "FAILURE";
						}
						examQuestion.setContent(replaceBlank(sheet.getCell(3,i).getContents().trim()));
						compareQuestion.setContent(replaceBlank(sheet.getCell(3,i).getContents().trim()));
						
						// 试题状态
						examQuestion.setState(0);
						// 对比状态
						compareQuestion.setState(99);
						// 试题难度
						examQuestion.setGrade(Integer.parseInt(sheet.getCell(19,i).getContents().trim().equals("")?"0":sheet.getCell(19,i).getContents().trim()));
						// 试题区分度
				//		examQuestion.setDiffer(Integer.parseInt(sheet.getCell(13,i).getContents().trim().equals("")?"0":sheet.getCell(13,i).getContents().trim()));
						// 试题分析
						examQuestion.setAnalyse(replaceBlank(sheet.getCell(11,i).getContents().trim()));
						// 试题来源
						examQuestion.setSource(replaceBlank(sheet.getCell(15,i).getContents().trim()));
						// 试题顺序
						examQuestion.setSeq(0);
						//是否为多媒体试题
						examQuestion.setIsnot_multimedia(Integer.parseInt(sheet.getCell(21,i).getContents().trim().equals("")?"0":sheet.getCell(21,i).getContents().trim()));
						
						// 创建者
/*						if(sheet.getCell(15,i).getContents().trim().equals("")){
							request.setAttribute("errMsg", "第" + (errRow+1) + "行,创建者不能为空，导入失败！");
							return "FAILURE";								
						}
						examQuestion.setAuthor(sheet.getCell(15,i).getContents().trim());
*/						
						// 试题属性
						examQuestion.setQuestionPropMap(questionPropMap);
						
						examQuestion.setQuestPropValCascade(propValCascade);
						
						// 试题分类
						setQuestType("1",(errRow+1),subSysQuestTypeList);
						examQuestion.setSubSysQuestTypeList(subSysQuestTypeList);
						
						if(questionLabel != Constants.A3 && questionLabel != Constants.B1){
							
							if(sheet.getCell(10,i).getContents().trim().equals("")){
								request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为空，导入失败！");
								return "FAILURE";
							} else if(questionLabel == Constants.A1 || 
									questionLabel == Constants.A2 || 
									questionLabel == Constants.A3_1 ||
									questionLabel == Constants.A3_2 ||
									questionLabel == Constants.B1_1 ||
									questionLabel == Constants.DXT) {
								if(!isEnna(sheet.getCell(10,i).getContents().trim())){
									request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为全角字符，导入失败！");
									return "FAILURE";
								}								
							}
							
						}
						
						if(questionLabel == Constants.A1|| questionLabel == Constants.A2) { // 单选题
							for (int k = 0; k < num; k++) {
								ExamQuestionKey key = new ExamQuestionKey();
								key.setContent(replaceBlank(result[k]));
								if (String.valueOf((char)(65+k)).trim().equals(sheet.getCell(10,i).getContents().trim().toUpperCase())) {
									key.setIsnot_true(1);
								} else {
									key.setIsnot_true(0);
								}
								key.setSeq(k + 1);
								keyList.add(key);
							}
						}else if (questionLabel == Constants.DXT) { // 多选题 
							for (int k = 0; k < num; k++) {
								ExamQuestionKey key = new ExamQuestionKey();
								key.setContent(replaceBlank(result[k]));
								if (sheet.getCell(10,i).getContents().trim().toUpperCase().indexOf(String.valueOf((char)(65+k)).trim())!= -1) {
									key.setIsnot_true(1);
								} else {
									key.setIsnot_true(0);
								}
								key.setSeq(k + 1);
								keyList.add(key);
							}
						}else if (questionLabel == Constants.TK
								|| questionLabel == Constants.JD
								|| questionLabel == Constants.MCJS
								|| questionLabel == Constants.NLFX) { // 填空题
							ExamQuestionKey key = new ExamQuestionKey();
							key.setContent(replaceBlank(sheet.getCell(10,i).getContents().trim()));
							key.setSeq(1);
							keyList.add(key);
						}else if (questionLabel == Constants.PD) { // 判断题
							ExamQuestionKey key = new ExamQuestionKey();
							key.setIsnot_true(Integer.parseInt(sheet.getCell(10,i).getContents().trim().toUpperCase()));
							key.setSeq(1);
							keyList.add(key);
						}else if (questionLabel == Constants.B1){
							//处理B1子试题
							List<ExamQuestion> childList = new ArrayList<ExamQuestion>();
							for (int j = 1; j < rowNum; j++) {
								if(sheet.getCell(1,j).getContents().trim().equals(sheet.getCell(0,i).getContents().trim())){
									errRow = j;
									int childLabel = sheet.getCell(2,j).getContents().trim().equals("")? 0:Integer.parseInt(sheet.getCell(2,j).getContents().trim());
									if(childLabel == 0){
										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID有误，导入失败！");
										return "FAILURE";
									}
									if(sheet.getCell(10,j).getContents().trim().equals("")){
										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为空，导入失败！");
										return "FAILURE";
									}
									ExamQuestion childQuestion = new ExamQuestion();

									// 试题类型								
									if(childLabel == Constants.B1_1){
										childQuestion.setQuestion_label_id(Integer.valueOf(Constants.B1_1));
									}else{
										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID不是第" + (i+1) + "行数据的子试题题型，导入失败！");
										return "FAILURE";
									}									
									if(sheet.getCell(3,j).getContents().trim().equals("")){
										request.setAttribute("errMsg", "第" + (errRow+1) + "行试题内容不能为空，导入失败！");
										return "FAILURE";
									}
									// 题干内容
									childQuestion.setContent(replaceBlank(sheet.getCell(3,j).getContents().trim()));
									// 试题分析
									childQuestion.setAnalyse(sheet.getCell(11,j).getContents().trim());
									// 试题状态
									childQuestion.setState(5);
									// 试题难度
									childQuestion.setGrade(Integer.parseInt(sheet.getCell(19,j).getContents().trim().equals("")?"0":sheet.getCell(19,j).getContents().trim()));
									// 试题区分度
							//		childQuestion.setDiffer(Integer.parseInt(sheet.getCell(13,j).getContents().trim().equals("")?"0":sheet.getCell(13,j).getContents().trim()));
									// 试题来源
									childQuestion.setSource(sheet.getCell(15,j).getContents().trim());
									// 试题顺序
									childQuestion.setSeq(childList.size()+1);
									// 创建者
							//		childQuestion.setAuthor(sheet.getCell(15,j).getContents().trim());
									//是否为多媒体试题
									childQuestion.setIsnot_multimedia(examQuestion.getIsnot_multimedia());
									
									// 试题答案
									List<ExamQuestionKey> childKeyList = new ArrayList<ExamQuestionKey>();
									ExamQuestionKey childkey = new ExamQuestionKey();
									// 答案
									childkey.setContent(replaceBlank(sheet.getCell(10, j).getContents().trim()));
									// 是否正确
									childkey.setIsnot_true(1);
									// 顺序
									childkey.setSeq(1);
									childKeyList.add(childkey);
									// 子试题选项答案
									childQuestion.setQuestionKeyList(childKeyList);
									// 子试题属性
									childQuestion.setQuestionPropMap(questionPropMap);
									ExamQuestionPropValCascade childPropValCascade = new ExamQuestionPropValCascade();
									BeanUtils.copyProperties(propValCascade,childPropValCascade);
									childQuestion.setQuestPropValCascade(childPropValCascade);
									// 子试题分类
									List<ExamSubSysQuestType> childQuestTypeList = new ArrayList<ExamSubSysQuestType>();
									for(ExamSubSysQuestType tmp : childQuestTypeList){
										ExamSubSysQuestType nt = new ExamSubSysQuestType();
										BeanUtils.copyProperties(tmp,nt);
										childQuestTypeList.add(nt);
										
									}
									childQuestion.setSubSysQuestTypeList(childQuestTypeList);
									
									childList.add(childQuestion);
								}
							}
							errRow = i;
							// 如果没有子试题
							if(childList.size()==0){
								request.setAttribute("errMsg", "第" + (errRow+1) + "行数据没有子试题，导入失败！");
								return "FAILURE";
							}
							// 子试题列表
							examQuestion.setChildQuestionList(childList);
						} else if(questionLabel == Constants.A3){
							//处理A3子试题
							List<ExamQuestion> childList = new ArrayList<ExamQuestion>();
							for (int j = 1; j < rowNum; j++) {
								if(sheet.getCell(1,j).getContents().trim().equals(sheet.getCell(0,i).getContents().trim())){
									errRow = j;
									int childLabel = sheet.getCell(2,j).getContents().trim().equals("")? 0:Integer.parseInt(sheet.getCell(2,j).getContents().trim());
									if(childLabel == 0){
										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID有误，导入失败！");
										return "FAILURE";
									}
									if(sheet.getCell(10,j).getContents().trim().equals("")){
										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为空，导入失败！");
										return "FAILURE";
									}
									ExamQuestion childQuestion = new ExamQuestion();								
									// 试题类型
									if(questionLabel == Constants.A3 && (childLabel == Constants.A3_1 ||childLabel == Constants.A3_2)){
										childQuestion.setQuestion_label_id(Integer.valueOf(childLabel));
									}else{
										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID不是第" + (i+1) + "行数据的子试题题型，导入失败！");
										return "FAILURE";
									}
									if(sheet.getCell(3,j).getContents().trim().equals("")){
										request.setAttribute("errMsg", "第" + (errRow+1) + "行试题内容不能为空，导入失败！");
										return "FAILURE";
									}
									// 题干内容
									childQuestion.setContent(replaceBlank(sheet.getCell(3,j).getContents().trim()));
									// 试题分析
									childQuestion.setAnalyse(sheet.getCell(11,j).getContents().trim());
									// 试题状态
									childQuestion.setState(5);
									// 试题难度
									childQuestion.setGrade(Integer.parseInt(sheet.getCell(19,j).getContents().trim().equals("")?"0":sheet.getCell(19,j).getContents().trim()));
									// 试题区分度
						//			childQuestion.setDiffer(Integer.parseInt(sheet.getCell(13,j).getContents().trim().equals("")?"0":sheet.getCell(13,j).getContents().trim()));
									// 试题来源
									childQuestion.setSource(sheet.getCell(15,j).getContents().trim());
									// 试题顺序
									childQuestion.setSeq(childList.size()+1);										
									// 创建者
						//			childQuestion.setAuthor(sheet.getCell(15,j).getContents().trim());
									//是否为多媒体试题
									childQuestion.setIsnot_multimedia(examQuestion.getIsnot_multimedia());
									
									//子试题选项和答案
									List<ExamQuestionKey> childKeyList = new ArrayList<ExamQuestionKey>();
									for (int k = 0; k < 6; k++) {
										if(sheet.getCell(k+4,j).getContents().trim().equals("")){
											break;
										}
										ExamQuestionKey childkey = new ExamQuestionKey();
										// 答案
										childkey.setContent(replaceBlank(sheet.getCell(k+4,j).getContents().trim()));
										// 是否正确
										if(questionLabel == Constants.A3){
											if(childLabel == Constants.A3_1){
												if (String.valueOf((char)(65+k)).trim().equals(sheet.getCell(10,j).getContents().trim().toUpperCase())) {
													childkey.setIsnot_true(1);
												}else{
													childkey.setIsnot_true(0);
												}
											}else if(childLabel == Constants.A3_2){
												if (sheet.getCell(10,j).getContents().trim().toUpperCase().indexOf(String.valueOf((char)(65+k)).trim())!= -1) {
													childkey.setIsnot_true(1);
												}else{
													childkey.setIsnot_true(0);
												}
											}else{
												request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID不是第" + (i+1) + "行数据的子试题题型，导入失败！");
												return "FAILURE";
											}
										}
										// 顺序
										childkey.setSeq(k + 1);
										
										childKeyList.add(childkey);
									}
									// 子试题选项答案
									childQuestion.setQuestionKeyList(childKeyList);
									// 子试题属性
									childQuestion.setQuestionPropMap(questionPropMap);
									//
									ExamQuestionPropValCascade childPropValCascade = new ExamQuestionPropValCascade();
									BeanUtils.copyProperties(propValCascade,childPropValCascade);
									childQuestion.setQuestPropValCascade(childPropValCascade);
									// 子试题分类
									List<ExamSubSysQuestType> childQuestTypeList = new ArrayList<ExamSubSysQuestType>();
									for(ExamSubSysQuestType tmp : childQuestTypeList){
										ExamSubSysQuestType nt = new ExamSubSysQuestType();
										BeanUtils.copyProperties(tmp,nt);
										childQuestTypeList.add(nt);
									}
									childQuestion.setSubSysQuestTypeList(childQuestTypeList);
									
									childList.add(childQuestion);
								}
							}
							errRow = i;
							// 如果没有A3子试题
							if(childList.size()==0){
								request.setAttribute("errMsg", "第" + (errRow+1) + "行数据没有子试题，导入失败！");
								return "FAILURE";
							}
							// 子试题列表
							examQuestion.setChildQuestionList(childList);
						}
						// 试题答案选项
						examQuestion.setQuestionKeyList(keyList);
						// 加入试题列表
						questionList.add(examQuestion);
						compareList.add(compareQuestion);
					}
				}
				
			
				
				if(questionList!= null && questionList.size()>0){
					
					if(compareQuestion(questionList)){
						request.setAttribute("errMsg", "导入文件内有重复试题请检查！");	
						return "FAILURE";
					}
					
					List<ExamQuestion> resultList = localExamQuestionFacade.getCompareQuestion(compareList);
					
					//判断是否有重复试题
					if(null==resultList || resultList.isEmpty()){
						
						localExamQuestionFacade.addImportQuestion(questionList);
					}else{
						for(ExamQuestion eq : resultList){
							System.out.println("id:"+eq.getId()+"===content:"+eq.getContent()+";laber_id:"+eq.getQuestion_label_id());
							logger.warn("id:"+eq.getId()+"===content:"+eq.getContent()+";laber_id:"+eq.getQuestion_label_id());
						}
						request.setAttribute("errMsg", "有重复试题请检查！");	
						return "FAILURE";
					}					
				}else{
					request.setAttribute("errMsg", "没有找到可导入的数据！");
					return "FAILURE";
				}	
//////////////////////////////////////////////////////////////////////
/*				
				for (int i = 1; i < rowNum; i++) {
					
					ExamQuestion quest = new ExamQuestion();
					String userName = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
					
					String[] courseNames = sheet.getCell(0,i).getContents().trim().split(",");
					String[] ICD10Names = sheet.getCell(1,i).getContents().trim().split(","); 
					String[] subjectNames = sheet.getCell(2,i).getContents().trim().split(",");
					String[] sourceNames = sheet.getCell(3,i).getContents().trim().split(",");
					String[] sectorNames = sheet.getCell(4,i).getContents().trim().split(","); 
					String[] cognizeNames = sheet.getCell(5,i).getContents().trim().split(","); 
					String labelName  = sheet.getCell(6,i).getContents().trim(); 
					int grade = Integer.parseInt(sheet.getCell(7,i).getContents().trim()); 
					int upLoadAvaiable = sheet.getCell(8,i).getContents().trim().equals("不") ? 0 : 1; 
					int multiMedia = sheet.getCell(9,i).getContents().trim().equals("不") ? 0 : 1; 
					String content = sheet.getCell(10,i).getContents(); 
					String analyse = sheet.getCell(11,i).getContents(); 
					
					
					List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
					
					ExamQuestionForm qform = new ExamQuestionForm();
					
//					ExamQuestion quest = new ExamQuestion();
//					List<ExamSubSysQuestType> subSysQuestTypeList = new ArrayList<ExamSubSysQuestType>();
//					List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();
//					Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
					
					Map<String,List<ExamPropVal>> propMap1 = localExamPropValFacade.getSysPropValBySysId();
					
					List<ExamPropVal> questPropUnit = new ArrayList<ExamPropVal>();
					List<ExamPropVal> questPropICD10 = new ArrayList<ExamPropVal>();
					List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
					List<ExamPropVal> questPropSource = new ArrayList<ExamPropVal>();
					List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
					List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
					
					for (Iterator iter = propMap1.entrySet().iterator(); iter.hasNext();) {
						Map.Entry entry = (Map.Entry) iter.next();
						if (entry.getKey().equals(Constants.EXAM_PROP_UNIT)){
							questPropUnit = (List<ExamPropVal>)entry.getValue();
						} else if(entry.getKey().equals(Constants.EXAM_PROP_ICD10)){
							questPropICD10 = (List<ExamPropVal>)entry.getValue();
						} else if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
							questPropPoint2 = (List<ExamPropVal>)entry.getValue();
						} else if(entry.getKey().equals(Constants.EXAM_PROP_SOURCE)){
							questPropSource = (List<ExamPropVal>)entry.getValue();
						} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
							questPropTitle = (List<ExamPropVal>)entry.getValue();
						} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
							questPropCognize =(List<ExamPropVal>)entry.getValue();
						}
					}
					
					int t, k;
					Long[] courseIds = new Long[courseNames.length];
					for (t=0; t<questPropUnit.size(); t++) {
						for (k=0; k<courseNames.length; k++) {
							if (questPropUnit.get(t).getName().equals(courseNames[k]))
								courseIds[k] = questPropUnit.get(t).getId();
						}
					}
					
					Long[] ICD10Ids = new Long[ICD10Names.length];
					for (t=0; t<questPropICD10.size(); t++) {
						for (k=0; k<ICD10Names.length; k++) {
							if (questPropICD10.get(t).getName().equals(ICD10Names[k]))
								ICD10Ids[k] = questPropICD10.get(t).getId();
						}
					}
					
					Long[] subjectIds = new Long[subjectNames.length];
					for (t=0; t<questPropPoint2.size(); t++) {
						for (k=0; k<subjectNames.length; k++) {
							if (questPropPoint2.get(t).getName().equals(subjectNames[k]))
								subjectIds[k] = questPropPoint2.get(t).getId();
						}
					}
					
					Long[] sourceIds = new Long[sourceNames.length];
					for (t=0; t<questPropSource.size(); t++) {
						for (k=0; k<sourceNames.length; k++) {
							if (questPropSource.get(t).getName().equals(sourceNames[k]))
								sourceIds[k] = questPropSource.get(t).getId();
						}
					}
					
					Long[] sectorIds = new Long[sectorNames.length];
					for (t=0; t<questPropTitle.size(); t++) {
						for (k=0; k<sectorNames.length; k++) {
							if (questPropTitle.get(t).getName().equals(sectorNames[k]))
								sectorIds[k] = questPropTitle.get(t).getId();
						}
					}
					
					Long[] cognizeIds = new Long[cognizeNames.length];
					for (t=0; t<questPropCognize.size(); t++) {
						for (k=0; k<cognizeNames.length; k++) {
							if (questPropCognize.get(t).getName().equals(cognizeNames[k]))
								cognizeIds[k] = questPropCognize.get(t).getId();
						}
					}
					
					int question_label_id = 0;
					for (t=0; t<questLabel.size(); t++) {
						if (questLabel.get(t).getName().equals(labelName))
							question_label_id =questLabel.get(t).getId(); 
					}
					
					qform.setQuestUnit_4(courseIds);
					qform.setQuestICD10_11(ICD10Ids);
					qform.setQuestPoint2_7(subjectIds);
					qform.setQuestSource_10(sourceIds);
					qform.setQuestTitle_9(sectorIds);
					qform.setQuestCognize_8(cognizeIds);

					Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
					setPropVal(qform.getQuestUnit_4(), Constants.EXAM_PROP_UNIT, questionPropMap);
					setPropVal(qform.getQuestPoint_5(), Constants.EXAM_PROP_POINT, questionPropMap);
					setPropVal(qform.getQuestPoint2_7(),Constants.EXAM_PROP_POINT2,questionPropMap);
					setPropVal(qform.getQuestCognize_8(),Constants.EXAM_PROP_COGNIZE,questionPropMap);
					setPropVal(qform.getQuestTitle_9(), Constants.EXAM_PROP_TITLE,questionPropMap);
					setPropVal(qform.getQuestSource_10(), Constants.EXAM_PROP_SOURCE,questionPropMap);
					setPropVal(qform.getQuestICD10_11(), Constants.EXAM_PROP_ICD10,questionPropMap);
					
					quest.setQuestionPropMap(questionPropMap);
					
					quest.setContent(content);
					quest.setState(5);
					quest.setGrade(grade);
					quest.setParent_id(0L);
					quest.setSeq(0);
					quest.setQuestion_label_id(question_label_id);
					quest.setAnalyse(analyse);
					quest.setAuthor(userName);
					quest.setIsnot_multimedia(multiMedia);
					
					String renderSelIds = qform.getRenderSelIds();
					ExamQuestionPropValCascade questPropValCascade = new ExamQuestionPropValCascade();
					questPropValCascade.setPropval_id(renderSelIds);
					questPropValCascade.setPropval_name(qform.getTxtLoc());
					
					quest.setQuestPropValCascade(questPropValCascade);
					
					List<ExamSubSysQuestType> subSysQuestTypeList = new ArrayList<ExamSubSysQuestType>();
					List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();
					
					
					List<ExamQuestionType> typeList= localExamQuestionTypeFacade.getSubSysTypeByTypeId(qform.getQuestType());
					
					HashSet m = new HashSet();
					
					for(ExamQuestionType tt : typeList){
						ExamSubSysQuestType  type = new ExamSubSysQuestType();
						if(!m.add(tt.getSub_sys_id())){
							throw new FrameworkRuntimeException(ErrorCode.ec00002);
						}
						type.setSub_type_id(tt.getId());
						type.setSub_sys_id(tt.getSub_sys_id());
						type.setState(1);
						subSysQuestTypeList.add(type);
					}
					quest.setSubSysQuestTypeList(subSysQuestTypeList);
					
					
					if(null==localExamQuestionFacade.addQuestion(quest)){
						request.setAttribute("msg", "该试题已经存在!");
						return "FAILURE";
					}
				}
*/
//				for (int i = 1; i < rowNum; i++) {
//					
//					errRow = i;
//					
//					ExamQuestion examQuestion = new ExamQuestion();
//					
//					ExamQuestion compareQuestion = new ExamQuestion();
//					
//					ExamQuestionPropValCascade propValCascade = new ExamQuestionPropValCascade();
//					
//					List<ExamSubSysQuestType> subSysQuestTypeList = new ArrayList<ExamSubSysQuestType>();
//					
//					Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
//					
//					List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();					
//					
//					// 父试题ID
//					Long parentId = sheet.getCell(1,i).getContents().trim().equals("")? 0: Long.parseLong(sheet.getCell(1,i).getContents().trim());
//					// 题型ID
//					int questionLabel = sheet.getCell(2,i).getContents().trim().equals("")? 0:Integer.parseInt(sheet.getCell(2,i).getContents().trim());
//					
//					//被收入题库
//					String msg = "";
//					String questType = sheet.getCell(16,i).getContents().trim();
//					if(questType.equals("")){
//						request.setAttribute("errMsg", "第" + (errRow+1) + "行,试题分类未填写，导入失败！");
//						return "FAILURE";
//					}
//					msg = setQuestType(questType,(errRow+1),subSysQuestTypeList);
//					if(!msg.equals("")){
//						request.setAttribute("errMsg", msg);
//						return "FAILURE";							
//					}					
//					
//					//行业
//					String questPoint2 = sheet.getCell(17,i).getContents().trim();
//					if(!questPoint2.equals("")){
//						msg = setProp(questPoint2,(errRow+1),Constants.EXAM_PROP_POINT2, questionPropMap, p7list);
//						if(!msg.equals("")){
//							request.setAttribute("errMsg", msg);
//							return "FAILURE";							
//						}
//					}
//					
//					
//					//知识分类
//					String questCognize = sheet.getCell(18,i).getContents().trim();
//					if(questCognize.equals("")){
//						request.setAttribute("errMsg", "第" + (errRow+1) + "行,知识分类未填写，导入失败！");
//						return "FAILURE";
//					}
//					msg = setProp(questCognize,(errRow+1),Constants.EXAM_PROP_COGNIZE, questionPropMap, p8list);
//					if(!msg.equals("")){
//						request.setAttribute("errMsg", msg);
//						return "FAILURE";							
//					}					
//					
//					
//					//适用对象
//					String questTitle = sheet.getCell(19,i).getContents().trim();
//					if(questTitle.equals("")){
//						request.setAttribute("errMsg", "第" + (errRow+1) + "行,适用对象未填写，导入失败！");
//						return "FAILURE";
//					}
//					msg = setProp(questTitle,(errRow+1),Constants.EXAM_PROP_TITLE, questionPropMap, p9list);
//					if(!msg.equals("")){
//						request.setAttribute("errMsg", msg);
//						return "FAILURE";							
//					}					
//					
//					//知识点
//					/*String questPoint = sheet.getCell(20,i).getContents().trim();
//					if(questPoint.equals("")){
//						request.setAttribute("errMsg", "第" + (errRow+1) + "行,知识点未填写，导入失败！");
//						return "FAILURE";
//					}*/
//					msg = setRelProp("",(errRow+1), propValCascade, questionPropMap, null);
//					if(!msg.equals("")){
//						request.setAttribute("errMsg", msg);
//						return "FAILURE";							
//					}						
//					
//					
//					if(questionLabel == 0){
//						request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID有误，导入失败！");
//						return "FAILURE";
//					}
//					
//					if(parentId.intValue() == 0){
//						// 试题选项
//						String[] result= new String[6];
//						// 选项个数
//						int num = 0;
//						for (int k = 0; k < result.length; k++) {
//							Cell cell = sheet.getCell(k+4,i);
//							result[k]= cell.getContents().trim();
//							if(cell.getContents().trim().equals("")){
//								num = k;
//								break;
//							}
//							if(k == result.length -1){
//								num = result.length;
//							}
//						}
//						
//						compareQuestion.setId((long)(errRow+1));
//						
//						// 试题类型
//						examQuestion.setQuestion_label_id(Integer.valueOf(questionLabel));
//						compareQuestion.setQuestion_label_id(Integer.valueOf(questionLabel));
//						
//						// 父试题ID
//						examQuestion.setParent_id(0L);
//						
//						// 题干内容
//						if(sheet.getCell(3,i).getContents().trim().equals("")){
//							request.setAttribute("errMsg", "第" + (errRow+1) + "行试题内容不能为空，导入失败！");
//							return "FAILURE";
//						}
//						examQuestion.setContent(replaceBlank(sheet.getCell(3,i).getContents().trim()));
//						compareQuestion.setContent(replaceBlank(sheet.getCell(3,i).getContents().trim()));
//						
//						// 试题状态
//						examQuestion.setState(5);
//						// 对比状态
//						compareQuestion.setState(99);
//						// 试题难度
//						examQuestion.setGrade(Integer.parseInt(sheet.getCell(12,i).getContents().trim().equals("")?"0":sheet.getCell(12,i).getContents().trim()));
//						// 试题区分度
//						examQuestion.setDiffer(Integer.parseInt(sheet.getCell(13,i).getContents().trim().equals("")?"0":sheet.getCell(13,i).getContents().trim()));
//						// 试题分析
//						examQuestion.setAnalyse(replaceBlank(sheet.getCell(11,i).getContents().trim()));
//						// 试题来源
//						examQuestion.setSource(replaceBlank(sheet.getCell(14,i).getContents().trim()));
//						// 试题顺序
//						examQuestion.setSeq(0);
//						//是否为多媒体试题
//						examQuestion.setIsnot_multimedia(Integer.parseInt(sheet.getCell(21,i).getContents().trim().equals("")?"0":sheet.getCell(21,i).getContents().trim()));
//						
//						// 创建者
//						if(sheet.getCell(15,i).getContents().trim().equals("")){
//							request.setAttribute("errMsg", "第" + (errRow+1) + "行,创建者不能为空，导入失败！");
//							return "FAILURE";								
//						}
//						examQuestion.setAuthor(sheet.getCell(15,i).getContents().trim());
//						
//						// 试题属性
//						examQuestion.setQuestionPropMap(questionPropMap);
//						
//						examQuestion.setQuestPropValCascade(propValCascade);
//						
//						// 试题分类
//						examQuestion.setSubSysQuestTypeList(subSysQuestTypeList);
//						
//						if(questionLabel != Constants.A3 && questionLabel != Constants.B1){
//							
//							if(sheet.getCell(10,i).getContents().trim().equals("")){
//								request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为空，导入失败！");
//								return "FAILURE";
//							} else if(questionLabel == Constants.A1 || 
//									questionLabel == Constants.A2 || 
//									questionLabel == Constants.A3_1 ||
//									questionLabel == Constants.A3_2 ||
//									questionLabel == Constants.B1_1 ||
//									questionLabel == Constants.DXT) {
//								if(!isEnna(sheet.getCell(10,i).getContents().trim())){
//									request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为全角字符，导入失败！");
//									return "FAILURE";
//								}								
//							}
//							
//						}
//						
//						if(questionLabel == Constants.A1|| questionLabel == Constants.A2) { // 单选题
//							for (int k = 0; k < num; k++) {
//								ExamQuestionKey key = new ExamQuestionKey();
//								key.setContent(replaceBlank(result[k]));
//								if (String.valueOf((char)(65+k)).trim().equals(sheet.getCell(10,i).getContents().trim().toUpperCase())) {
//									key.setIsnot_true(1);
//								} else {
//									key.setIsnot_true(0);
//								}
//								key.setSeq(k + 1);
//								keyList.add(key);
//							}
//						}else if (questionLabel == Constants.DXT) { // 多选题 
//							for (int k = 0; k < num; k++) {
//								ExamQuestionKey key = new ExamQuestionKey();
//								key.setContent(replaceBlank(result[k]));
//								if (sheet.getCell(10,i).getContents().trim().toUpperCase().indexOf(String.valueOf((char)(65+k)).trim())!= -1) {
//									key.setIsnot_true(1);
//								} else {
//									key.setIsnot_true(0);
//								}
//								key.setSeq(k + 1);
//								keyList.add(key);
//							}
//						}else if (questionLabel == Constants.TK
//								|| questionLabel == Constants.JD
//								|| questionLabel == Constants.MCJS
//								|| questionLabel == Constants.NLFX) { // 填空题
//							ExamQuestionKey key = new ExamQuestionKey();
//							key.setContent(replaceBlank(sheet.getCell(10,i).getContents().trim()));
//							key.setSeq(1);
//							keyList.add(key);
//						}else if (questionLabel == Constants.PD) { // 判断题
//							ExamQuestionKey key = new ExamQuestionKey();
//							key.setIsnot_true(Integer.parseInt(sheet.getCell(10,i).getContents().trim().toUpperCase()));
//							key.setSeq(1);
//							keyList.add(key);
//						}else if (questionLabel == Constants.B1){
//							//处理B1子试题
//							List<ExamQuestion> childList = new ArrayList<ExamQuestion>();
//							for (int j = 1; j < rowNum; j++) {
//								if(sheet.getCell(1,j).getContents().trim().equals(sheet.getCell(0,i).getContents().trim())){
//									errRow = j;
//									int childLabel = sheet.getCell(2,j).getContents().trim().equals("")? 0:Integer.parseInt(sheet.getCell(2,j).getContents().trim());
//									if(childLabel == 0){
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID有误，导入失败！");
//										return "FAILURE";
//									}
//									if(sheet.getCell(10,j).getContents().trim().equals("")){
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为空，导入失败！");
//										return "FAILURE";
//									}
//									ExamQuestion childQuestion = new ExamQuestion();
//
//									// 试题类型								
//									if(childLabel == Constants.B1_1){
//										childQuestion.setQuestion_label_id(Integer.valueOf(Constants.B1_1));
//									}else{
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID不是第" + (i+1) + "行数据的子试题题型，导入失败！");
//										return "FAILURE";
//									}									
//									if(sheet.getCell(3,j).getContents().trim().equals("")){
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行试题内容不能为空，导入失败！");
//										return "FAILURE";
//									}
//									// 题干内容
//									childQuestion.setContent(replaceBlank(sheet.getCell(3,j).getContents().trim()));
//									// 试题分析
//									childQuestion.setAnalyse(sheet.getCell(11,j).getContents().trim());
//									// 试题状态
//									childQuestion.setState(5);
//									// 试题难度
//									childQuestion.setGrade(Integer.parseInt(sheet.getCell(12,j).getContents().trim().equals("")?"0":sheet.getCell(12,j).getContents().trim()));
//									// 试题区分度
//									childQuestion.setDiffer(Integer.parseInt(sheet.getCell(13,j).getContents().trim().equals("")?"0":sheet.getCell(13,j).getContents().trim()));
//									// 试题来源
//									childQuestion.setSource(sheet.getCell(14,j).getContents().trim());
//									// 试题顺序
//									childQuestion.setSeq(childList.size()+1);
//									// 创建者
//									childQuestion.setAuthor(sheet.getCell(15,j).getContents().trim());
//									//是否为多媒体试题
//									childQuestion.setIsnot_multimedia(examQuestion.getIsnot_multimedia());
//									
//									// 试题答案
//									List<ExamQuestionKey> childKeyList = new ArrayList<ExamQuestionKey>();
//									ExamQuestionKey childkey = new ExamQuestionKey();
//									// 答案
//									childkey.setContent(replaceBlank(sheet.getCell(10, j).getContents().trim()));
//									// 是否正确
//									childkey.setIsnot_true(1);
//									// 顺序
//									childkey.setSeq(1);
//									childKeyList.add(childkey);
//									// 子试题选项答案
//									childQuestion.setQuestionKeyList(childKeyList);
//									// 子试题属性
//									childQuestion.setQuestionPropMap(questionPropMap);
//									ExamQuestionPropValCascade childPropValCascade = new ExamQuestionPropValCascade();
//									BeanUtils.copyProperties(propValCascade,childPropValCascade);
//									childQuestion.setQuestPropValCascade(childPropValCascade);
//									// 子试题分类
//									List<ExamSubSysQuestType> childQuestTypeList = new ArrayList<ExamSubSysQuestType>();
//									for(ExamSubSysQuestType tmp : childQuestTypeList){
//										ExamSubSysQuestType nt = new ExamSubSysQuestType();
//										BeanUtils.copyProperties(tmp,nt);
//										childQuestTypeList.add(nt);
//										
//									}
//									childQuestion.setSubSysQuestTypeList(childQuestTypeList);
//									
//									childList.add(childQuestion);
//								}
//							}
//							errRow = i;
//							// 如果没有子试题
//							if(childList.size()==0){
//								request.setAttribute("errMsg", "第" + (errRow+1) + "行数据没有子试题，导入失败！");
//								return "FAILURE";
//							}
//							// 子试题列表
//							examQuestion.setChildQuestionList(childList);
//						} else if(questionLabel == Constants.A3){
//							//处理A3子试题
//							List<ExamQuestion> childList = new ArrayList<ExamQuestion>();
//							for (int j = 1; j < rowNum; j++) {
//								if(sheet.getCell(1,j).getContents().trim().equals(sheet.getCell(0,i).getContents().trim())){
//									errRow = j;
//									int childLabel = sheet.getCell(2,j).getContents().trim().equals("")? 0:Integer.parseInt(sheet.getCell(2,j).getContents().trim());
//									if(childLabel == 0){
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID有误，导入失败！");
//										return "FAILURE";
//									}
//									if(sheet.getCell(10,j).getContents().trim().equals("")){
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据中答案字段不能为空，导入失败！");
//										return "FAILURE";
//									}
//									ExamQuestion childQuestion = new ExamQuestion();								
//									// 试题类型
//									if(questionLabel == Constants.A3 && (childLabel == Constants.A3_1 ||childLabel == Constants.A3_2)){
//										childQuestion.setQuestion_label_id(Integer.valueOf(childLabel));
//									}else{
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID不是第" + (i+1) + "行数据的子试题题型，导入失败！");
//										return "FAILURE";
//									}
//									if(sheet.getCell(3,j).getContents().trim().equals("")){
//										request.setAttribute("errMsg", "第" + (errRow+1) + "行试题内容不能为空，导入失败！");
//										return "FAILURE";
//									}
//									// 题干内容
//									childQuestion.setContent(replaceBlank(sheet.getCell(3,j).getContents().trim()));
//									// 试题分析
//									childQuestion.setAnalyse(sheet.getCell(11,j).getContents().trim());
//									// 试题状态
//									childQuestion.setState(5);
//									// 试题难度
//									childQuestion.setGrade(Integer.parseInt(sheet.getCell(12,j).getContents().trim().equals("")?"0":sheet.getCell(12,j).getContents().trim()));
//									// 试题区分度
//									childQuestion.setDiffer(Integer.parseInt(sheet.getCell(13,j).getContents().trim().equals("")?"0":sheet.getCell(13,j).getContents().trim()));
//									// 试题来源
//									childQuestion.setSource(sheet.getCell(14,j).getContents().trim());
//									// 试题顺序
//									childQuestion.setSeq(childList.size()+1);										
//									// 创建者
//									childQuestion.setAuthor(sheet.getCell(15,j).getContents().trim());
//									//是否为多媒体试题
//									childQuestion.setIsnot_multimedia(examQuestion.getIsnot_multimedia());
//									
//									//子试题选项和答案
//									List<ExamQuestionKey> childKeyList = new ArrayList<ExamQuestionKey>();
//									for (int k = 0; k < 6; k++) {
//										if(sheet.getCell(k+4,j).getContents().trim().equals("")){
//											break;
//										}
//										ExamQuestionKey childkey = new ExamQuestionKey();
//										// 答案
//										childkey.setContent(replaceBlank(sheet.getCell(k+4,j).getContents().trim()));
//										// 是否正确
//										if(questionLabel == Constants.A3){
//											if(childLabel == Constants.A3_1){
//												if (String.valueOf((char)(65+k)).trim().equals(sheet.getCell(10,j).getContents().trim().toUpperCase())) {
//													childkey.setIsnot_true(1);
//												}else{
//													childkey.setIsnot_true(0);
//												}
//											}else if(childLabel == Constants.A3_2){
//												if (sheet.getCell(10,j).getContents().trim().toUpperCase().indexOf(String.valueOf((char)(65+k)).trim())!= -1) {
//													childkey.setIsnot_true(1);
//												}else{
//													childkey.setIsnot_true(0);
//												}
//											}else{
//												request.setAttribute("errMsg", "第" + (errRow+1) + "行数据题型ID不是第" + (i+1) + "行数据的子试题题型，导入失败！");
//												return "FAILURE";
//											}
//										}
//										// 顺序
//										childkey.setSeq(k + 1);
//										
//										childKeyList.add(childkey);
//									}
//									// 子试题选项答案
//									childQuestion.setQuestionKeyList(childKeyList);
//									// 子试题属性
//									childQuestion.setQuestionPropMap(questionPropMap);
//									//
//									ExamQuestionPropValCascade childPropValCascade = new ExamQuestionPropValCascade();
//									BeanUtils.copyProperties(propValCascade,childPropValCascade);
//									childQuestion.setQuestPropValCascade(childPropValCascade);
//									// 子试题分类
//									List<ExamSubSysQuestType> childQuestTypeList = new ArrayList<ExamSubSysQuestType>();
//									for(ExamSubSysQuestType tmp : childQuestTypeList){
//										ExamSubSysQuestType nt = new ExamSubSysQuestType();
//										BeanUtils.copyProperties(tmp,nt);
//										childQuestTypeList.add(nt);
//									}
//									childQuestion.setSubSysQuestTypeList(childQuestTypeList);
//									
//									childList.add(childQuestion);
//								}
//							}
//							errRow = i;
//							// 如果没有A3子试题
//							if(childList.size()==0){
//								request.setAttribute("errMsg", "第" + (errRow+1) + "行数据没有子试题，导入失败！");
//								return "FAILURE";
//							}
//							// 子试题列表
//							examQuestion.setChildQuestionList(childList);
//						}
//						// 试题答案选项
//						examQuestion.setQuestionKeyList(keyList);
//						// 加入试题列表
//						questionList.add(examQuestion);
//						compareList.add(compareQuestion);
//					}
//				}
				
			
				
//				if(questionList!= null && questionList.size()>0){
//					
//					if(compareQuestion(questionList)){
//						request.setAttribute("errMsg", "导入文件内有重复试题请检查！");	
//						return "FAILURE";
//					}
//					
//					List<ExamQuestion> resultList = localExamQuestionFacade.getCompareQuestion(compareList);
//					
//					//判断是否有重复试题
//					if(null==resultList || resultList.isEmpty()){
//						
//						localExamQuestionFacade.addImportQuestion(questionList);
//					}else{
//						for(ExamQuestion eq : resultList){
//							System.out.println("id:"+eq.getId()+"===content:"+eq.getContent()+";laber_id:"+eq.getQuestion_label_id());
//							logger.warn("id:"+eq.getId()+"===content:"+eq.getContent()+";laber_id:"+eq.getQuestion_label_id());
//						}
//						request.setAttribute("errMsg", "有重复试题请检查！");	
//						return "FAILURE";
//					}					
//				}else{
//					request.setAttribute("errMsg", "没有找到可导入的数据！");
//					return "FAILURE";
//				}				
			}
		} catch (Exception e) {
			request.setAttribute("errMsg", "第" +  (errRow+1) + "行数据格式错误，导入失败！");
			e.printStackTrace();
			return "FAILURE";
		} finally {
			is.close();
			rwb.close();
		}
		
		
		request.setAttribute("errMsg", "导入成功");		
		return "SUCCESS";
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
	
	
	/**
	 * 
	 * @param propStr 属性
	 * @param rowNum 行号
	 * @param key 属性KEY
	 * @param  questionPropMap 试题属性
	 */
	private String setProp(String propStr,int rowNum,String key,Map<String,List<ExamQuestionProp>> questionPropMap,List<Long> propIdList){
		String msg = "";
		try{
			List<ExamQuestionProp>  questPropList = new ArrayList<ExamQuestionProp>();
			ArrayList<String> tmp = new ArrayList<String>();
			tmp = StringUtils.stringToArrayList(propStr, ",");
			for(String propId : tmp){
				if(key.equals(Constants.EXAM_PROP_SOURCE)){
					ExamQuestionProp questProp = new ExamQuestionProp();
					questProp.setProp_val_id(Long.parseLong(propId));
					questPropList.add(questProp);
				}
				else{
					if(propIdList.contains(Long.parseLong(propId))){
						ExamQuestionProp questProp = new ExamQuestionProp();
						questProp.setProp_val_id(Long.parseLong(propId));
						questPropList.add(questProp);
					} else{
						if(key.equals(Constants.EXAM_PROP_POINT2)){
							msg += "主题";
						}else if(key.equals(Constants.EXAM_PROP_COGNIZE)){
							msg += "认知水平";
						}else if(key.equals(Constants.EXAM_PROP_TITLE)){
							msg += "职称";
						}
						return "第" +  rowNum + "行," + msg + "数据错误，导入失败！";					
					}
				}
			}
			questionPropMap.put(key, questPropList);
		}catch(Exception e){
			if(key.equals(Constants.EXAM_PROP_POINT2)){
				msg += "主题";
			}else if(key.equals(Constants.EXAM_PROP_COGNIZE)){
				msg += "认知水平";
			}else if(key.equals(Constants.EXAM_PROP_TITLE)){
				msg += "职称";
			}
			return "第" +  rowNum + "行," + msg + "数据格式错误，导入失败！";
		}
		
		return msg;
	}
	
	private String setRelProp(String propStr, int rowNum, 
			ExamQuestionPropValCascade propValCascade,
			Map<String, List<ExamQuestionProp>> questionPropMap,
			Map<String, ExamRelationProp> relPropMap) {
		String msg = "";
		try{
			ArrayList<String> tmp = new ArrayList<String>();
			tmp = StringUtils.stringToArrayList(propStr, ",");
			
			List<ExamQuestionProp> study1List = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> study2List = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> study3List = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> unitList = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> pointList = new ArrayList<ExamQuestionProp>();
			
			if(questionPropMap.get(Constants.EXAM_PROP_STUDY1)!=null){
				study1List = questionPropMap.get(Constants.EXAM_PROP_STUDY1);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_STUDY2)!=null){
				study2List = questionPropMap.get(Constants.EXAM_PROP_STUDY2);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_STUDY3)!=null){
				study3List = questionPropMap.get(Constants.EXAM_PROP_STUDY3);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_UNIT)!=null){
				unitList = questionPropMap.get(Constants.EXAM_PROP_UNIT);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_POINT)!=null){
				pointList = questionPropMap.get(Constants.EXAM_PROP_POINT);
			}
			
			String cid="";
			String cname = "";
			for(String propId : tmp){
				setProp(Long.valueOf(propId), unitList);
/*				for(ExamQuestionProp p: unitList){
					p.setQuestion_id(questionId);
				}
*//*				if(relPropMap.containsKey(propId)){
					ExamRelationProp relProp = relPropMap.get(propId);
					setProp(relProp.getStudy1_prop_id(),study1List);
					setProp(relProp.getStudy2_prop_id(),study2List);
					setProp(relProp.getStudy3_prop_id(),study3List);
					setProp(relProp.getUnit_prop_id(),unitList);
					setProp(relProp.getPoint_prop_id(),pointList);
					if(cid.equals("")){
						cid = relProp.getRelationPropId();
						cname = relProp.getRelationPropName();
					}else{
						if(cid.indexOf(relProp.getRelationPropId()) == -1){
							cid += "+"+relProp.getRelationPropId();
							cname +="+"+relProp.getRelationPropName();
						}
					}
				}else{
					return "第" +  rowNum + "行,知识点数据错误，导入失败！";
				}
*/			}
			questionPropMap.put(Constants.EXAM_PROP_STUDY1, removeDuplicateWithOrder(study1List));
			questionPropMap.put(Constants.EXAM_PROP_STUDY2, removeDuplicateWithOrder(study2List));
			questionPropMap.put(Constants.EXAM_PROP_STUDY3, removeDuplicateWithOrder(study3List));
			questionPropMap.put(Constants.EXAM_PROP_UNIT, removeDuplicateWithOrder(unitList));
			questionPropMap.put(Constants.EXAM_PROP_POINT, removeDuplicateWithOrder(pointList));
			propValCascade.setPropval_id(cid);
			propValCascade.setPropval_name(cname);
			
		}catch(Exception e){
			return "第" +  rowNum + "行,知识点数据格式错误，导入失败！";
		}
		
		return msg;
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
	
	private void setProp(Long propId,List<ExamQuestionProp> list){
		ExamQuestionProp prop = new ExamQuestionProp();
		prop.setProp_val_id(propId);
		list.add(prop);
	}
	
	
	private List<ExamQuestionProp> removeDuplicateWithOrder(
			List<ExamQuestionProp> list) {
		Set<ExamQuestionProp> set = new HashSet<ExamQuestionProp>();
		List<ExamQuestionProp> newList = new ArrayList<ExamQuestionProp>();
		for (ExamQuestionProp p : list) {
			if (set.add(p)) {
				newList.add(p);
			}
		}
		return newList;
	}
	
	/**
	 * 
	 * @param _questType 分类ID  1,2
	 * @param rowNum 行号
	 * @param subSysQuestTypeList 试题分类对象
	 * @return
	 */
	private String setQuestType(String _questType,int rowNum,List<ExamSubSysQuestType> subSysQuestTypeList){
		String msg = "";
		try{
			ArrayList<String> tmp = new ArrayList<String>();
			tmp = StringUtils.stringToArrayList(_questType, ",");
			Long[] typeIdArr = new Long[tmp.size()];
			for(int i=0;i<tmp.size();i++){
				typeIdArr[i] = Long.parseLong(tmp.get(i));
			}
			
			//试题分类处理
			List<ExamQuestionType> typeList= localExamQuestionTypeFacade.getSubSysTypeByTypeId(typeIdArr);
			
			if(typeList.isEmpty()){
				return "第" +  rowNum + "行,试题分类数据错误，导入失败！";
			}
			
			HashSet<Long> m = new HashSet<Long>();
			
			for(ExamQuestionType t : typeList){
				ExamSubSysQuestType  type = new ExamSubSysQuestType();
				if(!m.add(t.getSub_sys_id())){
					return "第" +  rowNum + "行,试题分类系统重复，导入失败！";
				}
				type.setSub_type_id(t.getId());
				type.setSub_sys_id(t.getSub_sys_id());
				type.setState(1);
				subSysQuestTypeList.add(type);
			}
		}catch(Exception e){
			return "第" +  rowNum + "行,试题分类数据格式错误，导入失败！";
		}
		
		return msg;
	}
	
	
	private static boolean compareQuestion(List<ExamQuestion> questionList){
		boolean flag = false;
		Map<String,String> em = new HashMap<String,String>();
		
		for(ExamQuestion q : questionList) {
			String key = q.getContent()+"_"+q.getQuestion_label_id();
			if(em.containsKey(key)){
				flag = true;
				break;
			}else{
				em.put(key, "");
			}
		}
		return flag;
	}
	
	private static boolean isEnna(String p) {
		int length = p.length();
		boolean b = true;
		for (int i = 0; i < length; i++) {
			char c = p.charAt(i);
			if (c > 255) {
				b = false;
				break;
			}
		}
		return b;
	}
}
