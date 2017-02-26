package com.student0.www.moreload;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.student0.www.view.LoadListView;

public class MainActivity extends Activity implements LoadListView.ILoadListener{

    LoadListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylistview = (LoadListView)findViewById(R.id.mylistview);
    }

    @Override
    public void onLoad() {
        //获取更多数据
        //通知ListView更新界面
    }
}
