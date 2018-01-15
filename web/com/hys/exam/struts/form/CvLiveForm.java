package com.hys.exam.struts.form;

import java.util.Date;
import com.hys.framework.web.form.BaseForm;

public class CvLiveForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6458762348683178421L;

	/**
	 * 
	 */
	
	private Long id;
	private String class_no;
	private String class_name;
	private String number;
	private String teacher_token;
	private String assistant_token;
	private String student_token;
	private String studentClient_token;
	private Date start_date;
	private Date invalid_date;
	private String teacher_join_url;
	private String student_join_url;
	private Integer is_web_join;
	private Integer is_client_join;
	private Integer scene;
	private Long cv_id;
	private Date create_date;
	private Date modify_date;
	private Integer course_make_type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClass_no() {
		return class_no;
	}
	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTeacher_token() {
		return teacher_token;
	}
	public void setTeacher_token(String teacher_token) {
		this.teacher_token = teacher_token;
	}
	public String getAssistant_token() {
		return assistant_token;
	}
	public void setAssistant_token(String assistant_token) {
		this.assistant_token = assistant_token;
	}
	public String getStudent_token() {
		return student_token;
	}
	public void setStudent_token(String student_token) {
		this.student_token = student_token;
	}
	public String getStudentClient_token() {
		return studentClient_token;
	}
	public void setStudentClient_token(String studentClient_token) {
		this.studentClient_token = studentClient_token;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getInvalid_date() {
		return invalid_date;
	}
	public void setInvalid_date(Date invalid_date) {
		this.invalid_date = invalid_date;
	}
	public String getTeacher_join_url() {
		return teacher_join_url;
	}
	public void setTeacher_join_url(String teacher_join_url) {
		this.teacher_join_url = teacher_join_url;
	}
	public String getStudent_join_url() {
		return student_join_url;
	}
	public void setStudent_join_url(String student_join_url) {
		this.student_join_url = student_join_url;
	}
	public Integer getIs_web_join() {
		return is_web_join;
	}
	public void setIs_web_join(Integer is_web_join) {
		this.is_web_join = is_web_join;
	}
	public Integer getIs_client_join() {
		return is_client_join;
	}
	public void setIs_client_join(Integer is_client_join) {
		this.is_client_join = is_client_join;
	}
	public Integer getScene() {
		return scene;
	}
	public void setScene(Integer scene) {
		this.scene = scene;
	}
	public Long getCv_id() {
		return cv_id;
	}
	public void setCv_id(Long cv_id) {
		this.cv_id = cv_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Integer getCourse_make_type() {
		return course_make_type;
	}
	public void setCourse_make_type(Integer course_make_type) {
		this.course_make_type = course_make_type;
	}

}
