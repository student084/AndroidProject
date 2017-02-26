package com.student0.www.imooc_flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.student0.www.view.FlowLayout;

public class MainActivity extends AppCompatActivity {

    private FlowLayout mFlowLayout;

    private String[] mVals = new String[]{
      "MainActivity","onCreate","Bundle","setContentView","String","class",
            "MainActivity","setContentView","String","class",
            "MainActivity","onCreate","Bundle","setContentView","String","class",
            "MainActivity","onCreate","Bundle","setContentView","String","class"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlowLayout = (FlowLayout) findViewById(R.id.id_flowlayout);
        initData(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void initData(int width, int height){
//        for (int i = 0; i < mVals.length; i ++){
//            Button button = new Button(this);
//           ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(width, height);
//            button.setText(mVals[i]);
//            mFlowLayout.addView(button,layoutParams);
//        }
//------------------------------l---------------------------------------------
                LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < mVals.length; i ++){
          TextView tv =  (TextView) layoutInflater.inflate(R.layout.tv, mFlowLayout, false);
            tv.setText(mVals[i]);
            mFlowLayout.addView(tv);
        }
        //------------------------------------
    }
}
