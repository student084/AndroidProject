package com.student0.www.bean;

/**
 * Created by willj on 2017/2/18.
 */

public class Picture {
    private String data;
    private String resource;
    private int id;

    public Picture(String data, String resource, int id) {
        this.data = data;
        this.resource = resource;
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public String getResource() {
        return resource;
    }

    public int getId() {
        return id;
    }
}
