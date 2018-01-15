package com.hys.exam.model;

import java.sql.Date;


public class CVSetAuthorization {
    private Long id;

    private String name;

    private Long cvSetId;

	private String cvSetSerialNumber;

    private Integer cvSetLevel;

    private Double cvSetScore;

    private Integer cvSetCostType;

    private Double cvSetCost;

    private String cvSetSign;

    private Date cvSetStartDate;

    private Date cvSetEndDate;

    private Integer cvSetRegistrationStop;

    private Integer cvSetBreakDays;
    
    private java.util.Date modify_date;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCvSetId() {
		return cvSetId;
	}
	
	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCvSetSerialNumber() {
        return cvSetSerialNumber;
    }

    public void setCvSetSerialNumber(String cvSetSerialNumber) {
        this.cvSetSerialNumber = cvSetSerialNumber == null ? null : cvSetSerialNumber.trim();
    }

    public Integer getCvSetLevel() {
        return cvSetLevel;
    }

    public void setCvSetLevel(Integer cvSetLevel) {
        this.cvSetLevel = cvSetLevel;
    }

    public Double getCvSetScore() {
        return cvSetScore;
    }

    public void setCvSetScore(Double cvSetScore) {
        this.cvSetScore = cvSetScore;
    }

    public Integer getCvSetCostType() {
        return cvSetCostType;
    }

    public void setCvSetCostType(Integer cvSetCostType) {
        this.cvSetCostType = cvSetCostType;
    }

    public Double getCvSetCost() {
        return cvSetCost;
    }

    public void setCvSetCost(Double cvSetCost) {
        this.cvSetCost = cvSetCost;
    }

    public String getCvSetSign() {
        return cvSetSign;
    }

    public void setCvSetSign(String cvSetSign) {
        this.cvSetSign = cvSetSign == null ? null : cvSetSign.trim();
    }

    public Date getCvSetStartDate() {
        return cvSetStartDate;
    }

    public void setCvSetStartDate(Date cvSetStartDate) {
        this.cvSetStartDate = cvSetStartDate;
    }

    public Date getCvSetEndDate() {
        return cvSetEndDate;
    }

    public void setCvSetEndDate(Date cvSetEndDate) {
        this.cvSetEndDate = cvSetEndDate;
    }

    public Integer getCvSetRegistrationStop() {
        return cvSetRegistrationStop;
    }

    public void setCvSetRegistrationStop(Integer cvSetRegistrationStop) {
        this.cvSetRegistrationStop = cvSetRegistrationStop;
    }

    public Integer getCvSetBreakDays() {
        return cvSetBreakDays;
    }

    public void setCvSetBreakDays(Integer cvSetBreakDays) {
        this.cvSetBreakDays = cvSetBreakDays;
    }

	public java.util.Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(java.util.Date modify_date) {
		this.modify_date = modify_date;
	}

}