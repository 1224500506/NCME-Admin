package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.exam.service.local.StudyCourseElementManage;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程信息 facadeImpl
 * 
 * 说明:
 */
public class StudyCourseElementFacadeImpl extends BaseSessionFacadeImpl implements StudyCourseElementFacade {

	private StudyCourseElementManage studyCourseElementManage;

	public void setStudyCourseElementManage(StudyCourseElementManage studyCourseElementManage) {
		this.studyCourseElementManage = studyCourseElementManage;
	}
	
	@Override //修改课程组件信息
	public int updateStudyCourseElement(StudyCourseElement element) {
		try{
			return studyCourseElementManage.updateStudyCourseElement(element) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //根据主键ID 查询课程组件信息
	public StudyCourseElement getStudyCourseElementById(Long elementId) {
		try{
			return studyCourseElementManage.getStudyCourseElementById(elementId) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //取得当前课程下组件距离seq 最近的一条数据
	public StudyCourseElement getCurElementRecentlyData(Long courseId, Long seq, int flag) {
		try{
			return studyCourseElementManage.getCurElementRecentlyData(courseId, seq, flag) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //取得当前课程下 组件列表的最大Seq
	public Integer getCurElementMaxSeq(Long courseId) {
		try{
			return studyCourseElementManage.getCurElementMaxSeq(courseId) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //批量修改课程组件
	public int updateCurElementBatch(List<StudyCourseElement> eleList) {
		try{
			return studyCourseElementManage.updateCurElementBatch(eleList) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //删除课程组件信息
	public int deleteStudyCourseElement(List<StudyCourseElement> eleList) {
		try{
			return studyCourseElementManage.deleteStudyCourseElement(eleList) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override //删除课程组件ID
	public int deleteStudyCourseElement(Long elementId) {
		try{
			return studyCourseElementManage.deleteStudyCourseElement(elementId) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //判断当前课程下课程组件分类数量
	public List<Integer> getJudgeCurElementNumber(Long courseId) {
		try{
			return studyCourseElementManage.getJudgeCurElementNumber(courseId) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //添加课程组件信息
	public int addStudyCourseElement(StudyCourseElement element) {
		try{
			return studyCourseElementManage.addStudyCourseElement(element) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //添加课程组件课件信息
	public int addStudyCourseElementWare(List<StudyCourseElement> eleList, Long courseId) {
		try{
			return studyCourseElementManage.addStudyCourseElementWare(eleList, courseId) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //添加课程 课程组件试题
	public int addStudyCourseElementQuestion(StudyCourseElement element) {
		try{
			return studyCourseElementManage.addStudyCourseElementQuestion(element) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //根据课程组件ID 考试试题ID 列表
	public List<StudyCourseElementQuestion> getCurEleQuestByElement(Long elementId) {
		try{
			return studyCourseElementManage.getCurEleQuestByElement(elementId) ;
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
}