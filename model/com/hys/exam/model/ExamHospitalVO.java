package com.hys.exam.model;

import java.io.Serializable;

/**
 * ExamHospital vo
 *
 * @author Han
 */
public class ExamHospitalVO implements Serializable {


    private Long id;
    private String title;
    private String cityName;
    private String provName;
    private String cityID;
    private String provID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getProvID() {
        return provID;
    }

    public void setProvID(String provID) {
        this.provID = provID;
    }
}
