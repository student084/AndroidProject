package com.student0.www.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.student0.www.moreload.R;

/**
 * Created by willj on 2017/2/21.
 */

public class LoadListView extends ListView implements AbsListView.OnScrollListener {

    private View footer;//boot view
    private int totalItemCount; //总数量
    private int lastVisibleItem; //最后一个可见的item
    private boolean isLoading ;//是否正在加载

    ILoadListener iLoadListener;

    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_layout, null);
        footer.findViewById(R.id.load_layout).setVisibility(INVISIBLE);
        this.addFooterView(footer);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibleItem && scrollState == SCROLL_STATE_IDLE){
            if(!isLoading){
                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(VISIBLE);
            }
            //加载更多数据
            iLoadListener.onLoad();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public void setInterface(ILoadListener iLoadListener){
        this.iLoadListener = iLoadListener;
    }

    //加载更多数据
    public interface ILoadListener{
        public void onLoad();
    }
}
