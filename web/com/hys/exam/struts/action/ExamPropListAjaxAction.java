package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.CVSetAuthorCheck;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVSetAuthorizationManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;


public class ExamPropListAjaxAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;
	
	private CVSetAuthorizationManage cVSetAuthorizationManage;
	

	public CVSetAuthorizationManage getcVSetAuthorizationManage() {
		return cVSetAuthorizationManage;
	}

	public void setcVSetAuthorizationManage(
			CVSetAuthorizationManage cVSetAuthorizationManage) {
		this.cVSetAuthorizationManage = cVSetAuthorizationManage;
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
//		PageList pl = new PageList();
	
		String mode = request.getParameter("mode") == null ? "" : request.getParameter("mode");
		String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids");
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		String sort = request.getParameter("sort") == null ? "t.name" : request.getParameter("sort");
		String zhtype = request.getParameter("zhtype") == null ? "0" : request.getParameter("zhtype");
		String extType = request.getParameter("extType") == null ? "0" : request.getParameter("extType");
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);//项目ID
		
		//添加或修改人物画像页面传的值
		String flag = request.getParameter("flag") == null ? "0" : request.getParameter("flag");
		//修改学员页面传的值
        String idd = request.getParameter("idd") == null ? "" : request.getParameter("idd");
		ExamProp prop = new ExamProp();
		
		if(extType.equals("1"))
			prop.setExt_type(1);
		
	if (mode.equals("getICD")){
			List<String> reslist = new ArrayList<String>();
			List<ExamICD> list = localExamPropValFacade.getICDListByPropIds(ids);
			if (list!=null)
			for(int i = 0; i<list.size(); i++){
				if(list.get(i).getType() == 10) // ICD10
				reslist.add(list.get(i).getName());
			}

			String resString = reslist.toString();
			resString = resString.substring(1, resString.length()-1);
			StrutsUtil.renderText(response, resString);
			return null;
		}
		
		if (mode.equals("getFullNames")){
			String idStrArray[] = ids.split(",");
			List<String> reslist = new ArrayList<String>();
			for (String idStr: idStrArray){
				try{
				Long prop_id = Long.valueOf(idStr.trim());
				List<ExamProp> parentList = localExamPropValFacade.getParentListByPropId(prop_id);
				String fullname = "";
				if(parentList.get(0).getProp_val_id() != null) fullname+= parentList.get(0).getName();
				if(parentList.get(1).getProp_val_id() != null) fullname+= ">"+parentList.get(1).getName();
				if(parentList.get(2).getProp_val_id() != null) fullname+= ">"+parentList.get(2).getName();
				if(parentList.get(3).getProp_val_id() != null) fullname+= ">"+parentList.get(3).getName();
				if(parentList.get(4).getProp_val_id() != null) fullname+= ">"+parentList.get(4).getName();
				
/*				if(!fullname.equals(""))fullname+=">";
				ExamProp curObj = localExamPropValFacade.getExamPropValByPropId(prop_id);//getSysPropVal(prop_id);
				fullname+=curObj.getName();
*/				reslist.add(fullname);
				}catch(Exception e){;}
			}
			String resString = reslist.toString();
			resString = resString.substring(1, resString.length()-1);
			StrutsUtil.renderText(response, resString);
			return null;
		}
		
		//地域
		if (mode.equals("region1list")){
			String idStrArray[] = ids.split(",");
			List<String> reslist = new ArrayList<String>();
			for (String idStr: idStrArray){
				try{
				Long prop_id = Long.valueOf(idStr.trim());
				List<ExamProp> parentList = localExamPropValFacade.getRegionListByPropId(prop_id);
				String fullname = "";
				if(parentList.get(0).getProp_val_id() != null) fullname+= parentList.get(0).getName();
				if(parentList.get(1).getProp_val_id() != null) fullname+= ">"+parentList.get(1).getName();
				if(parentList.get(2).getProp_val_id() != null) fullname+= ">"+parentList.get(2).getName();
				if(parentList.get(3).getProp_val_id() != null) fullname+= ">"+parentList.get(3).getName();
				if(parentList.get(4).getProp_val_id() != null) fullname+= ">"+parentList.get(4).getName();
				
/*				if(!fullname.equals(""))fullname+=">";
				ExamProp curObj = localExamPropValFacade.getExamPropValByPropId(prop_id);//getSysPropVal(prop_id);
				fullname+=curObj.getName();
*/				reslist.add(fullname);
				}catch(Exception e){;}
			}
			String resString = reslist.toString();
			resString = resString.substring(1, resString.length()-1);
			StrutsUtil.renderText(response, resString);
			return null;
		}
		
		List<ExamProp> list;
		if(id.equals("0")){
			prop.setType(Integer.valueOf(type));
			
			prop.setSort(sort);
			//修改学员页面传的值
			if (idd!=null && !idd.equals("")) {
				prop.setImg_type(Integer.valueOf(idd));
			}
			//职称
			if(Integer.valueOf(type) == 9){
				prop.setExt_type(Integer.valueOf(zhtype));
			}
			//地域
			else if(Integer.valueOf(type) == 20){
				//取得省列表
				prop.setType(Integer.valueOf(20));
			}
			if(flag.equals("1")){
				list = localExamPropValFacade.getPropListUserImage(prop,flag);
			}else{
				//添加授权区域筛选
				if(cvSetId > 0){
					CVSetAuthorCheck check = cVSetAuthorizationManage.getAuthorBeforeCheck(cvSetId);
					if(check.getNumericConstants() == 0){
						list = null;
					}else{
						list = check.getExamPropList();
					}
				}else{
					list = localExamPropValFacade.getPropListByType(prop);
				}
			}
//			List<ExamPropType> tlist = localExamPropValFacade.getExamPropTypeList();
			
//			request.setAttribute("ctype", tlist);
		}else{
			
			ExamPropQuery query  = new ExamPropQuery();
			
			if(extType.equals("1"))
				query.setType(1);

			
/*			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			
			query.setPageNo(currentPage);
			query.setPageSize(pageSize);
*/			
			query.setSort(sort);
			query.setSysPropId(Long.valueOf(id));
			
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			list = rprop.getReturnList();
//			List<ExamPropType> tlist = localExamPropValFacade.getExamPropTypeList();
/*			pl.setList(rprop.getReturnList());
			pl.setObjectsPerPage(pageSize);
			pl.setPageNumber(currentPage);
			pl.setFullListSize(rprop.getTotal_count());
*///			request.setAttribute("ctype", tlist);
		}
		JSONObject result = new JSONObject();
		
		result.put("list", list);
		StrutsUtil.renderText(response, result.toString());
	//	request.setAttribute("type", type);
	//	request.setAttribute("prop_val1", id);
//		request.setAttribute("propList", list);


		return null;
	}

}
