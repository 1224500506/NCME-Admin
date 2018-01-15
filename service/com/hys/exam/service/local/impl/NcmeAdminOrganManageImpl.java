package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.NcmeAdminOrganDAO;
import com.hys.exam.model.NcmeAdminOrgan;
import com.hys.exam.service.local.NcmeAdminOrganManage;
import com.hys.framework.service.impl.BaseMangerImpl;

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
public class NcmeAdminOrganManageImpl extends BaseMangerImpl implements
		NcmeAdminOrganManage {
	private NcmeAdminOrganDAO ncmeAdminOrganDAO;

	public void setNcmeAdminOrganDAO(NcmeAdminOrganDAO ncmeAdminOrganDAO) {
		this.ncmeAdminOrganDAO = ncmeAdminOrganDAO;
	}

	@Override
	public List<NcmeAdminOrgan> getNcmeAdminOrganList() {
		return ncmeAdminOrganDAO.getNcmeAdminOrganList();
	}
	
	
	@Override
	public List<NcmeAdminOrgan> getNcmeAdminOrganList(Page<NcmeAdminOrgan> page, NcmeAdminOrgan organ){
		return this.ncmeAdminOrganDAO.getNcmeAdminOrganList(page, organ);
	}
	
	@Override
	public List<NcmeAdminOrgan> getNcmeAdminOrganList(Long pid){
		return this.ncmeAdminOrganDAO.getNcmeAdminOrganList(pid);
	}
	
	@Override
	public NcmeAdminOrgan getNcmeAdminOrganById(Long id){
		return this.ncmeAdminOrganDAO.getNcmeAdminOrganById(id);
	}
	
	@Override
	public int saveNcmeAdminOrgan(NcmeAdminOrgan log){
		return this.ncmeAdminOrganDAO.saveNcmeAdminOrgan(log);
	}
	
	@Override
	public int deleteNcmeAdminOrgan(Long[] ids){
		if(null != ids && ids.length>0){
			for(Long id : ids){
				return this.ncmeAdminOrganDAO.deleteNcmeAdminOrgan(id);
			}
		}
		return 0;
	}
}
