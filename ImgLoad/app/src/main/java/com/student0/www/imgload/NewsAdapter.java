package com.student0.www.imgload;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

/**
 * Created by willj on 2017/2/19.
 */

public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener{

    private List<NewsBean> mList;
    private LayoutInflater mInflater;
//为了防止每次请求都创建cache，建立ImageLoader对象
    private ImageLoader mImgeLoader;//
    //listView的加载优化
    private int mStart, mEnd;
    //保存当前获取的所有的URL的地址
    public static String[] URLS;
    private boolean mFirstIn;

    //end 优化参数
    public NewsAdapter(Context context, List<NewsBean> data, ListView listView){
        mList = data;
        mInflater = LayoutInflater.from(context);//
        mImgeLoader = new ImageLoader(listView);//
        //注册事件
        listView.setOnScrollListener(this);
        mFirstIn = true;
        URLS = new String[data.size()];
        for(int i = 0; i < data.size(); i ++){
            URLS[i] = data.get(i).newsIconUrl;
        }
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_layout, null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        String url = mList.get(position).newsIconUrl;
        viewHolder.ivIcon.setTag(url);
       //使用多线程的方式加载
        // new ImageLoader().showImageByThread(viewHolder.ivIcon,url);

        //使用AsyncTask加载
        //new ImageLoader().showImageByAsyncTask(viewHolder.ivIcon, url);//
        //一个listView只使用一个缓存
        mImgeLoader.showImageByAsyncTask(viewHolder.ivIcon, url);
        //
        viewHolder.tvTitle.setText(mList.get(position).newsTitle);
        viewHolder.tvContent.setText(mList.get(position).newsContent);

        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE){
            //当前滚动为停止状态
            //加载可见项

            mImgeLoader.loadImages(mStart, mEnd);
        }else {
            //停止所有的加载
            mImgeLoader.cancelAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        System.out.println("--------------------------------------------------------------------------------------");
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        System.out.println("mStart  = " + mStart + ", mEnd = " + mEnd);
        //第一次显示调用
        if (mFirstIn && visibleItemCount > 0){
            System.out.println("It is the first to come");
            mImgeLoader.loadImages(mStart, mEnd);
            mFirstIn = false;
        }
    }

    class ViewHolder{
        public TextView tvTitle, tvContent;
        public ImageView ivIcon;
    }
}
