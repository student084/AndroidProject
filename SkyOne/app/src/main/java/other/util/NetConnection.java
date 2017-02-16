package other.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by willj on 2017/2/16.
 */

public class NetConnection {


    public NetConnection(final String url,final HttpMethod method, final SuccessCallBack successCallBack,final FailCallback failCallback,final String ... kvs){
        //开线程
        new AsyncTask<Void,Void, String >(){

            @Override
            protected String doInBackground(Void... params) {
                //创建参数对
                StringBuffer paramsStr = new StringBuffer();
                for(int i =  0; i < kvs.length; i += 2){
                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                //内部类无法访问到外部的url，所以要使用final型
                try {
                    //
                    URLConnection uc;

                    switch (method){

                        case POST:
                            //以流的方式写到服务器
                            uc = new URL(url).openConnection();
                            //往服务器输出
                            uc.setDoOutput(true);

                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(),"utf-8"));
                            bufferedWriter.write(paramsStr.toString());
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            break;
                        default:

                            uc = new URL(url + "?" + paramsStr.toString()).openConnection();

                            break;
                    }

                    System.out.println("Request url:" + uc.getURL());
                    System.out.println("Request data:" + paramsStr);
                    //读取数据

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
                    //行对象
                    String line = null;
                    StringBuffer result = new StringBuffer();

                    //将所有的读取到的数据放入result中
                    while ((line =bufferedReader.readLine()) != null){
                        result.append(line);
                    }
                    System.out.println("Result--------------:" + result.toString());
                    return result.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            //通知外部,重写onPostExecute(String result)其中的result就是doInBackground()的返回参数

            @Override
            protected void onPostExecute(String result) {
               // Log.i("4","onPostExecute......................");
                if (result != null){
                    if (successCallBack != null){
                        successCallBack.onSuccess(result);
                    }
                }else {
                    if (failCallback != null){
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(result);
            }
        }.execute();
    }
    //设置监听器
    public static interface SuccessCallBack{
        void onSuccess(String result);

    }

    public static interface FailCallback{
        void onFail();
    }
}
