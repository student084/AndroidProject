package com.student0.www.myapplication;

import android.util.Log;

/**
 * Created by willj on 2017/2/20.
 */

public class L {
    private static final String TAG = "student0";
    private static boolean debug;

    public static void e(String msg){
        if (debug){
            Log.e(TAG, msg);
        }
    }
}
