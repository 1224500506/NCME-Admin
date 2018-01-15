package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CV implements Serializable {

    /**
     * boy
     */
    private static final long serialVersionUID = 4599448644515104811L;

    private Long id;

    private String name;

    private String serial_number;

    private List<PropUnit> courseList;

    private String brand;

    private List<ExpertInfo> teacherList;

    private List<ExpertInfo> otherTeacherList;

    private String introduction;

    private String announcement;

    private String file_path;

    private String creator;

    private List<CVUnit> unitList;

    private List<PropUnit> usingItem;

    private Date create_date;

    //chenlb add
    private int ordernum;

    private Date startDate;        //开始时间

    private Date endDate;        //结束时间

    private String cvsetName;    //所属项目

    private Integer cv_type; //YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）

    private Integer is_add_out_chain; //0 否 1 是 添加外链

    private String cv_url;    //外链URL

    private String cv_address;    //面授课程地址

    private Integer is_need_apply;// 是否需要报名

    private Integer apply_num;  //报名人数

    private Integer sort;//排序------taoliang


    public CV() {
    }

    public CV(CV cv) {
        setId(cv.getId());
        setName(cv.getName());
        setSerial_number(cv.getSerial_number());
        setCourseList(cv.getCourseList());
        setBrand(cv.getBrand());
        setTeacherList(cv.getTeacherList());
        setOtherTeacherList(cv.getOtherTeacherList());
        setIntroduction(cv.getIntroduction());
        setAnnouncement(cv.getAnnouncement());
        setFile_path(cv.getFile_path());
        setCreator(cv.getCreator());
        setUnitList(cv.getUnitList());
        setCreate_date(cv.getCreate_date());
        setCv_type(cv.getCv_type());
        setIs_add_out_chain(cv.getIs_add_out_chain());
        setCv_url(cv.getCv_url());
        setCv_address(cv.getCv_address());
        setIs_need_apply(cv.getIs_need_apply());
        setApply_num(cv.getApply_num());
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public List<PropUnit> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<PropUnit> courseList) {
        this.courseList = courseList;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<ExpertInfo> getTeacherList() {
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
    }

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

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public List<CVUnit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<CVUnit> unitList) {
        this.unitList = unitList;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<PropUnit> getUsingItem() {
        return usingItem;
    }

    public void setUsingItem(List<PropUnit> usingItem) {
        this.usingItem = usingItem;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCvsetName() {
        return cvsetName;
    }

    public void setCvsetName(String cvsetName) {
        this.cvsetName = cvsetName;
    }

    public Integer getCv_type() {
        return cv_type;
    }

    public void setCv_type(Integer cv_type) {
        this.cv_type = cv_type;
    }


    //YHQ，2017-06-03，0点播，1面授，2直播（默认的老课程都是点播）
    public String getCvTypeName() {
        String cvTypeName = "";

        if (this.cv_type == 0) cvTypeName = "点播";
        if (this.cv_type == 1) cvTypeName = "面授";
        if (this.cv_type == 2) cvTypeName = "直播";

        return cvTypeName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
