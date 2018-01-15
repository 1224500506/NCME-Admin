package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemSiteCourseTypeDAO;
import com.hys.exam.model.SystemSiteCourseType;
import com.hys.exam.service.local.SystemSiteCourseTypeManage;
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
public class SystemSiteCourseTypeManageImpl extends BaseMangerImpl implements SystemSiteCourseTypeManage {

	private SystemSiteCourseTypeDAO systemSiteCourseTypeDAO ;

	public void setSystemSiteCourseTypeDAO(SystemSiteCourseTypeDAO systemSiteCourseTypeDAO) {
		this.systemSiteCourseTypeDAO = systemSiteCourseTypeDAO;
	}
	
	@Override //取得所有站点信息
	public List<SystemSiteCourseType> getListByItem(SystemSiteCourseType item){
		return systemSiteCourseTypeDAO.getListByItem(item) ;
	}
	
	public void querySystemSiteCourseTypeList(Page<SystemSiteCourseType> page,
			SystemSiteCourseType item) {
		systemSiteCourseTypeDAO.querySystemSiteCourseTypeList(page, item);
	}
	
	
	public int update(SystemSiteCourseType item) {
		return systemSiteCourseTypeDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return systemSiteCourseTypeDAO.deleteList(ids,idColName);
	}

	
	public SystemSiteCourseType getItemById(Long id) {
		return systemSiteCourseTypeDAO.getItemById(id);
	}

	
	public Integer save(SystemSiteCourseType item) {
		return systemSiteCourseTypeDAO.save(item);
	}

	@Override
	public int delete(long id, String idColName) {
		return systemSiteCourseTypeDAO.delete(id,idColName);
	}
	
	public boolean deleteBySiteId(Long siteId){
		return systemSiteCourseTypeDAO.deleteBySiteId(siteId);
	}

}