package com.comm.commpacket.network;

import com.comm.commpacket.bean.BaseBean;
import com.comm.commpacket.callback.HttpResultCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apple on 16/7/14.
 */

public class NetWorkModel<T> {

    private HttpResultCallBack<T> callBack;


    private HashMap<String,String> params = new HashMap<>();


    private List<String> fileName = new ArrayList<>();
    private List<List<String>> filePaths = new ArrayList<>();


    public NetWorkModel()
    {

    }


    public void setCallBack(HttpResultCallBack<T> callBack) {
        this.callBack = callBack;
    }

    public void PostTest(String url,String name,NetWorkParamsIndex paramsIndex)
    {
        params.clear();
        params.put("test",name);
        new HttpBuilder.POST().params(params).url(url).clz(BaseBean.class).callBack(callBack).run(paramsIndex);
    }

}
