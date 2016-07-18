package com.comm.commpacket.bean;

/**
 * Created by apple on 16/7/18.
 */

public class BaseBean {


    /**
     * errorcode : 1
     * msg : 手机号必须
     */

    private int errorcode;
    private String msg;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
