package com.student0.www.myapplication;

import android.os.Environment;
import android.support.annotation.UiThread;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String baseUrl = "http://10.60.0.164:8080/imooc_okhttp";

    //OkHttpClient是重量级的所以优化，定义为全局
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(3,TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(3,TimeUnit.SECONDS)//设置连接超时时间
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.id_tv_result);
    }
/*
* public final static int CONNECT_TIMEOUT =60;
public final static int READ_TIMEOUT=100;
public final static int WRITE_TIMEOUT=60;
span style="white-space:pre">    </span>//设置读取超时为100秒，就是为了读取充值的订单
public static OkHttpClient mOkHttpClient =
        new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
                .build();
* */
    public void doGet(View view){

        final Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(baseUrl + "/login?username=hyman&password=123").build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure : "+ e.getMessage());
                System.out.println("----------------1-----------------------");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().toString());
                System.out.println("----------------2-----------------------");
                L.e("onResponse : ");
                final String res = response.body().string();
                //System.out.println(res);
                //InputStream is = response.body().byteStream;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(res);
                    }
                });
            }
        });
    }

    public void doPost(View view){
        final Request.Builder builder = new Request.Builder();

        RequestBody formBody = new FormBody.Builder()
                .add("username", "4535345")
                .add("password", "7891")
                .build();

        Request request = builder.post(formBody).url(baseUrl + "/post").build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public void doPostString(View view){
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain; chaset=utf-8"), "{username:student0, password=fdosahfdo}");
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(baseUrl + "/postString").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
//上传文件，以图片为例
    public void doPostFile(View view){
        //此处需要获取权限
        File file = new File(Environment.getExternalStorageDirectory(), "banner2.jpg");
        if(!file.exists()){
            L.e(file.getAbsolutePath() + "not exist");
            return;
        }
        //下面的文件类型可以更精确，mime type
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(baseUrl + "/postString").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
    //同时上传图片，文件，文字等多种
    //大体格式如下，具体见Github:
    //https://github.com/square/okhttp/wiki/Recipes
    private static final String IMGUR_CLIENT_ID = "...";
    private static final MediaType MEDIA_TYPE = MediaType.parse("image/png");
    public void doUpload(View view) {
        //此处需要获取权限

        File file = new File(Environment.getExternalStorageDirectory(), "banner2.jpg");
        if (!file.exists()) {
            L.e(file.getAbsolutePath() + "not exist");
            return;
        }
        //下面的文件类型可以更精确，mime type
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("mPhoto", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE, new File("websites/static/logo-square.png")))
                .build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//下载文件
    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //进度管理
                long total = response.body().contentLength();
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });
    }
}

