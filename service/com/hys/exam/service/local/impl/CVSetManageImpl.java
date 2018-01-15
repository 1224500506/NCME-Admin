package com.hys.exam.service.local.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import com.es.ESConfig;
import com.es.ESUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.local.CVSetManageDAO;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.CvsetQualityHistory;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.Qualify;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CVSetManageImpl extends BaseMangerImpl implements CVSetManage {
	private CVSetManageDAO localCVSetManageDAO;	
	private ExpertManageDAO localExpertManageDAO;
	
	/**
	 * @author   杨红强
	 * @time     2017-06-06	 
	 * @return   
	 * 方法说明： 保存项目中的班次安排
	 */
	public void saveCVSetScheduleFaceTeach(List<CVSetScheduleFaceTeach> cvSetScheduleFaceTeachList, Long cvSetId){
		localCVSetManageDAO.saveCVSetScheduleFaceTeach(cvSetScheduleFaceTeachList,cvSetId);
	}
	/**
	 * @author   杨红强
	 * @time     2017-06-06	 
	 * @return   
	 * 方法说明： 获取项目中的班次安排
	 */
	public List<CVSetScheduleFaceTeach> queryCVSetScheduleFaceTeachByCVsetId(Long cvSetId) {
		return localCVSetManageDAO.queryCVSetScheduleFaceTeachByCVsetId(cvSetId) ;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-05-30
	 * @param    cvId
	 * @return   
	 * 方法说明： 保存项目中课程顺序
	 */
	public void saveScheduleSequence(Long cvSetId, String scheduleIds) {
		localCVSetManageDAO.saveScheduleSequence(cvSetId, scheduleIds);
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-05-26
	 * @param    cvId
	 * @return   
	 * 方法说明： 从项目中删除课程
	 */
	public void deleteCVfromCvsetByCvId(Long cvId) {
		localCVSetManageDAO.deleteCVfromCvsetByCvId(cvId);
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-29
	 * @param    CVSet
	 * @return   List<CVSet>
	 * 方法说明： //YHQ，查询绑定学习卡类型的项目，2017-03-29
	 */
	@Override
	public List<CVSet> cvSetListByBindCardType(CVSet queryCVSet) {
		return localCVSetManageDAO.cvSetListByBindCardType(queryCVSet) ;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-29
	 * @param    CVSet
	 * @return   List<CVSet>
	 * 方法说明： YHQ，查询未绑定学习卡类型的项目，2017-03-29
	 */
	@Override
	public List<CVSet> cvSetListByUnBindCardType(CVSet queryCVSet) {
		return localCVSetManageDAO.cvSetListByUnBindCardType(queryCVSet) ;
	}
	
	/**
	 * @author   杨红强
	 * @time     2017-03-18
	 * @param    CVSet
	 * @return   boolean
	 * 方法说明： YHQ，查询项目是否绑定学习卡类型，2017-03-18
	 */
	@Override
	public boolean bindCardTypeByCVSet(CVSet cvSet) {
		return localCVSetManageDAO.bindCardTypeByCVSet(cvSet) ;
	}
	
	@Override
	public List<CVSet> getCVSetList(CVSet queryCVSet) {
		return localCVSetManageDAO.getCVSetList(queryCVSet);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @param    CVSet
	 * @return   List
	 * 方法说明： 根据用户id查询所属专委会对应的学科信息
	 */
	public List<PropUnit> getPropByUserId(Long userId){
		return localCVSetManageDAO.getPropByUserId(userId);
	}
	
	//可以审核的项目列表  chenlb add
	@Override
	public List<CVSet> getCVSetListForQualify(CVSet queryCVSet, SystemUser currentUser){
		
		List<CVSet> list = new ArrayList<CVSet>();
		List<CVSet> allList = this.localCVSetManageDAO.getCVSetListForQualify(queryCVSet);		//本人创建的规避
		
		//在这里规避其他规则
		if(!Utils.isListEmpty(allList)){
			if(null != currentUser.getAccountName()){
				//得到当前登录的专家的对象
				String currentExpertWorkUnit = "";
				ExpertInfo currentExpertInfo = this.localExpertManageDAO.getExpertInfoByUsername(currentUser.getAccountName());
				if(null != currentExpertInfo){
					currentExpertWorkUnit = currentExpertInfo.getWorkUnit();
					
					for(CVSet cvset : allList){
						
						//所有相关的 专家
						//必须在项目的专家列表里面才可以审核
						boolean ifInCvsetExpertList = false;
						List<ExpertInfo> allExpertInfoList = this.localCVSetManageDAO.getExpertInfoListByCVSetId(cvset.getId());
						if(!Utils.isListEmpty(allExpertInfoList)){
							for(ExpertInfo info : allExpertInfoList){
								if(info.getId().equals(currentExpertInfo.getId())){
									ifInCvsetExpertList = true;
								}
							}
						}
						
						
						//规避：项目负责人不可以审核
						List<ExpertInfo> managerList = cvset.getManagerList();
						boolean ifManager = false;
						if(!Utils.isListEmpty(managerList)){
							for(ExpertInfo info : managerList){
								if(!info.getId().equals(currentUser.getUserId())){
									ifManager = true;
								}
							}
						}
						
						
						//规避：项目创建人的单位
						boolean ifmakerUnit = false;
						ExpertInfo makerExpertInfo = this.localExpertManageDAO.getExpertInfoByUsername(cvset.getMaker());
						if(null == makerExpertInfo) {
							ifmakerUnit = true;
						}
						else if(!currentExpertWorkUnit.equals(makerExpertInfo.getWorkUnit())){
							ifmakerUnit = true;
						}
						
						//项目负责人单位
						List<ExpertInfo> managerList2 = cvset.getManagerList();
						boolean ifManagerUnit = false;
						if(!Utils.isListEmpty(managerList2)){
							for(ExpertInfo info2 : managerList2){
								if(!info2.getWorkUnit().equals(currentExpertWorkUnit)){
									ifManagerUnit = true;
								}
							}
						}
						
						//规避：项目归属单位
						boolean ifOrgan = false;
						List<PeixunOrg> organizationList = cvset.getOrganizationList();
						
						if(!Utils.isListEmpty(organizationList)){
							for(PeixunOrg org : organizationList){
								if(!org.getName().equals(currentExpertWorkUnit)){
									ifOrgan = true;
								}
							}
						}else {
							ifOrgan = true;
						}
						
						//条件都满足,加入列表中
						if(ifInCvsetExpertList && ifManager && ifManagerUnit && ifmakerUnit && ifOrgan){
							list.add(cvset);
						}
					}
					
				}
				
			}
		}
		return list;
	}
	
	//添加审核记录	chenlb add
	//如果有2次相同的审核结果,则同时审核项目
	@Override
	public int saveCvsetQualityHistory(CvsetQualityHistory history){
		int row = this.localCVSetManageDAO.saveCvsetQualityHistory(history);
		
		//查询是否存在已经有跟当前审核状态一致的，如果有，则更新项目状态
		int qualifyStatusNum = 2;
		Integer qualifyStatus = history.getQualifyStatus();
		List<CvsetQualityHistory> list = localCVSetManageDAO.getCvsetQualityHistoryListByCvsetId(history.getCvSetId(), qualifyStatus);
		if(!Utils.isListEmpty(list) && list.size() == qualifyStatusNum){
			CVSet cvSet = new CVSet();
			cvSet.setId(history.getCvSetId());
			cvSet.setStatus(new Long(qualifyStatus));
			this.localCVSetManageDAO.updateCVSet(cvSet);
		}else{
			CVSet cvSet = new CVSet();
			cvSet.setId(history.getCvSetId());
			cvSet.setStatus(new Long(2));
			this.localCVSetManageDAO.updateCVSet(cvSet);
		}
		
		return row;
	}
	
	//根据项目和专家得到其审核历史记录 chenlb add 
	@Override
	public CvsetQualityHistory getCvsetQualityHistoryByCvsetAndExpert(Long cvsetId, Long expertId){
		return this.localCVSetManageDAO.getCvsetQualityHistoryByCvsetAndExpert(cvsetId, expertId);
	}
	
	//根据项目和专家得到其审核历史记录 chenlb add 
	@Override
	public List<CvsetQualityHistory> getCvsetQualityHistoryByCvsetAndExpertInfo(Long cvsetId){
		return localCVSetManageDAO.getCvsetQualityHistoryByCvsetAndExpertInfo(cvsetId);
	}
	

	@Override
	public Long addCVSet(CVSet cvSet) {
		return localCVSetManageDAO.addCVSet(cvSet);
	}

	@Override
	public boolean updateCVSet(CVSet cvSet) {
		return localCVSetManageDAO.updateCVSet(cvSet);
	}

	@Override
	public boolean deleteCVSet(CVSet cvSet) {
		return localCVSetManageDAO.deleteCVSet(cvSet);
	}
	@Override
	public CVSet getCVSetById(Long id)
	{
		return this.localCVSetManageDAO.getCVSetById(id);
	}
	@Override
	public int updateDistribute(List<Object> params)
	{
		return this.localCVSetManageDAO.updateDistribute(params);
	}
	
	@Override
	public boolean updateUnit_ref_Quality(long id, List<QualityModel> qm_list) {
		return localCVSetManageDAO.updateUnit_ref_Quality(id, qm_list);
	}
	@Override
	public boolean editCVS(CVSet cvSet) {
		return this.localCVSetManageDAO.editCVS(cvSet);
		
	}
	
	//上传至对象存储
	@Override
	public String saveObject(FormFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//向对象存储服务器上传资源
		File newFile = new File(request.getSession().getServletContext().getRealPath("/") + "/upload/" + file.getFileName());
	    if (!newFile.exists()){
	      newFile.createNewFile();
	    }
	    byte[] b = new byte[2248000];
	    int readBytes = 0;
	    InputStream is = file.getInputStream();
	    while (readBytes < 2248000) {
	      int read = is.read(b, readBytes, 2248000 - readBytes);
	      if (read == -1) {
	        break;
	      }
	      readBytes += read;
	    }
	    FileOutputStream fos = new FileOutputStream(newFile);
	    fos.write(b, 0, readBytes);
	    fos.flush();
	    fos.close();
	    is.close();
	    String fileName = newFile.getName();
	    String result = null;
	    fileName = fileName.substring(0, fileName.indexOf("."));
	    result = ESUtil.updateFile(newFile, ESConfig.DEFAULT_FOLDER, ESConfig.DEFAULT_BUCKET);
	    //删除文件
	    newFile.delete();
		return result;
	}	

	@Override
	public List<Qualify> getQualifyList(Qualify qualify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getCVSetPageList(PageList pl, CVSet queryCVSet) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public CVSet getCVSetByCode(String code)
	{
		return this.localCVSetManageDAO.getCVSetByCode(code);
	}
	
	public void setLocalExpertManageDAO(ExpertManageDAO localExpertManageDAO) {
		this.localExpertManageDAO = localExpertManageDAO;
	}
	public ExpertManageDAO getLocalExpertManageDAO() {
		return localExpertManageDAO;
	}
	public void setLocalCVSetManageDAO(CVSetManageDAO localCVSetManageDAO) {
		this.localCVSetManageDAO = localCVSetManageDAO;
	}
	public CVSetManageDAO getLocalCVSetManageDAO() {
		return localCVSetManageDAO;
	}
	@Override
	public int getFaceteachCount(Long cvsetId) {
		return 0;
	}
	@Override
	public void getCVSetListByPage(PageList pl, CVSet queryCVSet) {
		localCVSetManageDAO.getCVSetListByPage(pl,queryCVSet);		
	}
	
	/**
	 * 项目授权中显示的项目列表学科是重复的，但是dao层被另一个地方引用着，所以再复制一份，修改
	 * @param queryCVSet
	 * @return
	 */
	@Override
	public List<CVSet> getCVSetListDuplicateRemove(CVSet queryCVSet) {
		return localCVSetManageDAO.getCVSetListDuplicateRemove(queryCVSet);
	}
	@Override
	public CVSet queryCVSetListByCvId(Long cvId) {
		return localCVSetManageDAO.queryCVSetListByCvId(cvId);
	}
	@Override
	public List<PropUnit> getAreaByCode(String propIds){
		return localCVSetManageDAO.getAreaByCode(propIds);
	}
	@Override
	public List<PropUnit> getAreaForAuthor(String propIds) {
		return localCVSetManageDAO.getAreaForAuthor(propIds);
	}
}
