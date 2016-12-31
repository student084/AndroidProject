package com.example.sl_wj.sls60020_07_11;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    //控件变量
    private GestureDetector gestureDetector;
//    private Button home_choose_income;
//    private Button home_choose_expend;
    private TextView set_date_today;
    private EditText home_input_date;
    private EditText home_input_reason;
    private EditText home_input_money;
    private Button add_one_day;
    private Button sub_one_day;
    private Button home_choose_mode;
    private Button submit_save;
    private TextView extra_money;
    private TextView money_0;
    private TextView money_1;
    private TextView money_2;
    private TextView money_3;
    private TextView money_4;
    private TextView money_5;
    private TextView money_6;
    private TextView money_7;
    private TextView money_8;
    private TextView money_9;
    private TextView money_10;
    private TextView money_20;
    private TextView money_reset;
    private TextView money_add;
    private TextView recent_first_common_money;
    private TextView recent_first_common_moneyReason;
    private TextView recent_second_common_money;
    private TextView recent_second_common_moneyReason;
    private TextView float_money;
    private TextView add_sign;
    private Button menu;
    private Context context;
    //其他变量
    final int RIGHT = 0;
    final int LEFT = 1;
    static dataBase data_base;
    private Context mContext;
    private String date;
    private int record_id = -1;
    private String reason;
    private int money;
    private static int day_away_from_today = 0;
    private String small_money;
    private  String large_money;
    private String old_year;
    private String old_month;
    private String old_day;
    //变量初始化
    private void variable_init() {
        mContext = getApplicationContext();
        gestureDetector = new GestureDetector(MainActivity.this, onGestureListener);

        home_input_date = (EditText)findViewById(R.id.home_input_date);
        home_input_reason = (EditText)findViewById(R.id.home_input_reason);
        home_input_money = (EditText)findViewById(R.id.home_input_money);
        set_date_today = (TextView)findViewById(R.id.set_date_today);
        add_one_day = (Button)findViewById(R.id.add_one_day);
        sub_one_day = (Button)findViewById(R.id.sub_one_day);
        home_choose_mode = (Button)findViewById(R.id.home_choose_mode);
        submit_save = (Button)findViewById(R.id.submit_save);
        extra_money = (TextView)findViewById(R.id.extra_money);
        money_0  =(TextView)findViewById(R.id.money_0);
        money_1 = (TextView)findViewById(R.id.money_1);
        money_2 = (TextView)findViewById(R.id.money_2);
        money_3 = (TextView)findViewById(R.id.money_3);
        money_4 = (TextView)findViewById(R.id.money_4);
        money_5 = (TextView)findViewById(R.id.money_5);
        money_6 = (TextView)findViewById(R.id.money_6);
        money_7 = (TextView)findViewById(R.id.money_7);
        money_8 = (TextView)findViewById(R.id.money_8);
        money_9 = (TextView)findViewById(R.id.money_9);
        money_10 = (TextView)findViewById(R.id.money_10);
        money_20 = (TextView)findViewById(R.id.money_20);
        money_reset = (TextView)findViewById(R.id.money_reset);
        money_add = (TextView)findViewById(R.id.money_add);
        float_money = (TextView)findViewById(R.id.float_money);
        add_sign = (TextView)findViewById(R.id.add_sign);
        mContext = MainActivity.this;
        menu = (Button)findViewById(R.id.menu);
        recent_first_common_money = (TextView)findViewById(R.id.recent_first_common_money);
        recent_first_common_moneyReason = (TextView)findViewById(R.id.recent_first_common_moneyReason);
        recent_second_common_money = (TextView)findViewById(R.id.recent_second_common_money);
        recent_second_common_moneyReason = (TextView)findViewById(R.id.recent_second_common_moneyReason);
        data_base = new dataBase(mContext, "accounts.db", null, 1);
        small_money = "0";
        large_money = "0";
    }
    //监听
    private void widget_listen(){
//        home_choose_income.setOnClickListener(this);
//        home_choose_expend.setOnClickListener(this);
        set_date_today.setOnClickListener(this);
        add_one_day.setOnClickListener(this);
        sub_one_day.setOnClickListener(this);
        home_choose_mode.setOnClickListener(this);
        submit_save.setOnClickListener(this);
        extra_money.setOnClickListener(this);
        money_0.setOnClickListener(this);
        money_1.setOnClickListener(this);
        money_2.setOnClickListener(this);
        money_3.setOnClickListener(this);
        money_4.setOnClickListener(this);
        money_5.setOnClickListener(this);
        money_6.setOnClickListener(this);
        money_7.setOnClickListener(this);
        money_8.setOnClickListener(this);
        money_9.setOnClickListener(this);
        money_10.setOnClickListener(this);
        money_20.setOnClickListener(this);
        money_reset.setOnClickListener(this);
        money_add.setOnClickListener(this);
        recent_first_common_money.setOnClickListener(this);
        recent_first_common_moneyReason.setOnClickListener(this);
        recent_second_common_money.setOnClickListener(this);
        recent_second_common_moneyReason.setOnClickListener(this);
        home_input_money.setOnClickListener(this);
        menu.setOnClickListener(this);
        money_reset.setOnLongClickListener(this);
        money_add.setOnLongClickListener(this);
    }
    //默认日期为当前日期
    private void set_date(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        home_input_date.setText(date.format(new Date()).toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去titleBar
        setContentView(R.layout.activity_main);
        variable_init();
        widget_listen();
        set_date();
        home_input_reason.requestFocus();
        set_normal_view();
    }

    //手势传感器监控手势
    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            float x = e2.getX() - e1.getX();
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
@Override
protected void onResume(){
    day_away_from_today = 0;
    super.onResume();
    home_input_reason.requestFocus();
    set_normal_view();
    try{
        Intent intent = this.getIntent();
        record_id = Integer.parseInt(intent.getStringExtra("record_id"));
        home_input_date.setText(intent.getStringExtra("time")) ;
        home_input_reason.setText(intent.getStringExtra("reason"));
        home_input_money.setText(intent.getStringExtra("money"));
        old_day = home_input_date.getText().toString().substring(8,10);
        old_year = home_input_date.getText().toString().substring(0,4);
        old_month = home_input_date.getText().toString().substring(5,7);
        if(Integer.parseInt(intent.getStringExtra("money")) >= 0 ){
            home_input_money.setText(intent.getStringExtra("money"));
            home_choose_mode.setBackgroundColor(GREEN);
            home_choose_mode.setText("收");
            home_input_money.setTextColor(GREEN);
            float_money.setTextColor(GREEN);
        }else {
            home_input_money.setText(String.valueOf(0 - Integer.parseInt(intent.getStringExtra("money"))));
            home_choose_mode.setBackgroundColor(RED);
            home_choose_mode.setText("支");
            home_input_money.setTextColor(RED);
            float_money.setTextColor(RED);
        }
    }catch (Exception e){

    }
}
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    //右滑动转至历史记账展示页
    public void doResult(int action) {
        switch (action) {
            case RIGHT:
                break;
            case LEFT:
                if(record_id < 0){
                Intent intent = new Intent();
                intent.setClass(this, history_pageActivity.class);
                this.startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.recent_first_common_money:
            case R.id.recent_first_common_moneyReason:
                home_input_reason.setText(recent_first_common_moneyReason.getText());
                home_input_money.setText(recent_first_common_money.getText());
                extra_money.setText("0");
                extra_money.setVisibility(View.INVISIBLE);
                add_sign.setVisibility(View.INVISIBLE);
                break;
            case R.id.recent_second_common_money:
            case R.id.recent_second_common_moneyReason:
                home_input_reason.setText(recent_second_common_moneyReason.getText());
                home_input_money.setText(recent_second_common_money.getText());
                extra_money.setText("0");
                extra_money.setVisibility(View.INVISIBLE);
                add_sign.setVisibility(View.INVISIBLE);
                break;
            case R.id.money_0:
                input_money("0");
                break;
            case R.id.money_1:
                input_money("1");
                break;
            case R.id.money_2:
                input_money("2");
                break;
            case R.id.money_3:
                input_money("3");
                break;
            case R.id.money_4:
                input_money("4");
                break;
            case R.id.money_5:
                input_money("5");
                break;
            case R.id.money_6:
                input_money("6");
                break;
            case R.id.money_7:
                input_money("7");
                break;
            case R.id.money_8:
                input_money("8");
                break;
            case R.id.money_9:
                input_money("9");
                break;
            case R.id.money_10:
                home_input_money.setText("10");
                extra_money.setText("0");
                extra_money.setVisibility(View.INVISIBLE);
                add_sign.setVisibility(View.INVISIBLE);
                break;
            case R.id.money_20:
                home_input_money.setText("20");
                extra_money.setText("0");
                extra_money.setVisibility(View.INVISIBLE);
                add_sign.setVisibility(View.INVISIBLE);
                break;
            case R.id.money_reset:
                if(add_sign.getVisibility()== View.VISIBLE){
                    if(!extra_money.getText().toString().equals("0") && !extra_money.getText().toString().isEmpty()){
                        extra_money.setText(extra_money.getText().toString().substring(0, extra_money.getText().toString().length() - 1));
                    }
                    else{
                        extra_money.setText("0");
                    }
                }else{
                    if(!home_input_money.getText().toString().equals("0") && !home_input_money.getText().toString().isEmpty()){
                        home_input_money.setText(home_input_money.getText().toString().substring(0, home_input_money.getText().toString().length() - 1));
                    }else{
                        home_input_money.setText("0");
                    }
                }
                break;
            case R.id.add_one_day:
                ++ day_away_from_today;
                if(record_id >= 0){
                    home_input_date.setText(edit_set_time(day_away_from_today));
                }else {
                    home_input_date.setText(quick_set_time(day_away_from_today));
                }
                break;
            case R.id.sub_one_day:
                -- day_away_from_today;
                if(record_id >= 0){
                    home_input_date.setText(edit_set_time(day_away_from_today));
                }else {
                    home_input_date.setText(quick_set_time(day_away_from_today));
                }
                break;
            case R.id.home_input_money:
                break;
            case R.id.home_choose_mode:
                if(home_choose_mode.getText().toString().equals("支")) {
                    home_choose_mode.setBackgroundColor(GREEN);
                    home_choose_mode.setText("收");
                    home_input_money.setTextColor(GREEN);
                    float_money.setTextColor(GREEN);
                }
                else{
                    home_choose_mode.setBackgroundColor(RED);
                    home_choose_mode.setText("支");
                    home_input_money.setTextColor(RED);
                    float_money.setTextColor(RED);
                }
                break;
            case R.id.set_date_today:
                home_input_date.setText(quick_set_time(0));
                break;
            case R.id.submit_save:
                int temp_large = Integer.valueOf(home_input_money.getText().toString());
                int temp_small = Integer.valueOf(extra_money.getText().toString());
                home_input_money.setText(String.valueOf(temp_large + temp_small));
                date = home_input_date.getText().toString();
                reason = home_input_reason.getText().toString();
                money = 0;
                try {
                    money = Integer.parseInt(home_input_money.getText().toString());
                }catch (Exception e){
                    Toast.makeText(this, "金额不能为空！",Toast.LENGTH_SHORT).show();
                }
                if(reason.isEmpty() || money == 0){
                    Toast.makeText(this, "存在无效输入，请重新记录！",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(record_id >= 0){
                        alter_record();
                    }else {
                        if(home_choose_mode.getText().toString().equals("支"))
                            money = 0 - money;
                        data_base.save_on_person(" ", null, null);
                        data_base.save_on_event(reason);
                        data_base.save_on_record(" ", reason, date, money);
                        Toast.makeText(this, "数据存储成功，请查看记录！",Toast.LENGTH_SHORT).show();
                        set_normal_view();
                        extra_money.setText("0");
                        extra_money.setVisibility(View.INVISIBLE);
                        add_sign.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case R.id.money_add:
                if(add_sign.getVisibility()== View.VISIBLE){
                    temp_large = Integer.valueOf(home_input_money.getText().toString());
                    temp_small = Integer.valueOf(extra_money.getText().toString());
                    home_input_money.setText(String.valueOf(temp_large + temp_small));
                    extra_money.setText("0");
                }else {
                    add_sign.setVisibility(View.VISIBLE);
                    extra_money.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.menu:
                initPopWindow(view);
                break;
            default:
                break;
        }
    }
    //快捷获取日期
    private String quick_set_time(int i) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, i);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
   private String edit_set_time(int i){
       Date date;
       Calendar calendar = new GregorianCalendar();
       calendar.set(Calendar.YEAR, Integer.parseInt(old_year));
       calendar.set(Calendar.MONTH, Integer.parseInt(old_month) - 1);
       calendar.set(Calendar.DATE,Integer.parseInt(old_day));
       calendar.add(calendar.DATE, i);
       date = calendar.getTime();
       Log.i("see", "________" + old_year + "-" + old_month + "-" + old_day + "----" + String.valueOf(i));
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       String dateString = formatter.format(date);
       return dateString;
   }
    private void input_money(String s){
        if(add_sign.getVisibility() == View.VISIBLE){
            small_money = extra_money.getText().toString();
            small_money = small_money + s;
            int temp = Integer.parseInt(small_money);
            extra_money.setText(String.valueOf(temp));
        }else{
            large_money = home_input_money.getText().toString();
            large_money = large_money + s;
            int temp = Integer.parseInt(large_money);
            home_input_money.setText(String.valueOf(temp));
        }
    }
private void set_normal_view(){
    recent_first_common_money.setText(MainActivity.data_base.get_normal_data()[0]);
    recent_first_common_moneyReason.setText(MainActivity.data_base.get_normal_data()[1]);
    recent_second_common_money.setText(MainActivity.data_base.get_normal_data()[2]);
    recent_second_common_moneyReason.setText(MainActivity.data_base.get_normal_data()[3]);
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

    private void alter_record(){
        //保存被修改的记录
            if(home_choose_mode.getText().toString().equals("支"))
            money = 0 - money;
            MainActivity.data_base.save_on_person(" ", null, null);
            MainActivity.data_base.save_on_event(home_input_reason.getText().toString());
            MainActivity.data_base.update_on_record(record_id," ", home_input_reason.getText().toString(), home_input_date.getText().toString(), money);
            Toast.makeText(this, "修改成功，请查看记录！",Toast.LENGTH_SHORT).show();
            this.finish();
        }
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, null, false);
        Button derive;
        derive= (Button) view.findViewById(R.id.derive);
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
                Toast.makeText(MainActivity.this, "成功导出文件名:" + get_file_name, Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_base.delete_all_data();
                popWindow.dismiss();
            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()){
            case R.id.money_add:
                extra_money.setText("0");
                extra_money.setVisibility(View.INVISIBLE);
                add_sign.setVisibility(View.INVISIBLE);
            break;
            case R.id.money_reset:
                home_input_money.setText("0");
                extra_money.setText("0");
                extra_money.setVisibility(View.INVISIBLE);
                add_sign.setVisibility(View.INVISIBLE);
            default:
                break;
        }
        return true;
    }
}