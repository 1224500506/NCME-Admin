package com.hys.exam.struts.action.material;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.codec.StringEncoder;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.hys.auth.util.FilesUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.auth.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.model.MaterialProp;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.MaterialManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.exam.struts.form.MaterialInfoForm;
import com.hys.exam.utils.FilesUtils;
import com.hys.framework.web.action.BaseAction;


public class MaterialManageAction extends BaseAction {

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
	
	//素材管理
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = RequestUtil.getParameter(request, "mode");

		String handle = request.getParameter("handle") != null ? request.getParameter("handle"): "";
		String propType = request.getParameter("type") != null ? request.getParameter("type") : "";
		String id = request.getParameter("id");

		//查看题库属性
		if(handle.equals("jump")){
			
			String jsonStr = "";
			
			if (propType.equals(Constants.EXAM_PROP_STUDY1) || propType.equals(Constants.EXAM_PROP_STUDY2) || propType.equals(Constants.EXAM_PROP_STUDY3) || propType.equals(Constants.EXAM_PROP_UNIT) || propType.equals(Constants.EXAM_PROP_POINT)) {
				
				List<ExamProp> propList = new ArrayList<ExamProp>();
				
				ExamProp prop = new ExamProp();
				if(propType.equals("1")){
					prop.setType(1);
					propList = localExamPropValFacade.getPropListByType(prop);
				}else{					
					ExamPropQuery query  = new ExamPropQuery();
					
					String[] ids = id.split(", ");
					for (int i=0; i<ids.length; i++) {
						query.setSysPropId(Long.valueOf(ids[i]));
						ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
						for (int k=0; k<rprop.getReturnList().size(); k++)
							propList.add(rprop.getReturnList().get(k));
					}
				}
				jsonStr = JSONArray.fromObject(propList).toString();
				
			} else if (propType.equals(Constants.EXAM_PROP_SOURCE)) {
				
				ExamSource prop = new ExamSource();
				//prop.setPropIds(id);
				
				List<ExamSource> propList = localExamPropValFacade.getSourceValList(prop);
				jsonStr = JSONArray.fromObject(propList).toString();
				
			} else {
				
				Map<String,List<ExamPropVal>> propMap = localExamPropValFacade.getSysPropValBySysId();	
				List<ExamPropVal> propList = new ArrayList<ExamPropVal>();
				
				for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					if(entry.getKey().equals(propType)){
						propList = (List<ExamPropVal>)entry.getValue();
					}
				}
				jsonStr = JSONArray.fromObject(propList).toString();
			}
			
