package com.optimus.se.lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> items;

    public ItemAdapter(Context context, int layout, ArrayList<String> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    private class ViewHolder {
        TextView tv_item;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tv_item = view.findViewById(R.id.tv_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String item = items.get(i);
        holder.tv_item.setText(item);

        return view;
    }
}
