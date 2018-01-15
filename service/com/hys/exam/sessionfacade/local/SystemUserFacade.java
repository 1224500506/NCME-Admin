package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemUser;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 facade
 * 
 * 说明:
 */
public interface SystemUserFacade {

	public List<SystemUser> getListByItem(SystemUser item);

	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item);

	public Integer save(SystemUser item);

	public SystemUser getItemById(Long id);

	public int update(SystemUser item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
}