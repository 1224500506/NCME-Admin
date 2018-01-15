package com.hys.auth.service.impl;

import java.util.List;

import com.hys.auth.dao.RolesDAO;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataRoles;
import com.hys.auth.model.HysRoles;
import com.hys.auth.service.RolesManage;
import com.hys.framework.exception.FrameworkRuntimeException;

public class RolesManageImpl extends AbstractManageImpl implements RolesManage {

	private RolesDAO rolesDAO;
	@Override
	public RolesDAO getDao() {
		return rolesDAO;
	}
	
	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}


	public int delete(Integer id) {
		return rolesDAO.delete(id);
	}
	
	public boolean deleteRow(Integer id) {
		return rolesDAO.deleteRow(id);
	}


	public int update(HysRoles roles) {
		return rolesDAO.update(roles);
	}

	public List<HysRoles> getRoleByResourceId(Integer resourceId) {
		return rolesDAO.getRoleByResourceId(resourceId);
	}
	
	public List<HysDataDetail> getRolesDataList() throws FrameworkRuntimeException {
		return rolesDAO.getRolesDataList();
	}
	
	@Override
	public Long save(HysRoles roles) {
		return rolesDAO.save(roles);
	}
	
	@Override
	public Long saveData(HysDataRoles dataRoles) {
		return rolesDAO.saveData(dataRoles);
	}
	
	@Override
	public Long updateData(HysDataRoles dataRoles) {
		return rolesDAO.updateData(dataRoles);
	}
}
