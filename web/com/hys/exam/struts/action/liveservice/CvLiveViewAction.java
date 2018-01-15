package com.hys.exam.struts.action.liveservice;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.framework.web.action.BaseAction;
/**
 * 
 * @author taoliang
 * @desc 直播情况处理
 */
public class CvLiveViewAction extends BaseAction {
	
	private CvLiveManage localCvLiveManage;
	private CVManage localCVManage;
    @Override
    protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String mode = ParamUtils.getParameter(request, "mode", "");
		if (mode.equals("updateCourseware")) {
			return updateCourseware(mapping,form,request,response);
		}
    	return null;
    }
    
    
    private String updateCourseware(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	
    	//String subject =URLDecoder.decode(request.getParameter("subject"),"UTF-8");
    	String subject =request.getParameter("subject");
    	String coursewareNo =request.getParameter("coursewareNo");
    	String coursewareUrl =request.getParameter("coursewareUrl");
    	String coursewareToken =request.getParameter("coursewareToken");
    	JSONObject result = new JSONObject();
    	try{
	    	CvLiveCourseware ware = new CvLiveCourseware();
	    	ware.setCourseware_no(coursewareNo);
	    	List<CvLiveCourseware> wareList = localCvLiveManage.getCvLiveCoursewareList(ware);
	    	if(wareList != null && wareList.size() > 0){
	    		ware = wareList.get(0);
	    		ware.setSubject(subject);
	    		ware.setCourseware_no(coursewareNo);
	    		ware.setCourseware_url(coursewareUrl);
	    		ware.setCourseware_token(coursewareToken);
	    		int count = localCvLiveManage.updateCvLiveCoutsewareInfo(ware);
	    		if(count > 0){
	    			CvLiveRefUnit record = new CvLiveRefUnit();
	    			record.setCourseware_no(coursewareNo);
	    			List<CvLiveRefUnit> refList = localCvLiveManage.getCvLiveRefUnitList(record);
	    			if(refList != null && refList.size() > 0){
	    				Long unitId = refList.get(0).getUnit_id();
	    				CVUnit unit = new CVUnit();
	    				unit.setId(unitId);
	    				unit.setName(subject);
	    				localCVManage.updateCvUnitForLive(unit);
	    			}
	    			
	    		}
	    	}
    	}catch(Exception ex){
    		result.put("msg", "保存失败！");
    		StrutsUtil.renderText(response, result.toString());		
    		return null;
    	}
		
		result.put("msg", "保存成功！");
		StrutsUtil.renderText(response, result.toString());		
		return null;
	}
    
    public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}

	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}

	public CVManage getLocalCVManage() {
		return localCVManage;
	}


	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}
}
