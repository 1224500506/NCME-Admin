package com.hys.exam.service.local;

import java.util.List;
import com.hys.exam.model.CVT;
import com.hys.exam.model.GroupClassInfo;
import com.hys.framework.service.BaseService;

/**
 * 
 * 标题：组课功能
 * 
 * 作者：ZJG 2012-12-19
 * 
 * 描述：
 * 
 * 说明:
 */
public interface GroupClassInfoManage extends BaseService {

	//新增组课信息
	public boolean addGroupClassInfo(GroupClassInfo group) throws Exception;
	
	//根据课程id查询课程信息
	public List<GroupClassInfo> queryGroupClassContent(int classId) throws Exception;
	
	//修改课程信息
	public void updateGroupClassInfo(GroupClassInfo group) throws Exception;
	
	//根据课程id查询课程及单元信息
	public List<CVT> queryCVTList(int classId) throws Exception;
	
	//查询所有组课信息
	public List<GroupClassInfo> queryList() throws Exception;
	
        //YHQ 2017-03-04，更新课程单元的评分
	public boolean updateUnitQuota(Long unitId, Double unitQuota) throws Exception;
}
