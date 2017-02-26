package com.student0.www.imgload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by willj on 2017/2/19.
 */

public class ImageLoader {

    private ImageView mImageView;
    private String  mURL;
    //近期最少使用缓存LruCache
    private LruCache<String, Bitmap> mCaches;
    //
    private ListView mListView;
    private Set<NewsAsyncTask> mTask;

    //用来加载从start 到end <listView中的>的图片
    public void loadImages(int start, int end){
        for (int i = start; i < end; i ++){
            String url = NewsAdapter.URLS[i];

            //判断是否已存在缓存中
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null){
               NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                mTask.add(task);
            }else{
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void cancelAllTask(){

        if (mTask != null){
            for (NewsAsyncTask task : mTask){
                task.cancel(false);
            }
        }
    }

    public ImageLoader(ListView listView){
        mListView = listView;
        mTask = new HashSet<>();

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;

        mCaches = new LruCache<String, Bitmap>(cacheSize){
            //每次加入内存缓存的时候自动调用sizeOf()方法
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }

    //将索引和值传入缓存，此时的索引为url,值为bitmap
    public void addBitmapToCache(String url, Bitmap bitmap){
        if (getBitmapFromCache(url) == null){
            mCaches.put(url, bitmap);
        }
    }

    //通过索引得到缓存中的数据,此时为bitmap
    public Bitmap getBitmapFromCache(String url){
        //底层基本靠Map实现
        return mCaches.get(url);
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mImageView.getTag().equals(mURL)){
                mImageView.setImageBitmap((Bitmap)msg.obj);
            }
        }
    };

    public void showImageByThread(ImageView imageView, final String url){
        mURL = url;
        mImageView = imageView;
        new Thread(){
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromURL(url);
                //传送子线程中的值，使主线程能补货
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    public Bitmap getBitmapFromURL(String urlString){
        Bitmap bitmap;
        InputStream is = null;
        URL url = null;
        try {
            url = new URL(urlString);
            try {
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                is = new BufferedInputStream(connection.getInputStream());

                bitmap = BitmapFactory.decodeStream(is);
                connection.disconnect();
                return  bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }  finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

//-----------------------------------------------------------第二种实现方式
    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap>{

    private ImageView mImageView;
    private String mUrl;

//    public NewsAsyncTask(ImageView imageView, String url){
//        mImageView = imageView;
//        mUrl = url;
//    }
public NewsAsyncTask(String url){
    mUrl = url;
}
    @Override
    protected Bitmap doInBackground(String... params) {

        //start-way-1
        //下载进入缓存
        String url = params[0];
        Bitmap bitmap = getBitmapFromURL(url);
        if (bitmap != null) {
            addBitmapToCache(url, bitmap);
        }
        return bitmap;//end-way-1
        //start-way-2
        //return getBitmapFormURL(params[0]);
        // end-way-2
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
//        if (mImageView.getTag().equals(mUrl)){
//            mImageView.setImageBitmap(bitmap);
//        }
        ImageView imageView = (ImageView)mListView.findViewWithTag(mUrl);
        if (imageView != null && bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
        //传输完成后结束此任务
        mTask.remove(this);
    }
};
    public void showImageByAsyncTask(ImageView imageView, String url){
        //判断是否已存在缓存中
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null){
            //new NewsAsyncTask(imageView, url).execute(url);
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else{
            imageView.setImageBitmap(bitmap);
        }
    }
}
