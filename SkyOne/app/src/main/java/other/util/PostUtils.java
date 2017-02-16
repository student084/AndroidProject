package other.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import other.bean.User;

/**
 * Created by willj on 2017/2/15.
 */

public class PostUtils {
    public static String LOGIN_URL = "http://10.60.0.164:8080/test/servlet/Test";

    public PostUtils(User user, final SuccessCallBack successCallBack, final FailCallback failCallback){
        new NetConnection(LOGIN_URL, HttpMethod.POST, new NetConnection.SuccessCallBack() {
            @Override
            public void onSuccess(String result) {
                    try {
                        //处理返回的JSON数据，判断
                        //一般以配置文件形式标识各常量
                        Log.i("result-----------", result.toString());
                        JSONObject jsonObject = new JSONObject(result);
                        switch (jsonObject.getInt("login")){
                            case 1:
                                if(successCallBack != null){
                                    successCallBack.onSuccess(result);
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

                        if(failCallback != null ){
                            failCallback.onFail();
                        }
                    }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if(failCallback != null){
                    failCallback.onFail();
                }
            }
        }, Config.LOGIN_USERNAME, user.getUserName(), Config.LOGIN_PASSWORD,user.getPassWord()){

        };
    }
    //设置监听器
    public static interface SuccessCallBack{
        void onSuccess(String result);

    }

    public static interface FailCallback{
        void onFail();
    }
}
