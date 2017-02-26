package com.student0.www.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.student0.www.holder.TypeOneViewHolder;
import com.student0.www.holder.TypeThreeViewHolder;
import com.student0.www.holder.TypeTwoViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by willj on 2017/2/21.
 */

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    private LayoutInflater layoutInflater;
    private List<DataModel> list = new ArrayList<>();

    private List<Integer> types = new ArrayList<>();
    private Map<Integer, Integer> positions = new HashMap<>();
    public DemoAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    private List<DataModelOne> list1;
    private List<DataModelTwo> list2;
    private List<DataModelThree> list3;


    public void addList(List<DataModelOne> list1, List<DataModelTwo> list2, List<DataModelThree> list3){
        addListByType(TYPE_ONE, list1);
        addListByType(TYPE_TWO, list2);
        addListByType(TYPE_THREE, list3);

        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;

    }

    public void addListByType(int type, List list){
        //设置list的起始位置,加载必须连续，否则将记录显示错误
        positions.put(type, types.size());
        for (int i = 0; i < list.size(); i ++){
            types.add(type);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //return list.get(position).type;
        return types.get(position);
    }


    //建立视图
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (viewType){
//            case DataModel.TYPE_ONE:
//                return new TypeOneViewHolder(layoutInflater.inflate(R.layout.item_type_one, parent, false));
//            case DataModel.TYPE_TWO:
//                return new TypeTwoViewHolder(layoutInflater.inflate(R.layout.item_type_two, parent, false));
//            case DataModel.TYPE_THREE:
//                return new TypeThreeViewHolder(layoutInflater.inflate(R.layout.item_type_three, parent, false));
//        }
        switch (viewType){
            case TYPE_ONE:
                return new TypeOneViewHolder(layoutInflater.inflate(R.layout.item_type_one, parent, false));
            case TYPE_TWO:
                return new TypeTwoViewHolder(layoutInflater.inflate(R.layout.item_type_two, parent, false));
            case TYPE_THREE:
                return new TypeThreeViewHolder(layoutInflater.inflate(R.layout.item_type_three, parent, false));
        }
        return null;
    }

    //填塞数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
 //       int viewType = getItemViewType(position);
//        switch (viewType){
//            case DataModel.TYPE_ONE:
//                ((TypeOneViewHolder)(holder)).bindHolder(list.get(position));
//               break;
//            case DataModel.TYPE_TWO:
//                ((TypeTwoViewHolder)(holder)).bindHolder(list.get(position));
//                break;
//            case DataModel.TYPE_THREE:
//                ((TypeThreeViewHolder)(holder)).bindHolder(list.get(position));
//                break;
//        }
        int viewType = getItemViewType(position);
        int realPosition = position - positions.get(viewType);
        switch (viewType){
            case TYPE_ONE:
                ((TypeOneViewHolder)(holder)).bindHolder(list1.get(realPosition));
                break;
            case TYPE_TWO:
                ((TypeTwoViewHolder)(holder)).bindHolder(list2.get(realPosition));
                break;
            case TYPE_THREE:
                ((TypeThreeViewHolder)(holder)).bindHolder(list3.get(realPosition));
                break;
        }
    }

    @Override
    public int getItemCount() {
        //return list.size();

        return types.size();
    }
}
