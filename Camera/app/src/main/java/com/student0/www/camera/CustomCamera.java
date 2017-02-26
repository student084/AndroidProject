package com.student0.www.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by willj on 2017/2/24.
 */

public class CustomCamera extends Activity implements SurfaceHolder.Callback{

    private Camera camera;

    private SurfaceView mPreView;

    private SurfaceHolder surfaceHolder;

    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File tempFile = new File("/sdcard/temp.png");
            try {

                FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                fileOutputStream.write(data);
                fileOutputStream.close();

                Intent intent = new Intent(CustomCamera.this, ResultAty.class);
                intent.putExtra("picPath", tempFile.getAbsolutePath());
                startActivity(intent);
                CustomCamera.this.finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);

        mPreView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = mPreView.getHolder();
        surfaceHolder.addCallback(this);

        mPreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击屏幕自动对焦，不进行回调等
                camera.autoFocus(null);
            }
        });
    }

    public void capture(View view){
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewFormat(ImageFormat.JPEG);
        parameters.setPictureSize(800 , 400);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        camera.autoFocus(new Camera.AutoFocusCallback(){
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success){
                    camera.takePicture(null, null,pictureCallback );
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (camera == null){
            camera = getCamera();
            if(surfaceHolder != null){
                setStartPreview(camera, surfaceHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        releaseCamera();
    }

    /**
     * 获取Camera对象
     * */
    private Camera getCamera(){
        Camera camera;
        try {
            camera = Camera.open();
        }catch (Exception e){
            camera = null;
            e.printStackTrace();
        }

        return camera;
    }
    /**
     * 开始预览相机内容
     * 将Surface的内容数据与Camera的数据绑定，实质就是链接Camera与SurfaceHolder
     * */
    private void setStartPreview(Camera camera, SurfaceHolder holder){
        try {
            camera.setPreviewDisplay(holder);
            //相机默认为横屏
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //释放相机资源
    private void releaseCamera(){
        if (camera != null){
            //取消关联
            camera.setPreviewCallback(null);
            //停止取景
            camera.stopPreview();
            //释放资源
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreview(camera, surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.stopPreview();
        setStartPreview(camera, surfaceHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }
}
