package com.student0.www.atys;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.student0.www.net.Comment;
import com.student0.www.net.GetComment;
import com.student0.www.secret.Config;
import com.student0.www.secret.R;
import com.student0.www.tools.AtyMessageCommentListAdapter;

import java.util.List;

/**
 * Created by willj on 2017/2/16.
 */

public class AtyMessage extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_message);

        adapter = new AtyMessageCommentListAdapter(this);
        setListAdapter(adapter);
        Intent data = getIntent();
        phone_md5 = data.getStringExtra(Config.KEY_PHONE_MD5);
        msgId = data.getStringExtra(Config.KEY_MSG_ID);
        message = data.getStringExtra(Config.KEY_MSG);
        token = data.getStringExtra(Config.KEY_TOKEN);
        tvMessage = (TextView)findViewById(R.id.tvMessage);

        tvMessage.setText(message);

        final ProgressDialog progressDialog = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
        new GetComment(phone_md5, token, msgId, 1, 20, new GetComment.SuccessCallback() {
            @Override
            public void onSuccess(String msgId, int page, int perpage, List<Comment> comments) {
                progressDialog.dismiss();
                adapter.addAll(comments);
            }
        }, new GetComment.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                progressDialog.dismiss();

                if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyMessage.this, AtyLogin.class));
                    finish();
                }else {
                    Toast.makeText(AtyMessage.this, R.string.fail_to_get_comment,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String phone_md5;
    private String message;
    private String msgId;
    private String token;
    private TextView tvMessage;

    private AtyMessageCommentListAdapter adapter;
}
