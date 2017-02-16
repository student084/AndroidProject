package com.student0.www.atys;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

import com.student0.www.secret.R;

/**
 * Created by willj on 2017/2/16.
 */

public class AtyMessage extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.aty_message);
    }
}
