package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.AbstractDAO;
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
public interface SystemClientDAO extends AbstractDAO{

	public List<SystemClient> getListByItem(SystemClient item);
	
	public void getSystemClientList(Page<SystemClient> page, SystemClient item);

	public Integer save(SystemClient item);
	
	public int update(SystemClient item);
}
