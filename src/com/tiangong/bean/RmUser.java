package com.tiangong.bean;

import java.sql.Timestamp;

/**
 * RmUser entity. @author MyEclipse Persistence Tools
 */

public class RmUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String password;
	private String email;
	private String mobile;
	private String qq;
	private String wxOpenCommId;
	private String wxOpenJzId;
	private String qqOpenId;
	private String moodleToken;
	private String role;
	private Timestamp signinTime;
	private String isUse;
	private String isFirst;
	private String addUser;
	private Timestamp addTime;
	private String updUser;
	private Timestamp updTime;

	// Constructors

	/** default constructor */
	public RmUser() {
	}

	/** minimal constructor */
	public RmUser(String name, String password, String role,
			Timestamp signinTime, String isUse, String addUser,
			Timestamp addTime, String updUser, Timestamp updTime) {
		this.name = name;
		this.password = password;
		this.role = role;
		this.signinTime = signinTime;
		this.isUse = isUse;
		this.addUser = addUser;
		this.addTime = addTime;
		this.updUser = updUser;
		this.updTime = updTime;
	}

	/** full constructor */
	public RmUser(String name, String password, String email, String mobile,
			String qq, String wxOpenCommId, String wxOpenJzId, String qqOpenId,
			String moodleToken, String role, Timestamp signinTime,
			String isUse, String isFirst, String addUser, Timestamp addTime,
			String updUser, Timestamp updTime) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.qq = qq;
		this.wxOpenCommId = wxOpenCommId;
		this.wxOpenJzId = wxOpenJzId;
		this.qqOpenId = qqOpenId;
		this.moodleToken = moodleToken;
		this.role = role;
		this.signinTime = signinTime;
		this.isUse = isUse;
		this.isFirst = isFirst;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWxOpenCommId() {
		return this.wxOpenCommId;
	}

	public void setWxOpenCommId(String wxOpenCommId) {
		this.wxOpenCommId = wxOpenCommId;
	}

	public String getWxOpenJzId() {
		return this.wxOpenJzId;
	}

	public void setWxOpenJzId(String wxOpenJzId) {
		this.wxOpenJzId = wxOpenJzId;
	}

	public String getQqOpenId() {
		return this.qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getMoodleToken() {
		return this.moodleToken;
	}

	public void setMoodleToken(String moodleToken) {
		this.moodleToken = moodleToken;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getSigninTime() {
		return this.signinTime;
	}

	public void setSigninTime(Timestamp signinTime) {
		this.signinTime = signinTime;
	}

	public String getIsUse() {
		return this.isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getIsFirst() {
		return this.isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
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