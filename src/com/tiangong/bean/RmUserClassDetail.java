package com.tiangong.bean;

import java.sql.Timestamp;

/**
 * RmUserClassDetail entity. @author MyEclipse Persistence Tools
 */

public class RmUserClassDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer schoolId;
	private Integer teacherId;
	private String enterYear;
	private String classStage;
	private String classNumber;
	private Short classTotalMember;
	private String isSend;
	private String invitationCode;
	private String addUser;
	private Timestamp addTime;
	private String updUser;
	private Timestamp updTime;

	// Constructors

	/** default constructor */
	public RmUserClassDetail() {
	}

	/** minimal constructor */
	public RmUserClassDetail(Integer schoolId, Integer teacherId,
			String enterYear, String classStage, String classNumber,
			Short classTotalMember, String isSend, String addUser,
			Timestamp addTime, String updUser, Timestamp updTime) {
		this.schoolId = schoolId;
		this.teacherId = teacherId;
		this.enterYear = enterYear;
		this.classStage = classStage;
		this.classNumber = classNumber;
		this.classTotalMember = classTotalMember;
		this.isSend = isSend;
		this.addUser = addUser;
		this.addTime = addTime;
		this.updUser = updUser;
		this.updTime = updTime;
	}

	/** full constructor */
	public RmUserClassDetail(Integer schoolId, Integer teacherId,
			String enterYear, String classStage, String classNumber,
			Short classTotalMember, String isSend, String invitationCode,
			String addUser, Timestamp addTime, String updUser, Timestamp updTime) {
		this.schoolId = schoolId;
		this.teacherId = teacherId;
		this.enterYear = enterYear;
		this.classStage = classStage;
		this.classNumber = classNumber;
		this.classTotalMember = classTotalMember;
		this.isSend = isSend;
		this.invitationCode = invitationCode;
		this.addUser = addUser;
		this.addTime = addTime;
		this.updUser = updUser;
		this.updTime = updTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getEnterYear() {
		return this.enterYear;
	}

	public void setEnterYear(String enterYear) {
		this.enterYear = enterYear;
	}

	public String getClassStage() {
		return this.classStage;
	}

	public void setClassStage(String classStage) {
		this.classStage = classStage;
	}

	public String getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public Short getClassTotalMember() {
		return this.classTotalMember;
	}

	public void setClassTotalMember(Short classTotalMember) {
		this.classTotalMember = classTotalMember;
	}

	public String getIsSend() {
		return this.isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getInvitationCode() {
		return this.invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
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