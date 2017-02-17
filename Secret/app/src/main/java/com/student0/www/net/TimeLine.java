package com.student0.www.net;

import com.student0.www.secret.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by willj on 2017/2/17.
 */

public class TimeLine {
    public TimeLine(String phone_md5, String token, int page, int perpage, final  SuccessCallback successCallback, final  FailCallback failCallback) {
        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);

                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:

                            if (successCallback != null){
                                successCallback.onSuccess(jsonObject.getInt(Config.KEY_PAGE),jsonObject.getInt(Config.KEY_PERPAGE), jsonObject.getJSONArray(Config.KEY_TIMELINE));
                            }

                            break;
                        default:
                            if (failCallback != null){
                                failCallback.onFail();
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback != null){
                        failCallback.onFail();
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback != null){
                    failCallback.onFail();
                }
            }
        },Config.KEY_ACTION, Config.ACTION_TIMELINE,
                Config.KEY_PHONE_MD5, phone_md5,
                Config.KEY_TOKEN, token,
                Config.KEY_PAGE, page+"",
                Config.KEY_PERPAGE,perpage+"");
    }

    public static interface SuccessCallback{
        void onSuccess(int page, int perpage, JSONArray timeline);
    }

    public static interface FailCallback{
        void onFail();
    }
}
