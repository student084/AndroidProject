package com.student0.www.tools;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.student0.www.net.Message;
import com.student0.www.secret.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.tag;

/**
 * Created by willj on 2017/2/17.
 */

public class AtyTimelineMessageListAdapter extends BaseAdapter{

    private Context context = null;
    private List<Message> data = new ArrayList<Message>();

    public AtyTimelineMessageListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Message getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.aty_timeline_list_cell, null);

            //优化显示
            convertView.setTag(new ListCell((TextView) convertView.findViewById(R.id.tvCellLabel)));
        }
        ListCell listCell = (ListCell)convertView.getTag();

        Message message = getItem(position);
        listCell.getTvCellLabel().setText(message.getMsg());
       //TextView tvCellLabel = (TextView) convertView.findViewById(R.id.tvCellLabel);
        return convertView;
    }

    public Context getContext(){
        return context;
    }

    public void addAll(List<Message> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        this.data.clear();
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
