package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemApplication;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 manage
 * 
 * 说明:
 */
public interface SystemApplicationManage {
	
	public List<SystemApplication> getListByItem(SystemApplication item);

	public void querySystemApplicationList(Page<SystemApplication> page,
			SystemApplication item);

	public int update(SystemApplication item);

	public int deleteList(long[] ids, String idColName);

	public SystemApplication get(int intValue);

	public Integer save(SystemApplication item);
}