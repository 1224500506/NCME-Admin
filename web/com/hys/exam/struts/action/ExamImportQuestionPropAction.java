package com.hys.exam.struts.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamRelationProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-6-21
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamImportQuestionPropAction extends BaseAction {

	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamPropValFacade localExamPropValFacade;
	
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

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm uploadForm = (DynaActionForm) form;

		FormFile file = (FormFile) uploadForm.get("uploadfile");

		if (file.getFileSize() > 2048000) {
			request.setAttribute("errMsg", "导入文件不能大于2M！");
			return "FAILURE";
		}

		InputStream is = file.getInputStream();

		Workbook rwb = Workbook.getWorkbook(is);

		int rn = 0;
		try {
			
			// 判断excel sheet
			if (rwb.getNumberOfSheets() > 0) {
				Sheet sheet = rwb.getSheet(0);
				// sheet 行数
				int rowNum = getSheetRowLength(sheet);

//				ExamQuestionPropValCascade propValCascade = new ExamQuestionPropValCascade();
				
				ArrayList<ExamQuestionPropValCascade> cascadeList = new ArrayList<ExamQuestionPropValCascade>();
				
				Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
				
				//关联属性
				Map<String,ExamRelationProp> relPropMap = localExamPropValFacade.getExamRelationPropAll();
				
				List<Long> idArr = new ArrayList<Long>();
				
				for (int i = 1; i < rowNum; i++) {
					rn = i;
					String msg = "";
					// 试题ID
					String qids = sheet.getCell(0, i).getContents().trim();
					if(qids.equals("")){
						request.setAttribute("errMsg", "第" + (i+1) + "行,试题id未填写，导入失败！");
						return "FAILURE";
					}
					//知识点
					String questPoint = sheet.getCell(3,i).getContents().trim();
					if(questPoint.equals("")){
						request.setAttribute("errMsg", "第" + (i+1) + "行,知识点未填写，导入失败！");
						return "FAILURE";
					}
					msg = setRelProp(questPoint,(i+1), cascadeList, questionPropMap, relPropMap,Long.parseLong(qids));
					if(!msg.equals("")){
						request.setAttribute("errMsg", msg);
						return "FAILURE";							
					}
					
					if(!idArr.contains(Long.parseLong(qids))){
						idArr.add(Long.parseLong(qids));
					}
					
					
				}
				
				
				localExamQuestionFacade.addImportQuestionProp(questionPropMap, idArr, cascadeList, 4);

			}
		} catch (Exception e) {
			logger.error("ExamImportQuestionPropAction "+e);
			request.setAttribute("errMsg", "第 "+rn+" 行数据错误，导入失败！");
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

	
	private String setRelProp(String propStr, int rowNum,
			ArrayList<ExamQuestionPropValCascade> cascadeList,
			Map<String, List<ExamQuestionProp>> questionPropMap,
			Map<String, ExamRelationProp> relPropMap,Long qid) {
		String msg = "";
		try{
			ExamQuestionPropValCascade propValCascade = new ExamQuestionPropValCascade();
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
				if(relPropMap.containsKey(propId)){
					ExamRelationProp relProp = relPropMap.get(propId);
					setProp(relProp.getStudy1_prop_id(),qid,study1List);
					setProp(relProp.getStudy2_prop_id(),qid,study2List);
					setProp(relProp.getStudy3_prop_id(),qid,study3List);
					setProp(relProp.getUnit_prop_id(),qid,unitList);
					setProp(relProp.getPoint_prop_id(),qid,pointList);
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
			}
			questionPropMap.put(Constants.EXAM_PROP_STUDY1, removeDuplicateWithOrder(study1List));
			questionPropMap.put(Constants.EXAM_PROP_STUDY2, removeDuplicateWithOrder(study2List));
			questionPropMap.put(Constants.EXAM_PROP_STUDY3, removeDuplicateWithOrder(study3List));
			questionPropMap.put(Constants.EXAM_PROP_UNIT, removeDuplicateWithOrder(unitList));
			questionPropMap.put(Constants.EXAM_PROP_POINT, removeDuplicateWithOrder(pointList));
			propValCascade.setPropval_id(cid);
			propValCascade.setPropval_name(cname);
			propValCascade.setQuestion_id(qid);
			cascadeList.add(propValCascade);
			
			
		}catch(Exception e){
			logger.error("第" +  rowNum + "行,知识点数据格式错误，导入失败！"+e);
			return "第" +  rowNum + "行,知识点数据格式错误，导入失败！";
		}
		
		return msg;
	}
	
	private void setProp(Long propId,Long qid,List<ExamQuestionProp> list){
		ExamQuestionProp prop = new ExamQuestionProp();
		prop.setProp_val_id(propId);
		prop.setQuestion_id(qid);
		list.add(prop);
	}

}
