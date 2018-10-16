package com.busnow.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.busnow.entity.BusHere;
import com.busnow.entity.StopData;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<BusHere> mData;

    public MyAdapter() {}

    public MyAdapter(List<BusHere> mData,Context mContext) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
            holder = new ViewHolder();
            holder.bus_number = (TextView) convertView.findViewById(R.id.bus_number);
            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bus_number.setText(mData.get(position).getBusCode());
        holder.txt_content.setText(String.valueOf(mData.get(position).getNext().getComingTime()));
        return convertView;
    }

    private class ViewHolder{
        TextView bus_number;
        TextView txt_content;
    }

}
