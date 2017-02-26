package com.student0.www.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.student0.www.recyclerview.DataModel;
import com.student0.www.recyclerview.DataModelOne;
import com.student0.www.recyclerview.R;

/**
 * Created by willj on 2017/2/21.
 */

public class TypeOneViewHolder extends RecyclerView.ViewHolder{

    public ImageView avatar;

    public TextView name;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        name = (TextView) itemView.findViewById(R.id.name);
    }

    public void bindHolder(DataModelOne model){
        avatar.setImageResource(model.avatarColor);
        name.setText(model.name);
    }
}
