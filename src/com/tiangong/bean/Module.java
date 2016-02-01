package com.tiangong.bean;

/**
 * Module entity. @author MyEclipse Persistence Tools
 */

public class Module implements java.io.Serializable {

	// Fields

	private String id;
	private String pid;
	private String text;
	private String code;
	private String isPid;

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** full constructor */
	public Module(String pid, String text, String code, String isPid) {
		this.pid = pid;
		this.text = text;
		this.code = code;
		this.isPid = isPid;
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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsPid() {
		return this.isPid;
	}

	public void setIsPid(String isPid) {
		this.isPid = isPid;
	}

}