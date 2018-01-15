package com.hys.exam.service.local.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.StudyCourseDAO;
import com.hys.exam.dao.local.StudyCourseElementDAO;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.exam.model.StudyCourseElementWare;
import com.hys.exam.service.local.StudyCourseElementManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程信息 manageImpl
 * 
 * 说明:
 */
public class StudyCourseElementManageImpl extends BaseMangerImpl implements StudyCourseElementManage {

	private StudyCourseDAO studyCourseDAO ;
	
	private StudyCourseElementDAO studyCourseElementDAO ;

	public void setStudyCourseDAO(StudyCourseDAO studyCourseDAO) {
		this.studyCourseDAO = studyCourseDAO;
	}

	public void setStudyCourseElementDAO(StudyCourseElementDAO studyCourseElementDAO) {
		this.studyCourseElementDAO = studyCourseElementDAO;
	}

	@Override //修改课程组件信息
	public int updateStudyCourseElement(StudyCourseElement element) {
		return studyCourseElementDAO.updateStudyCourseElement(element);
	}
	
	@Override //根据主键ID 查询课程组件信息
	public StudyCourseElement getStudyCourseElementById(Long elementId) {
		return studyCourseElementDAO.getStudyCourseElementById(elementId);
	}
	
	@Override //取得当前课程下组件距离seq 最近的一条数据
	public StudyCourseElement getCurElementRecentlyData(Long courseId, Long seq, int flag) {
		return studyCourseElementDAO.getCurElementRecentlyData(courseId, seq, flag);
	}
	
	@Override //取得当前课程下 组件列表的最大Seq
	public Integer getCurElementMaxSeq(Long courseId) {
		return studyCourseElementDAO.getCurElementMaxSeq(courseId);
	}
	
	@Override //批量修改课程组件
	public int updateCurElementBatch(List<StudyCourseElement> eleList) {
		return studyCourseElementDAO.updateCurElementBatch(eleList);
	}
	
	@Override //删除课程组件信息
	public int deleteStudyCourseElement(List<StudyCourseElement> eleList) {
		int val = 0 ;
		if(!Utils.isListEmpty(eleList)){
			for (StudyCourseElement element : eleList) {
				element.setStatus(Constants.STATUS_2) ;
				int tem = studyCourseElementDAO.updateStudyCourseElement(element) ;
				if(tem > 0){
					val += 1 ;
				}
			}
		}
		return val ;
	}
	
	@Override //删除课程组件ID
	public int deleteStudyCourseElement(Long elementId) {
		StudyCourseElement element = new StudyCourseElement() ;
		element.setId(elementId) ;
		element.setStatus(Constants.STATUS_2) ;
		return studyCourseElementDAO.updateStudyCourseElement(element) ;
	}
	

	@Override //判断当前课程下课程组件分类数量
	public List<Integer> getJudgeCurElementNumber(Long courseId) {
		return studyCourseElementDAO.getJudgeCurElementNumber(courseId) ;
	}
	
	@Override //添加课程组件信息
	public int addStudyCourseElement(StudyCourseElement element) {
		return studyCourseElementDAO.addStudyCourseElement(element) ;
	}
	
	@Override //添加课程组件课件信息
	public int addStudyCourseElementWare(List<StudyCourseElement> eleList, Long courseId) {
		int val = 0 ;
		//取得当前课程下组件最大SEQ
		Integer maxSeq = studyCourseElementDAO.getCurElementMaxSeq(courseId) ;
		
		int length = eleList.size() ;
		for (int i = 1; i <= length; i++) {
			StudyCourseElement element = eleList.get(i-1) ;
			
			//新增课程组件信息
			element.setSeq(Long.valueOf(maxSeq + i)) ;
			studyCourseElementDAO.addStudyCourseElement(element) ;
			
			//新增课程组件课件信息
			StudyCourseElementWare ware = element.getElementWare() ;
			ware.setStudyCourseElementId(element.getId()) ;
			studyCourseElementDAO.addStudyCourseElementWare(ware) ;
			val += 1;
		}
		
		//修改课程类别信息
		int curType = checkCurEleWare(eleList, courseId) ;
		StudyCourse course = new StudyCourse() ;
		course.setId(courseId) ;
		course.setStudyCourseType(curType) ;
		studyCourseDAO.updateStudyCourse2(course) ;
		
		return val ;
	}
	
	/**
	 * 判断课程下分类类型
	 * @param eleList
	 * @param courseId
	 * @return
	 */
	private int checkCurEleWare(List<StudyCourseElement> eleList, Long courseId){
		//查询本课程下分类数量
		List<Integer> typeList = getJudgeCurElementNumber(courseId) ;
		//将所有类别存入List数组
		for (StudyCourseElement element : eleList) {
			typeList.add(element.getCourseElementType()) ;
		}
		
		Map<Integer, Integer> eleMap = new HashMap<Integer, Integer>() ;
		for (Integer integer : typeList) {
			eleMap.put(integer, integer) ;
		}
		
		int type = 0 ;
		if(eleMap.size() == 1){
			List<Integer> list = new ArrayList<Integer>(eleMap.values()) ;
			type = Constants.curTypeMap.get(list.get(0)) ;
		}else{
			type = Constants.STUDY_COURSE_TYPE_5 ;
		}
		
		return type ;
	}
	
	@Override //添加课程 课程组件试题
	public int addStudyCourseElementQuestion(StudyCourseElement element) {
		//取得当前课程下组件最大SEQ
		Integer maxSeq = studyCourseElementDAO.getCurElementMaxSeq(element.getCourseId()) ;
		
		//添加课程组件信息
		element.setSeq(Long.valueOf(maxSeq + 1)) ;
		element.setStatus(Constants.STATUS_1) ;
		studyCourseElementDAO.addStudyCourseElement(element) ;
		
		//添加组件与试题关联关系
		List<StudyCourseElementQuestion> questList = element.getQuestList() ;
		if(Utils.isListEmpty(questList)){
			return 0 ;
		}
		
		StudyCourseElementQuestion quest = null ;
		for (int i = 0; i < questList.size(); i++) {
			quest = questList.get(i) ;
			quest.setSeq(i + 1) ;
			quest.setStudyCourseElementId(element.getId()) ;
		}
		
		return studyCourseElementDAO.addStudyCourseElementQuestion(questList) ;
	}
	

	@Override //根据课程组件ID 考试试题ID 列表
	public List<StudyCourseElementQuestion> getCurEleQuestByElement(Long elementId) {
		return studyCourseElementDAO.getCurEleQuestByElement(elementId) ;
	}
}