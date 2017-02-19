package com.student0.www.netdata;

import com.student0.www.bean.Picture;
import com.student0.www.config.Config;
import com.student0.www.net.HttpMethod;
import com.student0.www.net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willj on 2017/2/18.
 */

public class GetPhotos {
    public GetPhotos(String phone, String oldest_date, int batch_gap, String token, final SuccessCallback successCallback, final FailCallback failCallback) {
        new NetConnection(Config.URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case Config.STATUS_SUCCESS:
                            if(successCallback != null) {
                                List<Picture> picturesList = new ArrayList<Picture>();
                                JSONArray jsonArray = jsonObject.getJSONArray(Config.KEY_PICTURES);
                                JSONObject pictureJSONObject;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    pictureJSONObject = jsonArray.getJSONObject(i);
                                    picturesList.add(new Picture(pictureJSONObject.getString(Config.KEY_PICTURE_DATE), pictureJSONObject.getString(Config.KEY_PICTURE_RESOURCE), pictureJSONObject.getInt(Config.KEY_PICTURE_ID)));
                                }
                                successCallback.onSuccess(picturesList);
                            }
                            break;
                        case Config.STATUS_INVALID_TOKEN:
                            if (failCallback != null){
                                failCallback.onFail(Config.STATUS_INVALID_TOKEN);
                            }
                            break;
                        default:
                            if (failCallback != null){
                                failCallback.onFail(Config.STATUS_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(Config.RESPONSE_STRING_FORM_FAIL);
                    if (failCallback != null){
                        failCallback.onFail(Config.STATUS_FAIL);
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {

            }
        },Config.KEY_ACTION, Config.ACTION_GET_PICTURE,
                Config.KEY_PHONE, phone,
                Config.KEY_OLDEST_DATE, oldest_date,
                Config.KEY_TOKEN, token);
    }

    public interface SuccessCallback{
        void onSuccess(List<Picture> picturesList);
    }

    public interface FailCallback{
        void onFail(int errorCode);
    }
}
