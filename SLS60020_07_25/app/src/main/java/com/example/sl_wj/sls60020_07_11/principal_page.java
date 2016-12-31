package com.example.sl_wj.sls60020_07_11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;


public class principal_page extends Activity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private Button principal_choose_ok;
    private TextView principal_add;
    private TextView principal_back_2_home;
    private ListView principal_listView;
    private String be_choose_person;
    //变量初始化
    private void variable_init(){
        principal_choose_ok = (Button)findViewById(R.id.principal_choose_ok);
        principal_add = (TextView)findViewById(R.id.principal_add);
        principal_back_2_home = (TextView)findViewById(R.id.principal_back_2_home);
        principal_listView = (ListView)findViewById(R.id.principal_listView);
    };
    //控件事件监听
    private void widget_listen(){
        principal_choose_ok.setOnClickListener(this);
        principal_add.setOnClickListener(this);
        principal_back_2_home.setOnClickListener(this);
        principal_listView.setOnItemLongClickListener(this);
        principal_listView.setOnItemClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去titleBar
        setContentView(R.layout.activity_principal_page);
        variable_init();
        widget_listen();
        MainActivity.data_base.list_person(this, principal_listView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.principal_choose_ok:
                pass_information();
                this.finish();
                break;
            case R.id.principal_add:
                turn_to_staff("add", -1, null, null);
                break;
            case R.id.principal_back_2_home:
                pass_information();
                this.finish();
            default:
                break;
        }
    }
    //跳转至职员信息界面；
    private void turn_to_staff(String way_to_staff, int id, String name, String phone){
        Intent intent = new Intent();
        intent.setClass(this, staff_information.class);
        intent.putExtra("way_to_staff", way_to_staff);
        if(id >= 0){
            String s_id = String.valueOf(id);
            intent.putExtra("person_id", s_id);
            intent.putExtra("person_name",name);
            intent.putExtra("person_phone", phone);
        }
        this.startActivity(intent);
    }
    private void pass_information(){
        //将消息传给主页的人员选项；
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("be_choose_person", be_choose_person);
        this.startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        ListView listView = (ListView)adapterView;
        HashMap<String, String> map = (HashMap<String, String>)listView.getItemAtPosition(i);
        int id = Integer.parseInt(map.get("person_id").toString());
        String name = map.get("person_name").toString();
        String phone = map.get("person_phone").toString();
        turn_to_staff("change", id, name, phone);
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ListView listView = (ListView)adapterView;
        HashMap<String, String> map = (HashMap<String, String>)listView.getItemAtPosition(i);
        String principal_id = map.get("person_id").toString();
        String principal_name = map.get("person_name").toString();
        String principal_phone = map.get("person_phone").toString();
        be_choose_person = principal_name;
    }
    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.data_base.list_person(this, principal_listView);
    }
}
