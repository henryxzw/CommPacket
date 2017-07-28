package com.comm.commpacket.launcher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by apple on 2017/1/6.
 * 仿淘宝分屏
 */

public class ScrollSplitView implements View.OnLayoutChangeListener,View.OnTouchListener{
    private Context context;
    private int aHeight,bHeight,width;//  a/b分屏高度
    private View view_a,view_b,view_mid;
    private ScrollView scrollView;

    public ScrollSplitView(Context context, int p_width, int p_height, LinearLayout parent_layout)
    {
        this.context = context;
        this.width = p_width;
        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(p_width,p_height));

        scrollView.addOnLayoutChangeListener(this);
        scrollView.setOnTouchListener(this);
        parent_layout.addView(scrollView);
        this.scrollView = scrollView;
    }

    public float getaHeight() {
        return aHeight;
    }

    public void setaHeight(int aHeight) {
        this.aHeight = aHeight;
    }

    public float getbHeight() {
        return bHeight;
    }

    public void setbHeight(int bHeight) {
        this.bHeight = bHeight;
    }

    public void InitView(View view_a,View view_b)
    {
        this.view_a = view_a;
        this.view_b = view_b;
        this.scrollView.addView(this.view_a,new ViewGroup.LayoutParams(width,aHeight));
        this.scrollView.addView(this.view_a,new ViewGroup.LayoutParams(width,aHeight));
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
