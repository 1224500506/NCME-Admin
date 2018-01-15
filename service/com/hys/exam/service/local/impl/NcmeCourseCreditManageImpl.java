package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.local.NcmeCourseCreditDAO;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.model.StudyCourseSetting;
import com.hys.exam.service.local.NcmeCourseCreditManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseCreditManageImpl extends BaseMangerImpl implements NcmeCourseCreditManage {

	private NcmeCourseCreditDAO ncmeCourseCreditDAO;

	public void setNcmeCourseCreditDAO(NcmeCourseCreditDAO ncmeCourseCreditDAO) {
		this.ncmeCourseCreditDAO = ncmeCourseCreditDAO;
	}

	@Override
	public void getNcmeCourseCreditList(Page<NcmeCourseCredit> page,
			NcmeCourseCredit ncmeCourseCredit) {
		ncmeCourseCreditDAO.getNcmeCourseCreditList(page, ncmeCourseCredit);
	}
	
	//根据课程分类得到其授权的课程列表
	@Override
	public List<NcmeCourseCredit> getNcmeCourseCreditListByCourseType(
			Page<NcmeCourseCredit> page, NcmeCourseCredit n, Long cardTypeId){
		return ncmeCourseCreditDAO.getNcmeCourseCreditListByCourseType(page, n, cardTypeId);
	}

	@Override
	public int addNcmeCourseCredit(NcmeCourseCredit n) {
		return ncmeCourseCreditDAO.addNcmeCourseCredit(n);
	}

	@Override
	public int deleteNcmeCourseCredit(NcmeCourseCredit n) {
		return ncmeCourseCreditDAO.deleteNcmeCourseCredit(n);
	}

	@Override
	public void addNcmeCourseCreditList(List<NcmeCourseCredit> list) {
		for (int i = 0; i < list.size(); ++i) {
			NcmeCourseCredit n = list.get(i);
			NcmeCourseCredit current = ncmeCourseCreditDAO
					.getNcmeCourseCreditById(n.getCreditYear(),	n.getCourseId(), n.getSiteId(), n.getCourseType());

			if (current != null) {
				//如果现有 删除原来的数据 使用的新的替换
				ncmeCourseCreditDAO.deleteNcmeCourseCredit(n);
			}

			ncmeCourseCreditDAO.addNcmeCourseCredit(n);
		}
	}
	
	/**
	 * 
	 * 修改授权
	 * @param n
	 */
	@Override
	public void updateNcmeCourseCredit(List<NcmeCourseCredit> list){
		if(!Utils.isListEmpty(list)){
			for(int i=0; i<list.size(); i++){
				NcmeCourseCredit credit = list.get(i);
				this.ncmeCourseCreditDAO.updateNcmeCourseCredit(credit);
			}
		}
		
	}
	
	//修改授权时间
	@Override
	public int updateNcmeCourseCreditDate(NcmeCourseCredit credit){
		return this.ncmeCourseCreditDAO.updateNcmeCourseCreditDate(credit);
	}
	
	/**
	 * 
	 * 得到授权 object
	 * @param year
	 * @param courseId
	 * @param siteId
	 * @return
	 */
	@Override
	public NcmeCourseCredit getNcmeCourseCredit(String year, Long courseId, Long siteId){
		return this.ncmeCourseCreditDAO.getNcmeCourseCredit(year, courseId, siteId);
	}

	@Override
	public void deleteNcmeCourseCreditList(List<NcmeCourseCredit> list) {
		for (int i = 0; i < list.size(); ++i) {
			NcmeCourseCredit n = list.get(i);
			ncmeCourseCreditDAO.deleteNcmeCourseCredit(n);
		}
	}
	
	//得到课件播放时间
	@Override
	public StudyCourseSetting getStudyCourseSetting(){
		return ncmeCourseCreditDAO.getStudyCourseSetting();
	}
	
	//保存课件播放时间
	@Override
	public int saveStudyCourseSetting(Long id, Double time){
		return ncmeCourseCreditDAO.saveStudyCourseSetting(id, time);
	}
}
