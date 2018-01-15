package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.NcmeAdminOrgan;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public interface NcmeAdminOrganDAO {
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<NcmeAdminOrgan> getNcmeAdminOrganList();
	
	public List<NcmeAdminOrgan> getNcmeAdminOrganList(Page<NcmeAdminOrgan> page, NcmeAdminOrgan organ);
	
	public List<NcmeAdminOrgan> getNcmeAdminOrganList(Long pid);
	
	public NcmeAdminOrgan getNcmeAdminOrganById(Long id);
	
	public int saveNcmeAdminOrgan(NcmeAdminOrgan log);
	
	public int deleteNcmeAdminOrgan(Long id);
}
