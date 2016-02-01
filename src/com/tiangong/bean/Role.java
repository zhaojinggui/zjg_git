package com.tiangong.bean;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String remark;
	private Integer currentPage;
	private Integer pageSize;

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String name, String remark) {
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}