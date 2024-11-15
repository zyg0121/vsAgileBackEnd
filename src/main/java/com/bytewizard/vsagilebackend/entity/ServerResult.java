package com.bytewizard.vsagilebackend.entity;
//控制层返给浏览器的对象
public class ServerResult {
    Integer state;//状态码 0：成功
    String msg;//队状态码的描述
    Object data;//返回的数据

    //有参的构造方法


    public ServerResult(Integer state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}