package com.luoran.zzbird.entity.vo;

public class CourseUserVo {

	private String name;
	private String headImg;

	public CourseUserVo() {
		super();
	}

	public CourseUserVo(String name, String headImg) {
		super();
		this.name = name;
		this.headImg = headImg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
