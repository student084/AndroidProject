package com.example.sl_wj.sls60020_07_11;

import android.app.VoiceInteractor;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by SL_wj on 2016/7/12.
 */
public class dataBase extends SQLiteOpenHelper {
    private String[] tableName = {"person", "record", "event"};
    private SQLiteDatabase writeSQL = this.getReadableDatabase();
    public dataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
//创建表格
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String create_table_person = "CREATE TABLE " +
            tableName[0] +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR[8]," +
            "phone VARCHAR[11]," +
            "position VARCHAR[6])";
        String create_table_record = "CREATE TABLE " +
                tableName[1] +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "staff_id INTEGER, " +
                "event_id INTEGER, " +
                "time VARCHAR[11], " +
                "money INTEGER)";
        String create_table_event = "CREATE TABLE " +
                tableName[2] +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "description STRING)";
        sqLiteDatabase.execSQL(create_table_person);
        sqLiteDatabase.execSQL(create_table_record);
        sqLiteDatabase.execSQL(create_table_event);

        //创建视图
        //最近账况分析视图
        //利用循环为每个时间日期建立3个视图支出，收入， 总视图视图名字为view_标签_时间（time， reason, principal, money, record_id）
            }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
    private void refresh_table(){
        writeSQL.execSQL("DROP TABLE person ");
        writeSQL.execSQL("DROP TABLE record ");
        writeSQL.execSQL("DROP TABLE event ");
        String create_table_person = "CREATE TABLE " +
                tableName[0] +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR[8]," +
                "phone VARCHAR[11]," +
                "position VARCHAR[6])";
        String create_table_record = "CREATE TABLE " +
                tableName[1] +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "staff_id INTEGER, " +
                "event_id INTEGER, " +
                "time VARCHAR[11], " +
                "money INTEGER)";
        String create_table_event = "CREATE TABLE " +
                tableName[2] +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "description STRING)";
        writeSQL.execSQL(create_table_person);
        writeSQL.execSQL(create_table_record);
        writeSQL.execSQL(create_table_event);
    }
    public void save_on_person(String name, String phone, String position){
        Cursor judge_cursor;
                judge_cursor = writeSQL.rawQuery(
                "SELECT * " +
                "FROM person " +
                "WHERE name = ?", new String[]{name});
        Log.i("11", String.valueOf(judge_cursor.getCount()) + name);
        //新数据不存在则存储，否则提示
        if (judge_cursor.getCount() == 0){

            writeSQL.execSQL("INSERT INTO person(name, phone, position) " +
                            " VALUES(?, ?, ?) ",
                    new String[]{ name, phone ,position});
            Log.i("1", "人员存储成功！");
        }else{
            Log.i("2","人员数据已经存在");
        }
        judge_cursor.close();
    }
    public void update_on_person(int id, String name, String phone, String position){
        writeSQL.execSQL("UPDATE person SET name = ?, phone = ?, position = ? WHERE id = ?",
                new String[]{name, phone, position, String.valueOf(id)});
    }

   //人员信息展示数据获取
    private List<Map<String, String>> get_person_data(){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Cursor cursor_person ;
        cursor_person = writeSQL.rawQuery("SELECT * FROM person",null);
        cursor_person.moveToFirst();
        while (!cursor_person.isAfterLast()){
            Map<String, String> map = new HashMap<String, String>();
            String person_id        = cursor_person.getString(0).toString();
            String person_name      = cursor_person.getString(1).toString();

            String person_phone;
            String person_position;
            try {
               person_phone     = cursor_person.getString(2).toString();
               person_position   = cursor_person.getString(3).toString();
            }catch (Exception e){
                person_phone     ="";
                 person_position   ="";
            }

            map.put("person_id", person_id);
            map.put("person_name", person_name);
            map.put("person_phone", person_phone);
            map.put("person_position", person_position);
            list.add(map);
            cursor_person.moveToNext();
        }
        return list;
    }
    //导出数据获取
    public String get_drive_date() {
        Cursor cursor;
        String temp = new String();
        cursor = writeSQL.rawQuery("SELECT * FROM new_view", null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast() && cursor.getCount() > 0) {
            Map<String, String> map = new HashMap<String, String>();
            String time = cursor.getString(0).toString();
            String description = cursor.getString(1).toString();
            String principal = cursor.getString(2).toString();
            String money = cursor.getString(3).toString();
            String record_id = cursor.getString(4).toString();
            temp =temp + record_id + ",\t" + time +",\t" + description + ",\t" + money +"\r\n" ;
            cursor.moveToNext();
        }
        return temp;
    }
    //新版展示记录使用数据获取
    public List<Map<String, String>> new_get_list_data() {
        Cursor cursor;
            List<Map<String, String>> temp_map_list = new ArrayList<Map<String, String>>() ;
        String create_view =
                "CREATE VIEW new_view " +
                        "AS SELECT time, description, name, money, record.id " +
                        "FROM person, record, event " +
                        "WHERE record.staff_id = person.id AND record.event_id = event.id ";
        try{
            writeSQL.execSQL(create_view);
        }catch (Exception e){

        }
                cursor = writeSQL.rawQuery("SELECT * FROM new_view", null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast() && cursor.getCount() > 0) {
                    Map<String, String> map = new HashMap<String, String>();
                    String time = cursor.getString(0).toString();
                    String description = cursor.getString(1).toString();
                    String principal = cursor.getString(2).toString();
                    String money = cursor.getString(3).toString();
                    String record_id = cursor.getString(4).toString();
                    map.put("time", time);
                    map.put("reason", description);
                    map.put("principal", principal);
                    map.put("money", money);
                    map.put("record_id", record_id);
                    temp_map_list.add(map);
                    cursor.moveToNext();
                }
        return temp_map_list;
            }
   public void new_list_history(Context context, ListView listView){
       SimpleAdapter adapter_key;
       adapter_key = new SimpleAdapter(context, new_get_list_data(),
               R.layout.home_recent_detail, new String[]{"time","reason", "money"},
               new int[]{R.id.home_recent_detail_date, R.id.home_recent_detail_reason, R.id.home_recent_detail_money});
       listView.setAdapter(adapter_key);
   }
    //在listView中展示人员
    public void list_person(Context context, ListView listView){
        SimpleAdapter adapter;
        adapter = new SimpleAdapter(context, get_person_data(), R.layout.principal_detail, new String[]{"person_name", "person_phone"}, new int[]{R.id.principal_name, R.id.principal_phone});
        listView.setAdapter(adapter);
    }

    //根据id与tableName删除表格中的相关数据
    public void delete_on_table(int id, String tableName){
        writeSQL.execSQL("DELETE  FROM "+ tableName +" WHERE id = ?", new Integer[]{id});
    }
    public void delete_all_data(){
        //"person", "record", "event" TRUNCATE TABLE name
      refresh_table();
    }
    public void save_on_record(String principal, String description, String time, int money){
        int staff_id;
        int event_id;
        Cursor cursor_staff;
        Cursor cursor_event;
        cursor_staff = writeSQL.rawQuery("SELECT id FROM person WHERE name = ?", new String[]{principal});
        cursor_event = writeSQL.rawQuery("SELECT id FROM event WHERE description = ?", new String[]{description});
        cursor_staff.moveToFirst();
        cursor_event.moveToFirst();
        event_id = Integer.parseInt(cursor_event.getString(0));
        staff_id = Integer.parseInt(cursor_staff.getString(0));
        writeSQL.execSQL("INSERT INTO record(staff_id, event_id, time, money) VALUES(?, ?, ?, ?)",
                new String[]{String.valueOf(staff_id), String.valueOf(event_id), time, String.valueOf(money)});
    }

    public void save_on_event( String description) {
        Cursor judge_cursor;
        judge_cursor = writeSQL.rawQuery(
                        "SELECT * " +
                        "FROM event " +
                        "WHERE description = ?", new String[]{description});
        //新数据不存在则存储，否则提示
        if (judge_cursor.getCount() == 0) {
            writeSQL.execSQL("INSERT INTO event(description) " +
                            "VALUES(?)",
                    new String[]{description});
        }
    }
    public void update_on_event(int id ,String description){
        writeSQL.execSQL("UPDATE event SET description = ? WHERE id = ?", new String[]{description, String.valueOf(id)});
    }
    public void update_on_record(int id, String principal, String description, String time, int money){
        int staff_id;
        int event_id;
        Cursor cursor_staff;
        Cursor cursor_event;
        save_on_event(description);
        save_on_person(principal, null, null);
        cursor_staff = writeSQL.rawQuery("SELECT id FROM person WHERE name = ?", new String[]{principal});
        cursor_event = writeSQL.rawQuery("SELECT id FROM event WHERE description = ?", new String[]{description});
        cursor_staff.moveToFirst();
        cursor_event.moveToFirst();
        event_id = Integer.parseInt(cursor_event.getString(0));
        staff_id = Integer.parseInt(cursor_staff.getString(0));
        writeSQL.execSQL("UPDATE record SET staff_id = ?, event_id = ?, time = ?, money = ? WHERE id = ?",new String[]{String.valueOf(staff_id), String.valueOf(event_id), time, String.valueOf(money), String.valueOf(id)});
    }
    public String[] get_normal_data(){
        String[] s;
        s = new String[4];
        s[0] = "0";
        s[1] = "待输入";
        s[2] = "0";
        s[3] = "待输入";
        Cursor cursor;
        String create_view =
                "CREATE VIEW new_view " +
                        "AS SELECT time, description, name, money, record.id " +
                        "FROM person, record, event " +
                        "WHERE record.staff_id = person.id AND record.event_id = event.id ";
        try{
            writeSQL.execSQL(create_view);
        }catch (Exception e){

        }
        cursor = writeSQL.rawQuery("SELECT * ,COUNT(money) AS count FROM new_view GROUP BY money ORDER BY count DESC", null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast() && cursor.getCount() > 0){
           s[1] = cursor.getString(1).toString();
           s[0] = cursor.getString(3).toString();
            cursor.moveToNext();
        }
        if (!cursor.isAfterLast() && cursor.getCount() > 0){
            s[3] = cursor.getString(1).toString();
            s[2] = cursor.getString(3).toString();
        }
            return s;
    }
}
