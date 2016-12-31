package com.example.sl_wj.sls60020_07_11;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Calendar;


public class edit_accounts extends Activity implements View.OnClickListener {
    private Button alter_delete;
    private Button alter_submit;
    private Button alter_choose_expend;
    private Button alter_choose_income;
    private EditText alter_date;
    private EditText alter_reason;
    private EditText alter_money;
    private Button reset_day;
    private String alter_date_value;
    private String alter_reason_value;
    private int alter_money_value;
    private String record_id;
    private String alter_choose;
    private String old_date;
    //变量初始化
    private void variable_init(){
        alter_delete = (Button)findViewById(R.id.alter_delete);
        alter_choose_expend = (Button)findViewById(R.id.alter_choose_expend);
        alter_choose_income =(Button)findViewById(R.id.alter_choose_income);
        alter_date = (EditText)findViewById(R.id.alter_date);
        alter_reason = (EditText)findViewById(R.id.alter_reason);
        alter_money = (EditText)findViewById(R.id.alter_money);
        reset_day  = (Button)findViewById(R.id.reset_day);
        Intent intent = this.getIntent();
        record_id = intent.getStringExtra("record_id");
        alter_date.setText(intent.getStringExtra("time"));
        alter_reason.setText(intent.getStringExtra("reason"));
        old_date = intent.getStringExtra("time");
        if(Integer.parseInt(intent.getStringExtra("money")) >= 0 ){
            alter_money.setText(intent.getStringExtra("money"));
            color_change(alter_choose_income, alter_choose_expend);
            alter_choose = "income";
        }else {
            alter_money.setText(String.valueOf(0 - Integer.parseInt(intent.getStringExtra("money"))));
            color_change(alter_choose_expend, alter_choose_income);
            alter_choose = "expend";
        }
    };

    //控件事件监听
    private void widget_listen(){
        alter_delete.setOnClickListener(this);
        alter_choose_expend .setOnClickListener(this);
        alter_choose_income.setOnClickListener(this);
        reset_day.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去titleBar
        setContentView(R.layout.activity_edit_accounts);
        variable_init();
        widget_listen();
        alter_money.requestFocus();
    }
    @Override
    public void onClick(View view) {
        alter_date_value = alter_date.getText().toString();
        alter_reason_value = alter_reason.getText().toString();
        alter_money_value = Integer.parseInt(alter_money.getText().toString());
        if (alter_reason_value.isEmpty() || alter_money_value == 0) {
            Toast.makeText(this, "存在无效输入，请重新记录！", Toast.LENGTH_SHORT).show();
        } else {
            switch (view.getId()) {
                case R.id.alter_delete:
                    delete_record(Integer.parseInt(record_id));
                    this.finish();
                    break;
                case R.id.alter_choose_expend:
                    alter_choose = "expend";
                    color_change(alter_choose_expend, alter_choose_income);
                    alter_record(Integer.parseInt(record_id), alter_choose, alter_reason_value, alter_date_value, alter_money_value);
                    this.finish();
                    //是数据为负
                    break;
                case R.id.alter_choose_income:
                    alter_choose = "income";
                    color_change(alter_choose_income, alter_choose_expend);
                    alter_record(Integer.parseInt(record_id), alter_choose, alter_reason_value, alter_date_value, alter_money_value);
                    this.finish();
                    //使数据为正
                    break;
                case R.id.reset_day:
                    alter_date.setText(old_date);
                    break;
                default:
                    break;
            }
        }
    }
    private void delete_record(int record_id){
        //删除该条记录
        MainActivity.data_base.delete_on_table(record_id, "record");
    }
    private void alter_record(int record_id, String choose, String description, String time, int money){
        //保存被修改的记录
        MainActivity.data_base.save_on_person(" ", null, null);
        MainActivity.data_base.save_on_event(alter_reason_value);
        if(choose.equals("income") ){
            if(money < 0){
                money = 0 - money;
            }
        }else {
            if(money > 0){
                money = 0 - money;
            }
        }
        MainActivity.data_base.update_on_record(record_id," ", description, time, money);
    }
    private void color_change(Button get, Button lose){
        get.setBackgroundColor(Color.rgb(99, 99, 99));
        lose.setBackgroundColor(Color.rgb(204, 204, 204));
    }

    private int get_day_num_by_month(int year, int month){
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
