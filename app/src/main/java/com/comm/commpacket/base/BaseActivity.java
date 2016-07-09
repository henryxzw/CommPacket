package com.comm.commpacket.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import static android.os.Build.VERSION.SDK_INT;

/**
 * Created by apple on 16/7/8.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ViewDataBinding viewDataBinding;
    protected View parentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int resId = getContentView();
        viewDataBinding = DataBindingUtil.setContentView(this,resId);
        parentView = viewDataBinding.getRoot();

        if(SDK_INT<=Build.VERSION_CODES.KITKAT)
        {
            this.getWindow().getDecorView().setFitsSystemWindows(true);
        }
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

    protected abstract void Resume();
    protected abstract void Pause();
    protected abstract void Destroy();
    protected abstract void InitView();
    protected abstract void InitEvent();
    protected abstract void InitData();
    protected abstract void InitListener();
    protected abstract int getContentView();
}
