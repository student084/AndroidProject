package com.student0.www.net;

import com.student0.www.secret.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

                                List<Message> msgs = new ArrayList<Message>();
                                JSONArray msgJSONArray = jsonObject.getJSONArray(Config.KEY_TIMELINE);
                                System.out.println(msgJSONArray);
                                JSONObject msgObj;
                                for (int i = 0; i < msgJSONArray.length(); i ++){
                                    msgObj = msgJSONArray.getJSONObject(i);
                                    msgs.add(new Message(msgObj.getString(Config.KEY_MSG_ID),
                                            msgObj.getString(Config.KEY_MSG),
                                            msgObj.getString(Config.KEY_PHONE_MD5)));
                                }

                                successCallback.onSuccess(jsonObject.getInt(Config.KEY_PAGE),jsonObject.getInt(Config.KEY_PERPAGE), msgs);
                            }

                            break;
                        case Config.RESULT_STATUS_INVALID_TOKEN:
                            failCallback.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
                            break;
                        default:
                            if (failCallback != null){
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback != null){
                        failCallback.onFail(Config.RESULT_STATUS_FAIL);
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback != null){
                    failCallback.onFail(Config.RESULT_STATUS_FAIL);
                }
            }
        },Config.KEY_ACTION, Config.ACTION_TIMELINE,
                Config.KEY_PHONE_MD5, phone_md5,
                Config.KEY_TOKEN, token,
                Config.KEY_PAGE, page+"",
                Config.KEY_PERPAGE,perpage+"");
    }

    public static interface SuccessCallback{
        void onSuccess(int page, int perpage, List<Message> timeline);
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
