package com.tiangong.bean;

/**
 * RmWxDictionary entity. @author MyEclipse Persistence Tools
 */

public class RmWxDictionary implements java.io.Serializable {

	// Fields

	private String ditctId;
	private String dictName;
	private String dictKeyName;
	private String dictKeyValue;
	private Boolean dictStatus;
	private String dictDesc;
	private String sortNum;
	private Integer currentPage;
	private Integer pageSize;

	// Constructors

	/** default constructor */
	public RmWxDictionary() {
	}

	/** full constructor */
	public RmWxDictionary(String dictName, String dictKeyName,
			String dictKeyValue, Boolean dictStatus, String dictDesc) {
		this.dictName = dictName;
		this.dictKeyName = dictKeyName;
		this.dictKeyValue = dictKeyValue;
		this.dictStatus = dictStatus;
		this.dictDesc = dictDesc;
	}

	// Property accessors

	
	public String getDitctId() {
		return this.ditctId;
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

	public void setDitctId(String ditctId) {
		this.ditctId = ditctId;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictKeyName() {
		return this.dictKeyName;
	}

	public void setDictKeyName(String dictKeyName) {
		this.dictKeyName = dictKeyName;
	}

	public String getDictKeyValue() {
		return this.dictKeyValue;
	}

	public void setDictKeyValue(String dictKeyValue) {
		this.dictKeyValue = dictKeyValue;
	}

	public Boolean getDictStatus() {
		return this.dictStatus;
	}

	public void setDictStatus(Boolean dictStatus) {
		this.dictStatus = dictStatus;
	}

	public String getDictDesc() {
		return this.dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

}