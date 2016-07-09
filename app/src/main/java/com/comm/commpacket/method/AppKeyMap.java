package com.comm.commpacket.method;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by apple on 16/7/8.
 */

public class AppKeyMap {
    public static final String ServerUrl = ""; //服务器地址
    public static final String AUTH ="";   //软件登录唯一凭证

    public static SharedPreferences sharedPreferences;

    private static AppKeyMap instance;

    public static AppKeyMap GetInstance()
    {
        synchronized (instance)
        {
            if(instance==null)
            {
                instance = new AppKeyMap();
            }
        }
        return instance;
    }


    public SharedPreferences GetSharedPreferences(Context context,@Nullable String name)
    {
        if(sharedPreferences==null)
        {
            if(TextUtils.isEmpty(name))
            {
                sharedPreferences = context.getSharedPreferences("default",Context.MODE_PRIVATE);
            }
            else
            {
                sharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
            }
        }
        return sharedPreferences;
    }

    public void SetString(String key, String value)
    {
        Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String GetString(String key)
    {
        return sharedPreferences.getString(key,"");
    }


}
