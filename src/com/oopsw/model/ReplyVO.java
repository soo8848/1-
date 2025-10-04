package com.oopsw.model;

import java.util.Date;

public class ReplyVO {
	private int replyNo;
	private String contents;
	private Date in_date;
	private Date up_date;
	private String file_name;
	private String file_path;
	private Date upload_date;
	private Date del_date;
	
	
	public ReplyVO() {}
	public ReplyVO(int replyNo, String contents, Date in_date, Date up_date, String file_name, String file_path,
			Date upload_date, Date del_date) {
		setReplyNo(replyNo);
		setContents(contents);
		setIn_date(in_date);
		setUp_date(up_date);
		setFile_name(file_name);
		setFile_path(file_path);
		setUpload_date(upload_date);
		setDel_date(del_date);
	}
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public Date getDel_date() {
		return del_date;
	}
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	@Override
	public String toString() {
		return "replyVO [replyNo=" + replyNo + ", contents=" + contents + ", in_date=" + in_date + ", up_date="
				+ up_date + ", file_name=" + file_name + ", file_path=" + file_path + ", upload_date=" + upload_date
				+ ", del_date=" + del_date + "]";
	}
	
	
}
