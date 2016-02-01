package com.tiangong.bean;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class RmView implements java.io.Serializable {

	// Fields
	private Integer id;
	private String head;
	private String userName;	
	private String userRole;
	private String userAge;
	private String userYears;
	private String userNum;//坚果数
	private String userDay;//登录天数
	private String userLevel;//阅读级别
	private String picName; // 头像
	

	// Constructors

	/** default constructor */
	public RmView() {
	}
					
	public RmView(String head, String userName, String userRole,
			String userAge, String userYears, String userNum, String userDay,
			String userLevel,Integer id,String picName) {
		super();
		this.id = id;
		this.head = head;
		this.userName = userName;
		this.userRole = userRole;
		this.userAge = userAge;
		this.userYears = userYears;
		this.userNum = userNum;
		this.userDay = userDay;
		this.userLevel = userLevel;
		this.picName = picName;
	}






	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserYears() {
		return userYears;
	}

	public void setUserYears(String userYears) {
		this.userYears = userYears;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserDay() {
		return userDay;
	}

	public void setUserDay(String userDay) {
		this.userDay = userDay;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

}