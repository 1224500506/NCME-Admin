package com.hys.exam.service.local.impl;

import com.hys.exam.dao.local.ContentIssueManageDAO;
import com.hys.exam.model.ContentIssue;
import com.hys.exam.service.local.ContentIssueManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;


/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ContentIssueManageImpl extends BaseMangerImpl implements ContentIssueManage {

	private ContentIssueManageDAO localContentIssueManageDAO;

	public ContentIssueManageDAO getLocalContentIssueManageDAO() {
		return localContentIssueManageDAO;
	}

	public void setLocalContentIssueManageDAO(
			ContentIssueManageDAO localContentIssueManageDAO) {
		this.localContentIssueManageDAO = localContentIssueManageDAO;
	}

	@Override
	public void queryIssuePageList(PageList pl, ContentIssue issue) {
		localContentIssueManageDAO.queryIssuePageList(pl, issue);
	}

	@Override
	public ContentIssue getIssueById(Long id) {
		return localContentIssueManageDAO.getIssueById(id);
	}

	@Override
	public boolean insertContentIssue(ContentIssue issue) {
		boolean flag = false;
		try{
			flag = localContentIssueManageDAO.insertContentIssue(issue);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateContentIssue(ContentIssue issue) {
		boolean flag = false;
		try{
			flag = localContentIssueManageDAO.updateContentIssue(issue);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean deleteContentIssue(Long id) {
		boolean flag = false;
		try{
			flag = localContentIssueManageDAO.deleteContentIssue(id);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean resortOrderNum(String orderstr) {
		boolean flag = false;
		try{
			flag = localContentIssueManageDAO.resortOrderNum(orderstr);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}
	
}
