package com.luoran.zzbird.entity.vo;

import java.sql.Date;

public class DakaDetailByMonthVo {
	private String monthDate;
	private String statDate;
	private String endDate;
	//private List<>
	public DakaDetailByMonthVo() {
		super();
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
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
