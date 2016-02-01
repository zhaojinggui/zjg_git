package com.tiangong.bean;

import java.util.Date;


/**
 * RmActivityCardRecord entity. @author MyEclipse Persistence Tools
 */

public class RmActivityCardRecord extends PubBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String mobile;
	private String cardNo;
	private Date addDate;
	private String state;
	private String wxOpenid;

	// Constructors

	/** default constructor */
	public RmActivityCardRecord() {
	}

	/** full constructor */
	public RmActivityCardRecord(String name, String mobile, String cardNo,
			Date addDate, String state, String wxOpenid) {
		this.name = name;
		this.mobile = mobile;
		this.cardNo = cardNo;
		this.addDate = addDate;
		this.state = state;
		this.wxOpenid = wxOpenid;
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

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWxOpenid() {
		return this.wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

}