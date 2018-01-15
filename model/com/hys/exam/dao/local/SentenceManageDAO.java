package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Sentence;
import com.hys.exam.utils.PageList;

public interface SentenceManageDAO {
	
	Sentence getSentenceById(Long id);
	
	List<Sentence> getSentenceList(Sentence sentence);
	
	boolean  addSentence(Sentence sentence);
	
	boolean updateSentence(Sentence sentence);
	
	boolean updateState(Long id, int state);
	
	boolean deleteSentenceById(Long id);

 	boolean resortOrderNum(String orderstr);

	void getSentencePageList(PageList pl, Sentence sentence);

	/**
	 * 根据标题查询文章
	 * @param title
	 * @return
	 */
	int getSentenceByTitle(String title);
}
