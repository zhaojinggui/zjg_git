package com.tiangong.bean;

import java.util.Date;

/**
 * RmActivityUser entity. @author MyEclipse Persistence Tools
 */

public class RmActivityUser extends PubBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String mobile;
	private String school;
	private String teacherNo;
	private String address;
	private Date addDate;
	private String wxOpenId;
	private Integer currentPage;
	private Integer pageSize;

	private Integer count;
	// Constructors

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/** default constructor */
	public RmActivityUser() {
	}

	/** minimal constructor */
	public RmActivityUser(String name, String mobile, Date addDate,
			String wxOpenId) {
		this.name = name;
		this.mobile = mobile;
		this.addDate = addDate;
		this.wxOpenId = wxOpenId;
	}

	/** full constructor */
	public RmActivityUser(String name, String mobile, String school,
			String teacherNo, String address, Date addDate, String wxOpenId) {
		this.name = name;
		this.mobile = mobile;
		this.school = school;
		this.teacherNo = teacherNo;
		this.address = address;
		this.addDate = addDate;
		this.wxOpenId = wxOpenId;
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

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTeacherNo() {
		return this.teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getWxOpenId() {
		return this.wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
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

	
}