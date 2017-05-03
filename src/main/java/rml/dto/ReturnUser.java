package rml.dto;

public class ReturnUser {
	private int serverStatus;

    private int errorCode;

    private String returnMessage;

    private String openId;
    
    private String sessionKey;

	public int getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(int serverStatus) {
		this.serverStatus = serverStatus;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
    
    
}
