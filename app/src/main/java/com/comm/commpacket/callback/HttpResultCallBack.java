package com.comm.commpacket.callback;

/**
 * Created by apple on 16/7/14.
 */

public interface HttpResultCallBack<T> {
    void onSuccess(T t,int resultCode);
    void onResponse(T t,int resultCode);
    void onFailure(String data,int resultCode);
}
