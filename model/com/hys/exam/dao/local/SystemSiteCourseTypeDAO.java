package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemSiteCourseType;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 dao
 * 
 * 说明:
 */
public interface SystemSiteCourseTypeDAO {
	
	public List<SystemSiteCourseType> getListByItem(SystemSiteCourseType item);

	public void querySystemSiteCourseTypeList(Page<SystemSiteCourseType> page,
			SystemSiteCourseType item);

	public Integer save(SystemSiteCourseType item);

	public SystemSiteCourseType getItemById(Long id);

	public int update(SystemSiteCourseType item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
	
	public boolean deleteBySiteId(Long siteId);
	
}