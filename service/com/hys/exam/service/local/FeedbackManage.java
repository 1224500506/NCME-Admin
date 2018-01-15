package com.hys.exam.service.local;

import java.util.List;
import com.hys.exam.model.Feedback;
import com.hys.exam.model.SystemSite;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface FeedbackManage extends BaseService {

	void getFeedbackPageList(PageList pl, Feedback feedback);
	List<SystemSite> getSiteListByFeebackId(long id);
	Feedback getfeedbackyId(Long id);
	boolean updateState(Long id, int state);
	boolean deleteFeedbackById(Long id);	
	public int deleteFeedbackType(Long [] typeIds);
	
}
