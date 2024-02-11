package com.jl.train.common.resp;

public class CommonResp<T> {
//    提示消息
    private String message;
//    业务上的成功或者失败
    private boolean success = true;
//    泛型数据
    private T data;

    public CommonResp() {

    }

    public CommonResp(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
