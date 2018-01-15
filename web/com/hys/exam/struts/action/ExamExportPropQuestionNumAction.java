package com.hys.exam.struts.action;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionLabelNum;
import com.hys.exam.model.ExamRelationProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Dec 9, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExportPropQuestionNumAction extends BaseAction {

	private ExamPropValFacade localExamPropValFacade;
	
	private ExamQuestionLabelFacade ExamQuestionLabelFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public ExamQuestionLabelFacade getExamQuestionLabelFacade() {
		return ExamQuestionLabelFacade;
	}

	public void setExamQuestionLabelFacade(
			ExamQuestionLabelFacade examQuestionLabelFacade) {
		ExamQuestionLabelFacade = examQuestionLabelFacade;
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
		
		List<ExamRelationProp> examRelationPropList = localExamPropValFacade.getExamRelationPropList();
		
		List<ExamQuestionLabel> labelList = ExamQuestionLabelFacade.getQuestionLabel(1);
		

		Map<Long,String> study1Map = new HashMap<Long,String>();
		Map<Long,String> study2Map = new HashMap<Long,String>();
		Map<Long,String> study3Map = new HashMap<Long,String>();
		
		for(ExamRelationProp rp : examRelationPropList){
			List<String> relationPropName = StringUtils.stringToArrayList(rp.getRelationPropName(), "-");
			
			String p1=relationPropName.get(0);
			String p2=relationPropName.get(1);
			String p3=relationPropName.get(2);
			
			if(!study1Map.containsKey(rp.getStudy1_prop_id().toString())){
				study1Map.put(rp.getStudy1_prop_id(), p1);
			}
			
			if(!study2Map.containsKey(rp.getStudy2_prop_id().toString())){
				study2Map.put(rp.getStudy2_prop_id(), p1+"-"+p2);
			}
			
			if(!study3Map.containsKey(rp.getStudy3_prop_id().toString())){
				study3Map.put(rp.getStudy3_prop_id(), p1+"-"+p2+"-"+p3);
			}
		}
		
		
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=\"ExamPropQuestionNum.xls\"");
		OutputStream os = response.getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet wsheet1 = wwb.createSheet("Sheet1", 0);
		WritableSheet wsheet2 = wwb.createSheet("Sheet2", 1);
		WritableSheet wsheet3 = wwb.createSheet("Sheet3", 2);
		
		
		createSheet(wsheet1,labelList,study1Map,1);
		createSheet(wsheet2,labelList,study2Map,2);
		createSheet(wsheet3,labelList,study3Map,3);
		
		wwb.write();
		wwb.close();
		os.close();
		response.flushBuffer();
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void createSheet(WritableSheet sheet,List<ExamQuestionLabel> labelList,Map<Long,String> propMap,int type) throws RowsExceededException, WriteException{
		
		Label label = new Label(0, 0, "属性名称");
		sheet.addCell(label);
		
		for(int i=0;i<labelList.size();i++){
			ExamQuestionLabel ql = labelList.get(i);
			label = new Label((i+1), 0, ql.getName());
			sheet.addCell(label);
		}
		
		int r = 1;
		for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			label = new Label(0, r, entry.getValue().toString());
			sheet.addCell(label);
			
			
			List<ExamQuestionLabelNum> list = localExamQuestionFacade.getQuestionLabelNumByPropId((Long)entry.getKey(), type);
			
			if(null!=list && !list.isEmpty()){
				for(int i=0;i<labelList.size();i++){
					ExamQuestionLabel ql = labelList.get(i);
					
					for(ExamQuestionLabelNum ln : list){
						if(ql.getId() == ln.getLabelId()){
							label = new Label(i+1, r , ln.getNum().toString());
							sheet.addCell(label);
						}
					}
				}
			}
			
			r++;
		}
		
	}

}
