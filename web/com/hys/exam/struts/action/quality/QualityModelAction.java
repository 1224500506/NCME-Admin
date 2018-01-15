package com.hys.exam.struts.action.quality;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;


import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class QualityModelAction extends BaseAction {
	private QualityModelManage localQualityModelManage;		
	private ExamPropValFacade localExamPropValFacade;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String mode = RequestUtil.getParameter(request, "mode");
		
		if(mode.equals("delete")){
			return del(mapping, form, request, response);
		}else if(mode.equals("edit")){
			return edit(mapping, form, request, response);
		}else if(mode.equals("add")){
			return add(mapping, form, request, response);
		}else if(mode.equals("total")){
			return total(mapping, form, request, response);
		}else if(mode.equals("twoLevelNameIsSame")){
			return twoLevelNameIsSame(mapping, form, request, response); //YHQ，2017-06-01，判断能力模型二级名称是否重复
		}
		
		Long id = ParamUtils.getLongParameter(request, "typeID", 0L);
		String pageNum = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		String captionName = request.getParameter("captionName");
		Long backId = ParamUtils.getLongParameter(request, "backId", 0L);
		
		QualityModel qualityModel = new QualityModel();
		List<QualityModel> list = new ArrayList<QualityModel>();
		PageList pl = new PageList();
	//BY IE to show Return CaptionName
		String beforeCaptionName ="";
		if(!StringUtils.checkNull(captionName))
		{
			String[] CaptionName = captionName.split(">");
			for(int i=0;i<CaptionName.length-1;i++){
				if(i>0) beforeCaptionName +=">";
				beforeCaptionName += CaptionName[i];
			}
		}
	//
		if (!StringUtil.checkNull(sname) && !pageNum.equals("3")) {	
			ModelType modelType = new ModelType();
			modelType.setName(sname);
			qualityModel.setType(modelType);
		}
		
		//取得类型列型列表
		ExamProp jobclass = new ExamProp();
		jobclass.setType(24);				
		List<ExamProp> jobclassList = localExamPropValFacade.getPropListByType(jobclass);


		
		if(pageNum.equals("1") && id==0L){		
			// paging code added by han.
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			String sort = ParamUtils.getParameter(request, "sort", "id");
			String dir = ParamUtils.getParameter(request, "dir", "desc");
					
			pl.setPageNumber(currentPage);
			pl.setObjectsPerPage(pageSize);
			pl.setSortCriterion(sort);
			if (dir.equals("asc"))
				pl.setSortDirection(SortOrderEnum.ASCENDING);
			else
				pl.setSortDirection(SortOrderEnum.DESCENDING);
			
			// end.
			//list = localQualityModelManage.getQualityModelList(qualityModel);
			localQualityModelManage.getQualityPageList(pl, qualityModel);
		} else if(pageNum.equals("2")){
			
			ModelType modelType = new ModelType();
			modelType.setId(id);
			qualityModel.setType(modelType);
			qualityModel.setName(sname);
			// paging code added by han.
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			String sort = ParamUtils.getParameter(request, "sort", "id ");
			String dir = ParamUtils.getParameter(request, "dir", "desc");
					
			pl.setPageNumber(currentPage);
			pl.setObjectsPerPage(pageSize);
			pl.setSortCriterion(sort);
			if (dir.equals("asc"))
				pl.setSortDirection(SortOrderEnum.ASCENDING);
			else
				pl.setSortDirection(SortOrderEnum.DESCENDING);
			
			// end.
			localQualityModelManage.getNextQualityModelList(pl,qualityModel);		
			
		}else if(pageNum.equals("3")){
			Long parentID = ParamUtils.getLongParameter(request, "typeId", 0L);			
			qualityModel.setParentId(parentID);
			qualityModel.setId(id);
			qualityModel.setName(sname);
			// paging code added by han.
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			String sort = ParamUtils.getParameter(request, "sort", "id ");
			String dir = ParamUtils.getParameter(request, "dir", "desc");
					
			pl.setPageNumber(currentPage);
			pl.setObjectsPerPage(pageSize);
			pl.setSortCriterion(sort);
			if (dir.equals("asc"))
				pl.setSortDirection(SortOrderEnum.ASCENDING);
			else
				pl.setSortDirection(SortOrderEnum.DESCENDING);
			
			// end.
			localQualityModelManage.getNextQualityModelList(pl,qualityModel);
			Long parentId = qualityModel.getId();
			List<PropUnit> zutilist = new ArrayList<PropUnit>();
			zutilist = localQualityModelManage.getZutiListByType();
			request.setAttribute("parentId", parentId);
			request.setAttribute("zutilist", zutilist);
			request.setAttribute("backId", backId);
		}else{}
		request.setAttribute("captionName", captionName);
		request.setAttribute("beforeCaptionName", beforeCaptionName);//BY IE
		request.setAttribute("QM_list", pl);
		request.setAttribute("sname", sname);
		request.setAttribute("typeID", id);
		return "list"+pageNum;
	}
	
	private String twoLevelNameIsSame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");		
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		Long typeId = ParamUtils.getLongParameter(request, "typeId", 0L);
		String result = "no" ;
		QualityModel qualityModel = new QualityModel();	
		ModelType mtObj = new ModelType() ;
		mtObj.setId(typeId);
		qualityModel.setType(mtObj);
		qualityModel.setName(name);
		qualityModel.setParentId(parentId);
		
		boolean qmFlag = localQualityModelManage.compareQualityModel(qualityModel) ;	
		if (qmFlag) result = "yes" ;
		
		JSONObject jsObj = new JSONObject();
		jsObj.put("result", result);
		StrutsUtil.renderText(response, jsObj.toString()); 		
		return null;
	}

	private String total(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		QualityModel qualityModel = new QualityModel();
		qualityModel.setId(id);
		List<QualityModel> list = new ArrayList<QualityModel>(); 
		list = localQualityModelManage.getQualityModelList(qualityModel);
		
		JSONObject result = new JSONObject();
		result.put("item", list.get(0));
		StrutsUtil.renderText(response, result.toString()); 
		
		return null;
	}

	protected String add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		String add_name = request.getParameter("name");	
		
		String param_zuti_ids = request.getParameter("zutiIds");
		List<PropUnit> subjectPropList = new ArrayList<PropUnit>();
		if(!StringUtil.checkNull(param_zuti_ids)){
			String[] str_zuti_ids = param_zuti_ids.split(",");
		
			for (int i=0; i<str_zuti_ids.length; i++) {
				PropUnit prop = new PropUnit();
				if (!StringUtils.checkNull(str_zuti_ids[i].trim())) {
					prop.setId(Long.parseLong(str_zuti_ids[i].trim()));
				}
				subjectPropList.add(prop);
			}
	}
		
		
		QualityModel qualityModel = new QualityModel();	
		ModelType modelType = new ModelType();
		modelType.setId(id);
		qualityModel.setName(add_name);
		qualityModel.setType(modelType);
		qualityModel.setParentId(parentId);
		qualityModel.setSubjectPropList(subjectPropList);
	
	/*if(param_zuti_ids == ""){
		boolean flag = localQualityModelManage.addQualityModel(qualityModel);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
	}
	else{*/
		
		//2017/01/11 Add By IE
		// To Check Compare 能力模型
		
		if(!localQualityModelManage.compareQualityModel(qualityModel)){		
			boolean flag = localQualityModelManage.addQualityModel(qualityModel);
			if(flag == true){
				StrutsUtil.renderText(response, "success");
			}else{
				StrutsUtil.renderText(response, "error");
			}
		}else {
			StrutsUtil.renderText(response, "nameIsSame");
		}
	//}
		return null;
	}

	protected String edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		String update_name = request.getParameter("name");
		String oldName = request.getParameter("oldName");
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		String param_zuti_ids = request.getParameter("zutiIds");
		
		Long typeId = ParamUtils.getLongParameter(request,"typeid",0L);
		
		QualityModel qualityModel = new QualityModel();
		ModelType modelType = new ModelType();
		modelType.setId(typeId);
		qualityModel.setType(modelType);
		qualityModel.setId(id);
		qualityModel.setName(update_name);
		qualityModel.setParentId(parentId);
		
		//2017/01/11 Add By IE
		//To Check Compare 能力模型
		if(StringUtil.checkNull(param_zuti_ids)){
			if(!update_name.equals(oldName) && localQualityModelManage.compareQualityModel(qualityModel)){
				StrutsUtil.renderText(response, "nameIsSame");
			}else{
				boolean flag = localQualityModelManage.updateQualityModel(qualityModel);
				if(flag == true){
					StrutsUtil.renderText(response, "success");
				}else{
					StrutsUtil.renderText(response, "error");
				}
			}
		}else{			
			String[] str_zuti_ids = param_zuti_ids.split(",");
			List<PropUnit> subjectPropList = new ArrayList<PropUnit>();
			for (int i=0; i<str_zuti_ids.length; i++) {
				PropUnit prop = new PropUnit();
				if (!StringUtils.checkNull(str_zuti_ids[i].trim())) {
					prop.setId(Long.parseLong(str_zuti_ids[i].trim()));
				}
				subjectPropList.add(prop);
			}
			qualityModel.setSubjectPropList(subjectPropList);
			if(!update_name.equals(oldName) && localQualityModelManage.compareQualityModel(qualityModel)){
				StrutsUtil.renderText(response, "nameIsSame");
			}else {
				boolean flag = localQualityModelManage.updateQualityModel(qualityModel);
				if(flag == true){
					StrutsUtil.renderText(response, "success");
				}else{
					StrutsUtil.renderText(response, "error");
				}
			}
		}
		return null;
		
	}

	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		QualityModel qualityModel = new QualityModel();
		qualityModel.setId(id);
		
		boolean flag = localQualityModelManage.deleteQualityModel(qualityModel);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	public QualityModelManage getLocalQualityModelManage() {
		return localQualityModelManage;
	}
	public void setLocalQualityModelManage(QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
}
