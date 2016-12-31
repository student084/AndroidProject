package com.example.sl_wj.sls60020_07_11;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SL_wj on 2016/7/19.
 */
public class FileHelper {
    private Context mContext;
    public FileHelper() {
    }
    public FileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }
    public void save(String file_name, String file_content) {
        //创建出来的文件只能被本应用访问,还会覆盖原文件哦
        File dest = new File( File.separator + "sdcard" + File.separator + file_name + ".txt");
        dest.setWritable(true);
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(dest, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("ni", "路径不存在" + dest);
        }
        try {
            output.write(file_content.getBytes());  //将String字符串以字节流的形式写入到输出流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();         //关闭输出流
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if(!file.mkdirs()) {
            Log.e("mission", "Directory not created");
        }
        return file;
    }
}
