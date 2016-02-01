package com.tiangong.bean;

/**
 * Userrole entity. @author MyEclipse Persistence Tools
 */

public class Userrole implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String roleId;

	// Constructors

	/** default constructor */
	public Userrole() {
	}

	/** full constructor */
	public Userrole(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}