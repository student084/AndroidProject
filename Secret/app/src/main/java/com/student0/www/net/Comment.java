package com.student0.www.net;

/**
 * Created by willj on 2017/2/17.
 */

public class Comment {
    private String content;
    private String phone_md5;

    public Comment(String content, String phone_md5) {
        this.content = content;
        this.phone_md5 = phone_md5;
    }

    public String getContent() {
        return content;
    }

    public String getPhone_md5() {
        return phone_md5;
    }
}
