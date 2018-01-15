package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.SentenceManageDAO;
import com.hys.exam.model.Sentence;
import com.hys.exam.service.local.SentenceManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class SentenceManageImpl extends BaseMangerImpl implements
		SentenceManage {
	
	private SentenceManageDAO localSentenceMangeDAO;

	@Override
	public Sentence getSentenceById(Long id) {
		return localSentenceMangeDAO.getSentenceById(id);
	}

	@Override
	public List<Sentence> getSentenceList(Sentence sentence) {
		return localSentenceMangeDAO.getSentenceList(sentence);
	}

 
	 
	@Override
	public boolean deleteSentenceById(Long id) {
		return localSentenceMangeDAO.deleteSentenceById(id);
	}
	
	@Override
	public boolean updateState(Long id, int state) {
		return localSentenceMangeDAO.updateState(id, state) ;
	}
	 
	public SentenceManageDAO getLocalSentenceMangeDAO() {
		return localSentenceMangeDAO;
	}

	public void setLocalSentenceMangeDAO(SentenceManageDAO localSentenceMangeDAO) {
		this.localSentenceMangeDAO = localSentenceMangeDAO;
	}

	@Override
	public boolean addSentence(Sentence sentence) {
		// TODO Auto-generated method stub
		if(localSentenceMangeDAO.addSentence(sentence))
			return true;
		else 
			return false;
	}

	@Override
	public boolean updateSentence(Sentence sentence) {
		   localSentenceMangeDAO.updateSentence(sentence);
		   return true;
	}

	@Override
	public boolean resortOrderNum(String orderstr) {
		boolean flag = false;
		try{
			flag = localSentenceMangeDAO.resortOrderNum(orderstr);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public void getSentencePageList(PageList pl, Sentence sentence) {
		localSentenceMangeDAO.getSentencePageList(pl, sentence);
	}

	/**
	 * 根据标题查询文章
	 * @param title
	 * @return
	 */
	@Override
	public int getSentenceByTitle(String title) {
		return localSentenceMangeDAO.getSentenceByTitle(title);
	}
		 
 
}
