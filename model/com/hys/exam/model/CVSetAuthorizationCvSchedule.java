package com.hys.exam.model;

import java.sql.Date;


public class CVSetAuthorizationCvSchedule {
    private Long id;

    private Long authorizationId;

    private Long cvScheduleId;

    private Date startDate;

    private Date endDate;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
	}

	public Long getCvScheduleId() {
		return cvScheduleId;
	}

	public void setCvScheduleId(Long cvScheduleId) {
		this.cvScheduleId = cvScheduleId;
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
}