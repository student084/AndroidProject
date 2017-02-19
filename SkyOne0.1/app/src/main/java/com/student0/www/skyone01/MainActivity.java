package com.student0.www.skyone01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.student0.www.tools.FlowLayout;

public class MainActivity extends AppCompatActivity {

   private FlowLayout photoFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoFlowLayout = (FlowLayout)findViewById(R.id.id_flowlayout);
        initData(200, 200);
    }

    //获取图片及相关数据，并动态添加到流式布局中

    public void initData(int width, int height){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < 32; i ++){
            ImageView iv  = (ImageView)layoutInflater.inflate(R.layout.iv_photo, photoFlowLayout, false);
            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(width, height);
            iv.setImageResource(R.mipmap.ic_launcher);
            photoFlowLayout.addView(iv,layoutParams);
        }
    }
}
