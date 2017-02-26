package com.student0.www.photoread;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.student0.www.bean.FolderBean;
import com.student0.www.util.ImageLoader;

import java.util.List;

/**
 * Created by willj on 2017/2/22.
 */

public class ListImageDirPopupWindow extends PopupWindow {

    private int mWidth;
    private int mHeight;
    private View mConvertView;
    private ListView mListView;
    private List<FolderBean> mDatas;


    public ListImageDirPopupWindow(Context context, List<FolderBean> datas){

        calWidthAndHeight(context);

        mConvertView = LayoutInflater.from(context).inflate(R.layout.popup_main, null);
        mDatas = datas;

        setContentView(mConvertView);
        setWidth(mWidth);
        setHeight((int) (mHeight * 0.7));

        setFocusable(true);
        setTouchable(true);
        //外部可点击
        setOutsideTouchable(true);
        //点击外部区域使其消失
        setBackgroundDrawable(new BitmapDrawable());

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
                {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        initView(context);
        initEvent();

    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListView != null){
                    //呼叫回收
                    System.gc();
                    mListener.onSelected(mDatas.get(position));
                }
            }
        });
    }


    private void initView(Context context) {
        mListView = (ListView)mConvertView.findViewById(R.id.id_list_dir);
        mListView.setAdapter(new ListDirAdapter(context, mDatas));
    }

    private void calWidthAndHeight(Context context) {
       WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mWidth = outMetrics.widthPixels;
        mHeight = (int)(outMetrics.heightPixels);
    }
//设置回调接口，通知mainActivity相关的点击消息
public interface OnDirSelectListener{
    void onSelected(FolderBean folderBean);
}
    public OnDirSelectListener mListener;
    public void setOnDirSelectedListener(OnDirSelectListener mListener){
        this.mListener = mListener;

    }

}
