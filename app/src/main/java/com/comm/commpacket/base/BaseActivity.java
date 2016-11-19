package com.comm.commpacket.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.comm.commpacket.callback.HttpResultCallBack;
import com.comm.commpacket.network.NetWorkModel;
import com.comm.commpacket.network.NetWorkParamsIndex;

import static android.os.Build.VERSION.SDK_INT;

/**
 * Created by apple on 16/7/8.
 */

public abstract class BaseActivity<T> extends AppCompatActivity implements HttpResultCallBack<T>{

    protected ViewDataBinding viewDataBinding;
    protected View parentView;
    protected Context context;
    protected NetWorkModel netWorkModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int resId = getContentView();
        this.context = this;
        viewDataBinding = DataBindingUtil.setContentView(this,resId);
        parentView = viewDataBinding.getRoot();

        if(SDK_INT<=Build.VERSION_CODES.KITKAT) {
            this.getWindow().getDecorView().setFitsSystemWindows(true);
        }

        netWorkModel = new NetWorkModel();
        netWorkModel.setCallBack(this);

        InitView();
        InitEvent();
        InitListener();
        InitData();
    }

    @Override
    protected void onResume() {
        Resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Destroy();
        super.onDestroy();
    }


    protected abstract void InitView();
    protected abstract void InitEvent();
    protected abstract void InitData();
    protected abstract void InitListener();
    protected abstract void Resume();
    protected abstract void Pause();
    protected abstract void Destroy();
    protected abstract int getContentView();
    protected abstract void OnSuccessResponse(T t,NetWorkParamsIndex resultCode);
    protected abstract void OnFailureResponse(String msg,NetWorkParamsIndex resultCode);

    @Override
    public void onResponse(T t, NetWorkParamsIndex resultCode) {
         OnSuccessResponse(t,resultCode);
    }

    @Override
    public void onFailure(String data, NetWorkParamsIndex resultCode) {
        OnFailureResponse(data,resultCode);
    }
}
