package com.tiangong.bean;

import java.util.Date;

/**
 * RmUser entity. @author MyEclipse Persistence Tools
 */

public class RmActivityYzYzm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String phone;
	private String code;

	// Constructors

	/** default constructor */
	public RmActivityYzYzm() {
	}

	/** minimal constructor */
	public RmActivityYzYzm(String phone,String code) {
		this.phone = phone;
		this.code = code;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}