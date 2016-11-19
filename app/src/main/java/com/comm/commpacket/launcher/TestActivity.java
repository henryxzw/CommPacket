package com.comm.commpacket.launcher;

import android.widget.Toast;

import com.comm.commpacket.bean.BaseBean;
import com.comm.commpacket.R;
import com.comm.commpacket.base.BaseActivity;
import com.comm.commpacket.network.NetWorkParamsIndex;

/**
 * Created by apple on 16/7/18.
 */

public class TestActivity extends BaseActivity {

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
         netWorkModel.PostTest("http://www.izhuangse.com/Api/User/Login/checkReg","13425741527",NetWorkParamsIndex.A);
    }

    @Override
    protected void InitEvent() {

    }

    @Override
    protected void InitData() {

    }

    @Override
    protected void InitListener() {

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


}
