package cn.edu.hytc.schoolhelper.http;

import java.io.Serializable;

/**
 * Created by zhusheng on 2017/8/9.
 */

public class HttpResult<T> implements Serializable {

    /**
     *  "status": 0,
     "msg": "加载可显示的顶级分类成功",
     "data": [
     */
    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
