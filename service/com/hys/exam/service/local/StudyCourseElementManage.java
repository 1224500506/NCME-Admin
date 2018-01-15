package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementQuestion;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程组件 manage
 * 
 * 说明:
 */
public interface StudyCourseElementManage {
	
	/**
	 *  修改课程组件信息
	 * @param element
	 * @return
	 */
	public int updateStudyCourseElement(StudyCourseElement element) ;
	
	/**
	 * 根据主键ID 查询课程组件信息
	 * @param elementId
	 * @return
	 */
	public StudyCourseElement getStudyCourseElementById(Long elementId) ;
	
	/**
	 * 取得当前课程下组件距离seq 最近的一条数据
	 * @param courseId	课程ID
	 * @param seq		组件需要移动的SEQ
	 * @param flag		上移或下移标记 1.向上 2.向下 
	 * @return
	 */
	public StudyCourseElement getCurElementRecentlyData(Long courseId, Long seq, int flag) ;
	
	/**
	 * 取得当前课程下 组件列表的最大Seq
	 * @param courseId
	 * @return
	 */
	public Integer getCurElementMaxSeq(Long courseId) ;
	
	/**
	 * 批量修改课程组件
	 * @param eleList
	 * @return
	 */
	public int updateCurElementBatch(List<StudyCourseElement> eleList) ;
	
	/**
	 * 删除课程组件信息
	 * @param eleList
	 * @return
	 */
	public int deleteStudyCourseElement(List<StudyCourseElement> eleList) ;
	
	/**
	 * 删除课程组件ID
	 * @param elementId
	 * @return
	 */
	public int deleteStudyCourseElement(Long elementId) ;
	
	/**
	 * 判断当前课程下课程组件分类数量
	 * @param courseId
	 * @return
	 */
	public List<Integer> getJudgeCurElementNumber(Long courseId) ;
	
	/**
	 * 添加课程组件信息
	 * @param element
	 * @return
	 */
	public int addStudyCourseElement(StudyCourseElement element) ;
	
	/**
	 * 添加课程组件课件信息
	 * @param ware
	 * @return
	 */
	public int addStudyCourseElementWare(List<StudyCourseElement> eleList, Long courseId) ;
	
	/**
	 * 添加课程 课程组件试题
	 * @param element
	 * @return
	 */
	public int addStudyCourseElementQuestion(StudyCourseElement element) ;
	
	/**
	 * 根据课程组件ID 考试试题ID 列表
	 * @param elementId
	 * @return
	 */
	public List<StudyCourseElementQuestion> getCurEleQuestByElement(Long elementId) ;
}