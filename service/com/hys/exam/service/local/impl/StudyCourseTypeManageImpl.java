package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.StudyCourseTypeDAO;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.model.StudyCourseTypeCourse;
import com.hys.exam.service.local.StudyCourseTypeManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-26
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseTypeManageImpl extends BaseMangerImpl implements
		StudyCourseTypeManage {

	private StudyCourseTypeDAO studyCourseTypeDAO;

	public void setStudyCourseTypeDAO(StudyCourseTypeDAO studyCourseTypeDAO) {
		this.studyCourseTypeDAO = studyCourseTypeDAO;
	}

	@Override
	// 查询课程分类信息
	public List<StudyCourseType> getCourseTypeByParent(Long parentId) {
		return studyCourseTypeDAO.getCourseTypeByParent(parentId);
	}

	@Override
	// 添加课程分类信息
	public int addStudyCourseType(StudyCourseType courseType) {
		return studyCourseTypeDAO.addStudyCourseType(courseType);
	}

	@Override
	// 修改课程分类
	public int updateStudyCourseType(StudyCourseType courseType) {
		return studyCourseTypeDAO.updateStudyCourseType(courseType);
	}

	@Override
	public void getCourseTypeByParent(Page<StudyCourseType> page,
			Long parentId, StudyCourseType query) {
		studyCourseTypeDAO.getCourseTypeByParent(page, parentId, query);
	}

	@Override
	public List<StudyCourseType> getStudyCourseTypeListById(Long id) {
		return studyCourseTypeDAO.getStudyCourseTypeListById(id);
	}

	@Override
	public void saveStudyCourseType(StudyCourseType courseType) {
		if (courseType.getId() != null && courseType.getId() != 0L) {
			studyCourseTypeDAO.updateStudyCourseType(courseType);
		} else {
			courseType.setStatus(Constants.STATUS_1);
			courseType.setIsLastLevel(Constants.STATUS_1);//最末分类为1

			studyCourseTypeDAO.addStudyCourseType(courseType);
			studyCourseTypeDAO.updateStudyCourseTypeLastLevel(courseType.getParentCourseTypeId(),Constants.STATUS_3);			
		}
	}

	@Override
	public StudyCourseType getStudyCourseTypeById(Long id) {
		return studyCourseTypeDAO.getStudyCourseTypeById(id);
	}

	@Override
	public void deleteStudyCourseType(List<StudyCourseType> list) {
		for (int i = 0; i < list.size(); ++i) {
			StudyCourseType s = list.get(i);
			studyCourseTypeDAO.deleteStudyCourseType(s);
		}

		if (!list.isEmpty()) {
			// 更新父分类的是否最末分类
			StudyCourseType s = list.get(0);

			List<StudyCourseType> StudyCourseTypeList = studyCourseTypeDAO
					.getCourseTypeByParent(s.getParentCourseTypeId());

			if (StudyCourseTypeList.isEmpty()) {
				studyCourseTypeDAO.updateStudyCourseTypeLastLevel(
						s.getParentCourseTypeId(), Constants.STATUS_1);
			}
		}
	}

	@Override
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course) {
		studyCourseTypeDAO.getStudyCourseList(page, course);
	}

	@Override
	public void addStudyCourseTypeCourseList(List<StudyCourseTypeCourse> list) {
		for (int i = 0; i < list.size(); ++i) {
			studyCourseTypeDAO.addStudyCourseTypeCourse(list.get(i));
		}
	}
	
	@Override //取得当前类别下 子类别列表的最大Seq
	public Integer getCurTypeMaxSeq(Long curTypeId) {
		return studyCourseTypeDAO.getCurTypeMaxSeq(curTypeId); 
	}
	
	@Override //取得距离当前分类SEQ 最近的一条数据
	public StudyCourseType getCurTypeRecentlyData(Long curTypeId, Integer seq, int flag) {
		return studyCourseTypeDAO.getCurTypeRecentlyData(curTypeId, seq, flag);
	}
	
	@Override //批量修改课程分类
	public int updateCurTypeBatch(List<StudyCourseType> curTypeList) {
		return studyCourseTypeDAO.updateCurTypeBatch(curTypeList);
	}
	
	@Override //根据分类ID 查询所有子分类信息
	public List<StudyCourseType> getAllChildCurTypeByTypeId(Long curTypeId) {
		return studyCourseTypeDAO.getAllChildCurTypeByTypeId(curTypeId);
	}
}
