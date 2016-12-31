package com.example.sl_wj.sls60020_07_11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class derive_page extends Activity implements View.OnClickListener {
    final int RIGHT = 0;
    final int LEFT = 1;
    private Button derive_ok;
    private GestureDetector gestureDetector;
    private EditText file_name;
    private String get_file_name;
    //变量初始化
    private void variable_init(){
        derive_ok = (Button)findViewById(R.id.derive_ok);
        file_name = (EditText)findViewById(R.id.file_name);
        gestureDetector = new GestureDetector(derive_page.this,onGestureListener);
    };
    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    float y = e2.getY() - e1.getY();
                    if (x > 0) {
                        doResult(RIGHT);
                        return true;
                    } else if (x < 0) {
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
    private void widget_listen(){
        derive_ok.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去titleBar
        setContentView(R.layout.activity_derive_page);
        variable_init();
        widget_listen();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.derive_ok:
                get_file_name = file_name.getText().toString();
                if(get_file_name.isEmpty()){
                    Toast.makeText(this, "文件名不能为空！", Toast.LENGTH_SHORT).show();
                }else {
                    derive_to_file(get_file_name);
                    this.finish();
                }
                break;
            default:
                break;
        }
    }

    private void derive_to_file(String file_name) {
        FileHelper fHelper = new FileHelper(this);
        String filename = file_name;
        String filedetail = MainActivity.data_base.get_drive_date();
        try {
            fHelper.save(filename, filedetail);
            Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
        }
    }
    private String get_derive_date(){
        return MainActivity.data_base.get_drive_date();
    }

}

