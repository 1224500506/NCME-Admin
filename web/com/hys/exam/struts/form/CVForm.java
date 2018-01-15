package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;
import com.hys.framework.web.form.BaseForm;

public class CVForm extends BaseForm {
    /**
     *
     */
    private static final long serialVersionUID = 8528979877396678303L;

    private FormFile matFile;

    private Long id;

    private String name;

    private String serial_number;

    //private List<PropUnit> courseList;

    private String brand;

    //private List<ExpertInfo> teacherList;

    //private List<ExpertInfo> otherTeacherList;

    private String introduction;

    private String announcement;

    private String file_path;

    private String creator;

    private Integer cv_type; //YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）

    private Integer is_add_out_chain; //0 否 1 是 添加外链

    private String cv_url;    //外链URL

    private String cv_address;     //面授课程地址

    private Integer is_need_apply;// 是否需要报名

    private Integer apply_num;  //报名人数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

	/*public List<ExpertInfo> getTeacherList() {
        return teacherList;
	}

	public void setTeacherList(List<ExpertInfo> teacherList) {
		this.teacherList = teacherList;
	}

	public List<ExpertInfo> getOtherTeacherList() {
		return otherTeacherList;
	}

	public void setOtherTeacherList(List<ExpertInfo> otherTeacherList) {
		this.otherTeacherList = otherTeacherList;
	}*/

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public FormFile getMatFile() {
        return matFile;
    }

    public void setMatFile(FormFile matFile) {
        this.matFile = matFile;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Integer getCv_type() {
        return cv_type;
    }

    public void setCv_type(Integer cv_type) {
        this.cv_type = cv_type;
    }

    public Integer getIs_add_out_chain() {
        return is_add_out_chain;
    }

    public void setIs_add_out_chain(Integer is_add_out_chain) {
        this.is_add_out_chain = is_add_out_chain;
    }

    public String getCv_url() {
        return cv_url;
    }

    public void setCv_url(String cv_url) {
        this.cv_url = cv_url;
    }

    public String getCv_address() {
        return cv_address;
    }

    public void setCv_address(String cv_address) {
        this.cv_address = cv_address;
    }

    public Integer getIs_need_apply() {
        return is_need_apply;
    }

    public void setIs_need_apply(Integer is_need_apply) {
        this.is_need_apply = is_need_apply;
    }

    public Integer getApply_num() {
        return apply_num;
    }

    public void setApply_num(Integer apply_num) {
        this.apply_num = apply_num;
    }
}
