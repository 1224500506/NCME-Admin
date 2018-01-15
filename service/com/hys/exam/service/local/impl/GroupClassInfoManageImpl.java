package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.GroupClassInfoDAO;
import com.hys.exam.model.CVT;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.service.local.GroupClassInfoManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：组课功能
 * 
 * 作者：ZJG 2016-12-19
 * 
 * 描述：
 * 
 * 说明:
 */
public class GroupClassInfoManageImpl extends BaseMangerImpl implements GroupClassInfoManage {

	private GroupClassInfoDAO groupClassInfoDAO;

	public GroupClassInfoDAO getGroupClassInfoDAO() {
		return groupClassInfoDAO;
	}

	public void setGroupClassInfoDAO(GroupClassInfoDAO groupClassInfoDAO) {
		this.groupClassInfoDAO = groupClassInfoDAO;
	}

	@Override
	public boolean addGroupClassInfo(GroupClassInfo group) throws Exception{
		return groupClassInfoDAO.addGroupClassInfo(group);
	}

	@Override
	public List<GroupClassInfo> queryGroupClassContent(int classId) throws Exception{
		return  groupClassInfoDAO.queryGroupClassContent(classId);
	}

	@Override
	public void updateGroupClassInfo(GroupClassInfo group) throws Exception{
		groupClassInfoDAO.updateGroupClassInfo(group);	
	}
	
	@Override
	public List<CVT> queryCVTList(int classId) throws Exception{
		return groupClassInfoDAO.queryCVTList(classId);
	}

	@Override
	public List<GroupClassInfo> queryList() throws Exception {
		return groupClassInfoDAO.queryList();
	}
        
        //YHQ 2017-03-04，更新课程单元的评分
	@Override
	public boolean updateUnitQuota(Long unitId, Double unitQuota) throws Exception {
            return groupClassInfoDAO.updateUnitQuota(unitId, unitQuota) ;
        }
}
