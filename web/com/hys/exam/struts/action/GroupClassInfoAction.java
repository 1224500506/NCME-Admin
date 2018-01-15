package com.hys.exam.struts.action;

import com.hys.auth.util.ParamUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.CVT;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.QualityModel;
import com.hys.exam.service.local.GroupClassInfoManage;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.util.JsonUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：组课功能
 * 
 * 作者：ZJG 2016-12-19
 * 
 * 描述：
 * 
 * 说明:
 */
public class GroupClassInfoAction extends BaseAction {
	private QualityModelManage qualityModelManage ;
	
	public GroupClassInfoManage groupClassInfoManage;

	public GroupClassInfoManage getGroupClassInfoManage() {
		return groupClassInfoManage;
	}
	public void setGroupClassInfoManage(GroupClassInfoManage groupClassInfoManage) {
		this.groupClassInfoManage = groupClassInfoManage;
	}
			
	public QualityModelManage getQualityModelManage() {
		return qualityModelManage;
	}
	public void setQualityModelManage(QualityModelManage qualityModelManage) {
		this.qualityModelManage = qualityModelManage;
	}
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String type = request.getParameter("type");
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		String content = request.getParameter("content");
		String ids = request.getParameter("ids");
		String pIds = request.getParameter("pIds");
		String pIdName = request.getParameter("pIdName");
		String reviewId = request.getParameter("reviewId");
		String templateType = request.getParameter("templateType");
		String compIndex = request.getParameter("compIndex");
		if(type.equals("queryGroup")){
			//根据课程id查询课程信息
			return queryGroup(mapping, classId, request, response);
		}else if(type.equals("save")){
			//新增信息
			return save(mapping, classId, className, content, pIds,pIdName,templateType,compIndex,request, response);
		}else if(type.equals("getAJAX")){
			//获取课程数据
			return getAJAX(mapping,classId,request, response);
		}else if(type.equals("cz")){
			//传值
			return chuanzhi(mapping, ids,request, response);
		}else if(type.equals("list")){
			//查询列表
			return list(mapping, form,request, response);
		}else if(type.equals("review")){
			//课程详情
			return review(mapping, reviewId,request, response);
		}else if(type.equals("unionEdit2")){
			//保存选中的单元任务点
			return unionEdit2(mapping, form,request, response);
		}else if(type.equals("saveUnitQuota")){
			//YHQ 2017-03-04，更新课程单元的评分
			return saveUnitQuota(mapping, form,request, response);
		}else if(type.equals("unionEditView")){
			//保存选中的单元任务点
			return unionEditView(mapping, form,request, response);
		}else if(type.equals("yhqRepairOldDate")){//YHQ,2017-07-29,修复group_class_info表中media_type、media_id，手动执行不被其他页面调用
			return yhqRepairOldDate(mapping, form,request, response);
		}else {
			return null;
		}
	}
	
	//YHQ,2017-07-29,修复group_class_info表中media_type、media_id，手动执行不被其他页面调用
	protected String yhqRepairOldDate(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		String allData = request.getParameter("allData");
		boolean allDataFlag = true ;//只修复media_type、media_id为空的
		if (allData != null && allData.equals("1")) { //全表更新
			allDataFlag = false ;
		} 
		Long updateNum = 0L ;
		try {
			List<GroupClassInfo> gciList = groupClassInfoManage.queryList() ;
			for (GroupClassInfo gciObj : gciList) {
				if (allDataFlag) {
					if (gciObj.getMediaType() == null) {
						groupClassInfoManage.updateGroupClassInfo(gciObj) ;
						updateNum++ ;
					}
				} else {
					groupClassInfoManage.updateGroupClassInfo(gciObj) ;
					updateNum++ ;
				}				
			}
			map.put("state", "success") ;
			map.put("updateNum", updateNum) ;
		} catch (Exception e) {			
			e.printStackTrace();
			map.put("state", "error") ;
			map.put("errorInfo", e.toString()) ;
		}
		
		String retStr = JsonUtil.map2json(map) ;
		StrutsUtil.renderText(response, retStr);
		return null;
	}
	
	//查询
	protected String queryGroup(ActionMapping mapping, String classId,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		List<GroupClassInfo> group = new ArrayList<GroupClassInfo>();
		//判断课程id是否存在
		if(classId!=null && !"".equals(classId)){
			group = groupClassInfoManage.queryGroupClassContent(Integer.parseInt(classId));
			if(group!=null && group.size()>0){
				map.put("message", "success");
				map.put("content", group.get(0).getClassContent());
			}else{
				map.put("content", "");
				map.put("message", "noData");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	//查询
	protected String save(ActionMapping mapping, String classId,String className,String content,String pIds,String pIdName,String templateType,String compIndex,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		List<GroupClassInfo> group = new ArrayList<GroupClassInfo>();
		GroupClassInfo g = new GroupClassInfo();
		//判断课程id是否存在
		if(classId!=null && !"".equals(classId)){
			group = groupClassInfoManage.queryGroupClassContent(Integer.parseInt(classId));
			if(group!=null && group.size()>0){
				//执行更新操作
				g = group.get(0);
				g.setClassContent(content);
				g.setTemplateType(templateType);
				if(compIndex!=null && !"".equals(compIndex)){
					g.setCompIndex(Integer.parseInt(compIndex));
				}else{
					g.setCompIndex(0);
				}
				groupClassInfoManage.updateGroupClassInfo(g);
				
				//YHQ 2017-02-25 增加能力模型
				QualityModel qualityModel = new QualityModel() ;
				qualityModel.setId(Long.parseLong(classId));
				qualityModel.setName(content);
				qualityModelManage.updateCvUnitRefQualityByGroupClass(qualityModel) ;
			}else{
				//执行新增操作
				g.setClassId(Integer.parseInt(classId));
				g.setClassName(className);
				g.setTemplateType(templateType);
				if(pIds!=null && !"".equals(pIds)){
					g.setClassParentId(Integer.parseInt(pIds));
				}
				if(pIdName!=null && !"".equals(pIdName)){
					g.setClassParentName(pIdName);
				}
				g.setClassContent(content);
				if(compIndex!=null && !"".equals(compIndex)){
					g.setCompIndex(Integer.parseInt(compIndex));
				}else{
					g.setCompIndex(0);
				}
				groupClassInfoManage.addGroupClassInfo(g);
				
				//YHQ 2017-02-24 增加能力模型
				QualityModel qualityModel = new QualityModel() ;
				qualityModel.setId(Long.parseLong(classId));
				qualityModelManage.insertCvUnitRefQualityByGroupClass(qualityModel) ;
			}
			map.put("message", "success");
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	//传值
	protected String chuanzhi(ActionMapping mapping,String ids,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if(ids!=null && !"".equals(ids)){
			HttpSession session = request.getSession();
			session.setAttribute("ids", ids);
		}
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	//获取课程列表
	protected String getAJAX(ActionMapping mapping,String classId,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<CVT> cvt = new ArrayList<CVT>();
		String ids[] = null;
		if(classId!=null && !"".equals(classId)){
			if(classId.indexOf(",")>0){
				ids = classId.split(",");
				for(int i=0;i<ids.length;i++){
					List<CVT> list = groupClassInfoManage.queryCVTList(Integer.parseInt(ids[i]));
					cvt.addAll(list);
				}
			}else{
				cvt = groupClassInfoManage.queryCVTList(Integer.parseInt(classId));
			}
			if(cvt!=null && cvt.size()>0){
				map.put("message", "success");
				map.put("tree", cvt);
			}else{
				map.put("message", "noData");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	//获取查询列表
	protected String list(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
        //获取前台参数
		//执行查询
		List<GroupClassInfo> list = groupClassInfoManage.queryList();
		request.setAttribute("list", list);
		return "list";
	}
	
	//课程详情
	protected String review(ActionMapping mapping, String reviewId,HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<GroupClassInfo> list = new ArrayList<GroupClassInfo>();
		//根据单元id查询单元课程内容
		if(reviewId!=null && !"".equals(reviewId)){
			list = groupClassInfoManage.queryGroupClassContent(Integer.parseInt(reviewId));
			if(list!=null && list.size()>0){
				request.setAttribute("content", list.get(0).getClassContent());
			}else{
				request.setAttribute("content", "");
			}
		}else{
			request.setAttribute("content", "");
		}
		if(list.get(0).getTemplateType()!=null && list.get(0).getTemplateType().equals("video")){
			return "reviewVideo";
		}else{
			return "review";
		}
	}
	
	//跳转到组课页面
	protected String unionEdit2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String ids = request.getParameter("ids");
		String chekPointIds = request.getParameter("chekPointIds");
		String type = request.getParameter("type");
		String pageType = request.getParameter("pageType");
		request.setAttribute("ids", ids);
		if(chekPointIds.equals("''")){
			request.setAttribute("chekPointIds", "Null");
		}else{
			request.setAttribute("chekPointIds", chekPointIds);
		}
		request.setAttribute("type", type);
		request.setAttribute("pageType", pageType);
		return "unionEdit2";
	}
        
        //YHQ 2017-03-04，更新课程单元的评分
	protected String saveUnitQuota(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {		
		Long unitId =ParamUtils.getLongParameter(request, "unitId", 0L);
                Double quotaVal = ParamUtils.getDoubleParameter(request, "quotaVal", 0) ;                
                String message = "" ;
                Map<String,Object> map = new HashMap<String,Object>();
                if (unitId > 0L && quotaVal > 0) {
                    boolean updateFlag = groupClassInfoManage.updateUnitQuota(unitId, quotaVal) ;
                    if (updateFlag) {
                        message = "success" ;
                    }
                }                                
                map.put("message", message);
                StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;		
	}
	
	protected String unionEditView(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String ids = request.getParameter("ids");
		String chekPointIds = request.getParameter("chekPointIds");
		String type = request.getParameter("type");
		String pageType = request.getParameter("pageType");
		request.setAttribute("ids", ids);
		if(chekPointIds.equals("''")){
			request.setAttribute("chekPointIds", "Null");
		}else{
			request.setAttribute("chekPointIds", chekPointIds);
		}
		request.setAttribute("type", type);
		request.setAttribute("pageType", pageType);
		return "unionEditView";
	}
	
}
