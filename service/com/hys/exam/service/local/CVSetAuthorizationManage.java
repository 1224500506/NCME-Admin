package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetAuthorCheck;
import com.hys.exam.model.CVSetAuthorQuery;
import com.hys.exam.model.CVSetAuthorization;
import com.hys.framework.service.BaseService;

public interface CVSetAuthorizationManage extends BaseService {
	
	/**
	 * @author taoLiang
	 * @param cvSetId
	 * @return
	 * @desc 项目列表授权（给项目添加新的授权）
	 */
	public CVSet getCVSetForAddAuthorization(Long cvSetId);
	
	
	/**
	 * @author taoLiang
	 * @param cvSetId
	 * @param authorizationId
	 * @return
	 */
	public CVSet getCVSetForAuthorization(Long cvSetId, Long authorizationId);
	
	/**
	 * @author taoLiang
	 * @param saveParams
	 * @return
	 * @desc 保存新增的授权信息
	 */
	public int saveCVSetAuthorization(List<Object> saveParams);
	
	/**
	 * @author taoLiang
	 * @param saveParams
	 * @return
	 * @desc 修改授权信息
	 */
	public int updateCVSetAuthorization(List<Object> saveParams);
	
	/**
	 * @author taoLiang
	 * @param record
	 * @return
	 * @desc 查询授权信息列表
	 */
	List<CVSetAuthorQuery> getCVSetListForAuthor(CVSet record);
	
	/**
	 * @author taoLiang
	 * @param queryCVSet
	 * @return
	 * @desc 项目发布列表
	 */
	List<CVSet> getCVSetListForRelease(CVSet queryCVSet, Long flag);
	
	/**
	 * @author taoLiang
	 * @param cvSetId
	 * @return
	 * @desc 用户授权前check
	 */
	CVSetAuthorCheck getAuthorBeforeCheck(Long cvSetId);
	
	
	/**
	 * @author taoLiang	
	 * @param cvSetId
	 * @param authorizationId
	 * @return
	 * @desc 删除项目已授权信息
	 */
	int deleteAuthor(Long cvSetId, Long authorizationId);
	
	/**
	 * @author taoLiang
	 * @param cvSetId
	 * @return
	 * @desc 项目发布
	 */
	int updateCVSetForRelease(Long [] cvSetIds, Long flag);
	
	/**
	 * author taoLiang
	 * @param cvSetId
	 * @return
	 * @desc 面授人数统计
	 */
	int getFaceteachCount(Long cvSetId);
	
	/**
	 * @author taoLiang
	 * @param cvSetId
	 * @param flag
	 * @desc 在项目重新上线和项目下线后发送系统消息(不需要进行事务处理，多少成功就发送多少)
	 */
	void SendMessageForUser(CVSet cvset, Long flag);
	
	/**
	 * @author taoLiang
	 * @param cvSetId
	 * @return
	 * @desc 发布项目时过滤符合发送条件的项目即已下过线再次发布的项目
	 */
	List<CVSet> getCVSetCheckForRelease(String _cvSetIds, Long flag);
	
}
