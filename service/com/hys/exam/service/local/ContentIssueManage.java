package com.hys.exam.service.local;

import com.hys.exam.model.ContentIssue;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

/**
 * 
 * @author Han
 *
 */
public interface ContentIssueManage extends BaseService {

	/**
	 * 取得通知列表
	 * @param pl
	 * @param issue
	 */
	public void queryIssuePageList(PageList pl, ContentIssue issue);

	/**
	 * 取得通知信息
	 * @param id
	 * @return
	 */
	public ContentIssue getIssueById(Long id);
	
	/**
	 * 添加新的通知
	 * @param issue
	 * @return
	 */
	public boolean insertContentIssue(ContentIssue issue);
	
	/**
	 * 修改通知信息
	 * @param issue
	 * @return
	 */
	public boolean updateContentIssue(ContentIssue issue);
	
	/**
	 * 删除通知
	 * @param id
	 * @return
	 */
	public boolean deleteContentIssue(Long id);

	public boolean resortOrderNum(String orderstr);
}
