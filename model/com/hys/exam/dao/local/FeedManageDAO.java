package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Feedback;
import com.hys.exam.model.SystemSite;
import com.hys.exam.utils.PageList;

public interface FeedManageDAO {
	void getFeedbackPageList(PageList pl, Feedback feedback);

	List<SystemSite> getSiteListByFeebackId(long id);

	Feedback getfeedbackyId(Long id);

	boolean deleteFeedbackById(Long id);
	
	boolean updateState(Long id, int state);

	int deleteFeedbackType(Long id);
}
