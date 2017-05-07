package com.zhuandian.baidulocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialize();
        MyApplication.getInstance().requestLocationInfo();
    }

    private void initialize() {
        registerBroadCastReceiver();
    }



    /**
     * 注册一个广播，监听定位结果
     */
    private void registerBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String address = intent.getStringExtra("address");


                Toast.makeText(MainActivity.this,address,Toast.LENGTH_LONG).show();
                Log.d("xiedong", address+"");
                //	locInfo.setText(address+"啊哈哈，这里就是地址的字符串");
            }
        };
        IntentFilter intentToReceiveFilter = new IntentFilter();
        intentToReceiveFilter.addAction("location_broadcast");
        registerReceiver(broadcastReceiver, intentToReceiveFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


}
