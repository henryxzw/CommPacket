package com.comm.commpacket.network;

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

}
