package com.comm.commpacket.listener;

import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by apple on 2017/1/6.
 */

public interface ScrollviewListener {
    void  onScrollListener(ScrollView scrollView,int l,int t,int old_l,int old_t);
    void  onTouchEvent(MotionEvent event);
}
