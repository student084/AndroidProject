package com.student0.www.config;

import android.content.Context;
import android.content.SharedPreferences;

import static android.provider.UserDictionary.Words.APP_ID;

/**
 * Created by willj on 2017/2/18.
 */

public class Config {
    public final static String CHARSET = "UTF-8";
    public final static String  URL = "http://10.77.254.109/SkyOne";


    public final static String URL_CONNECTION_FAIL = "url connection fail";
    public final static String URL_CONNECTION_SUCCESS = "url connection success";
    public final static String RESPONSE_RESULT_IS_NULL = "response result is a null object";

    public final static String RESPONSE_STRING_FORM_FAIL = "the response string is not a json String";

    public final static String KEY_STATUS = "status";
    public final static String KEY_ACTION = "action";
    public final static String KEY_PHONE = "phone";
    public final static String KEY_OLDEST_DATE ="oldest_date";
    public final static String KEY_BATCH_GAP = "batch_gap";
    public final static String KEY_TOKEN = "token";
    public final static String KEY_PICTURES = "pictures";

    public final static String KEY_PICTURE_DATE = "date";
    public final static String KEY_PICTURE_RESOURCE = "resource";
    public final static String KEY_PICTURE_ID = "id";
    public final static String ACTION_GET_PICTURE = "get_picture";


    public final static int STATUS_FAIL = 0;
    public final static int STATUS_SUCCESS = 1;
    public final static int STATUS_INVALID_TOKEN = 2;


    public String getCacheToken(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public void cacheToke(Context context, String token){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }

    public String getCachePhone(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_PHONE, null);
    }

    public void cachePhone(Context context, String phone){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_PHONE, phone);
        e.commit();
    }
}
