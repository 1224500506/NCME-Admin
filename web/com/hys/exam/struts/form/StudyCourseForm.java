package com.hys.exam.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseElementQuestion;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseForm extends BaseForm {

	private static final long serialVersionUID = -8312978765068084140L;

	/**
	 * 课程信息
	 */
	private StudyCourse course = new StudyCourse() ;
	
	/**
	 * 课程组件信息列表
	 */
	private List<StudyCourseElement> eleList ;
	
	/**
	 * 练习习题列表
	 */
	private List<StudyCourseElementQuestion> questList ;
	
	/**
	 * 课程组件类别
	 */
	private Integer courseElementType ;
	
	/**
	 * 课程ID
	 */
	private Long courseId ;
	
	/**
	 * 上传文件
	 */
	private FormFile file;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	//提交页面时动态构造list对象的核心方法
	public StudyCourseElement getElements(int i) {
		if (eleList == null)
			eleList = new ArrayList<StudyCourseElement>();
		if (eleList.size() <= i) {
			for (int j = eleList.size(); j <= i; j++)
				eleList.add(new StudyCourseElement());
		}
		return (StudyCourseElement) eleList.get(i);
	}

	//收集页面练习题列表信息
	public StudyCourseElementQuestion getPract(int i) {
		if (questList == null)
			questList = new ArrayList<StudyCourseElementQuestion>();
		if (questList.size() <= i) {
			for (int j = questList.size(); j <= i; j++)
				questList.add(new StudyCourseElementQuestion());
		}
		return (StudyCourseElementQuestion) questList.get(i);
	}

	public List<StudyCourseElement> getEleList() {
		return eleList;
	}

	public void setEleList(List<StudyCourseElement> eleList) {
		this.eleList = eleList;
	}	
	
	public StudyCourse getCourse() {
		return course;
	}

	public void setCourse(StudyCourse course) {
		this.course = course;
	}
	
	public Integer getCourseElementType() {
		return courseElementType;
	}

	public void setCourseElementType(Integer courseElementType) {
		this.courseElementType = courseElementType;
	}
	
	public List<StudyCourseElementQuestion> getQuestList() {
		return questList;
	}

	public void setQuestList(List<StudyCourseElementQuestion> questList) {
		this.questList = questList;
	}
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
