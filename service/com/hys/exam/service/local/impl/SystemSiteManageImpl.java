package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemSiteDAO;
import com.hys.exam.dao.remote.IcmeOrgDAO;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.IcmeOrg;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemSiteCourseTypeExam;
import com.hys.exam.model.system.SystemAdminOrganVO;
import com.hys.exam.model.system.SystemSiteVO;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 manageImpl
 * 
 * 说明:
 */
public class SystemSiteManageImpl extends BaseMangerImpl implements SystemSiteManage {

	private SystemSiteDAO systemSiteDAO ;
	
	private IcmeOrgDAO icmeOrgDAO;

	public void setSystemSiteDAO(SystemSiteDAO systemSiteDAO) {
		this.systemSiteDAO = systemSiteDAO;
	}
	
	public void setIcmeOrgDAO(IcmeOrgDAO icmeOrgDAO) {
		this.icmeOrgDAO = icmeOrgDAO;
	}
	
	@Override //取得所有站点信息
	public List<SystemSite> getListByItem(SystemSite item){
		return systemSiteDAO.getListByItem(item) ;
	}
	
	public void querySystemSiteList(Page<SystemSite> page,
			SystemSite item) {
		systemSiteDAO.querySystemSiteList(page, item);
	}
	
	
	public int update(SystemSite item) {
		return systemSiteDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return systemSiteDAO.deleteList(ids,idColName);
	}

	
	public SystemSite getItemById(Long id) {
		return systemSiteDAO.getItemById(id);
	}

	public Integer save(SystemSite item) {
		return systemSiteDAO.save(item);
		//TrainContentSite t = new TrainContentSite();
		//t.setApplicationId(item.getId().toString());
		//return systemSiteDAO.addTrainContentSite(t);
	}

	@Override
	public int delete(long id, String idColName) {
		return systemSiteDAO.delete(id,idColName);
	}

	@Override
	public List<ExamExamination> getExamExaminationList(Long siteId,
			Long courseTypeId) {
		return systemSiteDAO.getExamExaminationList(siteId, courseTypeId);
	}

	@Override
	public void addSystemSiteCourseTypeExamList(
			List<SystemSiteCourseTypeExam> list) {
		if (!list.isEmpty()) {
			systemSiteDAO.deleteSystemSiteCourseTypeExam(list.get(0)
					.getSiteId(), list.get(0).getCourseTypeId());

			for (int i = 0; i < list.size(); ++i) {
				systemSiteDAO.addSystemSiteCourseTypeExam(list.get(i));
			}

		}
	}
	
	@Override
	public List<SystemSiteVO> getSystemSiteList(){
		return this.systemSiteDAO.getSystemSiteList();
	}
	
	//得到绑定/未绑定站点
	@Override
	public List<SystemSiteVO> getSystemSiteListByTypeId(Long typeId, boolean flag){
		return this.systemSiteDAO.getSystemSiteListByTypeId(typeId, flag);
	}
	
	@Override
	public List<SystemAdminOrganVO> getSystemAdminOrganList(Long siteId){
		return this.systemSiteDAO.getSystemAdminOrganList(siteId);
	}
	
	//得到已绑定/未绑定的地区
	@Override
	public List<SystemAdminOrganVO> getSystemAdminOrganList(Long siteId, boolean flag){
		return this.systemSiteDAO.getSystemAdminOrganList(siteId, flag);
	}
	
	@Override
	public int saveSystemCardTypeSite(Long typeId, Long siteId){
		return this.systemSiteDAO.saveSystemCardTypeSite(typeId, siteId);
	}
	
	@Override
	public int deleteSystemCardTypeSite(Long typeId, Long siteId){
		return this.systemSiteDAO.deleteSystemCardTypeSite(typeId, siteId);
	}
	
	@Override
	public int saveSystemSiteOrgan(Long siteId, Long organId){
		return this.systemSiteDAO.saveSystemSiteOrgan(siteId, organId);
	}
	
	@Override
	public int deleteSystemSiteOrgan(Long siteId, Long organId){
		return this.systemSiteDAO.deleteSystemSiteOrgan(siteId, organId);
	}
	
	//得到学习卡已绑定的地区
	@Override
	public List<SystemAdminOrganVO> getSystemAdminOrganListByCardType(Long typeId, boolean flag){
		return this.systemSiteDAO.getSystemAdminOrganListByCardType(typeId, flag);
	}
	
	@Override
	public int savePaycardOrgan(Long typeId, Long organId, Integer orgType){
		return this.systemSiteDAO.savePaycardOrgan(typeId, organId, orgType);
	}
	public int deletePaycardOrgan(Long typeId, Long organId){
		return this.systemSiteDAO.deletePaycardOrgan(typeId, organId);
	}
	
	@Override
	public List<IcmeOrg> getIcmeGovOrgList(){
		return this.icmeOrgDAO.getIcmeGovOrgList();
	}

	/**
	 * 新增站点的时候，查询改站点是否存在
	 * @param site
	 * @return
	 */
	@Override
	public List<SystemSite> getListByItem(String site, Long id) {
		return systemSiteDAO.getListByItem(site, id) ;
	}

}