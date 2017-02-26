package com.student0.www.skyone01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.student0.www.bean.BoxContent;
import com.student0.www.bean.Photo;
import com.student0.www.tools.FlowLayout;

import java.util.List;

import static com.student0.www.skyone01.R.id.flowlayout_photos_box;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)findViewById(R.id.box_content_all);
        //initData(200, 200);
    }

    //获取图片及相关数据，并动态添加到流式布局中

    //通过数据获得图片及日期
    public void initData(int width, int height, List<BoxContent> boxContentlist){
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        for(int i = 0; i < boxContentlist.size(); i ++){
            String date = boxContentlist.get(i).getDate();
            //create a TextView im box_content_all
            TextView tv = new TextView(this);
            tv.setText(date);
            linearLayout.addView(tv);
            List<Photo> photoList = boxContentlist.get(i).getPhotoList();
            //create a FlowLayot
            FlowLayout flowLayout = new FlowLayout(this);
            for (int j = 0; j < photoList.size(); j ++){
                //create photos from photoList.get[j]
                Photo photo = photoList.get(i);
                //定义动态图片的样式
                ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(width, height);
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.mipmap.ic_launcher);
                flowLayout.addView(imageView);
            }
            linearLayout.addView(flowLayout);
        }

    }
}
