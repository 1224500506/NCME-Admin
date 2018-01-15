package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamAddQuestionPropAction extends BaseAction {

	private ExamQuestionLabelFacade localExamQuestionLabelFacade;

	private ExamQuestionFacade localExamQuestionFacade;
	
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
		
		ExamQuestionForm qform = (ExamQuestionForm)form;
		String renderSelIds = qform.getRenderSelIds();
		String renderSelNames = qform.getTxtLoc();
		
		int status = qform.getState();
		
		if(qform.getQuestion_label_id() != null &&  qform.getQuestion_label_id().intValue() != -1){
			request.setAttribute("questionLabelId", qform.getQuestion_label_id());
		}		
		
		// 试题内容
		if(qform.getContent() != null && !qform.getContent().trim().equals("")){
			request.setAttribute("content", qform.getContent());
		}
		// 创建者
		if(qform.getAuthor() != null && !qform.getAuthor().trim().equals("")){
			request.setAttribute("author", qform.getAuthor());
		}
		// 试题来源
		if(qform.getSource() != null && !qform.getSource().trim().equals("")){
		}
		// 是否含有试题分析
		if(qform.getIsExistAnalyse() != null && qform.getIsExistAnalyse().intValue() != -1){
		}
		
		//创建时间
		if(!StringUtils.checkNull(qform.getCreate_date())){
			request.setAttribute("createDate", qform.getCreate_date());
		}
		
		List<Long> ids = new ArrayList<Long>();		
		
		for(Long id : qform.getIds()){
			ids.add(id);
		}
		
		List<ExamQuestion> q = localExamQuestionFacade.getQuestionIdByIds(ids);
		
		
		//试题属性批量添加
		if(q!=null && !q.isEmpty()){
		
			
			Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
			
			List<ExamQuestionPropValCascade> questPropValCascade = new ArrayList<ExamQuestionPropValCascade>();
			
			//关联属性处理
			if(!StringUtils.checkNull(renderSelIds)){
				
				for(Long id : qform.getIds()){
					ExamQuestionPropValCascade cascade = new ExamQuestionPropValCascade();
					cascade.setQuestion_id(id);
					cascade.setPropval_id(renderSelIds);
					cascade.setPropval_name(renderSelNames);
					questPropValCascade.add(cascade);
				}	
				
				
				List<ExamQuestionProp> list1 = new LinkedList<ExamQuestionProp>();
				List<ExamQuestionProp> list2 = new LinkedList<ExamQuestionProp>();
				List<ExamQuestionProp> list3 = new LinkedList<ExamQuestionProp>();
				List<ExamQuestionProp> list4 = new LinkedList<ExamQuestionProp>();
				List<ExamQuestionProp> list5 = new LinkedList<ExamQuestionProp>();
				
				String[] array = renderSelIds.split("\\+");
				for (int i = 0; i < array.length; ++i) {
					String[] propValId = array[i].split("\\-");
					
					for (int j = 0; j < propValId.length; j++) {
						ExamQuestionProp eqp = new ExamQuestionProp();
						eqp.setProp_val_id(Long.valueOf(propValId[j]));
						switch (j) {
						case 0:
							if(!list1.contains(eqp)) {
								list1.add(eqp);
							}
							break;
						case 1:
							if(!list2.contains(eqp)) {
								list2.add(eqp);
							}
							break;
						case 2:
							if(!list3.contains(eqp)) {
								list3.add(eqp);
							}
							break;
						case 3:
							if(!list4.contains(eqp)) {
								list4.add(eqp);
							}
							break;
						case 4:
							if(!list5.contains(eqp)) {
								list5.add(eqp);
							}
							break;
						}
					}
				}
				setPropr(q,list1,questionPropMap,"1");
				setPropr(q,list2,questionPropMap,"2");
				setPropr(q,list3,questionPropMap,"3");
				setPropr(q,list4,questionPropMap,"4");
				setPropr(q,list5,questionPropMap,"5");
						
			}
			//非关联属性处理
			/*
			if(qform.getQuestPoint2_7()!=null){
				setProp(q,qform.getQuestPoint2_7(),questionPropMap,"7");
			}	
			if(qform.getQuestCognize_8()!=null){
				setProp(q,qform.getQuestCognize_8(),questionPropMap,"8");
			}
			if(qform.getQuestLib_9()!=null){
				setProp(q,qform.getQuestLib_9(),questionPropMap,"9");
			}
			if(qform.getQuestTitle_13()!=null){
				setProp(q,qform.getQuestTitle_13(),questionPropMap,"13");
			}
			*/
			localExamQuestionFacade.addQuestionProp(questionPropMap,ids,questPropValCascade,status);
		}else{
			return "FAILURE";
		}
		return "SUCCESS";
	}
	
	private void setPropr(List<ExamQuestion> idArr,List<ExamQuestionProp> questPropList,Map<String,List<ExamQuestionProp>> questionPropMap,String key){
		List<ExamQuestionProp> questPropListTmp  = new ArrayList<ExamQuestionProp>();
		for(ExamQuestion quest: idArr){
			ExamQuestionProp questPropTmp;
			for(ExamQuestionProp questProp: questPropList){
				questPropTmp = new ExamQuestionProp();
				questPropTmp.setQuestion_id(quest.getId());
				questPropTmp.setProp_val_id(questProp.getProp_val_id());
				questPropListTmp.add(questPropTmp);
			}
		}
		questionPropMap.put(key, questPropListTmp);
	}
	
	/*
	private void setProp(List<ExamQuestion> idArr,Long[] propValArr,Map<String,List<ExamQuestionProp>> questionPropMap,String key){
		List<ExamQuestionProp>  questPropList = new ArrayList<ExamQuestionProp>();
		for(ExamQuestion quest: idArr){
			ExamQuestionProp questProp;
			for(Long propVal: propValArr){
				questProp = new ExamQuestionProp();
				questProp.setQuestion_id(quest.getId());
				questProp.setProp_val_id(propVal);
				questPropList.add(questProp);
			}
		}
		questionPropMap.put(key, questPropList);
	}
	*/

}
