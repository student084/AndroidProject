package com.student0.www.ld;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.student0.www.secret.Config;
import com.student0.www.tools.MD5Tool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by willj on 2017/2/16.
 */

public class MyContacts {

    public static String getContactsJSONString(Context context){
        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        String phoneNum;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        while (c.moveToNext()){
            phoneNum = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if(phoneNum.substring(0,3).equals("+86")){
                phoneNum = phoneNum.substring(3);
            }
            jsonObject = new JSONObject();
            try {
                jsonObject.put(Config.KEY_PHONE_MD5, MD5Tool.md5(phoneNum));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
           // System.out.println(phoneNum);
        }
        return jsonArray.toString();
    }
}
