package com.comm.commpacket.launcher;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.View;
import android.widget.Toast;

import com.comm.commpacket.ActivityTestBinding;
import com.comm.commpacket.bean.BaseBean;
import com.comm.commpacket.R;
import com.comm.commpacket.base.BaseActivity;
import com.comm.commpacket.callback.FingerCallBack;
import com.comm.commpacket.network.NetWorkParamsIndex;

import java.security.Permission;
import java.security.Signature;

/**
 * Created by apple on 16/7/18.
 */

public class TestActivity extends BaseActivity {
    private ActivityTestBinding binding;

    private FingerprintManagerCompat managerCompat;

    @Override
    protected void Resume() {

    }

    @Override
    protected void Pause() {

    }

    @Override
    protected void Destroy() {

    }

    @Override
    protected void InitView() {
        binding = (ActivityTestBinding) viewDataBinding;

        // netWorkModel.PostTest("http://www.izhuangse.com/Api/User/Login/checkReg","13425741527",NetWorkParamsIndex.A);
    }

    @Override
    protected void InitEvent() {
         managerCompat = FingerprintManagerCompat.from(this);
    }

    @Override
    protected void InitData() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.USE_FINGERPRINT},1);
        }
    }

    @Override
    protected void InitListener() {

        binding.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartListener();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test;
    }

    @Override
    protected void OnSuccessResponse(Object o, NetWorkParamsIndex resultCode) {
        if(resultCode==NetWorkParamsIndex.A)
        {
            BaseBean baseBean = (BaseBean)o;
            Toast.makeText(this,baseBean.getMsg(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void OnFailureResponse(String msg, NetWorkParamsIndex resultCode) {
        if(resultCode==NetWorkParamsIndex.A)
        {
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }

    }

    public void StartListener()
    {
        if(isFinger()) {
            managerCompat.authenticate(null, 0, null, new FingerCallBack(), null);
        }
    }

    public boolean isFinger() {

        //android studio 上，没有这个会报错
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
            return false;
        }
        //判断硬件是否支持指纹识别
        if (!managerCompat.isHardwareDetected()) {
            Toast.makeText(this, "没有指纹识别模块", Toast.LENGTH_SHORT).show();
            return false;
        }

        //判断是否有指纹录入
        if (!managerCompat.hasEnrolledFingerprints()) {
            Toast.makeText(this, "没有录入指纹", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
