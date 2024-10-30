package ir.moke.sdk.coinex.model;

import java.util.List;

public class Response<T> {
    private Integer code ;
    private List<T> data;

    public Response() {
    }

    public Response(Integer code, List<T> data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
