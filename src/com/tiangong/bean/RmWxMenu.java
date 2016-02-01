package com.tiangong.bean;

/**
 * RmWxMenu entity. @author MyEclipse Persistence Tools
 */

public class RmWxMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String pid;
	private String menuName;
	private String menuType;
	private String menuKey;
	private String menuUrl;
	private String meunMediaid;

	// Constructors

	/** default constructor */
	public RmWxMenu() {
	}

	/** full constructor */
	public RmWxMenu(String pid, String menuName, String menuType,
			String menuKey, String menuUrl, String meunMediaid) {
		this.pid = pid;
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuKey = menuKey;
		this.menuUrl = menuUrl;
		this.meunMediaid = meunMediaid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMeunMediaid() {
		return this.meunMediaid;
	}

	public void setMeunMediaid(String meunMediaid) {
		this.meunMediaid = meunMediaid;
	}

}