package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.StudyCoursewareDAO;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.service.local.StudyCoursewareManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCoursewareManageImpl extends BaseMangerImpl implements
		StudyCoursewareManage {

	private StudyCoursewareDAO studyCoursewareDAO;

	public void setStudyCoursewareDAO(StudyCoursewareDAO studyCoursewareDAO) {
		this.studyCoursewareDAO = studyCoursewareDAO;
	}

	@Override
	public void getStudyCoursewareList(Page<StudyCourseware> page,
			StudyCourseware courseware) {
		studyCoursewareDAO.getStudyCoursewareList(page, courseware);
	}
	
	@Override //查询课程下的课件信息
	public void getCourseWareByCourse(Page<StudyCourseware> page, StudyCourseware curware){
		studyCoursewareDAO.getCourseWareByCourse(page, curware);
	}

	@Override
	public int saveStudyCourseware(StudyCourseware s) {
		if (s.getId() != null && s.getId() != 0L) {
			return studyCoursewareDAO.updateStudyCourseware(s);
		} else {
			return studyCoursewareDAO.addStudyCourseware(s);
		}
	}
	
	//导入课件
	@Override
	public int importStudyCourseware(List<StudyCourseware> list){
		int row = 0;
		for(StudyCourseware ware : list){
			row += studyCoursewareDAO.addStudyCourseware(ware);
		}
		return row;
	}
	
	//批量导入修改的课件
	@Override
	public int inportStudyCourseware2Update(List<StudyCourseware> list){
		int row = 0;
		for(StudyCourseware ware : list){
			row += studyCoursewareDAO.updateStudyCoursewareById(ware);
		}
		return row;
	}

	@Override
	public StudyCourseware getStudyCoursewareById(Long id) {
		return studyCoursewareDAO.getStudyCoursewareById(id);
	}

	@Override
	public void deleteStudyCourseware(List<StudyCourseware> list) {
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); ++i) {
				StudyCourseware s = list.get(i);
				studyCoursewareDAO.deleteStudyCourseware(s);
			}
		}
	}
	
	@Override //根据课程组件ID 查询课件信息
	public StudyCourseware getStudyCoursewareByEleId(Long elementId) {
		return studyCoursewareDAO.getStudyCoursewareByEleId(elementId);
	}
}
