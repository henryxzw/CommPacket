package com.comm.commpacket.network;

import com.comm.commpacket.callback.HttpResultCallBack;
import com.google.common.net.MediaType;
import com.google.repacked.antlr.v4.codegen.model.chunk.ThisRulePropertyRef_ctx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by apple on 16/7/18.
 */

public class HttpBuilder {

    public static class POST{
        private String url;
        private Class clz;
        private HttpResultCallBack callBack;
        private RequestBody requestBody;

        public POST url(String url)
        {
            this.url = url;
            return this;
        }

        public POST params(HashMap<String,String> params)
        {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for(Map.Entry<String,String> entry : params.entrySet())
            {
                builder.addFormDataPart(entry.getKey(),entry.getValue());
            }
            requestBody = builder.build();
            return this;
        }

        public POST params(HashMap<String,String> params, List<List<String>> files,String... keys)
        {
            return this;
        }

        public POST clz(Class clz)
        {
            this.clz = clz;
            return this;
        }

        public POST callBack(HttpResultCallBack callBack)
        {
            this.callBack = callBack;
            return this;
        }

        public void run(NetWorkParamsIndex paramsIndex)
        {
            Request request = new Request.Builder().url(url).post(requestBody).build();
            new HttpUtil().setClz(clz).setCallBack(callBack).Post(request,paramsIndex);
        }

    }

    public static class GET{}
}
