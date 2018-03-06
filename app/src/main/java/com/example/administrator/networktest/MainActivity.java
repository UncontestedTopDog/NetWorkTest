package com.example.administrator.networktest;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SignalIndicator signalIndicator;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            signalIndicator.invalidate();
        }
    };

    NetPingManager mLDNetPingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signalIndicator = findViewById(R.id.signal);
//        创建监听
        this.mLDNetPingService = new NetPingManager(getApplicationContext(), "www.baidu.com", new NetPingManager.IOnNetPingListener() {
            @Override
            public void ontDelay(long log) {
                Log.i("tag", "log-->" + log);
                signalIndicator.setsignal(log);
                handler.sendEmptyMessage(0x123);
            }

            @Override
            public void onError() {
                signalIndicator.setsignal(999);
                handler.sendEmptyMessage(0x123);
            }

        });
        //开始监听
        if (null != mLDNetPingService)
            this.mLDNetPingService.getDelay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放
        if (null != mLDNetPingService)
            this.mLDNetPingService.release();
    }
}
