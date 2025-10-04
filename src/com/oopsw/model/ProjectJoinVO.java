package com.oopsw.model;

import java.util.Date;

public class ProjectJoinVO {
	
	private int projectJoinNo;
	private String employeeId;
	private int projectNo;
	private Date inDate;
	private Date upDate;
	private int joinFlag;
	
	
	public ProjectJoinVO(){}
	public ProjectJoinVO(int projectJoinNo, String employeeId, int projectNo, Date inDate, Date upDate, int joinFlag) {
		setProjectJoinNo(projectJoinNo);
		setEmployeeId(employeeId);
		setProjectNo(projectNo);
		setInDate(inDate);
		setUpDate(upDate);
		setJoinFlag(joinFlag);
	}
	public int getProjectJoinNo() {
		return projectJoinNo;
	}
	public void setProjectJoinNo(int projectJoinNo) {
		this.projectJoinNo = projectJoinNo;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
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
	public int getJoinFlag() {
		return joinFlag;
	}
	public void setJoinFlag(int joinFlag) {
		this.joinFlag = joinFlag;
	}
	@Override
	public String toString() {
		return "ProjectJoinVO [projectJoinNo=" + projectJoinNo + ", employeeId=" + employeeId + ", projectNo="
				+ projectNo + ", inDate=" + inDate + ", upDate=" + upDate + ", joinFlag=" + joinFlag + "]";
	}
	
	
	
}

