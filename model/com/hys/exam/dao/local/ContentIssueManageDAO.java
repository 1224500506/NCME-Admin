package com.hys.exam.dao.local;

import com.hys.exam.model.ContentIssue;
import com.hys.exam.utils.PageList;


/**
 * 
 * @author Han
 *
 */
public interface ContentIssueManageDAO {


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
