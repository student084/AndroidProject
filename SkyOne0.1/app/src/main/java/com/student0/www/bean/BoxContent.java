package com.student0.www.bean;

import java.util.List;

/**
 * Created by willj on 2017/2/19.
 */

public class BoxContent {
    private String date;
    private List<Photo> photoList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
