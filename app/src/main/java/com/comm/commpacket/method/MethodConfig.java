package com.comm.commpacket.method;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class MethodConfig {

    /**
     * app 全局变量集合
     */
    private static Handler handler = new Handler();

    /**
     * 屏幕尺寸
     */

    public static DisplayMetrics metrics = new DisplayMetrics();;

    public static void InitMetrics(Context context)
    {
        WindowManager window = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        window.getDefaultDisplay().getMetrics(metrics);
    }


    public static void ShowNormalToast(final Context context, final String msg)
    {
       handler.post(new Runnable() {
           @Override
           public void run() {
               Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
           }
       });
    }



}
