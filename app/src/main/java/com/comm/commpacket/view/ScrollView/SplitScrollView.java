package com.comm.commpacket.view.ScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.comm.commpacket.listener.ScrollviewListener;

/**
 * Created by apple on 2017/1/6.
 */

public class SplitScrollView extends ScrollView{
    public ScrollviewListener scrollviewListener;
    public SplitScrollView(Context context) {
        super(context);
    }

    public SplitScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplitScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(scrollviewListener!=null)
        {
            scrollviewListener.onScrollListener(this,l,t,oldl,oldt);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(scrollviewListener!=null)
        {
            scrollviewListener.onTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }
}
