package com.dto;

public class appointment {
	
	private int appId;
	private String appDateTime;
	private String appVenue;
	private int appDoctorId;
	private int appPatientId;

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppDateTime() {
		return appDateTime;
	}

	public void setAppDateTime(String appDateTime) {
		this.appDateTime = appDateTime;
	}

	public String getAppVenue() {
		return appVenue;
	}

	public void setAppVenue(String appVenue) {
		this.appVenue = appVenue;
	}

	public int getAppDoctorId() {
		return appDoctorId;
	}

	public void setAppDoctorId(int appDoctorId) {
		this.appDoctorId = appDoctorId;
	}

	public int getAppPatientId() {
		return appPatientId;
	}

	public void setAppPatientId(int appPatientId) {
		this.appPatientId = appPatientId;
	}

}
