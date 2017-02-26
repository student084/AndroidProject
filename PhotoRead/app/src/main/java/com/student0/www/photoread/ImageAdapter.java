package com.student0.www.photoread;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.student0.www.util.ImageLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by willj on 2017/2/21.
 */

public class ImageAdapter extends BaseAdapter implements AbsListView.OnScrollListener{

    private static Set<String> mSelectedImg = new HashSet<String>();

    private String mDirPath;
    private List<String> mImgPaths;
    private LayoutInflater mInflater;

    public ImageAdapter(Context context, List<String> mDatas, String dirPath) {
        this.mDirPath = dirPath;
        this.mImgPaths = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mImgPaths.size();
    }

    @Override
    public Object getItem(int position) {
        return mImgPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImg = (ImageView)convertView.findViewById(R.id.id_item_image);
            viewHolder.mSelect = (ImageButton)convertView.findViewById(R.id.id_item_select);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.mImg.setDrawingCacheEnabled(false);
        final ViewHolder finalViewHolder = viewHolder;
        final String filePath = mDirPath + "/" + mImgPaths.get(position);
        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 已经被选择则清除，未被选择则加入
                 * */

                if (mSelectedImg.contains(filePath)){
                    mSelectedImg.remove(filePath);
                    //改变样式
                    finalViewHolder.mImg.setColorFilter(null);
                    finalViewHolder.mSelect.setImageResource(R.mipmap.checkbox_unchecked);

                }else{
                    mSelectedImg.add(filePath);
                    finalViewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
                    finalViewHolder.mSelect.setImageResource(R.mipmap.checkbox_checked);
                }

            }
        });
       viewHolder.mImg.setImageDrawable(null);
        viewHolder.mSelect.setImageResource(R.mipmap.checkbox_unchecked);
        if (mSelectedImg.contains(filePath)){
            finalViewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
            finalViewHolder.mSelect.setImageResource(R.mipmap.checkbox_checked);
        }
        ImageLoader.getInstance(4, ImageLoader.Type.LIFO).loadImage(mDirPath + "/" +  mImgPaths.get(position), viewHolder.mImg);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    private class ViewHolder{
        ImageView mImg;
        ImageButton mSelect;

    }


}
