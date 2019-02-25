package com.luoran.zzbird.core;

/**
 * @author lifetime
 *
 */
public class UserContextInfo {
	private String id;
	private String number;
	private String pwd;
	private String alias;
	private String companyId;
	private String companyCode;
	private String companyName;
	private long loginTime;
	private String pwdMd5;
	private Integer userType;
	private String accessToken;
	private String ctxPath;

	long getLoginTime() {
		return loginTime;
	}

	void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	String getPwdMd5() {
		return pwdMd5;
	}

	void setPwdMd5(String pwdMd5) {
		this.pwdMd5 = pwdMd5;
	}

	String getAccessToken() {
		return accessToken;
	}

	void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getUserType() {
		return userType;
	}
	
	public void setUserType(Integer userType) {
		this.userType=userType;
	}
	
	public boolean isSupperAdmin(){
		return userType != null && userType == 99;
	}

	public boolean isAdmin(){
		return userType != null && userType == 9;
	}

	public String getCtxPath() {
		return ctxPath;
	}

	public void setCtxPath(String ctxPath) {
		this.ctxPath = ctxPath;
	}
	
}
