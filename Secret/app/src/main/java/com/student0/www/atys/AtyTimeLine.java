package com.student0.www.atys;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.student0.www.ld.MyContacts;
import com.student0.www.net.Message;
import com.student0.www.net.TimeLine;
import com.student0.www.net.UploadContacts;
import com.student0.www.secret.Config;
import com.student0.www.secret.R;
import com.student0.www.tools.AtyTimelineMessageListAdapter;
import com.student0.www.tools.MD5Tool;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by willj on 2017/2/16.
 */

public class AtyTimeLine extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);

        adapter = new AtyTimelineMessageListAdapter(this);
        setListAdapter(adapter);
        phone = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
        token = getIntent().getStringExtra(Config.KEY_TOKEN);
        phone_md5 = MD5Tool.md5(phone);

        final ProgressDialog progressDialog = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
        new UploadContacts(phone_md5, token, MyContacts.getContactsJSONString(this), new UploadContacts.SuccessCallback() {
            @Override
            public void onSuccess() {
                loadMessage();
                progressDialog.dismiss();
            }
        }, new UploadContacts.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                progressDialog.dismiss();
                if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeLine.this, AtyLogin.class));
                    finish();
                }else{
                    loadMessage();
                }
            }
        });
    }

    private void loadMessage(){
        System.out.println(">>>>>>>>>>>>loadMessage");
        final ProgressDialog progressDialog = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));

        new TimeLine(phone_md5, token, 1, 20, new TimeLine.SuccessCallback() {
            @Override
            public void onSuccess(int page, int perpage, List<Message> timeline) {
                progressDialog.dismiss();

                adapter.addAll(timeline);
            }
        }, new TimeLine.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                progressDialog.dismiss();

                if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeLine.this, AtyLogin.class));
                    finish();
                }else{
                    Toast.makeText(AtyTimeLine.this, R.string.fail_to_load_data, Toast.LENGTH_LONG).show();
                }

            }
        });
   }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Message message = adapter.getItem(position);

        Intent intent = new Intent(this, AtyMessage.class);
        intent.putExtra(Config.KEY_MSG, message.getMsg());
        intent.putExtra(Config.KEY_MSG_ID, message.getMsgId());
        intent.putExtra(Config.KEY_PHONE_MD5, message.getPhone_md5());
        intent.putExtra(Config.KEY_TOKEN, token);
        startActivity(intent);
    }

    private String phone, token, phone_md5;
    private AtyTimelineMessageListAdapter adapter;
}
