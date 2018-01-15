package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamQuestionType;
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
 * 作者：Tony 2010-10-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamUpdateQuestionAction extends BaseAction {

	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamPropValFacade localExamPropValFacade;

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}

	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}

	public ExamQuestionLabelFacade getLocalExamQuestionLabelFacade() {
		return localExamQuestionLabelFacade;
	}

	public void setLocalExamQuestionLabelFacade(
			ExamQuestionLabelFacade localExamQuestionLabelFacade) {
		this.localExamQuestionLabelFacade = localExamQuestionLabelFacade;
	}

	public ExamQuestionTypeFacade getLocalExamQuestionTypeFacade() {
		return localExamQuestionTypeFacade;
	}

	public void setLocalExamQuestionTypeFacade(
			ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}

	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		
		ExamQuestionForm qform = (ExamQuestionForm)form;
		
		ExamQuestion quest = new ExamQuestion();
		
		List<ExamSubSysQuestType> subSysQuestTypeList = new ArrayList<ExamSubSysQuestType>();
		
		List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
		
		quest.setId(qform.getId());
		
		int questionLabel = qform.getQuestion_label_id().intValue();
		
		//内容处理
		quest.setContent(qform.getContent());
		quest.setDiffer(qform.getDiffer());
		quest.setGrade(qform.getGrade());
		quest.setParent_id(0L);
		quest.setSeq(0);
		quest.setSource(qform.getSource());
		quest.setSurname(qform.getSurname());
		quest.setQuestion_label_id(questionLabel);
		quest.setAnalyse(qform.getAnalyse());
		quest.setIsnot_multimedia(qform.getIsnot_multimedia());
		
		if(qform.getState()!=null){
			if(qform.getState()==6 || qform.getState()==5){
				quest.setState(qform.getState());
			}
		}
		//试题分类处理
		List<ExamQuestionType> typeList= localExamQuestionTypeFacade.getSubSysTypeByTypeId(qform.getQuestType());
		HashSet m = new HashSet();
		
		for(ExamQuestionType t : typeList){
			ExamSubSysQuestType  type = new ExamSubSysQuestType();
			if(!m.add(t.getSub_sys_id())){
				throw new FrameworkRuntimeException(ErrorCode.ec00002);
			}
			type.setSub_type_id(t.getId());
			type.setSub_sys_id(t.getSub_sys_id());
			type.setState(1);
			type.setQuestion_id(quest.getId());
			subSysQuestTypeList.add(type);
		}
		quest.setSubSysQuestTypeList(subSysQuestTypeList);
		
		String course = request.getParameter("course")!=null ? request.getParameter("course") : "";
		if (!course.equals("")) {
			String[] courseIds = course.split(",");
			Long[] ids = new Long[courseIds.length];
			for (int i=0; i<courseIds.length; i++) ids[i]= Long.valueOf(courseIds[i]); 
			qform.setQuestUnit_4(ids);
		}
		
		String src = request.getParameter("src")!=null ? request.getParameter("src") : "";
		if (!src.equals("")) {
			String[] srcIds = src.split(",");
			Long[] ids = new Long[srcIds.length];
			for (int i=0; i<srcIds.length; i++) ids[i]= Long.valueOf(srcIds[i]); 
			qform.setQuestSource_10(ids);
		}
		
		String subject = request.getParameter("subject")!=null ? request.getParameter("subject") : "";
		if (!subject.equals("")) {
			String[] subjectIds = subject.split(",");
			Long[] ids = new Long[subjectIds.length];
			for (int i=0; i<subjectIds.length; i++) ids[i]= Long.valueOf(subjectIds[i]); 
			qform.setQuestPoint2_7(ids);
		}
		
		String sector = request.getParameter("sector")!=null ? request.getParameter("sector") : "";
		if (!sector.equals("")) {
			String[] sectorIds = sector.split(",");
			Long[] ids = new Long[sectorIds.length];
			for (int i=0; i<sectorIds.length; i++) ids[i]= Long.valueOf(sectorIds[i]); 
			qform.setQuestTitle_9(ids);
		}
		
		Map<String,List<ExamPropVal>> propMap1 = localExamPropValFacade.getSysPropValBySysId();
		List<ExamPropVal> questPropPoint2 = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropCognize = new ArrayList<ExamPropVal>();
		List<ExamPropVal> questPropTitle = new ArrayList<ExamPropVal>();
		
		for (Iterator iter = propMap1.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getKey().equals(Constants.EXAM_PROP_POINT2)){
				questPropPoint2 = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)){
				questPropCognize = (List<ExamPropVal>)entry.getValue();
			} else if(entry.getKey().equals(Constants.EXAM_PROP_TITLE)){
				questPropTitle =(List<ExamPropVal>)entry.getValue();
			}
		}
		
		String cognize = request.getParameter("cognize")!=null ? request.getParameter("cognize") : "";
		if (!cognize.equals("") && !cognize.equals("请选择")) {
			for (int i=0; i<questPropCognize.size(); i++) {
				if (questPropCognize.get(i).getName().equals(cognize)) {
					 Long[] ids = new Long[1];
					 ids[0] = questPropCognize.get(i).getRefId();
					 qform.setQuestCognize_8(ids);
					 break;
				}
			}
		}
		
		
		//属性处理
		String renderSelIds = qform.getRenderSelIds();
		
		ExamQuestionPropValCascade questPropValCascade = new ExamQuestionPropValCascade();
		//questPropValCascade.setPropval_id(renderSelIds);
		//questPropValCascade.setPropval_name(qform.getTxtLoc().replaceAll("\r\n", "+"));
		questPropValCascade.setQuestion_id(quest.getId());
		
		setPropVal(qform.getQuestUnit_4(), Constants.EXAM_PROP_UNIT, questionPropMap, qform.getId());
		setPropVal(qform.getQuestPoint_5(), Constants.EXAM_PROP_POINT, questionPropMap, qform.getId());
		setPropVal(qform.getQuestPoint2_7(),Constants.EXAM_PROP_POINT2,questionPropMap, qform.getId());
		setPropVal(qform.getQuestCognize_8(),Constants.EXAM_PROP_COGNIZE,questionPropMap, qform.getId());
		setPropVal(qform.getQuestTitle_9(), Constants.EXAM_PROP_TITLE,questionPropMap, qform.getId());
		setPropVal(qform.getQuestSource_10(), Constants.EXAM_PROP_SOURCE,questionPropMap, qform.getId());		
		quest.setQuestionPropMap(questionPropMap);
		
		//答案处理
		if(questionLabel == Constants.A1
				|| questionLabel == Constants.A2) { // 单选题
			for (int i = 0; i < qform.getAnswer_content().length; i++) {
				ExamQuestionKey key = new ExamQuestionKey();
				key.setQuestion_id(qform.getId());
				key.setContent(qform.getAnswer_content()[i]);
				if ((qform.getAnswer_key()[0] - 1) == i) {
					key.setIsnot_true(1);
				} else {
					key.setIsnot_true(0);
				}
				key.setSeq(i + 1);
				keyList.add(key);
			}
		} else if (questionLabel == Constants.DXT) { // 多选题
			for (int i = 0; i < qform.getAnswer_content().length; i++) {
				ExamQuestionKey key = new ExamQuestionKey();
				key.setQuestion_id(qform.getId());
				key.setContent(qform.getAnswer_content()[i]);
				for (int j = 0; j < qform.getAnswer_key().length; j++) {
					int intKey = qform.getAnswer_key()[j] - 1;
					if (intKey == i) {
						key.setIsnot_true(1);
						break;
					} else {
						key.setIsnot_true(0);
					}
				}
				key.setSeq(i + 1);
				keyList.add(key);
			}
		} else if (questionLabel == Constants.TK
				|| questionLabel == Constants.JD
				|| questionLabel == Constants.MCJS
				|| questionLabel == Constants.NLFX) { // 填空题
			for (int i = 0; i < qform.getAnswer_content().length; i++) {
				ExamQuestionKey key = new ExamQuestionKey();
				key.setQuestion_id(qform.getId());
				key.setContent(qform.getAnswer_content()[i]);
				key.setSeq(i + 1);
				keyList.add(key);
			}
		} else if (questionLabel == Constants.PD) { // 判断题
			for (int i = 0; i < qform.getAnswer_key().length; i++) {
				ExamQuestionKey key = new ExamQuestionKey();
				key.setQuestion_id(qform.getId());
				if (qform.getAnswer_key()[0] == 1) {
					key.setIsnot_true(1);
				} else {
					key.setIsnot_true(0);
				}
				key.setSeq(i + 1);
				keyList.add(key);
			}
		} else if(questionLabel == Constants.B1){

			List<ExamQuestion> childList = new ArrayList<ExamQuestion>();
			for(int i=0;i<qform.getChild_content().length;i++){
				ExamQuestion childQuestion = new ExamQuestion();

				// 试题类型
				childQuestion.setQuestion_label_id(Constants.B1_1);
				// 题干内容
				childQuestion.setContent(qform.getChild_content()[i]);
				// 试题分析
				childQuestion.setAnalyse(qform.getChild_analyse()[i]);
//				 试题状态
				childQuestion.setState(1);
				// 试题难度
				childQuestion.setGrade(qform.getGrade());
				// 试题区分度
				childQuestion.setDiffer(qform.getDiffer());
				// 试题来源
				childQuestion.setSource(qform.getSource());
				// 试题顺序
				childQuestion.setSeq(i+1);
				// 创建者
				childQuestion.setAuthor("");
				// 试题属性
				Map<String,List<ExamQuestionProp>> childPropList = new HashMap<String,List<ExamQuestionProp>>();
				mapCopy(questionPropMap,childPropList);
				childQuestion.setQuestionPropMap(childPropList);

				//试题分类
				List<ExamSubSysQuestType> childTypeList = new ArrayList<ExamSubSysQuestType>();
				for(ExamSubSysQuestType tmp : subSysQuestTypeList){
					ExamSubSysQuestType nt = new ExamSubSysQuestType();
					BeanUtils.copyProperties(tmp,nt);
					childTypeList.add(nt);
				}
				
				childQuestion.setSubSysQuestTypeList(childTypeList);
				
				// 试题答案
				List<ExamQuestionKey> childKeyList = new ArrayList<ExamQuestionKey>();
				ExamQuestionKey childkey = new ExamQuestionKey();
				// 答案
				childkey.setContent(qform.getChild_answer()[i]);
				// 是否正确
				childkey.setIsnot_true(1);
				// 顺序
				childkey.setSeq(i + 1);
				childKeyList.add(childkey);
				// 试题选项答案
				childQuestion.setQuestionKeyList(childKeyList);
				childList.add(childQuestion);
			}
			// 子试题列表
			quest.setChildQuestionList(childList);
		} else if(questionLabel == Constants.A3){
			
			List<ExamQuestion> childList = new ArrayList<ExamQuestion>();
			if(qform.getSub_questions()!=null){
				for (int i = 0; i < qform.getSub_questions().length; i++) {
					//子试题
					ArrayList sub = StringUtils.stringToArrayList(qform.getSub_questions()[i], "|"); 
					if(sub.get(sub.size()-1).toString().indexOf("//")!= -1){
						sub.add("");
					}
					int sublength = sub.size();
					
					ExamQuestion childQuestion = new ExamQuestion();
					// 试题类型
					childQuestion.setQuestion_label_id(Integer.parseInt(sub.get(0).toString()));
					// 题干内容
					childQuestion.setContent(sub.get(1).toString());
					// 试题分析
					childQuestion.setAnalyse(sub.get(sublength-1).toString());
					// 试题状态
					childQuestion.setState(1);
					// 试题难度
					childQuestion.setGrade(qform.getGrade());
					// 试题区分度
					childQuestion.setDiffer(qform.getDiffer());
					// 试题来源
					childQuestion.setSource(qform.getSource());
					// 试题顺序
					childQuestion.setSeq(i+1);
					// 创建者
					childQuestion.setAuthor("");
					// 试题属性
					Map<String,List<ExamQuestionProp>> childPropList = new HashMap<String,List<ExamQuestionProp>>();
					mapCopy(questionPropMap,childPropList);
					childQuestion.setQuestionPropMap(childPropList);
					//试题分类
					List<ExamSubSysQuestType> childTypeList = new ArrayList<ExamSubSysQuestType>();
					for(ExamSubSysQuestType tmp : subSysQuestTypeList){
						ExamSubSysQuestType nt = new ExamSubSysQuestType();
						BeanUtils.copyProperties(tmp,nt);
						childTypeList.add(nt);
					}
					
					childQuestion.setSubSysQuestTypeList(childTypeList);
					
				
					//子试题选项和答案
					List<ExamQuestionKey> childKeyList = new ArrayList<ExamQuestionKey>();
					List tmp = sub.subList(2, sublength-1);
					for (int j = 0; j < tmp.size(); j++) {
						ExamQuestionKey childkey = new ExamQuestionKey();
						String[] key = tmp.get(j).toString().split("//");
						// 答案
						childkey.setContent(key[0]);
						// 是否正确
						if(key[1]!=null && key[1].trim().equals("1")){
							childkey.setIsnot_true(1);
						}else{
							childkey.setIsnot_true(0);
						}
						// 顺序
						childkey.setSeq(j + 1);
						
						childKeyList.add(childkey);
					}
					// 试题选项答案
					childQuestion.setQuestionKeyList(childKeyList);
					
					childList.add(childQuestion);
				}
			}
			// 子试题列表
			quest.setChildQuestionList(childList);
		}
		
		quest.setQuestionKeyList(keyList);
		quest.setQuestPropValCascade(questPropValCascade);
		
		localExamQuestionFacade.updateQuestionById(quest);
		
		// 试题选项
		quest.setQuestionKeyList(keyList);
		request.setAttribute("labelList", questLabel);
		
		if(null!=quest.getState()){
			return "SUCCESS2";
		}
		
		String handle = request.getParameter("handle") != null ? request.getParameter("handle"): "e";
		
		if(handle.equals("ads_e")){
			return "SUCCESS3";
		} else {
			return "SUCCESS";
		}
		
		
	}
	
	private void setPropVal(Long[] propVal,String propKey,Map<String,List<ExamQuestionProp>> questPropMap,Long questId){
		if(propVal!=null){
			List<ExamQuestionProp> list = new ArrayList<ExamQuestionProp>();
			for(int i=0;i<propVal.length;i++){
				ExamQuestionProp prop = new ExamQuestionProp();
				prop.setProp_val_id(propVal[i]);
				prop.setQuestion_id(questId);
				list.add(prop);
			}
			questPropMap.put(propKey, list);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void mapCopy(Map<String,List<ExamQuestionProp>> source,Map<String,List<ExamQuestionProp>> target){
		for (Iterator iter = source.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			List<ExamQuestionProp> srcList = (List<ExamQuestionProp>) entry.getValue();
			List<ExamQuestionProp> targetList = new ArrayList<ExamQuestionProp>();
			for(ExamQuestionProp o: srcList){
				ExamQuestionProp p = new ExamQuestionProp();
				BeanUtils.copyProperties(o,p);
				p.setProp_val_name("");
				targetList.add(p);
			}
			target.put(key, targetList);
		}
	}
}
