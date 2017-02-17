package com.student0.www.secret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.student0.www.atys.AtyLogin;
import com.student0.www.atys.AtyTimeLine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // System.out.println(MyContacts.getContactsJSONString(this));
        //页面的跳转
        String token = Config.getCachedToken(this);
        String phone_num = Config.getCachedPhoneNumber(this);

        startActivity(new Intent(this, AtyTimeLine.class));
        if(token != null && phone_num != null){
            Intent i = new Intent(this, AtyTimeLine.class);
            i.putExtra(Config.KEY_TOKEN, token);
            i.putExtra(Config.KEY_PHONE_NUM, phone_num);
            startActivity(i);
        }else{
            startActivity(new Intent(this, AtyLogin.class));
        }
        this.finish();
    }
}
