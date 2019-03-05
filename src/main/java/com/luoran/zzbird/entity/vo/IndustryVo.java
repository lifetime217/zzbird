package com.luoran.zzbird.entity.vo;

public class IndustryVo {

	private Integer id;
	private String iName;
	private Integer pId;
	private String pName;
	
	
	public IndustryVo() {
		super();
	}
	public IndustryVo(Integer id, String iName, Integer pId, String pName) {
		super();
		this.id = id;
		this.iName = iName;
		this.pId = pId;
		this.pName = pName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	

}
