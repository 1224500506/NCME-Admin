package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.AbstractDAO;
import com.hys.exam.model.SystemApplication;

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
public interface SystemApplicationDAO extends AbstractDAO{
	
	public List<SystemApplication> getListByItem(SystemApplication item);

	public void querySystemApplicationList(Page<SystemApplication> page, SystemApplication item);
	
	public Integer save(SystemApplication item);

	public int update(SystemApplication item);

	
}