package com.hys.exam.struts.action.liveservice;

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
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.LogStudyCVUnitVideo;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.struts.action.liveservice.comm.CommDoPost;
import com.hys.exam.struts.action.liveservice.comm.CvLiveConstant;
import com.hys.exam.struts.action.liveservice.comm.JsonToMap;
import com.hys.framework.web.action.BaseAction;
/**
 * 
 * @author taoliang
 * @desc 获取直播课件接口
 */
public class GetCoursewareForCvLive extends BaseAction {
	
	private CvLiveManage localCvLiveManage;
	private CVManage localCVManage;
	private CVSetManage localCVSetManage;
	private LogStudyCVUnitManage localLogStudyCVUnitManage;
	private SystemUserManage localSystemUserManage;
    @Override
    protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String mode = ParamUtils.getParameter(request, "mode", "");
		if (mode.equals("getCourseware")) {
			return getCourseware(mapping,form,request,response);
		}
    	return null;
    }
    
    private String getCourseware(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    	
    	SystemUser currentUser = localSystemUserManage.getItemByAccountName(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString(), null);
    	Map<String,String> map = new HashMap<String,String>();
    	JSONObject data = new JSONObject();
    	
    	Long Id = ParamUtils.getLongParameter(request, "id", 0L);
    	String url = CvLiveConstant.TRAINING_COURSEWARE_LIST;
    	String roomId = request.getParameter("roomId");
    	if(roomId == null && "".equals(roomId)){
    		data.put("msg", "直播SDK ID获取异常！");
    		StrutsUtil.renderText(response, data.toString());
    		return null;
    	}
		map.put("loginName", CvLiveConstant.LOGIN_NAME);
		map.put("password", CvLiveConstant.PASSWORD);
		map.put("roomId", roomId);
		String result;
		try {
			result = CommDoPost.sendRequestByPost(url, map);
			System.out.println(result);
			Map resultMap = JsonToMap.parseJSON2Map(result);
			String code = resultMap.get("code").toString();
			List list = (List) resultMap.get("coursewares");
			if(list != null && list.size() > 0){
				for(int i=0;i<list.size();i++){
					CvLiveCourseware ware = new CvLiveCourseware();
					Map<String, Object> wareMap = (Map<String, Object>) list.get(i);
					String courseware_no = wareMap.get("id")==null?"":wareMap.get("id").toString();
					String subject = wareMap.get("subject")==null?"":wareMap.get("subject").toString();
					String courseware_url = wareMap.get("url")==null?"":wareMap.get("url").toString();
					String courseware_token = wareMap.get("token")==null?"":wareMap.get("token").toString();
					Long createdTime = wareMap.get("createdTime")==null?0L:Long.valueOf(wareMap.get("createdTime").toString());
					String courseware_num = wareMap.get("number")==null?"":wareMap.get("number").toString();
					String class_no = wareMap.get("roomId")==null?"":wareMap.get("roomId").toString();
					ware.setSubject(subject);
					ware.setCourseware_no(courseware_no);
					ware.setCourseware_url(courseware_url);
					ware.setCourseware_token(courseware_token);
					ware.setCourseware_create_time(createdTime);
					ware.setCourseware_num(courseware_num);
					ware.setClass_no(class_no);
					ware.setCv_id(Id);
					Long wareId = localCvLiveManage.addCvLiveCourseware(ware);
					if(wareId > 0){
						this.getCoursewareSyn(wareId, createdTime, courseware_no, response);
						//初始化回放单元
						{
							CVUnit cvliveCvUnit = new CVUnit();
							cvliveCvUnit.setName(subject);
							cvliveCvUnit.setPoint(1);
							cvliveCvUnit.setIsBound(0);
							cvliveCvUnit.setType(7);//设置单元的教学类型为7标识它为直播课程单元
							cvliveCvUnit.setQuota(80d);
							cvliveCvUnit.setUnitMakeType(1);//标识该单元为直播回放所生成的单元
							//保存单元信息
							Long newUnitId = localCVManage.addCVUnit(cvliveCvUnit);
							if(newUnitId > 0){
								localCVManage.addCvRefUnit(Integer.parseInt(Id.toString()), 
									Integer.valueOf(newUnitId.toString()));
								
								//初始化课件和单元中间表
								CvLiveRefUnit liveRef = new CvLiveRefUnit();
								liveRef.setCv_id(Id);
								liveRef.setUnit_id(newUnitId);
								liveRef.setCourseware_no(courseware_no);
								liveRef.setClass_no(class_no);
								Long refId = localCvLiveManage.addCvLiveRefUnit(liveRef);
								/*if(refId > 0){
									//批量初始化看过直播的用户单元学习记录
							    	CVSet cvSet = localCVSetManage.queryCVSetListByCvId(Id);
							    	List<SystemAccount> userList = localCvLiveManage.getViewLiveUser(class_no);
							    	List<LogStudyCVUnit> lsCVlist = new ArrayList<LogStudyCVUnit>();
							    	if(userList != null && userList.size() > 0){
							    		for(SystemAccount acc : userList){
							    			LogStudyCVUnit newLogStudyUnit = new LogStudyCVUnit();
							    			newLogStudyUnit.setLogStudyCVSetId(cvSet.getId());
							    			newLogStudyUnit.setSystemUserId(acc.getAccountId());
							    			newLogStudyUnit.setCvId(Id);
							    			newLogStudyUnit.setCvUnitId(newUnitId);
							    			lsCVlist.add(newLogStudyUnit);
							    		}
							    		//批量执行
							    		localLogStudyCVUnitManage.addBatchLogStudyCVUnit(lsCVlist);
							    	}
							    	
							    	//批量初始化看过直播的用户单元学习记录情况统计表log_study_cv_unit_video
							    	LogStudyCVUnit record = new LogStudyCVUnit();
							    	record.setLogStudyCVSetId(cvSet.getId());
							    	record.setCvId(Id);
							    	record.setCvUnitId(newUnitId);
							    	List<LogStudyCVUnit> recordList = localLogStudyCVUnitManage.getLogStudyCVUnitRecord(record);
							    	if(recordList.size() > 0){
							    		List<LogStudyCVUnitVideo> videoList = new ArrayList<LogStudyCVUnitVideo>();
							    		Long videoLength = 0L;
							    		CvLiveStudyRef ref = new CvLiveStudyRef();
							    		ref.setClass_no(class_no);
							    		List<CvLiveStudyRef> refList = localCvLiveManage.queryCvLiveStudyRef(ref);
							    		if(refList.size() > 0){
							    			videoLength = Long.valueOf(refList.get(0).getTotal_live_length());
							    		}
							    		for(LogStudyCVUnit video : recordList){
							    			LogStudyCVUnitVideo videoLog = new  LogStudyCVUnitVideo();
							                videoLog.setLogStudyCvUnitId(video.getId());
							                videoLog.setCvId(Id);
							                videoLog.setCvUnitId(newUnitId);
							                videoLog.setVideoLength(videoLength);
							                videoLog.setVideoPlayLength(0L);
							                videoLog.setSystemUserId(video.getSystemUserId());
							                videoList.add(videoLog);
							    		}
							    		//批量执行
							    		localLogStudyCVUnitManage.addBatchLogStudyCVUnitVideo(videoList);
							    	}
								}*/
							}
						}
					}
				}
				//添加回放后，将该课程状态设置为回放
				List<CvLive> liveList = localCvLiveManage.queryCvLiveList(Id);
				if(liveList !=null && liveList.size() > 0){
					CvLive live = liveList.get(0);
					live.setCourse_make_type(1);
					localCVManage.updateLive(live);
				}
			}else{
				data.put("msg", "直播间SDK ID["+roomId+"]--该直播未录制课件！");
				StrutsUtil.renderText(response, data.toString());		
				return null;
			}
		}catch(Exception ex){
			data.put("msg", "回放信息生成失败！");
    		StrutsUtil.renderText(response, data.toString());
    		return null;
		}
		data.put("msg", "回放信息生成成功！");
		StrutsUtil.renderText(response, data.toString());		
		return null;
	}
    
    public String getCoursewareSyn(Long wareId, Long createTime, String courseware_no, HttpServletResponse response){
    	
    	CvLiveCourseware ware = new CvLiveCourseware();
    	ware.setId(wareId);
    	List<CvLiveCourseware> wareList = localCvLiveManage.getCvLiveCoursewareList(ware);
    	if(wareList != null && wareList.size() > 0){
    		ware = wareList.get(0);
    		Map<String,String> map = new HashMap<String,String>();
        	JSONObject data = new JSONObject();
        	String url = CvLiveConstant.TRAINING_COURSEWARE_SYN;
        	String url1 = CvLiveConstant.TRAINING_RECORD_SYN;
        	map.put("loginName", CvLiveConstant.LOGIN_NAME);
    		map.put("password", CvLiveConstant.PASSWORD);
    		map.put("startTime", createTime.toString());
    		String result;
    		try {
    			result = CommDoPost.sendRequestByPost(url, map);
    			Map resultMap = JsonToMap.parseJSON2Map(result);
    			String code = resultMap.get("code").toString();
    			List list = (List) resultMap.get("list");
    			if(list != null && list.size() > 0){
    				for(int i=0;i<list.size();i++){
    					Map<String, Object> wareMap = (Map<String, Object>) list.get(i);
    					String wareNo = wareMap.get("id")==null?"":wareMap.get("id").toString();
    					if(courseware_no.equals(wareNo)){
    						Long duration = 0L;
    						String recordId = wareMap.get("recordId").toString();
    						{//添加拿录制课件数据
    							String recordResult = CommDoPost.sendRequestByPost(url1, map);
    			    			Map recordMap = JsonToMap.parseJSON2Map(recordResult);
    			    			String rCode = recordMap.get("code").toString();
    			    			List recordList = (List) recordMap.get("list");
    			    			if(recordList != null && recordList.size() > 0){
    			    				for(int k=0;k<recordList.size();k++){
    			    					Map<String, Object> recordWareMap = (Map<String, Object>) recordList.get(k);
    			    					String rid = recordWareMap.get("id")==null?"0":recordWareMap.get("id").toString();
    			    					if(recordId.equals(rid)){
    			    						duration =  recordWareMap.get("duration") == null?0L:Long.valueOf(recordWareMap.get("duration").toString());
    			    						break;
    			    					}
    			    				}
    			    			}else{
    			    				duration =  wareMap.get("duration") == null?0L:Long.valueOf(wareMap.get("duration").toString());
    			    			}
    						}
    						ware.setDuration(duration);
    						localCvLiveManage.updateCvLiveCoutsewareInfo(ware);
    					}
    				}
    			}
    			
    		}catch(Exception ex){
    			data.put("msg", "课件SDK ID["+courseware_no+"]--分页同步课件数据接口异常！");
				StrutsUtil.renderText(response, data.toString());		
				return null;
    		}
    	}
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

	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}

	public void setLocalLogStudyCVUnitManage(
			LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}
	
}
