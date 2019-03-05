package com.luoran.zzbird.entity.vo;

import java.sql.Date;

public class DakaDetailByMonthVo {
	private Date monthDate;
	private String statDate;
	private String endDate;
	//private List<>
	public DakaDetailByMonthVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(Date monthDate) {
		this.monthDate = monthDate;
	}
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "DakaDetailByMonthVo [monthDate=" + monthDate + ", statDate=" + statDate + ", endDate=" + endDate + "]";
	}
	
}
