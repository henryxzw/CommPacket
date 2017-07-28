package com.comm.commpacket.listener;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public interface DownProgressListener {
    int GetProgress(float progress);
    int GetLength();
    void finish(long down_id);
}
