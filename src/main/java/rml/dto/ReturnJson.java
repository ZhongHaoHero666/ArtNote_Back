package rml.dto;

/**
 * Created by Administrator on 2015/9/12.
 */
public class ReturnJson {
    private int serverStatus;

    private int errorCode;

    private String returnMessage;

    private String returnValue;

    private Object returnObject;

    public Object getReturnObject() {
        return returnObject;
    }

    public ReturnJson setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
        return this;
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

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    @Override
    public String toString() {
        return " {errorCode:" + errorCode + ", returnObject=" + returnObject + ", returnMessage=" + returnMessage + ", returnValue=" + returnValue
                + ", serverStatus =" + serverStatus + "}";
    }

}
