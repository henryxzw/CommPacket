package com.comm.commpacket.launcher;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.LocationMode;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.Trace;
import com.comm.commpacket.ActivityMainBinding;
import com.comm.commpacket.CommItemListBinding;
import com.comm.commpacket.R;
import com.comm.commpacket.adapter.CommListAdapter;
import com.comm.commpacket.adapter.CommReAdapter;
import com.comm.commpacket.listener.ListItemOnClickListener;
import com.comm.commpacket.listener.RecyItemOnClickListener;
import com.comm.commpacket.listener.ScrollviewListener;
import com.comm.commpacket.method.AppKeyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private float maxY, currentY = 0;
    private boolean isScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.scrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                System.out.println("头部:" + top + "  底部：" + bottom + "   旧的头部：" + oldTop + "   旧的底部：" + oldBottom);
                maxY = bottom;
            }
        });
        binding.scrollView.scrollviewListener = new ScrollviewListener() {
            @Override
            public void onScrollListener(ScrollView scrollView, int l, int t, int old_l, int old_t) {
                float x = binding.linear1.getHeight() - binding.scrollView.getHeight() / 2;
                System.out.println("头部:" + t + "  底部：" + x);
                if (isScroll) {
                    currentY = t;
                } else {
                    if (t > binding.linear1.getHeight() - binding.scrollView.getHeight() && t < binding.linear1.getHeight() - binding.scrollView.getHeight() / 2) {
                        binding.scrollView.scrollTo(0, binding.linear1.getHeight() - binding.scrollView.getHeight());
                    } else if (t > binding.linear1.getHeight() - binding.scrollView.getHeight() / 2) {
                        binding.scrollView.scrollTo(0, (int) binding.linear1.getHeight());
                    }
                }
            }

            @Override
            public void onTouchEvent(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    isScroll = false;
//                   if(currentY>binding.linear1.getHeight()-binding.scrollView.getHeight()  && currentY<binding.linear1.getHeight()-binding.scrollView.getHeight()/2)
//                   {
//
//                           binding.scrollView.scrollTo(0, binding.linear1.getHeight() - binding.scrollView.getHeight());
//
//                   }
//                   else if(currentY>binding.linear1.getHeight()-binding.scrollView.getHeight()/2)
//                   {
//                       binding.scrollView.scrollTo(0, binding.linear1.getHeight());
//                   }
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isScroll = true;
                }
            }
        };

        binding.animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });

        InitData();
    }

    LBSTraceClient client;
    Trace trace;

    public void InitData() {
        client = new LBSTraceClient(this);
        client.setInterval(5,15);
        client.setLocationMode(LocationMode.High_Accuracy);
        client.setProtocolType(1);
// 轨迹服务ID
        long serviceId = 119920;
// 设备名称
        String entityName = "mycar";
// 轨迹服务类型，traceType必须设置为UPLOAD_LOCATION才能追踪轨迹
        int traceType = 2;
// 初始化轨迹服务
        trace = new Trace(this, serviceId, entityName, traceType);
        OnStartTraceListener startTraceListener = new OnStartTraceListener() {
            //开启轨迹服务回调接口（arg0 : 消息编码，arg1 : 消息内容，详情查看类参考）
             @Override
             public void onTraceCallback(int arg0, String arg1) {
            }

            //轨迹服务推送接口（用于接收服务端推送消息，arg0 : 消息类型，arg1 : 消息内容，详情查看类参考）
            @Override
             public void onTracePushCallback(byte arg0, String arg1) {
            }
        };

//开启轨迹服务
       // client.startTrace(trace, startTraceListener);
    }

    @Override
    public void onBackPressed() {
        //实例化停止轨迹服务回调接口
        OnStopTraceListener stopTraceListener = new OnStopTraceListener() {
            // 轨迹服务停止成功
            @Override
            public void onStopTraceSuccess() {
            }

            // 轨迹服务停止失败（arg0 : 错误编码，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onStopTraceFailed(int arg0, String arg1) {
            }
        };

//停止轨迹服务
       // client.stopTrace(trace, stopTraceListener);
        super.onBackPressed();
    }
}
