package rml.request;

import java.io.Serializable;


public class UserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675547126499250212L;

	private String mobile;

	private String loginType;

	private String pwd;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

}
