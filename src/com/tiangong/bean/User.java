package com.tiangong.bean;

import java.util.Map;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String name;
	private String password;
	private String email;
	private String phone;
	private String jobtitle;
	private String remark;
	private Integer currentPage;
	private Integer pageSize;
	private Map functions;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userId, String name, String password, String email,
			String phone, String jobtitle, String remark) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.jobtitle = jobtitle;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJobtitle() {
		return this.jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Map getFunctions() {
		return functions;
	}

	public void setFunctions(Map functions) {
		this.functions = functions;
	}
	
	

}