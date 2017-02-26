package com.student0.www.photoread;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.student0.www.bean.FolderBean;
import com.student0.www.util.ImageLoader;

import java.util.List;

/**
 * Created by willj on 2017/2/22.
 */

public class ListDirAdapter extends ArrayAdapter<FolderBean> {
    private LayoutInflater mInflater;

    private List<FolderBean> mDatas;

    public ListDirAdapter(Context context, List objects) {
        super(context, 0, objects);

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_popup, parent, false);

            holder.mImg = (ImageView)convertView.findViewById(R.id.id_id_dir_item_image);
            holder.mDirName = (TextView) convertView.findViewById(R.id.id_dir_item_name);
            holder.mDirCount = (TextView)convertView.findViewById(R.id.id_dir_item_count);
            holder.mImg.setImageResource(R.mipmap.picture);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        FolderBean bean = getItem(position);
        ImageLoader.getInstance(4, ImageLoader.Type.LIFO).loadImage(bean.getFirstImgPath(), holder.mImg);

        holder.mDirName.setText(bean.getDir().substring(bean.getDir().lastIndexOf("/")));
        holder.mDirCount.setText(bean.getCount() + "");


        return convertView;
    }

    private class ViewHolder{

        ImageView mImg;
        TextView mDirName;
        TextView mDirCount;
    }
}
