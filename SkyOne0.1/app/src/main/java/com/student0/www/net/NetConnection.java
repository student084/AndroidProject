package com.student0.www.net;

import android.os.AsyncTask;

import com.student0.www.config.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by willj on 2017/2/18.
 */

public class NetConnection {
    public NetConnection(final String url, final HttpMethod httpMethod, final SuccessCallback successCallback, final FailCallback failCallback, final String ... kvs) {
        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                StringBuffer paramsStr = new StringBuffer();
                for(int i = 0; i < kvs.length; i += 2){
                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                try {
                URLConnection urlConnection;
                switch (httpMethod){
                    case POST:
                            urlConnection = new URL(url).openConnection();
                            urlConnection.setDoOutput(true);
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), Config.CHARSET));
                            bufferedWriter.write(paramsStr.toString());
                            bufferedWriter.flush();
                            bufferedWriter.close();

                        break;
                    default:
                            urlConnection = new URL(url + url + "?" +paramsStr.toString()).openConnection();
                        break;
                }
                    System.out.println("Request url:" + urlConnection.getURL());
                    System.out.println("Request data:" + paramsStr);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Config.CHARSET));

                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while ((line = bufferedReader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();
                } catch (IOException e) {
                    System.out.println(Config.URL_CONNECTION_FAIL);
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
                }else{
                    if (failCallback != null){
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(result);
            }
        }.execute();
    }

    public interface SuccessCallback{
        void onSuccess(String result);
    }

    public interface FailCallback{
        void onFail();
    }
}
