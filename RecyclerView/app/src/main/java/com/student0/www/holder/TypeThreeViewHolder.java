package com.student0.www.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.student0.www.recyclerview.DataModel;
import com.student0.www.recyclerview.DataModelThree;
import com.student0.www.recyclerview.R;

/**
 * Created by willj on 2017/2/21.
 */

public class TypeThreeViewHolder extends RecyclerView.ViewHolder{

    public ImageView contentImage;

    public ImageView avatar;

    public TextView name;

    public TextView content;
    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        contentImage = (ImageView) itemView.findViewById(R.id.contentImage);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    public void bindHolder(DataModelThree model){
        contentImage.setImageResource(model.contentColor);
        avatar.setImageResource(model.avatarColor);
        name.setText(model.name);
        content.setText(model.content);
    }
}
