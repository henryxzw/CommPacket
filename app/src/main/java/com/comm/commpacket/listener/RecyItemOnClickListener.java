package com.comm.commpacket.listener;

import android.databinding.ViewDataBinding;

/**
 * Created by apple on 16/7/6.
 */

public interface RecyItemOnClickListener<T> {
    void OnBindItemClickListener(T data, ViewDataBinding viewDataBinding);
}
