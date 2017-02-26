package com.student0.www.photoread;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.student0.www.bean.FolderBean;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private ImageAdapter mImgAdapter;
    //GridView的数据集
    private List<String> mImgs;

    private RelativeLayout mBottomLy;
    private TextView mDirName;
    private TextView mDirCount;

    private File mCurrentDir;
    private int mMaxCount;
    private ListImageDirPopupWindow mDirPopupWindow;

    ProgressDialog mProgressDialog;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x110){
                mProgressDialog.dismiss();

                //绑定数据到View中
                data2View();

            }

        }
    };

    private void initDirPopupWindow() {
        mDirPopupWindow = new ListImageDirPopupWindow(this, mFolderBeans);

        //弹出框消失后将后面变亮
        mDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });

    }
/**
 *
 * 内容变亮，透明度增加
 * */
    private void lightOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }

    private void data2View() {
        if(mCurrentDir == null){
            Toast.makeText(this, "There is no photo finded in you scard ...", Toast.LENGTH_SHORT).show();
            return;
        }
        //将数组包装成list返回
        //File.list():Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.
        mImgs = Arrays.asList(mCurrentDir.list());

        mImgAdapter = new ImageAdapter(this,mImgs, mCurrentDir.getAbsolutePath());
        mGridView.setAdapter(mImgAdapter);

        mDirCount.setText(mMaxCount + "");
        mDirName.setText(mCurrentDir.getName());
    }

    private List<FolderBean> mFolderBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDirPopupWindow();
        initView();
        initDatas();
        initEvent();
    }
/**
 * 利用ContentProvider扫描手机中的所有图片
 * */
    private void initDatas(){

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "当前存储卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        mProgressDialog = mProgressDialog.show(this,null, "Loading ...");
        new Thread(){
            @Override
            public void run() {
                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                ContentResolver cr = MainActivity.this.getContentResolver();


                Cursor cursor = cr.query(mImgUri, null,
                        MediaStore.Images.Media.MIME_TYPE + " = ? or "
                                + MediaStore.Images.Media.MIME_TYPE + " = ? or "
                        + MediaStore.Images.Media.MIME_TYPE + " = ? ",
                        new String[]{"image/jpeg", "image/png", "image/jpg"},
                        MediaStore.Images.Media.DATE_MODIFIED );
//防止重复扫描
                Set<String> mDirPaths = new HashSet<String>();
                while (cursor.moveToNext()){
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null){
                        continue;
                    }
                    String dirPath = parentFile.getAbsolutePath();

                    FolderBean folderBean = null;

                    if (mDirPaths.contains(dirPath)){
                        continue;
                    }else{
                        mDirPaths.add(dirPath);

                        folderBean = new FolderBean();
                        folderBean.setDir(dirPath);
                        folderBean.setFirstImgPath(path);
                    }

                    if (parentFile.list() == null)
                        continue;
                    //获取该文件夹中含有图片的数量
                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            if(name.endsWith(".jpg")
                                    || name.endsWith(".jpeg")
                                    ||name.endsWith(".png"))
                                return true;
                            return false;
                        }
                    }).length;


                    folderBean.setCount(picSize);
                    mFolderBeans.add(folderBean);
                    if (picSize > mMaxCount){
                        mMaxCount = picSize;
                        mCurrentDir = parentFile;
                    }
                }
                cursor.close();
                //通知Handler完成
                mHandler.sendEmptyMessage(0x110);
            }
        }.start();
    }

    private void initView(){

        mGridView = (GridView) findViewById(R.id.id_gridView);
        mBottomLy = (RelativeLayout)findViewById(R.id.id_bottom_ly);
        mDirName = (TextView)findViewById(R.id.id_dir_name);
        mDirCount = (TextView)findViewById(R.id.id_dir_count);

    }

    private void initEvent(){
        mBottomLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // mDirPopupWindow.setAnimationStyle(ani);
                //设置显示的位置
                mDirPopupWindow.showAsDropDown(mBottomLy, 0 , 0);
                //内容背景变暗
                lightOff();
            }
        });

        mDirPopupWindow.setOnDirSelectedListener(new ListImageDirPopupWindow.OnDirSelectListener() {
            @Override
            public void onSelected(FolderBean folderBean) {
                mCurrentDir = new File(folderBean.getDir());

                mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        if(name.endsWith(".jpg")
                                || name.endsWith(".jpeg")
                                ||name.endsWith(".png"))
                            return true;
                        return false;
                    }
                }));

                mImgAdapter = new ImageAdapter(MainActivity.this, mImgs, mCurrentDir.getAbsolutePath());
                mGridView.setAdapter(mImgAdapter);

                mDirCount.setText(mImgs.size() + "");
                mDirName.setText(folderBean.getDir().substring(folderBean.getDir().lastIndexOf("/") + 1));

                mDirPopupWindow.dismiss();
            }
        });
    }

    private void lightOff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }


}
