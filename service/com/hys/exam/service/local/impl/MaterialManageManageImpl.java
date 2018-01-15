package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.MaterialManageDAO;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.service.local.MaterialManageManage;
import com.hys.framework.service.impl.BaseMangerImpl;


public class MaterialManageManageImpl extends BaseMangerImpl implements
	MaterialManageManage {

	private MaterialManageDAO localMaterialManageDAO;

	public MaterialManageDAO getLocalMaterialManageDAO() {
		return localMaterialManageDAO;
	}

	public void setLocalMaterialManageDAO(MaterialManageDAO localMaterialManageDAO) {
		this.localMaterialManageDAO = localMaterialManageDAO;
	}

	@Override
	public List<MaterialInfo> getMaterialList(MaterialInfo material, String[] upload_date, String[] deli_date, int orderBy) {
		return localMaterialManageDAO.getMaterialList(material, upload_date, deli_date, orderBy);
	}
	
	@Override
	public List<MaterialInfo> getMaterialList(MaterialInfo material) {
		return localMaterialManageDAO.getMaterialList(material);
	}

	@Override
	public MaterialInfo getMaterialInfo(MaterialInfo material) {
		return localMaterialManageDAO.getMaterialInfo(material.getId());
	}

	@Override
	public MaterialInfo getMaterialInfo(Long id) {
		return localMaterialManageDAO.getMaterialInfo(id);
	}

	@Override
	public MaterialInfo getMaterialInfo(String materialName) {
		return localMaterialManageDAO.getMaterialInfo(materialName);
	}
	
	@Override
	public boolean addMaterialInfo(MaterialInfo material) {
		return localMaterialManageDAO.addMaterialInfo(material);
	}
	
	@Override
	public boolean addMaterialProp(MaterialInfo material) {
		return localMaterialManageDAO.addMaterialProp(material);
	}

	@Override
	public boolean updateMaterialInfo(MaterialInfo material) {
		return localMaterialManageDAO.updateMaterialInfo(material);
	}

	@Override
	public boolean updateMaterialProp(MaterialInfo material) {
		return localMaterialManageDAO.updateMaterialProp(material);
	}
	
	@Override
	public boolean deleteMaterialInfo(MaterialInfo material) {
		return localMaterialManageDAO.deleteMaterialInfo(material.getId());
	}

	@Override
	public boolean deleteMaterialInfo(Long id) {
		return localMaterialManageDAO.deleteMaterialInfo(id);
	}

	@Override
	public boolean updateStatesOfMaterial(Long[] ids, int state, String opinion, String userName) {
		return localMaterialManageDAO.updateStatesOfMaterial(ids, state, opinion, userName);
	}

	@Override
	public List<Long> getMaterialPropById(Long id) {
		return localMaterialManageDAO.getMaterialPropById(id);
	}

	@Override
	public void updateMaterialSourceId(Long oldId, Long newId) {
		localMaterialManageDAO.updateMaterialSourceId(oldId, newId);
	}

	@Override
	public boolean updateStatesOfMaterial(Long[] ids, int state) {
		// TODO Auto-generated method stub
		return false;
	}
}
