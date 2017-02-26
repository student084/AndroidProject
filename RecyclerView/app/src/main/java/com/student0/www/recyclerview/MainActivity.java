package com.student0.www.recyclerview;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DemoAdapter adapter;

    int colors[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        //设置recyclerView
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,
//                LinearLayoutManager.VERTICAL, false));

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        //分类设置换行情况
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            //返回值为跨度值，及分裂数的倒数视为一个跨度值的单位
            @Override
            public int getSpanSize(int position) {
                int type = recyclerView.getAdapter().getItemViewType(position);
                if (type == DataModel.TYPE_THREE){
                    //返回分列数*跨度值，即为占据一行
                    return gridLayoutManager.getSpanCount();
                }else{
                    return 1;
                }
            }
        });
        //设置recycleView的间隔设置
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //
               GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams)view.getLayoutParams();
                //获取当前spanSize的大小，跨度值单位
                int spanSize = layoutParams.getSpanSize();
                //左右序列从0开始，0为最左端
                int spanIndex = layoutParams.getSpanIndex();
                //设置为20像素
                outRect.top = 20;
                //当前item大小单位不等于分列数，即独占一行
                if (spanSize != gridLayoutManager.getSpanCount()){
                    if (spanIndex == 1){
                        outRect.left = 10;
                    }else {
                        outRect.right = 10;
                    }
                }
            }
        });

        //设置recyclerView的类型
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new DemoAdapter(this);
        recyclerView.setAdapter(adapter);

        initData();
    }

    private void initData() {

        List<DataModel> list = new ArrayList<>();

        for (int i = 0; i < 30; i ++){
            int type = (int) ((Math.random() * 3) + 1);
            DataModel data = new DataModel();
            data.avatarColor = colors[type - 1];
            if(i < 5 || (i > 15 && i < 20)){
                type = 1;
            }else if(i < 10 || i >26){
                type = 2;
            }else{
                type = 3;
            }
            data.type = type;
            data.name = "name :" + i;
            data.content = "content :" + i;
            data.contentColor = colors[(type + 1)%3];
            list.add(data);
        }
//        adapter.addList(list);
//        adapter.notifyDataSetChanged();

        List<DataModelOne> list1 = new ArrayList<>();
        for (int i = 0; i < 30; i ++){
            DataModelOne data = new DataModelOne();
            data.avatarColor = colors[0];
            data.name = "name :" + i;
            list1.add(data);
        }
        List<DataModelTwo> list2 = new ArrayList<>();
        for (int i = 0; i < 30; i ++){
            DataModelTwo data = new DataModelTwo();
            data.avatarColor = colors[0];
            data.name = "name :" + i;
            data.content = "content :" + i;
            list2.add(data);
        }
        List<DataModelThree> list3 = new ArrayList<>();
        for (int i = 0; i < 30; i ++){
            DataModelThree data = new DataModelThree();
            data.avatarColor = colors[1];
            data.name = "name :" + i;
            data.content = "content :" + i;
            data.contentColor = colors[2];
            list3.add(data);
        }

        adapter.addList(list1, list2, list3);
        adapter.notifyDataSetChanged();
    }
}
