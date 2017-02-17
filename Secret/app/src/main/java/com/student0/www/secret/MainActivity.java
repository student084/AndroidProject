package com.student0.www.secret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.student0.www.atys.AtyLogin;
import com.student0.www.atys.AtyTimeLine;
import com.student0.www.ld.MyContacts;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println(MyContacts.getContactsJSONString(this));
        //页面的跳转
//        String token = Config.getCachedToken(this);

        //startActivity(new Intent(this, AtyTimeLine.class));
//        if(token != null){
//            Intent i = new Intent(this, AtyTimeLine.class);
//            i.putExtra(Config.KEY_TOKEN, token);
//            startActivity(i);
//        }else{
//            startActivity(new Intent(this, AtyLogin.class));
//        }
//        this.finish();
    }
}