			StrutsUtil.renderText(response, jsonStr);			
			return null;
		}
		
		if (handle.equals("pick")) {
			
			String materialName = request.getParameter("name") != null ? request.getParameter("name"): "";
			if (!materialName.equals("")) {
				MaterialInfo materialInfo = new MaterialInfo();
				materialInfo = localMaterialManageManage.getMaterialInfo(materialName);
				
				if (materialInfo == null)
					StrutsUtil.renderText(response, "good");
				else {
					if (id.equals(materialInfo.getId().toString()))
						StrutsUtil.renderText(response, "good");
					else 
						StrutsUtil.renderText(response, "bad");
				}
			}					
			return null;
		}
		
		if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return edit(mapping, form, request, response);
		}
		else if (method.equals("save")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("lock")){
			return lock(mapping, form, request, response);
		}
		else if (method.equals("detail")){
			return detail(mapping, form, request, response);
		}
		else if (method.equals("update")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("downloadFile")) {
			return downloadFile(request, response);			
		}
		else
			return list(mapping, form, request, response);
	}
	
	protected String lock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MaterialInfoForm eform = (MaterialInfoForm)form;
		MaterialInfo materialInfo = new MaterialInfo();
		
		//设置禁用状态为4
		materialInfo.setId(eform.getId());
		materialInfo = localMaterialManageManage.getMaterialInfo(materialInfo);
		if(materialInfo.getState() != 5)
			materialInfo.setState(5);
		else
			materialInfo.setState(1);
		
		boolean flag = localMaterialManageManage.updateMaterialInfo(materialInfo);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	//添加素材
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MaterialInfo materialInfo = new MaterialInfo();
		request.setAttribute("info", materialInfo);
		
		
		return "add";
	}

	//修改素材
	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MaterialInfoForm eform = (MaterialInfoForm)form;
		MaterialInfo materialInfo = new MaterialInfo();
		
		materialInfo = localMaterialManageManage.getMaterialInfo(eform.getId());
		request.setAttribute("info", materialInfo);
		return "detail";
	}

	//查看素材
	protected String detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MaterialInfoForm eform = (MaterialInfoForm)form;
		MaterialInfo materialInfo = new MaterialInfo();
		
		materialInfo = localMaterialManageManage.getMaterialInfo(eform.getId());
		request.setAttribute("info", materialInfo);

		String attNames = "";
		String attIds = "";
		String ICD10_names = "";
		
		if (materialInfo.getProp_map().get(Constants.EXAM_PROP_STUDY3) != null)
		for (MaterialProp materialProp :materialInfo.getProp_map().get(Constants.EXAM_PROP_STUDY3)) {
			attNames += materialProp.getProp_val_name() + ",";
			attIds += materialProp.getProp_val_id() + ",";
			
			ExamPropQuery query  = new ExamPropQuery();		
			try{
				query.setSysPropId(materialProp.getProp_val_id());
				ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
				
				if (rprop != null)
					for (int k=0; k<rprop.getReturnList().size(); k++)
						for (int g=0; g<rprop.getReturnList().get(k).getIcdList().size(); g++)
							ICD10_names += "," + rprop.getReturnList().get(k).getIcdList().get(g).getName();
			} catch(Exception e){}
		}
		
		if (materialInfo.getProp_map().get(Constants.EXAM_PROP_UNIT) != null)
		for (MaterialProp materialProp :materialInfo.getProp_map().get(Constants.EXAM_PROP_UNIT)) {
			attNames += materialProp.getProp_val_name() + ",";
			attIds += materialProp.getProp_val_id() + ",";
			
			ExamPropQuery query  = new ExamPropQuery();		
			try{
				query.setSysPropId(materialProp.getProp_val_id());
				ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
				
				if (rprop != null)
					for (int k=0; k<rprop.getReturnList().size(); k++)
						for (int g=0; g<rprop.getReturnList().get(k).getIcdList().size(); g++)
							ICD10_names += "," + rprop.getReturnList().get(k).getIcdList().get(g).getName();
			} catch(Exception e){}
		}
		
		if (materialInfo.getProp_map().get(Constants.EXAM_PROP_POINT) != null)
		for (MaterialProp materialProp :materialInfo.getProp_map().get(Constants.EXAM_PROP_POINT)) {
			attNames += materialProp.getProp_val_name() + ",";
			attIds += materialProp.getProp_val_id() + ",";
			
			ExamPropQuery query  = new ExamPropQuery();		
			try{
				query.setSysPropId(materialProp.getProp_val_id());
				ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
				
				if (rprop != null)
					for (int k=0; k<rprop.getReturnList().size(); k++)
						for (int g=0; g<rprop.getReturnList().get(k).getIcdList().size(); g++)
							ICD10_names += "," + rprop.getReturnList().get(k).getIcdList().get(g).getName();
			} catch(Exception e){}
		}
		
		request.setAttribute("unit_names", attNames);
		request.setAttribute("unit_ids", attIds);		
		request.setAttribute("ICD10_names", ICD10_names);
		
		attNames = "";
		attIds = "";
		if (materialInfo.getProp_map().get(Constants.EXAM_PROP_POINT2) != null)
		for (MaterialProp materialProp :materialInfo.getProp_map().get(Constants.EXAM_PROP_POINT2)) {
			attNames += materialProp.getProp_val_name() + ",";
			attIds += materialProp.getProp_val_id() + ",";
		}
		request.setAttribute("subject_names", attNames);
		request.setAttribute("subject_ids", attIds);
		
		attNames = "";
		attIds = "";
		if (materialInfo.getProp_map().get(Constants.EXAM_PROP_TITLE) != null)
		for (MaterialProp materialProp :materialInfo.getProp_map().get(Constants.EXAM_PROP_TITLE)) {
			attNames += materialProp.getProp_val_name() + ",";
			attIds += materialProp.getProp_val_id() + ",";
		}
		request.setAttribute("sector_names", attNames);
		request.setAttribute("sector_ids", attIds);
		
		attNames = "";
		attIds = "";
		if (materialInfo.getProp_map().get(Constants.EXAM_PROP_SOURCE) != null)
		for (MaterialProp materialProp :materialInfo.getProp_map().get(Constants.EXAM_PROP_SOURCE)) {
			attNames += materialProp.getProp_val_name() + ",";
			attIds += materialProp.getProp_val_id() + ",";
		}
		request.setAttribute("source_names", attNames);
		request.setAttribute("source_ids", attIds);
		request.setAttribute("spec",request.getParameter("spec"));
		request.setAttribute("type",request.getParameter("type"));
		
		if (materialInfo.getState() >= 4) 
			return "info";
		
		if (request.getParameter("spec") != null)
			return "info";
		
		return "detail";
	}

	//更新素材
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String stateParam = RequestUtil.getParameter(request, "state");
		String idParams = RequestUtil.getParameter(request, "id");
		String opinion = RequestUtil.getParameter(request, "opinion");
		
		if (stateParam != null && !stateParam.equals("")) {
			
			String[] idArray = idParams.split(",");
			Long[] material_ids = new Long[idArray.length];
			String userName = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
			for (int i=0; i<idArray.length; i++)
			{
				material_ids[i] = Long.parseLong(idArray[i]);
				MaterialInfo curInfo = localMaterialManageManage.getMaterialInfo(Long.parseLong(idArray[i]));
				
					if(userName.equals(curInfo.getExpertName()))
					{
						StrutsUtil.renderText(response, "noPermission");
						return null;
					}				
			}
			int state = Integer.parseInt(stateParam);
			localMaterialManageManage.updateStatesOfMaterial(material_ids, state, opinion, userName);
			StrutsUtil.renderText(response, "success");
			return null;
		}
		
		MaterialInfoForm eform = (MaterialInfoForm)form;
		MaterialInfo materialInfo = new MaterialInfo();
	
		materialInfo.setId(eform.getId());
		materialInfo.setCode(eform.getCode());
		materialInfo.setName(eform.getName());
		materialInfo.setSurname(eform.getSurname());
		materialInfo.setType(eform.getType());
		materialInfo.setFormat(eform.getFormat());

		materialInfo.setDuration(eform.getDuration());
		materialInfo.setSavePath(eform.getSavePath());
		materialInfo.setFps(eform.getFps());
		materialInfo.setResolution(eform.getResolution());
		materialInfo.setNational_state(eform.getNational_state());
		materialInfo.setSummary(eform.getSummary());
		materialInfo.setOtherSource(eform.getOtherSource());
		materialInfo.setExpertName(eform.getExpertName());
		materialInfo.setDeli_opinion(eform.getDeli_opinion());
		materialInfo.setCognize(eform.getCognize());
		materialInfo.setState(materialInfo.getNational_state()+1);
		
		Map<String,List<MaterialProp>> materialPropMap = new HashMap<String, List<MaterialProp>>();
		
		String course = request.getParameter("course")!=null ? request.getParameter("course") : "";
		if (!course.equals("")) {
			String[] courseIds = course.split(",");
			Long[] ids = new Long[courseIds.length];
			for (int i=0; i<courseIds.length; i++) ids[i]= Long.valueOf(courseIds[i].trim()); 
			setPropVal(ids, Constants.EXAM_PROP_UNIT, materialPropMap);
		}
		
		String src = request.getParameter("src")!=null ? request.getParameter("src") : "";
		if (!src.equals("")) {
			String[] srcIds = src.split(",");
			Long[] ids = new Long[srcIds.length];
			for (int i=0; i<srcIds.length; i++) ids[i]= Long.valueOf(srcIds[i].trim()); 
			setPropVal(ids, Constants.EXAM_PROP_SOURCE,materialPropMap);
		}
		
		String subject = request.getParameter("subject")!=null ? request.getParameter("subject") : "";
		if (!subject.equals("")) {
			String[] subjectIds = subject.split(",");
			Long[] ids = new Long[subjectIds.length];
			for (int i=0; i<subjectIds.length; i++) ids[i]= Long.valueOf(subjectIds[i].trim()); 
			setPropVal(ids,Constants.EXAM_PROP_POINT2,materialPropMap);
		}
		
		String sector = request.getParameter("sector")!=null ? request.getParameter("sector") : "";
		if (!sector.equals("")) {
			String[] sectorIds = sector.split(",");
			Long[] ids = new Long[sectorIds.length];
			for (int i=0; i<sectorIds.length; i++) ids[i]= Long.valueOf(sectorIds[i].trim()); 
			setPropVal(ids, Constants.EXAM_PROP_TITLE,materialPropMap);
		}
		
		materialInfo.setProp_map(materialPropMap);
		
		if (materialInfo.getId() == null) {
			if (localMaterialManageManage.addMaterialInfo(materialInfo)) {
				localMaterialManageManage.addMaterialProp(materialInfo);
			}
		}
		else {
			if (localMaterialManageManage.updateMaterialInfo(materialInfo)) {
				localMaterialManageManage.updateMaterialProp(materialInfo);
			}
		}

		FormFile file = eform.getMatFile();
		if (file!= null && !file.getFileName().equals(""))
		{
			materialInfo.setFileName(file.getFileName());
			FilesUtils.materialUpload(file, request, Constants.UPLOAD_FILE_PATH_MATERIAL,materialInfo.getId(),file.getFileName(),eform.getSavePath());
		}
		
		response.sendRedirect("MaterialManage.do");
		return null;
	}

	//删除素材
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {										
								
		MaterialInfoForm eform = (MaterialInfoForm)form;			
		MaterialInfo materialInfo = new MaterialInfo();					
																	
		materialInfo.setId(eform.getId());
		
		boolean flag = localMaterialManageManage.deleteMaterialInfo(materialInfo);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	//禁用素材
	protected String forb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {										
								
		MaterialInfoForm eform = (MaterialInfoForm)form;			
		MaterialInfo materialInfo = new MaterialInfo();					
																	
		materialInfo.setId(eform.getId());
		
		boolean flag = localMaterialManageManage.deleteMaterialInfo(materialInfo);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	//素材列表
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MaterialInfoForm eform = (MaterialInfoForm)form;
		MaterialInfo materialInfo = new MaterialInfo();
		
		materialInfo.setId(eform.getId());
		materialInfo.setCode(eform.getCode());
		materialInfo.setType(eform.getType());
		materialInfo.setName(eform.getName());
		materialInfo.setSurname(eform.getSurname());
		materialInfo.setFileName(eform.getFileName());
		materialInfo.setState(eform.getState());
		materialInfo.setCognize(eform.getCognize());
		materialInfo.setFormat(eform.getFormat());
		materialInfo.setDeli_opinion(eform.getDeli_opinion());
		materialInfo.setOtherSource(eform.getOtherSource());
		materialInfo.setSummary(eform.getSummary());
		
		String[] uploadDate = {(String) request.getParameter("upload_date"), (String) request.getParameter("upload_date1")};
		String[] deliDate = {(String) request.getParameter("deli_date"), (String) request.getParameter("deli_date1")};
		String orderItem = (String) request.getParameter("orderItem");
		int orderBy = (orderItem != null && !orderItem.equals("")) ? Integer.parseInt(orderItem) : 1;
		
		Map<String,List<MaterialProp>> materialPropMap = new HashMap<String, List<MaterialProp>>();
		
		String course = request.getParameter("propIds")!=null ? request.getParameter("propIds") : "";
		if (!course.equals("")) {
			String[] courseIds = course.split(",");
			Long[] ids = new Long[courseIds.length];
			for (int i=0; i<courseIds.length; i++) ids[i]= Long.valueOf(courseIds[i]); 
			setPropVal(ids, Constants.EXAM_PROP_UNIT, materialPropMap);
		}		
		
		String src = request.getParameter("laiIds")!=null ? request.getParameter("laiIds") : "";
		if (!src.equals("")) {
			String[] srcIds = src.split(",");
			Long[] ids = new Long[srcIds.length];
			for (int i=0; i<srcIds.length; i++) ids[i]= Long.valueOf(srcIds[i]); 
			setPropVal(ids, Constants.EXAM_PROP_SOURCE,materialPropMap);
		}
		
		String subject = request.getParameter("zutiIds")!=null ? request.getParameter("zutiIds") : "";
		if (!subject.equals("")) {
			String[] subjectIds = subject.split(",");
			Long[] ids = new Long[subjectIds.length];
			for (int i=0; i<subjectIds.length; i++) ids[i]= Long.valueOf(subjectIds[i]); 
			setPropVal(ids,Constants.EXAM_PROP_POINT2,materialPropMap);
		}
		
		String sector = request.getParameter("dutyIds")!=null ? request.getParameter("dutyIds") : "";
		if (!sector.equals("")) {
			String[] sectorIds = sector.split(",");
			Long[] ids = new Long[sectorIds.length];
			for (int i=0; i<sectorIds.length; i++) ids[i]= Long.valueOf(sectorIds[i]); 
			setPropVal(ids, Constants.EXAM_PROP_TITLE,materialPropMap);
		}
		
		materialInfo.setProp_map(materialPropMap);
		List<MaterialInfo> list =  localMaterialManageManage.getMaterialList(materialInfo, uploadDate, deliDate, orderBy);
		
		if (list != null && list.size() > 0)
		for (MaterialInfo mInfo :list) {
						
			List<MaterialProp> mInfoPropList =  mInfo.getProp_map().get(Constants.EXAM_PROP_UNIT);
			if (mInfoPropList == null || mInfoPropList.size() < 1) continue;
			
			List<MaterialProp> icdPropList = new ArrayList<MaterialProp>();
			
			for (MaterialProp materialProp :mInfoPropList) {
			
				ExamPropQuery query  = new ExamPropQuery();
				try{
					query.setSysPropId(materialProp.getProp_val_id());
					ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
					
					String ICD10_names = "";
					if (rprop != null)
						for (int k=0; k<rprop.getReturnList().size(); k++)
							for (int g=0; g<rprop.getReturnList().get(k).getIcdList().size(); g++)
								ICD10_names += " " + rprop.getReturnList().get(k).getIcdList().get(g).getName();
					
					MaterialProp icdProp = new MaterialProp();
					icdProp.setMaterial_id(materialProp.getMaterial_id());
					icdProp.setProp_val_name(ICD10_names);
					icdPropList.add(icdProp);
					
				} catch(Exception e){}
				
				mInfo.getProp_map().put(Constants.EXAM_PROP_ICD10, icdPropList);
			}
		}
				
		String handle = request.getParameter("handle")==null ? "" : request.getParameter("handle"); 
		if (handle.equals("main2")) {
			List<MaterialInfo> new_list = new ArrayList<MaterialInfo>(); 
			if (list != null && list.size() > 0)
				for (MaterialInfo material_info :list) {
					if (material_info.getState() > 1)
						new_list.add(material_info);
				}
			request.setAttribute("mtlList", new_list);
		} else {
			request.setAttribute("mtlList", list);
		}
		
		for (MaterialInfo material_info :list) {
			material_info.setUpload_date(Utils.dateFormat(Utils.str2Date(material_info.getUpload_date(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm"));
			material_info.setDeli_date(Utils.dateFormat(Utils.str2Date(material_info.getDeli_date(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm"));
		}
		request.setAttribute("info", materialInfo);
		request.setAttribute("upload_date", uploadDate[0]);
		request.setAttribute("upload_date1", uploadDate[1]);
		request.setAttribute("deli_date", deliDate[0]);
		request.setAttribute("deli_date1", deliDate[1]);
		request.setAttribute("propIds", request.getParameter("propIds"));
		request.setAttribute("propNames", request.getParameter("propNames"));
		
		request.setAttribute("zutiIds", request.getParameter("zutiIds"));
		request.setAttribute("zutiNames", request.getParameter("zutiNames"));
		request.setAttribute("orderItem", orderBy);
		
	/*	request.setAttribute("laiIds", request.getParameter("laiIds"));
		request.setAttribute("laiNames", request.getParameter("laiNames"));
		
		request.setAttribute("dutyIds", request.getParameter("dutyIds"));
		request.setAttribute("dutyNames", request.getParameter("dutyNames"));
		*/
		
		request.setAttribute("ICD", request.getParameter("ICD"));
		
		if (handle.equals("main2")) return "main2";
		return "list";
	}
	
	private String downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sel_id = request.getParameter("sel_id");
		
		MaterialInfo materialInfo = new MaterialInfo();
		materialInfo = localMaterialManageManage.getMaterialInfo(Long.parseLong(sel_id));
		try{	
		
			String uploadedFilePath = request.getSession().getServletContext().getRealPath("/") + Constants.UPLOAD_FILE_PATH_MATERIAL + "\\" + materialInfo.getSavePath();
			String uploadedFileName = sel_id + materialInfo.getFileName().substring(materialInfo.getFileName().lastIndexOf("."));
			
			String filename = URLEncoder.encode(materialInfo.getFileName(), "utf-8"); 
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
					
			File file = new File(uploadedFilePath, uploadedFileName);			
			if (file.exists()) {
				byte[] buffer = FilesUtil.readFileToByteArray(file);											
				response.getOutputStream().write(buffer);					
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} else {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("<script>alert('文件不存在!');window.close();</script>");
			}
		}catch(Exception e){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script>alert('文件不存在!');window.close();</script>");
		}
		
		return null; 
	}
	
	private void setPropVal(Long[] propVal,String propKey,Map<String,List<MaterialProp>> questPropMap){
		if(propVal!=null){
			List<MaterialProp> list = new ArrayList<MaterialProp>();
			for(int i=0;i<propVal.length;i++){
				MaterialProp prop = new MaterialProp();
				prop.setProp_val_id(propVal[i]);
				list.add(prop);
			}
			questPropMap.put(propKey, list);
		}
	}

}

