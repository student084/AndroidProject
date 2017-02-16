package com.student0.www.skyone;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

import other.bean.User;
import other.dao.UserDao;
import other.imp.UserDaoImp;
import other.util.PostUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    //控件变量
    private EditText username;
    private EditText password;

    private Button submit;
    private Button loginOut;

    private void varInit(){
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        loginOut = (Button)findViewById(R.id.loginOut);
    }

    private void objectListener(){
        submit.setOnClickListener(this);
        loginOut.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // variable init
        varInit();
        objectListener();
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        User user = new User();
        user.setUserName(username.getText().toString());
        user.setPassWord(password.getText().toString());
        UserDao uDao = new UserDaoImp();
        switch (v.getId()){
            case R.id.submit:
                Log.i("1","onClick......................"+ user.getUserName());
                //login in
                //send the username and password to the Service
                if (!user.getUserName().isEmpty()&& !user.getPassWord().isEmpty()){
                    //进度调试(Context, 标题, 内容)
                    //配置后的标准写法为show(Activity.this, getResources().getString(R.string.name), getResources().getString(R.string.name2))
                    final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Connection", "Connecting to server");
                    new PostUtils(user, new PostUtils.SuccessCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            //进度条管理
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "success login", Toast.LENGTH_LONG).show();
                        }
                    }, new PostUtils.FailCallback() {
                        @Override
                        public void onFail() {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "fail to login", Toast.LENGTH_LONG).show();

                        }
                    });
                }else{
                    Toast.makeText(this, R.string.illegal_input, Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.loginOut:
                //login out
                // send the id ,and destroy the session
                uDao.loginOut(user.getUserName());
                break;
            default:
                break;
        }
        //Toast.makeText(this, resultMsg, Toast.LENGTH_SHORT).show();
    }
}
