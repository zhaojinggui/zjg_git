package com.tiangong.bean;

import java.sql.Timestamp;

/**
 * RmUserDetail entity. @author MyEclipse Persistence Tools
 */

public class RmUserDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private String realname;
	private String gender;
	private String age;
	private String school;
	private String studyYears;
	private String provinceId;
	private String cityId;
	private String isSendMail;
	private String isSendMessage;
	private String parentMail;
	private String parentMobile;
	private Timestamp validatyDate;
	private Integer salerUserId;
	private String showBookshelfHelp;
	private String showRechargeReminderMonth;
	private String showRechargeReminderHalfMonth;
	private String showRechargeReminderWeek;
	private String showRechargeReminder3day;
	private String showRechargeReminderDay;
	private String showMyTask;
	private String showRecommendedLevel;
	private Long schoolId;
	private String picName;

	// Constructors

	/** default constructor */
	public RmUserDetail() {
	}

	/** minimal constructor */
	public RmUserDetail(Timestamp validatyDate, String showMyTask) {
		this.validatyDate = validatyDate;
		this.showMyTask = showMyTask;
	}

	/** full constructor */
	public RmUserDetail(String realname, String gender, String age,
			String school, String studyYears, String provinceId, String cityId,
			String isSendMail, String isSendMessage, String parentMail,
			String parentMobile, Timestamp validatyDate, Integer salerUserId,
			String showBookshelfHelp, String showRechargeReminderMonth,
			String showRechargeReminderHalfMonth,
			String showRechargeReminderWeek, String showRechargeReminder3day,
			String showRechargeReminderDay, String showMyTask,
			String showRecommendedLevel, Long schoolId, String picName) {
		this.realname = realname;
		this.gender = gender;
		this.age = age;
		this.school = school;
		this.studyYears = studyYears;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.isSendMail = isSendMail;
		this.isSendMessage = isSendMessage;
		this.parentMail = parentMail;
		this.parentMobile = parentMobile;
		this.validatyDate = validatyDate;
		this.salerUserId = salerUserId;
		this.showBookshelfHelp = showBookshelfHelp;
		this.showRechargeReminderMonth = showRechargeReminderMonth;
		this.showRechargeReminderHalfMonth = showRechargeReminderHalfMonth;
		this.showRechargeReminderWeek = showRechargeReminderWeek;
		this.showRechargeReminder3day = showRechargeReminder3day;
		this.showRechargeReminderDay = showRechargeReminderDay;
		this.showMyTask = showMyTask;
		this.showRecommendedLevel = showRecommendedLevel;
		this.schoolId = schoolId;
		this.picName = picName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStudyYears() {
		return this.studyYears;
	}

	public void setStudyYears(String studyYears) {
		this.studyYears = studyYears;
	}

	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getIsSendMail() {
		return this.isSendMail;
	}

	public void setIsSendMail(String isSendMail) {
		this.isSendMail = isSendMail;
	}

	public String getIsSendMessage() {
		return this.isSendMessage;
	}

	public void setIsSendMessage(String isSendMessage) {
		this.isSendMessage = isSendMessage;
	}

	public String getParentMail() {
		return this.parentMail;
	}

	public void setParentMail(String parentMail) {
		this.parentMail = parentMail;
	}

	public String getParentMobile() {
		return this.parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public Timestamp getValidatyDate() {
		return this.validatyDate;
	}

	public void setValidatyDate(Timestamp validatyDate) {
		this.validatyDate = validatyDate;
	}

	public Integer getSalerUserId() {
		return this.salerUserId;
	}

	public void setSalerUserId(Integer salerUserId) {
		this.salerUserId = salerUserId;
	}

	public String getShowBookshelfHelp() {
		return this.showBookshelfHelp;
	}

	public void setShowBookshelfHelp(String showBookshelfHelp) {
		this.showBookshelfHelp = showBookshelfHelp;
	}

	public String getShowRechargeReminderMonth() {
		return this.showRechargeReminderMonth;
	}

	public void setShowRechargeReminderMonth(String showRechargeReminderMonth) {
		this.showRechargeReminderMonth = showRechargeReminderMonth;
	}

	public String getShowRechargeReminderHalfMonth() {
		return this.showRechargeReminderHalfMonth;
	}

	public void setShowRechargeReminderHalfMonth(
			String showRechargeReminderHalfMonth) {
		this.showRechargeReminderHalfMonth = showRechargeReminderHalfMonth;
	}

	public String getShowRechargeReminderWeek() {
		return this.showRechargeReminderWeek;
	}

	public void setShowRechargeReminderWeek(String showRechargeReminderWeek) {
		this.showRechargeReminderWeek = showRechargeReminderWeek;
	}

	public String getShowRechargeReminder3day() {
		return this.showRechargeReminder3day;
	}

	public void setShowRechargeReminder3day(String showRechargeReminder3day) {
		this.showRechargeReminder3day = showRechargeReminder3day;
	}

	public String getShowRechargeReminderDay() {
		return this.showRechargeReminderDay;
	}

	public void setShowRechargeReminderDay(String showRechargeReminderDay) {
		this.showRechargeReminderDay = showRechargeReminderDay;
	}

	public String getShowMyTask() {
		return this.showMyTask;
	}

	public void setShowMyTask(String showMyTask) {
		this.showMyTask = showMyTask;
	}

	public String getShowRecommendedLevel() {
		return this.showRecommendedLevel;
	}

	public void setShowRecommendedLevel(String showRecommendedLevel) {
		this.showRecommendedLevel = showRecommendedLevel;
	}

	public Long getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

}