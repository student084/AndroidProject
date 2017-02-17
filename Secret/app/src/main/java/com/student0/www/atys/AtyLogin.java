package com.student0.www.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.student0.www.net.GetCode;
import com.student0.www.net.Login;
import com.student0.www.secret.Config;
import com.student0.www.secret.R;
import com.student0.www.tools.MD5Tool;

/**
 * Created by willj on 2017/2/16.
 */

public class AtyLogin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        etPhone = (EditText)findViewById(R.id.etPhoneNum);
        etCode =(EditText)findViewById(R.id.etCode);
        findViewById(R.id.btnGetCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPhone.getText())){
                    Toast.makeText(AtyLogin.this, R.string.phone_number_can_not_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                final ProgressDialog pregressDioalog = ProgressDialog.show(AtyLogin.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
                new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallback() {
                    @Override
                    public void onSuccess(String string) {
                        pregressDioalog.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.success_to_get_code, Toast.LENGTH_LONG).show();
                    }
                }, new GetCode.FailCallback() {
                    @Override
                    public void onFail() {
                        pregressDioalog.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.fail_to_get_code, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

       findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (TextUtils.isEmpty(etPhone.getText())){
                   Toast.makeText(AtyLogin.this, R.string.phone_number_can_not_be_empty, Toast.LENGTH_LONG).show();
                   return;
               }
               if (TextUtils.isEmpty(etCode.getText())){
                   Toast.makeText(AtyLogin.this, R.string.code_can_not_be_empty, Toast.LENGTH_LONG).show();
                   return;
               }

               final ProgressDialog progressDialog = ProgressDialog.show(AtyLogin.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
               new Login(MD5Tool.md5(etPhone.getText().toString()), etCode.getText().toString(), new Login.SuccessCallback() {
                   @Override
                   public void onSuccess(String token) {
                       Config.cacheToken(AtyLogin.this, token);
                       Config.cachePhoneNumber(AtyLogin.this, etPhone.getText().toString());

                       Intent i = new Intent(AtyLogin.this, AtyTimeLine.class);
                       i.putExtra(Config.KEY_TOKEN, token);
                       i.putExtra(Config.KEY_PHONE_NUM, etPhone.getText().toString());
                       progressDialog.dismiss();
                       startActivity(i);
                       finish();
                   }
               }, new Login.FailCallback() {
                   @Override
                   public void onFail() {
                       progressDialog.dismiss();
                       Toast.makeText(AtyLogin.this, R.string.fail_to_login, Toast.LENGTH_LONG).show();
                   }
               });
           }
       });
    }

    private EditText etPhone;
    private EditText etCode;
}
