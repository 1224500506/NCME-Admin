package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemApplication;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-25
 * 
 * 描述：应用系统信息 facade
 * 
 * 说明:
 */
public interface SystemApplicationFacade {
	
	public List<SystemApplication> getListByItem(SystemApplication item);

	public void querySystemApplicationList(Page<SystemApplication> page,
			SystemApplication item);

	public Integer save(SystemApplication item);

	public SystemApplication getItemById(Long id);

	public int update(SystemApplication item);

	public int deleteList(long[] selIdArr,String idColName);
}