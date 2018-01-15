package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemClient;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public interface SystemClientManage  {

	public List<SystemClient> getListByItem(SystemClient item);

	public void getSystemClientList(Page<SystemClient> page,
			SystemClient item);

	public int update(SystemClient item);

	public int deleteList(long[] ids, String idColName);

	public SystemClient get(int intValue);

	public Integer save(SystemClient item);

	public int delete(long id, String idColName);
}
