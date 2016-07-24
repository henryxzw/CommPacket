package com.comm.commpacket.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comm.commpacket.callback.HttpResultCallBack;
import com.comm.commpacket.network.NetWorkModel;
import com.comm.commpacket.network.NetWorkParamsIndex;

/**
 * Created by apple on 16/7/8.
 */

public abstract class BaseFragment<T> extends Fragment implements HttpResultCallBack<T>{

    protected ViewDataBinding viewDataBinding;
    protected View parentView;
    protected NetWorkModel netWorkModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater,getContentView(),container,false);
        parentView = viewDataBinding.getRoot();
        netWorkModel = new NetWorkModel();
        netWorkModel.setCallBack(this);
        InitView();
        InitEvent();
        InitData();
        InitListener();
        return parentView;
    }

    protected abstract void InitView();
    protected abstract void InitEvent();
    protected abstract void InitData();
    protected abstract void InitListener();
    protected abstract int  getContentView();
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
