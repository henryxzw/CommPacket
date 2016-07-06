package com.comm.commpacket.listener;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public interface ListItemOnClickListener<T> {
    void onItemClickListener(T data, ViewDataBinding binding);
}
