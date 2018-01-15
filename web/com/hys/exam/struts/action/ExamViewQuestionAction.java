package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp2;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
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
public class ExamViewQuestionAction extends BaseAction {
	
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	private ExamPropValFacade localExamPropValFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;
	
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private JdbcTemplate jdbcTemplate;
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ExamQuestionTypeFacade getLocalExamQuestionTypeFacade() {
		return localExamQuestionTypeFacade;
	}

	public void setLocalExamQuestionTypeFacade(
			ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}

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

	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ExamQuestionForm qform = (ExamQuestionForm)form;
		
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1); //get QuestionTypes 
		//试题分类
		List<ExamQuestionType> questType = localExamQuestionTypeFacade.getQuestionTypeRootBySysId(null);
				
		String sign = "A";
		
		ExamQuestion quest = localExamQuestionFacade.getQuestionById(qform.getId()); // get QuestionInformation by Id
		//String handle = request.getParameter("handle");
		
		int labelId = quest.getQuestion_label_id();
		String labelName = "";
		for (int i=0; i<questLabel.size(); i++) {
			if (questLabel.get(i).getId() == labelId) { 
				labelName = questLabel.get(i).getName();
				break;
			}
		}
		
		if (Constants.A1 == labelId || Constants.A2 == labelId) {
			sign = "A";
		}else if (Constants.DXT == labelId) {
			sign = "B";
		}else if (Constants.TK == labelId || Constants.JD == labelId
				|| Constants.MCJS == labelId || Constants.NLFX == labelId) {
			sign = "C";
		}else if (Constants.PD == labelId) {
			sign = "D";
		}else if (Constants.B1 == labelId) {
			sign = "B1";
		}else if(Constants.A3 == labelId){
			sign = "A3";
		}
		
		if(quest.getState()==6){
			//导入试题
			sign = "NOT_PROP_" + sign;
		}else{
			//非导入试题
			String handle = request.getParameter("handle") != null ? request.getParameter("handle"): "v";
			if(handle.equals("v") || handle.equals("ads_v")){
				sign = "VIEW_" + sign;
			}else{
				sign = "EDIT_" + sign;
			}
			request.setAttribute("handle", handle);
		}
		request.setAttribute("type", request.getParameter("type"));
		
		Map<String,List<ExamQuestionProp>> propMap = quest.getQuestionPropMap();
		List<ExamQuestionProp> questPropUnit_4 = new ArrayList<ExamQuestionProp>();
		List<ExamQuestionProp> questPropPoint_5 = new ArrayList<ExamQuestionProp>();
		List<ExamQuestionProp> questPropPoint2_7 = new ArrayList<ExamQuestionProp>();
		List<ExamQuestionProp> questPropCognize_8 = new ArrayList<ExamQuestionProp>();
		List<ExamQuestionProp> questPropTitle_9 = new ArrayList<ExamQuestionProp>();
		List<ExamQuestionProp> questPropSource_10 = new ArrayList<ExamQuestionProp>();
		List<ExamQuestionProp> questPropICD10_11 = new ArrayList<ExamQuestionProp>();
		
		for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			if (entry.getKey().equals(Constants.EXAM_PROP_UNIT)) {
				questPropUnit_4 = (List<ExamQuestionProp>)entry.getValue();
				String names = "";
				String ids = "";
				String ICD10_names = "";
				for (int i=0; i<questPropUnit_4.size(); i++) {
					if (i > 0) {
						names += ",";
						ids += ",";
					}						
					
					try{						
						ExamPropQuery query  = new ExamPropQuery();
//						String my_sql = "select id from SUB_SYS_PROP_VAL where prop_val_id=" + questPropUnit_4.get(i).getProp_val_id();
//						Long id = getJdbcTemplate().queryForLong(my_sql);
						query.setSysPropId(questPropUnit_4.get(i).getProp_val_id());						
//						questPropUnit_4.get(i).setProp_val_id(id);
						
						ids += questPropUnit_4.get(i).getProp_val_id();
						names += questPropUnit_4.get(i).getProp_val_name();		
						
						
					} catch(Exception e){}
				}
				//根据学科得到相对应的ICD10的值
				List<String> reslist = new ArrayList<String>();
				List<ExamICD> list = localExamPropValFacade.getICDListByPropIds(ids);
				if (list!=null)
				for(int i = 0; i<list.size(); i++){
					if(list.get(i).getType() == 10)	//ICD10
					reslist.add(list.get(i).getName());
				}

				ICD10_names = reslist.toString();
				ICD10_names = ICD10_names.substring(1, ICD10_names.length()-1);
				request.setAttribute("unit_names", names);
				request.setAttribute("unit_ids", ids);
				
				request.setAttribute("ICD10_names", ICD10_names);
				
			} else if (entry.getKey().equals(Constants.EXAM_PROP_POINT)) {
				questPropPoint_5 = (List<ExamQuestionProp>)entry.getValue();
				String names = "";
				String ids = "";
				for (int i=0; i<questPropPoint_5.size(); i++) {
					if (i > 0) {
						names += ",";
						ids += ",";
					}
					names += questPropPoint_5.get(i).getProp_val_name();
					ids += questPropPoint_5.get(i).getProp_val_id();
				}
				request.setAttribute("point_names", names);
				request.setAttribute("point_ids", ids);
			} else if (entry.getKey().equals(Constants.EXAM_PROP_POINT2)) {
				questPropPoint2_7 = (List<ExamQuestionProp>)entry.getValue();
				String names = "";
				String ids = "";
				for (int i=0; i<questPropPoint2_7.size(); i++) {
					if (i > 0) {
						names += ",";
						ids += ",";
					}
					try{	
/*						ExamPropQuery query  = new ExamPropQuery();
						String my_sql = "select id from SUB_SYS_PROP_VAL where prop_val_id=" + questPropPoint2_7.get(i).getProp_val_id();
						Long id = getJdbcTemplate().queryForLong(my_sql);
						query.setSysPropId(id);						
						questPropPoint2_7.get(i).setProp_val_id(id);
*/						
						names += questPropPoint2_7.get(i).getProp_val_name();
						ids += questPropPoint2_7.get(i).getProp_val_id();
						
					} catch(Exception e){}									
				}
				request.setAttribute("subject_names", names);
				request.setAttribute("subject_ids", ids);
			} else if (entry.getKey().equals(Constants.EXAM_PROP_COGNIZE)) {
				questPropCognize_8 = (List<ExamQuestionProp>)entry.getValue();
				String names = "";
				String ids = "";
				for (int i=0; i<questPropCognize_8.size(); i++) {
					if (i > 0) {
						names += ",";
						ids += ",";
					}
					names += questPropCognize_8.get(i).getProp_val_name();
					ids += questPropCognize_8.get(i).getProp_val_id();
				}
				request.setAttribute("cognize_names", names);
				request.setAttribute("cognize_ids", ids);
			} else if (entry.getKey().equals(Constants.EXAM_PROP_TITLE)) {
				questPropTitle_9 = (List<ExamQuestionProp>)entry.getValue();
				String names = "";
				String ids = "";
				for (int i=0; i<questPropTitle_9.size(); i++) {
					if (i > 0) {
						names += ",";
						ids += ",";
					}
					
					try{	
/*						ExamPropQuery query  = new ExamPropQuery();
						String my_sql = "select id from SUB_SYS_PROP_VAL where prop_val_id=" + questPropTitle_9.get(i).getProp_val_id();
						Long id = getJdbcTemplate().queryForLong(my_sql);
						query.setSysPropId(id);						
						questPropTitle_9.get(i).setProp_val_id(id);
						
*/						names += questPropTitle_9.get(i).getProp_val_name();
						ids += questPropTitle_9.get(i).getProp_val_id();
						
					} catch(Exception e){}
				}
				request.setAttribute("sector_names", names);
				request.setAttribute("sector_ids", ids);
			} else if (entry.getKey().equals(Constants.EXAM_PROP_SOURCE)) {//来源
				questPropSource_10 = (List<ExamQuestionProp>)entry.getValue();
				String names = "";
				String ids = "";
				for (int i=0; i<questPropSource_10.size(); i++) {
					if (i > 0) {
						names += ",";
						ids += ",";
					}
					names += questPropSource_10.get(i).getProp_val_name();
					ids += questPropSource_10.get(i).getProp_val_id();
				}
				request.setAttribute("source_names", names);
				request.setAttribute("source_ids", ids);
			} else {}
		}
		
		int multimedia = quest.getIsnot_multimedia() == null ? 0:quest.getIsnot_multimedia();
		String isMedia = multimedia == 0 ? "不" : "是";//是否多媒体试题
		String isUpload = quest.getState() == 0 ? "不" : "是";//是否上传国家库
		
		
		List<ExamPropVal> propCognizeList = localExamPropValFacade.getSysPropValBySysId().get(Constants.EXAM_PROP_COGNIZE);
		
		request.setAttribute("id", quest.getId());
		request.setAttribute("labelList", questLabel);
		request.setAttribute("cognizeList", propCognizeList);
		request.setAttribute("labelName", labelName);
		request.setAttribute("quest", quest);
		request.setAttribute("isMedia", isMedia);
		request.setAttribute("questionLabel", labelId);
		request.setAttribute("isUpload", isUpload);
		if(labelId == 13)
		{
			request.setAttribute("answer_key", quest.getQuestionKeyList().get(0).getIsnot_true());
		}
		
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
			
			//String [] idsArry = ids.split("\\+");
			
			List<ExamPropValTemp2> list = new ArrayList<ExamPropValTemp2>();
			
			jsonStr = JSONArray.fromObject(list).toString().replace('"', '\'');
			//System.out.println(jsonStr);
		}
		request.setAttribute("relationProp", jsonStr);
	}

}
