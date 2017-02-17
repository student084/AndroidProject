package com.student0.www.net;

/**
 * Created by willj on 2017/2/17.
 */

public class Message {

    public Message(String msgId, String msg, String phone_md5) {
        this.msgId = msgId;
        this.msg = msg;
        this.phone_md5 = phone_md5;
    }

    private String msgId = null;
    private String msg = null;
    private String phone_md5 = null;

    public String getMsg() {
        return msg;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getPhone_md5() {
        return phone_md5;
    }

}
