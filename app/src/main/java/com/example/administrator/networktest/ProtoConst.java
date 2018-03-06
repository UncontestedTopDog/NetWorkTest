package com.example.administrator.networktest;

/**
 * Created by huangweiliang on 2018/3/5.
 */

class ProtoConst {
    public static final int	PROTO_TCP_BUFFER_SIZE = 256*1024;
    public static final int	PROTO_UDP_BUFFER_SIZE = 32*1024;
    public static final int	PROTO_PACKET_SIZE = 4*1024;
    public static final int PROTO_VERSION = -1;

    public static final int INVALID_UID = -1;
    public static final int INVALID_SID = -1;

    public static final int INVALID_EVENT=-1;
    public static final int INVALID_REQ=-1;


    public static final byte PLATFORM_ANDROID = 0;
    public static final byte PLATFORM_IOS=1;
    public static final byte PLATFORM_WINPHONE=2;
    public static final byte PLATFORM_UNKNOWN=127;

    public static final byte SYSNET_WIFI=0;
    public static final byte SYSNET_MOBILE=1;
    public static final byte SYSNET_DISCONNECT=2;
    public static final byte SYSNET_2G = 3;
    public static final byte SYSNET_3G = 4;
    public static final byte SYSNET_4G = 5;
    public static final byte SYSNET_UNKNOWN=127;

    // TELPHONE NETWORK TYPE
    public static final int TELNET_UNKNOWN = 0;
    public static final int TELNET_GPRS = 1;
    public static final int TELNET_EDGE = 2;
    public static final int TELNET_UMTS = 3;
    public static final int TELNET_CDMA = 4;
    public static final int TELNET_EVDO_0 = 5;
    public static final int TELNET_EVDO_A = 6;
    public static final int TELNET_1xRTT = 7;
    public static final int TELNET_HSDPA = 8;
    public static final int TELNET_HSUPA = 9;
    public static final int TELNET_HSPA = 10;
    public static final int TELNET_IDEN = 11;
    public static final int TELNET_EVDO_B = 12;
    public static final int TELNET_LTE = 13;
    public static final int TELNET_EHRPD = 14;
    public static final int TELNET_HSPAP = 15;
}
