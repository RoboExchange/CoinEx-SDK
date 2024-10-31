package ir.moke.coinex.model;

import ir.moke.kafir.utils.JsonUtils;

public class Response<T> {
    private Integer code;
    private T data;
    private String message;

    public Response() {
    }

    public Response(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
