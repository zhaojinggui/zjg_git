package com.tiangong.bean;

import java.sql.Timestamp;

/**
 * RmUserClassList entity. @author MyEclipse Persistence Tools
 */

public class RmUserClassList implements java.io.Serializable {

	// Fields

	private String studentId;
	private Integer classId;
	private Integer studentUserId;
	private String approvalStatus;
	private String studentNumber;
	private String addUser;
	private Timestamp addTime;
	private String updUser;
	private Timestamp updTime;

	// Constructors
	
	
	//级别
	private String level;
	
	//学生用户名称
	private String stuUserName;

	public String getStuUserName() {
		return stuUserName;
	}

	public void setStuUserName(String stuUserName) {
		this.stuUserName = stuUserName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/** default constructor */
	public RmUserClassList() {
	}

	/** minimal constructor */
	public RmUserClassList(Integer classId, String approvalStatus,
			String addUser, Timestamp addTime, String updUser, Timestamp updTime) {
		this.classId = classId;
		this.approvalStatus = approvalStatus;
		this.addUser = addUser;
		this.addTime = addTime;
		this.updUser = updUser;
		this.updTime = updTime;
	}

	/** full constructor */
	public RmUserClassList(Integer classId, Integer studentUserId,
			String approvalStatus, String studentNumber, String addUser,
			Timestamp addTime, String updUser, Timestamp updTime) {
		this.classId = classId;
		this.studentUserId = studentUserId;
		this.approvalStatus = approvalStatus;
		this.studentNumber = studentNumber;
		this.addUser = addUser;
		this.addTime = addTime;
		this.updUser = updUser;
		this.updTime = updTime;
	}

	// Property accessors

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getStudentUserId() {
		return this.studentUserId;
	}

	public void setStudentUserId(Integer studentUserId) {
		this.studentUserId = studentUserId;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getStudentNumber() {
		return this.studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public Timestamp getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Timestamp updTime) {
		this.updTime = updTime;
	}

}