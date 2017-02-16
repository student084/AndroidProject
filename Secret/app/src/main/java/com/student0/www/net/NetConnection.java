package com.student0.www.net;

import android.os.AsyncTask;
import android.util.Log;

import com.student0.www.secret.Config;

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

    public NetConnection(final String url, final HttpMethod httpMethod, final SuccessCallback successCallback, final FailCallback failCallback, final String ... kvs){

        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {

                StringBuffer paramsStr = new StringBuffer();
                for (int i = 0; i < kvs.length; i += 2){
                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                Log.i("paramStr", paramsStr.toString());
                try {
                    URLConnection urlConnection;

                    switch (httpMethod){
                        case POST:
                            urlConnection = new URL(url).openConnection();
                            urlConnection.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), Config.CHARSET));
                            bw.write(paramsStr.toString());
                            bw.flush();
                            bw.close();
                            break;
                        default:
                            urlConnection = new URL(url + "?" +paramsStr.toString()).openConnection();
                            break;
                    }
                    Log.i("1232",urlConnection.getURL().toString());
                    Log.i("12332",paramsStr.toString());
                    System.out.println("Request url:" + urlConnection.getURL());
                    System.out.println("Request data:" + paramsStr);
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Config.CHARSET));

                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while ((line = br.readLine()) != null){
                        result.append(line);
                    }
                    System.out.println("Result Data:" + result);
                    return result.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {

                if (result != null){
                    if (successCallback != null){
                        successCallback.onSuccess(result);
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

    public static interface SuccessCallback{
        void onSuccess(String string);
    }

    public static interface FailCallback{
        void onFail();
    }
}
