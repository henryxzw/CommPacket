package com.comm.commpacket.base;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.comm.commpacket.method.AppKeyMap;
import com.comm.commpacket.method.MethodConfig;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by apple on 16/7/14.
 */

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化程序配置
        MethodConfig.InitMetrics(this);
        AppKeyMap.GetInstance().GetSharedPreferences(this,getApplicationConfigName());
        InitData();
        InitListener();

        InitGalleryFinal();
    }

    protected abstract void  InitData();
    protected abstract void InitListener();
    protected abstract String getApplicationConfigName();

    /**
     * gfinal 初始化
     */

    public void InitGalleryFinal()
    {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
        .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new UILImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }

    public class UILImageLoader implements ImageLoader
    {

        private Bitmap.Config mConfig;

        public UILImageLoader() {
            this(Bitmap.Config.RGB_565);
        }

        public UILImageLoader(Bitmap.Config config) {
            this.mConfig = config;
        }
        @Override
        public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            Picasso.with(activity)
                    .load(new File(path))
                    .placeholder(defaultDrawable)
                    .error(defaultDrawable)
                    .config(mConfig)
                    .resize(width, height)
                    .centerInside()
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(imageView);
        }

        @Override
        public void clearMemoryCache() {

        }
    }

}
