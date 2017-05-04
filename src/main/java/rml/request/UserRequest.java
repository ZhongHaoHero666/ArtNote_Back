package rml.request;

import java.io.Serializable;


public class UserRequest implements Serializable {
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String userId;

	private String mobile;

	private String IMeiId;

	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIMeiId() {
		return IMeiId;
	}

	public void setIMeiId(String IMeiId) {
		this.IMeiId = IMeiId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
