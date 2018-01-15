package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.Sentence;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface SentenceManage extends BaseService {
	
	Sentence getSentenceById(Long id);
	
	List<Sentence> getSentenceList(Sentence sentence);
	
	boolean addSentence(Sentence sentence);	 
	
	boolean updateState(Long id, int state);
	
	boolean deleteSentenceById(Long id);	 

	boolean resortOrderNum(String orderstr); 

	boolean updateSentence(Sentence sentence);

	void getSentencePageList(PageList pl, Sentence sentence);

	/**
	 * 根据标题查询文章
	 * @param title
	 * @return
	 */
	int getSentenceByTitle(String title); 
	
}
