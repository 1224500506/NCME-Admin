package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemClient;

/**
 * 
 * 标题：
 * 
 * 作者：郭津 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public interface SystemClientFacade {
	
	public List<SystemClient> getListByItem(SystemClient item);
	
	public void getSystemClientList(Page<SystemClient> page,
			SystemClient item);

	public Integer save(SystemClient item);

	public SystemClient getItemById(Long id);

	public int update(SystemClient item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String idColName);

	

}
