package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.dao.AbstractDAO;
import com.hys.exam.model.SystemOrg;

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
public interface SystemOrgDAO extends AbstractDAO{

	/**
	 * 查询 
	 * 
	 * @param page
	 * @param SystemClient
	 */
	public List<SystemOrg> getSystemOrgList(SystemOrg item);

}
