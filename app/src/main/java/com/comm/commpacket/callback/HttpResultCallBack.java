package com.comm.commpacket.callback;

import com.comm.commpacket.network.NetWorkParamsIndex;

/**
 * Created by apple on 16/7/14.
 */

public interface HttpResultCallBack<T> {
    void onResponse(T t,NetWorkParamsIndex resultCode);
    void onFailure(String data,NetWorkParamsIndex resultCode);
}
