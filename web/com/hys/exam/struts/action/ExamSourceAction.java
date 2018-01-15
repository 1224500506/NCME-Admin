package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.ResponseUtils;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.model.MaterialProp;
import com.hys.exam.service.local.MaterialManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExamPropForm;
import com.hys.framework.web.action.BaseAction;
import org.displaytag.properties.SortOrderEnum;

/**
 * 来源管理
 * @author Han
 *
 */
public class ExamSourceAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;

	private MaterialManageManage localMaterialManageManage;

	public MaterialManageManage getLocalMaterialManageManage() {
		return localMaterialManageManage;
	}

	public void setLocalMaterialManageManage(
			MaterialManageManage localMaterialManageManage) {
		this.localMaterialManageManage = localMaterialManageManage;
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

		String method = RequestUtil.getParameter(request, "mode");
		if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("updateState")){
			return updateState(request, response);
		}
		else if (method.equals("merge")){
			return merge(mapping, form, request, response);
		}
		else if (method.equals("showrel")){
			return showRel(mapping, form, request, response);
		}
		else if (method.equals("addrel")){
			return addRel(mapping, form, request, response);
		}
		else if (method.equals("getListByType")){
			return getListByType(mapping, form, request, response);
		}
		else{
			return list(mapping, form, request, response);
		}
	}
	
	/**
	 * 添加来源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExamPropForm pform = (ExamPropForm)form;
		ExamSource prop = new ExamSource();
		
		prop.setCode(pform.getCode());
		prop.setName(pform.getPropName());
		prop.setType(pform.getProp_val1());
		prop.setSource(pform.getSource());
		prop.setOld(pform.getOld());
		prop.setZhutiIds(pform.getHint());
//		prop.setPropIds(pform.getHint());
		
		boolean flag = localExamPropValFacade.addSourceVal(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	/**
	 * 修改来源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExamPropForm pform = (ExamPropForm)form;
		ExamSource prop = new ExamSource();
		
		prop.setCode(pform.getCode());
		prop.setName(pform.getPropName());
		prop.setType(pform.getProp_val1());
		prop.setId(pform.getId());
		prop.setSource(pform.getSource());
		prop.setOld(pform.getOld());
		prop.setZhutiIds(pform.getHint());
//		prop.setPropIds(pform.getHint());
		
		boolean flag = localExamPropValFacade.updateSourceVal(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	/**
	 * 审核来源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String updateState(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExamSource prop = new ExamSource();
		Long id = Long.parseLong(request.getParameter("id"));
		Integer state = Integer.parseInt(request.getParameter("state"));
		prop.setId(id);
		prop.setState(state);
		boolean flag = localExamPropValFacade.updateSourceVal(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	/**
	 * 删除来源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExamPropForm pform = (ExamPropForm)form;
		ExamSource prop = new ExamSource();
		
	//	prop.setProp_val_id(pform.getProp_val_id());
		prop.setId(pform.getId());
		
		boolean flag = localExamPropValFacade.deleteSourceVal(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	/**
	 * 合并来源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String merge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = request.getParameter("target") == null ? "0" : request.getParameter("target");
		String code = request.getParameter("code") == null ? "" : request.getParameter("code");
		
		String[] srcIds = code.split(",");
	
		Map<Long,Long>propMap = new LinkedHashMap<Long, Long>();
		Map<Long,Long>zhutiMap = new LinkedHashMap<Long, Long>();
		ExamSource prop = new ExamSource();
		for (int i = 0; i < srcIds.length; i++){
			if(srcIds[i].equals("")) continue;
			prop.setId(Long.valueOf(srcIds[i]));
			List<ExamSource> propList = localExamPropValFacade.getSourceValList(prop);
			List<ExamProp> props =  propList.get(0).getProp();
			
			for (int j = 0; j < props.size(); j++){
				propMap.put(props.get(j).getProp_val_id(), props.get(j).getProp_val_id());
			}
			//
			props =  propList.get(0).getZhuti();
			for (int j = 0; j < props.size(); j++){
				zhutiMap.put(props.get(j).getProp_val_id(), props.get(j).getProp_val_id());
			}
			
			prop.setPropIds("");
			prop.setZhutiIds("");
			localExamPropValFacade.updateSourceVal(prop);
			
			localMaterialManageManage.updateMaterialSourceId(Long.valueOf(srcIds[i]), Long.valueOf(target));
			if (!target.equals(srcIds[i]))
				localExamPropValFacade.deleteSourceVal(prop);
		}

		prop.setId(Long.valueOf(target));
		String propIds = propMap.keySet().toString();
		propIds = propIds.substring(1, propIds.length()-1);
		propIds = propIds.replaceAll(" ", "");
		prop.setPropIds(propIds);
		
		propIds = zhutiMap.keySet().toString();
		propIds = propIds.substring(1, propIds.length()-1);
		propIds = propIds.replaceAll(" ", "");
		prop.setZhutiIds(propIds);
		
		localExamPropValFacade.updateSourceVal(prop);

		StrutsUtil.renderText(response, "success");
		return null;
	}

	/**
	 * 显示关联学科页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String addRel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String propIds = request.getParameter("groupIds") == null ? "" : request.getParameter("groupIds");
		
		ExamSource prop = new ExamSource();
		prop.setId(Long.valueOf(id));
		prop.setPropIds(propIds);
		
		localExamPropValFacade.updateSourceVal(prop);
		
		response.sendRedirect("sourceList.do");
		return null;
	}

	/**
	 * 显示关联学科页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String showRel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");

		ExamSource prop = new ExamSource();
		prop.setId(Long.valueOf(id));
		List<ExamSource> propList = localExamPropValFacade.getSourceValList(prop);
		
		request.setAttribute("selid", id);
		request.setAttribute("propList", propList.get(0));
		return "SHOWREL";
	}

	/**
	 * 取得来源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    protected String list(ActionMapping mapping, ActionForm form,
                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
        String stype = request.getParameter("stype") == null ? "0" : request.getParameter("stype");
        String propIds = request.getParameter("propIds") == null ? "" : request.getParameter("propIds");
        String propNames = request.getParameter("propNames") == null ? "" : request.getParameter("propNames");
        String chooseSourseIDs = request.getParameter("chooseSourseIDs") == null ? "" : request.getParameter("chooseSourseIDs");
        String chooseSourseBookID = request.getParameter("chooseSourseBookID") == null ? "" : request.getParameter("chooseSourseBookID");
        String chooseSourseKnowledgeID = request.getParameter("chooseSourseKnowledgeID") == null ? "" : request.getParameter("chooseSourseKnowledgeID");
        String chooseSourseReferenceID = request.getParameter("chooseSourseReferenceID") == null ? "" : request.getParameter("chooseSourseReferenceID");
        String soure_type = request.getParameter("soure_type") == null ? "" : request.getParameter("soure_type");
        ExamSource prop = new ExamSource();
        prop.setName(sname);
        prop.setType(Long.valueOf(stype));
        prop.setPropIds(propIds);

        PageList pl = new PageList();
        int currentPage = PageUtil.getPageIndex(request);
        int pageSize = PageUtil.getPageSize(request);
        String sort = ParamUtils.getParameter(request, "sort", "t.id");
        String dir = ParamUtils.getParameter(request, "dir", "desc");

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(pageSize);
        pl.setSortCriterion(sort);
        if (dir.equals("asc"))
            pl.setSortDirection(SortOrderEnum.ASCENDING);
        else
            pl.setSortDirection(SortOrderEnum.DESCENDING);

        localExamPropValFacade.getSourceValPageList(pl, prop);
        //List<ExamSource> list = localExamPropValFacade.getSourceValList(prop);

        List<ExamSourceType> typeList = localExamPropValFacade.getSourceTypeList(new ExamSourceType());

        request.setAttribute("propList", pl);
        request.setAttribute("typeList", typeList);
        request.setAttribute("stype", stype);
        request.setAttribute("chooseSourseIDs", chooseSourseIDs);
        request.setAttribute("chooseSourseBookID", chooseSourseBookID);
        request.setAttribute("chooseSourseKnowledgeID", chooseSourseKnowledgeID);
        request.setAttribute("chooseSourseReferenceID", chooseSourseReferenceID);
        request.setAttribute("soure_type", soure_type);
        request.setAttribute("propIds", propIds);
        request.setAttribute("propNames", propNames);
        request.setAttribute("sname", sname);
        return "SUCCESS";
    }

	/**
	 * AJAX 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String getListByType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String typeid = request.getParameter("typeid") == null ? "0" : request.getParameter("typeid");
		String state = request.getParameter("state") == null ? "1" : request.getParameter("state");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		
		ExamSource prop = new ExamSource();
		prop.setType(Long.valueOf(typeid));
		prop.setName(sname);
		
		Integer stateVal = Integer.valueOf(state);
		if (stateVal == -1)
			prop.setState(null);
		else
			prop.setState(stateVal);
		
		List<ExamSource> list = localExamPropValFacade.getSourceValList(prop);

		JSONObject result = new JSONObject();
		result.put("list", list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
}
