package com.hys.exam.struts.action.quality;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.model.UserImage;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.service.local.StudyGuideManage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class StudyGuideAction extends BaseAction {
	
	private StudyGuideManage localStudyGuideManage;
	
	private QualityModelManage localQualityModelManage;
	
	
	public StudyGuideManage getLocalStudyGuideManage() {
		return localStudyGuideManage;
	}

	public void setLocalStudyGuideManage(StudyGuideManage localStudyGuideManage) {
		this.localStudyGuideManage = localStudyGuideManage;
	}
	
	public QualityModelManage getLocalQualityModelManage() {
		return localQualityModelManage;
	}

	public void setLocalQualityModelManage(
			QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		String userImageName = ParamUtils.getParameter(request, "userImageName");

		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		queryGuide.setName(userImageName);
		
		String handle = ParamUtils.getParameter(request, "handle");
		if (handle.equals("add")) {			
			return addActionExecute(request, response);						
		
		}
		 else if (handle.equals("delete")) {
			return deleteActionExecute(request, response);
						
		} else if (handle.equals("getUpdateData")) {
			return UpdateDataActionExecute(request, response);
						
		} else if (handle.equals("update")) {			
			return updateActionExecute(request, response);
						
		} else if (handle.equals("icdUpdate")) {
			return icdUpdateActionExecute(request, response);
						
		} else if (handle.equals("export")) {
			return exportActionExecute(request, response);
						
		} else if (handle.equals("getToAdd")){
				return getToAdd(mapping,form,request,response);
		} else if (handle.equals("getICD")){
			return getICD(mapping,form,request,response);
		} else if(handle.equals("getICDByKey")){
			return getICDByKey(mapping,form,request,response);
		} else if(handle.equals("getEditICD")){
			return getEditICD(mapping,form,request,response);
		} else if(handle.equals("getNextStudyGuide")){
			return getNextStudyGuide(mapping,form,request,response);
		} else {}
		
		Long qualityId=0L;
		
		switch (pageNum.charAt(0)) {
		case '1' :
		{
			PropUnit prop = new PropUnit();
			ModelType type = new ModelType();
			
			
			
			/*String typeName = ParamUtils.getParameter(request, "type");
			if (!StringUtils.checkNull(typeName)) {
				type.setName(typeName);
				image.setType(type);
			}
			String department = ParamUtils.getParameter(request, "department");
			if (!StringUtils.checkNull(department)) {
				prop.setName(department);
				List<PropUnit> departmentPropList = new ArrayList<PropUnit>();
				departmentPropList.add(prop);
				image.setDepartmentPropList(departmentPropList);				
			}*/
			String userImage = ParamUtils.getParameter(request, "userImage");
			if (!StringUtils.checkNull(userImage)) {
				UserImage image = new UserImage();
				image.setName(userImage);
				queryGuide.setUserImage(image);
				
			}
			PageList pl = new PageList();
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
			localStudyGuideManage.getStudyGuidePageList(pl,queryGuide);			
			request.setAttribute("list", pl);
			
			/*request.setAttribute("type", typeName);
			request.setAttribute("department", department);*/
			request.setAttribute("userImage", userImage);
			request.setAttribute("userImageName", userImageName);
			break;
		}
		case '2' :
		{				
			/*Long typeId = ParamUtils.getLongParameter(request, "typeId", 0L);
			String name = ParamUtils.getParameter(request, "name");
			
			QualityModel queryQuality = new QualityModel();
				ModelType modelType = new ModelType();
				modelType.setId(typeId);
			queryQuality.setType(modelType);
			
			if (!name.equals("")) queryQuality.setName(name);
			
			List<QualityModel> list = localQualityModelManage.getNextQualityModelList(queryQuality);

			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("typeId", typeId);
			request.setAttribute("parentName", request.getParameter("parentName"));
			request.setAttribute("name", name);*/
			
			Long parentId = ParamUtils.getLongParameter(request,"parentId",0L);
			String name = ParamUtils.getParameter(request, "name");
			if (parentId == 0L) break; 
		
			queryGuide.setId(parentId);
			//queryGuide.setLevel(1L);
			
			List<StudyGuide> guideList = localStudyGuideManage.getStudyGuideList(queryGuide);
			//queryGuide.setLevel(2L);
			List<UserImage> imageList = guideList.get(0).getUserImageList();	
			
			List<QualityModel> list = new ArrayList<QualityModel>();
			
			long[] fIds = new long[100];
			int cnt = 0;
			
			PageList pl = new PageList();
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
			
			for(int i=0;i<imageList.size();i++){
				Long typeId = imageList.get(i).getType().getId();
				
				int k=0;
				for (k=0; k<cnt; k++) {
					if (fIds[k] == typeId) break;
				}
				if (k < cnt) continue;
				fIds[cnt++] = typeId;
				
				QualityModel queryQuality = new QualityModel();
				ModelType modelType = new ModelType();
				modelType.setId(typeId);
				queryQuality.setType(modelType);
				if (!name.equals("")) queryQuality.setName(name);
				
				
				
				 localQualityModelManage.getNextQualityModelList(pl,queryQuality);
				
				/*for (QualityModel qModel:iList)
					list.add(qModel);*/
			}
			

			request.setAttribute("list", pl);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("parentName", request.getParameter("parentName"));
			request.setAttribute("name", name);
			
			break;
		}
		case '3' :
		{
			qualityId = ParamUtils.getLongParameter(request, "qualityId", 0L);
			String name = ParamUtils.getParameter(request, "name");
			String beforeParentName ="";
			String parentName = request.getParameter("parentName");
			if(!StringUtils.checkNull(parentName))
			{
				String[] CaptionName = parentName.split(">");
				for(int i=0;i<CaptionName.length-1;i++){
					if(i>0) beforeParentName +=">";
					beforeParentName += CaptionName[i];
				}
			}
			
			
			QualityModel queryQuality = new QualityModel();
			queryQuality.setId(qualityId);
			if (!name.equals("")) queryQuality.setName(name);
			PageList pl = new PageList();
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
			localQualityModelManage.getNextQualityModelList(pl,queryQuality);
			
			request.setAttribute("list", pl);
		
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("typeId", ParamUtils.getParameter(request, "typeId"));
			request.setAttribute("qualityId", ParamUtils.getParameter(request, "qualityId"));	
			request.setAttribute("parentName", parentName);
			request.setAttribute("beforeParentName",beforeParentName);
			request.setAttribute("name", name);
			break;
		}
		case '4' :
		{
			qualityId = ParamUtils.getLongParameter(request, "qualityId", 0L);
			Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
			String search_ctr = ParamUtils.getParameter(request, "search_ctr");
			String name = ParamUtils.getParameter(request, "name");			
			String icd_name = ParamUtils.getParameter(request, "icd_name");
			icd_name= StringEscapeUtils.unescapeHtml(icd_name);
			String parentName = request.getParameter("parentName");
			String beforeParentName ="";
			if(!StringUtils.checkNull(parentName))
			{
				String[] CaptionName = parentName.split(">");
				for(int i=0;i<CaptionName.length-1;i++){
					if(i>0) beforeParentName +=">";
					beforeParentName += CaptionName[i];
				}
			}
			
			queryGuide.setParent_id(parentId);
			
			QualityModel quality = new QualityModel();
			quality.setId(qualityId);
			queryGuide.setQuality(quality);
			if (!name.equals("")) queryGuide.setName(name);
			
			List<StudyGuide> list = localStudyGuideManage.getStudyGuideList(queryGuide);			
			
			List<PropUnit> icdPropList = new ArrayList<PropUnit>();
			for (StudyGuide guide:list) {
				if (guide.getIcdPropList() != null) { 					
					for (PropUnit prop:guide.getIcdPropList()) {
						
						if (search_ctr.equals("icd"))
							if (!StringUtils.contains(prop.getName().toLowerCase(), icd_name.toLowerCase()))
								continue;
						boolean flag = true;
						for (PropUnit icdProp:icdPropList) {
							if (prop.getId() == icdProp.getId()) {								
								flag = false;
								break;
							}
						}
						if (flag) icdPropList.add(prop);
					}
				}
			}
			
			List<StudyGuide> new_list = new ArrayList<StudyGuide>();
			for (PropUnit prop:icdPropList) {
				for (StudyGuide guide:list) {
					if (guide.getIcdPropList() == null) continue;
					int i = -1;
					for (i=0; i<guide.getIcdPropList().size(); i++) 
						if (guide.getIcdPropList().get(i).getId() == prop.getId()) break;
					if (i == guide.getIcdPropList().size()) continue;
					
					StudyGuide t_guide = new StudyGuide(guide);					
					List<PropUnit> propList = new ArrayList<PropUnit>();
					propList.add(prop);
					t_guide.setIcdPropList(propList);
					new_list.add(t_guide);
				}
			}
			
			
			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("typeId", ParamUtils.getParameter(request, "typeId"));
			request.setAttribute("qualityId", ParamUtils.getParameter(request, "qualityId"));
			request.setAttribute("new_list", new_list);
			request.setAttribute("new_list_totalSize", new_list.size());
			request.setAttribute("parentName", parentName);
			request.setAttribute("beforeParentName",beforeParentName);
			request.setAttribute("search_ctr", search_ctr);
			request.setAttribute("name", name);
			request.setAttribute("icd_name", icd_name);
			break;
		}
		case '5' :
		{
			Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
			String search_ctr = ParamUtils.getParameter(request, "search_ctr");
			String name = ParamUtils.getParameter(request, "name");			
			String icd_name = ParamUtils.getParameter(request, "icd_name");
			String parentName = request.getParameter("parentName");
			qualityId = ParamUtils.getLongParameter(request, "qualityId", 0L);
			String beforeParentName ="";
			if(!StringUtils.checkNull(parentName))
			{
				String[] CaptionName = parentName.split(">");
				for(int i=0;i<CaptionName.length-1;i++){
					if(i>0) beforeParentName +=">";
					beforeParentName += CaptionName[i];
				}
			}
			
			queryGuide.setId(parentId);			
			
			List<StudyGuide> parentlist = localStudyGuideManage.getStudyGuideList(queryGuide);
			
			queryGuide.setParent_id(parentId);
			if (!name.equals("")) queryGuide.setName(name);
			
			List<StudyGuide> list = localStudyGuideManage.getStudyGuideList(queryGuide);			
			
			List<PropUnit> icdPropList = new ArrayList<PropUnit>();
			for (StudyGuide guide:list) {
				if (guide.getIcdPropList() != null) { 
					for (PropUnit prop:guide.getIcdPropList()) {
						if (search_ctr.equals("icd"))
							if (!StringUtils.contains(prop.getName().toLowerCase(), icd_name.toLowerCase()))
								continue;
						boolean flag = true;
						for (PropUnit icdProp:icdPropList) {
							if (prop.getId() == icdProp.getId()) {
								flag = false;
								break;
							}
						}
						if (flag) icdPropList.add(prop);
					}
				}
			}
			
			List<StudyGuide> new_list = new ArrayList<StudyGuide>();
			for (PropUnit prop:icdPropList) {
				for (StudyGuide guide:list) {
					if (guide.getIcdPropList() == null) continue;
					int i = -1;
					for (i=0; i<guide.getIcdPropList().size(); i++) 
						if (guide.getIcdPropList().get(i).getId() == prop.getId()) break;
					if (i == guide.getIcdPropList().size()) continue;
					
					StudyGuide t_guide = new StudyGuide(guide);					
					List<PropUnit> propList = new ArrayList<PropUnit>();
					propList.add(prop);
					t_guide.setIcdPropList(propList);
					new_list.add(t_guide);
				}
			}
			
			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("icdPropList", parentlist.get(0).getIcdPropList());
			request.setAttribute("new_list", new_list);
			request.setAttribute("new_list_totalSize", new_list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("parentName", parentName);
			request.setAttribute("beforeParentName",beforeParentName);
			request.setAttribute("qualityId", qualityId);
			request.setAttribute("search_ctr", search_ctr);
			request.setAttribute("name", name);
			request.setAttribute("icd_name", icd_name);
			break;
		}
		default:
			break;
		}
		return "list" + pageNum;
	}
	
	private String getEditICD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long Id = ParamUtils.getLongParameter(request, "id", 0L);
		
		List<ExamICD> icdList = new ArrayList<ExamICD>();
		icdList = localStudyGuideManage.getEditICDList(Id);
		JSONObject json = new JSONObject();
		json.put("result", icdList);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}

	private String getICDByKey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		ExamICD icd = new ExamICD();
		List<ExamICD> icdList = new ArrayList<ExamICD>();
		icd.setType(10);
		icd.setName(name);
		icdList = localStudyGuideManage.getIcdListByKey(icd);
		JSONObject json = new JSONObject();
		json.put("result", icdList);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}

	private String getICD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		Long Id = ParamUtils.getLongParameter(request, "id",0L);
		String name = ParamUtils.getParameter(request, "searchName");
		ExamICD icd = new ExamICD();
		List<ExamICD> icdList = new ArrayList<ExamICD>();
		List<ExamICD> icdList1 = new ArrayList<ExamICD>();
		icd.setType(10);
		if(!StringUtils.checkNull(name))
			icd.setName(name);
		if(parentId == 0L)
			icdList = localStudyGuideManage.getICDList(icd);
		else {
			icd.setId(parentId);
			icdList = localStudyGuideManage.getICDList(icd);
		}
		if(Id != 0L){
			icd.setId(Id);
			icdList1 = localStudyGuideManage.getICDList(icd);
		}
			
		JSONObject json = new JSONObject();
		json.put("result", icdList);
		json.put("list",icdList1);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	private String UpdateDataActionExecute(HttpServletRequest request,
			HttpServletResponse response) {
		StudyGuide queryGuide = new StudyGuide();
		Long Id = ParamUtils.getLongParameter(request,"id",0L);
		
		List<StudyGuide> list = new ArrayList<StudyGuide>();
		queryGuide.setId(Id);
		list = localStudyGuideManage.getStudyGuideListById(queryGuide);
		JSONObject json = new JSONObject();
		
		json.put("result", list);
		StrutsUtil.renderText(response, json.toString());
		return "success";
	}
	@SuppressWarnings("unused")
	private String getToAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pageNum = ParamUtils.getParameter(request, "pageNum","1");
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.valueOf(pageNum));
		
		Long qualityId = ParamUtils.getLongParameter(request,"qualityId",0L);
		Long parentId = ParamUtils.getLongParameter(request,"parentId",0L);
		queryGuide.setParent_id(parentId);
		QualityModel quality = new QualityModel();
		quality.setId(qualityId);
		queryGuide.setQuality(quality);
		List<StudyGuide> list = new ArrayList<StudyGuide>();
		list = localStudyGuideManage.getStudyGuideList(queryGuide);
		JSONObject json = new JSONObject();
		json.put("result", list);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}

	private String addActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		Long uiId = ParamUtils.getLongParameter(request, "uiId", 0);
		
		
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		Long qualityId = ParamUtils.getLongParameter(request, "qualityId", 0L);
		String name = ParamUtils.getParameter(request, "name");
		String user_image_id=request.getParameter("userImage_id");
		Long level = ParamUtils.getLongParameter(request, "pageNum", 0L);
		
		String Icd_Id = ParamUtils.getParameter(request, "icdName");
				
		if(!StringUtil.checkNull(Icd_Id)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = Icd_Id.split(",");
			Long[] ICDIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) 
				ICDIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(ICDIDS[i]);
				tempList.add(tempProp[i]);
			}
			queryGuide.setIcdPropList(tempList);
		}
		queryGuide.setName(name);
		queryGuide.setLevel(level);
		queryGuide.setParent_id(parentId);
		List<UserImage> tempList = new ArrayList<UserImage>();
		if(!StringUtils.checkNull(user_image_id))	{
			
			
			String[] str=user_image_id.split(",");
			Long[] IDS = new Long[str.length];
			UserImage[] temp = new UserImage[IDS.length];
			for(int i=0;i<str.length;i++) IDS[i] = Long.valueOf(str[i].trim());
			for(int i=0;i<IDS.length;i++){
				temp[i]=new UserImage();
				temp[i].setId(IDS[i]);
				tempList.add(temp[i]);
			}
			
		  }
		queryGuide.setUserImageList(tempList);
		QualityModel quality = new QualityModel();
		quality.setId(qualityId);
		queryGuide.setQuality(quality);
 		if(localStudyGuideManage.addStudyGuide(queryGuide)){		
 			StrutsUtil.renderText(response, "success");
 		}else{
 			StrutsUtil.renderText(response, "error");
 		}
		
		return null;
	}
	
	private String deleteActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		queryGuide.setId(id);
		if (localStudyGuideManage.deleteStudyGuide(queryGuide))
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		
		return null;
	}
	
	private String updateActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		queryGuide.setId(id);
		String name = ParamUtils.getParameter(request, "name");
		String Icd_Id = ParamUtils.getParameter(request, "icdName");
		
		if(!StringUtil.checkNull(Icd_Id)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = Icd_Id.split(",");
			Long[] ICDIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) 
				ICDIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(ICDIDS[i]);
				tempList.add(tempProp[i]);
			}
			queryGuide.setIcdPropList(tempList);
		}
			String ids = ParamUtils.getParameter(request, "imageId");
		if(!StringUtil.checkNull(ids)){
			List<UserImage> tempList = new ArrayList<UserImage>();
			String[] IDS = ids.split(",");
			Long[] userImageIds = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) userImageIds[i] = Long.valueOf(IDS[i].trim());
			UserImage[] tempProp = new UserImage[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new UserImage();
				tempProp[i].setId(userImageIds[i]);
				tempList.add(tempProp[i]);
			}
			queryGuide.setUserImageList(tempList);
		}
		
		if (!StringUtils.checkNull(name)) queryGuide.setName(name);
		
		if (localStudyGuideManage.updateStudyGuide(queryGuide))
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "error");
		
		return null;
	}
	
	private String icdUpdateActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		int ctr = -1;
		String method = ParamUtils.getParameter(request, "method");
		if (method.equals("add")) 			ctr = 1;
		else if (method.equals("delete"))	ctr = 0;
		else {}
		
		String word = ParamUtils.getParameter(request, "word");
		String[] str_ids = word.split("_");
		Long guideId = Long.parseLong(str_ids[0].trim());
		Long propId = Long.parseLong(str_ids[1].trim());
		
		if (localStudyGuideManage.updateStudyGuideICDs(guideId, propId, ctr))
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, word);	
		
		return null;
	}
	
	private String exportActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=\"card.xls\"");
		OutputStream os = response.getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		List<StudyGuide> list = localStudyGuideManage.getStudyGuideList(queryGuide);
		
		if(!Utils.isListEmpty(list)){
			createSheet(wwb, list);
		}
		
		wwb.write();
		wwb.close();
		os.close();
		response.flushBuffer();
		
		return null;
	}
	
	private void createSheet(WritableWorkbook wwb,List<StudyGuide> list) throws WriteException, RowsExceededException {
		
		String name = "人物";
		
		WritableSheet wsheet = wwb.createSheet("人物画像",wwb.getSheets().length);
		WritableFont wfc = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD);
		
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);  
		wcfFC.setAlignment(Alignment.CENTRE);
		
		
		Label label = new Label(0, 0, "序号", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(1, 0, name + "类型", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(2, 0, name + "科室", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(3, 0, name + "画像",wcfFC);
		wsheet.addCell(label);

		
		if(!Utils.isListEmpty(list)){
			for (int j = 0; j < list.size(); j++) {
				StudyGuide val = list.get(j);
				wfc = new WritableFont(WritableFont.createFont("宋体"),10);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(0, j+1, String.valueOf(val.getId()),wcfFC);
				wsheet.addCell(label);

				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(1, j+1, String.valueOf(val.getUserImage().getType().getName() + "人物类型"),wcfFC);
				wsheet.addCell(label);				
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);				
				String str_departmentList = "";
				for (int i=0; i<val.getUserImage().getDepartmentPropList().size(); i++) {
					str_departmentList += val.getUserImage().getDepartmentPropList().get(i).getName();
				}				
				label = new Label(2, j+1, str_departmentList, wcfFC);
				wsheet.addCell(label);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(3, j+1, val.getUserImage().getName(), wcfFC);
				wsheet.addCell(label);

			}
		}
		
	}
	
	private String getNextStudyGuide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		long id = ParamUtils.getLongParameter(request, "id", -1L);
		long level = ParamUtils.getLongParameter(request, "level", -1L);
		
		if (id < 0 || level < 0) return null;
		
		List<StudyGuide> list = localStudyGuideManage.getNextStudyGuide(id, (int)level);
		
		JSONObject result = new JSONObject();
		result.put("list", list);
		result.put("size", list.size());
		
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}

}
