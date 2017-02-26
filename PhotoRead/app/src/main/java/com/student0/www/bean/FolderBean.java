package com.student0.www.bean;

/**
 * Created by willj on 2017/2/21.
 */

public class FolderBean {
//当前文件夹的路径
    private String dir;
    private String firstImgPath;
    private String name;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        int lastIndexOf = this.dir.lastIndexOf("/");
        this.name = this.dir.substring(lastIndexOf);
    }

    public String getFirstImgPath() {
        return firstImgPath;
    }

    public void setFirstImgPath(String firstImgPath) {
        this.firstImgPath = firstImgPath;
    }

    public void setName(String name) {
        this.name = name;
    }
}
