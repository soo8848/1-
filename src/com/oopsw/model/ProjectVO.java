package com.oopsw.model;

import java.util.Date;

public class ProjectVO {
	
	private int projectNo;
	private String employeeId;
	private String projectName;
	private String client;
	private Date startDate;
	private Date endDate;
	private Date inDate;
	private Date upDate;
	private String description;
	private Date delFlag;
	
	public ProjectVO(){}
	public ProjectVO(int projectNo, String employeeId, String projectName, String client, Date startDate, Date endDate,
			Date inDate, Date upDate, String description, Date delFlag) {
		setProjectName(projectName);
		setEmployeeId(employeeId);
		setProjectName(projectName);
		setClient(client);
		setStartDate(startDate);
		setEndDate(endDate);
		setInDate(inDate);
		setUpDate(upDate);
		setDescription(description);
		setDelFlag(delFlag);
	}

	public int getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Date delFlag) {
		this.delFlag = delFlag;
	}
	@Override
	public String toString() {
		return "ProjectVO [projectNo=" + projectNo + ", employeeId=" + employeeId + ", projectName=" + projectName
				+ ", client=" + client + ", startDate=" + startDate + ", endDate=" + endDate + ", inDate=" + inDate
				+ ", upDate=" + upDate + ", description=" + description + ", delFlag=" + delFlag + "]";
	}
	
	
	
	
}
