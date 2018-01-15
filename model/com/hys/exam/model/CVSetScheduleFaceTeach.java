package com.hys.exam.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CVSetScheduleFaceTeach implements Serializable {
    private static final long serialVersionUID = 201706061007001L;

    private Integer sequenceNum;
    private Integer cv_set_id;
    private String class_name;
    private Integer people_number;

    private Date train_starttime;
    private Date train_endtime;

    private String lession_starttime;
    private String lession_endtime;
    private String train_place;
    private String contact_way;
    private String route_way;


    private String trainStartTime;//格式化显示而已
    private String trainEndTime; //格式化显示而已



    public String getTrainStartTime() {
        if (this.trainStartTime == null) {
            if (train_starttime != null) {
                SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
                this.trainStartTime = dateSdf.format(this.train_starttime);
            } else {
                this.trainStartTime = "";
            }
        }

        return this.trainStartTime;
    }

    public String getTrainEndTime() {
        if (this.trainEndTime == null) {
            if (train_endtime != null) {
                SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
                this.trainEndTime = dateSdf.format(this.train_endtime);
            } else {
                this.trainEndTime = "";
            }
        }

        return this.trainEndTime;
    }

    public void setTrainStartTime(String trainStartTime) {
        this.trainStartTime = trainStartTime;
    }

    public void setTrainEndTime(String trainEndTime) {
        this.trainEndTime = trainEndTime;
    }

    public Integer getCv_set_id() {
        return cv_set_id;
    }

    public void setCv_set_id(Integer cv_set_id) {
        this.cv_set_id = cv_set_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Integer getPeople_number() {
        return people_number;
    }

    public void setPeople_number(Integer people_number) {
        this.people_number = people_number;
    }

    public String getTrain_place() {
        return train_place;
    }

    public void setTrain_place(String train_place) {
        this.train_place = train_place;
    }

    public Date getTrain_starttime() {
        return train_starttime;
    }

    public void setTrain_starttime(Date train_starttime) {
        this.train_starttime = train_starttime;
    }

    public Date getTrain_endtime() {
        return train_endtime;
    }

    public void setTrain_endtime(Date train_endtime) {
        this.train_endtime = train_endtime;
    }

    public String getContact_way() {
        return contact_way;
    }

    public void setContact_way(String contact_way) {
        this.contact_way = contact_way;
    }

    public Integer getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(Integer sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public String getLession_starttime() {
        return lession_starttime;
    }

    public void setLession_starttime(String lession_starttime) {
        this.lession_starttime = lession_starttime;
    }

    public String getLession_endtime() {
        return lession_endtime;
    }

    public void setLession_endtime(String lession_endtime) {
        this.lession_endtime = lession_endtime;
    }

    public String getRoute_way() {
        return route_way;
    }

    public void setRoute_way(String route_way) {
        this.route_way = route_way;
    }

}
