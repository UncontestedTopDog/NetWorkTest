package com.example.administrator.networktest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.Log;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by ruoshili on 12/12/14.
 */
public class NetworkHelper {
    public enum NetworkClass {
        WIFI,
        MOBILE_2G,
        MOBILE_3G,
        MOBILE_4G,
        DISCONNECTED,
        UNKNOWN
    }

    public static byte getNetType(@NonNull Context contxt) {
        byte netType = ProtoConst.SYSNET_DISCONNECT;
        ConnectivityManager connMgr = (ConnectivityManager) contxt
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null) {
            return netType;
        }

        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            netType =  ProtoConst.SYSNET_MOBILE;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = ProtoConst.SYSNET_WIFI;
        }

        return netType;
    }

    public static NetworkClass getNetworkClass(@NonNull Context context) {
        byte netType = getNetType(context);

        switch (netType) {
            case ProtoConst.SYSNET_DISCONNECT:
                return NetworkClass.DISCONNECTED;
            case ProtoConst.SYSNET_WIFI:
                return NetworkClass.WIFI;
            case ProtoConst.SYSNET_MOBILE:
                return getMobileNetworkClass(context);
        }

        return NetworkClass.UNKNOWN;
    }

    public static boolean isDisconnected(@NonNull Context context) {
        return (NetworkClass.DISCONNECTED == getNetworkClass(context));
    }

    @NonNull
    public static NetworkClass getMobileNetworkClass(@NonNull Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetworkClass.MOBILE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetworkClass.MOBILE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetworkClass.MOBILE_4G;
            default:
                return NetworkClass.UNKNOWN;
        }
    }

    public static int getWifiInfo(@NonNull Context contxt) {
        WifiManager wifiManager = (WifiManager) contxt.getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getBSSID() != null) {
            //wifi名称
            String ssid = wifiInfo.getSSID();
            //wifi信号强度
            int signalLevel = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 10);
            //wifi速度
            int speed = wifiInfo.getLinkSpeed();
            //wifi速度单位
            String units = WifiInfo.LINK_SPEED_UNITS;
            Log.i("ASD","wifi_level:"+signalLevel +"_speed:"+speed +"_units:"+units);
            return signalLevel;
        }
        else return  0;
    }
}
