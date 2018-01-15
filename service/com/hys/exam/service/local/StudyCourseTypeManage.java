package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.model.StudyCourseTypeCourse;

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
public interface StudyCourseTypeManage {

	/**
	 * 查询课程分类信息
	 * @param parentId 课程分类父ID
	 * @return
	 */
	public List<StudyCourseType> getCourseTypeByParent(Long parentId) ;

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public List<StudyCourseType> getStudyCourseTypeListById(Long id);

	/**
	 * 保存课程分类信息
	 * 
	 * @param courseType
	 * @return
	 */
	public void saveStudyCourseType(StudyCourseType courseType);

	/**
	 * 添加课程分类信息
	 * @param courseType
	 * @return
	 */
	public int addStudyCourseType(StudyCourseType courseType) ;
	
	/**
	 * 修改课程分类
	 * @param courseType
	 * @return
	 */
	public int updateStudyCourseType(StudyCourseType courseType) ;

	/**
	 * 查询课程分类信息
	 * 
	 * @param parentId
	 *            课程分类父ID
	 * @return
	 */
	public void getCourseTypeByParent(
			Page<StudyCourseType> page, Long parentId, StudyCourseType query);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public StudyCourseType getStudyCourseTypeById(Long id);

	/**
	 * 删除课程分类
	 * 
	 * @param list
	 */
	public void deleteStudyCourseType(List<StudyCourseType> list);

	/**
	 * 查询课程分类下的课程信息
	 * @param page
	 * @param course
	 * @return
	 */
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course);	

	/**
	 * 新增课程分类课程
	 * 
	 * @param list
	 */
	public void addStudyCourseTypeCourseList(List<StudyCourseTypeCourse> list);
	
	/**
	 * 取得当前类别下 子类别列表的最大Seq
	 * @param curTypeId
	 * @return
	 */
	public Integer getCurTypeMaxSeq(Long curTypeId) ;
	
	/**
	 * 取得距离当前分类SEQ 最近的一条数据
	 * @param curTypeId	类别ID
	 * @param seq		分类需要移动的SEQ
	 * @param flag		上移或下移标记 1.向上 2.向下 
	 * @return
	 */
	public StudyCourseType getCurTypeRecentlyData(Long curTypeId, Integer seq, int flag) ;
	
	/**
	 * 批量修改课程分类
	 * @param curTypeList
	 * @return
	 */
	public int updateCurTypeBatch(List<StudyCourseType> curTypeList) ;
	
	/**
	 * 根据分类ID 查询所有子分类信息
	 * @param curTypeId
	 * @return
	 */
	public List<StudyCourseType> getAllChildCurTypeByTypeId(Long curTypeId) ;
}
