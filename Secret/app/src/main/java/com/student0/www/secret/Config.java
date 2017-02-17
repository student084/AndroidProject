package com.student0.www.secret;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by willj on 2017/2/16.
 */

public class Config {

    public static final String KEY_TOKEN = "token";
    public static final String APP_ID = "com.student0.secret";
    public static final String CHARSET= "UTF-8";
    //http://demo.eoeschool.com/api/v1/nimings/io
    public static final String SERVER_URL = "http://10.60.0.164:8080/Server/servlet/Test";

    public static final int RESULT_STATUS_SUCCESS = 1;
    public static final int RESULT_STATUS_FAIL = 0;
    //TOKEN timeout
    public static final int RESULT_STATUS_INVALID_TOKEN = 2;
    public static final String KEY_CODE = "code";
    public static final String KEY_STATUS = "status";
    public static final String ACTION_GET_CODE = "send_pass";
    public static final String KEY_ACTION = "action";
    public static final String KEY_PHONE_MD5 = "phone_md5";
    public static final String KEY_PHONE_NUM = "phone";

    public static final String ACTION_LOGIN = "login";


    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public static void cacheToken(Context context, String token){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }
    public static String getCachedPhoneNumber(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_PHONE_NUM, null);
    }

    public static void cachePhoneNumber(Context context, String phoneNumber){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, phoneNumber);
        e.commit();
    }
}
