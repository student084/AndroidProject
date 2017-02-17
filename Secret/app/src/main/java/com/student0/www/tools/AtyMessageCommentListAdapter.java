package com.student0.www.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.student0.www.net.Comment;
import com.student0.www.secret.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willj on 2017/2/17.
 */

public class AtyMessageCommentListAdapter extends BaseAdapter {
    public AtyMessageCommentListAdapter( Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.aty_comments_list_cell, null);

            //优化显示
            convertView.setTag(new AtyMessageCommentListAdapter.ListCell((TextView) convertView.findViewById(R.id.tvCellLabel)));
        }
        ListCell listCell = (ListCell)convertView.getTag();

        Comment comment = getItem(position);
        listCell.getTvCellLabel().setText(comment.getContent());
        //TextView tvCellLabel = (TextView) convertView.findViewById(R.id.tvCellLabel);
        return convertView;
    }

    private List<Comment> comments = new ArrayList<Comment>();
    private Context context;

    public Context getContext(){
        return this.context;
    }

    public void addAll(List<Comment> data){
        this.comments.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        this.comments.clear();
        notifyDataSetChanged();
    }

    public static class ListCell{
        public ListCell(TextView tvCellLabel){
            this.tvCellLabel = tvCellLabel;
        }

        private TextView tvCellLabel;

        public TextView getTvCellLabel(){
            return tvCellLabel;
        }
    }
}
