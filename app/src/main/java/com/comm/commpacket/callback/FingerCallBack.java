package com.comm.commpacket.callback;

import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.util.Log;

/**
 * Created by apple on 2017/7/28.
 */

public class FingerCallBack extends FingerprintManagerCompat.AuthenticationCallback{
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        super.onAuthenticationError(errMsgId, errString);
        Log.e("error","error-->>"+errMsgId+"   string-->>"+errString);
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        super.onAuthenticationHelp(helpMsgId, helpString);
        Log.e("help","help-->>"+helpMsgId+"   string-->>"+helpString);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Log.e("succeed","succeed-->>"+result+"   -》》成功");
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Log.e("failed","failed-->>失败");
    }
}
