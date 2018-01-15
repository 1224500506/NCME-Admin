package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.model.StudyCourseTypeCourse;
import com.hys.exam.service.local.StudyCourseTypeManage;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

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
public class StudyCourseTypeFacadeImpl extends BaseSessionFacadeImpl implements StudyCourseTypeFacade {

	private StudyCourseTypeManage studyCourseTypeManage ;

	public void setStudyCourseTypeManage(StudyCourseTypeManage studyCourseTypeManage) {
		this.studyCourseTypeManage = studyCourseTypeManage;
	}
	
	@Override //查询课程分类信息
	public List<StudyCourseType> getCourseTypeByParent(Long parentId)
			throws FrameworkRuntimeException{
		try{
			return studyCourseTypeManage.getCourseTypeByParent(parentId);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //添加课程分类信息
	public int addStudyCourseType(StudyCourseType courseType) {
		try {
			return studyCourseTypeManage.addStudyCourseType(courseType) ;
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override //修改课程分类
	public int updateStudyCourseType(StudyCourseType courseType) {
		try {
			return studyCourseTypeManage.updateStudyCourseType(courseType) ;
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void getCourseTypeByParent(Page<StudyCourseType> page,
			Long parentId, StudyCourseType query) {
		try {
			studyCourseTypeManage.getCourseTypeByParent(page, parentId, query);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public List<StudyCourseType> getStudyCourseTypeListById(Long id) {
		try {
			return studyCourseTypeManage.getStudyCourseTypeListById(id);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void saveStudyCourseType(StudyCourseType courseType) {
		try {
			studyCourseTypeManage.saveStudyCourseType(courseType);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public StudyCourseType getStudyCourseTypeById(Long id) {
		try {
			return studyCourseTypeManage.getStudyCourseTypeById(id);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void deleteStudyCourseType(List<StudyCourseType> list) {
		try {
			studyCourseTypeManage.deleteStudyCourseType(list);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course) {
		try {
			studyCourseTypeManage.getStudyCourseList(page, course);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void addStudyCourseTypeCourseList(List<StudyCourseTypeCourse> list) {
		try {
			studyCourseTypeManage.addStudyCourseTypeCourseList(list);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override //取得当前类别下 子类别列表的最大Seq
	public Integer getCurTypeMaxSeq(Long curTypeId) {
		try {
			return studyCourseTypeManage.getCurTypeMaxSeq(curTypeId);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override //取得距离当前分类SEQ 最近的一条数据
	public StudyCourseType getCurTypeRecentlyData(Long curTypeId, Integer seq, int flag) {
		try {
			return studyCourseTypeManage.getCurTypeRecentlyData(curTypeId, seq, flag);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override //批量修改课程分类
	public int updateCurTypeBatch(List<StudyCourseType> curTypeList) {
		try {
			return studyCourseTypeManage.updateCurTypeBatch(curTypeList);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override //根据分类ID 查询所有子分类信息
	public List<StudyCourseType> getAllChildCurTypeByTypeId(Long curTypeId) {
		try {
			return studyCourseTypeManage.getAllChildCurTypeByTypeId(curTypeId);
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
}
