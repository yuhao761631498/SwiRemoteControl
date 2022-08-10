package com.swi.remotecontrol.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.swi.remotecontrol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 测试数据接收的适配器
 * <p>
 * 创建时间: 2022/8/10 15:36
 *
 * @author yuhao
 */
public class TestAdapter extends BaseAdapter {

    private final Context context;

    private List<String> data;

    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_test, viewGroup, false);
            view.setTag(viewHolder);
            viewHolder.tv_item = view.findViewById(R.id.tv_item);
        } else {
            viewHolder = ((ViewHolder) view.getTag());
        }
        viewHolder.tv_item.setText(data.get(i));
        return view;
    }

    class ViewHolder {
        public TextView tv_item;
    }

    public void setData(List<String> receiveData) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(receiveData);
        notifyDataSetChanged();
    }

    public void setData(String str) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(str);
        notifyDataSetChanged();
    }
}
