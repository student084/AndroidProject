package com.student0.www.net;

import com.student0.www.secret.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by willj on 2017/2/16.
 */

public class GetCode {
    public GetCode(String phone,final SuccessCallback successCallback, final FailCallback failCallback) {

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback != null){
                                successCallback.onSuccess(string);
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
        }, Config.KEY_ACTION, Config.ACTION_GET_CODE, Config.KEY_PHONE_NUM, phone);
    }

    public static interface SuccessCallback{
        void onSuccess(String string);
    }

    public static interface FailCallback{
        void onFail();
    }
}
