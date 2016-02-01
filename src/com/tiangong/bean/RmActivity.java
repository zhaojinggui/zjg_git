package com.tiangong.bean;

import java.sql.Timestamp;

/**
 * RmActivity entity. @author MyEclipse Persistence Tools
 */

public class RmActivity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityId;
	private String activityName;
	private String briefDescription;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer userId;
	private String addUser;
	private Timestamp addDate;

	// Constructors

	/** default constructor */
	public RmActivity() {
	}

	/** full constructor */
	public RmActivity(String activityId, String activityName,
			String briefDescription, Timestamp startDate, Timestamp endDate,
			Integer userId, String addUser, Timestamp addDate) {
		this.activityId = activityId;
		this.activityName = activityName;
		this.briefDescription = briefDescription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.addUser = addUser;
		this.addDate = addDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getBriefDescription() {
		return this.briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

}