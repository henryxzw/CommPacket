package com.comm.commpacket.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.comm.commpacket.callback.HttpResultCallBack;
import com.google.gson.Gson;
import com.google.gson.internal.Excluder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by apple on 16/7/18.
 */

public class HttpUtil<T> implements Callback {


    private static OkHttpClient okHttpClient = new OkHttpClient();
    private HttpResultCallBack<T> httpResultCallBack;
    private Class<T> clz;
    private NetWorkParamsIndex paramsIndex;

    public void Get()
    {}

    public void Post(Request request,NetWorkParamsIndex paramsIndex)
    {
        this.paramsIndex = paramsIndex;
       okHttpClient.newCall(request).enqueue(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if (httpResultCallBack != null) {
            httpResultCallBack.onFailure(e.getMessage(), paramsIndex);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
       if(response.isSuccessful())
       {
           ParseJson(response.body().string());
       }
        else
       {
           if(httpResultCallBack!=null)
           {
               httpResultCallBack.onFailure(response.message(),paramsIndex);
           }
       }
    }

    public HttpUtil setCallBack(HttpResultCallBack<T> httpResultCallBack)
    {
        this.httpResultCallBack = httpResultCallBack;
        return this;
    }

    public HttpUtil setClz(Class<T> clz)
    {
        this.clz = clz;
        return this;
    }

    private void ParseJson(String data)
    {
        Message message = new Message();
        try {
            Gson gson = new Gson();
            T t = gson.fromJson(data, clz);
            message.what = 0;
            message.obj = t;
            handler.sendMessage(message);
        }
        catch (Exception e)
        {
            message.what = 1;
            message.obj = e.getMessage();
            handler.sendMessage(message);
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0) {
                T t = (T) msg.obj;
                if (httpResultCallBack != null) {
                    httpResultCallBack.onResponse(t, paramsIndex);
                }
            }
            else
            {
                if (httpResultCallBack != null) {
                    httpResultCallBack.onFailure((String)msg.obj, paramsIndex);
                }
            }
        }
    };
}
