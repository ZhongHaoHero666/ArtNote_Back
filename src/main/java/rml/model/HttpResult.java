package rml.model;

/**
 * Created by BBLink on 2017/5/3.
 */
public class HttpResult<T>{
    private int code;
    private String state;
    private T data;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
