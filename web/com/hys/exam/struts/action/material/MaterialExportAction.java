package com.hys.exam.struts.action.material;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.service.local.MaterialManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：病历管理平台
 * 
 * 作者：Tiger 2016-09-17
 * 
 * 描述：
 * 
 * 说明: 导出病例
 */
public class MaterialExportAction extends BaseAction {

	private MaterialManageManage localMaterialManageManage;
	
	private ExamPropValFacade localExamPropValFacade;
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public MaterialManageManage getLocalMaterialManageManage() {
		return localMaterialManageManage;
	}

	public void setLocalMaterialManageManage(
			MaterialManageManage localMaterialManageManage) {
		this.localMaterialManageManage = localMaterialManageManage;
	}
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	String[] titles = new String[]{ "素材编号", "素材名称","学科","ICD10","素材格式","入库时间","审核时间","素材类型","状态","审核意见","审核人"};
		    List<MaterialInfo> materialInfoList = new ArrayList<MaterialInfo>();
		    Date date = new Date();
		    String fileName = "MaterialInf(" + DateUtils.formatDate(date, "yyyy-MM-dd") + ")" + ".xls";
		    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		    WritableFont fontred = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
		    WritableCellFormat cellFormatred = new WritableCellFormat(fontred);
		    WritableFont fontpunk = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		    WritableCellFormat cellFormatpunk = new WritableCellFormat(fontpunk);
		    try {
		        OutputStream os = response.getOutputStream();
		        WritableWorkbook book = Workbook.createWorkbook(os);
		        
		        WritableSheet sheet = book.createSheet("Material", 0);
		        for (int i = 0; i < titles.length; ++i) {
		                Label label = new Label(i, 0, titles[i], cellFormatred);
		                sheet.addCell(label);
		        }
		        String[] array = request.getParameterValues("idArr");
		        
		        if (array != null) {
		            for (String anArray : array) {
		            	MaterialInfo inf = localMaterialManageManage.getMaterialInfo(Long.valueOf(anArray));
		                if (inf != null) {
		                	materialInfoList.add(inf);
		                }
		            }
		        }
		        for(int i=0; i < materialInfoList.size(); ++i){
		            Label label1 = new Label(0,i+1,materialInfoList.get(i).getCode());
		            Label label2 = new Label(1,i+1,materialInfoList.get(i).getFileName());
		            String strProp = "";
		            if(materialInfoList.get(i).getProp_map().get("4") != null){
		            	for(int j = 0 ; j < materialInfoList.get(i).getProp_map().get("4").size() ; j++)
			            {
			            	strProp += materialInfoList.get(i).getProp_map().get("4").get(j).getProp_val_name();
			            }	
		            }
		            Label label3 = new Label(2,i+1,strProp);
		            strProp = "";
		            if(materialInfoList.get(i).getProp_map().get("11") != null)
		            {
		            	for(int j = 0 ; j < materialInfoList.get(i).getProp_map().get("11").size() ; j++)
			            {
			            	strProp += materialInfoList.get(i).getProp_map().get("11").get(j).getProp_val_name();
			            }	
		            }
		            Label label4 = new Label(3,i+1,strProp);
		            Label label5 = new Label(4,i+1,materialInfoList.get(i).getFormat());
		            Label label6 = new Label(5,i+1,materialInfoList.get(i).getUpload_date());
		            Label label7 = new Label(6,i+1,materialInfoList.get(i).getDeli_date());
		            Label label8;
		            switch(materialInfoList.get(i).getType()){
		            case 1:
		            	label8 = new Label(7,i+1,"视频");	
		            	break;
		            case 2:
		            	label8 = new Label(7,i+1,"图片");	
		            	break;
		            case 3:
		            	label8 = new Label(7,i+1,"PPT");	
		            	break;
		            case 4:
		            	label8 = new Label(7,i+1,"文本");	
		            	break;
		            default:
		            	label8 = new Label(7,i+1,"");
		            	break;
		            }
		            Label label9;
		            switch(materialInfoList.get(i).getState())
		            {
			            case 0:
			            	label9 = new Label(8,i+1,"未上传");	
			            	break;
			            case 1:
			            	label9 = new Label(8,i+1,"未审核");	
			            	break;
			            case 2:
			            	label9 = new Label(8,i+1,"不合格");	
			            	break;
			            case 3:
			            	label9 = new Label(8,i+1,"合格");	
			            	break;
			            case 4:
			            	label9 = new Label(8,i+1,"禁用");	
			            	break;
			            default:
			            	label9 = new Label(8,i+1,"禁用");
			            	break;
		            }
		            Label label10 = new Label(9,i+1,materialInfoList.get(i).getDeli_opinion());
		            Label label11 = new Label(10,i+1,materialInfoList.get(i).getDeli_man());
		            sheet.addCell(label1);
		            sheet.addCell(label2);
		            sheet.addCell(label3);
		            sheet.addCell(label4);
		            sheet.addCell(label5);
		            sheet.addCell(label6);
		            sheet.addCell(label7);
		            sheet.addCell(label8);
		            sheet.addCell(label9);
		            sheet.addCell(label10);
		            sheet.addCell(label11);
		        }
		        
		        book.write();
		        book.close();        
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		    }
		    return "success";
	}
}
