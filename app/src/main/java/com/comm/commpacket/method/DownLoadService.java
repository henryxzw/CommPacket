package com.comm.commpacket.method;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.comm.commpacket.listener.DownProgressListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class DownLoadService {

    private static DownLoadService instance;
    private DownloadManager manager;
    private Context context;
    private DownReceiver receiver;
    private DownProgressListener downProgressListener;
    private long currentId = 0,currentBytes,totalBytes;

    public static DownLoadService getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new DownLoadService(context);
        }
        return instance;
    }

    public DownLoadService(Context context)
    {
         manager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        this.context = context;
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.registerReceiver(receiver,filter);
    }

    /**
     * 下载开始
     * @param url
     * @param title
     * @param detail
     * @param fileName
     */
    public void Start(String url,String title,String detail,String fileName)
    {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);
        request.setTitle(title);
        request.setDescription(detail);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        currentId = manager.enqueue(request);
    }

    /**
     * 结束程序的下载，当软件退出时，需要调用此方法，释放广播注册
     */
    public void End()
    {
        this.context.unregisterReceiver(receiver);
    }

    public void GetProgress()
    {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(currentId);
        Cursor cursor = manager.query(query);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                currentBytes =  cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                totalBytes = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                if(downProgressListener!=null) {
                    downProgressListener.GetProgress(currentBytes / totalBytes);
                }
            }
            else
            {
                downProgressListener.GetProgress(0);
            }
        }catch (Exception ex)
        {
            if(cursor!=null)
            {
                cursor.close();
            }
            downProgressListener.GetProgress(0);
        }
    }

    public long getCurrentBytes() {
        return currentBytes;
    }



    public long getTotalBytes() {
        return totalBytes;
    }


    public void setDownProgressListener(DownProgressListener downProgressListener) {
        this.downProgressListener = downProgressListener;
    }

    private class  DownReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
            {
                if(currentId == intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1))
                {
                    if(downProgressListener!=null) {
                        downProgressListener.finish(currentId);
                        downProgressListener = null;
                    }
                }
            }
        }
    }

}
