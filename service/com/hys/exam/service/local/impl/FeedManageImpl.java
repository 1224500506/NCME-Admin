package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.FeedManageDAO;
import com.hys.exam.model.Advert;
import com.hys.exam.model.Feedback;
import com.hys.exam.model.SystemSite;
import com.hys.exam.service.local.FeedbackManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class FeedManageImpl extends BaseMangerImpl implements FeedbackManage{

	private FeedManageDAO feedManageDAO;

	public FeedManageDAO getFeedManageDAO() {
		return feedManageDAO;
	}

	public void setFeedManageDAO(FeedManageDAO feedManageDAO) {
		this.feedManageDAO = feedManageDAO;
	}

	@Override
	public void getFeedbackPageList(PageList pl, Feedback feedback) {
		feedManageDAO.getFeedbackPageList(pl,feedback);
		//设置banner对于的站点信息
		/*		List<Feedback> list=pl.getList();
				for(Feedback fee:list){
					fee.setSiteList(getSiteListByFeebackId(fee.getId()));
				}*/
	}

	@Override
	public List<SystemSite> getSiteListByFeebackId(long id) {
		return feedManageDAO.getSiteListByFeebackId(id);
	}

	@Override
	public Feedback getfeedbackyId(Long id) {
		
		return feedManageDAO.getfeedbackyId(id);
	}

	@Override
	public boolean updateState(Long id, int state) {
		
		return feedManageDAO.updateState(id, state);
	}

	@Override
	public boolean deleteFeedbackById(Long id) {
		
		return feedManageDAO.deleteFeedbackById(id);
	}

	@Override
	public int deleteFeedbackType(Long[] typeIds) {
		int row = 0;
		if(null != typeIds && typeIds.length>0){
			for(Long id : typeIds){
				//先删除子表
				row = this.feedManageDAO.deleteFeedbackType(id);
			}
		}
		return row;
	}
	
	
	
}
