package com.hys.exam.model;

import java.io.Serializable;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnitVideo.java
 *     
 *     Description       :   视频学习记录实体类(单元)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-03-09              YHQ                 杨红强  Created
 ********************************************************************************
 */

public class LogStudyCVUnitVideo implements Serializable {
    private Long id ;
    private Long logStudyCvUnitId ;
    private Long systemUserId ;
    private Long cvId ;
    private Long cvUnitId ;
    private String startDate ;
    private String endDate ;
    private Long videoLength ;
    private Long videoPlayLength ;

    
    
    
    
    
    
    public LogStudyCVUnitVideo() {
		super();
	}

      
    
	public LogStudyCVUnitVideo(Long logStudyCvUnitId, Long systemUserId,
			String startDate, String endDate) {
		super();
		this.logStudyCvUnitId = logStudyCvUnitId;
		this.systemUserId = systemUserId;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogStudyCvUnitId() {
        return logStudyCvUnitId;
    }

    public void setLogStudyCvUnitId(Long logStudyCvUnitId) {
        this.logStudyCvUnitId = logStudyCvUnitId;
    }

    public Long getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Long systemUserId) {
        this.systemUserId = systemUserId;
    }

    public Long getCvId() {
        return cvId;
    }

    public void setCvId(Long cvId) {
        this.cvId = cvId;
    }

    public Long getCvUnitId() {
        return cvUnitId;
    }

    public void setCvUnitId(Long cvUnitId) {
        this.cvUnitId = cvUnitId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(Long videoLength) {
        this.videoLength = videoLength;
    }

    public Long getVideoPlayLength() {
        return videoPlayLength;
    }

    public void setVideoPlayLength(Long videoPlayLength) {
        this.videoPlayLength = videoPlayLength;
    }
}
