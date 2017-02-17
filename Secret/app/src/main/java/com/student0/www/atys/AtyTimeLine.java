package com.student0.www.atys;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import com.student0.www.ld.MyContacts;
import com.student0.www.net.UploadContacts;
import com.student0.www.secret.Config;
import com.student0.www.secret.R;
import com.student0.www.tools.MD5Tool;

/**
 * Created by willj on 2017/2/16.
 */

public class AtyTimeLine extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);

        phone = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
        token = getIntent().getStringExtra(Config.KEY_TOKEN);
        phone_md5 = MD5Tool.md5(phone);
        new UploadContacts(phone_md5, token, MyContacts.getContactsJSONString(this), new UploadContacts.SuccessCallback() {
            @Override
            public void onSuccess() {
                loadMessage();
            }
        }, new UploadContacts.FailCallback() {
            @Override
            public void onFail(int errorCode) {
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
    }

    private String phone, token, phone_md5;
}
