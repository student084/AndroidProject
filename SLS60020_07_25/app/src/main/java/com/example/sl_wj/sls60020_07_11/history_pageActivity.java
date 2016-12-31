package com.example.sl_wj.sls60020_07_11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class history_pageActivity extends Activity implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemLongClickListener {
    private GestureDetector gestureDetector;
    final int RIGHT = 0;
    final int LEFT = 1;
    private ListView listView;
    private Context mContext;
    private Button button;

    //变量初始化
    private void variable_init() {
        gestureDetector = new GestureDetector(history_pageActivity.this, onGestureListener);
        button = (Button)findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        mContext = history_pageActivity.this;
    }

    ;
    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    float y = e2.getY() - e1.getY();
                    if (x > 20) {
                        doResult(RIGHT);
                        return true;
                    } else if (x < 20) {
                        doResult(LEFT);
                        return true;
                    }
                    return false;
                }
            };

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void doResult(int action) {
        switch (action) {
            case RIGHT:
                this.finish();
                break;
            case LEFT:
                break;
        }
    }

    //控件事件监听
    private void widget_listen() {
        listView.setOnItemLongClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去titleBar
        setContentView(R.layout.activity_history_page);
        variable_init();
        widget_listen();
        MainActivity.data_base.new_list_history(this, listView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                initPopWindow(view);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.data_base.new_list_history(this, listView);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String, String> map = (HashMap<String, String>) MainActivity.data_base.new_get_list_data().get(i);
        initPopWindow_detail(view ,map.get("time"), map.get("reason"), map.get("money"), map.get("record_id"));
        return true;
    }

    private void derive_to_file(String file_name) {
        FileHelper fHelper = new FileHelper(this);
        String filename = file_name;
        String file_detail = MainActivity.data_base.get_drive_date();
        try {
            fHelper.save(filename, file_detail);
            Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
        }
    }
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, null, false);
        Button derive;
        derive = (Button) view.findViewById(R.id.derive);
        Button delete;
        delete = (Button) view.findViewById(R.id.delete);
        //构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 50, 0);

        //设置popupWindow里的按钮的事件
        derive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String get_file_name = "记录" + df.format(new Date());
                derive_to_file(get_file_name);
                Toast.makeText(history_pageActivity.this, "成功导出文件名:" + get_file_name, Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.data_base.delete_all_data();
                popWindow.dismiss();
            }
        });
    }
    private void initPopWindow_detail(View v, final String time, final String reason, final String money, final String id) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_pop, null, false);
        Button edit;
        edit= (Button) view.findViewById(R.id.edit_history);
        Button delete;
        delete = (Button) view.findViewById(R.id.delete_history);
        //构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 700, -180);

        //设置popupWindow里的按钮的事件
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("time", time);
                intent.putExtra("principal", " ");
                intent.putExtra("reason", reason);
                intent.putExtra("money", money);
                intent.putExtra("record_id", id);
                intent.setClass(history_pageActivity.this, MainActivity.class);
                history_pageActivity.this.startActivityForResult(intent, 1);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.data_base.delete_on_table(Integer.parseInt(id), "record");
                popWindow.dismiss();
                MainActivity.data_base.new_list_history(history_pageActivity.this, listView);
            }
        });
    }

}
