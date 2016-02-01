package com.tiangong.bean;

/**
 * Rolemodule entity. @author MyEclipse Persistence Tools
 */

public class Rolemodule implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private String functionIds;

	// Constructors

	/** default constructor */
	public Rolemodule() {
	}

	/** full constructor */
	public Rolemodule(String roleId, String functionIds) {
		this.roleId = roleId;
		this.functionIds = functionIds;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFunctionIds() {
		return this.functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

}