package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp2;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyExamQuesViewAction extends BaseAction {
	
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamPropValFacade localExamPropValFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ExamQuestionForm qform = (ExamQuestionForm)form;
		
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		//试题分类
		List<ExamQuestionType> questType = localExamQuestionTypeFacade.getQuestionTypeRootBySysId(null);
		
		Map<String,List<ExamPropVal>> propMap = localExamPropValFacade.getSysPropValBySysId();
		List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
		for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
				questPropPoint2 = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
				questPropCognize = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
				questPropTitle =(List<ExamPropVal>)entry.getValue();
			}
		}
		
		
		String sign = "VIEW_A";
		
		ExamQuestion quest = localExamQuestionFacade.getQuestionById(qform.getId());
		
		int labelId = quest.getQuestion_label_id();
		String handle = ParamUtils.getParameter(request, "handle", null) ;
		
		if (Constants.A1 == labelId || Constants.A2 == labelId) {
			sign = "VIEW_A";
		}else if (Constants.DXT == labelId) {
			sign = "VIEW_B";
		}else if (Constants.TK == labelId || Constants.JD == labelId || Constants.MCJS == labelId || Constants.NLFX == labelId) {
			sign = "VIEW_C";
		}else if (Constants.PD == labelId) {
			sign = "VIEW_D";
		}else if (Constants.B1 == labelId) {
			sign = "VIEW_B1";
		}else if(Constants.A3 == labelId){
			sign = "VIEW_A3";
		}
		
		request.setAttribute("questPropPoint2", removeRepeat(questPropPoint2,quest.getQuestionPropMap().get(Constants.EXAM_PROP_POINT2)));
		request.setAttribute("questPropCognize", removeRepeat(questPropCognize,quest.getQuestionPropMap().get(Constants.EXAM_PROP_COGNIZE)));
		request.setAttribute("questType", removeRepeatType(questType,quest.getSubSysQuestTypeList()));
		request.setAttribute("questPropTitle", removeRepeat(questPropTitle,quest.getQuestionPropMap().get(Constants.EXAM_PROP_TITLE)));
		
		if(null!=quest.getQuestPropValCascade()){
			request.setAttribute("relationPropList", quest.getQuestPropValCascade().getPropval_name());
			request.setAttribute("relationPropIds", quest.getQuestPropValCascade().getPropval_id());
			if(StringUtils.isNotBlank(quest.getQuestPropValCascade().getPropval_id())){
				setProp(quest, request);
			}
		}
		
		request.setAttribute("handle", handle);
		
		request.setAttribute("labelList", questLabel);
		request.setAttribute("quest", quest);
		
		request.setAttribute("result", Constants.RESULT);
		
		return sign;
	}
	
	//去除试题已存在分类
	@SuppressWarnings("unchecked")
	private List<ExamQuestionType> removeRepeatType(List<ExamQuestionType> list,List<ExamSubSysQuestType> list2){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ExamQuestionType name = (ExamQuestionType) iterator.next();
			if(null != list2) {
				for (Iterator iterator2 = list2.iterator(); iterator2.hasNext();) {
					ExamSubSysQuestType name2 = (ExamSubSysQuestType) iterator2.next();
					if(name.getId().equals(name2.getSub_type_id())) {
						iterator.remove();
					}
				}
			}
		}
		
		return list;		
	}
	
	
	//去除试题已存在属性
	@SuppressWarnings("unchecked")
	private List<ExamPropVal> removeRepeat(List<ExamPropVal> list,List<ExamQuestionProp> list2) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ExamPropVal name = (ExamPropVal) iterator.next();
			
			if(null != list2) {
				for (Iterator iterator2 = list2.iterator(); iterator2.hasNext();) {
					ExamQuestionProp name2 = (ExamQuestionProp) iterator2.next();
					if(name.getId().equals(name2.getProp_val_id())) {
						iterator.remove();
					}
				}
			}
		}
		
		return list;
	}
	
	private void setProp(ExamQuestion quest,HttpServletRequest request){
		String jsonStr = "";
		
		if(null != quest.getQuestPropValCascade()) {
			String ids = quest.getQuestPropValCascade().getPropval_id();
			String names = quest.getQuestPropValCascade().getPropval_name();
			
			String [] idsArry = ids.split("\\+");
			String [] namesArry = names.split("\\+");
			
			List<ExamPropValTemp2> list = new ArrayList<ExamPropValTemp2>();
			
			ExamPropValTemp2 prop = null;
			ExamPropValTemp2 prop2 = null;
			ExamPropValTemp2 prop3 = null;
			ExamPropValTemp2 prop4 = null;
			ExamPropValTemp2 prop5 = null;
			
			for (int i = 0; i < idsArry.length; i++) {
				prop = new ExamPropValTemp2();
				prop2 = new ExamPropValTemp2();
				prop3 = new ExamPropValTemp2();
				prop4 = new ExamPropValTemp2();
				prop5 = new ExamPropValTemp2();
				
				String [] subIds = idsArry[i].split("\\-");
				String [] subNames = namesArry[i].split("\\-");
				if(subIds.length==5) {
					prop.setId(subIds[0]);
					prop2.setId(subIds[1]);
					prop3.setId(subIds[2]);
					prop4.setId(subIds[3]);
					prop5.setId(subIds[4]);
					
					prop.setName(subNames[0]);
					prop2.setName(subNames[1]);
					prop3.setName(subNames[2]);
					prop4.setName(subNames[3]);
					prop5.setName(subNames[4]);
					
					prop2.setParObj(prop);
					prop3.setParObj(prop2);
					prop4.setParObj(prop3);
					prop5.setParObj(prop4);
					list.add(prop5);
				}else if(subNames.length == 4) {
					prop.setId(subIds[0]);
					prop2.setId(subIds[1]);
					prop3.setId(subIds[2]);
					prop4.setId(subIds[3]);
					
					prop.setName(subNames[0]);
					prop2.setName(subNames[1]);
					prop3.setName(subNames[2]);
					prop4.setName(subNames[3]);
					
					prop2.setParObj(prop);
					prop3.setParObj(prop2);
					prop4.setParObj(prop3);
					list.add(prop4);
				}
				
			}
			
			jsonStr = JSONArray.fromObject(list).toString().replace('"', '\'');
			//System.out.println(jsonStr);
		}
		request.setAttribute("relationProp", jsonStr);
	}

	public void setLocalExamQuestionTypeFacade(ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public void setLocalExamQuestionFacade(ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}

	public void setLocalExamQuestionLabelFacade(ExamQuestionLabelFacade localExamQuestionLabelFacade) {
		this.localExamQuestionLabelFacade = localExamQuestionLabelFacade;
	}
}
