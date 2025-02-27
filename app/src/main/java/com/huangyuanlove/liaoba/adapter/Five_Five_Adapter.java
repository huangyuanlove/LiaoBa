package com.huangyuanlove.liaoba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridLayout;

import com.huangyuanlove.liaoba.R;

/**
 * Created by huangyuan on 15-12-21.
 */
public class Five_Five_Adapter extends BaseAdapter {
    private Context context;
    private int[][] datas;
    private WindowManager wm;
    private int width;

    public Five_Five_Adapter(Context context, int[][] datas) {
        super();
        this.context = context;
        this.datas = datas;

         wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        width = (wm.getDefaultDisplay().getWidth()-80)/5;

    }

    @Override
    public int getCount() {
        return 25;
    }

    @Override
    public Object getItem(int position) {
        return datas[position/5][position%5];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {//返回指定数据位置的UI类型
        return datas[position/5][position%5];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(getItemViewType(position)==1){
            convertView=LayoutInflater.from(context).inflate(R.layout.five_five_close, parent,false);

        }else{
            convertView= LayoutInflater.from(context).inflate(R.layout.five_five_open,parent,false);
        }

        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = width;
        convertView.setLayoutParams(layoutParams);

        return convertView;
    }
}
