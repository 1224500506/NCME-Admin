package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

public class CVSchedule extends CV implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5671424071861687927L;
	
	private Long schedule_id;
	
	private Date start_date;
	
	private Date end_date;

	public CVSchedule() {}
	
	public CVSchedule(CV cv) {
		super(cv);
	}
	
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Long getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(Long schedule_id) {
		this.schedule_id = schedule_id;
	}
	
	

}
