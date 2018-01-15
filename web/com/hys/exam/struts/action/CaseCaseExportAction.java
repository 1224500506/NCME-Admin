package com.hys.exam.struts.action;

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

import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseCasePatient;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CaseManage;
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
public class CaseCaseExportAction extends BaseAction {

	CaseManage localCaseManage;
	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}
	
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	public CaseManage getLocalCaseManage() {
		return localCaseManage;
	}
	
	public void setLocalCaseManage(CaseManage localCaseManage) {
		this.localCaseManage = localCaseManage;
	}
	
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	String[] titles = new String[]{ "学科", "ICD10","主题","就诊日期","姓名","性别","出生日期","年龄","身份证号","编号类型1","编号1","编号类型2","编号2","手机号","固定电话","诊断","基本病情","状态","审核意见","审核人",
		 			"病程时间","病程类型","主诉","现病史","既往史","个人史","过敏史","家族史","神经系统状态","神经系统","头部状态","头部","颈部状态","颈部","胸部状态","胸部","腹部状态","腹部","四肢状态","四肢","皮肤状态","皮肤","淋巴状态","淋巴",
		 			"化验","影像学","其他","诊断","治疗方案","预后"};
		    List<CaseCase> caseList = new ArrayList<CaseCase>();
			List<CaseCasePatient> casePatientList = new ArrayList<CaseCasePatient>();
		    Date date = new Date();
		    String fileName = "CaseInf(" + DateUtils.formatDate(date, "yyyy-MM-dd") + ")" + ".xls";
		    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		    WritableFont fontred = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
		    WritableCellFormat cellFormatred = new WritableCellFormat(fontred);
		    WritableFont fontpunk = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		    WritableCellFormat cellFormatpunk = new WritableCellFormat(fontpunk);
		    
		    try {
		        OutputStream os = response.getOutputStream();
		        WritableWorkbook book = Workbook.createWorkbook(os);
		        
		        WritableSheet sheet = book.createSheet("CASE_CASE", 0);
		        for (int i = 0; i < titles.length; ++i) {
		                Label label = new Label(i, 0, titles[i], cellFormatred);
		                sheet.addCell(label);
		        }
		        String[] array = request.getParameterValues("caseId");
		        
		        if (array != null) {
		            for (String anArray : array) {
		            	CaseCase qCase = localCaseManage.getCaseById(Long.valueOf(anArray));
		                if (qCase != null) {
		                	caseList.add(qCase);
		                }
		            }
		        }
		        
				for(int i = 0 ; i < caseList.size() ; i++){
						CasePatient patient = localCaseManage.getPatientById(caseList.get(i).getpId());
						Date birthday = DateUtil.parse(patient.getBirthday(), "yyyy-MM-dd");
						patient.setBirthday(DateUtils.formatDate(birthday, "yyyy年MM月dd日"));
						patient.setpAge(getAge(birthday));
						CaseDisease newCaseDisease = new CaseDisease();
						newCaseDisease = localCaseManage.getDiseaseById(caseList.get(i).getDiseaseId());
						CaseCasePatient newCasePatient = new CaseCasePatient();
						newCasePatient.setCaseObject(caseList.get(i));
						newCasePatient.setPatientObject(patient);
						newCasePatient.setPatientDiagnosisObject(getPatientDiagnosis(patient.getId()));
						newCasePatient.setCaseDiseaseObject(newCaseDisease);
						newCasePatient.setDiseaseDiagnosisObject(getDiseaseDiagnosis(newCaseDisease.getId()));
						
						casePatientList.add(newCasePatient);
				}
				
		        for (int i=0; i < casePatientList.size(); ++i) {
		        	List<CasePatientPropVal> patientProp = new ArrayList<CasePatientPropVal>();
					patientProp = localCaseManage.getCasePropByPatientId(casePatientList.get(i).getPatientObject().getId());
					String courseString = "";
					String subjectString = "";
					String ICD10_names = "";
					for (int j = 0 ; j < patientProp.size() ; j ++)
					{
						CasePatientPropVal curProp = patientProp.get(j);
						if (curProp.getType() < 5){
							courseString += curProp.getPropName() + ",";
						}
						else {
							subjectString += curProp.getPropName() + ",";
						}
						ExamPropQuery query  = new ExamPropQuery();
						
						try{
							query.setSysPropId(curProp.getPropId());
							ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
							
							if (rprop != null)
								for (int k=0; k<rprop.getReturnList().size(); k++)
									for (int g=0; g<rprop.getReturnList().get(k).getIcdList().size(); g++)
										ICD10_names += " " + rprop.getReturnList().get(k).getIcdList().get(g).getName();
						} catch(Exception e){}
					}
					if (courseString != "")
					{
						courseString = courseString.substring(0,courseString.length() - 1);	
					}
					if (subjectString != "")
					{
						subjectString = subjectString.substring(0,subjectString.length() - 1);	
					}
					
		            Label label1 = new Label(0,i+1,courseString);
		            Label label2 = new Label(1,i+1,ICD10_names);
		            Label label3 = new Label(2,i+1,subjectString);
		            Label label4 = new Label(3,i+1,casePatientList.get(i).getPatientObject().getDiagDate());
		            Label label5 = new Label(4,i+1,casePatientList.get(i).getPatientObject().getPName());
		            Label label6;
		            
		            if (casePatientList.get(i).getPatientObject().getSex().equals(0)){
		            	label6 = new Label(5,i+1,"男");	
		            }
		            else {
		            	label6 = new Label(5,i+1,"女");
		            }
		            
		            Label label7 = new Label(6,i+1,casePatientList.get(i).getPatientObject().getBirthday());
		            Label label8 = new Label(7,i+1,casePatientList.get(i).getPatientObject().getpAge().toString());
		            Label label9 = new Label(8,i+1,casePatientList.get(i).getPatientObject().getCertificate());
		            Label label10 = null;
		            if(casePatientList.get(i).getPatientObject().getNumber1Type() != null)
		            {
		            	 switch(casePatientList.get(i).getPatientObject().getNumber1Type())
				         {
					            case 1:
					            	label10 = new Label(9,i+1,"门诊号");	
					            	break;
					            case 2:
					            	label10 = new Label(9,i+1,"住院号");	
					            	break;
					            case 3:
					            	label10 = new Label(9,i+1,"床位号");	
					            	break;
					            case 4:
					            	label10 = new Label(9,i+1,"其他编号");	
					            	break;
					            default:
					            	label10 = new Label(9,i+1,"没有");
					            	break;
					      }
		            }
		            else
		            {
		            	label10 = new Label(9,i+1,"");
		            }
		           
		            
		            Label label11 = new Label(10,i+1,casePatientList.get(i).getPatientObject().getNumber1());
		            Label label12 = new Label(11,i+1,"");
		            if(casePatientList.get(i).getPatientObject().getNumber2Type() != null)
		            {
		            	switch(casePatientList.get(i).getPatientObject().getNumber2Type())
			            {
				            case 1:
				            	label12 = new Label(11,i+1,"门诊号");	
				            	break;
				            case 2:
				            	label12 = new Label(11,i+1,"住院号");	
				            	break;
				            case 3:
				            	label12 = new Label(11,i+1,"床位号");	
				            	break;
				            case 4:
				            	label12 = new Label(11,i+1,"其他编号");
				            	break;
				            default:
				            	break;
			            }	
		            }
		            
		            Label label13 = new Label(12,i+1,casePatientList.get(i).getPatientObject().getNumber2());
		            Label label14 = new Label(13,i+1,casePatientList.get(i).getPatientObject().getPhoneNumber1());
		            Label label15 = new Label(14,i+1,casePatientList.get(i).getPatientObject().getPhoneNumber2());
		            Label label16 = new Label(15,i+1,casePatientList.get(i).getPatientDiagnosisObject());
		            Label label17 = new Label(16,i+1,casePatientList.get(i).getPatientObject().getDiagName());
		            Label label18 = null;
		            switch(casePatientList.get(i).getCaseObject().getState())
		            {
			            case 0:
			            	label18 = new Label(17,i+1,"未上传");	
			            	break;
			            case 1:
			            	label18 = new Label(17,i+1,"未审核");	
			            	break;
			            case 2:
			            	label18 = new Label(17,i+1,"不合格");	
			            	break;
			            case 3:
			            	label18 = new Label(17,i+1,"合格");	
			            	break;
			            case 4:
			            	label18 = new Label(17,i+1,"禁用");	
			            	break;
		            }
		            Label label19 = new Label(18,i+1,casePatientList.get(i).getCaseObject().getDeliOpinion());
		            Label label20 = new Label(19,i+1,casePatientList.get(i).getCaseObject().getDeliMan());
		            Label label21 = new Label(20,i+1,casePatientList.get(i).getCaseDiseaseObject().getDiseaseDate());
		            Label label22 = null;
		            switch(casePatientList.get(i).getCaseDiseaseObject().getDiseaseType())
		            {
			            case 0:
			            	label22 = new Label(21,i+1,"首诊");	
			            	break;
			            case 1:
			            	label22 = new Label(21,i+1,"复诊");	
			            	break;
			            case 2:
			            	label22 = new Label(21,i+1,"入院");	
			            	break;
		            }
		            Label label23 = new Label(22,i+1,casePatientList.get(i).getCaseDiseaseObject().getComplaint());
		            Label label24 = new Label(23,i+1,casePatientList.get(i).getCaseDiseaseObject().getCurrentDisease());
		            Label label25 = new Label(24,i+1,casePatientList.get(i).getCaseDiseaseObject().getHistory1());
		            Label label26 = new Label(25,i+1,casePatientList.get(i).getCaseDiseaseObject().getHistory2());
		            Label label27 = new Label(26,i+1,casePatientList.get(i).getCaseDiseaseObject().getHistory3());
		            Label label28 = new Label(27,i+1,casePatientList.get(i).getCaseDiseaseObject().getHistory4());
		            Label label29 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState1Type().equals(0)){
		            	label29 = new Label(28,i+1,"正常");
		            }
		            else
		            {
		            	label29 = new Label(28,i+1,"异样");
		            }
		            Label label30 = new Label(29,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState1());
		            Label label31 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState2Type().equals(0)){
		            	label31 = new Label(30,i+1,"正常");
		            }
		            else
		            {
		            	label31 = new Label(30,i+1,"异样");
		            }
		            Label label32 = new Label(31,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState2());
		            Label label33 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState3Type().equals(0)){
		            	label33 = new Label(32,i+1,"正常");
		            }
		            else
		            {
		            	label33 = new Label(32,i+1,"异样");
		            }
		            Label label34 = new Label(33,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState3());
		            Label label35 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState4Type().equals(0)){
		            	label35 = new Label(34,i+1,"正常");
		            }
		            else
		            {
		            	label35 = new Label(34,i+1,"异样");
		            }
		            Label label36 = new Label(35,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState4());
		            Label label37 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState5Type().equals(0)){
		            	label37 = new Label(36,i+1,"正常");
		            }
		            else
		            {
		            	label37 = new Label(36,i+1,"异样");
		            }
		            Label label38 = new Label(37,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState5());
		            Label label39 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState6Type().equals(0)){
		            	label39 = new Label(38,i+1,"正常");
		            }
		            else
		            {
		            	label39 = new Label(38,i+1,"异样");
		            }
		            Label label40 = new Label(39,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState6());
		            Label label41 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState7Type().equals(0)){
		            	label41 = new Label(40,i+1,"正常");
		            }
		            else
		            {
		            	label41 = new Label(40,i+1,"异样");
		            }
		            Label label42 = new Label(41,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState7());
		            Label label43 = null;
		            if(casePatientList.get(i).getCaseDiseaseObject().getBodyState8Type().equals(0)){
		            	label43 = new Label(42,i+1,"正常");
		            }
		            else
		            {
		            	label43 = new Label(42,i+1,"异样");
		            }
		            Label label44 = new Label(43,i+1,casePatientList.get(i).getCaseDiseaseObject().getBodyState8());
		            Label label45 = new Label(44,i+1,casePatientList.get(i).getCaseDiseaseObject().getAssay());
		            Label label46 = new Label(45,i+1,casePatientList.get(i).getCaseDiseaseObject().getImage());
		            Label label47 = new Label(46,i+1,casePatientList.get(i).getCaseDiseaseObject().getRest());
		            Label label48 = new Label(47,i+1,casePatientList.get(i).getDiseaseDiagnosisObject());
		            Label label49 = new Label(48,i+1,casePatientList.get(i).getCaseDiseaseObject().getPlan());
		            Label label50 = new Label(49,i+1,casePatientList.get(i).getCaseDiseaseObject().getFutureState());                   
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
		            sheet.addCell(label12);
		            sheet.addCell(label13);
		            sheet.addCell(label14);
		            sheet.addCell(label15);
		            sheet.addCell(label16);
		            sheet.addCell(label17);
		            sheet.addCell(label18);
		            sheet.addCell(label19);
		            sheet.addCell(label20);
		            sheet.addCell(label21);
		            sheet.addCell(label22);
		            sheet.addCell(label23);
		            sheet.addCell(label24);
		            sheet.addCell(label25);
		            sheet.addCell(label26);
		            sheet.addCell(label27);
		            sheet.addCell(label28);
		            sheet.addCell(label29);
		            sheet.addCell(label30);
		            sheet.addCell(label31);
		            sheet.addCell(label32);
		            sheet.addCell(label33);
		            sheet.addCell(label34);
		            sheet.addCell(label35);
		            sheet.addCell(label36);
		            sheet.addCell(label37);
		            sheet.addCell(label38);
		            sheet.addCell(label39);
		            sheet.addCell(label40);
		            sheet.addCell(label41);
		            sheet.addCell(label42);
		            sheet.addCell(label43);
		            sheet.addCell(label44);
		            sheet.addCell(label45);
		            sheet.addCell(label46);
		            sheet.addCell(label47);
		            sheet.addCell(label48);
		            sheet.addCell(label49);
		            sheet.addCell(label50);
		        }
		        
		        book.write();
		        book.close();        
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		    }
		    return "success";
	}
	@SuppressWarnings("deprecation")
	public Integer getAge(Date Birthday){
		Date now = new Date();
		int age = 0;
		if(Birthday != null)
		{
			age = now.getYear() - Birthday.getYear();
		}
		return age;
	}
	public String getPatientDiagnosis(Long patientId){
		String result = "";
		List<CasePatientDiagnosis> curPatientDiagnosis = localCaseManage.getPatientDiagnosisById(patientId);
		for(int i = 0 ; i < curPatientDiagnosis.size(); i++){
			result += curPatientDiagnosis.get(i).getDiagnosis();
			if(i != curPatientDiagnosis.size() - 1){
				result += ",";
			}
		}
		return result;
	}
	public String getDiseaseDiagnosis(Long diseaseId){
		String result = "";
		List<CaseDiseaseDiagnosis> curDiseaseDiagnosis = localCaseManage.getDiseaseDiagnosisById(diseaseId);
		for(int i = 0 ; i < curDiseaseDiagnosis.size(); i++){
			result += curDiseaseDiagnosis.get(i).getDiagnosis();
			if(i != curDiseaseDiagnosis.size() - 1){
				result += ",";
			}
		}
		return result;
	}
}
