package com.student0.www.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static int REQ_1 = 1;
    private static int REQ_2 = 2;
    private ImageView imageView;
    private String mFilePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取SD卡的路径
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        mFilePath = mFilePath  + "/" + "temp.png";
    }

    public void startCamera1(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQ_1);

        imageView = (ImageView) findViewById(R.id.iv);
    }

    public void startCamera2(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageView = (ImageView) findViewById(R.id.iv);
        //修改系统默认的OUTPUT
        Uri photoUri = Uri.fromFile(new File(mFilePath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, REQ_2);

    }

    public void CustomCamera(View view){
        startActivity(new Intent(this, CustomCamera.class));
    }

/**
 * get camera result
 *
 * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if (requestCode == REQ_1){
                //方式一：获得缩略图
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);
            }else if (requestCode == REQ_2){
                //方式二：获得原图
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(mFilePath);
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);

                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    if (fileInputStream != null)
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                       // e.printStackTrace();
                    }
                }
            }
        }
    }
}
