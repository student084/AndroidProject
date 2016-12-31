package com.example.sl_wj.sls60020_07_11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class staff_information extends Activity implements View.OnClickListener {
    private Button staff_choose_submit;
    private Button staff_choose_cancel;
    private EditText edit_staff_name;
    private EditText edit_staff_phone;
    private EditText edit_staff_position;

    private String way_to_staff;
    private String getEdit_staff_name;
    private String getEdit_staff_phone;
    private String getEdit_staff_position;
    private Intent intent;
    private String person_id;
    private void variable_init() {
        staff_choose_submit = (Button)findViewById(R.id.staff_choose_submit);
        staff_choose_cancel = (Button)findViewById(R.id.staff_choose_cancel);
        edit_staff_name = (EditText)findViewById(R.id.edit_staff_name);
        edit_staff_phone = (EditText)findViewById(R.id.edit_staff_phone);
        edit_staff_position = (EditText)findViewById(R.id.edit_staff_position);
        intent = this.getIntent();
        way_to_staff = intent.getStringExtra("way_to_staff").toString();
        if(way_to_staff.equals("change")){
            person_id = intent.getStringExtra("person_id").toString();
            edit_staff_name.setText(intent.getStringExtra("person_name").toString());
            edit_staff_phone.setText(intent.getStringExtra("person_phone").toString());
        }
    }
    private void widget_listen(){
        staff_choose_submit.setOnClickListener(this);
        staff_choose_cancel.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去titleBar
        setContentView(R.layout.activity_staff_information);
        variable_init();
        widget_listen();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.staff_choose_cancel:
                this.finish();
                break;
            case R.id.staff_choose_submit:
                //修改或存进数据库
                getEdit_staff_name = edit_staff_name.getText().toString();
                getEdit_staff_phone = edit_staff_phone.getText().toString();
                getEdit_staff_position = edit_staff_position.getText().toString();
                if(getEdit_staff_name.isEmpty()){
                    Toast.makeText(this, "请输入姓名！",Toast.LENGTH_SHORT).show();
                }else {
                    if (way_to_staff.equals("add")) {
                        MainActivity.data_base.save_on_person(getEdit_staff_name, getEdit_staff_phone, getEdit_staff_position);
                        Log.i("3", "进入方式为add" + "员工姓名为：" + getEdit_staff_name);
                    } else if (way_to_staff.equals("change")) {
                        MainActivity.data_base.update_on_person(Integer.parseInt(person_id), getEdit_staff_name, getEdit_staff_phone, getEdit_staff_position);
                    }
                }
                this.finish();
                break;
            default:
                break;
        }
    }
}
