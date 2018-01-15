package com.hys.auth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hys.auth.dao.ResourceDAO;
import com.hys.auth.dto.ResourceDTO;
import com.hys.auth.model.HysResources;
import com.hys.auth.model.RoleResource;
import com.hys.auth.service.ResourceManage;
import com.hys.auth.util.IntegerUtil;
import com.hys.framework.exception.FrameworkRuntimeException;

public class ResourceManageImpl extends AbstractManageImpl implements ResourceManage {

	private ResourceDAO resourceDAO;

	@Override
	public ResourceDAO getDao() {
		return resourceDAO;
	}

	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	public int update(HysResources resource) {
		return resourceDAO.update(resource);
	}

	public List<ResourceDTO> findResources() {
		return resourceDAO.findResources();
	}

	public HysResources getResource(String value) {
		return resourceDAO.getResource(value);
	}

	public int saveRelateRoleAndResource(String roleIds, String resourceId) {
		String[] roleArray = StringUtils.split(roleIds, ",");
		try {
			deleteRoleAndResource(IntegerUtil.parseInteger(resourceId));
			for (int i = 0; i < roleArray.length; i++) {
				RoleResource rr = new RoleResource();
				rr.setResourceId(IntegerUtil.parseInteger(resourceId));
				rr.setRoleId(IntegerUtil.parseInteger(roleArray[i]));
				resourceDAO.relateRoleAndResource(rr);
			}
		} catch (Exception e) {
			logger.info("关联出问题了", e);
			return 0;
		}
		return 1;
	}

	public int deleteRoleAndResource(Integer resourceId) {
		return resourceDAO.deleteRoleAndResource(resourceId);
	}

	@Override
	public Long save(HysResources resource) {
		return resourceDAO.save(resource);
	}

}
