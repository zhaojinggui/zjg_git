package com.tiangong.bean;

import java.util.Date;

/**
 * RmUser entity. @author MyEclipse Persistence Tools
 */

public class RmActivityYzOrderRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tid;
	private Integer num;
	private Integer weixinUserId;
	private String phone;
	private String cardType;
	private String cardId;
	private String password;
	private String sendStatus;
	private Date sendDate;
	private Date payTime;
	private Date usableBefore;

	// Constructors

	/** default constructor */
	public RmActivityYzOrderRecord() {
	}

	/** minimal constructor */
	public RmActivityYzOrderRecord(String tid, Integer num,
			Integer weixinUserId, String phone, String cardType,
			String cardId, String password, String sendStatus
			,Date sendDate,Date payTime,Date usableBefore) {
		this.tid = tid;
		this.num = num;
		this.weixinUserId = weixinUserId;
		this.phone = phone;
		this.cardType = cardType;
		this.cardId = cardId;
		this.password = password;
		this.sendStatus = sendStatus;
		this.sendDate = sendDate;
		this.payTime = payTime;
		this.usableBefore = usableBefore;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getWeixinUserId() {
		return weixinUserId;
	}

	public void setWeixinUserId(Integer weixinUserId) {
		this.weixinUserId = weixinUserId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public Date getUsableBefore() {
		return usableBefore;
	}

	public void setUsableBefore(Date usableBefore) {
		this.usableBefore = usableBefore;
	}
}